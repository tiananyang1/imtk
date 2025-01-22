package com.xiaomi.push.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.mipush.sdk.Constants;
import com.xiaomi.push.C0499ao;
import com.xiaomi.push.C0709ii;
import com.xiaomi.push.C0711ik;
import com.xiaomi.push.EnumC0706if;
import com.xiaomi.push.EnumC0710ij;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.xiaomi.push.service.g */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/g.class */
public class C0856g {

    /* renamed from: a */
    private static volatile C0856g f2642a;

    /* renamed from: a */
    private static String f2643a = "GeoFenceDao.";

    /* renamed from: a */
    private Context f2644a;

    private C0856g(Context context) {
        this.f2644a = context;
    }

    /* renamed from: a */
    private Cursor m2688a(SQLiteDatabase sQLiteDatabase) {
        Cursor query;
        synchronized (this) {
            C0499ao.m465a(false);
            try {
                query = sQLiteDatabase.query("geofence", null, null, null, null, null, null);
            } catch (Exception e) {
                return null;
            }
        }
        return query;
    }

    /* renamed from: a */
    private EnumC0706if m2689a(Cursor cursor) {
        EnumC0706if valueOf;
        synchronized (this) {
            try {
                valueOf = EnumC0706if.valueOf(cursor.getString(cursor.getColumnIndex("coordinate_provider")));
            } catch (IllegalArgumentException e) {
                AbstractC0407b.m75d(e.toString());
                return null;
            }
        }
        return valueOf;
    }

    /* renamed from: a */
    private EnumC0710ij m2690a(Cursor cursor) {
        synchronized (this) {
            try {
                EnumC0710ij[] values = EnumC0710ij.values();
                int length = values.length;
                int i = 0;
                while (true) {
                    int i2 = i;
                    if (i2 >= length) {
                        return null;
                    }
                    EnumC0710ij enumC0710ij = values[i2];
                    if (TextUtils.equals(cursor.getString(cursor.getColumnIndex("type")), enumC0710ij.name())) {
                        return enumC0710ij;
                    }
                    i = i2 + 1;
                }
            } catch (Exception e) {
                AbstractC0407b.m75d(e.toString());
                return null;
            }
        }
    }

    /* renamed from: a */
    private C0711ik m2691a(Cursor cursor) {
        C0711ik c0711ik;
        synchronized (this) {
            c0711ik = new C0711ik();
            try {
                c0711ik.m1745b(Double.parseDouble(cursor.getString(cursor.getColumnIndex("center_lantitude"))));
                c0711ik.m1739a(Double.parseDouble(cursor.getString(cursor.getColumnIndex("center_longtitude"))));
            } catch (Exception e) {
                AbstractC0407b.m75d(e.toString());
                return null;
            }
        }
        return c0711ik;
    }

