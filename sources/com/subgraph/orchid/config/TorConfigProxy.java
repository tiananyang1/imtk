package com.subgraph.orchid.config;

import com.subgraph.orchid.TorConfig;
import com.subgraph.orchid.data.HexDigest;
import com.subgraph.orchid.data.IPv4Address;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/config/TorConfigProxy.class */
public class TorConfigProxy implements InvocationHandler {
    private final TorConfigParser parser;
    private final Map<String, Object> configValues = new HashMap();
    private final List<TorConfigBridgeLine> bridges = new ArrayList();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.subgraph.orchid.config.TorConfigProxy$1 */
    /* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/config/TorConfigProxy$1.class */
    public static /* synthetic */ class C03341 {
        static final /* synthetic */ int[] $SwitchMap$com$subgraph$orchid$TorConfig$ConfigVarType = new int[TorConfig.ConfigVarType.values().length];

        /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:8:0x0020
            	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1166)
            	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:1022)
            	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:55)
            */
        static {
            /*
                com.subgraph.orchid.TorConfig$ConfigVarType[] r0 = com.subgraph.orchid.TorConfig.ConfigVarType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.subgraph.orchid.config.TorConfigProxy.C03341.$SwitchMap$com$subgraph$orchid$TorConfig$ConfigVarType = r0
                int[] r0 = com.subgraph.orchid.config.TorConfigProxy.C03341.$SwitchMap$com$subgraph$orchid$TorConfig$ConfigVarType     // Catch: java.lang.NoSuchFieldError -> L20
                com.subgraph.orchid.TorConfig$ConfigVarType r1 = com.subgraph.orchid.TorConfig.ConfigVarType.HS_AUTH     // Catch: java.lang.NoSuchFieldError -> L20
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L20
                r2 = 1
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L20
            L14:
                int[] r0 = com.subgraph.orchid.config.TorConfigProxy.C03341.$SwitchMap$com$subgraph$orchid$TorConfig$ConfigVarType     // Catch: java.lang.NoSuchFieldError -> L20 java.lang.NoSuchFieldError -> L24
                com.subgraph.orchid.TorConfig$ConfigVarType r1 = com.subgraph.orchid.TorConfig.ConfigVarType.BRIDGE_LINE     // Catch: java.lang.NoSuchFieldError -> L24
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L24
                r2 = 2
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L24
                return
            L20:
                r4 = move-exception
                goto L14
            L24:
                r4 = move-exception
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.subgraph.orchid.config.TorConfigProxy.C03341.m3837clinit():void");
        }
    }

    public TorConfigProxy() {
        this.configValues.put("Bridges", this.bridges);
        this.parser = new TorConfigParser();
    }

    private TorConfig.ConfigVar getAnnotationForVariable(String str) {
        String str2 = "get" + str;
        Method[] declaredMethods = TorConfig.class.getDeclaredMethods();
        int length = declaredMethods.length;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length) {
                return null;
            }
            Method method = declaredMethods[i2];
            if (str2.equals(method.getName())) {
                return (TorConfig.ConfigVar) method.getAnnotation(TorConfig.ConfigVar.class);
            }
            i = i2 + 1;
        }
    }

    private String getDefaultValueString(String str) {
        TorConfig.ConfigVar annotationForVariable = getAnnotationForVariable(str);
        if (annotationForVariable == null) {
            return null;
        }
        return annotationForVariable.defaultValue();
    }

    private Object getDefaultVariableValue(String str) {
        String defaultValueString = getDefaultValueString(str);
        TorConfig.ConfigVarType variableType = getVariableType(str);
        if (defaultValueString == null || variableType == null) {
            return null;
        }
        return this.parser.parseValue(defaultValueString, variableType);
    }

    private TorConfigHSAuth getHSAuth(String str) {
        if (!this.configValues.containsKey(str)) {
            this.configValues.put(str, new TorConfigHSAuth());
        }
        return (TorConfigHSAuth) this.configValues.get(str);
    }

    private String getVariableNameForMethod(Method method) {
        String name = method.getName();
        if (name.startsWith("get") || name.startsWith("set") || name.startsWith("add")) {
            return name.substring(3);
        }
        throw new IllegalArgumentException();
    }

    private TorConfig.ConfigVarType getVariableType(String str) {
        if ("Bridge".equals(str)) {
            return TorConfig.ConfigVarType.BRIDGE_LINE;
        }
        TorConfig.ConfigVar annotationForVariable = getAnnotationForVariable(str);
        if (annotationForVariable == null) {
            return null;
        }
        return annotationForVariable.type();
    }

    private Object getVariableValue(String str) {
        return this.configValues.containsKey(str) ? this.configValues.get(str) : getDefaultVariableValue(str);
    }

    private void invokeAddMethod(Method method, Object[] objArr) {
        String variableNameForMethod = getVariableNameForMethod(method);
        int i = C03341.$SwitchMap$com$subgraph$orchid$TorConfig$ConfigVarType[getVariableType(variableNameForMethod).ordinal()];
        if (i == 1) {
            invokeHSAuthAdd(variableNameForMethod, objArr);
        } else {
            if (i != 2) {
                throw new UnsupportedOperationException("addX configuration methods only supported for HS_AUTH or BRIDGE_LINE type");
            }
            invokeBridgeAdd(objArr);
        }
    }

    private void invokeBridgeAdd(Object[] objArr) {
        if (objArr.length >= 2 && (objArr[0] instanceof IPv4Address) && (objArr[1] instanceof Integer)) {
            if (objArr.length == 2) {
                this.bridges.add(new TorConfigBridgeLine((IPv4Address) objArr[0], ((Integer) objArr[1]).intValue(), null));
                return;
            } else if (objArr.length == 3 && (objArr[2] instanceof HexDigest)) {
                this.bridges.add(new TorConfigBridgeLine((IPv4Address) objArr[0], ((Integer) objArr[1]).intValue(), (HexDigest) objArr[2]));
                return;
            }
        }
        throw new IllegalArgumentException();
    }

    private Object invokeGetMethod(Method method) {
        Object variableValue = getVariableValue(getVariableNameForMethod(method));
        Object obj = variableValue;
        if (variableValue instanceof TorConfigInterval) {
            obj = Long.valueOf(((TorConfigInterval) variableValue).getMilliseconds());
        }
        return obj;
    }

    private Object invokeGetMethodWithArgs(Method method, Object[] objArr) {
        String variableNameForMethod = getVariableNameForMethod(method);
        if (getVariableType(variableNameForMethod) == TorConfig.ConfigVarType.HS_AUTH) {
            return invokeHSAuthGet(variableNameForMethod, objArr);
        }
        throw new IllegalArgumentException();
    }

    private void invokeHSAuthAdd(String str, Object[] objArr) {
        if (objArr.length != 2 || !(objArr[0] instanceof String) || !(objArr[1] instanceof String)) {
            throw new IllegalArgumentException();
        }
        getHSAuth(str).add((String) objArr[0], (String) objArr[1]);
    }

    private Object invokeHSAuthGet(String str, Object[] objArr) {
        if (objArr[0] instanceof String) {
            return getHSAuth(str).get((String) objArr[0]);
        }
        throw new IllegalArgumentException();
    }

    private void setIntervalValue(String str, Object[] objArr) {
        if (!(objArr[0] instanceof Long) || !(objArr[1] instanceof TimeUnit)) {
            throw new IllegalArgumentException();
        }
        this.configValues.put(str, new TorConfigInterval(((Long) objArr[0]).longValue(), (TimeUnit) objArr[1]));
    }

    @Override // java.lang.reflect.InvocationHandler
    public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
        if (method.getName().startsWith("set")) {
            invokeSetMethod(method, objArr);
            return null;
        }
        if (method.getName().startsWith("get")) {
            return objArr == null ? invokeGetMethod(method) : invokeGetMethodWithArgs(method, objArr);
        }
        if (!method.getName().startsWith("add")) {
            throw new IllegalArgumentException();
        }
        invokeAddMethod(method, objArr);
        return null;
    }

    void invokeSetMethod(Method method, Object[] objArr) {
        String variableNameForMethod = getVariableNameForMethod(method);
        TorConfig.ConfigVar annotationForVariable = getAnnotationForVariable(variableNameForMethod);
        if (annotationForVariable == null || annotationForVariable.type() != TorConfig.ConfigVarType.INTERVAL) {
            this.configValues.put(variableNameForMethod, objArr[0]);
        } else {
            setIntervalValue(variableNameForMethod, objArr);
        }
    }
}
