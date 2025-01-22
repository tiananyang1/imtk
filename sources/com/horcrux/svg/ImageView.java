package com.horcrux.svg;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import com.facebook.common.executors.UiThreadImmediateExecutorService;
import com.facebook.common.logging.FLog;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableBitmap;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.imagehelper.ImageSource;
import com.facebook.react.views.imagehelper.ResourceDrawableIdHelper;
import com.microsoft.codepush.react.CodePushConstants;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes08-dex2jar.jar:com/horcrux/svg/ImageView.class */
class ImageView extends RenderableView {
    private String mAlign;

    /* renamed from: mH */
    private SVGLength f40mH;
    private int mImageHeight;
    private int mImageWidth;
    private final AtomicBoolean mLoading;
    private int mMeetOrSlice;

    /* renamed from: mW */
    private SVGLength f41mW;

    /* renamed from: mX */
    private SVGLength f42mX;

    /* renamed from: mY */
    private SVGLength f43mY;
    private String uriString;

    public ImageView(ReactContext reactContext) {
        super(reactContext);
        this.mLoading = new AtomicBoolean(false);
    }

    private void doRender(Canvas canvas, Paint paint, Bitmap bitmap, float f) {
        if (this.mImageWidth == 0 || this.mImageHeight == 0) {
            this.mImageWidth = bitmap.getWidth();
            this.mImageHeight = bitmap.getHeight();
        }
        RectF rect = getRect();
        RectF rectF = new RectF(0.0f, 0.0f, this.mImageWidth, this.mImageHeight);
        ViewBox.getTransform(rectF, rect, this.mAlign, this.mMeetOrSlice).mapRect(rectF);
        canvas.clipPath(getPath(canvas, paint));
        Path clipPath = getClipPath(canvas, paint);
        if (clipPath != null) {
            canvas.clipPath(clipPath);
        }
        Paint paint2 = new Paint();
        paint2.setAlpha((int) (f * 255.0f));
        canvas.drawBitmap(bitmap, (Rect) null, rectF, paint2);
        canvas.getMatrix().mapRect(rectF);
        setClientRect(rectF);
    }

    @Nonnull
    private RectF getRect() {
        double relativeOnWidth = relativeOnWidth(this.f42mX);
        double relativeOnHeight = relativeOnHeight(this.f43mY);
        double relativeOnWidth2 = relativeOnWidth(this.f41mW);
        double relativeOnHeight2 = relativeOnHeight(this.f40mH);
        double d = relativeOnWidth2;
        if (relativeOnWidth2 == 0.0d) {
            d = this.mImageWidth * this.mScale;
        }
        double d2 = relativeOnHeight2;
        if (relativeOnHeight2 == 0.0d) {
            d2 = this.mImageHeight * this.mScale;
        }
        return new RectF((float) relativeOnWidth, (float) relativeOnHeight, (float) (relativeOnWidth + d), (float) (relativeOnHeight + d2));
    }

    private void loadBitmap(ImagePipeline imagePipeline, ImageRequest imageRequest) {
        this.mLoading.set(true);
        imagePipeline.fetchDecodedImage(imageRequest, this.mContext).subscribe(new BaseBitmapDataSubscriber() { // from class: com.horcrux.svg.ImageView.1
            public void onFailureImpl(DataSource dataSource) {
                ImageView.this.mLoading.set(false);
                FLog.w(CodePushConstants.REACT_NATIVE_LOG_TAG, dataSource.getFailureCause(), "RNSVG: fetchDecodedImage failed!", new Object[0]);
            }

            public void onNewResultImpl(Bitmap bitmap) {
                ImageView.this.mLoading.set(false);
                SvgView svgView = ImageView.this.getSvgView();
                if (svgView != null) {
                    svgView.invalidate();
                }
            }
        }, UiThreadImmediateExecutorService.getInstance());
    }

