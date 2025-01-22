package com.sensorsdata.analytics.android.sdk;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.sensorsdata.analytics.android.sdk.SensorsDataAPI;
import com.sensorsdata.analytics.android.sdk.data.DbAdapter;
import com.sensorsdata.analytics.android.sdk.exceptions.ConnectErrorException;
import com.sensorsdata.analytics.android.sdk.exceptions.DebugModeException;
import com.sensorsdata.analytics.android.sdk.exceptions.ResponseErrorException;
import com.sensorsdata.analytics.android.sdk.util.Base64Coder;
import com.sensorsdata.analytics.android.sdk.util.JSONUtils;
import com.stub.StubApp;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.zip.GZIPOutputStream;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes08-dex2jar.jar:com/sensorsdata/analytics/android/sdk/AnalyticsMessages.class */
public class AnalyticsMessages {
    private static final int DELETE_ALL = 4;
    private static final int FLUSH_QUEUE = 3;
    private static final Map<Context, AnalyticsMessages> S_INSTANCES = new HashMap();
    private static final String TAG = "SA.AnalyticsMessages";
    private final Context mContext;
    private final DbAdapter mDbAdapter = DbAdapter.getInstance();
    private final Worker mWorker = new Worker();

    /* loaded from: classes08-dex2jar.jar:com/sensorsdata/analytics/android/sdk/AnalyticsMessages$Worker.class */
    private class Worker {
        private Handler mHandler;
        private final Object mHandlerLock = new Object();

        /* loaded from: classes08-dex2jar.jar:com/sensorsdata/analytics/android/sdk/AnalyticsMessages$Worker$AnalyticsMessageHandler.class */
        private class AnalyticsMessageHandler extends Handler {
            AnalyticsMessageHandler(Looper looper) {
                super(looper);
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                try {
                    if (message.what == 3) {
                        AnalyticsMessages.this.sendData();
                        return;
                    }
                    if (message.what == 4) {
                        try {
                            AnalyticsMessages.this.mDbAdapter.deleteAllEvents();
                            return;
                        } catch (Exception e) {
                            SALog.printStackTrace(e);
                            return;
                        }
                    }
                    SALog.m55i(AnalyticsMessages.TAG, "Unexpected message received by SensorsData worker: " + message);
                } catch (RuntimeException e2) {
                    SALog.m56i(AnalyticsMessages.TAG, "Worker threw an unhandled exception", e2);
                }
            }
        }

        Worker() {
            HandlerThread handlerThread = new HandlerThread("com.sensorsdata.analytics.android.sdk.AnalyticsMessages.Worker", 1);
            handlerThread.start();
            this.mHandler = new AnalyticsMessageHandler(handlerThread.getLooper());
        }

        void runMessage(Message message) {
            synchronized (this.mHandlerLock) {
                if (this.mHandler == null) {
                    SALog.m55i(AnalyticsMessages.TAG, "Dead worker dropping a message: " + message.what);
                } else {
                    this.mHandler.sendMessage(message);
                }
            }
        }

        void runMessageOnce(Message message, long j) {
            synchronized (this.mHandlerLock) {
                if (this.mHandler == null) {
                    SALog.m55i(AnalyticsMessages.TAG, "Dead worker dropping a message: " + message.what);
                } else if (!this.mHandler.hasMessages(message.what)) {
                    this.mHandler.sendMessageDelayed(message, j);
                }
            }
        }
    }

    private AnalyticsMessages(Context context) {
        this.mContext = context;
    }

