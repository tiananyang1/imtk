package com.google.zxing.datamatrix;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Dimension;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Writer;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.datamatrix.encoder.DefaultPlacement;
import com.google.zxing.datamatrix.encoder.ErrorCorrection;
import com.google.zxing.datamatrix.encoder.HighLevelEncoder;
import com.google.zxing.datamatrix.encoder.SymbolInfo;
import com.google.zxing.datamatrix.encoder.SymbolShapeHint;
import com.google.zxing.qrcode.encoder.ByteMatrix;
import java.util.Map;

/* loaded from: classes08-dex2jar.jar:com/google/zxing/datamatrix/DataMatrixWriter.class */
public final class DataMatrixWriter implements Writer {
    private static BitMatrix convertByteMatrixToBitMatrix(ByteMatrix byteMatrix) {
        int width = byteMatrix.getWidth();
        int height = byteMatrix.getHeight();
        BitMatrix bitMatrix = new BitMatrix(width, height);
        bitMatrix.clear();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= width) {
                return bitMatrix;
            }
            int i3 = 0;
            while (true) {
                int i4 = i3;
                if (i4 < height) {
                    if (byteMatrix.get(i2, i4) == 1) {
                        bitMatrix.set(i2, i4);
                    }
                    i3 = i4 + 1;
                }
            }
            i = i2 + 1;
        }
    }

    private static BitMatrix encodeLowLevel(DefaultPlacement defaultPlacement, SymbolInfo symbolInfo) {
        int symbolDataWidth = symbolInfo.getSymbolDataWidth();
        int symbolDataHeight = symbolInfo.getSymbolDataHeight();
        ByteMatrix byteMatrix = new ByteMatrix(symbolInfo.getSymbolWidth(), symbolInfo.getSymbolHeight());
        int i = 0;
        for (int i2 = 0; i2 < symbolDataHeight; i2++) {
            int i3 = i;
            if (i2 % symbolInfo.matrixHeight == 0) {
                int i4 = 0;
                for (int i5 = 0; i5 < symbolInfo.getSymbolWidth(); i5++) {
                    byteMatrix.set(i4, i, i5 % 2 == 0);
                    i4++;
                }
                i3 = i + 1;
            }
            int i6 = 0;
            for (int i7 = 0; i7 < symbolDataWidth; i7++) {
                int i8 = i6;
                if (i7 % symbolInfo.matrixWidth == 0) {
                    byteMatrix.set(i6, i3, true);
                    i8 = i6 + 1;
                }
                byteMatrix.set(i8, i3, defaultPlacement.getBit(i7, i2));
                int i9 = i8 + 1;
                i6 = i9;
                if (i7 % symbolInfo.matrixWidth == symbolInfo.matrixWidth - 1) {
                    byteMatrix.set(i9, i3, i2 % 2 == 0);
                    i6 = i9 + 1;
                }
            }
            int i10 = i3 + 1;
            i = i10;
            if (i2 % symbolInfo.matrixHeight == symbolInfo.matrixHeight - 1) {
                int i11 = 0;
                for (int i12 = 0; i12 < symbolInfo.getSymbolWidth(); i12++) {
                    byteMatrix.set(i11, i10, true);
                    i11++;
                }
                i = i10 + 1;
            }
        }
        return convertByteMatrixToBitMatrix(byteMatrix);
    }

    @Override // com.google.zxing.Writer
    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i, int i2) {
        return encode(str, barcodeFormat, i, i2, null);
    }

    @Override // com.google.zxing.Writer
    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) {
        Dimension dimension;
        SymbolShapeHint symbolShapeHint;
        if (str.isEmpty()) {
            throw new IllegalArgumentException("Found empty contents");
        }
        if (barcodeFormat != BarcodeFormat.DATA_MATRIX) {
            throw new IllegalArgumentException("Can only encode DATA_MATRIX, but got " + barcodeFormat);
        }
        if (i < 0 || i2 < 0) {
            throw new IllegalArgumentException("Requested dimensions are too small: " + i + 'x' + i2);
        }
        SymbolShapeHint symbolShapeHint2 = SymbolShapeHint.FORCE_NONE;
        Dimension dimension2 = null;
        if (map != null) {
            SymbolShapeHint symbolShapeHint3 = (SymbolShapeHint) map.get(EncodeHintType.DATA_MATRIX_SHAPE);
            if (symbolShapeHint3 != null) {
                symbolShapeHint2 = symbolShapeHint3;
            }
            Dimension dimension3 = (Dimension) map.get(EncodeHintType.MIN_SIZE);
            if (dimension3 == null) {
                dimension3 = null;
            }
            Dimension dimension4 = (Dimension) map.get(EncodeHintType.MAX_SIZE);
            symbolShapeHint = symbolShapeHint2;
            dimension = dimension3;
            if (dimension4 != null) {
                dimension2 = dimension4;
                symbolShapeHint = symbolShapeHint2;
                dimension = dimension3;
            }
        } else {
            dimension = null;
            symbolShapeHint = symbolShapeHint2;
        }
        String encodeHighLevel = HighLevelEncoder.encodeHighLevel(str, symbolShapeHint, dimension, dimension2);
        SymbolInfo lookup = SymbolInfo.lookup(encodeHighLevel.length(), symbolShapeHint, dimension, dimension2, true);
        DefaultPlacement defaultPlacement = new DefaultPlacement(ErrorCorrection.encodeECC200(encodeHighLevel, lookup), lookup.getSymbolDataWidth(), lookup.getSymbolDataHeight());
        defaultPlacement.place();
        return encodeLowLevel(defaultPlacement, lookup);
    }
}
