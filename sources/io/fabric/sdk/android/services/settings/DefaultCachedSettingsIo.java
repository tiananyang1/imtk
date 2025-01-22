package io.fabric.sdk.android.services.settings;

import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.persistence.FileStoreImpl;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes08-dex2jar.jar:io/fabric/sdk/android/services/settings/DefaultCachedSettingsIo.class */
public class DefaultCachedSettingsIo implements CachedSettingsIo {
    private final Kit kit;

    public DefaultCachedSettingsIo(Kit kit) {
        this.kit = kit;
    }

    @Override // io.fabric.sdk.android.services.settings.CachedSettingsIo
    public JSONObject readCachedSettings() {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2;
        JSONObject jSONObject;
        Fabric.getLogger().mo2870d(Fabric.TAG, "Reading cached settings...");
        FileInputStream fileInputStream3 = null;
        try {
            File file = new File(new FileStoreImpl(this.kit).getFilesDir(), Settings.SETTINGS_CACHE_FILENAME);
            if (file.exists()) {
                fileInputStream2 = new FileInputStream(file);
                fileInputStream = fileInputStream2;
                try {
                    try {
                        fileInputStream3 = fileInputStream2;
                        jSONObject = new JSONObject(CommonUtils.streamToString(fileInputStream2));
                    } catch (Throwable th) {
                        th = th;
                        CommonUtils.closeOrLog(fileInputStream, "Error while closing settings cache file.");
                        throw th;
                    }
                } catch (Exception e) {
                    e = e;
                    fileInputStream = fileInputStream2;
                    Fabric.getLogger().mo2873e(Fabric.TAG, "Failed to fetch cached settings", e);
                    CommonUtils.closeOrLog(fileInputStream2, "Error while closing settings cache file.");
                    return null;
                }
            } else {
                Fabric.getLogger().mo2870d(Fabric.TAG, "No cached settings found.");
                jSONObject = null;
            }
            CommonUtils.closeOrLog(fileInputStream3, "Error while closing settings cache file.");
            return jSONObject;
        } catch (Exception e2) {
            e = e2;
            fileInputStream2 = null;
        } catch (Throwable th2) {
            th = th2;
            fileInputStream = null;
            CommonUtils.closeOrLog(fileInputStream, "Error while closing settings cache file.");
            throw th;
        }
    }

    @Override // io.fabric.sdk.android.services.settings.CachedSettingsIo
    public void writeCachedSettings(long j, JSONObject jSONObject) {
        FileWriter fileWriter;
        Fabric.getLogger().mo2870d(Fabric.TAG, "Writing settings to cache file...");
        if (jSONObject == null) {
            return;
        }
        FileWriter fileWriter2 = null;
        try {
            try {
                jSONObject.put(SettingsJsonConstants.EXPIRES_AT_KEY, j);
                FileWriter fileWriter3 = new FileWriter(new File(new FileStoreImpl(this.kit).getFilesDir(), Settings.SETTINGS_CACHE_FILENAME));
                try {
                    fileWriter3.write(jSONObject.toString());
                    fileWriter3.flush();
                    CommonUtils.closeOrLog(fileWriter3, "Failed to close settings writer.");
                } catch (Exception e) {
                    fileWriter = fileWriter3;
                    e = e;
                    Fabric.getLogger().mo2873e(Fabric.TAG, "Failed to cache settings", e);
                    CommonUtils.closeOrLog(fileWriter, "Failed to close settings writer.");
                } catch (Throwable th) {
                    th = th;
                    fileWriter2 = fileWriter3;
                    CommonUtils.closeOrLog(fileWriter2, "Failed to close settings writer.");
                    throw th;
                }
            } catch (Exception e2) {
                e = e2;
                fileWriter = null;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
