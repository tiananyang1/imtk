package com.google.zxing.maxicode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.maxicode.decoder.Decoder;
import java.util.Map;

/* loaded from: classes08-dex2jar.jar:com/google/zxing/maxicode/MaxiCodeReader.class */
public final class MaxiCodeReader implements Reader {
    private static final int MATRIX_HEIGHT = 33;
    private static final int MATRIX_WIDTH = 30;
    private static final ResultPoint[] NO_POINTS = new ResultPoint[0];
    private final Decoder decoder = new Decoder();

    private static BitMatrix extractPureBits(BitMatrix bitMatrix) throws NotFoundException {
        int[] enclosingRectangle = bitMatrix.getEnclosingRectangle();
        if (enclosingRectangle == null) {
            throw NotFoundException.getNotFoundInstance();
        }
        int i = enclosingRectangle[0];
        int i2 = enclosingRectangle[1];
        int i3 = enclosingRectangle[2];
        int i4 = enclosingRectangle[3];
        BitMatrix bitMatrix2 = new BitMatrix(30, 33);
        int i5 = 0;
        while (true) {
            int i6 = i5;
            if (i6 >= 33) {
                return bitMatrix2;
            }
            int i7 = ((i6 * i4) + (i4 / 2)) / 33;
            int i8 = 0;
            while (true) {
                int i9 = i8;
                if (i9 < 30) {
                    if (bitMatrix.get(((((i9 * i3) + (i3 / 2)) + (((i6 & 1) * i3) / 2)) / 30) + i, i7 + i2)) {
                        bitMatrix2.set(i9, i6);
                    }
                    i8 = i9 + 1;
                }
            }
            i5 = i6 + 1;
        }
    }

    @Override // com.google.zxing.Reader
    public Result decode(BinaryBitmap binaryBitmap) throws NotFoundException, ChecksumException, FormatException {
        return decode(binaryBitmap, null);
    }

    @Override // com.google.zxing.Reader
    public Result decode(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException {
        if (map == null || !map.containsKey(DecodeHintType.PURE_BARCODE)) {
            throw NotFoundException.getNotFoundInstance();
        }
        DecoderResult decode = this.decoder.decode(extractPureBits(binaryBitmap.getBlackMatrix()), map);
        Result result = new Result(decode.getText(), decode.getRawBytes(), NO_POINTS, BarcodeFormat.MAXICODE);
        String eCLevel = decode.getECLevel();
        if (eCLevel != null) {
            result.putMetadata(ResultMetadataType.ERROR_CORRECTION_LEVEL, eCLevel);
        }
        return result;
    }

    @Override // com.google.zxing.Reader
    public void reset() {
    }
}
