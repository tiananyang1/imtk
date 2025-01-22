package com.intercom.composer.input.text;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.EditText;
import com.intercom.composer.input.IconProvider;
import com.intercom.composer.input.Input;
import com.intercom.composer.input.InputFragment;
import com.intercom.composer.input.text.options.TextInputOption;
import java.util.List;

/* loaded from: classes08-dex2jar.jar:com/intercom/composer/input/text/TextInput.class */
public abstract class TextInput<T extends InputFragment> extends Input<T> {
    private EditText editText;

    @Nullable
    private final List<TextInputOption> options;
    private SendTextCallback sendTextCallback;

    public TextInput(String str, IconProvider iconProvider, SendTextCallback sendTextCallback) {
        this(str, iconProvider, sendTextCallback, null);
    }

    public TextInput(String str, IconProvider iconProvider, SendTextCallback sendTextCallback, @Nullable List<TextInputOption> list) {
        super(str, iconProvider);
        this.sendTextCallback = sendTextCallback;
        this.options = list;
    }

    @NonNull
    protected abstract EditText createEditText();

    @NonNull
    public EditText getEditText() {
        if (this.editText == null) {
            this.editText = createEditText();
        }
        return this.editText;
    }

    @Nullable
    public List<TextInputOption> getOptions() {
        return this.options;
    }

    public void sendTextBack(CharSequence charSequence) {
        this.sendTextCallback.textToBeSent(this, charSequence);
    }
}
