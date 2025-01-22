package com.sun.jna;

import java.io.BufferedReader;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.Reference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes08-dex2jar.jar:com/sun/jna/NativeLibrary.class */
public class NativeLibrary {
    private static final Level DEBUG_LOAD_LEVEL;
    private static final int DEFAULT_OPEN_OPTIONS = -1;
    private static final Logger LOG = Logger.getLogger(NativeLibrary.class.getName());
    private static Method addSuppressedMethod;
    private static final Map<String, Reference<NativeLibrary>> libraries;
    private static final List<String> librarySearchPath;
    private static final Map<String, List<String>> searchPaths;
    final int callFlags;
    private String encoding;
    private final Map<String, Function> functions = new HashMap();
    private long handle;
    private final String libraryName;
    private final String libraryPath;
    final Map<String, ?> options;

    static {
        String sb;
        DEBUG_LOAD_LEVEL = Native.DEBUG_LOAD ? Level.INFO : Level.FINE;
        libraries = new HashMap();
        searchPaths = Collections.synchronizedMap(new HashMap());
        librarySearchPath = new ArrayList();
        if (Native.POINTER_SIZE == 0) {
            throw new Error("Native library not initialized");
        }
        addSuppressedMethod = null;
        try {
            addSuppressedMethod = Throwable.class.getMethod("addSuppressed", Throwable.class);
        } catch (NoSuchMethodException e) {
        } catch (SecurityException e2) {
            Logger.getLogger(NativeLibrary.class.getName()).log(Level.SEVERE, "Failed to initialize 'addSuppressed' method", (Throwable) e2);
        }
        String webStartLibraryPath = Native.getWebStartLibraryPath("jnidispatch");
        if (webStartLibraryPath != null) {
            librarySearchPath.add(webStartLibraryPath);
        }
        if (System.getProperty("jna.platform.library.path") == null && !Platform.isWindows()) {
            if (Platform.isLinux() || Platform.isSolaris() || Platform.isFreeBSD() || Platform.iskFreeBSD()) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(Platform.isSolaris() ? "/" : "");
                sb2.append(Native.POINTER_SIZE * 8);
                sb = sb2.toString();
            } else {
                sb = "";
            }
            String[] strArr = {"/usr/lib" + sb, "/lib" + sb, "/usr/lib", "/lib"};
            if (Platform.isLinux() || Platform.iskFreeBSD() || Platform.isGNU()) {
                String multiArchPath = getMultiArchPath();
                strArr = new String[]{"/usr/lib/" + multiArchPath, "/lib/" + multiArchPath, "/usr/lib" + sb, "/lib" + sb, "/usr/lib", "/lib"};
            }
            String[] strArr2 = strArr;
            if (Platform.isLinux()) {
                ArrayList<String> linuxLdPaths = getLinuxLdPaths();
                int length = strArr.length;
                while (true) {
                    int i = length - 1;
                    if (i < 0) {
                        break;
                    }
                    int indexOf = linuxLdPaths.indexOf(strArr[i]);
                    if (indexOf != -1) {
                        linuxLdPaths.remove(indexOf);
                    }
                    linuxLdPaths.add(0, strArr[i]);
                    length = i;
                }
                strArr2 = (String[]) linuxLdPaths.toArray(new String[0]);
            }
            String str = "";
            String str2 = str;
            int i2 = 0;
            while (i2 < strArr2.length) {
                File file = new File(strArr2[i2]);
                String str3 = str;
                String str4 = str2;
                if (file.exists()) {
                    str3 = str;
                    str4 = str2;
                    if (file.isDirectory()) {
                        str3 = str + str2 + strArr2[i2];
                        str4 = File.pathSeparator;
                    }
                }
                i2++;
                str = str3;
                str2 = str4;
            }
            if (!"".equals(str)) {
                System.setProperty("jna.platform.library.path", str);
            }
        }
        librarySearchPath.addAll(initPaths("jna.platform.library.path"));
    }

    private NativeLibrary(String str, String str2, long j, Map<String, ?> map) {
        this.libraryName = getLibraryName(str);
        this.libraryPath = str2;
        this.handle = j;
        Object obj = map.get(Library.OPTION_CALLING_CONVENTION);
        this.callFlags = obj instanceof Number ? ((Number) obj).intValue() : 0;
        this.options = map;
        this.encoding = (String) map.get(Library.OPTION_STRING_ENCODING);
        if (this.encoding == null) {
            this.encoding = Native.getDefaultStringEncoding();
        }
        if (Platform.isWindows() && "kernel32".equals(this.libraryName.toLowerCase())) {
            synchronized (this.functions) {
                this.functions.put(functionKey("GetLastError", this.callFlags, this.encoding), new Function(this, "GetLastError", 63, this.encoding) { // from class: com.sun.jna.NativeLibrary.1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    @Override // com.sun.jna.Function
                    public Object invoke(Method method, Class<?>[] clsArr, Class<?> cls, Object[] objArr, Map<String, ?> map2) {
                        return Integer.valueOf(Native.getLastError());
                    }

                    @Override // com.sun.jna.Function
                    Object invoke(Object[] objArr, Class<?> cls, boolean z, int i) {
                        return Integer.valueOf(Native.getLastError());
                    }
                });
            }
        }
    }

    public static final void addSearchPath(String str, String str2) {
        synchronized (searchPaths) {
            List<String> list = searchPaths.get(str);
            List<String> list2 = list;
            if (list == null) {
                list2 = Collections.synchronizedList(new ArrayList());
                searchPaths.put(str, list2);
            }
            list2.add(str2);
        }
    }

    private static void addSuppressedReflected(Throwable th, Throwable th2) {
        Method method = addSuppressedMethod;
        if (method == null) {
            return;
        }
        try {
            method.invoke(th, th2);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Failed to call addSuppressedMethod", e);
        } catch (IllegalArgumentException e2) {
            throw new RuntimeException("Failed to call addSuppressedMethod", e2);
        } catch (InvocationTargetException e3) {
            throw new RuntimeException("Failed to call addSuppressedMethod", e3);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void disposeAll() {
        LinkedHashSet linkedHashSet;
        synchronized (libraries) {
            linkedHashSet = new LinkedHashSet(libraries.values());
        }
        Iterator it = linkedHashSet.iterator();
        while (it.hasNext()) {
            NativeLibrary nativeLibrary = (NativeLibrary) ((Reference) it.next()).get();
            if (nativeLibrary != null) {
                nativeLibrary.dispose();
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x0092, code lost:            return r6;     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String findLibraryPath(java.lang.String r6, java.util.List<java.lang.String> r7) {
        /*
            java.io.File r0 = new java.io.File
            r1 = r0
            r2 = r6
            r1.<init>(r2)
            boolean r0 = r0.isAbsolute()
            if (r0 == 0) goto L10
            r0 = r6
            return r0
        L10:
            r0 = r6
            java.lang.String r0 = mapSharedLibraryName(r0)
            r8 = r0
            r0 = r7
            java.util.Iterator r0 = r0.iterator()
            r7 = r0
        L1c:
            r0 = r8
            r6 = r0
            r0 = r7
            boolean r0 = r0.hasNext()
            if (r0 == 0) goto L91
            r0 = r7
            java.lang.Object r0 = r0.next()
            java.lang.String r0 = (java.lang.String) r0
            r6 = r0
            java.io.File r0 = new java.io.File
            r1 = r0
            r2 = r6
            r3 = r8
            r1.<init>(r2, r3)
            r9 = r0
            r0 = r9
            boolean r0 = r0.exists()
            if (r0 == 0) goto L47
            r0 = r9
            java.lang.String r0 = r0.getAbsolutePath()
            return r0
        L47:
            boolean r0 = com.sun.jna.Platform.isMac()
            if (r0 == 0) goto L1c
            r0 = r8
            java.lang.String r1 = ".dylib"
            boolean r0 = r0.endsWith(r1)
            if (r0 == 0) goto L1c
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r1 = r0
            r1.<init>()
            r9 = r0
            r0 = r9
            r1 = r8
            r2 = 0
            r3 = r8
            java.lang.String r4 = ".dylib"
            int r3 = r3.lastIndexOf(r4)
            java.lang.String r1 = r1.substring(r2, r3)
            java.lang.StringBuilder r0 = r0.append(r1)
            r0 = r9
            java.lang.String r1 = ".jnilib"
            java.lang.StringBuilder r0 = r0.append(r1)
            java.io.File r0 = new java.io.File
            r1 = r0
            r2 = r6
            r3 = r9
            java.lang.String r3 = r3.toString()
            r1.<init>(r2, r3)
            r6 = r0
            r0 = r6
            boolean r0 = r0.exists()
            if (r0 == 0) goto L1c
            r0 = r6
            java.lang.String r0 = r0.getAbsolutePath()
            r6 = r0
        L91:
            r0 = r6
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.jna.NativeLibrary.findLibraryPath(java.lang.String, java.util.List):java.lang.String");
    }

    private static String functionKey(String str, int i, String str2) {
        return str + "|" + i + "|" + str2;
    }

    public static final NativeLibrary getInstance(String str) {
        return getInstance(str, (Map<String, ?>) Collections.emptyMap());
    }

    public static final NativeLibrary getInstance(String str, ClassLoader classLoader) {
        return getInstance(str, (Map<String, ?>) Collections.singletonMap(Library.OPTION_CLASSLOADER, classLoader));
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x0032, code lost:            if (com.sun.jna.Platform.isAIX() != false) goto L11;     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final com.sun.jna.NativeLibrary getInstance(java.lang.String r8, java.util.Map<java.lang.String, ?> r9) {
        /*
            Method dump skipped, instructions count: 339
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.jna.NativeLibrary.getInstance(java.lang.String, java.util.Map):com.sun.jna.NativeLibrary");
    }

    private String getLibraryName(String str) {
        String mapSharedLibraryName = mapSharedLibraryName("---");
        int indexOf = mapSharedLibraryName.indexOf("---");
        String str2 = str;
        if (indexOf > 0) {
            str2 = str;
            if (str.startsWith(mapSharedLibraryName.substring(0, indexOf))) {
                str2 = str.substring(indexOf);
            }
        }
        int indexOf2 = str2.indexOf(mapSharedLibraryName.substring(indexOf + 3));
        String str3 = str2;
        if (indexOf2 != -1) {
            str3 = str2.substring(0, indexOf2);
        }
        return str3;
    }

    private static ArrayList<String> getLinuxLdPaths() {
        BufferedReader bufferedReader;
        ArrayList<String> arrayList = new ArrayList<>();
        BufferedReader bufferedReader2 = null;
        try {
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("/sbin/ldconfig -p").getInputStream()));
                while (true) {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            bufferedReader.close();
                            return arrayList;
                        }
                        int indexOf = readLine.indexOf(" => ");
                        int lastIndexOf = readLine.lastIndexOf(47);
                        if (indexOf != -1 && lastIndexOf != -1 && indexOf < lastIndexOf) {
                            String substring = readLine.substring(indexOf + 4, lastIndexOf);
                            if (!arrayList.contains(substring)) {
                                arrayList.add(substring);
                            }
                        }
                    } catch (Exception e) {
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                        return arrayList;
                    } catch (Throwable th) {
                        bufferedReader2 = bufferedReader;
                        th = th;
                        if (bufferedReader2 != null) {
                            try {
                                bufferedReader2.close();
                            } catch (IOException e2) {
                            }
                        }
                        throw th;
                    }
                }
            } catch (Exception e3) {
                bufferedReader = null;
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e4) {
            return arrayList;
        }
    }

    private static String getMultiArchPath() {
        String str;
        String str2 = Platform.ARCH;
        String str3 = Platform.iskFreeBSD() ? "-kfreebsd" : Platform.isGNU() ? "" : "-linux";
        String str4 = "-gnu";
        if (Platform.isIntel()) {
            str = Platform.is64Bit() ? "x86_64" : "i386";
        } else if (Platform.isPPC()) {
            str = Platform.is64Bit() ? "powerpc64" : "powerpc";
        } else if (Platform.isARM()) {
            str = "arm";
            str4 = "-gnueabi";
        } else {
            str = str2;
            if (Platform.ARCH.equals("mips64el")) {
                str4 = "-gnuabi64";
                str = str2;
            }
        }
        return str + str3 + str4;
    }

    public static final NativeLibrary getProcess() {
        NativeLibrary nativeLibrary;
        synchronized (NativeLibrary.class) {
            try {
                nativeLibrary = getInstance(null);
            } catch (Throwable th) {
                throw th;
            }
        }
        return nativeLibrary;
    }

    public static final NativeLibrary getProcess(Map<String, ?> map) {
        NativeLibrary nativeLibrary;
        synchronized (NativeLibrary.class) {
            try {
                nativeLibrary = getInstance((String) null, map);
            } catch (Throwable th) {
                throw th;
            }
        }
        return nativeLibrary;
    }

    private static List<String> initPaths(String str) {
        String property = System.getProperty(str, "");
        if ("".equals(property)) {
            return Collections.emptyList();
        }
        StringTokenizer stringTokenizer = new StringTokenizer(property, File.pathSeparator);
        ArrayList arrayList = new ArrayList();
        while (stringTokenizer.hasMoreTokens()) {
            String nextToken = stringTokenizer.nextToken();
            if (!"".equals(nextToken)) {
                arrayList.add(nextToken);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isVersionedName(String str) {
        int lastIndexOf;
        if (!str.startsWith("lib") || (lastIndexOf = str.lastIndexOf(".so.")) == -1) {
            return false;
        }
        int i = lastIndexOf + 4;
        if (i >= str.length()) {
            return false;
        }
        while (i < str.length()) {
            char charAt = str.charAt(i);
            if (!Character.isDigit(charAt) && charAt != '.') {
                return false;
            }
            i++;
        }
        return true;
    }

    private static NativeLibrary loadLibrary(String str, Map<String, ?> map) {
        long j;
        long j2;
        LOG.log(DEBUG_LOAD_LEVEL, "Looking for library '" + str + "'");
        ArrayList<Throwable> arrayList = new ArrayList();
        boolean isAbsolute = new File(str).isAbsolute();
        ArrayList arrayList2 = new ArrayList();
        int openFlags = openFlags(map);
        String webStartLibraryPath = Native.getWebStartLibraryPath(str);
        if (webStartLibraryPath != null) {
            LOG.log(DEBUG_LOAD_LEVEL, "Adding web start path " + webStartLibraryPath);
            arrayList2.add(webStartLibraryPath);
        }
        List<String> list = searchPaths.get(str);
        if (list != null) {
            synchronized (list) {
                arrayList2.addAll(0, list);
            }
        }
        LOG.log(DEBUG_LOAD_LEVEL, "Adding paths from jna.library.path: " + System.getProperty("jna.library.path"));
        arrayList2.addAll(initPaths("jna.library.path"));
        String findLibraryPath = findLibraryPath(str, arrayList2);
        try {
            LOG.log(DEBUG_LOAD_LEVEL, "Trying " + findLibraryPath);
            j = Native.open(findLibraryPath, openFlags);
        } catch (UnsatisfiedLinkError e) {
            LOG.log(DEBUG_LOAD_LEVEL, "Loading failed with message: " + e.getMessage());
            LOG.log(DEBUG_LOAD_LEVEL, "Adding system paths: " + librarySearchPath);
            arrayList.add(e);
            arrayList2.addAll(librarySearchPath);
            j = 0L;
        }
        String str2 = findLibraryPath;
        long j3 = j;
        if (j == 0) {
            long j4 = j;
            try {
                str2 = findLibraryPath(str, arrayList2);
                Logger logger = LOG;
                Level level = DEBUG_LOAD_LEVEL;
                StringBuilder sb = new StringBuilder();
                sb.append("Trying ");
                sb.append(str2);
                logger.log(level, sb.toString());
                long open = Native.open(str2, openFlags);
                if (open == 0) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Failed to load library '");
                    sb2.append(str);
                    sb2.append("'");
                    findLibraryPath = str2;
                    j4 = open;
                    throw new UnsatisfiedLinkError(sb2.toString());
                }
                j3 = open;
            } catch (UnsatisfiedLinkError e2) {
                LOG.log(DEBUG_LOAD_LEVEL, "Loading failed with message: " + e2.getMessage());
                arrayList.add(e2);
                if (Platform.isAndroid()) {
                    try {
                        LOG.log(DEBUG_LOAD_LEVEL, "Preload (via System.loadLibrary) " + str);
                        System.loadLibrary(str);
                        j2 = Native.open(findLibraryPath, openFlags);
                        str2 = findLibraryPath;
                    } catch (UnsatisfiedLinkError e3) {
                        LOG.log(DEBUG_LOAD_LEVEL, "Loading failed with message: " + e3.getMessage());
                        arrayList.add(e3);
                        str2 = findLibraryPath;
                        j2 = j4;
                    }
                } else if (Platform.isLinux() || Platform.isFreeBSD()) {
                    LOG.log(DEBUG_LOAD_LEVEL, "Looking for version variants");
                    String matchLibrary = matchLibrary(str, arrayList2);
                    str2 = matchLibrary;
                    j2 = j4;
                    if (matchLibrary != null) {
                        LOG.log(DEBUG_LOAD_LEVEL, "Trying " + matchLibrary);
                        try {
                            j2 = Native.open(matchLibrary, openFlags);
                            str2 = matchLibrary;
                        } catch (UnsatisfiedLinkError e4) {
                            LOG.log(DEBUG_LOAD_LEVEL, "Loading failed with message: " + e4.getMessage());
                            arrayList.add(e4);
                            j2 = j4;
                            str2 = matchLibrary;
                        }
                    }
                } else if (!Platform.isMac() || str.endsWith(".dylib")) {
                    str2 = findLibraryPath;
                    j2 = j4;
                    if (Platform.isWindows()) {
                        str2 = findLibraryPath;
                        j2 = j4;
                        if (!isAbsolute) {
                            LOG.log(DEBUG_LOAD_LEVEL, "Looking for lib- prefix");
                            String findLibraryPath2 = findLibraryPath("lib" + str, arrayList2);
                            str2 = findLibraryPath2;
                            j2 = j4;
                            if (findLibraryPath2 != null) {
                                LOG.log(DEBUG_LOAD_LEVEL, "Trying " + findLibraryPath2);
                                try {
                                    j2 = Native.open(findLibraryPath2, openFlags);
                                    str2 = findLibraryPath2;
                                } catch (UnsatisfiedLinkError e5) {
                                    LOG.log(DEBUG_LOAD_LEVEL, "Loading failed with message: " + e5.getMessage());
                                    arrayList.add(e5);
                                    str2 = findLibraryPath2;
                                    j2 = j4;
                                }
                            }
                        }
                    }
                } else {
                    LOG.log(DEBUG_LOAD_LEVEL, "Looking for matching frameworks");
                    String matchFramework = matchFramework(str);
                    str2 = matchFramework;
                    j2 = j4;
                    if (matchFramework != null) {
                        try {
                            LOG.log(DEBUG_LOAD_LEVEL, "Trying " + matchFramework);
                            j2 = Native.open(matchFramework, openFlags);
                            str2 = matchFramework;
                        } catch (UnsatisfiedLinkError e6) {
                            LOG.log(DEBUG_LOAD_LEVEL, "Loading failed with message: " + e6.getMessage());
                            arrayList.add(e6);
                            str2 = matchFramework;
                            j2 = j4;
                        }
                    }
                }
                if (j2 == 0) {
                    String str3 = str2;
                    long j5 = j2;
                    try {
                        File extractFromResourcePath = Native.extractFromResourcePath(str, (ClassLoader) map.get(Library.OPTION_CLASSLOADER));
                        try {
                            long open2 = Native.open(extractFromResourcePath.getAbsolutePath(), openFlags);
                            j2 = open2;
                            String absolutePath = extractFromResourcePath.getAbsolutePath();
                            str2 = absolutePath;
                            j2 = open2;
                            if (Native.isUnpacked(extractFromResourcePath)) {
                                Native.deleteLibrary(extractFromResourcePath);
                                str2 = absolutePath;
                                j2 = open2;
                            }
                        } catch (Throwable th) {
                            if (Native.isUnpacked(extractFromResourcePath)) {
                                String str4 = str2;
                                Native.deleteLibrary(extractFromResourcePath);
                            }
                            String str5 = str2;
                            throw th;
                        }
                    } catch (IOException e7) {
                        LOG.log(DEBUG_LOAD_LEVEL, "Loading failed with message: " + e7.getMessage());
                        arrayList.add(e7);
                        str2 = str3;
                        j2 = j5;
                    }
                }
                j3 = j2;
                if (j2 == 0) {
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("Unable to load library '");
                    sb3.append(str);
                    sb3.append("':");
                    for (Throwable th2 : arrayList) {
                        sb3.append("\n");
                        sb3.append(th2.getMessage());
                    }
                    UnsatisfiedLinkError unsatisfiedLinkError = new UnsatisfiedLinkError(sb3.toString());
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        addSuppressedReflected(unsatisfiedLinkError, (Throwable) it.next());
                    }
                    throw unsatisfiedLinkError;
                }
            }
        }
        LOG.log(DEBUG_LOAD_LEVEL, "Found library '" + str + "' at " + str2);
        return new NativeLibrary(str, str2, j3, map);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String mapSharedLibraryName(String str) {
        String str2;
        if (Platform.isMac()) {
            if (str.startsWith("lib") && (str.endsWith(".dylib") || str.endsWith(".jnilib"))) {
                return str;
            }
            String mapLibraryName = System.mapLibraryName(str);
            String str3 = mapLibraryName;
            if (mapLibraryName.endsWith(".jnilib")) {
                str3 = mapLibraryName.substring(0, mapLibraryName.lastIndexOf(".jnilib")) + ".dylib";
            }
            return str3;
        }
        if (Platform.isLinux() || Platform.isFreeBSD()) {
            str2 = str;
            if (!isVersionedName(str)) {
                if (str.endsWith(".so")) {
                    return str;
                }
            }
            return str2;
        }
        if (Platform.isAIX()) {
            if (str.startsWith("lib")) {
                return str;
            }
        } else if (Platform.isWindows() && (str.endsWith(".drv") || str.endsWith(".dll"))) {
            return str;
        }
        str2 = System.mapLibraryName(str);
        return str2;
    }

    static String matchFramework(String str) {
        File file = new File(str);
        if (file.isAbsolute()) {
            if (str.indexOf(".framework") != -1 && file.exists()) {
                return file.getAbsolutePath();
            }
            File file2 = new File(new File(file.getParentFile(), file.getName() + ".framework"), file.getName());
            if (file2.exists()) {
                return file2.getAbsolutePath();
            }
            return null;
        }
        String[] strArr = {System.getProperty("user.home"), "", "/System"};
        int i = 0;
        String str2 = str;
        if (str.indexOf(".framework") == -1) {
            str2 = str + ".framework/" + str;
            i = 0;
        }
        while (i < strArr.length) {
            String str3 = strArr[i] + "/Library/Frameworks/" + str2;
            if (new File(str3).exists()) {
                return str3;
            }
            i++;
        }
        return null;
    }

    static String matchLibrary(final String str, List<String> list) {
        File file = new File(str);
        if (file.isAbsolute()) {
            list = Arrays.asList(file.getParent());
        }
        FilenameFilter filenameFilter = new FilenameFilter() { // from class: com.sun.jna.NativeLibrary.2
            @Override // java.io.FilenameFilter
            public boolean accept(File file2, String str2) {
                if (!str2.startsWith("lib" + str + ".so")) {
                    if (!str2.startsWith(str + ".so") || !str.startsWith("lib")) {
                        return false;
                    }
                }
                return NativeLibrary.isVersionedName(str2);
            }
        };
        LinkedList linkedList = new LinkedList();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            File[] listFiles = new File(it.next()).listFiles(filenameFilter);
            if (listFiles != null && listFiles.length > 0) {
                linkedList.addAll(Arrays.asList(listFiles));
            }
        }
        double d = -1.0d;
        String str2 = null;
        Iterator it2 = linkedList.iterator();
        while (it2.hasNext()) {
            String absolutePath = ((File) it2.next()).getAbsolutePath();
            double parseVersion = parseVersion(absolutePath.substring(absolutePath.lastIndexOf(".so.") + 4));
            if (parseVersion > d) {
                str2 = absolutePath;
                d = parseVersion;
            }
        }
        return str2;
    }

    private static int openFlags(Map<String, ?> map) {
        Object obj = map.get(Library.OPTION_OPEN_FLAGS);
        if (obj instanceof Number) {
            return ((Number) obj).intValue();
        }
        return -1;
    }

    static double parseVersion(String str) {
        String str2;
        int indexOf = str.indexOf(".");
        double d = 1.0d;
        double d2 = 0.0d;
        while (str != null) {
            if (indexOf != -1) {
                str2 = str.substring(0, indexOf);
                str = str.substring(indexOf + 1);
                indexOf = str.indexOf(".");
            } else {
                str2 = str;
                str = null;
            }
            try {
                d2 += Integer.parseInt(str2) / d;
                d *= 100.0d;
            } catch (NumberFormatException e) {
                return 0.0d;
            }
        }
        return d2;
    }

    public void dispose() {
        HashSet hashSet = new HashSet();
        synchronized (libraries) {
            for (Map.Entry<String, Reference<NativeLibrary>> entry : libraries.entrySet()) {
                if (entry.getValue().get() == this) {
                    hashSet.add(entry.getKey());
                }
            }
            Iterator it = hashSet.iterator();
            while (it.hasNext()) {
                libraries.remove((String) it.next());
            }
        }
        synchronized (this) {
            if (this.handle != 0) {
                Native.close(this.handle);
                this.handle = 0L;
            }
        }
    }

    protected void finalize() {
        dispose();
    }

    public File getFile() {
        String str = this.libraryPath;
        if (str == null) {
            return null;
        }
        return new File(str);
    }

    public Function getFunction(String str) {
        return getFunction(str, this.callFlags);
    }

    public Function getFunction(String str, int i) {
        return getFunction(str, i, this.encoding);
    }

    public Function getFunction(String str, int i, String str2) {
        Function function;
        if (str == null) {
            throw new NullPointerException("Function name may not be null");
        }
        synchronized (this.functions) {
            String functionKey = functionKey(str, i, str2);
            Function function2 = this.functions.get(functionKey);
            function = function2;
            if (function2 == null) {
                function = new Function(this, str, i, str2);
                this.functions.put(functionKey, function);
            }
        }
        return function;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Function getFunction(String str, Method method) {
        FunctionMapper functionMapper = (FunctionMapper) this.options.get(Library.OPTION_FUNCTION_MAPPER);
        if (functionMapper != null) {
            str = functionMapper.getFunctionName(this, method);
        }
        String property = System.getProperty("jna.profiler.prefix", "$$YJP$$");
        String str2 = str;
        if (str.startsWith(property)) {
            str2 = str.substring(property.length());
        }
        int i = this.callFlags;
        Class<?>[] exceptionTypes = method.getExceptionTypes();
        int i2 = 0;
        while (i2 < exceptionTypes.length) {
            int i3 = i;
            if (LastErrorException.class.isAssignableFrom(exceptionTypes[i2])) {
                i3 = i | 64;
            }
            i2++;
            i = i3;
        }
        return getFunction(str2, i);
    }

    public Pointer getGlobalVariableAddress(String str) {
        try {
            return new Pointer(getSymbolAddress(str));
        } catch (UnsatisfiedLinkError e) {
            throw new UnsatisfiedLinkError("Error looking up '" + str + "': " + e.getMessage());
        }
    }

    public String getName() {
        return this.libraryName;
    }

    public Map<String, ?> getOptions() {
        return this.options;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long getSymbolAddress(String str) {
        long j = this.handle;
        if (j != 0) {
            return Native.findSymbol(j, str);
        }
        throw new UnsatisfiedLinkError("Library has been unloaded");
    }

    public String toString() {
        return "Native Library <" + this.libraryPath + "@" + this.handle + ">";
    }
}
