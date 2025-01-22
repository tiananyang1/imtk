package com.xiaomi.push;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Process;
import android.text.TextUtils;
import com.stub.StubApp;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.mipush.sdk.Constants;
import com.xiaomi.push.service.C0794a;
import com.xiaomi.push.service.module.PushChannelRegion;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.xiaomi.push.dc */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/dc.class */
public class C0568dc {

    /* renamed from: a */
    protected static Context f650a;

    /* renamed from: a */
    private static a f651a;

    /* renamed from: a */
    private static C0568dc f652a;

    /* renamed from: c */
    private static String f655c;

    /* renamed from: d */
    private static String f656d;

    /* renamed from: a */
    private long f657a;

    /* renamed from: a */
    private InterfaceC0567db f658a;

    /* renamed from: a */
    protected b f659a;

    /* renamed from: a */
    private String f660a;

    /* renamed from: a */
    protected Map<String, C0564cz> f661a;

    /* renamed from: b */
    private final long f662b;

    /* renamed from: b */
    private String f663b;

    /* renamed from: c */
    private long f664c;

    /* renamed from: b */
    protected static Map<String, C0563cy> f654b = new HashMap();

    /* renamed from: a */
    protected static boolean f653a = false;

    /* renamed from: com.xiaomi.push.dc$a */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/dc$a.class */
    public interface a {
        /* renamed from: a */
        C0568dc mo898a(Context context, InterfaceC0567db interfaceC0567db, b bVar, String str);
    }

    /* renamed from: com.xiaomi.push.dc$b */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/dc$b.class */
    public interface b {
        /* renamed from: a */
        String mo899a(String str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public C0568dc(Context context, InterfaceC0567db interfaceC0567db, b bVar, String str) {
        this(context, interfaceC0567db, bVar, str, null, null);
    }

    protected C0568dc(Context context, InterfaceC0567db interfaceC0567db, b bVar, String str, String str2, String str3) {
        this.f661a = new HashMap();
        this.f660a = "0";
        this.f657a = 0L;
        this.f662b = 15L;
        this.f664c = 0L;
        this.f663b = "isp_prov_city_country_ip";
        this.f659a = bVar;
        this.f658a = interfaceC0567db == null ? new C0569dd(this) : interfaceC0567db;
        this.f660a = str;
        f655c = str2 == null ? context.getPackageName() : str2;
        f656d = str3 == null ? m878f() : str3;
    }

    /* renamed from: a */
    public static C0568dc m871a() {
        C0568dc c0568dc;
        synchronized (C0568dc.class) {
            try {
                if (f652a == null) {
                    throw new IllegalStateException("the host manager is not initialized yet.");
                }
                c0568dc = f652a;
            } catch (Throwable th) {
                throw th;
            }
        }
        return c0568dc;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static String m872a() {
        NetworkInfo activeNetworkInfo;
        Context context = f650a;
        if (context == null) {
            return "unknown";
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null) {
                return "unknown";
            }
            if (activeNetworkInfo.getType() != 1) {
                return activeNetworkInfo.getTypeName() + Constants.ACCEPT_TIME_SEPARATOR_SERVER + activeNetworkInfo.getSubtypeName();
            }
            WifiManager wifiManager = (WifiManager) f650a.getSystemService("wifi");
            if (wifiManager == null || wifiManager.getConnectionInfo() == null) {
                return "unknown";
            }
            return "WIFI-" + wifiManager.getConnectionInfo().getSSID();
        } catch (Throwable th) {
            return "unknown";
        }
    }

    /* renamed from: a */
    static String m873a(String str) {
        try {
            int length = str.length();
            byte[] bytes = str.getBytes("UTF-8");
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= bytes.length) {
                    return new String(bytes);
                }
                byte b2 = bytes[i2];
                int i3 = b2 & 240;
                if (i3 != 240) {
                    bytes[i2] = (byte) (((b2 & 15) ^ ((byte) (((b2 >> 4) + length) & 15))) | i3);
                }
                i = i2 + 1;
            }
        } catch (UnsupportedEncodingException e) {
            return str;
        }
    }

