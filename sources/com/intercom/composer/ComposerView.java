package com.intercom.composer;

import android.app.Activity;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.intercom.composer.animation.AnimationStatus;
import com.intercom.composer.animation.EditTextLayoutAnimator;
import com.intercom.composer.animation.EditTextLayoutAnimatorInternalListener;
import com.intercom.composer.animation.HideSendButtonAnimatorListener;
import com.intercom.composer.animation.SendButtonAnimator;
import com.intercom.composer.animation.ShowSendButtonAnimatorListener;
import com.intercom.composer.input.Input;
import com.intercom.composer.input.iconbar.InputClickedListener;
import com.intercom.composer.input.iconbar.InputIconRecyclerDecoration;
import com.intercom.composer.input.iconbar.InputIconsRecyclerAdapter;
import com.intercom.composer.input.iconbar.InputSelectedListener;
import com.intercom.composer.input.text.TextInput;
import com.intercom.composer.input.text.listener.OnSendButtonClickedListener;
import com.intercom.composer.input.text.listener.SendButtonClickListener;
import com.intercom.composer.input.text.options.InputOptionImageViewClickListener;
import com.intercom.composer.input.text.options.TextInputOption;
import com.intercom.composer.keyboard.KeyboardHelper;
import com.intercom.composer.keyboard.OrientationProvider;
import com.intercom.composer.pager.ComposerPagerAdapter;
import com.intercom.composer.watcher.OnSendButtonStateChangedListener;
import com.intercom.composer.watcher.SendButtonTextWatcher;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes08-dex2jar.jar:com/intercom/composer/ComposerView.class */
public class ComposerView extends LinearLayout implements OnSendButtonStateChangedListener, OnSendButtonClickedListener, InputSelectedListener, InputClickedListener, ComposerAnimationStateListener {

    @VisibleForTesting
    View composerLowerBorder;

    @VisibleForTesting
    View composerUpperBorder;

    @VisibleForTesting
    LinearLayout editTextLayout;

    @VisibleForTesting
    EditTextLayoutAnimator editTextLayoutAnimator;

    @VisibleForTesting
    InputIconsRecyclerAdapter inputIconsRecyclerAdapter;

    @VisibleForTesting
    RecyclerView inputIconsRecyclerView;

    @VisibleForTesting
    @Nullable
    OnInputSelectedListener inputSelectedListener;
    private final List<Input> inputs;

    @VisibleForTesting
    KeyboardHelper keyboardHelper;
    private final LinearLayoutManager layoutManager;

    @Nullable
    private OnSendButtonClickedListener onSendButtonClickListener;
    private final OrientationProvider orientationProvider;

    @VisibleForTesting
    ImageView sendButton;

    @VisibleForTesting
    AnimationStatus sendButtonAnimationStatus;

    @VisibleForTesting
    @Nullable
    SendButtonAnimator sendButtonAnimator;

    @VisibleForTesting
    View sendButtonFadingBackground;

    @VisibleForTesting
    final SendButtonTextWatcher sendButtonTextWatcher;

    @VisibleForTesting
    ViewPager viewPager;

    public ComposerView(Context context) {
        this(context, null);
    }

