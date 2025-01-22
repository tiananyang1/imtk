package com.scottyab.rootbeer;

import com.scottyab.rootbeer.util.QLog;

/* loaded from: classes08-dex2jar.jar:com/scottyab/rootbeer/RootBeerNative.class */
public class RootBeerNative {
    static boolean libraryLoaded;

    static {
        try {
            System.loadLibrary("tool-checker");
            libraryLoaded = true;
        } catch (UnsatisfiedLinkError e) {
            QLog.m47e(e);
        }
    }

    public native int checkForRoot(Object[] objArr);

    public native int setLogDebugMessages(boolean z);

    public boolean wasNativeLibraryLoaded() {
        return libraryLoaded;
    }
}
