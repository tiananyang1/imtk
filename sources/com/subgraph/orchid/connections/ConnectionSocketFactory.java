package com.subgraph.orchid.connections;

import com.subgraph.orchid.TorException;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/connections/ConnectionSocketFactory.class */
public class ConnectionSocketFactory {
    private final SSLSocketFactory socketFactory = createSSLContext().getSocketFactory();
    static final String[] V1_CIPHERS_ONLY = {"TLS_DHE_RSA_WITH_AES_256_CBC_SHA", "TLS_DHE_RSA_WITH_AES_128_CBC_SHA", "SSL_DHE_RSA_WITH_3DES_EDE_CBC_SHA"};
    private static final String[] MANDATORY_CIPHERS = {"TLS_DHE_RSA_WITH_AES_256_CBC_SHA", "TLS_DHE_RSA_WITH_AES_128_CBC_SHA", "SSL_DHE_RSA_WITH_3DES_EDE_CBC_SHA", "SSL_DHE_DSS_WITH_3DES_EDE_CBC_SHA"};
    private static final TrustManager[] NULL_TRUST = {new X509TrustManager() { // from class: com.subgraph.orchid.connections.ConnectionSocketFactory.1
        private final X509Certificate[] empty = new X509Certificate[0];

        @Override // javax.net.ssl.X509TrustManager
        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        }

        @Override // javax.net.ssl.X509TrustManager
        public X509Certificate[] getAcceptedIssuers() {
            return this.empty;
        }
    }};

    private static SSLContext createSSLContext() {
        System.setProperty("sun.security.ssl.allowUnsafeRenegotiation", "true");
        try {
            SSLContext sSLContext = SSLContext.getInstance("TLS");
            sSLContext.init(null, NULL_TRUST, null);
            return sSLContext;
        } catch (KeyManagementException e) {
            throw new TorException(e);
        } catch (NoSuchAlgorithmException e2) {
            throw new TorException(e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SSLSocket createSocket() {
        try {
            SSLSocket sSLSocket = (SSLSocket) this.socketFactory.createSocket();
            sSLSocket.setEnabledCipherSuites(MANDATORY_CIPHERS);
            sSLSocket.setUseClientMode(true);
            return sSLSocket;
        } catch (IOException e) {
            throw new TorException(e);
        }
    }
}
