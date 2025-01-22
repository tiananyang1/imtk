package com.yqritc.scalablevideoview;

import android.graphics.Matrix;

/* loaded from: classes08-dex2jar.jar:com/yqritc/scalablevideoview/ScaleManager.class */
public class ScaleManager {
    private Size mVideoSize;
    private Size mViewSize;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.yqritc.scalablevideoview.ScaleManager$1 */
    /* loaded from: classes08-dex2jar.jar:com/yqritc/scalablevideoview/ScaleManager$1.class */
    public static /* synthetic */ class C08861 {
        static final /* synthetic */ int[] $SwitchMap$com$yqritc$scalablevideoview$PivotPoint = new int[PivotPoint.values().length];
        static final /* synthetic */ int[] $SwitchMap$com$yqritc$scalablevideoview$ScalableType;

        /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:177:0x020d
            	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1166)
            	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:1022)
            	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:55)
            */
        static {
            /*
                Method dump skipped, instructions count: 567
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.yqritc.scalablevideoview.ScaleManager.C08861.m4093clinit():void");
        }
    }

    public ScaleManager(Size size, Size size2) {
        this.mViewSize = size;
        this.mVideoSize = size2;
    }

    private Matrix centerInside() {
        return (this.mVideoSize.getHeight() > this.mViewSize.getWidth() || this.mVideoSize.getHeight() > this.mViewSize.getHeight()) ? fitCenter() : getOriginalScale(PivotPoint.CENTER);
    }

    private Matrix endInside() {
        return (this.mVideoSize.getHeight() > this.mViewSize.getWidth() || this.mVideoSize.getHeight() > this.mViewSize.getHeight()) ? fitEnd() : getOriginalScale(PivotPoint.RIGHT_BOTTOM);
    }

    private Matrix fitCenter() {
        return getFitScale(PivotPoint.CENTER);
    }

    private Matrix fitEnd() {
        return getFitScale(PivotPoint.RIGHT_BOTTOM);
    }

    private Matrix fitStart() {
        return getFitScale(PivotPoint.LEFT_TOP);
    }

    private Matrix fitXY() {
        return getMatrix(1.0f, 1.0f, PivotPoint.LEFT_TOP);
    }

    private Matrix getCropScale(PivotPoint pivotPoint) {
        float width = this.mViewSize.getWidth() / this.mVideoSize.getWidth();
        float height = this.mViewSize.getHeight() / this.mVideoSize.getHeight();
        float max = Math.max(width, height);
        return getMatrix(max / width, max / height, pivotPoint);
    }

    private Matrix getFitScale(PivotPoint pivotPoint) {
        float width = this.mViewSize.getWidth() / this.mVideoSize.getWidth();
        float height = this.mViewSize.getHeight() / this.mVideoSize.getHeight();
        float min = Math.min(width, height);
        return getMatrix(min / width, min / height, pivotPoint);
    }

    private Matrix getMatrix(float f, float f2, float f3, float f4) {
        Matrix matrix = new Matrix();
        matrix.setScale(f, f2, f3, f4);
        return matrix;
    }

    private Matrix getMatrix(float f, float f2, PivotPoint pivotPoint) {
        switch (C08861.$SwitchMap$com$yqritc$scalablevideoview$PivotPoint[pivotPoint.ordinal()]) {
            case 1:
                return getMatrix(f, f2, 0.0f, 0.0f);
            case 2:
                return getMatrix(f, f2, 0.0f, this.mViewSize.getHeight() / 2.0f);
            case 3:
                return getMatrix(f, f2, 0.0f, this.mViewSize.getHeight());
            case 4:
                return getMatrix(f, f2, this.mViewSize.getWidth() / 2.0f, 0.0f);
            case 5:
                return getMatrix(f, f2, this.mViewSize.getWidth() / 2.0f, this.mViewSize.getHeight() / 2.0f);
            case 6:
                return getMatrix(f, f2, this.mViewSize.getWidth() / 2.0f, this.mViewSize.getHeight());
            case 7:
                return getMatrix(f, f2, this.mViewSize.getWidth(), 0.0f);
            case 8:
                return getMatrix(f, f2, this.mViewSize.getWidth(), this.mViewSize.getHeight() / 2.0f);
            case 9:
                return getMatrix(f, f2, this.mViewSize.getWidth(), this.mViewSize.getHeight());
            default:
                throw new IllegalArgumentException("Illegal PivotPoint");
        }
    }

    private Matrix getNoScale() {
        return getMatrix(this.mVideoSize.getWidth() / this.mViewSize.getWidth(), this.mVideoSize.getHeight() / this.mViewSize.getHeight(), PivotPoint.LEFT_TOP);
    }

    private Matrix getOriginalScale(PivotPoint pivotPoint) {
        return getMatrix(this.mVideoSize.getWidth() / this.mViewSize.getWidth(), this.mVideoSize.getHeight() / this.mViewSize.getHeight(), pivotPoint);
    }

    private Matrix startInside() {
        return (this.mVideoSize.getHeight() > this.mViewSize.getWidth() || this.mVideoSize.getHeight() > this.mViewSize.getHeight()) ? fitStart() : getOriginalScale(PivotPoint.LEFT_TOP);
    }

    public Matrix getScaleMatrix(ScalableType scalableType) {
        switch (C08861.$SwitchMap$com$yqritc$scalablevideoview$ScalableType[scalableType.ordinal()]) {
            case 1:
                return getNoScale();
            case 2:
                return fitXY();
            case 3:
                return fitCenter();
            case 4:
                return fitStart();
            case 5:
                return fitEnd();
            case 6:
                return getOriginalScale(PivotPoint.LEFT_TOP);
            case 7:
                return getOriginalScale(PivotPoint.LEFT_CENTER);
            case 8:
                return getOriginalScale(PivotPoint.LEFT_BOTTOM);
            case 9:
                return getOriginalScale(PivotPoint.CENTER_TOP);
            case 10:
                return getOriginalScale(PivotPoint.CENTER);
            case 11:
                return getOriginalScale(PivotPoint.CENTER_BOTTOM);
            case 12:
                return getOriginalScale(PivotPoint.RIGHT_TOP);
            case 13:
                return getOriginalScale(PivotPoint.RIGHT_CENTER);
            case 14:
                return getOriginalScale(PivotPoint.RIGHT_BOTTOM);
            case 15:
                return getCropScale(PivotPoint.LEFT_TOP);
            case 16:
                return getCropScale(PivotPoint.LEFT_CENTER);
            case 17:
                return getCropScale(PivotPoint.LEFT_BOTTOM);
            case 18:
                return getCropScale(PivotPoint.CENTER_TOP);
            case 19:
                return getCropScale(PivotPoint.CENTER);
            case 20:
                return getCropScale(PivotPoint.CENTER_BOTTOM);
            case 21:
                return getCropScale(PivotPoint.RIGHT_TOP);
            case 22:
                return getCropScale(PivotPoint.RIGHT_CENTER);
            case 23:
                return getCropScale(PivotPoint.RIGHT_BOTTOM);
            case 24:
                return startInside();
            case 25:
                return centerInside();
            case 26:
                return endInside();
            default:
                return null;
        }
    }
}
