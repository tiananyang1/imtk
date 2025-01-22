package com.xiaomi.push;

import android.util.Log;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.xiaomi.push.at */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/at.class */
public class C0504at {

    /* renamed from: a */
    private static final Map<Class<?>, Class<?>> f449a = new HashMap();

    /* renamed from: com.xiaomi.push.at$a */
    /* loaded from: classes08-dex2jar.jar:com/xiaomi/push/at$a.class */
    public static class a<T> {

        /* renamed from: a */
        public final Class<? extends T> f450a;

        /* renamed from: a */
        public final T f451a;
    }

    static {
        f449a.put(Boolean.class, Boolean.TYPE);
        f449a.put(Byte.class, Byte.TYPE);
        f449a.put(Character.class, Character.TYPE);
        f449a.put(Short.class, Short.TYPE);
        f449a.put(Integer.class, Integer.TYPE);
        f449a.put(Float.class, Float.TYPE);
        f449a.put(Long.class, Long.TYPE);
        f449a.put(Double.class, Double.TYPE);
        f449a.put(Boolean.TYPE, Boolean.TYPE);
        f449a.put(Byte.TYPE, Byte.TYPE);
        f449a.put(Character.TYPE, Character.TYPE);
        f449a.put(Short.TYPE, Short.TYPE);
        f449a.put(Integer.TYPE, Integer.TYPE);
        f449a.put(Float.TYPE, Float.TYPE);
        f449a.put(Long.TYPE, Long.TYPE);
        f449a.put(Double.TYPE, Double.TYPE);
    }

    /* renamed from: a */
    public static <T> T m491a(Class<? extends Object> cls, Object obj, String str) {
        Class<? extends Object> cls2 = cls;
        Field field = null;
        while (field == null) {
            try {
                Field declaredField = cls2.getDeclaredField(str);
                declaredField.setAccessible(true);
                field = declaredField;
            } catch (NoSuchFieldException e) {
                cls2 = cls2.getSuperclass();
            }
            if (cls2 == null) {
                throw new NoSuchFieldException();
            }
        }
        field.setAccessible(true);
        return (T) field.get(obj);
    }

    /* renamed from: a */
    public static <T> T m492a(Class<? extends Object> cls, String str) {
        try {
            return (T) m491a(cls, (Object) null, str);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } catch (NoSuchFieldException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static <T> T m493a(Class<?> cls, String str, Object... objArr) {
        return (T) m498a(cls, str, m501a(objArr)).invoke(null, m502a(objArr));
    }

    /* renamed from: a */
    public static <T> T m494a(Object obj, String str) {
        try {
            return (T) m491a((Class<? extends Object>) obj.getClass(), obj, str);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } catch (NoSuchFieldException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static <T> T m495a(Object obj, String str, Object... objArr) {
        try {
            return (T) m503b(obj, str, objArr);
        } catch (Exception e) {
            Log.w("JavaCalls", "Meet exception when call Method '" + str + "' in " + obj, e);
            return null;
        }
    }

    /* renamed from: a */
    public static <T> T m496a(String str, String str2) {
        try {
            return (T) m491a((Class<? extends Object>) Class.forName(str), (Object) null, str2);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            return null;
        } catch (NoSuchFieldException e3) {
            e3.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static <T> T m497a(String str, String str2, Object... objArr) {
        try {
            return (T) m493a(Class.forName(str), str2, objArr);
        } catch (Exception e) {
            Log.w("JavaCalls", "Meet exception when call Method '" + str2 + "' in " + str, e);
            return null;
        }
    }

    /* renamed from: a */
    private static Method m498a(Class<?> cls, String str, Class<?>... clsArr) {
        Method m499a = m499a(cls.getDeclaredMethods(), str, clsArr);
        if (m499a != null) {
            m499a.setAccessible(true);
            return m499a;
        }
        if (cls.getSuperclass() != null) {
            return m498a((Class<?>) cls.getSuperclass(), str, clsArr);
        }
        throw new NoSuchMethodException();
    }

    /* renamed from: a */
    private static Method m499a(Method[] methodArr, String str, Class<?>[] clsArr) {
        if (str == null) {
            throw new NullPointerException("Method name must not be null.");
        }
        int length = methodArr.length;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length) {
                return null;
            }
            Method method = methodArr[i2];
            if (method.getName().equals(str) && m500a(method.getParameterTypes(), clsArr)) {
                return method;
            }
            i = i2 + 1;
        }
    }

    /* renamed from: a */
    private static boolean m500a(Class<?>[] clsArr, Class<?>[] clsArr2) {
        boolean z = true;
        if (clsArr == null) {
            if (clsArr2 != null) {
                if (clsArr2.length == 0) {
                    return true;
                }
                z = false;
            }
            return z;
        }
        if (clsArr2 == null) {
            return clsArr.length == 0;
        }
        if (clsArr.length != clsArr2.length) {
            return false;
        }
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= clsArr.length) {
                return true;
            }
            if (clsArr2[i2] != null && !clsArr[i2].isAssignableFrom(clsArr2[i2]) && (!f449a.containsKey(clsArr[i2]) || !f449a.get(clsArr[i2]).equals(f449a.get(clsArr2[i2])))) {
                return false;
            }
            i = i2 + 1;
        }
    }

    /* renamed from: a */
    private static Class<?>[] m501a(Object... objArr) {
        Class<?>[] clsArr = null;
        if (objArr != null) {
            clsArr = null;
            if (objArr.length > 0) {
                Class<?>[] clsArr2 = new Class[objArr.length];
                int i = 0;
                while (true) {
                    int i2 = i;
                    if (i2 >= objArr.length) {
                        break;
                    }
                    Object obj = objArr[i2];
                    if (obj == null || !(obj instanceof a)) {
                        clsArr2[i2] = obj == null ? null : obj.getClass();
                    } else {
                        clsArr2[i2] = ((a) obj).f450a;
                    }
                    i = i2 + 1;
                }
                clsArr = clsArr2;
            }
        }
        return clsArr;
    }

    /* renamed from: a */
    private static Object[] m502a(Object... objArr) {
        Object[] objArr2;
        if (objArr != null && objArr.length > 0) {
            Object[] objArr3 = new Object[objArr.length];
            int i = 0;
            while (true) {
                int i2 = i;
                objArr2 = objArr3;
                if (i2 >= objArr.length) {
                    break;
                }
                Object obj = objArr[i2];
                if (obj == null || !(obj instanceof a)) {
                    objArr3[i2] = obj;
                } else {
                    objArr3[i2] = ((a) obj).f451a;
                }
                i = i2 + 1;
            }
        } else {
            objArr2 = null;
        }
        return objArr2;
    }

    /* renamed from: b */
    public static <T> T m503b(Object obj, String str, Object... objArr) {
        return (T) m498a(obj.getClass(), str, m501a(objArr)).invoke(obj, m502a(objArr));
    }
}
