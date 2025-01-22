package com.sensorsdata.analytics.android.sdk.data;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.sensorsdata.analytics.android.sdk.SALog;
import com.sensorsdata.analytics.android.sdk.SensorsDataAPI;
import com.sensorsdata.analytics.android.sdk.data.PersistentLoader;
import com.stub.StubApp;
import java.io.File;

/* loaded from: classes08-dex2jar.jar:com/sensorsdata/analytics/android/sdk/data/DbAdapter.class */
public class DbAdapter {
    private static final String TAG = "SA.DbAdapter";
    private static DbAdapter instance;
    private ContentResolver contentResolver;
    private final Context mContext;
    private final File mDatabaseFile;
    private final DbParams mDbParams;
    private int mSessionTime = 30000;
    private int mSavedSessionTime = 0;
    private long mAppPausedTime = 0;

    private DbAdapter(Context context, String str) {
        this.mContext = StubApp.getOrigApplicationContext(context.getApplicationContext());
        this.contentResolver = this.mContext.getContentResolver();
        this.mDatabaseFile = context.getDatabasePath("sensorsdata");
        this.mDbParams = DbParams.getInstance(str);
    }

    private boolean belowMemThreshold() {
        boolean z = false;
        if (this.mDatabaseFile.exists()) {
            z = false;
            if (Math.max(this.mDatabaseFile.getUsableSpace(), getMaxCacheSize(this.mContext)) < this.mDatabaseFile.length()) {
                z = true;
            }
        }
        return z;
    }

    public static DbAdapter getInstance() {
        DbAdapter dbAdapter = instance;
        if (dbAdapter != null) {
            return dbAdapter;
        }
        throw new IllegalStateException("The static method getInstance(Context context, String packageName) should be called before calling getInstance()");
    }

    public static DbAdapter getInstance(Context context, String str) {
        if (instance == null) {
            instance = new DbAdapter(context, str);
        }
        return instance;
    }

