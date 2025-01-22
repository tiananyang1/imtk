package com.subgraph.orchid.connections;

import com.subgraph.orchid.Cell;
import com.subgraph.orchid.ConnectionHandshakeException;
import com.subgraph.orchid.ConnectionIOException;
import com.xiaomi.mipush.sdk.Constants;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.Principal;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/connections/ConnectionHandshakeV3.class */
public class ConnectionHandshakeV3 extends ConnectionHandshake {
    private X509Certificate identityCertificate;
    private X509Certificate linkCertificate;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ConnectionHandshakeV3(ConnectionImpl connectionImpl, SSLSocket sSLSocket) {
        super(connectionImpl, sSLSocket);
    }

    private static String getCN(String str) {
        int indexOf = str.indexOf("CN=");
        if (indexOf == -1) {
            return "";
        }
        int indexOf2 = str.indexOf(44, indexOf);
        return indexOf2 == -1 ? str.substring(indexOf) : str.substring(indexOf, indexOf2);
    }

    private static javax.security.cert.X509Certificate getConnectionCertificateFromSession(SSLSession sSLSession) {
        try {
            return sSLSession.getPeerCertificateChain()[0];
        } catch (SSLPeerUnverifiedException e) {
            return null;
        }
    }

    private static boolean isSelfSigned(javax.security.cert.X509Certificate x509Certificate) {
        try {
            x509Certificate.verify(x509Certificate.getPublicKey());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private X509Certificate readCertificateFromCell(Cell cell) {
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            byte[] bArr = new byte[cell.getShort()];
            cell.getByteArray(bArr);
            return (X509Certificate) certificateFactory.generateCertificate(new ByteArrayInputStream(bArr));
        } catch (CertificateException e) {
            return null;
        }
    }

    public static boolean sessionSupportsHandshake(SSLSession sSLSession) {
        javax.security.cert.X509Certificate connectionCertificateFromSession = getConnectionCertificateFromSession(sSLSession);
        boolean z = false;
        if (connectionCertificateFromSession == null) {
            return false;
        }
        if (isSelfSigned(connectionCertificateFromSession) || testDName(connectionCertificateFromSession.getSubjectDN()) || testDName(connectionCertificateFromSession.getIssuerDN()) || testModulusLength(connectionCertificateFromSession)) {
            z = true;
        }
        return z;
    }

    private X509Certificate testAndReadCertificate(Cell cell, X509Certificate x509Certificate, String str) throws ConnectionHandshakeException {
        if (x509Certificate == null) {
            return readCertificateFromCell(cell);
        }
        throw new ConnectionHandshakeException("Duplicate " + str + " certificates in CERTS cell");
    }

    private static boolean testDName(Principal principal) {
        if (principal.getName().indexOf(Constants.ACCEPT_TIME_SEPARATOR_SP) >= 0) {
            return true;
        }
        return !getCN(r0).endsWith(".net");
    }

    private static boolean testModulusLength(javax.security.cert.X509Certificate x509Certificate) {
        boolean z = false;
        if (!(x509Certificate.getPublicKey() instanceof RSAPublicKey)) {
            return false;
        }
        if (((RSAPublicKey) x509Certificate.getPublicKey()).getModulus().bitLength() > 1024) {
            z = true;
        }
        return z;
    }

    RSAPublicKey getConnectionPublicKey() {
        try {
            return (RSAPublicKey) this.socket.getSession().getPeerCertificateChain()[0].getPublicKey();
        } catch (SSLPeerUnverifiedException e) {
            return null;
        }
    }

    void recvAuthChallengeAndNetinfo() throws ConnectionHandshakeException {
        Cell expectCell = expectCell(Integer.valueOf(Cell.AUTH_CHALLENGE), 8);
        if (expectCell.getCommand() == 8) {
            processNetInfo(expectCell);
        } else {
            processNetInfo(expectCell(8));
        }
    }

    void recvCerts() throws ConnectionHandshakeException {
        Cell expectCell = expectCell(Integer.valueOf(Cell.CERTS));
        int i = expectCell.getByte();
        if (i != 2) {
            throw new ConnectionHandshakeException("Expecting 2 certificates and got " + i);
        }
        this.linkCertificate = null;
        this.identityCertificate = null;
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = expectCell.getByte();
            if (i3 == 1) {
                this.linkCertificate = testAndReadCertificate(expectCell, this.linkCertificate, "Link (type = 1)");
            } else {
                if (i3 != 2) {
                    throw new ConnectionHandshakeException("Unexpected certificate type = " + i3 + " in CERTS cell");
                }
                this.identityCertificate = testAndReadCertificate(expectCell, this.identityCertificate, "Identity (type = 2)");
            }
        }
    }

    @Override // com.subgraph.orchid.connections.ConnectionHandshake
    void runHandshake() throws IOException, InterruptedException, ConnectionIOException {
        sendVersions(3);
        receiveVersions();
        recvCerts();
        recvAuthChallengeAndNetinfo();
        verifyCertificates();
        sendNetinfo();
    }

    void verifyCertificates() throws ConnectionHandshakeException {
        PublicKey publicKey = this.identityCertificate.getPublicKey();
        verifyIdentityKey(publicKey);
        RSAPublicKey rSAPublicKey = (RSAPublicKey) publicKey;
        if (rSAPublicKey.getModulus().bitLength() != 1024) {
            throw new ConnectionHandshakeException("Invalid RSA modulus length in router identity key");
        }
        try {
            this.identityCertificate.checkValidity();
            this.identityCertificate.verify(rSAPublicKey);
            this.linkCertificate.checkValidity();
            this.linkCertificate.verify(rSAPublicKey);
            if (!getConnectionPublicKey().getModulus().equals(((RSAPublicKey) this.linkCertificate.getPublicKey()).getModulus())) {
                throw new ConnectionHandshakeException("Link certificate in CERTS cell does not match connection certificate");
            }
        } catch (GeneralSecurityException e) {
            throw new ConnectionHandshakeException("Router presented invalid certificate chain in CERTS cell");
        }
    }
}
