package com.sun.jna;

import com.sun.jna.Structure;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Map;

/* loaded from: classes08-dex2jar.jar:com/sun/jna/Function.class */
public class Function extends Pointer {
    public static final int ALT_CONVENTION = 63;
    public static final int C_CONVENTION = 0;
    private static final int MASK_CC = 63;
    public static final int MAX_NARGS = 256;
    static final String OPTION_INVOKING_METHOD = "invoking-method";
    public static final int THROW_LAST_ERROR = 64;
    public static final int USE_VARARGS = 384;
    final int callFlags;
    final String encoding;
    private final String functionName;
    private NativeLibrary library;
    final Map<String, ?> options;
    static final Integer INTEGER_TRUE = -1;
    static final Integer INTEGER_FALSE = 0;
    private static final VarArgsChecker IS_VARARGS = VarArgsChecker.create();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes08-dex2jar.jar:com/sun/jna/Function$NativeMappedArray.class */
    public static class NativeMappedArray extends Memory implements PostCallRead {
        private final NativeMapped[] original;

        public NativeMappedArray(NativeMapped[] nativeMappedArr) {
            super(Native.getNativeSize(nativeMappedArr.getClass(), nativeMappedArr));
            this.original = nativeMappedArr;
            NativeMapped[] nativeMappedArr2 = this.original;
            setValue(0L, nativeMappedArr2, nativeMappedArr2.getClass());
        }

        @Override // com.sun.jna.Function.PostCallRead
        public void read() {
            getValue(0L, this.original.getClass(), this.original);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes08-dex2jar.jar:com/sun/jna/Function$PointerArray.class */
    public static class PointerArray extends Memory implements PostCallRead {
        private final Pointer[] original;

        public PointerArray(Pointer[] pointerArr) {
            super(Native.POINTER_SIZE * (pointerArr.length + 1));
            this.original = pointerArr;
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= pointerArr.length) {
                    setPointer(Native.POINTER_SIZE * pointerArr.length, null);
                    return;
                } else {
                    setPointer(Native.POINTER_SIZE * i2, pointerArr[i2]);
                    i = i2 + 1;
                }
            }
        }

        @Override // com.sun.jna.Function.PostCallRead
        public void read() {
            Pointer[] pointerArr = this.original;
            read(0L, pointerArr, 0, pointerArr.length);
        }
    }

