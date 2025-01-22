package com.horcrux.svg;

import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.horcrux.svg.TextProperties;

/* loaded from: classes08-dex2jar.jar:com/horcrux/svg/FontData.class */
class FontData {
    static final double DEFAULT_FONT_SIZE = 12.0d;
    private static final double DEFAULT_KERNING = 0.0d;
    private static final double DEFAULT_LETTER_SPACING = 0.0d;
    private static final double DEFAULT_WORD_SPACING = 0.0d;
    static final FontData Defaults = new FontData();
    private static final String FONT_DATA = "fontData";
    private static final String FONT_FEATURE_SETTINGS = "fontFeatureSettings";
    private static final String FONT_VARIANT_LIGATURES = "fontVariantLigatures";
    private static final String KERNING = "kerning";
    private static final String LETTER_SPACING = "letterSpacing";
    private static final String TEXT_ANCHOR = "textAnchor";
    private static final String TEXT_DECORATION = "textDecoration";
    private static final String WORD_SPACING = "wordSpacing";
    final ReadableMap fontData;
    final String fontFamily;
    final String fontFeatureSettings;
    final double fontSize;
    final TextProperties.FontStyle fontStyle;
    final TextProperties.FontVariantLigatures fontVariantLigatures;
    final TextProperties.FontWeight fontWeight;
    final double kerning;
    final double letterSpacing;
    final boolean manualKerning;
    final TextProperties.TextAnchor textAnchor;
    private final TextProperties.TextDecoration textDecoration;
    final double wordSpacing;

    private FontData() {
        this.fontData = null;
        this.fontFamily = "";
        this.fontStyle = TextProperties.FontStyle.normal;
        this.fontWeight = TextProperties.FontWeight.Normal;
        this.fontFeatureSettings = "";
        this.fontVariantLigatures = TextProperties.FontVariantLigatures.normal;
        this.textAnchor = TextProperties.TextAnchor.start;
        this.textDecoration = TextProperties.TextDecoration.None;
        this.manualKerning = false;
        this.kerning = 0.0d;
        this.fontSize = DEFAULT_FONT_SIZE;
        this.wordSpacing = 0.0d;
        this.letterSpacing = 0.0d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FontData(ReadableMap readableMap, FontData fontData, double d) {
        double d2 = fontData.fontSize;
        if (!readableMap.hasKey("fontSize")) {
            this.fontSize = d2;
        } else if (readableMap.getType("fontSize") == ReadableType.Number) {
            this.fontSize = readableMap.getDouble("fontSize");
        } else {
            this.fontSize = PropHelper.fromRelative(readableMap.getString("fontSize"), d2, 1.0d, d2);
        }
        this.fontData = readableMap.hasKey(FONT_DATA) ? readableMap.getMap(FONT_DATA) : fontData.fontData;
        this.fontFamily = readableMap.hasKey("fontFamily") ? readableMap.getString("fontFamily") : fontData.fontFamily;
        this.fontStyle = readableMap.hasKey("fontStyle") ? TextProperties.FontStyle.valueOf(readableMap.getString("fontStyle")) : fontData.fontStyle;
        this.fontWeight = readableMap.hasKey("fontWeight") ? TextProperties.FontWeight.getEnum(readableMap.getString("fontWeight")) : fontData.fontWeight;
        this.fontFeatureSettings = readableMap.hasKey(FONT_FEATURE_SETTINGS) ? readableMap.getString(FONT_FEATURE_SETTINGS) : fontData.fontFeatureSettings;
        this.fontVariantLigatures = readableMap.hasKey(FONT_VARIANT_LIGATURES) ? TextProperties.FontVariantLigatures.valueOf(readableMap.getString(FONT_VARIANT_LIGATURES)) : fontData.fontVariantLigatures;
        this.textAnchor = readableMap.hasKey(TEXT_ANCHOR) ? TextProperties.TextAnchor.valueOf(readableMap.getString(TEXT_ANCHOR)) : fontData.textAnchor;
        this.textDecoration = readableMap.hasKey(TEXT_DECORATION) ? TextProperties.TextDecoration.getEnum(readableMap.getString(TEXT_DECORATION)) : fontData.textDecoration;
        boolean hasKey = readableMap.hasKey(KERNING);
        this.manualKerning = hasKey || fontData.manualKerning;
        this.kerning = hasKey ? toAbsolute(readableMap.getString(KERNING), d, this.fontSize) : fontData.kerning;
        this.wordSpacing = readableMap.hasKey(WORD_SPACING) ? toAbsolute(readableMap.getString(WORD_SPACING), d, this.fontSize) : fontData.wordSpacing;
        this.letterSpacing = readableMap.hasKey(LETTER_SPACING) ? toAbsolute(readableMap.getString(LETTER_SPACING), d, this.fontSize) : fontData.letterSpacing;
    }

    private double toAbsolute(String str, double d, double d2) {
        return PropHelper.fromRelative(str, 0.0d, d, d2);
    }
}
