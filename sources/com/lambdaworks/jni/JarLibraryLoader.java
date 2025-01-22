package com.lambdaworks.jni;

import com.lambdaworks.jni.Platform;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes08-dex2jar.jar:com/lambdaworks/jni/JarLibraryLoader.class */
public class JarLibraryLoader implements LibraryLoader {
    private final CodeSource codeSource;
    private final String libraryPath;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.lambdaworks.jni.JarLibraryLoader$1 */
    /* loaded from: classes08-dex2jar.jar:com/lambdaworks/jni/JarLibraryLoader$1.class */
    public static /* synthetic */ class C01361 {
        static final /* synthetic */ int[] $SwitchMap$com$lambdaworks$jni$Platform$OS = new int[Platform.EnumC0137OS.values().length];

        /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:15:0x002f
            	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1166)
            	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:1022)
            	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:55)
            */
        static {
            /*
                com.lambdaworks.jni.Platform$OS[] r0 = com.lambdaworks.jni.Platform.EnumC0137OS.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.lambdaworks.jni.JarLibraryLoader.C01361.$SwitchMap$com$lambdaworks$jni$Platform$OS = r0
                int[] r0 = com.lambdaworks.jni.JarLibraryLoader.C01361.$SwitchMap$com$lambdaworks$jni$Platform$OS     // Catch: java.lang.NoSuchFieldError -> L2b
                com.lambdaworks.jni.Platform$OS r1 = com.lambdaworks.jni.Platform.EnumC0137OS.darwin     // Catch: java.lang.NoSuchFieldError -> L2b
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L2b
                r2 = 1
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L2b
            L14:
                int[] r0 = com.lambdaworks.jni.JarLibraryLoader.C01361.$SwitchMap$com$lambdaworks$jni$Platform$OS     // Catch: java.lang.NoSuchFieldError -> L2b java.lang.NoSuchFieldError -> L2f
                com.lambdaworks.jni.Platform$OS r1 = com.lambdaworks.jni.Platform.EnumC0137OS.linux     // Catch: java.lang.NoSuchFieldError -> L2f
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L2f
                r2 = 2
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L2f
            L1f:
                int[] r0 = com.lambdaworks.jni.JarLibraryLoader.C01361.$SwitchMap$com$lambdaworks$jni$Platform$OS     // Catch: java.lang.NoSuchFieldError -> L2f java.lang.NoSuchFieldError -> L33
                com.lambdaworks.jni.Platform$OS r1 = com.lambdaworks.jni.Platform.EnumC0137OS.freebsd     // Catch: java.lang.NoSuchFieldError -> L33
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L33
                r2 = 3
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L33
                return
            L2b:
                r4 = move-exception
                goto L14
            L2f:
                r4 = move-exception
                goto L1f
            L33:
                r4 = move-exception
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.lambdaworks.jni.JarLibraryLoader.C01361.m3611clinit():void");
        }
    }

    public JarLibraryLoader() {
        this(JarLibraryLoader.class.getProtectionDomain().getCodeSource(), "lib");
    }

    public JarLibraryLoader(CodeSource codeSource, String str) {
        this.codeSource = codeSource;
        this.libraryPath = str;
    }

    private static File extract(String str, InputStream inputStream) throws IOException {
        byte[] bArr = new byte[4096];
        File createTempFile = File.createTempFile(str, "lib");
        FileOutputStream fileOutputStream = new FileOutputStream(createTempFile);
        while (true) {
            try {
                try {
                    int read = inputStream.read(bArr);
                    if (read <= 0) {
                        return createTempFile;
                    }
                    fileOutputStream.write(bArr, 0, read);
                } catch (IOException e) {
                    createTempFile.delete();
                    throw e;
                }
            } finally {
                fileOutputStream.close();
                inputStream.close();
            }
        }
    }

    private List<String> libCandidates(Platform platform, String str) {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        sb.append(this.libraryPath);
        sb.append("/");
        sb.append(platform.arch);
        sb.append("/");
        sb.append(platform.f72os);
        sb.append("/");
        sb.append("lib");
        sb.append(str);
        int i = C01361.$SwitchMap$com$lambdaworks$jni$Platform$OS[platform.f72os.ordinal()];
        if (i != 1) {
            if (i != 2 && i != 3) {
                return arrayList;
            }
            arrayList.add(((Object) sb) + ".so");
            return arrayList;
        }
        arrayList.add(((Object) sb) + ".dylib");
        arrayList.add(((Object) sb) + ".jnilib");
        return arrayList;
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0048, code lost:            r0 = extract(r6, r0.getInputStream(r0));        java.lang.System.load(r0.getAbsolutePath());        r0.delete();     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0060, code lost:            r7 = true;     */
    @Override // com.lambdaworks.jni.LibraryLoader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean load(java.lang.String r6, boolean r7) {
        /*
            r5 = this;
            com.lambdaworks.jni.Platform r0 = com.lambdaworks.jni.Platform.detect()     // Catch: java.lang.Throwable -> L75
            r9 = r0
            java.util.jar.JarFile r0 = new java.util.jar.JarFile     // Catch: java.lang.Throwable -> L75
            r1 = r0
            r2 = r5
            java.security.CodeSource r2 = r2.codeSource     // Catch: java.lang.Throwable -> L75
            java.net.URL r2 = r2.getLocation()     // Catch: java.lang.Throwable -> L75
            java.lang.String r2 = r2.getPath()     // Catch: java.lang.Throwable -> L75
            r3 = r7
            r1.<init>(r2, r3)     // Catch: java.lang.Throwable -> L75
            r8 = r0
            r0 = r5
            r1 = r9
            r2 = r6
            java.util.List r0 = r0.libCandidates(r1, r2)     // Catch: java.lang.Throwable -> L6c
            java.util.Iterator r0 = r0.iterator()     // Catch: java.lang.Throwable -> L6c
            r9 = r0
        L26:
            r0 = r9
            boolean r0 = r0.hasNext()     // Catch: java.lang.Throwable -> L6c
            if (r0 == 0) goto L64
            r0 = r8
            r1 = r9
            java.lang.Object r1 = r1.next()     // Catch: java.lang.Throwable -> L6c
            java.lang.String r1 = (java.lang.String) r1     // Catch: java.lang.Throwable -> L6c
            java.util.jar.JarEntry r0 = r0.getJarEntry(r1)     // Catch: java.lang.Throwable -> L6c
            r10 = r0
            r0 = r10
            if (r0 != 0) goto L48
            goto L26
        L48:
            r0 = r6
            r1 = r8
            r2 = r10
            java.io.InputStream r1 = r1.getInputStream(r2)     // Catch: java.lang.Throwable -> L6c
            java.io.File r0 = extract(r0, r1)     // Catch: java.lang.Throwable -> L6c
            r6 = r0
            r0 = r6
            java.lang.String r0 = r0.getAbsolutePath()     // Catch: java.lang.Throwable -> L6c
            java.lang.System.load(r0)     // Catch: java.lang.Throwable -> L6c
            r0 = r6
            boolean r0 = r0.delete()     // Catch: java.lang.Throwable -> L6c
            r0 = 1
            r7 = r0
            goto L66
        L64:
            r0 = 0
            r7 = r0
        L66:
            r0 = r8
            r0.close()     // Catch: java.lang.Throwable -> L75
            r0 = r7
            return r0
        L6c:
            r6 = move-exception
            r0 = r8
            r0.close()     // Catch: java.lang.Throwable -> L75
            r0 = r6
            throw r0     // Catch: java.lang.Throwable -> L75
        L73:
            r0 = 0
            return r0
        L75:
            r6 = move-exception
            goto L73
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lambdaworks.jni.JarLibraryLoader.load(java.lang.String, boolean):boolean");
    }
}
