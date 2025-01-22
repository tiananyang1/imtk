package com.intercom.composer.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.v7.widget.RecyclerView;
import com.intercom.composer.ComposerAnimationStateListener;
import com.intercom.composer.input.Input;
import com.intercom.composer.input.empty.EmptyInput;
import com.intercom.composer.pager.ComposerPagerAdapter;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes08-dex2jar.jar:com/intercom/composer/animation/SendButtonAnimatorListener.class */
abstract class SendButtonAnimatorListener extends AnimatorListenerAdapter {
    boolean animationCanceled;
    final ComposerAnimationStateListener animationStateListener;
    final ComposerPagerAdapter composerPagerAdapter;
    protected final List<Input> inputs;
    final RecyclerView.Adapter recyclerAdapter;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SendButtonAnimatorListener(List<Input> list, ComposerPagerAdapter composerPagerAdapter, RecyclerView.Adapter adapter, ComposerAnimationStateListener composerAnimationStateListener) {
        this.inputs = list;
        this.composerPagerAdapter = composerPagerAdapter;
        this.recyclerAdapter = adapter;
        this.animationStateListener = composerAnimationStateListener;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean emptyInputAdded() {
        Iterator<Input> it = this.inputs.iterator();
        while (it.hasNext()) {
            if (it.next() instanceof EmptyInput) {
                return true;
            }
        }
        return false;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationCancel(Animator animator) {
        super.onAnimationCancel(animator);
        this.animationCanceled = true;
    }
}
