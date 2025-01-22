package com.sun.jna.win32;

import com.sun.jna.FunctionMapper;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import com.sun.jna.NativeMapped;
import com.sun.jna.NativeMappedConverter;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.lang.reflect.Method;

/* loaded from: classes08-dex2jar.jar:com/sun/jna/win32/StdCallFunctionMapper.class */
public class StdCallFunctionMapper implements FunctionMapper {
    protected int getArgumentNativeStackSize(Class<?> cls) {
        Class<?> cls2 = cls;
        if (NativeMapped.class.isAssignableFrom(cls)) {
            cls2 = NativeMappedConverter.getInstance(cls).nativeType();
        }
        if (cls2.isArray()) {
            return Native.POINTER_SIZE;
        }
        try {
            return Native.getNativeSize(cls2);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unknown native stack allocation size for " + cls2);
        }
    }

    @Override // com.sun.jna.FunctionMapper
    public String getFunctionName(NativeLibrary nativeLibrary, Method method) {
        String name = method.getName();
        int i = 0;
        for (Class<?> cls : method.getParameterTypes()) {
            i += getArgumentNativeStackSize(cls);
        }
        String str = name + "@" + i;
        try {
            return nativeLibrary.getFunction(str, 63).getName();
        } catch (UnsatisfiedLinkError e) {
            try {
                return nativeLibrary.getFunction(EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR + str, 63).getName();
            } catch (UnsatisfiedLinkError e2) {
                return name;
            }
        }
    }
}
