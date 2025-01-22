package com.horcrux.svg;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReadableArray;
import com.microsoft.codepush.react.CodePushConstants;

/* loaded from: classes08-dex2jar.jar:com/horcrux/svg/Brush.class */
class Brush {
    private ReadableArray mColors;
    private Matrix mMatrix;
    private PatternView mPattern;
    private final SVGLength[] mPoints;
    private final BrushType mType;
    private boolean mUseContentObjectBoundingBoxUnits;
    private final boolean mUseObjectBoundingBox;
    private Rect mUserSpaceBoundingBox;

    /* loaded from: classes08-dex2jar.jar:com/horcrux/svg/Brush$BrushType.class */
    enum BrushType {
        LINEAR_GRADIENT,
        RADIAL_GRADIENT,
        PATTERN
    }

    /* loaded from: classes08-dex2jar.jar:com/horcrux/svg/Brush$BrushUnits.class */
    enum BrushUnits {
        OBJECT_BOUNDING_BOX,
        USER_SPACE_ON_USE
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Brush(BrushType brushType, SVGLength[] sVGLengthArr, BrushUnits brushUnits) {
        this.mType = brushType;
        this.mPoints = sVGLengthArr;
        this.mUseObjectBoundingBox = brushUnits == BrushUnits.OBJECT_BOUNDING_BOX;
    }

    private RectF getPaintRect(RectF rectF) {
        float f;
        if (!this.mUseObjectBoundingBox) {
            rectF = new RectF(this.mUserSpaceBoundingBox);
        }
        float width = rectF.width();
        float height = rectF.height();
        float f2 = 0.0f;
        if (this.mUseObjectBoundingBox) {
            f2 = rectF.left;
            f = rectF.top;
        } else {
            f = 0.0f;
        }
        return new RectF(f2, f, width + f2, height + f);
    }

    private double getVal(SVGLength sVGLength, double d, float f, float f2) {
        return PropHelper.fromRelative(sVGLength, d, 0.0d, (this.mUseObjectBoundingBox && sVGLength.unit == SVGLengthUnitType.SVG_LENGTHTYPE_NUMBER) ? d : f, f2);
    }

    private static void parseGradientStops(ReadableArray readableArray, int i, float[] fArr, int[] iArr, float f) {
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 >= i) {
                return;
            }
            int i4 = i3 * 2;
            fArr[i3] = (float) readableArray.getDouble(i4);
            iArr[i3] = (readableArray.getInt(i4 + 1) & 16777215) | (Math.round((r0 >>> 24) * f) << 24);
            i2 = i3 + 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setContentUnits(BrushUnits brushUnits) {
        this.mUseContentObjectBoundingBoxUnits = brushUnits == BrushUnits.OBJECT_BOUNDING_BOX;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setGradientColors(ReadableArray readableArray) {
        this.mColors = readableArray;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setGradientTransform(Matrix matrix) {
        this.mMatrix = matrix;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setPattern(PatternView patternView) {
        this.mPattern = patternView;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setUserSpaceBoundingBox(Rect rect) {
        this.mUserSpaceBoundingBox = rect;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setupPaint(Paint paint, RectF rectF, float f, float f2) {
        int[] iArr;
        RectF paintRect = getPaintRect(rectF);
        float width = paintRect.width();
        float height = paintRect.height();
        float f3 = paintRect.left;
        float f4 = paintRect.top;
        float textSize = paint.getTextSize();
        if (this.mType == BrushType.PATTERN) {
            double d = width;
            double val = getVal(this.mPoints[0], d, f, textSize);
            double d2 = height;
            double val2 = getVal(this.mPoints[1], d2, f, textSize);
            double val3 = getVal(this.mPoints[2], d, f, textSize);
            double val4 = getVal(this.mPoints[3], d2, f, textSize);
            if (val3 <= 1.0d || val4 <= 1.0d) {
                return;
            }
            Bitmap createBitmap = Bitmap.createBitmap((int) val3, (int) val4, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            RectF viewBox = this.mPattern.getViewBox();
            if (viewBox != null && viewBox.width() > 0.0f && viewBox.height() > 0.0f) {
                canvas.concat(ViewBox.getTransform(viewBox, new RectF((float) val, (float) val2, (float) val3, (float) val4), this.mPattern.mAlign, this.mPattern.mMeetOrSlice));
            }
            if (this.mUseContentObjectBoundingBoxUnits) {
                canvas.scale(width / f, height / f);
            }
            this.mPattern.draw(canvas, new Paint(), f2);
            Matrix matrix = new Matrix();
            Matrix matrix2 = this.mMatrix;
            if (matrix2 != null) {
                matrix.preConcat(matrix2);
            }
            BitmapShader bitmapShader = new BitmapShader(createBitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
            bitmapShader.setLocalMatrix(matrix);
            paint.setShader(bitmapShader);
            return;
        }
        int size = this.mColors.size() / 2;
        int[] iArr2 = new int[size];
        float[] fArr = new float[size];
        parseGradientStops(this.mColors, size, fArr, iArr2, f2);
        if (fArr.length == 1) {
            iArr = new int[]{iArr2[0], iArr2[0]};
            float[] fArr2 = {fArr[0], fArr[0]};
            FLog.w(CodePushConstants.REACT_NATIVE_LOG_TAG, "Gradient contains only on stop");
            fArr = fArr2;
        } else {
            iArr = iArr2;
        }
        if (this.mType == BrushType.LINEAR_GRADIENT) {
            double d3 = width;
            double d4 = f3;
            double d5 = f;
            double d6 = textSize;
            double fromRelative = PropHelper.fromRelative(this.mPoints[0], d3, d4, d5, d6);
            double d7 = height;
            double d8 = f4;
            Shader linearGradient = new LinearGradient((float) fromRelative, (float) PropHelper.fromRelative(this.mPoints[1], d7, d8, d5, d6), (float) PropHelper.fromRelative(this.mPoints[2], d3, d4, d5, d6), (float) PropHelper.fromRelative(this.mPoints[3], d7, d8, d5, d6), iArr, fArr, Shader.TileMode.CLAMP);
            if (this.mMatrix != null) {
                Matrix matrix3 = new Matrix();
                matrix3.preConcat(this.mMatrix);
                linearGradient.setLocalMatrix(matrix3);
            }
            paint.setShader(linearGradient);
            return;
        }
        if (this.mType == BrushType.RADIAL_GRADIENT) {
            double d9 = width;
            double d10 = f;
            double d11 = textSize;
            double fromRelative2 = PropHelper.fromRelative(this.mPoints[2], d9, 0.0d, d10, d11);
            double d12 = height;
            double fromRelative3 = PropHelper.fromRelative(this.mPoints[3], d12, 0.0d, d10, d11);
            double fromRelative4 = PropHelper.fromRelative(this.mPoints[4], d9, f3, d10, d11);
            double fromRelative5 = PropHelper.fromRelative(this.mPoints[5], d12, f4, d10, d11);
            double d13 = fromRelative3 / fromRelative2;
            Shader radialGradient = new RadialGradient((float) fromRelative4, (float) (fromRelative5 / d13), (float) fromRelative2, iArr, fArr, Shader.TileMode.CLAMP);
            Matrix matrix4 = new Matrix();
            matrix4.preScale(1.0f, (float) d13);
            Matrix matrix5 = this.mMatrix;
            if (matrix5 != null) {
                matrix4.preConcat(matrix5);
            }
            radialGradient.setLocalMatrix(matrix4);
            paint.setShader(radialGradient);
        }
    }
}
