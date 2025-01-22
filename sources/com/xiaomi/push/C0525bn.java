package com.xiaomi.push;

import android.text.TextUtils;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;

/* renamed from: com.xiaomi.push.bn */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/bn.class */
public class C0525bn {
    /* renamed from: a */
    public static String m622a(String str, Map map) {
        HttpURLConnection httpURLConnection;
        HttpURLConnection httpURLConnection2;
        BufferedReader bufferedReader;
        String str2;
        String str3;
        HttpURLConnection httpURLConnection3;
        HttpURLConnection httpURLConnection4;
        String str4;
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String str5 = "";
        try {
            URL url = new URL(str);
            BufferedReader bufferedReader2 = null;
            BufferedReader bufferedReader3 = null;
            try {
                httpURLConnection4 = url.getProtocol().toLowerCase().equals("https") ? (HttpsURLConnection) url.openConnection() : (HttpURLConnection) url.openConnection();
                try {
                    httpURLConnection4.setConnectTimeout(30000);
                    httpURLConnection4.setReadTimeout(30000);
                    httpURLConnection4.setRequestMethod(HttpRequest.METHOD_GET);
                    httpURLConnection4.setDoOutput(false);
                    if (map != null && map.size() > 0) {
                        for (String str6 : map.keySet()) {
                            httpURLConnection4.addRequestProperty(str6, (String) map.get(str6));
                        }
                    }
                    httpURLConnection4.connect();
                    str4 = "";
                    if (httpURLConnection4.getResponseCode() == 200) {
                        BufferedReader bufferedReader4 = new BufferedReader(new InputStreamReader(httpURLConnection4.getInputStream()));
                        String str7 = "";
                        try {
                            StringBuffer stringBuffer = new StringBuffer();
                            while (true) {
                                String readLine = bufferedReader4.readLine();
                                if (readLine == null) {
                                    break;
                                }
                                stringBuffer.append(readLine);
                            }
                            String stringBuffer2 = stringBuffer.toString();
                            str7 = stringBuffer2;
                            bufferedReader4.close();
                            bufferedReader3 = bufferedReader4;
                            str4 = stringBuffer2;
                        } catch (Exception e) {
                            bufferedReader = bufferedReader4;
                            httpURLConnection2 = httpURLConnection4;
                            str2 = str7;
                            if (bufferedReader != null) {
                                bufferedReader.close();
                            }
                            str3 = str2;
                            if (httpURLConnection2 != null) {
                                httpURLConnection3 = httpURLConnection2;
                                str5 = str2;
                                httpURLConnection3.disconnect();
                                return str2;
                            }
                            return str3;
                        } catch (Throwable th) {
                            bufferedReader2 = bufferedReader4;
                            httpURLConnection = httpURLConnection4;
                            th = th;
                            if (bufferedReader2 != null) {
                                try {
                                    bufferedReader2.close();
                                } catch (Exception e2) {
                                    throw th;
                                }
                            }
                            if (httpURLConnection != null) {
                                httpURLConnection.disconnect();
                            }
                            throw th;
                        }
                    }
                    if (bufferedReader3 != null) {
                        bufferedReader3.close();
                    }
                    str3 = str4;
                } catch (Exception e3) {
                    httpURLConnection2 = httpURLConnection4;
                    str2 = "";
                    bufferedReader = null;
                } catch (Throwable th2) {
                    httpURLConnection = httpURLConnection4;
                    th = th2;
                }
            } catch (Exception e4) {
                httpURLConnection2 = null;
                bufferedReader = null;
                str2 = "";
            } catch (Throwable th3) {
                th = th3;
                httpURLConnection = null;
            }
            if (httpURLConnection4 != null) {
                httpURLConnection3 = httpURLConnection4;
                str2 = str4;
                str5 = str2;
                httpURLConnection3.disconnect();
                return str2;
            }
            return str3;
        } catch (MalformedURLException | Exception e5) {
            return str5;
        }
    }
}
