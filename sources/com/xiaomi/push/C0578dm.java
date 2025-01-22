package com.xiaomi.push;

import com.xiaomi.push.C0577dl;
import java.io.File;
import java.util.Date;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.push.dm */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/dm.class */
public class C0578dm extends C0577dl.b {

    /* renamed from: a */
    final /* synthetic */ int f696a;

    /* renamed from: a */
    final /* synthetic */ C0577dl f697a;

    /* renamed from: a */
    File f698a;

    /* renamed from: a */
    final /* synthetic */ String f699a;

    /* renamed from: a */
    final /* synthetic */ Date f700a;

    /* renamed from: a */
    final /* synthetic */ boolean f701a;

    /* renamed from: b */
    final /* synthetic */ String f702b;

    /* renamed from: b */
    final /* synthetic */ Date f703b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0578dm(C0577dl c0577dl, int i, Date date, Date date2, String str, String str2, boolean z) {
        super();
        this.f697a = c0577dl;
        this.f696a = i;
        this.f700a = date;
        this.f703b = date2;
        this.f699a = str;
        this.f702b = str2;
        this.f701a = z;
    }

    @Override // com.xiaomi.push.C0577dl.b, com.xiaomi.push.C0496al.b
    /* renamed from: b */
    public void mo462b() {
        if (C0485aa.m423d()) {
            try {
                File file = new File(this.f697a.f684a.getExternalFilesDir(null) + "/.logcache");
                file.mkdirs();
                if (file.isDirectory()) {
                    C0576dk c0576dk = new C0576dk();
                    c0576dk.m926a(this.f696a);
                    this.f698a = c0576dk.m925a(this.f697a.f684a, this.f700a, this.f703b, file);
                }
            } catch (NullPointerException e) {
            }
        }
    }

    @Override // com.xiaomi.push.C0496al.b
    /* renamed from: c */
    public void mo463c() {
        File file = this.f698a;
        if (file != null && file.exists()) {
            this.f697a.f685a.add(new C0577dl.c(this.f699a, this.f702b, this.f698a, this.f701a));
        }
        this.f697a.m930a(0L);
    }
}
