package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitArray;
import java.util.Arrays;
import java.util.Map;

/* loaded from: classes08-dex2jar.jar:com/google/zxing/oned/UPCEANReader.class */
public abstract class UPCEANReader extends OneDReader {
    private static final float MAX_AVG_VARIANCE = 0.48f;
    private static final float MAX_INDIVIDUAL_VARIANCE = 0.7f;
    static final int[] START_END_PATTERN = {1, 1, 1};
    static final int[] MIDDLE_PATTERN = {1, 1, 1, 1, 1};
    static final int[] END_PATTERN = {1, 1, 1, 1, 1, 1};
    static final int[][] L_PATTERNS = {new int[]{3, 2, 1, 1}, new int[]{2, 2, 2, 1}, new int[]{2, 1, 2, 2}, new int[]{1, 4, 1, 1}, new int[]{1, 1, 3, 2}, new int[]{1, 2, 3, 1}, new int[]{1, 1, 1, 4}, new int[]{1, 3, 1, 2}, new int[]{1, 2, 1, 3}, new int[]{3, 1, 1, 2}};
    static final int[][] L_AND_G_PATTERNS = new int[20];
    private final StringBuilder decodeRowStringBuffer = new StringBuilder(20);
    private final UPCEANExtensionSupport extensionReader = new UPCEANExtensionSupport();
    private final EANManufacturerOrgSupport eanManSupport = new EANManufacturerOrgSupport();

