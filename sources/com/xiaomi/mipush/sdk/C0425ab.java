package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.text.TextUtils;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/* renamed from: com.xiaomi.mipush.sdk.ab */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/ab.class */
public class C0425ab {

    /* renamed from: com.xiaomi.mipush.sdk.ab$a */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/ab$a.class */
    public static class a extends RuntimeException {
        public a(String str) {
            super(str);
        }
    }

    /* renamed from: com.xiaomi.mipush.sdk.ab$b */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/mipush/sdk/ab$b.class */
    public static class b {

        /* renamed from: a */
        public String f271a;

        /* renamed from: a */
        public boolean f272a;

        /* renamed from: b */
        public String f273b;

        /* renamed from: b */
        public boolean f274b;

        public b(String str, boolean z, boolean z2, String str2) {
            this.f271a = str;
            this.f272a = z;
            this.f274b = z2;
            this.f273b = str2;
        }
    }

    /* renamed from: a */
    private static ActivityInfo m171a(PackageManager packageManager, Intent intent, Class<?> cls) {
        Iterator<ResolveInfo> it = packageManager.queryBroadcastReceivers(intent, 16384).iterator();
        while (it.hasNext()) {
            ActivityInfo activityInfo = it.next().activityInfo;
            if (activityInfo != null && cls.getCanonicalName().equals(activityInfo.name)) {
                return activityInfo;
            }
        }
        return null;
    }

    /* renamed from: a */
    public static void m172a(Context context) {
        new Thread(new RunnableC0426ac(context)).start();
    }

    /* renamed from: a */
    private static void m174a(Context context, String str, String str2) {
        boolean z;
        PackageManager packageManager = context.getPackageManager();
        String packageName = context.getPackageName();
        Intent intent = new Intent(str);
        intent.setPackage(packageName);
        Iterator<ResolveInfo> it = packageManager.queryBroadcastReceivers(intent, 16384).iterator();
        boolean z2 = false;
        do {
            z = z2;
            if (!it.hasNext()) {
                break;
            }
            ActivityInfo activityInfo = it.next().activityInfo;
            z = (activityInfo == null || TextUtils.isEmpty(activityInfo.name) || !activityInfo.name.equals(str2)) ? false : true;
            z2 = z;
        } while (!z);
        if (!z) {
            throw new a(String.format("<receiver android:name=\"%1$s\" .../> is missing or disabled in AndroidManifest.", str2));
        }
    }

    /* renamed from: a */
    private static void m175a(ActivityInfo activityInfo, Boolean[] boolArr) {
        if (boolArr[0].booleanValue() != activityInfo.enabled) {
            throw new a(String.format("<receiver android:name=\"%1$s\" .../> in AndroidManifest had the wrong enabled attribute, which should be android:enabled=%2$b.", activityInfo.name, boolArr[0]));
        }
        if (boolArr[1].booleanValue() != activityInfo.exported) {
            throw new a(String.format("<receiver android:name=\"%1$s\" .../> in AndroidManifest had the wrong exported attribute, which should be android:exported=%2$b.", activityInfo.name, boolArr[1]));
        }
    }

