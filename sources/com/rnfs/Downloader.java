package com.rnfs;

import android.os.AsyncTask;
import android.os.Build;
import java.net.HttpURLConnection;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes08-dex2jar.jar:com/rnfs/Downloader.class */
public class Downloader extends AsyncTask<DownloadParams, long[], DownloadResult> {
    private AtomicBoolean mAbort = new AtomicBoolean(false);
    private DownloadParams mParam;
    DownloadResult res;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0375  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x037e  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0387  */
    /* JADX WARN: Type inference failed for: r0v9, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r1v21, types: [long[], java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r1v41, types: [long[], java.lang.Object[]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void download(com.rnfs.DownloadParams r11, com.rnfs.DownloadResult r12) throws java.lang.Exception {
        /*
            Method dump skipped, instructions count: 940
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.rnfs.Downloader.download(com.rnfs.DownloadParams, com.rnfs.DownloadResult):void");
    }

    private long getContentLength(HttpURLConnection httpURLConnection) {
        return Build.VERSION.SDK_INT >= 24 ? httpURLConnection.getContentLengthLong() : httpURLConnection.getContentLength();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public DownloadResult doInBackground(DownloadParams... downloadParamsArr) {
        this.mParam = downloadParamsArr[0];
        this.res = new DownloadResult();
        new Thread(new Runnable() { // from class: com.rnfs.Downloader.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Downloader.this.download(Downloader.this.mParam, Downloader.this.res);
                    Downloader.this.mParam.onTaskCompleted.onTaskCompleted(Downloader.this.res);
                } catch (Exception e) {
                    Downloader.this.res.exception = e;
                    Downloader.this.mParam.onTaskCompleted.onTaskCompleted(Downloader.this.res);
                }
            }
        }).start();
        return this.res;
    }

    protected void onPostExecute(Exception exc) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onProgressUpdate(long[]... jArr) {
        super.onProgressUpdate((Object[]) jArr);
        this.mParam.onDownloadProgress.onDownloadProgress(jArr[0][0], jArr[0][1]);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void stop() {
        this.mAbort.set(true);
    }
}
