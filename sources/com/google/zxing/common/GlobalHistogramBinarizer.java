package com.google.zxing.common;

import com.google.zxing.Binarizer;
import com.google.zxing.LuminanceSource;
import com.google.zxing.NotFoundException;

/* loaded from: classes08-dex2jar.jar:com/google/zxing/common/GlobalHistogramBinarizer.class */
public class GlobalHistogramBinarizer extends Binarizer {
    private static final byte[] EMPTY = new byte[0];
    private static final int LUMINANCE_BITS = 5;
    private static final int LUMINANCE_BUCKETS = 32;
    private static final int LUMINANCE_SHIFT = 3;
    private final int[] buckets;
    private byte[] luminances;

    public GlobalHistogramBinarizer(LuminanceSource luminanceSource) {
        super(luminanceSource);
        this.luminances = EMPTY;
        this.buckets = new int[32];
    }

    private static int estimateBlackPoint(int[] iArr) throws NotFoundException {
        int length = iArr.length;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i < length) {
            int i5 = i2;
            if (iArr[i] > i2) {
                i5 = iArr[i];
                i4 = i;
            }
            int i6 = i3;
            if (iArr[i] > i3) {
                i6 = iArr[i];
            }
            i++;
            i2 = i5;
            i3 = i6;
        }
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        while (i9 < length) {
            int i10 = i9 - i4;
            int i11 = iArr[i9] * i10 * i10;
            int i12 = i8;
            if (i11 > i8) {
                i7 = i9;
                i12 = i11;
            }
            i9++;
            i8 = i12;
        }
        int i13 = i7;
        int i14 = i4;
        if (i4 > i7) {
            i14 = i7;
            i13 = i4;
        }
        if (i13 - i14 <= length / 16) {
            throw NotFoundException.getNotFoundInstance();
        }
        int i15 = i13 - 1;
        int i16 = i15;
        int i17 = -1;
        while (true) {
            int i18 = i17;
            if (i15 <= i14) {
                return i16 << 3;
            }
            int i19 = i15 - i14;
            int i20 = i19 * i19 * (i13 - i15) * (i3 - iArr[i15]);
            int i21 = i18;
            if (i20 > i18) {
                i16 = i15;
                i21 = i20;
            }
            i15--;
            i17 = i21;
        }
    }

    private void initArrays(int i) {
        if (this.luminances.length < i) {
            this.luminances = new byte[i];
        }
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 >= 32) {
                return;
            }
            this.buckets[i3] = 0;
            i2 = i3 + 1;
        }
    }

    @Override // com.google.zxing.Binarizer
    public Binarizer createBinarizer(LuminanceSource luminanceSource) {
        return new GlobalHistogramBinarizer(luminanceSource);
    }

    @Override // com.google.zxing.Binarizer
    public BitMatrix getBlackMatrix() throws NotFoundException {
        LuminanceSource luminanceSource = getLuminanceSource();
        int width = luminanceSource.getWidth();
        int height = luminanceSource.getHeight();
        BitMatrix bitMatrix = new BitMatrix(width, height);
        initArrays(width);
        int[] iArr = this.buckets;
        int i = 1;
        while (true) {
            int i2 = i;
            if (i2 >= 5) {
                break;
            }
            byte[] row = luminanceSource.getRow((height * i2) / 5, this.luminances);
            int i3 = (width << 2) / 5;
            int i4 = width / 5;
            while (true) {
                int i5 = i4;
                if (i5 < i3) {
                    int i6 = (row[i5] & 255) >> 3;
                    iArr[i6] = iArr[i6] + 1;
                    i4 = i5 + 1;
                }
            }
            i = i2 + 1;
        }
        int estimateBlackPoint = estimateBlackPoint(iArr);
        byte[] matrix = luminanceSource.getMatrix();
        int i7 = 0;
        while (true) {
            int i8 = i7;
            if (i8 >= height) {
                return bitMatrix;
            }
            int i9 = 0;
            while (true) {
                int i10 = i9;
                if (i10 < width) {
                    if ((matrix[(i8 * width) + i10] & 255) < estimateBlackPoint) {
                        bitMatrix.set(i10, i8);
                    }
                    i9 = i10 + 1;
                }
            }
            i7 = i8 + 1;
        }
    }

    @Override // com.google.zxing.Binarizer
    public BitArray getBlackRow(int i, BitArray bitArray) throws NotFoundException {
        LuminanceSource luminanceSource = getLuminanceSource();
        int width = luminanceSource.getWidth();
        if (bitArray == null || bitArray.getSize() < width) {
            bitArray = new BitArray(width);
        } else {
            bitArray.clear();
        }
        initArrays(width);
        byte[] row = luminanceSource.getRow(i, this.luminances);
        int[] iArr = this.buckets;
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 >= width) {
                break;
            }
            int i4 = (row[i3] & 255) >> 3;
            iArr[i4] = iArr[i4] + 1;
            i2 = i3 + 1;
        }
        int estimateBlackPoint = estimateBlackPoint(iArr);
        if (width < 3) {
            int i5 = 0;
            while (true) {
                int i6 = i5;
                if (i6 >= width) {
                    break;
                }
                if ((row[i6] & 255) < estimateBlackPoint) {
                    bitArray.set(i6);
                }
                i5 = i6 + 1;
            }
        } else {
            byte b = row[0];
            int i7 = row[1] & 255;
            int i8 = b & 255;
            int i9 = 1;
            while (i9 < width - 1) {
                int i10 = i9 + 1;
                int i11 = row[i10] & 255;
                if ((((i7 << 2) - i8) - i11) / 2 < estimateBlackPoint) {
                    bitArray.set(i9);
                }
                i8 = i7;
                i9 = i10;
                i7 = i11;
            }
        }
        return bitArray;
    }
}
