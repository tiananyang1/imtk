package com.xiaomi.push;

/* renamed from: com.xiaomi.push.et */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/et.class */
public final class C0612et {

    /* renamed from: com.xiaomi.push.et$a */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/et$a.class */
    public static final class a extends AbstractC0592e {

        /* renamed from: a */
        private boolean f767a;

        /* renamed from: b */
        private boolean f770b;

        /* renamed from: c */
        private boolean f773c;

        /* renamed from: d */
        private boolean f776d;

        /* renamed from: e */
        private boolean f779e;

        /* renamed from: f */
        private boolean f781f;

        /* renamed from: g */
        private boolean f782g;

        /* renamed from: h */
        private boolean f783h;

        /* renamed from: i */
        private boolean f784i;

        /* renamed from: j */
        private boolean f785j;

        /* renamed from: k */
        private boolean f786k;

        /* renamed from: a */
        private int f764a = 0;

        /* renamed from: a */
        private long f765a = 0;

        /* renamed from: a */
        private String f766a = "";

        /* renamed from: b */
        private String f769b = "";

        /* renamed from: c */
        private String f772c = "";

        /* renamed from: d */
        private String f775d = "";

        /* renamed from: e */
        private String f778e = "";

        /* renamed from: b */
        private int f768b = 1;

        /* renamed from: c */
        private int f771c = 0;

        /* renamed from: d */
        private int f774d = 0;

        /* renamed from: f */
        private String f780f = "";

        /* renamed from: e */
        private int f777e = -1;

        @Override // com.xiaomi.push.AbstractC0592e
        /* renamed from: a */
        public int mo959a() {
            if (this.f777e < 0) {
                mo967b();
            }
            return this.f777e;
        }

        /* renamed from: a */
        public long m1025a() {
            return this.f765a;
        }

        /* renamed from: a */
        public a m1026a() {
            this.f781f = false;
            this.f775d = "";
            return this;
        }

        /* renamed from: a */
        public a m1027a(int i) {
            this.f767a = true;
            this.f764a = i;
            return this;
        }

        /* renamed from: a */
        public a m1028a(long j) {
            this.f770b = true;
            this.f765a = j;
            return this;
        }

        @Override // com.xiaomi.push.AbstractC0592e
        /* renamed from: a */
        public a mo960a(C0511b c0511b) {
            while (true) {
                int m539a = c0511b.m539a();
                switch (m539a) {
                    case 0:
                        return this;
                    case 8:
                        m1027a(c0511b.m550b());
                        break;
                    case 16:
                        m1028a(c0511b.m551b());
                        break;
                    case 26:
                        m1029a(c0511b.m543a());
                        break;
                    case 34:
                        m1033b(c0511b.m543a());
                        break;
                    case 42:
                        m1038c(c0511b.m543a());
                        break;
                    case 50:
                        m1043d(c0511b.m543a());
                        break;
                    case 58:
                        m1047e(c0511b.m543a());
                        break;
                    case 64:
                        m1032b(c0511b.m550b());
                        break;
                    case 72:
                        m1037c(c0511b.m550b());
                        break;
                    case 80:
                        m1042d(c0511b.m550b());
                        break;
                    case 90:
                        m1051f(c0511b.m543a());
                        break;
                    default:
                        if (!m965a(c0511b, m539a)) {
                            return this;
                        }
                        break;
                }
            }
        }

        /* renamed from: a */
        public a m1029a(String str) {
            this.f773c = true;
            this.f766a = str;
            return this;
        }

        /* renamed from: a */
        public String m1030a() {
            return this.f766a;
        }

        @Override // com.xiaomi.push.AbstractC0592e
        /* renamed from: a */
        public void mo963a(C0538c c0538c) {
            if (m1031a()) {
                c0538c.m685a(1, m1036c());
            }
            if (m1035b()) {
                c0538c.m701b(2, m1025a());
            }
            if (m1040c()) {
                c0538c.m689a(3, m1030a());
            }
            if (m1045d()) {
                c0538c.m689a(4, m1034b());
            }
            if (m1049e()) {
                c0538c.m689a(5, m1039c());
            }
            if (m1053f()) {
                c0538c.m689a(6, m1044d());
            }
            if (m1054g()) {
                c0538c.m689a(7, m1048e());
            }
            if (m1055h()) {
                c0538c.m685a(8, m1041d());
            }
            if (m1056i()) {
                c0538c.m685a(9, m1046e());
            }
            if (m1057j()) {
                c0538c.m685a(10, m1050f());
            }
            if (m1058k()) {
                c0538c.m689a(11, m1052f());
            }
        }

        /* renamed from: a */
        public boolean m1031a() {
            return this.f767a;
        }

        @Override // com.xiaomi.push.AbstractC0592e
        /* renamed from: b */
        public int mo967b() {
            int i = 0;
            if (m1031a()) {
                i = 0 + C0538c.m659a(1, m1036c());
            }
            int i2 = i;
            if (m1035b()) {
                i2 = i + C0538c.m675b(2, m1025a());
            }
            int i3 = i2;
            if (m1040c()) {
                i3 = i2 + C0538c.m663a(3, m1030a());
            }
            int i4 = i3;
            if (m1045d()) {
                i4 = i3 + C0538c.m663a(4, m1034b());
            }
            int i5 = i4;
            if (m1049e()) {
                i5 = i4 + C0538c.m663a(5, m1039c());
            }
            int i6 = i5;
            if (m1053f()) {
                i6 = i5 + C0538c.m663a(6, m1044d());
            }
            int i7 = i6;
            if (m1054g()) {
                i7 = i6 + C0538c.m663a(7, m1048e());
            }
            int i8 = i7;
            if (m1055h()) {
                i8 = i7 + C0538c.m659a(8, m1041d());
            }
            int i9 = i8;
            if (m1056i()) {
                i9 = i8 + C0538c.m659a(9, m1046e());
            }
            int i10 = i9;
            if (m1057j()) {
                i10 = i9 + C0538c.m659a(10, m1050f());
            }
            int i11 = i10;
            if (m1058k()) {
                i11 = i10 + C0538c.m663a(11, m1052f());
            }
            this.f777e = i11;
            return i11;
        }

