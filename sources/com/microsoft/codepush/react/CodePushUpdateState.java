package com.microsoft.codepush.react;

/* loaded from: classes08-dex2jar.jar:com/microsoft/codepush/react/CodePushUpdateState.class */
public enum CodePushUpdateState {
    RUNNING(0),
    PENDING(1),
    LATEST(2);

    private final int value;

    CodePushUpdateState(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
