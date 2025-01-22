package com.sensorsdata.analytics.android.sdk;

import com.xiaomi.mipush.sdk.Constants;

/* loaded from: classes08-dex2jar.jar:com/sensorsdata/analytics/android/sdk/PropertyDescription.class */
class PropertyDescription {
    public final Caller accessor;
    private final String mMutatorName;
    public final String name;
    public final Class<?> targetClass;

    public PropertyDescription(String str, Class<?> cls, Caller caller, String str2) {
        this.name = str;
        this.targetClass = cls;
        this.accessor = caller;
        this.mMutatorName = str2;
    }

    public String toString() {
        return "[PropertyDescription " + this.name + Constants.ACCEPT_TIME_SEPARATOR_SP + this.targetClass + ", " + this.accessor + "/" + this.mMutatorName + "]";
    }
}
