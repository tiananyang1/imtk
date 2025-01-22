package com.google.zxing.pdf417.detector;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/* loaded from: classes08-dex2jar.jar:com/google/zxing/pdf417/detector/Detector.class */
public final class Detector {
    private static final int BARCODE_MIN_HEIGHT = 10;
    private static final float MAX_AVG_VARIANCE = 0.42f;
    private static final float MAX_INDIVIDUAL_VARIANCE = 0.8f;
    private static final int MAX_PATTERN_DRIFT = 5;
    private static final int MAX_PIXEL_DRIFT = 3;
    private static final int ROW_STEP = 5;
    private static final int SKIPPED_ROW_COUNT_MAX = 25;
    private static final int[] INDEXES_START_PATTERN = {0, 4, 1, 5};
    private static final int[] INDEXES_STOP_PATTERN = {6, 2, 7, 3};
    private static final int[] START_PATTERN = {8, 1, 1, 1, 1, 1, 1, 3};
    private static final int[] STOP_PATTERN = {7, 1, 1, 3, 1, 1, 1, 2, 1};

    private Detector() {
    }

    private static void copyToResult(ResultPoint[] resultPointArr, ResultPoint[] resultPointArr2, int[] iArr) {
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= iArr.length) {
                return;
            }
            resultPointArr[iArr[i2]] = resultPointArr2[i2];
            i = i2 + 1;
        }
    }

    public static PDF417DetectorResult detect(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map, boolean z) throws NotFoundException {
        BitMatrix blackMatrix = binaryBitmap.getBlackMatrix();
        List<ResultPoint[]> detect = detect(z, blackMatrix);
        BitMatrix bitMatrix = blackMatrix;
        List<ResultPoint[]> list = detect;
        if (detect.isEmpty()) {
            bitMatrix = blackMatrix.m3502clone();
            bitMatrix.rotate180();
            list = detect(z, bitMatrix);
        }
        return new PDF417DetectorResult(bitMatrix, list);
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0032, code lost:            if (r9 == false) goto L33;     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0035, code lost:            r0 = r0.iterator();     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0045, code lost:            if (r0.hasNext() == false) goto L38;     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0048, code lost:            r0 = (com.google.zxing.ResultPoint[]) r0.next();        r8 = r7;     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x005b, code lost:            if (r0[1] == null) goto L18;     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x005e, code lost:            r8 = (int) java.lang.Math.max(r7, r0[1].getY());     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x006d, code lost:            r7 = r8;     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0074, code lost:            if (r0[3] == null) goto L40;     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0077, code lost:            r7 = java.lang.Math.max(r8, (int) r0[3].getY());     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.util.List<com.google.zxing.ResultPoint[]> detect(boolean r4, com.google.zxing.common.BitMatrix r5) {
        /*
            Method dump skipped, instructions count: 220
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.detector.Detector.detect(boolean, com.google.zxing.common.BitMatrix):java.util.List");
    }

    private static int[] findGuardPattern(BitMatrix bitMatrix, int i, int i2, int i3, boolean z, int[] iArr, int[] iArr2) {
        Arrays.fill(iArr2, 0, iArr2.length, 0);
        int i4 = 0;
        while (true) {
            int i5 = i4;
            if (!bitMatrix.get(i, i2) || i <= 0 || i5 >= 3) {
                break;
            }
            i--;
            i4 = i5 + 1;
        }
        int length = iArr.length;
        int i6 = 0;
        int i7 = i;
        int i8 = i;
        while (i7 < i3) {
            if (bitMatrix.get(i7, i2) ^ z) {
                iArr2[i6] = iArr2[i6] + 1;
            } else {
                int i9 = length - 1;
                if (i6 != i9) {
                    i6++;
                } else {
                    if (patternMatchVariance(iArr2, iArr, MAX_INDIVIDUAL_VARIANCE) < MAX_AVG_VARIANCE) {
                        return new int[]{i8, i7};
                    }
                    i8 += iArr2[0] + iArr2[1];
                    int i10 = length - 2;
                    System.arraycopy(iArr2, 2, iArr2, 0, i10);
                    iArr2[i10] = 0;
                    iArr2[i9] = 0;
                    i6--;
                }
                iArr2[i6] = 1;
                z = !z;
            }
            i7++;
        }
        if (i6 != length - 1 || patternMatchVariance(iArr2, iArr, MAX_INDIVIDUAL_VARIANCE) >= MAX_AVG_VARIANCE) {
            return null;
        }
        return new int[]{i8, i7 - 1};
    }

    private static ResultPoint[] findRowsWithPattern(BitMatrix bitMatrix, int i, int i2, int i3, int i4, int[] iArr) {
        boolean z;
        int i5;
        int i6;
        int i7;
        ResultPoint[] resultPointArr = new ResultPoint[4];
        int[] iArr2 = new int[iArr.length];
        while (true) {
            if (i3 >= i) {
                z = false;
                i5 = i3;
                break;
            }
            int[] findGuardPattern = findGuardPattern(bitMatrix, i4, i3, i2, false, iArr, iArr2);
            if (findGuardPattern != null) {
                int i8 = i3;
                while (true) {
                    i7 = i8;
                    if (i8 <= 0) {
                        break;
                    }
                    i8--;
                    int[] findGuardPattern2 = findGuardPattern(bitMatrix, i4, i8, i2, false, iArr, iArr2);
                    if (findGuardPattern2 == null) {
                        i7 = i8 + 1;
                        break;
                    }
                    findGuardPattern = findGuardPattern2;
                }
                float f = i7;
                resultPointArr[0] = new ResultPoint(findGuardPattern[0], f);
                resultPointArr[1] = new ResultPoint(findGuardPattern[1], f);
                z = true;
                i5 = i7;
            } else {
                i3 += 5;
            }
        }
        int i9 = i5 + 1;
        int i10 = i9;
        if (z) {
            int[] iArr3 = {(int) resultPointArr[0].getX(), (int) resultPointArr[1].getX()};
            int i11 = i9;
            int i12 = 0;
            while (i11 < i) {
                int i13 = i12;
                int[] findGuardPattern3 = findGuardPattern(bitMatrix, iArr3[0], i11, i2, false, iArr, iArr2);
                if (findGuardPattern3 != null && Math.abs(iArr3[0] - findGuardPattern3[0]) < 5 && Math.abs(iArr3[1] - findGuardPattern3[1]) < 5) {
                    iArr3 = findGuardPattern3;
                    i6 = 0;
                } else {
                    if (i13 > 25) {
                        break;
                    }
                    i6 = i13 + 1;
                }
                i12 = i6;
                i11++;
            }
            i10 = i11 - (i12 + 1);
            float f2 = i10;
            resultPointArr[2] = new ResultPoint(iArr3[0], f2);
            resultPointArr[3] = new ResultPoint(iArr3[1], f2);
        }
        if (i10 - i5 < 10) {
            int i14 = 0;
            while (true) {
                int i15 = i14;
                if (i15 >= 4) {
                    break;
                }
                resultPointArr[i15] = null;
                i14 = i15 + 1;
            }
        }
        return resultPointArr;
    }

    private static ResultPoint[] findVertices(BitMatrix bitMatrix, int i, int i2) {
        int height = bitMatrix.getHeight();
        int width = bitMatrix.getWidth();
        ResultPoint[] resultPointArr = new ResultPoint[8];
        copyToResult(resultPointArr, findRowsWithPattern(bitMatrix, height, width, i, i2, START_PATTERN), INDEXES_START_PATTERN);
        if (resultPointArr[4] != null) {
            i2 = (int) resultPointArr[4].getX();
            i = (int) resultPointArr[4].getY();
        }
        copyToResult(resultPointArr, findRowsWithPattern(bitMatrix, height, width, i, i2, STOP_PATTERN), INDEXES_STOP_PATTERN);
        return resultPointArr;
    }

    private static float patternMatchVariance(int[] iArr, int[] iArr2, float f) {
        int length = iArr.length;
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            i += iArr[i3];
            i2 += iArr2[i3];
        }
        if (i < i2) {
            return Float.POSITIVE_INFINITY;
        }
        float f2 = i;
        float f3 = f2 / i2;
        float f4 = 0.0f;
        int i4 = 0;
        while (true) {
            int i5 = i4;
            if (i5 >= length) {
                return f4 / f2;
            }
            float f5 = iArr2[i5] * f3;
            float f6 = iArr[i5];
            float f7 = f6 > f5 ? f6 - f5 : f5 - f6;
            if (f7 > f * f3) {
                return Float.POSITIVE_INFINITY;
            }
            f4 += f7;
            i4 = i5 + 1;
        }
    }
}
