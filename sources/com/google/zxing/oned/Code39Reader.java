package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.Arrays;
import java.util.Map;

/* loaded from: classes08-dex2jar.jar:com/google/zxing/oned/Code39Reader.class */
public final class Code39Reader extends OneDReader {
    static final String ALPHABET_STRING = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. *$/+%";
    static final int ASTERISK_ENCODING;
    static final int[] CHARACTER_ENCODINGS;
    private static final String CHECK_DIGIT_STRING = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%";
    private final int[] counters;
    private final StringBuilder decodeRowResult;
    private final boolean extendedMode;
    private final boolean usingCheckDigit;

    static {
        int[] iArr = {52, 289, 97, 352, 49, 304, 112, 37, 292, 100, 265, 73, 328, 25, 280, 88, 13, 268, 76, 28, 259, 67, 322, 19, 274, 82, 7, 262, 70, 22, 385, 193, 448, 145, 400, 208, 133, 388, 196, 148, 168, 162, 138, 42};
        CHARACTER_ENCODINGS = iArr;
        ASTERISK_ENCODING = iArr[39];
    }

    public Code39Reader() {
        this(false);
    }

    public Code39Reader(boolean z) {
        this(z, false);
    }

    public Code39Reader(boolean z, boolean z2) {
        this.usingCheckDigit = z;
        this.extendedMode = z2;
        this.decodeRowResult = new StringBuilder(20);
        this.counters = new int[9];
    }