    private void closeStream(BufferedOutputStream bufferedOutputStream, OutputStream outputStream, InputStream inputStream, HttpURLConnection httpURLConnection) {
        if (bufferedOutputStream != null) {
            try {
                bufferedOutputStream.close();
            } catch (Exception e) {
                SALog.m55i(TAG, e.getMessage());
            }
        }
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (Exception e2) {
                SALog.m55i(TAG, e2.getMessage());
            }
        }
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (Exception e3) {
                SALog.m55i(TAG, e3.getMessage());
            }
        }
        if (httpURLConnection != null) {
            try {
                httpURLConnection.disconnect();
            } catch (Exception e4) {
                SALog.m55i(TAG, e4.getMessage());
            }
        }
    }

    private String encodeData(String str) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(str.getBytes("UTF-8").length);
        GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
        gZIPOutputStream.write(str.getBytes("UTF-8"));
        gZIPOutputStream.close();
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();
        return new String(Base64Coder.encode(byteArray));
    }

    public static AnalyticsMessages getInstance(Context context) {
        AnalyticsMessages analyticsMessages;
        synchronized (S_INSTANCES) {
            Context origApplicationContext = StubApp.getOrigApplicationContext(context.getApplicationContext());
            if (S_INSTANCES.containsKey(origApplicationContext)) {
                analyticsMessages = S_INSTANCES.get(origApplicationContext);
            } else {
                analyticsMessages = new AnalyticsMessages(origApplicationContext);
                S_INSTANCES.put(origApplicationContext, analyticsMessages);
            }
        }
        return analyticsMessages;
    }

    private boolean isDeleteEventsByCode(int i) {
        if (i == 404 || i == 403) {
            return false;
        }
        return i < 500 || i >= 600;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:143:0x0473, code lost:            if (com.sensorsdata.analytics.android.sdk.SensorsDataAPI.sEnableLog != false) goto L173;     */
    /* JADX WARN: Code restructure failed: missing block: B:181:0x03a5, code lost:            if (com.sensorsdata.analytics.android.sdk.SensorsDataAPI.sEnableLog != false) goto L147;     */
    /* JADX WARN: Code restructure failed: missing block: B:213:0x02c6, code lost:            if (com.sensorsdata.analytics.android.sdk.SensorsDataAPI.sEnableLog != false) goto L115;     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00e8, code lost:            if (com.sensorsdata.analytics.android.sdk.SensorsDataAPI.sEnableLog != false) goto L44;     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x01db, code lost:            if (com.sensorsdata.analytics.android.sdk.SensorsDataAPI.sEnableLog != false) goto L84;     */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0519  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x053b  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x055f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void sendData() {
        /*
            Method dump skipped, instructions count: 1426
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sensorsdata.analytics.android.sdk.AnalyticsMessages.sendData():void");
    }

    private void sendHttpRequest(String str, String str2, String str3, boolean z) throws ConnectErrorException, ResponseErrorException {
        HttpURLConnection httpURLConnection;
        InputStream inputStream;
        OutputStream outputStream;
        BufferedOutputStream bufferedOutputStream;
        HttpURLConnection httpURLConnection2;
        BufferedOutputStream bufferedOutputStream2;
        InputStream inputStream2;
        try {
            URL url = new URL(str);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            try {
                if (httpURLConnection == null) {
                    SALog.m56i(TAG, String.format("can not connect %s, it shouldn't happen", url.toString()), null);
                    closeStream(null, null, null, httpURLConnection);
                    return;
                }
                if (SensorsDataAPI.sharedInstance().getSSLSocketFactory() != null && (httpURLConnection instanceof HttpsURLConnection)) {
                    ((HttpsURLConnection) httpURLConnection).setSSLSocketFactory(SensorsDataAPI.sharedInstance().getSSLSocketFactory());
                }
                httpURLConnection.setInstanceFollowRedirects(false);
                if (SensorsDataAPI.sharedInstance(this.mContext).getDebugMode() == SensorsDataAPI.DebugMode.DEBUG_ONLY) {
                    httpURLConnection.addRequestProperty("Dry-Run", "true");
                }
                httpURLConnection.setRequestProperty("Cookie", SensorsDataAPI.sharedInstance(this.mContext).getCookie(false));
                Uri.Builder builder = new Uri.Builder();
                if (!TextUtils.isEmpty(str2)) {
                    builder.appendQueryParameter("crc", String.valueOf(str2.hashCode()));
                }
                builder.appendQueryParameter(HttpRequest.ENCODING_GZIP, "1");
                builder.appendQueryParameter("data_list", str2);
                String encodedQuery = builder.build().getEncodedQuery();
                if (TextUtils.isEmpty(encodedQuery)) {
                    closeStream(null, null, null, httpURLConnection);
                    return;
                }
                httpURLConnection.setFixedLengthStreamingMode(encodedQuery.getBytes("UTF-8").length);
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setRequestMethod(HttpRequest.METHOD_POST);
                outputStream = httpURLConnection.getOutputStream();
                try {
                    BufferedOutputStream bufferedOutputStream3 = new BufferedOutputStream(outputStream);
                    try {
                        bufferedOutputStream3.write(encodedQuery.getBytes("UTF-8"));
                        bufferedOutputStream3.flush();
                        int responseCode = httpURLConnection.getResponseCode();
                        SALog.m55i(TAG, "responseCode: " + responseCode);
                        if (!z && SensorsDataHttpURLConnectionHelper.needRedirects(responseCode)) {
                            String location = SensorsDataHttpURLConnectionHelper.getLocation(httpURLConnection, str);
                            if (!TextUtils.isEmpty(location)) {
                                closeStream(bufferedOutputStream3, outputStream, null, httpURLConnection);
                                sendHttpRequest(location, str2, str3, true);
                                closeStream(bufferedOutputStream3, outputStream, null, httpURLConnection);
                                return;
                            }
                        }
                        try {
                            inputStream = httpURLConnection.getInputStream();
                        } catch (FileNotFoundException e) {
                            inputStream = httpURLConnection.getErrorStream();
                        }
                        try {
                            byte[] slurp = slurp(inputStream);
                            inputStream.close();
                            String str4 = new String(slurp, "UTF-8");
                            if (SensorsDataAPI.sEnableLog) {
                                String formatJson = JSONUtils.formatJson(str3);
                                if (responseCode < 200 || responseCode >= 300) {
                                    SALog.m55i(TAG, "invalid message: \n" + formatJson);
                                    SALog.m55i(TAG, String.format(Locale.CHINA, "ret_code: %d", Integer.valueOf(responseCode)));
                                    SALog.m55i(TAG, String.format(Locale.CHINA, "ret_content: %s", str4));
                                } else {
                                    SALog.m55i(TAG, "valid message: \n" + formatJson);
                                }
                            }
                            if (responseCode < 200 || responseCode >= 300) {
                                throw new ResponseErrorException(String.format("flush failure with response '%s', the response code is '%d'", str4, Integer.valueOf(responseCode)), responseCode);
                            }
                            closeStream(bufferedOutputStream3, outputStream, null, httpURLConnection);
                        } catch (IOException e2) {
                            e = e2;
                            bufferedOutputStream = bufferedOutputStream3;
                            e = e;
                            httpURLConnection2 = httpURLConnection;
                            try {
                                throw new ConnectErrorException(e);
                            } catch (Throwable th) {
                                httpURLConnection = httpURLConnection2;
                                th = th;
                                InputStream inputStream3 = inputStream;
                                bufferedOutputStream2 = bufferedOutputStream;
                                inputStream2 = inputStream3;
                                closeStream(bufferedOutputStream2, outputStream, inputStream2, httpURLConnection);
                                throw th;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            bufferedOutputStream = bufferedOutputStream3;
                            InputStream inputStream32 = inputStream;
                            bufferedOutputStream2 = bufferedOutputStream;
                            inputStream2 = inputStream32;
                            closeStream(bufferedOutputStream2, outputStream, inputStream2, httpURLConnection);
                            throw th;
                        }
                    } catch (IOException e3) {
                        e = e3;
                        inputStream = null;
                        bufferedOutputStream = bufferedOutputStream3;
                    } catch (Throwable th3) {
                        th = th3;
                        inputStream2 = null;
                        bufferedOutputStream2 = bufferedOutputStream3;
                        closeStream(bufferedOutputStream2, outputStream, inputStream2, httpURLConnection);
                        throw th;
                    }
                } catch (IOException e4) {
                    e = e4;
                    inputStream = null;
                    bufferedOutputStream = null;
                } catch (Throwable th4) {
                    th = th4;
                    bufferedOutputStream2 = null;
                    inputStream2 = null;
                }
            } catch (IOException e5) {
                e = e5;
                inputStream = null;
                outputStream = null;
                bufferedOutputStream = null;
            } catch (Throwable th5) {
                th = th5;
                outputStream = null;
                bufferedOutputStream2 = null;
                inputStream2 = null;
                closeStream(bufferedOutputStream2, outputStream, inputStream2, httpURLConnection);
                throw th;
            }
        } catch (IOException e6) {
            e = e6;
            inputStream = null;
            outputStream = null;
            bufferedOutputStream = null;
            httpURLConnection2 = null;
        } catch (Throwable th6) {
            th = th6;
            httpURLConnection = null;
        }
    }

    private static byte[] slurp(InputStream inputStream) throws IOException {
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

    /* JADX INFO: Access modifiers changed from: package-private */
    public void deleteAll() {
        Message obtain = Message.obtain();
        obtain.what = 4;
        this.mWorker.runMessage(obtain);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void enqueueEventMessage(String str, JSONObject jSONObject) {
        try {
            synchronized (this.mDbAdapter) {
                int addJSON = this.mDbAdapter.addJSON(jSONObject);
                if (addJSON < 0) {
                    String str2 = "Failed to enqueue the event: " + jSONObject;
                    if (SensorsDataAPI.sharedInstance(this.mContext).isDebugMode()) {
                        throw new DebugModeException(str2);
                    }
                    SALog.m55i(TAG, str2);
                }
                Message obtain = Message.obtain();
                obtain.what = 3;
                if (SensorsDataAPI.sharedInstance(this.mContext).isDebugMode() || addJSON == -2) {
                    this.mWorker.runMessage(obtain);
                } else {
                    if (!str.equals("track_signup") && addJSON <= SensorsDataAPI.sharedInstance(this.mContext).getFlushBulkSize()) {
                        this.mWorker.runMessageOnce(obtain, SensorsDataAPI.sharedInstance(this.mContext).getFlushInterval());
                    }
                    this.mWorker.runMessage(obtain);
                }
            }
        } catch (Exception e) {
            SALog.m55i(TAG, "enqueueEventMessage error:" + e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void flush() {
        Message obtain = Message.obtain();
        obtain.what = 3;
        this.mWorker.runMessage(obtain);
    }

    void flush(long j) {
        Message obtain = Message.obtain();
        obtain.what = 3;
        this.mWorker.runMessageOnce(obtain, j);
    }
}
