package com.sensorsdata.analytics.android.sdk;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.widget.Toast;
import com.sensorsdata.analytics.android.sdk.DebugModeSelectDialog;
import com.sensorsdata.analytics.android.sdk.SensorsDataAPI;
import com.sensorsdata.analytics.android.sdk.data.DbAdapter;
import com.sensorsdata.analytics.android.sdk.data.DbParams;
import com.sensorsdata.analytics.android.sdk.data.PersistentLoader;
import com.sensorsdata.analytics.android.sdk.data.persistent.PersistentFirstDay;
import com.sensorsdata.analytics.android.sdk.data.persistent.PersistentFirstStart;
import com.sensorsdata.analytics.android.sdk.util.AopUtil;
import com.sensorsdata.analytics.android.sdk.util.DateFormatUtils;
import com.sensorsdata.analytics.android.sdk.util.NetworkUtils;
import com.sensorsdata.analytics.android.sdk.util.SensorsDataTimer;
import com.sensorsdata.analytics.android.sdk.util.SensorsDataUtils;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import org.json.JSONObject;

@TargetApi(14)
/* loaded from: classes08-dex2jar.jar:com/sensorsdata/analytics/android/sdk/SensorsDataActivityLifecycleCallbacks.class */
class SensorsDataActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {
    private static final String APP_VERSION = "$app_version";
    private static final String EVENT_TIMER = "event_timer";
    private static final String LIB_VERSION = "$lib_version";
    private static final String TAG = "SA.LifecycleCallbacks";
    private static final int TIME_INTERVAL = 2000;
    private String app_version;
    private Handler handler;
    private boolean isMultiProcess;
    private String lib_version;
    private Context mContext;
    private final PersistentFirstDay mFirstDay;
    private final PersistentFirstStart mFirstStart;
    private final SensorsDataAPI mSensorsDataInstance;
    private int startActivityCount;
    private int startTimerCount;
    private boolean resumeFromBackground = false;
    private JSONObject activityProperty = new JSONObject();
    private JSONObject endDataProperty = new JSONObject();
    private final int MESSAGE_END = 0;
    private final String APP_END_TIME = "app_end_time";
    private final String APP_END_DATA = PersistentLoader.PersistentName.APP_END_DATA;
    private final String APP_RESET_STATE = "app_reset_state";
    private Runnable timer = new Runnable() { // from class: com.sensorsdata.analytics.android.sdk.SensorsDataActivityLifecycleCallbacks.1
        @Override // java.lang.Runnable
        public void run() {
            if (SensorsDataActivityLifecycleCallbacks.this.mSensorsDataInstance.isAutoTrackEnabled() && SensorsDataActivityLifecycleCallbacks.this.isAutoTrackAppEnd()) {
                SensorsDataActivityLifecycleCallbacks.this.generateAppEndData();
            }
        }
    };
    private DbAdapter mDbAdapter = DbAdapter.getInstance();
    private int sessionTime = this.mDbAdapter.getSessionIntervalTime();

    /* loaded from: classes08-dex2jar.jar:com/sensorsdata/analytics/android/sdk/SensorsDataActivityLifecycleCallbacks$SendDebugIdThread.class */
    class SendDebugIdThread extends Thread {
        private String distinctId;
        private String infoId;
        private String serverUrl;

        SendDebugIdThread(String str, String str2, String str3) {
            this.distinctId = str2;
            this.infoId = str3;
            this.serverUrl = str;
        }

        private void closeStream(ByteArrayOutputStream byteArrayOutputStream, OutputStream outputStream, BufferedOutputStream bufferedOutputStream, HttpURLConnection httpURLConnection) {
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (Exception e) {
                    SALog.printStackTrace(e);
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (Exception e2) {
                    SALog.printStackTrace(e2);
                }
            }
            if (bufferedOutputStream != null) {
                try {
                    bufferedOutputStream.close();
                } catch (Exception e3) {
                    SALog.printStackTrace(e3);
                }
            }
            if (httpURLConnection != null) {
                try {
                    httpURLConnection.disconnect();
                } catch (Exception e4) {
                    SALog.printStackTrace(e4);
                }
            }
        }

