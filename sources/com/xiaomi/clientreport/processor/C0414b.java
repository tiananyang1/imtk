package com.xiaomi.clientreport.processor;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.clientreport.data.C0408a;
import com.xiaomi.clientreport.data.PerfClientReport;
import com.xiaomi.push.C0516be;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.xiaomi.clientreport.processor.b */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/clientreport/processor/b.class */
public class C0414b implements IPerfProcessor {

    /* renamed from: a */
    protected Context f237a;

    /* renamed from: a */
    private HashMap<String, HashMap<String, C0408a>> f238a;

    public C0414b(Context context) {
        this.f237a = context;
    }

    /* renamed from: a */
    public static String m102a(C0408a c0408a) {
        return String.valueOf(c0408a.production) + "#" + c0408a.clientInterfaceId;
    }

    /* renamed from: b */
    private String m103b(C0408a c0408a) {
        String str;
        int i = c0408a.production;
        String str2 = c0408a.clientInterfaceId;
        if (i <= 0 || TextUtils.isEmpty(str2)) {
            str = "";
        } else {
            str = String.valueOf(i) + "#" + str2;
        }
        File externalFilesDir = this.f237a.getExternalFilesDir("perf");
        if (externalFilesDir == null) {
            AbstractC0407b.m75d("cannot get folder when to write perf");
            return null;
        }
        if (!externalFilesDir.exists()) {
            externalFilesDir.mkdirs();
        }
        return new File(externalFilesDir, str).getAbsolutePath();
    }

    /* renamed from: c */
    private String m104c(C0408a c0408a) {
        String m103b = m103b(c0408a);
        if (TextUtils.isEmpty(m103b)) {
            return null;
        }
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= 20) {
                return null;
            }
            String str = m103b + i2;
            if (C0516be.m571a(this.f237a, str)) {
                return str;
            }
            i = i2 + 1;
        }
    }

    @Override // com.xiaomi.clientreport.processor.InterfaceC0415c
    /* renamed from: a */
    public void mo96a() {
        C0516be.m568a(this.f237a, "perf", "perfUploading");
        File[] m573a = C0516be.m573a(this.f237a, "perfUploading");
        if (m573a == null || m573a.length <= 0) {
            return;
        }
        AbstractC0407b.m74c(this.f237a.getPackageName() + "  perfread  paths " + m573a.length);
        int length = m573a.length;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length) {
                return;
            }
            File file = m573a[i2];
            if (file != null) {
                List<String> m111a = C0417e.m111a(this.f237a, file.getAbsolutePath());
                file.delete();
                mo105a(m111a);
            }
            i = i2 + 1;
        }
    }

    @Override // com.xiaomi.clientreport.processor.InterfaceC0416d
    /* renamed from: a */
    public void mo98a(C0408a c0408a) {
        if ((c0408a instanceof PerfClientReport) && this.f238a != null) {
            PerfClientReport perfClientReport = (PerfClientReport) c0408a;
            String m102a = m102a((C0408a) perfClientReport);
            String m109a = C0417e.m109a(perfClientReport);
            HashMap<String, C0408a> hashMap = this.f238a.get(m102a);
            HashMap<String, C0408a> hashMap2 = hashMap;
            if (hashMap == null) {
                hashMap2 = new HashMap<>();
            }
            PerfClientReport perfClientReport2 = (PerfClientReport) hashMap2.get(m109a);
            if (perfClientReport2 != null) {
                perfClientReport.perfCounts += perfClientReport2.perfCounts;
                perfClientReport.perfLatencies += perfClientReport2.perfLatencies;
            }
            hashMap2.put(m109a, perfClientReport);
            this.f238a.put(m102a, hashMap2);
            AbstractC0407b.m74c("pre perf inner " + hashMap2.size() + " outer " + this.f238a.size());
        }
    }

    /* renamed from: a */
    public void mo105a(List<String> list) {
        C0516be.m569a(this.f237a, list);
    }

    /* renamed from: a */
    public void m106a(C0408a[] c0408aArr) {
        String m104c = m104c(c0408aArr[0]);
        if (TextUtils.isEmpty(m104c)) {
            return;
        }
        C0417e.m113a(m104c, c0408aArr);
    }

    @Override // com.xiaomi.clientreport.processor.InterfaceC0416d
    /* renamed from: b */
    public void mo101b() {
        HashMap<String, HashMap<String, C0408a>> hashMap = this.f238a;
        if (hashMap == null) {
            return;
        }
        if (hashMap.size() > 0) {
            Iterator<String> it = this.f238a.keySet().iterator();
            while (it.hasNext()) {
                HashMap<String, C0408a> hashMap2 = this.f238a.get(it.next());
                if (hashMap2 != null && hashMap2.size() > 0) {
                    AbstractC0407b.m74c("begin write perfJob " + hashMap2.size());
                    C0408a[] c0408aArr = new C0408a[hashMap2.size()];
                    hashMap2.values().toArray(c0408aArr);
                    m106a(c0408aArr);
                }
            }
        }
        this.f238a.clear();
    }

    @Override // com.xiaomi.clientreport.processor.IPerfProcessor
    public void setPerfMap(HashMap<String, HashMap<String, C0408a>> hashMap) {
        this.f238a = hashMap;
    }
}
