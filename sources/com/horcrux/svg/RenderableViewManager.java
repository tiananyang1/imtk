package com.horcrux.svg;

import android.graphics.Matrix;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.JavaOnlyMap;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.uimanager.DisplayMetricsHolder;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.MatrixMathHelper;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ReactShadowNode;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.TransformHelper;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.lang.reflect.Array;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* loaded from: classes08-dex2jar.jar:com/horcrux/svg/RenderableViewManager.class */
class RenderableViewManager extends ViewGroupManager<VirtualView> {
    private static final float CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER = 5.0f;
    private static final double EPSILON = 1.0E-5d;
    private static final int PERSPECTIVE_ARRAY_INVERTED_CAMERA_DISTANCE_INDEX = 2;
    private static final MatrixDecompositionContext sMatrixDecompositionContext = new MatrixDecompositionContext();
    private static final double[] sTransformDecompositionArray = new double[16];
    private final String mClassName;
    private final SVGClass svgClass;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.horcrux.svg.RenderableViewManager$2 */
    /* loaded from: classes08-dex2jar.jar:com/horcrux/svg/RenderableViewManager$2.class */
    public static /* synthetic */ class C01072 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$react$bridge$ReadableType;
        static final /* synthetic */ int[] $SwitchMap$com$horcrux$svg$RenderableViewManager$SVGClass = new int[SVGClass.values().length];

