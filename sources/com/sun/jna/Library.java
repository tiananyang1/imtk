package com.sun.jna;

import com.sun.jna.internal.ReflectionUtils;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/* loaded from: classes08-dex2jar.jar:com/sun/jna/Library.class */
public interface Library {
    public static final String OPTION_ALLOW_OBJECTS = "allow-objects";
    public static final String OPTION_CALLING_CONVENTION = "calling-convention";
    public static final String OPTION_CLASSLOADER = "classloader";
    public static final String OPTION_FUNCTION_MAPPER = "function-mapper";
    public static final String OPTION_INVOCATION_MAPPER = "invocation-mapper";
    public static final String OPTION_OPEN_FLAGS = "open-flags";
    public static final String OPTION_STRING_ENCODING = "string-encoding";
    public static final String OPTION_STRUCTURE_ALIGNMENT = "structure-alignment";
    public static final String OPTION_TYPE_MAPPER = "type-mapper";

    /* loaded from: classes08-dex2jar.jar:com/sun/jna/Library$Handler.class */
    public static class Handler implements InvocationHandler {
        static final Method OBJECT_EQUALS;
        static final Method OBJECT_HASHCODE;
        static final Method OBJECT_TOSTRING;
        private final Map<Method, FunctionInfo> functions = new WeakHashMap();
        private final Class<?> interfaceClass;
        private final InvocationMapper invocationMapper;
        private final NativeLibrary nativeLibrary;
        private final Map<String, Object> options;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes08-dex2jar.jar:com/sun/jna/Library$Handler$FunctionInfo.class */
        public static final class FunctionInfo {
            final Function function;
            final InvocationHandler handler;
            final boolean isVarArgs;
            final Object methodHandle;
            final Map<String, ?> options;
            final Class<?>[] parameterTypes;

            FunctionInfo(Object obj) {
                this.handler = null;
                this.function = null;
                this.isVarArgs = false;
                this.options = null;
                this.parameterTypes = null;
                this.methodHandle = obj;
            }

            FunctionInfo(InvocationHandler invocationHandler, Function function, Class<?>[] clsArr, boolean z, Map<String, ?> map) {
                this.handler = invocationHandler;
                this.function = function;
                this.isVarArgs = z;
                this.options = map;
                this.parameterTypes = clsArr;
                this.methodHandle = null;
            }
        }

        static {
            try {
                OBJECT_TOSTRING = Object.class.getMethod("toString", new Class[0]);
                OBJECT_HASHCODE = Object.class.getMethod("hashCode", new Class[0]);
                OBJECT_EQUALS = Object.class.getMethod("equals", Object.class);
            } catch (Exception e) {
                throw new Error("Error retrieving Object.toString() method");
            }
        }

        public Handler(String str, Class<?> cls, Map<String, ?> map) {
            if (str != null && "".equals(str.trim())) {
                throw new IllegalArgumentException("Invalid library name \"" + str + "\"");
            }
            if (!cls.isInterface()) {
                throw new IllegalArgumentException(str + " does not implement an interface: " + cls.getName());
            }
            this.interfaceClass = cls;
            this.options = new HashMap(map);
            int i = AltCallingConvention.class.isAssignableFrom(cls) ? 63 : 0;
            if (this.options.get(Library.OPTION_CALLING_CONVENTION) == null) {
                this.options.put(Library.OPTION_CALLING_CONVENTION, Integer.valueOf(i));
            }
            if (this.options.get(Library.OPTION_CLASSLOADER) == null) {
                this.options.put(Library.OPTION_CLASSLOADER, cls.getClassLoader());
            }
            this.nativeLibrary = NativeLibrary.getInstance(str, (Map<String, ?>) this.options);
            this.invocationMapper = (InvocationMapper) this.options.get(Library.OPTION_INVOCATION_MAPPER);
        }

        public Class<?> getInterfaceClass() {
            return this.interfaceClass;
        }

        public String getLibraryName() {
            return this.nativeLibrary.getName();
        }

        public NativeLibrary getNativeLibrary() {
            return this.nativeLibrary;
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            Function function;
            Class<?>[] clsArr;
            HashMap hashMap;
            if (OBJECT_TOSTRING.equals(method)) {
                return "Proxy interface to " + this.nativeLibrary;
            }
            if (OBJECT_HASHCODE.equals(method)) {
                return Integer.valueOf(hashCode());
            }
            if (OBJECT_EQUALS.equals(method)) {
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
            FunctionInfo functionInfo = this.functions.get(method);
            FunctionInfo functionInfo2 = functionInfo;
            if (functionInfo == null) {
                synchronized (this.functions) {
                    FunctionInfo functionInfo3 = this.functions.get(method);
                    functionInfo2 = functionInfo3;
                    if (functionInfo3 == null) {
                        if (ReflectionUtils.isDefault(method)) {
                            functionInfo2 = new FunctionInfo(ReflectionUtils.getMethodHandle(method));
                        } else {
                            boolean isVarArgs = Function.isVarArgs(method);
                            InvocationHandler invocationHandler = this.invocationMapper != null ? this.invocationMapper.getInvocationHandler(this.nativeLibrary, method) : null;
                            if (invocationHandler == null) {
                                function = this.nativeLibrary.getFunction(method.getName(), method);
                                clsArr = method.getParameterTypes();
                                hashMap = new HashMap(this.options);
                                hashMap.put("invoking-method", method);
                            } else {
                                function = null;
                                clsArr = null;
                                hashMap = null;
                            }
                            functionInfo2 = new FunctionInfo(invocationHandler, function, clsArr, isVarArgs, hashMap);
                        }
                        this.functions.put(method, functionInfo2);
                    }
                }
            }
            if (functionInfo2.methodHandle != null) {
                return ReflectionUtils.invokeDefaultMethod(obj, functionInfo2.methodHandle, objArr);
            }
            Object[] objArr2 = objArr;
            if (functionInfo2.isVarArgs) {
                objArr2 = Function.concatenateVarArgs(objArr);
            }
            return functionInfo2.handler != null ? functionInfo2.handler.invoke(obj, method, objArr2) : functionInfo2.function.invoke(method, functionInfo2.parameterTypes, method.getReturnType(), objArr2, functionInfo2.options);
        }
    }
}
