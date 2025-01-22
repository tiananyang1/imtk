package com.microsoft.codepush.react;

/* loaded from: classes08-dex2jar.jar:com/microsoft/codepush/react/CodePushInstallMode.class */
public enum CodePushInstallMode {
    IMMEDIATE(0),
    ON_NEXT_RESTART(1),
    ON_NEXT_RESUME(2),
    ON_NEXT_SUSPEND(3);

    private final int value;

    CodePushInstallMode(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
