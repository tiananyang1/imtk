package com.subgraph.orchid.circuits.p002hs;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/circuits/hs/HSDescriptorCookie.class */
public class HSDescriptorCookie {
    private final CookieType type;
    private final byte[] value;

    /* renamed from: com.subgraph.orchid.circuits.hs.HSDescriptorCookie$1 */
    /* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/circuits/hs/HSDescriptorCookie$1.class */
    static /* synthetic */ class C03211 {

        /* renamed from: $SwitchMap$com$subgraph$orchid$circuits$hs$HSDescriptorCookie$CookieType */
        static final /* synthetic */ int[] f189x6a4c1817 = new int[CookieType.values().length];

        /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:8:0x0020
            	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1166)
            	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:1022)
            	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:55)
            */
        static {
            /*
                com.subgraph.orchid.circuits.hs.HSDescriptorCookie$CookieType[] r0 = com.subgraph.orchid.circuits.hs.HSDescriptorCookie.CookieType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.subgraph.orchid.circuits.p002hs.HSDescriptorCookie.C03211.f189x6a4c1817 = r0
                int[] r0 = com.subgraph.orchid.circuits.p002hs.HSDescriptorCookie.C03211.f189x6a4c1817     // Catch: java.lang.NoSuchFieldError -> L20
                com.subgraph.orchid.circuits.hs.HSDescriptorCookie$CookieType r1 = com.subgraph.orchid.circuits.hs.HSDescriptorCookie.CookieType.COOKIE_BASIC     // Catch: java.lang.NoSuchFieldError -> L20
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L20
                r2 = 1
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L20
            L14:
                int[] r0 = com.subgraph.orchid.circuits.p002hs.HSDescriptorCookie.C03211.f189x6a4c1817     // Catch: java.lang.NoSuchFieldError -> L20 java.lang.NoSuchFieldError -> L24
                com.subgraph.orchid.circuits.hs.HSDescriptorCookie$CookieType r1 = com.subgraph.orchid.circuits.hs.HSDescriptorCookie.CookieType.COOKIE_STEALTH     // Catch: java.lang.NoSuchFieldError -> L24
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
            throw new UnsupportedOperationException("Method not decompiled: com.subgraph.orchid.circuits.p002hs.HSDescriptorCookie.C03211.m3820clinit():void");
        }
    }

    /* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/circuits/hs/HSDescriptorCookie$CookieType.class */
    public enum CookieType {
        COOKIE_BASIC,
        COOKIE_STEALTH
    }

    public HSDescriptorCookie(CookieType cookieType, byte[] bArr) {
        this.type = cookieType;
        this.value = bArr;
    }

    public byte getAuthTypeByte() {
        int i = C03211.f189x6a4c1817[this.type.ordinal()];
        if (i == 1) {
            return (byte) 1;
        }
        if (i == 2) {
            return (byte) 2;
        }
        throw new IllegalStateException();
    }

    public CookieType getType() {
        return this.type;
    }

    public byte[] getValue() {
        return this.value;
    }
}
