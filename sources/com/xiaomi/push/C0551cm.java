package com.xiaomi.push;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/* renamed from: com.xiaomi.push.cm */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/cm.class */
public class C0551cm extends Thread {

    /* renamed from: a */
    private final int f591a;

    /* renamed from: a */
    private WeakReference f592a;

    /* renamed from: b */
    private WeakReference f593b;

    public C0551cm(Handler handler, Context context, int i) {
        this.f592a = new WeakReference(handler);
        this.f593b = new WeakReference(context);
        this.f591a = i;
        start();
    }

    /* renamed from: a */
    private Context m778a() {
        if (this.f592a == null) {
            return null;
        }
        return (Context) this.f593b.get();
    }

    /* renamed from: a */
    private Handler m779a() {
        WeakReference weakReference = this.f592a;
        if (weakReference == null) {
            return null;
        }
        return (Handler) weakReference.get();
    }

    /* renamed from: a */
    private HashMap m780a() {
        HashMap hashMap = new HashMap();
        String m767a = C0548cj.m767a(m778a());
        if (m767a != null) {
            String substring = m767a.substring(0, m767a.lastIndexOf("."));
            ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(40);
            try {
                Runnable[] runnableArr = new Runnable[255];
                int i = 1;
                while (true) {
                    int i2 = i;
                    if (i2 >= 255) {
                        break;
                    }
                    runnableArr[i2] = new RunnableC0543ce(this, substring + "." + i2);
                    i = i2 + 1;
                }
                int i3 = 1;
                while (true) {
                    int i4 = i3;
                    if (i4 >= 255) {
                        break;
                    }
                    newFixedThreadPool.execute(runnableArr[i4]);
                    i3 = i4 + 1;
                }
                newFixedThreadPool.shutdown();
            } catch (Exception e) {
                newFixedThreadPool.shutdown();
            } catch (Throwable th) {
                newFixedThreadPool.shutdown();
                try {
                    newFixedThreadPool.awaitTermination(10000L, TimeUnit.MILLISECONDS);
                } catch (Exception e2) {
                }
                throw th;
            }
            try {
                newFixedThreadPool.awaitTermination(10000L, TimeUnit.MILLISECONDS);
            } catch (Exception e3) {
            }
            m782a(substring, 1, 255, hashMap);
        }
        return hashMap;
    }

    /* renamed from: a */
    public static void m781a(Context context, Handler handler, int i) {
        new C0551cm(handler, context, i);
    }

    /* renamed from: a */
    private void m782a(String str, int i, int i2, HashMap hashMap) {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2;
        String[] strArr;
        try {
            bufferedReader = new BufferedReader(new FileReader("/proc/net/arp"));
            try {
                bufferedReader.readLine();
                int i3 = (i2 - i) + 1;
                strArr = new String[i3];
                int i4 = 0;
                while (true) {
                    int i5 = i4;
                    if (i5 >= i3) {
                        break;
                    }
                    strArr[i5] = str + "." + i5;
                    i4 = i5 + 1;
                }
            } catch (FileNotFoundException e) {
                bufferedReader2 = bufferedReader;
                if (bufferedReader2 == null) {
                    return;
                }
                try {
                    bufferedReader2.close();
                    return;
                } catch (IOException e2) {
                    return;
                }
            } catch (IOException e3) {
                bufferedReader2 = bufferedReader;
                if (bufferedReader2 == null) {
                    return;
                }
                bufferedReader2.close();
                return;
            } catch (Throwable th) {
                th = th;
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e4) {
                    }
                }
                throw th;
            }
        } catch (FileNotFoundException e5) {
            bufferedReader2 = null;
        } catch (IOException e6) {
            bufferedReader2 = null;
        } catch (Throwable th2) {
            th = th2;
            bufferedReader = null;
        }
        while (true) {
            String readLine = bufferedReader.readLine();
            bufferedReader2 = bufferedReader;
            if (readLine != null) {
                String[] split = readLine.split("[ ]+");
                if (split.length >= 6) {
                    String str2 = split[0];
                    String str3 = split[3];
                    int length = strArr.length;
                    int i6 = 0;
                    while (true) {
                        int i7 = i6;
                        if (i7 < length) {
                            if (strArr[i7].equals(str2) && str3.matches("..:..:..:..:..:..") && !"00:00:00:00:00:00".equals(str3)) {
                                hashMap.put(str2, str3);
                            }
                            i6 = i7 + 1;
                        }
                    }
                }
            }
            bufferedReader2.close();
            return;
        }
    }

    /* renamed from: a */
    private void m783a(HashMap hashMap) {
        Handler m779a = m779a();
        Message obtainMessage = m779a.obtainMessage(this.f591a);
        obtainMessage.obj = hashMap;
        m779a.sendMessage(obtainMessage);
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        m783a(m780a());
    }
}
