package com.sensorsdata.analytics.android.sdk.data.persistent;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import com.sensorsdata.analytics.android.sdk.SALog;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@SuppressLint({"CommitPrefEdits"})
/* loaded from: classes08-dex2jar.jar:com/sensorsdata/analytics/android/sdk/data/persistent/PersistentIdentity.class */
public abstract class PersistentIdentity<T> {
    private static final String TAG = "SA.PersistentIdentity";
    private T item;
    private final Future<SharedPreferences> loadStoredPreferences;
    private final String persistentKey;
    private final PersistentSerializer serializer;

    /* loaded from: classes08-dex2jar.jar:com/sensorsdata/analytics/android/sdk/data/persistent/PersistentIdentity$PersistentSerializer.class */
    interface PersistentSerializer<T> {
        T create();

        T load(String str);

        String save(T t);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PersistentIdentity(Future<SharedPreferences> future, String str, PersistentSerializer<T> persistentSerializer) {
        this.loadStoredPreferences = future;
        this.serializer = persistentSerializer;
        this.persistentKey = str;
    }

    public void commit(T t) {
        this.item = t;
        synchronized (this.loadStoredPreferences) {
            SharedPreferences sharedPreferences = null;
            try {
                try {
                    sharedPreferences = this.loadStoredPreferences.get();
                } catch (ExecutionException e) {
                    SALog.m54d(TAG, "Cannot read distinct ids from sharedPreferences.", e.getCause());
                }
            } catch (InterruptedException e2) {
                SALog.m54d(TAG, "Cannot read distinct ids from sharedPreferences.", e2);
            }
            if (sharedPreferences == null) {
                return;
            }
            SharedPreferences.Editor edit = sharedPreferences.edit();
            if (this.item == null) {
                this.item = (T) this.serializer.create();
            }
            edit.putString(this.persistentKey, this.serializer.save(this.item));
            edit.apply();
        }
    }

    public T get() {
        String str;
        if (this.item == null) {
            synchronized (this.loadStoredPreferences) {
                try {
                    SharedPreferences sharedPreferences = this.loadStoredPreferences.get();
                    str = null;
                    if (sharedPreferences != null) {
                        str = sharedPreferences.getString(this.persistentKey, null);
                    }
                } catch (InterruptedException e) {
                    SALog.m54d(TAG, "Cannot read distinct ids from sharedPreferences.", e);
                    str = null;
                } catch (ExecutionException e2) {
                    SALog.m54d(TAG, "Cannot read distinct ids from sharedPreferences.", e2.getCause());
                    str = null;
                }
                if (str == null) {
                    this.item = (T) this.serializer.create();
                } else {
                    this.item = (T) this.serializer.load(str);
                }
            }
        }
        return this.item;
    }
}
