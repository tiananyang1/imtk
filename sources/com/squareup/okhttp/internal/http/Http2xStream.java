package com.squareup.okhttp.internal.http;

import com.squareup.okhttp.Headers;
import com.squareup.okhttp.Protocol;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;
import com.squareup.okhttp.internal.Util;
import com.squareup.okhttp.internal.framed.ErrorCode;
import com.squareup.okhttp.internal.framed.FramedConnection;
import com.squareup.okhttp.internal.framed.FramedStream;
import com.squareup.okhttp.internal.framed.Header;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import okio.ByteString;
import okio.ForwardingSource;
import okio.Okio;
import okio.Sink;
import okio.Source;

/* loaded from: classes08-dex2jar.jar:com/squareup/okhttp/internal/http/Http2xStream.class */
public final class Http2xStream implements HttpStream {
    private final FramedConnection framedConnection;
    private HttpEngine httpEngine;
    private FramedStream stream;
    private final StreamAllocation streamAllocation;
    private static final ByteString CONNECTION = ByteString.encodeUtf8("connection");
    private static final ByteString HOST = ByteString.encodeUtf8("host");
    private static final ByteString KEEP_ALIVE = ByteString.encodeUtf8("keep-alive");
    private static final ByteString PROXY_CONNECTION = ByteString.encodeUtf8("proxy-connection");
    private static final ByteString TRANSFER_ENCODING = ByteString.encodeUtf8("transfer-encoding");

    /* renamed from: TE */
    private static final ByteString f182TE = ByteString.encodeUtf8("te");
    private static final ByteString ENCODING = ByteString.encodeUtf8("encoding");
    private static final ByteString UPGRADE = ByteString.encodeUtf8("upgrade");
    private static final List<ByteString> SPDY_3_SKIPPED_REQUEST_HEADERS = Util.immutableList(CONNECTION, HOST, KEEP_ALIVE, PROXY_CONNECTION, TRANSFER_ENCODING, Header.TARGET_METHOD, Header.TARGET_PATH, Header.TARGET_SCHEME, Header.TARGET_AUTHORITY, Header.TARGET_HOST, Header.VERSION);
    private static final List<ByteString> SPDY_3_SKIPPED_RESPONSE_HEADERS = Util.immutableList(CONNECTION, HOST, KEEP_ALIVE, PROXY_CONNECTION, TRANSFER_ENCODING);
    private static final List<ByteString> HTTP_2_SKIPPED_REQUEST_HEADERS = Util.immutableList(CONNECTION, HOST, KEEP_ALIVE, PROXY_CONNECTION, f182TE, TRANSFER_ENCODING, ENCODING, UPGRADE, Header.TARGET_METHOD, Header.TARGET_PATH, Header.TARGET_SCHEME, Header.TARGET_AUTHORITY, Header.TARGET_HOST, Header.VERSION);
    private static final List<ByteString> HTTP_2_SKIPPED_RESPONSE_HEADERS = Util.immutableList(CONNECTION, HOST, KEEP_ALIVE, PROXY_CONNECTION, f182TE, TRANSFER_ENCODING, ENCODING, UPGRADE);

    /* loaded from: classes08-dex2jar.jar:com/squareup/okhttp/internal/http/Http2xStream$StreamFinishingSource.class */
    class StreamFinishingSource extends ForwardingSource {
        public StreamFinishingSource(Source source) {
            super(source);
        }

        public void close() throws IOException {
            Http2xStream.this.streamAllocation.streamFinished(Http2xStream.this);
            super.close();
        }
    }

    public Http2xStream(StreamAllocation streamAllocation, FramedConnection framedConnection) {
        this.streamAllocation = streamAllocation;
        this.framedConnection = framedConnection;
    }

