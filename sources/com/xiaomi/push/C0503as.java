package com.xiaomi.push;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.mipush.sdk.Constants;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/* renamed from: com.xiaomi.push.as */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/as.class */
public class C0503as {

    /* renamed from: a */
    public static final Pattern f443a = Pattern.compile("([^\\s;]+)(.*)");

    /* renamed from: b */
    public static final Pattern f444b = Pattern.compile("(.*?charset\\s*=[^a-zA-Z0-9]*)([-a-zA-Z0-9]+)(.*)", 2);

    /* renamed from: c */
    public static final Pattern f445c = Pattern.compile("(\\<\\?xml\\s+.*?encoding\\s*=[^a-zA-Z0-9]*)([-a-zA-Z0-9]+)(.*)", 2);

    /* renamed from: com.xiaomi.push.as$a */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/as$a.class */
    public static final class a extends FilterInputStream {

        /* renamed from: a */
        private boolean f446a;

        public a(InputStream inputStream) {
            super(inputStream);
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public int read(byte[] bArr, int i, int i2) {
            int read;
            if (!this.f446a && (read = super.read(bArr, i, i2)) != -1) {
                return read;
            }
            this.f446a = true;
            return -1;
        }
    }

    /* renamed from: com.xiaomi.push.as$b */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/as$b.class */
    public static class b {

        /* renamed from: a */
        public int f447a;

        /* renamed from: a */
        public Map<String, String> f448a;

        public String toString() {
            return String.format("resCode = %1$d, headers = %2$s", Integer.valueOf(this.f447a), this.f448a.toString());
        }
    }

