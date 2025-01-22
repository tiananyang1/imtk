package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.Map;

/* loaded from: classes08-dex2jar.jar:com/google/zxing/oned/ITFReader.class */
public final class ITFReader extends OneDReader {
    private static final float MAX_AVG_VARIANCE = 0.38f;
    private static final float MAX_INDIVIDUAL_VARIANCE = 0.78f;

    /* renamed from: N */
    private static final int f19N = 1;

    /* renamed from: W */
    private static final int f20W = 3;
    private int narrowLineWidth = -1;
    private static final int[] DEFAULT_ALLOWED_LENGTHS = {6, 8, 10, 12, 14};
    private static final int[] START_PATTERN = {1, 1, 1, 1};
    private static final int[] END_PATTERN_REVERSED = {1, 1, 3};
    static final int[][] PATTERNS = {new int[]{1, 1, 3, 3, 1}, new int[]{3, 1, 1, 1, 3}, new int[]{1, 3, 1, 1, 3}, new int[]{3, 3, 1, 1, 1}, new int[]{1, 1, 3, 1, 3}, new int[]{3, 1, 3, 1, 1}, new int[]{1, 3, 3, 1, 1}, new int[]{1, 1, 1, 3, 3}, new int[]{3, 1, 1, 3, 1}, new int[]{1, 3, 1, 3, 1}};

    private static int decodeDigit(int[] iArr) throws NotFoundException {
        int length = PATTERNS.length;
        float f = 0.38f;
        int i = -1;
        int i2 = 0;
        while (i2 < length) {
            float patternMatchVariance = patternMatchVariance(iArr, PATTERNS[i2], MAX_INDIVIDUAL_VARIANCE);
            float f2 = f;
            if (patternMatchVariance < f) {
                i = i2;
                f2 = patternMatchVariance;
            }
            i2++;
            f = f2;
        }
        if (i >= 0) {
            return i;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private int[] decodeEnd(BitArray bitArray) throws NotFoundException {
        bitArray.reverse();
        try {
            int[] findGuardPattern = findGuardPattern(bitArray, skipWhiteSpace(bitArray), END_PATTERN_REVERSED);
            validateQuietZone(bitArray, findGuardPattern[0]);
            int i = findGuardPattern[0];
            findGuardPattern[0] = bitArray.getSize() - findGuardPattern[1];
            findGuardPattern[1] = bitArray.getSize() - i;
            return findGuardPattern;
        } finally {
            bitArray.reverse();
        }
    }

    private static void decodeMiddle(BitArray bitArray, int i, int i2, StringBuilder sb) throws NotFoundException {
        int[] iArr = new int[10];
        int[] iArr2 = new int[5];
        int[] iArr3 = new int[5];
        while (i < i2) {
            recordPattern(bitArray, i, iArr);
            int i3 = 0;
            while (true) {
                int i4 = i3;
                if (i4 >= 5) {
                    break;
                }
                int i5 = i4 * 2;
                iArr2[i4] = iArr[i5];
                iArr3[i4] = iArr[i5 + 1];
                i3 = i4 + 1;
            }
            sb.append((char) (decodeDigit(iArr2) + 48));
            sb.append((char) (decodeDigit(iArr3) + 48));
            int i6 = i;
            int i7 = 0;
            while (true) {
                int i8 = i7;
                i = i6;
                if (i8 < 10) {
                    i6 += iArr[i8];
                    i7 = i8 + 1;
                }
            }
        }
    }

    private int[] decodeStart(BitArray bitArray) throws NotFoundException {
        int[] findGuardPattern = findGuardPattern(bitArray, skipWhiteSpace(bitArray), START_PATTERN);
        this.narrowLineWidth = (findGuardPattern[1] - findGuardPattern[0]) / 4;
        validateQuietZone(bitArray, findGuardPattern[0]);
        return findGuardPattern;
    }

    private static int[] findGuardPattern(BitArray bitArray, int i, int[] iArr) throws NotFoundException {
        int i2;
        int i3;
        int i4;
        int length = iArr.length;
        int[] iArr2 = new int[length];
        int size = bitArray.getSize();
        boolean z = false;
        int i5 = i;
        int i6 = i;
        int i7 = 0;
        while (i5 < size) {
            if (bitArray.get(i5) ^ z) {
                iArr2[i7] = iArr2[i7] + 1;
                i4 = i6;
            } else {
                int i8 = length - 1;
                if (i7 != i8) {
                    int i9 = i7 + 1;
                    i2 = i6;
                    i3 = i9;
                } else {
                    if (patternMatchVariance(iArr2, iArr, MAX_INDIVIDUAL_VARIANCE) < MAX_AVG_VARIANCE) {
                        return new int[]{i6, i5};
                    }
                    int i10 = i6 + iArr2[0] + iArr2[1];
                    int i11 = length - 2;
                    System.arraycopy(iArr2, 2, iArr2, 0, i11);
                    iArr2[i11] = 0;
                    iArr2[i8] = 0;
                    i3 = i7 - 1;
                    i2 = i10;
                }
                iArr2[i3] = 1;
                z = !z;
                i4 = i2;
                i7 = i3;
            }
            i5++;
            i6 = i4;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static int skipWhiteSpace(BitArray bitArray) throws NotFoundException {
        int size = bitArray.getSize();
        int nextSet = bitArray.getNextSet(0);
        if (nextSet != size) {
            return nextSet;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private void validateQuietZone(BitArray bitArray, int i) throws NotFoundException {
        int i2 = this.narrowLineWidth * 10;
        if (i2 >= i) {
            i2 = i;
        }
        while (true) {
            i--;
            if (i2 <= 0 || i < 0 || bitArray.get(i)) {
                break;
            } else {
                i2--;
            }
        }
        if (i2 != 0) {
            throw NotFoundException.getNotFoundInstance();
        }
    }

    @Override // com.google.zxing.oned.OneDReader
    public Result decodeRow(int i, BitArray bitArray, Map<DecodeHintType, ?> map) throws FormatException, NotFoundException {
        int i2;
        boolean z;
        int[] decodeStart = decodeStart(bitArray);
        int[] decodeEnd = decodeEnd(bitArray);
        StringBuilder sb = new StringBuilder(20);
        decodeMiddle(bitArray, decodeStart[1], decodeEnd[0], sb);
        String sb2 = sb.toString();
        int[] iArr = map != null ? (int[]) map.get(DecodeHintType.ALLOWED_LENGTHS) : null;
        int[] iArr2 = iArr;
        if (iArr == null) {
            iArr2 = DEFAULT_ALLOWED_LENGTHS;
        }
        int length = sb2.length();
        int length2 = iArr2.length;
        int i3 = 0;
        int i4 = 0;
        while (true) {
            i2 = i4;
            if (i3 >= length2) {
                z = false;
                break;
            }
            int i5 = iArr2[i3];
            if (length == i5) {
                z = true;
                break;
            }
            int i6 = i2;
            if (i5 > i2) {
                i6 = i5;
            }
            i3++;
            i4 = i6;
        }
        boolean z2 = z;
        if (!z) {
            z2 = z;
            if (length > i2) {
                z2 = true;
            }
        }
        if (!z2) {
            throw FormatException.getFormatInstance();
        }
        float f = decodeStart[1];
        float f2 = i;
        return new Result(sb2, null, new ResultPoint[]{new ResultPoint(f, f2), new ResultPoint(decodeEnd[0], f2)}, BarcodeFormat.ITF);
    }
}
