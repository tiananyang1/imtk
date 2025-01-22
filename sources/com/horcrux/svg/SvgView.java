package com.horcrux.svg;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.Base64;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.DisplayMetricsHolder;
import com.facebook.react.uimanager.ReactCompoundView;
import com.facebook.react.uimanager.ReactCompoundViewGroup;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.view.ReactViewGroup;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes08-dex2jar.jar:com/horcrux/svg/SvgView.class */
public class SvgView extends ReactViewGroup implements ReactCompoundView, ReactCompoundViewGroup {
    private String mAlign;

    @Nullable
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private final Map<String, Brush> mDefinedBrushes;
    private final Map<String, VirtualView> mDefinedClipPaths;
    private final Map<String, VirtualView> mDefinedMasks;
    private final Map<String, VirtualView> mDefinedTemplates;
    private final Matrix mInvViewBoxMatrix;
    private boolean mInvertible;
    private int mMeetOrSlice;
    private float mMinX;
    private float mMinY;
    private boolean mRendered;
    private boolean mResponsible;
    private final float mScale;
    int mTintColor;
    private float mVbHeight;
    private float mVbWidth;
    private SVGLength mbbHeight;
    private SVGLength mbbWidth;
    private Runnable toDataUrlTask;

    /* loaded from: classes08-dex2jar.jar:com/horcrux/svg/SvgView$Events.class */
    public enum Events {
        EVENT_DATA_URL("onDataURL");

        private final String mName;

        Events(String str) {
            this.mName = str;
        }

        @Override // java.lang.Enum
        @Nonnull
        public String toString() {
            return this.mName;
        }
    }

    public SvgView(ReactContext reactContext) {
        super(reactContext);
        this.toDataUrlTask = null;
        this.mResponsible = false;
        this.mDefinedClipPaths = new HashMap();
        this.mDefinedTemplates = new HashMap();
        this.mDefinedMasks = new HashMap();
        this.mDefinedBrushes = new HashMap();
        this.mInvViewBoxMatrix = new Matrix();
        this.mInvertible = true;
        this.mRendered = false;
        this.mTintColor = 0;
        this.mScale = DisplayMetricsHolder.getScreenDisplayMetrics().density;
    }

    private void clearChildCache() {
        if (this.mRendered) {
            this.mRendered = false;
            for (int i = 0; i < getChildCount(); i++) {
                Object childAt = getChildAt(i);
                if (childAt instanceof VirtualView) {
                    ((VirtualView) childAt).clearChildCache();
                }
            }
        }
    }

    private Bitmap drawOutput() {
        this.mRendered = true;
        float width = getWidth();
        float height = getHeight();
        boolean z = true;
        if (!Float.isNaN(width)) {
            z = true;
            if (!Float.isNaN(height)) {
                z = true;
                if (width >= 1.0f) {
                    z = true;
                    if (height >= 1.0f) {
                        z = Math.log10((double) width) + Math.log10((double) height) > 42.0d;
                    }
                }
            }
        }
        if (z) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap((int) width, (int) height, Bitmap.Config.ARGB_8888);
        drawChildren(new Canvas(createBitmap));
        return createBitmap;
    }

    private RectF getViewBox() {
        float f = this.mMinX;
        float f2 = this.mScale;
        float f3 = this.mMinY;
        return new RectF(f * f2, f3 * f2, (f + this.mVbWidth) * f2, (f3 + this.mVbHeight) * f2);
    }

