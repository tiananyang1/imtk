package com.sun.jna;

import com.xiaomi.mipush.sdk.Constants;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes08-dex2jar.jar:com/sun/jna/Platform.class */
public final class Platform {
    public static final int AIX = 7;
    public static final int ANDROID = 8;
    public static final String ARCH;
    public static final String C_LIBRARY_NAME;
    public static final int FREEBSD = 4;
    public static final int GNU = 9;
    public static final boolean HAS_AWT;
    public static final boolean HAS_BUFFERS;
    public static final boolean HAS_DLL_CALLBACKS;
    public static final boolean HAS_JAWT;
    public static final int KFREEBSD = 10;
    public static final int LINUX = 1;
    public static final int MAC = 0;
    public static final String MATH_LIBRARY_NAME;
    public static final int NETBSD = 11;
    public static final int OPENBSD = 5;
    public static final String RESOURCE_PREFIX;
    public static final boolean RO_FIELDS;
    public static final int SOLARIS = 3;
    public static final int UNSPECIFIED = -1;
    public static final int WINDOWS = 2;
    public static final int WINDOWSCE = 6;
    private static final int osType;

    static {
        boolean z;
        String property = System.getProperty("os.name");
        if (property.startsWith("Linux")) {
            if ("dalvik".equals(System.getProperty("java.vm.name").toLowerCase())) {
                osType = 8;
                System.setProperty("jna.nounpack", "true");
            } else {
                osType = 1;
            }
        } else if (property.startsWith("AIX")) {
            osType = 7;
        } else if (property.startsWith("Mac") || property.startsWith("Darwin")) {
            osType = 0;
        } else if (property.startsWith("Windows CE")) {
            osType = 6;
        } else if (property.startsWith("Windows")) {
            osType = 2;
        } else if (property.startsWith("Solaris") || property.startsWith("SunOS")) {
            osType = 3;
        } else if (property.startsWith("FreeBSD")) {
            osType = 4;
        } else if (property.startsWith("OpenBSD")) {
            osType = 5;
        } else if (property.equalsIgnoreCase("gnu")) {
            osType = 9;
        } else if (property.equalsIgnoreCase("gnu/kfreebsd")) {
            osType = 10;
        } else if (property.equalsIgnoreCase("netbsd")) {
            osType = 11;
        } else {
            osType = -1;
        }
        try {
            Class.forName("java.nio.Buffer");
            z = true;
        } catch (ClassNotFoundException e) {
            z = false;
        }
        int i = osType;
        HAS_AWT = (i == 6 || i == 8 || i == 7) ? false : true;
        HAS_JAWT = HAS_AWT && osType != 0;
        HAS_BUFFERS = z;
        RO_FIELDS = osType != 6;
        int i2 = osType;
        C_LIBRARY_NAME = i2 == 2 ? "msvcrt" : i2 == 6 ? "coredll" : "c";
        int i3 = osType;
        MATH_LIBRARY_NAME = i3 == 2 ? "msvcrt" : i3 == 6 ? "coredll" : "m";
        boolean z2 = false;
        if (osType == 2) {
            z2 = true;
        }
        HAS_DLL_CALLBACKS = z2;
        ARCH = getCanonicalArchitecture(System.getProperty("os.arch"), osType);
        RESOURCE_PREFIX = getNativeLibraryResourcePrefix();
    }

