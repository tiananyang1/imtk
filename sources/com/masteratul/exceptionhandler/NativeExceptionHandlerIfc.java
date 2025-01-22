package com.masteratul.exceptionhandler;

import java.lang.Thread;

/* loaded from: classes08-dex2jar.jar:com/masteratul/exceptionhandler/NativeExceptionHandlerIfc.class */
public interface NativeExceptionHandlerIfc {
    void handleNativeException(Thread thread, Throwable th, Thread.UncaughtExceptionHandler uncaughtExceptionHandler);
}
