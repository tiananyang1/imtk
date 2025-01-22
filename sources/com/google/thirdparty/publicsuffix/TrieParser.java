package com.google.thirdparty.publicsuffix;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

@GwtCompatible
/* loaded from: classes08-dex2jar.jar:com/google/thirdparty/publicsuffix/TrieParser.class */
class TrieParser {
    private static final Joiner PREFIX_JOINER = Joiner.on("");

    TrieParser() {
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x00be, code lost:            if (r10 != ',') goto L32;     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00c1, code lost:            r12 = r11;     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00c9, code lost:            if (r11 >= r0) goto L47;     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00cc, code lost:            r0 = r11 + doParseTrieToBuilder(r6, r7.subSequence(r11, r0), r8);     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00ea, code lost:            if (r7.charAt(r0) == '?') goto L48;     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00ed, code lost:            r11 = r0;     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00fb, code lost:            if (r7.charAt(r0) != ',') goto L50;     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00fe, code lost:            r12 = r0 + 1;     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static int doParseTrieToBuilder(java.util.List<java.lang.CharSequence> r6, java.lang.CharSequence r7, com.google.common.collect.ImmutableMap.Builder<java.lang.String, com.google.thirdparty.publicsuffix.PublicSuffixType> r8) {
        /*
            Method dump skipped, instructions count: 271
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.thirdparty.publicsuffix.TrieParser.doParseTrieToBuilder(java.util.List, java.lang.CharSequence, com.google.common.collect.ImmutableMap$Builder):int");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ImmutableMap<String, PublicSuffixType> parseTrie(CharSequence charSequence) {
        ImmutableMap.Builder builder = ImmutableMap.builder();
        int length = charSequence.length();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length) {
                return builder.build();
            }
            i = i2 + doParseTrieToBuilder(Lists.newLinkedList(), charSequence.subSequence(i2, length), builder);
        }
    }

    private static CharSequence reverse(CharSequence charSequence) {
        int length = charSequence.length();
        if (length <= 1) {
            return charSequence;
        }
        char[] cArr = new char[length];
        int i = length - 1;
        cArr[0] = charSequence.charAt(i);
        for (int i2 = 1; i2 < length; i2++) {
            cArr[i2] = charSequence.charAt(i - i2);
            char c = cArr[i2];
            int i3 = i2 - 1;
            if (Character.isSurrogatePair(c, cArr[i3])) {
                swap(cArr, i3, i2);
            }
        }
        return new String(cArr);
    }

    private static void swap(char[] cArr, int i, int i2) {
        char c = cArr[i];
        cArr[i] = cArr[i2];
        cArr[i2] = c;
    }
}
