package com.sensorsdata.analytics.android.sdk;

import android.view.View;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes08-dex2jar.jar:com/sensorsdata/analytics/android/sdk/Caller.class */
class Caller {
    private static final String TAG = "SA.Caller";
    private final Object[] mMethodArgs;
    private final String mMethodName;
    private final Class<?> mMethodResultType;
    private final Class<?> mTargetClass;
    private final Method mTargetMethod;

    public Caller(Class<?> cls, String str, Object[] objArr, Class<?> cls2) throws NoSuchMethodException {
        this.mMethodName = str;
        this.mMethodArgs = objArr;
        this.mMethodResultType = cls2;
        this.mTargetMethod = pickMethod(cls);
        Method method = this.mTargetMethod;
        if (method != null) {
            this.mTargetClass = method.getDeclaringClass();
            return;
        }
        throw new NoSuchMethodException("Method " + cls.getName() + "." + this.mMethodName + " doesn't exit");
    }

    private static Class<?> assignableArgType(Class<?> cls) {
        if (cls == Byte.class) {
            return Byte.TYPE;
        }
        if (cls == Short.class) {
            return Short.TYPE;
        }
        if (cls == Integer.class) {
            return Integer.TYPE;
        }
        if (cls == Long.class) {
            return Long.TYPE;
        }
        if (cls == Float.class) {
            return Float.TYPE;
        }
        if (cls == Double.class) {
            return Double.TYPE;
        }
        if (cls == Boolean.class) {
            return Boolean.TYPE;
        }
        Class<?> cls2 = cls;
        if (cls == Character.class) {
            cls2 = Character.TYPE;
        }
        return cls2;
    }

    private Method pickMethod(Class<?> cls) {
        Class[] clsArr = new Class[this.mMethodArgs.length];
        int i = 0;
        while (true) {
            int i2 = i;
            Object[] objArr = this.mMethodArgs;
            if (i2 >= objArr.length) {
                break;
            }
            clsArr[i2] = objArr[i2].getClass();
            i = i2 + 1;
        }
        Method[] methods = cls.getMethods();
        int length = methods.length;
        int i3 = 0;
        while (true) {
            int i4 = i3;
            if (i4 >= length) {
                return null;
            }
            Method method = methods[i4];
            String name = method.getName();
            Class<?>[] parameterTypes = method.getParameterTypes();
            if (name.equals(this.mMethodName) && parameterTypes.length == this.mMethodArgs.length && assignableArgType(this.mMethodResultType).isAssignableFrom(assignableArgType(method.getReturnType()))) {
                boolean z = true;
                for (int i5 = 0; i5 < parameterTypes.length && z; i5++) {
                    z = assignableArgType(parameterTypes[i5]).isAssignableFrom(assignableArgType(clsArr[i5]));
                }
                if (z) {
                    return method;
                }
            }
            i3 = i4 + 1;
        }
    }

    public Object applyMethod(View view) {
        return applyMethodWithArguments(view, this.mMethodArgs);
    }

    public Object applyMethodWithArguments(View view, Object[] objArr) {
        if (!this.mTargetClass.isAssignableFrom(view.getClass())) {
            return null;
        }
        try {
            return this.mTargetMethod.invoke(view, objArr);
        } catch (IllegalAccessException e) {
            SALog.m56i(TAG, "Method " + this.mTargetMethod.getName() + " appears not to be public", e);
            return null;
        } catch (IllegalArgumentException e2) {
            SALog.m56i(TAG, "Method " + this.mTargetMethod.getName() + " called with arguments of the wrong type", e2);
            return null;
        } catch (InvocationTargetException e3) {
            SALog.m56i(TAG, "Method " + this.mTargetMethod.getName() + " threw an exception", e3);
            return null;
        }
    }

    public boolean argsAreApplicable(Object[] objArr) {
        Class<?>[] parameterTypes = this.mTargetMethod.getParameterTypes();
        if (objArr.length != parameterTypes.length) {
            return false;
        }
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= objArr.length) {
                return true;
            }
            Class<?> assignableArgType = assignableArgType(parameterTypes[i2]);
            if (objArr[i2] == null) {
                if (assignableArgType == Byte.TYPE || assignableArgType == Short.TYPE || assignableArgType == Integer.TYPE || assignableArgType == Long.TYPE || assignableArgType == Float.TYPE || assignableArgType == Double.TYPE || assignableArgType == Boolean.TYPE || assignableArgType == Character.TYPE) {
                    return false;
                }
            } else if (!assignableArgType.isAssignableFrom(assignableArgType(objArr[i2].getClass()))) {
                return false;
            }
            i = i2 + 1;
        }
    }

    public Object[] getArgs() {
        return this.mMethodArgs;
    }

    public String toString() {
        return "[Caller " + this.mMethodName + "(" + this.mMethodArgs + ")]";
    }
}
