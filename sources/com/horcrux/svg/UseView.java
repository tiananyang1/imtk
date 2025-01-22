package com.horcrux.svg;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.microsoft.codepush.react.CodePushConstants;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes08-dex2jar.jar:com/horcrux/svg/UseView.class */
class UseView extends RenderableView {

    /* renamed from: mH */
    private SVGLength f59mH;
    private String mHref;

    /* renamed from: mW */
    private SVGLength f60mW;

    /* renamed from: mX */
    private SVGLength f61mX;

    /* renamed from: mY */
    private SVGLength f62mY;

    public UseView(ReactContext reactContext) {
        super(reactContext);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.horcrux.svg.RenderableView, com.horcrux.svg.VirtualView
    public void draw(Canvas canvas, Paint paint, float f) {
        VirtualView definedTemplate = getSvgView().getDefinedTemplate(this.mHref);
        if (definedTemplate == null) {
            FLog.w(CodePushConstants.REACT_NATIVE_LOG_TAG, "`Use` element expected a pre-defined svg template as `href` prop, template named: " + this.mHref + " is not defined.");
            return;
        }
        definedTemplate.clearCache();
        canvas.translate((float) relativeOnWidth(this.f61mX), (float) relativeOnHeight(this.f62mY));
        boolean z = definedTemplate instanceof RenderableView;
        if (z) {
            ((RenderableView) definedTemplate).mergeProperties(this);
        }
        int saveAndSetupCanvas = definedTemplate.saveAndSetupCanvas(canvas);
        clip(canvas, paint);
        if (definedTemplate instanceof SymbolView) {
            ((SymbolView) definedTemplate).drawSymbol(canvas, paint, f, (float) relativeOnWidth(this.f60mW), (float) relativeOnHeight(this.f59mH));
        } else {
            definedTemplate.draw(canvas, paint, f * this.mOpacity);
        }
        setClientRect(definedTemplate.getClientRect());
        definedTemplate.restoreCanvas(canvas, saveAndSetupCanvas);
        if (z) {
            ((RenderableView) definedTemplate).resetProperties();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.horcrux.svg.RenderableView, com.horcrux.svg.VirtualView
    public Path getPath(Canvas canvas, Paint paint) {
        VirtualView definedTemplate = getSvgView().getDefinedTemplate(this.mHref);
        if (definedTemplate == null) {
            FLog.w(CodePushConstants.REACT_NATIVE_LOG_TAG, "`Use` element expected a pre-defined svg template as `href` prop, template named: " + this.mHref + " is not defined.");
            return null;
        }
        Path path = definedTemplate.getPath(canvas, paint);
        Path path2 = new Path();
        Matrix matrix = new Matrix();
        matrix.setTranslate((float) relativeOnWidth(this.f61mX), (float) relativeOnHeight(this.f62mY));
        path.transform(matrix, path2);
        return path2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.horcrux.svg.RenderableView, com.horcrux.svg.VirtualView
    public int hitTest(float[] fArr) {
        if (!this.mInvertible || !this.mTransformInvertible) {
            return -1;
        }
        float[] fArr2 = new float[2];
        this.mInvMatrix.mapPoints(fArr2, fArr);
        this.mInvTransform.mapPoints(fArr2);
        VirtualView definedTemplate = getSvgView().getDefinedTemplate(this.mHref);
        if (definedTemplate == null) {
            FLog.w(CodePushConstants.REACT_NATIVE_LOG_TAG, "`Use` element expected a pre-defined svg template as `href` prop, template named: " + this.mHref + " is not defined.");
            return -1;
        }
        int hitTest = definedTemplate.hitTest(fArr2);
        if (hitTest == -1) {
            return -1;
        }
        int i = hitTest;
        if (!definedTemplate.isResponsible()) {
            if (hitTest != definedTemplate.getId()) {
                return hitTest;
            }
            i = getId();
        }
        return i;
    }

    @ReactProp(name = SettingsJsonConstants.ICON_HEIGHT_KEY)
    public void setHeight(Dynamic dynamic) {
        this.f59mH = SVGLength.from(dynamic);
        invalidate();
    }

    @ReactProp(name = "href")
    public void setHref(String str) {
        this.mHref = str;
        invalidate();
    }

    @ReactProp(name = SettingsJsonConstants.ICON_WIDTH_KEY)
    public void setWidth(Dynamic dynamic) {
        this.f60mW = SVGLength.from(dynamic);
        invalidate();
    }

    @ReactProp(name = "x")
    public void setX(Dynamic dynamic) {
        this.f61mX = SVGLength.from(dynamic);
        invalidate();
    }

    @ReactProp(name = "y")
    public void setY(Dynamic dynamic) {
        this.f62mY = SVGLength.from(dynamic);
        invalidate();
    }
}
