package com.subgraph.orchid.circuits.p002hs;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/circuits/hs/HSDescriptorKeyword.class */
public enum HSDescriptorKeyword {
    RENDEZVOUS_SERVICE_DESCRIPTOR("rendezvous-service-descriptor", 1),
    VERSION("version", 1),
    PERMANENT_KEY("permanent-key", 0),
    SECRET_ID_PART("secret-id-part", 1),
    PUBLICATION_TIME("publication-time", 2),
    PROTOCOL_VERSIONS("protocol-versions", 2),
    INTRODUCTION_POINTS("introduction-points", 0),
    SIGNATURE("signature", 0),
    UNKNOWN_KEYWORD("KEYWORD NOT FOUND", 0);

    private final int argumentCount;
    private final String keyword;

    HSDescriptorKeyword(String str, int i) {
        this.keyword = str;
        this.argumentCount = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static HSDescriptorKeyword findKeyword(String str) {
        HSDescriptorKeyword[] values = values();
        int length = values.length;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length) {
                return UNKNOWN_KEYWORD;
            }
            HSDescriptorKeyword hSDescriptorKeyword = values[i2];
            if (hSDescriptorKeyword.getKeyword().equals(str)) {
                return hSDescriptorKeyword;
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