    /* renamed from: a */
    private static boolean m176a(PackageInfo packageInfo, String[] strArr) {
        ServiceInfo[] serviceInfoArr = packageInfo.services;
        int length = serviceInfoArr.length;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length) {
                return false;
            }
            if (m177a(strArr, serviceInfoArr[i2].name)) {
                return true;
            }
            i = i2 + 1;
        }
    }

    /* renamed from: a */
    private static boolean m177a(String[] strArr, String str) {
        if (strArr == null || str == null) {
            return false;
        }
        int length = strArr.length;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length) {
                return false;
            }
            if (TextUtils.equals(strArr[i2], str)) {
                return true;
            }
            i = i2 + 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0112 A[EDGE_INSN: B:25:0x0112->B:26:0x0112 BREAK  A[LOOP:0: B:9:0x00b6->B:37:0x00b6], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00b6 A[SYNTHETIC] */
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void m180c(android.content.Context r8) {
        /*
            Method dump skipped, instructions count: 311
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mipush.sdk.C0425ab.m180c(android.content.Context):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public static void m181c(Context context, PackageInfo packageInfo) {
        boolean z;
        HashSet hashSet = new HashSet();
        String str = context.getPackageName() + ".permission.MIPUSH_RECEIVE";
        hashSet.addAll(Arrays.asList("android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE", str, "android.permission.ACCESS_WIFI_STATE", "android.permission.READ_PHONE_STATE", "android.permission.GET_TASKS", "android.permission.VIBRATE"));
        if (packageInfo.permissions != null) {
            PermissionInfo[] permissionInfoArr = packageInfo.permissions;
            int length = permissionInfoArr.length;
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= length) {
                    break;
                }
                if (str.equals(permissionInfoArr[i2].name)) {
                    z = true;
                    break;
                }
                i = i2 + 1;
            }
        }
        z = false;
        if (!z) {
            throw new a(String.format("<permission android:name=\"%1$s\" .../> is undefined in AndroidManifest.", str));
        }
        if (packageInfo.requestedPermissions != null) {
            String[] strArr = packageInfo.requestedPermissions;
            int length2 = strArr.length;
            int i3 = 0;
            while (true) {
                int i4 = i3;
                if (i4 >= length2) {
                    break;
                }
                String str2 = strArr[i4];
                if (!TextUtils.isEmpty(str2) && hashSet.contains(str2)) {
                    hashSet.remove(str2);
                    if (hashSet.isEmpty()) {
                        break;
                    }
                }
                i3 = i4 + 1;
            }
        }
        if (!hashSet.isEmpty()) {
            throw new a(String.format("<uses-permission android:name=\"%1$s\"/> is missing in AndroidManifest.", hashSet.iterator().next()));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d */
    public static void m182d(Context context, PackageInfo packageInfo) {
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        hashMap2.put(PushMessageHandler.class.getCanonicalName(), new b(PushMessageHandler.class.getCanonicalName(), true, true, ""));
        hashMap2.put(MessageHandleService.class.getCanonicalName(), new b(MessageHandleService.class.getCanonicalName(), true, false, ""));
        if (!MiPushClient.shouldUseMIUIPush(context) || m176a(packageInfo, new String[]{"com.xiaomi.push.service.XMJobService", "com.xiaomi.push.service.XMPushService"})) {
            hashMap2.put("com.xiaomi.push.service.XMJobService", new b("com.xiaomi.push.service.XMJobService", true, false, "android.permission.BIND_JOB_SERVICE"));
            hashMap2.put("com.xiaomi.push.service.XMPushService", new b("com.xiaomi.push.service.XMPushService", true, false, ""));
        }
        if (MiPushClient.getOpenFCMPush()) {
            hashMap2.put("com.xiaomi.assemble.control.MiFireBaseInstanceIdService", new b("com.xiaomi.assemble.control.MiFireBaseInstanceIdService", true, false, ""));
            hashMap2.put("com.xiaomi.assemble.control.MiFirebaseMessagingService", new b("com.xiaomi.assemble.control.MiFirebaseMessagingService", true, false, ""));
        }
        if (MiPushClient.getOpenOPPOPush()) {
            hashMap2.put("com.xiaomi.assemble.control.COSPushMessageService", new b("com.xiaomi.assemble.control.COSPushMessageService", true, true, "com.coloros.mcs.permission.SEND_MCS_MESSAGE"));
        }
        if (packageInfo.services != null) {
            ServiceInfo[] serviceInfoArr = packageInfo.services;
            int length = serviceInfoArr.length;
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= length) {
                    break;
                }
                ServiceInfo serviceInfo = serviceInfoArr[i2];
                if (!TextUtils.isEmpty(serviceInfo.name) && hashMap2.containsKey(serviceInfo.name)) {
                    b bVar = (b) hashMap2.remove(serviceInfo.name);
                    boolean z = bVar.f272a;
                    boolean z2 = bVar.f274b;
                    String str = bVar.f273b;
                    if (z != serviceInfo.enabled) {
                        throw new a(String.format("<service android:name=\"%1$s\" .../> in AndroidManifest had the wrong enabled attribute, which should be android:enabled=%2$b.", serviceInfo.name, Boolean.valueOf(z)));
                    }
                    if (z2 != serviceInfo.exported) {
                        throw new a(String.format("<service android:name=\"%1$s\" .../> in AndroidManifest had the wrong exported attribute, which should be android:exported=%2$b.", serviceInfo.name, Boolean.valueOf(z2)));
                    }
                    if (!TextUtils.isEmpty(str) && !TextUtils.equals(str, serviceInfo.permission)) {
                        throw new a(String.format("<service android:name=\"%1$s\" .../> in AndroidManifest had the wrong permission attribute, which should be android:permission=\"%2$s\".", serviceInfo.name, str));
                    }
                    hashMap.put(serviceInfo.name, serviceInfo.processName);
                    if (hashMap2.isEmpty()) {
                        break;
                    }
                }
                i = i2 + 1;
            }
        }
        if (!hashMap2.isEmpty()) {
            throw new a(String.format("<service android:name=\"%1$s\" .../> is missing or disabled in AndroidManifest.", hashMap2.keySet().iterator().next()));
        }
        if (!TextUtils.equals((CharSequence) hashMap.get(PushMessageHandler.class.getCanonicalName()), (CharSequence) hashMap.get(MessageHandleService.class.getCanonicalName()))) {
            throw new a(String.format("\"%1$s\" and \"%2$s\" must be running in the same process.", PushMessageHandler.class.getCanonicalName(), MessageHandleService.class.getCanonicalName()));
        }
        if (hashMap.containsKey("com.xiaomi.push.service.XMJobService") && hashMap.containsKey("com.xiaomi.push.service.XMPushService") && !TextUtils.equals((CharSequence) hashMap.get("com.xiaomi.push.service.XMJobService"), (CharSequence) hashMap.get("com.xiaomi.push.service.XMPushService"))) {
            throw new a(String.format("\"%1$s\" and \"%2$s\" must be running in the same process.", "com.xiaomi.push.service.XMJobService", "com.xiaomi.push.service.XMPushService"));
        }
    }
}
