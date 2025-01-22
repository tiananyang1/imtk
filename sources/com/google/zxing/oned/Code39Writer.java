package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.util.Map;

/* loaded from: classes08-dex2jar.jar:com/google/zxing/oned/Code39Writer.class */
public final class Code39Writer extends OneDimensionalCodeWriter {
    private static void toIntArray(int i, int[] iArr) {
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 >= 9) {
                return;
            }
            int i4 = 1;
            if (((1 << (8 - i3)) & i) != 0) {
                i4 = 2;
            }
            iArr[i3] = i4;
            i2 = i3 + 1;
        }
    }

    @Override // com.google.zxing.oned.OneDimensionalCodeWriter, com.google.zxing.Writer
    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) throws WriterException {
        if (barcodeFormat == BarcodeFormat.CODE_39) {
            return super.encode(str, barcodeFormat, i, i2, map);
        }
        throw new IllegalArgumentException("Can only encode CODE_39, but got " + barcodeFormat);
    }

    @Override // com.google.zxing.oned.OneDimensionalCodeWriter
    public boolean[] encode(String str) {
        int length = str.length();
        if (length > 80) {
            throw new IllegalArgumentException("Requested contents should be less than 80 digits long, but got " + length);
        }
        int[] iArr = new int[9];
        int i = length + 25;
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 < length) {
                int indexOf = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. *$/+%".indexOf(str.charAt(i3));
                if (indexOf < 0) {
                    throw new IllegalArgumentException("Bad contents: " + str);
                }
                toIntArray(Code39Reader.CHARACTER_ENCODINGS[indexOf], iArr);
                int i4 = 0;
                while (true) {
                    int i5 = i4;
                    if (i5 < 9) {
                        i += iArr[i5];
                        i4 = i5 + 1;
                    }
                }
                i2 = i3 + 1;
            } else {
                boolean[] zArr = new boolean[i];
                toIntArray(Code39Reader.ASTERISK_ENCODING, iArr);
                int appendPattern = appendPattern(zArr, 0, iArr, true);
                int[] iArr2 = {1};
                int appendPattern2 = appendPattern + appendPattern(zArr, appendPattern, iArr2, false);
                int i6 = 0;
                while (true) {
                    int i7 = i6;
                    if (i7 >= length) {
                        toIntArray(Code39Reader.ASTERISK_ENCODING, iArr);
                        appendPattern(zArr, appendPattern2, iArr, true);
                        return zArr;
                    }
                    toIntArray(Code39Reader.CHARACTER_ENCODINGS["0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. *$/+%".indexOf(str.charAt(i7))], iArr);
                    int appendPattern3 = appendPattern2 + appendPattern(zArr, appendPattern2, iArr, true);
                    appendPattern2 = appendPattern3 + appendPattern(zArr, appendPattern3, iArr2, false);
                    i6 = i7 + 1;
                }
            }
        }
    }
}
