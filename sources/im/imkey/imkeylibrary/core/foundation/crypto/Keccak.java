package im.imkey.imkeylibrary.core.foundation.crypto;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/core/foundation/crypto/Keccak.class */
public class Keccak {
    private static final int MAX_STATE_SIZE = 1600;
    private static final int MAX_STATE_SIZE_WORDS = 25;

    /* renamed from: RC */
    private static final long[] f2742RC = {1, 32898, -9223372036854742902L, -9223372034707259392L, 32907, 2147483649L, -9223372034707259263L, -9223372036854743031L, 138, 136, 2147516425L, 2147483658L, 2147516555L, -9223372036854775669L, -9223372036854742903L, -9223372036854743037L, -9223372036854743038L, -9223372036854775680L, 32778, -9223372034707292150L, -9223372034707259263L, -9223372036854742912L, 2147483649L, -9223372034707259384L};
    private int digestSizeBits;
    private boolean padded;
    private int rateBits;
    private int rateSizeBits;
    private long[] state;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Keccak(int i) {
        this.state = new long[25];
        reset(i);
    }

    public Keccak(Keccak keccak) {
        this.state = new long[25];
        long[] jArr = keccak.state;
        System.arraycopy(jArr, 0, this.state, 0, jArr.length);
        this.rateBits = keccak.rateBits;
        this.rateSizeBits = keccak.rateSizeBits;
        this.digestSizeBits = keccak.digestSizeBits;
        this.padded = keccak.padded;
    }

    private ByteBuffer digest(int i) {
        return digest(i, false);
    }

    private ByteBuffer digest(int i, boolean z) {
        ByteBuffer allocateDirect = z ? ByteBuffer.allocateDirect(i) : ByteBuffer.allocate(i);
        digest(allocateDirect);
        allocateDirect.flip();
        return allocateDirect;
    }

