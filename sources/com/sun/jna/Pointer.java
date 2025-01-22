package com.sun.jna;

import com.sun.jna.Structure;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Array;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;

/* loaded from: classes08-dex2jar.jar:com/sun/jna/Pointer.class */
public class Pointer {
    public static final Pointer NULL = null;
    protected long peer;

    /* loaded from: classes08-dex2jar.jar:com/sun/jna/Pointer$Opaque.class */
    private static class Opaque extends Pointer {
        private final String MSG;

        private Opaque(long j) {
            super(j);
            this.MSG = "This pointer is opaque: " + this;
        }

        @Override // com.sun.jna.Pointer
        public void clear(long j) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public String dump(long j, int i) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public byte getByte(long j) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public ByteBuffer getByteBuffer(long j, long j2) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public char getChar(long j) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public double getDouble(long j) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public float getFloat(long j) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public int getInt(long j) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public long getLong(long j) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public Pointer getPointer(long j) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public short getShort(long j) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public String getString(long j, String str) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public String getWideString(long j) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public long indexOf(long j, byte b) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public void read(long j, byte[] bArr, int i, int i2) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public void read(long j, char[] cArr, int i, int i2) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public void read(long j, double[] dArr, int i, int i2) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public void read(long j, float[] fArr, int i, int i2) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public void read(long j, int[] iArr, int i, int i2) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public void read(long j, long[] jArr, int i, int i2) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public void read(long j, Pointer[] pointerArr, int i, int i2) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public void read(long j, short[] sArr, int i, int i2) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public void setByte(long j, byte b) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public void setChar(long j, char c) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public void setDouble(long j, double d) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public void setFloat(long j, float f) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public void setInt(long j, int i) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public void setLong(long j, long j2) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public void setMemory(long j, long j2, byte b) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public void setPointer(long j, Pointer pointer) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public void setShort(long j, short s) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public void setString(long j, String str, String str2) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public void setWideString(long j, String str) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public Pointer share(long j, long j2) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public String toString() {
            return "const@0x" + Long.toHexString(this.peer);
        }

