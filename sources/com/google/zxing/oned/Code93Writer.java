package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.util.Map;

/* loaded from: classes08-dex2jar.jar:com/google/zxing/oned/Code93Writer.class */
public class Code93Writer extends OneDimensionalCodeWriter {
    protected static int appendPattern(boolean[] zArr, int i, int[] iArr, boolean z) {
        int length = iArr.length;
        int i2 = 0;
        while (i2 < length) {
            zArr[i] = iArr[i2] != 0;
            i2++;
            i++;
        }
        return 9;
    }

    private static int computeChecksumIndex(String str, int i) {
        int i2 = 0;
        int i3 = 1;
        for (int length = str.length() - 1; length >= 0; length--) {
            i2 += "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*".indexOf(str.charAt(length)) * i3;
            int i4 = i3 + 1;
            i3 = i4;
            if (i4 > i) {
                i3 = 1;
            }
        }
        return i2 % 47;
    }

    private static void toIntArray(int i, int[] iArr) {
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 >= 9) {
                return;
            }
            int i4 = 1;
            if (((1 << (8 - i3)) & i) == 0) {
                i4 = 0;
            }
            iArr[i3] = i4;
            i2 = i3 + 1;
        }
    }

    @Override // com.google.zxing.oned.OneDimensionalCodeWriter, com.google.zxing.Writer
    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) throws WriterException {
        if (barcodeFormat == BarcodeFormat.CODE_93) {
            return super.encode(str, barcodeFormat, i, i2, map);
        }
        throw new IllegalArgumentException("Can only encode CODE_93, but got " + barcodeFormat);
    }

    @Override // com.google.zxing.oned.OneDimensionalCodeWriter
    public boolean[] encode(String str) {
        int length = str.length();
        if (length > 80) {
            throw new IllegalArgumentException("Requested contents should be less than 80 digits long, but got " + length);
        }
        int[] iArr = new int[9];
        boolean[] zArr = new boolean[((str.length() + 2 + 2) * 9) + 1];
        toIntArray(Code93Reader.CHARACTER_ENCODINGS[47], iArr);
        int appendPattern = appendPattern(zArr, 0, iArr, true);
        for (int i = 0; i < length; i++) {
            toIntArray(Code93Reader.CHARACTER_ENCODINGS["0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*".indexOf(str.charAt(i))], iArr);
            appendPattern += appendPattern(zArr, appendPattern, iArr, true);
        }
        int computeChecksumIndex = computeChecksumIndex(str, 20);
        toIntArray(Code93Reader.CHARACTER_ENCODINGS[computeChecksumIndex], iArr);
        int appendPattern2 = appendPattern + appendPattern(zArr, appendPattern, iArr, true);
        toIntArray(Code93Reader.CHARACTER_ENCODINGS[computeChecksumIndex(str + "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*".charAt(computeChecksumIndex), 15)], iArr);
        int appendPattern3 = appendPattern2 + appendPattern(zArr, appendPattern2, iArr, true);
        toIntArray(Code93Reader.CHARACTER_ENCODINGS[47], iArr);
        zArr[appendPattern3 + appendPattern(zArr, appendPattern3, iArr, true)] = true;
        return zArr;
    }
}
