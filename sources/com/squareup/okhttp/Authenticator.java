package com.squareup.okhttp;

import java.io.IOException;
import java.net.Proxy;

/* loaded from: classes08-dex2jar.jar:com/squareup/okhttp/Authenticator.class */
public interface Authenticator {
    Request authenticate(Proxy proxy, Response response) throws IOException;

    Request authenticateProxy(Proxy proxy, Response response) throws IOException;
}
