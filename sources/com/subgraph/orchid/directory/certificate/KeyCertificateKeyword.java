package com.subgraph.orchid.directory.certificate;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/directory/certificate/KeyCertificateKeyword.class */
public enum KeyCertificateKeyword {
    DIR_KEY_CERTIFICATE_VERSION("dir-key-certificate-version", 1),
    DIR_ADDRESS("dir-address", 1),
    FINGERPRINT("fingerprint", 1),
    DIR_IDENTITY_KEY("dir-identity-key", 0),
    DIR_KEY_PUBLISHED("dir-key-published", 2),
    DIR_KEY_EXPIRES("dir-key-expires", 2),
    DIR_SIGNING_KEY("dir-signing-key", 0),
    DIR_KEY_CROSSCERT("dir-key-crosscert", 0),
    DIR_KEY_CERTIFICATION("dir-key-certification", 0),
    UNKNOWN_KEYWORD("KEYWORD NOT FOUND", 0);

    private final int argumentCount;
    private final String keyword;

    KeyCertificateKeyword(String str, int i) {
        this.keyword = str;
        this.argumentCount = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static KeyCertificateKeyword findKeyword(String str) {
        KeyCertificateKeyword[] values = values();
        int length = values.length;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length) {
                return UNKNOWN_KEYWORD;
            }
            KeyCertificateKeyword keyCertificateKeyword = values[i2];
            if (keyCertificateKeyword.getKeyword().equals(str)) {
                return keyCertificateKeyword;
            }
            i = i2 + 1;
        }
    }

    int getArgumentCount() {
        return this.argumentCount;
    }

    String getKeyword() {
        return this.keyword;
    }
}
