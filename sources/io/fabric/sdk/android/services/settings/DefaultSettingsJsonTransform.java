package io.fabric.sdk.android.services.settings;

import io.fabric.sdk.android.services.common.CurrentTimeProvider;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes08-dex2jar.jar:io/fabric/sdk/android/services/settings/DefaultSettingsJsonTransform.class */
class DefaultSettingsJsonTransform implements SettingsJsonTransform {
    private AnalyticsSettingsData buildAnalyticsSessionDataFrom(JSONObject jSONObject) {
        return new AnalyticsSettingsData(jSONObject.optString("url", SettingsJsonConstants.ANALYTICS_URL_DEFAULT), jSONObject.optInt(SettingsJsonConstants.ANALYTICS_FLUSH_INTERVAL_SECS_KEY, SettingsJsonConstants.ANALYTICS_FLUSH_INTERVAL_SECS_DEFAULT), jSONObject.optInt(SettingsJsonConstants.ANALYTICS_MAX_BYTE_SIZE_PER_FILE_KEY, 8000), jSONObject.optInt(SettingsJsonConstants.ANALYTICS_MAX_FILE_COUNT_PER_SEND_KEY, 1), jSONObject.optInt(SettingsJsonConstants.ANALYTICS_MAX_PENDING_SEND_FILE_COUNT_KEY, 100), jSONObject.optBoolean(SettingsJsonConstants.ANALYTICS_FORWARD_TO_GOOGLE_ANALYTICS_KEY, false), jSONObject.optBoolean(SettingsJsonConstants.ANALYTICS_INCLUDE_PURCHASE_EVENTS_IN_FORWARDED_EVENTS_KEY, false), jSONObject.optBoolean(SettingsJsonConstants.ANALYTICS_TRACK_CUSTOM_EVENTS_KEY, true), jSONObject.optBoolean(SettingsJsonConstants.ANALYTICS_TRACK_PREDEFINED_EVENTS_KEY, true), jSONObject.optInt(SettingsJsonConstants.ANALYTICS_SAMPLING_RATE_KEY, 1), jSONObject.optBoolean(SettingsJsonConstants.ANALYTICS_FLUSH_ON_BACKGROUND_KEY, true));
    }

    private AppSettingsData buildAppDataFrom(JSONObject jSONObject) throws JSONException {
        return new AppSettingsData(jSONObject.getString(SettingsJsonConstants.APP_IDENTIFIER_KEY), jSONObject.getString(SettingsJsonConstants.APP_STATUS_KEY), jSONObject.getString("url"), jSONObject.getString(SettingsJsonConstants.APP_REPORTS_URL_KEY), jSONObject.getString(SettingsJsonConstants.APP_NDK_REPORTS_URL_KEY), jSONObject.optBoolean(SettingsJsonConstants.APP_UPDATE_REQUIRED_KEY, false), (jSONObject.has(SettingsJsonConstants.APP_ICON_KEY) && jSONObject.getJSONObject(SettingsJsonConstants.APP_ICON_KEY).has("hash")) ? buildIconDataFrom(jSONObject.getJSONObject(SettingsJsonConstants.APP_ICON_KEY)) : null);
    }

    private BetaSettingsData buildBetaSettingsDataFrom(JSONObject jSONObject) throws JSONException {
        return new BetaSettingsData(jSONObject.optString(SettingsJsonConstants.BETA_UPDATE_ENDPOINT, SettingsJsonConstants.BETA_UPDATE_ENDPOINT_DEFAULT), jSONObject.optInt(SettingsJsonConstants.BETA_UPDATE_SUSPEND_DURATION, 3600));
    }

