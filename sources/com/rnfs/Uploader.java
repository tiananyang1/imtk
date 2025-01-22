package com.rnfs;

import android.os.AsyncTask;
import android.webkit.MimeTypeMap;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes08-dex2jar.jar:com/rnfs/Uploader.class */
public class Uploader extends AsyncTask<UploadParams, int[], UploadResult> {
    private AtomicBoolean mAbort = new AtomicBoolean(false);
    private UploadParams mParams;
    private UploadResult res;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't wrap try/catch for region: R(5:(10:84|85|86|(6:87|88|89|90|91|92)|93|94|95|(1:97)|98|99)|81|80|82|79) */
    /* JADX WARN: Code restructure failed: missing block: B:194:0x0616, code lost:            r7 = th;     */
    /* JADX WARN: Code restructure failed: missing block: B:195:0x0617, code lost:            r22 = r0;     */
    /* JADX WARN: Code restructure failed: missing block: B:196:0x0622, code lost:            r8 = null;        r20 = null;        r21 = null;     */
    /* JADX WARN: Removed duplicated region for block: B:110:0x063e  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0648  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0651  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x065a  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x02ff A[Catch: all -> 0x0396, TRY_LEAVE, TryCatch #2 {all -> 0x0396, blocks: (B:85:0x0204, B:93:0x0261, B:95:0x02f1, B:97:0x02ff, B:98:0x030d, B:102:0x0238), top: B:84:0x0204 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void upload(com.rnfs.UploadParams r7, com.rnfs.UploadResult r8) throws java.lang.Exception {
        /*
            Method dump skipped, instructions count: 1646
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.rnfs.Uploader.upload(com.rnfs.UploadParams, com.rnfs.UploadResult):void");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public UploadResult doInBackground(UploadParams... uploadParamsArr) {
        this.mParams = uploadParamsArr[0];
        this.res = new UploadResult();
        new Thread(new Runnable() { // from class: com.rnfs.Uploader.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Uploader.this.upload(Uploader.this.mParams, Uploader.this.res);
                    Uploader.this.mParams.onUploadComplete.onUploadComplete(Uploader.this.res);
                } catch (Exception e) {
                    Uploader.this.res.exception = e;
                    Uploader.this.mParams.onUploadComplete.onUploadComplete(Uploader.this.res);
                }
            }
        }).start();
        return this.res;
    }

    protected String getMimeType(String str) {
        String fileExtensionFromUrl = MimeTypeMap.getFileExtensionFromUrl(str);
        String mimeTypeFromExtension = fileExtensionFromUrl != null ? MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtensionFromUrl.toLowerCase()) : null;
        String str2 = mimeTypeFromExtension;
        if (mimeTypeFromExtension == null) {
            str2 = "*/*";
        }
        return str2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void stop() {
        this.mAbort.set(true);
    }
}
