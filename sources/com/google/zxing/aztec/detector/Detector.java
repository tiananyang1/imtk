package com.google.zxing.aztec.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.aztec.AztecDetectorResult;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.GridSampler;
import com.google.zxing.common.detector.MathUtils;
import com.google.zxing.common.detector.WhiteRectangleDetector;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonDecoder;
import com.google.zxing.common.reedsolomon.ReedSolomonException;

/* loaded from: classes08-dex2jar.jar:com/google/zxing/aztec/detector/Detector.class */
public final class Detector {
    private static final int[] EXPECTED_CORNER_BITS = {3808, 476, 2107, 1799};
    private boolean compact;
    private final BitMatrix image;
    private int nbCenterLayers;
    private int nbDataBlocks;
    private int nbLayers;
    private int shift;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes08-dex2jar.jar:com/google/zxing/aztec/detector/Detector$Point.class */
    public static final class Point {

        /* renamed from: x */
        private final int f11x;

        /* renamed from: y */
        private final int f12y;

        Point(int i, int i2) {
            this.f11x = i;
            this.f12y = i2;
        }

        int getX() {
            return this.f11x;
        }

        int getY() {
            return this.f12y;
        }

        ResultPoint toResultPoint() {
            return new ResultPoint(getX(), getY());
        }

        public String toString() {
            return "<" + this.f11x + ' ' + this.f12y + '>';
        }
    }

    public Detector(BitMatrix bitMatrix) {
        this.image = bitMatrix;
    }

    private static float distance(ResultPoint resultPoint, ResultPoint resultPoint2) {
        return MathUtils.distance(resultPoint.getX(), resultPoint.getY(), resultPoint2.getX(), resultPoint2.getY());
    }

    private static float distance(Point point, Point point2) {
        return MathUtils.distance(point.getX(), point.getY(), point2.getX(), point2.getY());
    }

    private static ResultPoint[] expandSquare(ResultPoint[] resultPointArr, float f, float f2) {
        float f3 = f2 / (f * 2.0f);
        float x = resultPointArr[0].getX();
        float x2 = resultPointArr[2].getX();
        float y = resultPointArr[0].getY();
        float y2 = resultPointArr[2].getY();
        float x3 = (resultPointArr[0].getX() + resultPointArr[2].getX()) / 2.0f;
        float y3 = (resultPointArr[0].getY() + resultPointArr[2].getY()) / 2.0f;
        float f4 = (x - x2) * f3;
        float f5 = (y - y2) * f3;
        ResultPoint resultPoint = new ResultPoint(x3 + f4, y3 + f5);
        ResultPoint resultPoint2 = new ResultPoint(x3 - f4, y3 - f5);
        float x4 = resultPointArr[1].getX();
        float x5 = resultPointArr[3].getX();
        float y4 = resultPointArr[1].getY();
        float y5 = resultPointArr[3].getY();
        float x6 = (resultPointArr[1].getX() + resultPointArr[3].getX()) / 2.0f;
        float y6 = (resultPointArr[1].getY() + resultPointArr[3].getY()) / 2.0f;
        float f6 = (x4 - x5) * f3;
        float f7 = f3 * (y4 - y5);
        return new ResultPoint[]{resultPoint, new ResultPoint(x6 + f6, y6 + f7), resultPoint2, new ResultPoint(x6 - f6, y6 - f7)};
    }

    private void extractParameters(ResultPoint[] resultPointArr) throws NotFoundException {
        long j;
        int i;
        if (!isValid(resultPointArr[0]) || !isValid(resultPointArr[1]) || !isValid(resultPointArr[2]) || !isValid(resultPointArr[3])) {
            throw NotFoundException.getNotFoundInstance();
        }
        int i2 = this.nbCenterLayers * 2;
        int[] iArr = {sampleLine(resultPointArr[0], resultPointArr[1], i2), sampleLine(resultPointArr[1], resultPointArr[2], i2), sampleLine(resultPointArr[2], resultPointArr[3], i2), sampleLine(resultPointArr[3], resultPointArr[0], i2)};
        this.shift = getRotation(iArr, i2);
        long j2 = 0;
        for (int i3 = 0; i3 < 4; i3++) {
            int i4 = iArr[(this.shift + i3) % 4];
            if (this.compact) {
                j = j2 << 7;
                i = (i4 >> 1) & 127;
            } else {
                j = j2 << 10;
                i = ((i4 >> 2) & 992) + ((i4 >> 1) & 31);
            }
            j2 = j + i;
        }
        int correctedParameterData = getCorrectedParameterData(j2, this.compact);
        if (this.compact) {
            this.nbLayers = (correctedParameterData >> 6) + 1;
            this.nbDataBlocks = (correctedParameterData & 63) + 1;
        } else {
            this.nbLayers = (correctedParameterData >> 11) + 1;
            this.nbDataBlocks = (correctedParameterData & 2047) + 1;
        }
    }

