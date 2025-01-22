package com.squareup.okhttp.internal.http;

import com.squareup.okhttp.Headers;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.ResponseBody;
import io.fabric.sdk.android.services.network.HttpRequest;
import okio.BufferedSource;

/* loaded from: classes08-dex2jar.jar:com/squareup/okhttp/internal/http/RealResponseBody.class */
public final class RealResponseBody extends ResponseBody {
    private final Headers headers;
    private final BufferedSource source;

    public RealResponseBody(Headers headers, BufferedSource bufferedSource) {
        this.headers = headers;
        this.source = bufferedSource;
    }

    @Override // com.squareup.okhttp.ResponseBody
    public long contentLength() {
        return OkHeaders.contentLength(this.headers);
    }

    @Override // com.squareup.okhttp.ResponseBody
    public MediaType contentType() {
        String str = this.headers.get(HttpRequest.HEADER_CONTENT_TYPE);
        if (str != null) {
            return MediaType.parse(str);
        }
        return null;
    }

    @Override // com.squareup.okhttp.ResponseBody
    public BufferedSource source() {
        return this.source;
    }
}
