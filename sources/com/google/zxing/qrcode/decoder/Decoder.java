package com.google.zxing.qrcode.decoder;

import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonDecoder;
import com.google.zxing.common.reedsolomon.ReedSolomonException;
import java.util.Map;

/* loaded from: classes08-dex2jar.jar:com/google/zxing/qrcode/decoder/Decoder.class */
public final class Decoder {
    private final ReedSolomonDecoder rsDecoder = new ReedSolomonDecoder(GenericGF.QR_CODE_FIELD_256);

    private void correctErrors(byte[] bArr, int i) throws ChecksumException {
        int length = bArr.length;
        int[] iArr = new int[length];
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 >= length) {
                try {
                    break;
                } catch (ReedSolomonException e) {
                    throw ChecksumException.getChecksumInstance();
                }
            } else {
                iArr[i3] = bArr[i3] & 255;
                i2 = i3 + 1;
            }
        }
        this.rsDecoder.decode(iArr, bArr.length - i);
        int i4 = 0;
        while (true) {
            int i5 = i4;
            if (i5 >= i) {
                return;
            }
            bArr[i5] = (byte) iArr[i5];
            i4 = i5 + 1;
        }
    }

    private DecoderResult decode(BitMatrixParser bitMatrixParser, Map<DecodeHintType, ?> map) throws FormatException, ChecksumException {
        Version readVersion = bitMatrixParser.readVersion();
        ErrorCorrectionLevel errorCorrectionLevel = bitMatrixParser.readFormatInformation().getErrorCorrectionLevel();
        DataBlock[] dataBlocks = DataBlock.getDataBlocks(bitMatrixParser.readCodewords(), readVersion, errorCorrectionLevel);
        int i = 0;
        for (DataBlock dataBlock : dataBlocks) {
            i += dataBlock.getNumDataCodewords();
        }
        byte[] bArr = new byte[i];
        int i2 = 0;
        for (DataBlock dataBlock2 : dataBlocks) {
            byte[] codewords = dataBlock2.getCodewords();
            int numDataCodewords = dataBlock2.getNumDataCodewords();
            correctErrors(codewords, numDataCodewords);
            int i3 = 0;
            while (i3 < numDataCodewords) {
                bArr[i2] = codewords[i3];
                i3++;
                i2++;
            }
        }
        return DecodedBitStreamParser.decode(bArr, readVersion, errorCorrectionLevel, map);
    }

    public DecoderResult decode(BitMatrix bitMatrix) throws ChecksumException, FormatException {
        return decode(bitMatrix, (Map<DecodeHintType, ?>) null);
    }

    public DecoderResult decode(BitMatrix bitMatrix, Map<DecodeHintType, ?> map) throws FormatException, ChecksumException {
        ChecksumException e;
        BitMatrixParser bitMatrixParser = new BitMatrixParser(bitMatrix);
        FormatException e2 = null;
        try {
            return decode(bitMatrixParser, map);
        } catch (ChecksumException e3) {
            e = e3;
            try {
                bitMatrixParser.remask();
                bitMatrixParser.setMirror(true);
                bitMatrixParser.readVersion();
                bitMatrixParser.readFormatInformation();
                bitMatrixParser.mirror();
                DecoderResult decode = decode(bitMatrixParser, map);
                decode.setOther(new QRCodeDecoderMetaData(true));
                return decode;
            } catch (ChecksumException | FormatException e4) {
                if (e2 != null) {
                    throw e2;
                }
                if (e != null) {
                    throw e;
                }
                throw e4;
            }
        } catch (FormatException e5) {
            e2 = e5;
            e = null;
            bitMatrixParser.remask();
            bitMatrixParser.setMirror(true);
            bitMatrixParser.readVersion();
            bitMatrixParser.readFormatInformation();
            bitMatrixParser.mirror();
            DecoderResult decode2 = decode(bitMatrixParser, map);
            decode2.setOther(new QRCodeDecoderMetaData(true));
            return decode2;
        }
    }

    public DecoderResult decode(boolean[][] zArr) throws ChecksumException, FormatException {
        return decode(zArr, (Map<DecodeHintType, ?>) null);
    }

    public DecoderResult decode(boolean[][] zArr, Map<DecodeHintType, ?> map) throws ChecksumException, FormatException {
        int length = zArr.length;
        BitMatrix bitMatrix = new BitMatrix(length);
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length) {
                return decode(bitMatrix, map);
            }
            int i3 = 0;
            while (true) {
                int i4 = i3;
                if (i4 < length) {
                    if (zArr[i2][i4]) {
                        bitMatrix.set(i4, i2);
                    }
                    i3 = i4 + 1;
                }
            }
            i = i2 + 1;
        }
    }
}
