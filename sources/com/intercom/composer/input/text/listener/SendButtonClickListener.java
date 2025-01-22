package com.intercom.composer.input.text.listener;

import android.support.annotation.VisibleForTesting;
import android.view.View;
import android.widget.EditText;
import com.sensorsdata.analytics.android.sdk.SensorsDataAutoTrackHelper;
import com.sensorsdata.analytics.android.sdk.SensorsDataInstrumented;

/* loaded from: classes08-dex2jar.jar:com/intercom/composer/input/text/listener/SendButtonClickListener.class */
public class SendButtonClickListener implements View.OnClickListener {
    private final EditText editText;
    private final OnSendButtonClickedListener listener;

    public SendButtonClickListener(OnSendButtonClickedListener onSendButtonClickedListener, EditText editText) {
        this.listener = onSendButtonClickedListener;
        this.editText = editText;
    }

    @VisibleForTesting
    void clearEditText() {
        this.editText.setText("");
    }

    @Override // android.view.View.OnClickListener
    @SensorsDataInstrumented
    public void onClick(View view) {
        this.listener.onSendButtonClicked(this.editText.getText());
        clearEditText();
        SensorsDataAutoTrackHelper.trackViewOnClick(view);
    }
}
