package com.swmansion.gesturehandler;

/* loaded from: classes08-dex2jar.jar:com/swmansion/gesturehandler/BaseGestureHandlerInteractionController.class */
public abstract class BaseGestureHandlerInteractionController implements GestureHandlerInteractionController {
    @Override // com.swmansion.gesturehandler.GestureHandlerInteractionController
    public boolean shouldRecognizeSimultaneously(GestureHandler gestureHandler, GestureHandler gestureHandler2) {
        return false;
    }

    @Override // com.swmansion.gesturehandler.GestureHandlerInteractionController
    public boolean shouldRequireHandlerToWaitForFailure(GestureHandler gestureHandler, GestureHandler gestureHandler2) {
        return false;
    }

    @Override // com.swmansion.gesturehandler.GestureHandlerInteractionController
    public boolean shouldWaitForHandlerFailure(GestureHandler gestureHandler, GestureHandler gestureHandler2) {
        return false;
    }
}
