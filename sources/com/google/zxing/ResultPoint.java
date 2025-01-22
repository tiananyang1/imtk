package com.google.zxing;

import com.google.zxing.common.detector.MathUtils;

/* loaded from: classes08-dex2jar.jar:com/google/zxing/ResultPoint.class */
public class ResultPoint {

    /* renamed from: x */
    private final float f9x;

    /* renamed from: y */
    private final float f10y;

    public ResultPoint(float f, float f2) {
        this.f9x = f;
        this.f10y = f2;
    }

    private static float crossProductZ(ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3) {
        float f = resultPoint2.f9x;
        float f2 = resultPoint2.f10y;
        return ((resultPoint3.f9x - f) * (resultPoint.f10y - f2)) - ((resultPoint3.f10y - f2) * (resultPoint.f9x - f));
    }

    public static float distance(ResultPoint resultPoint, ResultPoint resultPoint2) {
        return MathUtils.distance(resultPoint.f9x, resultPoint.f10y, resultPoint2.f9x, resultPoint2.f10y);
    }

    public static void orderBestPatterns(ResultPoint[] resultPointArr) {
        ResultPoint resultPoint;
        ResultPoint resultPoint2;
        ResultPoint resultPoint3;
        float distance = distance(resultPointArr[0], resultPointArr[1]);
        float distance2 = distance(resultPointArr[1], resultPointArr[2]);
        float distance3 = distance(resultPointArr[0], resultPointArr[2]);
        if (distance2 >= distance && distance2 >= distance3) {
            resultPoint = resultPointArr[0];
            resultPoint2 = resultPointArr[1];
            resultPoint3 = resultPointArr[2];
        } else if (distance3 < distance2 || distance3 < distance) {
            resultPoint = resultPointArr[2];
            resultPoint2 = resultPointArr[0];
            resultPoint3 = resultPointArr[1];
        } else {
            resultPoint = resultPointArr[1];
            resultPoint2 = resultPointArr[0];
            resultPoint3 = resultPointArr[2];
        }
        ResultPoint resultPoint4 = resultPoint2;
        ResultPoint resultPoint5 = resultPoint3;
        if (crossProductZ(resultPoint2, resultPoint, resultPoint3) < 0.0f) {
            resultPoint5 = resultPoint2;
            resultPoint4 = resultPoint3;
        }
        resultPointArr[0] = resultPoint4;
        resultPointArr[1] = resultPoint;
        resultPointArr[2] = resultPoint5;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof ResultPoint)) {
            return false;
        }
        ResultPoint resultPoint = (ResultPoint) obj;
        return this.f9x == resultPoint.f9x && this.f10y == resultPoint.f10y;
    }

    public final float getX() {
        return this.f9x;
    }

    public final float getY() {
        return this.f10y;
    }

    public final int hashCode() {
        return (Float.floatToIntBits(this.f9x) * 31) + Float.floatToIntBits(this.f10y);
    }

    public final String toString() {
        return "(" + this.f9x + ',' + this.f10y + ')';
    }
}
