package com.intercom.input.gallery;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

/* loaded from: classes08-dex2jar.jar:com/intercom/input/gallery/GalleryImage.class */
public class GalleryImage implements Parcelable {
    public static final Parcelable.Creator<GalleryImage> CREATOR = new Parcelable.Creator<GalleryImage>() { // from class: com.intercom.input.gallery.GalleryImage.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GalleryImage createFromParcel(Parcel parcel) {
            return new GalleryImage(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GalleryImage[] newArray(int i) {
            return new GalleryImage[i];
        }
    };

    @NonNull
    private final String attribution;

    @NonNull
    private final String fileName;
    private final int fileSize;
    private final int imageHeight;
    private final int imageWidth;
    private final boolean isGif;

    @NonNull
    private final String mimeType;

    @NonNull
    private final String previewPath;

    @NonNull
    private final Uri uri;

    /* loaded from: classes08-dex2jar.jar:com/intercom/input/gallery/GalleryImage$Builder.class */
    public static final class Builder {
        private String attribution;
        private String fileName;
        private int fileSize;
        private int imageHeight;
        private int imageWidth;
        private boolean isGif;
        private String mimeType;
        private String previewPath;
        private Uri uri;

        private static String valueOrEmpty(String str) {
            String str2 = str;
            if (str == null) {
                str2 = "";
            }
            return str2;
        }

        public GalleryImage build() {
            String valueOrEmpty = valueOrEmpty(this.fileName);
            String valueOrEmpty2 = valueOrEmpty(this.mimeType);
            Uri uri = this.uri;
            Uri uri2 = uri;
            if (uri == null) {
                uri2 = Uri.EMPTY;
            }
            return new GalleryImage(valueOrEmpty, valueOrEmpty2, uri2, valueOrEmpty(this.previewPath), valueOrEmpty(this.attribution), this.imageWidth, this.imageHeight, this.fileSize, this.isGif);
        }

        public Builder isGif(boolean z) {
            this.isGif = z;
            return this;
        }

        public Builder withAttribution(String str) {
            this.attribution = str;
            return this;
        }

        public Builder withFileName(String str) {
            this.fileName = str;
            return this;
        }

        public Builder withFileSize(int i) {
            this.fileSize = i;
            return this;
        }

        public Builder withImageHeight(int i) {
            this.imageHeight = i;
            return this;
        }

        public Builder withImageWidth(int i) {
            this.imageWidth = i;
            return this;
        }

        public Builder withMimeType(String str) {
            this.mimeType = str;
            return this;
        }

        public Builder withPreviewPath(String str) {
            this.previewPath = str;
            return this;
        }

        public Builder withUri(Uri uri) {
            this.uri = uri;
            return this;
        }
    }

    protected GalleryImage(Parcel parcel) {
        this.fileName = parcel.readString();
        this.mimeType = parcel.readString();
        this.uri = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
        this.previewPath = parcel.readString();
        this.attribution = parcel.readString();
        this.imageWidth = parcel.readInt();
        this.imageHeight = parcel.readInt();
        this.fileSize = parcel.readInt();
        this.isGif = parcel.readInt() != 1 ? false : true;
    }

    GalleryImage(@NonNull String str, @NonNull String str2, @NonNull Uri uri, @NonNull String str3, @NonNull String str4, int i, int i2, int i3, boolean z) {
        this.fileName = str;
        this.mimeType = str2;
        this.uri = uri;
        this.previewPath = str3;
        this.attribution = str4;
        this.imageWidth = i;
        this.imageHeight = i2;
        this.fileSize = i3;
        this.isGif = z;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GalleryImage galleryImage = (GalleryImage) obj;
        if (this.imageWidth == galleryImage.imageWidth && this.imageHeight == galleryImage.imageHeight && this.fileSize == galleryImage.fileSize && this.fileName.equals(galleryImage.fileName) && this.mimeType.equals(galleryImage.mimeType) && this.uri.equals(galleryImage.uri) && this.previewPath.equals(galleryImage.previewPath) && this.isGif == galleryImage.isGif) {
            return this.attribution.equals(galleryImage.attribution);
        }
        return false;
    }

    @NonNull
    public String getAttribution() {
        return this.attribution;
    }

    @NonNull
    public String getFileName() {
        return this.fileName;
    }

    public int getFileSize() {
        return this.fileSize;
    }

    public int getImageHeight() {
        return this.imageHeight;
    }

    public int getImageWidth() {
        return this.imageWidth;
    }

    public String getImageWidthXHeight() {
        return this.imageWidth + "x" + this.imageHeight;
    }

    @NonNull
    public String getMimeType() {
        return this.mimeType;
    }

    @NonNull
    public String getPreviewPath() {
        return this.previewPath;
    }

    @NonNull
    public Uri getUri() {
        return this.uri;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Throwable, java.lang.Runtime] */
    public int hashCode() {
        throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.provideAs(TypeTransformer.java:780)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e1expr(TypeTransformer.java:496)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:713)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e2expr(TypeTransformer.java:632)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:716)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s1stmt(TypeTransformer.java:810)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:840)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
    }

    public boolean isGif() {
        return this.isGif;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Throwable, java.lang.Runtime] */
    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.provideAs(TypeTransformer.java:780)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e1expr(TypeTransformer.java:496)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:713)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:698)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s1stmt(TypeTransformer.java:810)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:840)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
    }
}
