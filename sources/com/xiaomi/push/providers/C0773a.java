package com.xiaomi.push.providers;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.mipush.sdk.Constants;

/* renamed from: com.xiaomi.push.providers.a */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/providers/a.class */
public class C0773a extends SQLiteOpenHelper {

    /* renamed from: a */
    private static int f2364a = 1;

    /* renamed from: a */
    public static final Object f2365a = new Object();

    /* renamed from: a */
    private static final String[] f2366a = {Constants.PACKAGE_NAME, "TEXT", "message_ts", " LONG DEFAULT 0 ", "bytes", " LONG DEFAULT 0 ", "network_type", " INT DEFAULT -1 ", "rcv", " INT DEFAULT -1 ", "imsi", "TEXT"};

    public C0773a(Context context) {
        super(context, "traffic.db", (SQLiteDatabase.CursorFactory) null, f2364a);
    }

    /* renamed from: a */
    private void m2414a(SQLiteDatabase sQLiteDatabase) {
        StringBuilder sb = new StringBuilder("CREATE TABLE traffic(_id INTEGER  PRIMARY KEY ,");
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= f2366a.length - 1) {
                sb.append(");");
                sQLiteDatabase.execSQL(sb.toString());
                return;
            }
            if (i2 != 0) {
                sb.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
            }
            sb.append(f2366a[i2]);
            sb.append(" ");
            sb.append(f2366a[i2 + 1]);
            i = i2 + 2;
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        synchronized (f2365a) {
            try {
                m2414a(sQLiteDatabase);
            } catch (SQLException e) {
                AbstractC0407b.m72a(e);
            }
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
