package com.xiaomi.push.service;

import android.os.Process;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.push.C0566da;
import com.xiaomi.push.C0611es;
import com.xiaomi.push.C0688ho;
import com.xiaomi.push.C0883y;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/* renamed from: com.xiaomi.push.service.al */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/al.class */
public class C0806al {

    /* renamed from: a */
    private static final Pattern f2472a = Pattern.compile("([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3})");

    /* renamed from: a */
    private static long f2470a = 0;

    /* renamed from: a */
    private static ThreadPoolExecutor f2471a = new ThreadPoolExecutor(1, 1, 20, TimeUnit.SECONDS, new LinkedBlockingQueue());

    /* renamed from: a */
    private static String m2544a(String str) {
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(new File(str)));
        } catch (Exception e) {
            bufferedReader = null;
        } catch (Throwable th) {
            th = th;
            bufferedReader = null;
        }
        try {
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    String sb2 = sb.toString();
                    C0883y.m2858a(bufferedReader);
                    return sb2;
                }
                sb.append("\n");
                sb.append(readLine);
            }
        } catch (Exception e2) {
            C0883y.m2858a(bufferedReader);
            return null;
        } catch (Throwable th2) {
            th = th2;
            C0883y.m2858a(bufferedReader);
            throw th;
        }
    }

    /* renamed from: a */
    public static void m2545a() {
        C0611es.a m2651a;
        long currentTimeMillis = System.currentTimeMillis();
        if ((f2471a.getActiveCount() <= 0 || currentTimeMillis - f2470a >= 1800000) && C0688ho.m1568a().m1575a() && (m2651a = C0830bi.m2642a().m2651a()) != null && m2651a.m1022e() > 0) {
            f2470a = currentTimeMillis;
            m2546a(m2651a.m1013a(), true);
        }
    }

    /* renamed from: a */
    public static void m2546a(List<String> list, boolean z) {
        f2471a.execute(new RunnableC0807am(list, z));
    }

    /* renamed from: b */
    public static void m2548b() {
        String m2544a = m2544a("/proc/self/net/tcp");
        if (!TextUtils.isEmpty(m2544a)) {
            AbstractC0407b.m70a("dump tcp for uid = " + Process.myUid());
            AbstractC0407b.m70a(m2544a);
        }
        String m2544a2 = m2544a("/proc/self/net/tcp6");
        if (TextUtils.isEmpty(m2544a2)) {
            return;
        }
        AbstractC0407b.m70a("dump tcp6 for uid = " + Process.myUid());
        AbstractC0407b.m70a(m2544a2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static boolean m2549b(String str) {
        long currentTimeMillis = System.currentTimeMillis();
        try {
            AbstractC0407b.m70a("ConnectivityTest: begin to connect to " + str);
            Socket socket = new Socket();
            socket.connect(C0566da.m867a(str, 5222), 5000);
            socket.setTcpNoDelay(true);
            AbstractC0407b.m70a("ConnectivityTest: connect to " + str + " in " + (System.currentTimeMillis() - currentTimeMillis));
            socket.close();
            return true;
        } catch (Throwable th) {
            AbstractC0407b.m75d("ConnectivityTest: could not connect to:" + str + " exception: " + th.getClass().getSimpleName() + " description: " + th.getMessage());
            return false;
        }
    }
}
