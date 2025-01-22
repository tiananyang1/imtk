package com.intercom.composer.input.iconbar;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.VisibleForTesting;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.intercom.composer.C0123R;

/* loaded from: classes08-dex2jar.jar:com/intercom/composer/input/iconbar/InputIconRecyclerDecoration.class */
public class InputIconRecyclerDecoration extends RecyclerView.ItemDecoration {

    @VisibleForTesting
    final int startSpacing;

    public InputIconRecyclerDecoration(Context context) {
        this.startSpacing = context.getResources().getDimensionPixelSize(C0123R.dimen.intercom_composer_icon_bar_left_spacing);
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        if (recyclerView.getChildLayoutPosition(view) == 0) {
            rect.set(this.startSpacing, 0, 0, 0);
        }
    }
}
