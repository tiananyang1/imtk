package com.horcrux.svg;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Paint;
import com.facebook.react.bridge.ReactContext;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes08-dex2jar.jar:com/horcrux/svg/DefsView.class */
class DefsView extends DefinitionView {
    public DefsView(ReactContext reactContext) {
        super(reactContext);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.horcrux.svg.DefinitionView, com.horcrux.svg.VirtualView
    public void draw(Canvas canvas, Paint paint, float f) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.horcrux.svg.VirtualView
    public void saveDefinition() {
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
}
