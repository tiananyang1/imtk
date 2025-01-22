package com.horcrux.svg;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nonnull;

/* loaded from: classes08-dex2jar.jar:com/horcrux/svg/TextProperties.class */
class TextProperties {

    /* loaded from: classes08-dex2jar.jar:com/horcrux/svg/TextProperties$AlignmentBaseline.class */
    enum AlignmentBaseline {
        baseline("baseline"),
        textBottom("text-bottom"),
        alphabetic("alphabetic"),
        ideographic("ideographic"),
        middle("middle"),
        central("central"),
        mathematical("mathematical"),
        textTop("text-top"),
        bottom("bottom"),
        center("center"),
        top("top"),
        textBeforeEdge("text-before-edge"),
        textAfterEdge("text-after-edge"),
        beforeEdge("before-edge"),
        afterEdge("after-edge"),
        hanging("hanging");

        private static final Map<String, AlignmentBaseline> alignmentToEnum = new HashMap();
        private final String alignment;

        static {
            for (AlignmentBaseline alignmentBaseline : values()) {
                alignmentToEnum.put(alignmentBaseline.alignment, alignmentBaseline);
            }
        }

        AlignmentBaseline(String str) {
            this.alignment = str;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static AlignmentBaseline getEnum(String str) {
            if (alignmentToEnum.containsKey(str)) {
                return alignmentToEnum.get(str);
            }
            throw new IllegalArgumentException("Unknown String Value: " + str);
        }

        @Override // java.lang.Enum
        @Nonnull
        public String toString() {
            return this.alignment;
        }
    }

    /* loaded from: classes08-dex2jar.jar:com/horcrux/svg/TextProperties$Direction.class */
    enum Direction {
        ltr,
        rtl
    }

    /* loaded from: classes08-dex2jar.jar:com/horcrux/svg/TextProperties$FontStyle.class */
    enum FontStyle {
        normal,
        italic,
        oblique
    }

    /* loaded from: classes08-dex2jar.jar:com/horcrux/svg/TextProperties$FontVariantLigatures.class */
    enum FontVariantLigatures {
        normal,
        none
    }

    /* loaded from: classes08-dex2jar.jar:com/horcrux/svg/TextProperties$FontWeight.class */
    enum FontWeight {
        Normal("normal"),
        Bold("bold"),
        Bolder("bolder"),
        Lighter("lighter"),
        w100("100"),
        w200("200"),
        w300("300"),
        w400("400"),
        w500("500"),
        w600("600"),
        w700("700"),
        w800("800"),
        w900("900");

        private static final Map<String, FontWeight> weightToEnum = new HashMap();
        private final String weight;

        static {
            for (FontWeight fontWeight : values()) {
                weightToEnum.put(fontWeight.weight, fontWeight);
            }
        }

        FontWeight(String str) {
            this.weight = str;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static FontWeight getEnum(String str) {
            if (weightToEnum.containsKey(str)) {
                return weightToEnum.get(str);
            }
            throw new IllegalArgumentException("Unknown String Value: " + str);
        }

        @Override // java.lang.Enum
        @Nonnull
        public String toString() {
            return this.weight;
        }
    }

    /* loaded from: classes08-dex2jar.jar:com/horcrux/svg/TextProperties$TextAnchor.class */
    enum TextAnchor {
        start,
        middle,
        end
    }

    /* loaded from: classes08-dex2jar.jar:com/horcrux/svg/TextProperties$TextDecoration.class */
    enum TextDecoration {
        None("none"),
        Underline("underline"),
        Overline("overline"),
        LineThrough("line-through"),
        Blink("blink");

        private static final Map<String, TextDecoration> decorationToEnum = new HashMap();
        private final String decoration;

        static {
            for (TextDecoration textDecoration : values()) {
                decorationToEnum.put(textDecoration.decoration, textDecoration);
            }
        }

        TextDecoration(String str) {
            this.decoration = str;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static TextDecoration getEnum(String str) {
            if (decorationToEnum.containsKey(str)) {
                return decorationToEnum.get(str);
            }
            throw new IllegalArgumentException("Unknown String Value: " + str);
        }

        @Override // java.lang.Enum
        @Nonnull
        public String toString() {
            return this.decoration;
        }
    }

    /* loaded from: classes08-dex2jar.jar:com/horcrux/svg/TextProperties$TextLengthAdjust.class */
    enum TextLengthAdjust {
        spacing,
        spacingAndGlyphs
    }

    /* loaded from: classes08-dex2jar.jar:com/horcrux/svg/TextProperties$TextPathMethod.class */
    enum TextPathMethod {
        align,
        stretch
    }

    /* loaded from: classes08-dex2jar.jar:com/horcrux/svg/TextProperties$TextPathMidLine.class */
    enum TextPathMidLine {
        sharp,
        smooth
    }

    /* loaded from: classes08-dex2jar.jar:com/horcrux/svg/TextProperties$TextPathSide.class */
    enum TextPathSide {
        left,
        right
    }

    /* loaded from: classes08-dex2jar.jar:com/horcrux/svg/TextProperties$TextPathSpacing.class */
    enum TextPathSpacing {
        auto,
        exact
    }

    TextProperties() {
    }
}
