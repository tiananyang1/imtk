package io.fabric.sdk.android.services.concurrency;

import java.util.Collection;

/* loaded from: classes08-dex2jar.jar:io/fabric/sdk/android/services/concurrency/Dependency.class */
public interface Dependency<T> {
    void addDependency(T t);

    boolean areDependenciesMet();

    Collection<T> getDependencies();
}
