package com.intercom.input.gallery.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.intercom.input.gallery.C0134R;

/* loaded from: classes08-dex2jar.jar:com/intercom/input/gallery/adapter/SquareLayout.class */
public class SquareLayout extends FrameLayout {
    private static final int WIDTH_TYPE = 0;
    private int measureType;

    public SquareLayout(Context context) {
        super(context);
    }

    public SquareLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        checkAttributes(context, attributeSet);
    }

    public SquareLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        checkAttributes(context, attributeSet);
    }

    @TargetApi(21)
    public SquareLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        checkAttributes(context, attributeSet);
    }

    private void checkAttributes(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, C0134R.styleable.intercom_composer_square_layout, 0, 0);
        try {
            this.measureType = obtainStyledAttributes.getInteger(C0134R.styleable.intercom_composer_square_layout_intercom_composer_measure_type, 0);
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        if (this.measureType == 0) {
            super.onMeasure(i, i);
        } else {
            super.onMeasure(i2, i2);
        }
    }
}
