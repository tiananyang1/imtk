package com.horcrux.svg;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.JavaOnlyArray;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.uimanager.annotations.ReactProp;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nullable;

/* loaded from: classes08-dex2jar.jar:com/horcrux/svg/RenderableView.class */
public abstract class RenderableView extends VirtualView {
    private static final int CAP_BUTT = 0;
    static final int CAP_ROUND = 1;
    private static final int CAP_SQUARE = 2;
    private static final int FILL_RULE_EVENODD = 0;
    static final int FILL_RULE_NONZERO = 1;
    private static final int JOIN_BEVEL = 2;
    private static final int JOIN_MITER = 0;
    static final int JOIN_ROUND = 1;
    private static final int VECTOR_EFFECT_DEFAULT = 0;
    private static final int VECTOR_EFFECT_NON_SCALING_STROKE = 1;
    private static final Pattern regex = Pattern.compile("[0-9.-]+");

    @Nullable
    public ReadableArray fill;
    public float fillOpacity;
    public Path.FillType fillRule;

    @Nullable
    private ArrayList<String> mAttributeList;

    @Nullable
    private ArrayList<String> mLastMergedList;

    @Nullable
    private ArrayList<Object> mOriginProperties;

    @Nullable
    private ArrayList<String> mPropList;

    @Nullable
    public ReadableArray stroke;

    @Nullable
    public SVGLength[] strokeDasharray;
    public float strokeDashoffset;
    public Paint.Cap strokeLinecap;
    public Paint.Join strokeLinejoin;
    public float strokeMiterlimit;
    public float strokeOpacity;
    public SVGLength strokeWidth;
    public int vectorEffect;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RenderableView(ReactContext reactContext) {
        super(reactContext);
        this.vectorEffect = 0;
        this.strokeWidth = new SVGLength(1.0d);
        this.strokeOpacity = 1.0f;
        this.strokeMiterlimit = 4.0f;
        this.strokeDashoffset = 0.0f;
        this.strokeLinecap = Paint.Cap.ROUND;
        this.strokeLinejoin = Paint.Join.ROUND;
        this.fillOpacity = 1.0f;
        this.fillRule = Path.FillType.WINDING;
    }

    private ArrayList<String> getAttributeList() {
        return this.mAttributeList;
    }

    private boolean hasOwnProperty(String str) {
        ArrayList<String> arrayList = this.mAttributeList;
        return arrayList != null && arrayList.contains(str);
    }

    private static double saturate(double d) {
        if (d <= 0.0d) {
            return 0.0d;
        }
        double d2 = d;
        if (d >= 1.0d) {
            d2 = 1.0d;
        }
        return d2;
    }

    private boolean setupFillPaint(Paint paint, float f) {
        ReadableArray readableArray = this.fill;
        if (readableArray == null || readableArray.size() <= 0) {
            return false;
        }
        paint.reset();
        paint.setFlags(385);
        paint.setStyle(Paint.Style.FILL);
        setupPaint(paint, f, this.fill);
        return true;
    }

    private void setupPaint(Paint paint, float f, ReadableArray readableArray) {
        int i = readableArray.getInt(0);
        if (i == 0) {
            if (readableArray.size() != 2) {
                paint.setARGB((int) (readableArray.size() > 4 ? readableArray.getDouble(4) * f * 255.0d : f * 255.0f), (int) (readableArray.getDouble(1) * 255.0d), (int) (readableArray.getDouble(2) * 255.0d), (int) (readableArray.getDouble(3) * 255.0d));
                return;
            } else {
                paint.setColor((Math.round((r0 >>> 24) * f) << 24) | (readableArray.getInt(1) & 16777215));
                return;
            }
        }
        if (i != 1) {
            if (i != 2) {
                return;
            }
            paint.setColor(getSvgView().mTintColor);
        } else {
            Brush definedBrush = getSvgView().getDefinedBrush(readableArray.getString(1));
            if (definedBrush != null) {
                definedBrush.setupPaint(paint, this.mBox, this.mScale, f);
            }
        }
    }

