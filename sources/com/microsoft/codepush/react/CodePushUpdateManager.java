package com.microsoft.codepush.react;

import java.io.IOException;
import org.json.JSONObject;

/* loaded from: classes08-dex2jar.jar:com/microsoft/codepush/react/CodePushUpdateManager.class */
public class CodePushUpdateManager {
    private String mDocumentsDirectory;

    public CodePushUpdateManager(String str) {
        this.mDocumentsDirectory = str;
    }

    private String getCodePushPath() {
        String appendPathComponent = CodePushUtils.appendPathComponent(getDocumentsDirectory(), "CodePush");
        String str = appendPathComponent;
        if (CodePush.isUsingTestConfiguration()) {
            str = CodePushUtils.appendPathComponent(appendPathComponent, "TestPackages");
        }
        return str;
    }

    private String getDocumentsDirectory() {
        return this.mDocumentsDirectory;
    }

    private String getDownloadFilePath() {
        return CodePushUtils.appendPathComponent(getCodePushPath(), CodePushConstants.DOWNLOAD_FILE_NAME);
    }

    private String getStatusFilePath() {
        return CodePushUtils.appendPathComponent(getCodePushPath(), CodePushConstants.STATUS_FILE);
    }

    private String getUnzippedFolderPath() {
        return CodePushUtils.appendPathComponent(getCodePushPath(), CodePushConstants.UNZIPPED_FOLDER_NAME);
    }