        /* renamed from: b */
        public a m1032b(int i) {
            this.f783h = true;
            this.f768b = i;
            return this;
        }

        /* renamed from: b */
        public a m1033b(String str) {
            this.f776d = true;
            this.f769b = str;
            return this;
        }

        /* renamed from: b */
        public String m1034b() {
            return this.f769b;
        }

        /* renamed from: b */
        public boolean m1035b() {
            return this.f770b;
        }

        /* renamed from: c */
        public int m1036c() {
            return this.f764a;
        }

        /* renamed from: c */
        public a m1037c(int i) {
            this.f784i = true;
            this.f771c = i;
            return this;
        }

        /* renamed from: c */
        public a m1038c(String str) {
            this.f779e = true;
            this.f772c = str;
            return this;
        }

        /* renamed from: c */
        public String m1039c() {
            return this.f772c;
        }

        /* renamed from: c */
        public boolean m1040c() {
            return this.f773c;
        }

        /* renamed from: d */
        public int m1041d() {
            return this.f768b;
        }

        /* renamed from: d */
        public a m1042d(int i) {
            this.f785j = true;
            this.f774d = i;
            return this;
        }

        /* renamed from: d */
        public a m1043d(String str) {
            this.f781f = true;
            this.f775d = str;
            return this;
        }

        /* renamed from: d */
        public String m1044d() {
            return this.f775d;
        }

        /* renamed from: d */
        public boolean m1045d() {
            return this.f776d;
        }

        /* renamed from: e */
        public int m1046e() {
            return this.f771c;
        }

        /* renamed from: e */
        public a m1047e(String str) {
            this.f782g = true;
            this.f778e = str;
            return this;
        }

        /* renamed from: e */
        public String m1048e() {
            return this.f778e;
        }

        /* renamed from: e */
        public boolean m1049e() {
            return this.f779e;
        }

        /* renamed from: f */
        public int m1050f() {
            return this.f774d;
        }

        /* renamed from: f */
        public a m1051f(String str) {
            this.f786k = true;
            this.f780f = str;
            return this;
        }

        /* renamed from: f */
        public String m1052f() {
            return this.f780f;
        }

        /* renamed from: f */
        public boolean m1053f() {
            return this.f781f;
        }

        /* renamed from: g */
        public boolean m1054g() {
            return this.f782g;
        }

        /* renamed from: h */
        public boolean m1055h() {
            return this.f783h;
        }

        /* renamed from: i */
        public boolean m1056i() {
            return this.f784i;
        }

        /* renamed from: j */
        public boolean m1057j() {
            return this.f785j;
        }

        /* renamed from: k */
        public boolean m1058k() {
            return this.f786k;
        }
    }

    /* renamed from: com.xiaomi.push.et$b */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/et$b.class */
    public static final class b extends AbstractC0592e {

        /* renamed from: a */
        private boolean f788a;

        /* renamed from: c */
        private boolean f792c;

        /* renamed from: d */
        private boolean f794d;

        /* renamed from: e */
        private boolean f795e;

        /* renamed from: b */
        private boolean f790b = false;

        /* renamed from: a */
        private int f787a = 0;

        /* renamed from: b */
        private int f789b = 0;

        /* renamed from: c */
        private int f791c = 0;

        /* renamed from: d */
        private int f793d = -1;

        /* renamed from: a */
        public static b m1059a(byte[] bArr) {
            return (b) new b().m1059a(bArr);
        }

        @Override // com.xiaomi.push.AbstractC0592e
        /* renamed from: a */
        public int mo959a() {
            if (this.f793d < 0) {
                mo967b();
            }
            return this.f793d;
        }

        /* renamed from: a */
        public b m1060a(int i) {
            this.f792c = true;
            this.f787a = i;
            return this;
        }

        @Override // com.xiaomi.push.AbstractC0592e
        /* renamed from: a */
        public b mo960a(C0511b c0511b) {
            while (true) {
                int m539a = c0511b.m539a();
                if (m539a == 0) {
                    return this;
                }
                if (m539a == 8) {
                    m1061a(c0511b.m547a());
                } else if (m539a == 24) {
                    m1060a(c0511b.m550b());
                } else if (m539a == 32) {
                    m1063b(c0511b.m550b());
                } else if (m539a == 40) {
                    m1066c(c0511b.m550b());
                } else if (!m965a(c0511b, m539a)) {
                    return this;
                }
            }
        }

        /* renamed from: a */
        public b m1061a(boolean z) {
            this.f788a = true;
            this.f790b = z;
            return this;
        }

        @Override // com.xiaomi.push.AbstractC0592e
        /* renamed from: a */
        public void mo963a(C0538c c0538c) {
            if (m1064b()) {
                c0538c.m690a(1, m1062a());
            }
            if (m1067c()) {
                c0538c.m685a(3, m1065c());
            }
            if (m1069d()) {
                c0538c.m685a(4, m1068d());
            }
            if (m1071e()) {
                c0538c.m685a(5, m1070e());
            }
        }

        /* renamed from: a */
        public boolean m1062a() {
            return this.f790b;
        }

        @Override // com.xiaomi.push.AbstractC0592e
        /* renamed from: b */
        public int mo967b() {
            int i = 0;
            if (m1064b()) {
                i = 0 + C0538c.m664a(1, m1062a());
            }
            int i2 = i;
            if (m1067c()) {
                i2 = i + C0538c.m659a(3, m1065c());
            }
            int i3 = i2;
            if (m1069d()) {
                i3 = i2 + C0538c.m659a(4, m1068d());
            }
            int i4 = i3;
            if (m1071e()) {
                i4 = i3 + C0538c.m659a(5, m1070e());
            }
            this.f793d = i4;
            return i4;
        }

        /* renamed from: b */
        public b m1063b(int i) {
            this.f794d = true;
            this.f789b = i;
            return this;
        }

        /* renamed from: b */
        public boolean m1064b() {
            return this.f788a;
        }

        /* renamed from: c */
        public int m1065c() {
            return this.f787a;
        }

