package com.google.zxing.pdf417.decoder;

import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.detector.MathUtils;
import com.google.zxing.pdf417.PDF417Common;
import com.google.zxing.pdf417.decoder.ec.ErrorCorrection;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Formatter;

/* loaded from: classes08-dex2jar.jar:com/google/zxing/pdf417/decoder/PDF417ScanningDecoder.class */
public final class PDF417ScanningDecoder {
    private static final int CODEWORD_SKEW_SIZE = 2;
    private static final int MAX_EC_CODEWORDS = 512;
    private static final int MAX_ERRORS = 3;
    private static final ErrorCorrection errorCorrection = new ErrorCorrection();

    private PDF417ScanningDecoder() {
    }

    private static BoundingBox adjustBoundingBox(DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn) throws NotFoundException {
        int[] rowHeights;
        int i;
        int i2;
        if (detectionResultRowIndicatorColumn == null || (rowHeights = detectionResultRowIndicatorColumn.getRowHeights()) == null) {
            return null;
        }
        int max = getMax(rowHeights);
        int length = rowHeights.length;
        int i3 = 0;
        int i4 = 0;
        while (true) {
            i = i4;
            if (i3 >= length) {
                break;
            }
            int i5 = rowHeights[i3];
            i4 += max - i5;
            i = i4;
            if (i5 > 0) {
                break;
            }
            i3++;
        }
        Codeword[] codewords = detectionResultRowIndicatorColumn.getCodewords();
        int i6 = i;
        int i7 = 0;
        while (true) {
            int i8 = i7;
            if (i6 <= 0 || codewords[i8] != null) {
                break;
            }
            i6--;
            i7 = i8 + 1;
        }
        int length2 = rowHeights.length - 1;
        int i9 = 0;
        while (true) {
            i2 = i9;
            if (length2 < 0) {
                break;
            }
            i9 += max - rowHeights[length2];
            i2 = i9;
            if (rowHeights[length2] > 0) {
                break;
            }
            length2--;
        }
        int i10 = i2;
        int length3 = codewords.length - 1;
        while (true) {
            int i11 = length3;
            if (i10 <= 0 || codewords[i11] != null) {
                break;
            }
            i10--;
            length3 = i11 - 1;
        }
        return detectionResultRowIndicatorColumn.getBoundingBox().addMissingRows(i6, i10, detectionResultRowIndicatorColumn.isLeft());
    }

