package com.intercom.composer;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.intercom.composer.animation.EditTextLayoutAnimatorInternalListener;
import com.intercom.composer.input.Input;
import com.intercom.composer.input.text.TextInput;
import com.intercom.composer.input.text.listener.OnSendButtonClickedListener;
import com.intercom.composer.pager.ComposerPagerAdapter;
import com.sensorsdata.analytics.android.sdk.SensorsDataAutoTrackHelper;
import com.sensorsdata.analytics.android.sdk.SensorsDataInstrumented;
import java.util.List;

/* loaded from: classes08-dex2jar.jar:com/intercom/composer/ComposerFragment.class */
public class ComposerFragment extends Fragment {
    private static final String KEY_INITIAL_INPUT_IDENTIFIER = "initial_input_identifier";
    private static final String KEY_SHOW_KEYBOARD_FOR_INITIAL_INPUT = "show_keyboard_for_initial_input";
    private static final String KEY_THEME_COLOR = "theme_color";

    @VisibleForTesting
    @Nullable
    ComposerHost composerHost;

    @VisibleForTesting
    ComposerView composerView;

    @Nullable
    String initialInputIdentifier;

    @VisibleForTesting
    Input inputToBeRestored;

    @Nullable
    private OnInputSelectedListener onInputSelectedListener;
    private Runnable selectDefaultInputRunnable = new Runnable() { // from class: com.intercom.composer.ComposerFragment.1
        @Override // java.lang.Runnable
        public void run() {
            ComposerFragment.this.selectDefaultInputOnCreateView();
        }
    };
    private boolean showKeyboardForInitialInput;

    @ColorInt
    private int themeColor;

    private boolean hasSelectedInput(Input input) {
        return input != null;
    }

    private boolean isNotTextInput(Input input) {
        return !(input instanceof TextInput);
    }

    public static ComposerFragment newInstance(@Nullable String str, boolean z, @ColorInt int i) {
        ComposerFragment composerFragment = new ComposerFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_INITIAL_INPUT_IDENTIFIER, str);
        bundle.putBoolean(KEY_SHOW_KEYBOARD_FOR_INITIAL_INPUT, z);
        bundle.putInt(KEY_THEME_COLOR, i);
        composerFragment.setArguments(bundle);
        return composerFragment;
    }

    @Nullable
    public Input findInputForIdentifier(@Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        for (Input input : this.composerHost.getInputs()) {
            if (input.getUniqueIdentifier().equals(str)) {
                return input;
            }
        }
        return null;
    }

    @Nullable
    public Input getLastSelectedInput() {
        return this.composerView.getSelectedInput();
    }

    public int getTextInputHeight() {
        return this.composerView.getTextInputHeight();
    }

    public void hideAllInputsExcept(List<String> list) {
        this.composerView.hideAllInputsExcept(list);
    }

    public boolean isOpen() {
        Input lastSelectedInput = getLastSelectedInput();
        return hasSelectedInput(lastSelectedInput) && isNotTextInput(lastSelectedInput);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ComposerHost) {
            this.composerHost = (ComposerHost) context;
        }
        if (context instanceof OnInputSelectedListener) {
            this.onInputSelectedListener = (OnInputSelectedListener) context;
        }
    }

    public boolean onBackPressed() {
        return this.composerView.onBackPressed();
    }

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setRetainInstance(true);
        this.initialInputIdentifier = getArguments().getString(KEY_INITIAL_INPUT_IDENTIFIER);
        this.showKeyboardForInitialInput = getArguments().getBoolean(KEY_SHOW_KEYBOARD_FOR_INITIAL_INPUT);
        this.themeColor = getArguments().getInt(KEY_THEME_COLOR);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.composerView = (ComposerView) layoutInflater.inflate(C0123R.layout.intercom_composer_layout, viewGroup, false);
        this.composerView.applyTheme(getContext(), this.themeColor);
        this.composerView.setFragmentManager(getChildFragmentManager());
        this.composerView.setInputs(this.composerHost.getInputs());
        this.composerView.setOnSendButtonClickListener(new OnSendButtonClickedListener() { // from class: com.intercom.composer.ComposerFragment.2
            @Override // com.intercom.composer.input.text.listener.OnSendButtonClickedListener
            public void onSendButtonClicked(CharSequence charSequence) {
                Input selectedInput = ComposerFragment.this.composerView.getSelectedInput();
                if (selectedInput instanceof TextInput) {
                    ((TextInput) selectedInput).sendTextBack(charSequence);
                }
            }
        });
        OnInputSelectedListener onInputSelectedListener = this.onInputSelectedListener;
        if (onInputSelectedListener != null) {
            this.composerView.setInputSelectedListener(onInputSelectedListener);
        }
        this.composerView.setComposerPagerAdapter(new ComposerPagerAdapter(getChildFragmentManager(), this.composerView.getInputs()));
        this.composerView.setEditTextLayoutAnimationListener(new EditTextLayoutAnimatorInternalListener(getActivity()));
        this.composerView.post(this.selectDefaultInputRunnable);
        return this.composerView;
    }

    public void onDestroy() {
        ComposerView composerView = this.composerView;
        if (composerView != null) {
            composerView.onDestroy();
        }
        super.onDestroy();
    }

    public void onDestroyView() {
        this.inputToBeRestored = this.composerView.getSelectedInput();
        super.onDestroyView();
    }

    @SensorsDataInstrumented
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        SensorsDataAutoTrackHelper.trackOnHiddenChanged(this, z);
    }

    @SensorsDataInstrumented
    public void onResume() {
        super.onResume();
        SensorsDataAutoTrackHelper.trackFragmentResume(this);
    }

    @SensorsDataInstrumented
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        SensorsDataAutoTrackHelper.onFragmentViewCreated(this, view, bundle);
    }

    @VisibleForTesting
    void selectDefaultInputOnCreateView() {
        Input input = this.inputToBeRestored;
        if (input == null || !this.composerView.selectInput(input, false, true)) {
            List<Input> inputs = this.composerHost.getInputs();
            if (inputs.isEmpty()) {
                return;
            }
            Input findInputForIdentifier = findInputForIdentifier(this.initialInputIdentifier);
            Input input2 = findInputForIdentifier;
            if (findInputForIdentifier == null) {
                input2 = inputs.get(0);
            }
            this.composerView.selectInput(input2, this.showKeyboardForInitialInput, true);
        }
    }

    public void selectInput(String str, boolean z) {
        Input findInputForIdentifier = findInputForIdentifier(str);
        if (findInputForIdentifier != null) {
            this.composerView.selectInput(findInputForIdentifier, z, true);
        }
    }

    public void setComposerHost(@NonNull ComposerHost composerHost) {
        this.composerHost = composerHost;
    }

    public void setOnInputSelectedListener(@NonNull OnInputSelectedListener onInputSelectedListener) {
        this.onInputSelectedListener = onInputSelectedListener;
    }

    @SensorsDataInstrumented
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        SensorsDataAutoTrackHelper.trackFragmentSetUserVisibleHint(this, z);
    }

    public void showAllInputs() {
        this.composerView.showAllInputs();
    }
}