    private FeaturesSettingsData buildFeaturesSessionDataFrom(JSONObject jSONObject) {
        return new FeaturesSettingsData(jSONObject.optBoolean(SettingsJsonConstants.FEATURES_PROMPT_ENABLED_KEY, false), jSONObject.optBoolean(SettingsJsonConstants.FEATURES_COLLECT_LOGGED_EXCEPTIONS_KEY, true), jSONObject.optBoolean(SettingsJsonConstants.FEATURES_COLLECT_REPORTS_KEY, true), jSONObject.optBoolean(SettingsJsonConstants.FEATURES_COLLECT_ANALYTICS_KEY, false), jSONObject.optBoolean(SettingsJsonConstants.FEATURES_FIREBASE_CRASHLYTICS_ENABLED_KEY, false));
    }

    private AppIconSettingsData buildIconDataFrom(JSONObject jSONObject) throws JSONException {
        return new AppIconSettingsData(jSONObject.getString("hash"), jSONObject.getInt(SettingsJsonConstants.ICON_WIDTH_KEY), jSONObject.getInt(SettingsJsonConstants.ICON_HEIGHT_KEY));
    }

    private PromptSettingsData buildPromptDataFrom(JSONObject jSONObject) throws JSONException {
        return new PromptSettingsData(jSONObject.optString(SettingsJsonConstants.PROMPT_TITLE_KEY, SettingsJsonConstants.PROMPT_TITLE_DEFAULT), jSONObject.optString(SettingsJsonConstants.PROMPT_MESSAGE_KEY, SettingsJsonConstants.PROMPT_MESSAGE_DEFAULT), jSONObject.optString(SettingsJsonConstants.PROMPT_SEND_BUTTON_TITLE_KEY, SettingsJsonConstants.PROMPT_SEND_BUTTON_TITLE_DEFAULT), jSONObject.optBoolean(SettingsJsonConstants.PROMPT_SHOW_CANCEL_BUTTON_KEY, true), jSONObject.optString(SettingsJsonConstants.PROMPT_CANCEL_BUTTON_TITLE_KEY, SettingsJsonConstants.PROMPT_CANCEL_BUTTON_TITLE_DEFAULT), jSONObject.optBoolean(SettingsJsonConstants.PROMPT_SHOW_ALWAYS_SEND_BUTTON_KEY, true), jSONObject.optString(SettingsJsonConstants.PROMPT_ALWAYS_SEND_BUTTON_TITLE_KEY, SettingsJsonConstants.PROMPT_ALWAYS_SEND_BUTTON_TITLE_DEFAULT));
    }

    private SessionSettingsData buildSessionDataFrom(JSONObject jSONObject) throws JSONException {
        return new SessionSettingsData(jSONObject.optInt(SettingsJsonConstants.SETTINGS_LOG_BUFFER_SIZE_KEY, SettingsJsonConstants.SETTINGS_LOG_BUFFER_SIZE_DEFAULT), jSONObject.optInt(SettingsJsonConstants.SETTINGS_MAX_CHAINED_EXCEPTION_DEPTH_KEY, 8), jSONObject.optInt(SettingsJsonConstants.SETTINGS_MAX_CUSTOM_EXCEPTION_EVENTS_KEY, 64), jSONObject.optInt(SettingsJsonConstants.SETTINGS_MAX_CUSTOM_KEY_VALUE_PAIRS_KEY, 64), jSONObject.optInt(SettingsJsonConstants.SETTINGS_IDENTIFIER_MASK_KEY, 255), jSONObject.optBoolean(SettingsJsonConstants.SETTINGS_SEND_SESSION_WITHOUT_CRASH_KEY, false), jSONObject.optInt(SettingsJsonConstants.SETTINGS_MAX_COMPLETE_SESSIONS_COUNT_KEY, 4));
    }

    private long getExpiresAtFrom(CurrentTimeProvider currentTimeProvider, long j, JSONObject jSONObject) throws JSONException {
        return jSONObject.has(SettingsJsonConstants.EXPIRES_AT_KEY) ? jSONObject.getLong(SettingsJsonConstants.EXPIRES_AT_KEY) : currentTimeProvider.getCurrentTimeMillis() + (j * 1000);
    }