        /* renamed from: c */
        public b m1066c(int i) {
            this.f795e = true;
            this.f791c = i;
            return this;
        }

        /* renamed from: c */
        public boolean m1067c() {
            return this.f792c;
        }

        /* renamed from: d */
        public int m1068d() {
            return this.f789b;
        }

        /* renamed from: d */
        public boolean m1069d() {
            return this.f794d;
        }

        /* renamed from: e */
        public int m1070e() {
            return this.f791c;
        }

        /* renamed from: e */
        public boolean m1071e() {
            return this.f795e;
        }
    }

    /* renamed from: com.xiaomi.push.et$c */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/et$c.class */
    public static final class c extends AbstractC0592e {

        /* renamed from: a */
        private boolean f798a;

        /* renamed from: b */
        private boolean f800b;

        /* renamed from: c */
        private boolean f802c;

        /* renamed from: d */
        private boolean f804d;

        /* renamed from: e */
        private boolean f806e;

        /* renamed from: f */
        private boolean f808f;

        /* renamed from: a */
        private String f797a = "";

        /* renamed from: b */
        private String f799b = "";

        /* renamed from: c */
        private String f801c = "";

        /* renamed from: d */
        private String f803d = "";

        /* renamed from: e */
        private String f805e = "";

        /* renamed from: f */
        private String f807f = "";

        /* renamed from: a */
        private int f796a = -1;

        @Override // com.xiaomi.push.AbstractC0592e
        /* renamed from: a */
        public int mo959a() {
            if (this.f796a < 0) {
                mo967b();
            }
            return this.f796a;
        }

        @Override // com.xiaomi.push.AbstractC0592e
        /* renamed from: a */
        public c mo960a(C0511b c0511b) {
            while (true) {
                int m539a = c0511b.m539a();
                if (m539a == 0) {
                    return this;
                }
                if (m539a == 10) {
                    m1072a(c0511b.m543a());
                } else if (m539a == 18) {
                    m1075b(c0511b.m543a());
                } else if (m539a == 26) {
                    m1078c(c0511b.m543a());
                } else if (m539a == 34) {
                    m1081d(c0511b.m543a());
                } else if (m539a == 42) {
                    m1084e(c0511b.m543a());
                } else if (m539a == 50) {
                    m1087f(c0511b.m543a());
                } else if (!m965a(c0511b, m539a)) {
                    return this;
                }
            }
        }

        /* renamed from: a */
        public c m1072a(String str) {
            this.f798a = true;
            this.f797a = str;
            return this;
        }

        /* renamed from: a */
        public String m1073a() {
            return this.f797a;
        }

        @Override // com.xiaomi.push.AbstractC0592e
        /* renamed from: a */
        public void mo963a(C0538c c0538c) {
            if (m1074a()) {
                c0538c.m689a(1, m1073a());
            }
            if (m1077b()) {
                c0538c.m689a(2, m1076b());
            }
            if (m1080c()) {
                c0538c.m689a(3, m1079c());
            }
            if (m1083d()) {
                c0538c.m689a(4, m1082d());
            }
            if (m1086e()) {
                c0538c.m689a(5, m1085e());
            }
            if (m1089f()) {
                c0538c.m689a(6, m1088f());
            }
        }

        /* renamed from: a */
        public boolean m1074a() {
            return this.f798a;
        }

        @Override // com.xiaomi.push.AbstractC0592e
        /* renamed from: b */
        public int mo967b() {
            int i = 0;
            if (m1074a()) {
                i = 0 + C0538c.m663a(1, m1073a());
            }
            int i2 = i;
            if (m1077b()) {
                i2 = i + C0538c.m663a(2, m1076b());
            }
            int i3 = i2;
            if (m1080c()) {
                i3 = i2 + C0538c.m663a(3, m1079c());
            }
            int i4 = i3;
            if (m1083d()) {
                i4 = i3 + C0538c.m663a(4, m1082d());
            }
            int i5 = i4;
            if (m1086e()) {
                i5 = i4 + C0538c.m663a(5, m1085e());
            }
            int i6 = i5;
            if (m1089f()) {
                i6 = i5 + C0538c.m663a(6, m1088f());
            }
            this.f796a = i6;
            return i6;
        }

        /* renamed from: b */
        public c m1075b(String str) {
            this.f800b = true;
            this.f799b = str;
            return this;
        }

        /* renamed from: b */
        public String m1076b() {
            return this.f799b;
        }

        /* renamed from: b */
        public boolean m1077b() {
            return this.f800b;
        }

        /* renamed from: c */
        public c m1078c(String str) {
            this.f802c = true;
            this.f801c = str;
            return this;
        }

        /* renamed from: c */
        public String m1079c() {
            return this.f801c;
        }

        /* renamed from: c */
        public boolean m1080c() {
            return this.f802c;
        }

        /* renamed from: d */
        public c m1081d(String str) {
            this.f804d = true;
            this.f803d = str;
            return this;
        }

        /* renamed from: d */
        public String m1082d() {
            return this.f803d;
        }

        /* renamed from: d */
        public boolean m1083d() {
            return this.f804d;
        }

        /* renamed from: e */
        public c m1084e(String str) {
            this.f806e = true;
            this.f805e = str;
            return this;
        }

        /* renamed from: e */
        public String m1085e() {
            return this.f805e;
        }

        /* renamed from: e */
        public boolean m1086e() {
            return this.f806e;
        }

        /* renamed from: f */
        public c m1087f(String str) {
            this.f808f = true;
            this.f807f = str;
            return this;
        }

        /* renamed from: f */
        public String m1088f() {
            return this.f807f;
        }

        /* renamed from: f */
        public boolean m1089f() {
            return this.f808f;
        }
    }

    /* renamed from: com.xiaomi.push.et$d */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/et$d.class */
    public static final class d extends AbstractC0592e {

        /* renamed from: a */
        private boolean f811a;

        /* renamed from: c */
        private boolean f815c;

        /* renamed from: d */
        private boolean f816d;

        /* renamed from: e */
        private boolean f817e;

        /* renamed from: b */
        private boolean f813b = false;

        /* renamed from: a */
        private String f810a = "";

