package com.xiaomi.push;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.subgraph.orchid.Cell;
import fr.greweb.reactnativeviewshot.DebugViews;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.xiaomi.push.dg */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/dg.class */
public abstract class AbstractC0572dg {

    /* renamed from: com.xiaomi.push.dg$a */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/dg$a.class */
    public static class a extends AbstractC0571df {
        public a() {
            super(1);
        }

        @Override // com.xiaomi.push.AbstractC0571df
        /* renamed from: a */
        public String mo901a(Context context, String str, List<InterfaceC0502ar> list) {
            URL url;
            if (list == null) {
                url = new URL(str);
            } else {
                Uri.Builder buildUpon = Uri.parse(str).buildUpon();
                for (InterfaceC0502ar interfaceC0502ar : list) {
                    buildUpon.appendQueryParameter(interfaceC0502ar.mo466a(), interfaceC0502ar.mo467b());
                }
                url = new URL(buildUpon.toString());
            }
            return C0503as.m476a(context, url);
        }
    }

    /* renamed from: a */
    static int m903a(int i, int i2) {
        return (((i2 + 243) / 1448) * Cell.AUTHORIZE) + 1080 + i + i2;
    }

    /* renamed from: a */
    static int m904a(int i, int i2, int i3) {
        return (((i2 + DebugViews.LOG_MSG_LIMIT) / 1448) * Cell.AUTHORIZE) + 1011 + i2 + i + i3;
    }

    /* renamed from: a */
    private static int m905a(AbstractC0571df abstractC0571df, String str, List<InterfaceC0502ar> list, String str2) {
        if (abstractC0571df.m900a() == 1) {
            return m903a(str.length(), m906a(str2));
        }
        if (abstractC0571df.m900a() != 2) {
            return -1;
        }
        return m904a(str.length(), m907a(list), m906a(str2));
    }

    /* renamed from: a */
    static int m906a(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        try {
            return str.getBytes("UTF-8").length;
        } catch (UnsupportedEncodingException e) {
            return 0;
        }
    }

    /* renamed from: a */
    static int m907a(List<InterfaceC0502ar> list) {
        int i = 0;
        for (InterfaceC0502ar interfaceC0502ar : list) {
            int i2 = i;
            if (!TextUtils.isEmpty(interfaceC0502ar.mo466a())) {
                i2 = i + interfaceC0502ar.mo466a().length();
            }
            i = i2;
            if (!TextUtils.isEmpty(interfaceC0502ar.mo467b())) {
                i = i2 + interfaceC0502ar.mo467b().length();
            }
        }
        return i * 2;
    }

    /* renamed from: a */
    public static String m908a(Context context, String str, List<InterfaceC0502ar> list) {
        return m909a(context, str, list, new a(), true);
    }

    /* renamed from: a */
    public static String m909a(Context context, String str, List<InterfaceC0502ar> list, AbstractC0571df abstractC0571df, boolean z) {
        C0563cy c0563cy;
        String str2;
        String str3;
        IOException e;
        if (!C0503as.m484b(context)) {
            return null;
        }
        try {
            ArrayList<String> arrayList = new ArrayList<>();
            if (z) {
                c0563cy = C0568dc.m871a().m879a(str);
                if (c0563cy != null) {
                    arrayList = c0563cy.m832a(str);
                }
            } else {
                c0563cy = null;
            }
            if (!arrayList.contains(str)) {
                arrayList.add(str);
            }
            Iterator<String> it = arrayList.iterator();
            String str4 = null;
            while (true) {
                str2 = str4;
                if (!it.hasNext()) {
                    break;
                }
                String next = it.next();
                ArrayList arrayList2 = list != null ? new ArrayList(list) : null;
                long currentTimeMillis = System.currentTimeMillis();
                try {
                } catch (IOException e2) {
                    str3 = str2;
                    e = e2;
                }
                if (!abstractC0571df.m902a(context, next, (List<InterfaceC0502ar>) arrayList2)) {
                    break;
                }
                str3 = abstractC0571df.mo901a(context, next, (List<InterfaceC0502ar>) arrayList2);
                try {
                } catch (IOException e3) {
                    e = e3;
                }
                if (!TextUtils.isEmpty(str3)) {
                    str2 = str3;
                    if (c0563cy == null) {
                        break;
                    }
                    try {
                        c0563cy.m840a(next, System.currentTimeMillis() - currentTimeMillis, m905a(abstractC0571df, next, arrayList2, str3));
                        return str3;
                    } catch (IOException e4) {
                        e = e4;
                    }
                } else {
                    if (c0563cy != null) {
                        try {
                            c0563cy.m841a(next, System.currentTimeMillis() - currentTimeMillis, m905a(abstractC0571df, next, arrayList2, str3), null);
                        } catch (IOException e5) {
                            e = e5;
                        }
                    }
                    str4 = str3;
                }
                if (c0563cy != null) {
                    c0563cy.m841a(next, System.currentTimeMillis() - currentTimeMillis, m905a(abstractC0571df, next, arrayList2, str3), e);
                }
                e.printStackTrace();
                str4 = str3;
            }
            return str2;
        } catch (MalformedURLException e6) {
            e6.printStackTrace();
            return null;
        }
    }
}
