package com.intercom.composer.animation;

import android.app.Activity;
import android.content.ComponentCallbacks2;

/* loaded from: classes08-dex2jar.jar:com/intercom/composer/animation/EditTextLayoutAnimatorInternalListener.class */
public class EditTextLayoutAnimatorInternalListener {
    private final Activity activity;

    public EditTextLayoutAnimatorInternalListener(Activity activity) {
        this.activity = activity;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onEditTextLayoutAnimationEnd(AnimationStatus animationStatus) {
        ComponentCallbacks2 componentCallbacks2 = this.activity;
        if (componentCallbacks2 instanceof EditTextLayoutAnimatorListener) {
            ((EditTextLayoutAnimatorListener) componentCallbacks2).onEditTextLayoutAnimationEnd(animationStatus);
        }
    }
}
