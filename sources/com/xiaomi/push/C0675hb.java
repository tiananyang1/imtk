package com.xiaomi.push;

import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.push.C0664gr;
import com.xiaomi.push.C0668gv;
import com.xiaomi.push.C0670gx;
import com.xiaomi.push.service.C0814at;
import com.xiaomi.push.service.C0824bc;
import com.xiaomi.push.service.C0854e;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* renamed from: com.xiaomi.push.hb */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/hb.class */
public class C0675hb {

    /* renamed from: a */
    private static XmlPullParser f1352a;

    /* renamed from: a */
    public static C0663gq m1518a(String str, String str2, XmlPullParser xmlPullParser) {
        Object m1515a = C0674ha.m1512a().m1515a("all", "xm:chat");
        if (m1515a == null || !(m1515a instanceof C0854e)) {
            return null;
        }
        return ((C0854e) m1515a).m2687b(xmlPullParser);
    }

    /* renamed from: a */
    public static C0664gr m1519a(XmlPullParser xmlPullParser, AbstractC0649gc abstractC0649gc) {
        String attributeValue = xmlPullParser.getAttributeValue("", "id");
        String attributeValue2 = xmlPullParser.getAttributeValue("", "to");
        String attributeValue3 = xmlPullParser.getAttributeValue("", "from");
        String attributeValue4 = xmlPullParser.getAttributeValue("", "chid");
        C0664gr.a m1460a = C0664gr.a.m1460a(xmlPullParser.getAttributeValue("", "type"));
        HashMap hashMap = new HashMap();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= xmlPullParser.getAttributeCount()) {
                break;
            }
            String attributeName = xmlPullParser.getAttributeName(i2);
            hashMap.put(attributeName, xmlPullParser.getAttributeValue("", attributeName));
            i = i2 + 1;
        }
        C0664gr c0664gr = null;
        C0670gx c0670gx = null;
        boolean z = false;
        while (!z) {
            int next = xmlPullParser.next();
            if (next == 2) {
                String name = xmlPullParser.getName();
                String namespace = xmlPullParser.getNamespace();
                if (name.equals("error")) {
                    c0670gx = m1523a(xmlPullParser);
                } else {
                    c0664gr = new C0664gr();
                    c0664gr.m1488a(m1518a(name, namespace, xmlPullParser));
                }
            } else if (next == 3 && xmlPullParser.getName().equals("iq")) {
                z = true;
            }
        }
        C0664gr c0664gr2 = c0664gr;
        if (c0664gr == null) {
            if (C0664gr.a.f1118a == m1460a || C0664gr.a.f1119b == m1460a) {
                C0676hc c0676hc = new C0676hc();
                c0676hc.m1493k(attributeValue);
                c0676hc.m1497m(attributeValue3);
                c0676hc.m1499n(attributeValue2);
                c0676hc.m1457a(C0664gr.a.f1121d);
                c0676hc.m1495l(attributeValue4);
                c0676hc.m1489a(new C0670gx(C0670gx.a.f1181e));
                abstractC0649gc.mo1375a(c0676hc);
                AbstractC0407b.m75d("iq usage error. send packet in packet parser.");
                return null;
            }
            c0664gr2 = new C0677hd();
        }
        c0664gr2.m1493k(attributeValue);
        c0664gr2.m1497m(attributeValue2);
        c0664gr2.m1495l(attributeValue4);
        c0664gr2.m1499n(attributeValue3);
        c0664gr2.m1457a(m1460a);
        c0664gr2.m1489a(c0670gx);
        c0664gr2.m1458a(hashMap);
        return c0664gr2;
    }

    /* renamed from: a */
    public static AbstractC0666gt m1520a(XmlPullParser xmlPullParser) {
        String str;
        String str2;
        boolean z;
        String str3;
        boolean z2 = false;
        if ("1".equals(xmlPullParser.getAttributeValue("", "s"))) {
            String attributeValue = xmlPullParser.getAttributeValue("", "chid");
            String attributeValue2 = xmlPullParser.getAttributeValue("", "id");
            String attributeValue3 = xmlPullParser.getAttributeValue("", "from");
            String attributeValue4 = xmlPullParser.getAttributeValue("", "to");
            String attributeValue5 = xmlPullParser.getAttributeValue("", "type");
            C0814at.b m2581a = C0814at.m2578a().m2581a(attributeValue, attributeValue4);
            C0814at.b bVar = m2581a;
            if (m2581a == null) {
                bVar = C0814at.m2578a().m2581a(attributeValue, attributeValue3);
            }
            if (bVar == null) {
                throw new C0660gn("the channel id is wrong while receiving a encrypted message");
            }
            AbstractC0666gt abstractC0666gt = null;
            while (!z2) {
                int next = xmlPullParser.next();
                if (next == 2) {
                    if (!"s".equals(xmlPullParser.getName())) {
                        throw new C0660gn("error while receiving a encrypted message with wrong format");
                    }
                    if (xmlPullParser.next() != 4) {
                        throw new C0660gn("error while receiving a encrypted message with wrong format");
                    }
                    String text = xmlPullParser.getText();
                    if ("5".equals(attributeValue) || "6".equals(attributeValue)) {
                        C0665gs c0665gs = new C0665gs();
                        c0665gs.m1495l(attributeValue);
                        c0665gs.m1466b(true);
                        c0665gs.m1499n(attributeValue3);
                        c0665gs.m1497m(attributeValue4);
                        c0665gs.m1493k(attributeValue2);
                        c0665gs.m1474f(attributeValue5);
                        String[] strArr = (String[]) null;
                        C0663gq c0663gq = new C0663gq("s", null, strArr, strArr);
                        c0663gq.m1450a(text);
                        c0665gs.m1488a(c0663gq);
                        return c0665gs;
                    }
                    m1525a(C0824bc.m2623a(C0824bc.m2622a(bVar.f2510h, attributeValue2), text));
                    f1352a.next();
                    abstractC0666gt = m1520a(f1352a);
                } else if (next == 3 && xmlPullParser.getName().equals(SettingsJsonConstants.PROMPT_MESSAGE_KEY)) {
                    z2 = true;
                }
            }
            if (abstractC0666gt != null) {
                return abstractC0666gt;
            }
            throw new C0660gn("error while receiving a encrypted message with wrong format");
        }
        C0665gs c0665gs2 = new C0665gs();
        String attributeValue6 = xmlPullParser.getAttributeValue("", "id");
        String str4 = attributeValue6;
        if (attributeValue6 == null) {
            str4 = "ID_NOT_AVAILABLE";
        }
        c0665gs2.m1493k(str4);
        c0665gs2.m1497m(xmlPullParser.getAttributeValue("", "to"));
        c0665gs2.m1499n(xmlPullParser.getAttributeValue("", "from"));
        c0665gs2.m1495l(xmlPullParser.getAttributeValue("", "chid"));
        c0665gs2.m1461a(xmlPullParser.getAttributeValue("", "appid"));
        try {
            str = xmlPullParser.getAttributeValue("", "transient");
        } catch (Exception e) {
            str = null;
        }
        try {
            String attributeValue7 = xmlPullParser.getAttributeValue("", "seq");
            if (!TextUtils.isEmpty(attributeValue7)) {
                c0665gs2.m1465b(attributeValue7);
            }
        } catch (Exception e2) {
        }
        try {
            String attributeValue8 = xmlPullParser.getAttributeValue("", "mseq");
            if (!TextUtils.isEmpty(attributeValue8)) {
                c0665gs2.m1468c(attributeValue8);
            }
        } catch (Exception e3) {
        }
        try {
            String attributeValue9 = xmlPullParser.getAttributeValue("", "fseq");
            if (!TextUtils.isEmpty(attributeValue9)) {
                c0665gs2.m1470d(attributeValue9);
            }
        } catch (Exception e4) {
        }
        try {
            String attributeValue10 = xmlPullParser.getAttributeValue("", SettingsJsonConstants.APP_STATUS_KEY);
            if (!TextUtils.isEmpty(attributeValue10)) {
                c0665gs2.m1472e(attributeValue10);
            }
        } catch (Exception e5) {
        }
        c0665gs2.m1463a(!TextUtils.isEmpty(str) && str.equalsIgnoreCase("true"));
        c0665gs2.m1474f(xmlPullParser.getAttributeValue("", "type"));
        String m1526b = m1526b(xmlPullParser);
        if (m1526b == null || "".equals(m1526b.trim())) {
            AbstractC0666gt.m1482q();
            str2 = null;
            z = false;
        } else {
            c0665gs2.m1480j(m1526b);
            z = false;
            str2 = null;
        }
        while (!z) {
            int next2 = xmlPullParser.next();
            if (next2 == 2) {
                String name = xmlPullParser.getName();
                String namespace = xmlPullParser.getNamespace();
                String str5 = namespace;
                if (TextUtils.isEmpty(namespace)) {
                    str5 = "xm";
                }
                if (name.equals("subject")) {
                    m1526b(xmlPullParser);
                    c0665gs2.m1476g(m1524a(xmlPullParser));
                    str3 = str2;
                } else if (name.equals("body")) {
                    String attributeValue11 = xmlPullParser.getAttributeValue("", "encode");
                    String m1524a = m1524a(xmlPullParser);
                    if (TextUtils.isEmpty(attributeValue11)) {
                        c0665gs2.m1478h(m1524a);
                        str3 = str2;
                    } else {
                        c0665gs2.m1462a(m1524a, attributeValue11);
                        str3 = str2;
                    }
                } else if (name.equals("thread")) {
                    str3 = str2;
                    if (str2 == null) {
                        str3 = xmlPullParser.nextText();
                    }
                } else if (name.equals("error")) {
                    c0665gs2.m1489a(m1523a(xmlPullParser));
                    str3 = str2;
                } else {
                    c0665gs2.m1488a(m1518a(name, str5, xmlPullParser));
                    str3 = str2;
                }
                str2 = str3;
            } else if (next2 == 3 && xmlPullParser.getName().equals(SettingsJsonConstants.PROMPT_MESSAGE_KEY)) {
                z = true;
            }
        }
        c0665gs2.m1479i(str2);
        return c0665gs2;
    }

    /* renamed from: a */
    public static C0668gv m1521a(XmlPullParser xmlPullParser) {
        C0668gv.b bVar = C0668gv.b.available;
        String attributeValue = xmlPullParser.getAttributeValue("", "type");
        C0668gv.b bVar2 = bVar;
        if (attributeValue != null) {
            bVar2 = bVar;
            if (!attributeValue.equals("")) {
                try {
                    bVar2 = C0668gv.b.valueOf(attributeValue);
                } catch (IllegalArgumentException e) {
                    System.err.println("Found invalid presence type " + attributeValue);
                    bVar2 = bVar;
                }
            }
        }
        C0668gv c0668gv = new C0668gv(bVar2);
        c0668gv.m1497m(xmlPullParser.getAttributeValue("", "to"));
        c0668gv.m1499n(xmlPullParser.getAttributeValue("", "from"));
        c0668gv.m1495l(xmlPullParser.getAttributeValue("", "chid"));
        String attributeValue2 = xmlPullParser.getAttributeValue("", "id");
        String str = attributeValue2;
        if (attributeValue2 == null) {
            str = "ID_NOT_AVAILABLE";
        }
        c0668gv.m1493k(str);
        boolean z = false;
        while (!z) {
            int next = xmlPullParser.next();
            if (next == 2) {
                String name = xmlPullParser.getName();
                String namespace = xmlPullParser.getNamespace();
                if (name.equals(SettingsJsonConstants.APP_STATUS_KEY)) {
                    c0668gv.m1506a(xmlPullParser.nextText());
                } else if (name.equals("priority")) {
                    try {
                        c0668gv.m1503a(Integer.parseInt(xmlPullParser.nextText()));
                    } catch (NumberFormatException e2) {
                    } catch (IllegalArgumentException e3) {
                        c0668gv.m1503a(0);
                    }
                } else if (name.equals("show")) {
                    String nextText = xmlPullParser.nextText();
                    try {
                        c0668gv.m1504a(C0668gv.a.valueOf(nextText));
                    } catch (IllegalArgumentException e4) {
                        System.err.println("Found invalid presence mode " + nextText);
                    }
                } else if (name.equals("error")) {
                    c0668gv.m1489a(m1523a(xmlPullParser));
                } else {
                    c0668gv.m1488a(m1518a(name, namespace, xmlPullParser));
                }
            } else if (next == 3 && xmlPullParser.getName().equals("presence")) {
                z = true;
            }
        }
        return c0668gv;
    }

    /* renamed from: a */
    public static C0669gw m1522a(XmlPullParser xmlPullParser) {
        C0669gw c0669gw = null;
        boolean z = false;
        while (!z) {
            int next = xmlPullParser.next();
            if (next == 2) {
                c0669gw = new C0669gw(xmlPullParser.getName());
            } else if (next == 3 && xmlPullParser.getName().equals("error")) {
                z = true;
            }
        }
        return c0669gw;
    }

    /* renamed from: a */
    public static C0670gx m1523a(XmlPullParser xmlPullParser) {
        ArrayList arrayList = new ArrayList();
        String str = null;
        String str2 = null;
        String str3 = "-1";
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= xmlPullParser.getAttributeCount()) {
                break;
            }
            if (xmlPullParser.getAttributeName(i2).equals("code")) {
                str3 = xmlPullParser.getAttributeValue("", "code");
            }
            if (xmlPullParser.getAttributeName(i2).equals("type")) {
                str = xmlPullParser.getAttributeValue("", "type");
            }
            if (xmlPullParser.getAttributeName(i2).equals("reason")) {
                str2 = xmlPullParser.getAttributeValue("", "reason");
            }
            i = i2 + 1;
        }
        String str4 = null;
        String str5 = null;
        boolean z = false;
        while (!z) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals("text")) {
                    str5 = xmlPullParser.nextText();
                } else {
                    String name = xmlPullParser.getName();
                    String namespace = xmlPullParser.getNamespace();
                    if ("urn:ietf:params:xml:ns:xmpp-stanzas".equals(namespace)) {
                        str4 = name;
                    } else {
                        arrayList.add(m1518a(name, namespace, xmlPullParser));
                    }
                }
            } else if (next == 3) {
                if (xmlPullParser.getName().equals("error")) {
                    z = true;
                }
            } else if (next == 4) {
                str5 = xmlPullParser.getText();
            }
        }
        if (str == null) {
            str = "cancel";
        }
        return new C0670gx(Integer.parseInt(str3), str, str2, str4, str5, arrayList);
    }

    /* renamed from: a */
    private static String m1524a(XmlPullParser xmlPullParser) {
        int depth = xmlPullParser.getDepth();
        String str = "";
        while (true) {
            String str2 = str;
            if (xmlPullParser.next() == 3 && xmlPullParser.getDepth() == depth) {
                return str2;
            }
            str = str2 + xmlPullParser.getText();
        }
    }

    /* renamed from: a */
    private static void m1525a(byte[] bArr) {
        if (f1352a == null) {
            try {
                f1352a = XmlPullParserFactory.newInstance().newPullParser();
                f1352a.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", true);
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }
        }
        f1352a.setInput(new InputStreamReader(new ByteArrayInputStream(bArr)));
    }

    /* renamed from: b */
    private static String m1526b(XmlPullParser xmlPullParser) {
        int i;
        int i2 = 0;
        while (true) {
            i = i2;
            if (i >= xmlPullParser.getAttributeCount()) {
                return null;
            }
            String attributeName = xmlPullParser.getAttributeName(i);
            if ("xml:lang".equals(attributeName) || ("lang".equals(attributeName) && "xml".equals(xmlPullParser.getAttributePrefix(i)))) {
                break;
            }
            i2 = i + 1;
        }
        return xmlPullParser.getAttributeValue(i);
    }
}