    private Platform() {
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x004c, code lost:            if ("amd64".equals(r0) != false) goto L17;     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static java.lang.String getCanonicalArchitecture(java.lang.String r3, int r4) {
        /*
            r0 = r3
            java.lang.String r0 = r0.toLowerCase()
            java.lang.String r0 = r0.trim()
            r5 = r0
            java.lang.String r0 = "powerpc"
            r1 = r5
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L17
            java.lang.String r0 = "ppc"
            r3 = r0
            goto L58
        L17:
            java.lang.String r0 = "powerpc64"
            r1 = r5
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L26
            java.lang.String r0 = "ppc64"
            r3 = r0
            goto L58
        L26:
            java.lang.String r0 = "i386"
            r1 = r5
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L55
            java.lang.String r0 = "i686"
            r1 = r5
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L3b
            goto L55
        L3b:
            java.lang.String r0 = "x86_64"
            r1 = r5
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L4f
            r0 = r5
            r3 = r0
            java.lang.String r0 = "amd64"
            r1 = r5
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L58
        L4f:
            java.lang.String r0 = "x86-64"
            r3 = r0
            goto L58
        L55:
            java.lang.String r0 = "x86"
            r3 = r0
        L58:
            r0 = r3
            r5 = r0
            java.lang.String r0 = "ppc64"
            r1 = r3
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L75
            r0 = r3
            r5 = r0
            java.lang.String r0 = "little"
            java.lang.String r1 = "sun.cpu.endian"
            java.lang.String r1 = java.lang.System.getProperty(r1)
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L75
            java.lang.String r0 = "ppc64le"
            r5 = r0
        L75:
            r0 = r5
            r3 = r0
            java.lang.String r0 = "arm"
            r1 = r5
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L92
            r0 = r5
            r3 = r0
            r0 = r4
            r1 = 1
            if (r0 != r1) goto L92
            r0 = r5
            r3 = r0
            boolean r0 = isSoftFloat()
            if (r0 == 0) goto L92
            java.lang.String r0 = "armel"
            r3 = r0
        L92:
            r0 = r3
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.jna.Platform.getCanonicalArchitecture(java.lang.String, int):java.lang.String");
    }

    static String getNativeLibraryResourcePrefix() {
        String property = System.getProperty("jna.prefix");
        return property != null ? property : getNativeLibraryResourcePrefix(getOSType(), System.getProperty("os.arch"), System.getProperty("os.name"));
    }

    static String getNativeLibraryResourcePrefix(int i, String str, String str2) {
        String canonicalArchitecture = getCanonicalArchitecture(str, i);
        switch (i) {
            case 0:
                return "darwin";
            case 1:
                return "linux-" + canonicalArchitecture;
            case 2:
                return "win32-" + canonicalArchitecture;
            case 3:
                return "sunos-" + canonicalArchitecture;
            case 4:
                return "freebsd-" + canonicalArchitecture;
            case 5:
                return "openbsd-" + canonicalArchitecture;
            case 6:
                return "w32ce-" + canonicalArchitecture;
            case 7:
            case 9:
            default:
                String lowerCase = str2.toLowerCase();
                int indexOf = lowerCase.indexOf(" ");
                String str3 = lowerCase;
                if (indexOf != -1) {
                    str3 = lowerCase.substring(0, indexOf);
                }
                return str3 + Constants.ACCEPT_TIME_SEPARATOR_SERVER + canonicalArchitecture;
            case 8:
                return "android-" + (canonicalArchitecture.startsWith("arm") ? "arm" : canonicalArchitecture);
            case 10:
                return "kfreebsd-" + canonicalArchitecture;
            case 11:
                return "netbsd-" + canonicalArchitecture;
        }
    }

    public static final int getOSType() {
        return osType;
    }

    public static final boolean hasRuntimeExec() {
        return (isWindowsCE() && "J9".equals(System.getProperty("java.vm.name"))) ? false : true;
    }

    public static final boolean is64Bit() {
        String property = System.getProperty("sun.arch.data.model", System.getProperty("com.ibm.vm.bitmode"));
        if (property != null) {
            return "64".equals(property);
        }
        boolean z = true;
        if (!"x86-64".equals(ARCH)) {
            z = true;
            if (!"ia64".equals(ARCH)) {
                z = true;
                if (!"ppc64".equals(ARCH)) {
                    z = true;
                    if (!"ppc64le".equals(ARCH)) {
                        z = true;
                        if (!"sparcv9".equals(ARCH)) {
                            z = true;
                            if (!"mips64".equals(ARCH)) {
                                z = true;
                                if (!"mips64el".equals(ARCH)) {
                                    if ("amd64".equals(ARCH) || Native.POINTER_SIZE == 8) {
                                        return true;
                                    }
                                    z = false;
                                }
                            }
                        }
                    }
                }
            }
        }
        return z;
    }

    public static final boolean isAIX() {
        return osType == 7;
    }

    public static final boolean isARM() {
        return ARCH.startsWith("arm");
    }

    public static final boolean isAndroid() {
        return osType == 8;
    }

    public static final boolean isFreeBSD() {
        return osType == 4;
    }

    public static final boolean isGNU() {
        return osType == 9;
    }

    public static final boolean isIntel() {
        return ARCH.startsWith("x86");
    }

    public static final boolean isLinux() {
        return osType == 1;
    }

    public static final boolean isMIPS() {
        return ARCH.equals("mips") || ARCH.equals("mips64") || ARCH.equals("mipsel") || ARCH.equals("mips64el");
    }

    public static final boolean isMac() {
        return osType == 0;
    }

    public static final boolean isNetBSD() {
        return osType == 11;
    }

    public static final boolean isOpenBSD() {
        return osType == 5;
    }

    public static final boolean isPPC() {
        return ARCH.startsWith("ppc");
    }

    public static final boolean isSPARC() {
        return ARCH.startsWith("sparc");
    }

    static boolean isSoftFloat() {
        try {
            if (new File("/proc/self/exe").exists()) {
                return !ELFAnalyser.analyse(r0.getCanonicalPath()).isArmHardFloat();
            }
            return false;
        } catch (IOException e) {
            Logger.getLogger(Platform.class.getName()).log(Level.INFO, "Failed to read '/proc/self/exe' or the target binary.", (Throwable) e);
            return false;
        } catch (SecurityException e2) {
            Logger.getLogger(Platform.class.getName()).log(Level.INFO, "SecurityException while analysing '/proc/self/exe' or the target binary.", (Throwable) e2);
            return false;
        }
    }

    public static final boolean isSolaris() {
        return osType == 3;
    }

    public static final boolean isWindows() {
        int i = osType;
        return i == 2 || i == 6;
    }

    public static final boolean isWindowsCE() {
        return osType == 6;
    }

    public static final boolean isX11() {
        return (isWindows() || isMac()) ? false : true;
    }

    public static final boolean iskFreeBSD() {
        return osType == 10;
    }
}
