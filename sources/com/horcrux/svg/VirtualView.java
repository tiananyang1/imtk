package com.horcrux.svg;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.uimanager.DisplayMetricsHolder;
import com.facebook.react.uimanager.OnLayoutEvent;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.view.ReactViewGroup;
import com.microsoft.codepush.react.CodePushConstants;
import javax.annotation.Nullable;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes08-dex2jar.jar:com/horcrux/svg/VirtualView.class */
public abstract class VirtualView extends ReactViewGroup {
    private static final int CLIP_RULE_EVENODD = 0;
    static final int CLIP_RULE_NONZERO = 1;
    static final float MIN_OPACITY_FOR_DRAW = 0.01f;
    private static final double M_SQRT1_2l = 0.7071067811865476d;
    private static final float[] sRawMatrix = {1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    private double canvasDiagonal;
    private float canvasHeight;
    private float canvasWidth;
    private double fontSize;
    private GlyphContext glyphContext;
    RectF mBox;
    private Path mCachedClipPath;
    private RectF mClientRect;

    @Nullable
    private String mClipPath;
    Region mClipRegion;
    Path mClipRegionPath;
    int mClipRule;
    final ReactContext mContext;
    Path mFillPath;
    Matrix mInvMatrix;
    final Matrix mInvTransform;
    boolean mInvertible;

    @Nullable
    String mMask;
    Matrix mMatrix;
    String mName;
    float mOpacity;
    Path mPath;
    Region mRegion;
    private boolean mResponsible;
    final float mScale;
    Path mStrokePath;
    Region mStrokeRegion;
    private GroupView mTextRoot;
    Matrix mTransform;
    boolean mTransformInvertible;
    private SvgView svgView;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.horcrux.svg.VirtualView$1 */
    /* loaded from: classes08-dex2jar.jar:com/horcrux/svg/VirtualView$1.class */
    public static /* synthetic */ class C01121 {
        static final /* synthetic */ int[] $SwitchMap$com$horcrux$svg$SVGLengthUnitType = new int[SVGLengthUnitType.values().length];

        /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:43:0x006d
            	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1166)
            	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:1022)
            	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:55)
            */
        static {
            /*
                com.horcrux.svg.SVGLengthUnitType[] r0 = com.horcrux.svg.SVGLengthUnitType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.horcrux.svg.VirtualView.C01121.$SwitchMap$com$horcrux$svg$SVGLengthUnitType = r0
                int[] r0 = com.horcrux.svg.VirtualView.C01121.$SwitchMap$com$horcrux$svg$SVGLengthUnitType     // Catch: java.lang.NoSuchFieldError -> L59
                com.horcrux.svg.SVGLengthUnitType r1 = com.horcrux.svg.SVGLengthUnitType.SVG_LENGTHTYPE_EMS     // Catch: java.lang.NoSuchFieldError -> L59
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L59
                r2 = 1
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L59
            L14:
                int[] r0 = com.horcrux.svg.VirtualView.C01121.$SwitchMap$com$horcrux$svg$SVGLengthUnitType     // Catch: java.lang.NoSuchFieldError -> L59 java.lang.NoSuchFieldError -> L5d
                com.horcrux.svg.SVGLengthUnitType r1 = com.horcrux.svg.SVGLengthUnitType.SVG_LENGTHTYPE_EXS     // Catch: java.lang.NoSuchFieldError -> L5d
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L5d
                r2 = 2
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L5d
            L1f:
                int[] r0 = com.horcrux.svg.VirtualView.C01121.$SwitchMap$com$horcrux$svg$SVGLengthUnitType     // Catch: java.lang.NoSuchFieldError -> L5d java.lang.NoSuchFieldError -> L61
                com.horcrux.svg.SVGLengthUnitType r1 = com.horcrux.svg.SVGLengthUnitType.SVG_LENGTHTYPE_CM     // Catch: java.lang.NoSuchFieldError -> L61
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L61
                r2 = 3
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L61
            L2a:
                int[] r0 = com.horcrux.svg.VirtualView.C01121.$SwitchMap$com$horcrux$svg$SVGLengthUnitType     // Catch: java.lang.NoSuchFieldError -> L61 java.lang.NoSuchFieldError -> L65
                com.horcrux.svg.SVGLengthUnitType r1 = com.horcrux.svg.SVGLengthUnitType.SVG_LENGTHTYPE_MM     // Catch: java.lang.NoSuchFieldError -> L65
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L65
                r2 = 4
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L65
            L35:
                int[] r0 = com.horcrux.svg.VirtualView.C01121.$SwitchMap$com$horcrux$svg$SVGLengthUnitType     // Catch: java.lang.NoSuchFieldError -> L65 java.lang.NoSuchFieldError -> L69
                com.horcrux.svg.SVGLengthUnitType r1 = com.horcrux.svg.SVGLengthUnitType.SVG_LENGTHTYPE_IN     // Catch: java.lang.NoSuchFieldError -> L69
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L69
                r2 = 5
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L69
            L40:
                int[] r0 = com.horcrux.svg.VirtualView.C01121.$SwitchMap$com$horcrux$svg$SVGLengthUnitType     // Catch: java.lang.NoSuchFieldError -> L69 java.lang.NoSuchFieldError -> L6d
                com.horcrux.svg.SVGLengthUnitType r1 = com.horcrux.svg.SVGLengthUnitType.SVG_LENGTHTYPE_PT     // Catch: java.lang.NoSuchFieldError -> L6d
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L6d
                r2 = 6
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L6d
            L4c:
                int[] r0 = com.horcrux.svg.VirtualView.C01121.$SwitchMap$com$horcrux$svg$SVGLengthUnitType     // Catch: java.lang.NoSuchFieldError -> L6d java.lang.NoSuchFieldError -> L71
                com.horcrux.svg.SVGLengthUnitType r1 = com.horcrux.svg.SVGLengthUnitType.SVG_LENGTHTYPE_PC     // Catch: java.lang.NoSuchFieldError -> L71
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L71
                r2 = 7
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L71
                return
            L59:
                r4 = move-exception
                goto L14
            L5d:
                r4 = move-exception
                goto L1f
            L61:
                r4 = move-exception
                goto L2a
            L65:
                r4 = move-exception
                goto L35
            L69:
                r4 = move-exception
                goto L40
            L6d:
                r4 = move-exception
                goto L4c
            L71:
                r4 = move-exception
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.horcrux.svg.VirtualView.C01121.m3599clinit():void");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public VirtualView(ReactContext reactContext) {
        super(reactContext);
        this.mOpacity = 1.0f;
        this.mMatrix = new Matrix();
        this.mTransform = new Matrix();
        this.mInvMatrix = new Matrix();
        this.mInvTransform = new Matrix();
        this.mInvertible = true;
        this.mTransformInvertible = true;
        this.fontSize = -1.0d;
        this.canvasDiagonal = -1.0d;
        this.canvasHeight = -1.0f;
        this.canvasWidth = -1.0f;
        this.mContext = reactContext;
        this.mScale = DisplayMetricsHolder.getScreenDisplayMetrics().density;
    }

    private void clearParentCache() {
        VirtualView virtualView = this;
        while (true) {
            Object parent = virtualView.getParent();
            if (!(parent instanceof VirtualView)) {
                return;
            }
            virtualView = (VirtualView) parent;
            if (virtualView.mPath == null) {
                return;
            } else {
                virtualView.clearCache();
            }
        }
    }

    private double fromRelativeFast(SVGLength sVGLength) {
        double fontSizeFromContext;
        switch (C01121.$SwitchMap$com$horcrux$svg$SVGLengthUnitType[sVGLength.unit.ordinal()]) {
            case 1:
                fontSizeFromContext = getFontSizeFromContext();
                break;
            case 2:
                fontSizeFromContext = getFontSizeFromContext() / 2.0d;
                break;
            case 3:
                fontSizeFromContext = 35.43307d;
                break;
            case 4:
                fontSizeFromContext = 3.543307d;
                break;
            case 5:
                fontSizeFromContext = 90.0d;
                break;
            case 6:
                fontSizeFromContext = 1.25d;
                break;
            case 7:
                fontSizeFromContext = 15.0d;
                break;
            default:
                fontSizeFromContext = 1.0d;
                break;
        }
        return sVGLength.value * fontSizeFromContext * this.mScale;
    }

    private double getCanvasDiagonal() {
        double d = this.canvasDiagonal;
        if (d != -1.0d) {
            return d;
        }
        this.canvasDiagonal = Math.sqrt(Math.pow(getCanvasWidth(), 2.0d) + Math.pow(getCanvasHeight(), 2.0d)) * M_SQRT1_2l;
        return this.canvasDiagonal;
    }

    private float getCanvasHeight() {
        float f = this.canvasHeight;
        if (f != -1.0f) {
            return f;
        }
        GroupView textRoot = getTextRoot();
        if (textRoot == null) {
            this.canvasHeight = getSvgView().getCanvasBounds().height();
        } else {
            this.canvasHeight = textRoot.getGlyphContext().getHeight();
        }
        return this.canvasHeight;
    }

    private float getCanvasWidth() {
        float f = this.canvasWidth;
        if (f != -1.0f) {
            return f;
        }
        GroupView textRoot = getTextRoot();
        if (textRoot == null) {
            this.canvasWidth = getSvgView().getCanvasBounds().width();
        } else {
            this.canvasWidth = textRoot.getGlyphContext().getWidth();
        }
        return this.canvasWidth;
    }

    private double getFontSizeFromContext() {
        double d = this.fontSize;
        if (d != -1.0d) {
            return d;
        }
        GroupView textRoot = getTextRoot();
        if (textRoot == null) {
            return 12.0d;
        }
        if (this.glyphContext == null) {
            this.glyphContext = textRoot.getGlyphContext();
        }
        this.fontSize = this.glyphContext.getFontSize();
        return this.fontSize;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void clearCache() {
        this.canvasDiagonal = -1.0d;
        this.canvasHeight = -1.0f;
        this.canvasWidth = -1.0f;
        this.fontSize = -1.0d;
        this.mStrokeRegion = null;
        this.mRegion = null;
        this.mPath = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void clearChildCache() {
        clearCache();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= getChildCount()) {
                return;
            }
            Object childAt = getChildAt(i2);
            if (childAt instanceof VirtualView) {
                ((VirtualView) childAt).clearChildCache();
            }
            i = i2 + 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void clip(Canvas canvas, Paint paint) {
        Path clipPath = getClipPath(canvas, paint);
        if (clipPath != null) {
            canvas.clipPath(clipPath);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void draw(Canvas canvas, Paint paint, float f);

    /* JADX INFO: Access modifiers changed from: package-private */
    public RectF getClientRect() {
        return this.mClientRect;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public Path getClipPath() {
        return this.mCachedClipPath;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public Path getClipPath(Canvas canvas, Paint paint) {
        if (this.mClipPath != null) {
            ClipPathView clipPathView = (ClipPathView) getSvgView().getDefinedClipPath(this.mClipPath);
            if (clipPathView != null) {
                Path path = clipPathView.mClipRule == 0 ? clipPathView.getPath(canvas, paint) : clipPathView.getPath(canvas, paint, Region.Op.UNION);
                int i = clipPathView.mClipRule;
                if (i == 0) {
                    path.setFillType(Path.FillType.EVEN_ODD);
                } else if (i != 1) {
                    FLog.w(CodePushConstants.REACT_NATIVE_LOG_TAG, "RNSVG: clipRule: " + this.mClipRule + " unrecognized");
                }
                this.mCachedClipPath = path;
            } else {
                FLog.w(CodePushConstants.REACT_NATIVE_LOG_TAG, "RNSVG: Undefined clipPath: " + this.mClipPath);
            }
        }
        return getClipPath();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public GroupView getParentTextRoot() {
        Object parent = getParent();
        if (parent instanceof VirtualView) {
            return ((VirtualView) parent).getTextRoot();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Path getPath(Canvas canvas, Paint paint);

    /* JADX INFO: Access modifiers changed from: package-private */
    public SvgView getSvgView() {
        SvgView svgView = this.svgView;
        if (svgView != null) {
            return svgView;
        }
        Object parent = getParent();
        if (parent == null) {
            return null;
        }
        if (parent instanceof SvgView) {
            this.svgView = (SvgView) parent;
        } else if (parent instanceof VirtualView) {
            this.svgView = ((VirtualView) parent).getSvgView();
        } else {
            FLog.e(CodePushConstants.REACT_NATIVE_LOG_TAG, "RNSVG: " + getClass().getName() + " should be descendant of a SvgView.");
        }
        return this.svgView;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public GroupView getTextRoot() {
        if (this.mTextRoot == null) {
            VirtualView virtualView = this;
            while (true) {
                VirtualView virtualView2 = virtualView;
                if (virtualView2 == null) {
                    break;
                }
                if (virtualView2 instanceof GroupView) {
                    GroupView groupView = (GroupView) virtualView2;
                    if (groupView.getGlyphContext() != null) {
                        this.mTextRoot = groupView;
                        break;
                    }
                }
                Object parent = virtualView2.getParent();
                virtualView = !(parent instanceof VirtualView) ? null : (VirtualView) parent;
            }
        }
        return this.mTextRoot;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int hitTest(float[] fArr);

    public void invalidate() {
        if ((this instanceof RenderableView) && this.mPath == null) {
            return;
        }
        clearCache();
        clearParentCache();
        super.invalidate();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isResponsible() {
        return this.mResponsible;
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (this.mClientRect == null) {
            return;
        }
        if (!(this instanceof GroupView)) {
            int floor = (int) Math.floor(r0.left);
            int floor2 = (int) Math.floor(this.mClientRect.top);
            int ceil = (int) Math.ceil(this.mClientRect.right);
            int ceil2 = (int) Math.ceil(this.mClientRect.bottom);
            setLeft(floor);
            setTop(floor2);
            setRight(ceil);
            setBottom(ceil2);
        }
        setMeasuredDimension((int) Math.ceil(this.mClientRect.width()), (int) Math.ceil(this.mClientRect.height()));
    }

    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(this.mClientRect != null ? (int) Math.ceil(r0.width()) : getDefaultSize(getSuggestedMinimumWidth(), i), this.mClientRect != null ? (int) Math.ceil(r0.height()) : getDefaultSize(getSuggestedMinimumHeight(), i2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public double relativeOnHeight(SVGLength sVGLength) {
        double d;
        float canvasHeight;
        SVGLengthUnitType sVGLengthUnitType = sVGLength.unit;
        if (sVGLengthUnitType == SVGLengthUnitType.SVG_LENGTHTYPE_NUMBER) {
            d = sVGLength.value;
            canvasHeight = this.mScale;
        } else {
            if (sVGLengthUnitType != SVGLengthUnitType.SVG_LENGTHTYPE_PERCENTAGE) {
                return fromRelativeFast(sVGLength);
            }
            d = sVGLength.value / 100.0d;
            canvasHeight = getCanvasHeight();
        }
        return d * canvasHeight;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public double relativeOnOther(SVGLength sVGLength) {
        double d;
        double canvasDiagonal;
        SVGLengthUnitType sVGLengthUnitType = sVGLength.unit;
        if (sVGLengthUnitType == SVGLengthUnitType.SVG_LENGTHTYPE_NUMBER) {
            d = sVGLength.value;
            canvasDiagonal = this.mScale;
        } else {
            if (sVGLengthUnitType != SVGLengthUnitType.SVG_LENGTHTYPE_PERCENTAGE) {
                return fromRelativeFast(sVGLength);
            }
            d = sVGLength.value / 100.0d;
            canvasDiagonal = getCanvasDiagonal();
        }
        return d * canvasDiagonal;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public double relativeOnWidth(SVGLength sVGLength) {
        double d;
        float canvasWidth;
        SVGLengthUnitType sVGLengthUnitType = sVGLength.unit;
        if (sVGLengthUnitType == SVGLengthUnitType.SVG_LENGTHTYPE_NUMBER) {
            d = sVGLength.value;
            canvasWidth = this.mScale;
        } else {
            if (sVGLengthUnitType != SVGLengthUnitType.SVG_LENGTHTYPE_PERCENTAGE) {
                return fromRelativeFast(sVGLength);
            }
            d = sVGLength.value / 100.0d;
            canvasWidth = getCanvasWidth();
        }
        return d * canvasWidth;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void render(Canvas canvas, Paint paint, float f) {
        draw(canvas, paint, f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void restoreCanvas(Canvas canvas, int i) {
        canvas.restoreToCount(i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int saveAndSetupCanvas(Canvas canvas) {
        int save = canvas.save();
        canvas.concat(this.mMatrix);
        canvas.concat(this.mTransform);
        return save;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void saveDefinition() {
        if (this.mName != null) {
            getSvgView().defineTemplate(this, this.mName);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setClientRect(RectF rectF) {
        RectF rectF2 = this.mClientRect;
        if (rectF2 == null || !rectF2.equals(rectF)) {
            this.mClientRect = rectF;
            if (this.mClientRect == null) {
                return;
            }
            int floor = (int) Math.floor(r0.left);
            int floor2 = (int) Math.floor(this.mClientRect.top);
            int ceil = (int) Math.ceil(this.mClientRect.right);
            int ceil2 = (int) Math.ceil(this.mClientRect.bottom);
            int ceil3 = (int) Math.ceil(this.mClientRect.width());
            int ceil4 = (int) Math.ceil(this.mClientRect.height());
            if (!(this instanceof GroupView)) {
                setLeft(floor);
                setTop(floor2);
                setRight(ceil);
                setBottom(ceil2);
            }
            setMeasuredDimension(ceil3, ceil4);
            this.mContext.getNativeModule(UIManagerModule.class).getEventDispatcher().dispatchEvent(OnLayoutEvent.obtain(getId(), floor, floor2, ceil3, ceil4));
        }
    }

    @ReactProp(name = "clipPath")
    public void setClipPath(String str) {
        this.mCachedClipPath = null;
        this.mClipPath = str;
        invalidate();
    }

    @ReactProp(defaultInt = 1, name = "clipRule")
    public void setClipRule(int i) {
        this.mClipRule = i;
        invalidate();
    }

    @ReactProp(name = "mask")
    public void setMask(String str) {
        this.mMask = str;
        invalidate();
    }

    @ReactProp(name = "matrix")
    public void setMatrix(Dynamic dynamic) {
        ReadableType type = dynamic.getType();
        if (dynamic.isNull() || !type.equals(ReadableType.Array)) {
            this.mMatrix = null;
            this.mInvMatrix = null;
            this.mInvertible = false;
        } else {
            int matrixData = PropHelper.toMatrixData(dynamic.asArray(), sRawMatrix, this.mScale);
            if (matrixData == 6) {
                if (this.mMatrix == null) {
                    this.mMatrix = new Matrix();
                    this.mInvMatrix = new Matrix();
                }
                this.mMatrix.setValues(sRawMatrix);
                this.mInvertible = this.mMatrix.invert(this.mInvMatrix);
            } else if (matrixData != -1) {
                FLog.w(CodePushConstants.REACT_NATIVE_LOG_TAG, "RNSVG: Transform matrices must be of size 6");
            }
        }
        super.invalidate();
        clearParentCache();
    }

    @ReactProp(name = "name")
    public void setName(String str) {
        this.mName = str;
        invalidate();
    }

    @ReactProp(defaultFloat = 1.0f, name = "opacity")
    public void setOpacity(float f) {
        this.mOpacity = f;
        invalidate();
    }

    @ReactProp(name = "responsible")
    public void setResponsible(boolean z) {
        this.mResponsible = z;
        invalidate();
    }
}
