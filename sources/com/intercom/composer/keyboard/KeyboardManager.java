package com.intercom.composer.keyboard;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.Px;
import android.support.annotation.VisibleForTesting;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import com.intercom.composer.C0123R;

/* loaded from: classes08-dex2jar.jar:com/intercom/composer/keyboard/KeyboardManager.class */
class KeyboardManager implements ViewTreeObserver.OnGlobalLayoutListener, OnKeyboardVisibilityListener {
    private static final String KEY_KEYBOARD_HEIGHT_PORTRAIT = "keyboard_height_portrait";
    private static final String PREFERENCES_NAME = "keyboard";
    private final InputMethodManager inputMethodManager;

    @Nullable
    private OnKeyboardVisibilityListener onKeyboardVisibilityListener;
    private final OrientationProvider orientationProvider;

    @VisibleForTesting
    int previousRelayoutOrientation;
    private final SharedPreferences sharedPreferences;

    @VisibleForTesting
    boolean wasOpened;
    private final Window window;
    private final WindowManager windowManager;

    /* JADX INFO: Access modifiers changed from: package-private */
    public KeyboardManager(Activity activity, OrientationProvider orientationProvider) {
        this(activity.getWindow(), (WindowManager) activity.getSystemService("window"), (InputMethodManager) activity.getSystemService("input_method"), activity.getSharedPreferences(PREFERENCES_NAME, 0), orientationProvider);
    }

    @VisibleForTesting
    KeyboardManager(Window window, WindowManager windowManager, InputMethodManager inputMethodManager, SharedPreferences sharedPreferences, OrientationProvider orientationProvider) {
        this.window = window;
        this.windowManager = windowManager;
        this.inputMethodManager = inputMethodManager;
        this.sharedPreferences = sharedPreferences;
        this.orientationProvider = orientationProvider;
        this.window.getDecorView().getRootView().getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    @Px
    private int getCurrentKeyboardHeight() {
        View decorView = this.window.getDecorView();
        Rect rect = new Rect();
        decorView.getWindowVisibleDisplayFrame(rect);
        int height = decorView.getRootView().getHeight() - rect.bottom;
        int i = height;
        if (Build.VERSION.SDK_INT >= 18) {
            i = height - getSoftButtonsBarHeight();
        }
        return i;
    }

    @TargetApi(18)
    @Px
    private int getSoftButtonsBarHeight() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        Display defaultDisplay = this.windowManager.getDefaultDisplay();
        defaultDisplay.getMetrics(displayMetrics);
        int i = displayMetrics.heightPixels;
        defaultDisplay.getRealMetrics(displayMetrics);
        int i2 = displayMetrics.heightPixels;
        if (i2 > i) {
            return i2 - i;
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Px
    public int getKeyboardHeight(int i) {
        Resources resources = this.window.getContext().getResources();
        if (i != 1) {
            return resources.getDimensionPixelSize(C0123R.dimen.intercom_composer_keyboard_landscape_height);
        }
        return this.sharedPreferences.getInt(KEY_KEYBOARD_HEIGHT_PORTRAIT, resources.getDimensionPixelSize(C0123R.dimen.intercom_composer_keyboard_portrait_height));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void hideSoftInput(View view) {
        this.inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isShowingKeyboard() {
        return getCurrentKeyboardHeight() > 0;
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public void onGlobalLayout() {
        int currentKeyboardHeight = getCurrentKeyboardHeight();
        boolean z = currentKeyboardHeight > 0;
        int orientation = this.orientationProvider.getOrientation();
        if (z == this.wasOpened && orientation == this.previousRelayoutOrientation) {
            return;
        }
        this.wasOpened = z;
        this.previousRelayoutOrientation = orientation;
        onKeyboardVisibilityChanged(z, currentKeyboardHeight);
        OnKeyboardVisibilityListener onKeyboardVisibilityListener = this.onKeyboardVisibilityListener;
        if (onKeyboardVisibilityListener != null) {
            onKeyboardVisibilityListener.onKeyboardVisibilityChanged(z, currentKeyboardHeight);
        }
    }

    @Override // com.intercom.composer.keyboard.OnKeyboardVisibilityListener
    public void onKeyboardVisibilityChanged(boolean z, @Px int i) {
        if (z && this.orientationProvider.getOrientation() == 1) {
            this.sharedPreferences.edit().putInt(KEY_KEYBOARD_HEIGHT_PORTRAIT, i).apply();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void removeGlobalLayoutListener() {
        Window window = this.window;
        if (window != null) {
            ViewTreeObserver viewTreeObserver = window.getDecorView().getRootView().getViewTreeObserver();
            if (Build.VERSION.SDK_INT >= 16) {
                viewTreeObserver.removeOnGlobalLayoutListener(this);
            } else {
                viewTreeObserver.removeGlobalOnLayoutListener(this);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setOnKeyboardVisibilityListener(@Nullable OnKeyboardVisibilityListener onKeyboardVisibilityListener) {
        this.onKeyboardVisibilityListener = onKeyboardVisibilityListener;
    }
}
