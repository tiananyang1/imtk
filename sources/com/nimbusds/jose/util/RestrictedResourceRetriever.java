package com.nimbusds.jose.util;

/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/util/RestrictedResourceRetriever.class */
public interface RestrictedResourceRetriever extends ResourceRetriever {
    int getConnectTimeout();

    int getReadTimeout();

    int getSizeLimit();

    void setConnectTimeout(int i);

    void setReadTimeout(int i);

    void setSizeLimit(int i);
}
