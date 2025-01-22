package com.intercom.input.gallery;

import android.graphics.Rect;
import android.support.annotation.DimenRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/* loaded from: classes08-dex2jar.jar:com/intercom/input/gallery/HeadingMarginDecoration.class */
public class HeadingMarginDecoration extends RecyclerView.ItemDecoration {

    @DimenRes
    private final int heightRes;

    public HeadingMarginDecoration(@DimenRes int i) {
        this.heightRes = i;
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        int integer = recyclerView.getResources().getInteger(C0134R.integer.intercom_composer_expanded_column_count);
        int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
        if (childAdapterPosition < 0 || childAdapterPosition >= integer) {
            return;
        }
        rect.set(0, recyclerView.getResources().getDimensionPixelOffset(this.heightRes), 0, 0);
    }
}
