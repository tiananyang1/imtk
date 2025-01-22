package com.xiaomi.push.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.mipush.sdk.MIPushNotificationHelper4Hybrid;
import com.xiaomi.push.C0499ao;
import com.xiaomi.push.service.module.C0863a;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: com.xiaomi.push.service.i */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/i.class */
public class C0858i {

    /* renamed from: a */
    private static volatile C0858i f2652a;

    /* renamed from: a */
    private Context f2653a;

    private C0858i(Context context) {
        this.f2653a = context;
    }

    /* renamed from: a */
    private Cursor m2708a(SQLiteDatabase sQLiteDatabase) {
        Cursor query;
        synchronized (this) {
            C0499ao.m465a(false);
            try {
                query = sQLiteDatabase.query("geoMessage", null, null, null, null, null, null);
            } catch (Exception e) {
                AbstractC0407b.m75d(e.toString());
                return null;
            }
        }
        return query;
    }

    /* renamed from: a */
    public static C0858i m2709a(Context context) {
        if (f2652a == null) {
            synchronized (C0858i.class) {
                try {
                    if (f2652a == null) {
                        f2652a = new C0858i(context);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return f2652a;
    }

    /* renamed from: a */
    public int m2710a(String str) {
        synchronized (this) {
            C0499ao.m465a(false);
            if (TextUtils.isEmpty(str)) {
                return 0;
            }
            try {
                int delete = C0857h.m2703a(this.f2653a).m2706a().delete("geoMessage", "message_id = ?", new String[]{str});
                C0857h.m2703a(this.f2653a).m2707a();
                return delete;
            } catch (Exception e) {
                AbstractC0407b.m75d(e.toString());
                return 0;
            }
        }
    }

    /* renamed from: a */
    public ArrayList<C0863a> m2711a() {
        ArrayList<C0863a> arrayList;
        synchronized (this) {
            C0499ao.m465a(false);
            try {
                Cursor m2708a = m2708a(C0857h.m2703a(this.f2653a).m2706a());
                arrayList = new ArrayList<>();
                if (m2708a != null) {
                    while (m2708a.moveToNext()) {
                        C0863a c0863a = new C0863a();
                        c0863a.m2765a(m2708a.getString(m2708a.getColumnIndex(MIPushNotificationHelper4Hybrid.KEY_MESSAGE_ID)));
                        c0863a.m2769b(m2708a.getString(m2708a.getColumnIndex("geo_id")));
                        c0863a.m2766a(m2708a.getBlob(m2708a.getColumnIndex("content")));
                        c0863a.m2763a(m2708a.getInt(m2708a.getColumnIndex("action")));
                        c0863a.m2764a(m2708a.getLong(m2708a.getColumnIndex("deadline")));
                        arrayList.add(c0863a);
                    }
                    m2708a.close();
                }
                C0857h.m2703a(this.f2653a).m2707a();
            } catch (Exception e) {
                AbstractC0407b.m75d(e.toString());
                return null;
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    public ArrayList<C0863a> m2712a(String str) {
        synchronized (this) {
            C0499ao.m465a(false);
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            try {
                ArrayList<C0863a> m2711a = m2711a();
                ArrayList<C0863a> arrayList = new ArrayList<>();
                Iterator<C0863a> it = m2711a.iterator();
                while (it.hasNext()) {
                    C0863a next = it.next();
                    if (TextUtils.equals(next.m2768b(), str)) {
                        arrayList.add(next);
                    }
                }
                return arrayList;
            } catch (Exception e) {
                AbstractC0407b.m75d(e.toString());
                return null;
            }
        }
    }

    /* renamed from: a */
    public boolean m2713a(ArrayList<ContentValues> arrayList) {
        boolean z;
        synchronized (this) {
            C0499ao.m465a(false);
            if (arrayList == null || arrayList.size() <= 0) {
                return false;
            }
            try {
                SQLiteDatabase m2706a = C0857h.m2703a(this.f2653a).m2706a();
                m2706a.beginTransaction();
                Iterator<ContentValues> it = arrayList.iterator();
                while (true) {
                    z = true;
                    if (!it.hasNext()) {
                        break;
                    }
                    if (-1 == m2706a.insert("geoMessage", null, it.next())) {
                        z = false;
                        break;
                    }
                }
                if (z) {
                    m2706a.setTransactionSuccessful();
                }
                m2706a.endTransaction();
                C0857h.m2703a(this.f2653a).m2707a();
                return z;
            } catch (Exception e) {
                AbstractC0407b.m75d(e.toString());
                return false;
            }
        }
    }

    /* renamed from: b */
    public int m2714b(String str) {
        synchronized (this) {
            C0499ao.m465a(false);
            if (TextUtils.isEmpty(str)) {
                return 0;
            }
            try {
                int delete = C0857h.m2703a(this.f2653a).m2706a().delete("geoMessage", "geo_id = ?", new String[]{str});
                C0857h.m2703a(this.f2653a).m2707a();
                return delete;
            } catch (Exception e) {
                AbstractC0407b.m75d(e.toString());
                return 0;
            }
        }
    }
}
