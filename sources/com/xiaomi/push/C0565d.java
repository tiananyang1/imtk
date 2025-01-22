package com.xiaomi.push;

import java.io.IOException;

/* renamed from: com.xiaomi.push.d */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/d.class */
public class C0565d extends IOException {
    public C0565d(String str) {
        super(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static C0565d m858a() {
        return new C0565d("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static C0565d m859b() {
        return new C0565d("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public static C0565d m860c() {
        return new C0565d("CodedInputStream encountered a malformed varint.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: d */
    public static C0565d m861d() {
        return new C0565d("Protocol message contained an invalid tag (zero).");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: e */
    public static C0565d m862e() {
        return new C0565d("Protocol message end-group tag did not match expected tag.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: f */
    public static C0565d m863f() {
        return new C0565d("Protocol message tag had invalid wire type.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: g */
    public static C0565d m864g() {
        return new C0565d("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: h */
    public static C0565d m865h() {
        return new C0565d("Protocol message was too large.  May be malicious.  Use CodedInputStream.setSizeLimit() to increase the size limit.");
    }
}
