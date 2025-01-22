package com.meituan.android.walle;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import java.io.File;
import java.util.Map;

/* loaded from: classes08-dex2jar.jar:com/meituan/android/walle/WalleChannelReader.class */
public final class WalleChannelReader {
    private WalleChannelReader() {
    }

    @Nullable
    public static String get(@NonNull Context context, @NonNull String str) {
        Map<String, String> channelInfoMap = getChannelInfoMap(context);
        if (channelInfoMap == null) {
            return null;
        }
        return channelInfoMap.get(str);
    }

    @Nullable
    private static String getApkPath(@NonNull Context context) {
        try {
            ApplicationInfo applicationInfo = context.getApplicationInfo();
            if (applicationInfo == null) {
                return null;
            }
            return applicationInfo.sourceDir;
        } catch (Throwable th) {
            return null;
        }
    }

    @Nullable
    public static String getChannel(@NonNull Context context) {
        return getChannel(context, null);
    }

    @Nullable
    public static String getChannel(@NonNull Context context, @NonNull String str) {
        ChannelInfo channelInfo = getChannelInfo(context);
        return channelInfo == null ? str : channelInfo.getChannel();
    }

    @Nullable
    public static ChannelInfo getChannelInfo(@NonNull Context context) {
        String apkPath = getApkPath(context);
        if (TextUtils.isEmpty(apkPath)) {
            return null;
        }
        return ChannelReader.get(new File(apkPath));
    }

    @Nullable
    public static Map<String, String> getChannelInfoMap(@NonNull Context context) {
        String apkPath = getApkPath(context);
        if (TextUtils.isEmpty(apkPath)) {
            return null;
        }
        return ChannelReader.getMap(new File(apkPath));
    }
}
