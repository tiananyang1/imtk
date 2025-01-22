package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.EnumMap;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes08-dex2jar.jar:com/google/zxing/oned/UPCEANExtension5Support.class */
public final class UPCEANExtension5Support {
    private static final int[] CHECK_DIGIT_ENCODINGS = {24, 20, 18, 17, 12, 6, 3, 10, 9, 5};
    private final int[] decodeMiddleCounters = new int[4];
    private final StringBuilder decodeRowStringBuffer = new StringBuilder();

    private int decodeMiddle(BitArray bitArray, int[] iArr, StringBuilder sb) throws NotFoundException {
        int i;
        int[] iArr2 = this.decodeMiddleCounters;
        iArr2[0] = 0;
        iArr2[1] = 0;
        iArr2[2] = 0;
        iArr2[3] = 0;
        int size = bitArray.getSize();
        int i2 = iArr[1];
        int i3 = 0;
        int i4 = 0;
        while (true) {
            i = i4;
            if (i3 >= 5 || i2 >= size) {
                break;
            }
            int decodeDigit = UPCEANReader.decodeDigit(bitArray, iArr2, i2, UPCEANReader.L_AND_G_PATTERNS);
            sb.append((char) ((decodeDigit % 10) + 48));
            int length = iArr2.length;
            int i5 = 0;
            while (true) {
                int i6 = i5;
                if (i6 >= length) {
                    break;
                }
                i2 += iArr2[i6];
                i5 = i6 + 1;
            }
            int i7 = i;
            if (decodeDigit >= 10) {
                i7 = i | (1 << (4 - i3));
            }
            if (i3 != 4) {
                i2 = bitArray.getNextUnset(bitArray.getNextSet(i2));
            }
            i3++;
            i4 = i7;
        }
        if (sb.length() != 5) {
            throw NotFoundException.getNotFoundInstance();
        }
        if (extensionChecksum(sb.toString()) == determineCheckDigit(i)) {
            return i2;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static int determineCheckDigit(int i) throws NotFoundException {
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 >= 10) {
                throw NotFoundException.getNotFoundInstance();
            }
            if (i == CHECK_DIGIT_ENCODINGS[i3]) {
                return i3;
            }
            i2 = i3 + 1;
        }
    }

    private static int extensionChecksum(CharSequence charSequence) {
        int length = charSequence.length();
        int i = 0;
        for (int i2 = length - 2; i2 >= 0; i2 -= 2) {
            i += charSequence.charAt(i2) - '0';
        }
        int i3 = i * 3;
        int i4 = length;
        int i5 = 1;
        while (true) {
            int i6 = i4 - i5;
            if (i6 < 0) {
                return (i3 * 3) % 10;
            }
            i3 += charSequence.charAt(i6) - '0';
            i4 = i6;
            i5 = 2;
        }
    }

    private static String parseExtension5String(String str) {
        String valueOf;
        char charAt = str.charAt(0);
        String str2 = "";
        if (charAt == '0') {
            str2 = "£";
        } else if (charAt == '5') {
            str2 = "$";
        } else if (charAt == '9') {
            if ("90000".equals(str)) {
                return null;
            }
            if ("99991".equals(str)) {
                return "0.00";
            }
            if ("99990".equals(str)) {
                return "Used";
            }
        }
        int parseInt = Integer.parseInt(str.substring(1));
        int i = parseInt / 100;
        int i2 = parseInt % 100;
        if (i2 < 10) {
            valueOf = "0" + i2;
        } else {
            valueOf = String.valueOf(i2);
        }
        return str2 + String.valueOf(i) + '.' + valueOf;
    }

    private static Map<ResultMetadataType, Object> parseExtensionString(String str) {
        String parseExtension5String;
        if (str.length() != 5 || (parseExtension5String = parseExtension5String(str)) == null) {
            return null;
        }
        EnumMap enumMap = new EnumMap(ResultMetadataType.class);
        enumMap.put((EnumMap) ResultMetadataType.SUGGESTED_PRICE, (ResultMetadataType) parseExtension5String);
        return enumMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Result decodeRow(int i, BitArray bitArray, int[] iArr) throws NotFoundException {
        StringBuilder sb = this.decodeRowStringBuffer;
        sb.setLength(0);
        int decodeMiddle = decodeMiddle(bitArray, iArr, sb);
        String sb2 = sb.toString();
        Map<ResultMetadataType, Object> parseExtensionString = parseExtensionString(sb2);
        float f = i;
        Result result = new Result(sb2, null, new ResultPoint[]{new ResultPoint((iArr[0] + iArr[1]) / 2.0f, f), new ResultPoint(decodeMiddle, f)}, BarcodeFormat.UPC_EAN_EXTENSION);
        if (parseExtensionString != null) {
            result.putAllMetadata(parseExtensionString);
        }
        return result;
    }
}
