package com.google.zxing.oned.rss;

import com.google.zxing.NotFoundException;
import com.google.zxing.common.detector.MathUtils;
import com.google.zxing.oned.OneDReader;

/* loaded from: classes08-dex2jar.jar:com/google/zxing/oned/rss/AbstractRSSReader.class */
public abstract class AbstractRSSReader extends OneDReader {
    private static final float MAX_AVG_VARIANCE = 0.2f;
    private static final float MAX_FINDER_PATTERN_RATIO = 0.89285713f;
    private static final float MAX_INDIVIDUAL_VARIANCE = 0.45f;
    private static final float MIN_FINDER_PATTERN_RATIO = 0.7916667f;
    private final int[] evenCounts;
    private final int[] oddCounts;
    private final int[] decodeFinderCounters = new int[4];
    private final int[] dataCharacterCounters = new int[8];
    private final float[] oddRoundingErrors = new float[4];
    private final float[] evenRoundingErrors = new float[4];

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractRSSReader() {
        int[] iArr = this.dataCharacterCounters;
        this.oddCounts = new int[iArr.length / 2];
        this.evenCounts = new int[iArr.length / 2];
    }

    @Deprecated
    protected static int count(int[] iArr) {
        return MathUtils.sum(iArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void decrement(int[] iArr, float[] fArr) {
        float f = fArr[0];
        int i = 1;
        int i2 = 0;
        while (i < iArr.length) {
            float f2 = f;
            if (fArr[i] < f) {
                f2 = fArr[i];
                i2 = i;
            }
            i++;
            f = f2;
        }
        iArr[i2] = iArr[i2] - 1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void increment(int[] iArr, float[] fArr) {
        float f = fArr[0];
        int i = 1;
        int i2 = 0;
        while (i < iArr.length) {
            float f2 = f;
            if (fArr[i] > f) {
                f2 = fArr[i];
                i2 = i;
            }
            i++;
            f = f2;
        }
        iArr[i2] = iArr[i2] + 1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean isFinderPattern(int[] iArr) {
        int i;
        float f = (iArr[0] + iArr[1]) / ((iArr[2] + r0) + iArr[3]);
        if (f < MIN_FINDER_PATTERN_RATIO || f > MAX_FINDER_PATTERN_RATIO) {
            return false;
        }
        int i2 = Integer.MIN_VALUE;
        int length = iArr.length;
        int i3 = 0;
        int i4 = Integer.MAX_VALUE;
        while (true) {
            i = i4;
            if (i3 >= length) {
                break;
            }
            int i5 = iArr[i3];
            int i6 = i2;
            if (i5 > i2) {
                i6 = i5;
            }
            int i7 = i;
            if (i5 < i) {
                i7 = i5;
            }
            i3++;
            i2 = i6;
            i4 = i7;
        }
        return i2 < i * 10;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static int parseFinderValue(int[] iArr, int[][] iArr2) throws NotFoundException {
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= iArr2.length) {
                throw NotFoundException.getNotFoundInstance();
            }
            if (patternMatchVariance(iArr, iArr2[i2], MAX_INDIVIDUAL_VARIANCE) < MAX_AVG_VARIANCE) {
                return i2;
            }
            i = i2 + 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int[] getDataCharacterCounters() {
        return this.dataCharacterCounters;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int[] getDecodeFinderCounters() {
        return this.decodeFinderCounters;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int[] getEvenCounts() {
        return this.evenCounts;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final float[] getEvenRoundingErrors() {
        return this.evenRoundingErrors;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int[] getOddCounts() {
        return this.oddCounts;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final float[] getOddRoundingErrors() {
        return this.oddRoundingErrors;
    }
}
