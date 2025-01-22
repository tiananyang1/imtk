package com.horcrux.svg;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.view.ViewParent;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.horcrux.svg.TextProperties;
import java.util.ArrayList;
import javax.annotation.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
@SuppressLint({"ViewConstructor"})
/* loaded from: classes08-dex2jar.jar:com/horcrux/svg/TextView.class */
public class TextView extends GroupView {
    double cachedAdvance;
    private TextProperties.AlignmentBaseline mAlignmentBaseline;
    private String mBaselineShift;

    @Nullable
    private ArrayList<SVGLength> mDeltaX;

    @Nullable
    private ArrayList<SVGLength> mDeltaY;
    TextProperties.TextLengthAdjust mLengthAdjust;

    @Nullable
    private ArrayList<SVGLength> mPositionX;

    @Nullable
    private ArrayList<SVGLength> mPositionY;

    @Nullable
    private ArrayList<SVGLength> mRotate;
    SVGLength mTextLength;

    public TextView(ReactContext reactContext) {
        super(reactContext);
        this.mTextLength = null;
        this.mBaselineShift = null;
        this.mLengthAdjust = TextProperties.TextLengthAdjust.spacing;
        this.cachedAdvance = Double.NaN;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.horcrux.svg.VirtualView
    public void clearCache() {
        this.cachedAdvance = Double.NaN;
        super.clearCache();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.horcrux.svg.GroupView, com.horcrux.svg.RenderableView, com.horcrux.svg.VirtualView
    public void draw(Canvas canvas, Paint paint, float f) {
        if (f > 0.01f) {
            setupGlyphContext(canvas);
            clip(canvas, paint);
            getGroupPath(canvas, paint);
            drawGroup(canvas, paint, f);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    public TextProperties.AlignmentBaseline getAlignmentBaseline() {
        TextProperties.AlignmentBaseline alignmentBaseline;
        if (this.mAlignmentBaseline == null) {
            ViewParent parent = getParent();
            while (true) {
                ViewParent viewParent = parent;
                if (viewParent == 0) {
                    break;
                }
                if ((viewParent instanceof TextView) && (alignmentBaseline = ((TextView) viewParent).mAlignmentBaseline) != null) {
                    this.mAlignmentBaseline = alignmentBaseline;
                    return alignmentBaseline;
                }
                parent = viewParent.getParent();
            }
        }
        if (this.mAlignmentBaseline == null) {
            this.mAlignmentBaseline = TextProperties.AlignmentBaseline.baseline;
        }
        return this.mAlignmentBaseline;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    public String getBaselineShift() {
        String str;
        if (this.mBaselineShift == null) {
            ViewParent parent = getParent();
            while (true) {
                ViewParent viewParent = parent;
                if (viewParent == 0) {
                    break;
                }
                if ((viewParent instanceof TextView) && (str = ((TextView) viewParent).mBaselineShift) != null) {
                    this.mBaselineShift = str;
                    return str;
                }
                parent = viewParent.getParent();
            }
        }
        return this.mBaselineShift;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Path getGroupPath(Canvas canvas, Paint paint) {
        if (this.mPath != null) {
            return this.mPath;
        }
        pushGlyphContext();
        this.mPath = super.getPath(canvas, paint);
        popGlyphContext();
        return this.mPath;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.horcrux.svg.GroupView, com.horcrux.svg.RenderableView, com.horcrux.svg.VirtualView
    public Path getPath(Canvas canvas, Paint paint) {
        if (this.mPath != null) {
            return this.mPath;
        }
        setupGlyphContext(canvas);
        return getGroupPath(canvas, paint);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.horcrux.svg.GroupView
    public Path getPath(Canvas canvas, Paint paint, Region.Op op) {
        return getPath(canvas, paint);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public double getSubtreeTextChunksTotalAdvance(Paint paint) {
        if (!Double.isNaN(this.cachedAdvance)) {
            return this.cachedAdvance;
        }
        double d = 0.0d;
        int i = 0;
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

    /* JADX INFO: Access modifiers changed from: package-private */
    public TextView getTextAnchorRoot() {
        ArrayList<FontData> arrayList = getTextRootGlyphContext().mFontContext;
        ViewParent parent = getParent();
        TextView textView = this;
        for (int size = arrayList.size() - 1; size >= 0 && (parent instanceof TextView) && arrayList.get(size).textAnchor != TextProperties.TextAnchor.start; size--) {
            if (textView.mPositionX != null) {
                return textView;
            }
            textView = parent;
            parent = textView.getParent();
        }
        return textView;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TextView getTextContainer() {
        ViewParent parent = getParent();
        TextView textView = this;
        while (parent instanceof TextView) {
            textView = parent;
            parent = textView.getParent();
        }
        return textView;
    }

    @Override // com.horcrux.svg.VirtualView
    public void invalidate() {
        if (this.mPath == null) {
            return;
        }
        super.invalidate();
        getTextContainer().clearChildCache();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.horcrux.svg.GroupView
    public void pushGlyphContext() {
        getTextRootGlyphContext().pushContext(((this instanceof TextPathView) || (this instanceof TSpanView)) ? false : true, this, this.mFont, this.mPositionX, this.mPositionY, this.mDeltaX, this.mDeltaY, this.mRotate);
    }

    @ReactProp(name = "baselineShift")
    public void setBaselineShift(Dynamic dynamic) {
        this.mBaselineShift = SVGLength.toString(dynamic);
        invalidate();
    }

    @ReactProp(name = "dx")
    public void setDeltaX(Dynamic dynamic) {
        this.mDeltaX = SVGLength.arrayFrom(dynamic);
        invalidate();
    }

    @ReactProp(name = "dy")
    public void setDeltaY(Dynamic dynamic) {
        this.mDeltaY = SVGLength.arrayFrom(dynamic);
        invalidate();
    }

    @ReactProp(name = "lengthAdjust")
    public void setLengthAdjust(@Nullable String str) {
        this.mLengthAdjust = TextProperties.TextLengthAdjust.valueOf(str);
        invalidate();
    }

    @ReactProp(name = "alignmentBaseline")
    public void setMethod(@Nullable String str) {
        this.mAlignmentBaseline = TextProperties.AlignmentBaseline.getEnum(str);
        invalidate();
    }

    @ReactProp(name = "x")
    public void setPositionX(Dynamic dynamic) {
        this.mPositionX = SVGLength.arrayFrom(dynamic);
        invalidate();
    }

    @ReactProp(name = "y")
    public void setPositionY(Dynamic dynamic) {
        this.mPositionY = SVGLength.arrayFrom(dynamic);
        invalidate();
    }

    @ReactProp(name = "rotate")
    public void setRotate(Dynamic dynamic) {
        this.mRotate = SVGLength.arrayFrom(dynamic);
        invalidate();
    }

    @ReactProp(name = "textLength")
    public void setTextLength(Dynamic dynamic) {
        this.mTextLength = SVGLength.from(dynamic);
        invalidate();
    }

    @ReactProp(name = "verticalAlign")
    public void setVerticalAlign(@Nullable String str) {
        if (str != null) {
            String trim = str.trim();
            int lastIndexOf = trim.lastIndexOf(32);
            try {
                this.mAlignmentBaseline = TextProperties.AlignmentBaseline.getEnum(trim.substring(lastIndexOf));
            } catch (IllegalArgumentException e) {
                this.mAlignmentBaseline = TextProperties.AlignmentBaseline.baseline;
            }
            try {
                this.mBaselineShift = trim.substring(0, lastIndexOf);
            } catch (IndexOutOfBoundsException e2) {
                this.mBaselineShift = null;
            }
        } else {
            this.mAlignmentBaseline = TextProperties.AlignmentBaseline.baseline;
            this.mBaselineShift = null;
        }
        invalidate();
    }
}