    private JSONObject toAnalyticsJson(AnalyticsSettingsData analyticsSettingsData) throws JSONException {
        return new JSONObject().put("url", analyticsSettingsData.analyticsURL).put(SettingsJsonConstants.ANALYTICS_FLUSH_INTERVAL_SECS_KEY, analyticsSettingsData.flushIntervalSeconds).put(SettingsJsonConstants.ANALYTICS_MAX_BYTE_SIZE_PER_FILE_KEY, analyticsSettingsData.maxByteSizePerFile).put(SettingsJsonConstants.ANALYTICS_MAX_FILE_COUNT_PER_SEND_KEY, analyticsSettingsData.maxFileCountPerSend).put(SettingsJsonConstants.ANALYTICS_MAX_PENDING_SEND_FILE_COUNT_KEY, analyticsSettingsData.maxPendingSendFileCount);
    }

    private JSONObject toAppJson(AppSettingsData appSettingsData) throws JSONException {
        JSONObject put = new JSONObject().put(SettingsJsonConstants.APP_IDENTIFIER_KEY, appSettingsData.identifier).put(SettingsJsonConstants.APP_STATUS_KEY, appSettingsData.status).put("url", appSettingsData.url).put(SettingsJsonConstants.APP_REPORTS_URL_KEY, appSettingsData.reportsUrl).put(SettingsJsonConstants.APP_NDK_REPORTS_URL_KEY, appSettingsData.ndkReportsUrl).put(SettingsJsonConstants.APP_UPDATE_REQUIRED_KEY, appSettingsData.updateRequired);
        if (appSettingsData.icon != null) {
            put.put(SettingsJsonConstants.APP_ICON_KEY, toIconJson(appSettingsData.icon));
        }
        return put;
    }

    private JSONObject toBetaJson(BetaSettingsData betaSettingsData) throws JSONException {
        return new JSONObject().put(SettingsJsonConstants.BETA_UPDATE_ENDPOINT, betaSettingsData.updateUrl).put(SettingsJsonConstants.BETA_UPDATE_SUSPEND_DURATION, betaSettingsData.updateSuspendDurationSeconds);
    }

    private JSONObject toFeaturesJson(FeaturesSettingsData featuresSettingsData) throws JSONException {
        return new JSONObject().put(SettingsJsonConstants.FEATURES_COLLECT_LOGGED_EXCEPTIONS_KEY, featuresSettingsData.collectLoggedException).put(SettingsJsonConstants.FEATURES_COLLECT_REPORTS_KEY, featuresSettingsData.collectReports).put(SettingsJsonConstants.FEATURES_COLLECT_ANALYTICS_KEY, featuresSettingsData.collectAnalytics);
    }

    private JSONObject toIconJson(AppIconSettingsData appIconSettingsData) throws JSONException {
        return new JSONObject().put("hash", appIconSettingsData.hash).put(SettingsJsonConstants.ICON_WIDTH_KEY, appIconSettingsData.width).put(SettingsJsonConstants.ICON_HEIGHT_KEY, appIconSettingsData.height);
    }

    private JSONObject toPromptJson(PromptSettingsData promptSettingsData) throws JSONException {
        return new JSONObject().put(SettingsJsonConstants.PROMPT_TITLE_KEY, promptSettingsData.title).put(SettingsJsonConstants.PROMPT_MESSAGE_KEY, promptSettingsData.message).put(SettingsJsonConstants.PROMPT_SEND_BUTTON_TITLE_KEY, promptSettingsData.sendButtonTitle).put(SettingsJsonConstants.PROMPT_SHOW_CANCEL_BUTTON_KEY, promptSettingsData.showCancelButton).put(SettingsJsonConstants.PROMPT_CANCEL_BUTTON_TITLE_KEY, promptSettingsData.cancelButtonTitle).put(SettingsJsonConstants.PROMPT_SHOW_ALWAYS_SEND_BUTTON_KEY, promptSettingsData.showAlwaysSendButton).put(SettingsJsonConstants.PROMPT_ALWAYS_SEND_BUTTON_TITLE_KEY, promptSettingsData.alwaysSendButtonTitle);
    }

