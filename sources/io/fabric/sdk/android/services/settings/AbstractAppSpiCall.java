package io.fabric.sdk.android.services.settings;

import android.content.res.Resources;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.KitInfo;
import io.fabric.sdk.android.Logger;
import io.fabric.sdk.android.services.common.AbstractSpiCall;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.common.ResponseParser;
import io.fabric.sdk.android.services.network.HttpMethod;
import io.fabric.sdk.android.services.network.HttpRequest;
import io.fabric.sdk.android.services.network.HttpRequestFactory;
import java.io.InputStream;
import java.util.Locale;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes08-dex2jar.jar:io/fabric/sdk/android/services/settings/AbstractAppSpiCall.class */
public abstract class AbstractAppSpiCall extends AbstractSpiCall implements AppSpiCall {
    public static final String APP_BUILD_VERSION_PARAM = "app[build_version]";
    public static final String APP_BUILT_SDK_VERSION_PARAM = "app[built_sdk_version]";
    public static final String APP_DISPLAY_VERSION_PARAM = "app[display_version]";
    public static final String APP_ICON_DATA_PARAM = "app[icon][data]";
    public static final String APP_ICON_HASH_PARAM = "app[icon][hash]";
    public static final String APP_ICON_HEIGHT_PARAM = "app[icon][height]";
    public static final String APP_ICON_PRERENDERED_PARAM = "app[icon][prerendered]";
    public static final String APP_ICON_WIDTH_PARAM = "app[icon][width]";
    public static final String APP_IDENTIFIER_PARAM = "app[identifier]";
    public static final String APP_INSTANCE_IDENTIFIER_PARAM = "app[instance_identifier]";
    public static final String APP_MIN_SDK_VERSION_PARAM = "app[minimum_sdk_version]";
    public static final String APP_NAME_PARAM = "app[name]";
    public static final String APP_SDK_MODULES_PARAM_BUILD_TYPE = "app[build][libraries][%s][type]";
    public static final String APP_SDK_MODULES_PARAM_PREFIX = "app[build][libraries][%s]";
    public static final String APP_SDK_MODULES_PARAM_VERSION = "app[build][libraries][%s][version]";
    public static final String APP_SOURCE_PARAM = "app[source]";
    static final String ICON_CONTENT_TYPE = "application/octet-stream";
    static final String ICON_FILE_NAME = "icon.png";

    public AbstractAppSpiCall(Kit kit, String str, String str2, HttpRequestFactory httpRequestFactory, HttpMethod httpMethod) {
        super(kit, str, str2, httpRequestFactory, httpMethod);
    }

    private HttpRequest applyHeadersTo(HttpRequest httpRequest, AppRequestData appRequestData) {
        return httpRequest.header(AbstractSpiCall.HEADER_API_KEY, appRequestData.apiKey).header(AbstractSpiCall.HEADER_CLIENT_TYPE, AbstractSpiCall.ANDROID_CLIENT_TYPE).header(AbstractSpiCall.HEADER_CLIENT_VERSION, this.kit.getVersion());
    }

    private HttpRequest applyMultipartDataTo(HttpRequest httpRequest, AppRequestData appRequestData) {
        HttpRequest part = httpRequest.part(APP_IDENTIFIER_PARAM, appRequestData.appId).part(APP_NAME_PARAM, appRequestData.name).part(APP_DISPLAY_VERSION_PARAM, appRequestData.displayVersion).part(APP_BUILD_VERSION_PARAM, appRequestData.buildVersion).part(APP_SOURCE_PARAM, Integer.valueOf(appRequestData.source)).part(APP_MIN_SDK_VERSION_PARAM, appRequestData.minSdkVersion).part(APP_BUILT_SDK_VERSION_PARAM, appRequestData.builtSdkVersion);
        if (!CommonUtils.isNullOrEmpty(appRequestData.instanceIdentifier)) {
            part.part(APP_INSTANCE_IDENTIFIER_PARAM, appRequestData.instanceIdentifier);
        }
        if (appRequestData.icon != null) {
            InputStream inputStream = null;
            InputStream inputStream2 = null;
            try {
                try {
                    InputStream openRawResource = this.kit.getContext().getResources().openRawResource(appRequestData.icon.iconResourceId);
                    inputStream2 = openRawResource;
                    inputStream = openRawResource;
                    part.part(APP_ICON_HASH_PARAM, appRequestData.icon.hash).part(APP_ICON_DATA_PARAM, ICON_FILE_NAME, ICON_CONTENT_TYPE, openRawResource).part(APP_ICON_WIDTH_PARAM, Integer.valueOf(appRequestData.icon.width)).part(APP_ICON_HEIGHT_PARAM, Integer.valueOf(appRequestData.icon.height));
                    inputStream = openRawResource;
                } catch (Resources.NotFoundException e) {
                    Logger logger = Fabric.getLogger();
                    InputStream inputStream3 = inputStream;
                    StringBuilder sb = new StringBuilder();
                    InputStream inputStream4 = inputStream;
                    sb.append("Failed to find app icon with resource ID: ");
                    InputStream inputStream5 = inputStream;
                    sb.append(appRequestData.icon.iconResourceId);
                    inputStream2 = inputStream;
                    logger.mo2873e(Fabric.TAG, sb.toString(), e);
                }
                CommonUtils.closeOrLog(inputStream, "Failed to close app icon InputStream.");
            } catch (Throwable th) {
                CommonUtils.closeOrLog(inputStream2, "Failed to close app icon InputStream.");
                throw th;
            }
        }
        if (appRequestData.sdkKits != null) {
            for (KitInfo kitInfo : appRequestData.sdkKits) {
                part.part(getKitVersionKey(kitInfo), kitInfo.getVersion());
                part.part(getKitBuildTypeKey(kitInfo), kitInfo.getBuildType());
            }
        }
        return part;
    }

    String getKitBuildTypeKey(KitInfo kitInfo) {
        return String.format(Locale.US, APP_SDK_MODULES_PARAM_BUILD_TYPE, kitInfo.getIdentifier());
    }

    String getKitVersionKey(KitInfo kitInfo) {
        return String.format(Locale.US, APP_SDK_MODULES_PARAM_VERSION, kitInfo.getIdentifier());
    }

    @Override // io.fabric.sdk.android.services.settings.AppSpiCall
    public boolean invoke(AppRequestData appRequestData) {
        HttpRequest applyMultipartDataTo = applyMultipartDataTo(applyHeadersTo(getHttpRequest(), appRequestData), appRequestData);
        Fabric.getLogger().mo2870d(Fabric.TAG, "Sending app info to " + getUrl());
        if (appRequestData.icon != null) {
            Fabric.getLogger().mo2870d(Fabric.TAG, "App icon hash is " + appRequestData.icon.hash);
            Fabric.getLogger().mo2870d(Fabric.TAG, "App icon size is " + appRequestData.icon.width + "x" + appRequestData.icon.height);
        }
        int code = applyMultipartDataTo.code();
        String str = HttpRequest.METHOD_POST.equals(applyMultipartDataTo.method()) ? "Create" : "Update";
        Fabric.getLogger().mo2870d(Fabric.TAG, str + " app request ID: " + applyMultipartDataTo.header(AbstractSpiCall.HEADER_REQUEST_ID));
        Fabric.getLogger().mo2870d(Fabric.TAG, "Result was " + code);
        return ResponseParser.parse(code) == 0;
    }
}
