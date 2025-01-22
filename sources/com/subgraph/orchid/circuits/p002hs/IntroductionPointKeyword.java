package com.subgraph.orchid.circuits.p002hs;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/circuits/hs/IntroductionPointKeyword.class */
public enum IntroductionPointKeyword {
    SERVICE_AUTHENTICATION("service-authentication", 2),
    INTRODUCTION_POINT("introduction-point", 1),
    IP_ADDRESS("ip-address", 1),
    ONION_PORT("onion-port", 1),
    ONION_KEY("onion-key", 0),
    SERVICE_KEY("service-key", 0),
    INTRO_AUTHENTICATION("intro-authentication", 2),
    UNKNOWN_KEYWORD("KEYWORD NOT FOUND", 0);

    private final int argumentCount;
    private final String keyword;

    IntroductionPointKeyword(String str, int i) {
        this.keyword = str;
        this.argumentCount = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static IntroductionPointKeyword findKeyword(String str) {
        IntroductionPointKeyword[] values = values();
        int length = values.length;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length) {
                return UNKNOWN_KEYWORD;
            }
            IntroductionPointKeyword introductionPointKeyword = values[i2];
            if (introductionPointKeyword.getKeyword().equals(str)) {
                return introductionPointKeyword;
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