    private long getMaxCacheSize(Context context) {
        try {
            return SensorsDataAPI.sharedInstance(context).getMaxCacheSize();
        } catch (Exception e) {
            SALog.printStackTrace(e);
            return 33554432L;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:55:0x01ba, code lost:            if (r8 != null) goto L56;     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int addJSON(java.util.List<org.json.JSONObject> r8) {
        /*
            Method dump skipped, instructions count: 495
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sensorsdata.analytics.android.sdk.data.DbAdapter.addJSON(java.util.List):int");
    }

    /* JADX WARN: Code restructure failed: missing block: B:43:0x014f, code lost:            if (r8 != null) goto L44;     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int addJSON(org.json.JSONObject r8) {
        /*
            Method dump skipped, instructions count: 388
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sensorsdata.analytics.android.sdk.data.DbAdapter.addJSON(org.json.JSONObject):int");
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x005b, code lost:            if (r9 != null) goto L40;     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int cleanupEvents(java.lang.String r9) {
        /*
            r8 = this;
            r0 = 0
            r16 = r0
            r0 = 0
            r15 = r0
            r0 = -1
            r11 = r0
            r0 = r15
            r14 = r0
            r0 = r16
            r13 = r0
            r0 = r8
            android.content.ContentResolver r0 = r0.contentResolver     // Catch: java.lang.Throwable -> L66 java.lang.Exception -> L6a
            r1 = r8
            com.sensorsdata.analytics.android.sdk.data.DbParams r1 = r1.mDbParams     // Catch: java.lang.Throwable -> L66 java.lang.Exception -> L6a
            android.net.Uri r1 = r1.getEventUri()     // Catch: java.lang.Throwable -> L66 java.lang.Exception -> L6a
            java.lang.String r2 = "_id <= ?"
            r3 = 1
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch: java.lang.Throwable -> L66 java.lang.Exception -> L6a
            r4 = r3
            r5 = 0
            r6 = r9
            r4[r5] = r6     // Catch: java.lang.Throwable -> L66 java.lang.Exception -> L6a
            int r0 = r0.delete(r1, r2, r3)     // Catch: java.lang.Throwable -> L66 java.lang.Exception -> L6a
            r0 = r15
            r14 = r0
            r0 = r16
            r13 = r0
            r0 = r8
            android.content.ContentResolver r0 = r0.contentResolver     // Catch: java.lang.Throwable -> L66 java.lang.Exception -> L6a
            r1 = r8
            com.sensorsdata.analytics.android.sdk.data.DbParams r1 = r1.mDbParams     // Catch: java.lang.Throwable -> L66 java.lang.Exception -> L6a
            android.net.Uri r1 = r1.getEventUri()     // Catch: java.lang.Throwable -> L66 java.lang.Exception -> L6a
            r2 = 0
            r3 = 0
            r4 = 0
            r5 = 0
            android.database.Cursor r0 = r0.query(r1, r2, r3, r4, r5)     // Catch: java.lang.Throwable -> L66 java.lang.Exception -> L6a
            r9 = r0
            r0 = r11
            r10 = r0
            r0 = r9
            if (r0 == 0) goto L57
            r0 = r9
            r14 = r0
            r0 = r9
            r13 = r0
            r0 = r9
            int r0 = r0.getCount()     // Catch: java.lang.Throwable -> L66 java.lang.Exception -> L6a
            r10 = r0
        L57:
            r0 = r10
            r12 = r0
            r0 = r9
            if (r0 == 0) goto L83
        L5e:
            r0 = r9
            r0.close()     // Catch: java.lang.Exception -> L94
            r0 = r10
            return r0
        L66:
            r9 = move-exception
            goto L86
        L6a:
            r9 = move-exception
            r0 = r13
            r14 = r0
            r0 = r9
            com.sensorsdata.analytics.android.sdk.SALog.printStackTrace(r0)     // Catch: java.lang.Throwable -> L66
            r0 = r11
            r12 = r0
            r0 = r13
            if (r0 == 0) goto L83
            r0 = r13
            r9 = r0
            r0 = r11
            r10 = r0
            goto L5e
        L83:
            r0 = r12
            return r0
        L86:
            r0 = r14
            if (r0 == 0) goto L92
            r0 = r14
            r0.close()     // Catch: java.lang.Exception -> L97
        L92:
            r0 = r9
            throw r0
        L94:
            r9 = move-exception
            r0 = r10
            return r0
        L97:
            r13 = move-exception
            goto L92
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sensorsdata.analytics.android.sdk.data.DbAdapter.cleanupEvents(java.lang.String):int");
    }

    public void commitActivityCount(int i) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("activity_started_count", Integer.valueOf(i));
        this.contentResolver.insert(this.mDbParams.getActivityStartCountUri(), contentValues);
    }

    public void commitAppEndData(String str) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(PersistentLoader.PersistentName.APP_END_DATA, str);
        this.contentResolver.insert(this.mDbParams.getAppEndDataUri(), contentValues);
    }

    public void commitAppPausedTime(long j) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(PersistentLoader.PersistentName.APP_PAUSED_TIME, Long.valueOf(j));
            this.contentResolver.insert(this.mDbParams.getAppPausedUri(), contentValues);
        } catch (Exception e) {
            SALog.printStackTrace(e);
        }
        this.mAppPausedTime = j;
    }

    public void commitAppStartTime(long j) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(PersistentLoader.PersistentName.APP_START_TIME, Long.valueOf(j));
        this.contentResolver.insert(this.mDbParams.getAppStartTimeUri(), contentValues);
    }

    public void commitLoginId(String str) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(PersistentLoader.PersistentName.LOGIN_ID, str);
        this.contentResolver.insert(this.mDbParams.getLoginIdUri(), contentValues);
    }

    public void commitSessionIntervalTime(int i) {
        if (i == this.mSavedSessionTime) {
            return;
        }
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(PersistentLoader.PersistentName.APP_SESSION_TIME, Integer.valueOf(i));
            this.contentResolver.insert(this.mDbParams.getSessionTimeUri(), contentValues);
        } catch (Exception e) {
            SALog.printStackTrace(e);
        }
        this.mSavedSessionTime = i;
    }

    public void deleteAllEvents() {
        try {
            this.contentResolver.delete(this.mDbParams.getEventUri(), null, null);
        } catch (Exception e) {
            SALog.printStackTrace(e);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:113:0x024a  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0235  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0243 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String[] generateDataString(java.lang.String r8, int r9) {
        /*
            Method dump skipped, instructions count: 594
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sensorsdata.analytics.android.sdk.data.DbAdapter.generateDataString(java.lang.String, int):java.lang.String[]");
    }

    public int getActivityCount() {
        Cursor query = this.contentResolver.query(this.mDbParams.getActivityStartCountUri(), null, null, null, null);
        int i = 0;
        if (query != null) {
            i = 0;
            if (query.getCount() > 0) {
                int i2 = 0;
                while (true) {
                    i = i2;
                    if (!query.moveToNext()) {
                        break;
                    }
                    i2 = query.getInt(0);
                }
            }
        }
        if (query != null) {
            query.close();
        }
        return i;
    }

    public String getAppEndData() {
        Cursor query = this.contentResolver.query(this.mDbParams.getAppEndDataUri(), null, null, null, null);
        String str = "";
        if (query != null) {
            str = "";
            if (query.getCount() > 0) {
                String str2 = "";
                while (true) {
                    str = str2;
                    if (!query.moveToNext()) {
                        break;
                    }
                    str2 = query.getString(0);
                }
            }
        }
        if (query != null) {
            query.close();
        }
        SALog.m53d(TAG, "getAppEndData:" + str);
        return str;
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x006d, code lost:            if (r8 == null) goto L32;     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public long getAppPausedTime() {
        /*
            r7 = this;
            long r0 = java.lang.System.currentTimeMillis()
            r1 = r7
            long r1 = r1.mAppPausedTime
            long r0 = r0 - r1
            r1 = r7
            int r1 = r1.mSessionTime
            long r1 = (long) r1
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 <= 0) goto L85
            r0 = 0
            r8 = r0
            r0 = 0
            r9 = r0
            r0 = r7
            android.content.ContentResolver r0 = r0.contentResolver     // Catch: java.lang.Throwable -> L61 java.lang.Exception -> L65
            r1 = r7
            com.sensorsdata.analytics.android.sdk.data.DbParams r1 = r1.mDbParams     // Catch: java.lang.Throwable -> L61 java.lang.Exception -> L65
            android.net.Uri r1 = r1.getAppPausedUri()     // Catch: java.lang.Throwable -> L61 java.lang.Exception -> L65
            r2 = 0
            r3 = 0
            r4 = 0
            r5 = 0
            android.database.Cursor r0 = r0.query(r1, r2, r3, r4, r5)     // Catch: java.lang.Throwable -> L61 java.lang.Exception -> L65
            r10 = r0
            r0 = r10
            if (r0 == 0) goto L58
            r0 = r10
            r9 = r0
            r0 = r10
            r8 = r0
            r0 = r10
            int r0 = r0.getCount()     // Catch: java.lang.Throwable -> L61 java.lang.Exception -> L65
            if (r0 <= 0) goto L58
        L39:
            r0 = r10
            r9 = r0
            r0 = r10
            r8 = r0
            r0 = r10
            boolean r0 = r0.moveToNext()     // Catch: java.lang.Throwable -> L61 java.lang.Exception -> L65
            if (r0 == 0) goto L58
            r0 = r10
            r9 = r0
            r0 = r10
            r8 = r0
            r0 = r7
            r1 = r10
            r2 = 0
            long r1 = r1.getLong(r2)     // Catch: java.lang.Throwable -> L61 java.lang.Exception -> L65
            r0.mAppPausedTime = r1     // Catch: java.lang.Throwable -> L61 java.lang.Exception -> L65
            goto L39
        L58:
            r0 = r10
            if (r0 == 0) goto L85
            r0 = r10
            r8 = r0
            goto L70
        L61:
            r8 = move-exception
            goto L79
        L65:
            r10 = move-exception
            r0 = r8
            r9 = r0
            r0 = r10
            com.sensorsdata.analytics.android.sdk.SALog.printStackTrace(r0)     // Catch: java.lang.Throwable -> L61
            r0 = r8
            if (r0 == 0) goto L85
        L70:
            r0 = r8
            r0.close()
            goto L85
        L79:
            r0 = r9
            if (r0 == 0) goto L83
            r0 = r9
            r0.close()
        L83:
            r0 = r8
            throw r0
        L85:
            r0 = r7
            long r0 = r0.mAppPausedTime
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sensorsdata.analytics.android.sdk.data.DbAdapter.getAppPausedTime():long");
    }

    public long getAppStartTime() {
        Cursor query = this.contentResolver.query(this.mDbParams.getAppStartTimeUri(), null, null, null, null);
        long j = 0;
        long j2 = 0;
        if (query != null) {
            j2 = 0;
            if (query.getCount() > 0) {
                while (true) {
                    j2 = j;
                    if (!query.moveToNext()) {
                        break;
                    }
                    j = query.getLong(0);
                }
            }
        }
        if (query != null) {
            query.close();
        }
        SALog.m53d(TAG, "getAppStartTime:" + j2);
        return j2;
    }

    public String getLoginId() {
        Cursor query = this.contentResolver.query(this.mDbParams.getLoginIdUri(), null, null, null, null);
        String str = "";
        if (query != null) {
            str = "";
            if (query.getCount() > 0) {
                String str2 = "";
                while (true) {
                    str = str2;
                    if (!query.moveToNext()) {
                        break;
                    }
                    str2 = query.getString(0);
                }
            }
        }
        if (query != null) {
            query.close();
        }
        SALog.m53d(TAG, "getLoginId:" + str);
        return str;
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x0067, code lost:            if (r8 == null) goto L32;     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int getSessionIntervalTime() {
        /*
            r7 = this;
            r0 = r7
            int r0 = r0.mSessionTime
            r1 = r7
            int r1 = r1.mSavedSessionTime
            if (r0 == r1) goto L7f
            r0 = 0
            r8 = r0
            r0 = 0
            r9 = r0
            r0 = r7
            android.content.ContentResolver r0 = r0.contentResolver     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5f
            r1 = r7
            com.sensorsdata.analytics.android.sdk.data.DbParams r1 = r1.mDbParams     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5f
            android.net.Uri r1 = r1.getSessionTimeUri()     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5f
            r2 = 0
            r3 = 0
            r4 = 0
            r5 = 0
            android.database.Cursor r0 = r0.query(r1, r2, r3, r4, r5)     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5f
            r10 = r0
            r0 = r10
            if (r0 == 0) goto L52
            r0 = r10
            r9 = r0
            r0 = r10
            r8 = r0
            r0 = r10
            int r0 = r0.getCount()     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5f
            if (r0 <= 0) goto L52
        L33:
            r0 = r10
            r9 = r0
            r0 = r10
            r8 = r0
            r0 = r10
            boolean r0 = r0.moveToNext()     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5f
            if (r0 == 0) goto L52
            r0 = r10
            r9 = r0
            r0 = r10
            r8 = r0
            r0 = r7
            r1 = r10
            r2 = 0
            int r1 = r1.getInt(r2)     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5f
            r0.mSessionTime = r1     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5f
            goto L33
        L52:
            r0 = r10
            if (r0 == 0) goto L7f
            r0 = r10
            r8 = r0
            goto L6a
        L5b:
            r8 = move-exception
            goto L73
        L5f:
            r10 = move-exception
            r0 = r8
            r9 = r0
            r0 = r10
            com.sensorsdata.analytics.android.sdk.SALog.printStackTrace(r0)     // Catch: java.lang.Throwable -> L5b
            r0 = r8
            if (r0 == 0) goto L7f
        L6a:
            r0 = r8
            r0.close()
            goto L7f
        L73:
            r0 = r9
            if (r0 == 0) goto L7d
            r0 = r9
            r0.close()
        L7d:
            r0 = r8
            throw r0
        L7f:
            r0 = r7
            r1 = r7
            int r1 = r1.mSessionTime
            r0.mSavedSessionTime = r1
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r1 = r0
            r1.<init>()
            r8 = r0
            r0 = r8
            java.lang.String r1 = "getSessionIntervalTime:"
            java.lang.StringBuilder r0 = r0.append(r1)
            r0 = r8
            r1 = r7
            int r1 = r1.mSessionTime
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = "SA.DbAdapter"
            r1 = r8
            java.lang.String r1 = r1.toString()
            com.sensorsdata.analytics.android.sdk.SALog.m53d(r0, r1)
            r0 = r7
            int r0 = r0.mSessionTime
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sensorsdata.analytics.android.sdk.data.DbAdapter.getSessionIntervalTime():int");
    }
}
