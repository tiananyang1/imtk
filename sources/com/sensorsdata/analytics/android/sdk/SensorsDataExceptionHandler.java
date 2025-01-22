package com.sensorsdata.analytics.android.sdk;

import android.os.Process;
import com.sensorsdata.analytics.android.sdk.SensorsDataAPI;
import com.sensorsdata.analytics.android.sdk.data.DbAdapter;
import com.sensorsdata.analytics.android.sdk.util.SensorsDataTimer;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread;
import org.json.JSONObject;

/* loaded from: classes08-dex2jar.jar:com/sensorsdata/analytics/android/sdk/SensorsDataExceptionHandler.class */
class SensorsDataExceptionHandler implements Thread.UncaughtExceptionHandler {
    private static final int SLEEP_TIMEOUT_MS = 3000;
    private static boolean isTrackCrash;
    private static SensorsDataExceptionHandler sInstance;
    private Thread.UncaughtExceptionHandler mDefaultExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();

    private SensorsDataExceptionHandler() {
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void enableAppCrash() {
        isTrackCrash = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void init() {
        synchronized (SensorsDataExceptionHandler.class) {
            try {
                if (sInstance == null) {
                    sInstance = new SensorsDataExceptionHandler();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private void killProcessAndExit() {
        try {
            Process.killProcess(Process.myPid());
            System.exit(10);
        } catch (Exception e) {
        }
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, final Throwable th) {
        try {
            SensorsDataAPI.allInstances(new SensorsDataAPI.InstanceProcessor() { // from class: com.sensorsdata.analytics.android.sdk.SensorsDataExceptionHandler.1
                @Override // com.sensorsdata.analytics.android.sdk.SensorsDataAPI.InstanceProcessor
                public void process(SensorsDataAPI sensorsDataAPI) {
                    if (SensorsDataExceptionHandler.isTrackCrash) {
                        try {
                            JSONObject jSONObject = new JSONObject();
                            try {
                                StringWriter stringWriter = new StringWriter();
                                PrintWriter printWriter = new PrintWriter(stringWriter);
                                th.printStackTrace(printWriter);
                                for (Throwable cause = th.getCause(); cause != null; cause = cause.getCause()) {
                                    cause.printStackTrace(printWriter);
                                }
                                printWriter.close();
                                jSONObject.put("app_crashed_reason", stringWriter.toString());
                            } catch (Exception e) {
                                SALog.printStackTrace(e);
                            }
                            sensorsDataAPI.track("AppCrashed", jSONObject);
                        } catch (Exception e2) {
                            SALog.printStackTrace(e2);
                        }
                    }
                    SensorsDataTimer.getInstance().shutdownTimerTask();
                    DbAdapter.getInstance().commitAppPausedTime(System.currentTimeMillis());
                    DbAdapter.getInstance().commitActivityCount(0);
                    sensorsDataAPI.flushSync();
                }
            });
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                SALog.printStackTrace(e);
            }
            if (this.mDefaultExceptionHandler != null) {
                this.mDefaultExceptionHandler.uncaughtException(thread, th);
            } else {
                killProcessAndExit();
            }
        } catch (Exception e2) {
        }
    }
}
