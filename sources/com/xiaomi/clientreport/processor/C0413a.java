package com.xiaomi.clientreport.processor;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.clientreport.data.C0408a;
import com.xiaomi.clientreport.data.EventClientReport;
import com.xiaomi.clientreport.manager.C0410a;
import com.xiaomi.push.C0487ac;
import com.xiaomi.push.C0509ay;
import com.xiaomi.push.C0516be;
import com.xiaomi.push.C0700i;
import com.xiaomi.push.C0883y;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/* renamed from: com.xiaomi.clientreport.processor.a */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/clientreport/processor/a.class */
public class C0413a implements IEventProcessor {

    /* renamed from: a */
    protected Context f235a;

    /* renamed from: a */
    private HashMap<String, ArrayList<C0408a>> f236a;

    public C0413a(Context context) {
        m97a(context);
    }

    /* renamed from: a */
    public static String m92a(C0408a c0408a) {
        return String.valueOf(c0408a.production);
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x0063, code lost:            com.xiaomi.channel.commonutils.logger.AbstractC0407b.m75d(r7);     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00a3, code lost:            r7 = "eventData read from cache file failed cause lengthBuffer < 1 || lengthBuffer > 4K";     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x003e, code lost:            com.xiaomi.channel.commonutils.logger.AbstractC0407b.m75d("eventData read from cache file failed because magicNumber error");     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.util.List<java.lang.String> m93a(java.lang.String r7) {
        /*
            Method dump skipped, instructions count: 235
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.clientreport.processor.C0413a.m93a(java.lang.String):java.util.List");
    }

    /* renamed from: a */
    private void m94a(C0408a[] c0408aArr, String str) {
        BufferedOutputStream bufferedOutputStream;
        if (c0408aArr == null || c0408aArr.length <= 0 || TextUtils.isEmpty(str)) {
            return;
        }
        File file = new File(str);
        BufferedOutputStream bufferedOutputStream2 = null;
        try {
            try {
                BufferedOutputStream bufferedOutputStream3 = new BufferedOutputStream(new FileOutputStream(file, true));
                try {
                    int length = c0408aArr.length;
                    int i = 0;
                    while (true) {
                        int i2 = i;
                        if (i2 >= length) {
                            C0883y.m2858a(bufferedOutputStream3);
                            return;
                        }
                        C0408a c0408a = c0408aArr[i2];
                        if (c0408a != null) {
                            byte[] stringToBytes = stringToBytes(c0408a.toJsonString());
                            if (stringToBytes != null && stringToBytes.length >= 1 && stringToBytes.length <= 4096) {
                                bufferedOutputStream3.write(C0487ac.m429a(-573785174));
                                bufferedOutputStream3.write(C0487ac.m429a(stringToBytes.length));
                                bufferedOutputStream3.write(stringToBytes);
                                bufferedOutputStream3.flush();
                            }
                            AbstractC0407b.m75d("event data throw a invalid item ");
                        }
                        i = i2 + 1;
                    }
                } catch (Exception e) {
                    bufferedOutputStream = bufferedOutputStream3;
                    e = e;
                    AbstractC0407b.m71a("event data write to cache file failed cause exception", e);
                    C0883y.m2858a(bufferedOutputStream);
                } catch (Throwable th) {
                    th = th;
                    bufferedOutputStream2 = bufferedOutputStream3;
                    C0883y.m2858a(bufferedOutputStream2);
                    throw th;
                }
            } catch (Exception e2) {
                e = e2;
                bufferedOutputStream = null;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* renamed from: b */
    private String m95b(C0408a c0408a) {
        File externalFilesDir = this.f235a.getExternalFilesDir("event");
        String m92a = m92a(c0408a);
        if (externalFilesDir == null) {
            return null;
        }
        String str = externalFilesDir.getAbsolutePath() + File.separator + m92a;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= 100) {
                return null;
            }
            String str2 = str + i2;
            if (C0516be.m571a(this.f235a, str2)) {
                return str2;
            }
            i = i2 + 1;
        }
    }

    @Override // com.xiaomi.clientreport.processor.InterfaceC0415c
    /* renamed from: a */
    public void mo96a() {
        File file;
        FileLock fileLock;
        RandomAccessFile randomAccessFile;
        File file2;
        C0516be.m568a(this.f235a, "event", "eventUploading");
        File[] m573a = C0516be.m573a(this.f235a, "eventUploading");
        if (m573a == null || m573a.length <= 0) {
            return;
        }
        int length = m573a.length;
        int i = 0;
        FileLock fileLock2 = null;
        RandomAccessFile randomAccessFile2 = null;
        File file3 = null;
        while (true) {
            File file4 = file3;
            if (i >= length) {
                return;
            }
            File file5 = m573a[i];
            if (file5 == null) {
                if (fileLock2 != null && fileLock2.isValid()) {
                    try {
                        fileLock2.release();
                    } catch (IOException e) {
                        AbstractC0407b.m72a(e);
                    }
                }
                C0883y.m2858a(randomAccessFile2);
                fileLock = fileLock2;
                randomAccessFile = randomAccessFile2;
                file2 = file4;
                if (file4 == null) {
                    i++;
                    fileLock2 = fileLock;
                    randomAccessFile2 = randomAccessFile;
                    file3 = file2;
                }
                file4.delete();
                fileLock = fileLock2;
                randomAccessFile = randomAccessFile2;
                file2 = file4;
                i++;
                fileLock2 = fileLock;
                randomAccessFile2 = randomAccessFile;
                file3 = file2;
            } else {
                FileLock fileLock3 = fileLock2;
                RandomAccessFile randomAccessFile3 = randomAccessFile2;
                try {
                    try {
                        String absolutePath = file5.getAbsolutePath();
                        FileLock fileLock4 = fileLock2;
                        StringBuilder sb = new StringBuilder();
                        FileLock fileLock5 = fileLock2;
                        sb.append(absolutePath);
                        FileLock fileLock6 = fileLock2;
                        sb.append(".lock");
                        FileLock fileLock7 = fileLock2;
                        file = new File(sb.toString());
                        try {
                            C0883y.m2863a(file);
                            RandomAccessFile randomAccessFile4 = new RandomAccessFile(file, "rw");
                            FileLock fileLock8 = fileLock2;
                            FileLock fileLock9 = fileLock2;
                            try {
                                FileLock lock = randomAccessFile4.getChannel().lock();
                                mo99a(m93a(absolutePath));
                                file5.delete();
                                if (lock != null && lock.isValid()) {
                                    try {
                                        lock.release();
                                    } catch (IOException e2) {
                                        AbstractC0407b.m72a(e2);
                                    }
                                }
                                C0883y.m2858a(randomAccessFile4);
                                file.delete();
                                file2 = file;
                                fileLock = lock;
                                randomAccessFile = randomAccessFile4;
                            } catch (Exception e3) {
                                randomAccessFile2 = randomAccessFile4;
                                fileLock2 = fileLock9;
                                e = e3;
                                file4 = file;
                                e = e;
                                AbstractC0407b.m72a(e);
                                if (fileLock2 != null && fileLock2.isValid()) {
                                    try {
                                        fileLock2.release();
                                    } catch (IOException e4) {
                                        AbstractC0407b.m72a(e4);
                                    }
                                }
                                C0883y.m2858a(randomAccessFile2);
                                fileLock = fileLock2;
                                randomAccessFile = randomAccessFile2;
                                file2 = file4;
                                if (file4 == null) {
                                    i++;
                                    fileLock2 = fileLock;
                                    randomAccessFile2 = randomAccessFile;
                                    file3 = file2;
                                }
                                file4.delete();
                                fileLock = fileLock2;
                                randomAccessFile = randomAccessFile2;
                                file2 = file4;
                                i++;
                                fileLock2 = fileLock;
                                randomAccessFile2 = randomAccessFile;
                                file3 = file2;
                            } catch (Throwable th) {
                                th = th;
                                fileLock2 = fileLock8;
                                randomAccessFile2 = randomAccessFile4;
                                if (fileLock2 != null && fileLock2.isValid()) {
                                    try {
                                        fileLock2.release();
                                    } catch (IOException e5) {
                                        AbstractC0407b.m72a(e5);
                                    }
                                }
                                C0883y.m2858a(randomAccessFile2);
                                if (file != null) {
                                    file.delete();
                                }
                                throw th;
                            }
                        } catch (Exception e6) {
                            e = e6;
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        fileLock2 = fileLock3;
                        randomAccessFile2 = randomAccessFile3;
                        file = file4;
                    }
                } catch (Exception e7) {
                    e = e7;
                }
                i++;
                fileLock2 = fileLock;
                randomAccessFile2 = randomAccessFile;
                file3 = file2;
            }
        }
    }

    /* renamed from: a */
    public void m97a(Context context) {
        this.f235a = context;
    }

    @Override // com.xiaomi.clientreport.processor.InterfaceC0416d
    /* renamed from: a */
    public void mo98a(C0408a c0408a) {
        if ((c0408a instanceof EventClientReport) && this.f236a != null) {
            EventClientReport eventClientReport = (EventClientReport) c0408a;
            String m92a = m92a((C0408a) eventClientReport);
            ArrayList<C0408a> arrayList = this.f236a.get(m92a);
            ArrayList<C0408a> arrayList2 = arrayList;
            if (arrayList == null) {
                arrayList2 = new ArrayList<>();
            }
            arrayList2.add(eventClientReport);
            this.f236a.put(m92a, arrayList2);
            AbstractC0407b.m74c("pre event out " + this.f236a.size() + " list " + arrayList2.size());
        }
    }

    /* renamed from: a */
    public void mo99a(List<String> list) {
        C0516be.m569a(this.f235a, list);
    }

    /* renamed from: a */
    public void m100a(C0408a[] c0408aArr) {
        RandomAccessFile randomAccessFile;
        RandomAccessFile randomAccessFile2;
        RandomAccessFile randomAccessFile3;
        if (c0408aArr == null || c0408aArr.length <= 0) {
            return;
        }
        if (c0408aArr[0] == null) {
            return;
        }
        String m95b = m95b(c0408aArr[0]);
        if (TextUtils.isEmpty(m95b)) {
            return;
        }
        FileLock fileLock = null;
        Closeable closeable = null;
        FileLock fileLock2 = null;
        try {
            try {
                File file = new File(m95b + ".lock");
                C0883y.m2863a(file);
                randomAccessFile3 = new RandomAccessFile(file, "rw");
                fileLock = null;
            } catch (Exception e) {
                e = e;
                randomAccessFile = null;
            } catch (Throwable th) {
                th = th;
                closeable = null;
                if (0 != 0) {
                    try {
                        fileLock2.release();
                    } catch (IOException e2) {
                        AbstractC0407b.m72a(e2);
                    }
                }
                C0883y.m2858a(closeable);
                throw th;
            }
            try {
                FileLock lock = randomAccessFile3.getChannel().lock();
                for (C0408a c0408a : c0408aArr) {
                    if (c0408a != null) {
                        fileLock = lock;
                        m94a(c0408aArr, m95b);
                    }
                }
                randomAccessFile2 = randomAccessFile3;
                if (lock != null) {
                    randomAccessFile2 = randomAccessFile3;
                    if (lock.isValid()) {
                        try {
                            lock.release();
                            randomAccessFile2 = randomAccessFile3;
                        } catch (IOException e3) {
                            randomAccessFile = randomAccessFile3;
                            e = e3;
                            AbstractC0407b.m72a(e);
                            randomAccessFile2 = randomAccessFile;
                            C0883y.m2858a(randomAccessFile2);
                        }
                    }
                }
            } catch (Exception e4) {
                randomAccessFile = randomAccessFile3;
                e = e4;
                AbstractC0407b.m72a(e);
                randomAccessFile2 = randomAccessFile;
                if (fileLock != null) {
                    randomAccessFile2 = randomAccessFile;
                    if (fileLock.isValid()) {
                        try {
                            fileLock.release();
                            randomAccessFile2 = randomAccessFile;
                        } catch (IOException e5) {
                            e = e5;
                            AbstractC0407b.m72a(e);
                            randomAccessFile2 = randomAccessFile;
                            C0883y.m2858a(randomAccessFile2);
                        }
                    }
                }
                C0883y.m2858a(randomAccessFile2);
            }
            C0883y.m2858a(randomAccessFile2);
        } catch (Throwable th2) {
            th = th2;
            if (0 != 0 && fileLock2.isValid()) {
                fileLock2.release();
            }
            C0883y.m2858a(closeable);
            throw th;
        }
    }

    @Override // com.xiaomi.clientreport.processor.InterfaceC0416d
    /* renamed from: b */
    public void mo101b() {
        HashMap<String, ArrayList<C0408a>> hashMap = this.f236a;
        if (hashMap == null) {
            return;
        }
        if (hashMap.size() > 0) {
            Iterator<String> it = this.f236a.keySet().iterator();
            while (it.hasNext()) {
                ArrayList<C0408a> arrayList = this.f236a.get(it.next());
                if (arrayList != null && arrayList.size() > 0) {
                    C0408a[] c0408aArr = new C0408a[arrayList.size()];
                    AbstractC0407b.m74c("begin write eventJob " + arrayList.size());
                    arrayList.toArray(c0408aArr);
                    m100a(c0408aArr);
                }
            }
        }
        this.f236a.clear();
    }

    @Override // com.xiaomi.clientreport.processor.IEventProcessor
    public String bytesToString(byte[] bArr) {
        byte[] m572a;
        if (bArr == null || bArr.length < 1) {
            return null;
        }
        if (!C0410a.m77a(this.f235a).m84a().isEventEncrypted()) {
            return C0509ay.m525a(bArr);
        }
        String m566a = C0516be.m566a(this.f235a);
        if (TextUtils.isEmpty(m566a) || (m572a = C0516be.m572a(m566a)) == null || m572a.length <= 0) {
            return null;
        }
        try {
            return C0509ay.m525a(Base64.decode(C0700i.m1628a(m572a, bArr), 2));
        } catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            AbstractC0407b.m72a(e);
            return null;
        }
    }

    @Override // com.xiaomi.clientreport.processor.IEventProcessor
    public void setEventMap(HashMap<String, ArrayList<C0408a>> hashMap) {
        this.f236a = hashMap;
    }

    @Override // com.xiaomi.clientreport.processor.IEventProcessor
    public byte[] stringToBytes(String str) {
        byte[] m572a;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (!C0410a.m77a(this.f235a).m84a().isEventEncrypted()) {
            return C0509ay.m529a(str);
        }
        String m566a = C0516be.m566a(this.f235a);
        byte[] m529a = C0509ay.m529a(str);
        if (TextUtils.isEmpty(m566a) || m529a == null || m529a.length <= 1 || (m572a = C0516be.m572a(m566a)) == null) {
            return null;
        }
        try {
            if (m572a.length > 1) {
                return C0700i.m1629b(m572a, Base64.encode(m529a, 2));
            }
            return null;
        } catch (Exception e) {
            AbstractC0407b.m72a(e);
            return null;
        }
    }
}
