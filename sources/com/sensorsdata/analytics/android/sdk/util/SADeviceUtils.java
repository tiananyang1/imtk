package com.sensorsdata.analytics.android.sdk.util;

import android.content.Context;
import android.text.TextUtils;
import com.sensorsdata.analytics.android.sdk.SALog;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes08-dex2jar.jar:com/sensorsdata/analytics/android/sdk/util/SADeviceUtils.class */
public class SADeviceUtils {
    private static final String TAG = "SA.DeviceUtils";
    private static CountDownLatch countDownLatch;
    private static String oaid = "";

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes08-dex2jar.jar:com/sensorsdata/analytics/android/sdk/util/SADeviceUtils$IdentifyListenerHandler.class */
    public static class IdentifyListenerHandler implements InvocationHandler {
        IdentifyListenerHandler() {
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            try {
                if (!"OnSupport".equals(method.getName())) {
                    return null;
                }
                if (((Boolean) objArr[0]).booleanValue()) {
                    String unused = SADeviceUtils.oaid = (String) Class.forName("com.bun.miitmdid.supplier.IdSupplier").getDeclaredMethod("getOAID", new Class[0]).invoke(objArr[1], new Object[0]);
                    SALog.m53d(SADeviceUtils.TAG, "oaid:" + SADeviceUtils.oaid);
                }
                SADeviceUtils.countDownLatch.countDown();
                return null;
            } catch (Exception e) {
                SADeviceUtils.countDownLatch.countDown();
                return null;
            }
        }
    }

    public static String getOAID(Context context) {
        try {
            countDownLatch = new CountDownLatch(1);
            if (TextUtils.isEmpty(oaid)) {
                getOAIDReflect(context, 2);
            }
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                SALog.printStackTrace(e);
            }
            SALog.m53d(TAG, "CountDownLatch await");
            return oaid;
        } catch (Exception e2) {
            SALog.printStackTrace(e2);
            return "";
        }
    }

    private static void getOAIDReflect(Context context, int i) {
        if (i == 0) {
            return;
        }
        int i2 = i;
        try {
            Class.forName("com.bun.miitmdid.core.JLibrary").getDeclaredMethod("InitEntry", Context.class).invoke(null, context);
            Class<?> cls = Class.forName("com.bun.miitmdid.core.IIdentifierListener");
            int intValue = ((Integer) Class.forName("com.bun.miitmdid.core.MdidSdkHelper").getDeclaredMethod("InitSdk", Context.class, Boolean.TYPE, cls).invoke(null, context, true, Proxy.newProxyInstance(context.getClassLoader(), new Class[]{cls}, new IdentifyListenerHandler()))).intValue();
            i2 = i;
            if (intValue != 1008614) {
                StringBuilder sb = new StringBuilder();
                sb.append("get OAID failed : ");
                sb.append(intValue);
                SALog.m53d(TAG, sb.toString());
                int i3 = i - 1;
                getOAIDReflect(context, i3);
                i2 = i3;
                if (i3 == 0) {
                    countDownLatch.countDown();
                    i2 = i3;
                }
            }
            new Thread(new Runnable() { // from class: com.sensorsdata.analytics.android.sdk.util.SADeviceUtils.1
                /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
                    jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:6:0x000d
                    	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1166)
                    	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:1022)
                    	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:55)
                    */
                @Override // java.lang.Runnable
                public void run() {
                    /*
                        r3 = this;
                        r0 = 2000(0x7d0, double:9.88E-321)
                        java.lang.Thread.sleep(r0)     // Catch: java.lang.InterruptedException -> Ld
                    L6:
                        java.util.concurrent.CountDownLatch r0 = com.sensorsdata.analytics.android.sdk.util.SADeviceUtils.access$000()     // Catch: java.lang.InterruptedException -> Ld
                        r0.countDown()
                        return
                    Ld:
                        r4 = move-exception
                        goto L6
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.sensorsdata.analytics.android.sdk.util.SADeviceUtils.RunnableC02581.run():void");
                }
            }).start();
        } catch (Exception e) {
            SALog.printStackTrace(e);
            int i4 = i2 - 1;
            getOAIDReflect(context, i4);
            if (i4 == 0) {
                countDownLatch.countDown();
            }
        }
    }
}
