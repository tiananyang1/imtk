package com.nimbusds.jose;

/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/KeyLengthException.class */
public class KeyLengthException extends KeyException {
    private final Algorithm alg;
    private final int expectedLength;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public KeyLengthException(int r5, com.nimbusds.jose.Algorithm r6) {
        /*
            r4 = this;
            r0 = r5
            if (r0 <= 0) goto L23
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r1 = r0
            java.lang.String r2 = "The expected key length is "
            r1.<init>(r2)
            r7 = r0
            r0 = r7
            r1 = r5
            java.lang.StringBuilder r0 = r0.append(r1)
            r0 = r7
            java.lang.String r1 = " bits"
            java.lang.StringBuilder r0 = r0.append(r1)
            r0 = r7
            java.lang.String r0 = r0.toString()
            r7 = r0
            goto L26
        L23:
            java.lang.String r0 = "Unexpected key length"
            r7 = r0
        L26:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r1 = r0
            r2 = r7
            java.lang.String r2 = java.lang.String.valueOf(r2)
            r1.<init>(r2)
            r8 = r0
            r0 = r6
            if (r0 == 0) goto L56
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r1 = r0
            java.lang.String r2 = " (for "
            r1.<init>(r2)
            r7 = r0
            r0 = r7
            r1 = r6
            java.lang.StringBuilder r0 = r0.append(r1)
            r0 = r7
            java.lang.String r1 = " algorithm)"
            java.lang.StringBuilder r0 = r0.append(r1)
            r0 = r7
            java.lang.String r0 = r0.toString()
            r7 = r0
            goto L59
        L56:
            java.lang.String r0 = ""
            r7 = r0
        L59:
            r0 = r8
            r1 = r7
            java.lang.StringBuilder r0 = r0.append(r1)
            r0 = r4
            r1 = r8
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            r0 = r4
            r1 = r5
            r0.expectedLength = r1
            r0 = r4
            r1 = r6
            r0.alg = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nimbusds.jose.KeyLengthException.<init>(int, com.nimbusds.jose.Algorithm):void");
    }

    public KeyLengthException(Algorithm algorithm) {
        this(0, algorithm);
    }

    public KeyLengthException(String str) {
        super(str);
        this.expectedLength = 0;
        this.alg = null;
    }

    public Algorithm getAlgorithm() {
        return this.alg;
    }

    public int getExpectedKeyLength() {
        return this.expectedLength;
    }
}