    private int hitTest(float f, float f2) {
        int i;
        if (!this.mResponsible || !this.mInvertible) {
            return getId();
        }
        float[] fArr = {f, f2};
        this.mInvViewBoxMatrix.mapPoints(fArr);
        int childCount = getChildCount() - 1;
        int i2 = -1;
        while (true) {
            i = i2;
            if (childCount < 0) {
                break;
            }
            Object childAt = getChildAt(childCount);
            if (childAt instanceof VirtualView) {
                i2 = ((VirtualView) childAt).hitTest(fArr);
            } else if (childAt instanceof SvgView) {
                i2 = ((SvgView) childAt).hitTest(f, f2);
            }
            if (i2 != -1) {
                i = i2;
                break;
            }
            childCount--;
        }
        int i3 = i;
        if (i == -1) {
            i3 = getId();
        }
        return i3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void defineBrush(Brush brush, String str) {
        this.mDefinedBrushes.put(str, brush);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void defineClipPath(VirtualView virtualView, String str) {
        this.mDefinedClipPaths.put(str, virtualView);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void defineMask(VirtualView virtualView, String str) {
        this.mDefinedMasks.put(str, virtualView);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void defineTemplate(VirtualView virtualView, String str) {
        this.mDefinedTemplates.put(str, virtualView);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void drawChildren(Canvas canvas) {
        int i;
        synchronized (this) {
            this.mRendered = true;
            this.mCanvas = canvas;
            if (this.mAlign != null) {
                RectF viewBox = getViewBox();
                float width = canvas.getWidth();
                float height = canvas.getHeight();
                boolean z = getParent() instanceof VirtualView;
                float f = width;
                float f2 = height;
                if (z) {
                    f = (float) PropHelper.fromRelative(this.mbbWidth, width, 0.0d, this.mScale, 12.0d);
                    f2 = (float) PropHelper.fromRelative(this.mbbHeight, height, 0.0d, this.mScale, 12.0d);
                }
                RectF rectF = new RectF(0.0f, 0.0f, f, f2);
                if (z) {
                    canvas.clipRect(rectF);
                }
                Matrix transform = ViewBox.getTransform(viewBox, rectF, this.mAlign, this.mMeetOrSlice);
                this.mInvertible = transform.invert(this.mInvViewBoxMatrix);
                canvas.concat(transform);
            }
            Paint paint = new Paint();
            paint.setFlags(385);
            paint.setTypeface(Typeface.DEFAULT);
            int i2 = 0;
            while (true) {
                int i3 = i2;
                if (i3 >= getChildCount()) {
                    break;
                }
                Object childAt = getChildAt(i3);
                if (childAt instanceof VirtualView) {
                    ((VirtualView) childAt).saveDefinition();
                }
                i2 = i3 + 1;
            }
            for (i = 0; i < getChildCount(); i++) {
                Object childAt2 = getChildAt(i);
                if (childAt2 instanceof VirtualView) {
                    VirtualView virtualView = (VirtualView) childAt2;
                    int saveAndSetupCanvas = virtualView.saveAndSetupCanvas(canvas);
                    virtualView.render(canvas, paint, 1.0f);
                    virtualView.restoreCanvas(canvas, saveAndSetupCanvas);
                    if (virtualView.isResponsible() && !this.mResponsible) {
                        this.mResponsible = true;
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void enableTouchEvents() {
        if (this.mResponsible) {
            return;
        }
        this.mResponsible = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Rect getCanvasBounds() {
        return this.mCanvas.getClipBounds();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Brush getDefinedBrush(String str) {
        return this.mDefinedBrushes.get(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public VirtualView getDefinedClipPath(String str) {
        return this.mDefinedClipPaths.get(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public VirtualView getDefinedMask(String str) {
        return this.mDefinedMasks.get(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public VirtualView getDefinedTemplate(String str) {
        return this.mDefinedTemplates.get(str);
    }

    public boolean interceptsTouchEvent(float f, float f2) {
        return true;
    }

    public void invalidate() {
        super.invalidate();
        Object parent = getParent();
        if (parent instanceof VirtualView) {
            if (this.mRendered) {
                this.mRendered = false;
                ((VirtualView) parent).getSvgView().invalidate();
                return;
            }
            return;
        }
        Bitmap bitmap = this.mBitmap;
        if (bitmap != null) {
            bitmap.recycle();
        }
        this.mBitmap = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isResponsible() {
        return this.mResponsible;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean notRendered() {
        return !this.mRendered;
    }

    protected void onDraw(Canvas canvas) {
        if (getParent() instanceof VirtualView) {
            return;
        }
        super.onDraw(canvas);
        if (this.mBitmap == null) {
            this.mBitmap = drawOutput();
        }
        Bitmap bitmap = this.mBitmap;
        if (bitmap != null) {
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
            Runnable runnable = this.toDataUrlTask;
            if (runnable != null) {
                runnable.run();
                this.toDataUrlTask = null;
            }
        }
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        invalidate();
    }

    public int reactTagForTouch(float f, float f2) {
        return hitTest(f, f2);
    }

    @ReactProp(name = "align")
    public void setAlign(String str) {
        this.mAlign = str;
        invalidate();
        clearChildCache();
    }

    @ReactProp(name = "bbHeight")
    public void setBbHeight(Dynamic dynamic) {
        this.mbbHeight = SVGLength.from(dynamic);
        invalidate();
        clearChildCache();
    }

    @ReactProp(name = "bbWidth")
    public void setBbWidth(Dynamic dynamic) {
        this.mbbWidth = SVGLength.from(dynamic);
        invalidate();
        clearChildCache();
    }

    public void setId(int i) {
        super.setId(i);
        SvgViewManager.setSvgView(i, this);
    }

    @ReactProp(name = "meetOrSlice")
    public void setMeetOrSlice(int i) {
        this.mMeetOrSlice = i;
        invalidate();
        clearChildCache();
    }

    @ReactProp(name = "minX")
    public void setMinX(float f) {
        this.mMinX = f;
        invalidate();
        clearChildCache();
    }

    @ReactProp(name = "minY")
    public void setMinY(float f) {
        this.mMinY = f;
        invalidate();
        clearChildCache();
    }

    @ReactProp(customType = "Color", name = "tintColor")
    public void setTintColor(@Nullable Integer num) {
        if (num == null) {
            this.mTintColor = 0;
        } else {
            this.mTintColor = num.intValue();
        }
        invalidate();
        clearChildCache();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setToDataUrlTask(Runnable runnable) {
        this.toDataUrlTask = runnable;
    }

    @ReactProp(name = "vbHeight")
    public void setVbHeight(float f) {
        this.mVbHeight = f;
        invalidate();
        clearChildCache();
    }

    @ReactProp(name = "vbWidth")
    public void setVbWidth(float f) {
        this.mVbWidth = f;
        invalidate();
        clearChildCache();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String toDataURL() {
        Bitmap createBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        clearChildCache();
        drawChildren(new Canvas(createBitmap));
        clearChildCache();
        invalidate();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        createBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        createBitmap.recycle();
        return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String toDataURL(int i, int i2) {
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        clearChildCache();
        drawChildren(new Canvas(createBitmap));
        clearChildCache();
        invalidate();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        createBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        createBitmap.recycle();
        return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
    }
}
