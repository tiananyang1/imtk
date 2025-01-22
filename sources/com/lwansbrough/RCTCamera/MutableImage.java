package com.lwansbrough.RCTCamera;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.support.media.ExifInterface;
import android.util.Base64;
import android.util.Log;
import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataException;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import com.facebook.react.bridge.ReadableMap;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/* loaded from: classes08-dex2jar.jar:com/lwansbrough/RCTCamera/MutableImage.class */
public class MutableImage {
    private static final String TAG = "RNCamera";
    private Bitmap currentRepresentation;
    private boolean hasBeenReoriented = false;
    private final byte[] originalImageData;
    private Metadata originalImageMetaData;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes08-dex2jar.jar:com/lwansbrough/RCTCamera/MutableImage$GPS.class */
    public static class GPS {
        private GPS() {
        }

        private static String latitudeRef(double d) {
            return d < 0.0d ? "S" : "N";
        }

        private static String longitudeRef(double d) {
            return d < 0.0d ? "W" : "E";
        }

        private static String toDegreeMinuteSecods(double d) {
            double abs = Math.abs(d);
            int i = (int) abs;
            double d2 = (abs * 60.0d) - (i * 60.0d);
            int i2 = (int) d2;
            int i3 = (int) (((d2 * 60.0d) - (i2 * 60.0d)) * 1000.0d);
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(i);
            stringBuffer.append("/1,");
            stringBuffer.append(i2);
            stringBuffer.append("/1,");
            stringBuffer.append(i3);
            stringBuffer.append("/1000,");
            return stringBuffer.toString();
        }

        public static void writeExifData(double d, double d2, ExifInterface exifInterface) throws IOException {
            exifInterface.setAttribute("GPSLatitude", toDegreeMinuteSecods(d));
            exifInterface.setAttribute("GPSLatitudeRef", latitudeRef(d));
            exifInterface.setAttribute("GPSLongitude", toDegreeMinuteSecods(d2));
            exifInterface.setAttribute("GPSLongitudeRef", longitudeRef(d2));
        }
    }

    /* loaded from: classes08-dex2jar.jar:com/lwansbrough/RCTCamera/MutableImage$ImageMutationFailedException.class */
    public static class ImageMutationFailedException extends Exception {
        public ImageMutationFailedException(String str) {
            super(str);
        }

        public ImageMutationFailedException(String str, Throwable th) {
            super(str, th);
        }
    }

    public MutableImage(byte[] bArr) {
        this.originalImageData = bArr;
        this.currentRepresentation = toBitmap(bArr);
    }

    private String convertExposureTimeToDoubleFormat(String str) {
        return !str.contains("/") ? "" : Double.toString(1.0d / Double.parseDouble(str.split("/")[1]));
    }

    private Metadata originalImageMetaData() throws ImageProcessingException, IOException {
        if (this.originalImageMetaData == null) {
            this.originalImageMetaData = ImageMetadataReader.readMetadata(new BufferedInputStream(new ByteArrayInputStream(this.originalImageData)), this.originalImageData.length);
        }
        return this.originalImageMetaData;
    }

    private void rewriteOrientation(ExifInterface exifInterface) {
        exifInterface.setAttribute("Orientation", String.valueOf(1));
    }

    private void rotate(int i) throws ImageMutationFailedException {
        Matrix matrix = new Matrix();
        switch (i) {
            case 1:
                return;
            case 2:
                matrix.postScale(-1.0f, 1.0f);
                break;
            case 3:
                matrix.postRotate(180.0f);
                break;
            case 4:
                matrix.postRotate(180.0f);
                matrix.postScale(-1.0f, 1.0f);
                break;
            case 5:
                matrix.postRotate(90.0f);
                matrix.postScale(-1.0f, 1.0f);
                break;
            case 6:
                matrix.postRotate(90.0f);
                break;
            case 7:
                matrix.postRotate(270.0f);
                matrix.postScale(-1.0f, 1.0f);
                break;
            case 8:
                matrix.postRotate(270.0f);
                break;
        }
        Bitmap createBitmap = Bitmap.createBitmap(this.currentRepresentation, 0, 0, getWidth(), getHeight(), matrix, false);
        if (createBitmap == null) {
            throw new ImageMutationFailedException("failed to rotate");
        }
        this.currentRepresentation = createBitmap;
        this.hasBeenReoriented = true;
    }

