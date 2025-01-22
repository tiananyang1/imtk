package com.google.zxing.common.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;

/* loaded from: classes08-dex2jar.jar:com/google/zxing/common/detector/WhiteRectangleDetector.class */
public final class WhiteRectangleDetector {
    private static final int CORR = 1;
    private static final int INIT_SIZE = 10;
    private final int downInit;
    private final int height;
    private final BitMatrix image;
    private final int leftInit;
    private final int rightInit;
    private final int upInit;
    private final int width;

    public WhiteRectangleDetector(BitMatrix bitMatrix) throws NotFoundException {
        this(bitMatrix, 10, bitMatrix.getWidth() / 2, bitMatrix.getHeight() / 2);
    }

    public WhiteRectangleDetector(BitMatrix bitMatrix, int i, int i2, int i3) throws NotFoundException {
        this.image = bitMatrix;
        this.height = bitMatrix.getHeight();
        this.width = bitMatrix.getWidth();
        int i4 = i / 2;
        this.leftInit = i2 - i4;
        this.rightInit = i2 + i4;
        this.upInit = i3 - i4;
        this.downInit = i3 + i4;
        if (this.upInit < 0 || this.leftInit < 0 || this.downInit >= this.height || this.rightInit >= this.width) {
            throw NotFoundException.getNotFoundInstance();
        }
    }

    private ResultPoint[] centerEdges(ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3, ResultPoint resultPoint4) {
        float x = resultPoint.getX();
        float y = resultPoint.getY();
        float x2 = resultPoint2.getX();
        float y2 = resultPoint2.getY();
        float x3 = resultPoint3.getX();
        float y3 = resultPoint3.getY();
        float x4 = resultPoint4.getX();
        float y4 = resultPoint4.getY();
        return x < ((float) this.width) / 2.0f ? new ResultPoint[]{new ResultPoint(x4 - 1.0f, y4 + 1.0f), new ResultPoint(x2 + 1.0f, y2 + 1.0f), new ResultPoint(x3 - 1.0f, y3 - 1.0f), new ResultPoint(x + 1.0f, y - 1.0f)} : new ResultPoint[]{new ResultPoint(x4 + 1.0f, y4 + 1.0f), new ResultPoint(x2 + 1.0f, y2 - 1.0f), new ResultPoint(x3 - 1.0f, y3 + 1.0f), new ResultPoint(x - 1.0f, y - 1.0f)};
    }

    private boolean containsBlackPoint(int i, int i2, int i3, boolean z) {
        if (z) {
            while (i <= i2) {
                if (this.image.get(i, i3)) {
                    return true;
                }
                i++;
            }
            return false;
        }
        for (int i4 = i; i4 <= i2; i4++) {
            if (this.image.get(i3, i4)) {
                return true;
            }
        }
        return false;
    }

