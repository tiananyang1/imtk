package im.shimo.react.prompt;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import com.sensorsdata.analytics.android.sdk.SensorsDataAutoTrackHelper;
import com.sensorsdata.analytics.android.sdk.SensorsDataInstrumented;
import im.shimo.react.prompt.RNPromptModule;
import javax.annotation.Nullable;

/* loaded from: classes08-dex2jar.jar:im/shimo/react/prompt/RNPromptFragment.class */
public class RNPromptFragment extends DialogFragment implements DialogInterface.OnClickListener {
    static final String ARG_BUTTON_NEGATIVE = "button_negative";
    static final String ARG_BUTTON_NEUTRAL = "button_neutral";
    static final String ARG_BUTTON_POSITIVE = "button_positive";
    static final String ARG_DEFAULT_VALUE = "defaultValue";
    static final String ARG_ITEMS = "items";
    static final String ARG_MESSAGE = "message";
    static final String ARG_PLACEHOLDER = "placeholder";
    static final String ARG_STYLE = "style";
    static final String ARG_TITLE = "title";
    static final String ARG_TYPE = "type";
    private EditText mInputText;

    @Nullable
    private RNPromptModule.PromptFragmentListener mListener = null;

    /* loaded from: classes08-dex2jar.jar:im/shimo/react/prompt/RNPromptFragment$PromptTypes.class */
    public enum PromptTypes {
        TYPE_DEFAULT("default"),
        PLAIN_TEXT("plain-text"),
        SECURE_TEXT("secure-text"),
        NUMERIC("numeric"),
        EMAIL_ADDRESS("email-address"),
        PHONE_PAD("phone-pad");

        private final String mName;

        PromptTypes(String str) {
            this.mName = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.mName;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Multi-variable type inference failed */
    public Dialog createDialog(Context context, Bundle bundle) {
        String string;
        boolean z;
        String string2 = bundle.containsKey(ARG_STYLE) ? bundle.getString(ARG_STYLE) : "default";
        if (string2 == null) {
            string2 = "default";
        }
        AlertDialog.Builder builder = (string2.hashCode() == 109407574 && string2.equals("shimo")) ? false : -1 ? new AlertDialog.Builder(context) : new AlertDialog.Builder(context, C0896R.style.ShimoAlertDialogStyle);
        builder.setTitle(bundle.getString("title"));
        if (bundle.containsKey(ARG_BUTTON_POSITIVE)) {
            builder.setPositiveButton(bundle.getString(ARG_BUTTON_POSITIVE), this);
        }
        if (bundle.containsKey(ARG_BUTTON_NEGATIVE)) {
            builder.setNegativeButton(bundle.getString(ARG_BUTTON_NEGATIVE), this);
        }
        if (bundle.containsKey(ARG_BUTTON_NEUTRAL)) {
            builder.setNeutralButton(bundle.getString(ARG_BUTTON_NEUTRAL), this);
        }
        if (bundle.containsKey("message")) {
            builder.setMessage(bundle.getString("message"));
        }
        if (bundle.containsKey(ARG_ITEMS)) {
            builder.setItems(bundle.getCharSequenceArray(ARG_ITEMS), this);
        }
        AlertDialog create = builder.create();
        EditText editText = (string2.hashCode() == 109407574 && string2.equals("shimo")) ? false : -1 ? new EditText(context) : (EditText) LayoutInflater.from(context).inflate(C0896R.layout.edit_text, (ViewGroup) null);
        int i = 524289;
        if (bundle.containsKey(ARG_TYPE)) {
            String string3 = bundle.getString(ARG_TYPE);
            i = 524289;
            if (string3 != null) {
                switch (string3.hashCode()) {
                    case -2010681661:
                        if (string3.equals("email-address")) {
                            z = 2;
                            break;
                        }
                        z = -1;
                        break;
                    case -2000413939:
                        if (string3.equals("numeric")) {
                            z = true;
                            break;
                        }
                        z = -1;
                        break;
                    case -1030161484:
                        if (string3.equals("phone-pad")) {
                            z = 3;
                            break;
                        }
                        z = -1;
                        break;
                    case 975575888:
                        if (string3.equals("plain-text")) {
                            z = 4;
                            break;
                        }
                        z = -1;
                        break;
                    case 1369873859:
                        if (string3.equals("secure-text")) {
                            z = false;
                            break;
                        }
                        z = -1;
                        break;
                    default:
                        z = -1;
                        break;
                }
                if (z) {
                    if (!z) {
                        if (z == 2) {
                            i = 33;
                        } else if (z != 3) {
                            i = 524289;
                        }
                    }
                    i = 3;
                } else {
                    i = 129;
                }
            }
        }
        editText.setInputType(i);
        if (bundle.containsKey(ARG_DEFAULT_VALUE) && (string = bundle.getString(ARG_DEFAULT_VALUE)) != null) {
            editText.setText(string);
            int length = editText.getText().length();
            editText.setSelection(length, length);
        }
        if (bundle.containsKey(ARG_PLACEHOLDER)) {
            editText.setHint(bundle.getString(ARG_PLACEHOLDER));
        }
        create.setView(editText, 50, 15, 50, 0);
        this.mInputText = editText;
        return create;
    }

    @Override // android.content.DialogInterface.OnClickListener
    @SensorsDataInstrumented
    public void onClick(DialogInterface dialogInterface, int i) {
        RNPromptModule.PromptFragmentListener promptFragmentListener = this.mListener;
        if (promptFragmentListener != null) {
            promptFragmentListener.onConfirm(i, this.mInputText.getText().toString());
        }
        SensorsDataAutoTrackHelper.trackDialog(dialogInterface, i);
    }

    @Override // android.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        Dialog createDialog = createDialog(getActivity(), getArguments());
        if (this.mInputText.requestFocus()) {
            createDialog.getWindow().setSoftInputMode(5);
        }
        return createDialog;
    }

    @Override // android.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        RNPromptModule.PromptFragmentListener promptFragmentListener = this.mListener;
        if (promptFragmentListener != null) {
            promptFragmentListener.onDismiss(dialogInterface);
        }
    }

    @Override // android.app.Fragment
    @SensorsDataInstrumented
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        SensorsDataAutoTrackHelper.trackOnHiddenChanged(this, z);
    }

    @Override // android.app.Fragment
    @SensorsDataInstrumented
    public void onResume() {
        super.onResume();
        SensorsDataAutoTrackHelper.trackFragmentResume(this);
    }

    @Override // android.app.Fragment
    @SensorsDataInstrumented
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        SensorsDataAutoTrackHelper.onFragmentViewCreated(this, view, bundle);
    }

    public void setListener(@Nullable RNPromptModule.PromptFragmentListener promptFragmentListener) {
        this.mListener = promptFragmentListener;
    }

    @Override // android.app.Fragment
    @SensorsDataInstrumented
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        SensorsDataAutoTrackHelper.trackFragmentSetUserVisibleHint(this, z);
    }
}
