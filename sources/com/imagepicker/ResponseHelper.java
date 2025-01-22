package com.imagepicker;

import android.support.annotation.NonNull;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.WritableMap;

/* loaded from: classes08-dex2jar.jar:com/imagepicker/ResponseHelper.class */
public class ResponseHelper {
    private WritableMap response = Arguments.createMap();

    public void cleanResponse() {
        this.response = Arguments.createMap();
    }

    @NonNull
    public WritableMap getResponse() {
        return this.response;
    }

    public void invokeCancel(@NonNull Callback callback) {
        cleanResponse();
        this.response.putBoolean("didCancel", true);
        invokeResponse(callback);
    }

    public void invokeCustomButton(@NonNull Callback callback, @NonNull String str) {
        cleanResponse();
        this.response.putString("customButton", str);
        invokeResponse(callback);
    }

    public void invokeError(@NonNull Callback callback, @NonNull String str) {
        cleanResponse();
        this.response.putString("error", str);
        invokeResponse(callback);
    }

    public void invokeResponse(@NonNull Callback callback) {
        callback.invoke(new Object[]{this.response});
    }

    public void putBoolean(@NonNull String str, boolean z) {
        this.response.putBoolean(str, z);
    }

    public void putDouble(@NonNull String str, double d) {
        this.response.putDouble(str, d);
    }

    public void putInt(@NonNull String str, int i) {
        this.response.putInt(str, i);
    }

    public void putString(@NonNull String str, @NonNull String str2) {
        this.response.putString(str, str2);
    }
}
