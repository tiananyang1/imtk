package com.subgraph.orchid.directory.downloader;

import com.subgraph.orchid.Router;
import com.subgraph.orchid.Stream;
import com.xiaomi.mipush.sdk.Constants;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/directory/downloader/HttpConnection.class */
public class HttpConnection {
    private static final Charset CHARSET = Charset.forName("ISO-8859-1");
    private static final String COMPRESSION_SUFFIX = ".z";
    private static final String CONTENT_ENCODING_HEADER = "Content-Encoding";
    private static final String CONTENT_LENGTH_HEADER = "Content-Length";
    private static final String HTTP_RESPONSE_REGEX = "HTTP/1\\.(\\d) (\\d+) (.*)";
    private boolean bodyCompressed;
    private final Map<String, String> headers;
    private final String hostname;
    private final InputStream input;
    private ByteBuffer messageBody;
    private final OutputStream output;
    private int responseCode;
    private String responseMessage;
    private final Stream stream;
    private final boolean useCompression;

    public HttpConnection(Stream stream) {
        this(stream, true);
    }

    public HttpConnection(Stream stream, boolean z) {
        this.hostname = getHostnameFromStream(stream);
        this.stream = stream;
        this.headers = new HashMap();
        this.input = stream.getInputStream();
        this.output = stream.getOutputStream();
        this.useCompression = z;
    }

    private ByteBuffer bytesToBody(byte[] bArr) throws IOException {
        return this.bodyCompressed ? ByteBuffer.wrap(decompressBuffer(bArr)) : ByteBuffer.wrap(bArr);
    }

    private byte[] decompressBuffer(byte[] bArr) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Inflater inflater = new Inflater();
        byte[] bArr2 = new byte[4096];
        inflater.setInput(bArr);
        while (true) {
            try {
                int inflate = inflater.inflate(bArr2);
                if (inflate == 0) {
                    return byteArrayOutputStream.toByteArray();
                }
                byteArrayOutputStream.write(bArr2, 0, inflate);
            } catch (DataFormatException e) {
                throw new IOException("Error decompressing http body: " + e);
            }
        }
    }

    private static String getHostnameFromStream(Stream stream) {
        StringBuilder sb = new StringBuilder();
        Router router = stream.getCircuit().getFinalCircuitNode().getRouter();
        if (router == null) {
            return null;
        }
        sb.append(router.getAddress().toString());
        if (router.getOnionPort() != 80) {
            sb.append(Constants.COLON_SEPARATOR);
            sb.append(router.getOnionPort());
        }
        return sb.toString();
    }

    private String nextResponseLine() throws IOException, DirectoryRequestFailedException {
        String readInputLine = readInputLine();
        if (readInputLine != null) {
            return readInputLine;
        }
        throw new DirectoryRequestFailedException("Unexpected EOF reading HTTP response");
    }

    private void processContentEncodingHeader() throws DirectoryRequestFailedException {
        String str = this.headers.get("Content-Encoding");
        if (str == null || str.equals("identity")) {
            this.bodyCompressed = false;
            return;
        }
        if (str.equals("deflate") || str.equals("x-deflate")) {
            this.bodyCompressed = true;
            return;
        }
        throw new DirectoryRequestFailedException("Unrecognized content encoding: " + str);
    }

    private void readAll(byte[] bArr) throws IOException {
        int length = bArr.length;
        int i = 0;
        while (length > 0) {
            int read = this.input.read(bArr, i, length);
            if (read == -1) {
                throw new IOException("Unexpected early EOF reading HTTP body");
            }
            i += read;
            length -= read;
        }
    }

    private void readBody() throws IOException, DirectoryRequestFailedException {
        processContentEncodingHeader();
        if (this.headers.containsKey("Content-Length")) {
            readBodyFromContentLength();
        } else {
            readBodyUntilEOF();
        }
    }

    private void readBodyFromContentLength() throws IOException {
        byte[] bArr = new byte[Integer.parseInt(this.headers.get("Content-Length"))];
        readAll(bArr);
        this.messageBody = bytesToBody(bArr);
    }

    private void readBodyUntilEOF() throws IOException {
        this.messageBody = bytesToBody(readToEOF());
    }

    private void readHeaders() throws IOException, DirectoryRequestFailedException {
        this.headers.clear();
        while (true) {
            String nextResponseLine = nextResponseLine();
            if (nextResponseLine.length() == 0) {
                return;
            }
            String[] split = nextResponseLine.split(": ", 2);
            if (split.length != 2) {
                throw new DirectoryRequestFailedException("Failed to parse HTTP header: " + nextResponseLine);
            }
            this.headers.put(split[0], split[1]);
        }
    }

    private String readInputLine() throws IOException {
        StringBuilder sb = new StringBuilder();
        while (true) {
            int read = this.input.read();
            if (read == -1) {
                if (sb.length() == 0) {
                    return null;
                }
                return sb.toString();
            }
            if (read == 10) {
                return sb.toString();
            }
            if (read != 13) {
                sb.append((char) read);
            }
        }
    }

    private void readStatusLine() throws IOException, DirectoryRequestFailedException {
        String nextResponseLine = nextResponseLine();
        Matcher matcher = Pattern.compile(HTTP_RESPONSE_REGEX).matcher(nextResponseLine);
        if (!matcher.find() || matcher.groupCount() != 3) {
            throw new DirectoryRequestFailedException("Error parsing HTTP response line: " + nextResponseLine);
        }
        try {
            int parseInt = Integer.parseInt(matcher.group(1));
            int parseInt2 = Integer.parseInt(matcher.group(2));
            if ((parseInt == 0 || parseInt == 1) && parseInt2 >= 100 && parseInt2 < 600) {
                this.responseCode = parseInt2;
                this.responseMessage = matcher.group(3);
            } else {
                throw new DirectoryRequestFailedException("Failed to parse header: " + nextResponseLine);
            }
        } catch (NumberFormatException e) {
            throw new DirectoryRequestFailedException("Failed to parse header: " + nextResponseLine);
        }
    }

    private byte[] readToEOF() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[2048];
        while (true) {
            int read = this.input.read(bArr, 0, bArr.length);
            if (read == -1) {
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr, 0, read);
        }
    }

    public void close() {
        Stream stream = this.stream;
        if (stream == null) {
            return;
        }
        stream.close();
    }

    public String getHost() {
        String str = this.hostname;
        return str == null ? str : "(none)";
    }

    public ByteBuffer getMessageBody() {
        return this.messageBody;
    }

    public int getStatusCode() {
        return this.responseCode;
    }

    public String getStatusMessage() {
        return this.responseMessage;
    }

    public void readResponse() throws IOException, DirectoryRequestFailedException {
        readStatusLine();
        readHeaders();
        readBody();
    }

    public void sendGetRequest(String str) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("GET ");
        sb.append(str);
        if (this.useCompression && !str.endsWith(COMPRESSION_SUFFIX)) {
            sb.append(COMPRESSION_SUFFIX);
        }
        sb.append(" HTTP/1.0\r\n");
        if (this.hostname != null) {
            sb.append("Host: " + this.hostname + "\r\n");
        }
        sb.append("\r\n");
        this.output.write(sb.toString().getBytes(CHARSET));
        this.output.flush();
    }
}
