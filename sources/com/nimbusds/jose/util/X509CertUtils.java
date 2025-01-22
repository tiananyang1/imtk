package com.nimbusds.jose.util;

import io.fabric.sdk.android.services.common.CommonUtils;
import java.io.ByteArrayInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/util/X509CertUtils.class */
public class X509CertUtils {
    private static final String PEM_BEGIN_MARKER = "-----BEGIN CERTIFICATE-----";
    private static final String PEM_END_MARKER = "-----END CERTIFICATE-----";

    public static Base64URL computeSHA256Thumbprint(X509Certificate x509Certificate) {
        try {
            return Base64URL.m3704encode(MessageDigest.getInstance(CommonUtils.SHA256_INSTANCE).digest(x509Certificate.getEncoded()));
        } catch (NoSuchAlgorithmException | CertificateEncodingException e) {
            return null;
        }
    }

    public static X509Certificate parse(String str) {
        int indexOf;
        String substring;
        int indexOf2;
        if (str == null || str.isEmpty() || (indexOf = str.indexOf(PEM_BEGIN_MARKER)) < 0 || (indexOf2 = (substring = str.substring(indexOf + 27)).indexOf(PEM_END_MARKER)) < 0) {
            return null;
        }
        return parse(new Base64(substring.substring(0, indexOf2).replaceAll("\\s", "")).decode());
    }

    public static X509Certificate parse(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        try {
            Certificate generateCertificate = CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(bArr));
            if (generateCertificate instanceof X509Certificate) {
                return (X509Certificate) generateCertificate;
            }
            return null;
        } catch (CertificateException e) {
            return null;
        }
    }

    public static String toPEMString(X509Certificate x509Certificate) {
        StringBuilder sb = new StringBuilder();
        sb.append(PEM_BEGIN_MARKER);
        sb.append('\n');
        try {
            sb.append(Base64.encode(x509Certificate.getEncoded()).toString());
            sb.append('\n');
            sb.append(PEM_END_MARKER);
            return sb.toString();
        } catch (CertificateEncodingException e) {
            return null;
        }
    }
}
