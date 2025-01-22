package com.google.zxing.aztec.encoder;

import com.google.zxing.common.BitArray;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonEncoder;

/* loaded from: classes08-dex2jar.jar:com/google/zxing/aztec/encoder/Encoder.class */
public final class Encoder {
    public static final int DEFAULT_AZTEC_LAYERS = 0;
    public static final int DEFAULT_EC_PERCENT = 33;
    private static final int MAX_NB_BITS = 32;
    private static final int MAX_NB_BITS_COMPACT = 4;
    private static final int[] WORD_SIZE = {4, 6, 6, 8, 8, 8, 8, 8, 8, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12};

    private Encoder() {
    }

    private static int[] bitsToWords(BitArray bitArray, int i, int i2) {
        int[] iArr = new int[i2];
        int size = bitArray.getSize() / i;
        int i3 = 0;
        while (true) {
            int i4 = i3;
            if (i4 >= size) {
                return iArr;
            }
            int i5 = 0;
            for (int i6 = 0; i6 < i; i6++) {
                i5 |= bitArray.get((i4 * i) + i6) ? 1 << ((i - i6) - 1) : 0;
            }
            iArr[i4] = i5;
            i3 = i4 + 1;
        }
    }

    private static void drawBullsEye(BitMatrix bitMatrix, int i, int i2) {
        int i3 = 0;
        while (true) {
            int i4 = i3;
            if (i4 >= i2) {
                int i5 = i - i2;
                bitMatrix.set(i5, i5);
                int i6 = i5 + 1;
                bitMatrix.set(i6, i5);
                bitMatrix.set(i5, i6);
                int i7 = i + i2;
                bitMatrix.set(i7, i5);
                bitMatrix.set(i7, i6);
                bitMatrix.set(i7, i7 - 1);
                return;
            }
            int i8 = i - i4;
            int i9 = i8;
            while (true) {
                int i10 = i9;
                int i11 = i + i4;
                if (i10 <= i11) {
                    bitMatrix.set(i10, i8);
                    bitMatrix.set(i10, i11);
                    bitMatrix.set(i8, i10);
                    bitMatrix.set(i11, i10);
                    i9 = i10 + 1;
                }
            }
            i3 = i4 + 2;
        }
    }

    private static void drawModeMessage(BitMatrix bitMatrix, boolean z, int i, BitArray bitArray) {
        int i2 = i / 2;
        if (!z) {
            for (int i3 = 0; i3 < 10; i3++) {
                int i4 = (i2 - 5) + i3 + (i3 / 5);
                if (bitArray.get(i3)) {
                    bitMatrix.set(i4, i2 - 7);
                }
                if (bitArray.get(i3 + 10)) {
                    bitMatrix.set(i2 + 7, i4);
                }
                if (bitArray.get(29 - i3)) {
                    bitMatrix.set(i4, i2 + 7);
                }
                if (bitArray.get(39 - i3)) {
                    bitMatrix.set(i2 - 7, i4);
                }
            }
            return;
        }
        int i5 = 0;
        while (true) {
            int i6 = i5;
            if (i6 >= 7) {
                return;
            }
            int i7 = (i2 - 3) + i6;
            if (bitArray.get(i6)) {
                bitMatrix.set(i7, i2 - 5);
            }
            if (bitArray.get(i6 + 7)) {
                bitMatrix.set(i2 + 5, i7);
            }
            if (bitArray.get(20 - i6)) {
                bitMatrix.set(i7, i2 + 5);
            }
            if (bitArray.get(27 - i6)) {
                bitMatrix.set(i2 - 5, i7);
            }
            i5 = i6 + 1;
        }
    }

    public static AztecCode encode(byte[] bArr) {
        return encode(bArr, 33, 0);
    }

