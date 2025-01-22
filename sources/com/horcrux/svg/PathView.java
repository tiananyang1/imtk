package com.horcrux.svg;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.horcrux.svg.PropHelper;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes08-dex2jar.jar:com/horcrux/svg/PathView.class */
class PathView extends RenderableView {
    private Path mPath;

    public PathView(ReactContext reactContext) {
        super(reactContext);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.horcrux.svg.RenderableView, com.horcrux.svg.VirtualView
    public Path getPath(Canvas canvas, Paint paint) {
        return this.mPath;
    }

    @ReactProp(name = "d")
    public void setD(String str) {
        this.mPath = new PropHelper.PathParser(str, this.mScale).getPath();
        invalidate();
    }
}
