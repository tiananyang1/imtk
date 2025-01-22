package com.subgraph.orchid.encoders;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/encoders/EncoderException.class */
public class EncoderException extends IllegalStateException {
    private static final long serialVersionUID = 6589388628939318400L;
    private Throwable cause;

    /* JADX INFO: Access modifiers changed from: package-private */
    public EncoderException(String str, Throwable th) {
        super(str);
        this.cause = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }
}