    /* JADX WARN: Code restructure failed: missing block: B:46:0x00ac, code lost:            throw com.google.zxing.FormatException.getFormatInstance();     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00d8, code lost:            throw com.google.zxing.FormatException.getFormatInstance();     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String decodeExtended(java.lang.CharSequence r4) throws com.google.zxing.FormatException {
        /*
            Method dump skipped, instructions count: 263
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.Code39Reader.decodeExtended(java.lang.CharSequence):java.lang.String");
    }

    private static int[] findAsteriskPattern(BitArray bitArray, int[] iArr) throws NotFoundException {
        int i;
        int i2;
        int i3;
        int size = bitArray.getSize();
        int nextSet = bitArray.getNextSet(0);
        int length = iArr.length;
        int i4 = nextSet;
        boolean z = false;
        int i5 = 0;
        while (nextSet < size) {
            if (bitArray.get(nextSet) ^ z) {
                iArr[i5] = iArr[i5] + 1;
                i3 = i4;
            } else {
                int i6 = length - 1;
                if (i5 != i6) {
                    int i7 = i5 + 1;
                    i = i4;
                    i2 = i7;
                } else {
                    if (toNarrowWidePattern(iArr) == ASTERISK_ENCODING && bitArray.isRange(Math.max(0, i4 - ((nextSet - i4) / 2)), i4, false)) {
                        return new int[]{i4, nextSet};
                    }
                    int i8 = i4 + iArr[0] + iArr[1];
                    int i9 = length - 2;
                    System.arraycopy(iArr, 2, iArr, 0, i9);
                    iArr[i9] = 0;
                    iArr[i6] = 0;
                    i2 = i5 - 1;
                    i = i8;
                }
                iArr[i2] = 1;
                z = !z;
                i3 = i;
                i5 = i2;
            }
            nextSet++;
            i4 = i3;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static char patternToChar(int i) throws NotFoundException {
        int i2 = 0;
        while (true) {
            int i3 = i2;
            int[] iArr = CHARACTER_ENCODINGS;
            if (i3 >= iArr.length) {
                throw NotFoundException.getNotFoundInstance();
            }
            if (iArr[i3] == i) {
                return ALPHABET_STRING.charAt(i3);
            }
            i2 = i3 + 1;
        }
    }

    private static int toNarrowWidePattern(int[] iArr) {
        int i;
        int i2;
        int length = iArr.length;
        int i3 = 0;
        while (true) {
            int i4 = i3;
            int length2 = iArr.length;
            int i5 = 0;
            int i6 = Integer.MAX_VALUE;
            while (true) {
                i = i6;
                if (i5 >= length2) {
                    break;
                }
                int i7 = iArr[i5];
                int i8 = i;
                if (i7 < i) {
                    i8 = i;
                    if (i7 > i4) {
                        i8 = i7;
                    }
                }
                i5++;
                i6 = i8;
            }
            int i9 = 0;
            int i10 = 0;
            int i11 = 0;
            int i12 = 0;
            while (true) {
                i2 = i12;
                if (i9 >= length) {
                    break;
                }
                int i13 = iArr[i9];
                int i14 = i10;
                int i15 = i11;
                int i16 = i2;
                if (i13 > i) {
                    i15 = i11 | (1 << ((length - 1) - i9));
                    i14 = i10 + 1;
                    i16 = i2 + i13;
                }
                i9++;
                i10 = i14;
                i11 = i15;
                i12 = i16;
            }
            if (i10 == 3) {
                int i17 = i10;
                int i18 = 0;
                while (i18 < length && i17 > 0) {
                    int i19 = iArr[i18];
                    int i20 = i17;
                    if (i19 > i) {
                        i20 = i17 - 1;
                        if ((i19 << 1) >= i2) {
                            return -1;
                        }
                    }
                    i18++;
                    i17 = i20;
                }
                return i11;
            }
            if (i10 <= 3) {
                return -1;
            }
            i3 = i;
        }
    }

    @Override // com.google.zxing.oned.OneDReader
    public Result decodeRow(int i, BitArray bitArray, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException {
        int[] iArr = this.counters;
        Arrays.fill(iArr, 0);
        StringBuilder sb = this.decodeRowResult;
        sb.setLength(0);
        int nextSet = bitArray.getNextSet(findAsteriskPattern(bitArray, iArr)[1]);
        int size = bitArray.getSize();
        while (true) {
            recordPattern(bitArray, nextSet, iArr);
            int narrowWidePattern = toNarrowWidePattern(iArr);
            if (narrowWidePattern < 0) {
                throw NotFoundException.getNotFoundInstance();
            }
            char patternToChar = patternToChar(narrowWidePattern);
            sb.append(patternToChar);
            int length = iArr.length;
            int i2 = nextSet;
            int i3 = 0;
            while (true) {
                int i4 = i3;
                if (i4 >= length) {
                    break;
                }
                i2 += iArr[i4];
                i3 = i4 + 1;
            }
            int nextSet2 = bitArray.getNextSet(i2);
            if (patternToChar == '*') {
                sb.setLength(sb.length() - 1);
                int i5 = 0;
                for (int i6 : iArr) {
                    i5 += i6;
                }
                if (nextSet2 != size && (((nextSet2 - nextSet) - i5) << 1) < i5) {
                    throw NotFoundException.getNotFoundInstance();
                }
                if (this.usingCheckDigit) {
                    int length2 = sb.length() - 1;
                    int i7 = 0;
                    for (int i8 = 0; i8 < length2; i8++) {
                        i7 += CHECK_DIGIT_STRING.indexOf(this.decodeRowResult.charAt(i8));
                    }
                    if (sb.charAt(length2) != CHECK_DIGIT_STRING.charAt(i7 % 43)) {
                        throw ChecksumException.getChecksumInstance();
                    }
                    sb.setLength(length2);
                }
                if (sb.length() == 0) {
                    throw NotFoundException.getNotFoundInstance();
                }
                String decodeExtended = this.extendedMode ? decodeExtended(sb) : sb.toString();
                float f = (r0[1] + r0[0]) / 2.0f;
                float f2 = i;
                return new Result(decodeExtended, null, new ResultPoint[]{new ResultPoint(f, f2), new ResultPoint(nextSet + (i5 / 2.0f), f2)}, BarcodeFormat.CODE_39);
            }
            nextSet = nextSet2;
        }
    }
}