    private static Bitmap toBitmap(byte[] bArr) {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            Bitmap decodeStream = BitmapFactory.decodeStream(byteArrayInputStream);
            byteArrayInputStream.close();
            return decodeStream;
        } catch (IOException e) {
            throw new IllegalStateException("Will not happen", e);
        }
    }

    private static byte[] toJpeg(Bitmap bitmap, int i) throws OutOfMemoryError {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, i, byteArrayOutputStream);
        try {
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
                return byteArray;
            } catch (IOException e) {
                Log.e(TAG, "problem compressing jpeg", e);
                return byteArray;
            }
        } catch (Throwable th) {
            try {
                byteArrayOutputStream.close();
            } catch (IOException e2) {
                Log.e(TAG, "problem compressing jpeg", e2);
            }
            throw th;
        }
    }

    private void writeLocationExifData(ReadableMap readableMap, ExifInterface exifInterface) {
        if (readableMap.hasKey("metadata")) {
            ReadableMap map = readableMap.getMap("metadata");
            if (map.hasKey("location")) {
                ReadableMap map2 = map.getMap("location");
                if (map2.hasKey("coords")) {
                    try {
                        ReadableMap map3 = map2.getMap("coords");
                        GPS.writeExifData(map3.getDouble("latitude"), map3.getDouble("longitude"), exifInterface);
                    } catch (IOException e) {
                        Log.e(TAG, "Couldn't write location data", e);
                    }
                }
            }
        }
    }

    public void cropToPreview(double d) throws IllegalArgumentException {
        int i;
        int i2;
        int width = getWidth();
        int height = getHeight();
        double d2 = height * d;
        double d3 = width;
        if (d2 > d3) {
            i2 = (int) (d3 / d);
            i = width;
        } else {
            i = (int) d2;
            i2 = height;
        }
        this.currentRepresentation = Bitmap.createBitmap(this.currentRepresentation, (width - i) / 2, (height - i2) / 2, i, i2);
    }

    public void fixOrientation() throws ImageMutationFailedException {
        try {
            ExifIFD0Directory firstDirectoryOfType = originalImageMetaData().getFirstDirectoryOfType(ExifIFD0Directory.class);
            if (firstDirectoryOfType != null && firstDirectoryOfType.containsTag(274)) {
                int i = firstDirectoryOfType.getInt(274);
                if (i != 1) {
                    rotate(i);
                    firstDirectoryOfType.setInt(274, 1);
                }
            }
        } catch (IOException | MetadataException | ImageProcessingException e) {
            throw new ImageMutationFailedException("failed to fix orientation", e);
        }
    }

    public int getHeight() {
        return this.currentRepresentation.getHeight();
    }

    public int getWidth() {
        return this.currentRepresentation.getWidth();
    }

    public void mirrorImage() throws ImageMutationFailedException {
        Matrix matrix = new Matrix();
        matrix.preScale(-1.0f, 1.0f);
        Bitmap createBitmap = Bitmap.createBitmap(this.currentRepresentation, 0, 0, getWidth(), getHeight(), matrix, false);
        if (createBitmap == null) {
            throw new ImageMutationFailedException("failed to mirror");
        }
        this.currentRepresentation = createBitmap;
    }

    public String toBase64(int i) {
        return Base64.encodeToString(toJpeg(this.currentRepresentation, i), 2);
    }

    public void writeDataToFile(File file, ReadableMap readableMap, int i) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(toJpeg(this.currentRepresentation, i));
        fileOutputStream.close();
        try {
            ExifInterface exifInterface = new ExifInterface(file.getAbsolutePath());
            for (Directory directory : originalImageMetaData().getDirectories()) {
                for (Tag tag : directory.getTags()) {
                    exifInterface.setAttribute(tag.getTagName(), directory.getObject(tag.getTagType()).toString());
                }
            }
            ExifSubIFDDirectory firstDirectoryOfType = originalImageMetaData().getFirstDirectoryOfType(ExifSubIFDDirectory.class);
            for (Tag tag2 : firstDirectoryOfType.getTags()) {
                int tagType = tag2.getTagType();
                String replaceAll = tag2.getTagName().replaceAll(" ", "");
                Object object = firstDirectoryOfType.getObject(tagType);
                if (replaceAll.equals("ExposureTime")) {
                    exifInterface.setAttribute(replaceAll, convertExposureTimeToDoubleFormat(object.toString()));
                } else {
                    exifInterface.setAttribute(replaceAll, object.toString());
                }
            }
            writeLocationExifData(readableMap, exifInterface);
            if (this.hasBeenReoriented) {
                rewriteOrientation(exifInterface);
            }
            exifInterface.saveAttributes();
        } catch (ImageProcessingException | IOException e) {
            Log.e(TAG, "failed to save exif data", e);
        }
    }
}
