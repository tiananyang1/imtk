package com.google.zxing.pdf417.decoder;

import com.google.zxing.pdf417.PDF417Common;
import java.util.Formatter;

/* loaded from: classes08-dex2jar.jar:com/google/zxing/pdf417/decoder/DetectionResult.class */
final class DetectionResult {
    private static final int ADJUST_ROW_NUMBER_SKIP = 2;
    private final int barcodeColumnCount;
    private final BarcodeMetadata barcodeMetadata;
    private BoundingBox boundingBox;
    private final DetectionResultColumn[] detectionResultColumns;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DetectionResult(BarcodeMetadata barcodeMetadata, BoundingBox boundingBox) {
        this.barcodeMetadata = barcodeMetadata;
        this.barcodeColumnCount = barcodeMetadata.getColumnCount();
        this.boundingBox = boundingBox;
        this.detectionResultColumns = new DetectionResultColumn[this.barcodeColumnCount + 2];
    }

    private void adjustIndicatorColumnRowNumbers(DetectionResultColumn detectionResultColumn) {
        if (detectionResultColumn != null) {
            ((DetectionResultRowIndicatorColumn) detectionResultColumn).adjustCompleteIndicatorColumnRowNumbers(this.barcodeMetadata);
        }
    }

    private static boolean adjustRowNumber(Codeword codeword, Codeword codeword2) {
        if (codeword2 == null || !codeword2.hasValidRowNumber() || codeword2.getBucket() != codeword.getBucket()) {
            return false;
        }
        codeword.setRowNumber(codeword2.getRowNumber());
        return true;
    }

    private static int adjustRowNumberIfValid(int i, int i2, Codeword codeword) {
        if (codeword == null) {
            return i2;
        }
        int i3 = i2;
        if (!codeword.hasValidRowNumber()) {
            if (codeword.isValidRowNumber(i)) {
                codeword.setRowNumber(i);
                return 0;
            }
            i3 = i2 + 1;
        }
        return i3;
    }

    private int adjustRowNumbers() {
        int adjustRowNumbersByRow = adjustRowNumbersByRow();
        if (adjustRowNumbersByRow == 0) {
            return 0;
        }
        int i = 1;
        while (true) {
            int i2 = i;
            if (i2 >= this.barcodeColumnCount + 1) {
                return adjustRowNumbersByRow;
            }
            Codeword[] codewords = this.detectionResultColumns[i2].getCodewords();
            int i3 = 0;
            while (true) {
                int i4 = i3;
                if (i4 < codewords.length) {
                    if (codewords[i4] != null && !codewords[i4].hasValidRowNumber()) {
                        adjustRowNumbers(i2, i4, codewords);
                    }
                    i3 = i4 + 1;
                }
            }
            i = i2 + 1;
        }
    }

    private void adjustRowNumbers(int i, int i2, Codeword[] codewordArr) {
        Codeword codeword = codewordArr[i2];
        Codeword[] codewords = this.detectionResultColumns[i - 1].getCodewords();
        DetectionResultColumn[] detectionResultColumnArr = this.detectionResultColumns;
        int i3 = i + 1;
        Codeword[] codewords2 = detectionResultColumnArr[i3] != null ? detectionResultColumnArr[i3].getCodewords() : codewords;
        Codeword[] codewordArr2 = new Codeword[14];
        codewordArr2[2] = codewords[i2];
        codewordArr2[3] = codewords2[i2];
        if (i2 > 0) {
            int i4 = i2 - 1;
            codewordArr2[0] = codewordArr[i4];
            codewordArr2[4] = codewords[i4];
            codewordArr2[5] = codewords2[i4];
        }
        if (i2 > 1) {
            int i5 = i2 - 2;
            codewordArr2[8] = codewordArr[i5];
            codewordArr2[10] = codewords[i5];
            codewordArr2[11] = codewords2[i5];
        }
        if (i2 < codewordArr.length - 1) {
            int i6 = i2 + 1;
            codewordArr2[1] = codewordArr[i6];
            codewordArr2[6] = codewords[i6];
            codewordArr2[7] = codewords2[i6];
        }
        int i7 = 0;
        if (i2 < codewordArr.length - 2) {
            int i8 = i2 + 2;
            codewordArr2[9] = codewordArr[i8];
            codewordArr2[12] = codewords[i8];
            codewordArr2[13] = codewords2[i8];
            i7 = 0;
        }
        while (i7 < 14 && !adjustRowNumber(codeword, codewordArr2[i7])) {
            i7++;
        }
    }

    private int adjustRowNumbersByRow() {
        adjustRowNumbersFromBothRI();
        return adjustRowNumbersFromLRI() + adjustRowNumbersFromRRI();
    }

    private void adjustRowNumbersFromBothRI() {
        DetectionResultColumn[] detectionResultColumnArr = this.detectionResultColumns;
        if (detectionResultColumnArr[0] == null || detectionResultColumnArr[this.barcodeColumnCount + 1] == null) {
            return;
        }
        Codeword[] codewords = detectionResultColumnArr[0].getCodewords();
        Codeword[] codewords2 = this.detectionResultColumns[this.barcodeColumnCount + 1].getCodewords();
        for (int i = 0; i < codewords.length; i++) {
            if (codewords[i] != null && codewords2[i] != null && codewords[i].getRowNumber() == codewords2[i].getRowNumber()) {
                int i2 = 1;
                while (true) {
                    int i3 = i2;
                    if (i3 <= this.barcodeColumnCount) {
                        Codeword codeword = this.detectionResultColumns[i3].getCodewords()[i];
                        if (codeword != null) {
                            codeword.setRowNumber(codewords[i].getRowNumber());
                            if (!codeword.hasValidRowNumber()) {
                                this.detectionResultColumns[i3].getCodewords()[i] = null;
                            }
                        }
                        i2 = i3 + 1;
                    }
                }
            }
        }
    }

