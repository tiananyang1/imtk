package com.squareup.okhttp.internal.http;

import io.fabric.sdk.android.services.network.HttpRequest;

/* loaded from: classes08-dex2jar.jar:com/squareup/okhttp/internal/http/HttpMethod.class */
public final class HttpMethod {
    private HttpMethod() {
    }

    public static boolean invalidatesCache(String str) {
        return str.equals(HttpRequest.METHOD_POST) || str.equals("PATCH") || str.equals(HttpRequest.METHOD_PUT) || str.equals(HttpRequest.METHOD_DELETE) || str.equals("MOVE");
    }

    public static boolean permitsRequestBody(String str) {
        return requiresRequestBody(str) || str.equals(HttpRequest.METHOD_OPTIONS) || str.equals(HttpRequest.METHOD_DELETE) || str.equals("PROPFIND") || str.equals("MKCOL") || str.equals("LOCK");
    }

    public static boolean redirectsToGet(String str) {
        return !str.equals("PROPFIND");
    }

    public static boolean requiresRequestBody(String str) {
        return str.equals(HttpRequest.METHOD_POST) || str.equals(HttpRequest.METHOD_PUT) || str.equals("PATCH") || str.equals("PROPPATCH") || str.equals("REPORT");
    }
}
