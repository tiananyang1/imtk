package com.horcrux.svg;

import android.annotation.SuppressLint;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import android.os.Build;
import android.view.ViewParent;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.text.ReactFontManager;
import com.horcrux.svg.TextProperties;
import java.util.ArrayList;
import javax.annotation.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
@SuppressLint({"ViewConstructor"})
/* loaded from: classes08-dex2jar.jar:com/horcrux/svg/TSpanView.class */
public class TSpanView extends TextView {
    private static final String FONTS = "fonts/";
    private static final String OTF = ".otf";
    private static final String TTF = ".ttf";
    private static final double radToDeg = 57.29577951308232d;
    private static final double tau = 6.283185307179586d;
    private final ArrayList<String> emoji;
    private final ArrayList<Matrix> emojiTransforms;
    private Path mCachedPath;

    @Nullable
    String mContent;
    private TextPathView textPath;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.horcrux.svg.TSpanView$1 */
    /* loaded from: classes08-dex2jar.jar:com/horcrux/svg/TSpanView$1.class */
    public static /* synthetic */ class C01101 {
        static final /* synthetic */ int[] $SwitchMap$com$horcrux$svg$TextProperties$AlignmentBaseline;
        static final /* synthetic */ int[] $SwitchMap$com$horcrux$svg$TextProperties$TextAnchor = new int[TextProperties.TextAnchor.values().length];
        static final /* synthetic */ int[] $SwitchMap$com$horcrux$svg$TextProperties$TextLengthAdjust;

        /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:121:0x014e
            	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1166)
            	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:1022)
            	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:55)
            */
        static {
            /*
                Method dump skipped, instructions count: 352
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.horcrux.svg.TSpanView.C01101.m3584clinit():void");
        }
    }

    public TSpanView(ReactContext reactContext) {
        super(reactContext);
        this.emoji = new ArrayList<>();
        this.emojiTransforms = new ArrayList<>();
    }

    private void applyTextPropertiesToPaint(Paint paint, FontData fontData) {
        Typeface typeface;
        AssetManager assets = this.mContext.getResources().getAssets();
        double d = fontData.fontSize;
        double d2 = this.mScale;
        boolean z = fontData.fontWeight == TextProperties.FontWeight.Bold;
        boolean z2 = fontData.fontStyle == TextProperties.FontStyle.italic;
        int i = (z && z2) ? 3 : z ? 1 : z2 ? 2 : 0;
        String str = fontData.fontFamily;
        try {
            typeface = Typeface.createFromAsset(assets, FONTS + str + OTF);
        } catch (Exception e) {
            try {
                typeface = Typeface.createFromAsset(assets, FONTS + str + TTF);
            } catch (Exception e2) {
                try {
                    typeface = ReactFontManager.getInstance().getTypeface(str, i, assets);
                } catch (Exception e3) {
                    typeface = null;
                }
            }
        }
        paint.setTypeface(typeface);
        paint.setTextSize((float) (d * d2));
        paint.setTextAlign(Paint.Align.LEFT);
        if (Build.VERSION.SDK_INT >= 21) {
            paint.setLetterSpacing(0.0f);
        }
    }

    private double getAbsoluteStartOffset(SVGLength sVGLength, double d, double d2) {
        return PropHelper.fromRelative(sVGLength, d, 0.0d, this.mScale, d2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0618  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x03ec  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0476  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0539  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private android.graphics.Path getLinePath(java.lang.String r11, android.graphics.Paint r12, android.graphics.Canvas r13) {
        /*
            Method dump skipped, instructions count: 2385
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.horcrux.svg.TSpanView.getLinePath(java.lang.String, android.graphics.Paint, android.graphics.Canvas):android.graphics.Path");
    }

    private double getTextAnchorOffset(TextProperties.TextAnchor textAnchor, double d) {
        int i = C01101.$SwitchMap$com$horcrux$svg$TextProperties$TextAnchor[textAnchor.ordinal()];
        if (i == 2) {
            return (-d) / 2.0d;
        }
        if (i != 3) {
            return 0.0d;
        }
        return -d;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void setupTextPath() {
        ViewParent parent = getParent();
        while (true) {
            ViewParent viewParent = parent;
            if (viewParent == 0) {
                return;
            }
            if (viewParent.getClass() == TextPathView.class) {
                this.textPath = (TextPathView) viewParent;
                return;
            } else if (!(viewParent instanceof TextView)) {
                return;
            } else {
                parent = viewParent.getParent();
            }
        }
    }

    @Override // com.horcrux.svg.TextView, com.horcrux.svg.VirtualView
    void clearCache() {
        this.mCachedPath = null;
        super.clearCache();
    }

    @Override // com.horcrux.svg.TextView, com.horcrux.svg.GroupView, com.horcrux.svg.RenderableView, com.horcrux.svg.VirtualView
    void draw(Canvas canvas, Paint paint, float f) {
        if (this.mContent == null) {
            clip(canvas, paint);
            drawGroup(canvas, paint, f);
            return;
        }
        int size = this.emoji.size();
        if (size > 0) {
            applyTextPropertiesToPaint(paint, getTextRootGlyphContext().getFont());
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= size) {
                    break;
                }
                String str = this.emoji.get(i2);
                Matrix matrix = this.emojiTransforms.get(i2);
                canvas.save();
                canvas.concat(matrix);
                canvas.drawText(str, 0.0f, 0.0f, paint);
                canvas.restore();
                i = i2 + 1;
            }
        }
        drawPath(canvas, paint, f);
    }

    @Override // com.horcrux.svg.TextView, com.horcrux.svg.GroupView, com.horcrux.svg.RenderableView, com.horcrux.svg.VirtualView
    Path getPath(Canvas canvas, Paint paint) {
        Path path = this.mCachedPath;
        if (path != null) {
            return path;
        }
        if (this.mContent == null) {
            this.mCachedPath = getGroupPath(canvas, paint);
            return this.mCachedPath;
        }
        setupTextPath();
        pushGlyphContext();
        this.mCachedPath = getLinePath(this.mContent, paint, canvas);
        popGlyphContext();
        return this.mCachedPath;
    }

    @Override // com.horcrux.svg.TextView
    double getSubtreeTextChunksTotalAdvance(Paint paint) {
        if (!Double.isNaN(this.cachedAdvance)) {
            return this.cachedAdvance;
        }
        String str = this.mContent;
        int i = 0;
        double d = 0.0d;
        if (str == null) {
            while (i < getChildCount()) {
                Object childAt = getChildAt(i);
                double d2 = d;
                if (childAt instanceof TextView) {
                    d2 = d + ((TextView) childAt).getSubtreeTextChunksTotalAdvance(paint);
                }
                i++;
                d = d2;
            }
            this.cachedAdvance = d;
            return d;
        }
        if (str.length() == 0) {
            this.cachedAdvance = 0.0d;
            return 0.0d;
        }
        FontData font = getTextRootGlyphContext().getFont();
        applyTextPropertiesToPaint(paint, font);
        double d3 = font.letterSpacing;
        boolean z = false;
        if (d3 == 0.0d) {
            z = false;
            if (font.fontVariantLigatures == TextProperties.FontVariantLigatures.normal) {
                z = true;
            }
        }
        if (Build.VERSION.SDK_INT >= 21) {
            String str2 = "'rlig', 'liga', 'clig', 'calt', 'locl', 'ccmp', 'mark', 'mkmk','kern', ";
            if (z) {
                paint.setFontFeatureSettings(str2 + "'hlig', 'cala', " + font.fontFeatureSettings);
            } else {
                paint.setFontFeatureSettings(str2 + "'liga' 0, 'clig' 0, 'dlig' 0, 'hlig' 0, 'cala' 0, " + font.fontFeatureSettings);
            }
            paint.setLetterSpacing((float) (d3 / (font.fontSize * this.mScale)));
        }
        this.cachedAdvance = paint.measureText(str);
        return this.cachedAdvance;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.horcrux.svg.GroupView, com.horcrux.svg.RenderableView, com.horcrux.svg.VirtualView
    public int hitTest(float[] fArr) {
        if (this.mContent == null) {
            return super.hitTest(fArr);
        }
        if (this.mPath == null || !this.mInvertible || !this.mTransformInvertible) {
            return -1;
        }
        float[] fArr2 = new float[2];
        this.mInvMatrix.mapPoints(fArr2, fArr);
        this.mInvTransform.mapPoints(fArr2);
        int round = Math.round(fArr2[0]);
        int round2 = Math.round(fArr2[1]);
        if (this.mRegion == null && this.mFillPath != null) {
            this.mRegion = getRegion(this.mFillPath);
        }
        if (this.mRegion == null && this.mPath != null) {
            this.mRegion = getRegion(this.mPath);
        }
        if (this.mStrokeRegion == null && this.mStrokePath != null) {
            this.mStrokeRegion = getRegion(this.mStrokePath);
        }
        if ((this.mRegion == null || !this.mRegion.contains(round, round2)) && (this.mStrokeRegion == null || !this.mStrokeRegion.contains(round, round2))) {
            return -1;
        }
        Path clipPath = getClipPath();
        if (clipPath != null) {
            if (this.mClipRegionPath != clipPath) {
                this.mClipRegionPath = clipPath;
                this.mClipRegion = getRegion(clipPath);
            }
            if (!this.mClipRegion.contains(round, round2)) {
                return -1;
            }
        }
        return getId();
    }

    @Override // com.horcrux.svg.TextView, com.horcrux.svg.VirtualView
    public void invalidate() {
        this.mCachedPath = null;
        super.invalidate();
    }

    @ReactProp(name = "content")
    public void setContent(@Nullable String str) {
        this.mContent = str;
        invalidate();
    }
}
