package com.horcrux.svg;

import com.facebook.react.bridge.ReadableMap;
import java.util.ArrayList;
import javax.annotation.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes08-dex2jar.jar:com/horcrux/svg/GlyphContext.class */
public class GlyphContext {
    private double mDX;
    private int mDXsIndex;
    private double mDY;
    private int mDYsIndex;
    private final float mHeight;
    private int mRsIndex;
    private final float mScale;
    private int mTop;
    private final float mWidth;

    /* renamed from: mX */
    private double f38mX;
    private int mXsIndex;

    /* renamed from: mY */
    private double f39mY;
    private int mYsIndex;
    final ArrayList<FontData> mFontContext = new ArrayList<>();
    private final ArrayList<SVGLength[]> mXsContext = new ArrayList<>();
    private final ArrayList<SVGLength[]> mYsContext = new ArrayList<>();
    private final ArrayList<SVGLength[]> mDXsContext = new ArrayList<>();
    private final ArrayList<SVGLength[]> mDYsContext = new ArrayList<>();
    private final ArrayList<double[]> mRsContext = new ArrayList<>();
    private final ArrayList<Integer> mXIndices = new ArrayList<>();
    private final ArrayList<Integer> mYIndices = new ArrayList<>();
    private final ArrayList<Integer> mDXIndices = new ArrayList<>();
    private final ArrayList<Integer> mDYIndices = new ArrayList<>();
    private final ArrayList<Integer> mRIndices = new ArrayList<>();
    private final ArrayList<Integer> mXsIndices = new ArrayList<>();
    private final ArrayList<Integer> mYsIndices = new ArrayList<>();
    private final ArrayList<Integer> mDXsIndices = new ArrayList<>();
    private final ArrayList<Integer> mDYsIndices = new ArrayList<>();
    private final ArrayList<Integer> mRsIndices = new ArrayList<>();
    private double mFontSize = 12.0d;
    private FontData topFont = FontData.Defaults;
    private SVGLength[] mXs = new SVGLength[0];
    private SVGLength[] mYs = new SVGLength[0];
    private SVGLength[] mDXs = new SVGLength[0];
    private SVGLength[] mDYs = new SVGLength[0];
    private double[] mRs = {0.0d};
    private int mXIndex = -1;
    private int mYIndex = -1;
    private int mDXIndex = -1;
    private int mDYIndex = -1;
    private int mRIndex = -1;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GlyphContext(float f, float f2, float f3) {
        this.mScale = f;
        this.mWidth = f2;
        this.mHeight = f3;
        this.mXsContext.add(this.mXs);
        this.mYsContext.add(this.mYs);
        this.mDXsContext.add(this.mDXs);
        this.mDYsContext.add(this.mDYs);
        this.mRsContext.add(this.mRs);
        this.mXIndices.add(Integer.valueOf(this.mXIndex));
        this.mYIndices.add(Integer.valueOf(this.mYIndex));
        this.mDXIndices.add(Integer.valueOf(this.mDXIndex));
        this.mDYIndices.add(Integer.valueOf(this.mDYIndex));
        this.mRIndices.add(Integer.valueOf(this.mRIndex));
        this.mFontContext.add(this.topFont);
        pushIndices();
    }

