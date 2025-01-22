package io.fabric.sdk.android.services.common;

/* loaded from: classes08-dex2jar.jar:io/fabric/sdk/android/services/common/DeliveryMechanism.class */
public enum DeliveryMechanism {
    DEVELOPER(1),
    USER_SIDELOAD(2),
    TEST_DISTRIBUTION(3),
    APP_STORE(4);

    public static final String BETA_APP_PACKAGE_NAME = "io.crash.air";

    /* renamed from: id */
    private final int f2758id;

    DeliveryMechanism(int i) {
        this.f2758id = i;
    }

    public static DeliveryMechanism determineFrom(String str) {
        return BETA_APP_PACKAGE_NAME.equals(str) ? TEST_DISTRIBUTION : str != null ? APP_STORE : DEVELOPER;
    }

    public int getId() {
        return this.f2758id;
    }

    @Override // java.lang.Enum
    public String toString() {
        return Integer.toString(this.f2758id);
    }
}
