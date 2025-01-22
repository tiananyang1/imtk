package io.fabric.sdk.android.services.persistence;

import android.annotation.SuppressLint;

/* loaded from: classes08-dex2jar.jar:io/fabric/sdk/android/services/persistence/PreferenceStoreStrategy.class */
public class PreferenceStoreStrategy<T> implements PersistenceStrategy<T> {
    private final String key;
    private final SerializationStrategy<T> serializer;
    private final PreferenceStore store;

    public PreferenceStoreStrategy(PreferenceStore preferenceStore, SerializationStrategy<T> serializationStrategy, String str) {
        this.store = preferenceStore;
        this.serializer = serializationStrategy;
        this.key = str;
    }

    @Override // io.fabric.sdk.android.services.persistence.PersistenceStrategy
    @SuppressLint({"CommitPrefEdits"})
    public void clear() {
        this.store.edit().remove(this.key).commit();
    }

    @Override // io.fabric.sdk.android.services.persistence.PersistenceStrategy
    public T restore() {
        return this.serializer.deserialize(this.store.get().getString(this.key, null));
    }

    @Override // io.fabric.sdk.android.services.persistence.PersistenceStrategy
    @SuppressLint({"CommitPrefEdits"})
    public void save(T t) {
        PreferenceStore preferenceStore = this.store;
        preferenceStore.save(preferenceStore.edit().putString(this.key, this.serializer.serialize(t)));
    }
}