        /* renamed from: b */
        private String f812b = "";

        /* renamed from: c */
        private String f814c = "";

        /* renamed from: a */
        private int f809a = -1;

        /* renamed from: a */
        public static d m1090a(byte[] bArr) {
            return (d) new d().m1090a(bArr);
        }

        @Override // com.xiaomi.push.AbstractC0592e
        /* renamed from: a */
        public int mo959a() {
            if (this.f809a < 0) {
                mo967b();
            }
            return this.f809a;
        }

        @Override // com.xiaomi.push.AbstractC0592e
        /* renamed from: a */
        public d mo960a(C0511b c0511b) {
            while (true) {
                int m539a = c0511b.m539a();
                if (m539a == 0) {
                    return this;
                }
                if (m539a == 8) {
                    m1092a(c0511b.m547a());
                } else if (m539a == 18) {
                    m1091a(c0511b.m543a());
                } else if (m539a == 26) {
                    m1095b(c0511b.m543a());
                } else if (m539a == 34) {
                    m1098c(c0511b.m543a());
                } else if (!m965a(c0511b, m539a)) {
                    return this;
                }
            }
        }

        /* renamed from: a */
        public d m1091a(String str) {
            this.f815c = true;
            this.f810a = str;
            return this;
        }

        /* renamed from: a */
        public d m1092a(boolean z) {
            this.f811a = true;
            this.f813b = z;
            return this;
        }

        /* renamed from: a */
        public String m1093a() {
            return this.f810a;
        }

        @Override // com.xiaomi.push.AbstractC0592e
        /* renamed from: a */
        public void mo963a(C0538c c0538c) {
            if (m1097b()) {
                c0538c.m690a(1, m1094a());
            }
            if (m1100c()) {
                c0538c.m689a(2, m1093a());
            }
            if (m1101d()) {
                c0538c.m689a(3, m1096b());
            }
            if (m1102e()) {
                c0538c.m689a(4, m1099c());
            }
        }

        /* renamed from: a */
        public boolean m1094a() {
            return this.f813b;
        }

        @Override // com.xiaomi.push.AbstractC0592e
        /* renamed from: b */
        public int mo967b() {
            int i = 0;
            if (m1097b()) {
                i = 0 + C0538c.m664a(1, m1094a());
            }
            int i2 = i;
            if (m1100c()) {
                i2 = i + C0538c.m663a(2, m1093a());
            }
            int i3 = i2;
            if (m1101d()) {
                i3 = i2 + C0538c.m663a(3, m1096b());
            }
            int i4 = i3;
            if (m1102e()) {
                i4 = i3 + C0538c.m663a(4, m1099c());
            }
            this.f809a = i4;
            return i4;
        }

        /* renamed from: b */
        public d m1095b(String str) {
            this.f816d = true;
            this.f812b = str;
            return this;
        }

        /* renamed from: b */
        public String m1096b() {
            return this.f812b;
        }

        /* renamed from: b */
        public boolean m1097b() {
            return this.f811a;
        }

        /* renamed from: c */
        public d m1098c(String str) {
            this.f817e = true;
            this.f814c = str;
            return this;
        }

        /* renamed from: c */
        public String m1099c() {
            return this.f814c;
        }

        /* renamed from: c */
        public boolean m1100c() {
            return this.f815c;
        }

        /* renamed from: d */
        public boolean m1101d() {
            return this.f816d;
        }

        /* renamed from: e */
        public boolean m1102e() {
            return this.f817e;
        }
    }

    /* renamed from: com.xiaomi.push.et$e */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/et$e.class */
    public static final class e extends AbstractC0592e {

        /* renamed from: a */
        private boolean f821a;

        /* renamed from: b */
        private boolean f824b;

        /* renamed from: c */
        private boolean f827c;

        /* renamed from: d */
        private boolean f830d;

        /* renamed from: e */
        private boolean f832e;

        /* renamed from: f */
        private boolean f834f;

        /* renamed from: g */
        private boolean f835g;

        /* renamed from: h */
        private boolean f836h;

        /* renamed from: i */
        private boolean f837i;

        /* renamed from: j */
        private boolean f838j;

        /* renamed from: a */
        private int f818a = 0;

        /* renamed from: a */
        private String f820a = "";

        /* renamed from: b */
        private String f823b = "";

        /* renamed from: c */
        private String f826c = "";

        /* renamed from: b */
        private int f822b = 0;

        /* renamed from: d */
        private String f829d = "";

        /* renamed from: e */
        private String f831e = "";

        /* renamed from: f */
        private String f833f = "";

        /* renamed from: a */
        private b f819a = null;

        /* renamed from: c */
        private int f825c = 0;

        /* renamed from: d */
        private int f828d = -1;

        @Override // com.xiaomi.push.AbstractC0592e
        /* renamed from: a */
        public int mo959a() {
            if (this.f828d < 0) {
                mo967b();
            }
            return this.f828d;
        }

        /* renamed from: a */
        public b m1103a() {
            return this.f819a;
        }

        /* renamed from: a */
        public e m1104a(int i) {
            this.f821a = true;
            this.f818a = i;
            return this;
        }

        @Override // com.xiaomi.push.AbstractC0592e
        /* renamed from: a */
        public e mo960a(C0511b c0511b) {
            while (true) {
                int m539a = c0511b.m539a();
                switch (m539a) {
                    case 0:
                        return this;
                    case 8:
                        m1104a(c0511b.m554c());
                        break;
                    case 18:
                        m1106a(c0511b.m543a());
                        break;
                    case 26:
                        m1110b(c0511b.m543a());
                        break;
                    case 34:
                        m1115c(c0511b.m543a());
                        break;
                    case 40:
                        m1109b(c0511b.m550b());
                        break;
                    case 50:
                        m1119d(c0511b.m543a());
                        break;
                    case 58:
                        m1123e(c0511b.m543a());
                        break;
                    case 66:
                        m1126f(c0511b.m543a());
                        break;
                    case 74:
                        b bVar = new b();
                        c0511b.m546a(bVar);
                        m1105a(bVar);
                        break;
                    case 80:
                        m1114c(c0511b.m550b());
                        break;
                    default:
                        if (!m965a(c0511b, m539a)) {
                            return this;
                        }
                        break;
                }
            }
        }

