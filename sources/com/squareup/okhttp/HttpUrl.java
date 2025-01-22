package com.squareup.okhttp;

import java.net.IDN;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import okio.Buffer;

/* loaded from: classes08-dex2jar.jar:com/squareup/okhttp/HttpUrl.class */
public final class HttpUrl {
    static final String FORM_ENCODE_SET = " \"':;<=>@[]^`{}|/\\?#&!$(),~";
    static final String FRAGMENT_ENCODE_SET = "";
    static final String FRAGMENT_ENCODE_SET_URI = " \"#<>\\^`{|}";
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    static final String PASSWORD_ENCODE_SET = " \"':;<=>@[]^`{}|/\\?#";
    static final String PATH_SEGMENT_ENCODE_SET = " \"<>^`{}|/\\?#";
    static final String PATH_SEGMENT_ENCODE_SET_URI = "[]";
    static final String QUERY_COMPONENT_ENCODE_SET = " \"'<>#&=";
    static final String QUERY_COMPONENT_ENCODE_SET_URI = "\\^`{|}";
    static final String QUERY_ENCODE_SET = " \"'<>#";
    static final String USERNAME_ENCODE_SET = " \"':;<=>@[]^`{}|/\\?#";
    private final String fragment;
    private final String host;
    private final String password;
    private final List<String> pathSegments;
    private final int port;
    private final List<String> queryNamesAndValues;
    private final String scheme;
    private final String url;
    private final String username;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.squareup.okhttp.HttpUrl$1 */
    /* loaded from: classes08-dex2jar.jar:com/squareup/okhttp/HttpUrl$1.class */
    public static /* synthetic */ class C02731 {
        static final /* synthetic */ int[] $SwitchMap$com$squareup$okhttp$HttpUrl$Builder$ParseResult = new int[Builder.ParseResult.values().length];

        /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:29:0x004d
            	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1166)
            	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:1022)
            	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:55)
            */
        static {
            /*
                com.squareup.okhttp.HttpUrl$Builder$ParseResult[] r0 = com.squareup.okhttp.HttpUrl.Builder.ParseResult.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.squareup.okhttp.HttpUrl.C02731.$SwitchMap$com$squareup$okhttp$HttpUrl$Builder$ParseResult = r0
                int[] r0 = com.squareup.okhttp.HttpUrl.C02731.$SwitchMap$com$squareup$okhttp$HttpUrl$Builder$ParseResult     // Catch: java.lang.NoSuchFieldError -> L41
                com.squareup.okhttp.HttpUrl$Builder$ParseResult r1 = com.squareup.okhttp.HttpUrl.Builder.ParseResult.SUCCESS     // Catch: java.lang.NoSuchFieldError -> L41
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L41
                r2 = 1
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L41
            L14:
                int[] r0 = com.squareup.okhttp.HttpUrl.C02731.$SwitchMap$com$squareup$okhttp$HttpUrl$Builder$ParseResult     // Catch: java.lang.NoSuchFieldError -> L41 java.lang.NoSuchFieldError -> L45
                com.squareup.okhttp.HttpUrl$Builder$ParseResult r1 = com.squareup.okhttp.HttpUrl.Builder.ParseResult.INVALID_HOST     // Catch: java.lang.NoSuchFieldError -> L45
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L45
                r2 = 2
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L45
            L1f:
                int[] r0 = com.squareup.okhttp.HttpUrl.C02731.$SwitchMap$com$squareup$okhttp$HttpUrl$Builder$ParseResult     // Catch: java.lang.NoSuchFieldError -> L45 java.lang.NoSuchFieldError -> L49
                com.squareup.okhttp.HttpUrl$Builder$ParseResult r1 = com.squareup.okhttp.HttpUrl.Builder.ParseResult.UNSUPPORTED_SCHEME     // Catch: java.lang.NoSuchFieldError -> L49
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L49
                r2 = 3
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L49
            L2a:
                int[] r0 = com.squareup.okhttp.HttpUrl.C02731.$SwitchMap$com$squareup$okhttp$HttpUrl$Builder$ParseResult     // Catch: java.lang.NoSuchFieldError -> L49 java.lang.NoSuchFieldError -> L4d
                com.squareup.okhttp.HttpUrl$Builder$ParseResult r1 = com.squareup.okhttp.HttpUrl.Builder.ParseResult.MISSING_SCHEME     // Catch: java.lang.NoSuchFieldError -> L4d
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L4d
                r2 = 4
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L4d
            L35:
                int[] r0 = com.squareup.okhttp.HttpUrl.C02731.$SwitchMap$com$squareup$okhttp$HttpUrl$Builder$ParseResult     // Catch: java.lang.NoSuchFieldError -> L4d java.lang.NoSuchFieldError -> L51
                com.squareup.okhttp.HttpUrl$Builder$ParseResult r1 = com.squareup.okhttp.HttpUrl.Builder.ParseResult.INVALID_PORT     // Catch: java.lang.NoSuchFieldError -> L51
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L51
                r2 = 5
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L51
                return
            L41:
                r4 = move-exception
                goto L14
            L45:
                r4 = move-exception
                goto L1f
            L49:
                r4 = move-exception
                goto L2a
            L4d:
                r4 = move-exception
                goto L35
            L51:
                r4 = move-exception
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.squareup.okhttp.HttpUrl.C02731.m3755clinit():void");
        }
    }

    /* loaded from: classes08-dex2jar.jar:com/squareup/okhttp/HttpUrl$Builder.class */
    public static final class Builder {
        String encodedFragment;
        List<String> encodedQueryNamesAndValues;
        String host;
        String scheme;
        String encodedUsername = "";
        String encodedPassword = "";
        int port = -1;
        final List<String> encodedPathSegments = new ArrayList();

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes08-dex2jar.jar:com/squareup/okhttp/HttpUrl$Builder$ParseResult.class */
        public enum ParseResult {
            SUCCESS,
            MISSING_SCHEME,
            UNSUPPORTED_SCHEME,
            INVALID_PORT,
            INVALID_HOST
        }

        public Builder() {
            this.encodedPathSegments.add("");
        }

        private static String canonicalizeHost(String str, int i, int i2) {
            String percentDecode = HttpUrl.percentDecode(str, i, i2, false);
            if (!percentDecode.startsWith("[") || !percentDecode.endsWith("]")) {
                return domainToAscii(percentDecode);
            }
            InetAddress decodeIpv6 = decodeIpv6(percentDecode, 1, percentDecode.length() - 1);
            if (decodeIpv6 == null) {
                return null;
            }
            byte[] address = decodeIpv6.getAddress();
            if (address.length == 16) {
                return inet6AddressToAscii(address);
            }
            throw new AssertionError();
        }

        private static boolean containsInvalidHostnameAsciiCodes(String str) {
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= str.length()) {
                    return false;
                }
                char charAt = str.charAt(i2);
                if (charAt <= 31 || charAt >= 127 || " #%/:?@[\\]".indexOf(charAt) != -1) {
                    return true;
                }
                i = i2 + 1;
            }
        }

        private static boolean decodeIpv4Suffix(String str, int i, int i2, byte[] bArr, int i3) {
            char charAt;
            int i4 = i3;
            int i5 = i;
            while (i5 < i2) {
                if (i4 == bArr.length) {
                    return false;
                }
                int i6 = i5;
                if (i4 != i3) {
                    if (str.charAt(i5) != '.') {
                        return false;
                    }
                    i6 = i5 + 1;
                }
                i5 = i6;
                int i7 = 0;
                while (i5 < i2 && (charAt = str.charAt(i5)) >= '0' && charAt <= '9') {
                    if (i7 == 0 && i6 != i5) {
                        return false;
                    }
                    i7 = ((i7 * 10) + charAt) - 48;
                    if (i7 > 255) {
                        return false;
                    }
                    i5++;
                }
                if (i5 - i6 == 0) {
                    return false;
                }
                bArr[i4] = (byte) i7;
                i4++;
            }
            return i4 == i3 + 4;
        }

        /* JADX WARN: Code restructure failed: missing block: B:36:0x0122, code lost:            if (r12 == r0.length) goto L65;     */
        /* JADX WARN: Code restructure failed: missing block: B:38:0x0128, code lost:            if (r14 != (-1)) goto L58;     */
        /* JADX WARN: Code restructure failed: missing block: B:39:0x012b, code lost:            return null;     */
        /* JADX WARN: Code restructure failed: missing block: B:40:0x012d, code lost:            r0 = r0.length;        r0 = r12 - r14;        java.lang.System.arraycopy(r0, r14, r0, r0 - r0, r0);        java.util.Arrays.fill(r0, r14, (r0.length - r12) + r14, (byte) 0);     */
        /* JADX WARN: Code restructure failed: missing block: B:43:0x015c, code lost:            return java.net.InetAddress.getByAddress(r0);     */
        /* JADX WARN: Code restructure failed: missing block: B:47:0x0164, code lost:            throw new java.lang.AssertionError();     */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private static java.net.InetAddress decodeIpv6(java.lang.String r7, int r8, int r9) {
            /*
                Method dump skipped, instructions count: 361
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.squareup.okhttp.HttpUrl.Builder.decodeIpv6(java.lang.String, int, int):java.net.InetAddress");
        }

        private static String domainToAscii(String str) {
            try {
                String lowerCase = IDN.toASCII(str).toLowerCase(Locale.US);
                if (lowerCase.isEmpty()) {
                    return null;
                }
                if (containsInvalidHostnameAsciiCodes(lowerCase)) {
                    return null;
                }
                return lowerCase;
            } catch (IllegalArgumentException e) {
                return null;
            }
        }

        private static String inet6AddressToAscii(byte[] bArr) {
            int i;
            int i2;
            int i3 = 0;
            int i4 = -1;
            int i5 = 0;
            while (true) {
                i = i5;
                if (i3 >= bArr.length) {
                    break;
                }
                int i6 = i3;
                while (true) {
                    i2 = i6;
                    if (i2 >= 16 || bArr[i2] != 0 || bArr[i2 + 1] != 0) {
                        break;
                    }
                    i6 = i2 + 2;
                }
                int i7 = i2 - i3;
                int i8 = i;
                if (i7 > i) {
                    i8 = i7;
                    i4 = i3;
                }
                i3 = i2 + 2;
                i5 = i8;
            }
            Buffer buffer = new Buffer();
            int i9 = 0;
            while (i9 < bArr.length) {
                if (i9 == i4) {
                    buffer.writeByte(58);
                    int i10 = i9 + i;
                    i9 = i10;
                    if (i10 == 16) {
                        buffer.writeByte(58);
                        i9 = i10;
                    }
                } else {
                    if (i9 > 0) {
                        buffer.writeByte(58);
                    }
                    buffer.writeHexadecimalUnsignedLong(((bArr[i9] & 255) << 8) | (bArr[i9 + 1] & 255));
                    i9 += 2;
                }
            }
            return buffer.readUtf8();
        }

        private boolean isDot(String str) {
            return str.equals(".") || str.equalsIgnoreCase("%2e");
        }

        private boolean isDotDot(String str) {
            return str.equals("..") || str.equalsIgnoreCase("%2e.") || str.equalsIgnoreCase(".%2e") || str.equalsIgnoreCase("%2e%2e");
        }

        private static int parsePort(String str, int i, int i2) {
            try {
                int parseInt = Integer.parseInt(HttpUrl.canonicalize(str, i, i2, "", false, false, true));
                if (parseInt <= 0 || parseInt > 65535) {
                    return -1;
                }
                return parseInt;
            } catch (NumberFormatException e) {
                return -1;
            }
        }

        private void pop() {
            List<String> list = this.encodedPathSegments;
            if (!list.remove(list.size() - 1).isEmpty() || this.encodedPathSegments.isEmpty()) {
                this.encodedPathSegments.add("");
            } else {
                List<String> list2 = this.encodedPathSegments;
                list2.set(list2.size() - 1, "");
            }
        }

        private static int portColonOffset(String str, int i, int i2) {
            int i3;
            while (i < i2) {
                char charAt = str.charAt(i);
                if (charAt == ':') {
                    return i;
                }
                int i4 = i;
                if (charAt != '[') {
                    i3 = i;
                    i = i3 + 1;
                }
                while (true) {
                    int i5 = i4 + 1;
                    i3 = i5;
                    if (i5 < i2) {
                        i4 = i5;
                        if (str.charAt(i5) == ']') {
                            i3 = i5;
                            break;
                        }
                    }
                }
                i = i3 + 1;
            }
            return i2;
        }

        private void push(String str, int i, int i2, boolean z, boolean z2) {
            String canonicalize = HttpUrl.canonicalize(str, i, i2, HttpUrl.PATH_SEGMENT_ENCODE_SET, z2, false, true);
            if (isDot(canonicalize)) {
                return;
            }
            if (isDotDot(canonicalize)) {
                pop();
                return;
            }
            List<String> list = this.encodedPathSegments;
            if (list.get(list.size() - 1).isEmpty()) {
                List<String> list2 = this.encodedPathSegments;
                list2.set(list2.size() - 1, canonicalize);
            } else {
                this.encodedPathSegments.add(canonicalize);
            }
            if (z) {
                this.encodedPathSegments.add("");
            }
        }

        private void removeAllCanonicalQueryParameters(String str) {
            int size = this.encodedQueryNamesAndValues.size();
            while (true) {
                int i = size - 2;
                if (i < 0) {
                    return;
                }
                if (str.equals(this.encodedQueryNamesAndValues.get(i))) {
                    this.encodedQueryNamesAndValues.remove(i + 1);
                    this.encodedQueryNamesAndValues.remove(i);
                    if (this.encodedQueryNamesAndValues.isEmpty()) {
                        this.encodedQueryNamesAndValues = null;
                        return;
                    }
                }
                size = i;
            }
        }

        private void resolvePath(String str, int i, int i2) {
            if (i == i2) {
                return;
            }
            char charAt = str.charAt(i);
            if (charAt == '/' || charAt == '\\') {
                this.encodedPathSegments.clear();
                this.encodedPathSegments.add("");
                i++;
            } else {
                List<String> list = this.encodedPathSegments;
                list.set(list.size() - 1, "");
            }
            while (i < i2) {
                int delimiterOffset = HttpUrl.delimiterOffset(str, i, i2, "/\\");
                boolean z = delimiterOffset < i2;
                push(str, i, delimiterOffset, z, true);
                i = delimiterOffset;
                if (z) {
                    i = delimiterOffset + 1;
                }
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:8:0x001d, code lost:            if (r0 > 'z') goto L10;     */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private static int schemeDelimiterOffset(java.lang.String r3, int r4, int r5) {
            /*
                r0 = r5
                r1 = r4
                int r0 = r0 - r1
                r1 = 2
                if (r0 >= r1) goto L9
                r0 = -1
                return r0
            L9:
                r0 = r3
                r1 = r4
                char r0 = r0.charAt(r1)
                r7 = r0
                r0 = r7
                r1 = 97
                if (r0 < r1) goto L20
                r0 = r4
                r6 = r0
                r0 = r7
                r1 = 122(0x7a, float:1.71E-43)
                if (r0 <= r1) goto L32
            L20:
                r0 = r7
                r1 = 65
                if (r0 < r1) goto L99
                r0 = r4
                r6 = r0
                r0 = r7
                r1 = 90
                if (r0 <= r1) goto L32
                r0 = -1
                return r0
            L32:
                r0 = r6
                r1 = 1
                int r0 = r0 + r1
                r4 = r0
                r0 = r4
                r1 = r5
                if (r0 >= r1) goto L99
                r0 = r3
                r1 = r4
                char r0 = r0.charAt(r1)
                r7 = r0
                r0 = r7
                r1 = 97
                if (r0 < r1) goto L52
                r0 = r4
                r6 = r0
                r0 = r7
                r1 = 122(0x7a, float:1.71E-43)
                if (r0 <= r1) goto L32
            L52:
                r0 = r7
                r1 = 65
                if (r0 < r1) goto L62
                r0 = r4
                r6 = r0
                r0 = r7
                r1 = 90
                if (r0 <= r1) goto L32
            L62:
                r0 = r7
                r1 = 48
                if (r0 < r1) goto L72
                r0 = r4
                r6 = r0
                r0 = r7
                r1 = 57
                if (r0 <= r1) goto L32
            L72:
                r0 = r4
                r6 = r0
                r0 = r7
                r1 = 43
                if (r0 == r1) goto L32
                r0 = r4
                r6 = r0
                r0 = r7
                r1 = 45
                if (r0 == r1) goto L32
                r0 = r7
                r1 = 46
                if (r0 != r1) goto L90
                r0 = r4
                r6 = r0
                goto L32
            L90:
                r0 = r7
                r1 = 58
                if (r0 != r1) goto L99
                r0 = r4
                return r0
            L99:
                r0 = -1
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.squareup.okhttp.HttpUrl.Builder.schemeDelimiterOffset(java.lang.String, int, int):int");
        }

        private int skipLeadingAsciiWhitespace(String str, int i, int i2) {
            while (i < i2) {
                char charAt = str.charAt(i);
                if (charAt != '\t' && charAt != '\n' && charAt != '\f' && charAt != '\r' && charAt != ' ') {
                    return i;
                }
                i++;
            }
            return i2;
        }

        private int skipTrailingAsciiWhitespace(String str, int i, int i2) {
            while (true) {
                i2--;
                if (i2 < i) {
                    return i;
                }
                char charAt = str.charAt(i2);
                if (charAt != '\t' && charAt != '\n' && charAt != '\f' && charAt != '\r' && charAt != ' ') {
                    return i2 + 1;
                }
            }
        }

        private static int slashCount(String str, int i, int i2) {
            char charAt;
            int i3 = 0;
            while (i < i2 && ((charAt = str.charAt(i)) == '\\' || charAt == '/')) {
                i3++;
                i++;
            }
            return i3;
        }

        public Builder addEncodedPathSegment(String str) {
            if (str == null) {
                throw new IllegalArgumentException("encodedPathSegment == null");
            }
            push(str, 0, str.length(), false, true);
            return this;
        }

        public Builder addEncodedQueryParameter(String str, String str2) {
            if (str == null) {
                throw new IllegalArgumentException("encodedName == null");
            }
            if (this.encodedQueryNamesAndValues == null) {
                this.encodedQueryNamesAndValues = new ArrayList();
            }
            this.encodedQueryNamesAndValues.add(HttpUrl.canonicalize(str, HttpUrl.QUERY_COMPONENT_ENCODE_SET, true, true, true));
            this.encodedQueryNamesAndValues.add(str2 != null ? HttpUrl.canonicalize(str2, HttpUrl.QUERY_COMPONENT_ENCODE_SET, true, true, true) : null);
            return this;
        }

        public Builder addPathSegment(String str) {
            if (str == null) {
                throw new IllegalArgumentException("pathSegment == null");
            }
            push(str, 0, str.length(), false, false);
            return this;
        }

        public Builder addQueryParameter(String str, String str2) {
            if (str == null) {
                throw new IllegalArgumentException("name == null");
            }
            if (this.encodedQueryNamesAndValues == null) {
                this.encodedQueryNamesAndValues = new ArrayList();
            }
            this.encodedQueryNamesAndValues.add(HttpUrl.canonicalize(str, HttpUrl.QUERY_COMPONENT_ENCODE_SET, false, true, true));
            this.encodedQueryNamesAndValues.add(str2 != null ? HttpUrl.canonicalize(str2, HttpUrl.QUERY_COMPONENT_ENCODE_SET, false, true, true) : null);
            return this;
        }

        public HttpUrl build() {
            if (this.scheme == null) {
                throw new IllegalStateException("scheme == null");
            }
            if (this.host != null) {
                return new HttpUrl(this, null);
            }
            throw new IllegalStateException("host == null");
        }

        int effectivePort() {
            int i = this.port;
            return i != -1 ? i : HttpUrl.defaultPort(this.scheme);
        }

        public Builder encodedFragment(String str) {
            this.encodedFragment = str != null ? HttpUrl.canonicalize(str, "", true, false, false) : null;
            return this;
        }

        public Builder encodedPassword(String str) {
            if (str == null) {
                throw new IllegalArgumentException("encodedPassword == null");
            }
            this.encodedPassword = HttpUrl.canonicalize(str, " \"':;<=>@[]^`{}|/\\?#", true, false, true);
            return this;
        }

        public Builder encodedPath(String str) {
            if (str == null) {
                throw new IllegalArgumentException("encodedPath == null");
            }
            if (str.startsWith("/")) {
                resolvePath(str, 0, str.length());
                return this;
            }
            throw new IllegalArgumentException("unexpected encodedPath: " + str);
        }

        public Builder encodedQuery(String str) {
            this.encodedQueryNamesAndValues = str != null ? HttpUrl.queryStringToNamesAndValues(HttpUrl.canonicalize(str, HttpUrl.QUERY_ENCODE_SET, true, true, true)) : null;
            return this;
        }

        public Builder encodedUsername(String str) {
            if (str == null) {
                throw new IllegalArgumentException("encodedUsername == null");
            }
            this.encodedUsername = HttpUrl.canonicalize(str, " \"':;<=>@[]^`{}|/\\?#", true, false, true);
            return this;
        }

        public Builder fragment(String str) {
            this.encodedFragment = str != null ? HttpUrl.canonicalize(str, "", false, false, false) : null;
            return this;
        }

        public Builder host(String str) {
            if (str == null) {
                throw new IllegalArgumentException("host == null");
            }
            String canonicalizeHost = canonicalizeHost(str, 0, str.length());
            if (canonicalizeHost != null) {
                this.host = canonicalizeHost;
                return this;
            }
            throw new IllegalArgumentException("unexpected host: " + str);
        }

        /* JADX WARN: Code restructure failed: missing block: B:15:0x00d7, code lost:            if (r11.charAt(r12) == '#') goto L26;     */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        com.squareup.okhttp.HttpUrl.Builder.ParseResult parse(com.squareup.okhttp.HttpUrl r10, java.lang.String r11) {
            /*
                Method dump skipped, instructions count: 719
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.squareup.okhttp.HttpUrl.Builder.parse(com.squareup.okhttp.HttpUrl, java.lang.String):com.squareup.okhttp.HttpUrl$Builder$ParseResult");
        }

        public Builder password(String str) {
            if (str == null) {
                throw new IllegalArgumentException("password == null");
            }
            this.encodedPassword = HttpUrl.canonicalize(str, " \"':;<=>@[]^`{}|/\\?#", false, false, true);
            return this;
        }

        public Builder port(int i) {
            if (i > 0 && i <= 65535) {
                this.port = i;
                return this;
            }
            throw new IllegalArgumentException("unexpected port: " + i);
        }

        public Builder query(String str) {
            this.encodedQueryNamesAndValues = str != null ? HttpUrl.queryStringToNamesAndValues(HttpUrl.canonicalize(str, HttpUrl.QUERY_ENCODE_SET, false, true, true)) : null;
            return this;
        }

        Builder reencodeForUri() {
            int size = this.encodedPathSegments.size();
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= size) {
                    break;
                }
                this.encodedPathSegments.set(i2, HttpUrl.canonicalize(this.encodedPathSegments.get(i2), HttpUrl.PATH_SEGMENT_ENCODE_SET_URI, true, false, true));
                i = i2 + 1;
            }
            List<String> list = this.encodedQueryNamesAndValues;
            if (list != null) {
                int size2 = list.size();
                int i3 = 0;
                while (true) {
                    int i4 = i3;
                    if (i4 >= size2) {
                        break;
                    }
                    String str = this.encodedQueryNamesAndValues.get(i4);
                    if (str != null) {
                        this.encodedQueryNamesAndValues.set(i4, HttpUrl.canonicalize(str, HttpUrl.QUERY_COMPONENT_ENCODE_SET_URI, true, true, true));
                    }
                    i3 = i4 + 1;
                }
            }
            String str2 = this.encodedFragment;
            if (str2 != null) {
                this.encodedFragment = HttpUrl.canonicalize(str2, HttpUrl.FRAGMENT_ENCODE_SET_URI, true, false, false);
            }
            return this;
        }

        public Builder removeAllEncodedQueryParameters(String str) {
            if (str == null) {
                throw new IllegalArgumentException("encodedName == null");
            }
            if (this.encodedQueryNamesAndValues == null) {
                return this;
            }
            removeAllCanonicalQueryParameters(HttpUrl.canonicalize(str, HttpUrl.QUERY_COMPONENT_ENCODE_SET, true, true, true));
            return this;
        }

        public Builder removeAllQueryParameters(String str) {
            if (str == null) {
                throw new IllegalArgumentException("name == null");
            }
            if (this.encodedQueryNamesAndValues == null) {
                return this;
            }
            removeAllCanonicalQueryParameters(HttpUrl.canonicalize(str, HttpUrl.QUERY_COMPONENT_ENCODE_SET, false, true, true));
            return this;
        }

        public Builder removePathSegment(int i) {
            this.encodedPathSegments.remove(i);
            if (this.encodedPathSegments.isEmpty()) {
                this.encodedPathSegments.add("");
            }
            return this;
        }

        public Builder scheme(String str) {
            if (str == null) {
                throw new IllegalArgumentException("scheme == null");
            }
            if (str.equalsIgnoreCase("http")) {
                this.scheme = "http";
                return this;
            }
            if (str.equalsIgnoreCase("https")) {
                this.scheme = "https";
                return this;
            }
            throw new IllegalArgumentException("unexpected scheme: " + str);
        }

        public Builder setEncodedPathSegment(int i, String str) {
            if (str == null) {
                throw new IllegalArgumentException("encodedPathSegment == null");
            }
            String canonicalize = HttpUrl.canonicalize(str, 0, str.length(), HttpUrl.PATH_SEGMENT_ENCODE_SET, true, false, true);
            this.encodedPathSegments.set(i, canonicalize);
            if (!isDot(canonicalize) && !isDotDot(canonicalize)) {
                return this;
            }
            throw new IllegalArgumentException("unexpected path segment: " + str);
        }

        public Builder setEncodedQueryParameter(String str, String str2) {
            removeAllEncodedQueryParameters(str);
            addEncodedQueryParameter(str, str2);
            return this;
        }

        public Builder setPathSegment(int i, String str) {
            if (str == null) {
                throw new IllegalArgumentException("pathSegment == null");
            }
            String canonicalize = HttpUrl.canonicalize(str, 0, str.length(), HttpUrl.PATH_SEGMENT_ENCODE_SET, false, false, true);
            if (!isDot(canonicalize) && !isDotDot(canonicalize)) {
                this.encodedPathSegments.set(i, canonicalize);
                return this;
            }
            throw new IllegalArgumentException("unexpected path segment: " + str);
        }

        public Builder setQueryParameter(String str, String str2) {
            removeAllQueryParameters(str);
            addQueryParameter(str, str2);
            return this;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.scheme);
            sb.append("://");
            if (!this.encodedUsername.isEmpty() || !this.encodedPassword.isEmpty()) {
                sb.append(this.encodedUsername);
                if (!this.encodedPassword.isEmpty()) {
                    sb.append(':');
                    sb.append(this.encodedPassword);
                }
                sb.append('@');
            }
            if (this.host.indexOf(58) != -1) {
                sb.append('[');
                sb.append(this.host);
                sb.append(']');
            } else {
                sb.append(this.host);
            }
            int effectivePort = effectivePort();
            if (effectivePort != HttpUrl.defaultPort(this.scheme)) {
                sb.append(':');
                sb.append(effectivePort);
            }
            HttpUrl.pathSegmentsToString(sb, this.encodedPathSegments);
            if (this.encodedQueryNamesAndValues != null) {
                sb.append('?');
                HttpUrl.namesAndValuesToQueryString(sb, this.encodedQueryNamesAndValues);
            }
            if (this.encodedFragment != null) {
                sb.append('#');
                sb.append(this.encodedFragment);
            }
            return sb.toString();
        }

        public Builder username(String str) {
            if (str == null) {
                throw new IllegalArgumentException("username == null");
            }
            this.encodedUsername = HttpUrl.canonicalize(str, " \"':;<=>@[]^`{}|/\\?#", false, false, true);
            return this;
        }
    }

    private HttpUrl(Builder builder) {
        this.scheme = builder.scheme;
        this.username = percentDecode(builder.encodedUsername, false);
        this.password = percentDecode(builder.encodedPassword, false);
        this.host = builder.host;
        this.port = builder.effectivePort();
        this.pathSegments = percentDecode(builder.encodedPathSegments, false);
        this.queryNamesAndValues = builder.encodedQueryNamesAndValues != null ? percentDecode(builder.encodedQueryNamesAndValues, true) : null;
        this.fragment = builder.encodedFragment != null ? percentDecode(builder.encodedFragment, false) : null;
        this.url = builder.toString();
    }

    /* synthetic */ HttpUrl(Builder builder, C02731 c02731) {
        this(builder);
    }

    static String canonicalize(String str, int i, int i2, String str2, boolean z, boolean z2, boolean z3) {
        int i3;
        int i4 = i;
        while (true) {
            i3 = i4;
            if (i3 >= i2) {
                return str.substring(i, i2);
            }
            int codePointAt = str.codePointAt(i3);
            if (codePointAt < 32 || codePointAt == 127 || ((codePointAt >= 128 && z3) || str2.indexOf(codePointAt) != -1 || ((codePointAt == 37 && !z) || (codePointAt == 43 && z2)))) {
                break;
            }
            i4 = i3 + Character.charCount(codePointAt);
        }
        Buffer buffer = new Buffer();
        buffer.writeUtf8(str, i, i3);
        canonicalize(buffer, str, i3, i2, str2, z, z2, z3);
        return buffer.readUtf8();
    }

    static String canonicalize(String str, String str2, boolean z, boolean z2, boolean z3) {
        return canonicalize(str, 0, str.length(), str2, z, z2, z3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void canonicalize(Buffer buffer, String str, int i, int i2, String str2, boolean z, boolean z2, boolean z3) {
        Buffer buffer2;
        Buffer buffer3 = null;
        while (true) {
            Buffer buffer4 = buffer3;
            if (i >= i2) {
                return;
            }
            int codePointAt = str.codePointAt(i);
            if (z) {
                buffer2 = buffer4;
                if (codePointAt != 9) {
                    buffer2 = buffer4;
                    if (codePointAt != 10) {
                        buffer2 = buffer4;
                        if (codePointAt != 12) {
                            if (codePointAt == 13) {
                                buffer2 = buffer4;
                            }
                        }
                    }
                }
                i += Character.charCount(codePointAt);
                buffer3 = buffer2;
            }
            if (codePointAt == 43 && z2) {
                buffer.writeUtf8(z ? "+" : "%2B");
                buffer2 = buffer4;
            } else if (codePointAt < 32 || codePointAt == 127 || ((codePointAt >= 128 && z3) || str2.indexOf(codePointAt) != -1 || (codePointAt == 37 && !z))) {
                Buffer buffer5 = buffer4;
                if (buffer4 == null) {
                    buffer5 = new Buffer();
                }
                buffer5.writeUtf8CodePoint(codePointAt);
                while (true) {
                    buffer2 = buffer5;
                    if (!buffer5.exhausted()) {
                        int readByte = buffer5.readByte() & 255;
                        buffer.writeByte(37);
                        buffer.writeByte(HEX_DIGITS[(readByte >> 4) & 15]);
                        buffer.writeByte(HEX_DIGITS[readByte & 15]);
                    }
                }
            } else {
                buffer.writeUtf8CodePoint(codePointAt);
                buffer2 = buffer4;
            }
            i += Character.charCount(codePointAt);
            buffer3 = buffer2;
        }
    }

    static int decodeHexDigit(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        char c2 = 'a';
        if (c < 'a' || c > 'f') {
            c2 = 'A';
            if (c < 'A' || c > 'F') {
                return -1;
            }
        }
        return (c - c2) + 10;
    }

    public static int defaultPort(String str) {
        if (str.equals("http")) {
            return 80;
        }
        return str.equals("https") ? 443 : -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int delimiterOffset(String str, int i, int i2, String str2) {
        while (i < i2) {
            if (str2.indexOf(str.charAt(i)) != -1) {
                return i;
            }
            i++;
        }
        return i2;
    }

    public static HttpUrl get(URI uri) {
        return parse(uri.toString());
    }

    public static HttpUrl get(URL url) {
        return parse(url.toString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static HttpUrl getChecked(String str) throws MalformedURLException, UnknownHostException {
        Builder builder = new Builder();
        Builder.ParseResult parse = builder.parse(null, str);
        int i = C02731.$SwitchMap$com$squareup$okhttp$HttpUrl$Builder$ParseResult[parse.ordinal()];
        if (i == 1) {
            return builder.build();
        }
        if (i == 2) {
            throw new UnknownHostException("Invalid host: " + str);
        }
        throw new MalformedURLException("Invalid URL: " + parse + " for " + str);
    }

    static void namesAndValuesToQueryString(StringBuilder sb, List<String> list) {
        int size = list.size();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= size) {
                return;
            }
            String str = list.get(i2);
            String str2 = list.get(i2 + 1);
            if (i2 > 0) {
                sb.append('&');
            }
            sb.append(str);
            if (str2 != null) {
                sb.append('=');
                sb.append(str2);
            }
            i = i2 + 2;
        }
    }

    public static HttpUrl parse(String str) {
        Builder builder = new Builder();
        HttpUrl httpUrl = null;
        if (builder.parse(null, str) == Builder.ParseResult.SUCCESS) {
            httpUrl = builder.build();
        }
        return httpUrl;
    }

    static void pathSegmentsToString(StringBuilder sb, List<String> list) {
        int size = list.size();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= size) {
                return;
            }
            sb.append('/');
            sb.append(list.get(i2));
            i = i2 + 1;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x002f, code lost:            r0 = new okio.Buffer();        r0.writeUtf8(r6, r7, r10);        percentDecode(r0, r6, r10, r8, r9);     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0051, code lost:            return r0.readUtf8();     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static java.lang.String percentDecode(java.lang.String r6, int r7, int r8, boolean r9) {
        /*
            r0 = r7
            r10 = r0
        L3:
            r0 = r10
            r1 = r8
            if (r0 >= r1) goto L52
            r0 = r6
            r1 = r10
            char r0 = r0.charAt(r1)
            r11 = r0
            r0 = r11
            r1 = 37
            if (r0 == r1) goto L2f
            r0 = r11
            r1 = 43
            if (r0 != r1) goto L26
            r0 = r9
            if (r0 == 0) goto L26
            goto L2f
        L26:
            r0 = r10
            r1 = 1
            int r0 = r0 + r1
            r10 = r0
            goto L3
        L2f:
            okio.Buffer r0 = new okio.Buffer
            r1 = r0
            r1.<init>()
            r12 = r0
            r0 = r12
            r1 = r6
            r2 = r7
            r3 = r10
            okio.Buffer r0 = r0.writeUtf8(r1, r2, r3)
            r0 = r12
            r1 = r6
            r2 = r10
            r3 = r8
            r4 = r9
            percentDecode(r0, r1, r2, r3, r4)
            r0 = r12
            java.lang.String r0 = r0.readUtf8()
            return r0
        L52:
            r0 = r6
            r1 = r7
            r2 = r8
            java.lang.String r0 = r0.substring(r1, r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.okhttp.HttpUrl.percentDecode(java.lang.String, int, int, boolean):java.lang.String");
    }

    static String percentDecode(String str, boolean z) {
        return percentDecode(str, 0, str.length(), z);
    }

    private List<String> percentDecode(List<String> list, boolean z) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String next = it.next();
            arrayList.add(next != null ? percentDecode(next, z) : null);
        }
        return Collections.unmodifiableList(arrayList);
    }

    static void percentDecode(Buffer buffer, String str, int i, int i2, boolean z) {
        int i3;
        while (i < i2) {
            int codePointAt = str.codePointAt(i);
            if (codePointAt != 37 || (i3 = i + 2) >= i2) {
                if (codePointAt == 43 && z) {
                    buffer.writeByte(32);
                }
                buffer.writeUtf8CodePoint(codePointAt);
            } else {
                int decodeHexDigit = decodeHexDigit(str.charAt(i + 1));
                int decodeHexDigit2 = decodeHexDigit(str.charAt(i3));
                if (decodeHexDigit != -1 && decodeHexDigit2 != -1) {
                    buffer.writeByte((decodeHexDigit << 4) + decodeHexDigit2);
                    i = i3;
                }
                buffer.writeUtf8CodePoint(codePointAt);
            }
            i += Character.charCount(codePointAt);
        }
    }

    static List<String> queryStringToNamesAndValues(String str) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 > str.length()) {
                return arrayList;
            }
            int indexOf = str.indexOf(38, i2);
            int i3 = indexOf;
            if (indexOf == -1) {
                i3 = str.length();
            }
            int indexOf2 = str.indexOf(61, i2);
            if (indexOf2 == -1 || indexOf2 > i3) {
                arrayList.add(str.substring(i2, i3));
                arrayList.add(null);
            } else {
                arrayList.add(str.substring(i2, indexOf2));
                arrayList.add(str.substring(indexOf2 + 1, i3));
            }
            i = i3 + 1;
        }
    }

    public String encodedFragment() {
        if (this.fragment == null) {
            return null;
        }
        return this.url.substring(this.url.indexOf(35) + 1);
    }

    public String encodedPassword() {
        if (this.password.isEmpty()) {
            return "";
        }
        int indexOf = this.url.indexOf(58, this.scheme.length() + 3);
        return this.url.substring(indexOf + 1, this.url.indexOf(64));
    }

    public String encodedPath() {
        int indexOf = this.url.indexOf(47, this.scheme.length() + 3);
        String str = this.url;
        return this.url.substring(indexOf, delimiterOffset(str, indexOf, str.length(), "?#"));
    }

    public List<String> encodedPathSegments() {
        int indexOf = this.url.indexOf(47, this.scheme.length() + 3);
        String str = this.url;
        int delimiterOffset = delimiterOffset(str, indexOf, str.length(), "?#");
        ArrayList arrayList = new ArrayList();
        while (indexOf < delimiterOffset) {
            int i = indexOf + 1;
            indexOf = delimiterOffset(this.url, i, delimiterOffset, "/");
            arrayList.add(this.url.substring(i, indexOf));
        }
        return arrayList;
    }

    public String encodedQuery() {
        if (this.queryNamesAndValues == null) {
            return null;
        }
        int indexOf = this.url.indexOf(63) + 1;
        String str = this.url;
        return this.url.substring(indexOf, delimiterOffset(str, indexOf + 1, str.length(), "#"));
    }

    public String encodedUsername() {
        if (this.username.isEmpty()) {
            return "";
        }
        int length = this.scheme.length() + 3;
        String str = this.url;
        return this.url.substring(length, delimiterOffset(str, length, str.length(), ":@"));
    }

    public boolean equals(Object obj) {
        return (obj instanceof HttpUrl) && ((HttpUrl) obj).url.equals(this.url);
    }

    public String fragment() {
        return this.fragment;
    }

    public int hashCode() {
        return this.url.hashCode();
    }

    public String host() {
        return this.host;
    }

    public boolean isHttps() {
        return this.scheme.equals("https");
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.scheme = this.scheme;
        builder.encodedUsername = encodedUsername();
        builder.encodedPassword = encodedPassword();
        builder.host = this.host;
        builder.port = this.port != defaultPort(this.scheme) ? this.port : -1;
        builder.encodedPathSegments.clear();
        builder.encodedPathSegments.addAll(encodedPathSegments());
        builder.encodedQuery(encodedQuery());
        builder.encodedFragment = encodedFragment();
        return builder;
    }

    public String password() {
        return this.password;
    }

    public List<String> pathSegments() {
        return this.pathSegments;
    }

    public int pathSize() {
        return this.pathSegments.size();
    }

    public int port() {
        return this.port;
    }

    public String query() {
        if (this.queryNamesAndValues == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        namesAndValuesToQueryString(sb, this.queryNamesAndValues);
        return sb.toString();
    }

    public String queryParameter(String str) {
        List<String> list = this.queryNamesAndValues;
        if (list == null) {
            return null;
        }
        int size = list.size();
        for (int i = 0; i < size; i += 2) {
            if (str.equals(this.queryNamesAndValues.get(i))) {
                return this.queryNamesAndValues.get(i + 1);
            }
        }
        return null;
    }

    public String queryParameterName(int i) {
        return this.queryNamesAndValues.get(i * 2);
    }

    public Set<String> queryParameterNames() {
        if (this.queryNamesAndValues == null) {
            return Collections.emptySet();
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        int size = this.queryNamesAndValues.size();
        for (int i = 0; i < size; i += 2) {
            linkedHashSet.add(this.queryNamesAndValues.get(i));
        }
        return Collections.unmodifiableSet(linkedHashSet);
    }

    public String queryParameterValue(int i) {
        return this.queryNamesAndValues.get((i * 2) + 1);
    }

    public List<String> queryParameterValues(String str) {
        if (this.queryNamesAndValues == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        int size = this.queryNamesAndValues.size();
        for (int i = 0; i < size; i += 2) {
            if (str.equals(this.queryNamesAndValues.get(i))) {
                arrayList.add(this.queryNamesAndValues.get(i + 1));
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    public int querySize() {
        List<String> list = this.queryNamesAndValues;
        if (list != null) {
            return list.size() / 2;
        }
        return 0;
    }

    public HttpUrl resolve(String str) {
        Builder builder = new Builder();
        if (builder.parse(this, str) == Builder.ParseResult.SUCCESS) {
            return builder.build();
        }
        return null;
    }

    public String scheme() {
        return this.scheme;
    }

    public String toString() {
        return this.url;
    }

    public URI uri() {
        try {
            return new URI(newBuilder().reencodeForUri().toString());
        } catch (URISyntaxException e) {
            throw new IllegalStateException("not valid as a java.net.URI: " + this.url);
        }
    }

    public URL url() {
        try {
            return new URL(this.url);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public String username() {
        return this.username;
    }
}