    private ResultPoint[] getBullsEyeCorners(Point point) throws NotFoundException {
        this.nbCenterLayers = 1;
        Point point2 = point;
        Point point3 = point2;
        Point point4 = point3;
        boolean z = true;
        while (this.nbCenterLayers < 9) {
            Point firstDifferent = getFirstDifferent(point, z, 1, -1);
            Point firstDifferent2 = getFirstDifferent(point2, z, 1, 1);
            Point firstDifferent3 = getFirstDifferent(point3, z, -1, 1);
            Point firstDifferent4 = getFirstDifferent(point4, z, -1, -1);
            if (this.nbCenterLayers > 2) {
                double distance = (distance(firstDifferent4, firstDifferent) * this.nbCenterLayers) / (distance(point4, point) * (this.nbCenterLayers + 2));
                if (distance < 0.75d || distance > 1.25d || !isWhiteOrBlackRectangle(firstDifferent, firstDifferent2, firstDifferent3, firstDifferent4)) {
                    break;
                }
            }
            z = !z;
            this.nbCenterLayers++;
            point4 = firstDifferent4;
            point = firstDifferent;
            point2 = firstDifferent2;
            point3 = firstDifferent3;
        }
        int i = this.nbCenterLayers;
        if (i != 5 && i != 7) {
            throw NotFoundException.getNotFoundInstance();
        }
        this.compact = this.nbCenterLayers == 5;
        ResultPoint resultPoint = new ResultPoint(point.getX() + 0.5f, point.getY() - 0.5f);
        ResultPoint resultPoint2 = new ResultPoint(point2.getX() + 0.5f, point2.getY() + 0.5f);
        ResultPoint resultPoint3 = new ResultPoint(point3.getX() - 0.5f, point3.getY() + 0.5f);
        ResultPoint resultPoint4 = new ResultPoint(point4.getX() - 0.5f, point4.getY() - 0.5f);
        int i2 = this.nbCenterLayers;
        return expandSquare(new ResultPoint[]{resultPoint, resultPoint2, resultPoint3, resultPoint4}, (i2 * 2) - 3, i2 * 2);
    }

