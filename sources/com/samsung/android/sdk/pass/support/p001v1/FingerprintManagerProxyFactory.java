package com.samsung.android.sdk.pass.support.p001v1;

import android.content.Context;
import android.util.Log;
import com.samsung.android.sdk.pass.support.IFingerprintManagerProxy;
import com.samsung.android.sdk.pass.support.SdkSupporter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes08-dex2jar.jar:com/samsung/android/sdk/pass/support/v1/FingerprintManagerProxyFactory.class */
public class FingerprintManagerProxyFactory {

    /* renamed from: com.samsung.android.sdk.pass.support.v1.FingerprintManagerProxyFactory$a */
    /* loaded from: classes08-dex2jar.jar:com/samsung/android/sdk/pass/support/v1/FingerprintManagerProxyFactory$a.class */
    private static final class C0194a implements InvocationHandler {

        /* renamed from: a */
        private Object f177a;

        /* renamed from: b */
        private Map f178b = new HashMap();

        public C0194a(Object obj) {
            this.f177a = obj;
            Method[] methods = IFingerprintManagerProxy.class.getMethods();
            Method[] methods2 = obj.getClass().getMethods();
            int length = methods2.length;
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= length) {
                    return;
                }
                Method method = methods2[i2];
                String name = method.getName();
                if (m45a(methods, method)) {
                    this.f178b.put(name, method);
                }
                i = i2 + 1;
            }
        }

        /* renamed from: a */
        private static boolean m45a(Method[] methodArr, Method method) {
            String name = method.getName();
            Class<?>[] parameterTypes = method.getParameterTypes();
            int length = methodArr.length;
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= length) {
                    return false;
                }
                Method method2 = methodArr[i2];
                if (method2.getName().equals(name)) {
                    Class<?>[] parameterTypes2 = method2.getParameterTypes();
                    if (parameterTypes == null || parameterTypes2 == null) {
                        return true;
                    }
                    if (parameterTypes.length == parameterTypes2.length) {
                        int length2 = parameterTypes.length;
                        boolean z = false;
                        for (int i3 = 0; i3 < length2; i3++) {
                            if (!parameterTypes2[i3].equals(parameterTypes[i3])) {
                                z = true;
                            }
                        }
                        if (!z) {
                            return true;
                        }
                    } else {
                        continue;
                    }
                }
                i = i2 + 1;
            }
        }

        @Override // java.lang.reflect.InvocationHandler
        public final Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            Method method2 = (Method) this.f178b.get(method.getName());
            if (method2 != null) {
                return method2.invoke(this.f177a, objArr);
            }
            return null;
        }
    }

    public static IFingerprintManagerProxy create(Context context) {
        Object obj;
        try {
            obj = Class.forName(SdkSupporter.CLASSNAME_FINGERPRINT_MANAGER).getMethod("getInstance", Context.class).invoke(null, context);
        } catch (Exception e) {
            Log.e("FingerprintManagerProxy", "Cannot create FingerprintManagerProxy : " + e);
            obj = null;
        }
        if (obj == null) {
            return null;
        }
        return (IFingerprintManagerProxy) Proxy.newProxyInstance(FingerprintManagerProxyFactory.class.getClassLoader(), new Class[]{IFingerprintManagerProxy.class}, new C0194a(obj));
    }
}
