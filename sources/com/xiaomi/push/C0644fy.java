package com.xiaomi.push;

import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* renamed from: com.xiaomi.push.fy */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/fy.class */
public class C0644fy {

    /* renamed from: a */
    private XmlPullParser f1044a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0644fy() {
        try {
            this.f1044a = XmlPullParserFactory.newInstance().newPullParser();
            this.f1044a.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", true);
        } catch (XmlPullParserException e) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public AbstractC0666gt m1355a(byte[] bArr, AbstractC0649gc abstractC0649gc) {
        this.f1044a.setInput(new InputStreamReader(new ByteArrayInputStream(bArr)));
        this.f1044a.next();
        int eventType = this.f1044a.getEventType();
        String name = this.f1044a.getName();
        if (eventType != 2) {
            return null;
        }
        if (name.equals(SettingsJsonConstants.PROMPT_MESSAGE_KEY)) {
            return C0675hb.m1520a(this.f1044a);
        }
        if (name.equals("iq")) {
            return C0675hb.m1519a(this.f1044a, abstractC0649gc);
        }
        if (name.equals("presence")) {
            return C0675hb.m1521a(this.f1044a);
        }
        if (this.f1044a.getName().equals("stream")) {
            return null;
        }
        if (this.f1044a.getName().equals("error")) {
            throw new C0660gn(C0675hb.m1522a(this.f1044a));
        }
        if (!this.f1044a.getName().equals("warning")) {
            this.f1044a.getName().equals("bind");
            return null;
        }
        this.f1044a.next();
        this.f1044a.getName().equals("multi-login");
        return null;
    }
}
