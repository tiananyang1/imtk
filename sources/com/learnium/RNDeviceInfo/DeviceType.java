package com.learnium.RNDeviceInfo;

/* loaded from: classes08-dex2jar.jar:com/learnium/RNDeviceInfo/DeviceType.class */
public enum DeviceType {
    HANDSET("Handset"),
    TABLET("Tablet"),
    TV("Tv"),
    UNKNOWN("Unknown");

    private final String value;

    DeviceType(String str) {
        this.value = str;
    }

    public String getValue() {
        return this.value;
    }
}
