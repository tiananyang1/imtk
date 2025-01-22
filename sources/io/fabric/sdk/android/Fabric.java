package io.fabric.sdk.android;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.stub.StubApp;
import io.fabric.sdk.android.ActivityLifecycleManager;
import io.fabric.sdk.android.services.common.IdManager;
import io.fabric.sdk.android.services.concurrency.DependsOn;
import io.fabric.sdk.android.services.concurrency.PriorityThreadPoolExecutor;
import io.fabric.sdk.android.services.concurrency.UnmetDependencyException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes08-dex2jar.jar:io/fabric/sdk/android/Fabric.class */
public class Fabric {
    static final boolean DEFAULT_DEBUGGABLE = false;
    static final Logger DEFAULT_LOGGER = new DefaultLogger();
    static final String ROOT_DIR = ".Fabric";
    public static final String TAG = "Fabric";
    static volatile Fabric singleton;
    private WeakReference<Activity> activity;
    private ActivityLifecycleManager activityLifecycleManager;
    private final Context context;
    final boolean debuggable;
    private final ExecutorService executorService;
    private final IdManager idManager;
    private final InitializationCallback<Fabric> initializationCallback;
    private AtomicBoolean initialized = new AtomicBoolean(false);
    private final InitializationCallback<?> kitInitializationCallback;
    private final Map<Class<? extends Kit>, Kit> kits;
    final Logger logger;
    private final Handler mainHandler;

    /* loaded from: classes08-dex2jar.jar:io/fabric/sdk/android/Fabric$Builder.class */
    public static class Builder {
        private String appIdentifier;
        private String appInstallIdentifier;
        private final Context context;
        private boolean debuggable;
        private Handler handler;
        private InitializationCallback<Fabric> initializationCallback;
        private Kit[] kits;
        private Logger logger;
        private PriorityThreadPoolExecutor threadPoolExecutor;

        public Builder(Context context) {
            if (context == null) {
                throw new IllegalArgumentException("Context must not be null.");
            }
            this.context = context;
        }

        public Builder appIdentifier(String str) {
            if (str == null) {
                throw new IllegalArgumentException("appIdentifier must not be null.");
            }
            if (this.appIdentifier != null) {
                throw new IllegalStateException("appIdentifier already set.");
            }
            this.appIdentifier = str;
            return this;
        }

        public Builder appInstallIdentifier(String str) {
            if (str == null) {
                throw new IllegalArgumentException("appInstallIdentifier must not be null.");
            }
            if (this.appInstallIdentifier != null) {
                throw new IllegalStateException("appInstallIdentifier already set.");
            }
            this.appInstallIdentifier = str;
            return this;
        }

        public Fabric build() {
            if (this.threadPoolExecutor == null) {
                this.threadPoolExecutor = PriorityThreadPoolExecutor.create();
            }
            if (this.handler == null) {
                this.handler = new Handler(Looper.getMainLooper());
            }
            if (this.logger == null) {
                if (this.debuggable) {
                    this.logger = new DefaultLogger(3);
                } else {
                    this.logger = new DefaultLogger();
                }
            }
            if (this.appIdentifier == null) {
                this.appIdentifier = this.context.getPackageName();
            }
            if (this.initializationCallback == null) {
                this.initializationCallback = InitializationCallback.EMPTY;
            }
            Kit[] kitArr = this.kits;
            Map hashMap = kitArr == null ? new HashMap() : Fabric.getKitMap(Arrays.asList(kitArr));
            Context origApplicationContext = StubApp.getOrigApplicationContext(this.context.getApplicationContext());
            return new Fabric(origApplicationContext, hashMap, this.threadPoolExecutor, this.handler, this.logger, this.debuggable, this.initializationCallback, new IdManager(origApplicationContext, this.appIdentifier, this.appInstallIdentifier, hashMap.values()), Fabric.extractActivity(this.context));
        }

        public Builder debuggable(boolean z) {
            this.debuggable = z;
            return this;
        }

        @Deprecated
        public Builder executorService(ExecutorService executorService) {
            return this;
        }

        @Deprecated
        public Builder handler(Handler handler) {
            return this;
        }

