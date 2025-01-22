package com.intercom.composer.pager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;
import com.intercom.composer.input.Input;
import java.util.List;

/* loaded from: classes08-dex2jar.jar:com/intercom/composer/pager/ComposerPagerAdapter.class */
public class ComposerPagerAdapter extends FragmentPagerAdapter {
    private final List<? extends Input> inputs;

    public ComposerPagerAdapter(FragmentManager fragmentManager, List<? extends Input> list) {
        super(fragmentManager);
        this.inputs = list;
    }

    public int getCount() {
        return this.inputs.size();
    }

    public Fragment getItem(int i) {
        return this.inputs.get(i).createFragment();
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        Fragment fragment = (Fragment) super.instantiateItem(viewGroup, i);
        this.inputs.get(i).setFragmentTag(fragment.getTag());
        return fragment;
    }
}