    private static void adjustCodewordCount(DetectionResult detectionResult, BarcodeValue[][] barcodeValueArr) throws NotFoundException {
        int[] value = barcodeValueArr[0][1].getValue();
        int barcodeColumnCount = (detectionResult.getBarcodeColumnCount() * detectionResult.getBarcodeRowCount()) - getNumberOfECCodeWords(detectionResult.getBarcodeECLevel());
        if (value.length != 0) {
            if (value[0] != barcodeColumnCount) {
                barcodeValueArr[0][1].setValue(barcodeColumnCount);
            }
        } else {
            if (barcodeColumnCount <= 0 || barcodeColumnCount > 928) {
                throw NotFoundException.getNotFoundInstance();
            }
            barcodeValueArr[0][1].setValue(barcodeColumnCount);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0056 A[EDGE_INSN: B:20:0x0056->B:21:0x0056 BREAK  A[LOOP:1: B:7:0x001e->B:15:0x004c], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static int adjustCodewordStartColumn(com.google.zxing.common.BitMatrix r5, int r6, int r7, boolean r8, int r9, int r10) {
        /*
            r0 = r8
            if (r0 == 0) goto La
            r0 = -1
            r11 = r0
            goto Ld
        La:
            r0 = 1
            r11 = r0
        Ld:
            r0 = r11
            r12 = r0
            r0 = 0
            r11 = r0
            r0 = r9
            r13 = r0
        L18:
            r0 = r11
            r1 = 2
            if (r0 >= r1) goto L6f
        L1e:
            r0 = r8
            if (r0 == 0) goto L2b
            r0 = r13
            r1 = r6
            if (r0 < r1) goto L56
            goto L31
        L2b:
            r0 = r13
            r1 = r7
            if (r0 >= r1) goto L56
        L31:
            r0 = r8
            r1 = r5
            r2 = r13
            r3 = r10
            boolean r1 = r1.get(r2, r3)
            if (r0 != r1) goto L56
            r0 = r9
            r1 = r13
            int r0 = r0 - r1
            int r0 = java.lang.Math.abs(r0)
            r1 = 2
            if (r0 <= r1) goto L4c
            r0 = r9
            return r0
        L4c:
            r0 = r13
            r1 = r12
            int r0 = r0 + r1
            r13 = r0
            goto L1e
        L56:
            r0 = r12
            int r0 = -r0
            r12 = r0
            r0 = r8
            if (r0 != 0) goto L64
            r0 = 1
            r8 = r0
            goto L66
        L64:
            r0 = 0
            r8 = r0
        L66:
            r0 = r11
            r1 = 1
            int r0 = r0 + r1
            r11 = r0
            goto L18
        L6f:
            r0 = r13
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.decoder.PDF417ScanningDecoder.adjustCodewordStartColumn(com.google.zxing.common.BitMatrix, int, int, boolean, int, int):int");
    }

    private static boolean checkCodewordSkew(int i, int i2, int i3) {
        return i2 - 2 <= i && i <= i3 + 2;
    }

    private static int correctErrors(int[] iArr, int[] iArr2, int i) throws ChecksumException {
        if ((iArr2 == null || iArr2.length <= (i / 2) + 3) && i >= 0 && i <= 512) {
            return errorCorrection.decode(iArr, i, iArr2);
        }
        throw ChecksumException.getChecksumInstance();
    }

    private static BarcodeValue[][] createBarcodeMatrix(DetectionResult detectionResult) {
        int rowNumber;
        BarcodeValue[][] barcodeValueArr = (BarcodeValue[][]) Array.newInstance((Class<?>) BarcodeValue.class, detectionResult.getBarcodeRowCount(), detectionResult.getBarcodeColumnCount() + 2);
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= barcodeValueArr.length) {
                break;
            }
            int i3 = 0;
            while (true) {
                int i4 = i3;
                if (i4 < barcodeValueArr[i2].length) {
                    barcodeValueArr[i2][i4] = new BarcodeValue();
                    i3 = i4 + 1;
                }
            }
            i = i2 + 1;
        }
        int i5 = 0;
        for (DetectionResultColumn detectionResultColumn : detectionResult.getDetectionResultColumns()) {
            if (detectionResultColumn != null) {
                Codeword[] codewords = detectionResultColumn.getCodewords();
                int length = codewords.length;
                int i6 = 0;
                while (true) {
                    int i7 = i6;
                    if (i7 < length) {
                        Codeword codeword = codewords[i7];
                        if (codeword != null && (rowNumber = codeword.getRowNumber()) >= 0 && rowNumber < barcodeValueArr.length) {
                            barcodeValueArr[rowNumber][i5].setValue(codeword.getValue());
                        }
                        i6 = i7 + 1;
                    }
                }
            }
            i5++;
        }
        return barcodeValueArr;
    }

    /* JADX WARN: Type inference failed for: r0v16, types: [int[], int[][]] */
    private static DecoderResult createDecoderResult(DetectionResult detectionResult) throws FormatException, ChecksumException, NotFoundException {
        BarcodeValue[][] createBarcodeMatrix = createBarcodeMatrix(detectionResult);
        adjustCodewordCount(detectionResult, createBarcodeMatrix);
        ArrayList arrayList = new ArrayList();
        int[] iArr = new int[detectionResult.getBarcodeRowCount() * detectionResult.getBarcodeColumnCount()];
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= detectionResult.getBarcodeRowCount()) {
                break;
            }
            int i3 = 0;
            while (true) {
                int i4 = i3;
                if (i4 < detectionResult.getBarcodeColumnCount()) {
                    BarcodeValue[] barcodeValueArr = createBarcodeMatrix[i2];
                    int i5 = i4 + 1;
                    int[] value = barcodeValueArr[i5].getValue();
                    int barcodeColumnCount = (detectionResult.getBarcodeColumnCount() * i2) + i4;
                    if (value.length == 0) {
                        arrayList.add(Integer.valueOf(barcodeColumnCount));
                    } else if (value.length == 1) {
                        iArr[barcodeColumnCount] = value[0];
                    } else {
                        arrayList3.add(Integer.valueOf(barcodeColumnCount));
                        arrayList2.add(value);
                    }
                    i3 = i5;
                }
            }
            i = i2 + 1;
        }
        ?? r0 = new int[arrayList2.size()];
        int i6 = 0;
        while (true) {
            int i7 = i6;
            if (i7 >= r0.length) {
                return createDecoderResultFromAmbiguousValues(detectionResult.getBarcodeECLevel(), iArr, PDF417Common.toIntArray(arrayList), PDF417Common.toIntArray(arrayList3), r0);
            }
            r0[i7] = (int[]) arrayList2.get(i7);
            i6 = i7 + 1;
        }
    }

    private static DecoderResult createDecoderResultFromAmbiguousValues(int i, int[] iArr, int[] iArr2, int[] iArr3, int[][] iArr4) throws FormatException, ChecksumException {
        int[] iArr5 = new int[iArr3.length];
        int i2 = 100;
        while (true) {
            int i3 = i2;
            if (i3 <= 0) {
                throw ChecksumException.getChecksumInstance();
            }
            int i4 = 0;
            while (true) {
                int i5 = i4;
                if (i5 < iArr5.length) {
                    iArr[iArr3[i5]] = iArr4[i5][iArr5[i5]];
                    i4 = i5 + 1;
                } else {
                    try {
                        return decodeCodewords(iArr, i, iArr2);
                    } catch (ChecksumException e) {
                        if (iArr5.length == 0) {
                            throw ChecksumException.getChecksumInstance();
                        }
                        int i6 = 0;
                        while (true) {
                            int i7 = i6;
                            if (i7 >= iArr5.length) {
                                break;
                            }
                            if (iArr5[i7] < iArr4[i7].length - 1) {
                                iArr5[i7] = iArr5[i7] + 1;
                                break;
                            }
                            iArr5[i7] = 0;
                            if (i7 == iArr5.length - 1) {
                                throw ChecksumException.getChecksumInstance();
                            }
                            i6 = i7 + 1;
                        }
                        i2 = i3 - 1;
                    }
                }
            }
        }
    }

    public static DecoderResult decode(BitMatrix bitMatrix, ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3, ResultPoint resultPoint4, int i, int i2) throws NotFoundException, FormatException, ChecksumException {
        BoundingBox boundingBox = new BoundingBox(bitMatrix, resultPoint, resultPoint2, resultPoint3, resultPoint4);
        DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn = null;
        DetectionResult detectionResult = null;
        DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn2 = null;
        int i3 = 0;
        while (true) {
            int i4 = i3;
            if (i4 >= 2) {
                break;
            }
            if (resultPoint != null) {
                detectionResultRowIndicatorColumn = getRowIndicatorColumn(bitMatrix, boundingBox, resultPoint, true, i, i2);
            }
            if (resultPoint3 != null) {
                detectionResultRowIndicatorColumn2 = getRowIndicatorColumn(bitMatrix, boundingBox, resultPoint3, false, i, i2);
            }
            detectionResult = merge(detectionResultRowIndicatorColumn, detectionResultRowIndicatorColumn2);
            if (detectionResult == null) {
                throw NotFoundException.getNotFoundInstance();
            }
            if (i4 != 0 || detectionResult.getBoundingBox() == null || (detectionResult.getBoundingBox().getMinY() >= boundingBox.getMinY() && detectionResult.getBoundingBox().getMaxY() <= boundingBox.getMaxY())) {
                break;
            }
            boundingBox = detectionResult.getBoundingBox();
            i3 = i4 + 1;
        }
        int barcodeColumnCount = detectionResult.getBarcodeColumnCount() + 1;
        detectionResult.setDetectionResultColumn(0, detectionResultRowIndicatorColumn);
        detectionResult.setDetectionResultColumn(barcodeColumnCount, detectionResultRowIndicatorColumn2);
        boolean z = detectionResultRowIndicatorColumn != null;
        int i5 = 1;
        while (i5 <= barcodeColumnCount) {
            int i6 = z ? i5 : barcodeColumnCount - i5;
            int i7 = i;
            int i8 = i2;
            if (detectionResult.getDetectionResultColumn(i6) == null) {
                DetectionResultColumn detectionResultRowIndicatorColumn3 = (i6 == 0 || i6 == barcodeColumnCount) ? new DetectionResultRowIndicatorColumn(boundingBox, i6 == 0) : new DetectionResultColumn(boundingBox);
                detectionResult.setDetectionResultColumn(i6, detectionResultRowIndicatorColumn3);
                int i9 = i;
                int i10 = i2;
                int i11 = -1;
                for (int minY = boundingBox.getMinY(); minY <= boundingBox.getMaxY(); minY++) {
                    int startColumn = getStartColumn(detectionResult, i6, minY, z);
                    if (startColumn < 0 || startColumn > boundingBox.getMaxX()) {
                        if (i11 != -1) {
                            startColumn = i11;
                        }
                    }
                    Codeword detectCodeword = detectCodeword(bitMatrix, boundingBox.getMinX(), boundingBox.getMaxX(), z, startColumn, minY, i9, i10);
                    if (detectCodeword != null) {
                        detectionResultRowIndicatorColumn3.setCodeword(minY, detectCodeword);
                        i9 = Math.min(i9, detectCodeword.getWidth());
                        i10 = Math.max(i10, detectCodeword.getWidth());
                        i11 = startColumn;
                    }
                }
                i7 = i9;
                i8 = i10;
            }
            i5++;
            i = i7;
            i2 = i8;
        }
        return createDecoderResult(detectionResult);
    }

    private static DecoderResult decodeCodewords(int[] iArr, int i, int[] iArr2) throws FormatException, ChecksumException {
        if (iArr.length == 0) {
            throw FormatException.getFormatInstance();
        }
        int i2 = 1 << (i + 1);
        int correctErrors = correctErrors(iArr, iArr2, i2);
        verifyCodewordCount(iArr, i2);
        DecoderResult decode = DecodedBitStreamParser.decode(iArr, String.valueOf(i));
        decode.setErrorsCorrected(Integer.valueOf(correctErrors));
        decode.setErasures(Integer.valueOf(iArr2.length));
        return decode;
    }

    private static Codeword detectCodeword(BitMatrix bitMatrix, int i, int i2, boolean z, int i3, int i4, int i5, int i6) {
        int i7;
        int decodedValue;
        int codeword;
        int adjustCodewordStartColumn = adjustCodewordStartColumn(bitMatrix, i, i2, z, i3, i4);
        int[] moduleBitCount = getModuleBitCount(bitMatrix, i, i2, z, adjustCodewordStartColumn, i4);
        if (moduleBitCount == null) {
            return null;
        }
        int sum = MathUtils.sum(moduleBitCount);
        if (z) {
            i7 = adjustCodewordStartColumn;
            adjustCodewordStartColumn += sum;
        } else {
            int i8 = 0;
            while (true) {
                int i9 = i8;
                if (i9 >= moduleBitCount.length / 2) {
                    break;
                }
                int i10 = moduleBitCount[i9];
                moduleBitCount[i9] = moduleBitCount[(moduleBitCount.length - 1) - i9];
                moduleBitCount[(moduleBitCount.length - 1) - i9] = i10;
                i8 = i9 + 1;
            }
            i7 = adjustCodewordStartColumn - sum;
        }
        if (checkCodewordSkew(sum, i5, i6) && (codeword = PDF417Common.getCodeword((decodedValue = PDF417CodewordDecoder.getDecodedValue(moduleBitCount)))) != -1) {
            return new Codeword(i7, adjustCodewordStartColumn, getCodewordBucketNumber(decodedValue), codeword);
        }
        return null;
    }

    private static BarcodeMetadata getBarcodeMetadata(DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn, DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn2) {
        BarcodeMetadata barcodeMetadata;
        if (detectionResultRowIndicatorColumn == null || (barcodeMetadata = detectionResultRowIndicatorColumn.getBarcodeMetadata()) == null) {
            if (detectionResultRowIndicatorColumn2 == null) {
                return null;
            }
            return detectionResultRowIndicatorColumn2.getBarcodeMetadata();
        }
        if (detectionResultRowIndicatorColumn2 != null) {
            BarcodeMetadata barcodeMetadata2 = detectionResultRowIndicatorColumn2.getBarcodeMetadata();
            if (barcodeMetadata2 == null) {
                return barcodeMetadata;
            }
            if (barcodeMetadata.getColumnCount() != barcodeMetadata2.getColumnCount() && barcodeMetadata.getErrorCorrectionLevel() != barcodeMetadata2.getErrorCorrectionLevel() && barcodeMetadata.getRowCount() != barcodeMetadata2.getRowCount()) {
                return null;
            }
        }
        return barcodeMetadata;
    }

    private static int[] getBitCountForCodeword(int i) {
        int[] iArr = new int[8];
        int i2 = 0;
        int i3 = 7;
        while (true) {
            int i4 = i3;
            int i5 = i & 1;
            int i6 = i2;
            int i7 = i4;
            if (i5 != i2) {
                i7 = i4 - 1;
                if (i7 < 0) {
                    return iArr;
                }
                i6 = i5;
            }
            iArr[i7] = iArr[i7] + 1;
            i >>= 1;
            i2 = i6;
            i3 = i7;
        }
    }

    private static int getCodewordBucketNumber(int i) {
        return getCodewordBucketNumber(getBitCountForCodeword(i));
    }

    private static int getCodewordBucketNumber(int[] iArr) {
        return ((((iArr[0] - iArr[2]) + iArr[4]) - iArr[6]) + 9) % 9;
    }

    private static int getMax(int[] iArr) {
        int length = iArr.length;
        int i = -1;
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 >= length) {
                return i;
            }
            i = Math.max(i, iArr[i3]);
            i2 = i3 + 1;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x006d A[EDGE_INSN: B:26:0x006d->B:27:0x006d BREAK  A[LOOP:0: B:5:0x0019->B:19:0x0019], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static int[] getModuleBitCount(com.google.zxing.common.BitMatrix r5, int r6, int r7, boolean r8, int r9, int r10) {
        /*
            r0 = 8
            int[] r0 = new int[r0]
            r14 = r0
            r0 = r8
            if (r0 == 0) goto L10
            r0 = 1
            r11 = r0
            goto L13
        L10:
            r0 = -1
            r11 = r0
        L13:
            r0 = r8
            r13 = r0
            r0 = 0
            r12 = r0
        L19:
            r0 = r8
            if (r0 == 0) goto L26
            r0 = r9
            r1 = r7
            if (r0 >= r1) goto L6d
            goto L2c
        L26:
            r0 = r9
            r1 = r6
            if (r0 < r1) goto L6d
        L2c:
            r0 = r12
            r1 = 8
            if (r0 >= r1) goto L6d
            r0 = r5
            r1 = r9
            r2 = r10
            boolean r0 = r0.get(r1, r2)
            r1 = r13
            if (r0 != r1) goto L56
            r0 = r14
            r1 = r12
            r2 = r14
            r3 = r12
            r2 = r2[r3]
            r3 = 1
            int r2 = r2 + r3
            r0[r1] = r2
            r0 = r9
            r1 = r11
            int r0 = r0 + r1
            r9 = r0
            goto L19
        L56:
            r0 = r12
            r1 = 1
            int r0 = r0 + r1
            r12 = r0
            r0 = r13
            if (r0 != 0) goto L67
            r0 = 1
            r13 = r0
            goto L19
        L67:
            r0 = 0
            r13 = r0
            goto L19
        L6d:
            r0 = r12
            r1 = 8
            if (r0 == r1) goto L8c
            r0 = r8
            if (r0 == 0) goto L7a
            r0 = r7
            r6 = r0
        L7a:
            r0 = r9
            r1 = r6
            if (r0 != r1) goto L8a
            r0 = r12
            r1 = 7
            if (r0 != r1) goto L8a
            r0 = r14
            return r0
        L8a:
            r0 = 0
            return r0
        L8c:
            r0 = r14
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.decoder.PDF417ScanningDecoder.getModuleBitCount(com.google.zxing.common.BitMatrix, int, int, boolean, int, int):int[]");
    }

    private static int getNumberOfECCodeWords(int i) {
        return 2 << i;
    }

    private static DetectionResultRowIndicatorColumn getRowIndicatorColumn(BitMatrix bitMatrix, BoundingBox boundingBox, ResultPoint resultPoint, boolean z, int i, int i2) {
        DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn = new DetectionResultRowIndicatorColumn(boundingBox, z);
        int i3 = 0;
        while (true) {
            int i4 = i3;
            if (i4 >= 2) {
                return detectionResultRowIndicatorColumn;
            }
            int i5 = i4 == 0 ? 1 : -1;
            int x = (int) resultPoint.getX();
            int y = (int) resultPoint.getY();
            while (true) {
                int i6 = y;
                if (i6 <= boundingBox.getMaxY() && i6 >= boundingBox.getMinY()) {
                    Codeword detectCodeword = detectCodeword(bitMatrix, 0, bitMatrix.getWidth(), z, x, i6, i, i2);
                    if (detectCodeword != null) {
                        detectionResultRowIndicatorColumn.setCodeword(i6, detectCodeword);
                        x = z ? detectCodeword.getStartX() : detectCodeword.getEndX();
                    }
                    y = i6 + i5;
                }
            }
            i3 = i4 + 1;
        }
    }

    private static int getStartColumn(DetectionResult detectionResult, int i, int i2, boolean z) {
        int i3 = z ? 1 : -1;
        Codeword codeword = null;
        int i4 = i - i3;
        if (isValidBarcodeColumn(detectionResult, i4)) {
            codeword = detectionResult.getDetectionResultColumn(i4).getCodeword(i2);
        }
        if (codeword != null) {
            return z ? codeword.getEndX() : codeword.getStartX();
        }
        Codeword codewordNearby = detectionResult.getDetectionResultColumn(i).getCodewordNearby(i2);
        if (codewordNearby != null) {
            return z ? codewordNearby.getStartX() : codewordNearby.getEndX();
        }
        if (isValidBarcodeColumn(detectionResult, i4)) {
            codewordNearby = detectionResult.getDetectionResultColumn(i4).getCodewordNearby(i2);
        }
        if (codewordNearby != null) {
            return z ? codewordNearby.getEndX() : codewordNearby.getStartX();
        }
        int i5 = i;
        int i6 = 0;
        while (true) {
            int i7 = i5 - i3;
            if (!isValidBarcodeColumn(detectionResult, i7)) {
                return z ? detectionResult.getBoundingBox().getMinX() : detectionResult.getBoundingBox().getMaxX();
            }
            Codeword[] codewords = detectionResult.getDetectionResultColumn(i7).getCodewords();
            int length = codewords.length;
            int i8 = 0;
            while (true) {
                int i9 = i8;
                if (i9 < length) {
                    Codeword codeword2 = codewords[i9];
                    if (codeword2 != null) {
                        return (z ? codeword2.getEndX() : codeword2.getStartX()) + (i3 * i6 * (codeword2.getEndX() - codeword2.getStartX()));
                    }
                    i8 = i9 + 1;
                }
            }
            i6++;
            i5 = i7;
        }
    }

    private static boolean isValidBarcodeColumn(DetectionResult detectionResult, int i) {
        return i >= 0 && i <= detectionResult.getBarcodeColumnCount() + 1;
    }

    private static DetectionResult merge(DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn, DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn2) throws NotFoundException {
        BarcodeMetadata barcodeMetadata;
        if ((detectionResultRowIndicatorColumn == null && detectionResultRowIndicatorColumn2 == null) || (barcodeMetadata = getBarcodeMetadata(detectionResultRowIndicatorColumn, detectionResultRowIndicatorColumn2)) == null) {
            return null;
        }
        return new DetectionResult(barcodeMetadata, BoundingBox.merge(adjustBoundingBox(detectionResultRowIndicatorColumn), adjustBoundingBox(detectionResultRowIndicatorColumn2)));
    }

    public static String toString(BarcodeValue[][] barcodeValueArr) {
        Formatter formatter = new Formatter();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= barcodeValueArr.length) {
                String formatter2 = formatter.toString();
                formatter.close();
                return formatter2;
            }
            formatter.format("Row %2d: ", Integer.valueOf(i2));
            int i3 = 0;
            while (true) {
                int i4 = i3;
                if (i4 < barcodeValueArr[i2].length) {
                    BarcodeValue barcodeValue = barcodeValueArr[i2][i4];
                    if (barcodeValue.getValue().length == 0) {
                        formatter.format("        ", null);
                    } else {
                        formatter.format("%4d(%2d)", Integer.valueOf(barcodeValue.getValue()[0]), barcodeValue.getConfidence(barcodeValue.getValue()[0]));
                    }
                    i3 = i4 + 1;
                }
            }
            formatter.format("%n", new Object[0]);
            i = i2 + 1;
        }
    }

    private static void verifyCodewordCount(int[] iArr, int i) throws FormatException {
        if (iArr.length < 4) {
            throw FormatException.getFormatInstance();
        }
        int i2 = iArr[0];
        if (i2 > iArr.length) {
            throw FormatException.getFormatInstance();
        }
        if (i2 == 0) {
            if (i >= iArr.length) {
                throw FormatException.getFormatInstance();
            }
            iArr[0] = iArr.length - i;
        }
    }
}
