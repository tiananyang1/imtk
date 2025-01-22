package com.xiaomi.push;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

/* renamed from: com.xiaomi.push.ha */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/ha.class */
public class C0674ha {

    /* renamed from: a */
    private static C0674ha f1349a;

    /* renamed from: a */
    private Map<String, Object> f1350a = new ConcurrentHashMap();

    /* renamed from: b */
    private Map<String, Object> f1351b = new ConcurrentHashMap();

    private C0674ha() {
        m1516a();
    }

    /* renamed from: a */
    public static C0674ha m1512a() {
        C0674ha c0674ha;
        synchronized (C0674ha.class) {
            try {
                if (f1349a == null) {
                    f1349a = new C0674ha();
                }
                c0674ha = f1349a;
            } catch (Throwable th) {
                throw th;
            }
        }
        return c0674ha;
    }

    /* renamed from: a */
    private String m1513a(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(str);
        sb.append("/>");
        if (str != null) {
            sb.append("<");
            sb.append(str2);
            sb.append("/>");
        }
        return sb.toString();
    }

    /* renamed from: a */
    private ClassLoader[] m1514a() {
        ClassLoader[] classLoaderArr = {C0674ha.class.getClassLoader(), Thread.currentThread().getContextClassLoader()};
        ArrayList arrayList = new ArrayList();
        for (ClassLoader classLoader : classLoaderArr) {
            if (classLoader != null) {
                arrayList.add(classLoader);
            }
        }
        return (ClassLoader[]) arrayList.toArray(new ClassLoader[arrayList.size()]);
    }

    /* renamed from: a */
    public Object m1515a(String str, String str2) {
        return this.f1350a.get(m1513a(str, str2));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: a */
    protected void m1516a() {
        int next;
        Map<String, Object> map;
        Map<String, Object> map2;
        try {
            ClassLoader[] m1514a = m1514a();
            int length = m1514a.length;
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= length) {
                    return;
                }
                Enumeration<URL> resources = m1514a[i2].getResources("META-INF/smack.providers");
                while (resources.hasMoreElements()) {
                    URL nextElement = resources.nextElement();
                    InputStream inputStream = null;
                    try {
                        InputStream openStream = nextElement.openStream();
                        XmlPullParser newPullParser = XmlPullParserFactory.newInstance().newPullParser();
                        newPullParser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", true);
                        newPullParser.setInput(openStream, "UTF-8");
                        int eventType = newPullParser.getEventType();
                        do {
                            if (eventType == 2) {
                                if (newPullParser.getName().equals("iqProvider")) {
                                    newPullParser.next();
                                    newPullParser.next();
                                    String nextText = newPullParser.nextText();
                                    newPullParser.next();
                                    newPullParser.next();
                                    String nextText2 = newPullParser.nextText();
                                    newPullParser.next();
                                    newPullParser.next();
                                    String nextText3 = newPullParser.nextText();
                                    String m1513a = m1513a(nextText, nextText2);
                                    if (!this.f1351b.containsKey(m1513a)) {
                                        try {
                                            Class<?> cls = Class.forName(nextText3);
                                            if (InterfaceC0671gy.class.isAssignableFrom(cls)) {
                                                map2 = this.f1351b;
                                                cls = cls.newInstance();
                                            } else if (C0664gr.class.isAssignableFrom(cls)) {
                                                map2 = this.f1351b;
                                            }
                                            map2.put(m1513a, cls);
                                        } catch (ClassNotFoundException e) {
                                            e = e;
                                            e.printStackTrace();
                                            next = newPullParser.next();
                                            eventType = next;
                                        }
                                    }
                                } else if (newPullParser.getName().equals("extensionProvider")) {
                                    newPullParser.next();
                                    newPullParser.next();
                                    String nextText4 = newPullParser.nextText();
                                    newPullParser.next();
                                    newPullParser.next();
                                    String nextText5 = newPullParser.nextText();
                                    newPullParser.next();
                                    newPullParser.next();
                                    String nextText6 = newPullParser.nextText();
                                    String m1513a2 = m1513a(nextText4, nextText5);
                                    if (!this.f1350a.containsKey(m1513a2)) {
                                        try {
                                            Class<?> cls2 = Class.forName(nextText6);
                                            if (InterfaceC0672gz.class.isAssignableFrom(cls2)) {
                                                map = this.f1350a;
                                                cls2 = cls2.newInstance();
                                            } else if (InterfaceC0667gu.class.isAssignableFrom(cls2)) {
                                                map = this.f1350a;
                                            }
                                            map.put(m1513a2, cls2);
                                        } catch (ClassNotFoundException e2) {
                                            e = e2;
                                            e.printStackTrace();
                                            next = newPullParser.next();
                                            eventType = next;
                                        }
                                    }
                                }
                            }
                            next = newPullParser.next();
                            eventType = next;
                        } while (next != 1);
                        openStream.close();
                    } catch (Throwable th) {
                        try {
                            inputStream.close();
                        } catch (Exception e3) {
                        }
                        throw th;
                    }
                }
                i = i2 + 1;
            }
        } catch (Exception e4) {
            e4.printStackTrace();
        }
    }

    /* renamed from: a */
    public void m1517a(String str, String str2, Object obj) {
        if (!(obj instanceof InterfaceC0672gz) && !(obj instanceof Class)) {
            throw new IllegalArgumentException("Provider must be a PacketExtensionProvider or a Class instance.");
        }
        this.f1350a.put(m1513a(str, str2), obj);
    }
}
