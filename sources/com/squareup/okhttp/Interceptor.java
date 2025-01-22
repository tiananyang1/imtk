package com.squareup.okhttp;

import java.io.IOException;

/* loaded from: classes08-dex2jar.jar:com/squareup/okhttp/Interceptor.class */
public interface Interceptor {

    /* loaded from: classes08-dex2jar.jar:com/squareup/okhttp/Interceptor$Chain.class */
    public interface Chain {
        Connection connection();

        Response proceed(Request request) throws IOException;

        Request request();
    }

    Response intercept(Chain chain) throws IOException;
}
