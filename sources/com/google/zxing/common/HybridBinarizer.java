package com.google.zxing.common;

import com.google.zxing.Binarizer;
import com.google.zxing.LuminanceSource;
import com.google.zxing.NotFoundException;
import java.lang.reflect.Array;

/* loaded from: classes08-dex2jar.jar:com/google/zxing/common/HybridBinarizer.class */
public final class HybridBinarizer extends GlobalHistogramBinarizer {
    private static final int BLOCK_SIZE = 8;
    private static final int BLOCK_SIZE_MASK = 7;
    private static final int BLOCK_SIZE_POWER = 3;
    private static final int MINIMUM_DIMENSION = 40;
    private static final int MIN_DYNAMIC_RANGE = 24;
    private BitMatrix matrix;

    public HybridBinarizer(LuminanceSource luminanceSource) {
        super(luminanceSource);
    }

    private static int[][] calculateBlackPoints(byte[] bArr, int i, int i2, int i3, int i4) {
        int[][] iArr = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, i2, i);
        int i5 = 0;
        while (true) {
            int i6 = i5;
            if (i6 >= i2) {
                return iArr;
            }
            int i7 = i6 << 3;
            int i8 = i4 - 8;
            int i9 = i7;
            if (i7 > i8) {
                i9 = i8;
            }
            int i10 = 0;
            while (true) {
                int i11 = i10;
                if (i11 < i) {
                    int i12 = i11 << 3;
                    int i13 = i3 - 8;
                    int i14 = i12;
                    if (i12 > i13) {
                        i14 = i13;
                    }
                    int i15 = (i9 * i3) + i14;
                    int i16 = 0;
                    int i17 = 0;
                    int i18 = 0;
                    int i19 = 255;
                    while (i16 < 8) {
                        int i20 = 0;
                        while (i20 < 8) {
                            int i21 = bArr[i15 + i20] & 255;
                            int i22 = i17 + i21;
                            int i23 = i19;
                            if (i21 < i19) {
                                i23 = i21;
                            }
                            int i24 = i18;
                            if (i21 > i18) {
                                i24 = i21;
                            }
                            i20++;
                            i18 = i24;
                            i19 = i23;
                            i17 = i22;
                        }
                        int i25 = i16;
                        int i26 = i15;
                        int i27 = i17;
                        if (i18 - i19 > 24) {
                            int i28 = i15;
                            int i29 = i16;
                            while (true) {
                                int i30 = i29 + 1;
                                int i31 = i28 + i3;
                                i25 = i30;
                                i26 = i31;
                                i27 = i17;
                                if (i30 < 8) {
                                    int i32 = 0;
                                    int i33 = i17;
                                    while (true) {
                                        i29 = i30;
                                        i28 = i31;
                                        i17 = i33;
                                        if (i32 < 8) {
                                            i33 += bArr[i31 + i32] & 255;
                                            i32++;
                                        }
                                    }
                                }
                            }
                        }
                        i17 = i27;
                        i16 = i25 + 1;
                        i15 = i26 + i3;
                    }
                    int i34 = i17 >> 6;
                    if (i18 - i19 <= 24) {
                        int i35 = i19 / 2;
                        i34 = i35;
                        if (i6 > 0) {
                            i34 = i35;
                            if (i11 > 0) {
                                int i36 = i6 - 1;
                                int i37 = iArr[i36][i11];
                                int[] iArr2 = iArr[i6];
                                int i38 = i11 - 1;
                                int i39 = ((i37 + (iArr2[i38] * 2)) + iArr[i36][i38]) / 4;
                                i34 = i35;
                                if (i19 < i39) {
                                    i34 = i39;
                                }
                            }
                        }
                    }
                    iArr[i6][i11] = i34;
                    i10 = i11 + 1;
                }
            }
            i5 = i6 + 1;
        }
    }

    private static void calculateThresholdForBlock(byte[] bArr, int i, int i2, int i3, int i4, int[][] iArr, BitMatrix bitMatrix) {
        int i5 = 0;
        while (true) {
            int i6 = i5;
            if (i6 >= i2) {
                return;
            }
            int i7 = i6 << 3;
            int i8 = i4 - 8;
            int i9 = i7;
            if (i7 > i8) {
                i9 = i8;
            }
            int i10 = 0;
            while (true) {
                int i11 = i10;
                if (i11 < i) {
                    int i12 = i11 << 3;
                    int i13 = i3 - 8;
                    if (i12 > i13) {
                        i12 = i13;
                    }
                    int cap = cap(i11, 2, i - 3);
                    int cap2 = cap(i6, 2, i2 - 3);
                    int i14 = 0;
                    for (int i15 = -2; i15 <= 2; i15++) {
                        int[] iArr2 = iArr[cap2 + i15];
                        i14 += iArr2[cap - 2] + iArr2[cap - 1] + iArr2[cap] + iArr2[cap + 1] + iArr2[cap + 2];
                    }
                    thresholdBlock(bArr, i12, i9, i14 / 25, i3, bitMatrix);
                    i10 = i11 + 1;
                }
            }
            i5 = i6 + 1;
        }
    }

    private static int cap(int i, int i2, int i3) {
        return i < i2 ? i2 : i > i3 ? i3 : i;
    }

    private static void thresholdBlock(byte[] bArr, int i, int i2, int i3, int i4, BitMatrix bitMatrix) {
        int i5 = (i2 * i4) + i;
        int i6 = 0;
        while (i6 < 8) {
            int i7 = 0;
            while (true) {
                int i8 = i7;
                if (i8 < 8) {
                    if ((bArr[i5 + i8] & 255) <= i3) {
                        bitMatrix.set(i + i8, i2 + i6);
                    }
                    i7 = i8 + 1;
                }
            }
            i6++;
            i5 += i4;
        }
    }

    @Override // com.google.zxing.common.GlobalHistogramBinarizer, com.google.zxing.Binarizer
    public Binarizer createBinarizer(LuminanceSource luminanceSource) {
        return new HybridBinarizer(luminanceSource);
    }

    @Override // com.google.zxing.common.GlobalHistogramBinarizer, com.google.zxing.Binarizer
    public BitMatrix getBlackMatrix() throws NotFoundException {
        BitMatrix bitMatrix = this.matrix;
        if (bitMatrix != null) {
            return bitMatrix;
        }
        LuminanceSource luminanceSource = getLuminanceSource();
        int width = luminanceSource.getWidth();
        int height = luminanceSource.getHeight();
        if (width < 40 || height < 40) {
            this.matrix = super.getBlackMatrix();
        } else {
            byte[] matrix = luminanceSource.getMatrix();
            int i = width >> 3;
            int i2 = i;
            if ((width & 7) != 0) {
                i2 = i + 1;
            }
            int i3 = height >> 3;
            int i4 = i3;
            if ((height & 7) != 0) {
                i4 = i3 + 1;
            }
            int[][] calculateBlackPoints = calculateBlackPoints(matrix, i2, i4, width, height);
            BitMatrix bitMatrix2 = new BitMatrix(width, height);
            calculateThresholdForBlock(matrix, i2, i4, width, height, calculateBlackPoints, bitMatrix2);
            this.matrix = bitMatrix2;
        }
        return this.matrix;
    }
}