        private void sendHttpRequest(String str, boolean z) {
            ByteArrayOutputStream byteArrayOutputStream;
            HttpURLConnection httpURLConnection;
            BufferedOutputStream bufferedOutputStream;
            HttpURLConnection httpURLConnection2;
            OutputStream outputStream;
            Exception e;
            BufferedOutputStream bufferedOutputStream2;
            HttpURLConnection httpURLConnection3;
            ByteArrayOutputStream byteArrayOutputStream2;
            Throwable th;
            URL url;
            BufferedOutputStream bufferedOutputStream3;
            OutputStream outputStream2 = null;
            try {
                url = new URL(String.format(str + "&info_id=%s", this.infoId));
                SALog.info(SensorsDataActivityLifecycleCallbacks.TAG, String.format("DebugMode URL:%s", url), null);
                httpURLConnection2 = (HttpURLConnection) url.openConnection();
            } catch (Exception e2) {
                e = e2;
                httpURLConnection2 = null;
            } catch (Throwable th2) {
                th = th2;
                byteArrayOutputStream = null;
                httpURLConnection = null;
                bufferedOutputStream = null;
            }
            try {
                if (httpURLConnection2 == null) {
                    SALog.info(SensorsDataActivityLifecycleCallbacks.TAG, String.format("can not connect %s,shouldn't happen", url.toString()), null);
                    closeStream(null, null, null, httpURLConnection2);
                    return;
                }
                SSLSocketFactory sSLSocketFactory = SensorsDataAPI.sharedInstance().getSSLSocketFactory();
                if (sSLSocketFactory != null && (httpURLConnection2 instanceof HttpsURLConnection)) {
                    ((HttpsURLConnection) httpURLConnection2).setSSLSocketFactory(sSLSocketFactory);
                }
                httpURLConnection2.setInstanceFollowRedirects(false);
                ByteArrayOutputStream byteArrayOutputStream3 = new ByteArrayOutputStream();
                try {
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(byteArrayOutputStream3);
                    String str2 = "{\"distinct_id\": \"" + this.distinctId + "\"}";
                    outputStreamWriter.write(str2);
                    outputStreamWriter.flush();
                    SALog.info(SensorsDataActivityLifecycleCallbacks.TAG, String.format("DebugMode request body : %s", str2), null);
                    httpURLConnection2.setDoOutput(true);
                    httpURLConnection2.setUseCaches(false);
                    httpURLConnection2.setRequestMethod(HttpRequest.METHOD_POST);
                    httpURLConnection2.setRequestProperty("Content-type", "text/plain");
                    OutputStream outputStream3 = httpURLConnection2.getOutputStream();
                    try {
                        bufferedOutputStream3 = new BufferedOutputStream(outputStream3);
                    } catch (Exception e3) {
                        bufferedOutputStream2 = null;
                        outputStream = outputStream3;
                        e = e3;
                    } catch (Throwable th3) {
                        bufferedOutputStream2 = null;
                        httpURLConnection = httpURLConnection2;
                        th = th3;
                    }
                    try {
                        bufferedOutputStream3.write(byteArrayOutputStream3.toString().getBytes("UTF-8"));
                        bufferedOutputStream3.flush();
                        byteArrayOutputStream3.close();
                        int responseCode = httpURLConnection2.getResponseCode();
                        SALog.info(SensorsDataActivityLifecycleCallbacks.TAG, String.format(Locale.CHINA, "DebugMode 后端的响应码是:%d", Integer.valueOf(responseCode)), null);
                        if (!z && SensorsDataHttpURLConnectionHelper.needRedirects(responseCode)) {
                            String location = SensorsDataHttpURLConnectionHelper.getLocation(httpURLConnection2, str);
                            if (!TextUtils.isEmpty(location)) {
                                closeStream(byteArrayOutputStream3, outputStream3, bufferedOutputStream3, httpURLConnection2);
                                sendHttpRequest(location, true);
                            }
                        }
                        closeStream(byteArrayOutputStream3, outputStream3, bufferedOutputStream3, httpURLConnection2);
                    } catch (Exception e4) {
                        outputStream = outputStream3;
                        bufferedOutputStream2 = bufferedOutputStream3;
                        e = e4;
                        byteArrayOutputStream2 = byteArrayOutputStream3;
                        httpURLConnection3 = httpURLConnection2;
                        try {
                            SALog.printStackTrace(e);
                            closeStream(byteArrayOutputStream2, outputStream, bufferedOutputStream2, httpURLConnection3);
                        } catch (Throwable th4) {
                            th = th4;
                            outputStream3 = outputStream;
                            httpURLConnection = httpURLConnection3;
                            byteArrayOutputStream3 = byteArrayOutputStream2;
                            outputStream2 = outputStream3;
                            th = th;
                            bufferedOutputStream = bufferedOutputStream2;
                            byteArrayOutputStream = byteArrayOutputStream3;
                            closeStream(byteArrayOutputStream, outputStream2, bufferedOutputStream, httpURLConnection);
                            throw th;
                        }
                    } catch (Throwable th5) {
                        httpURLConnection = httpURLConnection2;
                        bufferedOutputStream2 = bufferedOutputStream3;
                        th = th5;
                        outputStream2 = outputStream3;
                        th = th;
                        bufferedOutputStream = bufferedOutputStream2;
                        byteArrayOutputStream = byteArrayOutputStream3;
                        closeStream(byteArrayOutputStream, outputStream2, bufferedOutputStream, httpURLConnection);
                        throw th;
                    }
                } catch (Exception e5) {
                    e = e5;
                    outputStream = null;
                    bufferedOutputStream2 = null;
                } catch (Throwable th6) {
                    th = th6;
                    byteArrayOutputStream = byteArrayOutputStream3;
                    httpURLConnection = httpURLConnection2;
                    bufferedOutputStream = null;
                    closeStream(byteArrayOutputStream, outputStream2, bufferedOutputStream, httpURLConnection);
                    throw th;
                }
            } catch (Exception e6) {
                e = e6;
                outputStream = null;
                e = e;
                bufferedOutputStream2 = null;
                httpURLConnection3 = httpURLConnection2;
                byteArrayOutputStream2 = null;
                SALog.printStackTrace(e);
                closeStream(byteArrayOutputStream2, outputStream, bufferedOutputStream2, httpURLConnection3);
            } catch (Throwable th7) {
                byteArrayOutputStream = null;
                httpURLConnection = httpURLConnection2;
                bufferedOutputStream = null;
                th = th7;
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            super.run();
            sendHttpRequest(this.serverUrl, false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes08-dex2jar.jar:com/sensorsdata/analytics/android/sdk/SensorsDataActivityLifecycleCallbacks$SensorsActivityStateObserver.class */
    public class SensorsActivityStateObserver extends ContentObserver {
        SensorsActivityStateObserver(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z, Uri uri) {
            super.onChange(z, uri);
            try {
                if (DbParams.getInstance().getSessionTimeUri().equals(uri)) {
                    SensorsDataActivityLifecycleCallbacks.this.sessionTime = SensorsDataActivityLifecycleCallbacks.this.mDbAdapter.getSessionIntervalTime();
                }
            } catch (Exception e) {
                SALog.printStackTrace(e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SensorsDataActivityLifecycleCallbacks(SensorsDataAPI sensorsDataAPI, PersistentFirstStart persistentFirstStart, PersistentFirstDay persistentFirstDay, Context context) {
        this.mSensorsDataInstance = sensorsDataAPI;
        this.mFirstStart = persistentFirstStart;
        this.mFirstDay = persistentFirstDay;
        this.mContext = context;
        this.isMultiProcess = this.mSensorsDataInstance.isMultiProcess();
        try {
            this.app_version = this.mContext.getPackageManager().getPackageInfo(this.mContext.getPackageName(), 0).versionName;
            this.lib_version = BuildConfig.SDK_VERSION;
        } catch (Exception e) {
            SALog.m56i(TAG, "Exception getting version name = ", e);
        }
        initHandler();
    }

    private void checkFirstDay() {
        if (this.mFirstDay.get() == null) {
            this.mFirstDay.commit(DateFormatUtils.formatTime(System.currentTimeMillis(), DateFormatUtils.YYYY_MM_DD));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void generateAppEndData() {
        try {
            this.endDataProperty.put(EVENT_TIMER, SystemClock.elapsedRealtime());
            this.endDataProperty.put(APP_VERSION, this.app_version);
            this.endDataProperty.put(LIB_VERSION, this.lib_version);
            this.mDbAdapter.commitAppEndData(this.endDataProperty.toString());
            this.mDbAdapter.commitAppPausedTime(System.currentTimeMillis());
        } catch (Exception e) {
            SALog.printStackTrace(e);
        }
    }

    private Message generateMessage(boolean z) {
        Message obtain = Message.obtain(this.handler);
        obtain.what = 0;
        Bundle bundle = new Bundle();
        bundle.putLong("app_end_time", DbAdapter.getInstance().getAppPausedTime());
        bundle.putString(PersistentLoader.PersistentName.APP_END_DATA, DbAdapter.getInstance().getAppEndData());
        bundle.putBoolean("app_reset_state", z);
        obtain.setData(bundle);
        return obtain;
    }

    private void initHandler() {
        try {
            HandlerThread handlerThread = new HandlerThread("app_end_timer");
            handlerThread.start();
            this.handler = new Handler(handlerThread.getLooper()) { // from class: com.sensorsdata.analytics.android.sdk.SensorsDataActivityLifecycleCallbacks.2
                @Override // android.os.Handler
                public void handleMessage(Message message) {
                    if (message != null) {
                        Bundle data = message.getData();
                        long j = data.getLong("app_end_time");
                        String string = data.getString(PersistentLoader.PersistentName.APP_END_DATA);
                        if (data.getBoolean("app_reset_state")) {
                            SensorsDataActivityLifecycleCallbacks.this.resetState();
                        } else {
                            j += 2000;
                        }
                        SensorsDataActivityLifecycleCallbacks.this.trackAppEnd(j, string);
                    }
                }
            };
            this.mContext.getContentResolver().registerContentObserver(DbParams.getInstance().getSessionTimeUri(), false, new SensorsActivityStateObserver(this.handler));
        } catch (Exception e) {
            SALog.printStackTrace(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isAutoTrackAppEnd() {
        return !this.mSensorsDataInstance.isAutoTrackEventTypeIgnored(SensorsDataAPI.AutoTrackEventType.APP_END);
    }

    private boolean isSessionTimeOut() {
        long j = 946656000000L;
        if (System.currentTimeMillis() > 946656000000L) {
            j = System.currentTimeMillis();
        }
        boolean z = Math.abs(j - this.mDbAdapter.getAppPausedTime()) > ((long) this.sessionTime);
        SALog.m53d(TAG, "SessionTimeOut:" + z);
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetState() {
        try {
            this.mSensorsDataInstance.stopTrackScreenOrientation();
            this.mSensorsDataInstance.resetPullSDKConfigTimer();
            HeatMapService.getInstance().stop();
            VisualizedAutoTrackService.getInstance().stop();
            this.mSensorsDataInstance.appEnterBackground();
            this.resumeFromBackground = true;
        } catch (Exception e) {
            SALog.printStackTrace(e);
        }
    }

    private void showDebugModeSelectDialog(final Activity activity, final String str) {
        try {
            DebugModeSelectDialog debugModeSelectDialog = new DebugModeSelectDialog(activity, this.mSensorsDataInstance.getDebugMode());
            debugModeSelectDialog.setCanceledOnTouchOutside(false);
            debugModeSelectDialog.setOnDebugModeDialogClickListener(new DebugModeSelectDialog.OnDebugModeViewClickListener() { // from class: com.sensorsdata.analytics.android.sdk.SensorsDataActivityLifecycleCallbacks.3
                @Override // com.sensorsdata.analytics.android.sdk.DebugModeSelectDialog.OnDebugModeViewClickListener
                public void onCancel(Dialog dialog) {
                    dialog.cancel();
                }

                @Override // com.sensorsdata.analytics.android.sdk.DebugModeSelectDialog.OnDebugModeViewClickListener
                public void setDebugMode(Dialog dialog, SensorsDataAPI.DebugMode debugMode) {
                    SensorsDataActivityLifecycleCallbacks.this.mSensorsDataInstance.setDebugMode(debugMode);
                    dialog.cancel();
                }
            });
            debugModeSelectDialog.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.sensorsdata.analytics.android.sdk.SensorsDataActivityLifecycleCallbacks.4
                @Override // android.content.DialogInterface.OnCancelListener
                public void onCancel(DialogInterface dialogInterface) {
                    String serverUrl = SensorsDataActivityLifecycleCallbacks.this.mSensorsDataInstance.getServerUrl();
                    SensorsDataAPI.DebugMode debugMode = SensorsDataActivityLifecycleCallbacks.this.mSensorsDataInstance.getDebugMode();
                    if (SensorsDataActivityLifecycleCallbacks.this.mSensorsDataInstance.isNetworkRequestEnable() && !TextUtils.isEmpty(serverUrl) && !TextUtils.isEmpty(str) && debugMode != SensorsDataAPI.DebugMode.DEBUG_OFF) {
                        SensorsDataActivityLifecycleCallbacks sensorsDataActivityLifecycleCallbacks = SensorsDataActivityLifecycleCallbacks.this;
                        new SendDebugIdThread(serverUrl, sensorsDataActivityLifecycleCallbacks.mSensorsDataInstance.getDistinctId(), str).start();
                    }
                    Toast.makeText(activity, debugMode == SensorsDataAPI.DebugMode.DEBUG_OFF ? "已关闭调试模式，请重新扫描二维码进行开启" : debugMode == SensorsDataAPI.DebugMode.DEBUG_ONLY ? "开启调试模式，校验数据，但不进行数据导入；关闭 App 进程后，将自动关闭调试模式" : debugMode == SensorsDataAPI.DebugMode.DEBUG_AND_TRACK ? "开启调试模式，校验数据，并将数据导入到神策分析中；关闭 App 进程后，将自动关闭调试模式" : "", 1).show();
                    SALog.info(SensorsDataActivityLifecycleCallbacks.TAG, "您当前的调试模式是：" + debugMode, null);
                }
            });
            debugModeSelectDialog.show();
        } catch (Exception e) {
            SALog.printStackTrace(e);
        }
    }

    private void showNotRequestNetworkDialog(Context context, String str) {
        new AlertDialog.Builder(context).setTitle("提示").setMessage(str).setPositiveButton("确定", (DialogInterface.OnClickListener) null).show();
    }

    private void showOpenHeatMapDialog(final Activity activity, final String str, final String str2) {
        boolean z;
        try {
            if (!SensorsDataAPI.sharedInstance().isNetworkRequestEnable()) {
                showNotRequestNetworkDialog(activity, "已关闭网络请求（NetworkRequest），无法使用 App 点击分析，请开启后再试！");
                return;
            }
            if (!SensorsDataAPI.sharedInstance().isAppHeatMapConfirmDialogEnabled()) {
                HeatMapService.getInstance().start(activity, str, str2);
                return;
            }
            try {
                z = "WIFI".equals(NetworkUtils.networkType(activity));
            } catch (Exception e) {
                SALog.printStackTrace(e);
                z = false;
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setTitle("提示");
            if (z) {
                builder.setMessage("正在连接 App 点击分析");
            } else {
                builder.setMessage("正在连接 App 点击分析，建议在 WiFi 环境下使用");
            }
            builder.setCancelable(false);
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() { // from class: com.sensorsdata.analytics.android.sdk.SensorsDataActivityLifecycleCallbacks.5
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            builder.setPositiveButton("继续", new DialogInterface.OnClickListener() { // from class: com.sensorsdata.analytics.android.sdk.SensorsDataActivityLifecycleCallbacks.6
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    HeatMapService.getInstance().start(activity, str, str2);
                }
            });
            AlertDialog create = builder.create();
            create.show();
            try {
                create.getButton(-2).setTextColor(-16777216);
                create.getButton(-1).setTextColor(-65536);
            } catch (Exception e2) {
                SALog.printStackTrace(e2);
            }
        } catch (Exception e3) {
            SALog.printStackTrace(e3);
        }
    }

    private void showOpenVisualizedAutoTrackDialog(final Activity activity, final String str, final String str2) {
        boolean z;
        try {
            if (!SensorsDataAPI.sharedInstance().isNetworkRequestEnable()) {
                showNotRequestNetworkDialog(activity, "已关闭网络请求（NetworkRequest），无法使用 App 可视化全埋点，请开启后再试！");
                return;
            }
            if (!SensorsDataAPI.sharedInstance().isVisualizedAutoTrackConfirmDialogEnabled()) {
                VisualizedAutoTrackService.getInstance().start(activity, str, str2);
                return;
            }
            try {
                z = "WIFI".equals(NetworkUtils.networkType(activity));
            } catch (Exception e) {
                z = false;
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setTitle("提示");
            if (z) {
                builder.setMessage("正在连接 App 可视化全埋点");
            } else {
                builder.setMessage("正在连接 App 可视化全埋点，建议在 WiFi 环境下使用");
            }
            builder.setCancelable(false);
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() { // from class: com.sensorsdata.analytics.android.sdk.SensorsDataActivityLifecycleCallbacks.7
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            builder.setPositiveButton("继续", new DialogInterface.OnClickListener() { // from class: com.sensorsdata.analytics.android.sdk.SensorsDataActivityLifecycleCallbacks.8
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    VisualizedAutoTrackService.getInstance().start(activity, str, str2);
                }
            });
            AlertDialog create = builder.create();
            create.show();
            try {
                create.getButton(-2).setTextColor(-16777216);
                create.getButton(-1).setTextColor(-65536);
            } catch (Exception e2) {
                SALog.printStackTrace(e2);
            }
        } catch (Exception e3) {
            SALog.printStackTrace(e3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void trackAppEnd(long j, String str) {
        try {
            if (this.mSensorsDataInstance.isAutoTrackEnabled() && isAutoTrackAppEnd() && !TextUtils.isEmpty(str)) {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has(EVENT_TIMER)) {
                    long appStartTime = this.mDbAdapter.getAppStartTime();
                    long j2 = jSONObject.getLong(EVENT_TIMER);
                    EventTimer eventTimer = new EventTimer(TimeUnit.SECONDS, appStartTime, j2);
                    SALog.m53d(TAG, "startTime:" + appStartTime + "--endTime:" + j2 + "--event_duration:" + eventTimer.duration());
                    this.mSensorsDataInstance.trackTimer("$AppEnd", eventTimer);
                    jSONObject.remove(EVENT_TIMER);
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put(AopConstants.SCREEN_NAME, jSONObject.optString(AopConstants.SCREEN_NAME));
                    jSONObject2.put(AopConstants.TITLE, jSONObject.optString(AopConstants.TITLE));
                    jSONObject2.put(LIB_VERSION, jSONObject.optString(LIB_VERSION));
                    jSONObject2.put(APP_VERSION, jSONObject.optString(APP_VERSION));
                    this.mSensorsDataInstance.clearLastScreenUrl();
                    jSONObject2.put("event_time", j);
                    this.mSensorsDataInstance.track("$AppEnd", jSONObject2);
                    this.mDbAdapter.commitAppEndData("");
                    this.mSensorsDataInstance.flushSync();
                }
            }
        } catch (Exception e) {
            SALog.printStackTrace(e);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
        Uri uri = null;
        if (activity != null) {
            try {
                Intent intent = activity.getIntent();
                uri = null;
                if (intent != null) {
                    uri = intent.getData();
                }
            } catch (Exception e) {
                SALog.printStackTrace(e);
                return;
            }
        }
        if (uri != null) {
            String host = uri.getHost();
            if ("heatmap".equals(host)) {
                showOpenHeatMapDialog(activity, uri.getQueryParameter("feature_code"), uri.getQueryParameter("url"));
            } else if ("debugmode".equals(host)) {
                showDebugModeSelectDialog(activity, uri.getQueryParameter("info_id"));
            } else if ("visualized".equals(host)) {
                showOpenVisualizedAutoTrackDialog(activity, uri.getQueryParameter("feature_code"), uri.getQueryParameter("url"));
            }
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        try {
            if (!this.mSensorsDataInstance.isAutoTrackEnabled() || this.mSensorsDataInstance.isActivityAutoTrackAppViewScreenIgnored(activity.getClass()) || this.mSensorsDataInstance.isAutoTrackEventTypeIgnored(SensorsDataAPI.AutoTrackEventType.APP_VIEW_SCREEN)) {
                return;
            }
            try {
                JSONObject jSONObject = new JSONObject();
                SensorsDataUtils.mergeJSONObject(this.activityProperty, jSONObject);
                if (activity instanceof ScreenAutoTracker) {
                    ScreenAutoTracker screenAutoTracker = (ScreenAutoTracker) activity;
                    String screenUrl = screenAutoTracker.getScreenUrl();
                    JSONObject trackProperties = screenAutoTracker.getTrackProperties();
                    if (trackProperties != null) {
                        SensorsDataUtils.mergeJSONObject(trackProperties, jSONObject);
                    }
                    this.mSensorsDataInstance.trackViewScreen(screenUrl, jSONObject);
                    return;
                }
                SensorsDataAutoTrackAppViewScreenUrl sensorsDataAutoTrackAppViewScreenUrl = (SensorsDataAutoTrackAppViewScreenUrl) activity.getClass().getAnnotation(SensorsDataAutoTrackAppViewScreenUrl.class);
                if (sensorsDataAutoTrackAppViewScreenUrl == null) {
                    this.mSensorsDataInstance.track("$AppViewScreen", jSONObject);
                    return;
                }
                String url = sensorsDataAutoTrackAppViewScreenUrl.url();
                String str = url;
                if (TextUtils.isEmpty(url)) {
                    str = activity.getClass().getCanonicalName();
                }
                this.mSensorsDataInstance.trackViewScreen(str, jSONObject);
            } catch (Exception e) {
                SALog.m57i(TAG, e);
            }
        } catch (Exception e2) {
            SALog.printStackTrace(e2);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        try {
            this.activityProperty = AopUtil.buildTitleAndScreenName(activity);
            SensorsDataUtils.mergeJSONObject(this.activityProperty, this.endDataProperty);
            if (this.isMultiProcess) {
                this.startActivityCount = this.mDbAdapter.getActivityCount();
                DbAdapter dbAdapter = this.mDbAdapter;
                int i = this.startActivityCount + 1;
                this.startActivityCount = i;
                dbAdapter.commitActivityCount(i);
            } else {
                this.startActivityCount++;
            }
            if (this.startActivityCount == 1) {
                this.handler.removeMessages(0);
                if (isSessionTimeOut()) {
                    this.handler.sendMessage(generateMessage(false));
                    checkFirstDay();
                    boolean booleanValue = this.mFirstStart.get().booleanValue();
                    try {
                        this.mSensorsDataInstance.appBecomeActive();
                    } catch (Exception e) {
                        SALog.printStackTrace(e);
                    }
                    if (this.resumeFromBackground) {
                        this.mSensorsDataInstance.applySDKConfigFromCache();
                        this.mSensorsDataInstance.resumeTrackScreenOrientation();
                    }
                    this.mSensorsDataInstance.pullSDKConfigFromServer();
                    try {
                        if (this.mSensorsDataInstance.isAutoTrackEnabled() && !this.mSensorsDataInstance.isAutoTrackEventTypeIgnored(SensorsDataAPI.AutoTrackEventType.APP_START)) {
                            if (booleanValue) {
                                this.mFirstStart.commit(false);
                            }
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put("$resume_from_background", this.resumeFromBackground);
                            jSONObject.put("$is_first_time", booleanValue);
                            SensorsDataUtils.mergeJSONObject(this.activityProperty, jSONObject);
                            this.mSensorsDataInstance.track("$AppStart", jSONObject);
                        }
                        this.mDbAdapter.commitAppStartTime(SystemClock.elapsedRealtime());
                    } catch (Exception e2) {
                        SALog.m57i(TAG, e2);
                    }
                    if (this.resumeFromBackground) {
                        try {
                            HeatMapService.getInstance().resume();
                            VisualizedAutoTrackService.getInstance().resume();
                        } catch (Exception e3) {
                            SALog.printStackTrace(e3);
                        }
                    }
                    this.resumeFromBackground = true;
                }
            }
            int i2 = this.startTimerCount;
            this.startTimerCount = i2 + 1;
            if (i2 == 0) {
                SensorsDataTimer.getInstance().timer(this.timer, 0L, 2000L);
            }
        } catch (Exception e4) {
            SALog.printStackTrace(e4);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        int i;
        try {
            this.startTimerCount--;
            if (this.startTimerCount == 0) {
                SensorsDataTimer.getInstance().shutdownTimerTask();
            }
            if (this.mSensorsDataInstance.isMultiProcess()) {
                this.startActivityCount = this.mDbAdapter.getActivityCount();
                if (this.startActivityCount > 0) {
                    i = this.startActivityCount - 1;
                    this.startActivityCount = i;
                } else {
                    i = 0;
                }
                this.startActivityCount = i;
                this.mDbAdapter.commitActivityCount(this.startActivityCount);
            } else {
                this.startActivityCount--;
            }
            if (this.startActivityCount <= 0) {
                generateAppEndData();
                this.handler.sendMessageDelayed(generateMessage(true), this.sessionTime);
            }
        } catch (Exception e) {
            SALog.printStackTrace(e);
        }
    }
}
