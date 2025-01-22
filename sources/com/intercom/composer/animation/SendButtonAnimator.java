package com.intercom.composer.animation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.util.Property;
import android.view.View;
import android.widget.ImageView;

/* loaded from: classes08-dex2jar.jar:com/intercom/composer/animation/SendButtonAnimator.class */
public class SendButtonAnimator {
    private static final String ALPHA = "alpha";
    private static final long SEND_BUTTON_ANIMATION_MS = 100;

    @VisibleForTesting
    @Nullable
    AnimatorSet animatorSet;

    @VisibleForTesting
    final View background;

    @VisibleForTesting
    final ValueAnimator.AnimatorUpdateListener backgroundListener = new ValueAnimator.AnimatorUpdateListener() { // from class: com.intercom.composer.animation.SendButtonAnimator.1
        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            SendButtonAnimator.this.background.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
        }
    };

    @VisibleForTesting
    final ImageView button;
    private ObjectAnimator hideSendAnimator;
    private final HideSendButtonAnimatorListener hideSendButtonAnimatorListener;
    private ObjectAnimator showSendAnimator;
    private final ShowSendButtonAnimatorListener showSendButtonAnimatorListener;

    public SendButtonAnimator(View view, ImageView imageView, ShowSendButtonAnimatorListener showSendButtonAnimatorListener, HideSendButtonAnimatorListener hideSendButtonAnimatorListener) {
        this.background = view;
        this.button = imageView;
        this.showSendButtonAnimatorListener = showSendButtonAnimatorListener;
        this.hideSendButtonAnimatorListener = hideSendButtonAnimatorListener;
        this.showSendAnimator = ObjectAnimator.ofPropertyValuesHolder(imageView, PropertyValuesHolder.ofFloat((Property<?, Float>) View.ALPHA, 0.0f, 1.0f), PropertyValuesHolder.ofFloat("scaleX", 0.6f, 1.0f), PropertyValuesHolder.ofFloat("scaleY", 0.6f, 1.0f));
        this.showSendAnimator.setStartDelay(50L);
        this.hideSendAnimator = ObjectAnimator.ofPropertyValuesHolder(imageView, PropertyValuesHolder.ofFloat((Property<?, Float>) View.ALPHA, 1.0f, 0.0f), PropertyValuesHolder.ofFloat("scaleX", 1.0f, 0.6f), PropertyValuesHolder.ofFloat("scaleY", 1.0f, 0.6f));
    }

    public void animateButtonVisibility(boolean z, AnimationStatus animationStatus) {
        boolean z2;
        if (z && (animationStatus == AnimationStatus.HIDING || animationStatus == AnimationStatus.HIDDEN || animationStatus == AnimationStatus.UNKNOWN)) {
            z2 = true;
        } else {
            if (z) {
                return;
            }
            if (animationStatus != AnimationStatus.SHOWING && animationStatus != AnimationStatus.SHOWN && animationStatus != AnimationStatus.UNKNOWN) {
                return;
            } else {
                z2 = false;
            }
        }
        AnimatorSet animatorSet = this.animatorSet;
        if (animatorSet != null) {
            animatorSet.cancel();
        }
        this.animatorSet = new AnimatorSet();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.background, ALPHA, z2 ? 1.0f : 0.0f);
        ofFloat.addUpdateListener(this.backgroundListener);
        this.animatorSet.playTogether(ofFloat, z2 ? this.showSendAnimator : this.hideSendAnimator);
        this.animatorSet.setDuration(SEND_BUTTON_ANIMATION_MS);
        this.animatorSet.addListener(z ? this.showSendButtonAnimatorListener : this.hideSendButtonAnimatorListener);
        this.animatorSet.start();
    }
}
