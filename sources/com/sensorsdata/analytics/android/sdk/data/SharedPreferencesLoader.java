package com.sensorsdata.analytics.android.sdk.data;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/* loaded from: classes08-dex2jar.jar:com/sensorsdata/analytics/android/sdk/data/SharedPreferencesLoader.class */
class SharedPreferencesLoader {
    private final Executor mExecutor = Executors.newSingleThreadExecutor();

    /* loaded from: classes08-dex2jar.jar:com/sensorsdata/analytics/android/sdk/data/SharedPreferencesLoader$LoadSharedPreferences.class */
    private static class LoadSharedPreferences implements Callable<SharedPreferences> {
        private final Context mContext;
        private final String mPrefsName;

        LoadSharedPreferences(Context context, String str) {
            this.mContext = context;
            this.mPrefsName = str;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.concurrent.Callable
        public SharedPreferences call() {
            return this.mContext.getSharedPreferences(this.mPrefsName, 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Future<SharedPreferences> loadPreferences(Context context, String str) {
        FutureTask futureTask = new FutureTask(new LoadSharedPreferences(context, str));
        this.mExecutor.execute(futureTask);
        return futureTask;
    }
}
