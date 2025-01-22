package com.swmansion.gesturehandler.react;

import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.react.uimanager.PointerEvents;
import com.facebook.react.uimanager.ReactPointerEventsView;
import com.facebook.react.views.view.ReactViewGroup;
import com.swmansion.gesturehandler.PointerEventsConfig;
import com.swmansion.gesturehandler.ViewConfigurationHelper;

/* loaded from: classes08-dex2jar.jar:com/swmansion/gesturehandler/react/RNViewConfigurationHelper.class */
public class RNViewConfigurationHelper implements ViewConfigurationHelper {

    /* renamed from: com.swmansion.gesturehandler.react.RNViewConfigurationHelper$1 */
    /* loaded from: classes08-dex2jar.jar:com/swmansion/gesturehandler/react/RNViewConfigurationHelper$1.class */
    static /* synthetic */ class C03951 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$react$uimanager$PointerEvents = new int[PointerEvents.values().length];

        /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:15:0x002f
            	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1166)
            	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:1022)
            	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:55)
            */
        static {
            /*
                com.facebook.react.uimanager.PointerEvents[] r0 = com.facebook.react.uimanager.PointerEvents.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.swmansion.gesturehandler.react.RNViewConfigurationHelper.C03951.$SwitchMap$com$facebook$react$uimanager$PointerEvents = r0
                int[] r0 = com.swmansion.gesturehandler.react.RNViewConfigurationHelper.C03951.$SwitchMap$com$facebook$react$uimanager$PointerEvents     // Catch: java.lang.NoSuchFieldError -> L2b
                com.facebook.react.uimanager.PointerEvents r1 = com.facebook.react.uimanager.PointerEvents.BOX_ONLY     // Catch: java.lang.NoSuchFieldError -> L2b
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L2b
                r2 = 1
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L2b
            L14:
                int[] r0 = com.swmansion.gesturehandler.react.RNViewConfigurationHelper.C03951.$SwitchMap$com$facebook$react$uimanager$PointerEvents     // Catch: java.lang.NoSuchFieldError -> L2b java.lang.NoSuchFieldError -> L2f
                com.facebook.react.uimanager.PointerEvents r1 = com.facebook.react.uimanager.PointerEvents.BOX_NONE     // Catch: java.lang.NoSuchFieldError -> L2f
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L2f
                r2 = 2
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L2f
            L1f:
                int[] r0 = com.swmansion.gesturehandler.react.RNViewConfigurationHelper.C03951.$SwitchMap$com$facebook$react$uimanager$PointerEvents     // Catch: java.lang.NoSuchFieldError -> L2f java.lang.NoSuchFieldError -> L33
                com.facebook.react.uimanager.PointerEvents r1 = com.facebook.react.uimanager.PointerEvents.NONE     // Catch: java.lang.NoSuchFieldError -> L33
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L33
                r2 = 3
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L33
                return
            L2b:
                r4 = move-exception
                goto L14
            L2f:
                r4 = move-exception
                goto L1f
            L33:
                r4 = move-exception
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.swmansion.gesturehandler.react.RNViewConfigurationHelper.C03951.m3927clinit():void");
        }
    }

    @Override // com.swmansion.gesturehandler.ViewConfigurationHelper
    public View getChildInDrawingOrderAtIndex(ViewGroup viewGroup, int i) {
        return viewGroup instanceof ReactViewGroup ? viewGroup.getChildAt(((ReactViewGroup) viewGroup).getZIndexMappedChildIndex(i)) : viewGroup.getChildAt(i);
    }

    @Override // com.swmansion.gesturehandler.ViewConfigurationHelper
    public PointerEventsConfig getPointerEventsConfigForView(View view) {
        PointerEvents pointerEvents = view instanceof ReactPointerEventsView ? ((ReactPointerEventsView) view).getPointerEvents() : PointerEvents.AUTO;
        if (!view.isEnabled()) {
            if (pointerEvents == PointerEvents.AUTO) {
                return PointerEventsConfig.BOX_NONE;
            }
            if (pointerEvents == PointerEvents.BOX_ONLY) {
                return PointerEventsConfig.NONE;
            }
        }
        int i = C03951.$SwitchMap$com$facebook$react$uimanager$PointerEvents[pointerEvents.ordinal()];
        return i != 1 ? i != 2 ? i != 3 ? PointerEventsConfig.AUTO : PointerEventsConfig.NONE : PointerEventsConfig.BOX_NONE : PointerEventsConfig.BOX_ONLY;
    }

    @Override // com.swmansion.gesturehandler.ViewConfigurationHelper
    public boolean isViewClippingChildren(ViewGroup viewGroup) {
        if (Build.VERSION.SDK_INT < 18 || viewGroup.getClipChildren()) {
            return true;
        }
        if (viewGroup instanceof ReactViewGroup) {
            return "hidden".equals(((ReactViewGroup) viewGroup).getOverflow());
        }
        return false;
    }
}
