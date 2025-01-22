package com.xiaomi.push.service;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.Pair;
import android.widget.RemoteViews;
import com.microsoft.codepush.react.CodePushConstants;
import com.xiaomi.channel.commonutils.logger.AbstractC0407b;
import com.xiaomi.push.C0504at;
import com.xiaomi.push.C0646g;
import com.xiaomi.push.C0717iq;
import com.xiaomi.push.C0729jb;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.xiaomi.push.service.ai */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/ai.class */
public class C0803ai {

    /* renamed from: a */
    public static long f2456a;

    /* renamed from: a */
    private static final LinkedList<Pair<Integer, C0729jb>> f2457a = new LinkedList<>();

    /* renamed from: a */
    private static ExecutorService f2458a = Executors.newCachedThreadPool();

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.xiaomi.push.service.ai$a */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/ai$a.class */
    public static class a implements Callable<Bitmap> {

        /* renamed from: a */
        private Context f2459a;

        /* renamed from: a */
        private String f2460a;

        /* renamed from: a */
        private boolean f2461a;

        public a(String str, Context context, boolean z) {
            this.f2459a = context;
            this.f2460a = str;
            this.f2461a = z;
        }

        /* JADX WARN: Code restructure failed: missing block: B:13:0x004b, code lost:            if (r6 == null) goto L10;     */
        @Override // java.util.concurrent.Callable
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public android.graphics.Bitmap call() {
            /*
                r4 = this;
                r0 = r4
                java.lang.String r0 = r0.f2460a
                boolean r0 = android.text.TextUtils.isEmpty(r0)
                r5 = r0
                r0 = 0
                r7 = r0
                r0 = 0
                r6 = r0
                r0 = r5
                if (r0 != 0) goto L51
                r0 = r4
                java.lang.String r0 = r0.f2460a
                java.lang.String r1 = "http"
                boolean r0 = r0.startsWith(r1)
                if (r0 == 0) goto L3c
                r0 = r4
                android.content.Context r0 = r0.f2459a
                r1 = r4
                java.lang.String r1 = r1.f2460a
                r2 = r4
                boolean r2 = r2.f2461a
                com.xiaomi.push.service.an$b r0 = com.xiaomi.push.service.C0808an.m2553a(r0, r1, r2)
                r7 = r0
                r0 = r7
                if (r0 == 0) goto L35
                r0 = r7
                android.graphics.Bitmap r0 = r0.f2479a
                return r0
            L35:
                java.lang.String r0 = "Failed get online picture/icon resource"
                com.xiaomi.channel.commonutils.logger.AbstractC0407b.m70a(r0)
                r0 = r6
                return r0
            L3c:
                r0 = r4
                android.content.Context r0 = r0.f2459a
                r1 = r4
                java.lang.String r1 = r1.f2460a
                android.graphics.Bitmap r0 = com.xiaomi.push.service.C0808an.m2551a(r0, r1)
                r6 = r0
                r0 = r6
                r7 = r0
                r0 = r6
                if (r0 != 0) goto L56
                goto L35
            L51:
                java.lang.String r0 = "Failed get online picture/icon resource cause picUrl is empty"
                com.xiaomi.channel.commonutils.logger.AbstractC0407b.m70a(r0)
            L56:
                r0 = r7
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.service.C0803ai.a.call():android.graphics.Bitmap");
        }
    }

    /* renamed from: com.xiaomi.push.service.ai$b */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/ai$b.class */
    public static class b {

        /* renamed from: a */
        long f2462a = 0;

        /* renamed from: a */
        Notification f2463a;
    }

    /* renamed from: com.xiaomi.push.service.ai$c */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/service/ai$c.class */
    public static class c {

        /* renamed from: a */
        public long f2464a = 0;

        /* renamed from: a */
        public String f2465a;
    }

    /* renamed from: a */
    static int m2503a(Context context, String str) {
        return context.getSharedPreferences("pref_notify_type", 0).getInt(str, Integer.MAX_VALUE);
    }

    /* renamed from: a */
    private static int m2504a(Context context, String str, String str2) {
        if (str.equals(context.getPackageName())) {
            return context.getResources().getIdentifier(str2, "drawable", str);
        }
        return 0;
    }

    /* renamed from: a */
    private static int m2505a(Map<String, String> map) {
        String str = map == null ? null : map.get("timeout");
        int i = 0;
        if (!TextUtils.isEmpty(str)) {
            try {
                i = Integer.parseInt(str);
            } catch (Exception e) {
                return 0;
            }
        }
        return i;
    }

    /* renamed from: a */
    private static Notification.Builder m2506a(Notification.Builder builder, Context context, String str, Map<String, String> map) {
        PendingIntent m2511a = m2511a(context, str, 1, map);
        if (m2511a != null && !TextUtils.isEmpty(map.get("notification_style_button_left_name"))) {
            builder.addAction(0, map.get("notification_style_button_left_name"), m2511a);
        }
        PendingIntent m2511a2 = m2511a(context, str, 2, map);
        if (m2511a2 != null && !TextUtils.isEmpty(map.get("notification_style_button_mid_name"))) {
            builder.addAction(0, map.get("notification_style_button_mid_name"), m2511a2);
        }
        PendingIntent m2511a3 = m2511a(context, str, 3, map);
        if (m2511a3 != null && !TextUtils.isEmpty(map.get("notification_style_button_right_name"))) {
            builder.addAction(0, map.get("notification_style_button_right_name"), m2511a3);
        }
        return builder;
    }

    /* renamed from: a */
    private static Notification.Builder m2507a(Context context, Map<String, String> map, Notification.Builder builder, String str) {
        if (!"2".equals(map.get("notification_style_type"))) {
            if ("1".equals(map.get("notification_style_type"))) {
                builder.setStyle(new Notification.BigTextStyle().bigText(str));
            }
            return builder;
        }
        Notification.BigPictureStyle bigPictureStyle = new Notification.BigPictureStyle(builder);
        Bitmap m2515a = map == null ? null : m2515a(context, map.get("notification_bigPic_uri"), false);
        if (m2515a == null) {
            return builder;
        }
        bigPictureStyle.bigPicture(m2515a);
        bigPictureStyle.bigLargeIcon((Bitmap) null);
        builder.setStyle(bigPictureStyle);
        return builder;
    }

    /* renamed from: a */
    private static Notification m2508a(Notification notification) {
        Object m494a = C0504at.m494a(notification, "extraNotification");
        if (m494a != null) {
            C0504at.m495a(m494a, "setCustomizedIcon", true);
        }
        return notification;
    }

    /* renamed from: a */
    private static Notification m2509a(Notification notification, String str) {
        try {
            Field declaredField = Notification.class.getDeclaredField("extraNotification");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(notification);
            Method declaredMethod = obj.getClass().getDeclaredMethod("setTargetPkg", CharSequence.class);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(obj, str);
            return notification;
        } catch (Exception e) {
            AbstractC0407b.m72a(e);
            return notification;
        }
    }

    /* renamed from: a */
    private static PendingIntent m2510a(Context context, C0729jb c0729jb, C0717iq c0717iq, byte[] bArr) {
        Intent intent;
        int i = m2540c(c0729jb) ? 1000 : m2530a(c0729jb) ? 3000 : -1;
        String m1825a = c0717iq != null ? c0717iq.m1825a() : "";
        if (c0717iq != null && !TextUtils.isEmpty(c0717iq.f1834e)) {
            intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(c0717iq.f1834e));
            intent.addFlags(268435456);
        } else if (m2530a(c0729jb)) {
            intent = new Intent();
            intent.setComponent(new ComponentName("com.xiaomi.xmsf", "com.xiaomi.mipush.sdk.PushMessageHandler"));
            intent.putExtra("mipush_payload", bArr);
            intent.putExtra("mipush_notified", true);
            intent.addCategory(String.valueOf(c0717iq.m1840c()));
        } else {
            Intent intent2 = new Intent("com.xiaomi.mipush.RECEIVE_MESSAGE");
            intent2.setComponent(new ComponentName(c0729jb.f2004b, "com.xiaomi.mipush.sdk.PushMessageHandler"));
            intent2.putExtra("mipush_payload", bArr);
            intent2.putExtra("mipush_notified", true);
            intent2.addCategory(String.valueOf(c0717iq.m1840c()));
            intent = intent2;
        }
        intent.putExtra("messageId", m1825a);
        intent.putExtra("eventMessageType", i);
        return PendingIntent.getService(context, 0, intent, 134217728);
    }

