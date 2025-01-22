package com.sun.jna;

import com.sun.jna.Callback;
import com.sun.jna.Library;
import com.sun.jna.Structure;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.awt.Component;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Window;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.WeakHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes08-dex2jar.jar:com/sun/jna/Native.class */
public final class Native implements Version {
    public static final int BOOL_SIZE;
    static final int CB_HAS_INITIALIZER = 1;
    static final int CB_OPTION_DIRECT = 1;
    static final int CB_OPTION_IN_DLL = 2;
    private static final int CVT_ARRAY_BOOLEAN = 13;
    private static final int CVT_ARRAY_BYTE = 6;
    private static final int CVT_ARRAY_CHAR = 8;
    private static final int CVT_ARRAY_DOUBLE = 12;
    private static final int CVT_ARRAY_FLOAT = 11;
    private static final int CVT_ARRAY_INT = 9;
    private static final int CVT_ARRAY_LONG = 10;
    private static final int CVT_ARRAY_SHORT = 7;
    private static final int CVT_BOOLEAN = 14;
    private static final int CVT_BUFFER = 5;
    private static final int CVT_CALLBACK = 15;
    private static final int CVT_DEFAULT = 0;
    private static final int CVT_FLOAT = 16;
    private static final int CVT_INTEGER_TYPE = 21;
    private static final int CVT_JNIENV = 27;
    private static final int CVT_NATIVE_MAPPED = 17;
    private static final int CVT_NATIVE_MAPPED_STRING = 18;
    private static final int CVT_NATIVE_MAPPED_WSTRING = 19;
    private static final int CVT_OBJECT = 26;
    private static final int CVT_POINTER = 1;
    private static final int CVT_POINTER_TYPE = 22;
    private static final int CVT_STRING = 2;
    private static final int CVT_STRUCTURE = 3;
    private static final int CVT_STRUCTURE_BYVAL = 4;
    private static final int CVT_TYPE_MAPPER = 23;
    private static final int CVT_TYPE_MAPPER_STRING = 24;
    private static final int CVT_TYPE_MAPPER_WSTRING = 25;
    private static final int CVT_UNSUPPORTED = -1;
    private static final int CVT_WSTRING = 20;
    private static final Level DEBUG_JNA_LOAD_LEVEL;
    private static final Callback.UncaughtExceptionHandler DEFAULT_HANDLER;
    static final String JNA_TMPLIB_PREFIX = "jna";
    public static final int LONG_DOUBLE_SIZE;
    public static final int LONG_SIZE;
    static final int MAX_ALIGNMENT;
    static final int MAX_PADDING;
    public static final int POINTER_SIZE;
    public static final int SIZE_T_SIZE;
    private static final int TYPE_BOOL = 4;
    private static final int TYPE_LONG = 1;
    private static final int TYPE_LONG_DOUBLE = 5;
    private static final int TYPE_SIZE_T = 3;
    private static final int TYPE_VOIDP = 0;
    private static final int TYPE_WCHAR_T = 2;
    public static final int WCHAR_SIZE;
    private static final String _OPTION_ENCLOSING_LIBRARY = "enclosing-library";
    private static Callback.UncaughtExceptionHandler callbackExceptionHandler;
    private static final Object finalizer;
    static String jnidispatchPath;
    private static final Map<Class<?>, Reference<?>> libraries;
    private static final ThreadLocal<Memory> nativeThreadTerminationFlag;
    private static final Map<Thread, Pointer> nativeThreads;
    private static final Map<Class<?>, long[]> registeredClasses;
    private static final Map<Class<?>, NativeLibrary> registeredLibraries;
    private static final Map<Class<?>, Map<String, Object>> typeOptions;
    private static final Logger LOG = Logger.getLogger(Native.class.getName());
    public static final Charset DEFAULT_CHARSET = Charset.defaultCharset();
    public static final String DEFAULT_ENCODING = DEFAULT_CHARSET.name();
    public static final boolean DEBUG_LOAD = Boolean.getBoolean("jna.debug_load");
    public static final boolean DEBUG_JNA_LOAD = Boolean.getBoolean("jna.debug_load.jna");

    /* loaded from: classes08-dex2jar.jar:com/sun/jna/Native$AWT.class */
    private static class AWT {
        private AWT() {
        }

        static long getComponentID(Object obj) throws HeadlessException {
            if (GraphicsEnvironment.isHeadless()) {
                throw new HeadlessException("No native windows when headless");
            }
            Component component = (Component) obj;
            if (component.isLightweight()) {
                throw new IllegalArgumentException("Component must be heavyweight");
            }
            if (!component.isDisplayable()) {
                throw new IllegalStateException("Component must be displayable");
            }
            if (Platform.isX11() && System.getProperty("java.version").startsWith("1.4") && !component.isVisible()) {
                throw new IllegalStateException("Component must be visible");
            }
            return Native.getWindowHandle0(component);
        }