    private int adjustRowNumbersFromLRI() {
        DetectionResultColumn[] detectionResultColumnArr = this.detectionResultColumns;
        if (detectionResultColumnArr[0] == null) {
            return 0;
        }
        Codeword[] codewords = detectionResultColumnArr[0].getCodewords();
        int i = 0;
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i >= codewords.length) {
                return i3;
            }
            int i4 = i3;
            if (codewords[i] != null) {
                int rowNumber = codewords[i].getRowNumber();
                int i5 = 1;
                int i6 = 0;
                while (i5 < this.barcodeColumnCount + 1 && i6 < 2) {
                    Codeword codeword = this.detectionResultColumns[i5].getCodewords()[i];
                    int i7 = i6;
                    int i8 = i3;
                    if (codeword != null) {
                        int adjustRowNumberIfValid = adjustRowNumberIfValid(rowNumber, i6, codeword);
                        i7 = adjustRowNumberIfValid;
                        i8 = i3;
                        if (!codeword.hasValidRowNumber()) {
                            i8 = i3 + 1;
                            i7 = adjustRowNumberIfValid;
                        }
                    }
                    i5++;
                    i6 = i7;
                    i3 = i8;
                }
                i4 = i3;
            }
            i++;
            i2 = i4;
        }
    }

    private int adjustRowNumbersFromRRI() {
        DetectionResultColumn[] detectionResultColumnArr = this.detectionResultColumns;
        int i = this.barcodeColumnCount;
        if (detectionResultColumnArr[i + 1] == null) {
            return 0;
        }
        Codeword[] codewords = detectionResultColumnArr[i + 1].getCodewords();
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int i4 = i3;
            if (i2 >= codewords.length) {
                return i4;
            }
            int i5 = i4;
            if (codewords[i2] != null) {
                int rowNumber = codewords[i2].getRowNumber();
                int i6 = this.barcodeColumnCount + 1;
                int i7 = 0;
                while (i6 > 0 && i7 < 2) {
                    Codeword codeword = this.detectionResultColumns[i6].getCodewords()[i2];
                    int i8 = i7;
                    int i9 = i4;
                    if (codeword != null) {
                        int adjustRowNumberIfValid = adjustRowNumberIfValid(rowNumber, i7, codeword);
                        i8 = adjustRowNumberIfValid;
                        i9 = i4;
                        if (!codeword.hasValidRowNumber()) {
                            i9 = i4 + 1;
                            i8 = adjustRowNumberIfValid;
                        }
                    }
                    i6--;
                    i7 = i8;
                    i4 = i9;
                }
                i5 = i4;
            }
            i2++;
            i3 = i5;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getBarcodeColumnCount() {
        return this.barcodeColumnCount;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getBarcodeECLevel() {
        return this.barcodeMetadata.getErrorCorrectionLevel();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getBarcodeRowCount() {
        return this.barcodeMetadata.getRowCount();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BoundingBox getBoundingBox() {
        return this.boundingBox;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DetectionResultColumn getDetectionResultColumn(int i) {
        return this.detectionResultColumns[i];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DetectionResultColumn[] getDetectionResultColumns() {
        adjustIndicatorColumnRowNumbers(this.detectionResultColumns[0]);
        adjustIndicatorColumnRowNumbers(this.detectionResultColumns[this.barcodeColumnCount + 1]);
        int i = PDF417Common.MAX_CODEWORDS_IN_BARCODE;
        while (true) {
            int i2 = i;
            int adjustRowNumbers = adjustRowNumbers();
            if (adjustRowNumbers <= 0 || adjustRowNumbers >= i2) {
                break;
            }
            i = adjustRowNumbers;
        }
        return this.detectionResultColumns;
    }

    public void setBoundingBox(BoundingBox boundingBox) {
        this.boundingBox = boundingBox;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setDetectionResultColumn(int i, DetectionResultColumn detectionResultColumn) {
        this.detectionResultColumns[i] = detectionResultColumn;
    }

    public String toString() {
        DetectionResultColumn[] detectionResultColumnArr = this.detectionResultColumns;
        DetectionResultColumn detectionResultColumn = detectionResultColumnArr[0];
        DetectionResultColumn detectionResultColumn2 = detectionResultColumn;
        if (detectionResultColumn == null) {
            detectionResultColumn2 = detectionResultColumnArr[this.barcodeColumnCount + 1];
        }
        Formatter formatter = new Formatter();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= detectionResultColumn2.getCodewords().length) {
                String formatter2 = formatter.toString();
                formatter.close();
                return formatter2;
            }
            formatter.format("CW %3d:", Integer.valueOf(i2));
            int i3 = 0;
            while (true) {
                int i4 = i3;
                if (i4 < this.barcodeColumnCount + 2) {
                    DetectionResultColumn[] detectionResultColumnArr2 = this.detectionResultColumns;
                    if (detectionResultColumnArr2[i4] == null) {
                        formatter.format("    |   ", new Object[0]);
                    } else {
                        Codeword codeword = detectionResultColumnArr2[i4].getCodewords()[i2];
                        if (codeword == null) {
                            formatter.format("    |   ", new Object[0]);
                        } else {
                            formatter.format(" %3d|%3d", Integer.valueOf(codeword.getRowNumber()), Integer.valueOf(codeword.getValue()));
                        }
                    }
                    i3 = i4 + 1;
                }
            }
            formatter.format("%n", new Object[0]);
            i = i2 + 1;
        }
    }
}