    private void digest(ByteBuffer byteBuffer) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int remaining = byteBuffer.remaining();
        if (remaining <= 0) {
            return;
        }
        long[] jArr = this.state;
        int i7 = this.rateBits;
        if (!this.padded) {
            pad();
            this.padded = true;
            i2 = 0;
            i = remaining;
        } else {
            if ((i7 & 7) > 0) {
                throw new IllegalStateException("Cannot digest while in bit-mode");
            }
            int i8 = i7 >>> 3;
            int i9 = i8 & 7;
            i = remaining;
            i2 = i8;
            if (i9 > 0) {
                int i10 = 8 - i9;
                int i11 = i10;
                if (i10 > remaining) {
                    i11 = remaining;
                }
                long j = jArr[i8 >>> 3];
                int i12 = remaining - i11;
                int i13 = i8 + i11;
                int i14 = i9 << 3;
                int i15 = i14;
                do {
                    byteBuffer.put((byte) (j >>> i15));
                    i3 = i15 + 8;
                    i15 = i3;
                } while (i3 < (i11 << 3) + i14);
                if (i12 <= 0) {
                    this.rateBits = i13 << 3;
                    return;
                } else {
                    i = i12;
                    i2 = i13;
                }
            }
        }
        int i16 = this.rateSizeBits >>> 6;
        int i17 = i2 >>> 3;
        int i18 = i >>> 3;
        int i19 = i;
        int i20 = i17;
        if (i18 > 0) {
            ByteOrder order = byteBuffer.order();
            try {
                byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
                int i21 = i18;
                do {
                    int i22 = i17;
                    if (i17 >= i16) {
                        squeeze();
                        i22 = 0;
                    }
                    int i23 = i16 - i22;
                    int i24 = i23;
                    if (i23 > i21) {
                        i24 = i21;
                    }
                    i5 = i21 - i24;
                    int i25 = i22;
                    do {
                        byteBuffer.putLong(jArr[i25]);
                        i6 = i25 + 1;
                        i25 = i6;
                    } while (i6 < i24 + i22);
                    i17 = i6;
                    i21 = i5;
                } while (i5 > 0);
                byteBuffer.order(order);
                int i26 = i & 7;
                i19 = i26;
                i20 = i6;
                if (i26 <= 0) {
                    this.rateBits = i6 << 6;
                    return;
                }
            } catch (Throwable th) {
                byteBuffer.order(order);
                throw th;
            }
        }
        int i27 = i20;
        if (i20 >= i16) {
            squeeze();
            i27 = 0;
        }
        long j2 = jArr[i27];
        int i28 = i19 << 3;
        int i29 = 0;
        do {
            byteBuffer.put((byte) (j2 >>> i29));
            i4 = i29 + 8;
            i29 = i4;
        } while (i4 < i28);
        this.rateBits = (i27 << 6) | i28;
    }

    private void digest(byte[] bArr, int i, int i2) {
        digest(ByteBuffer.wrap(bArr, i, i2));
    }

    private byte[] digestArray(int i) {
        byte[] bArr = new byte[i];
        digest(bArr, 0, i);
        return bArr;
    }

    private int digestSize() {
        return this.digestSizeBits >>> 3;
    }

    private static void keccak(long[] jArr) {
        int i = 0;
        do {
            long j = (((jArr[0] ^ jArr[5]) ^ jArr[10]) ^ jArr[15]) ^ jArr[20];
            long j2 = (((jArr[1] ^ jArr[6]) ^ jArr[11]) ^ jArr[16]) ^ jArr[21];
            long j3 = (((jArr[2] ^ jArr[7]) ^ jArr[12]) ^ jArr[17]) ^ jArr[22];
            long j4 = (((jArr[3] ^ jArr[8]) ^ jArr[13]) ^ jArr[18]) ^ jArr[23];
            long j5 = (((jArr[4] ^ jArr[9]) ^ jArr[14]) ^ jArr[19]) ^ jArr[24];
            long j6 = ((j << 1) ^ (j >>> 63)) ^ j4;
            long j7 = ((j2 << 1) ^ (j2 >>> 63)) ^ j5;
            long j8 = ((j3 << 1) ^ (j3 >>> 63)) ^ j;
            long j9 = ((j4 << 1) ^ (j4 >>> 63)) ^ j2;
            long j10 = ((j5 << 1) ^ (j5 >>> 63)) ^ j3;
            jArr[0] = jArr[0] ^ j7;
            long j11 = jArr[1] ^ j8;
            long j12 = jArr[6] ^ j8;
            jArr[1] = (j12 << 44) | (j12 >>> 20);
            long j13 = jArr[9] ^ j6;
            jArr[6] = (j13 << 20) | (j13 >>> 44);
            long j14 = jArr[22] ^ j9;
            jArr[9] = (j14 << 61) | (j14 >>> 3);
            long j15 = jArr[14] ^ j6;
            jArr[22] = (j15 << 39) | (j15 >>> 25);
            long j16 = jArr[20] ^ j7;
            jArr[14] = (j16 << 18) | (j16 >>> 46);
            long j17 = jArr[2] ^ j9;
            jArr[20] = (j17 << 62) | (j17 >>> 2);
            long j18 = jArr[12] ^ j9;
            jArr[2] = (j18 << 43) | (j18 >>> 21);
            long j19 = jArr[13] ^ j10;
            jArr[12] = (j19 << 25) | (j19 >>> 39);
            long j20 = jArr[19] ^ j6;
            jArr[13] = (j20 << 8) | (j20 >>> 56);
            long j21 = jArr[23] ^ j10;
            jArr[19] = (j21 << 56) | (j21 >>> 8);
            long j22 = jArr[15] ^ j7;
            jArr[23] = (j22 << 41) | (j22 >>> 23);
            long j23 = jArr[4] ^ j6;
            jArr[15] = (j23 << 27) | (j23 >>> 37);
            long j24 = jArr[24] ^ j6;
            jArr[4] = (j24 << 14) | (j24 >>> 50);
            long j25 = jArr[21] ^ j8;
            jArr[24] = (j25 << 2) | (j25 >>> 62);
            long j26 = jArr[8] ^ j10;
            jArr[21] = (j26 << 55) | (j26 >>> 9);
            long j27 = jArr[16] ^ j8;
            jArr[8] = (j27 << 45) | (j27 >>> 19);
            long j28 = jArr[5] ^ j7;
            jArr[16] = (j28 << 36) | (j28 >>> 28);
            long j29 = jArr[3] ^ j10;
            jArr[5] = (j29 << 28) | (j29 >>> 36);
            long j30 = jArr[18] ^ j10;
            jArr[3] = (j30 << 21) | (j30 >>> 43);
            long j31 = jArr[17] ^ j9;
            jArr[18] = (j31 << 15) | (j31 >>> 49);
            long j32 = j8 ^ jArr[11];
            jArr[17] = (j32 >>> 54) | (j32 << 10);
            long j33 = jArr[7] ^ j9;
            jArr[11] = (j33 >>> 58) | (j33 << 6);
            long j34 = jArr[10] ^ j7;
            jArr[7] = (j34 >>> 61) | (j34 << 3);
            jArr[10] = (j11 << 1) | (j11 >>> 63);
            int i2 = 0;
            do {
                int i3 = i2 + 0;
                long j35 = jArr[i3];
                int i4 = i2 + 1;
                long j36 = jArr[i4];
                int i5 = i2 + 2;
                long j37 = jArr[i5];
                int i6 = i2 + 3;
                long j38 = jArr[i6];
                int i7 = i2 + 4;
                long j39 = jArr[i7];
                jArr[i3] = j35 ^ (j36 & j37);
                jArr[i4] = (j37 & j38) ^ j36;
                jArr[i5] = j37 ^ (j38 & j39);
                jArr[i6] = j38 ^ (j39 & j35);
                jArr[i7] = (j35 & j36) ^ j39;
                i2 += 5;
            } while (i2 < 25);
            jArr[0] = jArr[0] ^ f2742RC[i];
            i++;
        } while (i < 24);
    }

    private void pad() {
        updateBits(1L, 1);
        if (this.rateBits >= this.rateSizeBits) {
            keccak(this.state);
            this.rateBits = 0;
        }
        this.rateBits = this.rateSizeBits - 1;
        updateBits(1L, 1);
        keccak(this.state);
    }

    private int rateSizeBitsFor(int i) {
        if (i == 128) {
            return 1344;
        }
        if (i == 224) {
            return 1152;
        }
        if (i == 256) {
            return 1088;
        }
        if (i == 288) {
            return 1024;
        }
        if (i == 384) {
            return 832;
        }
        if (i == 512) {
            return 576;
        }
        throw new IllegalArgumentException("Invalid digestSizeBits: " + i + " ⊄ { 128, 224, 256, 288, 384, 512 }");
    }

    private void reset(int i) {
        reset(rateSizeBitsFor(i), i);
    }

    private void reset(int i, int i2) {
        if ((i2 * 2) + i != MAX_STATE_SIZE) {
            throw new IllegalArgumentException("Invalid rateSizebits + digestSizeBits * 2: " + i + " + " + i2 + " * 2 != " + MAX_STATE_SIZE);
        }
        if (i <= 0 || (i & 63) > 0) {
            throw new IllegalArgumentException("Invalid rateSizebits: " + i);
        }
        int i3 = 0;
        while (true) {
            int i4 = i3;
            if (i4 >= 25) {
                this.rateBits = 0;
                this.rateSizeBits = i;
                this.digestSizeBits = i2;
                this.padded = false;
                return;
            }
            this.state[i4] = 0;
            i3 = i4 + 1;
        }
    }

    private void squeeze() {
        keccak(this.state);
    }

    private void update(ByteBuffer byteBuffer) {
        long j;
        int i;
        int i2;
        int i3;
        long j2;
        int i4;
        int remaining = byteBuffer.remaining();
        if (remaining <= 0) {
            return;
        }
        if (this.padded) {
            throw new IllegalStateException("Cannot update while padded");
        }
        int i5 = this.rateBits;
        if ((i5 & 7) > 0) {
            throw new IllegalStateException("Cannot update while in bit-mode");
        }
        long[] jArr = this.state;
        int i6 = i5 >>> 3;
        int i7 = i6 & 7;
        int i8 = remaining;
        int i9 = i6;
        if (i7 > 0) {
            int i10 = 8 - i7;
            int i11 = i10;
            if (i10 > remaining) {
                i11 = remaining;
            }
            int i12 = i6 >>> 3;
            long j3 = jArr[i12];
            int i13 = i6 + i11;
            int i14 = remaining - i11;
            int i15 = i7 << 3;
            int i16 = i15;
            do {
                j2 = j3 ^ ((byteBuffer.get() & 255) << i16);
                i4 = i16 + 8;
                i16 = i4;
                j3 = j2;
            } while (i4 < i15 + (i11 << 3));
            jArr[i12] = j2;
            if (i14 > 0) {
                this.rateBits = i13 << 3;
                return;
            } else {
                i9 = i13;
                i8 = i14;
            }
        }
        int i17 = i9 >>> 3;
        int i18 = this.rateSizeBits >>> 6;
        int i19 = i8 >>> 3;
        int i20 = i8;
        int i21 = i17;
        if (i19 > 0) {
            ByteOrder order = byteBuffer.order();
            try {
                byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
                int i22 = i19;
                do {
                    int i23 = i17;
                    if (i17 >= i18) {
                        keccak(jArr);
                        i23 = 0;
                    }
                    int i24 = i18 - i23;
                    int i25 = i24;
                    if (i24 > i22) {
                        i25 = i22;
                    }
                    i2 = i22 - i25;
                    int i26 = i23;
                    do {
                        jArr[i26] = jArr[i26] ^ byteBuffer.getLong();
                        i3 = i26 + 1;
                        i26 = i3;
                    } while (i3 < i25 + i23);
                    i17 = i3;
                    i22 = i2;
                } while (i2 > 0);
                byteBuffer.order(order);
                int i27 = i8 & 7;
                i20 = i27;
                i21 = i3;
                if (i27 <= 0) {
                    this.rateBits = i3 << 6;
                    return;
                }
            } catch (Throwable th) {
                byteBuffer.order(order);
                throw th;
            }
        }
        int i28 = i21;
        if (i21 >= i18) {
            keccak(jArr);
            i28 = 0;
        }
        long j4 = jArr[i28];
        int i29 = i20 << 3;
        int i30 = 0;
        do {
            j = j4 ^ ((byteBuffer.get() & 255) << i30);
            i = i30 + 8;
            j4 = j;
            i30 = i;
        } while (i < i29);
        jArr[i28] = j;
        this.rateBits = (i28 << 6) | i29;
    }

    private void updateBits(long j, int i) {
        if (i < 0 || i > 64) {
            throw new IllegalArgumentException("Invalid valueBits: 0 < " + i + " > 64");
        }
        if (i <= 0) {
            return;
        }
        if (this.padded) {
            throw new IllegalStateException("Cannot update while padded");
        }
        long[] jArr = this.state;
        int i2 = this.rateBits;
        int i3 = i2 & 63;
        int i4 = i2;
        long j2 = j;
        int i5 = i;
        if (i3 > 0) {
            int i6 = 64 - i3;
            int i7 = i6;
            if (i6 > i) {
                i7 = i;
            }
            int i8 = i2 >>> 6;
            jArr[i8] = jArr[i8] ^ ((((-1) >>> i7) & j) << i3);
            int i9 = i2 + i7;
            i5 = i - i7;
            if (i5 <= 0) {
                this.rateBits = i9;
                return;
            } else {
                j2 = j >>> i7;
                i4 = i9;
            }
        }
        int i10 = i4;
        if (i4 >= this.rateSizeBits) {
            keccak(jArr);
            i10 = 0;
        }
        int i11 = i10 >>> 6;
        jArr[i11] = (j2 & ((-1) >>> i5)) ^ jArr[i11];
        this.rateBits = i10 + i5;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ByteBuffer digest() {
        return digest(digestSize());
    }

    public void digest(byte[] bArr) {
        digest(ByteBuffer.wrap(bArr));
    }

    public byte[] digestArray() {
        return digestArray(digestSize());
    }

    public int rateSize() {
        return this.rateSizeBits >>> 3;
    }

    public void reset() {
        reset(this.rateSizeBits, this.digestSizeBits);
    }

    public String toString() {
        return "Keccak-" + this.digestSizeBits;
    }

    public void update(byte b) {
        updateBits(b & 255, 8);
    }

    public void update(byte[] bArr) {
        update(ByteBuffer.wrap(bArr));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void update(byte[] bArr, int i, int i2) {
        update(ByteBuffer.wrap(bArr, i, i2));
    }
}