    private JSONObject toSessionJson(SessionSettingsData sessionSettingsData) throws JSONException {
        return new JSONObject().put(SettingsJsonConstants.SETTINGS_LOG_BUFFER_SIZE_KEY, sessionSettingsData.logBufferSize).put(SettingsJsonConstants.SETTINGS_MAX_CHAINED_EXCEPTION_DEPTH_KEY, sessionSettingsData.maxChainedExceptionDepth).put(SettingsJsonConstants.SETTINGS_MAX_CUSTOM_EXCEPTION_EVENTS_KEY, sessionSettingsData.maxCustomExceptionEvents).put(SettingsJsonConstants.SETTINGS_MAX_CUSTOM_KEY_VALUE_PAIRS_KEY, sessionSettingsData.maxCustomKeyValuePairs).put(SettingsJsonConstants.SETTINGS_IDENTIFIER_MASK_KEY, sessionSettingsData.identifierMask).put(SettingsJsonConstants.SETTINGS_SEND_SESSION_WITHOUT_CRASH_KEY, sessionSettingsData.sendSessionWithoutCrash);
    }

    @Override // io.fabric.sdk.android.services.settings.SettingsJsonTransform
    public SettingsData buildFromJson(CurrentTimeProvider currentTimeProvider, JSONObject jSONObject) throws JSONException {
        int optInt = jSONObject.optInt(SettingsJsonConstants.SETTINGS_VERSION, 0);
        int optInt2 = jSONObject.optInt(SettingsJsonConstants.CACHE_DURATION_KEY, 3600);
        return new SettingsData(getExpiresAtFrom(currentTimeProvider, optInt2, jSONObject), buildAppDataFrom(jSONObject.getJSONObject(SettingsJsonConstants.APP_KEY)), buildSessionDataFrom(jSONObject.getJSONObject(SettingsJsonConstants.SESSION_KEY)), buildPromptDataFrom(jSONObject.getJSONObject(SettingsJsonConstants.PROMPT_KEY)), buildFeaturesSessionDataFrom(jSONObject.getJSONObject(SettingsJsonConstants.FEATURES_KEY)), buildAnalyticsSessionDataFrom(jSONObject.getJSONObject(SettingsJsonConstants.ANALYTICS_KEY)), buildBetaSettingsDataFrom(jSONObject.getJSONObject(SettingsJsonConstants.BETA_KEY)), optInt, optInt2);
    }

    @Override // io.fabric.sdk.android.services.settings.SettingsJsonTransform
    public JSONObject toJson(SettingsData settingsData) throws JSONException {
        return new JSONObject().put(SettingsJsonConstants.EXPIRES_AT_KEY, settingsData.expiresAtMillis).put(SettingsJsonConstants.CACHE_DURATION_KEY, settingsData.cacheDuration).put(SettingsJsonConstants.SETTINGS_VERSION, settingsData.settingsVersion).put(SettingsJsonConstants.FEATURES_KEY, toFeaturesJson(settingsData.featuresData)).put(SettingsJsonConstants.ANALYTICS_KEY, toAnalyticsJson(settingsData.analyticsSettingsData)).put(SettingsJsonConstants.BETA_KEY, toBetaJson(settingsData.betaSettingsData)).put(SettingsJsonConstants.APP_KEY, toAppJson(settingsData.appData)).put(SettingsJsonConstants.SESSION_KEY, toSessionJson(settingsData.sessionData)).put(SettingsJsonConstants.PROMPT_KEY, toPromptJson(settingsData.promptData));
    }
}