    private ResultPoint getBlackPointOnSegment(float f, float f2, float f3, float f4) {
        int round = MathUtils.round(MathUtils.distance(f, f2, f3, f4));
        float f5 = round;
        float f6 = (f3 - f) / f5;
        float f7 = (f4 - f2) / f5;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= round) {
                return null;
            }
            float f8 = i2;
            int round2 = MathUtils.round((f8 * f6) + f);
            int round3 = MathUtils.round((f8 * f7) + f2);
            if (this.image.get(round2, round3)) {
                return new ResultPoint(round2, round3);
            }
            i = i2 + 1;
        }
    }

    public ResultPoint[] detect() throws NotFoundException {
        int i;
        int i2;
        int i3;
        boolean z;
        int i4;
        int i5 = this.leftInit;
        int i6 = this.rightInit;
        int i7 = this.upInit;
        int i8 = this.downInit;
        boolean z2 = true;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
        boolean z7 = false;
        while (true) {
            i = i6;
            i2 = i7;
            i3 = i8;
            z = false;
            i4 = i5;
            if (!z2) {
                break;
            }
            boolean z8 = true;
            boolean z9 = false;
            boolean z10 = z3;
            while (true) {
                if ((z8 || !z10) && i6 < this.width) {
                    boolean containsBlackPoint = containsBlackPoint(i7, i8, i6, false);
                    if (containsBlackPoint) {
                        i6++;
                        z10 = true;
                        z9 = true;
                        z8 = containsBlackPoint;
                    } else {
                        z8 = containsBlackPoint;
                        if (!z10) {
                            i6++;
                            z8 = containsBlackPoint;
                        }
                    }
                }
            }
            if (i6 >= this.width) {
                break;
            }
            boolean z11 = true;
            while (true) {
                if ((z11 || !z4) && i8 < this.height) {
                    boolean containsBlackPoint2 = containsBlackPoint(i5, i6, i8, true);
                    if (containsBlackPoint2) {
                        i8++;
                        z4 = true;
                        z9 = true;
                        z11 = containsBlackPoint2;
                    } else {
                        z11 = containsBlackPoint2;
                        if (!z4) {
                            i8++;
                            z11 = containsBlackPoint2;
                        }
                    }
                }
            }
            if (i8 >= this.height) {
                break;
            }
            boolean z12 = true;
            while (true) {
                if ((z12 || !z5) && i5 >= 0) {
                    boolean containsBlackPoint3 = containsBlackPoint(i7, i8, i5, false);
                    if (containsBlackPoint3) {
                        i5--;
                        z5 = true;
                        z9 = true;
                        z12 = containsBlackPoint3;
                    } else {
                        z12 = containsBlackPoint3;
                        if (!z5) {
                            i5--;
                            z12 = containsBlackPoint3;
                        }
                    }
                }
            }
            if (i5 < 0) {
                break;
            }
            boolean z13 = true;
            while (true) {
                if ((z13 || !z7) && i7 >= 0) {
                    boolean containsBlackPoint4 = containsBlackPoint(i5, i6, i7, true);
                    if (containsBlackPoint4) {
                        i7--;
                        z7 = true;
                        z9 = true;
                        z13 = containsBlackPoint4;
                    } else {
                        z13 = containsBlackPoint4;
                        if (!z7) {
                            i7--;
                            z13 = containsBlackPoint4;
                        }
                    }
                }
            }
            if (i7 < 0) {
                break;
            }
            if (z9) {
                z6 = true;
            }
            z2 = z9;
            z3 = z10;
        }
        z = true;
        i = i6;
        i2 = i7;
        i3 = i8;
        i4 = i5;
        if (z || !z6) {
            throw NotFoundException.getNotFoundInstance();
        }
        int i9 = i - i4;
        ResultPoint resultPoint = null;
        ResultPoint resultPoint2 = null;
        int i10 = 1;
        while (true) {
            int i11 = i10;
            if (resultPoint2 != null || i11 >= i9) {
                break;
            }
            resultPoint2 = getBlackPointOnSegment(i4, i3 - i11, i4 + i11, i3);
            i10 = i11 + 1;
        }
        if (resultPoint2 == null) {
            throw NotFoundException.getNotFoundInstance();
        }
        ResultPoint resultPoint3 = null;
        int i12 = 1;
        while (true) {
            int i13 = i12;
            if (resultPoint3 != null || i13 >= i9) {
                break;
            }
            resultPoint3 = getBlackPointOnSegment(i4, i2 + i13, i4 + i13, i2);
            i12 = i13 + 1;
        }
        if (resultPoint3 == null) {
            throw NotFoundException.getNotFoundInstance();
        }
        ResultPoint resultPoint4 = null;
        int i14 = 1;
        while (true) {
            int i15 = i14;
            if (resultPoint4 != null || i15 >= i9) {
                break;
            }
            resultPoint4 = getBlackPointOnSegment(i, i2 + i15, i - i15, i2);
            i14 = i15 + 1;
        }
        if (resultPoint4 == null) {
            throw NotFoundException.getNotFoundInstance();
        }
        int i16 = 1;
        while (true) {
            int i17 = i16;
            if (resultPoint != null || i17 >= i9) {
                break;
            }
            resultPoint = getBlackPointOnSegment(i, i3 - i17, i - i17, i3);
            i16 = i17 + 1;
        }
        if (resultPoint != null) {
            return centerEdges(resultPoint, resultPoint2, resultPoint4, resultPoint3);
        }
        throw NotFoundException.getNotFoundInstance();
    }
}
