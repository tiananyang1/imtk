package com.google.zxing.pdf417.encoder;

/* loaded from: classes08-dex2jar.jar:com/google/zxing/pdf417/encoder/BarcodeRow.class */
final class BarcodeRow {
    private int currentLocation = 0;
    private final byte[] row;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BarcodeRow(int i) {
        this.row = new byte[i];
    }

    private void set(int i, boolean z) {
        this.row[i] = z ? (byte) 1 : (byte) 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addBar(boolean z, int i) {
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 >= i) {
                return;
            }
            int i4 = this.currentLocation;
            this.currentLocation = i4 + 1;
            set(i4, z);
            i2 = i3 + 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] getScaledRow(int i) {
        byte[] bArr = new byte[this.row.length * i];
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 >= bArr.length) {
                return bArr;
            }
            bArr[i3] = this.row[i3 / i];
            i2 = i3 + 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void set(int i, byte b) {
        this.row[i] = b;
    }
}
