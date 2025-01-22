package com.rnfs;

import com.facebook.react.bridge.ReadableMap;
import java.io.File;
import java.net.URL;
import java.util.Map;

/* loaded from: classes08-dex2jar.jar:com/rnfs/DownloadParams.class */
public class DownloadParams {
    public int connectionTimeout;
    public File dest;
    public ReadableMap headers;
    public OnDownloadBegin onDownloadBegin;
    public OnDownloadProgress onDownloadProgress;
    public OnTaskCompleted onTaskCompleted;
    public float progressDivider;
    public int readTimeout;
    public URL src;

    /* loaded from: classes08-dex2jar.jar:com/rnfs/DownloadParams$OnDownloadBegin.class */
    public interface OnDownloadBegin {
        void onDownloadBegin(int i, long j, Map<String, String> map);
    }

    /* loaded from: classes08-dex2jar.jar:com/rnfs/DownloadParams$OnDownloadProgress.class */
    public interface OnDownloadProgress {
        void onDownloadProgress(long j, long j2);
    }

    /* loaded from: classes08-dex2jar.jar:com/rnfs/DownloadParams$OnTaskCompleted.class */
    public interface OnTaskCompleted {
        void onTaskCompleted(DownloadResult downloadResult);
    }
}
