package com.sensorsdata.analytics.android.sdk.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.sensorsdata.analytics.android.sdk.SALog;

/* loaded from: classes08-dex2jar.jar:com/sensorsdata/analytics/android/sdk/data/SensorsDataDBHelper.class */
class SensorsDataDBHelper extends SQLiteOpenHelper {
    private static final String CREATE_EVENTS_TABLE = String.format("CREATE TABLE %s (_id INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT NOT NULL, %s INTEGER NOT NULL);", DbParams.TABLE_EVENTS, "data", "created_at");
    private static final String EVENTS_TIME_INDEX = String.format("CREATE INDEX IF NOT EXISTS time_idx ON %s (%s);", DbParams.TABLE_EVENTS, "created_at");
    private static final String TAG = "SA.SQLiteOpenHelper";

    /* JADX INFO: Access modifiers changed from: package-private */
    public SensorsDataDBHelper(Context context) {
        super(context, "sensorsdata", (SQLiteDatabase.CursorFactory) null, 4);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        SALog.m55i(TAG, "Creating a new Sensors Analytics DB");
        sQLiteDatabase.execSQL(CREATE_EVENTS_TABLE);
        sQLiteDatabase.execSQL(EVENTS_TIME_INDEX);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        SALog.m55i(TAG, "Upgrading app, replacing Sensors Analytics DB");
        sQLiteDatabase.execSQL(String.format("DROP TABLE IF EXISTS %s", DbParams.TABLE_EVENTS));
        sQLiteDatabase.execSQL(CREATE_EVENTS_TABLE);
        sQLiteDatabase.execSQL(EVENTS_TIME_INDEX);
    }
}
