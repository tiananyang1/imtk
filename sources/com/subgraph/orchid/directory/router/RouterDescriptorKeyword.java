package com.subgraph.orchid.directory.router;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/directory/router/RouterDescriptorKeyword.class */
public enum RouterDescriptorKeyword {
    ROUTER("router", 5),
    BANDWIDTH("bandwidth", 3),
    PLATFORM("platform"),
    PUBLISHED("published", 2),
    FINGERPRINT("fingerprint", 10),
    HIBERNATING("hibernating", 1),
    UPTIME("uptime", 1),
    ONION_KEY("onion-key", 0),
    NTOR_ONION_KEY("ntor-onion-key", 1),
    SIGNING_KEY("signing-key", 0),
    ACCEPT("accept", 1),
    REJECT("reject", 1),
    ROUTER_SIGNATURE("router-signature", 0),
    CONTACT("contact"),
    FAMILY("family"),
    READ_HISTORY("read-history"),
    WRITE_HISTORY("write-history"),
    EVENTDNS("eventdns", 1),
    CACHES_EXTRA_INFO("caches-extra-info", 0),
    EXTRA_INFO_DIGEST("extra-info-digest", 1),
    HIDDEN_SERVICE_DIR("hidden-service-dir"),
    PROTOCOLS("protocols"),
    ALLOW_SINGLE_HOP_EXITS("allow-single-hop-exits", 0),
    UNKNOWN_KEYWORD("KEYWORD NOT FOUND");

    public static final int VARIABLE_ARGUMENT_COUNT = -1;
    private final int argumentCount;
    private final String keyword;

    RouterDescriptorKeyword(String str) {
        this(str, -1);
    }

    RouterDescriptorKeyword(String str, int i) {
        this.keyword = str;
        this.argumentCount = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static RouterDescriptorKeyword findKeyword(String str) {
        RouterDescriptorKeyword[] values = values();
        int length = values.length;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length) {
                return UNKNOWN_KEYWORD;
            }
            RouterDescriptorKeyword routerDescriptorKeyword = values[i2];
            if (routerDescriptorKeyword.getKeyword().equals(str)) {
                return routerDescriptorKeyword;
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
