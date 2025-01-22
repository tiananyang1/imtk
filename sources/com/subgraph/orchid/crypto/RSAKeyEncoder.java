package com.subgraph.orchid.crypto;

import com.subgraph.orchid.crypto.ASN1Parser;
import com.subgraph.orchid.encoders.Base64;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.util.List;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/crypto/RSAKeyEncoder.class */
public class RSAKeyEncoder {
    private static final String FOOTER = "-----END RSA PUBLIC KEY-----";
    private static final String HEADER = "-----BEGIN RSA PUBLIC KEY-----";
    private final ASN1Parser asn1Parser = new ASN1Parser();

    private BigInteger asn1ObjectToBigInt(ASN1Parser.ASN1Object aSN1Object) {
        if (aSN1Object instanceof ASN1Parser.ASN1Integer) {
            return ((ASN1Parser.ASN1Integer) aSN1Object).getValue();
        }
        throw new IllegalArgumentException();
    }

    private byte[] asn1ObjectToBitString(ASN1Parser.ASN1Object aSN1Object) {
        if (aSN1Object instanceof ASN1Parser.ASN1BitString) {
            return ((ASN1Parser.ASN1BitString) aSN1Object).getBytes();
        }
        throw new IllegalArgumentException();
    }

    private List<ASN1Parser.ASN1Object> asn1ObjectToSequence(ASN1Parser.ASN1Object aSN1Object, int i) {
        if (!(aSN1Object instanceof ASN1Parser.ASN1Sequence)) {
            throw new IllegalArgumentException();
        }
        ASN1Parser.ASN1Sequence aSN1Sequence = (ASN1Parser.ASN1Sequence) aSN1Object;
        if (aSN1Sequence.getItems().size() == i) {
            return aSN1Sequence.getItems();
        }
        throw new IllegalArgumentException();
    }

    private RSAPublicKey createKeyFromModulusAndExponent(BigInteger bigInteger, BigInteger bigInteger2) throws GeneralSecurityException {
        return (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new RSAPublicKeySpec(bigInteger, bigInteger2));
    }

    private byte[] decodeAsciiArmoredPEM(String str) {
        return Base64.decode(removeDelimiters(str));
    }

    private byte[] extractPKCS1KeyFromSubjectPublicKeyInfo(byte[] bArr) {
        return asn1ObjectToBitString(asn1ObjectToSequence(this.asn1Parser.parseASN1(ByteBuffer.wrap(bArr)), 2).get(1));
    }

    private String removeDelimiters(String str) {
        int indexOf = str.indexOf(HEADER);
        int indexOf2 = str.indexOf(FOOTER);
        if (indexOf == -1 || indexOf2 == -1 || indexOf2 <= indexOf) {
            throw new IllegalArgumentException("PEM object not formatted with expected header and footer");
        }
        return str.substring(indexOf + 30, indexOf2);
    }

    public byte[] getPKCS1Encoded(RSAPublicKey rSAPublicKey) {
        return extractPKCS1KeyFromSubjectPublicKeyInfo(rSAPublicKey.getEncoded());
    }

    public RSAPublicKey parsePEMPublicKey(String str) throws GeneralSecurityException {
        try {
            List<ASN1Parser.ASN1Object> asn1ObjectToSequence = asn1ObjectToSequence(this.asn1Parser.parseASN1(ByteBuffer.wrap(decodeAsciiArmoredPEM(str))), 2);
            return createKeyFromModulusAndExponent(asn1ObjectToBigInt(asn1ObjectToSequence.get(0)), asn1ObjectToBigInt(asn1ObjectToSequence.get(1)));
        } catch (IllegalArgumentException e) {
            throw new InvalidKeyException();
        }
    }
}
