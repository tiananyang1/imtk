package com.xiaomi.push.service;

import com.xiaomi.push.C0663gq;
import com.xiaomi.push.C0674ha;
import com.xiaomi.push.C0678he;
import com.xiaomi.push.InterfaceC0672gz;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;

/* renamed from: com.xiaomi.push.service.e */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/e.class */
public class C0854e implements InterfaceC0672gz {
    /* renamed from: a */
    public static C0663gq m2685a(XmlPullParser xmlPullParser) {
        String str;
        ArrayList arrayList;
        String[] strArr;
        String[] strArr2;
        if (xmlPullParser.getEventType() != 2) {
            return null;
        }
        String name = xmlPullParser.getName();
        String namespace = xmlPullParser.getNamespace();
        if (xmlPullParser.getAttributeCount() > 0) {
            strArr2 = new String[xmlPullParser.getAttributeCount()];
            strArr = new String[xmlPullParser.getAttributeCount()];
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= xmlPullParser.getAttributeCount()) {
                    break;
                }
                strArr2[i2] = xmlPullParser.getAttributeName(i2);
                strArr[i2] = C0678he.m1531b(xmlPullParser.getAttributeValue(i2));
                i = i2 + 1;
            }
            str = null;
            arrayList = null;
        } else {
            str = null;
            arrayList = null;
            strArr = null;
            strArr2 = null;
        }
        while (true) {
            int next = xmlPullParser.next();
            if (next == 3) {
                return new C0663gq(name, namespace, strArr2, strArr, str, arrayList);
            }
            if (next == 4) {
                str = xmlPullParser.getText().trim();
            } else if (next == 2) {
                ArrayList arrayList2 = arrayList;
                if (arrayList == null) {
                    arrayList2 = new ArrayList();
                }
                C0663gq m2685a = m2685a(xmlPullParser);
                arrayList = arrayList2;
                if (m2685a != null) {
                    arrayList2.add(m2685a);
                    arrayList = arrayList2;
                }
            }
        }
    }

    /* renamed from: a */
    public void m2686a() {
        C0674ha.m1512a().m1517a("all", "xm:chat", this);
    }

    /* renamed from: b */
    public C0663gq m2687b(XmlPullParser xmlPullParser) {
        int i;
        int eventType = xmlPullParser.getEventType();
        while (true) {
            i = eventType;
            if (i == 1 || i == 2) {
                break;
            }
            eventType = xmlPullParser.next();
        }
        if (i == 2) {
            return m2685a(xmlPullParser);
        }
        return null;
    }
}
