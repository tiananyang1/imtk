package com.intercom.composer.input.text.options;

import android.view.View;
import com.sensorsdata.analytics.android.sdk.SensorsDataAutoTrackHelper;
import com.sensorsdata.analytics.android.sdk.SensorsDataInstrumented;

/* loaded from: classes08-dex2jar.jar:com/intercom/composer/input/text/options/InputOptionImageViewClickListener.class */
public class InputOptionImageViewClickListener implements View.OnClickListener {
    private TextInputOption option;

    public InputOptionImageViewClickListener(TextInputOption textInputOption) {
        this.option = textInputOption;
    }

    @Override // android.view.View.OnClickListener
    @SensorsDataInstrumented
    public void onClick(View view) {
        this.option.inputOptionClicked();
        SensorsDataAutoTrackHelper.trackViewOnClick(view);
    }
}
