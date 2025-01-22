package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.util.Map;

/* loaded from: classes08-dex2jar.jar:com/google/zxing/oned/UPCEWriter.class */
public final class UPCEWriter extends UPCEANWriter {
    private static final int CODE_WIDTH = 51;

    @Override // com.google.zxing.oned.OneDimensionalCodeWriter, com.google.zxing.Writer
    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) throws WriterException {
        if (barcodeFormat == BarcodeFormat.UPC_E) {
            return super.encode(str, barcodeFormat, i, i2, map);
        }
        throw new IllegalArgumentException("Can only encode UPC_E, but got " + barcodeFormat);
    }

    @Override // com.google.zxing.oned.OneDimensionalCodeWriter
    public boolean[] encode(String str) {
        if (str.length() != 8) {
            throw new IllegalArgumentException("Requested contents should be 8 digits long, but got " + str.length());
        }
        int i = UPCEReader.CHECK_DIGIT_ENCODINGS[Integer.parseInt(str.substring(7, 8))];
        boolean[] zArr = new boolean[51];
        int appendPattern = appendPattern(zArr, 0, UPCEANReader.START_END_PATTERN, true) + 0;
        int i2 = 1;
        while (true) {
            int i3 = i2;
            if (i3 > 6) {
                appendPattern(zArr, appendPattern, UPCEANReader.END_PATTERN, false);
                return zArr;
            }
            int i4 = i3 + 1;
            int parseInt = Integer.parseInt(str.substring(i3, i4));
            int i5 = parseInt;
            if (((i >> (6 - i3)) & 1) == 1) {
                i5 = parseInt + 10;
            }
            appendPattern += appendPattern(zArr, appendPattern, UPCEANReader.L_AND_G_PATTERNS[i5], false);
            i2 = i4;
        }
    }
}
