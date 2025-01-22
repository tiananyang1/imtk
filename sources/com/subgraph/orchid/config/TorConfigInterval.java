package com.subgraph.orchid.config;

import java.util.concurrent.TimeUnit;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/config/TorConfigInterval.class */
public class TorConfigInterval {
    private final TimeUnit timeUnit;
    private final long value;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.subgraph.orchid.config.TorConfigInterval$1 */
    /* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/config/TorConfigInterval$1.class */
    public static /* synthetic */ class C03321 {
        static final /* synthetic */ int[] $SwitchMap$java$util$concurrent$TimeUnit = new int[TimeUnit.values().length];

        /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:29:0x004d
            	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1166)
            	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:1022)
            	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:55)
            */
        static {
            /*
                java.util.concurrent.TimeUnit[] r0 = java.util.concurrent.TimeUnit.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.subgraph.orchid.config.TorConfigInterval.C03321.$SwitchMap$java$util$concurrent$TimeUnit = r0
                int[] r0 = com.subgraph.orchid.config.TorConfigInterval.C03321.$SwitchMap$java$util$concurrent$TimeUnit     // Catch: java.lang.NoSuchFieldError -> L41
                java.util.concurrent.TimeUnit r1 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch: java.lang.NoSuchFieldError -> L41
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L41
                r2 = 1
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L41
            L14:
                int[] r0 = com.subgraph.orchid.config.TorConfigInterval.C03321.$SwitchMap$java$util$concurrent$TimeUnit     // Catch: java.lang.NoSuchFieldError -> L41 java.lang.NoSuchFieldError -> L45
                java.util.concurrent.TimeUnit r1 = java.util.concurrent.TimeUnit.SECONDS     // Catch: java.lang.NoSuchFieldError -> L45
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L45
                r2 = 2
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L45
            L1f:
                int[] r0 = com.subgraph.orchid.config.TorConfigInterval.C03321.$SwitchMap$java$util$concurrent$TimeUnit     // Catch: java.lang.NoSuchFieldError -> L45 java.lang.NoSuchFieldError -> L49
                java.util.concurrent.TimeUnit r1 = java.util.concurrent.TimeUnit.MINUTES     // Catch: java.lang.NoSuchFieldError -> L49
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L49
                r2 = 3
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L49
            L2a:
                int[] r0 = com.subgraph.orchid.config.TorConfigInterval.C03321.$SwitchMap$java$util$concurrent$TimeUnit     // Catch: java.lang.NoSuchFieldError -> L49 java.lang.NoSuchFieldError -> L4d
                java.util.concurrent.TimeUnit r1 = java.util.concurrent.TimeUnit.HOURS     // Catch: java.lang.NoSuchFieldError -> L4d
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L4d
                r2 = 4
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L4d
            L35:
                int[] r0 = com.subgraph.orchid.config.TorConfigInterval.C03321.$SwitchMap$java$util$concurrent$TimeUnit     // Catch: java.lang.NoSuchFieldError -> L4d java.lang.NoSuchFieldError -> L51
                java.util.concurrent.TimeUnit r1 = java.util.concurrent.TimeUnit.DAYS     // Catch: java.lang.NoSuchFieldError -> L51
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L51
                r2 = 5
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L51
                return
            L41:
                r4 = move-exception
                goto L14
            L45:
                r4 = move-exception
                goto L1f
            L49:
                r4 = move-exception
                goto L2a
            L4d:
                r4 = move-exception
                goto L35
            L51:
                r4 = move-exception
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.subgraph.orchid.config.TorConfigInterval.C03321.m3835clinit():void");
        }
    }

    public TorConfigInterval(long j, TimeUnit timeUnit) {
        this.timeUnit = getTimeUnitFor(j, timeUnit);
        this.value = getValueFor(j, timeUnit);
    }

    private static TorConfigInterval createForValueAndUnit(long j, String str) {
        return stringMatchesUnit(str, "week") ? new TorConfigInterval(j * 7, TimeUnit.DAYS) : new TorConfigInterval(j, stringToUnit(str));
    }

    public static TorConfigInterval createFrom(String str) {
        String[] split = str.split(" ");
        long parseLong = Long.parseLong(split[0]);
        return split.length == 1 ? new TorConfigInterval(parseLong, TimeUnit.SECONDS) : createForValueAndUnit(parseLong, split[1]);
    }

    private static TimeUnit getTimeUnitFor(long j, TimeUnit timeUnit) {
        return (timeUnit == TimeUnit.NANOSECONDS || timeUnit == TimeUnit.MICROSECONDS) ? TimeUnit.MILLISECONDS : timeUnit;
    }

    private static long getValueFor(long j, TimeUnit timeUnit) {
        return (timeUnit == TimeUnit.NANOSECONDS || timeUnit == TimeUnit.MICROSECONDS) ? TimeUnit.MILLISECONDS.convert(j, timeUnit) : j;
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x002d, code lost:            if (r3.equalsIgnoreCase(r4 + "s") != false) goto L10;     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static boolean stringMatchesUnit(java.lang.String r3, java.lang.String r4) {
        /*
            r0 = 0
            r5 = r0
            r0 = r3
            if (r0 != 0) goto L8
            r0 = 0
            return r0
        L8:
            r0 = r3
            r1 = r4
            boolean r0 = r0.equalsIgnoreCase(r1)
            if (r0 != 0) goto L30
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r1 = r0
            r1.<init>()
            r6 = r0
            r0 = r6
            r1 = r4
            java.lang.StringBuilder r0 = r0.append(r1)
            r0 = r6
            java.lang.String r1 = "s"
            java.lang.StringBuilder r0 = r0.append(r1)
            r0 = r3
            r1 = r6
            java.lang.String r1 = r1.toString()
            boolean r0 = r0.equalsIgnoreCase(r1)
            if (r0 == 0) goto L32
        L30:
            r0 = 1
            r5 = r0
        L32:
            r0 = r5
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.subgraph.orchid.config.TorConfigInterval.stringMatchesUnit(java.lang.String, java.lang.String):boolean");
    }

    private static TimeUnit stringToUnit(String str) {
        if (stringMatchesUnit(str, "day")) {
            return TimeUnit.DAYS;
        }
        if (stringMatchesUnit(str, "hour")) {
            return TimeUnit.HOURS;
        }
        if (stringMatchesUnit(str, "minute")) {
            return TimeUnit.MINUTES;
        }
        if (stringMatchesUnit(str, "second")) {
            return TimeUnit.SECONDS;
        }
        if (stringMatchesUnit(str, "millisecond")) {
            return TimeUnit.MILLISECONDS;
        }
        throw new IllegalArgumentException();
    }

    private static String unitToString(TimeUnit timeUnit) {
        int i = C03321.$SwitchMap$java$util$concurrent$TimeUnit[timeUnit.ordinal()];
        if (i == 1) {
            return "millisecond";
        }
        if (i == 2) {
            return "second";
        }
        if (i == 3) {
            return "minute";
        }
        if (i == 4) {
            return "hour";
        }
        if (i == 5) {
            return "days";
        }
        throw new IllegalArgumentException();
    }

    public long getMilliseconds() {
        return TimeUnit.MILLISECONDS.convert(this.value, this.timeUnit);
    }

    public String toString() {
        if (this.timeUnit == TimeUnit.DAYS) {
            long j = this.value;
            if (j % 7 == 0) {
                long j2 = j / 7;
                if (j2 == 1) {
                    return "1 week";
                }
                return j2 + " weeks";
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.value);
        sb.append(" ");
        sb.append(unitToString(this.timeUnit));
        if (this.value != 1) {
            sb.append("s");
        }
        return sb.toString();
    }
}
