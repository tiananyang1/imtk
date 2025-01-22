package com.swmansion.gesturehandler.react;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.PaintDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import io.fabric.sdk.android.services.common.AbstractSpiCall;

/* loaded from: classes08-dex2jar.jar:com/swmansion/gesturehandler/react/RNGestureHandlerButtonViewManager.class */
public class RNGestureHandlerButtonViewManager extends ViewGroupManager<ButtonViewGroup> {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes08-dex2jar.jar:com/swmansion/gesturehandler/react/RNGestureHandlerButtonViewManager$ButtonViewGroup.class */
    public static class ButtonViewGroup extends ViewGroup {
        static TypedValue sResolveOutValue = new TypedValue();
        static ButtonViewGroup sResponder;
        int mBackgroundColor;
        float mBorderRadius;
        boolean mNeedBackgroundUpdate;
        Integer mRippleColor;
        boolean mUseBorderless;
        boolean mUseForeground;

        public ButtonViewGroup(Context context) {
            super(context);
            this.mBackgroundColor = 0;
            this.mUseForeground = false;
            this.mUseBorderless = false;
            this.mBorderRadius = 0.0f;
            this.mNeedBackgroundUpdate = false;
            setClickable(true);
            setFocusable(true);
            this.mNeedBackgroundUpdate = true;
        }

        /* JADX WARN: Type inference failed for: r2v2, types: [int[], int[][]] */
        private Drawable applyRippleEffectWhenNeeded(Drawable drawable) {
            if (this.mRippleColor != null && drawable != null && Build.VERSION.SDK_INT >= 21 && (drawable instanceof RippleDrawable)) {
                ((RippleDrawable) drawable).setColor(new ColorStateList(new int[]{new int[]{android.R.attr.state_enabled}}, new int[]{this.mRippleColor.intValue()}));
            }
            return drawable;
        }

        private Drawable createSelectableDrawable() {
            int i = Build.VERSION.SDK_INT;
            getContext().getTheme().resolveAttribute(getResources().getIdentifier((!this.mUseBorderless || i < 21) ? "selectableItemBackground" : "selectableItemBackgroundBorderless", "attr", AbstractSpiCall.ANDROID_CLIENT_TYPE), sResolveOutValue, true);
            return i >= 21 ? getResources().getDrawable(sResolveOutValue.resourceId, getContext().getTheme()) : getResources().getDrawable(sResolveOutValue.resourceId);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void updateBackground() {
            if (this.mNeedBackgroundUpdate) {
                this.mNeedBackgroundUpdate = false;
                if (this.mBackgroundColor == 0) {
                    setBackground(null);
                }
                if (Build.VERSION.SDK_INT >= 23) {
                    setForeground(null);
                }
                if (this.mUseForeground && Build.VERSION.SDK_INT >= 23) {
                    setForeground(applyRippleEffectWhenNeeded(createSelectableDrawable()));
                    int i = this.mBackgroundColor;
                    if (i != 0) {
                        setBackgroundColor(i);
                        return;
                    }
                    return;
                }
                if (this.mBackgroundColor == 0 && this.mRippleColor == null) {
                    setBackground(createSelectableDrawable());
                    return;
                }
                PaintDrawable paintDrawable = new PaintDrawable(this.mBackgroundColor);
                Drawable createSelectableDrawable = createSelectableDrawable();
                float f = this.mBorderRadius;
                if (f != 0.0f) {
                    paintDrawable.setCornerRadius(f);
                    if (Build.VERSION.SDK_INT >= 21 && (createSelectableDrawable instanceof RippleDrawable)) {
                        PaintDrawable paintDrawable2 = new PaintDrawable(-1);
                        paintDrawable2.setCornerRadius(this.mBorderRadius);
                        ((RippleDrawable) createSelectableDrawable).setDrawableByLayerId(android.R.id.mask, paintDrawable2);
                    }
                }
                applyRippleEffectWhenNeeded(createSelectableDrawable);
                setBackground(new LayerDrawable(new Drawable[]{paintDrawable, createSelectableDrawable}));
            }
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDrawableHotspotChanged(float f, float f2) {
        }

        @Override // android.view.View
        public void drawableHotspotChanged(float f, float f2) {
            ButtonViewGroup buttonViewGroup = sResponder;
            if (buttonViewGroup == null || buttonViewGroup == this) {
                super.drawableHotspotChanged(f, f2);
            }
        }

        @Override // android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            if (super.onInterceptTouchEvent(motionEvent)) {
                return true;
            }
            onTouchEvent(motionEvent);
            return isPressed();
        }

        @Override // android.view.ViewGroup, android.view.View
        protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        }

        @Override // android.view.View
        public void setBackgroundColor(int i) {
            this.mBackgroundColor = i;
            this.mNeedBackgroundUpdate = true;
        }

        public void setBorderRadius(float f) {
            this.mBorderRadius = f * getResources().getDisplayMetrics().density;
            this.mNeedBackgroundUpdate = true;
        }

        @Override // android.view.View
        public void setPressed(boolean z) {
            if (z && sResponder == null) {
                sResponder = this;
            }
            if (!z || sResponder == this) {
                super.setPressed(z);
            }
            if (z || sResponder != this) {
                return;
            }
            sResponder = null;
        }

        public void setRippleColor(Integer num) {
            this.mRippleColor = num;
            this.mNeedBackgroundUpdate = true;
        }

        public void setUseBorderlessDrawable(boolean z) {
            this.mUseBorderless = z;
        }

        public void setUseDrawableOnForeground(boolean z) {
            this.mUseForeground = z;
            this.mNeedBackgroundUpdate = true;
        }
    }

    public ButtonViewGroup createViewInstance(ThemedReactContext themedReactContext) {
        return new ButtonViewGroup(themedReactContext);
    }

    public String getName() {
        return "RNGestureHandlerButton";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onAfterUpdateTransaction(ButtonViewGroup buttonViewGroup) {
        buttonViewGroup.updateBackground();
    }

    @ReactProp(name = "borderRadius")
    public void setBorderRadius(ButtonViewGroup buttonViewGroup, float f) {
        buttonViewGroup.setBorderRadius(f);
    }

    @ReactProp(name = "borderless")
    public void setBorderless(ButtonViewGroup buttonViewGroup, boolean z) {
        buttonViewGroup.setUseBorderlessDrawable(z);
    }

    @ReactProp(name = "enabled")
    public void setEnabled(ButtonViewGroup buttonViewGroup, boolean z) {
        buttonViewGroup.setEnabled(z);
    }

    @ReactProp(name = "foreground")
    @TargetApi(23)
    public void setForeground(ButtonViewGroup buttonViewGroup, boolean z) {
        buttonViewGroup.setUseDrawableOnForeground(z);
    }

    @ReactProp(name = "rippleColor")
    public void setRippleColor(ButtonViewGroup buttonViewGroup, Integer num) {
        buttonViewGroup.setRippleColor(num);
    }
}
