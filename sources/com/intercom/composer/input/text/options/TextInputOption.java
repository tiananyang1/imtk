package com.intercom.composer.input.text.options;

import android.support.annotation.DrawableRes;

/* loaded from: classes08-dex2jar.jar:com/intercom/composer/input/text/options/TextInputOption.class */
public class TextInputOption {

    @DrawableRes
    private final int iconResource;
    private final InputOptionClickListener inputOptionClickListener;

    public TextInputOption(@DrawableRes int i, InputOptionClickListener inputOptionClickListener) {
        this.iconResource = i;
        this.inputOptionClickListener = inputOptionClickListener;
    }

    @DrawableRes
    public int getResourceId() {
        return this.iconResource;
    }

    public void inputOptionClicked() {
        this.inputOptionClickListener.onInputOptionClicked();
    }
}