        /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:92:0x012c
            	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1166)
            	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:1022)
            	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:55)
            */
        static {
            /*
                Method dump skipped, instructions count: 330
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.horcrux.svg.RenderableViewManager.C01072.m3576clinit():void");
        }
    }

    /* loaded from: classes08-dex2jar.jar:com/horcrux/svg/RenderableViewManager$CircleViewManager.class */
    static class CircleViewManager extends RenderableViewManager {
        /* JADX INFO: Access modifiers changed from: package-private */
        public CircleViewManager() {
            super(SVGClass.RNSVGCircle);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.horcrux.svg.RenderableViewManager
        protected /* bridge */ /* synthetic */ void addEventEmitters(@Nonnull ThemedReactContext themedReactContext, @Nonnull View view) {
            super.addEventEmitters(themedReactContext, (VirtualView) view);
        }

        @Override // com.horcrux.svg.RenderableViewManager
        /* renamed from: createShadowNodeInstance */
        public /* bridge */ /* synthetic */ ReactShadowNode mo3574createShadowNodeInstance() {
            return super.mo3574createShadowNodeInstance();
        }

        @Override // com.horcrux.svg.RenderableViewManager
        @Nonnull
        protected /* bridge */ /* synthetic */ View createViewInstance(@Nonnull ThemedReactContext themedReactContext) {
            return super.createViewInstance(themedReactContext);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.horcrux.svg.RenderableViewManager
        protected /* bridge */ /* synthetic */ void onAfterUpdateTransaction(@Nonnull View view) {
            super.onAfterUpdateTransaction((VirtualView) view);
        }

        @ReactProp(name = "cx")
        public void setCx(CircleView circleView, Dynamic dynamic) {
            circleView.setCx(dynamic);
        }

        @ReactProp(name = "cy")
        public void setCy(CircleView circleView, Dynamic dynamic) {
            circleView.setCy(dynamic);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.horcrux.svg.RenderableViewManager
        @ReactProp(defaultFloat = 1.0f, name = "opacity")
        public /* bridge */ /* synthetic */ void setOpacity(@Nonnull View view, float f) {
            super.setOpacity((VirtualView) view, f);
        }

        @ReactProp(name = "r")
        public void setR(CircleView circleView, Dynamic dynamic) {
            circleView.setR(dynamic);
        }
    }

    /* loaded from: classes08-dex2jar.jar:com/horcrux/svg/RenderableViewManager$ClipPathViewManager.class */
    static class ClipPathViewManager extends GroupViewManager {
        /* JADX INFO: Access modifiers changed from: package-private */
        public ClipPathViewManager() {
            super(SVGClass.RNSVGClipPath);
        }
    }

    /* loaded from: classes08-dex2jar.jar:com/horcrux/svg/RenderableViewManager$DefsViewManager.class */
    static class DefsViewManager extends RenderableViewManager {
        /* JADX INFO: Access modifiers changed from: package-private */
        public DefsViewManager() {
            super(SVGClass.RNSVGDefs);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.horcrux.svg.RenderableViewManager
        protected /* bridge */ /* synthetic */ void addEventEmitters(@Nonnull ThemedReactContext themedReactContext, @Nonnull View view) {
            super.addEventEmitters(themedReactContext, (VirtualView) view);
        }

        @Override // com.horcrux.svg.RenderableViewManager
        /* renamed from: createShadowNodeInstance */
        public /* bridge */ /* synthetic */ ReactShadowNode mo3574createShadowNodeInstance() {
            return super.mo3574createShadowNodeInstance();
        }

        @Override // com.horcrux.svg.RenderableViewManager
        @Nonnull
        protected /* bridge */ /* synthetic */ View createViewInstance(@Nonnull ThemedReactContext themedReactContext) {
            return super.createViewInstance(themedReactContext);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.horcrux.svg.RenderableViewManager
        protected /* bridge */ /* synthetic */ void onAfterUpdateTransaction(@Nonnull View view) {
            super.onAfterUpdateTransaction((VirtualView) view);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.horcrux.svg.RenderableViewManager
        @ReactProp(defaultFloat = 1.0f, name = "opacity")
        public /* bridge */ /* synthetic */ void setOpacity(@Nonnull View view, float f) {
            super.setOpacity((VirtualView) view, f);
        }
    }

    /* loaded from: classes08-dex2jar.jar:com/horcrux/svg/RenderableViewManager$EllipseViewManager.class */
    static class EllipseViewManager extends RenderableViewManager {
        /* JADX INFO: Access modifiers changed from: package-private */
        public EllipseViewManager() {
            super(SVGClass.RNSVGEllipse);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.horcrux.svg.RenderableViewManager
        protected /* bridge */ /* synthetic */ void addEventEmitters(@Nonnull ThemedReactContext themedReactContext, @Nonnull View view) {
            super.addEventEmitters(themedReactContext, (VirtualView) view);
        }

        @Override // com.horcrux.svg.RenderableViewManager
        /* renamed from: createShadowNodeInstance */
        public /* bridge */ /* synthetic */ ReactShadowNode mo3574createShadowNodeInstance() {
            return super.mo3574createShadowNodeInstance();
        }

        @Override // com.horcrux.svg.RenderableViewManager
        @Nonnull
        protected /* bridge */ /* synthetic */ View createViewInstance(@Nonnull ThemedReactContext themedReactContext) {
            return super.createViewInstance(themedReactContext);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.horcrux.svg.RenderableViewManager
        protected /* bridge */ /* synthetic */ void onAfterUpdateTransaction(@Nonnull View view) {
            super.onAfterUpdateTransaction((VirtualView) view);
        }

        @ReactProp(name = "cx")
        public void setCx(EllipseView ellipseView, Dynamic dynamic) {
            ellipseView.setCx(dynamic);
        }

        @ReactProp(name = "cy")
        public void setCy(EllipseView ellipseView, Dynamic dynamic) {
            ellipseView.setCy(dynamic);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.horcrux.svg.RenderableViewManager
        @ReactProp(defaultFloat = 1.0f, name = "opacity")
        public /* bridge */ /* synthetic */ void setOpacity(@Nonnull View view, float f) {
            super.setOpacity((VirtualView) view, f);
        }

        @ReactProp(name = "rx")
        public void setRx(EllipseView ellipseView, Dynamic dynamic) {
            ellipseView.setRx(dynamic);
        }

        @ReactProp(name = "ry")
        public void setRy(EllipseView ellipseView, Dynamic dynamic) {
            ellipseView.setRy(dynamic);
        }
    }

    /* loaded from: classes08-dex2jar.jar:com/horcrux/svg/RenderableViewManager$GroupViewManager.class */
    static class GroupViewManager extends RenderableViewManager {
        /* JADX INFO: Access modifiers changed from: package-private */
        public GroupViewManager() {
            super(SVGClass.RNSVGGroup);
        }

        GroupViewManager(SVGClass sVGClass) {
            super(sVGClass);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.horcrux.svg.RenderableViewManager
        protected /* bridge */ /* synthetic */ void addEventEmitters(@Nonnull ThemedReactContext themedReactContext, @Nonnull View view) {
            super.addEventEmitters(themedReactContext, (VirtualView) view);
        }

        @Override // com.horcrux.svg.RenderableViewManager
        /* renamed from: createShadowNodeInstance */
        public /* bridge */ /* synthetic */ ReactShadowNode mo3574createShadowNodeInstance() {
            return super.mo3574createShadowNodeInstance();
        }

        @Override // com.horcrux.svg.RenderableViewManager
        @Nonnull
        protected /* bridge */ /* synthetic */ View createViewInstance(@Nonnull ThemedReactContext themedReactContext) {
            return super.createViewInstance(themedReactContext);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.horcrux.svg.RenderableViewManager
        protected /* bridge */ /* synthetic */ void onAfterUpdateTransaction(@Nonnull View view) {
            super.onAfterUpdateTransaction((VirtualView) view);
        }

        @ReactProp(name = "font")
        public void setFont(GroupView groupView, @Nullable ReadableMap readableMap) {
            groupView.setFont(readableMap);
        }

        @ReactProp(name = "fontSize")
        public void setFontSize(GroupView groupView, Dynamic dynamic) {
            JavaOnlyMap javaOnlyMap = new JavaOnlyMap();
            int i = C01072.$SwitchMap$com$facebook$react$bridge$ReadableType[dynamic.getType().ordinal()];
            if (i == 1) {
                javaOnlyMap.putDouble("fontSize", dynamic.asDouble());
            } else if (i != 2) {
                return;
            } else {
                javaOnlyMap.putString("fontSize", dynamic.asString());
            }
            groupView.setFont(javaOnlyMap);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.horcrux.svg.RenderableViewManager
        @ReactProp(defaultFloat = 1.0f, name = "opacity")
        public /* bridge */ /* synthetic */ void setOpacity(@Nonnull View view, float f) {
            super.setOpacity((VirtualView) view, f);
        }
    }

    /* loaded from: classes08-dex2jar.jar:com/horcrux/svg/RenderableViewManager$ImageViewManager.class */
    static class ImageViewManager extends RenderableViewManager {
        /* JADX INFO: Access modifiers changed from: package-private */
        public ImageViewManager() {
            super(SVGClass.RNSVGImage);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.horcrux.svg.RenderableViewManager
        protected /* bridge */ /* synthetic */ void addEventEmitters(@Nonnull ThemedReactContext themedReactContext, @Nonnull View view) {
            super.addEventEmitters(themedReactContext, (VirtualView) view);
        }

        @Override // com.horcrux.svg.RenderableViewManager
        /* renamed from: createShadowNodeInstance */
        public /* bridge */ /* synthetic */ ReactShadowNode mo3574createShadowNodeInstance() {
            return super.mo3574createShadowNodeInstance();
        }

        @Override // com.horcrux.svg.RenderableViewManager
        @Nonnull
        protected /* bridge */ /* synthetic */ View createViewInstance(@Nonnull ThemedReactContext themedReactContext) {
            return super.createViewInstance(themedReactContext);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.horcrux.svg.RenderableViewManager
        protected /* bridge */ /* synthetic */ void onAfterUpdateTransaction(@Nonnull View view) {
            super.onAfterUpdateTransaction((VirtualView) view);
        }

        @ReactProp(name = "align")
        public void setAlign(ImageView imageView, String str) {
            imageView.setAlign(str);
        }

        @ReactProp(name = SettingsJsonConstants.ICON_HEIGHT_KEY)
        public void setHeight(ImageView imageView, Dynamic dynamic) {
            imageView.setHeight(dynamic);
        }

        @ReactProp(name = "meetOrSlice")
        public void setMeetOrSlice(ImageView imageView, int i) {
            imageView.setMeetOrSlice(i);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.horcrux.svg.RenderableViewManager
        @ReactProp(defaultFloat = 1.0f, name = "opacity")
        public /* bridge */ /* synthetic */ void setOpacity(@Nonnull View view, float f) {
            super.setOpacity((VirtualView) view, f);
        }

        @ReactProp(name = "src")
        public void setSrc(ImageView imageView, @Nullable ReadableMap readableMap) {
            imageView.setSrc(readableMap);
        }

        @ReactProp(name = SettingsJsonConstants.ICON_WIDTH_KEY)
        public void setWidth(ImageView imageView, Dynamic dynamic) {
            imageView.setWidth(dynamic);
        }

        @ReactProp(name = "x")
        public void setX(ImageView imageView, Dynamic dynamic) {
            imageView.setX(dynamic);
        }

        @ReactProp(name = "y")
        public void setY(ImageView imageView, Dynamic dynamic) {
            imageView.setY(dynamic);
        }
    }

    /* loaded from: classes08-dex2jar.jar:com/horcrux/svg/RenderableViewManager$LineViewManager.class */
    static class LineViewManager extends RenderableViewManager {
        /* JADX INFO: Access modifiers changed from: package-private */
        public LineViewManager() {
            super(SVGClass.RNSVGLine);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.horcrux.svg.RenderableViewManager
        protected /* bridge */ /* synthetic */ void addEventEmitters(@Nonnull ThemedReactContext themedReactContext, @Nonnull View view) {
            super.addEventEmitters(themedReactContext, (VirtualView) view);
        }

        @Override // com.horcrux.svg.RenderableViewManager
        /* renamed from: createShadowNodeInstance */
        public /* bridge */ /* synthetic */ ReactShadowNode mo3574createShadowNodeInstance() {
            return super.mo3574createShadowNodeInstance();
        }

        @Override // com.horcrux.svg.RenderableViewManager
        @Nonnull
        protected /* bridge */ /* synthetic */ View createViewInstance(@Nonnull ThemedReactContext themedReactContext) {
            return super.createViewInstance(themedReactContext);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.horcrux.svg.RenderableViewManager
        protected /* bridge */ /* synthetic */ void onAfterUpdateTransaction(@Nonnull View view) {
            super.onAfterUpdateTransaction((VirtualView) view);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.horcrux.svg.RenderableViewManager
        @ReactProp(defaultFloat = 1.0f, name = "opacity")
        public /* bridge */ /* synthetic */ void setOpacity(@Nonnull View view, float f) {
            super.setOpacity((VirtualView) view, f);
        }

        @ReactProp(name = "x1")
        public void setX1(LineView lineView, Dynamic dynamic) {
            lineView.setX1(dynamic);
        }

        @ReactProp(name = "x2")
        public void setX2(LineView lineView, Dynamic dynamic) {
            lineView.setX2(dynamic);
        }

        @ReactProp(name = "y1")
        public void setY1(LineView lineView, Dynamic dynamic) {
            lineView.setY1(dynamic);
        }

        @ReactProp(name = "y2")
        public void setY2(LineView lineView, Dynamic dynamic) {
            lineView.setY2(dynamic);
        }
    }

    /* loaded from: classes08-dex2jar.jar:com/horcrux/svg/RenderableViewManager$LinearGradientManager.class */
    static class LinearGradientManager extends RenderableViewManager {
        /* JADX INFO: Access modifiers changed from: package-private */
        public LinearGradientManager() {
            super(SVGClass.RNSVGLinearGradient);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.horcrux.svg.RenderableViewManager
        protected /* bridge */ /* synthetic */ void addEventEmitters(@Nonnull ThemedReactContext themedReactContext, @Nonnull View view) {
            super.addEventEmitters(themedReactContext, (VirtualView) view);
        }

        @Override // com.horcrux.svg.RenderableViewManager
        /* renamed from: createShadowNodeInstance */
        public /* bridge */ /* synthetic */ ReactShadowNode mo3574createShadowNodeInstance() {
            return super.mo3574createShadowNodeInstance();
        }

        @Override // com.horcrux.svg.RenderableViewManager
        @Nonnull
        protected /* bridge */ /* synthetic */ View createViewInstance(@Nonnull ThemedReactContext themedReactContext) {
            return super.createViewInstance(themedReactContext);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.horcrux.svg.RenderableViewManager
        protected /* bridge */ /* synthetic */ void onAfterUpdateTransaction(@Nonnull View view) {
            super.onAfterUpdateTransaction((VirtualView) view);
        }

        @ReactProp(name = "gradient")
        public void setGradient(LinearGradientView linearGradientView, ReadableArray readableArray) {
            linearGradientView.setGradient(readableArray);
        }

        @ReactProp(name = "gradientTransform")
        public void setGradientTransform(LinearGradientView linearGradientView, @Nullable ReadableArray readableArray) {
            linearGradientView.setGradientTransform(readableArray);
        }

        @ReactProp(name = "gradientUnits")
        public void setGradientUnits(LinearGradientView linearGradientView, int i) {
            linearGradientView.setGradientUnits(i);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.horcrux.svg.RenderableViewManager
        @ReactProp(defaultFloat = 1.0f, name = "opacity")
        public /* bridge */ /* synthetic */ void setOpacity(@Nonnull View view, float f) {
            super.setOpacity((VirtualView) view, f);
        }

        @ReactProp(name = "x1")
        public void setX1(LinearGradientView linearGradientView, Dynamic dynamic) {
            linearGradientView.setX1(dynamic);
        }

        @ReactProp(name = "x2")
        public void setX2(LinearGradientView linearGradientView, Dynamic dynamic) {
            linearGradientView.setX2(dynamic);
        }

        @ReactProp(name = "y1")
        public void setY1(LinearGradientView linearGradientView, Dynamic dynamic) {
            linearGradientView.setY1(dynamic);
        }

        @ReactProp(name = "y2")
        public void setY2(LinearGradientView linearGradientView, Dynamic dynamic) {
            linearGradientView.setY2(dynamic);
        }
    }

    /* loaded from: classes08-dex2jar.jar:com/horcrux/svg/RenderableViewManager$MaskManager.class */
    static class MaskManager extends GroupViewManager {
        /* JADX INFO: Access modifiers changed from: package-private */
        public MaskManager() {
            super(SVGClass.RNSVGMask);
        }

        @ReactProp(name = SettingsJsonConstants.ICON_HEIGHT_KEY)
        public void setHeight(MaskView maskView, Dynamic dynamic) {
            maskView.setHeight(dynamic);
        }

        @ReactProp(name = "maskContentUnits")
        public void setMaskContentUnits(MaskView maskView, int i) {
            maskView.setMaskContentUnits(i);
        }

        @ReactProp(name = "maskTransform")
        public void setMaskTransform(MaskView maskView, @Nullable ReadableArray readableArray) {
            maskView.setMaskTransform(readableArray);
        }

        @ReactProp(name = "maskUnits")
        public void setMaskUnits(MaskView maskView, int i) {
            maskView.setMaskUnits(i);
        }

        @ReactProp(name = SettingsJsonConstants.ICON_WIDTH_KEY)
        public void setWidth(MaskView maskView, Dynamic dynamic) {
            maskView.setWidth(dynamic);
        }

        @ReactProp(name = "x")
        public void setX(MaskView maskView, Dynamic dynamic) {
            maskView.setX(dynamic);
        }

        @ReactProp(name = "y")
        public void setY(MaskView maskView, Dynamic dynamic) {
            maskView.setY(dynamic);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes08-dex2jar.jar:com/horcrux/svg/RenderableViewManager$MatrixDecompositionContext.class */
    public static class MatrixDecompositionContext extends MatrixMathHelper.MatrixDecompositionContext {
        final double[] perspective = new double[4];
        final double[] scale = new double[3];
        final double[] skew = new double[3];
        final double[] translation = new double[3];
        final double[] rotationDegrees = new double[3];

        MatrixDecompositionContext() {
        }
    }

    /* loaded from: classes08-dex2jar.jar:com/horcrux/svg/RenderableViewManager$PathViewManager.class */
    static class PathViewManager extends RenderableViewManager {
        /* JADX INFO: Access modifiers changed from: package-private */
        public PathViewManager() {
            super(SVGClass.RNSVGPath);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.horcrux.svg.RenderableViewManager
        protected /* bridge */ /* synthetic */ void addEventEmitters(@Nonnull ThemedReactContext themedReactContext, @Nonnull View view) {
            super.addEventEmitters(themedReactContext, (VirtualView) view);
        }

        @Override // com.horcrux.svg.RenderableViewManager
        /* renamed from: createShadowNodeInstance */
        public /* bridge */ /* synthetic */ ReactShadowNode mo3574createShadowNodeInstance() {
            return super.mo3574createShadowNodeInstance();
        }

        @Override // com.horcrux.svg.RenderableViewManager
        @Nonnull
        protected /* bridge */ /* synthetic */ View createViewInstance(@Nonnull ThemedReactContext themedReactContext) {
            return super.createViewInstance(themedReactContext);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.horcrux.svg.RenderableViewManager
        protected /* bridge */ /* synthetic */ void onAfterUpdateTransaction(@Nonnull View view) {
            super.onAfterUpdateTransaction((VirtualView) view);
        }

        @ReactProp(name = "d")
        public void setD(PathView pathView, String str) {
            pathView.setD(str);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.horcrux.svg.RenderableViewManager
        @ReactProp(defaultFloat = 1.0f, name = "opacity")
        public /* bridge */ /* synthetic */ void setOpacity(@Nonnull View view, float f) {
            super.setOpacity((VirtualView) view, f);
        }
    }

    /* loaded from: classes08-dex2jar.jar:com/horcrux/svg/RenderableViewManager$PatternManager.class */
    static class PatternManager extends GroupViewManager {
        /* JADX INFO: Access modifiers changed from: package-private */
        public PatternManager() {
            super(SVGClass.RNSVGPattern);
        }

        @ReactProp(name = "align")
        public void setAlign(PatternView patternView, String str) {
            patternView.setAlign(str);
        }

        @ReactProp(name = SettingsJsonConstants.ICON_HEIGHT_KEY)
        public void setHeight(PatternView patternView, Dynamic dynamic) {
            patternView.setHeight(dynamic);
        }

        @ReactProp(name = "meetOrSlice")
        public void setMeetOrSlice(PatternView patternView, int i) {
            patternView.setMeetOrSlice(i);
        }

        @ReactProp(name = "minX")
        public void setMinX(PatternView patternView, float f) {
            patternView.setMinX(f);
        }

        @ReactProp(name = "minY")
        public void setMinY(PatternView patternView, float f) {
            patternView.setMinY(f);
        }

        @ReactProp(name = "patternContentUnits")
        public void setPatternContentUnits(PatternView patternView, int i) {
            patternView.setPatternContentUnits(i);
        }

        @ReactProp(name = "patternTransform")
        public void setPatternTransform(PatternView patternView, @Nullable ReadableArray readableArray) {
            patternView.setPatternTransform(readableArray);
        }

        @ReactProp(name = "patternUnits")
        public void setPatternUnits(PatternView patternView, int i) {
            patternView.setPatternUnits(i);
        }

        @ReactProp(name = "vbHeight")
        public void setVbHeight(PatternView patternView, float f) {
            patternView.setVbHeight(f);
        }

        @ReactProp(name = "vbWidth")
        public void setVbWidth(PatternView patternView, float f) {
            patternView.setVbWidth(f);
        }

        @ReactProp(name = SettingsJsonConstants.ICON_WIDTH_KEY)
        public void setWidth(PatternView patternView, Dynamic dynamic) {
            patternView.setWidth(dynamic);
        }

        @ReactProp(name = "x")
        public void setX(PatternView patternView, Dynamic dynamic) {
            patternView.setX(dynamic);
        }

        @ReactProp(name = "y")
        public void setY(PatternView patternView, Dynamic dynamic) {
            patternView.setY(dynamic);
        }
    }

    /* loaded from: classes08-dex2jar.jar:com/horcrux/svg/RenderableViewManager$RadialGradientManager.class */
    static class RadialGradientManager extends RenderableViewManager {
        /* JADX INFO: Access modifiers changed from: package-private */
        public RadialGradientManager() {
            super(SVGClass.RNSVGRadialGradient);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.horcrux.svg.RenderableViewManager
        protected /* bridge */ /* synthetic */ void addEventEmitters(@Nonnull ThemedReactContext themedReactContext, @Nonnull View view) {
            super.addEventEmitters(themedReactContext, (VirtualView) view);
        }

        @Override // com.horcrux.svg.RenderableViewManager
        /* renamed from: createShadowNodeInstance */
        public /* bridge */ /* synthetic */ ReactShadowNode mo3574createShadowNodeInstance() {
            return super.mo3574createShadowNodeInstance();
        }

        @Override // com.horcrux.svg.RenderableViewManager
        @Nonnull
        protected /* bridge */ /* synthetic */ View createViewInstance(@Nonnull ThemedReactContext themedReactContext) {
            return super.createViewInstance(themedReactContext);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.horcrux.svg.RenderableViewManager
        protected /* bridge */ /* synthetic */ void onAfterUpdateTransaction(@Nonnull View view) {
            super.onAfterUpdateTransaction((VirtualView) view);
        }

        @ReactProp(name = "cx")
        public void setCx(RadialGradientView radialGradientView, Dynamic dynamic) {
            radialGradientView.setCx(dynamic);
        }

        @ReactProp(name = "cy")
        public void setCy(RadialGradientView radialGradientView, Dynamic dynamic) {
            radialGradientView.setCy(dynamic);
        }

        @ReactProp(name = "fx")
        public void setFx(RadialGradientView radialGradientView, Dynamic dynamic) {
            radialGradientView.setFx(dynamic);
        }

        @ReactProp(name = "fy")
        public void setFy(RadialGradientView radialGradientView, Dynamic dynamic) {
            radialGradientView.setFy(dynamic);
        }

        @ReactProp(name = "gradient")
        public void setGradient(RadialGradientView radialGradientView, ReadableArray readableArray) {
            radialGradientView.setGradient(readableArray);
        }

        @ReactProp(name = "gradientTransform")
        public void setGradientTransform(RadialGradientView radialGradientView, @Nullable ReadableArray readableArray) {
            radialGradientView.setGradientTransform(readableArray);
        }

        @ReactProp(name = "gradientUnits")
        public void setGradientUnits(RadialGradientView radialGradientView, int i) {
            radialGradientView.setGradientUnits(i);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.horcrux.svg.RenderableViewManager
        @ReactProp(defaultFloat = 1.0f, name = "opacity")
        public /* bridge */ /* synthetic */ void setOpacity(@Nonnull View view, float f) {
            super.setOpacity((VirtualView) view, f);
        }

        @ReactProp(name = "rx")
        public void setRx(RadialGradientView radialGradientView, Dynamic dynamic) {
            radialGradientView.setRx(dynamic);
        }

        @ReactProp(name = "ry")
        public void setRy(RadialGradientView radialGradientView, Dynamic dynamic) {
            radialGradientView.setRy(dynamic);
        }
    }

    /* loaded from: classes08-dex2jar.jar:com/horcrux/svg/RenderableViewManager$RectViewManager.class */
    static class RectViewManager extends RenderableViewManager {
        /* JADX INFO: Access modifiers changed from: package-private */
        public RectViewManager() {
            super(SVGClass.RNSVGRect);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.horcrux.svg.RenderableViewManager
        protected /* bridge */ /* synthetic */ void addEventEmitters(@Nonnull ThemedReactContext themedReactContext, @Nonnull View view) {
            super.addEventEmitters(themedReactContext, (VirtualView) view);
        }

        @Override // com.horcrux.svg.RenderableViewManager
        /* renamed from: createShadowNodeInstance */
        public /* bridge */ /* synthetic */ ReactShadowNode mo3574createShadowNodeInstance() {
            return super.mo3574createShadowNodeInstance();
        }

        @Override // com.horcrux.svg.RenderableViewManager
        @Nonnull
        protected /* bridge */ /* synthetic */ View createViewInstance(@Nonnull ThemedReactContext themedReactContext) {
            return super.createViewInstance(themedReactContext);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.horcrux.svg.RenderableViewManager
        protected /* bridge */ /* synthetic */ void onAfterUpdateTransaction(@Nonnull View view) {
            super.onAfterUpdateTransaction((VirtualView) view);
        }

        @ReactProp(name = SettingsJsonConstants.ICON_HEIGHT_KEY)
        public void setHeight(RectView rectView, Dynamic dynamic) {
            rectView.setHeight(dynamic);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.horcrux.svg.RenderableViewManager
        @ReactProp(defaultFloat = 1.0f, name = "opacity")
        public /* bridge */ /* synthetic */ void setOpacity(@Nonnull View view, float f) {
            super.setOpacity((VirtualView) view, f);
        }

        @ReactProp(name = "rx")
        public void setRx(RectView rectView, Dynamic dynamic) {
            rectView.setRx(dynamic);
        }

        @ReactProp(name = "ry")
        public void setRy(RectView rectView, Dynamic dynamic) {
            rectView.setRy(dynamic);
        }

        @ReactProp(name = SettingsJsonConstants.ICON_WIDTH_KEY)
        public void setWidth(RectView rectView, Dynamic dynamic) {
            rectView.setWidth(dynamic);
        }

        @ReactProp(name = "x")
        public void setX(RectView rectView, Dynamic dynamic) {
            rectView.setX(dynamic);
        }

        @ReactProp(name = "y")
        public void setY(RectView rectView, Dynamic dynamic) {
            rectView.setY(dynamic);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes08-dex2jar.jar:com/horcrux/svg/RenderableViewManager$RenderableShadowNode.class */
    public class RenderableShadowNode extends LayoutShadowNode {
        RenderableShadowNode() {
        }

        @ReactPropGroup(names = {"alignSelf", "alignItems", "collapsable", "flex", "flexBasis", "flexDirection", "flexGrow", "flexShrink", "flexWrap", "justifyContent", "overflow", "alignContent", "display", "position", "right", "top", "bottom", "left", "start", "end", SettingsJsonConstants.ICON_WIDTH_KEY, SettingsJsonConstants.ICON_HEIGHT_KEY, "minWidth", "maxWidth", "minHeight", "maxHeight", "margin", "marginVertical", "marginHorizontal", "marginLeft", "marginRight", "marginTop", "marginBottom", "marginStart", "marginEnd", "padding", "paddingVertical", "paddingHorizontal", "paddingLeft", "paddingRight", "paddingTop", "paddingBottom", "paddingStart", "paddingEnd", "borderWidth", "borderStartWidth", "borderEndWidth", "borderTopWidth", "borderBottomWidth", "borderLeftWidth", "borderRightWidth"})
        public void ignoreLayoutProps(int i, Dynamic dynamic) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes08-dex2jar.jar:com/horcrux/svg/RenderableViewManager$SVGClass.class */
    public enum SVGClass {
        RNSVGGroup,
        RNSVGPath,
        RNSVGText,
        RNSVGTSpan,
        RNSVGTextPath,
        RNSVGImage,
        RNSVGCircle,
        RNSVGEllipse,
        RNSVGLine,
        RNSVGRect,
        RNSVGClipPath,
        RNSVGDefs,
        RNSVGUse,
        RNSVGSymbol,
        RNSVGLinearGradient,
        RNSVGRadialGradient,
        RNSVGPattern,
        RNSVGMask
    }

    /* loaded from: classes08-dex2jar.jar:com/horcrux/svg/RenderableViewManager$SymbolManager.class */
    static class SymbolManager extends GroupViewManager {
        /* JADX INFO: Access modifiers changed from: package-private */
        public SymbolManager() {
            super(SVGClass.RNSVGSymbol);
        }

        @ReactProp(name = "align")
        public void setAlign(SymbolView symbolView, String str) {
            symbolView.setAlign(str);
        }

        @ReactProp(name = "meetOrSlice")
        public void setMeetOrSlice(SymbolView symbolView, int i) {
            symbolView.setMeetOrSlice(i);
        }

        @ReactProp(name = "minX")
        public void setMinX(SymbolView symbolView, float f) {
            symbolView.setMinX(f);
        }

        @ReactProp(name = "minY")
        public void setMinY(SymbolView symbolView, float f) {
            symbolView.setMinY(f);
        }

        @ReactProp(name = "vbHeight")
        public void setVbHeight(SymbolView symbolView, float f) {
            symbolView.setVbHeight(f);
        }

        @ReactProp(name = "vbWidth")
        public void setVbWidth(SymbolView symbolView, float f) {
            symbolView.setVbWidth(f);
        }
    }

    /* loaded from: classes08-dex2jar.jar:com/horcrux/svg/RenderableViewManager$TSpanViewManager.class */
    static class TSpanViewManager extends TextViewManager {
        /* JADX INFO: Access modifiers changed from: package-private */
        public TSpanViewManager() {
            super(SVGClass.RNSVGTSpan);
        }

        @ReactProp(name = "content")
        public void setContent(TSpanView tSpanView, @Nullable String str) {
            tSpanView.setContent(str);
        }
    }

    /* loaded from: classes08-dex2jar.jar:com/horcrux/svg/RenderableViewManager$TextPathViewManager.class */
    static class TextPathViewManager extends TextViewManager {
        /* JADX INFO: Access modifiers changed from: package-private */
        public TextPathViewManager() {
            super(SVGClass.RNSVGTextPath);
        }

        @ReactProp(name = "href")
        public void setHref(TextPathView textPathView, String str) {
            textPathView.setHref(str);
        }

        @ReactProp(name = "method")
        public void setMethod(TextPathView textPathView, @Nullable String str) {
            textPathView.setMethod(str);
        }

        @ReactProp(name = "midLine")
        public void setSharp(TextPathView textPathView, @Nullable String str) {
            textPathView.setSharp(str);
        }

        @ReactProp(name = "side")
        public void setSide(TextPathView textPathView, @Nullable String str) {
            textPathView.setSide(str);
        }

        @ReactProp(name = "spacing")
        public void setSpacing(TextPathView textPathView, @Nullable String str) {
            textPathView.setSpacing(str);
        }

        @ReactProp(name = "startOffset")
        public void setStartOffset(TextPathView textPathView, Dynamic dynamic) {
            textPathView.setStartOffset(dynamic);
        }
    }

    /* loaded from: classes08-dex2jar.jar:com/horcrux/svg/RenderableViewManager$TextViewManager.class */
    static class TextViewManager extends GroupViewManager {
        /* JADX INFO: Access modifiers changed from: package-private */
        public TextViewManager() {
            super(SVGClass.RNSVGText);
        }

        TextViewManager(SVGClass sVGClass) {
            super(sVGClass);
        }

        @ReactProp(name = "baselineShift")
        public void setBaselineShift(TextView textView, Dynamic dynamic) {
            textView.setBaselineShift(dynamic);
        }

        @ReactProp(name = "dx")
        public void setDeltaX(TextView textView, Dynamic dynamic) {
            textView.setDeltaX(dynamic);
        }

        @ReactProp(name = "dy")
        public void setDeltaY(TextView textView, Dynamic dynamic) {
            textView.setDeltaY(dynamic);
        }

        @ReactProp(name = "font")
        public void setFont(TextView textView, @Nullable ReadableMap readableMap) {
            textView.setFont(readableMap);
        }

        @ReactProp(name = "lengthAdjust")
        public void setLengthAdjust(TextView textView, @Nullable String str) {
            textView.setLengthAdjust(str);
        }

        @ReactProp(name = "alignmentBaseline")
        public void setMethod(TextView textView, @Nullable String str) {
            textView.setMethod(str);
        }

        @ReactProp(name = "rotate")
        public void setRotate(TextView textView, Dynamic dynamic) {
            textView.setRotate(dynamic);
        }

        @ReactProp(name = "textLength")
        public void setTextLength(TextView textView, Dynamic dynamic) {
            textView.setTextLength(dynamic);
        }

        @ReactProp(name = "verticalAlign")
        public void setVerticalAlign(TextView textView, @Nullable String str) {
            textView.setVerticalAlign(str);
        }

        @ReactProp(name = "x")
        public void setX(TextView textView, Dynamic dynamic) {
            textView.setPositionX(dynamic);
        }

        @ReactProp(name = "y")
        public void setY(TextView textView, Dynamic dynamic) {
            textView.setPositionY(dynamic);
        }
    }

    /* loaded from: classes08-dex2jar.jar:com/horcrux/svg/RenderableViewManager$UseViewManager.class */
    static class UseViewManager extends RenderableViewManager {
        /* JADX INFO: Access modifiers changed from: package-private */
        public UseViewManager() {
            super(SVGClass.RNSVGUse);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.horcrux.svg.RenderableViewManager
        protected /* bridge */ /* synthetic */ void addEventEmitters(@Nonnull ThemedReactContext themedReactContext, @Nonnull View view) {
            super.addEventEmitters(themedReactContext, (VirtualView) view);
        }

        @Override // com.horcrux.svg.RenderableViewManager
        /* renamed from: createShadowNodeInstance */
        public /* bridge */ /* synthetic */ ReactShadowNode mo3574createShadowNodeInstance() {
            return super.mo3574createShadowNodeInstance();
        }

        @Override // com.horcrux.svg.RenderableViewManager
        @Nonnull
        protected /* bridge */ /* synthetic */ View createViewInstance(@Nonnull ThemedReactContext themedReactContext) {
            return super.createViewInstance(themedReactContext);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.horcrux.svg.RenderableViewManager
        protected /* bridge */ /* synthetic */ void onAfterUpdateTransaction(@Nonnull View view) {
            super.onAfterUpdateTransaction((VirtualView) view);
        }

        @ReactProp(name = SettingsJsonConstants.ICON_HEIGHT_KEY)
        public void setHeight(UseView useView, Dynamic dynamic) {
            useView.setHeight(dynamic);
        }

        @ReactProp(name = "href")
        public void setHref(UseView useView, String str) {
            useView.setHref(str);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.horcrux.svg.RenderableViewManager
        @ReactProp(defaultFloat = 1.0f, name = "opacity")
        public /* bridge */ /* synthetic */ void setOpacity(@Nonnull View view, float f) {
            super.setOpacity((VirtualView) view, f);
        }

        @ReactProp(name = SettingsJsonConstants.ICON_WIDTH_KEY)
        public void setWidth(UseView useView, Dynamic dynamic) {
            useView.setWidth(dynamic);
        }

        @ReactProp(name = "x")
        public void setX(UseView useView, Dynamic dynamic) {
            useView.setX(dynamic);
        }

        @ReactProp(name = "y")
        public void setY(UseView useView, Dynamic dynamic) {
            useView.setY(dynamic);
        }
    }

    private RenderableViewManager(SVGClass sVGClass) {
        this.svgClass = sVGClass;
        this.mClassName = sVGClass.toString();
    }

    private static void decomposeMatrix() {
        double[] dArr = sMatrixDecompositionContext.perspective;
        double[] dArr2 = sMatrixDecompositionContext.scale;
        double[] dArr3 = sMatrixDecompositionContext.skew;
        double[] dArr4 = sMatrixDecompositionContext.translation;
        double[] dArr5 = sMatrixDecompositionContext.rotationDegrees;
        if (isZero(sTransformDecompositionArray[15])) {
            return;
        }
        double[][] dArr6 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, 4, 4);
        double[] dArr7 = new double[16];
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= 4) {
                break;
            }
            int i3 = 0;
            while (true) {
                int i4 = i3;
                if (i4 < 4) {
                    double[] dArr8 = sTransformDecompositionArray;
                    int i5 = (i2 * 4) + i4;
                    double d = dArr8[i5] / dArr8[15];
                    dArr6[i2][i4] = d;
                    if (i4 == 3) {
                        d = 0.0d;
                    }
                    dArr7[i5] = d;
                    i3 = i4 + 1;
                }
            }
            i = i2 + 1;
        }
        dArr7[15] = 1.0d;
        if (isZero(MatrixMathHelper.determinant(dArr7))) {
            return;
        }
        if (isZero(dArr6[0][3]) && isZero(dArr6[1][3]) && isZero(dArr6[2][3])) {
            dArr[2] = 0.0d;
            dArr[1] = 0.0d;
            dArr[0] = 0.0d;
            dArr[3] = 1.0d;
        } else {
            MatrixMathHelper.multiplyVectorByMatrix(new double[]{dArr6[0][3], dArr6[1][3], dArr6[2][3], dArr6[3][3]}, MatrixMathHelper.transpose(MatrixMathHelper.inverse(dArr7)), dArr);
        }
        System.arraycopy(dArr6[3], 0, dArr4, 0, 3);
        double[][] dArr9 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, 3, 3);
        int i6 = 0;
        while (true) {
            int i7 = i6;
            if (i7 >= 3) {
                break;
            }
            dArr9[i7][0] = dArr6[i7][0];
            dArr9[i7][1] = dArr6[i7][1];
            dArr9[i7][2] = dArr6[i7][2];
            i6 = i7 + 1;
        }
        dArr2[0] = MatrixMathHelper.v3Length(dArr9[0]);
        dArr9[0] = MatrixMathHelper.v3Normalize(dArr9[0], dArr2[0]);
        dArr3[0] = MatrixMathHelper.v3Dot(dArr9[0], dArr9[1]);
        dArr9[1] = MatrixMathHelper.v3Combine(dArr9[1], dArr9[0], 1.0d, -dArr3[0]);
        dArr3[0] = MatrixMathHelper.v3Dot(dArr9[0], dArr9[1]);
        dArr9[1] = MatrixMathHelper.v3Combine(dArr9[1], dArr9[0], 1.0d, -dArr3[0]);
        dArr2[1] = MatrixMathHelper.v3Length(dArr9[1]);
        dArr9[1] = MatrixMathHelper.v3Normalize(dArr9[1], dArr2[1]);
        dArr3[0] = dArr3[0] / dArr2[1];
        dArr3[1] = MatrixMathHelper.v3Dot(dArr9[0], dArr9[2]);
        dArr9[2] = MatrixMathHelper.v3Combine(dArr9[2], dArr9[0], 1.0d, -dArr3[1]);
        dArr3[2] = MatrixMathHelper.v3Dot(dArr9[1], dArr9[2]);
        dArr9[2] = MatrixMathHelper.v3Combine(dArr9[2], dArr9[1], 1.0d, -dArr3[2]);
        dArr2[2] = MatrixMathHelper.v3Length(dArr9[2]);
        dArr9[2] = MatrixMathHelper.v3Normalize(dArr9[2], dArr2[2]);
        dArr3[1] = dArr3[1] / dArr2[2];
        dArr3[2] = dArr3[2] / dArr2[2];
        if (MatrixMathHelper.v3Dot(dArr9[0], MatrixMathHelper.v3Cross(dArr9[1], dArr9[2])) < 0.0d) {
            int i8 = 0;
            while (true) {
                int i9 = i8;
                if (i9 >= 3) {
                    break;
                }
                dArr2[i9] = dArr2[i9] * (-1.0d);
                double[] dArr10 = dArr9[i9];
                dArr10[0] = dArr10[0] * (-1.0d);
                double[] dArr11 = dArr9[i9];
                dArr11[1] = dArr11[1] * (-1.0d);
                double[] dArr12 = dArr9[i9];
                dArr12[2] = dArr12[2] * (-1.0d);
                i8 = i9 + 1;
            }
        }
        dArr5[0] = MatrixMathHelper.roundTo3Places((-Math.atan2(dArr9[2][1], dArr9[2][2])) * 57.29577951308232d);
        dArr5[1] = MatrixMathHelper.roundTo3Places((-Math.atan2(-dArr9[2][0], Math.sqrt((dArr9[2][1] * dArr9[2][1]) + (dArr9[2][2] * dArr9[2][2])))) * 57.29577951308232d);
        dArr5[2] = MatrixMathHelper.roundTo3Places((-Math.atan2(dArr9[1][0], dArr9[0][0])) * 57.29577951308232d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void invalidateSvgView(VirtualView virtualView) {
        SvgView svgView = virtualView.getSvgView();
        if (svgView != null) {
            svgView.invalidate();
        }
        if (virtualView instanceof TextView) {
            ((TextView) virtualView).getTextContainer().clearChildCache();
        }
    }

    private static boolean isZero(double d) {
        return !Double.isNaN(d) && Math.abs(d) < EPSILON;
    }

    private static void resetTransformProperty(View view) {
        view.setTranslationX(PixelUtil.toPixelFromDIP(0.0f));
        view.setTranslationY(PixelUtil.toPixelFromDIP(0.0f));
        view.setRotation(0.0f);
        view.setRotationX(0.0f);
        view.setRotationY(0.0f);
        view.setScaleX(1.0f);
        view.setScaleY(1.0f);
        view.setCameraDistance(0.0f);
    }

    private static void setTransformProperty(View view, ReadableArray readableArray) {
        TransformHelper.processTransform(readableArray, sTransformDecompositionArray);
        decomposeMatrix();
        view.setTranslationX(PixelUtil.toPixelFromDIP((float) sMatrixDecompositionContext.translation[0]));
        view.setTranslationY(PixelUtil.toPixelFromDIP((float) sMatrixDecompositionContext.translation[1]));
        view.setRotation((float) sMatrixDecompositionContext.rotationDegrees[2]);
        view.setRotationX((float) sMatrixDecompositionContext.rotationDegrees[0]);
        view.setRotationY((float) sMatrixDecompositionContext.rotationDegrees[1]);
        view.setScaleX((float) sMatrixDecompositionContext.scale[0]);
        view.setScaleY((float) sMatrixDecompositionContext.scale[1]);
        double[] dArr = sMatrixDecompositionContext.perspective;
        if (dArr.length > 2) {
            float f = (float) dArr[2];
            float f2 = f;
            if (f == 0.0f) {
                f2 = 7.8125E-4f;
            }
            float f3 = DisplayMetricsHolder.getScreenDisplayMetrics().density;
            view.setCameraDistance(f3 * f3 * ((-1.0f) / f2) * CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // 
    public void addEventEmitters(@Nonnull ThemedReactContext themedReactContext, @Nonnull VirtualView virtualView) {
        super.addEventEmitters(themedReactContext, virtualView);
        virtualView.setOnHierarchyChangeListener(new ViewGroup.OnHierarchyChangeListener() { // from class: com.horcrux.svg.RenderableViewManager.1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // android.view.ViewGroup.OnHierarchyChangeListener
            public void onChildViewAdded(View view, View view2) {
                if (view instanceof VirtualView) {
                    RenderableViewManager.this.invalidateSvgView((VirtualView) view);
                }
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // android.view.ViewGroup.OnHierarchyChangeListener
            public void onChildViewRemoved(View view, View view2) {
                if (view instanceof VirtualView) {
                    RenderableViewManager.this.invalidateSvgView((VirtualView) view);
                }
            }
        });
    }

    @Override // 
    /* renamed from: createShadowNodeInstance, reason: merged with bridge method [inline-methods] */
    public LayoutShadowNode mo3574createShadowNodeInstance() {
        return new RenderableShadowNode();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // 
    @Nonnull
    public VirtualView createViewInstance(@Nonnull ThemedReactContext themedReactContext) {
        switch (C01072.$SwitchMap$com$horcrux$svg$RenderableViewManager$SVGClass[this.svgClass.ordinal()]) {
            case 1:
                return new GroupView(themedReactContext);
            case 2:
                return new PathView(themedReactContext);
            case 3:
                return new CircleView(themedReactContext);
            case 4:
                return new EllipseView(themedReactContext);
            case 5:
                return new LineView(themedReactContext);
            case 6:
                return new RectView(themedReactContext);
            case 7:
                return new TextView(themedReactContext);
            case 8:
                return new TSpanView(themedReactContext);
            case 9:
                return new TextPathView(themedReactContext);
            case 10:
                return new ImageView(themedReactContext);
            case 11:
                return new ClipPathView(themedReactContext);
            case 12:
                return new DefsView(themedReactContext);
            case 13:
                return new UseView(themedReactContext);
            case 14:
                return new SymbolView(themedReactContext);
            case 15:
                return new LinearGradientView(themedReactContext);
            case 16:
                return new RadialGradientView(themedReactContext);
            case 17:
                return new PatternView(themedReactContext);
            case 18:
                return new MaskView(themedReactContext);
            default:
                throw new IllegalStateException("Unexpected type " + this.svgClass.toString());
        }
    }

    @Nonnull
    public String getName() {
        return this.mClassName;
    }

    public Class<RenderableShadowNode> getShadowNodeClass() {
        return RenderableShadowNode.class;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // 
    public void onAfterUpdateTransaction(@Nonnull VirtualView virtualView) {
        super.onAfterUpdateTransaction(virtualView);
        invalidateSvgView(virtualView);
    }

    @ReactProp(name = "clipPath")
    public void setClipPath(VirtualView virtualView, String str) {
        virtualView.setClipPath(str);
    }

    @ReactProp(name = "clipRule")
    public void setClipRule(VirtualView virtualView, int i) {
        virtualView.setClipRule(i);
    }

    @ReactProp(name = "fill")
    public void setFill(RenderableView renderableView, @Nullable Dynamic dynamic) {
        renderableView.setFill(dynamic);
    }

    @ReactProp(defaultFloat = 1.0f, name = "fillOpacity")
    public void setFillOpacity(RenderableView renderableView, float f) {
        renderableView.setFillOpacity(f);
    }

    @ReactProp(defaultInt = 1, name = "fillRule")
    public void setFillRule(RenderableView renderableView, int i) {
        renderableView.setFillRule(i);
    }

    @ReactProp(name = "mask")
    public void setMask(VirtualView virtualView, String str) {
        virtualView.setMask(str);
    }

    @ReactProp(name = "matrix")
    public void setMatrix(VirtualView virtualView, Dynamic dynamic) {
        virtualView.setMatrix(dynamic);
    }

    @ReactProp(name = "name")
    public void setName(VirtualView virtualView, String str) {
        virtualView.setName(str);
    }

    @Override // 
    @ReactProp(defaultFloat = 1.0f, name = "opacity")
    public void setOpacity(@Nonnull VirtualView virtualView, float f) {
        virtualView.setOpacity(f);
    }

    @ReactProp(name = "propList")
    public void setPropList(RenderableView renderableView, @Nullable ReadableArray readableArray) {
        renderableView.setPropList(readableArray);
    }

    @ReactProp(name = "responsible")
    public void setResponsible(VirtualView virtualView, boolean z) {
        virtualView.setResponsible(z);
    }

    @ReactProp(name = "stroke")
    public void setStroke(RenderableView renderableView, @Nullable Dynamic dynamic) {
        renderableView.setStroke(dynamic);
    }

    @ReactProp(name = "strokeDasharray")
    public void setStrokeDasharray(RenderableView renderableView, @Nullable ReadableArray readableArray) {
        renderableView.setStrokeDasharray(readableArray);
    }

    @ReactProp(name = "strokeDashoffset")
    public void setStrokeDashoffset(RenderableView renderableView, float f) {
        renderableView.setStrokeDashoffset(f);
    }

    @ReactProp(defaultInt = 1, name = "strokeLinecap")
    public void setStrokeLinecap(RenderableView renderableView, int i) {
        renderableView.setStrokeLinecap(i);
    }

    @ReactProp(defaultInt = 1, name = "strokeLinejoin")
    public void setStrokeLinejoin(RenderableView renderableView, int i) {
        renderableView.setStrokeLinejoin(i);
    }

    @ReactProp(defaultFloat = 4.0f, name = "strokeMiterlimit")
    public void setStrokeMiterlimit(RenderableView renderableView, float f) {
        renderableView.setStrokeMiterlimit(f);
    }

    @ReactProp(defaultFloat = 1.0f, name = "strokeOpacity")
    public void setStrokeOpacity(RenderableView renderableView, float f) {
        renderableView.setStrokeOpacity(f);
    }

    @ReactProp(name = "strokeWidth")
    public void setStrokeWidth(RenderableView renderableView, Dynamic dynamic) {
        renderableView.setStrokeWidth(dynamic);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @ReactProp(name = "transform")
    public void setTransform(VirtualView virtualView, Dynamic dynamic) {
        if (dynamic.getType() != ReadableType.Array) {
            return;
        }
        ReadableArray asArray = dynamic.asArray();
        if (asArray == null) {
            resetTransformProperty(virtualView);
        } else {
            setTransformProperty(virtualView, asArray);
        }
        Matrix matrix = virtualView.getMatrix();
        virtualView.mTransform = matrix;
        virtualView.mTransformInvertible = matrix.invert(virtualView.mInvTransform);
    }

    @ReactProp(name = "vectorEffect")
    public void setVectorEffect(RenderableView renderableView, int i) {
        renderableView.setVectorEffect(i);
    }
}