        @Override // com.sun.jna.Pointer
        public void write(long j, byte[] bArr, int i, int i2) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public void write(long j, char[] cArr, int i, int i2) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public void write(long j, double[] dArr, int i, int i2) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public void write(long j, float[] fArr, int i, int i2) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public void write(long j, int[] iArr, int i, int i2) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public void write(long j, long[] jArr, int i, int i2) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public void write(long j, Pointer[] pointerArr, int i, int i2) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override // com.sun.jna.Pointer
        public void write(long j, short[] sArr, int i, int i2) {
            throw new UnsupportedOperationException(this.MSG);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Pointer() {
    }

    public Pointer(long j) {
        this.peer = j;
    }

    public static final Pointer createConstant(int i) {
        return new Opaque(i & (-1));
    }

    public static final Pointer createConstant(long j) {
        return new Opaque(j);
    }

    public static long nativeValue(Pointer pointer) {
        if (pointer == null) {
            return 0L;
        }
        return pointer.peer;
    }

    public static void nativeValue(Pointer pointer, long j) {
        pointer.peer = j;
    }

    private void readArray(long j, Object obj, Class<?> cls) {
        int length = Array.getLength(obj);
        if (cls == Byte.TYPE) {
            read(j, (byte[]) obj, 0, length);
            return;
        }
        if (cls == Short.TYPE) {
            read(j, (short[]) obj, 0, length);
            return;
        }
        if (cls == Character.TYPE) {
            read(j, (char[]) obj, 0, length);
            return;
        }
        if (cls == Integer.TYPE) {
            read(j, (int[]) obj, 0, length);
            return;
        }
        if (cls == Long.TYPE) {
            read(j, (long[]) obj, 0, length);
            return;
        }
        if (cls == Float.TYPE) {
            read(j, (float[]) obj, 0, length);
            return;
        }
        if (cls == Double.TYPE) {
            read(j, (double[]) obj, 0, length);
            return;
        }
        if (Pointer.class.isAssignableFrom(cls)) {
            read(j, (Pointer[]) obj, 0, length);
            return;
        }
        if (!Structure.class.isAssignableFrom(cls)) {
            if (!NativeMapped.class.isAssignableFrom(cls)) {
                throw new IllegalArgumentException("Reading array of " + cls + " from memory not supported");
            }
            NativeMapped[] nativeMappedArr = (NativeMapped[]) obj;
            NativeMappedConverter nativeMappedConverter = NativeMappedConverter.getInstance(cls);
            int nativeSize = Native.getNativeSize(obj.getClass(), obj) / nativeMappedArr.length;
            for (int i = 0; i < nativeMappedArr.length; i++) {
                nativeMappedArr[i] = (NativeMapped) nativeMappedConverter.fromNative(getValue((nativeSize * i) + j, nativeMappedConverter.nativeType(), nativeMappedArr[i]), new FromNativeContext(cls));
            }
            return;
        }
        Structure[] structureArr = (Structure[]) obj;
        if (Structure.ByReference.class.isAssignableFrom(cls)) {
            Pointer[] pointerArray = getPointerArray(j, structureArr.length);
            int i2 = 0;
            while (true) {
                int i3 = i2;
                if (i3 >= structureArr.length) {
                    return;
                }
                structureArr[i3] = Structure.updateStructureByReference(cls, structureArr[i3], pointerArray[i3]);
                i2 = i3 + 1;
            }
        } else {
            Structure structure = structureArr[0];
            if (structure == null) {
                structure = Structure.newInstance((Class<Structure>) cls, share(j));
                structure.conditionalAutoRead();
                structureArr[0] = structure;
            } else {
                structure.useMemory(this, (int) j, true);
                structure.read();
            }
            Structure[] array = structure.toArray(structureArr.length);
            int i4 = 1;
            while (true) {
                int i5 = i4;
                if (i5 >= structureArr.length) {
                    return;
                }
                if (structureArr[i5] == null) {
                    structureArr[i5] = array[i5];
                } else {
                    structureArr[i5].useMemory(this, (int) ((structureArr[i5].size() * i5) + j), true);
                    structureArr[i5].read();
                }
                i4 = i5 + 1;
            }
        }
    }

    private void writeArray(long j, Object obj, Class<?> cls) {
        if (cls == Byte.TYPE) {
            byte[] bArr = (byte[]) obj;
            write(j, bArr, 0, bArr.length);
            return;
        }
        if (cls == Short.TYPE) {
            short[] sArr = (short[]) obj;
            write(j, sArr, 0, sArr.length);
            return;
        }
        if (cls == Character.TYPE) {
            char[] cArr = (char[]) obj;
            write(j, cArr, 0, cArr.length);
            return;
        }
        if (cls == Integer.TYPE) {
            int[] iArr = (int[]) obj;
            write(j, iArr, 0, iArr.length);
            return;
        }
        if (cls == Long.TYPE) {
            long[] jArr = (long[]) obj;
            write(j, jArr, 0, jArr.length);
            return;
        }
        if (cls == Float.TYPE) {
            float[] fArr = (float[]) obj;
            write(j, fArr, 0, fArr.length);
            return;
        }
        if (cls == Double.TYPE) {
            double[] dArr = (double[]) obj;
            write(j, dArr, 0, dArr.length);
            return;
        }
        if (Pointer.class.isAssignableFrom(cls)) {
            Pointer[] pointerArr = (Pointer[]) obj;
            write(j, pointerArr, 0, pointerArr.length);
            return;
        }
        if (!Structure.class.isAssignableFrom(cls)) {
            if (!NativeMapped.class.isAssignableFrom(cls)) {
                throw new IllegalArgumentException("Writing array of " + cls + " to memory not supported");
            }
            NativeMapped[] nativeMappedArr = (NativeMapped[]) obj;
            NativeMappedConverter nativeMappedConverter = NativeMappedConverter.getInstance(cls);
            Class<?> nativeType = nativeMappedConverter.nativeType();
            int nativeSize = Native.getNativeSize(obj.getClass(), obj) / nativeMappedArr.length;
            for (int i = 0; i < nativeMappedArr.length; i++) {
                setValue((i * nativeSize) + j, nativeMappedConverter.toNative(nativeMappedArr[i], new ToNativeContext()), nativeType);
            }
            return;
        }
        Structure[] structureArr = (Structure[]) obj;
        if (Structure.ByReference.class.isAssignableFrom(cls)) {
            Pointer[] pointerArr2 = new Pointer[structureArr.length];
            int i2 = 0;
            while (true) {
                int i3 = i2;
                if (i3 >= structureArr.length) {
                    write(j, pointerArr2, 0, pointerArr2.length);
                    return;
                }
                if (structureArr[i3] == null) {
                    pointerArr2[i3] = null;
                } else {
                    pointerArr2[i3] = structureArr[i3].getPointer();
                    structureArr[i3].write();
                }
                i2 = i3 + 1;
            }
        } else {
            Structure structure = structureArr[0];
            if (structure == null) {
                structure = Structure.newInstance((Class<Structure>) cls, share(j));
                structureArr[0] = structure;
            } else {
                structure.useMemory(this, (int) j, true);
            }
            structure.write();
            Structure[] array = structure.toArray(structureArr.length);
            int i4 = 1;
            while (true) {
                int i5 = i4;
                if (i5 >= structureArr.length) {
                    return;
                }
                if (structureArr[i5] == null) {
                    structureArr[i5] = array[i5];
                } else {
                    structureArr[i5].useMemory(this, (int) ((structureArr[i5].size() * i5) + j), true);
                }
                structureArr[i5].write();
                i4 = i5 + 1;
            }
        }
    }

    public void clear(long j) {
        setMemory(0L, j, (byte) 0);
    }

    public String dump(long j, int i) {
        StringWriter stringWriter = new StringWriter(13 + (i * 2) + ((i / 4) * 4));
        PrintWriter printWriter = new PrintWriter(stringWriter);
        printWriter.println("memory dump");
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 >= i) {
                break;
            }
            byte b = getByte(i3 + j);
            int i4 = i3 % 4;
            if (i4 == 0) {
                printWriter.print("[");
            }
            if (b >= 0 && b < 16) {
                printWriter.print("0");
            }
            printWriter.print(Integer.toHexString(b & 255));
            if (i4 == 3 && i3 < i - 1) {
                printWriter.println("]");
            }
            i2 = i3 + 1;
        }
        if (stringWriter.getBuffer().charAt(stringWriter.getBuffer().length() - 2) != ']') {
            printWriter.println("]");
        }
        return stringWriter.toString();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return obj != null && (obj instanceof Pointer) && ((Pointer) obj).peer == this.peer;
    }

