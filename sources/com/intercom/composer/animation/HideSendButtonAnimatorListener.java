package com.intercom.composer.animation;

import android.animation.Animator;
import android.support.v7.widget.RecyclerView;
import com.intercom.composer.ComposerAnimationStateListener;
import com.intercom.composer.input.Input;
import com.intercom.composer.pager.ComposerPagerAdapter;
import java.util.List;

/* loaded from: classes08-dex2jar.jar:com/intercom/composer/animation/HideSendButtonAnimatorListener.class */
public class HideSendButtonAnimatorListener extends SendButtonAnimatorListener {
    public HideSendButtonAnimatorListener(List<Input> list, ComposerPagerAdapter composerPagerAdapter, RecyclerView.Adapter adapter, ComposerAnimationStateListener composerAnimationStateListener) {
        super(list, composerPagerAdapter, adapter, composerAnimationStateListener);
    }

    @Override // com.intercom.composer.animation.SendButtonAnimatorListener, android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public /* bridge */ /* synthetic */ void onAnimationCancel(Animator animator) {
        super.onAnimationCancel(animator);
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        super.onAnimationEnd(animator);
        this.animationStateListener.setSendButtonVisibility(8);
        if (this.animationCanceled || !emptyInputAdded()) {
            return;
        }
        this.animationStateListener.onAnimationStateChanged(AnimationStatus.HIDDEN);
        this.inputs.remove(this.inputs.size() - 1);
        this.composerPagerAdapter.notifyDataSetChanged();
        this.recyclerAdapter.notifyItemRemoved(this.inputs.size());
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator animator) {
        super.onAnimationStart(animator);
        this.animationStateListener.onAnimationStateChanged(AnimationStatus.HIDING);
    }
}
