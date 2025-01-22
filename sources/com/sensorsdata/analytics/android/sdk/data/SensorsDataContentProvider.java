package com.sensorsdata.analytics.android.sdk.data;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import com.sensorsdata.analytics.android.sdk.SALog;
import com.sensorsdata.analytics.android.sdk.data.PersistentLoader;
import com.sensorsdata.analytics.android.sdk.data.persistent.PersistentAppEndData;
import com.sensorsdata.analytics.android.sdk.data.persistent.PersistentAppPaused;
import com.sensorsdata.analytics.android.sdk.data.persistent.PersistentAppStartTime;
import com.sensorsdata.analytics.android.sdk.data.persistent.PersistentLoginId;
import com.sensorsdata.analytics.android.sdk.data.persistent.PersistentSessionIntervalTime;
import com.stub.StubApp;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes08-dex2jar.jar:com/sensorsdata/analytics/android/sdk/data/SensorsDataContentProvider.class */
public class SensorsDataContentProvider extends ContentProvider {
    private static final int ACTIVITY_START_COUNT = 2;
    private static final int APP_END_DATA = 4;
    private static final int APP_PAUSED_TIME = 5;
    private static final int APP_START_TIME = 3;
    private static final int EVENTS = 1;
    private static final int LOGIN_ID = 7;
    private static final int SESSION_INTERVAL_TIME = 6;
    private static UriMatcher uriMatcher = new UriMatcher(-1);
    private ContentResolver contentResolver;
    private SensorsDataDBHelper dbHelper;
    private PersistentAppEndData persistentAppEndData;
    private PersistentAppPaused persistentAppPaused;
    private PersistentAppStartTime persistentAppStartTime;
    private PersistentLoginId persistentLoginId;
    private PersistentSessionIntervalTime persistentSessionIntervalTime;
    private boolean isDbWritable = true;
    private int startActivityCount = 0;

    private void insert(int i, Uri uri, ContentValues contentValues) {
        switch (i) {
            case 2:
                this.startActivityCount = contentValues.getAsInteger("activity_started_count").intValue();
                return;
            case 3:
                this.persistentAppStartTime.commit(contentValues.getAsLong(PersistentLoader.PersistentName.APP_START_TIME));
                return;
            case 4:
                this.persistentAppEndData.commit(contentValues.getAsString(PersistentLoader.PersistentName.APP_END_DATA));
                return;
            case 5:
                this.persistentAppPaused.commit(contentValues.getAsLong(PersistentLoader.PersistentName.APP_PAUSED_TIME));
                return;
            case 6:
                this.persistentSessionIntervalTime.commit(contentValues.getAsInteger(PersistentLoader.PersistentName.APP_SESSION_TIME));
                this.contentResolver.notifyChange(uri, null);
                return;
            case 7:
                this.persistentLoginId.commit(contentValues.getAsString(PersistentLoader.PersistentName.LOGIN_ID));
                return;
            default:
                return;
        }
    }