        public Builder initializationCallback(InitializationCallback<Fabric> initializationCallback) {
            if (initializationCallback == null) {
                throw new IllegalArgumentException("initializationCallback must not be null.");
            }
            if (this.initializationCallback != null) {
                throw new IllegalStateException("initializationCallback already set.");
            }
            this.initializationCallback = initializationCallback;
            return this;
        }

        public Builder kits(Kit... kitArr) {
            if (this.kits != null) {
                throw new IllegalStateException("Kits already set.");
            }
            this.kits = kitArr;
            return this;
        }

        public Builder logger(Logger logger) {
            if (logger == null) {
                throw new IllegalArgumentException("Logger must not be null.");
            }
            if (this.logger != null) {
                throw new IllegalStateException("Logger already set.");
            }
            this.logger = logger;
            return this;
        }

        public Builder threadPoolExecutor(PriorityThreadPoolExecutor priorityThreadPoolExecutor) {
            if (priorityThreadPoolExecutor == null) {
                throw new IllegalArgumentException("PriorityThreadPoolExecutor must not be null.");
            }
            if (this.threadPoolExecutor != null) {
                throw new IllegalStateException("PriorityThreadPoolExecutor already set.");
            }
            this.threadPoolExecutor = priorityThreadPoolExecutor;
            return this;
        }
    }

