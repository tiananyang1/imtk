package com.xiaomi.push.service;

import android.content.Context;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.push.service.XMPushService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/* renamed from: com.xiaomi.push.service.at */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/at.class */
public class C0814at {

    /* renamed from: a */
    private static C0814at f2487a;

    /* renamed from: a */
    private ConcurrentHashMap<String, HashMap<String, b>> f2489a = new ConcurrentHashMap<>();

    /* renamed from: a */
    private List<a> f2488a = new ArrayList();

    /* renamed from: com.xiaomi.push.service.at$a */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/at$a.class */
    public interface a {
        /* renamed from: a */
        void mo2593a();
    }

    /* renamed from: com.xiaomi.push.service.at$b */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/at$b.class */
    public static class b {

        /* renamed from: a */
        public Context f2491a;

        /* renamed from: a */
        Messenger f2493a;

        /* renamed from: a */
        private XMPushService f2495a;

        /* renamed from: a */
        public C0853d f2498a;

        /* renamed from: a */
        public String f2499a;

        /* renamed from: a */
        public boolean f2501a;

        /* renamed from: b */
        public String f2503b;

        /* renamed from: c */
        public String f2505c;

        /* renamed from: d */
        public String f2506d;

        /* renamed from: e */
        public String f2507e;

        /* renamed from: f */
        public String f2508f;

        /* renamed from: g */
        public String f2509g;

        /* renamed from: h */
        public String f2510h;

        /* renamed from: i */
        public String f2511i;

        /* renamed from: a */
        c f2497a = c.unbind;

        /* renamed from: a */
        private int f2490a = 0;

        /* renamed from: a */
        private List<a> f2500a = new ArrayList();

        /* renamed from: b */
        c f2502b = null;

        /* renamed from: b */
        private boolean f2504b = false;

        /* renamed from: a */
        private XMPushService.C0779b f2494a = new XMPushService.C0779b(this);

        /* renamed from: a */
        IBinder.DeathRecipient f2492a = null;

        /* renamed from: a */
        final C0953b f2496a = new C0953b();

        /* renamed from: com.xiaomi.push.service.at$b$a */
        /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/at$b$a.class */
        public interface a {
            /* renamed from: a */
            void mo1555a(c cVar, c cVar2, int i);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: com.xiaomi.push.service.at$b$b, reason: collision with other inner class name */
        /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/at$b$b.class */
        public class C0953b extends XMPushService.AbstractC0786i {

            /* renamed from: a */
            String f2513a;

            /* renamed from: b */
            int f2514b;

            /* renamed from: b */
            String f2515b;

            /* renamed from: c */
            int f2516c;

            public C0953b() {
                super(0);
            }

            /* renamed from: a */
            public XMPushService.AbstractC0786i m2609a(int i, int i2, String str, String str2) {
                this.f2514b = i;
                this.f2516c = i2;
                this.f2515b = str2;
                this.f2513a = str;
                return this;
            }

            @Override // com.xiaomi.push.service.XMPushService.AbstractC0786i
            /* renamed from: a */
            public String mo1440a() {
                return "notify job";
            }

