package com.nimbusds.jose.util;

import net.jcip.annotations.NotThreadSafe;

@NotThreadSafe
/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/util/Container.class */
public class Container<T> {
    private T item;

    public Container() {
    }

    public Container(T t) {
        this.item = t;
    }

    public T get() {
        return this.item;
    }

    public void set(T t) {
        this.item = t;
    }
}
