package com.subgraph.orchid.encoders;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/encoders/DecoderException.class */
public class DecoderException extends IllegalStateException {
    private static final long serialVersionUID = 4997418733670548381L;
    private Throwable cause;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DecoderException(String str, Throwable th) {
        super(str);
        this.cause = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }
}
