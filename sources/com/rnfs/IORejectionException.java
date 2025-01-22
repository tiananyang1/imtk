package com.rnfs;

/* loaded from: classes08-dex2jar.jar:com/rnfs/IORejectionException.class */
class IORejectionException extends Exception {
    private String code;

    public IORejectionException(String str, String str2) {
        super(str2);
        this.code = str;
    }

    public String getCode() {
        return this.code;
    }
}
