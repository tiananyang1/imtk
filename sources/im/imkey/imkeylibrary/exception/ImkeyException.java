package im.imkey.imkeylibrary.exception;

/* loaded from: classes08-dex2jar.jar:im/imkey/imkeylibrary/exception/ImkeyException.class */
public class ImkeyException extends RuntimeException {
    public ImkeyException(String str) {
        super(str);
    }

    public ImkeyException(String str, Throwable th) {
        super(str, th);
    }

    public ImkeyException(Throwable th) {
        super(th);
    }

    @Override // java.lang.Throwable
    public String toString() {
        return getMessage();
    }
}
