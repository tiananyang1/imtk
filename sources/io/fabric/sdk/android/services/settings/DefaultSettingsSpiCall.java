package io.fabric.sdk.android.services.settings;

import android.text.TextUtils;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.Logger;
import io.fabric.sdk.android.services.common.AbstractSpiCall;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.network.HttpMethod;
import io.fabric.sdk.android.services.network.HttpRequest;
import io.fabric.sdk.android.services.network.HttpRequestFactory;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes08-dex2jar.jar:io/fabric/sdk/android/services/settings/DefaultSettingsSpiCall.class */
public class DefaultSettingsSpiCall extends AbstractSpiCall implements SettingsSpiCall {
    static final String BUILD_VERSION_PARAM = "build_version";
    static final String DISPLAY_VERSION_PARAM = "display_version";
    static final String HEADER_ADVERTISING_TOKEN = "X-CRASHLYTICS-ADVERTISING-TOKEN";
    static final String HEADER_ANDROID_ID = "X-CRASHLYTICS-ANDROID-ID";
    static final String HEADER_DEVICE_MODEL = "X-CRASHLYTICS-DEVICE-MODEL";
    static final String HEADER_INSTALLATION_ID = "X-CRASHLYTICS-INSTALLATION-ID";
    static final String HEADER_OS_BUILD_VERSION = "X-CRASHLYTICS-OS-BUILD-VERSION";
    static final String HEADER_OS_DISPLAY_VERSION = "X-CRASHLYTICS-OS-DISPLAY-VERSION";
    static final String ICON_HASH = "icon_hash";
    static final String INSTANCE_PARAM = "instance";
    static final String SOURCE_PARAM = "source";

    public DefaultSettingsSpiCall(Kit kit, String str, String str2, HttpRequestFactory httpRequestFactory) {
        this(kit, str, str2, httpRequestFactory, HttpMethod.GET);
    }

    DefaultSettingsSpiCall(Kit kit, String str, String str2, HttpRequestFactory httpRequestFactory, HttpMethod httpMethod) {
        super(kit, str, str2, httpRequestFactory, httpMethod);
    }

    private HttpRequest applyHeadersTo(HttpRequest httpRequest, SettingsRequest settingsRequest) {
        applyNonNullHeader(httpRequest, AbstractSpiCall.HEADER_API_KEY, settingsRequest.apiKey);
        applyNonNullHeader(httpRequest, AbstractSpiCall.HEADER_CLIENT_TYPE, AbstractSpiCall.ANDROID_CLIENT_TYPE);
        applyNonNullHeader(httpRequest, AbstractSpiCall.HEADER_CLIENT_VERSION, this.kit.getVersion());
        applyNonNullHeader(httpRequest, "Accept", "application/json");
        applyNonNullHeader(httpRequest, HEADER_DEVICE_MODEL, settingsRequest.deviceModel);
        applyNonNullHeader(httpRequest, HEADER_OS_BUILD_VERSION, settingsRequest.osBuildVersion);
        applyNonNullHeader(httpRequest, HEADER_OS_DISPLAY_VERSION, settingsRequest.osDisplayVersion);
        applyNonNullHeader(httpRequest, HEADER_INSTALLATION_ID, settingsRequest.installationId);
        if (TextUtils.isEmpty(settingsRequest.advertisingId)) {
            applyNonNullHeader(httpRequest, HEADER_ANDROID_ID, settingsRequest.androidId);
            return httpRequest;
        }
        applyNonNullHeader(httpRequest, HEADER_ADVERTISING_TOKEN, settingsRequest.advertisingId);
        return httpRequest;
    }

    private void applyNonNullHeader(HttpRequest httpRequest, String str, String str2) {
        if (str2 != null) {
            httpRequest.header(str, str2);
        }
    }

    private JSONObject getJsonObjectFrom(String str) {
        try {
            return new JSONObject(str);
        } catch (Exception e) {
            Fabric.getLogger().mo2871d(Fabric.TAG, "Failed to parse settings JSON from " + getUrl(), e);
            Fabric.getLogger().mo2870d(Fabric.TAG, "Settings response " + str);
            return null;
        }
    }

