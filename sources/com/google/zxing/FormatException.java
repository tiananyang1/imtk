package com.google.zxing;

/* loaded from: classes08-dex2jar.jar:com/google/zxing/FormatException.class */
public final class FormatException extends ReaderException {
    private static final FormatException INSTANCE;

    static {
        FormatException formatException = new FormatException();
        INSTANCE = formatException;
        formatException.setStackTrace(NO_TRACE);
    }

    private FormatException() {
    }

    private FormatException(Throwable th) {
        super(th);
    }

    public static FormatException getFormatInstance() {
        return isStackTrace ? new FormatException() : INSTANCE;
    }

    public static FormatException getFormatInstance(Throwable th) {
        return isStackTrace ? new FormatException(th) : INSTANCE;
    }
}
