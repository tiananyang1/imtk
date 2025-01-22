package com.google.zxing.oned;

import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

/* loaded from: classes08-dex2jar.jar:com/google/zxing/oned/Code128Reader.class */
public final class Code128Reader extends OneDReader {
    private static final int CODE_CODE_A = 101;
    private static final int CODE_CODE_B = 100;
    private static final int CODE_CODE_C = 99;
    private static final int CODE_FNC_1 = 102;
    private static final int CODE_FNC_2 = 97;
    private static final int CODE_FNC_3 = 96;
    private static final int CODE_FNC_4_A = 101;
    private static final int CODE_FNC_4_B = 100;
    static final int[][] CODE_PATTERNS = {new int[]{2, 1, 2, 2, 2, 2}, new int[]{2, 2, 2, 1, 2, 2}, new int[]{2, 2, 2, 2, 2, 1}, new int[]{1, 2, 1, 2, 2, 3}, new int[]{1, 2, 1, 3, 2, 2}, new int[]{1, 3, 1, 2, 2, 2}, new int[]{1, 2, 2, 2, 1, 3}, new int[]{1, 2, 2, 3, 1, 2}, new int[]{1, 3, 2, 2, 1, 2}, new int[]{2, 2, 1, 2, 1, 3}, new int[]{2, 2, 1, 3, 1, 2}, new int[]{2, 3, 1, 2, 1, 2}, new int[]{1, 1, 2, 2, 3, 2}, new int[]{1, 2, 2, 1, 3, 2}, new int[]{1, 2, 2, 2, 3, 1}, new int[]{1, 1, 3, 2, 2, 2}, new int[]{1, 2, 3, 1, 2, 2}, new int[]{1, 2, 3, 2, 2, 1}, new int[]{2, 2, 3, 2, 1, 1}, new int[]{2, 2, 1, 1, 3, 2}, new int[]{2, 2, 1, 2, 3, 1}, new int[]{2, 1, 3, 2, 1, 2}, new int[]{2, 2, 3, 1, 1, 2}, new int[]{3, 1, 2, 1, 3, 1}, new int[]{3, 1, 1, 2, 2, 2}, new int[]{3, 2, 1, 1, 2, 2}, new int[]{3, 2, 1, 2, 2, 1}, new int[]{3, 1, 2, 2, 1, 2}, new int[]{3, 2, 2, 1, 1, 2}, new int[]{3, 2, 2, 2, 1, 1}, new int[]{2, 1, 2, 1, 2, 3}, new int[]{2, 1, 2, 3, 2, 1}, new int[]{2, 3, 2, 1, 2, 1}, new int[]{1, 1, 1, 3, 2, 3}, new int[]{1, 3, 1, 1, 2, 3}, new int[]{1, 3, 1, 3, 2, 1}, new int[]{1, 1, 2, 3, 1, 3}, new int[]{1, 3, 2, 1, 1, 3}, new int[]{1, 3, 2, 3, 1, 1}, new int[]{2, 1, 1, 3, 1, 3}, new int[]{2, 3, 1, 1, 1, 3}, new int[]{2, 3, 1, 3, 1, 1}, new int[]{1, 1, 2, 1, 3, 3}, new int[]{1, 1, 2, 3, 3, 1}, new int[]{1, 3, 2, 1, 3, 1}, new int[]{1, 1, 3, 1, 2, 3}, new int[]{1, 1, 3, 3, 2, 1}, new int[]{1, 3, 3, 1, 2, 1}, new int[]{3, 1, 3, 1, 2, 1}, new int[]{2, 1, 1, 3, 3, 1}, new int[]{2, 3, 1, 1, 3, 1}, new int[]{2, 1, 3, 1, 1, 3}, new int[]{2, 1, 3, 3, 1, 1}, new int[]{2, 1, 3, 1, 3, 1}, new int[]{3, 1, 1, 1, 2, 3}, new int[]{3, 1, 1, 3, 2, 1}, new int[]{3, 3, 1, 1, 2, 1}, new int[]{3, 1, 2, 1, 1, 3}, new int[]{3, 1, 2, 3, 1, 1}, new int[]{3, 3, 2, 1, 1, 1}, new int[]{3, 1, 4, 1, 1, 1}, new int[]{2, 2, 1, 4, 1, 1}, new int[]{4, 3, 1, 1, 1, 1}, new int[]{1, 1, 1, 2, 2, 4}, new int[]{1, 1, 1, 4, 2, 2}, new int[]{1, 2, 1, 1, 2, 4}, new int[]{1, 2, 1, 4, 2, 1}, new int[]{1, 4, 1, 1, 2, 2}, new int[]{1, 4, 1, 2, 2, 1}, new int[]{1, 1, 2, 2, 1, 4}, new int[]{1, 1, 2, 4, 1, 2}, new int[]{1, 2, 2, 1, 1, 4}, new int[]{1, 2, 2, 4, 1, 1}, new int[]{1, 4, 2, 1, 1, 2}, new int[]{1, 4, 2, 2, 1, 1}, new int[]{2, 4, 1, 2, 1, 1}, new int[]{2, 2, 1, 1, 1, 4}, new int[]{4, 1, 3, 1, 1, 1}, new int[]{2, 4, 1, 1, 1, 2}, new int[]{1, 3, 4, 1, 1, 1}, new int[]{1, 1, 1, 2, 4, 2}, new int[]{1, 2, 1, 1, 4, 2}, new int[]{1, 2, 1, 2, 4, 1}, new int[]{1, 1, 4, 2, 1, 2}, new int[]{1, 2, 4, 1, 1, 2}, new int[]{1, 2, 4, 2, 1, 1}, new int[]{4, 1, 1, 2, 1, 2}, new int[]{4, 2, 1, 1, 1, 2}, new int[]{4, 2, 1, 2, 1, 1}, new int[]{2, 1, 2, 1, 4, 1}, new int[]{2, 1, 4, 1, 2, 1}, new int[]{4, 1, 2, 1, 2, 1}, new int[]{1, 1, 1, 1, 4, 3}, new int[]{1, 1, 1, 3, 4, 1}, new int[]{1, 3, 1, 1, 4, 1}, new int[]{1, 1, 4, 1, 1, 3}, new int[]{1, 1, 4, 3, 1, 1}, new int[]{4, 1, 1, 1, 1, 3}, new int[]{4, 1, 1, 3, 1, 1}, new int[]{1, 1, 3, 1, 4, 1}, new int[]{1, 1, 4, 1, 3, 1}, new int[]{3, 1, 1, 1, 4, 1}, new int[]{4, 1, 1, 1, 3, 1}, new int[]{2, 1, 1, 4, 1, 2}, new int[]{2, 1, 1, 2, 1, 4}, new int[]{2, 1, 1, 2, 3, 2}, new int[]{2, 3, 3, 1, 1, 1, 2}};
    private static final int CODE_SHIFT = 98;
    private static final int CODE_START_A = 103;
    private static final int CODE_START_B = 104;
    private static final int CODE_START_C = 105;
    private static final int CODE_STOP = 106;
    private static final float MAX_AVG_VARIANCE = 0.25f;
    private static final float MAX_INDIVIDUAL_VARIANCE = 0.7f;

