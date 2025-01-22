package io.fabric.sdk.android.services.concurrency;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.util.Log;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes08-dex2jar.jar:io/fabric/sdk/android/services/concurrency/AsyncTask.class */
public abstract class AsyncTask<Params, Progress, Result> {
    private static final int CORE_POOL_SIZE;
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int KEEP_ALIVE = 1;
    private static final String LOG_TAG = "AsyncTask";
    private static final int MAXIMUM_POOL_SIZE;
    private static final int MESSAGE_POST_PROGRESS = 2;
    private static final int MESSAGE_POST_RESULT = 1;
    public static final Executor SERIAL_EXECUTOR;
    public static final Executor THREAD_POOL_EXECUTOR;
    private static volatile Executor defaultExecutor;
    private static final InternalHandler handler;
    private static final BlockingQueue<Runnable> poolWorkQueue;
    private static final ThreadFactory threadFactory;
    private volatile Status status = Status.PENDING;
    private final AtomicBoolean cancelled = new AtomicBoolean();
    private final AtomicBoolean taskInvoked = new AtomicBoolean();
    private final WorkerRunnable<Params, Result> worker = new WorkerRunnable<Params, Result>() { // from class: io.fabric.sdk.android.services.concurrency.AsyncTask.2
        @Override // java.util.concurrent.Callable
        public Result call() throws Exception {
            AsyncTask.this.taskInvoked.set(true);
            Process.setThreadPriority(10);
            AsyncTask asyncTask = AsyncTask.this;
            return (Result) asyncTask.postResult(asyncTask.doInBackground(this.params));
        }
    };
    private final FutureTask<Result> future = new FutureTask<Result>(this.worker) { // from class: io.fabric.sdk.android.services.concurrency.AsyncTask.3
        @Override // java.util.concurrent.FutureTask
        protected void done() {
            try {
                AsyncTask.this.postResultIfNotInvoked(get());
            } catch (InterruptedException e) {
                Log.w(AsyncTask.LOG_TAG, e);
            } catch (CancellationException e2) {
                AsyncTask.this.postResultIfNotInvoked(null);
            } catch (ExecutionException e3) {
                throw new RuntimeException("An error occured while executing doInBackground()", e3.getCause());
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: io.fabric.sdk.android.services.concurrency.AsyncTask$4 */
    /* loaded from: classes08-dex2jar.jar:io/fabric/sdk/android/services/concurrency/AsyncTask$4.class */
    public static /* synthetic */ class C09134 {

        /* renamed from: $SwitchMap$io$fabric$sdk$android$services$concurrency$AsyncTask$Status */
        static final /* synthetic */ int[] f2759x37f1d2cc = new int[Status.values().length];

        /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:8:0x0020
            	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1166)
            	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:1022)
            	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:55)
            */
        static {
            /*
                io.fabric.sdk.android.services.concurrency.AsyncTask$Status[] r0 = io.fabric.sdk.android.services.concurrency.AsyncTask.Status.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                io.fabric.sdk.android.services.concurrency.AsyncTask.C09134.f2759x37f1d2cc = r0
                int[] r0 = io.fabric.sdk.android.services.concurrency.AsyncTask.C09134.f2759x37f1d2cc     // Catch: java.lang.NoSuchFieldError -> L20
                io.fabric.sdk.android.services.concurrency.AsyncTask$Status r1 = io.fabric.sdk.android.services.concurrency.AsyncTask.Status.RUNNING     // Catch: java.lang.NoSuchFieldError -> L20
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L20
                r2 = 1
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L20
            L14:
                int[] r0 = io.fabric.sdk.android.services.concurrency.AsyncTask.C09134.f2759x37f1d2cc     // Catch: java.lang.NoSuchFieldError -> L20 java.lang.NoSuchFieldError -> L24
                io.fabric.sdk.android.services.concurrency.AsyncTask$Status r1 = io.fabric.sdk.android.services.concurrency.AsyncTask.Status.FINISHED     // Catch: java.lang.NoSuchFieldError -> L24
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L24
                r2 = 2
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L24
                return
            L20:
                r4 = move-exception
                goto L14
            L24:
                r4 = move-exception
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.fabric.sdk.android.services.concurrency.AsyncTask.C09134.m4128clinit():void");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes08-dex2jar.jar:io/fabric/sdk/android/services/concurrency/AsyncTask$AsyncTaskResult.class */
    public static class AsyncTaskResult<Data> {
        final Data[] data;
        final AsyncTask task;

        AsyncTaskResult(AsyncTask asyncTask, Data... dataArr) {
            this.task = asyncTask;
            this.data = dataArr;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes08-dex2jar.jar:io/fabric/sdk/android/services/concurrency/AsyncTask$InternalHandler.class */
    public static class InternalHandler extends Handler {
        public InternalHandler() {
            super(Looper.getMainLooper());
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            AsyncTaskResult asyncTaskResult = (AsyncTaskResult) message.obj;
            int i = message.what;
            if (i == 1) {
                asyncTaskResult.task.finish(asyncTaskResult.data[0]);
            } else {
                if (i != 2) {
                    return;
                }
                asyncTaskResult.task.onProgressUpdate(asyncTaskResult.data);
            }
        }
    }

    /* loaded from: classes08-dex2jar.jar:io/fabric/sdk/android/services/concurrency/AsyncTask$SerialExecutor.class */
    private static class SerialExecutor implements Executor {
        Runnable active;
        final LinkedList<Runnable> tasks;

        private SerialExecutor() {
            this.tasks = new LinkedList<>();
        }

        @Override // java.util.concurrent.Executor
        public void execute(final Runnable runnable) {
            synchronized (this) {
                this.tasks.offer(new Runnable() { // from class: io.fabric.sdk.android.services.concurrency.AsyncTask.SerialExecutor.1
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            runnable.run();
                        } finally {
                            SerialExecutor.this.scheduleNext();
                        }
                    }
                });
                if (this.active == null) {
                    scheduleNext();
                }
            }
        }

        protected void scheduleNext() {
            synchronized (this) {
                Runnable poll = this.tasks.poll();
                this.active = poll;
                if (poll != null) {
                    AsyncTask.THREAD_POOL_EXECUTOR.execute(this.active);
                }
            }
        }
    }

    /* loaded from: classes08-dex2jar.jar:io/fabric/sdk/android/services/concurrency/AsyncTask$Status.class */
    public enum Status {
        PENDING,
        RUNNING,
        FINISHED
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes08-dex2jar.jar:io/fabric/sdk/android/services/concurrency/AsyncTask$WorkerRunnable.class */
    public static abstract class WorkerRunnable<Params, Result> implements Callable<Result> {
        Params[] params;

        private WorkerRunnable() {
        }
    }

    static {
        int i = CPU_COUNT;
        CORE_POOL_SIZE = i + 1;
        MAXIMUM_POOL_SIZE = (i * 2) + 1;
        threadFactory = new ThreadFactory() { // from class: io.fabric.sdk.android.services.concurrency.AsyncTask.1
            private final AtomicInteger count = new AtomicInteger(1);

            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(Runnable runnable) {
                return new Thread(runnable, "AsyncTask #" + this.count.getAndIncrement());
            }
        };
        poolWorkQueue = new LinkedBlockingQueue(128);
        THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, 1L, TimeUnit.SECONDS, poolWorkQueue, threadFactory);
        SERIAL_EXECUTOR = new SerialExecutor();
        handler = new InternalHandler();
        defaultExecutor = SERIAL_EXECUTOR;
    }

    public static void execute(Runnable runnable) {
        defaultExecutor.execute(runnable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void finish(Result result) {
        if (isCancelled()) {
            onCancelled(result);
        } else {
            onPostExecute(result);
        }
        this.status = Status.FINISHED;
    }

    public static void init() {
        handler.getLooper();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Result postResult(Result result) {
        handler.obtainMessage(1, new AsyncTaskResult(this, result)).sendToTarget();
        return result;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postResultIfNotInvoked(Result result) {
        if (this.taskInvoked.get()) {
            return;
        }
        postResult(result);
    }

    public static void setDefaultExecutor(Executor executor) {
        defaultExecutor = executor;
    }

    public final boolean cancel(boolean z) {
        this.cancelled.set(true);
        return this.future.cancel(z);
    }

    protected abstract Result doInBackground(Params... paramsArr);

    public final AsyncTask<Params, Progress, Result> execute(Params... paramsArr) {
        return executeOnExecutor(defaultExecutor, paramsArr);
    }

    public final AsyncTask<Params, Progress, Result> executeOnExecutor(Executor executor, Params... paramsArr) {
        if (this.status != Status.PENDING) {
            int i = C09134.f2759x37f1d2cc[this.status.ordinal()];
            if (i == 1) {
                throw new IllegalStateException("Cannot execute task: the task is already running.");
            }
            if (i == 2) {
                throw new IllegalStateException("Cannot execute task: the task has already been executed (a task can be executed only once)");
            }
        }
        this.status = Status.RUNNING;
        onPreExecute();
        this.worker.params = paramsArr;
        executor.execute(this.future);
        return this;
    }

    public final Result get() throws InterruptedException, ExecutionException {
        return this.future.get();
    }

    public final Result get(long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        return this.future.get(j, timeUnit);
    }

    public final Status getStatus() {
        return this.status;
    }

    public final boolean isCancelled() {
        return this.cancelled.get();
    }

    protected void onCancelled() {
    }

    protected void onCancelled(Result result) {
        onCancelled();
    }

    protected void onPostExecute(Result result) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onPreExecute() {
    }

    protected void onProgressUpdate(Progress... progressArr) {
    }

    protected final void publishProgress(Progress... progressArr) {
        if (isCancelled()) {
            return;
        }
        handler.obtainMessage(2, new AsyncTaskResult(this, progressArr)).sendToTarget();
    }
}