    public static AztecCode encode(byte[] bArr, int i, int i2) {
        BitArray bitArray;
        int i3;
        int i4;
        int i5;
        boolean z;
        int i6;
        BitArray encode = new HighLevelEncoder(bArr).encode();
        int size = ((encode.getSize() * i) / 100) + 11;
        int size2 = encode.getSize();
        if (i2 == 0) {
            bitArray = null;
            int i7 = 0;
            int i8 = 0;
            while (i7 <= 32) {
                boolean z2 = i7 <= 3;
                int i9 = z2 ? i7 + 1 : i7;
                int i10 = totalBitsInLayer(i9, z2);
                int i11 = i8;
                BitArray bitArray2 = bitArray;
                if (size2 + size <= i10) {
                    int[] iArr = WORD_SIZE;
                    int i12 = i8;
                    if (i8 != iArr[i9]) {
                        i12 = iArr[i9];
                        bitArray = stuffBits(encode, i12);
                    }
                    if (z2) {
                        i11 = i12;
                        bitArray2 = bitArray;
                        if (bitArray.getSize() > (i12 << 6)) {
                            continue;
                        }
                    }
                    if (bitArray.getSize() + size > i10 - (i10 % i12)) {
                        i11 = i12;
                        bitArray2 = bitArray;
                    } else {
                        i3 = i10;
                        i4 = i12;
                        i5 = i9;
                        z = z2;
                    }
                }
                i7++;
                i8 = i11;
                bitArray = bitArray2;
            }
            throw new IllegalArgumentException("Data too large for an Aztec code");
        }
        boolean z3 = i2 < 0;
        int abs = Math.abs(i2);
        if (abs > (z3 ? 4 : 32)) {
            throw new IllegalArgumentException(String.format("Illegal value %s for layers", Integer.valueOf(i2)));
        }
        int i13 = totalBitsInLayer(abs, z3);
        int i14 = WORD_SIZE[abs];
        BitArray stuffBits = stuffBits(encode, i14);
        if (stuffBits.getSize() + size > i13 - (i13 % i14)) {
            throw new IllegalArgumentException("Data to large for user specified layer");
        }
        bitArray = stuffBits;
        z = z3;
        i3 = i13;
        i5 = abs;
        i4 = i14;
        if (z3) {
            if (stuffBits.getSize() > (i14 << 6)) {
                throw new IllegalArgumentException("Data to large for user specified layer");
            }
            bitArray = stuffBits;
            z = z3;
            i3 = i13;
            i5 = abs;
            i4 = i14;
        }
        BitArray generateCheckWords = generateCheckWords(bitArray, i3, i4);
        int size3 = bitArray.getSize() / i4;
        BitArray generateModeMessage = generateModeMessage(z, i5, size3);
        int i15 = (z ? 11 : 14) + (i5 << 2);
        int[] iArr2 = new int[i15];
        if (!z) {
            int i16 = i15 / 2;
            int i17 = i15 + 1 + (((i16 - 1) / 15) * 2);
            int i18 = i17 / 2;
            int i19 = 0;
            while (true) {
                int i20 = i19;
                i6 = i17;
                if (i20 >= i16) {
                    break;
                }
                int i21 = (i20 / 15) + i20;
                iArr2[(i16 - i20) - 1] = (i18 - i21) - 1;
                iArr2[i16 + i20] = i21 + i18 + 1;
                i19 = i20 + 1;
            }
        } else {
            int i22 = 0;
            while (true) {
                int i23 = i22;
                if (i23 >= iArr2.length) {
                    break;
                }
                iArr2[i23] = i23;
                i22 = i23 + 1;
            }
            i6 = i15;
        }
        BitMatrix bitMatrix = new BitMatrix(i6);
        int i24 = 0;
        for (int i25 = 0; i25 < i5; i25++) {
            int i26 = ((i5 - i25) << 2) + (z ? 9 : 12);
            int i27 = 0;
            while (true) {
                int i28 = i27;
                if (i28 < i26) {
                    int i29 = i28 << 1;
                    for (int i30 = 0; i30 < 2; i30++) {
                        if (generateCheckWords.get(i24 + i29 + i30)) {
                            int i31 = i25 << 1;
                            bitMatrix.set(iArr2[i31 + i30], iArr2[i31 + i28]);
                        }
                        if (generateCheckWords.get((i26 << 1) + i24 + i29 + i30)) {
                            int i32 = i25 << 1;
                            bitMatrix.set(iArr2[i32 + i28], iArr2[((i15 - 1) - i32) - i30]);
                        }
                        if (generateCheckWords.get((i26 << 2) + i24 + i29 + i30)) {
                            int i33 = (i15 - 1) - (i25 << 1);
                            bitMatrix.set(iArr2[i33 - i30], iArr2[i33 - i28]);
                        }
                        if (generateCheckWords.get((i26 * 6) + i24 + i29 + i30)) {
                            int i34 = i25 << 1;
                            bitMatrix.set(iArr2[((i15 - 1) - i34) - i28], iArr2[i34 + i30]);
                        }
                    }
                    i27 = i28 + 1;
                }
            }
            i24 += i26 << 3;
        }
        drawModeMessage(bitMatrix, z, i6, generateModeMessage);
        if (!z) {
            int i35 = i6 / 2;
            drawBullsEye(bitMatrix, i35, 7);
            int i36 = 0;
            int i37 = 0;
            while (true) {
                int i38 = i37;
                if (i36 >= (i15 / 2) - 1) {
                    break;
                }
                int i39 = i35 & 1;
                while (true) {
                    int i40 = i39;
                    if (i40 < i6) {
                        int i41 = i35 - i38;
                        bitMatrix.set(i41, i40);
                        int i42 = i35 + i38;
                        bitMatrix.set(i42, i40);
                        bitMatrix.set(i40, i41);
                        bitMatrix.set(i40, i42);
                        i39 = i40 + 2;
                    }
                }
                i36 += 15;
                i37 = i38 + 16;
            }
        } else {
            drawBullsEye(bitMatrix, i6 / 2, 5);
        }
        AztecCode aztecCode = new AztecCode();
        aztecCode.setCompact(z);
        aztecCode.setSize(i6);
        aztecCode.setLayers(i5);
        aztecCode.setCodeWords(size3);
        aztecCode.setMatrix(bitMatrix);
        return aztecCode;
    }