    /* renamed from: a */
    public static int m469a(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return -1;
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return -1;
            }
            return activeNetworkInfo.getType();
        } catch (Exception e) {
            return -1;
        }
    }

    /* renamed from: a */
    public static NetworkInfo m470a(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return null;
            }
            return connectivityManager.getActiveNetworkInfo();
        } catch (Exception e) {
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v111, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r0v6, types: [java.io.Closeable] */
    /* renamed from: a */
    public static C0501aq m471a(Context context, String str, String str2, Map<String, String> map, String str3) {
        BufferedReader bufferedReader;
        boolean z;
        C0501aq c0501aq = new C0501aq();
        try {
            try {
                try {
                    HttpURLConnection m481a = m481a(context, m482a(str));
                    m481a.setConnectTimeout(10000);
                    m481a.setReadTimeout(15000);
                    String str4 = str2;
                    if (str2 == null) {
                        str4 = HttpRequest.METHOD_GET;
                    }
                    m481a.setRequestMethod(str4);
                    if (map != null) {
                        for (String str5 : map.keySet()) {
                            m481a.setRequestProperty(str5, map.get(str5));
                        }
                    }
                    int i = 0;
                    if (!TextUtils.isEmpty(str3)) {
                        m481a.setDoOutput(true);
                        byte[] bytes = str3.getBytes();
                        ?? outputStream = m481a.getOutputStream();
                        try {
                            outputStream.write(bytes, 0, bytes.length);
                            outputStream.flush();
                            outputStream.close();
                        } catch (IOException e) {
                            e = e;
                            z = outputStream;
                            bufferedReader = null;
                            boolean z2 = z;
                            StringBuilder sb = new StringBuilder();
                            boolean z3 = z;
                            sb.append("err while request ");
                            boolean z4 = z;
                            sb.append(str);
                            boolean z5 = z;
                            sb.append(Constants.COLON_SEPARATOR);
                            boolean z6 = z;
                            sb.append(e.getClass().getSimpleName());
                            boolean z7 = z;
                            throw new IOException(sb.toString());
                        } catch (Throwable th) {
                            map = null;
                            str2 = outputStream;
                            th = th;
                            C0883y.m2858a((Closeable) str2);
                            C0883y.m2858a((Closeable) map);
                            throw th;
                        }
                    }
                    c0501aq.f440a = m481a.getResponseCode();
                    Log.d("com.xiaomi.common.Network", "Http POST Response Code: " + c0501aq.f440a);
                    while (true) {
                        String headerFieldKey = m481a.getHeaderFieldKey(i);
                        String headerField = m481a.getHeaderField(i);
                        if (headerFieldKey == null && headerField == null) {
                            try {
                                break;
                            } catch (IOException e2) {
                                bufferedReader = new BufferedReader(new InputStreamReader(new a(m481a.getErrorStream())));
                            }
                        } else {
                            c0501aq.f442a.put(headerFieldKey, headerField);
                            i = i + 1 + 1;
                        }
                    }
                    bufferedReader = new BufferedReader(new InputStreamReader(new a(m481a.getInputStream())));
                } catch (Throwable th2) {
                    th = th2;
                    bufferedReader = null;
                }
                try {
                    StringBuffer stringBuffer = new StringBuffer();
                    String property = System.getProperty("line.separator");
                    for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                        stringBuffer.append(readLine);
                        stringBuffer.append(property);
                    }
                    c0501aq.f441a = stringBuffer.toString();
                    bufferedReader.close();
                    C0883y.m2858a((Closeable) null);
                    C0883y.m2858a((Closeable) null);
                    return c0501aq;
                } catch (IOException e3) {
                    e = e3;
                    z = false;
                    boolean z22 = z;
                    StringBuilder sb2 = new StringBuilder();
                    boolean z32 = z;
                    sb2.append("err while request ");
                    boolean z42 = z;
                    sb2.append(str);
                    boolean z52 = z;
                    sb2.append(Constants.COLON_SEPARATOR);
                    boolean z62 = z;
                    sb2.append(e.getClass().getSimpleName());
                    boolean z72 = z;
                    throw new IOException(sb2.toString());
                } catch (Throwable th3) {
                    th = th3;
                    throw new IOException(th.getMessage());
                }
            } catch (IOException e4) {
                e = e4;
                bufferedReader = null;
                z = false;
            }
        } catch (Throwable th4) {
            th = th4;
        }
    }

    /* renamed from: a */
    public static C0501aq m472a(Context context, String str, Map<String, String> map) {
        return m471a(context, str, HttpRequest.METHOD_POST, (Map<String, String>) null, m480a(map));
    }

    /* renamed from: a */
    public static InputStream m473a(Context context, URL url, boolean z, String str, String str2) {
        return m474a(context, url, z, str, str2, null, null);
    }

    /* renamed from: a */
    public static InputStream m474a(Context context, URL url, boolean z, String str, String str2, Map<String, String> map, b bVar) {
        if (context == null) {
            throw new IllegalArgumentException("context");
        }
        if (url == null) {
            throw new IllegalArgumentException("url");
        }
        URL url2 = !z ? new URL(m478a(url.toString())) : url;
        try {
            HttpURLConnection.setFollowRedirects(true);
            HttpURLConnection m481a = m481a(context, url2);
            m481a.setConnectTimeout(10000);
            m481a.setReadTimeout(15000);
            if (!TextUtils.isEmpty(str)) {
                m481a.setRequestProperty("User-Agent", str);
            }
            if (str2 != null) {
                m481a.setRequestProperty("Cookie", str2);
            }
            if (map != null) {
                for (String str3 : map.keySet()) {
                    m481a.setRequestProperty(str3, map.get(str3));
                }
            }
            if (bVar != null && (url.getProtocol().equals("http") || url.getProtocol().equals("https"))) {
                bVar.f447a = m481a.getResponseCode();
                if (bVar.f448a == null) {
                    bVar.f448a = new HashMap();
                }
                int i = 0;
                while (true) {
                    int i2 = i;
                    String headerFieldKey = m481a.getHeaderFieldKey(i2);
                    String headerField = m481a.getHeaderField(i2);
                    if (headerFieldKey == null && headerField == null) {
                        break;
                    }
                    if (!TextUtils.isEmpty(headerFieldKey) && !TextUtils.isEmpty(headerField)) {
                        bVar.f448a.put(headerFieldKey, headerField);
                    }
                    i = i2 + 1;
                }
            }
            return new a(m481a.getInputStream());
        } catch (IOException e) {
            throw new IOException("IOException:" + e.getClass().getSimpleName());
        } catch (Throwable th) {
            throw new IOException(th.getMessage());
        }
    }

    /* renamed from: a */
    public static String m475a(Context context) {
        if (m486d(context)) {
            return "wifi";
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return "";
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return "";
            }
            return (activeNetworkInfo.getTypeName() + Constants.ACCEPT_TIME_SEPARATOR_SERVER + activeNetworkInfo.getSubtypeName() + Constants.ACCEPT_TIME_SEPARATOR_SERVER + activeNetworkInfo.getExtraInfo()).toLowerCase();
        } catch (Exception e) {
            return "";
        }
    }

    /* renamed from: a */
    public static String m476a(Context context, URL url) {
        return m477a(context, url, false, null, "UTF-8", null);
    }

    /* renamed from: a */
    public static String m477a(Context context, URL url, boolean z, String str, String str2, String str3) {
        InputStream inputStream;
        try {
            inputStream = m473a(context, url, z, str, str3);
        } catch (Throwable th) {
            th = th;
            inputStream = null;
        }
        try {
            StringBuilder sb = new StringBuilder(1024);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, str2));
            char[] cArr = new char[4096];
            while (true) {
                int read = bufferedReader.read(cArr);
                if (-1 == read) {
                    C0883y.m2858a(inputStream);
                    return sb.toString();
                }
                sb.append(cArr, 0, read);
            }
        } catch (Throwable th2) {
            th = th2;
            C0883y.m2858a(inputStream);
            throw th;
        }
    }

    /* renamed from: a */
    public static String m478a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        new String();
        return String.format("%s&key=%s", str, C0508ax.m518a(String.format("%sbe988a6134bc8254465424e5a70ef037", str)));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v3 */
    /* JADX WARN: Type inference failed for: r8v9, types: [java.io.Closeable, java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v10 */
    /* JADX WARN: Type inference failed for: r9v11 */
    /* JADX WARN: Type inference failed for: r9v13, types: [java.io.Closeable, java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r9v4 */
    /* JADX WARN: Type inference failed for: r9v6 */
    /* JADX WARN: Type inference failed for: r9v9 */
    /* renamed from: a */
    public static String m479a(String str, Map<String, String> map, File file, String str2) {
        Closeable closeable;
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        if (!file.exists()) {
            return null;
        }
        String name = file.getName();
        try {
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
                httpURLConnection.setReadTimeout(15000);
                httpURLConnection.setConnectTimeout(10000);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setUseCaches(false);
                httpURLConnection.setRequestMethod(HttpRequest.METHOD_POST);
                httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
                httpURLConnection.setRequestProperty(HttpRequest.HEADER_CONTENT_TYPE, "multipart/form-data;boundary=*****");
                if (map != null) {
                    for (Map.Entry<String, String> entry : map.entrySet()) {
                        httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
                    }
                }
                httpURLConnection.setFixedLengthStreamingMode(name.length() + 77 + ((int) file.length()) + str2.length());
                DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                dataOutputStream.writeBytes("--*****\r\n");
                dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"" + str2 + "\";filename=\"" + file.getName() + "\"\r\n");
                dataOutputStream.writeBytes("\r\n");
                str = new FileInputStream(file);
                try {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = str.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        dataOutputStream.write(bArr, 0, read);
                        dataOutputStream.flush();
                    }
                    dataOutputStream.writeBytes("\r\n");
                    dataOutputStream.writeBytes("--");
                    dataOutputStream.writeBytes("*****");
                    dataOutputStream.writeBytes("--");
                    dataOutputStream.writeBytes("\r\n");
                    dataOutputStream.flush();
                    StringBuffer stringBuffer = new StringBuffer();
                    closeable = new BufferedReader(new InputStreamReader(new a(httpURLConnection.getInputStream())));
                    while (true) {
                        try {
                            String readLine = closeable.readLine();
                            if (readLine == null) {
                                String stringBuffer2 = stringBuffer.toString();
                                C0883y.m2858a((Closeable) str);
                                C0883y.m2858a((Closeable) closeable);
                                return stringBuffer2;
                            }
                            stringBuffer.append(readLine);
                        } catch (IOException e) {
                            e = e;
                            z4 = closeable;
                            z3 = str;
                            e = e;
                            z2 = z4;
                            boolean z5 = z3;
                            StringBuilder sb = new StringBuilder();
                            boolean z6 = z3;
                            sb.append("IOException:");
                            boolean z7 = z3;
                            sb.append(e.getClass().getSimpleName());
                            boolean z8 = z3;
                            throw new IOException(sb.toString());
                        } catch (Throwable th) {
                            th = th;
                            th = th;
                            z = closeable;
                            throw new IOException(th.getMessage());
                        }
                    }
                } catch (IOException e2) {
                    e = e2;
                    z4 = false;
                } catch (Throwable th2) {
                    th = th2;
                    closeable = 0;
                }
            } catch (IOException e3) {
                e = e3;
                z2 = false;
                z3 = false;
            } catch (Throwable th3) {
                th = th3;
                closeable = 0;
                str = 0;
            }
        } catch (Throwable th4) {
            closeable = file;
            th = th4;
        }
    }

    /* renamed from: a */
    public static String m480a(Map<String, String> map) {
        if (map == null || map.size() <= 0) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getKey() != null && entry.getValue() != null) {
                try {
                    stringBuffer.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                    stringBuffer.append("=");
                    stringBuffer.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
                    stringBuffer.append("&");
                } catch (UnsupportedEncodingException e) {
                    Log.d("com.xiaomi.common.Network", "Failed to convert from params map to string: " + e.toString());
                    Log.d("com.xiaomi.common.Network", "map: " + map.toString());
                    return null;
                }
            }
        }
        StringBuffer stringBuffer2 = stringBuffer;
        if (stringBuffer.length() > 0) {
            stringBuffer2 = stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        }
        return stringBuffer2.toString();
    }

    /* renamed from: a */
    public static HttpURLConnection m481a(Context context, URL url) {
        return (HttpURLConnection) (("http".equals(url.getProtocol()) && m483a(context)) ? url.openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.0.0.200", 80))) : url.openConnection());
    }

    /* renamed from: a */
    private static URL m482a(String str) {
        return new URL(str);
    }

    /* renamed from: a */
    public static boolean m483a(Context context) {
        if (!"CN".equalsIgnoreCase(((TelephonyManager) context.getSystemService("phone")).getSimCountryIso())) {
            return false;
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return false;
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return false;
            }
            String extraInfo = activeNetworkInfo.getExtraInfo();
            return !TextUtils.isEmpty(extraInfo) && extraInfo.length() >= 3 && extraInfo.contains("ctwap");
        } catch (Exception e) {
            return false;
        }
    }

    /* renamed from: b */
    public static boolean m484b(Context context) {
        return m469a(context) >= 0;
    }

    /* renamed from: c */
    public static boolean m485c(Context context) {
        NetworkInfo networkInfo;
        try {
            networkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (Exception e) {
            networkInfo = null;
        }
        return networkInfo != null && networkInfo.isConnected();
    }

    /* renamed from: d */
    public static boolean m486d(Context context) {
        boolean z = false;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return false;
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return false;
            }
            if (1 == activeNetworkInfo.getType()) {
                z = true;
            }
            return z;
        } catch (Exception e) {
            return false;
        }
    }

    /* renamed from: e */
    public static boolean m487e(Context context) {
        return m488f(context) || m489g(context) || m490h(context);
    }

    /* renamed from: f */
    public static boolean m488f(Context context) {
        NetworkInfo m470a = m470a(context);
        boolean z = false;
        if (m470a == null || m470a.getType() != 0) {
            return false;
        }
        if (13 == m470a.getSubtype()) {
            z = true;
        }
        return z;
    }

    /* renamed from: g */
    public static boolean m489g(Context context) {
        NetworkInfo m470a = m470a(context);
        if (m470a == null || m470a.getType() != 0) {
            return false;
        }
        String subtypeName = m470a.getSubtypeName();
        if ("TD-SCDMA".equalsIgnoreCase(subtypeName) || "CDMA2000".equalsIgnoreCase(subtypeName) || "WCDMA".equalsIgnoreCase(subtypeName)) {
            return true;
        }
        switch (m470a.getSubtype()) {
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
                return true;
            case 4:
            case 7:
            case 11:
            case 13:
            default:
                return false;
        }
    }

    /* renamed from: h */
    public static boolean m490h(Context context) {
        NetworkInfo m470a = m470a(context);
        if (m470a == null || m470a.getType() != 0) {
            return false;
        }
        int subtype = m470a.getSubtype();
        return subtype == 1 || subtype == 2 || subtype == 4 || subtype == 7 || subtype == 11;
    }
}
