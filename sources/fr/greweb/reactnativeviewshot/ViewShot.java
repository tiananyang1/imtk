package fr.greweb.reactnativeviewshot;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Base64;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.NativeViewHierarchyManager;
import com.facebook.react.uimanager.UIBlock;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.zip.Deflater;
import javax.annotation.Nullable;

/* loaded from: classes08-dex2jar.jar:fr/greweb/reactnativeviewshot/ViewShot.class */
public class ViewShot implements UIBlock {
    private static final int ARGB_SIZE = 4;
    public static final String ERROR_UNABLE_TO_SNAPSHOT = "E_UNABLE_TO_SNAPSHOT";
    private static final String TAG = "ViewShot";
    private final Activity currentActivity;
    private final String extension;

    @Formats
    private final int format;
    private final Integer height;
    private final File output;
    private final Promise promise;
    private final double quality;
    private final ReactApplicationContext reactContext;

    @Results
    private final String result;
    private final Boolean snapshotContentContainer;
    private final int tag;
    private final Integer width;
    private static final int PREALLOCATE_SIZE = 65536;
    private static byte[] outputBuffer = new byte[PREALLOCATE_SIZE];
    private static final Object guardBitmaps = new Object();
    private static final Set<Bitmap> weakBitmaps = Collections.newSetFromMap(new WeakHashMap());

    /* loaded from: classes08-dex2jar.jar:fr/greweb/reactnativeviewshot/ViewShot$Formats.class */
    public @interface Formats {
        public static final int JPEG = 0;
        public static final int PNG = 1;
        public static final int RAW = -1;
        public static final int WEBP = 2;
        public static final Bitmap.CompressFormat[] mapping = {Bitmap.CompressFormat.JPEG, Bitmap.CompressFormat.PNG, Bitmap.CompressFormat.WEBP};
    }

    /* loaded from: classes08-dex2jar.jar:fr/greweb/reactnativeviewshot/ViewShot$Results.class */
    public @interface Results {
        public static final String BASE_64 = "base64";
        public static final String DATA_URI = "data-uri";
        public static final String TEMP_FILE = "tmpfile";
        public static final String ZIP_BASE_64 = "zip-base64";
    }

    /* loaded from: classes08-dex2jar.jar:fr/greweb/reactnativeviewshot/ViewShot$ReusableByteArrayOutputStream.class */
    public static class ReusableByteArrayOutputStream extends ByteArrayOutputStream {
        private static final int MAX_ARRAY_SIZE = 2147483639;

        public ReusableByteArrayOutputStream(@NonNull byte[] bArr) {
            super(0);
            this.buf = bArr;
        }

        protected static int hugeCapacity(int i) {
            if (i < 0) {
                throw new OutOfMemoryError();
            }
            int i2 = MAX_ARRAY_SIZE;
            if (i > MAX_ARRAY_SIZE) {
                i2 = Integer.MAX_VALUE;
            }
            return i2;
        }

        @NonNull
        public ByteBuffer asBuffer(int i) {
            if (this.buf.length < i) {
                grow(i);
            }
            return ByteBuffer.wrap(this.buf);
        }

        protected void grow(int i) {
            int length = this.buf.length << 1;
            int i2 = length;
            if (length - i < 0) {
                i2 = i;
            }
            int i3 = i2;
            if (i2 - MAX_ARRAY_SIZE > 0) {
                i3 = hugeCapacity(i);
            }
            this.buf = Arrays.copyOf(this.buf, i3);
        }

        public byte[] innerBuffer() {
            return this.buf;
        }

        public void setSize(int i) {
            this.count = i;
        }
    }

    public ViewShot(int i, String str, @Formats int i2, double d, @Nullable Integer num, @Nullable Integer num2, File file, @Results String str2, Boolean bool, ReactApplicationContext reactApplicationContext, Activity activity, Promise promise) {
        this.tag = i;
        this.extension = str;
        this.format = i2;
        this.quality = d;
        this.width = num;
        this.height = num2;
        this.output = file;
        this.result = str2;
        this.snapshotContentContainer = bool;
        this.reactContext = reactApplicationContext;
        this.currentActivity = activity;
        this.promise = promise;
    }