    private int getColor(Point point, Point point2) {
        int i;
        float distance = distance(point, point2);
        float x = (point2.getX() - point.getX()) / distance;
        float y = (point2.getY() - point.getY()) / distance;
        float x2 = point.getX();
        float y2 = point.getY();
        boolean z = this.image.get(point.getX(), point.getY());
        int ceil = (int) Math.ceil(distance);
        boolean z2 = false;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            i = i3;
            if (i2 >= ceil) {
                break;
            }
            x2 += x;
            y2 += y;
            int i4 = i;
            if (this.image.get(MathUtils.round(x2), MathUtils.round(y2)) != z) {
                i4 = i + 1;
            }
            i2++;
            i3 = i4;
        }
        float f = i / distance;
        if (f > 0.1f && f < 0.9f) {
            return 0;
        }
        if (f <= 0.1f) {
            z2 = true;
        }
        return z2 == z ? 1 : -1;
    }

    private static int getCorrectedParameterData(long j, boolean z) throws NotFoundException {
        int i;
        int i2;
        if (z) {
            i = 7;
            i2 = 2;
        } else {
            i = 10;
            i2 = 4;
        }
        int[] iArr = new int[i];
        int i3 = i;
        while (true) {
            int i4 = i3 - 1;
            if (i4 < 0) {
                try {
                    break;
                } catch (ReedSolomonException e) {
                    throw NotFoundException.getNotFoundInstance();
                }
            }
            iArr[i4] = ((int) j) & 15;
            j >>= 4;
            i3 = i4;
        }
        new ReedSolomonDecoder(GenericGF.AZTEC_PARAM).decode(iArr, i - i2);
        int i5 = 0;
        for (int i6 = 0; i6 < i2; i6++) {
            i5 = (i5 << 4) + iArr[i6];
        }
        return i5;
    }

    private int getDimension() {
        if (this.compact) {
            return (this.nbLayers * 4) + 11;
        }
        int i = this.nbLayers;
        return i <= 4 ? (i * 4) + 15 : (i * 4) + ((((i - 4) / 8) + 1) * 2) + 15;
    }

    private Point getFirstDifferent(Point point, boolean z, int i, int i2) {
        int i3;
        int x = point.getX() + i;
        int y = point.getY();
        while (true) {
            y += i2;
            if (!isValid(x, y) || this.image.get(x, y) != z) {
                break;
            }
            x += i;
        }
        int i4 = x - i;
        int i5 = y - i2;
        while (isValid(i4, i5) && this.image.get(i4, i5) == z) {
            i4 += i;
        }
        int i6 = i4 - i;
        int i7 = i5;
        while (true) {
            i3 = i7;
            if (!isValid(i6, i3) || this.image.get(i6, i3) != z) {
                break;
            }
            i7 = i3 + i2;
        }
        return new Point(i6, i3 - i2);
    }

    private Point getMatrixCenter() {
        ResultPoint resultPoint;
        ResultPoint resultPoint2;
        ResultPoint resultPoint3;
        ResultPoint resultPoint4;
        ResultPoint resultPoint5;
        ResultPoint resultPoint6;
        ResultPoint resultPoint7;
        ResultPoint resultPoint8;
        try {
            ResultPoint[] detect = new WhiteRectangleDetector(this.image).detect();
            resultPoint3 = detect[0];
            resultPoint = detect[1];
            resultPoint2 = detect[2];
            resultPoint4 = detect[3];
        } catch (NotFoundException e) {
            int width = this.image.getWidth() / 2;
            int height = this.image.getHeight() / 2;
            int i = width + 7;
            int i2 = height - 7;
            ResultPoint resultPoint9 = getFirstDifferent(new Point(i, i2), false, 1, -1).toResultPoint();
            int i3 = height + 7;
            resultPoint = getFirstDifferent(new Point(i, i3), false, 1, 1).toResultPoint();
            int i4 = width - 7;
            resultPoint2 = getFirstDifferent(new Point(i4, i3), false, -1, 1).toResultPoint();
            resultPoint3 = resultPoint9;
            resultPoint4 = getFirstDifferent(new Point(i4, i2), false, -1, -1).toResultPoint();
        }
        int round = MathUtils.round((((resultPoint3.getX() + resultPoint4.getX()) + resultPoint.getX()) + resultPoint2.getX()) / 4.0f);
        int round2 = MathUtils.round((((resultPoint3.getY() + resultPoint4.getY()) + resultPoint.getY()) + resultPoint2.getY()) / 4.0f);
        try {
            ResultPoint[] detect2 = new WhiteRectangleDetector(this.image, 15, round, round2).detect();
            resultPoint5 = detect2[0];
            resultPoint6 = detect2[1];
            resultPoint7 = detect2[2];
            resultPoint8 = detect2[3];
        } catch (NotFoundException e2) {
            int i5 = round + 7;
            int i6 = round2 - 7;
            resultPoint5 = getFirstDifferent(new Point(i5, i6), false, 1, -1).toResultPoint();
            int i7 = round2 + 7;
            resultPoint6 = getFirstDifferent(new Point(i5, i7), false, 1, 1).toResultPoint();
            int i8 = round - 7;
            resultPoint7 = getFirstDifferent(new Point(i8, i7), false, -1, 1).toResultPoint();
            resultPoint8 = getFirstDifferent(new Point(i8, i6), false, -1, -1).toResultPoint();
        }
        return new Point(MathUtils.round((((resultPoint5.getX() + resultPoint8.getX()) + resultPoint6.getX()) + resultPoint7.getX()) / 4.0f), MathUtils.round((((resultPoint5.getY() + resultPoint8.getY()) + resultPoint6.getY()) + resultPoint7.getY()) / 4.0f));
    }

    private ResultPoint[] getMatrixCornerPoints(ResultPoint[] resultPointArr) {
        return expandSquare(resultPointArr, this.nbCenterLayers * 2, getDimension());
    }

    private static int getRotation(int[] iArr, int i) throws NotFoundException {
        int i2 = 0;
        for (int i3 : iArr) {
            i2 = (i2 << 3) + ((i3 >> (i - 2)) << 1) + (i3 & 1);
        }
        int i4 = 0;
        while (true) {
            int i5 = i4;
            if (i5 >= 4) {
                throw NotFoundException.getNotFoundInstance();
            }
            if (Integer.bitCount(EXPECTED_CORNER_BITS[i5] ^ (((i2 & 1) << 11) + (i2 >> 1))) <= 2) {
                return i5;
            }
            i4 = i5 + 1;
        }
    }

    private boolean isValid(int i, int i2) {
        return i >= 0 && i < this.image.getWidth() && i2 > 0 && i2 < this.image.getHeight();
    }

    private boolean isValid(ResultPoint resultPoint) {
        return isValid(MathUtils.round(resultPoint.getX()), MathUtils.round(resultPoint.getY()));
    }

    private boolean isWhiteOrBlackRectangle(Point point, Point point2, Point point3, Point point4) {
        Point point5 = new Point(point.getX() - 3, point.getY() + 3);
        Point point6 = new Point(point2.getX() - 3, point2.getY() - 3);
        Point point7 = new Point(point3.getX() + 3, point3.getY() - 3);
        Point point8 = new Point(point4.getX() + 3, point4.getY() + 3);
        int color = getColor(point8, point5);
        return color != 0 && getColor(point5, point6) == color && getColor(point6, point7) == color && getColor(point7, point8) == color;
    }

    private BitMatrix sampleGrid(BitMatrix bitMatrix, ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3, ResultPoint resultPoint4) throws NotFoundException {
        GridSampler gridSampler = GridSampler.getInstance();
        int dimension = getDimension();
        float f = dimension / 2.0f;
        int i = this.nbCenterLayers;
        float f2 = f - i;
        float f3 = f + i;
        return gridSampler.sampleGrid(bitMatrix, dimension, dimension, f2, f2, f3, f2, f3, f3, f2, f3, resultPoint.getX(), resultPoint.getY(), resultPoint2.getX(), resultPoint2.getY(), resultPoint3.getX(), resultPoint3.getY(), resultPoint4.getX(), resultPoint4.getY());
    }

    private int sampleLine(ResultPoint resultPoint, ResultPoint resultPoint2, int i) {
        float distance = distance(resultPoint, resultPoint2);
        float f = distance / i;
        float x = resultPoint.getX();
        float y = resultPoint.getY();
        float x2 = ((resultPoint2.getX() - resultPoint.getX()) * f) / distance;
        float y2 = (f * (resultPoint2.getY() - resultPoint.getY())) / distance;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int i4 = i3;
            if (i2 >= i) {
                return i4;
            }
            float f2 = i2;
            int i5 = i4;
            if (this.image.get(MathUtils.round((f2 * x2) + x), MathUtils.round((f2 * y2) + y))) {
                i5 = i4 | (1 << ((i - i2) - 1));
            }
            i2++;
            i3 = i5;
        }
    }

    public AztecDetectorResult detect() throws NotFoundException {
        return detect(false);
    }

    public AztecDetectorResult detect(boolean z) throws NotFoundException {
        ResultPoint[] bullsEyeCorners = getBullsEyeCorners(getMatrixCenter());
        if (z) {
            ResultPoint resultPoint = bullsEyeCorners[0];
            bullsEyeCorners[0] = bullsEyeCorners[2];
            bullsEyeCorners[2] = resultPoint;
        }
        extractParameters(bullsEyeCorners);
        BitMatrix bitMatrix = this.image;
        int i = this.shift;
        return new AztecDetectorResult(sampleGrid(bitMatrix, bullsEyeCorners[i % 4], bullsEyeCorners[(i + 1) % 4], bullsEyeCorners[(i + 2) % 4], bullsEyeCorners[(i + 3) % 4]), getMatrixCornerPoints(bullsEyeCorners), this.compact, this.nbDataBlocks, this.nbLayers);
    }
}
