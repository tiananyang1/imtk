package com.sun.jna;

import com.sun.jna.Library;
import com.sun.jna.Structure;
import com.sun.jna.win32.DLLCallback;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

/* loaded from: classes08-dex2jar.jar:com/sun/jna/CallbackReference.class */
public class CallbackReference extends WeakReference<Callback> {
    private static final Method PROXY_CALLBACK_METHOD;
    private static final Map<Callback, CallbackThreadInitializer> initializers;
    int callingConvention;
    Pointer cbstruct;
    Method method;
    CallbackProxy proxy;
    Pointer trampoline;
    static final Map<Callback, CallbackReference> callbackMap = new WeakHashMap();
    static final Map<Callback, CallbackReference> directCallbackMap = new WeakHashMap();
    static final Map<Pointer, Reference<Callback>> pointerCallbackMap = new WeakHashMap();
    static final Map<Object, Object> allocations = new WeakHashMap();
    private static final Map<CallbackReference, Reference<CallbackReference>> allocatedMemory = Collections.synchronizedMap(new WeakHashMap());

    /* loaded from: classes08-dex2jar.jar:com/sun/jna/CallbackReference$AttachOptions.class */
    static class AttachOptions extends Structure {
        public static final List<String> FIELDS = createFieldsOrder("daemon", "detach", "name");
        public boolean daemon;
        public boolean detach;
        public String name;

