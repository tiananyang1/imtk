package com.google.zxing.pdf417.encoder;

import java.lang.reflect.Array;

/* loaded from: classes08-dex2jar.jar:com/google/zxing/pdf417/encoder/BarcodeMatrix.class */
public final class BarcodeMatrix {
    private int currentRow;
    private final int height;
    private final BarcodeRow[] matrix;
    private final int width;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BarcodeMatrix(int i, int i2) {
        this.matrix = new BarcodeRow[i];
        int length = this.matrix.length;
        int i3 = 0;
        while (true) {
            int i4 = i3;
            if (i4 >= length) {
                this.width = i2 * 17;
                this.height = i;
                this.currentRow = -1;
                return;
            }
            this.matrix[i4] = new BarcodeRow(((i2 + 4) * 17) + 1);
            i3 = i4 + 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BarcodeRow getCurrentRow() {
        return this.matrix[this.currentRow];
    }

    public byte[][] getMatrix() {
        return getScaledMatrix(1, 1);
    }

    public byte[][] getScaledMatrix(int i, int i2) {
        byte[][] bArr = (byte[][]) Array.newInstance((Class<?>) Byte.TYPE, this.height * i2, this.width * i);
        int i3 = this.height * i2;
        int i4 = 0;
        while (true) {
            int i5 = i4;
            if (i5 >= i3) {
                return bArr;
            }
            bArr[(i3 - i5) - 1] = this.matrix[i5 / i2].getScaledRow(i);
            i4 = i5 + 1;
        }
    }

    void set(int i, int i2, byte b) {
        this.matrix[i2].set(i, b);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void startRow() {
        this.currentRow++;
    }
}