    private static BitArray generateCheckWords(BitArray bitArray, int i, int i2) {
        int size = bitArray.getSize() / i2;
        ReedSolomonEncoder reedSolomonEncoder = new ReedSolomonEncoder(getGF(i2));
        int i3 = i / i2;
        int[] bitsToWords = bitsToWords(bitArray, i2, i3);
        reedSolomonEncoder.encode(bitsToWords, i3 - size);
        BitArray bitArray2 = new BitArray();
        bitArray2.appendBits(0, i % i2);
        int length = bitsToWords.length;
        int i4 = 0;
        while (true) {
            int i5 = i4;
            if (i5 >= length) {
                return bitArray2;
            }
            bitArray2.appendBits(bitsToWords[i5], i2);
            i4 = i5 + 1;
        }
    }

    static BitArray generateModeMessage(boolean z, int i, int i2) {
        BitArray bitArray = new BitArray();
        if (z) {
            bitArray.appendBits(i - 1, 2);
            bitArray.appendBits(i2 - 1, 6);
            return generateCheckWords(bitArray, 28, 4);
        }
        bitArray.appendBits(i - 1, 5);
        bitArray.appendBits(i2 - 1, 11);
        return generateCheckWords(bitArray, 40, 4);
    }

    private static GenericGF getGF(int i) {
        if (i == 4) {
            return GenericGF.AZTEC_PARAM;
        }
        if (i == 6) {
            return GenericGF.AZTEC_DATA_6;
        }
        if (i == 8) {
            return GenericGF.AZTEC_DATA_8;
        }
        if (i == 10) {
            return GenericGF.AZTEC_DATA_10;
        }
        if (i == 12) {
            return GenericGF.AZTEC_DATA_12;
        }
        throw new IllegalArgumentException("Unsupported word size " + i);
    }

    static BitArray stuffBits(BitArray bitArray, int i) {
        int i2;
        int i3;
        BitArray bitArray2 = new BitArray();
        int size = bitArray.getSize();
        int i4 = (1 << i) - 2;
        int i5 = 0;
        while (true) {
            int i6 = i5;
            if (i6 >= size) {
                return bitArray2;
            }
            int i7 = 0;
            int i8 = 0;
            while (true) {
                i2 = i8;
                if (i7 >= i) {
                    break;
                }
                int i9 = i6 + i7;
                if (i9 < size) {
                    i3 = i2;
                    if (!bitArray.get(i9)) {
                        i7++;
                        i8 = i3;
                    }
                }
                i3 = i2 | (1 << ((i - 1) - i7));
                i7++;
                i8 = i3;
            }
            int i10 = i2 & i4;
            if (i10 == i4) {
                bitArray2.appendBits(i10, i);
            } else if (i10 == 0) {
                bitArray2.appendBits(i2 | 1, i);
            } else {
                bitArray2.appendBits(i2, i);
                i5 = i6 + i;
            }
            i6--;
            i5 = i6 + i;
        }
    }

    private static int totalBitsInLayer(int i, boolean z) {
        return ((z ? 88 : 112) + (i << 4)) * i;
    }
}
