package com.nimbusds.jose;

/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/PayloadTransformer.class */
public interface PayloadTransformer<T> {
    T transform(Payload payload);
}
