package com.lambdaworks.jni;

/* loaded from: classes08-dex2jar.jar:com/lambdaworks/jni/SysLibraryLoader.class */
public class SysLibraryLoader implements LibraryLoader {
    @Override // com.lambdaworks.jni.LibraryLoader
    public boolean load(String str, boolean z) {
        try {
            System.loadLibrary(str);
            return true;
        } catch (Throwable th) {
            return false;
        }
    }
}
