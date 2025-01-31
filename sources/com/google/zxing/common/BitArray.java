package com.google.zxing.common;

import java.util.Arrays;

/* loaded from: classes08-dex2jar.jar:com/google/zxing/common/BitArray.class */
public final class BitArray implements Cloneable {
    private int[] bits;
    private int size;

    public BitArray() {
        this.size = 0;
        this.bits = new int[1];
    }

    public BitArray(int i) {
        this.size = i;
        this.bits = makeArray(i);
    }

    BitArray(int[] iArr, int i) {
        this.bits = iArr;
        this.size = i;
    }

    private void ensureCapacity(int i) {
        if (i > (this.bits.length << 5)) {
            int[] makeArray = makeArray(i);
            int[] iArr = this.bits;
            System.arraycopy(iArr, 0, makeArray, 0, iArr.length);
            this.bits = makeArray;
        }
    }

    private static int[] makeArray(int i) {
        return new int[(i + 31) / 32];
    }

    public void appendBit(boolean z) {
        ensureCapacity(this.size + 1);
        if (z) {
            int[] iArr = this.bits;
            int i = this.size;
            int i2 = i / 32;
            iArr[i2] = (1 << (i & 31)) | iArr[i2];
        }
        this.size++;
    }

    public void appendBitArray(BitArray bitArray) {
        int i = bitArray.size;
        ensureCapacity(this.size + i);
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 >= i) {
                return;
            }
            appendBit(bitArray.get(i3));
            i2 = i3 + 1;
        }
    }

    public void appendBits(int i, int i2) {
        if (i2 < 0 || i2 > 32) {
            throw new IllegalArgumentException("Num bits must be between 0 and 32");
        }
        ensureCapacity(this.size + i2);
        while (i2 > 0) {
            boolean z = true;
            if (((i >> (i2 - 1)) & 1) != 1) {
                z = false;
            }
            appendBit(z);
            i2--;
        }
    }

    public void clear() {
        int length = this.bits.length;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length) {
                return;
            }
            this.bits[i2] = 0;
            i = i2 + 1;
        }
    }

    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public BitArray m3501clone() {
        return new BitArray((int[]) this.bits.clone(), this.size);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof BitArray)) {
            return false;
        }
        BitArray bitArray = (BitArray) obj;
        return this.size == bitArray.size && Arrays.equals(this.bits, bitArray.bits);
    }

    public void flip(int i) {
        int[] iArr = this.bits;
        int i2 = i / 32;
        iArr[i2] = (1 << (i & 31)) ^ iArr[i2];
    }

    public boolean get(int i) {
        return ((1 << (i & 31)) & this.bits[i / 32]) != 0;
    }

    public int[] getBitArray() {
        return this.bits;
    }

    public int getNextSet(int i) {
        int i2 = this.size;
        if (i >= i2) {
            return i2;
        }
        int i3 = i / 32;
        int i4 = ((1 << (i & 31)) - 1) & this.bits[i3];
        int i5 = i3;
        while (i4 == 0) {
            i5++;
            int[] iArr = this.bits;
            if (i5 == iArr.length) {
                return this.size;
            }
            i4 = iArr[i5];
        }
        int numberOfTrailingZeros = (i5 << 5) + Integer.numberOfTrailingZeros(i4);
        int i6 = this.size;
        return numberOfTrailingZeros > i6 ? i6 : numberOfTrailingZeros;
    }

    public int getNextUnset(int i) {
        int i2 = this.size;
        if (i >= i2) {
            return i2;
        }
        int i3 = i / 32;
        int i4 = ((1 << (i & 31)) - 1) & this.bits[i3];
        int i5 = i3;
        while (i4 == 0) {
            i5++;
            int[] iArr = this.bits;
            if (i5 == iArr.length) {
                return this.size;
            }
            i4 = iArr[i5];
        }
        int numberOfTrailingZeros = (i5 << 5) + Integer.numberOfTrailingZeros(i4);
        int i6 = this.size;
        return numberOfTrailingZeros > i6 ? i6 : numberOfTrailingZeros;
    }

    public int getSize() {
        return this.size;
    }

    public int getSizeInBytes() {
        return (this.size + 7) / 8;
    }

    public int hashCode() {
        return (this.size * 31) + Arrays.hashCode(this.bits);
    }

    public boolean isRange(int i, int i2, boolean z) {
        if (i2 < i || i < 0 || i2 > this.size) {
            throw new IllegalArgumentException();
        }
        if (i2 == i) {
            return true;
        }
        int i3 = i2 - 1;
        int i4 = i / 32;
        int i5 = i3 / 32;
        int i6 = i4;
        while (true) {
            int i7 = i6;
            if (i7 > i5) {
                return true;
            }
            int i8 = 31;
            int i9 = i7 > i4 ? 0 : i & 31;
            if (i7 >= i5) {
                i8 = 31 & i3;
            }
            int i10 = (2 << i8) - (1 << i9);
            if ((this.bits[i7] & i10) != (z ? i10 : 0)) {
                return false;
            }
            i6 = i7 + 1;
        }
    }

    public void reverse() {
        int[] iArr = new int[this.bits.length];
        int i = (this.size - 1) / 32;
        int i2 = i + 1;
        int i3 = 0;
        while (true) {
            int i4 = i3;
            if (i4 >= i2) {
                break;
            }
            long j = this.bits[i4];
            long j2 = ((j & 1431655765) << 1) | ((j >> 1) & 1431655765);
            long j3 = ((j2 & 858993459) << 2) | ((j2 >> 2) & 858993459);
            long j4 = ((j3 & 252645135) << 4) | ((j3 >> 4) & 252645135);
            long j5 = ((j4 & 16711935) << 8) | ((j4 >> 8) & 16711935);
            iArr[i - i4] = (int) (((j5 & 65535) << 16) | ((j5 >> 16) & 65535));
            i3 = i4 + 1;
        }
        int i5 = this.size;
        int i6 = i2 << 5;
        if (i5 != i6) {
            int i7 = i6 - i5;
            int i8 = iArr[0] >>> i7;
            int i9 = 1;
            while (true) {
                int i10 = i9;
                if (i10 >= i2) {
                    break;
                }
                int i11 = iArr[i10];
                iArr[i10 - 1] = i8 | (i11 << (32 - i7));
                i8 = i11 >>> i7;
                i9 = i10 + 1;
            }
            iArr[i2 - 1] = i8;
        }
        this.bits = iArr;
    }

    public void set(int i) {
        int[] iArr = this.bits;
        int i2 = i / 32;
        iArr[i2] = (1 << (i & 31)) | iArr[i2];
    }

    public void setBulk(int i, int i2) {
        this.bits[i / 32] = i2;
    }

    public void setRange(int i, int i2) {
        if (i2 < i || i < 0 || i2 > this.size) {
            throw new IllegalArgumentException();
        }
        if (i2 == i) {
            return;
        }
        int i3 = i2 - 1;
        int i4 = i / 32;
        int i5 = i3 / 32;
        int i6 = i4;
        while (true) {
            int i7 = i6;
            if (i7 > i5) {
                return;
            }
            int i8 = 31;
            int i9 = i7 > i4 ? 0 : i & 31;
            if (i7 >= i5) {
                i8 = 31 & i3;
            }
            int[] iArr = this.bits;
            iArr[i7] = ((2 << i8) - (1 << i9)) | iArr[i7];
            i6 = i7 + 1;
        }
    }

    public void toBytes(int i, byte[] bArr, int i2, int i3) {
        int i4;
        int i5 = 0;
        while (true) {
            int i6 = i5;
            if (i6 >= i3) {
                return;
            }
            int i7 = 0;
            int i8 = 0;
            while (true) {
                i4 = i8;
                if (i7 < 8) {
                    int i9 = i4;
                    if (get(i)) {
                        i9 = i4 | (1 << (7 - i7));
                    }
                    i++;
                    i7++;
                    i8 = i9;
                }
            }
            bArr[i2 + i6] = (byte) i4;
            i5 = i6 + 1;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(this.size);
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= this.size) {
                return sb.toString();
            }
            if ((i2 & 7) == 0) {
                sb.append(' ');
            }
            sb.append(get(i2) ? 'X' : '.');
            i = i2 + 1;
        }
    }

    public void xor(BitArray bitArray) {
        if (this.size != bitArray.size) {
            throw new IllegalArgumentException("Sizes don't match");
        }
        int i = 0;
        while (true) {
            int i2 = i;
            int[] iArr = this.bits;
            if (i2 >= iArr.length) {
                return;
            }
            iArr[i2] = iArr[i2] ^ bitArray.bits[i2];
            i = i2 + 1;
        }
    }
}