    /* JADX WARN: Type inference failed for: r0v10, types: [int[], int[][]] */
    /* JADX WARN: Type inference failed for: r0v8, types: [int[], int[][]] */
    static {
        System.arraycopy(L_PATTERNS, 0, L_AND_G_PATTERNS, 0, 10);
        for (int i = 10; i < 20; i++) {
            int[] iArr = L_PATTERNS[i - 10];
            int[] iArr2 = new int[iArr.length];
            int i2 = 0;
            while (true) {
                int i3 = i2;
                if (i3 < iArr.length) {
                    iArr2[i3] = iArr[(iArr.length - i3) - 1];
                    i2 = i3 + 1;
                }
            }
            L_AND_G_PATTERNS[i] = iArr2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean checkStandardUPCEANChecksum(CharSequence charSequence) throws FormatException {
        int length = charSequence.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        for (int i2 = length - 2; i2 >= 0; i2 -= 2) {
            int charAt = charSequence.charAt(i2) - '0';
            if (charAt < 0 || charAt > 9) {
                throw FormatException.getFormatInstance();
            }
            i += charAt;
        }
        int i3 = i * 3;
        int i4 = length;
        int i5 = 1;
        while (true) {
            int i6 = i4 - i5;
            if (i6 < 0) {
                return i3 % 10 == 0;
            }
            int charAt2 = charSequence.charAt(i6) - '0';
            if (charAt2 < 0 || charAt2 > 9) {
                break;
            }
            i3 += charAt2;
            i4 = i6;
            i5 = 2;
        }
        throw FormatException.getFormatInstance();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int decodeDigit(BitArray bitArray, int[] iArr, int i, int[][] iArr2) throws NotFoundException {
        recordPattern(bitArray, i, iArr);
        int length = iArr2.length;
        float f = 0.48f;
        int i2 = -1;
        int i3 = 0;
        while (i3 < length) {
            float patternMatchVariance = patternMatchVariance(iArr, iArr2[i3], MAX_INDIVIDUAL_VARIANCE);
            float f2 = f;
            if (patternMatchVariance < f) {
                i2 = i3;
                f2 = patternMatchVariance;
            }
            i3++;
            f = f2;
        }
        if (i2 >= 0) {
            return i2;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int[] findGuardPattern(BitArray bitArray, int i, boolean z, int[] iArr) throws NotFoundException {
        return findGuardPattern(bitArray, i, z, iArr, new int[iArr.length]);
    }

    private static int[] findGuardPattern(BitArray bitArray, int i, boolean z, int[] iArr, int[] iArr2) throws NotFoundException {
        int i2;
        int i3;
        int i4;
        int size = bitArray.getSize();
        int nextUnset = z ? bitArray.getNextUnset(i) : bitArray.getNextSet(i);
        int length = iArr.length;
        int i5 = nextUnset;
        int i6 = nextUnset;
        int i7 = 0;
        while (i5 < size) {
            if (bitArray.get(i5) ^ z) {
                iArr2[i7] = iArr2[i7] + 1;
                i4 = i6;
            } else {
                int i8 = length - 1;
                if (i7 != i8) {
                    int i9 = i7 + 1;
                    i2 = i6;
                    i3 = i9;
                } else {
                    if (patternMatchVariance(iArr2, iArr, MAX_INDIVIDUAL_VARIANCE) < MAX_AVG_VARIANCE) {
                        return new int[]{i6, i5};
                    }
                    int i10 = i6 + iArr2[0] + iArr2[1];
                    int i11 = length - 2;
                    System.arraycopy(iArr2, 2, iArr2, 0, i11);
                    iArr2[i11] = 0;
                    iArr2[i8] = 0;
                    i3 = i7 - 1;
                    i2 = i10;
                }
                iArr2[i3] = 1;
                z = !z;
                i4 = i2;
                i7 = i3;
            }
            i5++;
            i6 = i4;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int[] findStartGuardPattern(BitArray bitArray) throws NotFoundException {
        int[] iArr = new int[START_END_PATTERN.length];
        int[] iArr2 = null;
        boolean z = false;
        int i = 0;
        while (!z) {
            Arrays.fill(iArr, 0, START_END_PATTERN.length, 0);
            iArr2 = findGuardPattern(bitArray, i, false, START_END_PATTERN, iArr);
            int i2 = iArr2[0];
            i = iArr2[1];
            int i3 = i2 - (i - i2);
            if (i3 >= 0) {
                z = bitArray.isRange(i3, i2, false);
            }
        }
        return iArr2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean checkChecksum(String str) throws FormatException {
        return checkStandardUPCEANChecksum(str);
    }

    int[] decodeEnd(BitArray bitArray, int i) throws NotFoundException {
        return findGuardPattern(bitArray, i, false, START_END_PATTERN);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract int decodeMiddle(BitArray bitArray, int[] iArr, StringBuilder sb) throws NotFoundException;

    @Override // com.google.zxing.oned.OneDReader
    public Result decodeRow(int i, BitArray bitArray, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException {
        return decodeRow(i, bitArray, findStartGuardPattern(bitArray), map);
    }

    public Result decodeRow(int i, BitArray bitArray, int[] iArr, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException {
        int i2;
        String lookupCountryIdentifier;
        boolean z;
        ResultPointCallback resultPointCallback = map == null ? null : (ResultPointCallback) map.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
        if (resultPointCallback != null) {
            resultPointCallback.foundPossibleResultPoint(new ResultPoint((iArr[0] + iArr[1]) / 2.0f, i));
        }
        StringBuilder sb = this.decodeRowStringBuffer;
        sb.setLength(0);
        int decodeMiddle = decodeMiddle(bitArray, iArr, sb);
        if (resultPointCallback != null) {
            resultPointCallback.foundPossibleResultPoint(new ResultPoint(decodeMiddle, i));
        }
        int[] decodeEnd = decodeEnd(bitArray, decodeMiddle);
        if (resultPointCallback != null) {
            resultPointCallback.foundPossibleResultPoint(new ResultPoint((decodeEnd[0] + decodeEnd[1]) / 2.0f, i));
        }
        int i3 = decodeEnd[1];
        int i4 = (i3 - decodeEnd[0]) + i3;
        if (i4 >= bitArray.getSize() || !bitArray.isRange(i3, i4, false)) {
            throw NotFoundException.getNotFoundInstance();
        }
        String sb2 = sb.toString();
        if (sb2.length() < 8) {
            throw FormatException.getFormatInstance();
        }
        if (!checkChecksum(sb2)) {
            throw ChecksumException.getChecksumInstance();
        }
        float f = (decodeEnd[1] + decodeEnd[0]) / 2.0f;
        BarcodeFormat barcodeFormat = getBarcodeFormat();
        float f2 = i;
        Result result = new Result(sb2, null, new ResultPoint[]{new ResultPoint((iArr[1] + iArr[0]) / 2.0f, f2), new ResultPoint(f, f2)}, barcodeFormat);
        try {
            Result decodeRow = this.extensionReader.decodeRow(i, bitArray, decodeEnd[1]);
            result.putMetadata(ResultMetadataType.UPC_EAN_EXTENSION, decodeRow.getText());
            result.putAllMetadata(decodeRow.getResultMetadata());
            result.addResultPoints(decodeRow.getResultPoints());
            i2 = decodeRow.getText().length();
        } catch (ReaderException e) {
            i2 = 0;
        }
        int[] iArr2 = map == null ? null : (int[]) map.get(DecodeHintType.ALLOWED_EAN_EXTENSIONS);
        if (iArr2 != null) {
            int length = iArr2.length;
            int i5 = 0;
            while (true) {
                int i6 = i5;
                if (i6 >= length) {
                    z = false;
                    break;
                }
                if (i2 == iArr2[i6]) {
                    z = true;
                    break;
                }
                i5 = i6 + 1;
            }
            if (!z) {
                throw NotFoundException.getNotFoundInstance();
            }
        }
        if ((barcodeFormat == BarcodeFormat.EAN_13 || barcodeFormat == BarcodeFormat.UPC_A) && (lookupCountryIdentifier = this.eanManSupport.lookupCountryIdentifier(sb2)) != null) {
            result.putMetadata(ResultMetadataType.POSSIBLE_COUNTRY, lookupCountryIdentifier);
        }
        return result;
    }

    abstract BarcodeFormat getBarcodeFormat();
}
