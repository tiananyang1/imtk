package com.google.zxing.qrcode.detector;

import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitMatrix;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes08-dex2jar.jar:com/google/zxing/qrcode/detector/FinderPatternFinder.class */
public class FinderPatternFinder {
    private static final int CENTER_QUORUM = 2;
    protected static final int MAX_MODULES = 57;
    protected static final int MIN_SKIP = 3;
    private final int[] crossCheckStateCount;
    private boolean hasSkipped;
    private final BitMatrix image;
    private final List<FinderPattern> possibleCenters;
    private final ResultPointCallback resultPointCallback;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes08-dex2jar.jar:com/google/zxing/qrcode/detector/FinderPatternFinder$CenterComparator.class */
    public static final class CenterComparator implements Serializable, Comparator<FinderPattern> {
        private final float average;

        private CenterComparator(float f) {
            this.average = f;
        }

        @Override // java.util.Comparator
        public int compare(FinderPattern finderPattern, FinderPattern finderPattern2) {
            if (finderPattern2.getCount() != finderPattern.getCount()) {
                return finderPattern2.getCount() - finderPattern.getCount();
            }
            float abs = Math.abs(finderPattern2.getEstimatedModuleSize() - this.average);
            float abs2 = Math.abs(finderPattern.getEstimatedModuleSize() - this.average);
            if (abs < abs2) {
                return 1;
            }
            return abs == abs2 ? 0 : -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes08-dex2jar.jar:com/google/zxing/qrcode/detector/FinderPatternFinder$FurthestFromAverageComparator.class */
    public static final class FurthestFromAverageComparator implements Serializable, Comparator<FinderPattern> {
        private final float average;

        private FurthestFromAverageComparator(float f) {
            this.average = f;
        }

        @Override // java.util.Comparator
        public int compare(FinderPattern finderPattern, FinderPattern finderPattern2) {
            float abs = Math.abs(finderPattern2.getEstimatedModuleSize() - this.average);
            float abs2 = Math.abs(finderPattern.getEstimatedModuleSize() - this.average);
            if (abs < abs2) {
                return -1;
            }
            return abs == abs2 ? 0 : 1;
        }
    }

    public FinderPatternFinder(BitMatrix bitMatrix) {
        this(bitMatrix, null);
    }

    public FinderPatternFinder(BitMatrix bitMatrix, ResultPointCallback resultPointCallback) {
        this.image = bitMatrix;
        this.possibleCenters = new ArrayList();
        this.crossCheckStateCount = new int[5];
        this.resultPointCallback = resultPointCallback;
    }

    private static float centerFromEnd(int[] iArr, int i) {
        return ((i - iArr[4]) - iArr[3]) - (iArr[2] / 2.0f);
    }

    private boolean crossCheckDiagonal(int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int[] crossCheckStateCount = getCrossCheckStateCount();
        int i12 = 0;
        while (true) {
            i5 = i12;
            if (i < i5 || i2 < i5 || !this.image.get(i2 - i5, i - i5)) {
                break;
            }
            crossCheckStateCount[2] = crossCheckStateCount[2] + 1;
            i12 = i5 + 1;
        }
        if (i < i5) {
            return false;
        }
        int i13 = i5;
        if (i2 < i5) {
            return false;
        }
        while (i >= i13 && i2 >= i13 && !this.image.get(i2 - i13, i - i13) && crossCheckStateCount[1] <= i3) {
            crossCheckStateCount[1] = crossCheckStateCount[1] + 1;
            i13++;
        }
        if (i < i13 || i2 < i13 || crossCheckStateCount[1] > i3) {
            return false;
        }
        while (i >= i13 && i2 >= i13 && this.image.get(i2 - i13, i - i13) && crossCheckStateCount[0] <= i3) {
            crossCheckStateCount[0] = crossCheckStateCount[0] + 1;
            i13++;
        }
        if (crossCheckStateCount[0] > i3) {
            return false;
        }
        int height = this.image.getHeight();
        int width = this.image.getWidth();
        int i14 = 1;
        while (true) {
            i6 = i14;
            i7 = i + i6;
            if (i7 >= height || (i11 = i2 + i6) >= width || !this.image.get(i11, i7)) {
                break;
            }
            crossCheckStateCount[2] = crossCheckStateCount[2] + 1;
            i14 = i6 + 1;
        }
        if (i7 >= height) {
            return false;
        }
        int i15 = i6;
        if (i2 + i6 >= width) {
            return false;
        }
        while (true) {
            i8 = i + i15;
            if (i8 >= height || (i10 = i2 + i15) >= width || this.image.get(i10, i8) || crossCheckStateCount[3] >= i3) {
                break;
            }
            crossCheckStateCount[3] = crossCheckStateCount[3] + 1;
            i15++;
        }
        if (i8 >= height || i2 + i15 >= width || crossCheckStateCount[3] >= i3) {
            return false;
        }
        while (true) {
            int i16 = i + i15;
            if (i16 >= height || (i9 = i2 + i15) >= width || !this.image.get(i9, i16) || crossCheckStateCount[4] >= i3) {
                break;
            }
            crossCheckStateCount[4] = crossCheckStateCount[4] + 1;
            i15++;
        }
        return crossCheckStateCount[4] < i3 && Math.abs(((((crossCheckStateCount[0] + crossCheckStateCount[1]) + crossCheckStateCount[2]) + crossCheckStateCount[3]) + crossCheckStateCount[4]) - i4) < i4 * 2 && foundPatternCross(crossCheckStateCount);
    }

    private float crossCheckHorizontal(int i, int i2, int i3, int i4) {
        int i5;
        BitMatrix bitMatrix = this.image;
        int width = bitMatrix.getWidth();
        int[] crossCheckStateCount = getCrossCheckStateCount();
        int i6 = i;
        while (true) {
            i5 = i6;
            if (i5 < 0 || !bitMatrix.get(i5, i2)) {
                break;
            }
            crossCheckStateCount[2] = crossCheckStateCount[2] + 1;
            i6 = i5 - 1;
        }
        int i7 = i5;
        if (i5 < 0) {
            return Float.NaN;
        }
        while (i7 >= 0 && !bitMatrix.get(i7, i2) && crossCheckStateCount[1] <= i3) {
            crossCheckStateCount[1] = crossCheckStateCount[1] + 1;
            i7--;
        }
        if (i7 < 0 || crossCheckStateCount[1] > i3) {
            return Float.NaN;
        }
        while (i7 >= 0 && bitMatrix.get(i7, i2) && crossCheckStateCount[0] <= i3) {
            crossCheckStateCount[0] = crossCheckStateCount[0] + 1;
            i7--;
        }
        if (crossCheckStateCount[0] > i3) {
            return Float.NaN;
        }
        while (true) {
            i++;
            if (i >= width || !bitMatrix.get(i, i2)) {
                break;
            }
            crossCheckStateCount[2] = crossCheckStateCount[2] + 1;
        }
        int i8 = i;
        if (i == width) {
            return Float.NaN;
        }
        while (i8 < width && !bitMatrix.get(i8, i2) && crossCheckStateCount[3] < i3) {
            crossCheckStateCount[3] = crossCheckStateCount[3] + 1;
            i8++;
        }
        if (i8 == width || crossCheckStateCount[3] >= i3) {
            return Float.NaN;
        }
        while (i8 < width && bitMatrix.get(i8, i2) && crossCheckStateCount[4] < i3) {
            crossCheckStateCount[4] = crossCheckStateCount[4] + 1;
            i8++;
        }
        if (crossCheckStateCount[4] < i3 && Math.abs(((((crossCheckStateCount[0] + crossCheckStateCount[1]) + crossCheckStateCount[2]) + crossCheckStateCount[3]) + crossCheckStateCount[4]) - i4) * 5 < i4 && foundPatternCross(crossCheckStateCount)) {
            return centerFromEnd(crossCheckStateCount, i8);
        }
        return Float.NaN;
    }

    private float crossCheckVertical(int i, int i2, int i3, int i4) {
        int i5;
        BitMatrix bitMatrix = this.image;
        int height = bitMatrix.getHeight();
        int[] crossCheckStateCount = getCrossCheckStateCount();
        int i6 = i;
        while (true) {
            i5 = i6;
            if (i5 < 0 || !bitMatrix.get(i2, i5)) {
                break;
            }
            crossCheckStateCount[2] = crossCheckStateCount[2] + 1;
            i6 = i5 - 1;
        }
        int i7 = i5;
        if (i5 < 0) {
            return Float.NaN;
        }
        while (i7 >= 0 && !bitMatrix.get(i2, i7) && crossCheckStateCount[1] <= i3) {
            crossCheckStateCount[1] = crossCheckStateCount[1] + 1;
            i7--;
        }
        if (i7 < 0 || crossCheckStateCount[1] > i3) {
            return Float.NaN;
        }
        while (i7 >= 0 && bitMatrix.get(i2, i7) && crossCheckStateCount[0] <= i3) {
            crossCheckStateCount[0] = crossCheckStateCount[0] + 1;
            i7--;
        }
        if (crossCheckStateCount[0] > i3) {
            return Float.NaN;
        }
        while (true) {
            i++;
            if (i >= height || !bitMatrix.get(i2, i)) {
                break;
            }
            crossCheckStateCount[2] = crossCheckStateCount[2] + 1;
        }
        int i8 = i;
        if (i == height) {
            return Float.NaN;
        }
        while (i8 < height && !bitMatrix.get(i2, i8) && crossCheckStateCount[3] < i3) {
            crossCheckStateCount[3] = crossCheckStateCount[3] + 1;
            i8++;
        }
        if (i8 == height || crossCheckStateCount[3] >= i3) {
            return Float.NaN;
        }
        while (i8 < height && bitMatrix.get(i2, i8) && crossCheckStateCount[4] < i3) {
            crossCheckStateCount[4] = crossCheckStateCount[4] + 1;
            i8++;
        }
        if (crossCheckStateCount[4] < i3 && Math.abs(((((crossCheckStateCount[0] + crossCheckStateCount[1]) + crossCheckStateCount[2]) + crossCheckStateCount[3]) + crossCheckStateCount[4]) - i4) * 5 < i4 * 2 && foundPatternCross(crossCheckStateCount)) {
            return centerFromEnd(crossCheckStateCount, i8);
        }
        return Float.NaN;
    }

    private int findRowSkip() {
        if (this.possibleCenters.size() <= 1) {
            return 0;
        }
        FinderPattern finderPattern = null;
        for (FinderPattern finderPattern2 : this.possibleCenters) {
            if (finderPattern2.getCount() >= 2) {
                if (finderPattern != null) {
                    this.hasSkipped = true;
                    return ((int) (Math.abs(finderPattern.getX() - finderPattern2.getX()) - Math.abs(finderPattern.getY() - finderPattern2.getY()))) / 2;
                }
                finderPattern = finderPattern2;
            }
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean foundPatternCross(int[] iArr) {
        int i = 0;
        for (int i2 = 0; i2 < 5; i2++) {
            int i3 = iArr[i2];
            if (i3 == 0) {
                return false;
            }
            i += i3;
        }
        if (i < 7) {
            return false;
        }
        float f = i / 7.0f;
        float f2 = f / 2.0f;
        return Math.abs(f - ((float) iArr[0])) < f2 && Math.abs(f - ((float) iArr[1])) < f2 && Math.abs((f * 3.0f) - ((float) iArr[2])) < 3.0f * f2 && Math.abs(f - ((float) iArr[3])) < f2 && Math.abs(f - ((float) iArr[4])) < f2;
    }

    private int[] getCrossCheckStateCount() {
        int[] iArr = this.crossCheckStateCount;
        iArr[0] = 0;
        iArr[1] = 0;
        iArr[2] = 0;
        iArr[3] = 0;
        iArr[4] = 0;
        return iArr;
    }

    private boolean haveMultiplyConfirmedCenters() {
        int size = this.possibleCenters.size();
        float f = 0.0f;
        int i = 0;
        float f2 = 0.0f;
        for (FinderPattern finderPattern : this.possibleCenters) {
            if (finderPattern.getCount() >= 2) {
                i++;
                f2 += finderPattern.getEstimatedModuleSize();
            }
        }
        if (i < 3) {
            return false;
        }
        float f3 = f2 / size;
        Iterator<FinderPattern> it = this.possibleCenters.iterator();
        while (it.hasNext()) {
            f += Math.abs(it.next().getEstimatedModuleSize() - f3);
        }
        return f <= f2 * 0.05f;
    }

    private FinderPattern[] selectBestPatterns() throws NotFoundException {
        float f;
        float f2;
        int size = this.possibleCenters.size();
        if (size < 3) {
            throw NotFoundException.getNotFoundInstance();
        }
        if (size > 3) {
            Iterator<FinderPattern> it = this.possibleCenters.iterator();
            float f3 = 0.0f;
            float f4 = 0.0f;
            while (true) {
                f2 = f4;
                if (!it.hasNext()) {
                    break;
                }
                float estimatedModuleSize = it.next().getEstimatedModuleSize();
                f3 += estimatedModuleSize;
                f4 = f2 + (estimatedModuleSize * estimatedModuleSize);
            }
            float f5 = f3 / size;
            float sqrt = (float) Math.sqrt((f2 / r0) - (f5 * f5));
            Collections.sort(this.possibleCenters, new FurthestFromAverageComparator(f5));
            float max = Math.max(0.2f * f5, sqrt);
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= this.possibleCenters.size() || this.possibleCenters.size() <= 3) {
                    break;
                }
                int i3 = i2;
                if (Math.abs(this.possibleCenters.get(i2).getEstimatedModuleSize() - f5) > max) {
                    this.possibleCenters.remove(i2);
                    i3 = i2 - 1;
                }
                i = i3 + 1;
            }
        }
        if (this.possibleCenters.size() > 3) {
            Iterator<FinderPattern> it2 = this.possibleCenters.iterator();
            float f6 = 0.0f;
            while (true) {
                f = f6;
                if (!it2.hasNext()) {
                    break;
                }
                f6 = f + it2.next().getEstimatedModuleSize();
            }
            Collections.sort(this.possibleCenters, new CenterComparator(f / this.possibleCenters.size()));
            List<FinderPattern> list = this.possibleCenters;
            list.subList(3, list.size()).clear();
        }
        return new FinderPattern[]{this.possibleCenters.get(0), this.possibleCenters.get(1), this.possibleCenters.get(2)};
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final FinderPatternInfo find(Map<DecodeHintType, ?> map) throws NotFoundException {
        int i;
        boolean z;
        boolean z2 = map != null && map.containsKey(DecodeHintType.TRY_HARDER);
        boolean z3 = map != null && map.containsKey(DecodeHintType.PURE_BARCODE);
        int height = this.image.getHeight();
        int width = this.image.getWidth();
        int i2 = (height * 3) / 228;
        if (i2 < 3 || z2) {
            i2 = 3;
        }
        int[] iArr = new int[5];
        int i3 = i2 - 1;
        boolean z4 = false;
        while (i3 < height && !z4) {
            iArr[0] = 0;
            iArr[1] = 0;
            iArr[2] = 0;
            iArr[3] = 0;
            iArr[4] = 0;
            int i4 = 0;
            int i5 = i2;
            int i6 = 0;
            while (i4 < width) {
                if (this.image.get(i4, i3)) {
                    int i7 = i6;
                    if ((i6 & 1) == 1) {
                        i7 = i6 + 1;
                    }
                    iArr[i7] = iArr[i7] + 1;
                    i6 = i7;
                } else if ((i6 & 1) != 0) {
                    iArr[i6] = iArr[i6] + 1;
                } else if (i6 == 4) {
                    if (!foundPatternCross(iArr)) {
                        iArr[0] = iArr[2];
                        iArr[1] = iArr[3];
                        iArr[2] = iArr[4];
                        iArr[3] = 1;
                        iArr[4] = 0;
                    } else if (handlePossibleCenter(iArr, i3, i4, z3)) {
                        if (this.hasSkipped) {
                            z = haveMultiplyConfirmedCenters();
                            i = i3;
                        } else {
                            int findRowSkip = findRowSkip();
                            i = i3;
                            z = z4;
                            if (findRowSkip > iArr[2]) {
                                i = i3 + ((findRowSkip - iArr[2]) - 2);
                                i4 = width - 1;
                                z = z4;
                            }
                        }
                        iArr[0] = 0;
                        iArr[1] = 0;
                        iArr[2] = 0;
                        iArr[3] = 0;
                        iArr[4] = 0;
                        i5 = 2;
                        i3 = i;
                        i6 = 0;
                        z4 = z;
                    } else {
                        iArr[0] = iArr[2];
                        iArr[1] = iArr[3];
                        iArr[2] = iArr[4];
                        iArr[3] = 1;
                        iArr[4] = 0;
                    }
                    i6 = 3;
                } else {
                    i6++;
                    iArr[i6] = iArr[i6] + 1;
                }
                i4++;
            }
            if (foundPatternCross(iArr) && handlePossibleCenter(iArr, i3, width, z3)) {
                i2 = iArr[0];
                if (this.hasSkipped) {
                    z4 = haveMultiplyConfirmedCenters();
                }
            } else {
                i2 = i5;
            }
            i3 += i2;
        }
        FinderPattern[] selectBestPatterns = selectBestPatterns();
        ResultPoint.orderBestPatterns(selectBestPatterns);
        return new FinderPatternInfo(selectBestPatterns);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final BitMatrix getImage() {
        return this.image;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final List<FinderPattern> getPossibleCenters() {
        return this.possibleCenters;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean handlePossibleCenter(int[] iArr, int i, int i2, boolean z) {
        boolean z2;
        int i3 = iArr[0] + iArr[1] + iArr[2] + iArr[3] + iArr[4];
        int centerFromEnd = (int) centerFromEnd(iArr, i2);
        float crossCheckVertical = crossCheckVertical(i, centerFromEnd, iArr[2], i3);
        if (Float.isNaN(crossCheckVertical)) {
            return false;
        }
        int i4 = (int) crossCheckVertical;
        float crossCheckHorizontal = crossCheckHorizontal(centerFromEnd, i4, iArr[2], i3);
        if (Float.isNaN(crossCheckHorizontal)) {
            return false;
        }
        if (z && !crossCheckDiagonal(i4, (int) crossCheckHorizontal, iArr[2], i3)) {
            return false;
        }
        float f = i3 / 7.0f;
        int i5 = 0;
        while (true) {
            int i6 = i5;
            z2 = false;
            if (i6 >= this.possibleCenters.size()) {
                break;
            }
            FinderPattern finderPattern = this.possibleCenters.get(i6);
            if (finderPattern.aboutEquals(f, crossCheckVertical, crossCheckHorizontal)) {
                this.possibleCenters.set(i6, finderPattern.combineEstimate(crossCheckVertical, crossCheckHorizontal, f));
                z2 = true;
                break;
            }
            i5 = i6 + 1;
        }
        if (z2) {
            return true;
        }
        FinderPattern finderPattern2 = new FinderPattern(crossCheckHorizontal, crossCheckVertical, f);
        this.possibleCenters.add(finderPattern2);
        ResultPointCallback resultPointCallback = this.resultPointCallback;
        if (resultPointCallback == null) {
            return true;
        }
        resultPointCallback.foundPossibleResultPoint(finderPattern2);
        return true;
    }
}
