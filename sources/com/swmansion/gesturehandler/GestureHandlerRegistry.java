package com.swmansion.gesturehandler;

import android.view.View;
import java.util.ArrayList;

/* loaded from: classes08-dex2jar.jar:com/swmansion/gesturehandler/GestureHandlerRegistry.class */
public interface GestureHandlerRegistry {
    ArrayList<GestureHandler> getHandlersForView(View view);
}
