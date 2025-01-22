package com.sun.jna.win32;

import com.sun.jna.DefaultTypeMapper;
import com.sun.jna.FromNativeContext;
import com.sun.jna.StringArray;
import com.sun.jna.ToNativeContext;
import com.sun.jna.TypeConverter;
import com.sun.jna.TypeMapper;
import com.sun.jna.WString;

/* loaded from: classes08-dex2jar.jar:com/sun/jna/win32/W32APITypeMapper.class */
public class W32APITypeMapper extends DefaultTypeMapper {
    public static final TypeMapper DEFAULT;
    public static final TypeMapper UNICODE = new W32APITypeMapper(true);
    public static final TypeMapper ASCII = new W32APITypeMapper(false);

    static {
        DEFAULT = Boolean.getBoolean("w32.ascii") ? ASCII : UNICODE;
    }

    protected W32APITypeMapper(boolean z) {
        if (z) {
            TypeConverter typeConverter = new TypeConverter() { // from class: com.sun.jna.win32.W32APITypeMapper.1
                @Override // com.sun.jna.FromNativeConverter
                public Object fromNative(Object obj, FromNativeContext fromNativeContext) {
                    if (obj == null) {
                        return null;
                    }
                    return obj.toString();
                }

                @Override // com.sun.jna.FromNativeConverter, com.sun.jna.ToNativeConverter
                public Class<?> nativeType() {
                    return WString.class;
                }

                @Override // com.sun.jna.ToNativeConverter
                public Object toNative(Object obj, ToNativeContext toNativeContext) {
                    if (obj == null) {
                        return null;
                    }
                    return obj instanceof String[] ? new StringArray((String[]) obj, true) : new WString(obj.toString());
                }
            };
            addTypeConverter(String.class, typeConverter);
            addToNativeConverter(String[].class, typeConverter);
        }
        addTypeConverter(Boolean.class, new TypeConverter() { // from class: com.sun.jna.win32.W32APITypeMapper.2
            @Override // com.sun.jna.FromNativeConverter
            public Object fromNative(Object obj, FromNativeContext fromNativeContext) {
                return ((Integer) obj).intValue() != 0 ? Boolean.TRUE : Boolean.FALSE;
            }

            @Override // com.sun.jna.FromNativeConverter, com.sun.jna.ToNativeConverter
            public Class<?> nativeType() {
                return Integer.class;
            }

            /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Throwable, java.lang.Runtime] */
            @Override // com.sun.jna.ToNativeConverter
            public Object toNative(Object obj, ToNativeContext toNativeContext) {
                throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.provideAs(TypeTransformer.java:780)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:659)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:698)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s1stmt(TypeTransformer.java:810)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:840)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
            }
        });
    }
}
