package com.squareup.okhttp;

/* loaded from: classes08-dex2jar.jar:com/squareup/okhttp/TlsVersion.class */
public enum TlsVersion {
    TLS_1_2("TLSv1.2"),
    TLS_1_1("TLSv1.1"),
    TLS_1_0("TLSv1"),
    SSL_3_0("SSLv3");

    final String javaName;

    TlsVersion(String str) {
        this.javaName = str;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Multi-variable type inference failed */
    public static TlsVersion forJavaName(String str) {
        boolean z;
        switch (str.hashCode()) {
            case -503070503:
                if (str.equals("TLSv1.1")) {
                    z = true;
                    break;
                }
                z = -1;
                break;
            case -503070502:
                if (str.equals("TLSv1.2")) {
                    z = false;
                    break;
                }
                z = -1;
                break;
            case 79201641:
                if (str.equals("SSLv3")) {
                    z = 3;
                    break;
                }
                z = -1;
                break;
            case 79923350:
                if (str.equals("TLSv1")) {
                    z = 2;
                    break;
                }
                z = -1;
                break;
            default:
                z = -1;
                break;
        }
        if (!z) {
            return TLS_1_2;
        }
        if (z) {
            return TLS_1_1;
        }
        if (z == 2) {
            return TLS_1_0;
        }
        if (z == 3) {
            return SSL_3_0;
        }
        throw new IllegalArgumentException("Unexpected TLS version: " + str);
    }

    public String javaName() {
        return this.javaName;
    }
}
