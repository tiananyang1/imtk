package com.squareup.okhttp;

import io.fabric.sdk.android.services.network.HttpRequest;
import okio.Buffer;

/* loaded from: classes08-dex2jar.jar:com/squareup/okhttp/FormEncodingBuilder.class */
public final class FormEncodingBuilder {
    private static final MediaType CONTENT_TYPE = MediaType.parse(HttpRequest.CONTENT_TYPE_FORM);
    private final Buffer content = new Buffer();

    public FormEncodingBuilder add(String str, String str2) {
        if (this.content.size() > 0) {
            this.content.writeByte(38);
        }
        HttpUrl.canonicalize(this.content, str, 0, str.length(), " \"':;<=>@[]^`{}|/\\?#&!$(),~", false, true, true);
        this.content.writeByte(61);
        HttpUrl.canonicalize(this.content, str2, 0, str2.length(), " \"':;<=>@[]^`{}|/\\?#&!$(),~", false, true, true);
        return this;
    }

    public FormEncodingBuilder addEncoded(String str, String str2) {
        if (this.content.size() > 0) {
            this.content.writeByte(38);
        }
        HttpUrl.canonicalize(this.content, str, 0, str.length(), " \"':;<=>@[]^`{}|/\\?#&!$(),~", true, true, true);
        this.content.writeByte(61);
        HttpUrl.canonicalize(this.content, str2, 0, str2.length(), " \"':;<=>@[]^`{}|/\\?#&!$(),~", true, true, true);
        return this;
    }

    public RequestBody build() {
        return RequestBody.create(CONTENT_TYPE, this.content.snapshot());
    }
}
