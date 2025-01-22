package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.push.C0702ib;
import com.xiaomi.push.C0717iq;
import com.xiaomi.push.C0732je;
import com.xiaomi.push.EnumC0696hw;
import com.xiaomi.push.service.C0833bl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/MiTinyDataClient.class */
public class MiTinyDataClient {
    public static final String PENDING_REASON_APPID = "com.xiaomi.xmpushsdk.tinydataPending.appId";
    public static final String PENDING_REASON_CHANNEL = "com.xiaomi.xmpushsdk.tinydataPending.channel";
    public static final String PENDING_REASON_INIT = "com.xiaomi.xmpushsdk.tinydataPending.init";

    /* renamed from: com.xiaomi.mipush.sdk.MiTinyDataClient$a */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/MiTinyDataClient$a.class */
    public static class C0421a {

        /* renamed from: a */
        private static C0421a f256a;

        /* renamed from: a */
        private Context f257a;

        /* renamed from: a */
        private Boolean f259a;

        /* renamed from: a */
        private String f260a;

        /* renamed from: a */
        private a f258a = new a();

        /* renamed from: a */
        private final ArrayList<C0702ib> f261a = new ArrayList<>();

        /* renamed from: com.xiaomi.mipush.sdk.MiTinyDataClient$a$a */
        /* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/MiTinyDataClient$a$a.class */
        public class a {

            /* renamed from: a */
            private ScheduledFuture<?> f265a;

            /* renamed from: a */
            private ScheduledThreadPoolExecutor f266a = new ScheduledThreadPoolExecutor(1);

            /* renamed from: a */
            public final ArrayList<C0702ib> f264a = new ArrayList<>();

            /* renamed from: a */
            private final Runnable f263a = new RunnableC0437an(this);

            public a() {
            }

