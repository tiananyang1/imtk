package com.xiaomi.push;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.xiaomi.push.es */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/es.class */
public final class C0611es {

    /* renamed from: com.xiaomi.push.es$a */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/es$a.class */
    public static final class a extends AbstractC0592e {

        /* renamed from: a */
        private boolean f756a;

        /* renamed from: b */
        private boolean f758b;

        /* renamed from: d */
        private boolean f761d;

        /* renamed from: e */
        private boolean f762e;

        /* renamed from: a */
        private int f754a = 0;

        /* renamed from: c */
        private boolean f760c = false;

        /* renamed from: b */
        private int f757b = 0;

        /* renamed from: f */
        private boolean f763f = false;

        /* renamed from: a */
        private List<String> f755a = Collections.emptyList();

        /* renamed from: c */
        private int f759c = -1;

        /* renamed from: a */
        public static a m1008a(byte[] bArr) {
            return (a) new a().m1008a(bArr);
        }

        /* renamed from: b */
        public static a m1009b(C0511b c0511b) {
            return new a().mo960a(c0511b);
        }

        @Override // com.xiaomi.push.AbstractC0592e
        /* renamed from: a */
        public int mo959a() {
            if (this.f759c < 0) {
                mo967b();
            }
            return this.f759c;
        }

        /* renamed from: a */
        public a m1010a(int i) {
            this.f756a = true;
            this.f754a = i;
            return this;
        }

        @Override // com.xiaomi.push.AbstractC0592e
        /* renamed from: a */
        public a mo960a(C0511b c0511b) {
            while (true) {
                int m539a = c0511b.m539a();
                if (m539a == 0) {
                    return this;
                }
                if (m539a == 8) {
                    m1010a(c0511b.m554c());
                } else if (m539a == 16) {
                    m1012a(c0511b.m547a());
                } else if (m539a == 24) {
                    m1015b(c0511b.m550b());
                } else if (m539a == 32) {
                    m1016b(c0511b.m547a());
                } else if (m539a == 42) {
                    m1011a(c0511b.m543a());
                } else if (!m965a(c0511b, m539a)) {
                    return this;
                }
            }
        }

        /* renamed from: a */
        public a m1011a(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            if (this.f755a.isEmpty()) {
                this.f755a = new ArrayList();
            }
            this.f755a.add(str);
            return this;
        }

        /* renamed from: a */
        public a m1012a(boolean z) {
            this.f758b = true;
            this.f760c = z;
            return this;
        }

        /* renamed from: a */
        public List<String> m1013a() {
            return this.f755a;
        }

        @Override // com.xiaomi.push.AbstractC0592e
        /* renamed from: a */
        public void mo963a(C0538c c0538c) {
            if (m1014a()) {
                c0538c.m700b(1, m1018c());
            }
            if (m1019c()) {
                c0538c.m690a(2, m1017b());
            }
            if (m1021d()) {
                c0538c.m685a(3, m1020d());
            }
            if (m1024f()) {
                c0538c.m690a(4, m1023e());
            }
            Iterator<String> it = m1013a().iterator();
            while (it.hasNext()) {
                c0538c.m689a(5, it.next());
            }
        }

        /* renamed from: a */
        public boolean m1014a() {
            return this.f756a;
        }

        @Override // com.xiaomi.push.AbstractC0592e
        /* renamed from: b */
        public int mo967b() {
            int m674b = m1014a() ? C0538c.m674b(1, m1018c()) + 0 : 0;
            int i = m674b;
            if (m1019c()) {
                i = m674b + C0538c.m664a(2, m1017b());
            }
            int i2 = i;
            if (m1021d()) {
                i2 = i + C0538c.m659a(3, m1020d());
            }
            int i3 = i2;
            if (m1024f()) {
                i3 = i2 + C0538c.m664a(4, m1023e());
            }
            Iterator<String> it = m1013a().iterator();
            int i4 = 0;
            while (true) {
                int i5 = i4;
                if (!it.hasNext()) {
                    int size = i3 + i5 + (m1013a().size() * 1);
                    this.f759c = size;
                    return size;
                }
                i4 = i5 + C0538c.m668a(it.next());
            }
        }

        /* renamed from: b */
        public a m1015b(int i) {
            this.f761d = true;
            this.f757b = i;
            return this;
        }

        /* renamed from: b */
        public a m1016b(boolean z) {
            this.f762e = true;
            this.f763f = z;
            return this;
        }

        /* renamed from: b */
        public boolean m1017b() {
            return this.f760c;
        }

        /* renamed from: c */
        public int m1018c() {
            return this.f754a;
        }

        /* renamed from: c */
        public boolean m1019c() {
            return this.f758b;
        }

        /* renamed from: d */
        public int m1020d() {
            return this.f757b;
        }

        /* renamed from: d */
        public boolean m1021d() {
            return this.f761d;
        }

        /* renamed from: e */
        public int m1022e() {
            return this.f755a.size();
        }

        /* renamed from: e */
        public boolean m1023e() {
            return this.f763f;
        }

        /* renamed from: f */
        public boolean m1024f() {
            return this.f762e;
        }
    }
}
