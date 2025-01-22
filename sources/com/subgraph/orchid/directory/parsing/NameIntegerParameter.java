package com.subgraph.orchid.directory.parsing;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/directory/parsing/NameIntegerParameter.class */
public class NameIntegerParameter {
    private final String name;
    private final int value;

    public NameIntegerParameter(String str, int i) {
        this.name = str;
        this.value = i;
    }

    public String getName() {
        return this.name;
    }

    public int getValue() {
        return this.value;
    }

    public String toString() {
        return this.name + "=" + this.value;
    }
}
