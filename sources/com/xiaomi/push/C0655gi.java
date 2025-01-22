package com.xiaomi.push;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Vector;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

/* renamed from: com.xiaomi.push.gi */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/gi.class */
public final class C0655gi {

    /* renamed from: a */
    private static int f1086a = 5000;

    /* renamed from: a */
    private static Vector<String> f1087a = new Vector<>();

    /* renamed from: b */
    private static int f1088b = 330000;

    /* renamed from: c */
    private static int f1089c = 600000;

    /* renamed from: d */
    private static int f1090d = 330000;

    static {
        int next;
        try {
            ClassLoader[] m1427a = m1427a();
            int length = m1427a.length;
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= length) {
                    return;
                }
                Enumeration<URL> resources = m1427a[i2].getResources("META-INF/smack-config.xml");
                while (resources.hasMoreElements()) {
                    URL nextElement = resources.nextElement();
                    InputStream inputStream = null;
                    InputStream inputStream2 = null;
                    try {
                        try {
                            InputStream openStream = nextElement.openStream();
                            XmlPullParser newPullParser = XmlPullParserFactory.newInstance().newPullParser();
                            newPullParser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", true);
                            newPullParser.setInput(openStream, "UTF-8");
                            int eventType = newPullParser.getEventType();
                            do {
                                if (eventType == 2) {
                                    if (newPullParser.getName().equals("className")) {
                                        m1426a(newPullParser);
                                    } else if (newPullParser.getName().equals("packetReplyTimeout")) {
                                        f1086a = m1424a(newPullParser, f1086a);
                                    } else if (newPullParser.getName().equals("keepAliveInterval")) {
                                        f1088b = m1424a(newPullParser, f1088b);
                                    } else if (newPullParser.getName().equals("mechName")) {
                                        f1087a.add(newPullParser.nextText());
                                    }
                                }
                                next = newPullParser.next();
                                eventType = next;
                            } while (next != 1);
                            inputStream = openStream;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        try {
                            inputStream.close();
                        } catch (Exception e2) {
                        }
                    } catch (Throwable th) {
                        try {
                            inputStream2.close();
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

    private C0655gi() {
    }

    /* renamed from: a */
    public static int m1423a() {
        return f1088b;
    }

    /* renamed from: a */
    private static int m1424a(XmlPullParser xmlPullParser, int i) {
        try {
            return Integer.parseInt(xmlPullParser.nextText());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return i;
        }
    }

    /* renamed from: a */
    public static String m1425a() {
        return "3.1.0";
    }

    /* renamed from: a */
    private static void m1426a(XmlPullParser xmlPullParser) {
        String nextText = xmlPullParser.nextText();
        try {
            Class.forName(nextText);
        } catch (ClassNotFoundException e) {
            System.err.println("Error! A startup class specified in smack-config.xml could not be loaded: " + nextText);
        }
    }

    /* renamed from: a */
    private static ClassLoader[] m1427a() {
        ClassLoader[] classLoaderArr = {C0655gi.class.getClassLoader(), Thread.currentThread().getContextClassLoader()};
        ArrayList arrayList = new ArrayList();
        for (ClassLoader classLoader : classLoaderArr) {
            if (classLoader != null) {
                arrayList.add(classLoader);
            }
        }
        return (ClassLoader[]) arrayList.toArray(new ClassLoader[arrayList.size()]);
    }

    /* renamed from: b */
    public static int m1428b() {
        return f1089c;
    }
}
