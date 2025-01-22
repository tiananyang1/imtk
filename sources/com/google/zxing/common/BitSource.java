package com.google.zxing.common;

/* loaded from: classes08-dex2jar.jar:com/google/zxing/common/BitSource.class */
public final class BitSource {
    private int bitOffset;
    private int byteOffset;
    private final byte[] bytes;

    public BitSource(byte[] bArr) {
        this.bytes = bArr;
    }

    public int available() {
        return ((this.bytes.length - this.byteOffset) * 8) - this.bitOffset;
    }

    public int getBitOffset() {
        return this.bitOffset;
    }

    public int getByteOffset() {
        return this.byteOffset;
    }

    public int readBits(int i) {
        int i2;
        int i3;
        if (i <= 0 || i > 32 || i > available()) {
            throw new IllegalArgumentException(String.valueOf(i));
        }
        int i4 = this.bitOffset;
        if (i4 > 0) {
            int i5 = 8 - i4;
            int i6 = i < i5 ? i : i5;
            int i7 = i5 - i6;
            byte[] bArr = this.bytes;
            int i8 = this.byteOffset;
            int i9 = (((255 >> (8 - i6)) << i7) & bArr[i8]) >> i7;
            int i10 = i - i6;
            this.bitOffset += i6;
            i3 = i9;
            i2 = i10;
            if (this.bitOffset == 8) {
                this.bitOffset = 0;
                this.byteOffset = i8 + 1;
                i3 = i9;
                i2 = i10;
            }
        } else {
            i2 = i;
            i3 = 0;
        }
        int i11 = i3;
        if (i2 > 0) {
            while (i2 >= 8) {
                byte[] bArr2 = this.bytes;
                int i12 = this.byteOffset;
                i3 = (i3 << 8) | (bArr2[i12] & 255);
                this.byteOffset = i12 + 1;
                i2 -= 8;
            }
            i11 = i3;
            if (i2 > 0) {
                int i13 = 8 - i2;
                i11 = (i3 << i2) | ((((255 >> i13) << i13) & this.bytes[this.byteOffset]) >> i13);
                this.bitOffset += i2;
            }
        }
        return i11;
    }
}
