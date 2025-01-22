package com.intercom.composer;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.Window;

/* loaded from: classes08-dex2jar.jar:com/intercom/composer/WindowUtil.class */
public class WindowUtil {
    @TargetApi(19)
    public static void setFullscreenWindow(Window window, @ColorRes int i) {
        if (Build.VERSION.SDK_INT > 19) {
            window.getDecorView().setSystemUiVisibility(1280);
            setStatusBarColorRes(window, i);
        }
    }

    @TargetApi(21)
    private static void setStatusBarColor(@NonNull Window window, @ColorInt int i) {
        if (Build.VERSION.SDK_INT >= 21) {
            window.clearFlags(67108864);
            window.addFlags(Integer.MIN_VALUE);
            window.setStatusBarColor(i);
        }
    }

    @TargetApi(21)
    private static void setStatusBarColorRes(@NonNull Window window, @ColorRes int i) {
        setStatusBarColor(window, ContextCompat.getColor(window.getContext(), i));
    }
}
