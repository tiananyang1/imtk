package io.fabric.sdk.android;

import android.content.Context;
import io.fabric.sdk.android.services.common.IdManager;
import io.fabric.sdk.android.services.concurrency.DependsOn;
import io.fabric.sdk.android.services.concurrency.Task;
import java.io.File;
import java.util.Collection;

/* loaded from: classes08-dex2jar.jar:io/fabric/sdk/android/Kit.class */
public abstract class Kit<Result> implements Comparable<Kit> {
    Context context;
    Fabric fabric;
    IdManager idManager;
    InitializationCallback<Result> initializationCallback;
    InitializationTask<Result> initializationTask = new InitializationTask<>(this);
    final DependsOn dependsOnAnnotation = (DependsOn) getClass().getAnnotation(DependsOn.class);

    @Override // java.lang.Comparable
    public int compareTo(Kit kit) {
        if (containsAnnotatedDependency(kit)) {
            return 1;
        }
        if (kit.containsAnnotatedDependency(this)) {
            return -1;
        }
        if (!hasAnnotatedDependency() || kit.hasAnnotatedDependency()) {
            return (hasAnnotatedDependency() || !kit.hasAnnotatedDependency()) ? 0 : -1;
        }
        return 1;
    }

    boolean containsAnnotatedDependency(Kit kit) {
        if (!hasAnnotatedDependency()) {
            return false;
        }
        Class<?>[] value = this.dependsOnAnnotation.value();
        int length = value.length;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length) {
                return false;
            }
            if (value[i2].isAssignableFrom(kit.getClass())) {
                return true;
            }
            i = i2 + 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract Result doInBackground();

    public Context getContext() {
        return this.context;
    }

    protected Collection<Task> getDependencies() {
        return this.initializationTask.getDependencies();
    }

    public Fabric getFabric() {
        return this.fabric;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public IdManager getIdManager() {
        return this.idManager;
    }

    public abstract String getIdentifier();

    public String getPath() {
        return ".Fabric" + File.separator + getIdentifier();
    }

    public abstract String getVersion();

    boolean hasAnnotatedDependency() {
        return this.dependsOnAnnotation != null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void initialize() {
        this.initializationTask.executeOnExecutor(this.fabric.getExecutorService(), (Object[]) new Void[]{(Void) null});
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void injectParameters(Context context, Fabric fabric, InitializationCallback<Result> initializationCallback, IdManager idManager) {
        this.fabric = fabric;
        this.context = new FabricContext(context, getIdentifier(), getPath());
        this.initializationCallback = initializationCallback;
        this.idManager = idManager;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onCancelled(Result result) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onPostExecute(Result result) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean onPreExecute() {
        return true;
    }
}
