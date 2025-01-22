package com.intercom.composer.input;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import com.sensorsdata.analytics.android.sdk.SensorsDataAutoTrackHelper;
import com.sensorsdata.analytics.android.sdk.SensorsDataInstrumented;

/* loaded from: classes08-dex2jar.jar:com/intercom/composer/input/InputFragment.class */
public abstract class InputFragment extends Fragment {
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setRetainInstance(true);
    }

    @SensorsDataInstrumented
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        SensorsDataAutoTrackHelper.trackOnHiddenChanged(this, z);
    }

    public abstract void onInputDeselected();

    public abstract void onInputReselected();

    public abstract void onInputSelected();

    @SensorsDataInstrumented
    public void onResume() {
        super.onResume();
        SensorsDataAutoTrackHelper.trackFragmentResume(this);
    }

    @SensorsDataInstrumented
    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        passDataOnViewCreated(getArguments());
        SensorsDataAutoTrackHelper.onFragmentViewCreated(this, view, bundle);
    }

    public void passData(Bundle bundle) {
        if (getView() != null) {
            passDataOnViewCreated(bundle);
        } else {
            setArguments(bundle);
        }
    }

    protected abstract void passDataOnViewCreated(@Nullable Bundle bundle);

    public void setArguments(@Nullable Bundle bundle) {
        Bundle arguments = getArguments();
        Bundle bundle2 = arguments;
        if (arguments == null) {
            bundle2 = new Bundle();
        }
        if (bundle != null) {
            bundle2.putAll(bundle);
        }
        super.setArguments(bundle2);
    }

    @SensorsDataInstrumented
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        SensorsDataAutoTrackHelper.trackFragmentSetUserVisibleHint(this, z);
    }
}
