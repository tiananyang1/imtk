package com.intercom.composer.input.empty;

import android.content.Context;
import android.graphics.drawable.Drawable;
import com.intercom.composer.input.IconProvider;
import com.intercom.composer.input.Input;

/* loaded from: classes08-dex2jar.jar:com/intercom/composer/input/empty/EmptyInput.class */
public class EmptyInput extends Input<EmptyFragment> {
    public EmptyInput() {
        super("Empty", new IconProvider() { // from class: com.intercom.composer.input.empty.EmptyInput.1
            @Override // com.intercom.composer.input.IconProvider
            public Drawable getIconDrawable(String str, Context context) {
                return null;
            }
        });
    }

    @Override // com.intercom.composer.input.Input
    public EmptyFragment createFragment() {
        return new EmptyFragment();
    }
}
