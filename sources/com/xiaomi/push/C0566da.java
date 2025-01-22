package com.xiaomi.push;

import com.xiaomi.mipush.sdk.Constants;
import java.net.InetSocketAddress;

/* renamed from: com.xiaomi.push.da */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/da.class */
public final class C0566da {

    /* renamed from: a */
    private int f648a;

    /* renamed from: a */
    private String f649a;

    public C0566da(String str, int i) {
        this.f649a = str;
        this.f648a = i;
    }

    /* renamed from: a */
    public static C0566da m866a(String str, int i) {
        int lastIndexOf = str.lastIndexOf(Constants.COLON_SEPARATOR);
        if (lastIndexOf != -1) {
            String substring = str.substring(0, lastIndexOf);
            try {
                int parseInt = Integer.parseInt(str.substring(lastIndexOf + 1));
                if (parseInt <= 0) {
                    str = substring;
                } else {
                    i = parseInt;
                    str = substring;
                }
            } catch (NumberFormatException e) {
                str = substring;
            }
        }
        return new C0566da(str, i);
    }

    /* renamed from: a */
    public static InetSocketAddress m867a(String str, int i) {
        C0566da m866a = m866a(str, i);
        return new InetSocketAddress(m866a.m869a(), m866a.m868a());
    }

    /* renamed from: a */
    public int m868a() {
        return this.f648a;
    }

    /* renamed from: a */
    public String m869a() {
        return this.f649a;
    }

    public String toString() {
        if (this.f648a <= 0) {
            return this.f649a;
        }
        return this.f649a + Constants.COLON_SEPARATOR + this.f648a;
    }
}