        /* renamed from: a */
        public e m1105a(b bVar) {
            if (bVar == null) {
                throw new NullPointerException();
            }
            this.f837i = true;
            this.f819a = bVar;
            return this;
        }

        /* renamed from: a */
        public e m1106a(String str) {
            this.f824b = true;
            this.f820a = str;
            return this;
        }

        /* renamed from: a */
        public String m1107a() {
            return this.f820a;
        }

        @Override // com.xiaomi.push.AbstractC0592e
        /* renamed from: a */
        public void mo963a(C0538c c0538c) {
            if (m1108a()) {
                c0538c.m700b(1, m1113c());
            }
            if (m1112b()) {
                c0538c.m689a(2, m1107a());
            }
            if (m1117c()) {
                c0538c.m689a(3, m1111b());
            }
            if (m1121d()) {
                c0538c.m689a(4, m1116c());
            }
            if (m1125e()) {
                c0538c.m685a(5, m1118d());
            }
            if (m1128f()) {
                c0538c.m689a(6, m1120d());
            }
            if (m1129g()) {
                c0538c.m689a(7, m1124e());
            }
            if (m1130h()) {
                c0538c.m689a(8, m1127f());
            }
            if (m1131i()) {
                c0538c.m688a(9, (AbstractC0592e) m1103a());
            }
            if (m1132j()) {
                c0538c.m685a(10, m1122e());
            }
        }

        /* renamed from: a */
        public boolean m1108a() {
            return this.f821a;
        }

        @Override // com.xiaomi.push.AbstractC0592e
        /* renamed from: b */
        public int mo967b() {
            int i = 0;
            if (m1108a()) {
                i = 0 + C0538c.m674b(1, m1113c());
            }
            int i2 = i;
            if (m1112b()) {
                i2 = i + C0538c.m663a(2, m1107a());
            }
            int i3 = i2;
            if (m1117c()) {
                i3 = i2 + C0538c.m663a(3, m1111b());
            }
            int i4 = i3;
            if (m1121d()) {
                i4 = i3 + C0538c.m663a(4, m1116c());
            }
            int i5 = i4;
            if (m1125e()) {
                i5 = i4 + C0538c.m659a(5, m1118d());
            }
            int i6 = i5;
            if (m1128f()) {
                i6 = i5 + C0538c.m663a(6, m1120d());
            }
            int i7 = i6;
            if (m1129g()) {
                i7 = i6 + C0538c.m663a(7, m1124e());
            }
            int i8 = i7;
            if (m1130h()) {
                i8 = i7 + C0538c.m663a(8, m1127f());
            }
            int i9 = i8;
            if (m1131i()) {
                i9 = i8 + C0538c.m662a(9, (AbstractC0592e) m1103a());
            }
            int i10 = i9;
            if (m1132j()) {
                i10 = i9 + C0538c.m659a(10, m1122e());
            }
            this.f828d = i10;
            return i10;
        }

        /* renamed from: b */
        public e m1109b(int i) {
            this.f832e = true;
            this.f822b = i;
            return this;
        }

        /* renamed from: b */
        public e m1110b(String str) {
            this.f827c = true;
            this.f823b = str;
            return this;
        }

        /* renamed from: b */
        public String m1111b() {
            return this.f823b;
        }

        /* renamed from: b */
        public boolean m1112b() {
            return this.f824b;
        }

        /* renamed from: c */
        public int m1113c() {
            return this.f818a;
        }

        /* renamed from: c */
        public e m1114c(int i) {
            this.f838j = true;
            this.f825c = i;
            return this;
        }

        /* renamed from: c */
        public e m1115c(String str) {
            this.f830d = true;
            this.f826c = str;
            return this;
        }

        /* renamed from: c */
        public String m1116c() {
            return this.f826c;
        }

        /* renamed from: c */
        public boolean m1117c() {
            return this.f827c;
        }

        /* renamed from: d */
        public int m1118d() {
            return this.f822b;
        }

        /* renamed from: d */
        public e m1119d(String str) {
            this.f834f = true;
            this.f829d = str;
            return this;
        }

        /* renamed from: d */
        public String m1120d() {
            return this.f829d;
        }

        /* renamed from: d */
        public boolean m1121d() {
            return this.f830d;
        }

        /* renamed from: e */
        public int m1122e() {
            return this.f825c;
        }

        /* renamed from: e */
        public e m1123e(String str) {
            this.f835g = true;
            this.f831e = str;
            return this;
        }

        /* renamed from: e */
        public String m1124e() {
            return this.f831e;
        }

        /* renamed from: e */
        public boolean m1125e() {
            return this.f832e;
        }

        /* renamed from: f */
        public e m1126f(String str) {
            this.f836h = true;
            this.f833f = str;
            return this;
        }

        /* renamed from: f */
        public String m1127f() {
            return this.f833f;
        }

        /* renamed from: f */
        public boolean m1128f() {
            return this.f834f;
        }

        /* renamed from: g */
        public boolean m1129g() {
            return this.f835g;
        }

        /* renamed from: h */
        public boolean m1130h() {
            return this.f836h;
        }

        /* renamed from: i */
        public boolean m1131i() {
            return this.f837i;
        }

        /* renamed from: j */
        public boolean m1132j() {
            return this.f838j;
        }
    }

    /* renamed from: com.xiaomi.push.et$f */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/et$f.class */
    public static final class f extends AbstractC0592e {

        /* renamed from: a */
        private boolean f842a;

        /* renamed from: b */
        private boolean f844b;

        /* renamed from: c */
        private boolean f845c;

        /* renamed from: a */
        private String f841a = "";

        /* renamed from: b */
        private String f843b = "";

        /* renamed from: a */
        private b f840a = null;

        /* renamed from: a */
        private int f839a = -1;

        /* renamed from: a */
        public static f m1133a(byte[] bArr) {
            return (f) new f().m1133a(bArr);
        }