    private Map<String, String> getQueryParamsFor(SettingsRequest settingsRequest) {
        HashMap hashMap = new HashMap();
        hashMap.put(BUILD_VERSION_PARAM, settingsRequest.buildVersion);
        hashMap.put(DISPLAY_VERSION_PARAM, settingsRequest.displayVersion);
        hashMap.put(SOURCE_PARAM, Integer.toString(settingsRequest.source));
        if (settingsRequest.iconHash != null) {
            hashMap.put(ICON_HASH, settingsRequest.iconHash);
        }
        String str = settingsRequest.instanceId;
        if (!CommonUtils.isNullOrEmpty(str)) {
            hashMap.put(INSTANCE_PARAM, str);
        }
        return hashMap;
    }

    JSONObject handleResponse(HttpRequest httpRequest) {
        int code = httpRequest.code();
        Fabric.getLogger().mo2870d(Fabric.TAG, "Settings result was: " + code);
        if (requestWasSuccessful(code)) {
            return getJsonObjectFrom(httpRequest.body());
        }
        Fabric.getLogger().mo2872e(Fabric.TAG, "Failed to retrieve settings from " + getUrl());
        return null;
    }

    @Override // io.fabric.sdk.android.services.settings.SettingsSpiCall
    public JSONObject invoke(SettingsRequest settingsRequest) {
        HttpRequest httpRequest;
        HttpRequest httpRequest2;
        JSONObject jSONObject;
        Logger logger;
        JSONObject jSONObject2;
        StringBuilder sb;
        Map<String, String> queryParamsFor;
        try {
            try {
                queryParamsFor = getQueryParamsFor(settingsRequest);
                httpRequest = getHttpRequest(queryParamsFor);
            } catch (HttpRequest.HttpRequestException e) {
                e = e;
                httpRequest2 = null;
            } catch (Throwable th) {
                th = th;
                httpRequest = null;
            }
            try {
                httpRequest2 = applyHeadersTo(httpRequest, settingsRequest);
            } catch (HttpRequest.HttpRequestException e2) {
                e = e2;
                httpRequest2 = httpRequest;
            } catch (Throwable th2) {
                th = th2;
                if (httpRequest != null) {
                    Fabric.getLogger().mo2870d(Fabric.TAG, "Settings request ID: " + httpRequest.header(AbstractSpiCall.HEADER_REQUEST_ID));
                }
                throw th;
            }
            try {
                Logger logger2 = Fabric.getLogger();
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Requesting settings from ");
                sb2.append(getUrl());
                logger2.mo2870d(Fabric.TAG, sb2.toString());
                Logger logger3 = Fabric.getLogger();
                StringBuilder sb3 = new StringBuilder();
                sb3.append("Settings query params were: ");
                sb3.append(queryParamsFor);
                logger3.mo2870d(Fabric.TAG, sb3.toString());
                jSONObject2 = handleResponse(httpRequest2);
                jSONObject = jSONObject2;
            } catch (HttpRequest.HttpRequestException e3) {
                e = e3;
                Fabric.getLogger().mo2873e(Fabric.TAG, "Settings request failed.", e);
                jSONObject = null;
                if (httpRequest2 != null) {
                    logger = Fabric.getLogger();
                    jSONObject2 = null;
                    sb = new StringBuilder();
                    sb.append("Settings request ID: ");
                    sb.append(httpRequest2.header(AbstractSpiCall.HEADER_REQUEST_ID));
                    logger.mo2870d(Fabric.TAG, sb.toString());
                    return jSONObject2;
                }
                return jSONObject;
            }
            if (httpRequest2 != null) {
                logger = Fabric.getLogger();
                sb = new StringBuilder();
                sb.append("Settings request ID: ");
                sb.append(httpRequest2.header(AbstractSpiCall.HEADER_REQUEST_ID));
                logger.mo2870d(Fabric.TAG, sb.toString());
                return jSONObject2;
            }
            return jSONObject;
        } catch (Throwable th3) {
            th = th3;
        }
    }

    boolean requestWasSuccessful(int i) {
        return i == 200 || i == 201 || i == 202 || i == 203;
    }
}
