package com.subgraph.orchid.circuits;

import com.subgraph.orchid.crypto.TorRandom;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/circuits/CircuitStatus.class */
public class CircuitStatus {
    private int currentStreamId;
    private long timestampCreated;
    private long timestampDirty;
    private Object streamIdLock = new Object();
    private volatile CircuitState state = CircuitState.UNCONNECTED;

    /* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/circuits/CircuitStatus$CircuitState.class */
    enum CircuitState {
        UNCONNECTED("Unconnected"),
        BUILDING("Building"),
        FAILED("Failed"),
        OPEN("Open"),
        DESTROYED("Destroyed");

        String name;

        CircuitState(String str) {
            this.name = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.name;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CircuitStatus() {
        initializeCurrentStreamId();
    }

    private String getDirtyString() {
        if (!isDirty()) {
            return "Clean";
        }
        return "Dirty " + (getMillisecondsDirty() / 1000) + "s";
    }

    private void initializeCurrentStreamId() {
        this.currentStreamId = new TorRandom().nextInt(65535) + 1;
    }

    private static long millisecondsElapsedSince(long j) {
        if (j == 0) {
            return 0L;
        }
        return System.currentTimeMillis() - j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long getMillisecondsDirty() {
        long millisecondsElapsedSince;
        synchronized (this) {
            millisecondsElapsedSince = millisecondsElapsedSince(this.timestampDirty);
        }
        return millisecondsElapsedSince;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long getMillisecondsElapsedSinceCreated() {
        long millisecondsElapsedSince;
        synchronized (this) {
            millisecondsElapsedSince = millisecondsElapsedSince(this.timestampCreated);
        }
        return millisecondsElapsedSince;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getStateAsString() {
        if (this.state != CircuitState.OPEN) {
            return this.state.toString();
        }
        return this.state.toString() + " [" + getDirtyString() + "]";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isBuilding() {
        return this.state == CircuitState.BUILDING;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isConnected() {
        return this.state == CircuitState.OPEN;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isDirty() {
        boolean z;
        synchronized (this) {
            z = this.timestampDirty != 0;
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isUnconnected() {
        return this.state == CircuitState.UNCONNECTED;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int nextStreamId() {
        int i;
        synchronized (this.streamIdLock) {
            this.currentStreamId++;
            if (this.currentStreamId > 65535) {
                this.currentStreamId = 1;
            }
            i = this.currentStreamId;
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setStateBuilding() {
        this.state = CircuitState.BUILDING;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setStateDestroyed() {
        this.state = CircuitState.DESTROYED;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setStateFailed() {
        this.state = CircuitState.FAILED;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setStateOpen() {
        this.state = CircuitState.OPEN;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updateCreatedTimestamp() {
        synchronized (this) {
            this.timestampCreated = System.currentTimeMillis();
            this.timestampDirty = 0L;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updateDirtyTimestamp() {
        synchronized (this) {
            if (this.timestampDirty == 0 && this.state != CircuitState.BUILDING) {
                this.timestampDirty = System.currentTimeMillis();
            }
        }
    }
}
