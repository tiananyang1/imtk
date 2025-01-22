package com.sun.jna;

/* loaded from: classes08-dex2jar.jar:com/sun/jna/IntegerType.class */
public abstract class IntegerType extends Number implements NativeMapped {
    private static final long serialVersionUID = 1;
    private Number number;
    private int size;
    private boolean unsigned;
    private long value;

    public IntegerType(int i) {
        this(i, 0L, false);
    }

    public IntegerType(int i, long j) {
        this(i, j, false);
    }

    public IntegerType(int i, long j, boolean z) {
        this.size = i;
        this.unsigned = z;
        setValue(j);
    }

    public IntegerType(int i, boolean z) {
        this(i, 0L, z);
    }

    public static final int compare(long j, long j2) {
        if (j == j2) {
            return 0;
        }
        return j < j2 ? -1 : 1;
    }

    public static int compare(IntegerType integerType, long j) {
        if (integerType == null) {
            return 1;
        }
        return compare(integerType.longValue(), j);
    }

    public static <T extends IntegerType> int compare(T t, T t2) {
        if (t == t2) {
            return 0;
        }
        if (t == null) {
            return 1;
        }
        if (t2 == null) {
            return -1;
        }
        return compare(t.longValue(), t2.longValue());
    }

    @Override // java.lang.Number
    public double doubleValue() {
        return this.number.doubleValue();
    }

    public boolean equals(Object obj) {
        return (obj instanceof IntegerType) && this.number.equals(((IntegerType) obj).number);
    }

    @Override // java.lang.Number
    public float floatValue() {
        return this.number.floatValue();
    }

    @Override // com.sun.jna.NativeMapped
    public Object fromNative(Object obj, FromNativeContext fromNativeContext) {
        long longValue = obj == null ? 0L : ((Number) obj).longValue();
        IntegerType integerType = (IntegerType) Klass.newInstance(getClass());
        integerType.setValue(longValue);
        return integerType;
    }

    public int hashCode() {
        return this.number.hashCode();
    }

    @Override // java.lang.Number
    public int intValue() {
        return (int) this.value;
    }

    @Override // java.lang.Number
    public long longValue() {
        return this.value;
    }

    @Override // com.sun.jna.NativeMapped
    public Class<?> nativeType() {
        return this.number.getClass();
    }

    public void setValue(long j) {
        long j2;
        this.value = j;
        int i = this.size;
        if (i == 1) {
            if (this.unsigned) {
                this.value = 255 & j;
            }
            byte b = (byte) j;
            j2 = b;
            this.number = Byte.valueOf(b);
        } else if (i == 2) {
            if (this.unsigned) {
                this.value = 65535 & j;
            }
            short s = (short) j;
            j2 = s;
            this.number = Short.valueOf(s);
        } else if (i == 4) {
            if (this.unsigned) {
                this.value = 4294967295L & j;
            }
            int i2 = (int) j;
            j2 = i2;
            this.number = Integer.valueOf(i2);
        } else {
            if (i != 8) {
                throw new IllegalArgumentException("Unsupported size: " + this.size);
            }
            this.number = Long.valueOf(j);
            j2 = j;
        }
        int i3 = this.size;
        if (i3 < 8) {
            long j3 = (serialVersionUID << (i3 * 8)) - serialVersionUID;
            if ((j >= 0 || j2 == j) && (j < 0 || (j3 & j) == 0)) {
                return;
            }
            throw new IllegalArgumentException("Argument value 0x" + Long.toHexString(j) + " exceeds native capacity (" + this.size + " bytes) mask=0x" + Long.toHexString(j3));
        }
    }

    @Override // com.sun.jna.NativeMapped
    public Object toNative() {
        return this.number;
    }

    public String toString() {
        return this.number.toString();
    }
}