    private static int decodeCode(BitArray bitArray, int[] iArr, int i) throws NotFoundException {
        recordPattern(bitArray, i, iArr);
        float f = 0.25f;
        int i2 = -1;
        int i3 = 0;
        while (true) {
            int[][] iArr2 = CODE_PATTERNS;
            if (i3 >= iArr2.length) {
                break;
            }
            float patternMatchVariance = patternMatchVariance(iArr, iArr2[i3], MAX_INDIVIDUAL_VARIANCE);
            float f2 = f;
            if (patternMatchVariance < f) {
                i2 = i3;
                f2 = patternMatchVariance;
            }
            i3++;
            f = f2;
        }
        if (i2 >= 0) {
            return i2;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static int[] findStartPattern(BitArray bitArray) throws NotFoundException {
        int i;
        int i2;
        int size = bitArray.getSize();
        int nextSet = bitArray.getNextSet(0);
        int[] iArr = new int[6];
        int i3 = nextSet;
        boolean z = false;
        int i4 = 0;
        while (nextSet < size) {
            if (bitArray.get(nextSet) ^ z) {
                iArr[i4] = iArr[i4] + 1;
                i = i3;
            } else {
                if (i4 == 5) {
                    float f = 0.25f;
                    int i5 = 103;
                    int i6 = -1;
                    while (i5 <= 105) {
                        float patternMatchVariance = patternMatchVariance(iArr, CODE_PATTERNS[i5], MAX_INDIVIDUAL_VARIANCE);
                        float f2 = f;
                        if (patternMatchVariance < f) {
                            i6 = i5;
                            f2 = patternMatchVariance;
                        }
                        i5++;
                        f = f2;
                    }
                    if (i6 >= 0 && bitArray.isRange(Math.max(0, i3 - ((nextSet - i3) / 2)), i3, false)) {
                        return new int[]{i3, nextSet, i6};
                    }
                    i = i3 + iArr[0] + iArr[1];
                    System.arraycopy(iArr, 2, iArr, 0, 4);
                    iArr[4] = 0;
                    iArr[5] = 0;
                    i2 = i4 - 1;
                } else {
                    i = i3;
                    i2 = i4 + 1;
                }
                iArr[i2] = 1;
                z = !z;
                i4 = i2;
            }
            nextSet++;
            i3 = i;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:117:0x0368, code lost:            if (r18 != false) goto L104;     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x025e, code lost:            if (r18 != false) goto L104;     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x036b, code lost:            r23 = false;     */
    /* JADX WARN: Failed to find 'out' block for switch in B:101:0x02d3. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:29:0x012e. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:62:0x01d2. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0386 A[PHI: r25
  0x0386: PHI (r25v6 boolean) = (r25v3 boolean), (r25v12 boolean) binds: [B:101:0x02d3, B:62:0x01d2] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:87:0x039e A[PHI: r22 r26
  0x039e: PHI (r22v18 boolean) = 
  (r22v12 boolean)
  (r22v13 boolean)
  (r22v14 boolean)
  (r22v15 boolean)
  (r22v16 boolean)
  (r22v17 boolean)
  (r22v23 boolean)
  (r22v24 boolean)
  (r22v25 boolean)
  (r22v26 boolean)
  (r22v27 boolean)
 binds: [B:101:0x02d3, B:104:0x0311, B:108:0x032f, B:107:0x031c, B:102:0x02fc, B:88:0x039b, B:62:0x01d2, B:65:0x0211, B:69:0x022f, B:68:0x021c, B:63:0x01fc] A[DONT_GENERATE, DONT_INLINE]
  0x039e: PHI (r26v8 boolean) = 
  (r26v2 boolean)
  (r26v3 boolean)
  (r26v4 boolean)
  (r26v5 boolean)
  (r26v6 boolean)
  (r26v7 boolean)
  (r26v10 boolean)
  (r26v11 boolean)
  (r26v12 boolean)
  (r26v13 boolean)
  (r26v14 boolean)
 binds: [B:101:0x02d3, B:104:0x0311, B:108:0x032f, B:107:0x031c, B:102:0x02fc, B:88:0x039b, B:62:0x01d2, B:65:0x0211, B:69:0x022f, B:68:0x021c, B:63:0x01fc] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // com.google.zxing.oned.OneDReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.google.zxing.Result decodeRow(int r10, com.google.zxing.common.BitArray r11, java.util.Map<com.google.zxing.DecodeHintType, ?> r12) throws com.google.zxing.NotFoundException, com.google.zxing.FormatException, com.google.zxing.ChecksumException {
        /*
            Method dump skipped, instructions count: 1485
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.Code128Reader.decodeRow(int, com.google.zxing.common.BitArray, java.util.Map):com.google.zxing.Result");
    }
}
