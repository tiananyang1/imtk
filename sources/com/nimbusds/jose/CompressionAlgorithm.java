package com.nimbusds.jose;

import java.io.Serializable;
import net.jcip.annotations.Immutable;
import net.minidev.json.JSONAware;
import net.minidev.json.JSONObject;

@Immutable
/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/CompressionAlgorithm.class */
public final class CompressionAlgorithm implements JSONAware, Serializable {
    public static final CompressionAlgorithm DEF = new CompressionAlgorithm("DEF");
    private static final long serialVersionUID = 1;
    private final String name;

    public CompressionAlgorithm(String str) {
        if (str == null) {
            throw new IllegalArgumentException("The compression algorithm name must not be null");
        }
        this.name = str;
    }

    public boolean equals(Object obj) {
        return obj != null && (obj instanceof CompressionAlgorithm) && toString().equals(obj.toString());
    }

    public String getName() {
        return this.name;
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    public String toJSONString() {
        return "\"" + JSONObject.escape(this.name) + '\"';
    }

    public String toString() {
        return this.name;
    }
}