    private double[] getDoubleArrayFromReadableArray(ArrayList<SVGLength> arrayList) {
        int size = arrayList.size();
        double[] dArr = new double[size];
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= size) {
                return dArr;
            }
            dArr[i2] = arrayList.get(i2).value;
            i = i2 + 1;
        }
    }

    private SVGLength[] getStringArrayFromReadableArray(ArrayList<SVGLength> arrayList) {
        int size = arrayList.size();
        SVGLength[] sVGLengthArr = new SVGLength[size];
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= size) {
                return sVGLengthArr;
            }
            sVGLengthArr[i2] = arrayList.get(i2);
            i = i2 + 1;
        }
    }

    private FontData getTopOrParentFont(GroupView groupView) {
        if (this.mTop > 0) {
            return this.topFont;
        }
        GroupView parentTextRoot = groupView.getParentTextRoot();
        while (true) {
            GroupView groupView2 = parentTextRoot;
            if (groupView2 == null) {
                return FontData.Defaults;
            }
            FontData font = groupView2.getGlyphContext().getFont();
            if (font != FontData.Defaults) {
                return font;
            }
            parentTextRoot = groupView2.getParentTextRoot();
        }
    }

    private static void incrementIndices(ArrayList<Integer> arrayList, int i) {
        while (i >= 0) {
            arrayList.set(i, Integer.valueOf(arrayList.get(i).intValue() + 1));
            i--;
        }
    }

    private void pushIndices() {
        this.mXsIndices.add(Integer.valueOf(this.mXsIndex));
        this.mYsIndices.add(Integer.valueOf(this.mYsIndex));
        this.mDXsIndices.add(Integer.valueOf(this.mDXsIndex));
        this.mDYsIndices.add(Integer.valueOf(this.mDYsIndex));
        this.mRsIndices.add(Integer.valueOf(this.mRsIndex));
    }

    private void pushNodeAndFont(GroupView groupView, @Nullable ReadableMap readableMap) {
        FontData topOrParentFont = getTopOrParentFont(groupView);
        this.mTop++;
        if (readableMap == null) {
            this.mFontContext.add(topOrParentFont);
            return;
        }
        FontData fontData = new FontData(readableMap, topOrParentFont, this.mScale);
        this.mFontSize = fontData.fontSize;
        this.mFontContext.add(fontData);
        this.topFont = fontData;
    }

    private void reset() {
        this.mRsIndex = 0;
        this.mDYsIndex = 0;
        this.mDXsIndex = 0;
        this.mYsIndex = 0;
        this.mXsIndex = 0;
        this.mRIndex = -1;
        this.mDYIndex = -1;
        this.mDXIndex = -1;
        this.mYIndex = -1;
        this.mXIndex = -1;
        this.mDY = 0.0d;
        this.mDX = 0.0d;
        this.f39mY = 0.0d;
        this.f38mX = 0.0d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FontData getFont() {
        return this.topFont;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public double getFontSize() {
        return this.mFontSize;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float getHeight() {
        return this.mHeight;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float getWidth() {
        return this.mWidth;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public double nextDeltaX() {
        incrementIndices(this.mDXIndices, this.mDXsIndex);
        int i = this.mDXIndex + 1;
        SVGLength[] sVGLengthArr = this.mDXs;
        if (i < sVGLengthArr.length) {
            this.mDXIndex = i;
            this.mDX += PropHelper.fromRelative(sVGLengthArr[i], this.mWidth, 0.0d, this.mScale, this.mFontSize);
        }
        return this.mDX;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public double nextDeltaY() {
        incrementIndices(this.mDYIndices, this.mDYsIndex);
        int i = this.mDYIndex + 1;
        SVGLength[] sVGLengthArr = this.mDYs;
        if (i < sVGLengthArr.length) {
            this.mDYIndex = i;
            this.mDY += PropHelper.fromRelative(sVGLengthArr[i], this.mHeight, 0.0d, this.mScale, this.mFontSize);
        }
        return this.mDY;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public double nextRotation() {
        incrementIndices(this.mRIndices, this.mRsIndex);
        this.mRIndex = Math.min(this.mRIndex + 1, this.mRs.length - 1);
        return this.mRs[this.mRIndex];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public double nextX(double d) {
        incrementIndices(this.mXIndices, this.mXsIndex);
        int i = this.mXIndex + 1;
        SVGLength[] sVGLengthArr = this.mXs;
        if (i < sVGLengthArr.length) {
            this.mDX = 0.0d;
            this.mXIndex = i;
            this.f38mX = PropHelper.fromRelative(sVGLengthArr[i], this.mWidth, 0.0d, this.mScale, this.mFontSize);
        }
        this.f38mX += d;
        return this.f38mX;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public double nextY() {
        incrementIndices(this.mYIndices, this.mYsIndex);
        int i = this.mYIndex + 1;
        SVGLength[] sVGLengthArr = this.mYs;
        if (i < sVGLengthArr.length) {
            this.mDY = 0.0d;
            this.mYIndex = i;
            this.f39mY = PropHelper.fromRelative(sVGLengthArr[i], this.mHeight, 0.0d, this.mScale, this.mFontSize);
        }
        return this.f39mY;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void popContext() {
        this.mFontContext.remove(this.mTop);
        this.mXsIndices.remove(this.mTop);
        this.mYsIndices.remove(this.mTop);
        this.mDXsIndices.remove(this.mTop);
        this.mDYsIndices.remove(this.mTop);
        this.mRsIndices.remove(this.mTop);
        this.mTop--;
        int i = this.mXsIndex;
        int i2 = this.mYsIndex;
        int i3 = this.mDXsIndex;
        int i4 = this.mDYsIndex;
        int i5 = this.mRsIndex;
        this.topFont = this.mFontContext.get(this.mTop);
        this.mXsIndex = this.mXsIndices.get(this.mTop).intValue();
        this.mYsIndex = this.mYsIndices.get(this.mTop).intValue();
        this.mDXsIndex = this.mDXsIndices.get(this.mTop).intValue();
        this.mDYsIndex = this.mDYsIndices.get(this.mTop).intValue();
        this.mRsIndex = this.mRsIndices.get(this.mTop).intValue();
        if (i != this.mXsIndex) {
            this.mXsContext.remove(i);
            this.mXs = this.mXsContext.get(this.mXsIndex);
            this.mXIndex = this.mXIndices.get(this.mXsIndex).intValue();
        }
        if (i2 != this.mYsIndex) {
            this.mYsContext.remove(i2);
            this.mYs = this.mYsContext.get(this.mYsIndex);
            this.mYIndex = this.mYIndices.get(this.mYsIndex).intValue();
        }
        if (i3 != this.mDXsIndex) {
            this.mDXsContext.remove(i3);
            this.mDXs = this.mDXsContext.get(this.mDXsIndex);
            this.mDXIndex = this.mDXIndices.get(this.mDXsIndex).intValue();
        }
        if (i4 != this.mDYsIndex) {
            this.mDYsContext.remove(i4);
            this.mDYs = this.mDYsContext.get(this.mDYsIndex);
            this.mDYIndex = this.mDYIndices.get(this.mDYsIndex).intValue();
        }
        if (i5 != this.mRsIndex) {
            this.mRsContext.remove(i5);
            this.mRs = this.mRsContext.get(this.mRsIndex);
            this.mRIndex = this.mRIndices.get(this.mRsIndex).intValue();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void pushContext(GroupView groupView, @Nullable ReadableMap readableMap) {
        pushNodeAndFont(groupView, readableMap);
        pushIndices();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void pushContext(boolean z, TextView textView, @Nullable ReadableMap readableMap, @Nullable ArrayList<SVGLength> arrayList, @Nullable ArrayList<SVGLength> arrayList2, @Nullable ArrayList<SVGLength> arrayList3, @Nullable ArrayList<SVGLength> arrayList4, @Nullable ArrayList<SVGLength> arrayList5) {
        if (z) {
            reset();
        }
        pushNodeAndFont(textView, readableMap);
        if (arrayList != null && arrayList.size() != 0) {
            this.mXsIndex++;
            this.mXIndex = -1;
            this.mXIndices.add(Integer.valueOf(this.mXIndex));
            this.mXs = getStringArrayFromReadableArray(arrayList);
            this.mXsContext.add(this.mXs);
        }
        if (arrayList2 != null && arrayList2.size() != 0) {
            this.mYsIndex++;
            this.mYIndex = -1;
            this.mYIndices.add(Integer.valueOf(this.mYIndex));
            this.mYs = getStringArrayFromReadableArray(arrayList2);
            this.mYsContext.add(this.mYs);
        }
        if (arrayList3 != null && arrayList3.size() != 0) {
            this.mDXsIndex++;
            this.mDXIndex = -1;
            this.mDXIndices.add(Integer.valueOf(this.mDXIndex));
            this.mDXs = getStringArrayFromReadableArray(arrayList3);
            this.mDXsContext.add(this.mDXs);
        }
        if (arrayList4 != null && arrayList4.size() != 0) {
            this.mDYsIndex++;
            this.mDYIndex = -1;
            this.mDYIndices.add(Integer.valueOf(this.mDYIndex));
            this.mDYs = getStringArrayFromReadableArray(arrayList4);
            this.mDYsContext.add(this.mDYs);
        }
        if (arrayList5 != null && arrayList5.size() != 0) {
            this.mRsIndex++;
            this.mRIndex = -1;
            this.mRIndices.add(Integer.valueOf(this.mRIndex));
            this.mRs = getDoubleArrayFromReadableArray(arrayList5);
            this.mRsContext.add(this.mRs);
        }
        pushIndices();
    }
}
