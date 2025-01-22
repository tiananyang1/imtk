package com.swmansion.gesturehandler;

import android.view.View;
import android.view.ViewGroup;

/* loaded from: classes08-dex2jar.jar:com/swmansion/gesturehandler/ViewConfigurationHelper.class */
public interface ViewConfigurationHelper {
    View getChildInDrawingOrderAtIndex(ViewGroup viewGroup, int i);

    PointerEventsConfig getPointerEventsConfigForView(View view);

    boolean isViewClippingChildren(ViewGroup viewGroup);
}
