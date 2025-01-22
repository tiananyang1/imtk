package com.horcrux.svg;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.os.Build;
import android.view.View;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.annotations.ReactProp;
import javax.annotation.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
@SuppressLint({"ViewConstructor"})
/* loaded from: classes08-dex2jar.jar:com/horcrux/svg/GroupView.class */
public class GroupView extends RenderableView {

    @Nullable
    ReadableMap mFont;
    private GlyphContext mGlyphContext;

    public GroupView(ReactContext reactContext) {
        super(reactContext);
    }

    private static <T> T requireNonNull(T t) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.horcrux.svg.RenderableView, com.horcrux.svg.VirtualView
    public void draw(Canvas canvas, Paint paint, float f) {
        setupGlyphContext(canvas);
        if (f > 0.01f) {
            clip(canvas, paint);
            drawGroup(canvas, paint, f);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void drawGroup(Canvas canvas, Paint paint, float f) {
        pushGlyphContext();
        SvgView svgView = getSvgView();
        RectF rectF = new RectF();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= getChildCount()) {
                setClientRect(rectF);
                popGlyphContext();
                return;
            }
            Object childAt = getChildAt(i2);
            if (!(childAt instanceof MaskView)) {
                if (childAt instanceof VirtualView) {
                    VirtualView virtualView = (VirtualView) childAt;
                    boolean z = virtualView instanceof RenderableView;
                    if (z) {
                        ((RenderableView) virtualView).mergeProperties(this);
                    }
                    int saveAndSetupCanvas = virtualView.saveAndSetupCanvas(canvas);
                    virtualView.render(canvas, paint, this.mOpacity * f);
                    RectF clientRect = virtualView.getClientRect();
                    if (clientRect != null) {
                        rectF.union(clientRect);
                    }
                    virtualView.restoreCanvas(canvas, saveAndSetupCanvas);
                    if (z) {
                        ((RenderableView) virtualView).resetProperties();
                    }
                    if (virtualView.isResponsible()) {
                        svgView.enableTouchEvents();
                    }
                } else if (childAt instanceof SvgView) {
                    SvgView svgView2 = (SvgView) childAt;
                    svgView2.drawChildren(canvas);
                    if (svgView2.isResponsible()) {
                        svgView.enableTouchEvents();
                    }
                }
            }
            i = i2 + 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void drawPath(Canvas canvas, Paint paint, float f) {
        super.draw(canvas, paint, f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GlyphContext getGlyphContext() {
        return this.mGlyphContext;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.horcrux.svg.RenderableView, com.horcrux.svg.VirtualView
    public Path getPath(Canvas canvas, Paint paint) {
        if (this.mPath != null) {
            return this.mPath;
        }
        this.mPath = new Path();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= getChildCount()) {
                return this.mPath;
            }
            Object childAt = getChildAt(i2);
            if (!(childAt instanceof MaskView) && (childAt instanceof VirtualView)) {
                VirtualView virtualView = (VirtualView) childAt;
                this.mPath.addPath(virtualView.getPath(canvas, paint), virtualView.mMatrix);
            }
            i = i2 + 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Path getPath(Canvas canvas, Paint paint, Region.Op op) {
        Path path = new Path();
        if (Build.VERSION.SDK_INT >= 19) {
            Path.Op valueOf = Path.Op.valueOf(op.name());
            for (int i = 0; i < getChildCount(); i++) {
                Object childAt = getChildAt(i);
                if (!(childAt instanceof MaskView) && (childAt instanceof VirtualView)) {
                    VirtualView virtualView = (VirtualView) childAt;
                    Matrix matrix = virtualView.mMatrix;
                    Path path2 = virtualView instanceof GroupView ? ((GroupView) virtualView).getPath(canvas, paint, op) : virtualView.getPath(canvas, paint);
                    path2.transform(matrix);
                    path.op(path2, valueOf);
                }
            }
        } else {
            Region region = new Region(canvas.getClipBounds());
            Region region2 = new Region();
            int i2 = 0;
            while (true) {
                int i3 = i2;
                if (i3 >= getChildCount()) {
                    break;
                }
                Object childAt2 = getChildAt(i3);
                if (!(childAt2 instanceof MaskView) && (childAt2 instanceof VirtualView)) {
                    VirtualView virtualView2 = (VirtualView) childAt2;
                    Matrix matrix2 = virtualView2.mMatrix;
                    Path path3 = virtualView2 instanceof GroupView ? ((GroupView) virtualView2).getPath(canvas, paint, op) : virtualView2.getPath(canvas, paint);
                    if (matrix2 != null) {
                        path3.transform(matrix2);
                    }
                    Region region3 = new Region();
                    region3.setPath(path3, region);
                    region2.op(region3, op);
                }
                i2 = i3 + 1;
            }
            path.addPath(region2.getBoundaryPath());
        }
        return path;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GlyphContext getTextRootGlyphContext() {
        return ((GroupView) requireNonNull(getTextRoot())).getGlyphContext();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.horcrux.svg.RenderableView, com.horcrux.svg.VirtualView
    public int hitTest(float[] fArr) {
        int reactTagForTouch;
        VirtualView virtualView;
        int hitTest;
        if (!this.mInvertible || !this.mTransformInvertible) {
            return -1;
        }
        float[] fArr2 = new float[2];
        this.mInvMatrix.mapPoints(fArr2, fArr);
        this.mInvTransform.mapPoints(fArr2);
        int round = Math.round(fArr2[0]);
        int round2 = Math.round(fArr2[1]);
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
        int childCount = getChildCount();
        while (true) {
            int i = childCount - 1;
            if (i < 0) {
                return -1;
            }
            View childAt = getChildAt(i);
            if (childAt instanceof VirtualView) {
                if (!(childAt instanceof MaskView) && (hitTest = (virtualView = (VirtualView) childAt).hitTest(fArr2)) != -1) {
                    int i2 = hitTest;
                    if (!virtualView.isResponsible()) {
                        if (hitTest != childAt.getId()) {
                            return hitTest;
                        }
                        i2 = getId();
                    }
                    return i2;
                }
            } else if ((childAt instanceof SvgView) && (reactTagForTouch = ((SvgView) childAt).reactTagForTouch(fArr2[0], fArr2[1])) != childAt.getId()) {
                return reactTagForTouch;
            }
            childCount = i;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void popGlyphContext() {
        getTextRootGlyphContext().popContext();
    }

    void pushGlyphContext() {
        getTextRootGlyphContext().pushContext(this, this.mFont);
    }

    @Override // com.horcrux.svg.RenderableView
    void resetProperties() {
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= getChildCount()) {
                return;
            }
            Object childAt = getChildAt(i2);
            if (childAt instanceof RenderableView) {
                ((RenderableView) childAt).resetProperties();
            }
            i = i2 + 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.horcrux.svg.VirtualView
    public void saveDefinition() {
        if (this.mName != null) {
            getSvgView().defineTemplate(this, this.mName);
        }
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= getChildCount()) {
                return;
            }
            Object childAt = getChildAt(i2);
            if (childAt instanceof VirtualView) {
                ((VirtualView) childAt).saveDefinition();
            }
            i = i2 + 1;
        }
    }

    @ReactProp(name = "font")
    public void setFont(@Nullable ReadableMap readableMap) {
        this.mFont = readableMap;
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setupGlyphContext(Canvas canvas) {
        RectF rectF = new RectF(canvas.getClipBounds());
        if (this.mMatrix != null) {
            this.mMatrix.mapRect(rectF);
        }
        if (this.mTransform != null) {
            this.mTransform.mapRect(rectF);
        }
        this.mGlyphContext = new GlyphContext(this.mScale, rectF.width(), rectF.height());
    }
}
