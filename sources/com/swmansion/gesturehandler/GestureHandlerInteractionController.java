package com.swmansion.gesturehandler;

/* loaded from: classes08-dex2jar.jar:com/swmansion/gesturehandler/GestureHandlerInteractionController.class */
public interface GestureHandlerInteractionController {
    boolean shouldHandlerBeCancelledBy(GestureHandler gestureHandler, GestureHandler gestureHandler2);

    boolean shouldRecognizeSimultaneously(GestureHandler gestureHandler, GestureHandler gestureHandler2);

    boolean shouldRequireHandlerToWaitForFailure(GestureHandler gestureHandler, GestureHandler gestureHandler2);

    boolean shouldWaitForHandlerFailure(GestureHandler gestureHandler, GestureHandler gestureHandler2);
}