    public byte getByte(long j) {
        return Native.getByte(this, this.peer, j);
    }

    public byte[] getByteArray(long j, int i) {
        byte[] bArr = new byte[i];
        read(j, bArr, 0, i);
        return bArr;
    }

    public ByteBuffer getByteBuffer(long j, long j2) {
        return Native.getDirectByteBuffer(this, this.peer, j, j2).order(ByteOrder.nativeOrder());
    }

    public char getChar(long j) {
        return Native.getChar(this, this.peer, j);
    }

    public char[] getCharArray(long j, int i) {
        char[] cArr = new char[i];
        read(j, cArr, 0, i);
        return cArr;
    }

    public double getDouble(long j) {
        return Native.getDouble(this, this.peer, j);
    }

    public double[] getDoubleArray(long j, int i) {
        double[] dArr = new double[i];
        read(j, dArr, 0, i);
        return dArr;
    }

    public float getFloat(long j) {
        return Native.getFloat(this, this.peer, j);
    }

    public float[] getFloatArray(long j, int i) {
        float[] fArr = new float[i];
        read(j, fArr, 0, i);
        return fArr;
    }

    public int getInt(long j) {
        return Native.getInt(this, this.peer, j);
    }

    public int[] getIntArray(long j, int i) {
        int[] iArr = new int[i];
        read(j, iArr, 0, i);
        return iArr;
    }

