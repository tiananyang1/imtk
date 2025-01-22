package com.horcrux.svg;

import android.view.View;
import com.horcrux.svg.TextProperties;
import java.util.ArrayList;

/* loaded from: classes08-dex2jar.jar:com/horcrux/svg/TextLayoutAlgorithm.class */
class TextLayoutAlgorithm {

    /* renamed from: com.horcrux.svg.TextLayoutAlgorithm$1 */
    /* loaded from: classes08-dex2jar.jar:com/horcrux/svg/TextLayoutAlgorithm$1.class */
    static /* synthetic */ class C01111 {
        static final /* synthetic */ int[] $SwitchMap$com$horcrux$svg$TextProperties$TextAnchor = new int[TextProperties.TextAnchor.values().length];

        /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:15:0x002f
            	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1166)
            	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:1022)
            	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:55)
            */
        static {
            /*
                com.horcrux.svg.TextProperties$TextAnchor[] r0 = com.horcrux.svg.TextProperties.TextAnchor.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.horcrux.svg.TextLayoutAlgorithm.C01111.$SwitchMap$com$horcrux$svg$TextProperties$TextAnchor = r0
                int[] r0 = com.horcrux.svg.TextLayoutAlgorithm.C01111.$SwitchMap$com$horcrux$svg$TextProperties$TextAnchor     // Catch: java.lang.NoSuchFieldError -> L2b
                com.horcrux.svg.TextProperties$TextAnchor r1 = com.horcrux.svg.TextProperties.TextAnchor.start     // Catch: java.lang.NoSuchFieldError -> L2b
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L2b
                r2 = 1
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L2b
            L14:
                int[] r0 = com.horcrux.svg.TextLayoutAlgorithm.C01111.$SwitchMap$com$horcrux$svg$TextProperties$TextAnchor     // Catch: java.lang.NoSuchFieldError -> L2b java.lang.NoSuchFieldError -> L2f
                com.horcrux.svg.TextProperties$TextAnchor r1 = com.horcrux.svg.TextProperties.TextAnchor.middle     // Catch: java.lang.NoSuchFieldError -> L2f
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L2f
                r2 = 2
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L2f
            L1f:
                int[] r0 = com.horcrux.svg.TextLayoutAlgorithm.C01111.$SwitchMap$com$horcrux$svg$TextProperties$TextAnchor     // Catch: java.lang.NoSuchFieldError -> L2f java.lang.NoSuchFieldError -> L33
                com.horcrux.svg.TextProperties$TextAnchor r1 = com.horcrux.svg.TextProperties.TextAnchor.end     // Catch: java.lang.NoSuchFieldError -> L33
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
            throw new UnsupportedOperationException("Method not decompiled: com.horcrux.svg.TextLayoutAlgorithm.C01111.m3585clinit():void");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes08-dex2jar.jar:com/horcrux/svg/TextLayoutAlgorithm$CharacterInformation.class */
    public class CharacterInformation {
        double advance;
        char character;
        TextView element;
        int index;

        /* renamed from: x */
        double f57x = 0.0d;

        /* renamed from: y */
        double f58y = 0.0d;
        double rotate = 0.0d;
        boolean hidden = false;
        boolean middle = false;
        boolean resolved = false;
        boolean xSpecified = false;
        boolean ySpecified = false;
        boolean addressable = true;
        boolean anchoredChunk = false;
        boolean rotateSpecified = false;
        boolean firstCharacterInResolvedDescendant = false;

        CharacterInformation(int i, char c) {
            this.index = i;
            this.character = c;
        }
    }

    /* loaded from: classes08-dex2jar.jar:com/horcrux/svg/TextLayoutAlgorithm$LayoutInput.class */
    class LayoutInput {
        boolean horizontal;
        TextView text;

        LayoutInput() {
        }
    }

    TextLayoutAlgorithm() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void getSubTreeTypographicCharacterPositions(ArrayList<TextPathView> arrayList, ArrayList<TextView> arrayList2, StringBuilder sb, View view, TextPathView textPathView) {
        if (!(view instanceof TSpanView)) {
            int i = 0;
            if (view instanceof TextPathView) {
                textPathView = (TextPathView) view;
                i = 0;
            }
            while (i < textPathView.getChildCount()) {
                getSubTreeTypographicCharacterPositions(arrayList, arrayList2, sb, textPathView.getChildAt(i), textPathView);
                i++;
            }
            return;
        }
        TSpanView tSpanView = (TSpanView) view;
        String str = tSpanView.mContent;
        if (str != null) {
            for (int i2 = 0; i2 < str.length(); i2++) {
                arrayList2.add(tSpanView);
                arrayList.add(textPathView);
            }
            sb.append(str);
            return;
        }
        int i3 = 0;
        while (true) {
            int i4 = i3;
            if (i4 >= tSpanView.getChildCount()) {
                return;
            }
            getSubTreeTypographicCharacterPositions(arrayList, arrayList2, sb, tSpanView.getChildAt(i4), textPathView);
            i3 = i4 + 1;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:112:0x03c5, code lost:            if (r0 == com.horcrux.svg.TextProperties.Direction.ltr) goto L92;     */
    /* JADX WARN: Code restructure failed: missing block: B:114:0x0356, code lost:            if (r26 == (r0 - 1)) goto L76;     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x03a7, code lost:            if (r0 == com.horcrux.svg.TextProperties.Direction.ltr) goto L93;     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x03c8, code lost:            r11 = r11 - r15;     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x03d0, code lost:            r11 = r11 - r13;     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [android.view.View, com.horcrux.svg.TextView] */
    /* JADX WARN: Type inference failed for: r0v35, types: [com.horcrux.svg.TextLayoutAlgorithm$1TextLengthResolver] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    com.horcrux.svg.TextLayoutAlgorithm.CharacterInformation[] layoutText(com.horcrux.svg.TextLayoutAlgorithm.LayoutInput r10) {
        /*
            Method dump skipped, instructions count: 1842
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.horcrux.svg.TextLayoutAlgorithm.layoutText(com.horcrux.svg.TextLayoutAlgorithm$LayoutInput):com.horcrux.svg.TextLayoutAlgorithm$CharacterInformation[]");
    }
}
