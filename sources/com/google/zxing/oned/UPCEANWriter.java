package com.google.zxing.oned;

/* loaded from: classes08-dex2jar.jar:com/google/zxing/oned/UPCEANWriter.class */
public abstract class UPCEANWriter extends OneDimensionalCodeWriter {
    @Override // com.google.zxing.oned.OneDimensionalCodeWriter
    public int getDefaultMargin() {
        return UPCEANReader.START_END_PATTERN.length;
    }
}