    public void clearUpdates() {
        FileUtils.deleteDirectoryAtPath(getCodePushPath());
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x0138 A[Catch: IOException -> 0x0160, TRY_ENTER, TryCatch #11 {IOException -> 0x0160, blocks: (B:59:0x012c, B:45:0x0138, B:48:0x0141, B:51:0x014b), top: B:58:0x012c }] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0141 A[Catch: IOException -> 0x0160, TRY_ENTER, TryCatch #11 {IOException -> 0x0160, blocks: (B:59:0x012c, B:45:0x0138, B:48:0x0141, B:51:0x014b), top: B:58:0x012c }] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x014b A[Catch: IOException -> 0x0160, TRY_ENTER, TRY_LEAVE, TryCatch #11 {IOException -> 0x0160, blocks: (B:59:0x012c, B:45:0x0138, B:48:0x0141, B:51:0x014b), top: B:58:0x012c }] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x012c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void downloadAndReplaceCurrentBundle(java.lang.String r6, java.lang.String r7) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 356
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.codepush.react.CodePushUpdateManager.downloadAndReplaceCurrentBundle(java.lang.String, java.lang.String):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:53:0x0363 A[Catch: IOException -> 0x0393, TRY_ENTER, TryCatch #3 {IOException -> 0x0393, blocks: (B:67:0x0356, B:53:0x0363, B:56:0x036d, B:59:0x0377), top: B:66:0x0356 }] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x036d A[Catch: IOException -> 0x0393, TRY_ENTER, TryCatch #3 {IOException -> 0x0393, blocks: (B:67:0x0356, B:53:0x0363, B:56:0x036d, B:59:0x0377), top: B:66:0x0356 }] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0377 A[Catch: IOException -> 0x0393, TRY_ENTER, TRY_LEAVE, TryCatch #3 {IOException -> 0x0393, blocks: (B:67:0x0356, B:53:0x0363, B:56:0x036d, B:59:0x0377), top: B:66:0x0356 }] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0356 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void downloadPackage(org.json.JSONObject r9, java.lang.String r10, com.microsoft.codepush.react.DownloadProgressCallback r11, java.lang.String r12) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 919
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.codepush.react.CodePushUpdateManager.downloadPackage(org.json.JSONObject, java.lang.String, com.microsoft.codepush.react.DownloadProgressCallback, java.lang.String):void");
    }

    public JSONObject getCurrentPackage() {
        String currentPackageHash = getCurrentPackageHash();
        if (currentPackageHash == null) {
            return null;
        }
        return getPackage(currentPackageHash);
    }

    public String getCurrentPackageBundlePath(String str) {
        JSONObject currentPackage;
        String currentPackageFolderPath = getCurrentPackageFolderPath();
        if (currentPackageFolderPath == null || (currentPackage = getCurrentPackage()) == null) {
            return null;
        }
        String optString = currentPackage.optString(CodePushConstants.RELATIVE_BUNDLE_PATH_KEY, null);
        return optString == null ? CodePushUtils.appendPathComponent(currentPackageFolderPath, str) : CodePushUtils.appendPathComponent(currentPackageFolderPath, optString);
    }

    public String getCurrentPackageFolderPath() {
        String optString = getCurrentPackageInfo().optString(CodePushConstants.CURRENT_PACKAGE_KEY, null);
        if (optString == null) {
            return null;
        }
        return getPackageFolderPath(optString);
    }

    public String getCurrentPackageHash() {
        return getCurrentPackageInfo().optString(CodePushConstants.CURRENT_PACKAGE_KEY, null);
    }

    public JSONObject getCurrentPackageInfo() {
        String statusFilePath = getStatusFilePath();
        if (!FileUtils.fileAtPathExists(statusFilePath)) {
            return new JSONObject();
        }
        try {
            return CodePushUtils.getJsonObjectFromFile(statusFilePath);
        } catch (IOException e) {
            throw new CodePushUnknownException("Error getting current package info", e);
        }
    }

    public JSONObject getPackage(String str) {
        try {
            return CodePushUtils.getJsonObjectFromFile(CodePushUtils.appendPathComponent(getPackageFolderPath(str), CodePushConstants.PACKAGE_FILE_NAME));
        } catch (IOException e) {
            return null;
        }
    }

    public String getPackageFolderPath(String str) {
        return CodePushUtils.appendPathComponent(getCodePushPath(), str);
    }

    public JSONObject getPreviousPackage() {
        String previousPackageHash = getPreviousPackageHash();
        if (previousPackageHash == null) {
            return null;
        }
        return getPackage(previousPackageHash);
    }

    public String getPreviousPackageHash() {
        return getCurrentPackageInfo().optString(CodePushConstants.PREVIOUS_PACKAGE_KEY, null);
    }

    public void installPackage(JSONObject jSONObject, boolean z) {
        String optString = jSONObject.optString("packageHash", null);
        JSONObject currentPackageInfo = getCurrentPackageInfo();
        String optString2 = currentPackageInfo.optString(CodePushConstants.CURRENT_PACKAGE_KEY, null);
        if (optString == null || !optString.equals(optString2)) {
            if (z) {
                String currentPackageFolderPath = getCurrentPackageFolderPath();
                if (currentPackageFolderPath != null) {
                    FileUtils.deleteDirectoryAtPath(currentPackageFolderPath);
                }
            } else {
                String previousPackageHash = getPreviousPackageHash();
                if (previousPackageHash != null && !previousPackageHash.equals(optString)) {
                    FileUtils.deleteDirectoryAtPath(getPackageFolderPath(previousPackageHash));
                }
                CodePushUtils.setJSONValueForKey(currentPackageInfo, CodePushConstants.PREVIOUS_PACKAGE_KEY, currentPackageInfo.optString(CodePushConstants.CURRENT_PACKAGE_KEY, null));
            }
            CodePushUtils.setJSONValueForKey(currentPackageInfo, CodePushConstants.CURRENT_PACKAGE_KEY, optString);
            updateCurrentPackageInfo(currentPackageInfo);
        }
    }

    public void rollbackPackage() {
        JSONObject currentPackageInfo = getCurrentPackageInfo();
        FileUtils.deleteDirectoryAtPath(getCurrentPackageFolderPath());
        CodePushUtils.setJSONValueForKey(currentPackageInfo, CodePushConstants.CURRENT_PACKAGE_KEY, currentPackageInfo.optString(CodePushConstants.PREVIOUS_PACKAGE_KEY, null));
        CodePushUtils.setJSONValueForKey(currentPackageInfo, CodePushConstants.PREVIOUS_PACKAGE_KEY, null);
        updateCurrentPackageInfo(currentPackageInfo);
    }

    public void updateCurrentPackageInfo(JSONObject jSONObject) {
        try {
            CodePushUtils.writeJsonToFile(jSONObject, getStatusFilePath());
        } catch (IOException e) {
            throw new CodePushUnknownException("Error updating current package info", e);
        }
    }
}