    private Cursor query(int i) {
        String str;
        Object obj = null;
        switch (i) {
            case 2:
                obj = Integer.valueOf(this.startActivityCount);
                str = "activity_started_count";
                break;
            case 3:
                obj = this.persistentAppStartTime.get();
                str = PersistentLoader.PersistentName.APP_START_TIME;
                break;
            case 4:
                obj = this.persistentAppEndData.get();
                str = PersistentLoader.PersistentName.APP_END_DATA;
                break;
            case 5:
                obj = this.persistentAppPaused.get();
                str = PersistentLoader.PersistentName.APP_PAUSED_TIME;
                break;
            case 6:
                obj = this.persistentSessionIntervalTime.get();
                str = PersistentLoader.PersistentName.APP_SESSION_TIME;
                break;
            case 7:
                obj = this.persistentLoginId.get();
                str = PersistentLoader.PersistentName.LOGIN_ID;
                break;
            default:
                str = null;
                break;
        }
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{str});
        matrixCursor.addRow(new Object[]{obj});
        return matrixCursor;
    }

    @Override // android.content.ContentProvider
    public int bulkInsert(Uri uri, ContentValues[] contentValuesArr) {
        if (!this.isDbWritable) {
            return 0;
        }
        SQLiteDatabase sQLiteDatabase = null;
        try {
            try {
                SQLiteDatabase writableDatabase = this.dbHelper.getWritableDatabase();
                writableDatabase.beginTransaction();
                int length = contentValuesArr.length;
                for (ContentValues contentValues : contentValuesArr) {
                    insert(uri, contentValues);
                }
                writableDatabase.setTransactionSuccessful();
                if (writableDatabase != null) {
                    writableDatabase.endTransaction();
                }
                return length;
            } catch (SQLiteException e) {
                this.isDbWritable = false;
                SALog.printStackTrace(e);
                return 0;
            }
        } catch (Throwable th) {
            if (0 != 0) {
                sQLiteDatabase.endTransaction();
            }
            throw th;
        }
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        if (!this.isDbWritable) {
            return 0;
        }
        try {
            if (1 != uriMatcher.match(uri)) {
                return 0;
            }
            try {
                return this.dbHelper.getWritableDatabase().delete(DbParams.TABLE_EVENTS, str, strArr);
            } catch (SQLiteException e) {
                this.isDbWritable = false;
                SALog.printStackTrace(e);
                return 0;
            }
        } catch (Exception e2) {
            SALog.printStackTrace(e2);
            return 0;
        }
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        return null;
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        if (this.isDbWritable && contentValues != null) {
            if (contentValues.size() == 0) {
                return uri;
            }
            try {
                int match = uriMatcher.match(uri);
                if (match != 1) {
                    insert(match, uri, contentValues);
                    return uri;
                }
                try {
                    SQLiteDatabase writableDatabase = this.dbHelper.getWritableDatabase();
                    if (contentValues.containsKey("data") && contentValues.containsKey("created_at")) {
                        return ContentUris.withAppendedId(uri, writableDatabase.insert(DbParams.TABLE_EVENTS, "_id", contentValues));
                    }
                    return uri;
                } catch (SQLiteException e) {
                    this.isDbWritable = false;
                    SALog.printStackTrace(e);
                    return uri;
                }
            } catch (Exception e2) {
                SALog.printStackTrace(e2);
            }
        }
        return uri;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        String str;
        Context context = getContext();
        if (context == null) {
            return true;
        }
        try {
            str = StubApp.getOrigApplicationContext(context.getApplicationContext()).getPackageName();
        } catch (UnsupportedOperationException e) {
            str = "com.sensorsdata.analytics.android.sdk.test";
        }
        String str2 = str + ".SensorsDataContentProvider";
        this.contentResolver = context.getContentResolver();
        uriMatcher.addURI(str2, DbParams.TABLE_EVENTS, 1);
        uriMatcher.addURI(str2, "activity_started_count", 2);
        uriMatcher.addURI(str2, PersistentLoader.PersistentName.APP_START_TIME, 3);
        uriMatcher.addURI(str2, PersistentLoader.PersistentName.APP_END_DATA, 4);
        uriMatcher.addURI(str2, PersistentLoader.PersistentName.APP_PAUSED_TIME, 5);
        uriMatcher.addURI(str2, PersistentLoader.PersistentName.APP_SESSION_TIME, 6);
        uriMatcher.addURI(str2, PersistentLoader.PersistentName.LOGIN_ID, 7);
        this.dbHelper = new SensorsDataDBHelper(context);
        try {
            if (context.getDatabasePath(str).exists()) {
                JSONArray allEvents = new OldBDatabaseHelper(context, str).getAllEvents();
                int i = 0;
                while (true) {
                    int i2 = i;
                    if (i2 >= allEvents.length()) {
                        break;
                    }
                    JSONObject jSONObject = allEvents.getJSONObject(i2);
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("data", jSONObject.getString("data"));
                    contentValues.put("created_at", jSONObject.getString("created_at"));
                    try {
                        this.dbHelper.getWritableDatabase().insert(DbParams.TABLE_EVENTS, "_id", contentValues);
                    } catch (SQLiteException e2) {
                        this.isDbWritable = false;
                        SALog.printStackTrace(e2);
                    }
                    i = i2 + 1;
                }
            }
            if (this.isDbWritable) {
                context.deleteDatabase(str);
            }
        } catch (Exception e3) {
            SALog.printStackTrace(e3);
        }
        PersistentLoader.initLoader(context);
        this.persistentAppEndData = (PersistentAppEndData) PersistentLoader.loadPersistent(PersistentLoader.PersistentName.APP_END_DATA);
        this.persistentAppStartTime = (PersistentAppStartTime) PersistentLoader.loadPersistent(PersistentLoader.PersistentName.APP_START_TIME);
        this.persistentAppPaused = (PersistentAppPaused) PersistentLoader.loadPersistent(PersistentLoader.PersistentName.APP_PAUSED_TIME);
        this.persistentSessionIntervalTime = (PersistentSessionIntervalTime) PersistentLoader.loadPersistent(PersistentLoader.PersistentName.APP_SESSION_TIME);
        this.persistentLoginId = (PersistentLoginId) PersistentLoader.loadPersistent(PersistentLoader.PersistentName.LOGIN_ID);
        return true;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        Cursor query;
        if (!this.isDbWritable) {
            return null;
        }
        try {
            int match = uriMatcher.match(uri);
            if (match == 1) {
                try {
                    query = this.dbHelper.getWritableDatabase().query(DbParams.TABLE_EVENTS, strArr, str, strArr2, null, null, str2);
                } catch (SQLiteException e) {
                    this.isDbWritable = false;
                    SALog.printStackTrace(e);
                    return null;
                }
            } else {
                query = query(match);
            }
            return query;
        } catch (Exception e2) {
            SALog.printStackTrace(e2);
            return null;
        }
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }
}
