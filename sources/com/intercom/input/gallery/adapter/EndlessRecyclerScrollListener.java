package com.intercom.input.gallery.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/* loaded from: classes08-dex2jar.jar:com/intercom/input/gallery/adapter/EndlessRecyclerScrollListener.class */
public class EndlessRecyclerScrollListener extends RecyclerView.OnScrollListener {

    @NonNull
    private final EndlessScrollListener endlessScrollListener;

    @NonNull
    private final LinearLayoutManager layoutManager;
    private int maxCount = Integer.MIN_VALUE;

    public EndlessRecyclerScrollListener(@NonNull LinearLayoutManager linearLayoutManager, @NonNull EndlessScrollListener endlessScrollListener) {
        this.layoutManager = linearLayoutManager;
        this.endlessScrollListener = endlessScrollListener;
    }

    public void onScrolled(RecyclerView recyclerView, int i, int i2) {
        super.onScrolled(recyclerView, i, i2);
        int childCount = recyclerView.getChildCount();
        int itemCount = this.layoutManager.getItemCount();
        if (itemCount - childCount > this.layoutManager.findFirstVisibleItemPosition() || itemCount >= this.maxCount) {
            return;
        }
        this.endlessScrollListener.onLoadMore();
    }

    public void setMaxCount(int i) {
        this.maxCount = i;
    }
}
