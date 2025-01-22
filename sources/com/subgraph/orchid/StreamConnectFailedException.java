package com.subgraph.orchid;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/StreamConnectFailedException.class */
public class StreamConnectFailedException extends Exception {
    private static final long serialVersionUID = 8103571310659595097L;
    private final int reason;

    public StreamConnectFailedException(int i) {
        this.reason = i;
    }

    private static boolean isRetryableReason(int i) {
        return i == 1 || i == 2 || i == 4 || i == 11 || i == 8 || i == 9;
    }

    public int getReason() {
        return this.reason;
    }

    public boolean isReasonRetryable() {
        return isRetryableReason(this.reason);
    }
}