    public long getLong(long j) {
        return Native.getLong(this, this.peer, j);
    }

    public long[] getLongArray(long j, int i) {
        long[] jArr = new long[i];
        read(j, jArr, 0, i);
        return jArr;
    }

    public NativeLong getNativeLong(long j) {
        return new NativeLong(NativeLong.SIZE == 8 ? getLong(j) : getInt(j));
    }

    public Pointer getPointer(long j) {
        return Native.getPointer(this.peer + j);
    }

    public Pointer[] getPointerArray(long j) {
        ArrayList arrayList = new ArrayList();
        Pointer pointer = getPointer(j);
        int i = 0;
        while (pointer != null) {
            arrayList.add(pointer);
            i += Native.POINTER_SIZE;
            pointer = getPointer(i + j);
        }
        return (Pointer[]) arrayList.toArray(new Pointer[0]);
    }

    public Pointer[] getPointerArray(long j, int i) {
        Pointer[] pointerArr = new Pointer[i];
        read(j, pointerArr, 0, i);
        return pointerArr;
    }

    public short getShort(long j) {
        return Native.getShort(this, this.peer, j);
    }

    public short[] getShortArray(long j, int i) {
        short[] sArr = new short[i];
        read(j, sArr, 0, i);
        return sArr;
    }

    public String getString(long j) {
        return getString(j, Native.getDefaultStringEncoding());
    }

    public String getString(long j, String str) {
        return Native.getString(this, j, str);
    }

    public String[] getStringArray(long j) {
        return getStringArray(j, -1, Native.getDefaultStringEncoding());
    }

    public String[] getStringArray(long j, int i) {
        return getStringArray(j, i, Native.getDefaultStringEncoding());
    }

