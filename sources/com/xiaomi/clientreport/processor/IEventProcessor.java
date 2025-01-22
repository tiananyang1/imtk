package com.xiaomi.clientreport.processor;

import com.xiaomi.clientreport.data.C0408a;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes08-dex2jar.jar:com/xiaomi/clientreport/processor/IEventProcessor.class */
public interface IEventProcessor extends InterfaceC0415c, InterfaceC0416d {
    String bytesToString(byte[] bArr);

    void setEventMap(HashMap<String, ArrayList<C0408a>> hashMap);

    byte[] stringToBytes(String str);
}