            /* JADX INFO: Access modifiers changed from: private */
            /* renamed from: a */
            public void m149a() {
                if (this.f265a == null) {
                    this.f265a = this.f266a.scheduleAtFixedRate(this.f263a, 1000L, 1000L, TimeUnit.MILLISECONDS);
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            /* renamed from: b */
            public void m151b() {
                C0702ib remove = this.f264a.remove(0);
                for (C0732je c0732je : C0833bl.m2662a(Arrays.asList(remove), C0421a.this.f257a.getPackageName(), C0461d.m289a(C0421a.this.f257a).m293a(), 30720)) {
                    AbstractC0407b.m74c("MiTinyDataClient Send item by PushServiceClient.sendMessage(XmActionNotification)." + remove.m1656d());
                    C0449az.m224a(C0421a.this.f257a).m254a((C0449az) c0732je, EnumC0696hw.Notification, true, (C0717iq) null);
                }
            }

            /* renamed from: a */
            public void m153a(C0702ib c0702ib) {
                this.f266a.execute(new RunnableC0436am(this, c0702ib));
            }
        }

        /* renamed from: a */
        public static C0421a m137a() {
            if (f256a == null) {
                synchronized (C0421a.class) {
                    try {
                        if (f256a == null) {
                            f256a = new C0421a();
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }
            return f256a;
        }

        /* renamed from: a */
        private void m138a(C0702ib c0702ib) {
            synchronized (this.f261a) {
                if (!this.f261a.contains(c0702ib)) {
                    this.f261a.add(c0702ib);
                    if (this.f261a.size() > 100) {
                        this.f261a.remove(0);
                    }
                }
            }
        }

        /* renamed from: a */
        private boolean m139a(Context context) {
            if (!C0449az.m224a(context).m262a()) {
                return true;
            }
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo("com.xiaomi.xmsf", 4);
                if (packageInfo == null) {
                    return false;
                }
                return packageInfo.versionCode >= 108;
            } catch (Exception e) {
                return false;
            }
        }

        /* renamed from: b */
        private boolean m140b(Context context) {
            return C0461d.m289a(context).m293a() == null && !m139a(this.f257a);
        }

        /* renamed from: b */
        private boolean m141b(C0702ib c0702ib) {
            if (C0833bl.m2664a(c0702ib, false)) {
                return false;
            }
            if (!this.f259a.booleanValue()) {
                this.f258a.m153a(c0702ib);
                return true;
            }
            AbstractC0407b.m74c("MiTinyDataClient Send item by PushServiceClient.sendTinyData(ClientUploadDataItem)." + c0702ib.m1656d());
            C0449az.m224a(this.f257a).m249a(c0702ib);
            return true;
        }

        /* renamed from: a */
        public void m142a(Context context) {
            if (context == null) {
                AbstractC0407b.m70a("context is null, MiTinyDataClientImp.init() failed.");
                return;
            }
            this.f257a = context;
            this.f259a = Boolean.valueOf(m139a(context));
            m146b(MiTinyDataClient.PENDING_REASON_INIT);
        }

        /* renamed from: a */
        public void m143a(String str) {
            synchronized (this) {
                if (TextUtils.isEmpty(str)) {
                    AbstractC0407b.m70a("channel is null, MiTinyDataClientImp.setChannel(String) failed.");
                } else {
                    this.f260a = str;
                    m146b(MiTinyDataClient.PENDING_REASON_CHANNEL);
                }
            }
        }

        /* renamed from: a */
        public boolean m144a() {
            return this.f257a != null;
        }

        /* JADX WARN: Removed duplicated region for block: B:35:0x00d6 A[Catch: all -> 0x018c, TRY_ENTER, TryCatch #0 {, blocks: (B:10:0x000d, B:15:0x001d, B:17:0x0027, B:21:0x0036, B:25:0x0042, B:28:0x004a, B:35:0x00d6, B:37:0x010a, B:38:0x0183, B:43:0x0114, B:46:0x014e, B:51:0x0057, B:53:0x0084, B:55:0x008d, B:57:0x0096, B:59:0x00a0, B:61:0x00a9, B:63:0x00b6, B:65:0x00be, B:67:0x00c7), top: B:9:0x000d }] */
        /* JADX WARN: Removed duplicated region for block: B:41:0x0110  */
        /* renamed from: a */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public boolean m145a(com.xiaomi.push.C0702ib r6) {
            /*
                Method dump skipped, instructions count: 430
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mipush.sdk.MiTinyDataClient.C0421a.m145a(com.xiaomi.push.ib):boolean");
        }

        /* renamed from: b */
        public void m146b(String str) {
            AbstractC0407b.m74c("MiTinyDataClient.processPendingList(" + str + ")");
            ArrayList arrayList = new ArrayList();
            synchronized (this.f261a) {
                arrayList.addAll(this.f261a);
                this.f261a.clear();
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                m145a((C0702ib) it.next());
            }
        }
    }

    public static void init(Context context, String str) {
        if (context == null) {
            AbstractC0407b.m70a("context is null, MiTinyDataClient.init(Context, String) failed.");
            return;
        }
        C0421a.m137a().m142a(context);
        if (TextUtils.isEmpty(str)) {
            AbstractC0407b.m70a("channel is null or empty, MiTinyDataClient.init(Context, String) failed.");
        } else {
            C0421a.m137a().m143a(str);
        }
    }

    public static boolean upload(Context context, C0702ib c0702ib) {
        AbstractC0407b.m74c("MiTinyDataClient.upload " + c0702ib.m1656d());
        if (!C0421a.m137a().m144a()) {
            C0421a.m137a().m142a(context);
        }
        return C0421a.m137a().m145a(c0702ib);
    }

    public static boolean upload(Context context, String str, String str2, long j, String str3) {
        C0702ib c0702ib = new C0702ib();
        c0702ib.m1655d(str);
        c0702ib.m1651c(str2);
        c0702ib.m1638a(j);
        c0702ib.m1647b(str3);
        c0702ib.m1640a(true);
        c0702ib.m1639a("push_sdk_channel");
        return upload(context, c0702ib);
    }

    public static boolean upload(String str, String str2, long j, String str3) {
        C0702ib c0702ib = new C0702ib();
        c0702ib.m1655d(str);
        c0702ib.m1651c(str2);
        c0702ib.m1638a(j);
        c0702ib.m1647b(str3);
        return C0421a.m137a().m145a(c0702ib);
    }
}
