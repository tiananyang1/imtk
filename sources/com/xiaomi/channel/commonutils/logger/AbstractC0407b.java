package com.xiaomi.channel.commonutils.logger;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: com.xiaomi.channel.commonutils.logger.b */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/channel/commonutils/logger/b.class */
public abstract class AbstractC0407b {

    /* renamed from: a */
    private static int f218a = 2;

    /* renamed from: a */
    private static LoggerInterface f219a = new C0406a();

    /* renamed from: a */
    private static final HashMap<Integer, Long> f221a = new HashMap<>();

    /* renamed from: b */
    private static final HashMap<Integer, String> f223b = new HashMap<>();

    /* renamed from: a */
    private static final Integer f220a = -1;

    /* renamed from: a */
    private static AtomicInteger f222a = new AtomicInteger(1);

    /* renamed from: a */
    public static int m62a() {
        return f218a;
    }

    /* renamed from: a */
    public static Integer m63a(String str) {
        if (f218a > 1) {
            return f220a;
        }
        Integer valueOf = Integer.valueOf(f222a.incrementAndGet());
        f221a.put(valueOf, Long.valueOf(System.currentTimeMillis()));
        f223b.put(valueOf, str);
        f219a.log(str + " starts");
        return valueOf;
    }

    /* renamed from: a */
    public static void m64a(int i) {
        if (i < 0 || i > 5) {
            m65a(2, "set log level as " + i);
        }
        f218a = i;
    }

    /* renamed from: a */
    public static void m65a(int i, String str) {
        if (i >= f218a) {
            f219a.log(str);
        }
    }

    /* renamed from: a */
    public static void m66a(int i, String str, Throwable th) {
        if (i >= f218a) {
            f219a.log(str, th);
        }
    }

    /* renamed from: a */
    public static void m67a(int i, Throwable th) {
        if (i >= f218a) {
            f219a.log("", th);
        }
    }

    /* renamed from: a */
    public static void m68a(LoggerInterface loggerInterface) {
        f219a = loggerInterface;
    }

    /* renamed from: a */
    public static void m69a(Integer num) {
        if (f218a > 1 || !f221a.containsKey(num)) {
            return;
        }
        long longValue = f221a.remove(num).longValue();
        String remove = f223b.remove(num);
        long currentTimeMillis = System.currentTimeMillis();
        f219a.log(remove + " ends in " + (currentTimeMillis - longValue) + " ms");
    }

    /* renamed from: a */
    public static void m70a(String str) {
        m65a(2, "[Thread:" + Thread.currentThread().getId() + "] " + str);
    }

    /* renamed from: a */
    public static void m71a(String str, Throwable th) {
        m66a(4, str, th);
    }

    /* renamed from: a */
    public static void m72a(Throwable th) {
        m67a(4, th);
    }

    /* renamed from: b */
    public static void m73b(String str) {
        m65a(0, str);
    }

    /* renamed from: c */
    public static void m74c(String str) {
        m65a(1, "[Thread:" + Thread.currentThread().getId() + "] " + str);
    }

    /* renamed from: d */
    public static void m75d(String str) {
        m65a(4, str);
    }
}