        @Override // com.xiaomi.push.AbstractC0592e
        /* renamed from: a */
        public int mo959a() {
            if (this.f839a < 0) {
                mo967b();
            }
            return this.f839a;
        }

        /* renamed from: a */
        public b m1134a() {
            return this.f840a;
        }

        @Override // com.xiaomi.push.AbstractC0592e
        /* renamed from: a */
        public f mo960a(C0511b c0511b) {
            while (true) {
                int m539a = c0511b.m539a();
                if (m539a == 0) {
                    return this;
                }
                if (m539a == 10) {
                    m1136a(c0511b.m543a());
                } else if (m539a == 18) {
                    m1139b(c0511b.m543a());
                } else if (m539a == 26) {
                    b bVar = new b();
                    c0511b.m546a(bVar);
                    m1135a(bVar);
                } else if (!m965a(c0511b, m539a)) {
                    return this;
                }
            }
        }

        /* renamed from: a */
        public f m1135a(b bVar) {
            if (bVar == null) {
                throw new NullPointerException();
            }
            this.f845c = true;
            this.f840a = bVar;
            return this;
        }

        /* renamed from: a */
        public f m1136a(String str) {
            this.f842a = true;
            this.f841a = str;
            return this;
        }

        /* renamed from: a */
        public String m1137a() {
            return this.f841a;
        }

        @Override // com.xiaomi.push.AbstractC0592e
        /* renamed from: a */
        public void mo963a(C0538c c0538c) {
            if (m1138a()) {
                c0538c.m689a(1, m1137a());
            }
            if (m1141b()) {
                c0538c.m689a(2, m1140b());
            }
            if (m1142c()) {
                c0538c.m688a(3, (AbstractC0592e) m1134a());
            }
        }

        /* renamed from: a */
        public boolean m1138a() {
            return this.f842a;
        }

        @Override // com.xiaomi.push.AbstractC0592e
        /* renamed from: b */
        public int mo967b() {
            int i = 0;
            if (m1138a()) {
                i = 0 + C0538c.m663a(1, m1137a());
            }
            int i2 = i;
            if (m1141b()) {
                i2 = i + C0538c.m663a(2, m1140b());
            }
            int i3 = i2;
            if (m1142c()) {
                i3 = i2 + C0538c.m662a(3, (AbstractC0592e) m1134a());
            }
            this.f839a = i3;
            return i3;
        }

        /* renamed from: b */
        public f m1139b(String str) {
            this.f844b = true;
            this.f843b = str;
            return this;
        }

        /* renamed from: b */
        public String m1140b() {
            return this.f843b;
        }

        /* renamed from: b */
        public boolean m1141b() {
            return this.f844b;
        }

        /* renamed from: c */
        public boolean m1142c() {
            return this.f845c;
        }
    }

    /* renamed from: com.xiaomi.push.et$g */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/et$g.class */
    public static final class g extends AbstractC0592e {

        /* renamed from: a */
        private boolean f848a;

        /* renamed from: b */
        private boolean f850b;

        /* renamed from: c */
        private boolean f852c;

        /* renamed from: a */
        private String f847a = "";

        /* renamed from: b */
        private String f849b = "";

        /* renamed from: c */
        private String f851c = "";

        /* renamed from: a */
        private int f846a = -1;

        /* renamed from: a */
        public static g m1143a(byte[] bArr) {
            return (g) new g().m1143a(bArr);
        }

        @Override // com.xiaomi.push.AbstractC0592e
        /* renamed from: a */
        public int mo959a() {
            if (this.f846a < 0) {
                mo967b();
            }
            return this.f846a;
        }

        @Override // com.xiaomi.push.AbstractC0592e
        /* renamed from: a */
        public g mo960a(C0511b c0511b) {
            while (true) {
                int m539a = c0511b.m539a();
                if (m539a == 0) {
                    return this;
                }
                if (m539a == 10) {
                    m1144a(c0511b.m543a());
                } else if (m539a == 18) {
                    m1147b(c0511b.m543a());
                } else if (m539a == 26) {
                    m1150c(c0511b.m543a());
                } else if (!m965a(c0511b, m539a)) {
                    return this;
                }
            }
        }

        /* renamed from: a */
        public g m1144a(String str) {
            this.f848a = true;
            this.f847a = str;
            return this;
        }

        /* renamed from: a */
        public String m1145a() {
            return this.f847a;
        }

        @Override // com.xiaomi.push.AbstractC0592e
        /* renamed from: a */
        public void mo963a(C0538c c0538c) {
            if (m1146a()) {
                c0538c.m689a(1, m1145a());
            }
            if (m1149b()) {
                c0538c.m689a(2, m1148b());
            }
            if (m1152c()) {
                c0538c.m689a(3, m1151c());
            }
        }

        /* renamed from: a */
        public boolean m1146a() {
            return this.f848a;
        }

        @Override // com.xiaomi.push.AbstractC0592e
        /* renamed from: b */
        public int mo967b() {
            int i = 0;
            if (m1146a()) {
                i = 0 + C0538c.m663a(1, m1145a());
            }
            int i2 = i;
            if (m1149b()) {
                i2 = i + C0538c.m663a(2, m1148b());
            }
            int i3 = i2;
            if (m1152c()) {
                i3 = i2 + C0538c.m663a(3, m1151c());
            }
            this.f846a = i3;
            return i3;
        }

        /* renamed from: b */
        public g m1147b(String str) {
            this.f850b = true;
            this.f849b = str;
            return this;
        }

        /* renamed from: b */
        public String m1148b() {
            return this.f849b;
        }

        /* renamed from: b */
        public boolean m1149b() {
            return this.f850b;
        }

        /* renamed from: c */
        public g m1150c(String str) {
            this.f852c = true;
            this.f851c = str;
            return this;
        }

        /* renamed from: c */
        public String m1151c() {
            return this.f851c;
        }

        /* renamed from: c */
        public boolean m1152c() {
            return this.f852c;
        }
    }

    /* renamed from: com.xiaomi.push.et$h */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/et$h.class */
    public static final class h extends AbstractC0592e {

        /* renamed from: a */
        private boolean f855a;

        /* renamed from: b */
        private boolean f857b;

