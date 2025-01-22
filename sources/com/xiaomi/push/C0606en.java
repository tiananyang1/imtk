package com.xiaomi.push;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.xiaomi.push.C0493ai;
import com.xiaomi.push.service.C0809ao;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.xiaomi.push.en */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/en.class */
public class C0606en extends C0493ai.a {

    /* renamed from: a */
    private Context f744a;

    /* renamed from: a */
    private SharedPreferences f745a;

    /* renamed from: a */
    private C0809ao f746a;

    public C0606en(Context context) {
        this.f744a = context;
        this.f745a = context.getSharedPreferences("mipush_extra", 0);
        this.f746a = C0809ao.m2557a(context);
    }

    /* renamed from: a */
    private List<C0707ig> m996a(File file) {
        FileLock fileLock;
        RandomAccessFile randomAccessFile;
        FileInputStream fileInputStream;
        FileLock fileLock2;
        FileInputStream fileInputStream2;
        InterfaceC0584ds m947a = C0585dt.m946a().m947a();
        String mo371a = m947a == null ? "" : m947a.mo371a();
        if (TextUtils.isEmpty(mo371a)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        byte[] bArr = new byte[4];
        synchronized (C0590dy.f724a) {
            try {
                File file2 = new File(this.f744a.getExternalFilesDir(null), "push_cdata.lock");
                C0883y.m2863a(file2);
                randomAccessFile = new RandomAccessFile(file2, "rw");
                try {
                    fileLock = randomAccessFile.getChannel().lock();
                    try {
                        FileInputStream fileInputStream3 = new FileInputStream(file);
                        while (fileInputStream3.read(bArr) == 4) {
                            try {
                                int m428a = C0487ac.m428a(bArr);
                                byte[] bArr2 = new byte[m428a];
                                if (fileInputStream3.read(bArr2) != m428a) {
                                    break;
                                }
                                byte[] m957a = C0589dx.m957a(mo371a, bArr2);
                                if (m957a != null && m957a.length != 0) {
                                    C0707ig c0707ig = new C0707ig();
                                    C0743jp.m2313a(c0707ig, m957a);
                                    arrayList.add(c0707ig);
                                }
                            } catch (Exception e) {
                                fileInputStream2 = fileInputStream3;
                                fileLock2 = fileLock;
                                if (fileLock2 != null && fileLock2.isValid()) {
                                    try {
                                        fileLock2.release();
                                    } catch (IOException e2) {
                                    }
                                }
                                C0883y.m2858a(fileInputStream2);
                                C0883y.m2858a(randomAccessFile);
                                return arrayList;
                            } catch (Throwable th) {
                                fileInputStream = fileInputStream3;
                                th = th;
                                if (fileLock != null && fileLock.isValid()) {
                                    try {
                                        fileLock.release();
                                    } catch (IOException e3) {
                                    }
                                }
                                C0883y.m2858a(fileInputStream);
                                C0883y.m2858a(randomAccessFile);
                                throw th;
                            }
                        }
                        if (fileLock != null && fileLock.isValid()) {
                            try {
                                fileLock.release();
                            } catch (IOException e4) {
                            }
                        }
                        C0883y.m2858a(fileInputStream3);
                    } catch (Exception e5) {
                        fileInputStream2 = null;
                        fileLock2 = fileLock;
                    } catch (Throwable th2) {
                        th = th2;
                        fileInputStream = null;
                    }
                } catch (Exception e6) {
                    fileLock2 = null;
                    fileInputStream2 = null;
                } catch (Throwable th3) {
                    th = th3;
                    fileLock = null;
                    fileInputStream = null;
                }
            } catch (Exception e7) {
                fileLock2 = null;
                randomAccessFile = null;
                fileInputStream2 = null;
            } catch (Throwable th4) {
                th = th4;
                fileLock = null;
                randomAccessFile = null;
                fileInputStream = null;
            }
            C0883y.m2858a(randomAccessFile);
        }
        return arrayList;
    }

    /* renamed from: a */
    private void m997a() {
        SharedPreferences.Editor edit = this.f745a.edit();
        edit.putLong("last_upload_data_timestamp", System.currentTimeMillis() / 1000);
        edit.commit();
    }

    /* renamed from: a */
    private boolean m998a() {
        if (C0503as.m486d(this.f744a)) {
            return false;
        }
        if (!C0503as.m488f(this.f744a) || m1000c()) {
            return (C0503as.m489g(this.f744a) && !m999b()) || C0503as.m490h(this.f744a);
        }
        return true;
    }

    /* renamed from: b */
    private boolean m999b() {
        boolean z = false;
        if (!this.f746a.m2563a(EnumC0703ic.Upload3GSwitch.m1669a(), true)) {
            return false;
        }
        if (Math.abs((System.currentTimeMillis() / 1000) - this.f745a.getLong("last_upload_data_timestamp", -1L)) > Math.max(86400, this.f746a.m2561a(EnumC0703ic.Upload3GFrequency.m1669a(), 432000))) {
            z = true;
        }
        return z;
    }

    /* renamed from: c */
    private boolean m1000c() {
        boolean z = false;
        if (!this.f746a.m2563a(EnumC0703ic.Upload4GSwitch.m1669a(), true)) {
            return false;
        }
        if (Math.abs((System.currentTimeMillis() / 1000) - this.f745a.getLong("last_upload_data_timestamp", -1L)) > Math.max(86400, this.f746a.m2561a(EnumC0703ic.Upload4GFrequency.m1669a(), 259200))) {
            z = true;
        }
        return z;
    }

    @Override // com.xiaomi.push.C0493ai.a
    /* renamed from: a */
    public int mo185a() {
        return 1;
    }

    @Override // java.lang.Runnable
    public void run() {
        File file = new File(this.f744a.getExternalFilesDir(null), "push_cdata.data");
        if (!C0503as.m485c(this.f744a)) {
            if (file.length() > 1863680) {
                file.delete();
                return;
            }
            return;
        }
        if (!m998a() && file.exists()) {
            List<C0707ig> m996a = m996a(file);
            if (!C0488ad.m430a(m996a)) {
                int size = m996a.size();
                List<C0707ig> list = m996a;
                if (size > 4000) {
                    list = m996a.subList(size - 4000, size);
                }
                C0725iy c0725iy = new C0725iy();
                c0725iy.m1951a(list);
                byte[] m2864a = C0883y.m2864a(C0743jp.m2314a(c0725iy));
                C0732je c0732je = new C0732je("-1", false);
                c0732je.m2075c(EnumC0714in.DataCollection.f1752a);
                c0732je.m2062a(m2864a);
                InterfaceC0584ds m947a = C0585dt.m946a().m947a();
                if (m947a != null) {
                    m947a.mo372a(c0732je, EnumC0696hw.Notification, null);
                }
                m997a();
            }
            file.delete();
            this.f745a.edit().remove("ltapn").commit();
        }
    }
}