    public ComposerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ComposerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.inputs = new ArrayList();
        this.sendButtonAnimationStatus = AnimationStatus.UNKNOWN;
        setOrientation(1);
        inflate(context, C0123R.layout.intercom_composer_view_layout, this);
        this.composerUpperBorder = findViewById(C0123R.id.composer_upper_border);
        this.composerLowerBorder = findViewById(C0123R.id.composer_lower_border);
        this.editTextLayout = (LinearLayout) findViewById(C0123R.id.composer_edit_text_layout);
        this.inputIconsRecyclerView = findViewById(C0123R.id.composer_input_icons_recycler_view);
        this.viewPager = findViewById(C0123R.id.composer_view_pager);
        this.sendButton = (ImageView) findViewById(C0123R.id.send_button);
        this.sendButtonFadingBackground = findViewById(C0123R.id.send_button_fading_background);
        this.orientationProvider = new OrientationProvider(context);
        this.keyboardHelper = new KeyboardHelper((Activity) context, this.orientationProvider, this.editTextLayout, this.viewPager);
        this.editTextLayoutAnimator = new EditTextLayoutAnimator(this.editTextLayout);
        this.layoutManager = new LinearLayoutManager(context, 0, false);
        this.inputIconsRecyclerView.setLayoutManager(this.layoutManager);
        this.inputIconsRecyclerView.addItemDecoration(new InputIconRecyclerDecoration(context));
        this.sendButtonTextWatcher = new SendButtonTextWatcher(this);
    }

    private boolean editTextNeededBySomeInput() {
        Iterator<Input> it = this.inputs.iterator();
        while (it.hasNext()) {
            if (it.next() instanceof TextInput) {
                return true;
            }
        }
        return false;
    }

    private void removeViewFromParent(View view) {
        ViewParent parent = view.getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(view);
        }
    }

    private void setupTextInputs() {
        if (editTextNeededBySomeInput()) {
            this.editTextLayout.setVisibility(0);
            this.sendButton.setVisibility(0);
            this.sendButtonFadingBackground.setVisibility(0);
        } else {
            this.editTextLayout.setVisibility(8);
            this.sendButton.setVisibility(8);
            this.sendButtonFadingBackground.setVisibility(8);
        }
    }

    private void showKeyboard(EditText editText) {
        editText.requestFocus();
        ((InputMethodManager) editText.getContext().getSystemService("input_method")).showSoftInput(editText, 0);
        if (editText.getContext().getResources().getBoolean(C0123R.bool.intercom_composer_keyboard_takes_full_screen_in_landscape) && this.orientationProvider.getOrientation() == 2) {
            this.keyboardHelper.hideBehindKeyboardView();
        }
    }

    @Override // com.intercom.composer.watcher.OnSendButtonStateChangedListener
    public void animateSendButtonVisibility(boolean z) {
        SendButtonAnimator sendButtonAnimator = this.sendButtonAnimator;
        if (sendButtonAnimator != null) {
            sendButtonAnimator.animateButtonVisibility(z, this.sendButtonAnimationStatus);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void applyTheme(Context context, @ColorInt int i) {
        Drawable drawable = ContextCompat.getDrawable(context, C0123R.drawable.intercom_composer_send_background);
        drawable.setColorFilter(i, PorterDuff.Mode.SRC_ATOP);
        if (Build.VERSION.SDK_INT >= 16) {
            this.sendButton.setBackground(drawable);
        } else {
            this.sendButton.setBackgroundDrawable(drawable);
        }
    }

    public List<Input> getInputs() {
        return this.inputs;
    }

    public Input getSelectedInput() {
        return this.inputIconsRecyclerAdapter.getSelectedInput();
    }

    public int getTextInputHeight() {
        return this.editTextLayout.getHeight();
    }

    public void hideAllInputsExcept(List<String> list) {
        this.inputIconsRecyclerAdapter.hideAllInputsExcept(list);
    }

    @Override // com.intercom.composer.ComposerAnimationStateListener
    public void onAnimationStateChanged(AnimationStatus animationStatus) {
        this.sendButtonAnimationStatus = animationStatus;
    }

    public boolean onBackPressed() {
        Input selectedInput = getSelectedInput();
        if (selectedInput == null || selectedInput.equals(this.inputs.get(0))) {
            this.inputIconsRecyclerAdapter.deselectAllInputs();
        } else {
            selectInput(this.inputs.get(0), false, false);
        }
        return this.keyboardHelper.hideBehindKeyboardView();
    }

    public void onDestroy() {
        removeTextWatcher();
        this.keyboardHelper.onDestroy();
    }

    @Override // com.intercom.composer.input.iconbar.InputClickedListener
    public void onInputClicked(RecyclerView.ViewHolder viewHolder) {
        int adapterPosition = viewHolder.getAdapterPosition();
        if (adapterPosition < 0 || adapterPosition >= this.inputs.size()) {
            return;
        }
        selectInput(this.inputs.get(adapterPosition), true, true);
    }

    @Override // com.intercom.composer.input.iconbar.InputSelectedListener
    public void onInputSelected(Input input, int i, boolean z, boolean z2) {
        if (input instanceof TextInput) {
            EditText replaceEditText = replaceEditText((TextInput) input);
            this.editTextLayoutAnimator.showEditText(z2);
            if (z) {
                showKeyboard(replaceEditText);
            }
            animateSendButtonVisibility(!TextUtils.isEmpty(replaceEditText.getText()));
        } else {
            this.keyboardHelper.showBehindKeyboardView();
            this.editTextLayout.clearFocus();
            this.editTextLayoutAnimator.hideEditText();
            animateSendButtonVisibility(false);
        }
        updateColors(input.getBackgroundColor(), input.getBorderColor());
        this.viewPager.setCurrentItem(i, false);
    }

    @Override // com.intercom.composer.input.text.listener.OnSendButtonClickedListener
    public void onSendButtonClicked(CharSequence charSequence) {
        OnSendButtonClickedListener onSendButtonClickedListener = this.onSendButtonClickListener;
        if (onSendButtonClickedListener != null) {
            onSendButtonClickedListener.onSendButtonClicked(charSequence);
        }
    }

    @VisibleForTesting
    void removeTextWatcher() {
        int childCount = this.editTextLayout.getChildCount();
        if (childCount <= 0) {
            return;
        }
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= childCount) {
                return;
            }
            View childAt = this.editTextLayout.getChildAt(i2);
            if (childAt instanceof EditText) {
                ((EditText) childAt).removeTextChangedListener(this.sendButtonTextWatcher);
            }
            i = i2 + 1;
        }
    }

    EditText replaceEditText(TextInput textInput) {
        EditText editText = textInput.getEditText();
        List<TextInputOption> options = textInput.getOptions();
        this.editTextLayout.removeAllViews();
        removeViewFromParent(editText);
        this.editTextLayout.addView(editText, new LinearLayout.LayoutParams(0, -2, 1.0f));
        this.sendButton.setOnClickListener(new SendButtonClickListener(this, editText));
        editText.addTextChangedListener(this.sendButtonTextWatcher);
        animateSendButtonVisibility(!TextUtils.isEmpty(editText.getText()));
        if (options != null) {
            for (TextInputOption textInputOption : options) {
                int dimensionPixelSize = getResources().getDimensionPixelSize(C0123R.dimen.intercom_composer_editable_text_input_option_padding);
                int dimensionPixelSize2 = getResources().getDimensionPixelSize(C0123R.dimen.intercom_composer_editable_text_input_option_padding_bottom);
                ImageView imageView = new ImageView(getContext());
                imageView.setImageResource(textInputOption.getResourceId());
                imageView.setPadding(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize2);
                imageView.setOnClickListener(new InputOptionImageViewClickListener(textInputOption));
                this.editTextLayout.addView(imageView);
            }
        }
        return editText;
    }

    public boolean selectInput(Input input, boolean z, boolean z2) {
        OnInputSelectedListener onInputSelectedListener = this.inputSelectedListener;
        if (onInputSelectedListener != null) {
            onInputSelectedListener.onInputSelected(input);
        }
        return this.inputIconsRecyclerAdapter.selectInput(input, z, z2);
    }

    public void setComposerPagerAdapter(@NonNull ComposerPagerAdapter composerPagerAdapter) {
        this.viewPager.setAdapter(composerPagerAdapter);
        this.viewPager.setOffscreenPageLimit(this.inputs.size());
        this.sendButtonAnimator = new SendButtonAnimator(this.sendButtonFadingBackground, this.sendButton, new ShowSendButtonAnimatorListener(this.inputs, composerPagerAdapter, this.inputIconsRecyclerAdapter, this.layoutManager, this), new HideSendButtonAnimatorListener(this.inputs, composerPagerAdapter, this.inputIconsRecyclerAdapter, this));
    }

    public void setEditTextLayoutAnimationListener(EditTextLayoutAnimatorInternalListener editTextLayoutAnimatorInternalListener) {
        this.editTextLayoutAnimator.setEditTextLayoutAnimatorListener(editTextLayoutAnimatorInternalListener);
    }

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.inputIconsRecyclerAdapter = new InputIconsRecyclerAdapter(LayoutInflater.from(getContext()), this.inputs, this, this, fragmentManager);
        this.inputIconsRecyclerView.setAdapter(this.inputIconsRecyclerAdapter);
    }

    public void setInputSelectedListener(@NonNull OnInputSelectedListener onInputSelectedListener) {
        this.inputSelectedListener = onInputSelectedListener;
    }

    public void setInputs(List<? extends Input> list) {
        if (this.inputIconsRecyclerAdapter == null) {
            throw new IllegalStateException("Fragment manager should be set!");
        }
        this.inputs.clear();
        this.inputs.addAll(list);
        setupTextInputs();
        this.inputIconsRecyclerAdapter.notifyDataSetChanged();
    }

    public void setOnSendButtonClickListener(@NonNull OnSendButtonClickedListener onSendButtonClickedListener) {
        this.onSendButtonClickListener = onSendButtonClickedListener;
    }

    @Override // com.intercom.composer.ComposerAnimationStateListener
    public void setSendButtonVisibility(int i) {
        this.sendButton.setVisibility(i);
    }

    public void showAllInputs() {
        this.inputIconsRecyclerAdapter.showAllInputs();
    }

    public void updateColors(@ColorRes int i, @ColorRes int i2) {
        this.editTextLayout.setBackgroundResource(i);
        this.inputIconsRecyclerView.setBackgroundResource(i);
        this.sendButtonFadingBackground.setBackgroundResource(i);
        this.composerUpperBorder.setBackgroundResource(i2);
        this.composerLowerBorder.setBackgroundResource(i2);
    }
}