    @NonNull
    private Matrix applyTransformations(Canvas canvas, @NonNull View view, @NonNull View view2) {
        View view3;
        Matrix matrix = new Matrix();
        LinkedList linkedList = new LinkedList();
        View view4 = view2;
        do {
            linkedList.add(view4);
            view3 = (View) view4.getParent();
            view4 = view3;
        } while (view3 != view);
        Collections.reverse(linkedList);
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            View view5 = (View) it.next();
            canvas.save();
            float left = view5.getLeft() + (view5 != view2 ? view5.getPaddingLeft() : 0) + view5.getTranslationX();
            int top = view5.getTop();
            int i = 0;
            if (view5 != view2) {
                i = view5.getPaddingTop();
            }
            float translationY = top + i + view5.getTranslationY();
            canvas.translate(left, translationY);
            canvas.rotate(view5.getRotation(), view5.getPivotX(), view5.getPivotY());
            canvas.scale(view5.getScaleX(), view5.getScaleY());
            matrix.postTranslate(left, translationY);
            matrix.postRotate(view5.getRotation(), view5.getPivotX(), view5.getPivotY());
            matrix.postScale(view5.getScaleX(), view5.getScaleY());
        }
        return matrix;
    }

    private Point captureView(@NonNull View view, @NonNull OutputStream outputStream) throws IOException {
        try {
            DebugViews.longDebug(TAG, DebugViews.logViewHierarchy(this.currentActivity));
            return captureViewImpl(view, outputStream);
        } finally {
            outputStream.close();
        }
    }

    private Point captureViewImpl(@NonNull View view, @NonNull OutputStream outputStream) {
        Bitmap bitmap;
        int width = view.getWidth();
        int height = view.getHeight();
        if (width <= 0 || height <= 0) {
            throw new RuntimeException("Impossible to snapshot the view: view is invalid");
        }
        if (this.snapshotContentContainer.booleanValue()) {
            ScrollView scrollView = (ScrollView) view;
            height = 0;
            for (int i = 0; i < scrollView.getChildCount(); i++) {
                height += scrollView.getChildAt(i).getHeight();
            }
        }
        Point point = new Point(width, height);
        Bitmap bitmapForScreenshot = getBitmapForScreenshot(width, height);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        Canvas canvas = new Canvas(bitmapForScreenshot);
        view.draw(canvas);
        for (View view2 : getAllChildren(view)) {
            if ((view2 instanceof TextureView) && view2.getVisibility() == 0) {
                TextureView textureView = (TextureView) view2;
                textureView.setOpaque(false);
                Bitmap bitmap2 = textureView.getBitmap(getExactBitmapForScreenshot(view2.getWidth(), view2.getHeight()));
                int save = canvas.save();
                applyTransformations(canvas, view, view2);
                canvas.drawBitmap(bitmap2, 0.0f, 0.0f, paint);
                canvas.restoreToCount(save);
                recycleBitmap(bitmap2);
            }
        }
        Integer num = this.width;
        if (num == null || this.height == null || (num.intValue() == width && this.height.intValue() == height)) {
            bitmap = bitmapForScreenshot;
        } else {
            bitmap = Bitmap.createScaledBitmap(bitmapForScreenshot, this.width.intValue(), this.height.intValue(), true);
            recycleBitmap(bitmapForScreenshot);
        }
        if (-1 == this.format && (outputStream instanceof ReusableByteArrayOutputStream)) {
            int i2 = width * height * 4;
            ReusableByteArrayOutputStream reusableByteArrayOutputStream = (ReusableByteArrayOutputStream) cast(outputStream);
            bitmap.copyPixelsToBuffer(reusableByteArrayOutputStream.asBuffer(i2));
            reusableByteArrayOutputStream.setSize(i2);
        } else {
            bitmap.compress(Formats.mapping[this.format], (int) (this.quality * 100.0d), outputStream);
        }
        recycleBitmap(bitmap);
        return point;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static <T extends A, A> T cast(A a) {
        return a;
    }

    @NonNull
    private List<View> getAllChildren(@NonNull View view) {
        if (!(view instanceof ViewGroup)) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(view);
            return arrayList;
        }
        ArrayList arrayList2 = new ArrayList();
        ViewGroup viewGroup = (ViewGroup) view;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= viewGroup.getChildCount()) {
                return arrayList2;
            }
            arrayList2.addAll(getAllChildren(viewGroup.getChildAt(i2)));
            i = i2 + 1;
        }
    }

    @NonNull
    private static Bitmap getBitmapForScreenshot(int i, int i2) {
        synchronized (guardBitmaps) {
            for (Bitmap bitmap : weakBitmaps) {
                if (bitmap.getWidth() >= i && bitmap.getHeight() >= i2) {
                    weakBitmaps.remove(bitmap);
                    bitmap.eraseColor(0);
                    return bitmap;
                }
            }
            return Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        }
    }

    @NonNull
    private static Bitmap getExactBitmapForScreenshot(int i, int i2) {
        synchronized (guardBitmaps) {
            for (Bitmap bitmap : weakBitmaps) {
                if (bitmap.getWidth() == i && bitmap.getHeight() == i2) {
                    weakBitmaps.remove(bitmap);
                    bitmap.eraseColor(0);
                    return bitmap;
                }
            }
            return Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        }
    }

    private static int proposeSize(@NonNull View view) {
        return Math.min(view.getWidth() * view.getHeight() * 4, 32);
    }

    private static void recycleBitmap(@NonNull Bitmap bitmap) {
        synchronized (guardBitmaps) {
            weakBitmaps.add(bitmap);
        }
    }

    private void saveToBase64String(@NonNull View view) throws IOException {
        String str;
        boolean z = -1 == this.format;
        boolean equals = Results.ZIP_BASE_64.equals(this.result);
        ReusableByteArrayOutputStream reusableByteArrayOutputStream = new ReusableByteArrayOutputStream(outputBuffer);
        Point captureView = captureView(view, reusableByteArrayOutputStream);
        outputBuffer = reusableByteArrayOutputStream.innerBuffer();
        int size = reusableByteArrayOutputStream.size();
        String format = String.format(Locale.US, "%d:%d|", Integer.valueOf(captureView.x), Integer.valueOf(captureView.y));
        if (!z) {
            format = "";
        }
        if (equals) {
            Deflater deflater = new Deflater();
            deflater.setInput(outputBuffer, 0, size);
            deflater.finish();
            ReusableByteArrayOutputStream reusableByteArrayOutputStream2 = new ReusableByteArrayOutputStream(new byte[32]);
            byte[] bArr = new byte[1024];
            while (!deflater.finished()) {
                reusableByteArrayOutputStream2.write(bArr, 0, deflater.deflate(bArr));
            }
            str = format + Base64.encodeToString(reusableByteArrayOutputStream2.innerBuffer(), 0, reusableByteArrayOutputStream2.size(), 2);
        } else {
            str = format + Base64.encodeToString(outputBuffer, 0, size, 2);
        }
        this.promise.resolve(str);
    }

    private void saveToDataUriString(@NonNull View view) throws IOException {
        ReusableByteArrayOutputStream reusableByteArrayOutputStream = new ReusableByteArrayOutputStream(outputBuffer);
        captureView(view, reusableByteArrayOutputStream);
        outputBuffer = reusableByteArrayOutputStream.innerBuffer();
        String encodeToString = Base64.encodeToString(outputBuffer, 0, reusableByteArrayOutputStream.size(), 2);
        String str = "jpg".equals(this.extension) ? "jpeg" : this.extension;
        this.promise.resolve("data:image/" + str + ";base64," + encodeToString);
    }

    private void saveToRawFileOnDevice(@NonNull View view) throws IOException {
        String uri = Uri.fromFile(this.output).toString();
        FileOutputStream fileOutputStream = new FileOutputStream(this.output);
        ReusableByteArrayOutputStream reusableByteArrayOutputStream = new ReusableByteArrayOutputStream(outputBuffer);
        Point captureView = captureView(view, reusableByteArrayOutputStream);
        outputBuffer = reusableByteArrayOutputStream.innerBuffer();
        int size = reusableByteArrayOutputStream.size();
        fileOutputStream.write(String.format(Locale.US, "%d:%d|", Integer.valueOf(captureView.x), Integer.valueOf(captureView.y)).getBytes(Charset.forName("US-ASCII")));
        fileOutputStream.write(outputBuffer, 0, size);
        fileOutputStream.close();
        this.promise.resolve(uri);
    }

    private void saveToTempFileOnDevice(@NonNull View view) throws IOException {
        captureView(view, new FileOutputStream(this.output));
        this.promise.resolve(Uri.fromFile(this.output).toString());
    }

    public void execute(NativeViewHierarchyManager nativeViewHierarchyManager) {
        int i = this.tag;
        View findViewById = i == -1 ? this.currentActivity.getWindow().getDecorView().findViewById(android.R.id.content) : nativeViewHierarchyManager.resolveView(i);
        if (findViewById == null) {
            Log.e(TAG, "No view found with reactTag: " + this.tag, new AssertionError());
            this.promise.reject(ERROR_UNABLE_TO_SNAPSHOT, "No view found with reactTag: " + this.tag);
            return;
        }
        try {
            ReusableByteArrayOutputStream reusableByteArrayOutputStream = new ReusableByteArrayOutputStream(outputBuffer);
            reusableByteArrayOutputStream.setSize(proposeSize(findViewById));
            outputBuffer = reusableByteArrayOutputStream.innerBuffer();
            if (Results.TEMP_FILE.equals(this.result) && -1 == this.format) {
                saveToRawFileOnDevice(findViewById);
                return;
            }
            if (Results.TEMP_FILE.equals(this.result) && -1 != this.format) {
                saveToTempFileOnDevice(findViewById);
                return;
            }
            if (!Results.BASE_64.equals(this.result) && !Results.ZIP_BASE_64.equals(this.result)) {
                if (Results.DATA_URI.equals(this.result)) {
                    saveToDataUriString(findViewById);
                    return;
                }
                return;
            }
            saveToBase64String(findViewById);
        } catch (Throwable th) {
            Log.e(TAG, "Failed to capture view snapshot", th);
            this.promise.reject(ERROR_UNABLE_TO_SNAPSHOT, "Failed to capture view snapshot");
        }
    }
}