    /* loaded from: classes08-dex2jar.jar:com/sun/jna/Function$PostCallRead.class */
    public interface PostCallRead {
        void read();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Function(NativeLibrary nativeLibrary, String str, int i, String str2) {
        checkCallingConvention(i & 63);
        if (str == null) {
            throw new NullPointerException("Function name must not be null");
        }
        this.library = nativeLibrary;
        this.functionName = str;
        this.callFlags = i;
        this.options = nativeLibrary.options;
        this.encoding = str2 == null ? Native.getDefaultStringEncoding() : str2;
        try {
            this.peer = nativeLibrary.getSymbolAddress(str);
        } catch (UnsatisfiedLinkError e) {
            throw new UnsatisfiedLinkError("Error looking up function '" + str + "': " + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Function(Pointer pointer, int i, String str) {
        checkCallingConvention(i & 63);
        if (pointer == null || pointer.peer == 0) {
            throw new NullPointerException("Function address may not be null");
        }
        this.functionName = pointer.toString();
        this.callFlags = i;
        this.peer = pointer.peer;
        this.options = Collections.EMPTY_MAP;
        this.encoding = str == null ? Native.getDefaultStringEncoding() : str;
    }

    private void checkCallingConvention(int i) throws IllegalArgumentException {
        if ((i & 63) == i) {
            return;
        }
        throw new IllegalArgumentException("Unrecognized calling convention: " + i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object[] concatenateVarArgs(Object[] objArr) {
        Object[] objArr2 = objArr;
        if (objArr != null) {
            objArr2 = objArr;
            if (objArr.length > 0) {
                Object obj = objArr[objArr.length - 1];
                Class<?> cls = obj != null ? obj.getClass() : null;
                objArr2 = objArr;
                if (cls != null) {
                    objArr2 = objArr;
                    if (cls.isArray()) {
                        Object[] objArr3 = (Object[]) obj;
                        int i = 0;
                        while (true) {
                            int i2 = i;
                            if (i2 >= objArr3.length) {
                                break;
                            }
                            if (objArr3[i2] instanceof Float) {
                                objArr3[i2] = Double.valueOf(((Float) objArr3[i2]).floatValue());
                            }
                            i = i2 + 1;
                        }
                        objArr2 = new Object[objArr.length + objArr3.length];
                        System.arraycopy(objArr, 0, objArr2, 0, objArr.length - 1);
                        System.arraycopy(objArr3, 0, objArr2, objArr.length - 1, objArr3.length);
                        objArr2[objArr2.length - 1] = null;
                    }
                }
            }
        }
        return objArr2;
    }

    private Object convertArgument(Object[] objArr, int i, Method method, TypeMapper typeMapper, boolean z, Class<?> cls) {
        Object obj = objArr[i];
        Object obj2 = obj;
        if (obj != null) {
            Class<?> cls2 = obj.getClass();
            ToNativeConverter nativeMappedConverter = NativeMapped.class.isAssignableFrom(cls2) ? NativeMappedConverter.getInstance(cls2) : typeMapper != null ? typeMapper.getToNativeConverter(cls2) : null;
            obj2 = obj;
            if (nativeMappedConverter != null) {
                obj2 = nativeMappedConverter.toNative(obj, method != null ? new MethodParameterContext(this, objArr, i, method) : new FunctionParameterContext(this, objArr, i));
            }
        }
        if (obj2 != null && !isPrimitiveArray(obj2.getClass())) {
            Class<?> cls3 = obj2.getClass();
            if (obj2 instanceof Structure) {
                Structure structure = (Structure) obj2;
                structure.autoWrite();
                if (structure instanceof Structure.ByValue) {
                    Class<?> cls4 = structure.getClass();
                    Class<?> cls5 = cls4;
                    if (method != null) {
                        Class<?>[] parameterTypes = method.getParameterTypes();
                        if (!IS_VARARGS.isVarArgs(method)) {
                            cls5 = parameterTypes[i];
                        } else if (i < parameterTypes.length - 1) {
                            cls5 = parameterTypes[i];
                        } else {
                            Class<?> componentType = parameterTypes[parameterTypes.length - 1].getComponentType();
                            cls5 = cls4;
                            if (componentType != Object.class) {
                                cls5 = componentType;
                            }
                        }
                    }
                    if (Structure.ByValue.class.isAssignableFrom(cls5)) {
                        return structure;
                    }
                }
                return structure.getPointer();
            }
            if (obj2 instanceof Callback) {
                return CallbackReference.getFunctionPointer((Callback) obj2);
            }
            if (obj2 instanceof String) {
                return new NativeString((String) obj2, false).getPointer();
            }
            if (obj2 instanceof WString) {
                return new NativeString(obj2.toString(), true).getPointer();
            }
            if (obj2 instanceof Boolean) {
                return Boolean.TRUE.equals(obj2) ? INTEGER_TRUE : INTEGER_FALSE;
            }
            if (String[].class == cls3) {
                return new StringArray((String[]) obj2, this.encoding);
            }
            if (WString[].class == cls3) {
                return new StringArray((WString[]) obj2);
            }
            if (Pointer[].class == cls3) {
                return new PointerArray((Pointer[]) obj2);
            }
            if (NativeMapped[].class.isAssignableFrom(cls3)) {
                return new NativeMappedArray((NativeMapped[]) obj2);
            }
            if (!Structure[].class.isAssignableFrom(cls3)) {
                if (cls3.isArray()) {
                    throw new IllegalArgumentException("Unsupported array argument type: " + cls3.getComponentType());
                }
                if (!z && !Native.isSupportedNativeType(obj2.getClass())) {
                    throw new IllegalArgumentException("Unsupported argument type " + obj2.getClass().getName() + " at parameter " + i + " of function " + getName());
                }
                return obj2;
            }
            Structure[] structureArr = (Structure[]) obj2;
            Class<?> componentType2 = cls3.getComponentType();
            boolean isAssignableFrom = Structure.ByReference.class.isAssignableFrom(componentType2);
            if (cls != null && !Structure.ByReference[].class.isAssignableFrom(cls)) {
                if (!isAssignableFrom) {
                    int i2 = 0;
                    while (true) {
                        int i3 = i2;
                        if (i3 >= structureArr.length) {
                            break;
                        }
                        if (structureArr[i3] instanceof Structure.ByReference) {
                            throw new IllegalArgumentException("Function " + getName() + " declared Structure[] at parameter " + i + " but element " + i3 + " is of Structure.ByReference type");
                        }
                        i2 = i3 + 1;
                    }
                } else {
                    throw new IllegalArgumentException("Function " + getName() + " declared Structure[] at parameter " + i + " but array of " + componentType2 + " was passed");
                }
            }
            if (!isAssignableFrom) {
                if (structureArr.length == 0) {
                    throw new IllegalArgumentException("Structure array must have non-zero length");
                }
                if (structureArr[0] == null) {
                    Structure.newInstance(componentType2).toArray(structureArr);
                    return structureArr[0].getPointer();
                }
                Structure.autoWrite(structureArr);
                return structureArr[0].getPointer();
            }
            Structure.autoWrite(structureArr);
            Pointer[] pointerArr = new Pointer[structureArr.length + 1];
            int i4 = 0;
            while (true) {
                int i5 = i4;
                if (i5 >= structureArr.length) {
                    return new PointerArray(pointerArr);
                }
                pointerArr[i5] = structureArr[i5] != null ? structureArr[i5].getPointer() : null;
                i4 = i5 + 1;
            }
        }
        return obj2;
    }

    static int fixedArgs(Method method) {
        return IS_VARARGS.fixedArgs(method);
    }

    public static Function getFunction(Pointer pointer) {
        return getFunction(pointer, 0, (String) null);
    }

    public static Function getFunction(Pointer pointer, int i) {
        return getFunction(pointer, i, (String) null);
    }

    public static Function getFunction(Pointer pointer, int i, String str) {
        return new Function(pointer, i, str);
    }

    public static Function getFunction(String str, String str2) {
        return NativeLibrary.getInstance(str).getFunction(str2);
    }

    public static Function getFunction(String str, String str2, int i) {
        return NativeLibrary.getInstance(str).getFunction(str2, i, null);
    }

    public static Function getFunction(String str, String str2, int i, String str3) {
        return NativeLibrary.getInstance(str).getFunction(str2, i, str3);
    }

    private Pointer invokePointer(int i, Object[] objArr) {
        long invokePointer = Native.invokePointer(this, this.peer, i, objArr);
        if (invokePointer == 0) {
            return null;
        }
        return new Pointer(invokePointer);
    }

    private String invokeString(int i, Object[] objArr, boolean z) {
        Pointer invokePointer = invokePointer(i, objArr);
        if (invokePointer != null) {
            return z ? invokePointer.getWideString(0L) : invokePointer.getString(0L, this.encoding);
        }
        return null;
    }

    private boolean isPrimitiveArray(Class<?> cls) {
        return cls.isArray() && cls.getComponentType().isPrimitive();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isVarArgs(Method method) {
        return IS_VARARGS.isVarArgs(method);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Boolean valueOf(boolean z) {
        return z ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override // com.sun.jna.Pointer
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        Function function = (Function) obj;
        return function.callFlags == this.callFlags && function.options.equals(this.options) && function.peer == this.peer;
    }

    public int getCallingConvention() {
        return this.callFlags & 63;
    }

    public String getName() {
        return this.functionName;
    }

    @Override // com.sun.jna.Pointer
    public int hashCode() {
        return this.callFlags + this.options.hashCode() + super.hashCode();
    }

    public Object invoke(Class<?> cls, Object[] objArr) {
        return invoke(cls, objArr, this.options);
    }

    public Object invoke(Class<?> cls, Object[] objArr, Map<String, ?> map) {
        Method method = (Method) map.get(OPTION_INVOKING_METHOD);
        return invoke(method, method != null ? method.getParameterTypes() : null, cls, objArr, map);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    public Object invoke(Method method, Class<?>[] clsArr, Class<?> cls, Object[] objArr, Map<String, ?> map) {
        Class<?> cls2;
        FromNativeConverter fromNativeConverter;
        Object[] objArr2 = new Object[0];
        if (objArr != null) {
            if (objArr.length > 256) {
                throw new UnsupportedOperationException("Maximum argument count is 256");
            }
            objArr2 = new Object[objArr.length];
            System.arraycopy(objArr, 0, objArr2, 0, objArr2.length);
        }
        TypeMapper typeMapper = (TypeMapper) map.get(Library.OPTION_TYPE_MAPPER);
        boolean equals = Boolean.TRUE.equals(map.get(Library.OPTION_ALLOW_OBJECTS));
        boolean isVarArgs = (objArr2.length <= 0 || method == null) ? false : isVarArgs(method);
        int fixedArgs = (objArr2.length <= 0 || method == null) ? 0 : fixedArgs(method);
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= objArr2.length) {
                break;
            }
            objArr2[i2] = convertArgument(objArr2, i2, method, typeMapper, equals, method != null ? (!isVarArgs || i2 < clsArr.length - 1) ? clsArr[i2] : clsArr[clsArr.length - 1].getComponentType() : null);
            i = i2 + 1;
        }
        if (NativeMapped.class.isAssignableFrom(cls)) {
            NativeMappedConverter nativeMappedConverter = NativeMappedConverter.getInstance(cls);
            cls2 = nativeMappedConverter.nativeType();
            fromNativeConverter = nativeMappedConverter;
        } else {
            FromNativeConverter fromNativeConverter2 = null;
            if (typeMapper != null) {
                FromNativeConverter fromNativeConverter3 = typeMapper.getFromNativeConverter(cls);
                fromNativeConverter2 = fromNativeConverter3;
                if (fromNativeConverter3 != null) {
                    cls2 = fromNativeConverter3.nativeType();
                    fromNativeConverter = fromNativeConverter3;
                }
            }
            cls2 = cls;
            fromNativeConverter = fromNativeConverter2;
        }
        Object invoke = invoke(objArr2, cls2, equals, fixedArgs);
        Object obj = invoke;
        if (fromNativeConverter != false) {
            obj = fromNativeConverter.fromNative(invoke, method != null ? new MethodResultContext(cls, this, objArr, method) : new FunctionResultContext(cls, this, objArr));
        }
        if (objArr != null) {
            int i3 = 0;
            while (true) {
                int i4 = i3;
                if (i4 >= objArr.length) {
                    break;
                }
                Object obj2 = objArr[i4];
                if (obj2 != null) {
                    if (obj2 instanceof Structure) {
                        if (!(obj2 instanceof Structure.ByValue)) {
                            ((Structure) obj2).autoRead();
                        }
                    } else if (objArr2[i4] instanceof PostCallRead) {
                        ((PostCallRead) objArr2[i4]).read();
                        if (objArr2[i4] instanceof PointerArray) {
                            PointerArray pointerArray = (PointerArray) objArr2[i4];
                            if (Structure.ByReference[].class.isAssignableFrom(obj2.getClass())) {
                                Class<?> componentType = obj2.getClass().getComponentType();
                                Structure[] structureArr = (Structure[]) obj2;
                                int i5 = 0;
                                while (true) {
                                    int i6 = i5;
                                    if (i6 < structureArr.length) {
                                        structureArr[i6] = Structure.updateStructureByReference(componentType, structureArr[i6], pointerArray.getPointer(Native.POINTER_SIZE * i6));
                                        i5 = i6 + 1;
                                    }
                                }
                            }
                        }
                    } else if (Structure[].class.isAssignableFrom(obj2.getClass())) {
                        Structure.autoRead((Structure[]) obj2);
                    }
                }
                i3 = i4 + 1;
            }
        }
        return obj;
    }

    Object invoke(Object[] objArr, Class<?> cls, boolean z) {
        return invoke(objArr, cls, z, 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v112, types: [com.sun.jna.WString[]] */
    /* JADX WARN: Type inference failed for: r0v82, types: [java.lang.Object] */
    Object invoke(Object[] objArr, Class<?> cls, boolean z, int i) {
        Pointer pointer;
        int i2 = this.callFlags | ((i & 3) << 7);
        if (cls == null || cls == Void.TYPE || cls == Void.class) {
            Native.invokeVoid(this, this.peer, i2, objArr);
            pointer = null;
        } else {
            int i3 = 0;
            if (cls == Boolean.TYPE || cls == Boolean.class) {
                return valueOf(Native.invokeInt(this, this.peer, i2, objArr) != 0);
            }
            if (cls == Byte.TYPE || cls == Byte.class) {
                return Byte.valueOf((byte) Native.invokeInt(this, this.peer, i2, objArr));
            }
            if (cls == Short.TYPE || cls == Short.class) {
                return Short.valueOf((short) Native.invokeInt(this, this.peer, i2, objArr));
            }
            if (cls == Character.TYPE || cls == Character.class) {
                return Character.valueOf((char) Native.invokeInt(this, this.peer, i2, objArr));
            }
            if (cls == Integer.TYPE || cls == Integer.class) {
                return Integer.valueOf(Native.invokeInt(this, this.peer, i2, objArr));
            }
            if (cls == Long.TYPE || cls == Long.class) {
                return Long.valueOf(Native.invokeLong(this, this.peer, i2, objArr));
            }
            if (cls == Float.TYPE || cls == Float.class) {
                return Float.valueOf(Native.invokeFloat(this, this.peer, i2, objArr));
            }
            if (cls == Double.TYPE || cls == Double.class) {
                return Double.valueOf(Native.invokeDouble(this, this.peer, i2, objArr));
            }
            if (cls == String.class) {
                return invokeString(i2, objArr, false);
            }
            if (cls == WString.class) {
                String invokeString = invokeString(i2, objArr, true);
                pointer = null;
                if (invokeString != null) {
                    return new WString(invokeString);
                }
            } else {
                if (Pointer.class.isAssignableFrom(cls)) {
                    return invokePointer(i2, objArr);
                }
                if (Structure.class.isAssignableFrom(cls)) {
                    if (Structure.ByValue.class.isAssignableFrom(cls)) {
                        Structure invokeStructure = Native.invokeStructure(this, this.peer, i2, objArr, Structure.newInstance(cls));
                        invokeStructure.autoRead();
                        return invokeStructure;
                    }
                    Pointer invokePointer = invokePointer(i2, objArr);
                    pointer = invokePointer;
                    if (invokePointer != null) {
                        Structure newInstance = Structure.newInstance((Class<Structure>) cls, invokePointer);
                        newInstance.conditionalAutoRead();
                        return newInstance;
                    }
                } else if (Callback.class.isAssignableFrom(cls)) {
                    Pointer invokePointer2 = invokePointer(i2, objArr);
                    pointer = invokePointer2;
                    if (invokePointer2 != null) {
                        return CallbackReference.getCallback(cls, invokePointer2);
                    }
                } else if (cls == String[].class) {
                    Pointer invokePointer3 = invokePointer(i2, objArr);
                    pointer = null;
                    if (invokePointer3 != null) {
                        return invokePointer3.getStringArray(0L, this.encoding);
                    }
                } else if (cls == WString[].class) {
                    Pointer invokePointer4 = invokePointer(i2, objArr);
                    pointer = null;
                    if (invokePointer4 != null) {
                        String[] wideStringArray = invokePointer4.getWideStringArray(0L);
                        ?? r0 = new WString[wideStringArray.length];
                        while (true) {
                            pointer = r0;
                            if (i3 >= wideStringArray.length) {
                                break;
                            }
                            r0[i3] = new WString(wideStringArray[i3]);
                            i3++;
                        }
                    }
                } else if (cls == Pointer[].class) {
                    Pointer invokePointer5 = invokePointer(i2, objArr);
                    pointer = null;
                    if (invokePointer5 != null) {
                        return invokePointer5.getPointerArray(0L);
                    }
                } else {
                    if (!z) {
                        throw new IllegalArgumentException("Unsupported return type " + cls + " in function " + getName());
                    }
                    ?? invokeObject = Native.invokeObject(this, this.peer, i2, objArr);
                    pointer = invokeObject;
                    if (invokeObject != 0) {
                        if (cls.isAssignableFrom(invokeObject.getClass())) {
                            return invokeObject;
                        }
                        throw new ClassCastException("Return type " + cls + " does not match result " + invokeObject.getClass());
                    }
                }
            }
        }
        return pointer;
    }

    public void invoke(Object[] objArr) {
        invoke(Void.class, objArr);
    }

    public double invokeDouble(Object[] objArr) {
        return ((Double) invoke(Double.class, objArr)).doubleValue();
    }

    public float invokeFloat(Object[] objArr) {
        return ((Float) invoke(Float.class, objArr)).floatValue();
    }

    public int invokeInt(Object[] objArr) {
        return ((Integer) invoke(Integer.class, objArr)).intValue();
    }

    public long invokeLong(Object[] objArr) {
        return ((Long) invoke(Long.class, objArr)).longValue();
    }

    public Object invokeObject(Object[] objArr) {
        return invoke(Object.class, objArr);
    }

    public Pointer invokePointer(Object[] objArr) {
        return (Pointer) invoke(Pointer.class, objArr);
    }

    public String invokeString(Object[] objArr, boolean z) {
        Object invoke = invoke(z ? WString.class : String.class, objArr);
        if (invoke != null) {
            return invoke.toString();
        }
        return null;
    }

    public void invokeVoid(Object[] objArr) {
        invoke(Void.class, objArr);
    }

    @Override // com.sun.jna.Pointer
    public String toString() {
        if (this.library == null) {
            return "native function@0x" + Long.toHexString(this.peer);
        }
        return "native function " + this.functionName + "(" + this.library.getName() + ")@0x" + Long.toHexString(this.peer);
    }
}
