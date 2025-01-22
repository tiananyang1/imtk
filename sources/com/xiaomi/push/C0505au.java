package com.xiaomi.push;

import java.util.LinkedList;

/* renamed from: com.xiaomi.push.au */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/au.class */
public class C0505au {

    /* renamed from: a */
    private LinkedList<a> f452a = new LinkedList<>();

    /* renamed from: com.xiaomi.push.au$a */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/au$a.class */
    public static class a {

        /* renamed from: a */
        private static final C0505au f453a = new C0505au();

        /* renamed from: a */
        public int f454a;

        /* renamed from: a */
        public Object f455a;

        /* renamed from: a */
        public String f456a;

        a(int i, Object obj) {
            this.f454a = i;
            this.f455a = obj;
        }
    }

    /* renamed from: a */
    public static C0505au m504a() {
        return a.f453a;
    }

    /* renamed from: a */
    private void m505a() {
        if (this.f452a.size() > 100) {
            this.f452a.removeFirst();
        }
    }

    /* renamed from: a */
    public int m506a() {
        int size;
        synchronized (this) {
            size = this.f452a.size();
        }
        return size;
    }

    /* renamed from: a */
    public LinkedList<a> m507a() {
        LinkedList<a> linkedList;
        synchronized (this) {
            linkedList = this.f452a;
            this.f452a = new LinkedList<>();
        }
        return linkedList;
    }

    /* renamed from: a */
    public void m508a(Object obj) {
        synchronized (this) {
            this.f452a.add(new a(0, obj));
            m505a();
        }
    }
}