        /* renamed from: a */
        private int f853a = 0;

        /* renamed from: a */
        private String f854a = "";

        /* renamed from: b */
        private int f856b = -1;

        /* renamed from: a */
        public static h m1153a(byte[] bArr) {
            return (h) new h().m1153a(bArr);
        }

        @Override // com.xiaomi.push.AbstractC0592e
        /* renamed from: a */
        public int mo959a() {
            if (this.f856b < 0) {
                mo967b();
            }
            return this.f856b;
        }

        /* renamed from: a */
        public h m1154a(int i) {
            this.f855a = true;
            this.f853a = i;
            return this;
        }

        @Override // com.xiaomi.push.AbstractC0592e
        /* renamed from: a */
        public h mo960a(C0511b c0511b) {
            while (true) {
                int m539a = c0511b.m539a();
                if (m539a == 0) {
                    return this;
                }
                if (m539a == 8) {
                    m1154a(c0511b.m550b());
                } else if (m539a == 18) {
                    m1155a(c0511b.m543a());
                } else if (!m965a(c0511b, m539a)) {
                    return this;
                }
            }
        }

        /* renamed from: a */
        public h m1155a(String str) {
            this.f857b = true;
            this.f854a = str;
            return this;
        }

        /* renamed from: a */
        public String m1156a() {
            return this.f854a;
        }

        @Override // com.xiaomi.push.AbstractC0592e
        /* renamed from: a */
        public void mo963a(C0538c c0538c) {
            if (m1157a()) {
                c0538c.m685a(1, m1159c());
            }
            if (m1158b()) {
                c0538c.m689a(2, m1156a());
            }
        }

        /* renamed from: a */
        public boolean m1157a() {
            return this.f855a;
        }

        @Override // com.xiaomi.push.AbstractC0592e
        /* renamed from: b */
        public int mo967b() {
            int i = 0;
            if (m1157a()) {
                i = 0 + C0538c.m659a(1, m1159c());
            }
            int i2 = i;
            if (m1158b()) {
                i2 = i + C0538c.m663a(2, m1156a());
            }
            this.f856b = i2;
            return i2;
        }

        /* renamed from: b */
        public boolean m1158b() {
            return this.f857b;
        }

        /* renamed from: c */
        public int m1159c() {
            return this.f853a;
        }
    }

    /* renamed from: com.xiaomi.push.et$i */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/et$i.class */
    public static final class i extends AbstractC0592e {

        /* renamed from: a */
        private boolean f860a;

        /* renamed from: a */
        private C0484a f859a = C0484a.f400a;

        /* renamed from: a */
        private int f858a = -1;

        /* renamed from: a */
        public static i m1160a(byte[] bArr) {
            return (i) new i().m1160a(bArr);
        }

        @Override // com.xiaomi.push.AbstractC0592e
        /* renamed from: a */
        public int mo959a() {
            if (this.f858a < 0) {
                mo967b();
            }
            return this.f858a;
        }

        /* renamed from: a */
        public C0484a m1161a() {
            return this.f859a;
        }

        /* renamed from: a */
        public i m1162a(C0484a c0484a) {
            this.f860a = true;
            this.f859a = c0484a;
            return this;
        }

        @Override // com.xiaomi.push.AbstractC0592e
        /* renamed from: a */
        public i mo960a(C0511b c0511b) {
            while (true) {
                int m539a = c0511b.m539a();
                if (m539a == 0) {
                    return this;
                }
                if (m539a == 10) {
                    m1162a(c0511b.m542a());
                } else if (!m965a(c0511b, m539a)) {
                    return this;
                }
            }
        }

        @Override // com.xiaomi.push.AbstractC0592e
        /* renamed from: a */
        public void mo963a(C0538c c0538c) {
            if (m1163a()) {
                c0538c.m687a(1, m1161a());
            }
        }

        /* renamed from: a */
        public boolean m1163a() {
            return this.f860a;
        }

        @Override // com.xiaomi.push.AbstractC0592e
        /* renamed from: b */
        public int mo967b() {
            int i = 0;
            if (m1163a()) {
                i = 0 + C0538c.m661a(1, m1161a());
            }
            this.f858a = i;
            return i;
        }
    }

    /* renamed from: com.xiaomi.push.et$j */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/et$j.class */
    public static final class j extends AbstractC0592e {

        /* renamed from: a */
        private boolean f864a;

        /* renamed from: b */
        private boolean f865b;

        /* renamed from: a */
        private C0484a f862a = C0484a.f400a;

        /* renamed from: a */
        private b f863a = null;

        /* renamed from: a */
        private int f861a = -1;

        /* renamed from: a */
        public static j m1164a(byte[] bArr) {
            return (j) new j().m1164a(bArr);
        }

        @Override // com.xiaomi.push.AbstractC0592e
        /* renamed from: a */
        public int mo959a() {
            if (this.f861a < 0) {
                mo967b();
            }
            return this.f861a;
        }

        /* renamed from: a */
        public C0484a m1165a() {
            return this.f862a;
        }

        /* renamed from: a */
        public b m1166a() {
            return this.f863a;
        }

        /* renamed from: a */
        public j m1167a(C0484a c0484a) {
            this.f864a = true;
            this.f862a = c0484a;
            return this;
        }

        @Override // com.xiaomi.push.AbstractC0592e
        /* renamed from: a */
        public j mo960a(C0511b c0511b) {
            while (true) {
                int m539a = c0511b.m539a();
                if (m539a == 0) {
                    return this;
                }
                if (m539a == 10) {
                    m1167a(c0511b.m542a());
                } else if (m539a == 18) {
                    b bVar = new b();
                    c0511b.m546a(bVar);
                    m1168a(bVar);
                } else if (!m965a(c0511b, m539a)) {
                    return this;
                }
            }
        }

        /* renamed from: a */
        public j m1168a(b bVar) {
            if (bVar == null) {
                throw new NullPointerException();
            }
            this.f865b = true;
            this.f863a = bVar;
            return this;
        }