        static long getWindowID(Window window) throws HeadlessException {
            return getComponentID(window);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes08-dex2jar.jar:com/sun/jna/Native$Buffers.class */
    public static class Buffers {
        private Buffers() {
        }

        static boolean isBuffer(Class<?> cls) {
            return Buffer.class.isAssignableFrom(cls);
        }
    }

    /* loaded from: classes08-dex2jar.jar:com/sun/jna/Native$ffi_callback.class */
    public interface ffi_callback {
        void invoke(long j, long j2, long j3);
    }

    static {
        String property;
        DEBUG_JNA_LOAD_LEVEL = DEBUG_JNA_LOAD ? Level.INFO : Level.FINE;
        jnidispatchPath = null;
        typeOptions = Collections.synchronizedMap(new WeakHashMap());
        libraries = Collections.synchronizedMap(new WeakHashMap());
        DEFAULT_HANDLER = new Callback.UncaughtExceptionHandler() { // from class: com.sun.jna.Native.1
            @Override // com.sun.jna.Callback.UncaughtExceptionHandler
            public void uncaughtException(Callback callback, Throwable th) {
                Native.LOG.log(Level.WARNING, "JNA: Callback " + callback + " threw the following exception", th);
            }
        };
        callbackExceptionHandler = DEFAULT_HANDLER;
        loadNativeDispatchLibrary();
        if (isCompatibleVersion(Version.VERSION_NATIVE, getNativeVersion())) {
            POINTER_SIZE = sizeof(0);
            LONG_SIZE = sizeof(1);
            WCHAR_SIZE = sizeof(2);
            SIZE_T_SIZE = sizeof(3);
            BOOL_SIZE = sizeof(4);
            LONG_DOUBLE_SIZE = sizeof(5);
            initIDs();
            if (Boolean.getBoolean("jna.protected")) {
                setProtected(true);
            }
            MAX_ALIGNMENT = (Platform.isSPARC() || Platform.isWindows() || (Platform.isLinux() && (Platform.isARM() || Platform.isPPC() || Platform.isMIPS())) || Platform.isAIX() || (Platform.isAndroid() && !Platform.isIntel())) ? 8 : LONG_SIZE;
            MAX_PADDING = (Platform.isMac() && Platform.isPPC()) ? 8 : MAX_ALIGNMENT;
            System.setProperty("jna.loaded", "true");
            finalizer = new Object() { // from class: com.sun.jna.Native.2
                protected void finalize() throws Throwable {
                    Native.dispose();
                    super.finalize();
                }
            };
            registeredClasses = new WeakHashMap();
            registeredLibraries = new WeakHashMap();
            nativeThreadTerminationFlag = new ThreadLocal<Memory>() { // from class: com.sun.jna.Native.7
                /* JADX INFO: Access modifiers changed from: protected */
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.lang.ThreadLocal
                public Memory initialValue() {
                    Memory memory = new Memory(4L);
                    memory.clear();
                    return memory;
                }
            };
            nativeThreads = Collections.synchronizedMap(new WeakHashMap());
            return;
        }
        String property2 = System.getProperty("line.separator");
        StringBuilder sb = new StringBuilder();
        sb.append(property2);
        sb.append(property2);
        sb.append("There is an incompatible JNA native library installed on this system");
        sb.append(property2);
        sb.append("Expected: ");
        sb.append(Version.VERSION_NATIVE);
        sb.append(property2);
        sb.append("Found:    ");
        sb.append(getNativeVersion());
        sb.append(property2);
        if (jnidispatchPath != null) {
            property = "(at " + jnidispatchPath + ")";
        } else {
            property = System.getProperty("java.library.path");
        }
        sb.append(property);
        sb.append(".");
        sb.append(property2);
        sb.append("To resolve this issue you may do one of the following:");
        sb.append(property2);
        sb.append(" - remove or uninstall the offending library");
        sb.append(property2);
        sb.append(" - set the system property jna.nosys=true");
        sb.append(property2);
        sb.append(" - set jna.boot.library.path to include the path to the version of the ");
        sb.append(property2);
        sb.append("   jnidispatch library included with the JNA jar file you are using");
        sb.append(property2);
        throw new Error(sb.toString());
    }

    private Native() {
    }

    private static native long _getDirectBufferPointer(Buffer buffer);

    private static native long _getPointer(long j);

    private static Map<String, Object> cacheOptions(Class<?> cls, Map<String, ?> map, Object obj) {
        HashMap hashMap = new HashMap(map);
        hashMap.put(_OPTION_ENCLOSING_LIBRARY, cls);
        typeOptions.put(cls, hashMap);
        if (obj != null) {
            libraries.put(cls, new WeakReference(obj));
        }
        if (!cls.isInterface() && Library.class.isAssignableFrom(cls)) {
            Class<?>[] interfaces = cls.getInterfaces();
            int length = interfaces.length;
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= length) {
                    break;
                }
                Class<?> cls2 = interfaces[i2];
                if (Library.class.isAssignableFrom(cls2)) {
                    cacheOptions(cls2, hashMap, obj);
                    return hashMap;
                }
                i = i2 + 1;
            }
        }
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void close(long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native synchronized long createNativeCallback(Callback callback, Method method, Class<?>[] clsArr, Class<?> cls, int i, int i2, String str);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean deleteLibrary(File file) {
        if (file.delete()) {
            return true;
        }
        markTemporaryFile(file);
        return false;
    }

    public static void detach(boolean z) {
        Thread currentThread = Thread.currentThread();
        if (z) {
            nativeThreads.remove(currentThread);
            nativeThreadTerminationFlag.get();
            setDetachState(true, 0L);
        } else {
            if (nativeThreads.containsKey(currentThread)) {
                return;
            }
            Memory memory = nativeThreadTerminationFlag.get();
            nativeThreads.put(currentThread, memory);
            setDetachState(false, memory.peer);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void dispose() {
        CallbackReference.disposeAll();
        Memory.disposeAll();
        NativeLibrary.disposeAll();
        unregisterAll();
        jnidispatchPath = null;
        System.setProperty("jna.loaded", "false");
    }

    public static File extractFromResourcePath(String str) throws IOException {
        return extractFromResourcePath(str, null);
    }

    public static File extractFromResourcePath(String str, ClassLoader classLoader) throws IOException {
        String str2;
        File file;
        FileOutputStream fileOutputStream;
        Level level = (DEBUG_LOAD || (DEBUG_JNA_LOAD && str.contains("jnidispatch"))) ? Level.INFO : Level.FINE;
        ClassLoader classLoader2 = classLoader;
        if (classLoader == null) {
            ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
            classLoader2 = contextClassLoader;
            if (contextClassLoader == null) {
                classLoader2 = Native.class.getClassLoader();
            }
        }
        LOG.log(level, "Looking in classpath from {0} for {1}", new Object[]{classLoader2, str});
        String mapSharedLibraryName = str.startsWith("/") ? str : NativeLibrary.mapSharedLibraryName(str);
        if (str.startsWith("/")) {
            str2 = str;
        } else {
            str2 = Platform.RESOURCE_PREFIX + "/" + mapSharedLibraryName;
        }
        String str3 = str2;
        if (str2.startsWith("/")) {
            str3 = str2.substring(1);
        }
        URL resource = classLoader2.getResource(str3);
        URL url = resource;
        if (resource == null) {
            url = resource;
            if (str3.startsWith(Platform.RESOURCE_PREFIX)) {
                url = classLoader2.getResource(mapSharedLibraryName);
            }
        }
        if (url == null) {
            String property = System.getProperty("java.class.path");
            if (classLoader2 instanceof URLClassLoader) {
                property = Arrays.asList(((URLClassLoader) classLoader2).getURLs()).toString();
            }
            throw new IOException("Native library (" + str3 + ") not found in resource path (" + property + ")");
        }
        LOG.log(level, "Found library resource at {0}", url);
        if (url.getProtocol().toLowerCase().equals("file")) {
            try {
                file = new File(new URI(url.toString()));
            } catch (URISyntaxException e) {
                file = new File(url.getPath());
            }
            LOG.log(level, "Looking in {0}", file.getAbsolutePath());
            if (file.exists()) {
                return file;
            }
            throw new IOException("File URL " + url + " could not be properly decoded");
        }
        if (Boolean.getBoolean("jna.nounpack")) {
            return null;
        }
        InputStream resourceAsStream = classLoader2.getResourceAsStream(str3);
        if (resourceAsStream == null) {
            throw new IOException("Can't obtain InputStream for " + str3);
        }
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                File createTempFile = File.createTempFile(JNA_TMPLIB_PREFIX, Platform.isWindows() ? ".dll" : null, getTempDir());
                if (!Boolean.getBoolean("jnidispatch.preserve")) {
                    createTempFile.deleteOnExit();
                }
                LOG.log(level, "Extracting library to {0}", createTempFile.getAbsolutePath());
                fileOutputStream = new FileOutputStream(createTempFile);
                try {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = resourceAsStream.read(bArr, 0, bArr.length);
                        if (read > 0) {
                            fileOutputStream.write(bArr, 0, read);
                        } else {
                            try {
                                break;
                            } catch (IOException e2) {
                            }
                        }
                    }
                    resourceAsStream.close();
                    try {
                        fileOutputStream.close();
                    } catch (IOException e3) {
                    }
                    return createTempFile;
                } catch (IOException e4) {
                    e = e4;
                    StringBuilder sb = new StringBuilder();
                    FileOutputStream fileOutputStream3 = fileOutputStream;
                    sb.append("Failed to create temporary file for ");
                    FileOutputStream fileOutputStream4 = fileOutputStream;
                    sb.append(str);
                    FileOutputStream fileOutputStream5 = fileOutputStream;
                    sb.append(" library: ");
                    FileOutputStream fileOutputStream6 = fileOutputStream;
                    sb.append(e.getMessage());
                    fileOutputStream2 = fileOutputStream;
                    throw new IOException(sb.toString());
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream2 = fileOutputStream;
                    try {
                        resourceAsStream.close();
                    } catch (IOException e5) {
                    }
                    if (fileOutputStream2 != null) {
                        try {
                            fileOutputStream2.close();
                        } catch (IOException e6) {
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e7) {
            e = e7;
            fileOutputStream = null;
        }
    }

    public static native void ffi_call(long j, long j2, long j3, long j4);

    public static native void ffi_free_closure(long j);

    public static native long ffi_prep_cif(int i, int i2, long j, long j2);

    public static native long ffi_prep_closure(long j, ffi_callback ffi_callbackVar);

    static Class<?> findDirectMappedClass(Class<?> cls) {
        Method[] declaredMethods = cls.getDeclaredMethods();
        int length = declaredMethods.length;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length) {
                int lastIndexOf = cls.getName().lastIndexOf("$");
                if (lastIndexOf != -1) {
                    try {
                        return findDirectMappedClass(Class.forName(cls.getName().substring(0, lastIndexOf), true, cls.getClassLoader()));
                    } catch (ClassNotFoundException e) {
                    }
                }
                throw new IllegalArgumentException("Can't determine class with native methods from the current context (" + cls + ")");
            }
            if ((declaredMethods[i2].getModifiers() & Function.MAX_NARGS) != 0) {
                return cls;
            }
            i = i2 + 1;
        }
    }

    static Class<?> findEnclosingLibraryClass(Class<?> cls) {
        if (cls == null) {
            return null;
        }
        Map<String, Object> map = typeOptions.get(cls);
        if (map != null) {
            Class<?> cls2 = (Class) map.get(_OPTION_ENCLOSING_LIBRARY);
            return cls2 != null ? cls2 : cls;
        }
        if (Library.class.isAssignableFrom(cls)) {
            return cls;
        }
        Class<?> cls3 = cls;
        if (Callback.class.isAssignableFrom(cls)) {
            cls3 = CallbackReference.findCallbackClass(cls);
        }
        Class<?> findEnclosingLibraryClass = findEnclosingLibraryClass(cls3.getDeclaringClass());
        return findEnclosingLibraryClass != null ? findEnclosingLibraryClass : findEnclosingLibraryClass(cls3.getSuperclass());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long findSymbol(long j, String str);

    public static native void free(long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native synchronized void freeNativeCallback(long j);

    private static NativeMapped fromNative(Class<?> cls, Object obj) {
        return (NativeMapped) NativeMappedConverter.getInstance(cls).fromNative(obj, new FromNativeContext(cls));
    }

    private static NativeMapped fromNative(Method method, Object obj) {
        Class<?> returnType = method.getReturnType();
        return (NativeMapped) NativeMappedConverter.getInstance(returnType).fromNative(obj, new MethodResultContext(returnType, null, null, method));
    }

    private static Object fromNative(FromNativeConverter fromNativeConverter, Object obj, Method method) {
        return fromNativeConverter.fromNative(obj, new MethodResultContext(method.getReturnType(), null, null, method));
    }

    private static native String getAPIChecksum();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native byte getByte(Pointer pointer, long j, long j2);

    static byte[] getBytes(String str) {
        return getBytes(str, getDefaultStringEncoding());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] getBytes(String str, String str2) {
        return getBytes(str, getCharset(str2));
    }

    static byte[] getBytes(String str, Charset charset) {
        return str.getBytes(charset);
    }

    public static Callback.UncaughtExceptionHandler getCallbackExceptionHandler() {
        return callbackExceptionHandler;
    }

    static Class<?> getCallingClass() {
        Class<?>[] classContext = new SecurityManager() { // from class: com.sun.jna.Native.6
            @Override // java.lang.SecurityManager
            public Class<?>[] getClassContext() {
                return super.getClassContext();
            }
        }.getClassContext();
        if (classContext == null) {
            throw new IllegalStateException("The SecurityManager implementation on this platform is broken; you must explicitly provide the class to register");
        }
        if (classContext.length >= 4) {
            return classContext[3];
        }
        throw new IllegalStateException("This method must be called from the static initializer of a class");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native char getChar(Pointer pointer, long j, long j2);

    /* JADX WARN: Removed duplicated region for block: B:6:0x0051  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.nio.charset.Charset getCharset(java.lang.String r8) {
        /*
            r0 = r8
            if (r0 == 0) goto L49
            r0 = r8
            java.nio.charset.Charset r0 = java.nio.charset.Charset.forName(r0)     // Catch: java.nio.charset.UnsupportedCharsetException -> Le java.nio.charset.IllegalCharsetNameException -> L2d
            r9 = r0
            r0 = r9
            r8 = r0
            goto L4b
        Le:
            r9 = move-exception
            java.util.logging.Logger r0 = com.sun.jna.Native.LOG
            java.util.logging.Level r1 = java.util.logging.Level.WARNING
            java.lang.String r2 = "JNA Warning: Encoding ''{0}'' is unsupported ({1})"
            r3 = 2
            java.lang.Object[] r3 = new java.lang.Object[r3]
            r4 = r3
            r5 = 0
            r6 = r8
            r4[r5] = r6
            r4 = r3
            r5 = 1
            r6 = r9
            java.lang.String r6 = r6.getMessage()
            r4[r5] = r6
            r0.log(r1, r2, r3)
            goto L49
        L2d:
            r9 = move-exception
            java.util.logging.Logger r0 = com.sun.jna.Native.LOG
            java.util.logging.Level r1 = java.util.logging.Level.WARNING
            java.lang.String r2 = "JNA Warning: Encoding ''{0}'' is unsupported ({1})"
            r3 = 2
            java.lang.Object[] r3 = new java.lang.Object[r3]
            r4 = r3
            r5 = 0
            r6 = r8
            r4[r5] = r6
            r4 = r3
            r5 = 1
            r6 = r9
            java.lang.String r6 = r6.getMessage()
            r4[r5] = r6
            r0.log(r1, r2, r3)
        L49:
            r0 = 0
            r8 = r0
        L4b:
            r0 = r8
            r9 = r0
            r0 = r8
            if (r0 != 0) goto L64
            java.util.logging.Logger r0 = com.sun.jna.Native.LOG
            java.util.logging.Level r1 = java.util.logging.Level.WARNING
            java.lang.String r2 = "JNA Warning: Using fallback encoding {0}"
            java.nio.charset.Charset r3 = com.sun.jna.Native.DEFAULT_CHARSET
            r0.log(r1, r2, r3)
            java.nio.charset.Charset r0 = com.sun.jna.Native.DEFAULT_CHARSET
            r9 = r0
        L64:
            r0 = r9
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.jna.Native.getCharset(java.lang.String):java.nio.charset.Charset");
    }

    public static long getComponentID(Component component) throws HeadlessException {
        return AWT.getComponentID(component);
    }

    public static Pointer getComponentPointer(Component component) throws HeadlessException {
        return new Pointer(AWT.getComponentID(component));
    }

    private static int getConversion(Class<?> cls, TypeMapper typeMapper, boolean z) {
        Class<?> cls2 = cls;
        if (cls == Void.class) {
            cls2 = Void.TYPE;
        }
        if (typeMapper != null) {
            FromNativeConverter fromNativeConverter = typeMapper.getFromNativeConverter(cls2);
            ToNativeConverter toNativeConverter = typeMapper.getToNativeConverter(cls2);
            if (fromNativeConverter != null) {
                Class<?> nativeType = fromNativeConverter.nativeType();
                if (nativeType == String.class) {
                    return 24;
                }
                return nativeType == WString.class ? 25 : 23;
            }
            if (toNativeConverter != null) {
                Class<?> nativeType2 = toNativeConverter.nativeType();
                if (nativeType2 == String.class) {
                    return 24;
                }
                return nativeType2 == WString.class ? 25 : 23;
            }
        }
        if (Pointer.class.isAssignableFrom(cls2)) {
            return 1;
        }
        if (String.class == cls2) {
            return 2;
        }
        if (WString.class.isAssignableFrom(cls2)) {
            return 20;
        }
        if (Platform.HAS_BUFFERS && Buffers.isBuffer(cls2)) {
            return 5;
        }
        if (Structure.class.isAssignableFrom(cls2)) {
            return Structure.ByValue.class.isAssignableFrom(cls2) ? 4 : 3;
        }
        if (cls2.isArray()) {
            char charAt = cls2.getName().charAt(1);
            if (charAt == 'F') {
                return 11;
            }
            if (charAt == 'S') {
                return 7;
            }
            if (charAt == 'Z') {
                return 13;
            }
            if (charAt == 'I') {
                return 9;
            }
            if (charAt == 'J') {
                return 10;
            }
            switch (charAt) {
                case 'B':
                    return 6;
                case 'C':
                    return 8;
                case 'D':
                    return 12;
            }
        }
        if (cls2.isPrimitive()) {
            return cls2 == Boolean.TYPE ? 14 : 0;
        }
        if (Callback.class.isAssignableFrom(cls2)) {
            return 15;
        }
        if (IntegerType.class.isAssignableFrom(cls2)) {
            return 21;
        }
        if (PointerType.class.isAssignableFrom(cls2)) {
            return 22;
        }
        if (!NativeMapped.class.isAssignableFrom(cls2)) {
            if (JNIEnv.class == cls2) {
                return 27;
            }
            return z ? 26 : -1;
        }
        Class<?> nativeType3 = NativeMappedConverter.getInstance(cls2).nativeType();
        if (nativeType3 == String.class) {
            return 18;
        }
        return nativeType3 == WString.class ? 19 : 17;
    }

    public static String getDefaultStringEncoding() {
        return System.getProperty("jna.encoding", DEFAULT_ENCODING);
    }

    public static Pointer getDirectBufferPointer(Buffer buffer) {
        long _getDirectBufferPointer = _getDirectBufferPointer(buffer);
        if (_getDirectBufferPointer == 0) {
            return null;
        }
        return new Pointer(_getDirectBufferPointer);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native ByteBuffer getDirectByteBuffer(Pointer pointer, long j, long j2, long j3);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native double getDouble(Pointer pointer, long j, long j2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native float getFloat(Pointer pointer, long j, long j2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int getInt(Pointer pointer, long j, long j2);

    public static native int getLastError();

    public static Map<String, Object> getLibraryOptions(Class<?> cls) {
        Map emptyMap;
        Map<String, Object> map = typeOptions.get(cls);
        if (map != null) {
            return map;
        }
        Class<?> findEnclosingLibraryClass = findEnclosingLibraryClass(cls);
        if (findEnclosingLibraryClass != null) {
            loadLibraryInstance(findEnclosingLibraryClass);
        } else {
            findEnclosingLibraryClass = cls;
        }
        Map<String, Object> map2 = typeOptions.get(findEnclosingLibraryClass);
        if (map2 != null) {
            typeOptions.put(cls, map2);
            return map2;
        }
        try {
            Field field = findEnclosingLibraryClass.getField(HttpRequest.METHOD_OPTIONS);
            field.setAccessible(true);
            emptyMap = (Map) field.get(null);
            if (emptyMap == null) {
                throw new IllegalStateException("Null options field");
            }
        } catch (NoSuchFieldException e) {
            emptyMap = Collections.emptyMap();
        } catch (Exception e2) {
            throw new IllegalArgumentException("OPTIONS must be a public field of type java.util.Map (" + e2 + "): " + findEnclosingLibraryClass);
        }
        HashMap hashMap = new HashMap(emptyMap);
        if (!hashMap.containsKey(Library.OPTION_TYPE_MAPPER)) {
            hashMap.put(Library.OPTION_TYPE_MAPPER, lookupField(findEnclosingLibraryClass, "TYPE_MAPPER", TypeMapper.class));
        }
        if (!hashMap.containsKey(Library.OPTION_STRUCTURE_ALIGNMENT)) {
            hashMap.put(Library.OPTION_STRUCTURE_ALIGNMENT, lookupField(findEnclosingLibraryClass, "STRUCTURE_ALIGNMENT", Integer.class));
        }
        if (!hashMap.containsKey(Library.OPTION_STRING_ENCODING)) {
            hashMap.put(Library.OPTION_STRING_ENCODING, lookupField(findEnclosingLibraryClass, "STRING_ENCODING", String.class));
        }
        Map<String, Object> cacheOptions = cacheOptions(findEnclosingLibraryClass, hashMap, null);
        if (cls != findEnclosingLibraryClass) {
            typeOptions.put(cls, cacheOptions);
        }
        return cacheOptions;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long getLong(Pointer pointer, long j, long j2);

    public static int getNativeSize(Class<?> cls) {
        Class<?> cls2 = cls;
        if (NativeMapped.class.isAssignableFrom(cls)) {
            cls2 = NativeMappedConverter.getInstance(cls).nativeType();
        }
        if (cls2 == Boolean.TYPE || cls2 == Boolean.class) {
            return 4;
        }
        if (cls2 == Byte.TYPE || cls2 == Byte.class) {
            return 1;
        }
        if (cls2 == Short.TYPE || cls2 == Short.class) {
            return 2;
        }
        if (cls2 == Character.TYPE || cls2 == Character.class) {
            return WCHAR_SIZE;
        }
        if (cls2 == Integer.TYPE || cls2 == Integer.class) {
            return 4;
        }
        if (cls2 == Long.TYPE || cls2 == Long.class) {
            return 8;
        }
        if (cls2 == Float.TYPE || cls2 == Float.class) {
            return 4;
        }
        if (cls2 == Double.TYPE || cls2 == Double.class) {
            return 8;
        }
        if (Structure.class.isAssignableFrom(cls2)) {
            return Structure.ByValue.class.isAssignableFrom(cls2) ? Structure.size(cls2) : POINTER_SIZE;
        }
        if (Pointer.class.isAssignableFrom(cls2) || ((Platform.HAS_BUFFERS && Buffers.isBuffer(cls2)) || Callback.class.isAssignableFrom(cls2) || String.class == cls2 || WString.class == cls2)) {
            return POINTER_SIZE;
        }
        throw new IllegalArgumentException("Native size for type \"" + cls2.getName() + "\" is unknown");
    }

    public static int getNativeSize(Class<?> cls, Object obj) {
        if (cls.isArray()) {
            int length = Array.getLength(obj);
            if (length > 0) {
                return length * getNativeSize(cls.getComponentType(), Array.get(obj, 0));
            }
            throw new IllegalArgumentException("Arrays of length zero not allowed: " + cls);
        }
        if (Structure.class.isAssignableFrom(cls) && !Structure.ByReference.class.isAssignableFrom(cls)) {
            return Structure.size(cls, (Structure) obj);
        }
        try {
            return getNativeSize(cls);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("The type \"" + cls.getName() + "\" is not supported: " + e.getMessage());
        }
    }

    private static native String getNativeVersion();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Pointer getPointer(long j) {
        long _getPointer = _getPointer(j);
        if (_getPointer == 0) {
            return null;
        }
        return new Pointer(_getPointer);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native short getShort(Pointer pointer, long j, long j2);

    static String getSignature(Class<?> cls) {
        if (cls.isArray()) {
            return "[" + getSignature(cls.getComponentType());
        }
        if (cls.isPrimitive()) {
            if (cls == Void.TYPE) {
                return "V";
            }
            if (cls == Boolean.TYPE) {
                return "Z";
            }
            if (cls == Byte.TYPE) {
                return "B";
            }
            if (cls == Short.TYPE) {
                return "S";
            }
            if (cls == Character.TYPE) {
                return "C";
            }
            if (cls == Integer.TYPE) {
                return "I";
            }
            if (cls == Long.TYPE) {
                return "J";
            }
            if (cls == Float.TYPE) {
                return "F";
            }
            if (cls == Double.TYPE) {
                return "D";
            }
        }
        return "L" + replace(".", "/", cls.getName()) + ";";
    }

    static String getString(Pointer pointer, long j) {
        return getString(pointer, j, getDefaultStringEncoding());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getString(Pointer pointer, long j, String str) {
        byte[] stringBytes = getStringBytes(pointer, pointer.peer, j);
        if (str != null) {
            try {
                return new String(stringBytes, str);
            } catch (UnsupportedEncodingException e) {
            }
        }
        return new String(stringBytes);
    }

    static native byte[] getStringBytes(Pointer pointer, long j, long j2);

    public static String getStringEncoding(Class<?> cls) {
        String str = (String) getLibraryOptions(cls).get(Library.OPTION_STRING_ENCODING);
        return str != null ? str : getDefaultStringEncoding();
    }

    public static int getStructureAlignment(Class<?> cls) {
        Integer num = (Integer) getLibraryOptions(cls).get(Library.OPTION_STRUCTURE_ALIGNMENT);
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    static File getTempDir() throws IOException {
        File file;
        File file2;
        String property = System.getProperty("jna.tmpdir");
        if (property != null) {
            file2 = new File(property);
            file2.mkdirs();
        } else {
            File file3 = new File(System.getProperty("java.io.tmpdir"));
            if (Platform.isMac()) {
                file = new File(System.getProperty("user.home"), "Library/Caches/JNA/temp");
            } else if (Platform.isLinux() || Platform.isSolaris() || Platform.isAIX() || Platform.isFreeBSD() || Platform.isNetBSD() || Platform.isOpenBSD() || Platform.iskFreeBSD()) {
                String str = System.getenv("XDG_CACHE_HOME");
                file = new File((str == null || str.trim().isEmpty()) ? new File(System.getProperty("user.home"), ".cache") : new File(str), "JNA/temp");
            } else {
                file = new File(file3, "jna-" + System.getProperty("user.name").hashCode());
            }
            file.mkdirs();
            file2 = file3;
            if (file.exists()) {
                file2 = !file.canWrite() ? file3 : file;
            }
        }
        if (!file2.exists()) {
            throw new IOException("JNA temporary directory '" + file2 + "' does not exist");
        }
        if (file2.canWrite()) {
            return file2;
        }
        throw new IOException("JNA temporary directory '" + file2 + "' is not writable");
    }

    static Pointer getTerminationFlag(Thread thread) {
        return nativeThreads.get(thread);
    }

    public static TypeMapper getTypeMapper(Class<?> cls) {
        return (TypeMapper) getLibraryOptions(cls).get(Library.OPTION_TYPE_MAPPER);
    }

    public static String getWebStartLibraryPath(String str) {
        if (System.getProperty("javawebstart.version") == null) {
            return null;
        }
        try {
            String str2 = (String) ((Method) AccessController.doPrivileged(new PrivilegedAction<Method>() { // from class: com.sun.jna.Native.4
                @Override // java.security.PrivilegedAction
                public Method run() {
                    try {
                        Method declaredMethod = ClassLoader.class.getDeclaredMethod("findLibrary", String.class);
                        declaredMethod.setAccessible(true);
                        return declaredMethod;
                    } catch (Exception e) {
                        return null;
                    }
                }
            })).invoke(Native.class.getClassLoader(), str);
            if (str2 != null) {
                return new File(str2).getParent();
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native String getWideString(Pointer pointer, long j, long j2);

    static native long getWindowHandle0(Component component);

    public static long getWindowID(Window window) throws HeadlessException {
        return AWT.getWindowID(window);
    }

    public static Pointer getWindowPointer(Window window) throws HeadlessException {
        return new Pointer(AWT.getWindowID(window));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long indexOf(Pointer pointer, long j, long j2, byte b);

    private static native void initIDs();

    static native int initialize_ffi_type(long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native double invokeDouble(Function function, long j, int i, Object[] objArr);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native float invokeFloat(Function function, long j, int i, Object[] objArr);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int invokeInt(Function function, long j, int i, Object[] objArr);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long invokeLong(Function function, long j, int i, Object[] objArr);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native Object invokeObject(Function function, long j, int i, Object[] objArr);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long invokePointer(Function function, long j, int i, Object[] objArr);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Structure invokeStructure(Function function, long j, int i, Object[] objArr, Structure structure) {
        invokeStructure(function, j, i, objArr, structure.getPointer().peer, structure.getTypeInfo().peer);
        return structure;
    }

    private static native void invokeStructure(Function function, long j, int i, Object[] objArr, long j2, long j3);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void invokeVoid(Function function, long j, int i, Object[] objArr);

    static boolean isCompatibleVersion(String str, String str2) {
        String[] split = str.split("\\.");
        String[] split2 = str2.split("\\.");
        if (split.length < 3 || split2.length < 3) {
            return false;
        }
        return Integer.parseInt(split[0]) == Integer.parseInt(split2[0]) && Integer.parseInt(split[1]) <= Integer.parseInt(split2[1]);
    }

    public static native synchronized boolean isProtected();

    public static boolean isSupportedNativeType(Class<?> cls) {
        if (Structure.class.isAssignableFrom(cls)) {
            return true;
        }
        boolean z = false;
        try {
            if (getNativeSize(cls) != 0) {
                z = true;
            }
            return z;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isUnpacked(File file) {
        return file.getName().startsWith(JNA_TMPLIB_PREFIX);
    }

    public static <T extends Library> T load(Class<T> cls) {
        return (T) load((String) null, cls);
    }

    public static <T extends Library> T load(Class<T> cls, Map<String, ?> map) {
        return (T) load(null, cls, map);
    }

    public static <T extends Library> T load(String str, Class<T> cls) {
        return (T) load(str, cls, Collections.emptyMap());
    }

    public static <T extends Library> T load(String str, Class<T> cls, Map<String, ?> map) {
        if (Library.class.isAssignableFrom(cls)) {
            Object newProxyInstance = Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new Library.Handler(str, cls, map));
            cacheOptions(cls, map, newProxyInstance);
            return cls.cast(newProxyInstance);
        }
        throw new IllegalArgumentException("Interface (" + cls.getSimpleName() + ") of library=" + str + " does not extend " + Library.class.getSimpleName());
    }

    @Deprecated
    public static <T> T loadLibrary(Class<T> cls) {
        return (T) loadLibrary((String) null, cls);
    }

    @Deprecated
    public static <T> T loadLibrary(Class<T> cls, Map<String, ?> map) {
        return (T) loadLibrary(null, cls, map);
    }

    @Deprecated
    public static <T> T loadLibrary(String str, Class<T> cls) {
        return (T) loadLibrary(str, cls, Collections.emptyMap());
    }

    @Deprecated
    public static <T> T loadLibrary(String str, Class<T> cls, Map<String, ?> map) {
        if (Library.class.isAssignableFrom(cls)) {
            Object newProxyInstance = Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new Library.Handler(str, cls, map));
            cacheOptions(cls, map, newProxyInstance);
            return cls.cast(newProxyInstance);
        }
        throw new IllegalArgumentException("Interface (" + cls.getSimpleName() + ") of library=" + str + " does not extend " + Library.class.getSimpleName());
    }

    private static void loadLibraryInstance(Class<?> cls) {
        if (cls == null || libraries.containsKey(cls)) {
            return;
        }
        try {
            Field[] fields = cls.getFields();
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= fields.length) {
                    return;
                }
                Field field = fields[i2];
                if (field.getType() == cls && Modifier.isStatic(field.getModifiers())) {
                    libraries.put(cls, new WeakReference(field.get(null)));
                    return;
                }
                i = i2 + 1;
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Could not access instance of " + cls + " (" + e + ")");
        }
    }

    private static void loadNativeDispatchLibrary() {
        if (!Boolean.getBoolean("jna.nounpack")) {
            try {
                removeTemporaryFiles();
            } catch (IOException e) {
                LOG.log(Level.WARNING, "JNA Warning: IOException removing temporary files", (Throwable) e);
            }
        }
        String property = System.getProperty("jna.boot.library.name", "jnidispatch");
        String property2 = System.getProperty("jna.boot.library.path");
        if (property2 != null) {
            StringTokenizer stringTokenizer = new StringTokenizer(property2, File.pathSeparator);
            while (stringTokenizer.hasMoreTokens()) {
                File file = new File(new File(stringTokenizer.nextToken()), System.mapLibraryName(property).replace(".dylib", ".jnilib"));
                String absolutePath = file.getAbsolutePath();
                LOG.log(DEBUG_JNA_LOAD_LEVEL, "Looking in {0}", absolutePath);
                if (file.exists()) {
                    try {
                        LOG.log(DEBUG_JNA_LOAD_LEVEL, "Trying {0}", absolutePath);
                        System.setProperty("jnidispatch.path", absolutePath);
                        System.load(absolutePath);
                        jnidispatchPath = absolutePath;
                        LOG.log(DEBUG_JNA_LOAD_LEVEL, "Found jnidispatch at {0}", absolutePath);
                        return;
                    } catch (UnsatisfiedLinkError e2) {
                    }
                }
                if (Platform.isMac()) {
                    String str = "dylib";
                    String str2 = "jnilib";
                    if (!absolutePath.endsWith("dylib")) {
                        str2 = "dylib";
                        str = "jnilib";
                    }
                    String str3 = absolutePath.substring(0, absolutePath.lastIndexOf(str)) + str2;
                    LOG.log(DEBUG_JNA_LOAD_LEVEL, "Looking in {0}", str3);
                    if (new File(str3).exists()) {
                        try {
                            LOG.log(DEBUG_JNA_LOAD_LEVEL, "Trying {0}", str3);
                            System.setProperty("jnidispatch.path", str3);
                            System.load(str3);
                            jnidispatchPath = str3;
                            LOG.log(DEBUG_JNA_LOAD_LEVEL, "Found jnidispatch at {0}", str3);
                            return;
                        } catch (UnsatisfiedLinkError e3) {
                            LOG.log(Level.WARNING, "File found at " + str3 + " but not loadable: " + e3.getMessage(), (Throwable) e3);
                        }
                    } else {
                        continue;
                    }
                }
            }
        }
        if (!Boolean.parseBoolean(System.getProperty("jna.nosys", "true")) || Platform.isAndroid()) {
            try {
                LOG.log(DEBUG_JNA_LOAD_LEVEL, "Trying (via loadLibrary) {0}", property);
                System.loadLibrary(property);
                LOG.log(DEBUG_JNA_LOAD_LEVEL, "Found jnidispatch on system path");
                return;
            } catch (UnsatisfiedLinkError e4) {
            }
        }
        if (Boolean.getBoolean("jna.noclasspath")) {
            throw new UnsatisfiedLinkError("Unable to locate JNA native support library");
        }
        loadNativeDispatchLibraryFromClasspath();
    }

    private static void loadNativeDispatchLibraryFromClasspath() {
        try {
            String replace = System.mapLibraryName("jnidispatch").replace(".dylib", ".jnilib");
            if (Platform.isAIX()) {
                replace = "libjnidispatch.a";
            }
            File extractFromResourcePath = extractFromResourcePath("/com/sun/jna/" + Platform.RESOURCE_PREFIX + "/" + replace, Native.class.getClassLoader());
            if (extractFromResourcePath == null && extractFromResourcePath == null) {
                throw new UnsatisfiedLinkError("Could not find JNA native support");
            }
            LOG.log(DEBUG_JNA_LOAD_LEVEL, "Trying {0}", extractFromResourcePath.getAbsolutePath());
            System.setProperty("jnidispatch.path", extractFromResourcePath.getAbsolutePath());
            System.load(extractFromResourcePath.getAbsolutePath());
            jnidispatchPath = extractFromResourcePath.getAbsolutePath();
            LOG.log(DEBUG_JNA_LOAD_LEVEL, "Found jnidispatch at {0}", jnidispatchPath);
            if (!isUnpacked(extractFromResourcePath) || Boolean.getBoolean("jnidispatch.preserve")) {
                return;
            }
            deleteLibrary(extractFromResourcePath);
        } catch (IOException e) {
            throw new UnsatisfiedLinkError(e.getMessage());
        }
    }

    private static Object lookupField(Class<?> cls, String str, Class<?> cls2) {
        try {
            Field field = cls.getField(str);
            field.setAccessible(true);
            return field.get(null);
        } catch (NoSuchFieldException e) {
            return null;
        } catch (Exception e2) {
            throw new IllegalArgumentException(str + " must be a public field of type " + cls2.getName() + " (" + e2 + "): " + cls);
        }
    }

    public static void main(String[] strArr) {
        Package r0 = Native.class.getPackage();
        String specificationTitle = r0 != null ? r0.getSpecificationTitle() : "Java Native Access (JNA)";
        String str = specificationTitle != null ? specificationTitle : "Java Native Access (JNA)";
        String specificationVersion = r0 != null ? r0.getSpecificationVersion() : Version.VERSION;
        if (specificationVersion == null) {
            specificationVersion = Version.VERSION;
        }
        System.out.println(str + " API Version " + specificationVersion);
        String implementationVersion = r0 != null ? r0.getImplementationVersion() : "5.3.1 (package information missing)";
        String str2 = implementationVersion;
        if (implementationVersion == null) {
            str2 = "5.3.1 (package information missing)";
        }
        System.out.println("Version: " + str2);
        System.out.println(" Native: " + getNativeVersion() + " (" + getAPIChecksum() + ")");
        PrintStream printStream = System.out;
        StringBuilder sb = new StringBuilder();
        sb.append(" Prefix: ");
        sb.append(Platform.RESOURCE_PREFIX);
        printStream.println(sb.toString());
    }

    public static native long malloc(long j);

    static void markTemporaryFile(File file) {
        try {
            new File(file.getParentFile(), file.getName() + ".x").createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Class<?> nativeType(Class<?> cls) {
        return NativeMappedConverter.getInstance(cls).nativeType();
    }

    static long open(String str) {
        return open(str, -1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long open(String str, int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void read(Pointer pointer, long j, long j2, byte[] bArr, int i, int i2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void read(Pointer pointer, long j, long j2, char[] cArr, int i, int i2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void read(Pointer pointer, long j, long j2, double[] dArr, int i, int i2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void read(Pointer pointer, long j, long j2, float[] fArr, int i, int i2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void read(Pointer pointer, long j, long j2, int[] iArr, int i, int i2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void read(Pointer pointer, long j, long j2, long[] jArr, int i, int i2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void read(Pointer pointer, long j, long j2, short[] sArr, int i, int i2);

    public static void register(NativeLibrary nativeLibrary) {
        register(findDirectMappedClass(getCallingClass()), nativeLibrary);
    }

    /* JADX WARN: Code restructure failed: missing block: B:44:0x0258, code lost:            if (r0 == 25) goto L55;     */
    /* JADX WARN: Failed to find 'out' block for switch in B:21:0x00da. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:23:0x00f6. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:29:0x01e3  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x03e3  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x03fd A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void register(java.lang.Class<?> r22, com.sun.jna.NativeLibrary r23) {
        /*
            Method dump skipped, instructions count: 1289
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.jna.Native.register(java.lang.Class, com.sun.jna.NativeLibrary):void");
    }

    public static void register(Class<?> cls, String str) {
        register(cls, NativeLibrary.getInstance(str, (Map<String, ?>) Collections.singletonMap(Library.OPTION_CLASSLOADER, cls.getClassLoader())));
    }

    public static void register(String str) {
        register(findDirectMappedClass(getCallingClass()), str);
    }

    private static native long registerMethod(Class<?> cls, String str, String str2, int[] iArr, long[] jArr, long[] jArr2, int i, long j, long j2, Method method, long j3, int i2, boolean z, ToNativeConverter[] toNativeConverterArr, FromNativeConverter fromNativeConverter, String str3);

    public static boolean registered(Class<?> cls) {
        boolean containsKey;
        synchronized (registeredClasses) {
            containsKey = registeredClasses.containsKey(cls);
        }
        return containsKey;
    }

    static void removeTemporaryFiles() throws IOException {
        File[] listFiles = getTempDir().listFiles(new FilenameFilter() { // from class: com.sun.jna.Native.5
            @Override // java.io.FilenameFilter
            public boolean accept(File file, String str) {
                return str.endsWith(".x") && str.startsWith(Native.JNA_TMPLIB_PREFIX);
            }
        });
        int i = 0;
        while (true) {
            int i2 = i;
            if (listFiles == null || i2 >= listFiles.length) {
                return;
            }
            File file = listFiles[i2];
            String name = file.getName();
            File file2 = new File(file.getParentFile(), name.substring(0, name.length() - 2));
            if (!file2.exists() || file2.delete()) {
                file.delete();
            }
            i = i2 + 1;
        }
    }

    static String replace(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder();
        while (true) {
            int indexOf = str3.indexOf(str);
            if (indexOf == -1) {
                sb.append(str3);
                return sb.toString();
            }
            sb.append(str3.substring(0, indexOf));
            sb.append(str2);
            str3 = str3.substring(indexOf + str.length());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void setByte(Pointer pointer, long j, long j2, byte b);

    public static void setCallbackExceptionHandler(Callback.UncaughtExceptionHandler uncaughtExceptionHandler) {
        Callback.UncaughtExceptionHandler uncaughtExceptionHandler2 = uncaughtExceptionHandler;
        if (uncaughtExceptionHandler == null) {
            uncaughtExceptionHandler2 = DEFAULT_HANDLER;
        }
        callbackExceptionHandler = uncaughtExceptionHandler2;
    }

    public static void setCallbackThreadInitializer(Callback callback, CallbackThreadInitializer callbackThreadInitializer) {
        CallbackReference.setCallbackThreadInitializer(callback, callbackThreadInitializer);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void setChar(Pointer pointer, long j, long j2, char c);

    private static native void setDetachState(boolean z, long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void setDouble(Pointer pointer, long j, long j2, double d);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void setFloat(Pointer pointer, long j, long j2, float f);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void setInt(Pointer pointer, long j, long j2, int i);

    public static native void setLastError(int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void setLong(Pointer pointer, long j, long j2, long j3);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void setMemory(Pointer pointer, long j, long j2, long j3, byte b);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void setPointer(Pointer pointer, long j, long j2, long j3);

    public static native synchronized void setProtected(boolean z);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void setShort(Pointer pointer, long j, long j2, short s);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void setWideString(Pointer pointer, long j, long j2, String str);

    private static native int sizeof(int i);

    public static Library synchronizedLibrary(final Library library) {
        Class<?> cls = library.getClass();
        if (!Proxy.isProxyClass(cls)) {
            throw new IllegalArgumentException("Library must be a proxy class");
        }
        InvocationHandler invocationHandler = Proxy.getInvocationHandler(library);
        if (invocationHandler instanceof Library.Handler) {
            final Library.Handler handler = (Library.Handler) invocationHandler;
            return (Library) Proxy.newProxyInstance(cls.getClassLoader(), cls.getInterfaces(), new InvocationHandler() { // from class: com.sun.jna.Native.3
                @Override // java.lang.reflect.InvocationHandler
                public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
                    Object invoke;
                    synchronized (Library.Handler.this.getNativeLibrary()) {
                        invoke = Library.Handler.this.invoke(library, method, objArr);
                    }
                    return invoke;
                }
            });
        }
        throw new IllegalArgumentException("Unrecognized proxy handler: " + invocationHandler);
    }

    public static byte[] toByteArray(String str) {
        return toByteArray(str, getDefaultStringEncoding());
    }

    public static byte[] toByteArray(String str, String str2) {
        return toByteArray(str, getCharset(str2));
    }

    public static byte[] toByteArray(String str, Charset charset) {
        byte[] bytes = getBytes(str, charset);
        byte[] bArr = new byte[bytes.length + 1];
        System.arraycopy(bytes, 0, bArr, 0, bytes.length);
        return bArr;
    }

    public static char[] toCharArray(String str) {
        char[] charArray = str.toCharArray();
        char[] cArr = new char[charArray.length + 1];
        System.arraycopy(charArray, 0, cArr, 0, charArray.length);
        return cArr;
    }

    private static Object toNative(ToNativeConverter toNativeConverter, Object obj) {
        return toNativeConverter.toNative(obj, new ToNativeContext());
    }

    public static String toString(byte[] bArr) {
        return toString(bArr, getDefaultStringEncoding());
    }

    public static String toString(byte[] bArr, String str) {
        return toString(bArr, getCharset(str));
    }

    public static String toString(byte[] bArr, Charset charset) {
        int i;
        int length = bArr.length;
        int i2 = 0;
        while (true) {
            int i3 = i2;
            i = length;
            if (i3 >= length) {
                break;
            }
            if (bArr[i3] == 0) {
                i = i3;
                break;
            }
            i2 = i3 + 1;
        }
        return i == 0 ? "" : new String(bArr, 0, i, charset);
    }

    public static String toString(char[] cArr) {
        int i;
        int length = cArr.length;
        int i2 = 0;
        while (true) {
            int i3 = i2;
            i = length;
            if (i3 >= length) {
                break;
            }
            if (cArr[i3] == 0) {
                i = i3;
                break;
            }
            i2 = i3 + 1;
        }
        return i == 0 ? "" : new String(cArr, 0, i);
    }

    public static List<String> toStringList(char[] cArr) {
        return toStringList(cArr, 0, cArr.length);
    }

    public static List<String> toStringList(char[] cArr, int i, int i2) {
        ArrayList arrayList = new ArrayList();
        int i3 = i2 + i;
        int i4 = i;
        while (i < i3) {
            if (cArr[i] == 0) {
                if (i4 == i) {
                    return arrayList;
                }
                arrayList.add(new String(cArr, i4, i - i4));
                i4 = i + 1;
            }
            i++;
        }
        if (i4 < i3) {
            arrayList.add(new String(cArr, i4, i3 - i4));
        }
        return arrayList;
    }

    public static void unregister() {
        unregister(findDirectMappedClass(getCallingClass()));
    }

    public static void unregister(Class<?> cls) {
        synchronized (registeredClasses) {
            long[] jArr = registeredClasses.get(cls);
            if (jArr != null) {
                unregister(cls, jArr);
                registeredClasses.remove(cls);
                registeredLibraries.remove(cls);
            }
        }
    }

    private static native void unregister(Class<?> cls, long[] jArr);

    private static void unregisterAll() {
        synchronized (registeredClasses) {
            for (Map.Entry<Class<?>, long[]> entry : registeredClasses.entrySet()) {
                unregister(entry.getKey(), entry.getValue());
            }
            registeredClasses.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void write(Pointer pointer, long j, long j2, byte[] bArr, int i, int i2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void write(Pointer pointer, long j, long j2, char[] cArr, int i, int i2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void write(Pointer pointer, long j, long j2, double[] dArr, int i, int i2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void write(Pointer pointer, long j, long j2, float[] fArr, int i, int i2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void write(Pointer pointer, long j, long j2, int[] iArr, int i, int i2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void write(Pointer pointer, long j, long j2, long[] jArr, int i, int i2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void write(Pointer pointer, long j, long j2, short[] sArr, int i, int i2);
}
