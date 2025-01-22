package com.google.zxing.common;

import com.google.zxing.NotFoundException;

/* loaded from: classes08-dex2jar.jar:com/google/zxing/common/DefaultGridSampler.class */
public final class DefaultGridSampler extends GridSampler {
    @Override // com.google.zxing.common.GridSampler
    public BitMatrix sampleGrid(BitMatrix bitMatrix, int i, int i2, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16) throws NotFoundException {
        return sampleGrid(bitMatrix, i, i2, PerspectiveTransform.quadrilateralToQuadrilateral(f, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14, f15, f16));
    }

    @Override // com.google.zxing.common.GridSampler
    public BitMatrix sampleGrid(BitMatrix bitMatrix, int i, int i2, PerspectiveTransform perspectiveTransform) throws NotFoundException {
        if (i <= 0 || i2 <= 0) {
            throw NotFoundException.getNotFoundInstance();
        }
        BitMatrix bitMatrix2 = new BitMatrix(i, i2);
        float[] fArr = new float[i * 2];
        int i3 = 0;
        while (true) {
            int i4 = i3;
            if (i4 >= i2) {
                return bitMatrix2;
            }
            int length = fArr.length;
            float f = i4;
            int i5 = 0;
            while (true) {
                int i6 = i5;
                if (i6 >= length) {
                    break;
                }
                fArr[i6] = (i6 / 2) + 0.5f;
                fArr[i6 + 1] = f + 0.5f;
                i5 = i6 + 2;
            }
            perspectiveTransform.transformPoints(fArr);
            checkAndNudgePoints(bitMatrix, fArr);
            int i7 = 0;
            while (true) {
                int i8 = i7;
                if (i8 < length) {
                    try {
                        if (bitMatrix.get((int) fArr[i8], (int) fArr[i8 + 1])) {
                            bitMatrix2.set(i8 / 2, i4);
                        }
                        i7 = i8 + 2;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw NotFoundException.getNotFoundInstance();
                    }
                }
            }
            i3 = i4 + 1;
        }
    }
}
