package com.lambdaworks.jni;

import java.util.regex.Pattern;

/* loaded from: classes08-dex2jar.jar:com/lambdaworks/jni/Platform.class */
public class Platform {
    public final Arch arch;

    /* renamed from: os */
    public final EnumC0137OS f72os;

    /* loaded from: classes08-dex2jar.jar:com/lambdaworks/jni/Platform$Arch.class */
    public enum Arch {
        x86("x86|i386"),
        x86_64("x86_64|amd64");

        Pattern pattern;

        Arch(String str) {
            this.pattern = Pattern.compile("\\A" + str + "\\Z", 2);
        }
    }

    /* renamed from: com.lambdaworks.jni.Platform$OS */
    /* loaded from: classes08-dex2jar.jar:com/lambdaworks/jni/Platform$OS.class */
    public enum EnumC0137OS {
        darwin("darwin|mac os x"),
        freebsd("freebsd"),
        linux("linux");

        Pattern pattern;

        EnumC0137OS(String str) {
            this.pattern = Pattern.compile("\\A" + str + "\\Z", 2);
        }
    }

    private Platform(Arch arch, EnumC0137OS enumC0137OS) {
        this.arch = arch;
        this.f72os = enumC0137OS;
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x006d, code lost:            continue;     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.lambdaworks.jni.Platform detect() throws com.lambdaworks.jni.UnsupportedPlatformException {
        /*
            java.lang.String r0 = "os.arch"
            java.lang.String r0 = java.lang.System.getProperty(r0)
            r12 = r0
            java.lang.String r0 = "os.name"
            java.lang.String r0 = java.lang.System.getProperty(r0)
            r13 = r0
            com.lambdaworks.jni.Platform$Arch[] r0 = com.lambdaworks.jni.Platform.Arch.values()
            r14 = r0
            r0 = r14
            int r0 = r0.length
            r10 = r0
            r0 = 0
            r8 = r0
        L19:
            r0 = r8
            r1 = r10
            if (r0 >= r1) goto L74
            r0 = r14
            r1 = r8
            r0 = r0[r1]
            r15 = r0
            r0 = r15
            java.util.regex.Pattern r0 = r0.pattern
            r1 = r12
            java.util.regex.Matcher r0 = r0.matcher(r1)
            boolean r0 = r0.matches()
            if (r0 == 0) goto L6d
            com.lambdaworks.jni.Platform$OS[] r0 = com.lambdaworks.jni.Platform.EnumC0137OS.values()
            r16 = r0
            r0 = r16
            int r0 = r0.length
            r11 = r0
            r0 = 0
            r9 = r0
        L3f:
            r0 = r9
            r1 = r11
            if (r0 >= r1) goto L6d
            r0 = r16
            r1 = r9
            r0 = r0[r1]
            r17 = r0
            r0 = r17
            java.util.regex.Pattern r0 = r0.pattern
            r1 = r13
            java.util.regex.Matcher r0 = r0.matcher(r1)
            boolean r0 = r0.matches()
            if (r0 == 0) goto L66
            com.lambdaworks.jni.Platform r0 = new com.lambdaworks.jni.Platform
            r1 = r0
            r2 = r15
            r3 = r17
            r1.<init>(r2, r3)
            return r0
        L66:
            r0 = r9
            r1 = 1
            int r0 = r0 + r1
            r9 = r0
            goto L3f
        L6d:
            r0 = r8
            r1 = 1
            int r0 = r0 + r1
            r8 = r0
            goto L19
        L74:
            com.lambdaworks.jni.UnsupportedPlatformException r0 = new com.lambdaworks.jni.UnsupportedPlatformException
            r1 = r0
            java.lang.String r2 = "Unsupported platform %s %s"
            r3 = 2
            java.lang.Object[] r3 = new java.lang.Object[r3]
            r4 = r3
            r5 = 0
            r6 = r12
            r4[r5] = r6
            r4 = r3
            r5 = 1
            r6 = r13
            r4[r5] = r6
            java.lang.String r2 = java.lang.String.format(r2, r3)
            r1.<init>(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lambdaworks.jni.Platform.detect():com.lambdaworks.jni.Platform");
    }
}