    private boolean setupStrokePaint(Paint paint, float f) {
        ReadableArray readableArray;
        paint.reset();
        double relativeOnOther = relativeOnOther(this.strokeWidth);
        if (relativeOnOther == 0.0d || (readableArray = this.stroke) == null || readableArray.size() == 0) {
            return false;
        }
        paint.setFlags(385);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(this.strokeLinecap);
        paint.setStrokeJoin(this.strokeLinejoin);
        paint.setStrokeMiter(this.strokeMiterlimit * this.mScale);
        paint.setStrokeWidth((float) relativeOnOther);
        setupPaint(paint, f, this.stroke);
        SVGLength[] sVGLengthArr = this.strokeDasharray;
        if (sVGLengthArr == null) {
            return true;
        }
        int length = sVGLengthArr.length;
        float[] fArr = new float[length];
        for (int i = 0; i < length; i++) {
            fArr[i] = (float) relativeOnOther(this.strokeDasharray[i]);
        }
        paint.setPathEffect(new DashPathEffect(fArr, this.strokeDashoffset));
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.horcrux.svg.VirtualView
    public void draw(Canvas canvas, Paint paint, float f) {
        float f2 = f * this.mOpacity;
        if (f2 > 0.01f) {
            boolean z = false;
            boolean z2 = this.mPath == null;
            if (z2) {
                this.mPath = getPath(canvas, paint);
                this.mPath.setFillType(this.fillRule);
            }
            if (this.vectorEffect == 1) {
                z = true;
            }
            Path path = this.mPath;
            if (z) {
                path = new Path();
                this.mPath.transform(canvas.getMatrix(), path);
                canvas.setMatrix(null);
            }
            RectF rectF = new RectF();
            path.computeBounds(rectF, true);
            this.mBox = new RectF(rectF);
            new Matrix(canvas.getMatrix()).mapRect(rectF);
            setClientRect(rectF);
            clip(canvas, paint);
            if (setupFillPaint(paint, this.fillOpacity * f2)) {
                if (z2) {
                    this.mFillPath = new Path();
                    paint.getFillPath(path, this.mFillPath);
                }
                canvas.drawPath(path, paint);
            }
            if (setupStrokePaint(paint, f2 * this.strokeOpacity)) {
                if (z2) {
                    this.mStrokePath = new Path();
                    paint.getFillPath(path, this.mStrokePath);
                }
                canvas.drawPath(path, paint);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.horcrux.svg.VirtualView
    public abstract Path getPath(Canvas canvas, Paint paint);

    /* JADX INFO: Access modifiers changed from: package-private */
    public Region getRegion(Path path) {
        path.computeBounds(new RectF(), true);
        Region region = new Region();
        region.setPath(path, new Region((int) Math.floor(r0.left), (int) Math.floor(r0.top), (int) Math.ceil(r0.right), (int) Math.ceil(r0.bottom)));
        return region;
    }

    @Override // com.horcrux.svg.VirtualView
    int hitTest(float[] fArr) {
        if (this.mPath == null || !this.mInvertible || !this.mTransformInvertible) {
            return -1;
        }
        float[] fArr2 = new float[2];
        this.mInvMatrix.mapPoints(fArr2, fArr);
        this.mInvTransform.mapPoints(fArr2);
        int round = Math.round(fArr2[0]);
        int round2 = Math.round(fArr2[1]);
        if (this.mRegion == null && this.mFillPath != null) {
            this.mRegion = getRegion(this.mFillPath);
        }
        if (this.mRegion == null && this.mPath != null) {
            this.mRegion = getRegion(this.mPath);
        }
        if (this.mStrokeRegion == null && this.mStrokePath != null) {
            this.mStrokeRegion = getRegion(this.mStrokePath);
        }
        if ((this.mRegion == null || !this.mRegion.contains(round, round2)) && (this.mStrokeRegion == null || !this.mStrokeRegion.contains(round, round2))) {
            return -1;
        }
        Path clipPath = getClipPath();
        if (clipPath != null) {
            if (this.mClipRegionPath != clipPath) {
                this.mClipRegionPath = clipPath;
                this.mClipRegion = getRegion(clipPath);
            }
            if (!this.mClipRegion.contains(round, round2)) {
                return -1;
            }
        }
        return getId();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void mergeProperties(RenderableView renderableView) {
        ArrayList<String> attributeList = renderableView.getAttributeList();
        if (attributeList == null || attributeList.size() == 0) {
            return;
        }
        this.mOriginProperties = new ArrayList<>();
        ArrayList<String> arrayList = this.mPropList;
        this.mAttributeList = arrayList == null ? new ArrayList<>() : new ArrayList<>(arrayList);
        int size = attributeList.size();
        for (int i = 0; i < size; i++) {
            try {
                String str = attributeList.get(i);
                Field field = getClass().getField(str);
                Object obj = field.get(renderableView);
                this.mOriginProperties.add(field.get(this));
                if (!hasOwnProperty(str)) {
                    this.mAttributeList.add(str);
                    field.set(this, obj);
                }
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }
        this.mLastMergedList = attributeList;
    }

    @Override // com.horcrux.svg.VirtualView
    void render(Canvas canvas, Paint paint, float f) {
        MaskView maskView = this.mMask != null ? (MaskView) getSvgView().getDefinedMask(this.mMask) : null;
        if (maskView == null) {
            draw(canvas, paint, f);
            return;
        }
        Rect clipBounds = canvas.getClipBounds();
        int height = clipBounds.height();
        int width = clipBounds.width();
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Bitmap createBitmap2 = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Bitmap createBitmap3 = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas2 = new Canvas(createBitmap2);
        Canvas canvas3 = new Canvas(createBitmap);
        Canvas canvas4 = new Canvas(createBitmap3);
        canvas3.clipRect((float) relativeOnWidth(maskView.f46mX), (float) relativeOnWidth(maskView.f47mY), (float) relativeOnWidth(maskView.f45mW), (float) relativeOnWidth(maskView.f44mH));
        Paint paint2 = new Paint(1);
        maskView.draw(canvas3, paint2, 1.0f);
        int i = width * height;
        int[] iArr = new int[i];
        createBitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = iArr[i2];
            iArr[i2] = ((int) ((i3 >>> 24) * saturate((((((i3 >> 16) & 255) * 0.299d) + (((i3 >> 8) & 255) * 0.587d)) + ((i3 & 255) * 0.144d)) / 255.0d))) << 24;
        }
        createBitmap.setPixels(iArr, 0, width, 0, 0, width, height);
        draw(canvas2, paint, f);
        paint2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas4.drawBitmap(createBitmap2, 0.0f, 0.0f, (Paint) null);
        canvas4.drawBitmap(createBitmap, 0.0f, 0.0f, paint2);
        canvas.drawBitmap(createBitmap3, 0.0f, 0.0f, paint);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void resetProperties() {
        ArrayList<String> arrayList = this.mLastMergedList;
        if (arrayList == null || this.mOriginProperties == null) {
            return;
        }
        try {
            int size = arrayList.size();
            while (true) {
                int i = size - 1;
                if (i < 0) {
                    this.mLastMergedList = null;
                    this.mOriginProperties = null;
                    this.mAttributeList = this.mPropList;
                    return;
                }
                getClass().getField(this.mLastMergedList.get(i)).set(this, this.mOriginProperties.get(i));
                size = i;
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @ReactProp(name = "fill")
    public void setFill(@Nullable Dynamic dynamic) {
        if (dynamic == null || dynamic.isNull()) {
            this.fill = null;
            invalidate();
            return;
        }
        if (dynamic.getType().equals(ReadableType.Array)) {
            this.fill = dynamic.asArray();
        } else {
            JavaOnlyArray javaOnlyArray = new JavaOnlyArray();
            int i = 0;
            javaOnlyArray.pushInt(0);
            Matcher matcher = regex.matcher(dynamic.asString());
            while (matcher.find()) {
                double parseDouble = Double.parseDouble(matcher.group());
                double d = parseDouble;
                if (i < 3) {
                    d = parseDouble / 255.0d;
                }
                javaOnlyArray.pushDouble(d);
                i++;
            }
            this.fill = javaOnlyArray;
        }
        invalidate();
    }

    @ReactProp(defaultFloat = 1.0f, name = "fillOpacity")
    public void setFillOpacity(float f) {
        this.fillOpacity = f;
        invalidate();
    }

    @ReactProp(defaultInt = 1, name = "fillRule")
    public void setFillRule(int i) {
        if (i == 0) {
            this.fillRule = Path.FillType.EVEN_ODD;
        } else if (i != 1) {
            throw new JSApplicationIllegalArgumentException("fillRule " + this.fillRule + " unrecognized");
        }
        invalidate();
    }

    @ReactProp(name = "propList")
    public void setPropList(@Nullable ReadableArray readableArray) {
        if (readableArray != null) {
            ArrayList<String> arrayList = new ArrayList<>();
            this.mAttributeList = arrayList;
            this.mPropList = arrayList;
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= readableArray.size()) {
                    break;
                }
                this.mPropList.add(readableArray.getString(i2));
                i = i2 + 1;
            }
        }
        invalidate();
    }

    @ReactProp(name = "stroke")
    public void setStroke(@Nullable Dynamic dynamic) {
        if (dynamic == null || dynamic.isNull()) {
            this.stroke = null;
            invalidate();
            return;
        }
        if (dynamic.getType().equals(ReadableType.Array)) {
            this.stroke = dynamic.asArray();
        } else {
            JavaOnlyArray javaOnlyArray = new JavaOnlyArray();
            javaOnlyArray.pushInt(0);
            Matcher matcher = regex.matcher(dynamic.asString());
            while (matcher.find()) {
                javaOnlyArray.pushDouble(Double.parseDouble(matcher.group()));
            }
            this.stroke = javaOnlyArray;
        }
        invalidate();
    }

    @ReactProp(name = "strokeDasharray")
    public void setStrokeDasharray(@Nullable ReadableArray readableArray) {
        if (readableArray != null) {
            int size = readableArray.size();
            this.strokeDasharray = new SVGLength[size];
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= size) {
                    break;
                }
                this.strokeDasharray[i2] = SVGLength.from(readableArray.getDynamic(i2));
                i = i2 + 1;
            }
        } else {
            this.strokeDasharray = null;
        }
        invalidate();
    }

    @ReactProp(name = "strokeDashoffset")
    public void setStrokeDashoffset(float f) {
        this.strokeDashoffset = f * this.mScale;
        invalidate();
    }

    @ReactProp(defaultInt = 1, name = "strokeLinecap")
    public void setStrokeLinecap(int i) {
        if (i == 0) {
            this.strokeLinecap = Paint.Cap.BUTT;
        } else if (i == 1) {
            this.strokeLinecap = Paint.Cap.ROUND;
        } else {
            if (i != 2) {
                throw new JSApplicationIllegalArgumentException("strokeLinecap " + this.strokeLinecap + " unrecognized");
            }
            this.strokeLinecap = Paint.Cap.SQUARE;
        }
        invalidate();
    }

    @ReactProp(defaultInt = 1, name = "strokeLinejoin")
    public void setStrokeLinejoin(int i) {
        if (i == 0) {
            this.strokeLinejoin = Paint.Join.MITER;
        } else if (i == 1) {
            this.strokeLinejoin = Paint.Join.ROUND;
        } else {
            if (i != 2) {
                throw new JSApplicationIllegalArgumentException("strokeLinejoin " + this.strokeLinejoin + " unrecognized");
            }
            this.strokeLinejoin = Paint.Join.BEVEL;
        }
        invalidate();
    }

    @ReactProp(defaultFloat = 4.0f, name = "strokeMiterlimit")
    public void setStrokeMiterlimit(float f) {
        this.strokeMiterlimit = f;
        invalidate();
    }

    @ReactProp(defaultFloat = 1.0f, name = "strokeOpacity")
    public void setStrokeOpacity(float f) {
        this.strokeOpacity = f;
        invalidate();
    }

    @ReactProp(name = "strokeWidth")
    public void setStrokeWidth(Dynamic dynamic) {
        this.strokeWidth = SVGLength.from(dynamic);
        invalidate();
    }

    @ReactProp(name = "vectorEffect")
    public void setVectorEffect(int i) {
        this.vectorEffect = i;
        invalidate();
    }
}
