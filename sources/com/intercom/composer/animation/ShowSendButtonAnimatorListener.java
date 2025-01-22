package com.intercom.composer.animation;

import android.animation.Animator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.intercom.composer.ComposerAnimationStateListener;
import com.intercom.composer.input.Input;
import com.intercom.composer.input.empty.EmptyInput;
import com.intercom.composer.pager.ComposerPagerAdapter;
import java.util.List;

/* loaded from: classes08-dex2jar.jar:com/intercom/composer/animation/ShowSendButtonAnimatorListener.class */
public class ShowSendButtonAnimatorListener extends SendButtonAnimatorListener {
    private final LinearLayoutManager layoutManager;

    public ShowSendButtonAnimatorListener(List<Input> list, ComposerPagerAdapter composerPagerAdapter, RecyclerView.Adapter adapter, LinearLayoutManager linearLayoutManager, ComposerAnimationStateListener composerAnimationStateListener) {
        super(list, composerPagerAdapter, adapter, composerAnimationStateListener);
        this.layoutManager = linearLayoutManager;
    }

    @Override // com.intercom.composer.animation.SendButtonAnimatorListener, android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public /* bridge */ /* synthetic */ void onAnimationCancel(Animator animator) {
        super.onAnimationCancel(animator);
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        super.onAnimationEnd(animator);
        if (this.animationCanceled || emptyInputAdded()) {
            return;
        }
        this.animationStateListener.onAnimationStateChanged(AnimationStatus.SHOWN);
        this.inputs.add(new EmptyInput());
        this.composerPagerAdapter.notifyDataSetChanged();
        int size = this.inputs.size() - 1;
        this.recyclerAdapter.notifyItemInserted(size);
        if (this.layoutManager.findLastVisibleItemPosition() == size - 1) {
            this.layoutManager.scrollToPosition(size);
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator animator) {
        super.onAnimationStart(animator);
        this.animationStateListener.onAnimationStateChanged(AnimationStatus.SHOWING);
        this.animationStateListener.setSendButtonVisibility(0);
    }
}
