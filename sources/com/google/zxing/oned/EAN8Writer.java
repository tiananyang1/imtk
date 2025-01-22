package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.util.Map;

/* loaded from: classes08-dex2jar.jar:com/google/zxing/oned/EAN8Writer.class */
public final class EAN8Writer extends UPCEANWriter {
    private static final int CODE_WIDTH = 67;

    @Override // com.google.zxing.oned.OneDimensionalCodeWriter, com.google.zxing.Writer
    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) throws WriterException {
        if (barcodeFormat == BarcodeFormat.EAN_8) {
            return super.encode(str, barcodeFormat, i, i2, map);
        }
        throw new IllegalArgumentException("Can only encode EAN_8, but got " + barcodeFormat);
    }

    @Override // com.google.zxing.oned.OneDimensionalCodeWriter
    public boolean[] encode(String str) {
        if (str.length() != 8) {
            throw new IllegalArgumentException("Requested contents should be 8 digits long, but got " + str.length());
        }
        boolean[] zArr = new boolean[67];
        int appendPattern = appendPattern(zArr, 0, UPCEANReader.START_END_PATTERN, true) + 0;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 > 3) {
                break;
            }
            int i3 = i2 + 1;
            appendPattern += appendPattern(zArr, appendPattern, UPCEANReader.L_PATTERNS[Integer.parseInt(str.substring(i2, i3))], false);
            i = i3;
        }
        int appendPattern2 = appendPattern + appendPattern(zArr, appendPattern, UPCEANReader.MIDDLE_PATTERN, false);
        int i4 = 4;
        while (true) {
            int i5 = i4;
            if (i5 > 7) {
                appendPattern(zArr, appendPattern2, UPCEANReader.START_END_PATTERN, true);
                return zArr;
            }
            int i6 = i5 + 1;
            appendPattern2 += appendPattern(zArr, appendPattern2, UPCEANReader.L_PATTERNS[Integer.parseInt(str.substring(i5, i6))], true);
            i4 = i6;
        }
    }
}
