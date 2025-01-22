package com.intercom.composer.keyboard;

import android.app.Activity;
import android.support.annotation.Px;
import android.view.View;
import android.view.Window;

/* loaded from: classes08-dex2jar.jar:com/intercom/composer/keyboard/KeyboardHelper.class */
public class KeyboardHelper implements OnKeyboardVisibilityListener {
    private final View behindKeyboardView;
    private final View editText;
    private final KeyboardManager keyboardManager;
    private final OrientationProvider orientationProvider;
    private final Window window;

    public KeyboardHelper(Activity activity, OrientationProvider orientationProvider, View view, View view2) {
        this(new KeyboardManager(activity, orientationProvider), orientationProvider, activity.getWindow(), view, view2);
    }

    KeyboardHelper(KeyboardManager keyboardManager, OrientationProvider orientationProvider, Window window, View view, View view2) {
        if (view2 == null) {
            throw new IllegalArgumentException("behindKeyboardView can not be null!");
        }
        if (view == null) {
            throw new IllegalArgumentException("editText can not be null!");
        }
        this.editText = view;
        this.behindKeyboardView = view2;
        this.window = window;
        this.window.setSoftInputMode(19);
        this.orientationProvider = orientationProvider;
        this.keyboardManager = keyboardManager;
        this.keyboardManager.setOnKeyboardVisibilityListener(this);
    }

    private boolean isBehindKeyboardViewVisible() {
        return this.behindKeyboardView.getLayoutParams().height != 0;
    }

    public boolean hideBehindKeyboardView() {
        if (!isBehindKeyboardViewVisible()) {
            return false;
        }
        this.behindKeyboardView.getLayoutParams().height = 0;
        this.behindKeyboardView.requestLayout();
        this.window.setSoftInputMode(16);
        return true;
    }

    public void onDestroy() {
        this.keyboardManager.removeGlobalLayoutListener();
    }

    @Override // com.intercom.composer.keyboard.OnKeyboardVisibilityListener
    public void onKeyboardVisibilityChanged(boolean z, @Px int i) {
        if (!z) {
            if (isBehindKeyboardViewVisible()) {
                this.window.setSoftInputMode(32);
                return;
            } else {
                this.window.setSoftInputMode(16);
                return;
            }
        }
        this.window.setSoftInputMode(16);
        if (isBehindKeyboardViewVisible()) {
            this.behindKeyboardView.getLayoutParams().height = 0;
            this.behindKeyboardView.requestLayout();
        }
    }

    public void showBehindKeyboardView() {
        int orientation = this.orientationProvider.getOrientation();
        if (!this.keyboardManager.isShowingKeyboard()) {
            if (isBehindKeyboardViewVisible()) {
                return;
            }
            this.behindKeyboardView.getLayoutParams().height = this.keyboardManager.getKeyboardHeight(orientation);
            this.behindKeyboardView.requestLayout();
            this.window.setSoftInputMode(32);
            return;
        }
        this.behindKeyboardView.getLayoutParams().height = this.keyboardManager.getKeyboardHeight(orientation);
        this.behindKeyboardView.requestLayout();
        this.window.setSoftInputMode(32);
        if (this.keyboardManager.isShowingKeyboard()) {
            this.keyboardManager.hideSoftInput(this.editText);
        }
    }
}
