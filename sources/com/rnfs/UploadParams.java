package com.rnfs;

import com.facebook.react.bridge.ReadableMap;
import java.net.URL;
import java.util.ArrayList;

/* loaded from: classes08-dex2jar.jar:com/rnfs/UploadParams.class */
public class UploadParams {
    public ReadableMap fields;
    public ArrayList<ReadableMap> files;
    public ReadableMap headers;
    public String method;
    public String name;
    public onUploadBegin onUploadBegin;
    public onUploadComplete onUploadComplete;
    public onUploadProgress onUploadProgress;
    public URL src;

    /* loaded from: classes08-dex2jar.jar:com/rnfs/UploadParams$onUploadBegin.class */
    public interface onUploadBegin {
        void onUploadBegin();
    }

    /* loaded from: classes08-dex2jar.jar:com/rnfs/UploadParams$onUploadComplete.class */
    public interface onUploadComplete {
        void onUploadComplete(UploadResult uploadResult);
    }

    /* loaded from: classes08-dex2jar.jar:com/rnfs/UploadParams$onUploadProgress.class */
    public interface onUploadProgress {
        void onUploadProgress(int i, int i2);
    }
}