    /* renamed from: a */
    private static PendingIntent m2511a(Context context, String str, int i, Map<String, String> map) {
        Intent m2513a;
        if (map == null || (m2513a = m2513a(context, str, i, map)) == null) {
            return null;
        }
        return PendingIntent.getActivity(context, 0, m2513a, 0);
    }

    /* renamed from: a */
    private static Intent m2512a(Context context, Intent intent) {
        try {
            if (context.getPackageManager().getPackageInfo("com.android.browser", 4) != null) {
                intent.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
            }
            return intent;
        } catch (PackageManager.NameNotFoundException e) {
            AbstractC0407b.m72a(e);
            return intent;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0232  */
    /* JADX WARN: Removed duplicated region for block: B:36:? A[RETURN, SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static android.content.Intent m2513a(android.content.Context r6, java.lang.String r7, int r8, java.util.Map<java.lang.String, java.lang.String> r9) {
        /*
            Method dump skipped, instructions count: 622
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.service.C0803ai.m2513a(android.content.Context, java.lang.String, int, java.util.Map):android.content.Intent");
    }

    /* renamed from: a */
    private static Bitmap m2514a(Context context, int i) {
        return m2516a(context.getResources().getDrawable(i));
    }

    /* renamed from: a */
    private static Bitmap m2515a(Context context, String str, boolean z) {
        Bitmap bitmap;
        Future submit = f2458a.submit(new a(str, context, z));
        try {
            try {
                Bitmap bitmap2 = (Bitmap) submit.get(180L, TimeUnit.SECONDS);
                bitmap = bitmap2;
                if (bitmap2 == null) {
                    return bitmap2;
                }
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                AbstractC0407b.m72a(e);
                bitmap = null;
            }
            return bitmap;
        } finally {
            submit.cancel(true);
        }
    }

    /* renamed from: a */
    public static Bitmap m2516a(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int i = 1;
        if (intrinsicWidth <= 0) {
            intrinsicWidth = 1;
        }
        int intrinsicHeight = drawable.getIntrinsicHeight();
        if (intrinsicHeight > 0) {
            i = intrinsicHeight;
        }
        Bitmap createBitmap = Bitmap.createBitmap(intrinsicWidth, i, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return createBitmap;
    }

    /* renamed from: a */
    private static RemoteViews m2517a(Context context, C0729jb c0729jb, byte[] bArr) {
        C0717iq m2022a = c0729jb.m2022a();
        String m2521a = m2521a(c0729jb);
        Map<String, String> m1826a = m2022a.m1826a();
        if (m1826a == null) {
            return null;
        }
        String str = m1826a.get("layout_name");
        String str2 = m1826a.get("layout_value");
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        try {
            Resources resourcesForApplication = context.getPackageManager().getResourcesForApplication(m2521a);
            int identifier = resourcesForApplication.getIdentifier(str, "layout", m2521a);
            if (identifier == 0) {
                return null;
            }
            RemoteViews remoteViews = new RemoteViews(m2521a, identifier);
            try {
                JSONObject jSONObject = new JSONObject(str2);
                if (jSONObject.has("text")) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject("text");
                    Iterator<String> keys = jSONObject2.keys();
                    while (keys.hasNext()) {
                        String next = keys.next();
                        String string = jSONObject2.getString(next);
                        int identifier2 = resourcesForApplication.getIdentifier(next, "id", m2521a);
                        if (identifier2 > 0) {
                            remoteViews.setTextViewText(identifier2, string);
                        }
                    }
                }
                if (jSONObject.has("image")) {
                    JSONObject jSONObject3 = jSONObject.getJSONObject("image");
                    Iterator<String> keys2 = jSONObject3.keys();
                    while (keys2.hasNext()) {
                        String next2 = keys2.next();
                        String string2 = jSONObject3.getString(next2);
                        int identifier3 = resourcesForApplication.getIdentifier(next2, "id", m2521a);
                        int identifier4 = resourcesForApplication.getIdentifier(string2, "drawable", m2521a);
                        if (identifier3 > 0) {
                            remoteViews.setImageViewResource(identifier3, identifier4);
                        }
                    }
                }
                if (jSONObject.has(CodePushConstants.LATEST_ROLLBACK_TIME_KEY)) {
                    JSONObject jSONObject4 = jSONObject.getJSONObject(CodePushConstants.LATEST_ROLLBACK_TIME_KEY);
                    Iterator<String> keys3 = jSONObject4.keys();
                    while (keys3.hasNext()) {
                        String next3 = keys3.next();
                        String string3 = jSONObject4.getString(next3);
                        String str3 = string3;
                        if (string3.length() == 0) {
                            str3 = "yy-MM-dd hh:mm";
                        }
                        int identifier5 = resourcesForApplication.getIdentifier(next3, "id", m2521a);
                        if (identifier5 > 0) {
                            remoteViews.setTextViewText(identifier5, new SimpleDateFormat(str3).format(new Date(System.currentTimeMillis())));
                        }
                    }
                }
                return remoteViews;
            } catch (JSONException e) {
                AbstractC0407b.m72a(e);
                return null;
            }
        } catch (PackageManager.NameNotFoundException e2) {
            AbstractC0407b.m72a(e2);
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:109:0x0458  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x046c  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0647 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:127:0x04b1  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x054d A[Catch: Exception -> 0x062d, TRY_LEAVE, TryCatch #0 {Exception -> 0x062d, blocks: (B:130:0x053a, B:132:0x054d, B:135:0x0589, B:138:0x05a0, B:141:0x05b9, B:146:0x05dd, B:150:0x05ff, B:154:0x0614), top: B:129:0x053a }] */
    /* JADX WARN: Removed duplicated region for block: B:158:0x04df  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x067e  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x06b2  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x06c2  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x06e0  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x0715  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x044a  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x02c4  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x01e3  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x01fd A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x01e8  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x02bf  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x02db  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0318  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x03aa  */
    @android.annotation.SuppressLint({"NewApi"})
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static com.xiaomi.push.service.C0803ai.b m2518a(android.content.Context r8, com.xiaomi.push.C0729jb r9, byte[] r10, android.widget.RemoteViews r11, android.app.PendingIntent r12) {
        /*
            Method dump skipped, instructions count: 1868
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.service.C0803ai.m2518a(android.content.Context, com.xiaomi.push.jb, byte[], android.widget.RemoteViews, android.app.PendingIntent):com.xiaomi.push.service.ai$b");
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x0276  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x02a5  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0336  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0346  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0359  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0370  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0390  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x03a5  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x03ac  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0376  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x03cd  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x03ec  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x042b  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x044c  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0476  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x049d  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x04c5  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0503 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:96:0x03d4  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.xiaomi.push.service.C0803ai.c m2519a(android.content.Context r8, com.xiaomi.push.C0729jb r9, byte[] r10) {
        /*
            Method dump skipped, instructions count: 1319
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.service.C0803ai.m2519a(android.content.Context, com.xiaomi.push.jb, byte[]):com.xiaomi.push.service.ai$c");
    }

    /* renamed from: a */
    private static String m2520a(Context context, String str, Map<String, String> map) {
        return (map == null || TextUtils.isEmpty(map.get("channel_name"))) ? C0646g.m1366c(context, str) : map.get("channel_name");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static String m2521a(C0729jb c0729jb) {
        C0717iq m2022a;
        if ("com.xiaomi.xmsf".equals(c0729jb.f2004b) && (m2022a = c0729jb.m2022a()) != null && m2022a.m1826a() != null) {
            String str = m2022a.m1826a().get("miui_package_name");
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
        }
        return c0729jb.f2004b;
    }

    /* renamed from: a */
    private static void m2522a(Notification notification, int i) {
        Object m494a = C0504at.m494a(notification, "extraNotification");
        if (m494a != null) {
            C0504at.m495a(m494a, "setMessageCount", Integer.valueOf(i));
        }
    }

    /* renamed from: a */
    public static void m2523a(Context context, String str) {
        m2524a(context, str, -1);
    }

    /* renamed from: a */
    public static void m2524a(Context context, String str, int i) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        int hashCode = ((str.hashCode() / 10) * 10) + i;
        LinkedList linkedList = new LinkedList();
        if (i >= 0) {
            notificationManager.cancel(hashCode);
        }
        synchronized (f2457a) {
            Iterator<Pair<Integer, C0729jb>> it = f2457a.iterator();
            while (it.hasNext()) {
                Pair<Integer, C0729jb> next = it.next();
                C0729jb c0729jb = (C0729jb) next.second;
                if (c0729jb != null) {
                    String m2521a = m2521a(c0729jb);
                    if (i >= 0) {
                        if (hashCode == ((Integer) next.first).intValue() && TextUtils.equals(m2521a, str)) {
                            linkedList.add(next);
                        }
                    } else if (i == -1 && TextUtils.equals(m2521a, str)) {
                        notificationManager.cancel(((Integer) next.first).intValue());
                        linkedList.add(next);
                    }
                }
            }
            if (f2457a != null) {
                f2457a.removeAll(linkedList);
                m2526a(context, (LinkedList<? extends Object>) linkedList);
            }
        }
    }

    /* renamed from: a */
    public static void m2525a(Context context, String str, String str2, String str3) {
        if (TextUtils.isEmpty(str2) && TextUtils.isEmpty(str3)) {
            return;
        }
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        LinkedList linkedList = new LinkedList();
        synchronized (f2457a) {
            Iterator<Pair<Integer, C0729jb>> it = f2457a.iterator();
            while (it.hasNext()) {
                Pair<Integer, C0729jb> next = it.next();
                C0729jb c0729jb = (C0729jb) next.second;
                if (c0729jb != null) {
                    String m2521a = m2521a(c0729jb);
                    C0717iq m2022a = c0729jb.m2022a();
                    if (m2022a != null && TextUtils.equals(m2521a, str)) {
                        String m1843c = m2022a.m1843c();
                        String m1847d = m2022a.m1847d();
                        if (!TextUtils.isEmpty(m1843c) && !TextUtils.isEmpty(m1847d) && m2531a(str2, m1843c) && m2531a(str3, m1847d)) {
                            notificationManager.cancel(((Integer) next.first).intValue());
                            linkedList.add(next);
                        }
                    }
                }
            }
            if (f2457a != null) {
                f2457a.removeAll(linkedList);
                m2526a(context, (LinkedList<? extends Object>) linkedList);
            }
        }
    }

    /* renamed from: a */
    public static void m2526a(Context context, LinkedList<? extends Object> linkedList) {
        if (linkedList == null || linkedList.size() <= 0) {
            return;
        }
        C0833bl.m2663a(context, "category_clear_notification", "clear_notification", linkedList.size(), "");
    }

    /* renamed from: a */
    private static void m2527a(Object obj, Map<String, String> map) {
        if (map == null || TextUtils.isEmpty(map.get("channel_description"))) {
            return;
        }
        C0504at.m495a(obj, "setDescription", map.get("channel_description"));
    }

    /* renamed from: a */
    public static boolean m2528a(Context context, String str) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return false;
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.importance == 100 && Arrays.asList(runningAppProcessInfo.pkgList).contains(str)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    private static boolean m2529a(C0717iq c0717iq) {
        boolean z = false;
        if (c0717iq != null) {
            String m1825a = c0717iq.m1825a();
            z = false;
            if (!TextUtils.isEmpty(m1825a)) {
                z = false;
                if (m1825a.length() == 22) {
                    z = false;
                    if ("satuigm".indexOf(m1825a.charAt(0)) >= 0) {
                        z = true;
                    }
                }
            }
        }
        return z;
    }

    /* renamed from: a */
    public static boolean m2530a(C0729jb c0729jb) {
        C0717iq m2022a = c0729jb.m2022a();
        return m2529a(m2022a) && m2022a.m1858l();
    }

    /* renamed from: a */
    private static boolean m2531a(String str, String str2) {
        return TextUtils.isEmpty(str) || str2.contains(str);
    }

    /* renamed from: a */
    public static boolean m2532a(Map<String, String> map) {
        if (map == null || !map.containsKey("notify_foreground")) {
            return true;
        }
        return "1".equals(map.get("notify_foreground"));
    }

    /* renamed from: a */
    private static String[] m2533a(Context context, C0717iq c0717iq) {
        String str;
        String str2;
        String m1843c = c0717iq.m1843c();
        String m1847d = c0717iq.m1847d();
        Map<String, String> m1826a = c0717iq.m1826a();
        String str3 = m1843c;
        String str4 = m1847d;
        if (m1826a != null) {
            int intValue = Float.valueOf((context.getResources().getDisplayMetrics().widthPixels / context.getResources().getDisplayMetrics().density) + 0.5f).intValue();
            if (intValue <= 320) {
                String str5 = m1826a.get("title_short");
                str = m1843c;
                if (!TextUtils.isEmpty(str5)) {
                    str = str5;
                }
                String str6 = m1826a.get("description_short");
                str3 = str;
                str4 = m1847d;
                if (!TextUtils.isEmpty(str6)) {
                    str2 = str6;
                    str4 = str2;
                    str3 = str;
                }
            } else {
                str3 = m1843c;
                str4 = m1847d;
                if (intValue > 360) {
                    String str7 = m1826a.get("title_long");
                    str = m1843c;
                    if (!TextUtils.isEmpty(str7)) {
                        str = str7;
                    }
                    String str8 = m1826a.get("description_long");
                    str3 = str;
                    str4 = m1847d;
                    if (!TextUtils.isEmpty(str8)) {
                        str2 = str8;
                        str4 = str2;
                        str3 = str;
                    }
                }
            }
        }
        return new String[]{str3, str4};
    }

    /* renamed from: b */
    private static int m2534b(Context context, String str) {
        int m2504a = m2504a(context, str, "mipush_notification");
        int m2504a2 = m2504a(context, str, "mipush_small_notification");
        if (m2504a <= 0) {
            m2504a = m2504a2 > 0 ? m2504a2 : context.getApplicationInfo().icon;
        }
        int i = m2504a;
        if (m2504a == 0) {
            i = m2504a;
            if (Build.VERSION.SDK_INT >= 9) {
                i = context.getApplicationInfo().logo;
            }
        }
        return i;
    }

    /* renamed from: b */
    public static String m2535b(C0729jb c0729jb) {
        return m2530a(c0729jb) ? "E100002" : m2540c(c0729jb) ? "E100000" : m2539b(c0729jb) ? "E100001" : "";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static void m2536b(Context context, String str) {
        context.getSharedPreferences("pref_notify_type", 0).edit().remove(str).commit();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static void m2537b(Context context, String str, int i) {
        context.getSharedPreferences("pref_notify_type", 0).edit().putInt(str, i).commit();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static boolean m2538b(Context context, String str) {
        return context.getSharedPreferences("pref_notify_type", 0).contains(str);
    }

    /* renamed from: b */
    public static boolean m2539b(C0729jb c0729jb) {
        C0717iq m2022a = c0729jb.m2022a();
        return m2529a(m2022a) && m2022a.f1827b == 1 && !m2530a(c0729jb);
    }

    /* renamed from: c */
    public static boolean m2540c(C0729jb c0729jb) {
        C0717iq m2022a = c0729jb.m2022a();
        return m2529a(m2022a) && m2022a.f1827b == 0 && !m2530a(c0729jb);
    }

    /* renamed from: d */
    public static boolean m2541d(C0729jb c0729jb) {
        return m2530a(c0729jb) || m2540c(c0729jb) || m2539b(c0729jb);
    }
}
