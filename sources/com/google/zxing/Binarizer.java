package com.google.zxing;

import com.google.zxing.common.BitArray;
import com.google.zxing.common.BitMatrix;

/* loaded from: classes08-dex2jar.jar:com/google/zxing/Binarizer.class */
public abstract class Binarizer {
    private final LuminanceSource source;

    /* JADX INFO: Access modifiers changed from: protected */
    public Binarizer(LuminanceSource luminanceSource) {
        this.source = luminanceSource;
    }

    public abstract Binarizer createBinarizer(LuminanceSource luminanceSource);

    public abstract BitMatrix getBlackMatrix() throws NotFoundException;

    public abstract BitArray getBlackRow(int i, BitArray bitArray) throws NotFoundException;

    public final int getHeight() {
        return this.source.getHeight();
    }

    public final LuminanceSource getLuminanceSource() {
        return this.source;
    }

    public final int getWidth() {
        return this.source.getWidth();
    }
}
