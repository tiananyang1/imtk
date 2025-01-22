package com.xiaomi.push;

import android.content.Context;
import io.fabric.sdk.android.services.events.EventsFilesManager;

/* renamed from: com.xiaomi.push.cc */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/cc.class */
public class C0541cc {

    /* renamed from: a */
    private long f541a;

    /* renamed from: a */
    private Context f542a;

    /* renamed from: a */
    private String f543a;

    /* renamed from: b */
    private long f544b;

    /* renamed from: c */
    private long f545c;

    /* renamed from: d */
    private long f546d;

    public C0541cc(Context context) {
        this.f542a = context;
        m710a();
    }

    /* renamed from: a */
    public long m708a() {
        return this.f541a;
    }

    /* renamed from: a */
    public String m709a() {
        return this.f543a;
    }

    /* renamed from: a */
    public void m710a() {
        this.f543a = null;
        this.f541a = 0L;
        this.f544b = 0L;
        this.f545c = 0L;
        this.f546d = 0L;
    }

    /* renamed from: a */
    public void m711a(String str) {
        m718e();
        m710a();
        m714b(str);
    }

    /* renamed from: b */
    public long m712b() {
        return this.f544b;
    }

    /* renamed from: b */
    public void m713b() {
        this.f544b += System.currentTimeMillis() - this.f541a;
    }

    /* renamed from: b */
    public void m714b(String str) {
        String m769a = C0548cj.m769a(this.f542a, str, "none");
        if (m769a == null || "none".equals(m769a)) {
            m710a();
            this.f543a = str;
            long currentTimeMillis = System.currentTimeMillis();
            this.f546d = currentTimeMillis;
            this.f545c = currentTimeMillis;
            this.f541a = currentTimeMillis;
            return;
        }
        try {
            String[] split = m769a.split(EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR);
            this.f543a = str;
            this.f541a = Long.valueOf(split[1]).longValue();
            this.f544b = Long.valueOf(split[2]).longValue();
            this.f545c = Long.valueOf(split[3]).longValue();
            this.f546d = Long.valueOf(split[4]).longValue();
        } catch (Exception e) {
        }
    }

    /* renamed from: c */
    public long m715c() {
        return this.f546d;
    }

    /* renamed from: c */
    public void m716c() {
        this.f546d = System.currentTimeMillis();
    }

    /* renamed from: d */
    public void m717d() {
        m713b();
        m718e();
        m710a();
    }

    /* renamed from: e */
    public void m718e() {
        String str = this.f543a;
        if (str != null) {
            C0548cj.m772a(this.f542a, str, toString());
        }
    }

    public String toString() {
        if (this.f543a == null) {
            return "";
        }
        return this.f543a + EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR + this.f541a + EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR + this.f544b + EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR + this.f545c + EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR + this.f546d;
    }
}
