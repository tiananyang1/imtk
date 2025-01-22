package com.sun.jna;

/* loaded from: classes08-dex2jar.jar:com/sun/jna/CallbackThreadInitializer.class */
public class CallbackThreadInitializer {
    private boolean daemon;
    private boolean detach;
    private ThreadGroup group;
    private String name;

    public CallbackThreadInitializer() {
        this(true);
    }

    public CallbackThreadInitializer(boolean z) {
        this(z, false);
    }

    public CallbackThreadInitializer(boolean z, boolean z2) {
        this(z, z2, null);
    }

    public CallbackThreadInitializer(boolean z, boolean z2, String str) {
        this(z, z2, str, null);
    }

    public CallbackThreadInitializer(boolean z, boolean z2, String str, ThreadGroup threadGroup) {
        this.daemon = z;
        this.detach = z2;
        this.name = str;
        this.group = threadGroup;
    }

    public boolean detach(Callback callback) {
        return this.detach;
    }

    public String getName(Callback callback) {
        return this.name;
    }

    public ThreadGroup getThreadGroup(Callback callback) {
        return this.group;
    }

    public boolean isDaemon(Callback callback) {
        return this.daemon;
    }
}
