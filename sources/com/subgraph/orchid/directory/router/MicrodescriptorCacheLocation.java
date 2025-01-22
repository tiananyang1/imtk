package com.subgraph.orchid.directory.router;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/directory/router/MicrodescriptorCacheLocation.class */
public class MicrodescriptorCacheLocation {
    private final int length;
    private final int offset;

    public MicrodescriptorCacheLocation(int i, int i2) {
        this.offset = i;
        this.length = i2;
    }

    public int getLength() {
        return this.length;
    }

    public int getOffset() {
        return this.offset;
    }

    public String toString() {
        return "MD Cache offset: " + this.offset + " length: " + this.length;
    }
}