    Fabric(Context context, Map<Class<? extends Kit>, Kit> map, PriorityThreadPoolExecutor priorityThreadPoolExecutor, Handler handler, Logger logger, boolean z, InitializationCallback initializationCallback, IdManager idManager, Activity activity) {
        this.context = context;
        this.kits = map;
        this.executorService = priorityThreadPoolExecutor;
        this.mainHandler = handler;
        this.logger = logger;
        this.debuggable = z;
        this.initializationCallback = initializationCallback;
        this.kitInitializationCallback = createKitInitializationCallback(map.size());
        this.idManager = idManager;
        setCurrentActivity(activity);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static void addToKitMap(Map<Class<? extends Kit>, Kit> map, Collection<? extends Kit> collection) {
        for (Object obj : collection) {
            map.put(obj.getClass(), obj);
            if (obj instanceof KitGroup) {
                addToKitMap(map, ((KitGroup) obj).getKits());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Activity extractActivity(Context context) {
        if (context instanceof Activity) {
            return (Activity) context;
        }
        return null;
    }

    public static <T extends Kit> T getKit(Class<T> cls) {
        return (T) singleton().kits.get(cls);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Map<Class<? extends Kit>, Kit> getKitMap(Collection<? extends Kit> collection) {
        HashMap hashMap = new HashMap(collection.size());
        addToKitMap(hashMap, collection);
        return hashMap;
    }

    public static Logger getLogger() {
        return singleton == null ? DEFAULT_LOGGER : singleton.logger;
    }

    private void init() {
        this.activityLifecycleManager = new ActivityLifecycleManager(this.context);
        this.activityLifecycleManager.registerCallbacks(new ActivityLifecycleManager.Callbacks() { // from class: io.fabric.sdk.android.Fabric.1
            @Override // io.fabric.sdk.android.ActivityLifecycleManager.Callbacks
            public void onActivityCreated(Activity activity, Bundle bundle) {
                Fabric.this.setCurrentActivity(activity);
            }

            @Override // io.fabric.sdk.android.ActivityLifecycleManager.Callbacks
            public void onActivityResumed(Activity activity) {
                Fabric.this.setCurrentActivity(activity);
            }

            @Override // io.fabric.sdk.android.ActivityLifecycleManager.Callbacks
            public void onActivityStarted(Activity activity) {
                Fabric.this.setCurrentActivity(activity);
            }
        });
        initializeKits(this.context);
    }

    public static boolean isDebuggable() {
        if (singleton == null) {
            return false;
        }
        return singleton.debuggable;
    }

    public static boolean isInitialized() {
        return singleton != null && singleton.initialized.get();
    }

    private static void setFabric(Fabric fabric) {
        singleton = fabric;
        fabric.init();
    }

    static Fabric singleton() {
        if (singleton != null) {
            return singleton;
        }
        throw new IllegalStateException("Must Initialize Fabric before using singleton()");
    }

    public static Fabric with(Context context, Kit... kitArr) {
        if (singleton == null) {
            synchronized (Fabric.class) {
                try {
                    if (singleton == null) {
                        setFabric(new Builder(context).kits(kitArr).build());
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return singleton;
    }

    public static Fabric with(Fabric fabric) {
        if (singleton == null) {
            synchronized (Fabric.class) {
                try {
                    if (singleton == null) {
                        setFabric(fabric);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return singleton;
    }

    void addAnnotatedDependencies(Map<Class<? extends Kit>, Kit> map, Kit kit) {
        DependsOn dependsOn = kit.dependsOnAnnotation;
        if (dependsOn == null) {
            return;
        }
        Class<?>[] value = dependsOn.value();
        int length = value.length;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length) {
                return;
            }
            Class<?> cls = value[i2];
            if (cls.isInterface()) {
                for (Kit kit2 : map.values()) {
                    if (cls.isAssignableFrom(kit2.getClass())) {
                        kit.initializationTask.addDependency(kit2.initializationTask);
                    }
                }
            } else {
                if (map.get(cls) == null) {
                    throw new UnmetDependencyException("Referenced Kit was null, does the kit exist?");
                }
                kit.initializationTask.addDependency(map.get(cls).initializationTask);
            }
            i = i2 + 1;
        }
    }

    InitializationCallback<?> createKitInitializationCallback(final int i) {
        return new InitializationCallback() { // from class: io.fabric.sdk.android.Fabric.2
            final CountDownLatch kitInitializedLatch;

            {
                this.kitInitializedLatch = new CountDownLatch(i);
            }

            @Override // io.fabric.sdk.android.InitializationCallback
            public void failure(Exception exc) {
                Fabric.this.initializationCallback.failure(exc);
            }

            @Override // io.fabric.sdk.android.InitializationCallback
            public void success(Object obj) {
                this.kitInitializedLatch.countDown();
                if (this.kitInitializedLatch.getCount() == 0) {
                    Fabric.this.initialized.set(true);
                    Fabric.this.initializationCallback.success(Fabric.this);
                }
            }
        };
    }

    public ActivityLifecycleManager getActivityLifecycleManager() {
        return this.activityLifecycleManager;
    }

    public String getAppIdentifier() {
        return this.idManager.getAppIdentifier();
    }

    public String getAppInstallIdentifier() {
        return this.idManager.getAppInstallIdentifier();
    }

    public Activity getCurrentActivity() {
        WeakReference<Activity> weakReference = this.activity;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    public ExecutorService getExecutorService() {
        return this.executorService;
    }

    public String getIdentifier() {
        return "io.fabric.sdk.android:fabric";
    }

    public Collection<Kit> getKits() {
        return this.kits.values();
    }

    Future<Map<String, KitInfo>> getKitsFinderFuture(Context context) {
        return getExecutorService().submit(new FabricKitsFinder(context.getPackageCodePath()));
    }

    public Handler getMainHandler() {
        return this.mainHandler;
    }

    public String getVersion() {
        return "1.4.2.22";
    }

    void initializeKits(Context context) {
        StringBuilder sb;
        Future<Map<String, KitInfo>> kitsFinderFuture = getKitsFinderFuture(context);
        Collection<Kit> kits = getKits();
        Onboarding onboarding = new Onboarding(kitsFinderFuture, kits);
        ArrayList<Kit> arrayList = new ArrayList(kits);
        Collections.sort(arrayList);
        onboarding.injectParameters(context, this, InitializationCallback.EMPTY, this.idManager);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((Kit) it.next()).injectParameters(context, this, this.kitInitializationCallback, this.idManager);
        }
        onboarding.initialize();
        if (getLogger().isLoggable(TAG, 3)) {
            sb = new StringBuilder("Initializing ");
            sb.append(getIdentifier());
            sb.append(" [Version: ");
            sb.append(getVersion());
            sb.append("], with the following kits:\n");
        } else {
            sb = null;
        }
        for (Kit kit : arrayList) {
            kit.initializationTask.addDependency(onboarding.initializationTask);
            addAnnotatedDependencies(this.kits, kit);
            kit.initialize();
            if (sb != null) {
                sb.append(kit.getIdentifier());
                sb.append(" [Version: ");
                sb.append(kit.getVersion());
                sb.append("]\n");
            }
        }
        if (sb != null) {
            getLogger().mo2870d(TAG, sb.toString());
        }
    }

    public Fabric setCurrentActivity(Activity activity) {
        this.activity = new WeakReference<>(activity);
        return this;
    }
}
