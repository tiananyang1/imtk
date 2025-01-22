package com.google.thirdparty.publicsuffix;

import com.google.common.annotations.GwtCompatible;

@GwtCompatible
/* loaded from: classes08-dex2jar.jar:com/google/thirdparty/publicsuffix/PublicSuffixType.class */
enum PublicSuffixType {
    PRIVATE(':', ','),
    ICANN('!', '?');

    private final char innerNodeCode;
    private final char leafNodeCode;

    PublicSuffixType(char c, char c2) {
        this.innerNodeCode = c;
        this.leafNodeCode = c2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static PublicSuffixType fromCode(char c) {
        int i;
        PublicSuffixType[] values = values();
        int length = values.length;
        while (true) {
            int i2 = i;
            if (i2 < length) {
                PublicSuffixType publicSuffixType = values[i2];
                i = (publicSuffixType.getInnerNodeCode() == c || publicSuffixType.getLeafNodeCode() == c) ? 0 : i2 + 1;
                return publicSuffixType;
            }
            StringBuilder sb = new StringBuilder(38);
            sb.append("No enum corresponding to given code: ");
            sb.append(c);
            throw new IllegalArgumentException(sb.toString());
        }
    }

    static PublicSuffixType fromIsPrivate(boolean z) {
        return z ? PRIVATE : ICANN;
    }

    char getInnerNodeCode() {
        return this.innerNodeCode;
    }

    char getLeafNodeCode() {
        return this.leafNodeCode;
    }
}
