package com.xiaomi.push;

import java.util.HashMap;
import java.util.Map;

/* renamed from: com.xiaomi.push.bs */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/bs.class */
public final class C0530bs {

    /* renamed from: a */
    private static String f520a;

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0038, code lost:            if (r0 < 0) goto L15;     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String m640a() {
        /*
            java.lang.String r0 = com.xiaomi.push.C0528bq.m633b()
            r9 = r0
            java.lang.String r0 = com.xiaomi.push.C0528bq.m635c()
            r10 = r0
            java.lang.String r0 = com.xiaomi.push.C0528bq.m637e()
            r11 = r0
            int r0 = com.xiaomi.push.C0528bq.m628a()
            r7 = r0
            int r0 = com.xiaomi.push.C0528bq.m632b()
            r8 = r0
            r0 = r9
            if (r0 == 0) goto L68
            r0 = r9
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L68
            r0 = r10
            if (r0 == 0) goto L68
            r0 = r10
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L31
            goto L68
        L31:
            r0 = r7
            if (r0 < 0) goto L3b
            r0 = r8
            r6 = r0
            r0 = r8
            if (r0 >= 0) goto L42
        L3b:
            r0 = 999(0x3e7, float:1.4E-42)
            r7 = r0
            r0 = 99
            r6 = r0
        L42:
            java.lang.String r0 = "%s__%s__%d__%d__%s"
            r1 = 5
            java.lang.Object[] r1 = new java.lang.Object[r1]
            r2 = r1
            r3 = 0
            r4 = r9
            r2[r3] = r4
            r2 = r1
            r3 = 1
            r4 = r10
            r2[r3] = r4
            r2 = r1
            r3 = 2
            r4 = r7
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r2[r3] = r4
            r2 = r1
            r3 = 3
            r4 = r6
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r2[r3] = r4
            r2 = r1
            r3 = 4
            r4 = r11
            r2[r3] = r4
            java.lang.String r0 = java.lang.String.format(r0, r1)
            return r0
        L68:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.C0530bs.m640a():java.lang.String");
    }

    /* renamed from: a */
    public static String m641a(String str, String str2) {
        String str3;
        String m639a = C0529br.m639a();
        String m640a = m640a();
        if (m640a == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer(m639a);
        stringBuffer.append("/base/profile");
        stringBuffer.append("/");
        stringBuffer.append("metoknlpsdk");
        stringBuffer.append("/");
        stringBuffer.append(str);
        stringBuffer.append("/");
        stringBuffer.append(m640a);
        stringBuffer.append("__");
        stringBuffer.append(str2);
        String stringBuffer2 = stringBuffer.toString();
        Map m642a = m642a();
        try {
            str3 = C0525bn.m622a(stringBuffer2, m642a);
        } catch (Exception e) {
            str3 = null;
        }
        m642a.clear();
        return str3;
    }

    /* renamed from: a */
    private static Map m642a() {
        String m640a = m640a();
        HashMap hashMap = new HashMap();
        if (f520a == null) {
            String m629a = C0528bq.m629a();
            if (m629a == null || m629a.isEmpty()) {
                return null;
            }
            String m630a = C0528bq.m630a(m629a);
            if (m630a != null) {
                f520a = m630a;
            }
            if (f520a == null) {
                return null;
            }
        }
        hashMap.put("CCPVER", f520a);
        hashMap.put("CCPINF", m640a);
        return hashMap;
    }
}