    public String[] getStringArray(long j, int i, String str) {
        ArrayList arrayList = new ArrayList();
        if (i == -1) {
            int i2 = 0;
            while (true) {
                int i3 = i2;
                Pointer pointer = getPointer(i3 + j);
                if (pointer == null) {
                    break;
                }
                arrayList.add("--WIDE-STRING--".equals(str) ? pointer.getWideString(0L) : pointer.getString(0L, str));
                i2 = i3 + Native.POINTER_SIZE;
            }
        } else {
            Pointer pointer2 = getPointer(0 + j);
            int i4 = 0;
            int i5 = 0;
            while (true) {
                int i6 = i5;
                int i7 = i4 + 1;
                if (i4 >= i) {
                    break;
                }
                arrayList.add(pointer2 == null ? null : "--WIDE-STRING--".equals(str) ? pointer2.getWideString(0L) : pointer2.getString(0L, str));
                int i8 = i6;
                if (i7 < i) {
                    i8 = i6 + Native.POINTER_SIZE;
                    pointer2 = getPointer(i8 + j);
                }
                i4 = i7;
                i5 = i8;
            }
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    public String[] getStringArray(long j, String str) {
        return getStringArray(j, -1, str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    public Object getValue(long j, Class<?> cls, Object obj) {
        Boolean valueOf;
        Object fromNative;
        Pointer pointer;
        boolean z = true;
        if (!Structure.class.isAssignableFrom(cls)) {
            if (cls != Boolean.TYPE && cls != Boolean.class) {
                if (cls == Byte.TYPE || cls == Byte.class) {
                    return Byte.valueOf(getByte(j));
                }
                if (cls == Short.TYPE || cls == Short.class) {
                    return Short.valueOf(getShort(j));
                }
                if (cls == Character.TYPE || cls == Character.class) {
                    return Character.valueOf(getChar(j));
                }
                if (cls == Integer.TYPE || cls == Integer.class) {
                    return Integer.valueOf(getInt(j));
                }
                if (cls == Long.TYPE || cls == Long.class) {
                    return Long.valueOf(getLong(j));
                }
                if (cls == Float.TYPE || cls == Float.class) {
                    return Float.valueOf(getFloat(j));
                }
                if (cls == Double.TYPE || cls == Double.class) {
                    return Double.valueOf(getDouble(j));
                }
                if (Pointer.class.isAssignableFrom(cls)) {
                    Pointer pointer2 = getPointer(j);
                    pointer = null;
                    if (pointer2 != null) {
                        Pointer pointer3 = null;
                        if (obj instanceof Pointer) {
                            pointer3 = (Pointer) obj;
                        }
                        fromNative = pointer2;
                        if (pointer3 != null) {
                            pointer = pointer3;
                            if (pointer2.peer != pointer3.peer) {
                                fromNative = pointer2;
                            }
                        }
                    }
                    return pointer;
                }
                if (cls == String.class) {
                    Pointer pointer4 = getPointer(j);
                    pointer = null;
                    if (pointer4 != null) {
                        fromNative = pointer4.getString(0L);
                    }
                    return pointer;
                }
                if (cls == WString.class) {
                    Pointer pointer5 = getPointer(j);
                    pointer = null;
                    if (pointer5 != null) {
                        return new WString(pointer5.getWideString(0L));
                    }
                } else if (Callback.class.isAssignableFrom(cls)) {
                    Pointer pointer6 = getPointer(j);
                    if (pointer6 == null) {
                        pointer = null;
                    } else {
                        Callback callback = (Callback) obj;
                        valueOf = callback;
                        if (!pointer6.equals(CallbackReference.getFunctionPointer(callback))) {
                            return CallbackReference.getCallback(cls, pointer6);
                        }
                    }
                } else if (Platform.HAS_BUFFERS && Buffer.class.isAssignableFrom(cls)) {
                    Pointer pointer7 = getPointer(j);
                    if (pointer7 != null) {
                        Pointer directBufferPointer = obj == null ? null : Native.getDirectBufferPointer((Buffer) obj);
                        if (directBufferPointer == null || !directBufferPointer.equals(pointer7)) {
                            throw new IllegalStateException("Can't autogenerate a direct buffer on memory read");
                        }
                        return obj;
                    }
                    pointer = null;
                } else {
                    if (!NativeMapped.class.isAssignableFrom(cls)) {
                        if (cls.isArray()) {
                            if (obj == null) {
                                throw new IllegalStateException("Need an initialized array");
                            }
                            readArray(j, obj, cls.getComponentType());
                            return obj;
                        }
                        throw new IllegalArgumentException("Reading \"" + cls + "\" from memory is not supported");
                    }
                    NativeMapped nativeMapped = (NativeMapped) obj;
                    if (nativeMapped != null) {
                        Object fromNative2 = nativeMapped.fromNative(getValue(j, nativeMapped.nativeType(), null), new FromNativeContext(cls));
                        fromNative = fromNative2;
                        if (nativeMapped.equals(fromNative2)) {
                            fromNative = nativeMapped;
                        }
                    } else {
                        NativeMappedConverter nativeMappedConverter = NativeMappedConverter.getInstance(cls);
                        fromNative = nativeMappedConverter.fromNative(getValue(j, nativeMappedConverter.nativeType(), null), new FromNativeContext(cls));
                    }
                }
                return pointer;
            }
            if (getInt(j) == 0) {
                z = false;
            }
            valueOf = Function.valueOf(z);
            return valueOf;
        }
        Structure structure = (Structure) obj;
        if (!Structure.ByReference.class.isAssignableFrom(cls)) {
            structure.useMemory(this, (int) j, true);
            structure.read();
            return structure;
        }
        fromNative = Structure.updateStructureByReference(cls, structure, getPointer(j));
        return fromNative;
    }

    public String getWideString(long j) {
        return Native.getWideString(this, this.peer, j);
    }

    public String[] getWideStringArray(long j) {
        return getWideStringArray(j, -1);
    }

    public String[] getWideStringArray(long j, int i) {
        return getStringArray(j, i, "--WIDE-STRING--");
    }

    public int hashCode() {
        long j = this.peer;
        return (int) ((j >>> 32) + (j & (-1)));
    }

    public long indexOf(long j, byte b) {
        return Native.indexOf(this, this.peer, j, b);
    }

    public void read(long j, byte[] bArr, int i, int i2) {
        Native.read(this, this.peer, j, bArr, i, i2);
    }

    public void read(long j, char[] cArr, int i, int i2) {
        Native.read(this, this.peer, j, cArr, i, i2);
    }

    public void read(long j, double[] dArr, int i, int i2) {
        Native.read(this, this.peer, j, dArr, i, i2);
    }

    public void read(long j, float[] fArr, int i, int i2) {
        Native.read(this, this.peer, j, fArr, i, i2);
    }

    public void read(long j, int[] iArr, int i, int i2) {
        Native.read(this, this.peer, j, iArr, i, i2);
    }

    public void read(long j, long[] jArr, int i, int i2) {
        Native.read(this, this.peer, j, jArr, i, i2);
    }

    public void read(long j, Pointer[] pointerArr, int i, int i2) {
        int i3 = 0;
        while (true) {
            int i4 = i3;
            if (i4 >= i2) {
                return;
            }
            Pointer pointer = getPointer((Native.POINTER_SIZE * i4) + j);
            int i5 = i4 + i;
            Pointer pointer2 = pointerArr[i5];
            if (pointer2 == null || pointer == null || pointer.peer != pointer2.peer) {
                pointerArr[i5] = pointer;
            }
            i3 = i4 + 1;
        }
    }

    public void read(long j, short[] sArr, int i, int i2) {
        Native.read(this, this.peer, j, sArr, i, i2);
    }

    public void setByte(long j, byte b) {
        Native.setByte(this, this.peer, j, b);
    }

    public void setChar(long j, char c) {
        Native.setChar(this, this.peer, j, c);
    }

    public void setDouble(long j, double d) {
        Native.setDouble(this, this.peer, j, d);
    }

    public void setFloat(long j, float f) {
        Native.setFloat(this, this.peer, j, f);
    }

    public void setInt(long j, int i) {
        Native.setInt(this, this.peer, j, i);
    }

    public void setLong(long j, long j2) {
        Native.setLong(this, this.peer, j, j2);
    }

    public void setMemory(long j, long j2, byte b) {
        Native.setMemory(this, this.peer, j, j2, b);
    }

    public void setNativeLong(long j, NativeLong nativeLong) {
        if (NativeLong.SIZE == 8) {
            setLong(j, nativeLong.longValue());
        } else {
            setInt(j, nativeLong.intValue());
        }
    }

    public void setPointer(long j, Pointer pointer) {
        Native.setPointer(this, this.peer, j, pointer != null ? pointer.peer : 0L);
    }

    public void setShort(long j, short s) {
        Native.setShort(this, this.peer, j, s);
    }

    public void setString(long j, WString wString) {
        setWideString(j, wString == null ? null : wString.toString());
    }

    public void setString(long j, String str) {
        setString(j, str, Native.getDefaultStringEncoding());
    }

    public void setString(long j, String str, String str2) {
        byte[] bytes = Native.getBytes(str, str2);
        write(j, bytes, 0, bytes.length);
        setByte(j + bytes.length, (byte) 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setValue(long j, Object obj, Class<?> cls) {
        char c = 0;
        short s = 0;
        byte b = 0;
        int i = 0;
        if (cls == Boolean.TYPE || cls == Boolean.class) {
            int i2 = 0;
            if (Boolean.TRUE.equals(obj)) {
                i2 = -1;
            }
            setInt(j, i2);
            return;
        }
        if (cls == Byte.TYPE || cls == Byte.class) {
            if (obj != null) {
                b = ((Byte) obj).byteValue();
            }
            setByte(j, b);
            return;
        }
        if (cls == Short.TYPE || cls == Short.class) {
            if (obj != null) {
                s = ((Short) obj).shortValue();
            }
            setShort(j, s);
            return;
        }
        if (cls == Character.TYPE || cls == Character.class) {
            if (obj != null) {
                c = ((Character) obj).charValue();
            }
            setChar(j, c);
            return;
        }
        if (cls == Integer.TYPE || cls == Integer.class) {
            if (obj != null) {
                i = ((Integer) obj).intValue();
            }
            setInt(j, i);
            return;
        }
        if (cls == Long.TYPE || cls == Long.class) {
            setLong(j, obj == null ? 0L : ((Long) obj).longValue());
            return;
        }
        if (cls == Float.TYPE || cls == Float.class) {
            setFloat(j, obj == null ? 0.0f : ((Float) obj).floatValue());
            return;
        }
        if (cls == Double.TYPE || cls == Double.class) {
            setDouble(j, obj == null ? 0.0d : ((Double) obj).doubleValue());
            return;
        }
        if (cls == Pointer.class) {
            setPointer(j, (Pointer) obj);
            return;
        }
        if (cls == String.class) {
            setPointer(j, (Pointer) obj);
            return;
        }
        if (cls == WString.class) {
            setPointer(j, (Pointer) obj);
            return;
        }
        if (Structure.class.isAssignableFrom(cls)) {
            Structure structure = (Structure) obj;
            if (!Structure.ByReference.class.isAssignableFrom(cls)) {
                structure.useMemory(this, (int) j, true);
                structure.write();
                return;
            } else {
                setPointer(j, structure == null ? null : structure.getPointer());
                if (structure != null) {
                    structure.autoWrite();
                    return;
                }
                return;
            }
        }
        if (Callback.class.isAssignableFrom(cls)) {
            setPointer(j, CallbackReference.getFunctionPointer((Callback) obj));
            return;
        }
        if (Platform.HAS_BUFFERS && Buffer.class.isAssignableFrom(cls)) {
            setPointer(j, obj == null ? null : Native.getDirectBufferPointer((Buffer) obj));
            return;
        }
        if (NativeMapped.class.isAssignableFrom(cls)) {
            NativeMappedConverter nativeMappedConverter = NativeMappedConverter.getInstance(cls);
            setValue(j, nativeMappedConverter.toNative(obj, new ToNativeContext()), nativeMappedConverter.nativeType());
        } else {
            if (cls.isArray()) {
                writeArray(j, obj, cls.getComponentType());
                return;
            }
            throw new IllegalArgumentException("Writing " + cls + " to memory is not supported");
        }
    }

    public void setWideString(long j, String str) {
        Native.setWideString(this, this.peer, j, str);
    }

    public Pointer share(long j) {
        return share(j, 0L);
    }

    public Pointer share(long j, long j2) {
        return j == 0 ? this : new Pointer(this.peer + j);
    }

    public String toString() {
        return "native@0x" + Long.toHexString(this.peer);
    }

    public void write(long j, byte[] bArr, int i, int i2) {
        Native.write(this, this.peer, j, bArr, i, i2);
    }

    public void write(long j, char[] cArr, int i, int i2) {
        Native.write(this, this.peer, j, cArr, i, i2);
    }

    public void write(long j, double[] dArr, int i, int i2) {
        Native.write(this, this.peer, j, dArr, i, i2);
    }

    public void write(long j, float[] fArr, int i, int i2) {
        Native.write(this, this.peer, j, fArr, i, i2);
    }

    public void write(long j, int[] iArr, int i, int i2) {
        Native.write(this, this.peer, j, iArr, i, i2);
    }

    public void write(long j, long[] jArr, int i, int i2) {
        Native.write(this, this.peer, j, jArr, i, i2);
    }

    public void write(long j, Pointer[] pointerArr, int i, int i2) {
        int i3 = 0;
        while (true) {
            int i4 = i3;
            if (i4 >= i2) {
                return;
            }
            setPointer((Native.POINTER_SIZE * i4) + j, pointerArr[i + i4]);
            i3 = i4 + 1;
        }
    }

    public void write(long j, short[] sArr, int i, int i2) {
        Native.write(this, this.peer, j, sArr, i, i2);
    }
}