    private void tryRenderFromBitmapCache(ImagePipeline imagePipeline, ImageRequest imageRequest, Canvas canvas, Paint paint, float f) {
        DataSource fetchImageFromBitmapCache = imagePipeline.fetchImageFromBitmapCache(imageRequest, this.mContext);
        try {
            try {
                CloseableReference closeableReference = (CloseableReference) fetchImageFromBitmapCache.getResult();
                try {
                    if (closeableReference == null) {
                        fetchImageFromBitmapCache.close();
                        return;
                    }
                    try {
                        CloseableBitmap closeableBitmap = (CloseableImage) closeableReference.get();
                        if (!(closeableBitmap instanceof CloseableBitmap)) {
                            CloseableReference.closeSafely(closeableReference);
                            fetchImageFromBitmapCache.close();
                            return;
                        }
                        Bitmap underlyingBitmap = closeableBitmap.getUnderlyingBitmap();
                        if (underlyingBitmap == null) {
                            CloseableReference.closeSafely(closeableReference);
                            fetchImageFromBitmapCache.close();
                        } else {
                            doRender(canvas, paint, underlyingBitmap, f);
                            fetchImageFromBitmapCache.close();
                        }
                    } catch (Exception e) {
                        throw new IllegalStateException(e);
                    }
                } finally {
                    CloseableReference.closeSafely(closeableReference);
                }
            } catch (Exception e2) {
                throw new IllegalStateException(e2);
            }
        } catch (Throwable th) {
            fetchImageFromBitmapCache.close();
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.horcrux.svg.RenderableView, com.horcrux.svg.VirtualView
    public void draw(Canvas canvas, Paint paint, float f) {
        if (this.mLoading.get()) {
            return;
        }
        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        ImageRequest fromUri = ImageRequest.fromUri(new ImageSource(this.mContext, this.uriString).getUri());
        if (imagePipeline.isInBitmapMemoryCache(fromUri)) {
            tryRenderFromBitmapCache(imagePipeline, fromUri, canvas, paint, f * this.mOpacity);
        } else {
            loadBitmap(imagePipeline, fromUri);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.horcrux.svg.RenderableView, com.horcrux.svg.VirtualView
    public Path getPath(Canvas canvas, Paint paint) {
        Path path = new Path();
        path.addRect(getRect(), Path.Direction.CW);
        return path;
    }

    @ReactProp(name = "align")
    public void setAlign(String str) {
        this.mAlign = str;
        invalidate();
    }

    @ReactProp(name = SettingsJsonConstants.ICON_HEIGHT_KEY)
    public void setHeight(Dynamic dynamic) {
        this.f40mH = SVGLength.from(dynamic);
        invalidate();
    }

    @ReactProp(name = "meetOrSlice")
    public void setMeetOrSlice(int i) {
        this.mMeetOrSlice = i;
        invalidate();
    }

    @ReactProp(name = "src")
    public void setSrc(@Nullable ReadableMap readableMap) {
        if (readableMap != null) {
            this.uriString = readableMap.getString("uri");
            String str = this.uriString;
            if (str == null || str.isEmpty()) {
                return;
            }
            if (readableMap.hasKey(SettingsJsonConstants.ICON_WIDTH_KEY) && readableMap.hasKey(SettingsJsonConstants.ICON_HEIGHT_KEY)) {
                this.mImageWidth = readableMap.getInt(SettingsJsonConstants.ICON_WIDTH_KEY);
                this.mImageHeight = readableMap.getInt(SettingsJsonConstants.ICON_HEIGHT_KEY);
            } else {
                this.mImageWidth = 0;
                this.mImageHeight = 0;
            }
            if (Uri.parse(this.uriString).getScheme() == null) {
                ResourceDrawableIdHelper.getInstance().getResourceDrawableUri(this.mContext, this.uriString);
            }
        }
    }

    @ReactProp(name = SettingsJsonConstants.ICON_WIDTH_KEY)
    public void setWidth(Dynamic dynamic) {
        this.f41mW = SVGLength.from(dynamic);
        invalidate();
    }

    @ReactProp(name = "x")
    public void setX(Dynamic dynamic) {
        this.f42mX = SVGLength.from(dynamic);
        invalidate();
    }

    @ReactProp(name = "y")
    public void setY(Dynamic dynamic) {
        this.f43mY = SVGLength.from(dynamic);
        invalidate();
    }
}
