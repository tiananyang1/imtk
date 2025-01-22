package com.google.zxing.common.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;
import com.sun.jna.Function;

@Deprecated
/* loaded from: classes08-dex2jar.jar:com/google/zxing/common/detector/MonochromeRectangleDetector.class */
public final class MonochromeRectangleDetector {
    private static final int MAX_MODULES = 32;
    private final BitMatrix image;

    public MonochromeRectangleDetector(BitMatrix bitMatrix) {
        this.image = bitMatrix;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0100 A[EDGE_INSN: B:71:0x0100->B:50:0x0100 BREAK  A[LOOP:3: B:42:0x00c9->B:65:0x00c9], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0077 A[EDGE_INSN: B:85:0x0077->B:22:0x0077 BREAK  A[LOOP:1: B:14:0x0041->B:79:0x0041], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int[] blackWhiteRange(int r6, int r7, int r8, int r9, boolean r10) {
        /*
            Method dump skipped, instructions count: 305
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.common.detector.MonochromeRectangleDetector.blackWhiteRange(int, int, int, int, boolean):int[]");
    }

    /* JADX WARN: Multi-variable type inference failed */
    private ResultPoint findCornerFromCenter(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) throws NotFoundException {
        int i10 = i;
        int i11 = i5;
        int[] iArr = null;
        while (true) {
            int[] iArr2 = iArr;
            if (i11 >= i8 || i11 < i7 || i10 >= i4 || i10 < i3) {
                break;
            }
            int[] blackWhiteRange = i2 == 0 ? blackWhiteRange(i11, i9, i3, i4, true) : blackWhiteRange(i10, i9, i7, i8, false);
            if (blackWhiteRange == null) {
                if (iArr2 == null) {
                    throw NotFoundException.getNotFoundInstance();
                }
                if (i2 == 0) {
                    int i12 = i11 - i6;
                    if (iArr2[0] >= i) {
                        return new ResultPoint(iArr2[1], i12);
                    }
                    if (iArr2[1] <= i) {
                        return new ResultPoint(iArr2[0], i12);
                    }
                    byte b = true;
                    if (i6 > 0) {
                        b = false;
                    }
                    return new ResultPoint(iArr2[b == true ? 1 : 0], i12);
                }
                int i13 = i10 - i2;
                if (iArr2[0] >= i5) {
                    return new ResultPoint(i13, iArr2[1]);
                }
                if (iArr2[1] <= i5) {
                    return new ResultPoint(i13, iArr2[0]);
                }
                float f = i13;
                byte b2 = true;
                if (i2 < 0) {
                    b2 = false;
                }
                return new ResultPoint(f, iArr2[b2 == true ? 1 : 0]);
            }
            i11 += i6;
            i10 += i2;
            iArr = blackWhiteRange;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public ResultPoint[] detect() throws NotFoundException {
        int height = this.image.getHeight();
        int width = this.image.getWidth();
        int i = height / 2;
        int i2 = width / 2;
        int max = Math.max(1, height / Function.MAX_NARGS);
        int max2 = Math.max(1, width / Function.MAX_NARGS);
        int i3 = -max;
        int i4 = i2 / 2;
        int y = ((int) findCornerFromCenter(i2, 0, 0, width, i, i3, 0, height, i4).getY()) - 1;
        int i5 = -max2;
        int i6 = i / 2;
        ResultPoint findCornerFromCenter = findCornerFromCenter(i2, i5, 0, width, i, 0, y, height, i6);
        int x = ((int) findCornerFromCenter.getX()) - 1;
        ResultPoint findCornerFromCenter2 = findCornerFromCenter(i2, max2, x, width, i, 0, y, height, i6);
        int x2 = ((int) findCornerFromCenter2.getX()) + 1;
        ResultPoint findCornerFromCenter3 = findCornerFromCenter(i2, 0, x, x2, i, max, y, height, i4);
        return new ResultPoint[]{findCornerFromCenter(i2, 0, x, x2, i, i3, y, ((int) findCornerFromCenter3.getY()) + 1, i2 / 4), findCornerFromCenter, findCornerFromCenter2, findCornerFromCenter3};
    }
}
