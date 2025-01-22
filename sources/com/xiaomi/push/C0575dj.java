package com.xiaomi.push;

/* renamed from: com.xiaomi.push.dj */
/* loaded from: classes08-dex2jar.jar:com/xiaomi/push/dj.class */
/* synthetic */ class C0575dj {

    /* renamed from: a */
    static final /* synthetic */ int[] f674a = new int[EnumC0696hw.values().length];

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:78:0x00bd
        	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1166)
        	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:1022)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:55)
        */
    static {
        /*
            com.xiaomi.push.hw[] r0 = com.xiaomi.push.EnumC0696hw.values()
            int r0 = r0.length
            int[] r0 = new int[r0]
            com.xiaomi.push.C0575dj.f674a = r0
            int[] r0 = com.xiaomi.push.C0575dj.f674a     // Catch: java.lang.NoSuchFieldError -> L95
            com.xiaomi.push.hw r1 = com.xiaomi.push.EnumC0696hw.Registration     // Catch: java.lang.NoSuchFieldError -> L95
            int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L95
            r2 = 1
            r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L95
        L14:
            int[] r0 = com.xiaomi.push.C0575dj.f674a     // Catch: java.lang.NoSuchFieldError -> L95 java.lang.NoSuchFieldError -> L99
            com.xiaomi.push.hw r1 = com.xiaomi.push.EnumC0696hw.UnRegistration     // Catch: java.lang.NoSuchFieldError -> L99
            int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L99
            r2 = 2
            r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L99
        L1f:
            int[] r0 = com.xiaomi.push.C0575dj.f674a     // Catch: java.lang.NoSuchFieldError -> L99 java.lang.NoSuchFieldError -> L9d
            com.xiaomi.push.hw r1 = com.xiaomi.push.EnumC0696hw.Subscription     // Catch: java.lang.NoSuchFieldError -> L9d
            int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L9d
            r2 = 3
            r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L9d
        L2a:
            int[] r0 = com.xiaomi.push.C0575dj.f674a     // Catch: java.lang.NoSuchFieldError -> L9d java.lang.NoSuchFieldError -> La1
            com.xiaomi.push.hw r1 = com.xiaomi.push.EnumC0696hw.UnSubscription     // Catch: java.lang.NoSuchFieldError -> La1
            int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> La1
            r2 = 4
            r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> La1
        L35:
            int[] r0 = com.xiaomi.push.C0575dj.f674a     // Catch: java.lang.NoSuchFieldError -> La1 java.lang.NoSuchFieldError -> La5
            com.xiaomi.push.hw r1 = com.xiaomi.push.EnumC0696hw.SendMessage     // Catch: java.lang.NoSuchFieldError -> La5
            int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> La5
            r2 = 5
            r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> La5
        L40:
            int[] r0 = com.xiaomi.push.C0575dj.f674a     // Catch: java.lang.NoSuchFieldError -> La5 java.lang.NoSuchFieldError -> La9
            com.xiaomi.push.hw r1 = com.xiaomi.push.EnumC0696hw.AckMessage     // Catch: java.lang.NoSuchFieldError -> La9
            int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> La9
            r2 = 6
            r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> La9
        L4c:
            int[] r0 = com.xiaomi.push.C0575dj.f674a     // Catch: java.lang.NoSuchFieldError -> La9 java.lang.NoSuchFieldError -> Lad
            com.xiaomi.push.hw r1 = com.xiaomi.push.EnumC0696hw.SetConfig     // Catch: java.lang.NoSuchFieldError -> Lad
            int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> Lad
            r2 = 7
            r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> Lad
        L58:
            int[] r0 = com.xiaomi.push.C0575dj.f674a     // Catch: java.lang.NoSuchFieldError -> Lad java.lang.NoSuchFieldError -> Lb1
            com.xiaomi.push.hw r1 = com.xiaomi.push.EnumC0696hw.ReportFeedback     // Catch: java.lang.NoSuchFieldError -> Lb1
            int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> Lb1
            r2 = 8
            r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> Lb1
        L64:
            int[] r0 = com.xiaomi.push.C0575dj.f674a     // Catch: java.lang.NoSuchFieldError -> Lb1 java.lang.NoSuchFieldError -> Lb5
            com.xiaomi.push.hw r1 = com.xiaomi.push.EnumC0696hw.MultiConnectionBroadcast     // Catch: java.lang.NoSuchFieldError -> Lb5
            int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> Lb5
            r2 = 9
            r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> Lb5
        L70:
            int[] r0 = com.xiaomi.push.C0575dj.f674a     // Catch: java.lang.NoSuchFieldError -> Lb5 java.lang.NoSuchFieldError -> Lb9
            com.xiaomi.push.hw r1 = com.xiaomi.push.EnumC0696hw.MultiConnectionResult     // Catch: java.lang.NoSuchFieldError -> Lb9
            int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> Lb9
            r2 = 10
            r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> Lb9
        L7c:
            int[] r0 = com.xiaomi.push.C0575dj.f674a     // Catch: java.lang.NoSuchFieldError -> Lb9 java.lang.NoSuchFieldError -> Lbd
            com.xiaomi.push.hw r1 = com.xiaomi.push.EnumC0696hw.Notification     // Catch: java.lang.NoSuchFieldError -> Lbd
            int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> Lbd
            r2 = 11
            r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> Lbd
        L88:
            int[] r0 = com.xiaomi.push.C0575dj.f674a     // Catch: java.lang.NoSuchFieldError -> Lbd java.lang.NoSuchFieldError -> Lc1
            com.xiaomi.push.hw r1 = com.xiaomi.push.EnumC0696hw.Command     // Catch: java.lang.NoSuchFieldError -> Lc1
            int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> Lc1
            r2 = 12
            r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> Lc1
            return
        L95:
            r4 = move-exception
            goto L14
        L99:
            r4 = move-exception
            goto L1f
        L9d:
            r4 = move-exception
            goto L2a
        La1:
            r4 = move-exception
            goto L35
        La5:
            r4 = move-exception
            goto L40
        La9:
            r4 = move-exception
            goto L4c
        Lad:
            r4 = move-exception
            goto L58
        Lb1:
            r4 = move-exception
            goto L64
        Lb5:
            r4 = move-exception
            goto L70
        Lb9:
            r4 = move-exception
            goto L7c
        Lbd:
            r4 = move-exception
            goto L88
        Lc1:
            r4 = move-exception
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.C0575dj.m3973clinit():void");
    }
}
