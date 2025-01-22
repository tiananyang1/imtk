package com.squareup.okhttp;

import com.squareup.okhttp.internal.Util;
import java.security.Principal;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;

/* loaded from: classes08-dex2jar.jar:com/squareup/okhttp/Handshake.class */
public final class Handshake {
    private final String cipherSuite;
    private final List<Certificate> localCertificates;
    private final List<Certificate> peerCertificates;

    private Handshake(String str, List<Certificate> list, List<Certificate> list2) {
        this.cipherSuite = str;
        this.peerCertificates = list;
        this.localCertificates = list2;
    }

    public static Handshake get(String str, List<Certificate> list, List<Certificate> list2) {
        if (str != null) {
            return new Handshake(str, Util.immutableList(list), Util.immutableList(list2));
        }
        throw new IllegalArgumentException("cipherSuite == null");
    }

    public static Handshake get(SSLSession sSLSession) {
        Certificate[] certificateArr;
        String cipherSuite = sSLSession.getCipherSuite();
        if (cipherSuite == null) {
            throw new IllegalStateException("cipherSuite == null");
        }
        try {
            certificateArr = sSLSession.getPeerCertificates();
        } catch (SSLPeerUnverifiedException e) {
            certificateArr = null;
        }
        List immutableList = certificateArr != null ? Util.immutableList(certificateArr) : Collections.emptyList();
        Certificate[] localCertificates = sSLSession.getLocalCertificates();
        return new Handshake(cipherSuite, immutableList, localCertificates != null ? Util.immutableList(localCertificates) : Collections.emptyList());
    }

    public String cipherSuite() {
        return this.cipherSuite;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Handshake)) {
            return false;
        }
        Handshake handshake = (Handshake) obj;
        boolean z = false;
        if (this.cipherSuite.equals(handshake.cipherSuite)) {
            z = false;
            if (this.peerCertificates.equals(handshake.peerCertificates)) {
                z = false;
                if (this.localCertificates.equals(handshake.localCertificates)) {
                    z = true;
                }
            }
        }
        return z;
    }

    public int hashCode() {
        return ((((527 + this.cipherSuite.hashCode()) * 31) + this.peerCertificates.hashCode()) * 31) + this.localCertificates.hashCode();
    }

    public List<Certificate> localCertificates() {
        return this.localCertificates;
    }

    public Principal localPrincipal() {
        if (this.localCertificates.isEmpty()) {
            return null;
        }
        return ((X509Certificate) this.localCertificates.get(0)).getSubjectX500Principal();
    }

    public List<Certificate> peerCertificates() {
        return this.peerCertificates;
    }

    public Principal peerPrincipal() {
        if (this.peerCertificates.isEmpty()) {
            return null;
        }
        return ((X509Certificate) this.peerCertificates.get(0)).getSubjectX500Principal();
    }
}
