package com.google.protobuf;

import com.google.protobuf.GeneratedMessageLite;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes08-dex2jar.jar:com/google/protobuf/MessageLiteToString.class */
public final class MessageLiteToString {
    private static final String BUILDER_LIST_SUFFIX = "OrBuilderList";
    private static final String BYTES_SUFFIX = "Bytes";
    private static final String LIST_SUFFIX = "List";

    MessageLiteToString() {
    }

    private static final String camelCaseToSnakeCase(String str) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= str.length()) {
                return sb.toString();
            }
            char charAt = str.charAt(i2);
            if (Character.isUpperCase(charAt)) {
                sb.append(EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR);
            }
            sb.append(Character.toLowerCase(charAt));
            i = i2 + 1;
        }
    }

    private static boolean isDefaultValue(Object obj) {
        return obj instanceof Boolean ? !((Boolean) obj).booleanValue() : obj instanceof Integer ? ((Integer) obj).intValue() == 0 : obj instanceof Float ? ((Float) obj).floatValue() == 0.0f : obj instanceof Double ? ((Double) obj).doubleValue() == 0.0d : obj instanceof String ? obj.equals("") : obj instanceof ByteString ? obj.equals(ByteString.EMPTY) : obj instanceof MessageLite ? obj == ((MessageLite) obj).mo3095getDefaultInstanceForType() : (obj instanceof java.lang.Enum) && ((java.lang.Enum) obj).ordinal() == 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final void printField(StringBuilder sb, int i, String str, Object obj) {
        if (obj instanceof List) {
            Iterator it = ((List) obj).iterator();
            while (it.hasNext()) {
                printField(sb, i, str, it.next());
            }
            return;
        }
        sb.append('\n');
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 >= i) {
                break;
            }
            sb.append(' ');
            i2 = i3 + 1;
        }
        sb.append(str);
        if (obj instanceof String) {
            sb.append(": \"");
            sb.append(TextFormatEscaper.escapeText((String) obj));
            sb.append('\"');
            return;
        }
        if (obj instanceof ByteString) {
            sb.append(": \"");
            sb.append(TextFormatEscaper.escapeBytes((ByteString) obj));
            sb.append('\"');
        } else {
            if (!(obj instanceof GeneratedMessageLite)) {
                sb.append(": ");
                sb.append(obj.toString());
                return;
            }
            sb.append(" {");
            reflectivePrintWithIndent((GeneratedMessageLite) obj, sb, i + 2);
            sb.append("\n");
            int i4 = 0;
            while (true) {
                int i5 = i4;
                if (i5 >= i) {
                    sb.append("}");
                    return;
                } else {
                    sb.append(' ');
                    i4 = i5 + 1;
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static void reflectivePrintWithIndent(MessageLite messageLite, StringBuilder sb, int i) {
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        TreeSet treeSet = new TreeSet();
        java.lang.reflect.Method[] declaredMethods = messageLite.getClass().getDeclaredMethods();
        int length = declaredMethods.length;
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 >= length) {
                break;
            }
            java.lang.reflect.Method method = declaredMethods[i3];
            hashMap2.put(method.getName(), method);
            if (method.getParameterTypes().length == 0) {
                hashMap.put(method.getName(), method);
                if (method.getName().startsWith("get")) {
                    treeSet.add(method.getName());
                }
            }
            i2 = i3 + 1;
        }
        Iterator it = treeSet.iterator();
        while (it.hasNext()) {
            String replaceFirst = ((String) it.next()).replaceFirst("get", "");
            if (replaceFirst.endsWith(LIST_SUFFIX) && !replaceFirst.endsWith(BUILDER_LIST_SUFFIX)) {
                String str = replaceFirst.substring(0, 1).toLowerCase() + replaceFirst.substring(1, replaceFirst.length() - 4);
                java.lang.reflect.Method method2 = (java.lang.reflect.Method) hashMap.get("get" + replaceFirst);
                if (method2 != null && method2.getReturnType().equals(List.class)) {
                    printField(sb, i, camelCaseToSnakeCase(str), GeneratedMessageLite.invokeOrDie(method2, messageLite, new Object[0]));
                }
            }
            if (((java.lang.reflect.Method) hashMap2.get("set" + replaceFirst)) != null) {
                if (replaceFirst.endsWith(BYTES_SUFFIX)) {
                    if (hashMap.containsKey("get" + replaceFirst.substring(0, replaceFirst.length() - 5))) {
                    }
                }
                String str2 = replaceFirst.substring(0, 1).toLowerCase() + replaceFirst.substring(1);
                java.lang.reflect.Method method3 = (java.lang.reflect.Method) hashMap.get("get" + replaceFirst);
                java.lang.reflect.Method method4 = (java.lang.reflect.Method) hashMap.get("has" + replaceFirst);
                if (method3 != null) {
                    Object invokeOrDie = GeneratedMessageLite.invokeOrDie(method3, messageLite, new Object[0]);
                    if (method4 == null ? !isDefaultValue(invokeOrDie) : ((Boolean) GeneratedMessageLite.invokeOrDie(method4, messageLite, new Object[0])).booleanValue()) {
                        printField(sb, i, camelCaseToSnakeCase(str2), invokeOrDie);
                    }
                }
            }
        }
        if (messageLite instanceof GeneratedMessageLite.ExtendableMessage) {
            Iterator<Map.Entry<GeneratedMessageLite.ExtensionDescriptor, Object>> it2 = ((GeneratedMessageLite.ExtendableMessage) messageLite).extensions.iterator();
            while (it2.hasNext()) {
                Map.Entry<GeneratedMessageLite.ExtensionDescriptor, Object> next = it2.next();
                printField(sb, i, "[" + next.getKey().getNumber() + "]", next.getValue());
            }
        }
        GeneratedMessageLite generatedMessageLite = (GeneratedMessageLite) messageLite;
        if (generatedMessageLite.unknownFields != null) {
            generatedMessageLite.unknownFields.printWithIndent(sb, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String toString(MessageLite messageLite, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("# ");
        sb.append(str);
        reflectivePrintWithIndent(messageLite, sb, 0);
        return sb.toString();
    }
}
