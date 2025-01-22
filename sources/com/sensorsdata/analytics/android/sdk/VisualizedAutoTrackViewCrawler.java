package com.sensorsdata.analytics.android.sdk;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.sensorsdata.analytics.android.sdk.EditProtocol;
import com.sensorsdata.analytics.android.sdk.ResourceReader;
import com.sensorsdata.analytics.android.sdk.util.Base64Coder;
import com.stub.StubApp;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.URLDecoder;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.zip.GZIPOutputStream;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
@TargetApi(16)
/* loaded from: classes08-dex2jar.jar:com/sensorsdata/analytics/android/sdk/VisualizedAutoTrackViewCrawler.class */
public class VisualizedAutoTrackViewCrawler implements VTrack {
    private static final int MESSAGE_SEND_STATE_FOR_EDITING = 1;
    private static final String TAG = "SA.VisualizedAutoTrackViewCrawler";
    private final Activity mActivity;
    private String mAppVersion;
    private final EditState mEditState = new EditState();
    private String mFeatureCode;
    private final LifecycleCallbacks mLifecycleCallbacks;
    private JSONObject mMessageObject;
    private final ViewCrawlerHandler mMessageThreadHandler;
    private String mPostUrl;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes08-dex2jar.jar:com/sensorsdata/analytics/android/sdk/VisualizedAutoTrackViewCrawler$LifecycleCallbacks.class */
    public class LifecycleCallbacks implements Application.ActivityLifecycleCallbacks {
        private LifecycleCallbacks() {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
            VisualizedAutoTrackViewCrawler.this.mEditState.remove(activity);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
            VisualizedAutoTrackViewCrawler.this.mEditState.add(activity);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes08-dex2jar.jar:com/sensorsdata/analytics/android/sdk/VisualizedAutoTrackViewCrawler$ViewCrawlerHandler.class */
    public class ViewCrawlerHandler extends Handler {
        private final EditProtocol mProtocol;
        private ViewSnapshot mSnapshot;
        private boolean mUseGzip;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes08-dex2jar.jar:com/sensorsdata/analytics/android/sdk/VisualizedAutoTrackViewCrawler$ViewCrawlerHandler$CustomHostnameVerifier.class */
        public class CustomHostnameVerifier implements HostnameVerifier {
            private CustomHostnameVerifier() {
            }

            @Override // javax.net.ssl.HostnameVerifier
            public boolean verify(String str, SSLSession sSLSession) {
                return true;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes08-dex2jar.jar:com/sensorsdata/analytics/android/sdk/VisualizedAutoTrackViewCrawler$ViewCrawlerHandler$CustomTrustManager.class */
        public class CustomTrustManager implements X509TrustManager {
            private CustomTrustManager() {
            }

            @Override // javax.net.ssl.X509TrustManager
            public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
            }

            @Override // javax.net.ssl.X509TrustManager
            public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
            }

            @Override // javax.net.ssl.X509TrustManager
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        }

        private ViewCrawlerHandler(Context context, Looper looper, String str) {
            super(looper);
            this.mSnapshot = null;
            this.mProtocol = new EditProtocol(new ResourceReader.Ids(str, context));
            this.mUseGzip = true;
        }

        private void disableSSLCertificateChecking() {
            try {
                SSLContext sSLContext = SSLContext.getInstance("TLS");
                sSLContext.init(null, new TrustManager[]{new CustomTrustManager()}, new SecureRandom());
                HttpsURLConnection.setDefaultSSLSocketFactory(sSLContext.getSocketFactory());
                HttpsURLConnection.setDefaultHostnameVerifier(new CustomHostnameVerifier());
            } catch (Exception e) {
                SALog.printStackTrace(e);
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:35:0x014e  */
        /* JADX WARN: Removed duplicated region for block: B:37:0x0168  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private void postSnapshot(java.io.ByteArrayOutputStream r6) {
            /*
                Method dump skipped, instructions count: 373
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.sensorsdata.analytics.android.sdk.VisualizedAutoTrackViewCrawler.ViewCrawlerHandler.postSnapshot(java.io.ByteArrayOutputStream):void");
        }

        private void sendSnapshot(JSONObject jSONObject) {
            String snapshots;
            long currentTimeMillis = System.currentTimeMillis();
            try {
                JSONObject jSONObject2 = jSONObject.getJSONObject("payload");
                if (jSONObject2.has("config")) {
                    this.mSnapshot = this.mProtocol.readSnapshotConfig(jSONObject2);
                }
                if (this.mSnapshot == null) {
                    SALog.m55i(VisualizedAutoTrackViewCrawler.TAG, "Snapshot should be initialize at first calling.");
                    return;
                }
                if (jSONObject2.has("last_image_hash")) {
                    this.mSnapshot.updateLastImageHashArray(jSONObject2.getString("last_image_hash"));
                }
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(byteArrayOutputStream);
                try {
                    try {
                        try {
                            outputStreamWriter.write("{");
                            outputStreamWriter.write("\"type\": \"snapshot_response\",");
                            outputStreamWriter.write("\"feature_code\": \"" + VisualizedAutoTrackViewCrawler.this.mFeatureCode + "\",");
                            outputStreamWriter.write("\"app_version\": \"" + VisualizedAutoTrackViewCrawler.this.mAppVersion + "\",");
                            outputStreamWriter.write("\"os\": \"Android\",");
                            if (this.mUseGzip) {
                                ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                                OutputStreamWriter outputStreamWriter2 = new OutputStreamWriter(byteArrayOutputStream2);
                                outputStreamWriter2.write("{\"activities\":");
                                outputStreamWriter2.flush();
                                snapshots = this.mSnapshot.snapshots(VisualizedAutoTrackViewCrawler.this.mEditState, byteArrayOutputStream2);
                                long currentTimeMillis2 = System.currentTimeMillis();
                                outputStreamWriter2.write(",\"snapshot_time_millis\": ");
                                outputStreamWriter2.write(Long.toString(currentTimeMillis2 - currentTimeMillis));
                                outputStreamWriter2.write("}");
                                outputStreamWriter2.flush();
                                byteArrayOutputStream2.close();
                                byte[] bytes = byteArrayOutputStream2.toString().getBytes();
                                ByteArrayOutputStream byteArrayOutputStream3 = new ByteArrayOutputStream(bytes.length);
                                GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream3);
                                gZIPOutputStream.write(bytes);
                                gZIPOutputStream.close();
                                byte[] byteArray = byteArrayOutputStream3.toByteArray();
                                byteArrayOutputStream3.close();
                                outputStreamWriter.write("\"gzip_payload\": \"" + new String(Base64Coder.encode(byteArray)) + "\"");
                            } else {
                                outputStreamWriter.write("\"payload\": {");
                                outputStreamWriter.write("\"activities\":");
                                outputStreamWriter.flush();
                                snapshots = this.mSnapshot.snapshots(VisualizedAutoTrackViewCrawler.this.mEditState, byteArrayOutputStream);
                                long currentTimeMillis3 = System.currentTimeMillis();
                                outputStreamWriter.write(",\"snapshot_time_millis\": ");
                                outputStreamWriter.write(Long.toString(currentTimeMillis3 - currentTimeMillis));
                                outputStreamWriter.write("}");
                            }
                            if (!TextUtils.isEmpty(snapshots)) {
                                outputStreamWriter.write(",\"screen_name\": \"" + snapshots + "\"");
                            }
                            outputStreamWriter.write("}");
                            outputStreamWriter.flush();
                            outputStreamWriter.close();
                        } catch (Throwable th) {
                            try {
                                outputStreamWriter.close();
                            } catch (IOException e) {
                                SALog.m56i(VisualizedAutoTrackViewCrawler.TAG, "Can't close writer.", e);
                            }
                            throw th;
                        }
                    } catch (IOException e2) {
                        SALog.m56i(VisualizedAutoTrackViewCrawler.TAG, "Can't write snapshot request to server", e2);
                        outputStreamWriter.close();
                    }
                } catch (IOException e3) {
                    SALog.m56i(VisualizedAutoTrackViewCrawler.TAG, "Can't close writer.", e3);
                }
                postSnapshot(byteArrayOutputStream);
            } catch (EditProtocol.BadInstructionsException e4) {
                SALog.m56i(VisualizedAutoTrackViewCrawler.TAG, "VisualizedAutoTrack server sent malformed message with snapshot request", e4);
            } catch (JSONException e5) {
                SALog.m56i(VisualizedAutoTrackViewCrawler.TAG, "Payload with snapshot config required with snapshot request", e5);
            }
        }

        private byte[] slurp(InputStream inputStream) throws IOException {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[8192];
            while (true) {
                int read = inputStream.read(bArr, 0, bArr.length);
                if (read == -1) {
                    byteArrayOutputStream.flush();
                    return byteArrayOutputStream.toByteArray();
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what != 1) {
                return;
            }
            sendSnapshot(VisualizedAutoTrackViewCrawler.this.mMessageObject);
        }

        public void start() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public VisualizedAutoTrackViewCrawler(Activity activity, String str, String str2, String str3) {
        this.mActivity = activity;
        this.mFeatureCode = str2;
        this.mEditState.add(activity);
        this.mLifecycleCallbacks = new LifecycleCallbacks();
        try {
            this.mPostUrl = URLDecoder.decode(str3, "UTF-8");
            this.mMessageObject = new JSONObject("{\"type\":\"snapshot_request\",\"payload\":{\"config\":{\"classes\":[{\"name\":\"android.view.View\",\"properties\":[{\"name\":\"importantForAccessibility\",\"get\":{\"selector\":\"isImportantForAccessibility\",\"parameters\":[],\"result\":{\"type\":\"java.lang.Boolean\"}}},{\"name\":\"clickable\",\"get\":{\"selector\":\"isClickable\",\"parameters\":[],\"result\":{\"type\":\"java.lang.Boolean\"}}}]},{\"name\":\"android.widget.TextView\",\"properties\":[{\"name\":\"importantForAccessibility\",\"get\":{\"selector\":\"isImportantForAccessibility\",\"parameters\":[],\"result\":{\"type\":\"java.lang.Boolean\"}}},{\"name\":\"clickable\",\"get\":{\"selector\":\"isClickable\",\"parameters\":[],\"result\":{\"type\":\"java.lang.Boolean\"}}}]},{\"name\":\"android.widget.ImageView\",\"properties\":[{\"name\":\"importantForAccessibility\",\"get\":{\"selector\":\"isImportantForAccessibility\",\"parameters\":[],\"result\":{\"type\":\"java.lang.Boolean\"}}},{\"name\":\"clickable\",\"get\":{\"selector\":\"isClickable\",\"parameters\":[],\"result\":{\"type\":\"java.lang.Boolean\"}}}]}]}}}");
        } catch (Exception e) {
            SALog.printStackTrace(e);
            this.mMessageObject = null;
        }
        ((Application) StubApp.getOrigApplicationContext(this.mActivity.getApplicationContext())).registerActivityLifecycleCallbacks(this.mLifecycleCallbacks);
        try {
            this.mAppVersion = activity.getPackageManager().getPackageInfo(activity.getPackageName(), 0).versionName;
        } catch (Exception e2) {
            this.mAppVersion = "";
        }
        HandlerThread handlerThread = new HandlerThread(VisualizedAutoTrackViewCrawler.class.getCanonicalName(), 10);
        handlerThread.start();
        this.mMessageThreadHandler = new ViewCrawlerHandler(this.mActivity, handlerThread.getLooper(), str);
    }

    @Override // com.sensorsdata.analytics.android.sdk.VTrack
    public void startUpdates() {
        try {
            if (TextUtils.isEmpty(this.mFeatureCode) || TextUtils.isEmpty(this.mPostUrl)) {
                return;
            }
            ((Application) StubApp.getOrigApplicationContext(this.mActivity.getApplicationContext())).registerActivityLifecycleCallbacks(this.mLifecycleCallbacks);
            this.mMessageThreadHandler.start();
            this.mMessageThreadHandler.sendMessage(this.mMessageThreadHandler.obtainMessage(1));
        } catch (Exception e) {
            SALog.printStackTrace(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void stopUpdates(boolean z) {
        if (z) {
            try {
                this.mFeatureCode = null;
                this.mPostUrl = null;
            } catch (Exception e) {
                SALog.printStackTrace(e);
                return;
            }
        }
        this.mMessageThreadHandler.removeMessages(1);
        ((Application) StubApp.getOrigApplicationContext(this.mActivity.getApplicationContext())).unregisterActivityLifecycleCallbacks(this.mLifecycleCallbacks);
    }
}
