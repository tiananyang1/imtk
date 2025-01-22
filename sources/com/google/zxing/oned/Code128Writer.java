package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.util.Map;

/* loaded from: classes08-dex2jar.jar:com/google/zxing/oned/Code128Writer.class */
public final class Code128Writer extends OneDimensionalCodeWriter {
    private static final int CODE_CODE_B = 100;
    private static final int CODE_CODE_C = 99;
    private static final int CODE_FNC_1 = 102;
    private static final int CODE_FNC_2 = 97;
    private static final int CODE_FNC_3 = 96;
    private static final int CODE_FNC_4_B = 100;
    private static final int CODE_START_B = 104;
    private static final int CODE_START_C = 105;
    private static final int CODE_STOP = 106;
    private static final char ESCAPE_FNC_1 = 241;
    private static final char ESCAPE_FNC_2 = 242;
    private static final char ESCAPE_FNC_3 = 243;
    private static final char ESCAPE_FNC_4 = 244;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes08-dex2jar.jar:com/google/zxing/oned/Code128Writer$CType.class */
    public enum CType {
        UNCODABLE,
        ONE_DIGIT,
        TWO_DIGITS,
        FNC_1
    }

    private static int chooseCode(CharSequence charSequence, int i, int i2) {
        CType findCType;
        CType findCType2;
        CType findCType3 = findCType(charSequence, i);
        if (findCType3 == CType.UNCODABLE || findCType3 == CType.ONE_DIGIT) {
            return 100;
        }
        if (i2 == 99) {
            return i2;
        }
        if (i2 != 100) {
            CType cType = findCType3;
            if (findCType3 == CType.FNC_1) {
                cType = findCType(charSequence, i + 1);
            }
            return cType == CType.TWO_DIGITS ? 99 : 100;
        }
        if (findCType3 != CType.FNC_1 && (findCType = findCType(charSequence, i + 2)) != CType.UNCODABLE && findCType != CType.ONE_DIGIT) {
            if (findCType == CType.FNC_1) {
                return findCType(charSequence, i + 3) == CType.TWO_DIGITS ? 99 : 100;
            }
            int i3 = i;
            int i4 = 4;
            while (true) {
                int i5 = i3 + i4;
                findCType2 = findCType(charSequence, i5);
                if (findCType2 != CType.TWO_DIGITS) {
                    break;
                }
                i3 = i5;
                i4 = 2;
            }
            return findCType2 == CType.ONE_DIGIT ? 100 : 99;
        }
        return i2;
    }

    private static CType findCType(CharSequence charSequence, int i) {
        int length = charSequence.length();
        if (i >= length) {
            return CType.UNCODABLE;
        }
        char charAt = charSequence.charAt(i);
        if (charAt == ESCAPE_FNC_1) {
            return CType.FNC_1;
        }
        if (charAt < '0' || charAt > '9') {
            return CType.UNCODABLE;
        }
        int i2 = i + 1;
        if (i2 >= length) {
            return CType.ONE_DIGIT;
        }
        char charAt2 = charSequence.charAt(i2);
        return (charAt2 < '0' || charAt2 > '9') ? CType.ONE_DIGIT : CType.TWO_DIGITS;
    }

    @Override // com.google.zxing.oned.OneDimensionalCodeWriter, com.google.zxing.Writer
    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) throws WriterException {
        if (barcodeFormat == BarcodeFormat.CODE_128) {
            return super.encode(str, barcodeFormat, i, i2, map);
        }
        throw new IllegalArgumentException("Can only encode CODE_128, but got " + barcodeFormat);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0116, code lost:            r14 = r14 + 1;        r15 = r13;     */
    @Override // com.google.zxing.oned.OneDimensionalCodeWriter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean[] encode(java.lang.String r7) {
        /*
            Method dump skipped, instructions count: 558
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.Code128Writer.encode(java.lang.String):boolean[]");
    }
}