    /* renamed from: a */
    private ArrayList<C0563cy> m874a(ArrayList<String> arrayList) {
        m896d();
        synchronized (this.f661a) {
            m886a();
            for (String str : this.f661a.keySet()) {
                if (!arrayList.contains(str)) {
                    arrayList.add(str);
                }
            }
        }
        boolean isEmpty = f654b.isEmpty();
        synchronized (f654b) {
            Object[] array = f654b.values().toArray();
            int length = array.length;
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= length) {
                    break;
                }
                C0563cy c0563cy = (C0563cy) array[i2];
                if (!c0563cy.mo849b()) {
                    f654b.remove(c0563cy.f637b);
                    isEmpty = true;
                }
                i = i2 + 1;
            }
        }
        if (!arrayList.contains(m888b())) {
            arrayList.add(m888b());
        }
        ArrayList<C0563cy> arrayList2 = new ArrayList<>(arrayList.size());
        int i3 = 0;
        while (true) {
            int i4 = i3;
            if (i4 < arrayList.size()) {
                arrayList2.add(null);
                i3 = i4 + 1;
            } else {
                try {
                    break;
                } catch (Exception e) {
                    AbstractC0407b.m70a("failed to get bucket " + e.getMessage());
                }
            }
        }
        String str2 = C0503as.m486d(f650a) ? "wifi" : "wap";
        String mo881a = mo881a(arrayList, str2, this.f660a, isEmpty);
        if (!TextUtils.isEmpty(mo881a)) {
            JSONObject jSONObject = new JSONObject(mo881a);
            AbstractC0407b.m73b(mo881a);
            if ("OK".equalsIgnoreCase(jSONObject.getString("S"))) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("R");
                String string = jSONObject2.getString("province");
                String string2 = jSONObject2.getString("city");
                String string3 = jSONObject2.getString("isp");
                String string4 = jSONObject2.getString("ip");
                String string5 = jSONObject2.getString("country");
                JSONObject jSONObject3 = jSONObject2.getJSONObject(str2);
                AbstractC0407b.m74c("get bucket: ip = " + string4 + " net = " + string3 + " hosts = " + jSONObject3.toString());
                int i5 = 0;
                while (true) {
                    int i6 = i5;
                    if (i6 >= arrayList.size()) {
                        break;
                    }
                    String str3 = arrayList.get(i6);
                    JSONArray optJSONArray = jSONObject3.optJSONArray(str3);
                    if (optJSONArray == null) {
                        AbstractC0407b.m70a("no bucket found for " + str3);
                    } else {
                        C0563cy c0563cy2 = new C0563cy(str3);
                        int i7 = 0;
                        while (true) {
                            int i8 = i7;
                            if (i8 >= optJSONArray.length()) {
                                break;
                            }
                            String string6 = optJSONArray.getString(i8);
                            if (!TextUtils.isEmpty(string6)) {
                                c0563cy2.m837a(new C0573dh(string6, optJSONArray.length() - i8));
                            }
                            i7 = i8 + 1;
                        }
                        arrayList2.set(i6, c0563cy2);
                        c0563cy2.f642g = string5;
                        c0563cy2.f638c = string;
                        c0563cy2.f640e = string3;
                        c0563cy2.f641f = string4;
                        c0563cy2.f639d = string2;
                        if (jSONObject2.has("stat-percent")) {
                            c0563cy2.m835a(jSONObject2.getDouble("stat-percent"));
                        }
                        if (jSONObject2.has("stat-domain")) {
                            c0563cy2.m846b(jSONObject2.getString("stat-domain"));
                        }
                        if (jSONObject2.has("ttl")) {
                            c0563cy2.m836a(jSONObject2.getInt("ttl") * 1000);
                        }
                        m884a(c0563cy2.m830a());
                    }
                    i5 = i6 + 1;
                }
                JSONObject optJSONObject = jSONObject2.optJSONObject("reserved");
                if (optJSONObject != null) {
                    long j = jSONObject2.has("reserved-ttl") ? jSONObject2.getInt("reserved-ttl") * 1000 : 604800000L;
                    Iterator<String> keys = optJSONObject.keys();
                    while (keys.hasNext()) {
                        String next = keys.next();
                        JSONArray optJSONArray2 = optJSONObject.optJSONArray(next);
                        if (optJSONArray2 == null) {
                            AbstractC0407b.m70a("no bucket found for " + next);
                        } else {
                            C0563cy c0563cy3 = new C0563cy(next);
                            c0563cy3.m836a(j);
                            int i9 = 0;
                            while (true) {
                                int i10 = i9;
                                if (i10 >= optJSONArray2.length()) {
                                    break;
                                }
                                String string7 = optJSONArray2.getString(i10);
                                if (!TextUtils.isEmpty(string7)) {
                                    c0563cy3.m837a(new C0573dh(string7, optJSONArray2.length() - i10));
                                }
                                i9 = i10 + 1;
                            }
                            synchronized (f654b) {
                                if (this.f658a.mo870a(next)) {
                                    f654b.put(next, c0563cy3);
                                }
                            }
                        }
                    }
                }
            }
        }
        int i11 = 0;
        while (true) {
            int i12 = i11;
            if (i12 >= arrayList.size()) {
                m893c();
                return arrayList2;
            }
            C0563cy c0563cy4 = arrayList2.get(i12);
            if (c0563cy4 != null) {
                m885a(arrayList.get(i12), c0563cy4);
            }
            i11 = i12 + 1;
        }
    }

    /* renamed from: a */
    public static void m875a(Context context, InterfaceC0567db interfaceC0567db, b bVar, String str, String str2, String str3) {
        synchronized (C0568dc.class) {
            try {
                f650a = StubApp.getOrigApplicationContext(context.getApplicationContext());
                if (f650a == null) {
                    f650a = context;
                }
                if (f652a == null) {
                    if (f651a == null) {
                        f652a = new C0568dc(context, interfaceC0567db, bVar, str, str2, str3);
                    } else {
                        f652a = f651a.mo898a(context, interfaceC0567db, bVar, str);
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* renamed from: a */
    public static void m876a(a aVar) {
        synchronized (C0568dc.class) {
            try {
                f651a = aVar;
                f652a = null;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* renamed from: a */
    public static void m877a(String str, String str2) {
        C0563cy c0563cy = f654b.get(str);
        synchronized (f654b) {
            if (c0563cy == null) {
                C0563cy c0563cy2 = new C0563cy(str);
                c0563cy2.m836a(604800000L);
                c0563cy2.m838a(str2);
                f654b.put(str, c0563cy2);
            } else {
                c0563cy.m838a(str2);
            }
        }
    }

    /* renamed from: f */
    private String m878f() {
        try {
            PackageInfo packageInfo = f650a.getPackageManager().getPackageInfo(f650a.getPackageName(), 16384);
            return packageInfo != null ? packageInfo.versionName : "0";
        } catch (Exception e) {
            return "0";
        }
    }

    /* renamed from: a */
    public C0563cy m879a(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("the url is empty");
        }
        return m880a(new URL(str).getHost(), true);
    }

    /* renamed from: a */
    public C0563cy m880a(String str, boolean z) {
        C0563cy m894d;
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("the host is empty");
        }
        if (!this.f658a.mo870a(str)) {
            return null;
        }
        C0563cy m891c = m891c(str);
        return (m891c == null || !m891c.mo849b()) ? (z && C0503as.m484b(f650a) && (m894d = m894d(str)) != null) ? m894d : new C0570de(this, str, m891c) : m891c;
    }

    /* renamed from: a */
    protected String mo881a(ArrayList<String> arrayList, String str, String str2, boolean z) {
        ArrayList<String> m832a;
        ArrayList<String> arrayList2 = new ArrayList<>();
        ArrayList<InterfaceC0502ar> arrayList3 = new ArrayList();
        arrayList3.add(new C0500ap("type", str));
        if (str.equals("wap")) {
            arrayList3.add(new C0500ap("conpt", m873a(C0503as.m475a(f650a))));
        }
        if (z) {
            arrayList3.add(new C0500ap("reserved", "1"));
        }
        arrayList3.add(new C0500ap("uuid", str2));
        arrayList3.add(new C0500ap("list", C0509ay.m523a(arrayList, Constants.ACCEPT_TIME_SEPARATOR_SP)));
        C0563cy m891c = m891c(m888b());
        String format = String.format(Locale.US, "http://%1$s/gslb/?ver=4.0", m888b());
        if (m891c == null) {
            arrayList2.add(format);
            synchronized (f654b) {
                C0563cy c0563cy = f654b.get("resolver.msg.xiaomi.net");
                if (c0563cy != null) {
                    Iterator<String> it = c0563cy.mo833a(true).iterator();
                    while (it.hasNext()) {
                        arrayList2.add(String.format(Locale.US, "http://%1$s/gslb/?ver=4.0", it.next()));
                    }
                }
            }
            m832a = arrayList2;
        } else {
            m832a = m891c.m832a(format);
        }
        Iterator<String> it2 = m832a.iterator();
        IOException e = null;
        while (it2.hasNext()) {
            Uri.Builder buildUpon = Uri.parse(it2.next()).buildUpon();
            for (InterfaceC0502ar interfaceC0502ar : arrayList3) {
                buildUpon.appendQueryParameter(interfaceC0502ar.mo466a(), interfaceC0502ar.mo467b());
            }
            try {
                return this.f659a == null ? C0503as.m476a(f650a, new URL(buildUpon.toString())) : this.f659a.mo899a(buildUpon.toString());
            } catch (IOException e2) {
                e = e2;
            }
        }
        if (e == null) {
            return null;
        }
        AbstractC0407b.m70a("network exception: " + e.getMessage());
        throw e;
    }

    /* renamed from: a */
    protected JSONObject m882a() {
        JSONObject jSONObject;
        synchronized (this.f661a) {
            jSONObject = new JSONObject();
            jSONObject.put("ver", 2);
            JSONArray jSONArray = new JSONArray();
            Iterator<C0564cz> it = this.f661a.values().iterator();
            while (it.hasNext()) {
                jSONArray.put(it.next().m855a());
            }
            jSONObject.put("data", jSONArray);
            JSONArray jSONArray2 = new JSONArray();
            Iterator<C0563cy> it2 = f654b.values().iterator();
            while (it2.hasNext()) {
                jSONArray2.put(it2.next().m834a());
            }
            jSONObject.put("reserved", jSONArray2);
        }
        return jSONObject;
    }

    /* renamed from: a */
    public void m883a() {
        synchronized (this.f661a) {
            this.f661a.clear();
        }
    }

    /* renamed from: a */
    public void m884a(String str) {
        this.f663b = str;
    }

    /* renamed from: a */
    public void m885a(String str, C0563cy c0563cy) {
        if (TextUtils.isEmpty(str) || c0563cy == null) {
            throw new IllegalArgumentException("the argument is invalid " + str + ", " + c0563cy);
        }
        if (this.f658a.mo870a(str)) {
            synchronized (this.f661a) {
                m886a();
                if (this.f661a.containsKey(str)) {
                    this.f661a.get(str).m856a(c0563cy);
                } else {
                    C0564cz c0564cz = new C0564cz(str);
                    c0564cz.m856a(c0563cy);
                    this.f661a.put(str, c0564cz);
                }
            }
        }
    }

    /* renamed from: a */
    protected boolean m886a() {
        synchronized (this.f661a) {
            if (f653a) {
                return true;
            }
            f653a = true;
            this.f661a.clear();
            try {
                String m895d = m895d();
                if (!TextUtils.isEmpty(m895d)) {
                    m890b(m895d);
                    AbstractC0407b.m73b("loading the new hosts succeed");
                    return true;
                }
            } catch (Throwable th) {
                AbstractC0407b.m70a("load bucket failure: " + th.getMessage());
            }
            return false;
        }
    }

    /* renamed from: b */
    public C0563cy m887b(String str) {
        return m880a(str, true);
    }

    /* renamed from: b */
    protected String m888b() {
        String m2490a = C0794a.m2485a(f650a).m2490a();
        String str = "resolver.msg.xiaomi.net";
        if (!TextUtils.isEmpty(m2490a)) {
            if (PushChannelRegion.China.name().equals(m2490a)) {
                return "resolver.msg.xiaomi.net";
            }
            str = "resolver.msg.global.xiaomi.net";
        }
        return str;
    }

    /* renamed from: b */
    public void m889b() {
        ArrayList<String> arrayList;
        synchronized (this.f661a) {
            m886a();
            arrayList = new ArrayList<>(this.f661a.keySet());
            int size = arrayList.size();
            while (true) {
                int i = size - 1;
                if (i < 0) {
                    break;
                }
                C0564cz c0564cz = this.f661a.get(arrayList.get(i));
                if (c0564cz != null && c0564cz.m851a() != null) {
                    arrayList.remove(i);
                }
                size = i;
            }
        }
        ArrayList<C0563cy> m874a = m874a(arrayList);
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 >= arrayList.size()) {
                return;
            }
            if (m874a.get(i3) != null) {
                m885a(arrayList.get(i3), m874a.get(i3));
            }
            i2 = i3 + 1;
        }
    }

    /* renamed from: b */
    protected void m890b(String str) {
        synchronized (this.f661a) {
            this.f661a.clear();
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.optInt("ver") != 2) {
                throw new JSONException("Bad version");
            }
            JSONArray optJSONArray = jSONObject.optJSONArray("data");
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= optJSONArray.length()) {
                    break;
                }
                C0564cz m852a = new C0564cz().m852a(optJSONArray.getJSONObject(i2));
                this.f661a.put(m852a.m853a(), m852a);
                i = i2 + 1;
            }
            JSONArray optJSONArray2 = jSONObject.optJSONArray("reserved");
            int i3 = 0;
            while (true) {
                int i4 = i3;
                if (i4 < optJSONArray2.length()) {
                    C0563cy m829a = new C0563cy("").m829a(optJSONArray2.getJSONObject(i4));
                    f654b.put(m829a.f637b, m829a);
                    i3 = i4 + 1;
                }
            }
        }
    }

    /* renamed from: c */
    protected C0563cy m891c(String str) {
        C0564cz c0564cz;
        C0563cy m851a;
        synchronized (this.f661a) {
            m886a();
            c0564cz = this.f661a.get(str);
        }
        if (c0564cz == null || (m851a = c0564cz.m851a()) == null) {
            return null;
        }
        return m851a;
    }

    /* renamed from: c */
    public String m892c() {
        StringBuilder sb = new StringBuilder();
        synchronized (this.f661a) {
            for (Map.Entry<String, C0564cz> entry : this.f661a.entrySet()) {
                sb.append(entry.getKey());
                sb.append(":\n");
                sb.append(entry.getValue().toString());
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    /* renamed from: c */
    public void m893c() {
        synchronized (this.f661a) {
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(f650a.openFileOutput(m897e(), 0)));
                String jSONObject = m882a().toString();
                if (!TextUtils.isEmpty(jSONObject)) {
                    bufferedWriter.write(jSONObject);
                }
                bufferedWriter.close();
            } catch (Exception e) {
                AbstractC0407b.m70a("persist bucket failure: " + e.getMessage());
            }
        }
    }

    /* renamed from: d */
    protected C0563cy m894d(String str) {
        if (System.currentTimeMillis() - this.f664c <= this.f657a * 60 * 1000) {
            return null;
        }
        this.f664c = System.currentTimeMillis();
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(str);
        C0563cy c0563cy = m874a(arrayList).get(0);
        if (c0563cy != null) {
            this.f657a = 0L;
            return c0563cy;
        }
        long j = this.f657a;
        if (j >= 15) {
            return null;
        }
        this.f657a = j + 1;
        return null;
    }

    /*  JADX ERROR: Types fix failed
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:447)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryPossibleTypes(FixTypesVisitor.java:183)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.deduceType(FixTypesVisitor.java:242)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryDeduceTypes(FixTypesVisitor.java:221)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:91)
        */
    /* JADX WARN: Failed to calculate best type for var: r0v2 ??
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:447)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:447)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
     */
    /* JADX WARN: Not initialized variable reg: 9, insn: 0x00a6: MOVE (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r9 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]), block:B:36:0x00a6 */
    /* renamed from: d */
    protected java.lang.String m895d() {
        /*
            r8 = this;
            java.io.File r0 = new java.io.File     // Catch: java.lang.Throwable -> L6a java.lang.Throwable -> L70
            r1 = r0
            android.content.Context r2 = com.xiaomi.push.C0568dc.f650a     // Catch: java.lang.Throwable -> L6a java.lang.Throwable -> L70
            java.io.File r2 = r2.getFilesDir()     // Catch: java.lang.Throwable -> L6a java.lang.Throwable -> L70
            r3 = r8
            java.lang.String r3 = r3.m897e()     // Catch: java.lang.Throwable -> L6a java.lang.Throwable -> L70
            r1.<init>(r2, r3)     // Catch: java.lang.Throwable -> L6a java.lang.Throwable -> L70
            r9 = r0
            r0 = r9
            boolean r0 = r0.isFile()     // Catch: java.lang.Throwable -> L6a java.lang.Throwable -> L70
            if (r0 == 0) goto L64
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L6a java.lang.Throwable -> L70
            r1 = r0
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L6a java.lang.Throwable -> L70
            r3 = r2
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L6a java.lang.Throwable -> L70
            r5 = r4
            r6 = r9
            r5.<init>(r6)     // Catch: java.lang.Throwable -> L6a java.lang.Throwable -> L70
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L6a java.lang.Throwable -> L70
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L6a java.lang.Throwable -> L70
            r10 = r0
            r0 = r10
            r9 = r0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L60 java.lang.Throwable -> La5
            r1 = r0
            r1.<init>()     // Catch: java.lang.Throwable -> L60 java.lang.Throwable -> La5
            r11 = r0
        L3a:
            r0 = r10
            r9 = r0
            r0 = r10
            java.lang.String r0 = r0.readLine()     // Catch: java.lang.Throwable -> L60 java.lang.Throwable -> La5
            r12 = r0
            r0 = r12
            if (r0 == 0) goto L53
            r0 = r10
            r9 = r0
            r0 = r11
            r1 = r12
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch: java.lang.Throwable -> L60 java.lang.Throwable -> La5
            goto L3a
        L53:
            r0 = r10
            r9 = r0
            r0 = r11
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> L60 java.lang.Throwable -> La5
            r11 = r0
            r0 = r10
            com.xiaomi.push.C0883y.m2858a(r0)
            r0 = r11
            return r0
        L60:
            r11 = move-exception
            goto L73
        L64:
            r0 = 0
            com.xiaomi.push.C0883y.m2858a(r0)
            r0 = 0
            return r0
        L6a:
            r9 = move-exception
            r0 = 0
            r10 = r0
            goto Laa
        L70:
            r11 = move-exception
            r0 = 0
            r10 = r0
        L73:
            r0 = r10
            r9 = r0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> La5
            r1 = r0
            r1.<init>()     // Catch: java.lang.Throwable -> La5
            r12 = r0
            r0 = r10
            r9 = r0
            r0 = r12
            java.lang.String r1 = "load host exception "
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch: java.lang.Throwable -> La5
            r0 = r10
            r9 = r0
            r0 = r12
            r1 = r11
            java.lang.String r1 = r1.getMessage()     // Catch: java.lang.Throwable -> La5
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch: java.lang.Throwable -> La5
            r0 = r10
            r9 = r0
            r0 = r12
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> La5
            com.xiaomi.channel.commonutils.logger.AbstractC0407b.m70a(r0)     // Catch: java.lang.Throwable -> La5
            r0 = r10
            com.xiaomi.push.C0883y.m2858a(r0)
            r0 = 0
            return r0
        La5:
            r11 = move-exception
            r0 = r9
            r10 = r0
            r0 = r11
            r9 = r0
        Laa:
            r0 = r10
            com.xiaomi.push.C0883y.m2858a(r0)
            r0 = r9
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.C0568dc.m895d():java.lang.String");
    }

    /* renamed from: d */
    public void m896d() {
        String next;
        synchronized (this.f661a) {
            Iterator<C0564cz> it = this.f661a.values().iterator();
            while (it.hasNext()) {
                it.next().m857a(true);
            }
            while (true) {
                for (boolean z = false; !z; z = true) {
                    Iterator<String> it2 = this.f661a.keySet().iterator();
                    while (it2.hasNext()) {
                        next = it2.next();
                        if (this.f661a.get(next).m854a().isEmpty()) {
                            break;
                        }
                    }
                }
                this.f661a.remove(next);
            }
        }
    }

    /* renamed from: e */
    protected String m897e() {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) f650a.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return "com.xiaomi";
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.pid == Process.myPid()) {
                return runningAppProcessInfo.processName;
            }
        }
        return "com.xiaomi";
    }
}
