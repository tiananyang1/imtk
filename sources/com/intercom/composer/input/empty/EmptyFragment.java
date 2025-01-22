package com.intercom.composer.input.empty;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.intercom.composer.C0123R;
import com.intercom.composer.input.InputFragment;

/* loaded from: classes08-dex2jar.jar:com/intercom/composer/input/empty/EmptyFragment.class */
public class EmptyFragment extends InputFragment {
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(C0123R.layout.intercom_composer_fragment_empty, viewGroup, false);
    }

    @Override // com.intercom.composer.input.InputFragment
    public void onInputDeselected() {
    }

    @Override // com.intercom.composer.input.InputFragment
    public void onInputReselected() {
    }

    @Override // com.intercom.composer.input.InputFragment
    public void onInputSelected() {
    }

    @Override // com.intercom.composer.input.InputFragment
    protected void passDataOnViewCreated(Bundle bundle) {
    }
}
