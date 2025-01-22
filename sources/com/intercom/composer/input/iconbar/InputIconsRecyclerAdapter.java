package com.intercom.composer.input.iconbar;

import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.intercom.composer.C0123R;
import com.intercom.composer.input.Input;
import com.intercom.composer.input.InputFragment;
import com.intercom.composer.input.empty.EmptyInput;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes08-dex2jar.jar:com/intercom/composer/input/iconbar/InputIconsRecyclerAdapter.class */
public class InputIconsRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    @VisibleForTesting
    static final int VIEW_TYPE_EMPTY_SPACE = 2;

    @VisibleForTesting
    static final int VIEW_TYPE_INPUT = 1;
    private final FragmentManager fragmentManager;
    private final HashSet<String> hiddenInputIdentifiers = new HashSet<>();
    private final InputClickedListener inputClickedListener;
    private List<Input> inputs;
    private final LayoutInflater layoutInflater;
    private final InputSelectedListener onInputIconSelectedListener;

    @Nullable
    private Input selectedInput;

    public InputIconsRecyclerAdapter(LayoutInflater layoutInflater, List<Input> list, InputSelectedListener inputSelectedListener, InputClickedListener inputClickedListener, FragmentManager fragmentManager) {
        this.inputs = new ArrayList();
        this.inputs = list;
        this.onInputIconSelectedListener = inputSelectedListener;
        this.layoutInflater = layoutInflater;
        this.inputClickedListener = inputClickedListener;
        this.fragmentManager = fragmentManager;
    }

    private void fireInputSelectionCallbacks(Input input) {
        InputFragment findFragment;
        Input input2 = this.selectedInput;
        if (input == input2) {
            InputFragment findFragment2 = input.findFragment(this.fragmentManager);
            if (findFragment2 != null) {
                findFragment2.onInputReselected();
                return;
            }
            return;
        }
        if (input2 != null && (findFragment = input2.findFragment(this.fragmentManager)) != null) {
            findFragment.onInputDeselected();
        }
        InputFragment findFragment3 = input.findFragment(this.fragmentManager);
        if (findFragment3 != null) {
            findFragment3.onInputSelected();
        }
    }

    public void deselectAllInputs() {
        this.selectedInput = null;
        notifyDataSetChanged();
    }

    public int getItemCount() {
        return this.inputs.size();
    }

    public int getItemViewType(int i) {
        Input input = this.inputs.get(i);
        return ((input instanceof EmptyInput) || this.hiddenInputIdentifiers.contains(input.getUniqueIdentifier())) ? 2 : 1;
    }

    @Nullable
    public Input getSelectedInput() {
        return this.selectedInput;
    }

    public void hideAllInputsExcept(List<String> list) {
        this.hiddenInputIdentifiers.clear();
        Iterator<Input> it = this.inputs.iterator();
        while (it.hasNext()) {
            String uniqueIdentifier = it.next().getUniqueIdentifier();
            if (!list.contains(uniqueIdentifier)) {
                this.hiddenInputIdentifiers.add(uniqueIdentifier);
            }
        }
        notifyDataSetChanged();
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        Input input = this.inputs.get(i);
        if (viewHolder instanceof InputIconViewHolder) {
            ((InputIconViewHolder) viewHolder).bind(input, this.selectedInput != null && input.getUniqueIdentifier().equals(this.selectedInput.getUniqueIdentifier()));
        }
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return i == 2 ? new EmptyIconViewHolder(this.layoutInflater.inflate(C0123R.layout.intercom_composer_empty_view_layout, viewGroup, false)) : new InputIconViewHolder(this.layoutInflater.inflate(C0123R.layout.intercom_composer_input_icon_view_layout, viewGroup, false), this.inputClickedListener);
    }

    public boolean selectInput(Input input, boolean z, boolean z2) {
        if (this.inputs.indexOf(input) == -1) {
            return false;
        }
        fireInputSelectionCallbacks(input);
        if (input == this.selectedInput) {
            return false;
        }
        this.selectedInput = input;
        notifyDataSetChanged();
        this.onInputIconSelectedListener.onInputSelected(input, this.inputs.indexOf(input), z, z2);
        return true;
    }

    public void showAllInputs() {
        this.hiddenInputIdentifiers.clear();
        notifyDataSetChanged();
    }
}
