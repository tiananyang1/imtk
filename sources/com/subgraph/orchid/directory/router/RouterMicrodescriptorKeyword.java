package com.subgraph.orchid.directory.router;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/directory/router/RouterMicrodescriptorKeyword.class */
public enum RouterMicrodescriptorKeyword {
    ONION_KEY("onion-key", 0),
    NTOR_ONION_KEY("ntor-onion-key", 1),
    A("a", 1),
    FAMILY("family"),
    P("p", 2),
    UNKNOWN_KEYWORD("KEYWORD NOT FOUNE");

    public static final int VARIABLE_ARGUMENT_COUNT = -1;
    private final int argumentCount;
    private final String keyword;

    RouterMicrodescriptorKeyword(String str) {
        this(str, -1);
    }

    RouterMicrodescriptorKeyword(String str, int i) {
        this.keyword = str;
        this.argumentCount = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static RouterMicrodescriptorKeyword findKeyword(String str) {
        RouterMicrodescriptorKeyword[] values = values();
        int length = values.length;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length) {
                return UNKNOWN_KEYWORD;
            }
            RouterMicrodescriptorKeyword routerMicrodescriptorKeyword = values[i2];
            if (routerMicrodescriptorKeyword.getKeyword().equals(str)) {
                return routerMicrodescriptorKeyword;
            }
            i = i2 + 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getArgumentCount() {
        return this.argumentCount;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getKeyword() {
        return this.keyword;
    }
}
