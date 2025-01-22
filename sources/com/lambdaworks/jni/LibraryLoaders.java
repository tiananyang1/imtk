package com.lambdaworks.jni;

/* loaded from: classes08-dex2jar.jar:com/lambdaworks/jni/LibraryLoaders.class */
public class LibraryLoaders {
    public static LibraryLoader loader() {
        String property = System.getProperty("com.lambdaworks.jni.loader");
        if (property == null) {
            return System.getProperty("java.vm.specification.name").startsWith("Java") ? new JarLibraryLoader() : new SysLibraryLoader();
        }
        if (property.equals("sys")) {
            return new SysLibraryLoader();
        }
        if (property.equals("nil")) {
            return new NilLibraryLoader();
        }
        if (property.equals("jar")) {
            return new JarLibraryLoader();
        }
        throw new IllegalStateException("Illegal value for com.lambdaworks.jni.loader: " + property);
    }
}