    /* renamed from: a */
    public static C0856g m2692a(Context context) {
        if (f2642a == null) {
            synchronized (C0856g.class) {
                try {
                    if (f2642a == null) {
                        f2642a = new C0856g(context);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return f2642a;
    }

    /* renamed from: a */
    private String m2693a(List<C0711ik> list) {
        synchronized (this) {
            if (list != null) {
                if (list.size() >= 3) {
                    JSONArray jSONArray = new JSONArray();
                    try {
                        for (C0711ik c0711ik : list) {
                            if (c0711ik != null) {
                                JSONObject jSONObject = new JSONObject();
                                jSONObject.put("point_lantitude", c0711ik.m1744b());
                                jSONObject.put("point_longtitude", c0711ik.m1737a());
                                jSONArray.put(jSONObject);
                            }
                        }
                        return jSONArray.toString();
                    } catch (JSONException e) {
                        AbstractC0407b.m75d(e.toString());
                        return null;
                    }
                }
            }
            AbstractC0407b.m70a(f2643a + " points unvalidated");
            return null;
        }
    }

    /* renamed from: a */
    private ArrayList<C0711ik> m2694a(Cursor cursor) {
        ArrayList<C0711ik> arrayList;
        synchronized (this) {
            arrayList = new ArrayList<>();
            try {
                JSONArray jSONArray = new JSONArray(cursor.getString(cursor.getColumnIndex("polygon_points")));
                int i = 0;
                while (true) {
                    int i2 = i;
                    if (i2 < jSONArray.length()) {
                        C0711ik c0711ik = new C0711ik();
                        JSONObject jSONObject = (JSONObject) jSONArray.get(i2);
                        c0711ik.m1745b(jSONObject.getDouble("point_lantitude"));
                        c0711ik.m1739a(jSONObject.getDouble("point_longtitude"));
                        arrayList.add(c0711ik);
                        i = i2 + 1;
                    }
                }
            } catch (JSONException e) {
                AbstractC0407b.m75d(e.toString());
                return null;
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    public int m2695a(String str) {
        synchronized (this) {
            C0499ao.m465a(false);
            try {
                if (m2698a(str) == null) {
                    return 0;
                }
                int delete = C0857h.m2703a(this.f2644a).m2706a().delete("geofence", "id = ?", new String[]{str});
                C0857h.m2703a(this.f2644a).m2707a();
                return delete;
            } catch (Exception e) {
                AbstractC0407b.m75d(e.toString());
                return 0;
            }
        }
    }

    /* renamed from: a */
    public int m2696a(String str, String str2) {
        synchronized (this) {
            C0499ao.m465a(false);
            try {
                if (!"Enter".equals(str2) && !"Leave".equals(str2) && !"Unknown".equals(str2)) {
                    return 0;
                }
                ContentValues contentValues = new ContentValues();
                contentValues.put("current_status", str2);
                int update = C0857h.m2703a(this.f2644a).m2706a().update("geofence", contentValues, "id=?", new String[]{str});
                C0857h.m2703a(this.f2644a).m2707a();
                return update;
            } catch (Exception e) {
                AbstractC0407b.m75d(e.toString());
                return 0;
            }
        }
    }

    /* renamed from: a */
    public long m2697a(C0709ii c0709ii) {
        long insert;
        synchronized (this) {
            C0499ao.m465a(false);
            try {
                ContentValues contentValues = new ContentValues();
                contentValues.put("id", c0709ii.m1712a());
                contentValues.put("appId", Long.valueOf(c0709ii.m1701a()));
                contentValues.put("name", c0709ii.m1721b());
                contentValues.put(Constants.PACKAGE_NAME, c0709ii.m1725c());
                contentValues.put("create_time", Long.valueOf(c0709ii.m1718b()));
                contentValues.put("type", c0709ii.m1710a().name());
                contentValues.put("center_longtitude", String.valueOf(c0709ii.m1711a().m1737a()));
                contentValues.put("center_lantitude", String.valueOf(c0709ii.m1711a().m1744b()));
                contentValues.put("circle_radius", Double.valueOf(c0709ii.m1699a()));
                contentValues.put("polygon_point", m2693a(c0709ii.m1713a()));
                contentValues.put("coordinate_provider", c0709ii.m1702a().name());
                contentValues.put("current_status", "Unknown");
                insert = C0857h.m2703a(this.f2644a).m2706a().insert("geofence", null, contentValues);
                C0857h.m2703a(this.f2644a).m2707a();
            } catch (Exception e) {
                AbstractC0407b.m75d(e.toString());
                return -1L;
            }
        }
        return insert;
    }

    /* renamed from: a */
    public C0709ii m2698a(String str) {
        synchronized (this) {
            C0499ao.m465a(false);
            try {
                Iterator<C0709ii> it = m2700a().iterator();
                while (it.hasNext()) {
                    C0709ii next = it.next();
                    if (TextUtils.equals(next.m1712a(), str)) {
                        return next;
                    }
                }
                return null;
            } catch (Exception e) {
                AbstractC0407b.m75d(e.toString());
                return null;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x001f, code lost:            if (r0.moveToNext() == false) goto L35;     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0034, code lost:            if (android.text.TextUtils.equals(r0.getString(r0.getColumnIndex("id")), r5) == false) goto L36;     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0037, code lost:            r0 = r0.getString(r0.getColumnIndex("current_status"));        com.xiaomi.channel.commonutils.logger.AbstractC0407b.m74c(com.xiaomi.push.service.C0856g.f2643a + "findGeoStatueByGeoId: geo current statue is " + r0 + " geoId:" + r5);        r0.close();     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0089, code lost:            return r0;     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x008a, code lost:            r0.close();     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0091, code lost:            com.xiaomi.push.service.C0857h.m2703a(r4.f2644a).m2707a();     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x009c, code lost:            return "Unknown";     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0016, code lost:            if (r0 != null) goto L8;     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String m2699a(java.lang.String r5) {
        /*
            r4 = this;
            r0 = r4
            monitor-enter(r0)
            r0 = 0
            com.xiaomi.push.C0499ao.m465a(r0)     // Catch: java.lang.Throwable -> Lac
            r0 = r4
            r1 = r4
            android.content.Context r1 = r1.f2644a     // Catch: java.lang.Exception -> L9f java.lang.Throwable -> Lac
            com.xiaomi.push.service.h r1 = com.xiaomi.push.service.C0857h.m2703a(r1)     // Catch: java.lang.Exception -> L9f java.lang.Throwable -> Lac
            android.database.sqlite.SQLiteDatabase r1 = r1.m2706a()     // Catch: java.lang.Exception -> L9f java.lang.Throwable -> Lac
            android.database.Cursor r0 = r0.m2688a(r1)     // Catch: java.lang.Exception -> L9f java.lang.Throwable -> Lac
            r6 = r0
            r0 = r6
            if (r0 == 0) goto L90
        L19:
            r0 = r6
            boolean r0 = r0.moveToNext()     // Catch: java.lang.Exception -> L9f java.lang.Throwable -> Lac
            if (r0 == 0) goto L8a
            r0 = r6
            r1 = r6
            java.lang.String r2 = "id"
            int r1 = r1.getColumnIndex(r2)     // Catch: java.lang.Exception -> L9f java.lang.Throwable -> Lac
            java.lang.String r0 = r0.getString(r1)     // Catch: java.lang.Exception -> L9f java.lang.Throwable -> Lac
            r1 = r5
            boolean r0 = android.text.TextUtils.equals(r0, r1)     // Catch: java.lang.Exception -> L9f java.lang.Throwable -> Lac
            if (r0 == 0) goto L19
            r0 = r6
            r1 = r6
            java.lang.String r2 = "current_status"
            int r1 = r1.getColumnIndex(r2)     // Catch: java.lang.Exception -> L9f java.lang.Throwable -> Lac
            java.lang.String r0 = r0.getString(r1)     // Catch: java.lang.Exception -> L9f java.lang.Throwable -> Lac
            r7 = r0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L9f java.lang.Throwable -> Lac
            r1 = r0
            r1.<init>()     // Catch: java.lang.Exception -> L9f java.lang.Throwable -> Lac
            r8 = r0
            r0 = r8
            java.lang.String r1 = com.xiaomi.push.service.C0856g.f2643a     // Catch: java.lang.Exception -> L9f java.lang.Throwable -> Lac
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch: java.lang.Exception -> L9f java.lang.Throwable -> Lac
            r0 = r8
            java.lang.String r1 = "findGeoStatueByGeoId: geo current statue is "
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch: java.lang.Exception -> L9f java.lang.Throwable -> Lac
            r0 = r8
            r1 = r7
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch: java.lang.Exception -> L9f java.lang.Throwable -> Lac
            r0 = r8
            java.lang.String r1 = " geoId:"
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch: java.lang.Exception -> L9f java.lang.Throwable -> Lac
            r0 = r8
            r1 = r5
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch: java.lang.Exception -> L9f java.lang.Throwable -> Lac
            r0 = r8
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Exception -> L9f java.lang.Throwable -> Lac
            com.xiaomi.channel.commonutils.logger.AbstractC0407b.m74c(r0)     // Catch: java.lang.Exception -> L9f java.lang.Throwable -> Lac
            r0 = r6
            r0.close()     // Catch: java.lang.Exception -> L9f java.lang.Throwable -> Lac
            r0 = r4
            monitor-exit(r0)
            r0 = r7
            return r0
        L8a:
            r0 = r6
            r0.close()     // Catch: java.lang.Exception -> L9f java.lang.Throwable -> Lac
        L90:
            r0 = r4
            android.content.Context r0 = r0.f2644a     // Catch: java.lang.Exception -> L9f java.lang.Throwable -> Lac
            com.xiaomi.push.service.h r0 = com.xiaomi.push.service.C0857h.m2703a(r0)     // Catch: java.lang.Exception -> L9f java.lang.Throwable -> Lac
            r0.m2707a()     // Catch: java.lang.Exception -> L9f java.lang.Throwable -> Lac
            r0 = r4
            monitor-exit(r0)
            java.lang.String r0 = "Unknown"
            return r0
        L9f:
            r5 = move-exception
            r0 = r5
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> Lac
            com.xiaomi.channel.commonutils.logger.AbstractC0407b.m75d(r0)     // Catch: java.lang.Throwable -> Lac
            r0 = r4
            monitor-exit(r0)
            java.lang.String r0 = "Unknown"
            return r0
        Lac:
            r5 = move-exception
            r0 = r4
            monitor-exit(r0)
            r0 = r5
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.service.C0856g.m2699a(java.lang.String):java.lang.String");
    }

    /* renamed from: a */
    public ArrayList<C0709ii> m2700a() {
        ArrayList<C0709ii> arrayList;
        C0709ii c0709ii;
        EnumC0710ij m2690a;
        String str;
        synchronized (this) {
            C0499ao.m465a(false);
            try {
                Cursor m2688a = m2688a(C0857h.m2703a(this.f2644a).m2706a());
                arrayList = new ArrayList<>();
                if (m2688a != null) {
                    while (m2688a.moveToNext()) {
                        try {
                            c0709ii = new C0709ii();
                            c0709ii.m1708a(m2688a.getString(m2688a.getColumnIndex("id")));
                            c0709ii.m1720b(m2688a.getString(m2688a.getColumnIndex("name")));
                            c0709ii.m1704a(m2688a.getInt(m2688a.getColumnIndex("appId")));
                            c0709ii.m1724c(m2688a.getString(m2688a.getColumnIndex(Constants.PACKAGE_NAME)));
                            c0709ii.m1719b(m2688a.getInt(m2688a.getColumnIndex("create_time")));
                            m2690a = m2690a(m2688a);
                        } catch (Exception e) {
                            AbstractC0407b.m75d(e.toString());
                        }
                        if (m2690a == null) {
                            str = f2643a + "findAllGeoFencing: geo type null";
                        } else {
                            c0709ii.m1706a(m2690a);
                            if (TextUtils.equals("Circle", m2690a.name())) {
                                c0709ii.m1707a(m2691a(m2688a));
                                c0709ii.m1703a(m2688a.getDouble(m2688a.getColumnIndex("circle_radius")));
                            } else if (TextUtils.equals("Polygon", m2690a.name())) {
                                ArrayList<C0711ik> m2694a = m2694a(m2688a);
                                if (m2694a != null && m2694a.size() >= 3) {
                                    c0709ii.m1709a(m2694a);
                                }
                                str = f2643a + "findAllGeoFencing: geo points null or size<3";
                            }
                            EnumC0706if m2689a = m2689a(m2688a);
                            if (m2689a == null) {
                                AbstractC0407b.m74c(f2643a + "findAllGeoFencing: geo Coordinate Provider null ");
                            } else {
                                c0709ii.m1705a(m2689a);
                                arrayList.add(c0709ii);
                            }
                        }
                        AbstractC0407b.m74c(str);
                    }
                    m2688a.close();
                }
                C0857h.m2703a(this.f2644a).m2707a();
            } catch (Exception e2) {
                AbstractC0407b.m75d(e2.toString());
                return null;
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    public ArrayList<C0709ii> m2701a(String str) {
        ArrayList<C0709ii> arrayList;
        synchronized (this) {
            C0499ao.m465a(false);
            try {
                ArrayList<C0709ii> m2700a = m2700a();
                arrayList = new ArrayList<>();
                Iterator<C0709ii> it = m2700a.iterator();
                while (it.hasNext()) {
                    C0709ii next = it.next();
                    if (TextUtils.equals(next.m1725c(), str)) {
                        arrayList.add(next);
                    }
                }
            } catch (Exception e) {
                AbstractC0407b.m75d(e.toString());
                return null;
            }
        }
        return arrayList;
    }

    /* renamed from: b */
    public int m2702b(String str) {
        synchronized (this) {
            C0499ao.m465a(false);
            try {
                if (TextUtils.isEmpty(str)) {
                    return 0;
                }
                int delete = C0857h.m2703a(this.f2644a).m2706a().delete("geofence", "package_name = ?", new String[]{str});
                C0857h.m2703a(this.f2644a).m2707a();
                return delete;
            } catch (Exception e) {
                AbstractC0407b.m75d(e.toString());
                return 0;
            }
        }
    }
}
