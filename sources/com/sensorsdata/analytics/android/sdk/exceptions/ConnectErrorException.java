package com.sensorsdata.analytics.android.sdk.exceptions;

/* loaded from: classes08-dex2jar.jar:com/sensorsdata/analytics/android/sdk/exceptions/ConnectErrorException.class */
public class ConnectErrorException extends Exception {
    private int mRetryAfter;

    public ConnectErrorException(String str) {
        super(str);
        this.mRetryAfter = 30000;
    }

    public ConnectErrorException(String str, String str2) {
        super(str);
        try {
            this.mRetryAfter = Integer.parseInt(str2);
        } catch (NumberFormatException e) {
            this.mRetryAfter = 0;
        }
    }

    public ConnectErrorException(Throwable th) {
        super(th);
    }

    public int getRetryAfter() {
        return this.mRetryAfter;
    }
}