    public static List<Header> http2HeadersList(Request request) {
        Headers headers = request.headers();
        ArrayList arrayList = new ArrayList(headers.size() + 4);
        arrayList.add(new Header(Header.TARGET_METHOD, request.method()));
        arrayList.add(new Header(Header.TARGET_PATH, RequestLine.requestPath(request.httpUrl())));
        arrayList.add(new Header(Header.TARGET_AUTHORITY, Util.hostHeader(request.httpUrl())));
        arrayList.add(new Header(Header.TARGET_SCHEME, request.httpUrl().scheme()));
        int size = headers.size();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= size) {
                return arrayList;
            }
            ByteString encodeUtf8 = ByteString.encodeUtf8(headers.name(i2).toLowerCase(Locale.US));
            if (!HTTP_2_SKIPPED_REQUEST_HEADERS.contains(encodeUtf8)) {
                arrayList.add(new Header(encodeUtf8, headers.value(i2)));
            }
            i = i2 + 1;
        }
    }

    private static String joinOnNull(String str, String str2) {
        return str + (char) 0 + str2;
    }

    public static Response.Builder readHttp2HeadersList(List<Header> list) throws IOException {
        String str;
        Headers.Builder builder = new Headers.Builder();
        int size = list.size();
        String str2 = null;
        int i = 0;
        while (i < size) {
            ByteString byteString = list.get(i).name;
            String utf8 = list.get(i).value.utf8();
            if (byteString.equals(Header.RESPONSE_STATUS)) {
                str = utf8;
            } else {
                str = str2;
                if (!HTTP_2_SKIPPED_RESPONSE_HEADERS.contains(byteString)) {
                    builder.add(byteString.utf8(), utf8);
                    str = str2;
                }
            }
            i++;
            str2 = str;
        }
        if (str2 == null) {
            throw new ProtocolException("Expected ':status' header not present");
        }
        StatusLine parse = StatusLine.parse("HTTP/1.1 " + str2);
        return new Response.Builder().protocol(Protocol.HTTP_2).code(parse.code).message(parse.message).headers(builder.build());
    }

    public static Response.Builder readSpdy3HeadersList(List<Header> list) throws IOException {
        String str;
        String str2;
        Headers.Builder builder = new Headers.Builder();
        int size = list.size();
        String str3 = "HTTP/1.1";
        String str4 = null;
        int i = 0;
        while (i < size) {
            ByteString byteString = list.get(i).name;
            String utf8 = list.get(i).value.utf8();
            String str5 = str4;
            int i2 = 0;
            String str6 = str3;
            String str7 = str5;
            while (i2 < utf8.length()) {
                int indexOf = utf8.indexOf(0, i2);
                int i3 = indexOf;
                if (indexOf == -1) {
                    i3 = utf8.length();
                }
                String substring = utf8.substring(i2, i3);
                if (byteString.equals(Header.RESPONSE_STATUS)) {
                    str = substring;
                    str2 = str6;
                } else if (byteString.equals(Header.VERSION)) {
                    str = str7;
                    str2 = substring;
                } else {
                    str = str7;
                    str2 = str6;
                    if (!SPDY_3_SKIPPED_RESPONSE_HEADERS.contains(byteString)) {
                        builder.add(byteString.utf8(), substring);
                        str2 = str6;
                        str = str7;
                    }
                }
                i2 = i3 + 1;
                str7 = str;
                str6 = str2;
            }
            i++;
            String str8 = str6;
            str4 = str7;
            str3 = str8;
        }
        if (str4 == null) {
            throw new ProtocolException("Expected ':status' header not present");
        }
        StatusLine parse = StatusLine.parse(str3 + " " + str4);
        return new Response.Builder().protocol(Protocol.SPDY_3).code(parse.code).message(parse.message).headers(builder.build());
    }

    public static List<Header> spdy3HeadersList(Request request) {
        Headers headers = request.headers();
        ArrayList arrayList = new ArrayList(headers.size() + 5);
        arrayList.add(new Header(Header.TARGET_METHOD, request.method()));
        arrayList.add(new Header(Header.TARGET_PATH, RequestLine.requestPath(request.httpUrl())));
        arrayList.add(new Header(Header.VERSION, "HTTP/1.1"));
        arrayList.add(new Header(Header.TARGET_HOST, Util.hostHeader(request.httpUrl())));
        arrayList.add(new Header(Header.TARGET_SCHEME, request.httpUrl().scheme()));
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        int size = headers.size();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= size) {
                return arrayList;
            }
            ByteString encodeUtf8 = ByteString.encodeUtf8(headers.name(i2).toLowerCase(Locale.US));
            if (!SPDY_3_SKIPPED_REQUEST_HEADERS.contains(encodeUtf8)) {
                String value = headers.value(i2);
                if (linkedHashSet.add(encodeUtf8)) {
                    arrayList.add(new Header(encodeUtf8, value));
                } else {
                    int i3 = 0;
                    while (true) {
                        int i4 = i3;
                        if (i4 >= arrayList.size()) {
                            break;
                        }
                        if (((Header) arrayList.get(i4)).name.equals(encodeUtf8)) {
                            arrayList.set(i4, new Header(encodeUtf8, joinOnNull(((Header) arrayList.get(i4)).value.utf8(), value)));
                            break;
                        }
                        i3 = i4 + 1;
                    }
                }
            }
            i = i2 + 1;
        }
    }

    @Override // com.squareup.okhttp.internal.http.HttpStream
    public void cancel() {
        FramedStream framedStream = this.stream;
        if (framedStream != null) {
            framedStream.closeLater(ErrorCode.CANCEL);
        }
    }

    @Override // com.squareup.okhttp.internal.http.HttpStream
    public Sink createRequestBody(Request request, long j) throws IOException {
        return this.stream.getSink();
    }

    @Override // com.squareup.okhttp.internal.http.HttpStream
    public void finishRequest() throws IOException {
        this.stream.getSink().close();
    }

    @Override // com.squareup.okhttp.internal.http.HttpStream
    public ResponseBody openResponseBody(Response response) throws IOException {
        return new RealResponseBody(response.headers(), Okio.buffer(new StreamFinishingSource(this.stream.getSource())));
    }

    @Override // com.squareup.okhttp.internal.http.HttpStream
    public Response.Builder readResponseHeaders() throws IOException {
        return this.framedConnection.getProtocol() == Protocol.HTTP_2 ? readHttp2HeadersList(this.stream.getResponseHeaders()) : readSpdy3HeadersList(this.stream.getResponseHeaders());
    }

    @Override // com.squareup.okhttp.internal.http.HttpStream
    public void setHttpEngine(HttpEngine httpEngine) {
        this.httpEngine = httpEngine;
    }

    @Override // com.squareup.okhttp.internal.http.HttpStream
    public void writeRequestBody(RetryableSink retryableSink) throws IOException {
        retryableSink.writeToSocket(this.stream.getSink());
    }

    @Override // com.squareup.okhttp.internal.http.HttpStream
    public void writeRequestHeaders(Request request) throws IOException {
        if (this.stream != null) {
            return;
        }
        this.httpEngine.writingRequestHeaders();
        this.stream = this.framedConnection.newStream(this.framedConnection.getProtocol() == Protocol.HTTP_2 ? http2HeadersList(request) : spdy3HeadersList(request), this.httpEngine.permitsRequestBody(request), true);
        this.stream.readTimeout().timeout(this.httpEngine.client.getReadTimeout(), TimeUnit.MILLISECONDS);
        this.stream.writeTimeout().timeout(this.httpEngine.client.getWriteTimeout(), TimeUnit.MILLISECONDS);
    }
}
