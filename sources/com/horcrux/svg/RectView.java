package com.horcrux.svg;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes08-dex2jar.jar:com/horcrux/svg/RectView.class */
class RectView extends RenderableView {

    /* renamed from: mH */
    private SVGLength f53mH;
    private SVGLength mRx;
    private SVGLength mRy;

    /* renamed from: mW */
    private SVGLength f54mW;

    /* renamed from: mX */
    private SVGLength f55mX;

    /* renamed from: mY */
    private SVGLength f56mY;

    public RectView(ReactContext reactContext) {
        super(reactContext);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.horcrux.svg.RenderableView, com.horcrux.svg.VirtualView
    public Path getPath(Canvas canvas, Paint paint) {
        double d;
        double d2;
        Path path = new Path();
        double relativeOnWidth = relativeOnWidth(this.f55mX);
        double relativeOnHeight = relativeOnHeight(this.f56mY);
        double relativeOnWidth2 = relativeOnWidth(this.f54mW);
        double relativeOnHeight2 = relativeOnHeight(this.f53mH);
        double relativeOnWidth3 = relativeOnWidth(this.mRx);
        double relativeOnHeight3 = relativeOnHeight(this.mRy);
        if (relativeOnWidth3 == 0.0d && relativeOnHeight3 == 0.0d) {
            path.addRect((float) relativeOnWidth, (float) relativeOnHeight, (float) (relativeOnWidth + relativeOnWidth2), (float) (relativeOnHeight + relativeOnHeight2), Path.Direction.CW);
            return path;
        }
        if (relativeOnWidth3 == 0.0d) {
            d = relativeOnHeight3;
            d2 = relativeOnHeight3;
        } else {
            d = relativeOnWidth3;
            d2 = relativeOnHeight3;
            if (relativeOnHeight3 == 0.0d) {
                d2 = relativeOnWidth3;
                d = relativeOnWidth3;
            }
        }
        double d3 = relativeOnWidth2 / 2.0d;
        double d4 = d;
        if (d > d3) {
            d4 = d3;
        }
        double d5 = relativeOnHeight2 / 2.0d;
        if (d2 > d5) {
            d2 = d5;
        }
        path.addRoundRect(new RectF((float) relativeOnWidth, (float) relativeOnHeight, (float) (relativeOnWidth + relativeOnWidth2), (float) (relativeOnHeight + relativeOnHeight2)), (float) d4, (float) d2, Path.Direction.CW);
        return path;
    }

    @ReactProp(name = SettingsJsonConstants.ICON_HEIGHT_KEY)
    public void setHeight(Dynamic dynamic) {
        this.f53mH = SVGLength.from(dynamic);
        invalidate();
    }

    @ReactProp(name = "rx")
    public void setRx(Dynamic dynamic) {
        this.mRx = SVGLength.from(dynamic);
        invalidate();
    }

    @ReactProp(name = "ry")
    public void setRy(Dynamic dynamic) {
        this.mRy = SVGLength.from(dynamic);
        invalidate();
    }

    @ReactProp(name = SettingsJsonConstants.ICON_WIDTH_KEY)
    public void setWidth(Dynamic dynamic) {
        this.f54mW = SVGLength.from(dynamic);
        invalidate();
    }

    @ReactProp(name = "x")
    public void setX(Dynamic dynamic) {
        this.f55mX = SVGLength.from(dynamic);
        invalidate();
    }

    @ReactProp(name = "y")
    public void setY(Dynamic dynamic) {
        this.f56mY = SVGLength.from(dynamic);
        invalidate();
    }
}
