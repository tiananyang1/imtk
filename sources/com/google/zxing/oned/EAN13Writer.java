package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.util.Map;

/* loaded from: classes08-dex2jar.jar:com/google/zxing/oned/EAN13Writer.class */
public final class EAN13Writer extends UPCEANWriter {
    private static final int CODE_WIDTH = 95;

    @Override // com.google.zxing.oned.OneDimensionalCodeWriter, com.google.zxing.Writer
    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) throws WriterException {
        if (barcodeFormat == BarcodeFormat.EAN_13) {
            return super.encode(str, barcodeFormat, i, i2, map);
        }
        throw new IllegalArgumentException("Can only encode EAN_13, but got " + barcodeFormat);
    }

    @Override // com.google.zxing.oned.OneDimensionalCodeWriter
    public boolean[] encode(String str) {
        if (str.length() != 13) {
            throw new IllegalArgumentException("Requested contents should be 13 digits long, but got " + str.length());
        }
        try {
            if (!UPCEANReader.checkStandardUPCEANChecksum(str)) {
                throw new IllegalArgumentException("Contents do not pass checksum");
            }
            int i = EAN13Reader.FIRST_DIGIT_ENCODINGS[Integer.parseInt(str.substring(0, 1))];
            boolean[] zArr = new boolean[95];
            int appendPattern = appendPattern(zArr, 0, UPCEANReader.START_END_PATTERN, true) + 0;
            int i2 = 1;
            while (true) {
                int i3 = i2;
                if (i3 > 6) {
                    break;
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
            int appendPattern2 = appendPattern + appendPattern(zArr, appendPattern, UPCEANReader.MIDDLE_PATTERN, false);
            int i6 = 7;
            while (true) {
                int i7 = i6;
                if (i7 > 12) {
                    appendPattern(zArr, appendPattern2, UPCEANReader.START_END_PATTERN, true);
                    return zArr;
                }
                int i8 = i7 + 1;
                appendPattern2 += appendPattern(zArr, appendPattern2, UPCEANReader.L_PATTERNS[Integer.parseInt(str.substring(i7, i8))], true);
                i6 = i8;
            }
        } catch (FormatException e) {
            throw new IllegalArgumentException("Illegal contents");
        }
    }
}
