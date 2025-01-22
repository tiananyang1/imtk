package com.subgraph.orchid.config;

import com.subgraph.orchid.TorConfig;
import com.xiaomi.mipush.sdk.Constants;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/config/TorConfigParser.class */
public class TorConfigParser {

    /* renamed from: com.subgraph.orchid.config.TorConfigParser$1 */
    /* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/config/TorConfigParser$1.class */
    static /* synthetic */ class C03331 {
        static final /* synthetic */ int[] $SwitchMap$com$subgraph$orchid$TorConfig$ConfigVarType = new int[TorConfig.ConfigVarType.values().length];

        /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:57:0x008d
            	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1166)
            	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:1022)
            	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:55)
            */
        static {
            /*
                com.subgraph.orchid.TorConfig$ConfigVarType[] r0 = com.subgraph.orchid.TorConfig.ConfigVarType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.subgraph.orchid.config.TorConfigParser.C03331.$SwitchMap$com$subgraph$orchid$TorConfig$ConfigVarType = r0
                int[] r0 = com.subgraph.orchid.config.TorConfigParser.C03331.$SwitchMap$com$subgraph$orchid$TorConfig$ConfigVarType     // Catch: java.lang.NoSuchFieldError -> L71
                com.subgraph.orchid.TorConfig$ConfigVarType r1 = com.subgraph.orchid.TorConfig.ConfigVarType.BOOLEAN     // Catch: java.lang.NoSuchFieldError -> L71
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L71
                r2 = 1
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L71
            L14:
                int[] r0 = com.subgraph.orchid.config.TorConfigParser.C03331.$SwitchMap$com$subgraph$orchid$TorConfig$ConfigVarType     // Catch: java.lang.NoSuchFieldError -> L71 java.lang.NoSuchFieldError -> L75
                com.subgraph.orchid.TorConfig$ConfigVarType r1 = com.subgraph.orchid.TorConfig.ConfigVarType.INTEGER     // Catch: java.lang.NoSuchFieldError -> L75
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L75
                r2 = 2
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L75
            L1f:
                int[] r0 = com.subgraph.orchid.config.TorConfigParser.C03331.$SwitchMap$com$subgraph$orchid$TorConfig$ConfigVarType     // Catch: java.lang.NoSuchFieldError -> L75 java.lang.NoSuchFieldError -> L79
                com.subgraph.orchid.TorConfig$ConfigVarType r1 = com.subgraph.orchid.TorConfig.ConfigVarType.INTERVAL     // Catch: java.lang.NoSuchFieldError -> L79
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L79
                r2 = 3
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L79
            L2a:
                int[] r0 = com.subgraph.orchid.config.TorConfigParser.C03331.$SwitchMap$com$subgraph$orchid$TorConfig$ConfigVarType     // Catch: java.lang.NoSuchFieldError -> L79 java.lang.NoSuchFieldError -> L7d
                com.subgraph.orchid.TorConfig$ConfigVarType r1 = com.subgraph.orchid.TorConfig.ConfigVarType.PATH     // Catch: java.lang.NoSuchFieldError -> L7d
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L7d
                r2 = 4
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L7d
            L35:
                int[] r0 = com.subgraph.orchid.config.TorConfigParser.C03331.$SwitchMap$com$subgraph$orchid$TorConfig$ConfigVarType     // Catch: java.lang.NoSuchFieldError -> L7d java.lang.NoSuchFieldError -> L81
                com.subgraph.orchid.TorConfig$ConfigVarType r1 = com.subgraph.orchid.TorConfig.ConfigVarType.PORTLIST     // Catch: java.lang.NoSuchFieldError -> L81
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L81
                r2 = 5
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L81
            L40:
                int[] r0 = com.subgraph.orchid.config.TorConfigParser.C03331.$SwitchMap$com$subgraph$orchid$TorConfig$ConfigVarType     // Catch: java.lang.NoSuchFieldError -> L81 java.lang.NoSuchFieldError -> L85
                com.subgraph.orchid.TorConfig$ConfigVarType r1 = com.subgraph.orchid.TorConfig.ConfigVarType.STRING     // Catch: java.lang.NoSuchFieldError -> L85
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L85
                r2 = 6
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L85
            L4c:
                int[] r0 = com.subgraph.orchid.config.TorConfigParser.C03331.$SwitchMap$com$subgraph$orchid$TorConfig$ConfigVarType     // Catch: java.lang.NoSuchFieldError -> L85 java.lang.NoSuchFieldError -> L89
                com.subgraph.orchid.TorConfig$ConfigVarType r1 = com.subgraph.orchid.TorConfig.ConfigVarType.STRINGLIST     // Catch: java.lang.NoSuchFieldError -> L89
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L89
                r2 = 7
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L89
            L58:
                int[] r0 = com.subgraph.orchid.config.TorConfigParser.C03331.$SwitchMap$com$subgraph$orchid$TorConfig$ConfigVarType     // Catch: java.lang.NoSuchFieldError -> L89 java.lang.NoSuchFieldError -> L8d
                com.subgraph.orchid.TorConfig$ConfigVarType r1 = com.subgraph.orchid.TorConfig.ConfigVarType.AUTOBOOL     // Catch: java.lang.NoSuchFieldError -> L8d
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L8d
                r2 = 8
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L8d
            L64:
                int[] r0 = com.subgraph.orchid.config.TorConfigParser.C03331.$SwitchMap$com$subgraph$orchid$TorConfig$ConfigVarType     // Catch: java.lang.NoSuchFieldError -> L8d java.lang.NoSuchFieldError -> L91
                com.subgraph.orchid.TorConfig$ConfigVarType r1 = com.subgraph.orchid.TorConfig.ConfigVarType.HS_AUTH     // Catch: java.lang.NoSuchFieldError -> L91
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L91
                r2 = 9
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L91
                return
            L71:
                r4 = move-exception
                goto L14
            L75:
                r4 = move-exception
                goto L1f
            L79:
                r4 = move-exception
                goto L2a
            L7d:
                r4 = move-exception
                goto L35
            L81:
                r4 = move-exception
                goto L40
            L85:
                r4 = move-exception
                goto L4c
            L89:
                r4 = move-exception
                goto L58
            L8d:
                r4 = move-exception
                goto L64
            L91:
                r4 = move-exception
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.subgraph.orchid.config.TorConfigParser.C03331.m3836clinit():void");
        }
    }

    private TorConfig.AutoBoolValue parseAutoBool(String str) {
        if ("auto".equalsIgnoreCase(str)) {
            return TorConfig.AutoBoolValue.AUTO;
        }
        if ("true".equalsIgnoreCase(str)) {
            return TorConfig.AutoBoolValue.TRUE;
        }
        if ("false".equalsIgnoreCase(str)) {
            return TorConfig.AutoBoolValue.FALSE;
        }
        throw new IllegalArgumentException("Could not parse AutoBool value " + str);
    }

    private List<String> parseCSV(String str) {
        ArrayList arrayList = new ArrayList();
        String[] split = str.split(Constants.ACCEPT_TIME_SEPARATOR_SP);
        int length = split.length;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length) {
                return arrayList;
            }
            arrayList.add(split[i2]);
            i = i2 + 1;
        }
    }

    private File parseFileValue(String str) {
        return str.startsWith("~/") ? new File(new File(System.getProperty("user.home")), str.substring(2)) : new File(str);
    }

    private List<Integer> parseIntegerList(String str) {
        ArrayList arrayList = new ArrayList();
        String[] split = str.split(Constants.ACCEPT_TIME_SEPARATOR_SP);
        int length = split.length;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length) {
                return arrayList;
            }
            arrayList.add(Integer.valueOf(Integer.parseInt(split[i2])));
            i = i2 + 1;
        }
    }

    private TorConfigInterval parseIntervalValue(String str) {
        return TorConfigInterval.createFrom(str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Object parseValue(String str, TorConfig.ConfigVarType configVarType) {
        List<String> list = str;
        switch (C03331.$SwitchMap$com$subgraph$orchid$TorConfig$ConfigVarType[configVarType.ordinal()]) {
            case 1:
                return Boolean.valueOf(Boolean.parseBoolean(str));
            case 2:
                return Integer.valueOf(Integer.parseInt(str));
            case 3:
                return parseIntervalValue(str);
            case 4:
                return parseFileValue(str);
            case 5:
                return parseIntegerList(str);
            case 6:
                break;
            case 7:
                list = parseCSV(str);
                break;
            case 8:
                return parseAutoBool(str);
            default:
                throw new IllegalArgumentException();
        }
        return list;
    }
}
