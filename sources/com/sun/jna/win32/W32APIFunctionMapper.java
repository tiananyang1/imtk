package com.sun.jna.win32;

import com.sun.jna.FunctionMapper;
import com.sun.jna.NativeLibrary;
import java.lang.reflect.Method;

/* loaded from: classes08-dex2jar.jar:com/sun/jna/win32/W32APIFunctionMapper.class */
public class W32APIFunctionMapper implements FunctionMapper {
    private final String suffix;
    public static final FunctionMapper UNICODE = new W32APIFunctionMapper(true);
    public static final FunctionMapper ASCII = new W32APIFunctionMapper(false);

    protected W32APIFunctionMapper(boolean z) {
        this.suffix = z ? "W" : "A";
    }

    @Override // com.sun.jna.FunctionMapper
    public String getFunctionName(NativeLibrary nativeLibrary, Method method) {
        String name = method.getName();
        String str = name;
        if (!name.endsWith("W")) {
            str = name;
            if (!name.endsWith("A")) {
                try {
                    str = nativeLibrary.getFunction(name + this.suffix, 63).getName();
                } catch (UnsatisfiedLinkError e) {
                    return name;
                }
            }
        }
        return str;
    }
}
