package com.sun.jna;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.IdentityHashMap;

/* loaded from: classes08-dex2jar.jar:com/sun/jna/WeakMemoryHolder.class */
public class WeakMemoryHolder {
    ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
    IdentityHashMap<Reference<Object>, Memory> backingMap = new IdentityHashMap<>();

    public void clean() {
        synchronized (this) {
            ReferenceQueue<Object> referenceQueue = this.referenceQueue;
            while (true) {
                Reference<? extends Object> poll = referenceQueue.poll();
                if (poll != null) {
                    this.backingMap.remove(poll);
                    referenceQueue = this.referenceQueue;
                }
            }
        }
    }

    public void put(Object obj, Memory memory) {
        synchronized (this) {
            clean();
            this.backingMap.put(new WeakReference(obj, this.referenceQueue), memory);
        }
    }
}