        @Override // com.xiaomi.push.AbstractC0592e
        /* renamed from: a */
        public void mo963a(C0538c c0538c) {
            if (m1169a()) {
                c0538c.m687a(1, m1165a());
            }
            if (m1170b()) {
                c0538c.m688a(2, (AbstractC0592e) m1166a());
            }
        }

        /* renamed from: a */
        public boolean m1169a() {
            return this.f864a;
        }

        @Override // com.xiaomi.push.AbstractC0592e
        /* renamed from: b */
        public int mo967b() {
            int i = 0;
            if (m1169a()) {
                i = 0 + C0538c.m661a(1, m1165a());
            }
            int i2 = i;
            if (m1170b()) {
                i2 = i + C0538c.m662a(2, (AbstractC0592e) m1166a());
            }
            this.f861a = i2;
            return i2;
        }

        /* renamed from: b */
        public boolean m1170b() {
            return this.f865b;
        }
    }

    /* renamed from: com.xiaomi.push.et$k */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/et$k.class */
    public static final class k extends AbstractC0592e {

        /* renamed from: a */
        private boolean f869a;

        /* renamed from: b */
        private boolean f873b;

        /* renamed from: c */
        private boolean f874c;

        /* renamed from: d */
        private boolean f875d;

        /* renamed from: e */
        private boolean f876e;

        /* renamed from: g */
        private boolean f878g;

        /* renamed from: a */
        private String f868a = "";

        /* renamed from: b */
        private String f872b = "";

        /* renamed from: a */
        private long f867a = 0;

        /* renamed from: b */
        private long f871b = 0;

        /* renamed from: f */
        private boolean f877f = false;

        /* renamed from: a */
        private int f866a = 0;

        /* renamed from: b */
        private int f870b = -1;

        /* renamed from: a */
        public static k m1171a(byte[] bArr) {
            return (k) new k().m1171a(bArr);
        }

        @Override // com.xiaomi.push.AbstractC0592e
        /* renamed from: a */
        public int mo959a() {
            if (this.f870b < 0) {
                mo967b();
            }
            return this.f870b;
        }

        /* renamed from: a */
        public long m1172a() {
            return this.f867a;
        }

        /* renamed from: a */
        public k m1173a(int i) {
            this.f878g = true;
            this.f866a = i;
            return this;
        }

        /* renamed from: a */
        public k m1174a(long j) {
            this.f874c = true;
            this.f867a = j;
            return this;
        }

        @Override // com.xiaomi.push.AbstractC0592e
        /* renamed from: a */
        public k mo960a(C0511b c0511b) {
            while (true) {
                int m539a = c0511b.m539a();
                if (m539a == 0) {
                    return this;
                }
                if (m539a == 10) {
                    m1175a(c0511b.m543a());
                } else if (m539a == 18) {
                    m1181b(c0511b.m543a());
                } else if (m539a == 24) {
                    m1174a(c0511b.m541a());
                } else if (m539a == 32) {
                    m1180b(c0511b.m541a());
                } else if (m539a == 40) {
                    m1176a(c0511b.m547a());
                } else if (m539a == 48) {
                    m1173a(c0511b.m550b());
                } else if (!m965a(c0511b, m539a)) {
                    return this;
                }
            }
        }

        /* renamed from: a */
        public k m1175a(String str) {
            this.f869a = true;
            this.f868a = str;
            return this;
        }

        /* renamed from: a */
        public k m1176a(boolean z) {
            this.f876e = true;
            this.f877f = z;
            return this;
        }

        /* renamed from: a */
        public String m1177a() {
            return this.f868a;
        }

        @Override // com.xiaomi.push.AbstractC0592e
        /* renamed from: a */
        public void mo963a(C0538c c0538c) {
            if (m1178a()) {
                c0538c.m689a(1, m1177a());
            }
            if (m1183b()) {
                c0538c.m689a(2, m1182b());
            }
            if (m1185c()) {
                c0538c.m686a(3, m1172a());
            }
            if (m1186d()) {
                c0538c.m686a(4, m1179b());
            }
            if (m1188f()) {
                c0538c.m690a(5, m1187e());
            }
            if (m1189g()) {
                c0538c.m685a(6, m1184c());
            }
        }

        /* renamed from: a */
        public boolean m1178a() {
            return this.f869a;
        }

        @Override // com.xiaomi.push.AbstractC0592e
        /* renamed from: b */
        public int mo967b() {
            int i = 0;
            if (m1178a()) {
                i = 0 + C0538c.m663a(1, m1177a());
            }
            int i2 = i;
            if (m1183b()) {
                i2 = i + C0538c.m663a(2, m1182b());
            }
            int i3 = i2;
            if (m1185c()) {
                i3 = i2 + C0538c.m660a(3, m1172a());
            }
            int i4 = i3;
            if (m1186d()) {
                i4 = i3 + C0538c.m660a(4, m1179b());
            }
            int i5 = i4;
            if (m1188f()) {
                i5 = i4 + C0538c.m664a(5, m1187e());
            }
            int i6 = i5;
            if (m1189g()) {
                i6 = i5 + C0538c.m659a(6, m1184c());
            }
            this.f870b = i6;
            return i6;
        }

        /* renamed from: b */
        public long m1179b() {
            return this.f871b;
        }

        /* renamed from: b */
        public k m1180b(long j) {
            this.f875d = true;
            this.f871b = j;
            return this;
        }

        /* renamed from: b */
        public k m1181b(String str) {
            this.f873b = true;
            this.f872b = str;
            return this;
        }

        /* renamed from: b */
        public String m1182b() {
            return this.f872b;
        }

        /* renamed from: b */
        public boolean m1183b() {
            return this.f873b;
        }

        /* renamed from: c */
        public int m1184c() {
            return this.f866a;
        }

        /* renamed from: c */
        public boolean m1185c() {
            return this.f874c;
        }

        /* renamed from: d */
        public boolean m1186d() {
            return this.f875d;
        }

        /* renamed from: e */
        public boolean m1187e() {
            return this.f877f;
        }

        /* renamed from: f */
        public boolean m1188f() {
            return this.f876e;
        }

        /* renamed from: g */
        public boolean m1189g() {
            return this.f878g;
        }
    }
}
