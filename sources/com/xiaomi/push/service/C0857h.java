package com.xiaomi.push.service;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.mipush.sdk.Constants;
import com.xiaomi.mipush.sdk.MIPushNotificationHelper4Hybrid;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: com.xiaomi.push.service.h */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/h.class */
public class C0857h extends SQLiteOpenHelper {

    /* renamed from: a */
    private static C0857h f2645a;

    /* renamed from: a */
    private static final String[] f2646a = {"name", "TEXT NOT NULL", "appId", "INTEGER NOT NULL", Constants.PACKAGE_NAME, "TEXT NOT NULL", "create_time", "INTEGER NOT NULL", "type", "TEXT NOT NULL", "center_longtitude", "TEXT", "center_lantitude", "TEXT", "circle_radius", "REAL", "polygon_point", "TEXT", "coordinate_provider", "TEXT NOT NULL", "current_status", "TEXT NOT NULL"};

    /* renamed from: b */
    private static final String[] f2647b = {MIPushNotificationHelper4Hybrid.KEY_MESSAGE_ID, "TEXT NOT NULL", "geo_id", "TEXT NOT NULL", "content", "BLOB NOT NULL", "action", "INTEGER NOT NULL", "deadline", "INTEGER NOT NULL"};

    /* renamed from: a */
    private SQLiteDatabase f2648a;

    /* renamed from: a */
    public final Object f2649a;

    /* renamed from: a */
    private final String f2650a;

    /* renamed from: a */
    private AtomicInteger f2651a;

    public C0857h(Context context) {
        super(context, "geofencing.db", (SQLiteDatabase.CursorFactory) null, 1);
        this.f2650a = "GeoFenceDatabaseHelper.";
        this.f2649a = new Object();
        this.f2651a = new AtomicInteger();
    }

    /* renamed from: a */
    public static C0857h m2703a(Context context) {
        if (f2645a == null) {
            synchronized (C0857h.class) {
                try {
                    if (f2645a == null) {
                        f2645a = new C0857h(context);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return f2645a;
    }

    /* renamed from: a */
    private void m2704a(SQLiteDatabase sQLiteDatabase) {
        try {
            StringBuilder sb = new StringBuilder("CREATE TABLE geofence(id TEXT PRIMARY KEY ,");
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= f2646a.length - 1) {
                    sb.append(");");
                    sQLiteDatabase.execSQL(sb.toString());
                    return;
                }
                if (i2 != 0) {
                    sb.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
                }
                sb.append(f2646a[i2]);
                sb.append(" ");
                sb.append(f2646a[i2 + 1]);
                i = i2 + 2;
            }
        } catch (SQLException e) {
            AbstractC0407b.m75d(e.toString());
        }
    }

    /* renamed from: b */
    private void m2705b(SQLiteDatabase sQLiteDatabase) {
        try {
            StringBuilder sb = new StringBuilder("CREATE TABLE geoMessage(");
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= f2647b.length - 1) {
                    sb.append(",PRIMARY KEY(message_id,geo_id));");
                    sQLiteDatabase.execSQL(sb.toString());
                    return;
                }
                if (i2 != 0) {
                    sb.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
                }
                sb.append(f2647b[i2]);
                sb.append(" ");
                sb.append(f2646a[i2 + 1]);
                i = i2 + 2;
            }
        } catch (SQLException e) {
            AbstractC0407b.m75d(e.toString());
        }
    }

    /* renamed from: a */
    public SQLiteDatabase m2706a() {
        SQLiteDatabase sQLiteDatabase;
        synchronized (this) {
            if (this.f2651a.incrementAndGet() == 1) {
                this.f2648a = getWritableDatabase();
            }
            sQLiteDatabase = this.f2648a;
        }
        return sQLiteDatabase;
    }

    /* renamed from: a */
    public void m2707a() {
        synchronized (this) {
            if (this.f2651a.decrementAndGet() == 0) {
                this.f2648a.close();
            }
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        synchronized (this.f2649a) {
            try {
                m2704a(sQLiteDatabase);
                m2705b(sQLiteDatabase);
                AbstractC0407b.m74c("GeoFenceDatabaseHelper. create tables");
            } catch (SQLException e) {
                AbstractC0407b.m72a(e);
            }
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
