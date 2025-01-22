package com.yqritc.scalablevideoview;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RawRes;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.TextureView;
import java.io.FileDescriptor;
import java.io.IOException;
import java.util.Map;

/* loaded from: classes08-dex2jar.jar:com/yqritc/scalablevideoview/ScalableVideoView.class */
public class ScalableVideoView extends TextureView implements TextureView.SurfaceTextureListener, MediaPlayer.OnVideoSizeChangedListener {
    protected MediaPlayer mMediaPlayer;
    protected ScalableType mScalableType;

    public ScalableVideoView(Context context) {
        this(context, null);
    }

    public ScalableVideoView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ScalableVideoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray obtainStyledAttributes;
        this.mScalableType = ScalableType.NONE;
        if (attributeSet == null || (obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0885R.styleable.scaleStyle, 0, 0)) == null) {
            return;
        }
        int i2 = obtainStyledAttributes.getInt(C0885R.styleable.scaleStyle_scalableType, ScalableType.NONE.ordinal());
        obtainStyledAttributes.recycle();
        this.mScalableType = ScalableType.values()[i2];
    }

    private void initializeMediaPlayer() {
        if (this.mMediaPlayer != null) {
            reset();
            return;
        }
        this.mMediaPlayer = new MediaPlayer();
        this.mMediaPlayer.setOnVideoSizeChangedListener(this);
        setSurfaceTextureListener(this);
    }

    private void scaleVideoSize(int i, int i2) {
        Matrix scaleMatrix;
        if (i == 0 || i2 == 0 || (scaleMatrix = new ScaleManager(new Size(getWidth(), getHeight()), new Size(i, i2)).getScaleMatrix(this.mScalableType)) == null) {
            return;
        }
        setTransform(scaleMatrix);
    }

    private void setDataSource(@NonNull AssetFileDescriptor assetFileDescriptor) throws IOException {
        setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
        assetFileDescriptor.close();
    }

    public int getCurrentPosition() {
        return this.mMediaPlayer.getCurrentPosition();
    }

    public int getDuration() {
        return this.mMediaPlayer.getDuration();
    }

    public int getVideoHeight() {
        return this.mMediaPlayer.getVideoHeight();
    }

    public int getVideoWidth() {
        return this.mMediaPlayer.getVideoWidth();
    }

    public boolean isLooping() {
        return this.mMediaPlayer.isLooping();
    }

    public boolean isPlaying() {
        return this.mMediaPlayer.isPlaying();
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mMediaPlayer == null) {
            return;
        }
        if (isPlaying()) {
            stop();
        }
        release();
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        Surface surface = new Surface(surfaceTexture);
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.setSurface(surface);
        }
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        return false;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
    public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
        scaleVideoSize(i, i2);
    }

    public void pause() {
        this.mMediaPlayer.pause();
    }

    public void prepare() throws IOException, IllegalStateException {
        prepare(null);
    }

    public void prepare(@Nullable MediaPlayer.OnPreparedListener onPreparedListener) throws IOException, IllegalStateException {
        this.mMediaPlayer.setOnPreparedListener(onPreparedListener);
        this.mMediaPlayer.prepare();
    }

    public void prepareAsync() throws IllegalStateException {
        prepareAsync(null);
    }

    public void prepareAsync(@Nullable MediaPlayer.OnPreparedListener onPreparedListener) throws IllegalStateException {
        this.mMediaPlayer.setOnPreparedListener(onPreparedListener);
        this.mMediaPlayer.prepareAsync();
    }

    public void release() {
        reset();
        this.mMediaPlayer.release();
        this.mMediaPlayer = null;
    }

    public void reset() {
        this.mMediaPlayer.reset();
    }

    public void seekTo(int i) {
        this.mMediaPlayer.seekTo(i);
    }

    public void setAssetData(@NonNull String str) throws IOException {
        setDataSource(getContext().getAssets().openFd(str));
    }

    public void setDataSource(@NonNull Context context, @NonNull Uri uri) throws IOException {
        initializeMediaPlayer();
        this.mMediaPlayer.setDataSource(context, uri);
    }

    public void setDataSource(@NonNull Context context, @NonNull Uri uri, @Nullable Map<String, String> map) throws IOException {
        initializeMediaPlayer();
        this.mMediaPlayer.setDataSource(context, uri, map);
    }

    public void setDataSource(@NonNull FileDescriptor fileDescriptor) throws IOException {
        initializeMediaPlayer();
        this.mMediaPlayer.setDataSource(fileDescriptor);
    }

    public void setDataSource(@NonNull FileDescriptor fileDescriptor, long j, long j2) throws IOException {
        initializeMediaPlayer();
        this.mMediaPlayer.setDataSource(fileDescriptor, j, j2);
    }

    public void setDataSource(@NonNull String str) throws IOException {
        initializeMediaPlayer();
        this.mMediaPlayer.setDataSource(str);
    }

    public void setLooping(boolean z) {
        this.mMediaPlayer.setLooping(z);
    }

    public void setOnCompletionListener(@Nullable MediaPlayer.OnCompletionListener onCompletionListener) {
        this.mMediaPlayer.setOnCompletionListener(onCompletionListener);
    }

    public void setOnErrorListener(@Nullable MediaPlayer.OnErrorListener onErrorListener) {
        this.mMediaPlayer.setOnErrorListener(onErrorListener);
    }

    public void setOnInfoListener(@Nullable MediaPlayer.OnInfoListener onInfoListener) {
        this.mMediaPlayer.setOnInfoListener(onInfoListener);
    }

    public void setRawData(@RawRes int i) throws IOException {
        setDataSource(getResources().openRawResourceFd(i));
    }

    public void setScalableType(ScalableType scalableType) {
        this.mScalableType = scalableType;
        scaleVideoSize(getVideoWidth(), getVideoHeight());
    }

    public void setVolume(float f, float f2) {
        this.mMediaPlayer.setVolume(f, f2);
    }

    public void start() {
        this.mMediaPlayer.start();
    }

    public void stop() {
        this.mMediaPlayer.stop();
    }
}