        AttachOptions() {
            setStringEncoding("utf8");
        }

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return FIELDS;
        }
    }

    /* loaded from: classes08-dex2jar.jar:com/sun/jna/CallbackReference$DefaultCallbackProxy.class */
    private class DefaultCallbackProxy implements CallbackProxy {
        private final Method callbackMethod;
        private final String encoding;
        private final FromNativeConverter[] fromNative;
        private ToNativeConverter toNative;

        public DefaultCallbackProxy(Method method, TypeMapper typeMapper, String str) {
            this.callbackMethod = method;
            this.encoding = str;
            Class<?>[] parameterTypes = method.getParameterTypes();
            Class<?> returnType = method.getReturnType();
            this.fromNative = new FromNativeConverter[parameterTypes.length];
            if (NativeMapped.class.isAssignableFrom(returnType)) {
                this.toNative = NativeMappedConverter.getInstance(returnType);
            } else if (typeMapper != null) {
                this.toNative = typeMapper.getToNativeConverter(returnType);
            }
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= this.fromNative.length) {
                    break;
                }
                if (NativeMapped.class.isAssignableFrom(parameterTypes[i2])) {
                    this.fromNative[i2] = new NativeMappedConverter(parameterTypes[i2]);
                } else if (typeMapper != null) {
                    this.fromNative[i2] = typeMapper.getFromNativeConverter(parameterTypes[i2]);
                }
                i = i2 + 1;
            }
            if (method.isAccessible()) {
                return;
            }
            try {
                method.setAccessible(true);
            } catch (SecurityException e) {
                throw new IllegalArgumentException("Callback method is inaccessible, make sure the interface is public: " + method);
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:37:0x00c9, code lost:            if (java.lang.Boolean.class == r9) goto L36;     */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private java.lang.Object convertArgument(java.lang.Object r8, java.lang.Class<?> r9) {
            /*
                Method dump skipped, instructions count: 240
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.sun.jna.CallbackReference.DefaultCallbackProxy.convertArgument(java.lang.Object, java.lang.Class):java.lang.Object");
        }

        private Object convertResult(Object obj) {
            ToNativeConverter toNativeConverter = this.toNative;
            Object obj2 = obj;
            if (toNativeConverter != null) {
                obj2 = toNativeConverter.toNative(obj, new CallbackResultContext(this.callbackMethod));
            }
            if (obj2 == null) {
                return null;
            }
            Class<?> cls = obj2.getClass();
            if (Structure.class.isAssignableFrom(cls)) {
                return Structure.ByValue.class.isAssignableFrom(cls) ? obj2 : ((Structure) obj2).getPointer();
            }
            if (cls == Boolean.TYPE || cls == Boolean.class) {
                return Boolean.TRUE.equals(obj2) ? Function.INTEGER_TRUE : Function.INTEGER_FALSE;
            }
            if (cls == String.class || cls == WString.class) {
                return CallbackReference.getNativeString(obj2, cls == WString.class);
            }
            if (cls == String[].class || cls == WString.class) {
                StringArray stringArray = cls == String[].class ? new StringArray((String[]) obj2, this.encoding) : new StringArray((WString[]) obj2);
                CallbackReference.allocations.put(obj2, stringArray);
                return stringArray;
            }
            Object obj3 = obj2;
            if (Callback.class.isAssignableFrom(cls)) {
                obj3 = CallbackReference.getFunctionPointer((Callback) obj2);
            }
            return obj3;
        }

        private Object invokeCallback(Object[] objArr) {
            Class<?>[] parameterTypes = this.callbackMethod.getParameterTypes();
            Object[] objArr2 = new Object[objArr.length];
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= objArr.length) {
                    break;
                }
                Class<?> cls = parameterTypes[i2];
                Object obj = objArr[i2];
                if (this.fromNative[i2] != null) {
                    objArr2[i2] = this.fromNative[i2].fromNative(obj, new CallbackParameterContext(cls, this.callbackMethod, objArr, i2));
                } else {
                    objArr2[i2] = convertArgument(obj, cls);
                }
                i = i2 + 1;
            }
            Callback callback = getCallback();
            int i3 = 0;
            Object obj2 = null;
            if (callback != null) {
                try {
                    obj2 = convertResult(this.callbackMethod.invoke(callback, objArr2));
                    i3 = 0;
                } catch (IllegalAccessException e) {
                    Native.getCallbackExceptionHandler().uncaughtException(callback, e);
                    i3 = 0;
                    obj2 = null;
                } catch (IllegalArgumentException e2) {
                    Native.getCallbackExceptionHandler().uncaughtException(callback, e2);
                    obj2 = null;
                    i3 = 0;
                } catch (InvocationTargetException e3) {
                    Native.getCallbackExceptionHandler().uncaughtException(callback, e3.getTargetException());
                    i3 = 0;
                    obj2 = null;
                }
            }
            while (i3 < objArr2.length) {
                if ((objArr2[i3] instanceof Structure) && !(objArr2[i3] instanceof Structure.ByValue)) {
                    ((Structure) objArr2[i3]).autoWrite();
                }
                i3++;
            }
            return obj2;
        }

        @Override // com.sun.jna.CallbackProxy
        public Object callback(Object[] objArr) {
            try {
                return invokeCallback(objArr);
            } catch (Throwable th) {
                Native.getCallbackExceptionHandler().uncaughtException(getCallback(), th);
                return null;
            }
        }

        public Callback getCallback() {
            return CallbackReference.this.getCallback();
        }

        @Override // com.sun.jna.CallbackProxy
        public Class<?>[] getParameterTypes() {
            return this.callbackMethod.getParameterTypes();
        }

        @Override // com.sun.jna.CallbackProxy
        public Class<?> getReturnType() {
            return this.callbackMethod.getReturnType();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes08-dex2jar.jar:com/sun/jna/CallbackReference$NativeFunctionHandler.class */
    public static class NativeFunctionHandler implements InvocationHandler {
        private final Function function;
        private final Map<String, ?> options;

        public NativeFunctionHandler(Pointer pointer, int i, Map<String, ?> map) {
            this.options = map;
            this.function = new Function(pointer, i, (String) map.get(Library.OPTION_STRING_ENCODING));
        }

        public Pointer getPointer() {
            return this.function;
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            if (Library.Handler.OBJECT_TOSTRING.equals(method)) {
                return ("Proxy interface to " + this.function) + " (" + CallbackReference.findCallbackClass(((Method) this.options.get("invoking-method")).getDeclaringClass()).getName() + ")";
            }
            if (Library.Handler.OBJECT_HASHCODE.equals(method)) {
                return Integer.valueOf(hashCode());
            }
            if (!Library.Handler.OBJECT_EQUALS.equals(method)) {
                Object[] objArr2 = objArr;
                if (Function.isVarArgs(method)) {
                    objArr2 = Function.concatenateVarArgs(objArr);
                }
                return this.function.invoke(method.getReturnType(), objArr2, this.options);
            }
            boolean z = false;
            Object obj2 = objArr[0];
            if (obj2 == null || !Proxy.isProxyClass(obj2.getClass())) {
                return Boolean.FALSE;
            }
            if (Proxy.getInvocationHandler(obj2) == this) {
                z = true;
            }
            return Function.valueOf(z);
        }
    }

    static {
        try {
            PROXY_CALLBACK_METHOD = CallbackProxy.class.getMethod(Callback.METHOD_NAME, Object[].class);
            initializers = new WeakHashMap();
        } catch (Exception e) {
            throw new Error("Error looking up CallbackProxy.callback() method");
        }
    }

    private CallbackReference(Callback callback, int i, boolean z) {
        super(callback);
        long createNativeCallback;
        int i2;
        boolean z2;
        TypeMapper typeMapper = Native.getTypeMapper(callback.getClass());
        this.callingConvention = i;
        boolean isPPC = Platform.isPPC();
        boolean z3 = z;
        if (z) {
            Method callbackMethod = getCallbackMethod(callback);
            Class<?>[] parameterTypes = callbackMethod.getParameterTypes();
            while (true) {
                int i3 = i2;
                z2 = z;
                if (i3 >= parameterTypes.length) {
                    break;
                } else {
                    i2 = (!(isPPC && (parameterTypes[i3] == Float.TYPE || parameterTypes[i3] == Double.TYPE)) && (typeMapper == null || typeMapper.getFromNativeConverter(parameterTypes[i3]) == null)) ? i3 + 1 : 0;
                }
            }
            z3 = z2;
            if (typeMapper != null) {
                z3 = z2;
                if (typeMapper.getToNativeConverter(callbackMethod.getReturnType()) != null) {
                    z3 = false;
                }
            }
        }
        String stringEncoding = Native.getStringEncoding(callback.getClass());
        if (z3) {
            this.method = getCallbackMethod(callback);
            createNativeCallback = Native.createNativeCallback(callback, this.method, this.method.getParameterTypes(), this.method.getReturnType(), i, callback instanceof DLLCallback ? 3 : 1, stringEncoding);
        } else {
            if (callback instanceof CallbackProxy) {
                this.proxy = (CallbackProxy) callback;
            } else {
                this.proxy = new DefaultCallbackProxy(getCallbackMethod(callback), typeMapper, stringEncoding);
            }
            Class<?>[] parameterTypes2 = this.proxy.getParameterTypes();
            Class<?> returnType = this.proxy.getReturnType();
            Class<?> cls = returnType;
            if (typeMapper != null) {
                int i4 = 0;
                while (true) {
                    int i5 = i4;
                    if (i5 >= parameterTypes2.length) {
                        break;
                    }
                    FromNativeConverter fromNativeConverter = typeMapper.getFromNativeConverter(parameterTypes2[i5]);
                    if (fromNativeConverter != null) {
                        parameterTypes2[i5] = fromNativeConverter.nativeType();
                    }
                    i4 = i5 + 1;
                }
                ToNativeConverter toNativeConverter = typeMapper.getToNativeConverter(returnType);
                cls = returnType;
                if (toNativeConverter != null) {
                    cls = toNativeConverter.nativeType();
                }
            }
            int i6 = 0;
            while (true) {
                int i7 = i6;
                if (i7 < parameterTypes2.length) {
                    parameterTypes2[i7] = getNativeType(parameterTypes2[i7]);
                    if (!isAllowableNativeType(parameterTypes2[i7])) {
                        throw new IllegalArgumentException("Callback argument " + parameterTypes2[i7] + " requires custom type conversion");
                    }
                    i6 = i7 + 1;
                } else {
                    Class<?> nativeType = getNativeType(cls);
                    if (!isAllowableNativeType(nativeType)) {
                        throw new IllegalArgumentException("Callback return type " + nativeType + " requires custom type conversion");
                    }
                    createNativeCallback = Native.createNativeCallback(this.proxy, PROXY_CALLBACK_METHOD, parameterTypes2, nativeType, i, callback instanceof DLLCallback ? 2 : 0, stringEncoding);
                }
            }
        }
        this.cbstruct = createNativeCallback != 0 ? new Pointer(createNativeCallback) : null;
        allocatedMemory.put(this, new WeakReference(this));
    }

    private static Method checkMethod(Method method) {
        if (method.getParameterTypes().length <= 256) {
            return method;
        }
        throw new UnsupportedOperationException("Method signature exceeds the maximum parameter count: " + method);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void disposeAll() {
        Iterator it = new LinkedList(allocatedMemory.keySet()).iterator();
        while (it.hasNext()) {
            ((CallbackReference) it.next()).dispose();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Class<?> findCallbackClass(Class<?> cls) {
        if (!Callback.class.isAssignableFrom(cls)) {
            throw new IllegalArgumentException(cls.getName() + " is not derived from com.sun.jna.Callback");
        }
        if (cls.isInterface()) {
            return cls;
        }
        Class<?>[] interfaces = cls.getInterfaces();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= interfaces.length) {
                break;
            }
            if (Callback.class.isAssignableFrom(interfaces[i2])) {
                try {
                    getCallbackMethod(interfaces[i2]);
                    return interfaces[i2];
                } catch (IllegalArgumentException e) {
                }
            } else {
                i = i2 + 1;
            }
        }
        Class<?> cls2 = cls;
        if (Callback.class.isAssignableFrom(cls.getSuperclass())) {
            cls2 = findCallbackClass(cls.getSuperclass());
        }
        return cls2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Callback getCallback() {
        return (Callback) get();
    }

    public static Callback getCallback(Class<?> cls, Pointer pointer) {
        return getCallback(cls, pointer, false);
    }

    private static Callback getCallback(Class<?> cls, Pointer pointer, boolean z) {
        if (pointer == null) {
            return null;
        }
        if (!cls.isInterface()) {
            throw new IllegalArgumentException("Callback type must be an interface");
        }
        Map<Callback, CallbackReference> map = z ? directCallbackMap : callbackMap;
        synchronized (pointerCallbackMap) {
            Reference<Callback> reference = pointerCallbackMap.get(pointer);
            if (reference == null) {
                int i = AltCallingConvention.class.isAssignableFrom(cls) ? 63 : 0;
                HashMap hashMap = new HashMap(Native.getLibraryOptions(cls));
                hashMap.put("invoking-method", getCallbackMethod(cls));
                Callback callback = (Callback) Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new NativeFunctionHandler(pointer, i, hashMap));
                map.remove(callback);
                pointerCallbackMap.put(pointer, new WeakReference(callback));
                return callback;
            }
            Callback callback2 = reference.get();
            if (callback2 != null && !cls.isAssignableFrom(callback2.getClass())) {
                throw new IllegalStateException("Pointer " + pointer + " already mapped to " + callback2 + ".\nNative code may be re-using a default function pointer, in which case you may need to use a common Callback class wherever the function pointer is reused.");
            }
            return callback2;
        }
    }

    private static Method getCallbackMethod(Callback callback) {
        return getCallbackMethod(findCallbackClass(callback.getClass()));
    }

    private static Method getCallbackMethod(Class<?> cls) {
        Method[] declaredMethods = cls.getDeclaredMethods();
        Method[] methods = cls.getMethods();
        HashSet hashSet = new HashSet(Arrays.asList(declaredMethods));
        hashSet.retainAll(Arrays.asList(methods));
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            if (Callback.FORBIDDEN_NAMES.contains(((Method) it.next()).getName())) {
                it.remove();
            }
        }
        Method[] methodArr = (Method[]) hashSet.toArray(new Method[0]);
        if (methodArr.length == 1) {
            return checkMethod(methodArr[0]);
        }
        for (Method method : methodArr) {
            if (Callback.METHOD_NAME.equals(method.getName())) {
                return checkMethod(method);
            }
        }
        throw new IllegalArgumentException("Callback must implement a single public method, or one public method named 'callback'");
    }

    public static Pointer getFunctionPointer(Callback callback) {
        return getFunctionPointer(callback, false);
    }

    private static Pointer getFunctionPointer(Callback callback, boolean z) {
        Pointer trampoline;
        if (callback == null) {
            return null;
        }
        Pointer nativeFunctionPointer = getNativeFunctionPointer(callback);
        if (nativeFunctionPointer != null) {
            return nativeFunctionPointer;
        }
        Map<String, Object> libraryOptions = Native.getLibraryOptions(callback.getClass());
        int intValue = callback instanceof AltCallingConvention ? 63 : (libraryOptions == null || !libraryOptions.containsKey(Library.OPTION_CALLING_CONVENTION)) ? 0 : ((Integer) libraryOptions.get(Library.OPTION_CALLING_CONVENTION)).intValue();
        Map<Callback, CallbackReference> map = z ? directCallbackMap : callbackMap;
        synchronized (pointerCallbackMap) {
            CallbackReference callbackReference = map.get(callback);
            CallbackReference callbackReference2 = callbackReference;
            if (callbackReference == null) {
                CallbackReference callbackReference3 = new CallbackReference(callback, intValue, z);
                map.put(callback, callbackReference3);
                pointerCallbackMap.put(callbackReference3.getTrampoline(), new WeakReference(callback));
                callbackReference2 = callbackReference3;
                if (initializers.containsKey(callback)) {
                    callbackReference3.setCallbackOptions(1);
                    callbackReference2 = callbackReference3;
                }
            }
            trampoline = callbackReference2.getTrampoline();
        }
        return trampoline;
    }

    private static Pointer getNativeFunctionPointer(Callback callback) {
        if (!Proxy.isProxyClass(callback.getClass())) {
            return null;
        }
        InvocationHandler invocationHandler = Proxy.getInvocationHandler(callback);
        if (invocationHandler instanceof NativeFunctionHandler) {
            return ((NativeFunctionHandler) invocationHandler).getPointer();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Pointer getNativeString(Object obj, boolean z) {
        if (obj == null) {
            return null;
        }
        NativeString nativeString = new NativeString(obj.toString(), z);
        allocations.put(obj, nativeString);
        return nativeString.getPointer();
    }

    private Class<?> getNativeType(Class<?> cls) {
        if (Structure.class.isAssignableFrom(cls)) {
            Structure.validate(cls);
            if (!Structure.ByValue.class.isAssignableFrom(cls)) {
                return Pointer.class;
            }
        } else {
            if (NativeMapped.class.isAssignableFrom(cls)) {
                return NativeMappedConverter.getInstance(cls).nativeType();
            }
            if (cls == String.class || cls == WString.class || cls == String[].class || cls == WString[].class || Callback.class.isAssignableFrom(cls)) {
                return Pointer.class;
            }
        }
        return cls;
    }

    private static ThreadGroup initializeThread(Callback callback, AttachOptions attachOptions) {
        CallbackThreadInitializer callbackThreadInitializer;
        Callback callback2 = callback;
        if (callback instanceof DefaultCallbackProxy) {
            callback2 = ((DefaultCallbackProxy) callback).getCallback();
        }
        synchronized (initializers) {
            callbackThreadInitializer = initializers.get(callback2);
        }
        ThreadGroup threadGroup = null;
        if (callbackThreadInitializer != null) {
            threadGroup = callbackThreadInitializer.getThreadGroup(callback2);
            attachOptions.name = callbackThreadInitializer.getName(callback2);
            attachOptions.daemon = callbackThreadInitializer.isDaemon(callback2);
            attachOptions.detach = callbackThreadInitializer.detach(callback2);
            attachOptions.write();
        }
        return threadGroup;
    }

    private static boolean isAllowableNativeType(Class<?> cls) {
        if (cls == Void.TYPE || cls == Void.class || cls == Boolean.TYPE || cls == Boolean.class || cls == Byte.TYPE || cls == Byte.class || cls == Short.TYPE || cls == Short.class || cls == Character.TYPE || cls == Character.class || cls == Integer.TYPE || cls == Integer.class || cls == Long.TYPE || cls == Long.class || cls == Float.TYPE || cls == Float.class || cls == Double.TYPE || cls == Double.class) {
            return true;
        }
        return (Structure.ByValue.class.isAssignableFrom(cls) && Structure.class.isAssignableFrom(cls)) || Pointer.class.isAssignableFrom(cls);
    }

    private void setCallbackOptions(int i) {
        this.cbstruct.setInt(Native.POINTER_SIZE, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CallbackThreadInitializer setCallbackThreadInitializer(Callback callback, CallbackThreadInitializer callbackThreadInitializer) {
        synchronized (initializers) {
            if (callbackThreadInitializer != null) {
                return initializers.put(callback, callbackThreadInitializer);
            }
            return initializers.remove(callback);
        }
    }

    protected void dispose() {
        synchronized (this) {
            if (this.cbstruct != null) {
                try {
                    Native.freeNativeCallback(this.cbstruct.peer);
                    this.cbstruct.peer = 0L;
                    this.cbstruct = null;
                    allocatedMemory.remove(this);
                } catch (Throwable th) {
                    this.cbstruct.peer = 0L;
                    this.cbstruct = null;
                    allocatedMemory.remove(this);
                    throw th;
                }
            }
        }
    }

    protected void finalize() {
        dispose();
    }

    public Pointer getTrampoline() {
        if (this.trampoline == null) {
            this.trampoline = this.cbstruct.getPointer(0L);
        }
        return this.trampoline;
    }
}
