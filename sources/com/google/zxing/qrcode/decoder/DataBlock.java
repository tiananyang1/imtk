package com.google.zxing.qrcode.decoder;

import com.google.zxing.qrcode.decoder.Version;

/* loaded from: classes08-dex2jar.jar:com/google/zxing/qrcode/decoder/DataBlock.class */
final class DataBlock {
    private final byte[] codewords;
    private final int numDataCodewords;

    private DataBlock(int i, byte[] bArr) {
        this.numDataCodewords = i;
        this.codewords = bArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static DataBlock[] getDataBlocks(byte[] bArr, Version version, ErrorCorrectionLevel errorCorrectionLevel) {
        int i;
        int i2;
        int i3;
        if (bArr.length != version.getTotalCodewords()) {
            throw new IllegalArgumentException();
        }
        Version.ECBlocks eCBlocksForLevel = version.getECBlocksForLevel(errorCorrectionLevel);
        Version.ECB[] eCBlocks = eCBlocksForLevel.getECBlocks();
        int i4 = 0;
        for (Version.ECB ecb : eCBlocks) {
            i4 += ecb.getCount();
        }
        DataBlock[] dataBlockArr = new DataBlock[i4];
        int length = eCBlocks.length;
        int i5 = 0;
        int i6 = 0;
        while (true) {
            i = i6;
            if (i5 >= length) {
                break;
            }
            Version.ECB ecb2 = eCBlocks[i5];
            int i7 = i;
            int i8 = 0;
            while (i8 < ecb2.getCount()) {
                int dataCodewords = ecb2.getDataCodewords();
                dataBlockArr[i7] = new DataBlock(dataCodewords, new byte[eCBlocksForLevel.getECCodewordsPerBlock() + dataCodewords]);
                i8++;
                i7++;
            }
            i5++;
            i6 = i7;
        }
        int length2 = dataBlockArr[0].codewords.length;
        int length3 = dataBlockArr.length;
        while (true) {
            i2 = length3 - 1;
            if (i2 < 0 || dataBlockArr[i2].codewords.length == length2) {
                break;
            }
            length3 = i2;
        }
        int i9 = i2 + 1;
        int eCCodewordsPerBlock = length2 - eCBlocksForLevel.getECCodewordsPerBlock();
        int i10 = 0;
        for (int i11 = 0; i11 < eCCodewordsPerBlock; i11++) {
            int i12 = 0;
            while (i12 < i) {
                dataBlockArr[i12].codewords[i11] = bArr[i10];
                i12++;
                i10++;
            }
        }
        int i13 = i9;
        int i14 = i10;
        while (true) {
            i3 = i14;
            if (i13 >= i) {
                break;
            }
            dataBlockArr[i13].codewords[eCCodewordsPerBlock] = bArr[i3];
            i13++;
            i14 = i3 + 1;
        }
        int length4 = dataBlockArr[0].codewords.length;
        int i15 = eCCodewordsPerBlock;
        while (true) {
            int i16 = i15;
            if (i16 >= length4) {
                return dataBlockArr;
            }
            int i17 = 0;
            while (i17 < i) {
                dataBlockArr[i17].codewords[i17 < i9 ? i16 : i16 + 1] = bArr[i3];
                i17++;
                i3++;
            }
            i15 = i16 + 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] getCodewords() {
        return this.codewords;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getNumDataCodewords() {
        return this.numDataCodewords;
    }
}