            @Override // com.xiaomi.push.service.XMPushService.AbstractC0786i
            /* renamed from: a */
            public void mo1441a() {
                if (b.this.m2599a(this.f2514b, this.f2516c, this.f2515b)) {
                    b.this.m2597a(this.f2514b, this.f2516c, this.f2513a, this.f2515b);
                    return;
                }
                AbstractC0407b.m73b(" ignore notify client :" + b.this.f2509g);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: com.xiaomi.push.service.at$b$c */
        /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/at$b$c.class */
        public class c implements IBinder.DeathRecipient {

            /* renamed from: a */
            final Messenger f2517a;

            /* renamed from: a */
            final b f2518a;

            c(b bVar, Messenger messenger) {
                this.f2518a = bVar;
                this.f2517a = messenger;
            }

            @Override // android.os.IBinder.DeathRecipient
            public void binderDied() {
                AbstractC0407b.m73b("peer died, chid = " + this.f2518a.f2509g);
                b.this.f2495a.m2469a(new C0816av(this, 0), 0L);
                if ("9".equals(this.f2518a.f2509g) && "com.xiaomi.xmsf".equals(b.this.f2495a.getPackageName())) {
                    b.this.f2495a.m2469a(new C0817aw(this, 0), 60000L);
                }
            }
        }

        public b() {
        }

        public b(XMPushService xMPushService) {
            this.f2495a = xMPushService;
            m2606a(new C0815au(this));
        }

        /* renamed from: a */
        public static String m2596a(String str) {
            if (TextUtils.isEmpty(str)) {
                return "";
            }
            int lastIndexOf = str.lastIndexOf("/");
            return lastIndexOf != -1 ? str.substring(lastIndexOf + 1) : "";
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public void m2597a(int i, int i2, String str, String str2) {
            c cVar = this.f2497a;
            this.f2502b = cVar;
            if (i == 2) {
                this.f2498a.m2680a(this.f2491a, this, i2);
                return;
            }
            if (i == 3) {
                this.f2498a.m2681a(this.f2491a, this, str2, str);
                return;
            }
            if (i == 1) {
                boolean z = cVar == c.binded;
                if (!z && "wait".equals(str2)) {
                    this.f2490a++;
                } else if (z) {
                    this.f2490a = 0;
                    if (this.f2493a != null) {
                        try {
                            this.f2493a.send(Message.obtain(null, 16, this.f2495a.f2391a));
                        } catch (RemoteException e) {
                        }
                    }
                }
                this.f2498a.m2682a(this.f2495a, this, z, i2, str);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public boolean m2599a(int i, int i2, String str) {
            boolean z;
            StringBuilder sb;
            String str2;
            c cVar = this.f2502b;
            if (cVar == null || !(z = this.f2504b)) {
                return true;
            }
            if (cVar == this.f2497a) {
                sb = new StringBuilder();
                str2 = " status recovered, don't notify client:";
            } else {
                if (this.f2493a != null && z) {
                    AbstractC0407b.m73b("Peer alive notify status to client:" + this.f2509g);
                    return true;
                }
                sb = new StringBuilder();
                str2 = "peer died, ignore notify ";
            }
            sb.append(str2);
            sb.append(this.f2509g);
            AbstractC0407b.m73b(sb.toString());
            return false;
        }

        /* renamed from: b */
        private boolean m2601b(int i, int i2, String str) {
            if (i != 1) {
                if (i == 2) {
                    return this.f2495a.m2483c();
                }
                if (i != 3) {
                    return false;
                }
                return !"wait".equals(str);
            }
            if (this.f2497a == c.binded || !this.f2495a.m2483c() || i2 == 21) {
                return false;
            }
            return (i2 == 7 && "wait".equals(str)) ? false : true;
        }

        /* renamed from: a */
        public long m2602a() {
            return (((long) ((Math.random() * 20.0d) - 10.0d)) + ((this.f2490a + 1) * 15)) * 1000;
        }

        /* renamed from: a */
        public String m2603a(int i) {
            return i != 1 ? i != 2 ? i != 3 ? "unknown" : "KICK" : "CLOSE" : "OPEN";
        }

        /* renamed from: a */
        void m2604a() {
            try {
                Messenger messenger = this.f2493a;
                if (messenger != null && this.f2492a != null) {
                    messenger.getBinder().unlinkToDeath(this.f2492a, 0);
                }
            } catch (Exception e) {
            }
            this.f2502b = null;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: a */
        public void m2605a(Messenger messenger) {
            m2604a();
            try {
                if (messenger != null) {
                    this.f2493a = messenger;
                    this.f2504b = true;
                    this.f2492a = new c(this, messenger);
                    messenger.getBinder().linkToDeath(this.f2492a, 0);
                    return;
                }
                AbstractC0407b.m73b("peer linked with old sdk chid = " + this.f2509g);
            } catch (Exception e) {
                AbstractC0407b.m73b("peer linkToDeath err: " + e.getMessage());
                this.f2493a = null;
                this.f2504b = false;
            }
        }

        /* renamed from: a */
        public void m2606a(a aVar) {
            synchronized (this.f2500a) {
                this.f2500a.add(aVar);
            }
        }

        /* renamed from: a */
        public void m2607a(c cVar, int i, int i2, String str, String str2) {
            synchronized (this.f2500a) {
                Iterator<a> it = this.f2500a.iterator();
                while (it.hasNext()) {
                    it.next().mo1555a(this.f2497a, cVar, i2);
                }
            }
            c cVar2 = this.f2497a;
            if (cVar2 != cVar) {
                AbstractC0407b.m70a(String.format("update the client %7$s status. %1$s->%2$s %3$s %4$s %5$s %6$s", cVar2, cVar, m2603a(i), AbstractC0818ax.m2610a(i2), str, str2, this.f2509g));
                this.f2497a = cVar;
            }
            if (this.f2498a == null) {
                AbstractC0407b.m75d("status changed while the client dispatcher is missing");
                return;
            }
            if (cVar == c.binding) {
                return;
            }
            int i3 = 0;
            if (this.f2502b != null) {
                boolean z = this.f2504b;
                i3 = !z ? 0 : (this.f2493a == null || !z) ? 10100 : 1000;
            }
            this.f2495a.m2481b(this.f2496a);
            if (m2601b(i, i2, str2)) {
                m2597a(i, i2, str, str2);
            } else {
                this.f2495a.m2469a(this.f2496a.m2609a(i, i2, str, str2), i3);
            }
        }

        /* renamed from: b */
        public void m2608b(a aVar) {
            synchronized (this.f2500a) {
                this.f2500a.remove(aVar);
            }
        }
    }

    /* renamed from: com.xiaomi.push.service.at$c */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/at$c.class */
    public enum c {
        unbind,
        binding,
        binded
    }

    private C0814at() {
    }

    /* renamed from: a */
    public static C0814at m2578a() {
        C0814at c0814at;
        synchronized (C0814at.class) {
            try {
                if (f2487a == null) {
                    f2487a = new C0814at();
                }
                c0814at = f2487a;
            } catch (Throwable th) {
                throw th;
            }
        }
        return c0814at;
    }

    /* renamed from: a */
    private String m2579a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        int indexOf = str.indexOf("@");
        String str2 = str;
        if (indexOf > 0) {
            str2 = str.substring(0, indexOf);
        }
        return str2;
    }

    /* renamed from: a */
    public int m2580a() {
        int size;
        synchronized (this) {
            size = this.f2489a.size();
        }
        return size;
    }

    /* renamed from: a */
    public b m2581a(String str, String str2) {
        synchronized (this) {
            HashMap<String, b> hashMap = this.f2489a.get(str);
            if (hashMap == null) {
                return null;
            }
            return hashMap.get(m2579a(str2));
        }
    }

    /* renamed from: a */
    public ArrayList<b> m2582a() {
        ArrayList<b> arrayList;
        synchronized (this) {
            arrayList = new ArrayList<>();
            Iterator<HashMap<String, b>> it = this.f2489a.values().iterator();
            while (it.hasNext()) {
                arrayList.addAll(it.next().values());
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    public Collection<b> m2583a(String str) {
        synchronized (this) {
            if (this.f2489a.containsKey(str)) {
                return ((HashMap) this.f2489a.get(str).clone()).values();
            }
            return new ArrayList();
        }
    }

    /* renamed from: a */
    public List<String> m2584a(String str) {
        ArrayList arrayList;
        synchronized (this) {
            arrayList = new ArrayList();
            Iterator<HashMap<String, b>> it = this.f2489a.values().iterator();
            while (it.hasNext()) {
                for (b bVar : it.next().values()) {
                    if (str.equals(bVar.f2499a)) {
                        arrayList.add(bVar.f2509g);
                    }
                }
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    public void m2585a() {
        synchronized (this) {
            Iterator<b> it = m2582a().iterator();
            while (it.hasNext()) {
                it.next().m2604a();
            }
            this.f2489a.clear();
        }
    }

    /* renamed from: a */
    public void m2586a(Context context) {
        synchronized (this) {
            Iterator<HashMap<String, b>> it = this.f2489a.values().iterator();
            while (it.hasNext()) {
                Iterator<b> it2 = it.next().values().iterator();
                while (it2.hasNext()) {
                    it2.next().m2607a(c.unbind, 1, 3, (String) null, (String) null);
                }
            }
        }
    }

    /* renamed from: a */
    public void m2587a(Context context, int i) {
        synchronized (this) {
            Iterator<HashMap<String, b>> it = this.f2489a.values().iterator();
            while (it.hasNext()) {
                Iterator<b> it2 = it.next().values().iterator();
                while (it2.hasNext()) {
                    it2.next().m2607a(c.unbind, 2, i, (String) null, (String) null);
                }
            }
        }
    }

    /* renamed from: a */
    public void m2588a(a aVar) {
        synchronized (this) {
            this.f2488a.add(aVar);
        }
    }

    /* renamed from: a */
    public void m2589a(b bVar) {
        synchronized (this) {
            HashMap<String, b> hashMap = this.f2489a.get(bVar.f2509g);
            HashMap<String, b> hashMap2 = hashMap;
            if (hashMap == null) {
                hashMap2 = new HashMap<>();
                this.f2489a.put(bVar.f2509g, hashMap2);
            }
            hashMap2.put(m2579a(bVar.f2503b), bVar);
            Iterator<a> it = this.f2488a.iterator();
            while (it.hasNext()) {
                it.next().mo2593a();
            }
        }
    }

    /* renamed from: a */
    public void m2590a(String str) {
        synchronized (this) {
            HashMap<String, b> hashMap = this.f2489a.get(str);
            if (hashMap != null) {
                Iterator<b> it = hashMap.values().iterator();
                while (it.hasNext()) {
                    it.next().m2604a();
                }
                hashMap.clear();
                this.f2489a.remove(str);
            }
            Iterator<a> it2 = this.f2488a.iterator();
            while (it2.hasNext()) {
                it2.next().mo2593a();
            }
        }
    }

    /* renamed from: a */
    public void m2591a(String str, String str2) {
        synchronized (this) {
            HashMap<String, b> hashMap = this.f2489a.get(str);
            if (hashMap != null) {
                b bVar = hashMap.get(m2579a(str2));
                if (bVar != null) {
                    bVar.m2604a();
                }
                hashMap.remove(m2579a(str2));
                if (hashMap.isEmpty()) {
                    this.f2489a.remove(str);
                }
            }
            Iterator<a> it = this.f2488a.iterator();
            while (it.hasNext()) {
                it.next().mo2593a();
            }
        }
    }

    /* renamed from: b */
    public void m2592b() {
        synchronized (this) {
            this.f2488a.clear();
        }
    }
}
