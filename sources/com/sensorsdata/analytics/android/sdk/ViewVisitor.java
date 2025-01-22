package com.sensorsdata.analytics.android.sdk;

import android.annotation.TargetApi;
import android.view.View;
import com.sensorsdata.analytics.android.sdk.Pathfinder;
import java.util.List;

@TargetApi(16)
/* loaded from: classes08-dex2jar.jar:com/sensorsdata/analytics/android/sdk/ViewVisitor.class */
public abstract class ViewVisitor implements Pathfinder.Accumulator {
    private static final String TAG = "SA.ViewVisitor";
    private final List<Pathfinder.PathElement> mPath;
    private final Pathfinder mPathfinder = new Pathfinder();

    protected ViewVisitor(List<Pathfinder.PathElement> list) {
        this.mPath = list;
    }

    public abstract void cleanup();

    protected abstract String name();

    public void visit(View view) {
        this.mPathfinder.findTargetsInRoot(view, this.mPath, this);
    }
}
