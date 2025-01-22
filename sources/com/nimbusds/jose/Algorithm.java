package com.nimbusds.jose;

import java.io.Serializable;
import net.jcip.annotations.Immutable;
import net.minidev.json.JSONAware;
import net.minidev.json.JSONObject;

@Immutable
/* loaded from: classes08-dex2jar.jar:com/nimbusds/jose/Algorithm.class */
public class Algorithm implements JSONAware, Serializable {
    public static final Algorithm NONE = new Algorithm("none", Requirement.REQUIRED);
    private static final long serialVersionUID = 1;
    private final String name;
    private final Requirement requirement;

    public Algorithm(String str) {
        this(str, null);
    }

    public Algorithm(String str, Requirement requirement) {
        if (str == null) {
            throw new IllegalArgumentException("The algorithm name must not be null");
        }
        this.name = str;
        this.requirement = requirement;
    }

    public boolean equals(Object obj) {
        return obj != null && (obj instanceof Algorithm) && toString().equals(obj.toString());
    }

    public final String getName() {
        return this.name;
    }

    public final Requirement getRequirement() {
        return this.requirement;
    }

    public final int hashCode() {
        return this.name.hashCode();
    }

    public final String toJSONString() {
        return "\"" + JSONObject.escape(this.name) + '\"';
    }

    public final String toString() {
        return this.name;
    }
}
