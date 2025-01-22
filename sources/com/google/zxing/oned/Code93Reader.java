package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import com.squareup.okhttp.internal.http.StatusLine;
import java.util.Arrays;
import java.util.Map;

/* loaded from: classes08-dex2jar.jar:com/google/zxing/oned/Code93Reader.class */
public final class Code93Reader extends OneDReader {
    private static final int ASTERISK_ENCODING;
    static final int[] CHARACTER_ENCODINGS;
    static final String ALPHABET_STRING = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*";
    private static final char[] ALPHABET = ALPHABET_STRING.toCharArray();
    private final StringBuilder decodeRowResult = new StringBuilder(20);
    private final int[] counters = new int[6];

    static {
        int[] iArr = {276, 328, 324, 322, 296, 292, 290, 336, 274, 266, 424, 420, 418, 404, 402, 394, 360, 356, 354, StatusLine.HTTP_PERM_REDIRECT, 282, 344, 332, 326, 300, 278, 436, 434, 428, 422, 406, 410, 364, 358, 310, 314, 302, 468, 466, 458, 366, 374, 430, 294, 474, 470, 306, 350};
        CHARACTER_ENCODINGS = iArr;
        ASTERISK_ENCODING = iArr[47];
    }

    private static void checkChecksums(CharSequence charSequence) throws ChecksumException {
        int length = charSequence.length();
        checkOneChecksum(charSequence, length - 2, 20);
        checkOneChecksum(charSequence, length - 1, 15);
    }

    private static void checkOneChecksum(CharSequence charSequence, int i, int i2) throws ChecksumException {
        int i3 = 0;
        int i4 = 1;
        for (int i5 = i - 1; i5 >= 0; i5--) {
            i3 += ALPHABET_STRING.indexOf(charSequence.charAt(i5)) * i4;
            int i6 = i4 + 1;
            i4 = i6;
            if (i6 > i2) {
                i4 = 1;
            }
        }
        if (charSequence.charAt(i) != ALPHABET[i3 % 47]) {
            throw ChecksumException.getChecksumInstance();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0080, code lost:            throw com.google.zxing.FormatException.getFormatInstance();     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x0125, code lost:            throw com.google.zxing.FormatException.getFormatInstance();     */
    /* JADX WARN: Failed to find 'out' block for switch in B:11:0x0044. Please report as an issue. */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String decodeExtended(java.lang.CharSequence r4) throws com.google.zxing.FormatException {
        /*
            Method dump skipped, instructions count: 330
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.Code93Reader.decodeExtended(java.lang.CharSequence):java.lang.String");
    }

    private int[] findAsteriskPattern(BitArray bitArray) throws NotFoundException {
        int i;
        int i2;
        int i3;
        int size = bitArray.getSize();
        int nextSet = bitArray.getNextSet(0);
        Arrays.fill(this.counters, 0);
        int[] iArr = this.counters;
        int length = iArr.length;
        int i4 = nextSet;
        boolean z = false;
        int i5 = 0;
        while (nextSet < size) {
            if (bitArray.get(nextSet) ^ z) {
                iArr[i5] = iArr[i5] + 1;
                i3 = i4;
            } else {
                int i6 = length - 1;
                if (i5 != i6) {
                    int i7 = i5 + 1;
                    i = i4;
                    i2 = i7;
                } else {
                    if (toPattern(iArr) == ASTERISK_ENCODING) {
                        return new int[]{i4, nextSet};
                    }
                    int i8 = i4 + iArr[0] + iArr[1];
                    int i9 = length - 2;
                    System.arraycopy(iArr, 2, iArr, 0, i9);
                    iArr[i9] = 0;
                    iArr[i6] = 0;
                    i2 = i5 - 1;
                    i = i8;
                }
                iArr[i2] = 1;
                z = !z;
                i3 = i;
                i5 = i2;
            }
            nextSet++;
            i4 = i3;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static char patternToChar(int i) throws NotFoundException {
        int i2 = 0;
        while (true) {
            int i3 = i2;
            int[] iArr = CHARACTER_ENCODINGS;
            if (i3 >= iArr.length) {
                throw NotFoundException.getNotFoundInstance();
            }
            if (iArr[i3] == i) {
                return ALPHABET[i3];
            }
            i2 = i3 + 1;
        }
    }

    private static int toPattern(int[] iArr) {
        int i = 0;
        for (int i2 : iArr) {
            i += i2;
        }
        int length = iArr.length;
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4++) {
            int round = Math.round((iArr[i4] * 9.0f) / i);
            if (round <= 0 || round > 4) {
                return -1;
            }
            if ((i4 & 1) == 0) {
                int i5 = 0;
                while (true) {
                    int i6 = i5;
                    if (i6 < round) {
                        i3 = (i3 << 1) | 1;
                        i5 = i6 + 1;
                    }
                }
            } else {
                i3 <<= round;
            }
        }
        return i3;
    }

    @Override // com.google.zxing.oned.OneDReader
    public Result decodeRow(int i, BitArray bitArray, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException {
        int nextSet = bitArray.getNextSet(findAsteriskPattern(bitArray)[1]);
        int size = bitArray.getSize();
        int[] iArr = this.counters;
        Arrays.fill(iArr, 0);
        StringBuilder sb = this.decodeRowResult;
        sb.setLength(0);
        while (true) {
            recordPattern(bitArray, nextSet, iArr);
            int pattern = toPattern(iArr);
            if (pattern < 0) {
                throw NotFoundException.getNotFoundInstance();
            }
            char patternToChar = patternToChar(pattern);
            sb.append(patternToChar);
            int length = iArr.length;
            int i2 = nextSet;
            int i3 = 0;
            while (true) {
                int i4 = i3;
                if (i4 >= length) {
                    break;
                }
                i2 += iArr[i4];
                i3 = i4 + 1;
            }
            int nextSet2 = bitArray.getNextSet(i2);
            if (patternToChar == '*') {
                sb.deleteCharAt(sb.length() - 1);
                int i5 = 0;
                for (int i6 : iArr) {
                    i5 += i6;
                }
                if (nextSet2 == size || !bitArray.get(nextSet2)) {
                    throw NotFoundException.getNotFoundInstance();
                }
                if (sb.length() < 2) {
                    throw NotFoundException.getNotFoundInstance();
                }
                checkChecksums(sb);
                sb.setLength(sb.length() - 2);
                String decodeExtended = decodeExtended(sb);
                float f = (r0[1] + r0[0]) / 2.0f;
                float f2 = i;
                return new Result(decodeExtended, null, new ResultPoint[]{new ResultPoint(f, f2), new ResultPoint(nextSet + (i5 / 2.0f), f2)}, BarcodeFormat.CODE_93);
            }
            nextSet = nextSet2;
        }
    }
}
