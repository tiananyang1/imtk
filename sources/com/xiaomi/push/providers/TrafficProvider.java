package com.xiaomi.push.providers;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import com.xiaomi.push.C0681hh;

/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/providers/TrafficProvider.class */
public class TrafficProvider extends ContentProvider {

    /* renamed from: a */
    private SQLiteOpenHelper f2363a;

    /* renamed from: a */
    public static final Uri f2362a = Uri.parse("content://com.xiaomi.push.providers.TrafficProvider/traffic");

    /* renamed from: a */
    private static final UriMatcher f2361a = new UriMatcher(-1);

    static {
        f2361a.addURI("com.xiaomi.push.providers.TrafficProvider", "traffic", 1);
        f2361a.addURI("com.xiaomi.push.providers.TrafficProvider", "update_imsi", 2);
    }

    @Override // android.content.ContentProvider
    public int bulkInsert(Uri uri, ContentValues[] contentValuesArr) {
        return 0;
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        if (f2361a.match(uri) == 1) {
            return "vnd.android.cursor.dir/vnd.xiaomi.push.traffic";
        }
        throw new IllegalArgumentException("Unknown URI " + uri);
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        this.f2363a = new C0773a(getContext());
        return true;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        Cursor query;
        synchronized (C0773a.f2365a) {
            if (f2361a.match(uri) != 1) {
                throw new IllegalArgumentException("Unknown URI " + uri);
            }
            query = this.f2363a.getReadableDatabase().query("traffic", strArr, str, strArr2, null, null, str2);
        }
        return query;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        if (f2361a.match(uri) != 2 || contentValues == null || !contentValues.containsKey("imsi")) {
            return 0;
        }
        C0681hh.m1547a(contentValues.getAsString("imsi"));
        return 0;
    }
}
