package com.intercom.composer.pager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/* loaded from: classes08-dex2jar.jar:com/intercom/composer/pager/NonSwipeableViewPager.class */
public class NonSwipeableViewPager extends ViewPager {
    public NonSwipeableViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return false;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return false;
    }
}
