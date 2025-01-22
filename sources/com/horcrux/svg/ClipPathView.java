package com.horcrux.svg;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Paint;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactContext;
import com.microsoft.codepush.react.CodePushConstants;

/* JADX INFO: Access modifiers changed from: package-private */
@SuppressLint({"ViewConstructor"})
/* loaded from: classes08-dex2jar.jar:com/horcrux/svg/ClipPathView.class */
public class ClipPathView extends GroupView {
    public ClipPathView(ReactContext reactContext) {
        super(reactContext);
        this.mClipRule = 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.horcrux.svg.GroupView, com.horcrux.svg.RenderableView, com.horcrux.svg.VirtualView
    public void draw(Canvas canvas, Paint paint, float f) {
        FLog.w(CodePushConstants.REACT_NATIVE_LOG_TAG, "RNSVG: ClipPath can't be drawn, it should be defined as a child component for `Defs` ");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.horcrux.svg.GroupView, com.horcrux.svg.RenderableView, com.horcrux.svg.VirtualView
    public int hitTest(float[] fArr) {
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.horcrux.svg.VirtualView
    public boolean isResponsible() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.horcrux.svg.RenderableView
    public void mergeProperties(RenderableView renderableView) {
    }

    @Override // com.horcrux.svg.GroupView, com.horcrux.svg.RenderableView
    void resetProperties() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.horcrux.svg.GroupView, com.horcrux.svg.VirtualView
    public void saveDefinition() {
        getSvgView().defineClipPath(this, this.mName);
    }
}
