package com.xiaomi.push.service;

import android.content.SharedPreferences;
import com.xiaomi.mipush.sdk.Constants;
import com.xiaomi.push.C0509ay;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/* renamed from: com.xiaomi.push.service.ak */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/ak.class */
public class C0805ak {

    /* renamed from: a */
    private static Object f2468a = new Object();

    /* renamed from: a */
    private static Map<String, Queue<String>> f2469a = new HashMap();

    /* renamed from: a */
    public static boolean m2543a(XMPushService xMPushService, String str, String str2) {
        synchronized (f2468a) {
            SharedPreferences sharedPreferences = xMPushService.getSharedPreferences("push_message_ids", 0);
            Queue<String> queue = f2469a.get(str);
            Queue<String> queue2 = queue;
            if (queue == null) {
                String[] split = sharedPreferences.getString(str, "").split(Constants.ACCEPT_TIME_SEPARATOR_SP);
                queue2 = new LinkedList();
                int length = split.length;
                int i = 0;
                while (true) {
                    int i2 = i;
                    if (i2 >= length) {
                        break;
                    }
                    queue2.add(split[i2]);
                    i = i2 + 1;
                }
                f2469a.put(str, queue2);
            }
            if (queue2.contains(str2)) {
                return true;
            }
            queue2.add(str2);
            if (queue2.size() > 25) {
                queue2.poll();
            }
            String m523a = C0509ay.m523a(queue2, Constants.ACCEPT_TIME_SEPARATOR_SP);
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putString(str, m523a);
            edit.commit();
            return false;
        }
    }
}
