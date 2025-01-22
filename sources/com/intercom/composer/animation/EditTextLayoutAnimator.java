package com.intercom.composer.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.annotation.VisibleForTesting;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.view.View;
import android.widget.LinearLayout;

/* loaded from: classes08-dex2jar.jar:com/intercom/composer/animation/EditTextLayoutAnimator.class */
public class EditTextLayoutAnimator {
    private static final long EDIT_TEXT_ANIMATION_MS = 350;
    private ObjectAnimator animator;
    private final View editText;
    private EditTextLayoutAnimatorInternalListener editTextLayoutAnimatorListener;

    @VisibleForTesting
    AnimationStatus animationStatus = AnimationStatus.SHOWN;

    @VisibleForTesting
    final Animator.AnimatorListener showEditTextAnimationListener = new AnimatorListenerAdapter() { // from class: com.intercom.composer.animation.EditTextLayoutAnimator.1
        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            super.onAnimationEnd(animator);
            EditTextLayoutAnimator.this.animationStatus = AnimationStatus.SHOWN;
            if (EditTextLayoutAnimator.this.editTextLayoutAnimatorListener != null) {
                EditTextLayoutAnimator.this.editTextLayoutAnimatorListener.onEditTextLayoutAnimationEnd(AnimationStatus.SHOWN);
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            super.onAnimationStart(animator);
            EditTextLayoutAnimator.this.animationStatus = AnimationStatus.SHOWING;
        }
    };

    @VisibleForTesting
    final Animator.AnimatorListener hideEditTextAnimationListener = new AnimatorListenerAdapter() { // from class: com.intercom.composer.animation.EditTextLayoutAnimator.2
        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            super.onAnimationEnd(animator);
            EditTextLayoutAnimator.this.animationStatus = AnimationStatus.HIDDEN;
            if (EditTextLayoutAnimator.this.editTextLayoutAnimatorListener != null) {
                EditTextLayoutAnimator.this.editTextLayoutAnimatorListener.onEditTextLayoutAnimationEnd(AnimationStatus.HIDDEN);
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            super.onAnimationStart(animator);
            EditTextLayoutAnimator.this.animationStatus = AnimationStatus.HIDING;
        }
    };

    @VisibleForTesting
    final ValueAnimator.AnimatorUpdateListener animationUpdateListener = new ValueAnimator.AnimatorUpdateListener() { // from class: com.intercom.composer.animation.EditTextLayoutAnimator.3
        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            ((LinearLayout.LayoutParams) EditTextLayoutAnimator.this.editText.getLayoutParams()).bottomMargin = ((Float) valueAnimator.getAnimatedValue()).intValue();
            EditTextLayoutAnimator.this.editText.requestLayout();
        }
    };

    public EditTextLayoutAnimator(View view) {
        this.editText = view;
    }

    public void hideEditText() {
        ObjectAnimator objectAnimator;
        if (this.animationStatus == AnimationStatus.SHOWING && (objectAnimator = this.animator) != null) {
            objectAnimator.cancel();
        }
        if (this.animationStatus == AnimationStatus.SHOWN) {
            this.animator = ObjectAnimator.ofFloat(this.editText, "layout_marginBottom", 0.0f, -r0.getHeight());
            this.animator.setDuration(EDIT_TEXT_ANIMATION_MS);
            this.animator.setInterpolator(new FastOutSlowInInterpolator());
            this.animator.addUpdateListener(this.animationUpdateListener);
            this.animator.addListener(this.hideEditTextAnimationListener);
            this.animator.start();
        }
    }

    public void setEditTextLayoutAnimatorListener(EditTextLayoutAnimatorInternalListener editTextLayoutAnimatorInternalListener) {
        this.editTextLayoutAnimatorListener = editTextLayoutAnimatorInternalListener;
    }

    public void showEditText(boolean z) {
        ObjectAnimator objectAnimator;
        if (this.animationStatus == AnimationStatus.HIDING && (objectAnimator = this.animator) != null) {
            objectAnimator.cancel();
        }
        if (this.animationStatus == AnimationStatus.HIDDEN) {
            this.animator = ObjectAnimator.ofFloat(this.editText, "layout_marginBottom", -r0.getHeight(), 0.0f);
            this.animator.setDuration(z ? 350L : 0L);
            this.animator.setInterpolator(new FastOutSlowInInterpolator());
            this.animator.addUpdateListener(this.animationUpdateListener);
            this.animator.addListener(this.showEditTextAnimationListener);
            this.animator.start();
        }
    }
}
