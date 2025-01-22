package com.subgraph.orchid.connections;

import com.subgraph.orchid.ConnectionHandshakeException;
import com.subgraph.orchid.ConnectionIOException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.PublicKey;
import javax.net.ssl.HandshakeCompletedEvent;
import javax.net.ssl.HandshakeCompletedListener;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.security.cert.CertificateException;
import javax.security.cert.X509Certificate;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/connections/ConnectionHandshakeV2.class */
public class ConnectionHandshakeV2 extends ConnectionHandshake {

    /* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/connections/ConnectionHandshakeV2$HandshakeFinishedMonitor.class */
    private static class HandshakeFinishedMonitor implements HandshakeCompletedListener {
        boolean isFinished;
        final Object lock;

        private HandshakeFinishedMonitor() {
            this.lock = new Object();
        }

        @Override // javax.net.ssl.HandshakeCompletedListener
        public void handshakeCompleted(HandshakeCompletedEvent handshakeCompletedEvent) {
            synchronized (this.lock) {
                this.isFinished = true;
                this.lock.notifyAll();
            }
        }

        public void waitFinished() throws InterruptedException {
            synchronized (this.lock) {
                while (!this.isFinished) {
                    this.lock.wait();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ConnectionHandshakeV2(ConnectionImpl connectionImpl, SSLSocket sSLSocket) {
        super(connectionImpl, sSLSocket);
    }

    private X509Certificate getIdentityCertificateFromSession(SSLSession sSLSession) throws ConnectionHandshakeException {
        try {
            try {
                X509Certificate[] peerCertificateChain = sSLSession.getPeerCertificateChain();
                if (peerCertificateChain.length == 2) {
                    peerCertificateChain[0].verify(peerCertificateChain[1].getPublicKey());
                    return peerCertificateChain[1];
                }
                throw new ConnectionHandshakeException("Expecting 2 certificate chain from router and received chain length " + peerCertificateChain.length);
            } catch (CertificateException e) {
                throw new ConnectionHandshakeException("Malformed certificate received");
            }
        } catch (GeneralSecurityException e2) {
            throw new ConnectionHandshakeException("Incorrect signature on certificate chain");
        } catch (SSLPeerUnverifiedException e3) {
            throw new ConnectionHandshakeException("No certificates received from router");
        }
    }

    private PublicKey getIdentityKey() throws ConnectionHandshakeException {
        return getIdentityCertificateFromSession(this.socket.getSession()).getPublicKey();
    }

    @Override // com.subgraph.orchid.connections.ConnectionHandshake
    void runHandshake() throws IOException, InterruptedException, ConnectionIOException {
        this.socket.setEnabledCipherSuites(ConnectionSocketFactory.V1_CIPHERS_ONLY);
        HandshakeFinishedMonitor handshakeFinishedMonitor = new HandshakeFinishedMonitor();
        this.socket.addHandshakeCompletedListener(handshakeFinishedMonitor);
        this.socket.startHandshake();
        handshakeFinishedMonitor.waitFinished();
        this.socket.removeHandshakeCompletedListener(handshakeFinishedMonitor);
        verifyIdentityKey(getIdentityKey());
        sendVersions(2);
        receiveVersions();
        sendNetinfo();
        recvNetinfo();
    }
}
