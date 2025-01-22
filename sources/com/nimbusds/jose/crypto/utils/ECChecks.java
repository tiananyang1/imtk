package com.nimbusds.jose.crypto.utils;

import java.math.BigInteger;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECFieldFp;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.EllipticCurve;

/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/crypto/utils/ECChecks.class */
public class ECChecks {
    private ECChecks() {
    }

    public static boolean isPointOnCurve(BigInteger bigInteger, BigInteger bigInteger2, ECParameterSpec eCParameterSpec) {
        EllipticCurve curve = eCParameterSpec.getCurve();
        BigInteger a = curve.getA();
        BigInteger b = curve.getB();
        BigInteger p = ((ECFieldFp) curve.getField()).getP();
        return bigInteger2.pow(2).mod(p).equals(bigInteger.pow(3).add(a.multiply(bigInteger)).add(b).mod(p));
    }

    public static boolean isPointOnCurve(ECPublicKey eCPublicKey, ECPrivateKey eCPrivateKey) {
        return isPointOnCurve(eCPublicKey, eCPrivateKey.getParams());
    }

    public static boolean isPointOnCurve(ECPublicKey eCPublicKey, ECParameterSpec eCParameterSpec) {
        ECPoint w = eCPublicKey.getW();
        return isPointOnCurve(w.getAffineX(), w.getAffineY(), eCParameterSpec);
    }
}
