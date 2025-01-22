package com.sensorsdata.analytics.android.sdk.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/* loaded from: classes08-dex2jar.jar:com/sensorsdata/analytics/android/sdk/data/OldBDatabaseHelper.class */
public class OldBDatabaseHelper extends SQLiteOpenHelper {
    /* JADX INFO: Access modifiers changed from: package-private */
    public OldBDatabaseHelper(Context context, String str) {
        super(context, str, (SQLiteDatabase.CursorFactory) null, 4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00a3, code lost:            if (r8 == null) goto L28;     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public org.json.JSONArray getAllEvents() {
        /*
            Method dump skipped, instructions count: 191
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sensorsdata.analytics.android.sdk.data.OldBDatabaseHelper.getAllEvents():org.json.JSONArray");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
