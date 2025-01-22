package com.xiaomi.push.service;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.push.C0509ay;
import com.xiaomi.push.C0882x;
import com.xiaomi.push.C0883y;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: com.xiaomi.push.service.an */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/an.class */
public class C0808an {

    /* renamed from: a */
    private static long f2475a;

    /* renamed from: com.xiaomi.push.service.an$a */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/an$a.class */
    public static class a {

        /* renamed from: a */
        int f2476a;

        /* renamed from: a */
        byte[] f2477a;

        public a(byte[] bArr, int i) {
            this.f2477a = bArr;
            this.f2476a = i;
        }
    }

    /* renamed from: com.xiaomi.push.service.an$b */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/an$b.class */
    public static class b {

        /* renamed from: a */
        public long f2478a;

        /* renamed from: a */
        public Bitmap f2479a;

        public b(Bitmap bitmap, long j) {
            this.f2479a = bitmap;
            this.f2478a = j;
        }
    }

    /* renamed from: a */
    private static int m2550a(Context context, InputStream inputStream) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(inputStream, null, options);
        if (options.outWidth == -1 || options.outHeight == -1) {
            AbstractC0407b.m70a("decode dimension failed for bitmap.");
            return 1;
        }
        int round = Math.round((context.getResources().getDisplayMetrics().densityDpi / 160.0f) * 48.0f);
        if (options.outWidth <= round || options.outHeight <= round) {
            return 1;
        }
        return Math.min(options.outWidth / round, options.outHeight / round);
    }

    /* renamed from: a */
    public static Bitmap m2551a(Context context, String str) {
        Closeable closeable;
        InputStream inputStream;
        InputStream inputStream2;
        int m2550a;
        Uri parse = Uri.parse(str);
        try {
            try {
                inputStream = context.getContentResolver().openInputStream(parse);
                try {
                    m2550a = m2550a(context, inputStream);
                    inputStream2 = context.getContentResolver().openInputStream(parse);
                } catch (IOException e) {
                    e = e;
                    inputStream2 = null;
                } catch (Throwable th) {
                    th = th;
                    closeable = null;
                    C0883y.m2858a(closeable);
                    C0883y.m2858a(inputStream);
                    throw th;
                }
            } catch (IOException e2) {
                e = e2;
                inputStream2 = null;
                inputStream = null;
            } catch (Throwable th2) {
                th = th2;
                closeable = null;
                inputStream = null;
            }
            try {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = m2550a;
                Bitmap decodeStream = BitmapFactory.decodeStream(inputStream2, null, options);
                C0883y.m2858a(inputStream2);
                C0883y.m2858a(inputStream);
                return decodeStream;
            } catch (IOException e3) {
                e = e3;
                AbstractC0407b.m72a(e);
                C0883y.m2858a(inputStream2);
                C0883y.m2858a(inputStream);
                return null;
            }
        } catch (Throwable th3) {
            th = th3;
            inputStream = parse;
        }
    }

    /*  JADX ERROR: Types fix failed
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:447)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryPossibleTypes(FixTypesVisitor.java:183)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.deduceType(FixTypesVisitor.java:242)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryDeduceTypes(FixTypesVisitor.java:221)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:91)
        */
    /* JADX WARN: Failed to calculate best type for var: r0v8 ??
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:447)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:447)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
     */
    /* JADX WARN: Not initialized variable reg: 11, insn: 0x0236: MOVE (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r11 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]), block:B:98:0x0232 */
    /* renamed from: a */
    private static com.xiaomi.push.service.C0808an.a m2552a(java.lang.String r5, boolean r6) {
        /*
            Method dump skipped, instructions count: 608
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.service.C0808an.m2552a(java.lang.String, boolean):com.xiaomi.push.service.an$a");
    }

    /* renamed from: a */
    public static b m2553a(Context context, String str, boolean z) {
        ByteArrayInputStream byteArrayInputStream;
        a m2552a;
        b bVar = new b(null, 0L);
        Bitmap m2556b = m2556b(context, str);
        if (m2556b != null) {
            bVar.f2479a = m2556b;
            return bVar;
        }
        ByteArrayInputStream byteArrayInputStream2 = null;
        ByteArrayInputStream byteArrayInputStream3 = null;
        try {
            try {
                m2552a = m2552a(str, z);
            } catch (Exception e) {
                e = e;
                byteArrayInputStream = byteArrayInputStream3;
            }
            if (m2552a == null) {
                C0883y.m2858a((Closeable) null);
                return bVar;
            }
            bVar.f2478a = m2552a.f2476a;
            byte[] bArr = m2552a.f2477a;
            ByteArrayInputStream byteArrayInputStream4 = null;
            if (bArr != null) {
                if (z) {
                    byteArrayInputStream4 = new ByteArrayInputStream(bArr);
                    try {
                        int m2550a = m2550a(context, byteArrayInputStream4);
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inSampleSize = m2550a;
                        bVar.f2479a = BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
                    } catch (Exception e2) {
                        e = e2;
                        byteArrayInputStream = byteArrayInputStream4;
                        AbstractC0407b.m72a(e);
                        C0883y.m2858a(byteArrayInputStream);
                        return bVar;
                    } catch (Throwable th) {
                        th = th;
                        byteArrayInputStream2 = byteArrayInputStream4;
                        C0883y.m2858a(byteArrayInputStream2);
                        throw th;
                    }
                } else {
                    bVar.f2479a = BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
                    byteArrayInputStream4 = null;
                }
            }
            byteArrayInputStream3 = byteArrayInputStream4;
            m2555a(context, m2552a.f2477a, str);
            byteArrayInputStream = byteArrayInputStream4;
            C0883y.m2858a(byteArrayInputStream);
            return bVar;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* renamed from: a */
    private static void m2554a(Context context) {
        File file = new File(context.getCacheDir().getPath() + File.separator + "mipush_icon");
        if (file.exists()) {
            if (f2475a == 0) {
                f2475a = C0882x.m2856a(file);
            }
            if (f2475a > 15728640) {
                try {
                    File[] listFiles = file.listFiles();
                    int i = 0;
                    while (true) {
                        int i2 = i;
                        if (i2 >= listFiles.length) {
                            break;
                        }
                        if (!listFiles[i2].isDirectory() && Math.abs(System.currentTimeMillis() - listFiles[i2].lastModified()) > 1209600) {
                            listFiles[i2].delete();
                        }
                        i = i2 + 1;
                    }
                } catch (Exception e) {
                    AbstractC0407b.m72a(e);
                }
                f2475a = 0L;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x00db  */
    /* JADX WARN: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void m2555a(android.content.Context r5, byte[] r6, java.lang.String r7) {
        /*
            Method dump skipped, instructions count: 292
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.service.C0808an.m2555a(android.content.Context, byte[], java.lang.String):void");
    }

    /* renamed from: b */
    private static Bitmap m2556b(Context context, String str) {
        Bitmap bitmap;
        FileInputStream fileInputStream;
        Exception e;
        File file = new File(context.getCacheDir().getPath() + File.separator + "mipush_icon", C0509ay.m521a(str));
        FileInputStream fileInputStream2 = null;
        if (!file.exists()) {
            return null;
        }
        try {
            try {
                fileInputStream = new FileInputStream(file);
                Bitmap bitmap2 = null;
                try {
                    Bitmap decodeStream = BitmapFactory.decodeStream(fileInputStream);
                    bitmap2 = decodeStream;
                    file.setLastModified(System.currentTimeMillis());
                    C0883y.m2858a(fileInputStream);
                    return decodeStream;
                } catch (Exception e2) {
                    e = e2;
                    bitmap = bitmap2;
                    fileInputStream2 = fileInputStream;
                    AbstractC0407b.m72a(e);
                    C0883y.m2858a(fileInputStream);
                    return bitmap;
                } catch (Throwable th) {
                    th = th;
                    fileInputStream2 = fileInputStream;
                    C0883y.m2858a(fileInputStream2);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e3) {
            bitmap = null;
            fileInputStream = null;
            e = e3;
        }
    }
}
