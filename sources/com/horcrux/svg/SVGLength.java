package com.horcrux.svg;

import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableType;
import java.util.ArrayList;

/* loaded from: classes08-dex2jar.jar:com/horcrux/svg/SVGLength.class */
class SVGLength {
    final SVGLengthUnitType unit;
    final double value;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.horcrux.svg.SVGLength$1 */
    /* loaded from: classes08-dex2jar.jar:com/horcrux/svg/SVGLength$1.class */
    public static /* synthetic */ class C01081 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$react$bridge$ReadableType = new int[ReadableType.values().length];

        /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:15:0x002f
            	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1166)
            	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:1022)
            	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:55)
            */
        static {
            /*
                com.facebook.react.bridge.ReadableType[] r0 = com.facebook.react.bridge.ReadableType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.horcrux.svg.SVGLength.C01081.$SwitchMap$com$facebook$react$bridge$ReadableType = r0
                int[] r0 = com.horcrux.svg.SVGLength.C01081.$SwitchMap$com$facebook$react$bridge$ReadableType     // Catch: java.lang.NoSuchFieldError -> L2b
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Number     // Catch: java.lang.NoSuchFieldError -> L2b
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L2b
                r2 = 1
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L2b
            L14:
                int[] r0 = com.horcrux.svg.SVGLength.C01081.$SwitchMap$com$facebook$react$bridge$ReadableType     // Catch: java.lang.NoSuchFieldError -> L2b java.lang.NoSuchFieldError -> L2f
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.String     // Catch: java.lang.NoSuchFieldError -> L2f
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L2f
                r2 = 2
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L2f
            L1f:
                int[] r0 = com.horcrux.svg.SVGLength.C01081.$SwitchMap$com$facebook$react$bridge$ReadableType     // Catch: java.lang.NoSuchFieldError -> L2f java.lang.NoSuchFieldError -> L33
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Array     // Catch: java.lang.NoSuchFieldError -> L33
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
            throw new UnsupportedOperationException("Method not decompiled: com.horcrux.svg.SVGLength.C01081.m3578clinit():void");
        }
    }

    private SVGLength() {
        this.value = 0.0d;
        this.unit = SVGLengthUnitType.SVG_LENGTHTYPE_UNKNOWN;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SVGLength(double d) {
        this.value = d;
        this.unit = SVGLengthUnitType.SVG_LENGTHTYPE_NUMBER;
    }

    private SVGLength(String str) {
        boolean z;
        int i;
        String trim = str.trim();
        int length = trim.length();
        int i2 = length - 1;
        if (length == 0 || trim.equals("normal")) {
            this.unit = SVGLengthUnitType.SVG_LENGTHTYPE_UNKNOWN;
            this.value = 0.0d;
            return;
        }
        if (trim.codePointAt(i2) == 37) {
            this.unit = SVGLengthUnitType.SVG_LENGTHTYPE_PERCENTAGE;
            this.value = Double.valueOf(trim.substring(0, i2)).doubleValue();
            return;
        }
        int i3 = length - 2;
        if (i3 <= 0) {
            this.unit = SVGLengthUnitType.SVG_LENGTHTYPE_NUMBER;
            this.value = Double.valueOf(trim).doubleValue();
            return;
        }
        String substring = trim.substring(i3);
        int hashCode = substring.hashCode();
        if (hashCode == 3178) {
            if (substring.equals("cm")) {
                z = 6;
            }
            z = -1;
        } else if (hashCode == 3240) {
            if (substring.equals("em")) {
                z = true;
            }
            z = -1;
        } else if (hashCode == 3251) {
            if (substring.equals("ex")) {
                z = 2;
            }
            z = -1;
        } else if (hashCode == 3365) {
            if (substring.equals("in")) {
                z = 7;
            }
            z = -1;
        } else if (hashCode == 3488) {
            if (substring.equals("mm")) {
                z = 5;
            }
            z = -1;
        } else if (hashCode == 3571) {
            if (substring.equals("pc")) {
                z = 4;
            }
            z = -1;
        } else if (hashCode != 3588) {
            if (hashCode == 3592 && substring.equals("px")) {
                z = false;
            }
            z = -1;
        } else {
            if (substring.equals("pt")) {
                z = 3;
            }
            z = -1;
        }
        switch (z) {
            case false:
                this.unit = SVGLengthUnitType.SVG_LENGTHTYPE_NUMBER;
                i = i3;
                break;
            case true:
                this.unit = SVGLengthUnitType.SVG_LENGTHTYPE_EMS;
                i = i3;
                break;
            case true:
                this.unit = SVGLengthUnitType.SVG_LENGTHTYPE_EXS;
                i = i3;
                break;
            case true:
                this.unit = SVGLengthUnitType.SVG_LENGTHTYPE_PT;
                i = i3;
                break;
            case true:
                this.unit = SVGLengthUnitType.SVG_LENGTHTYPE_PC;
                i = i3;
                break;
            case true:
                this.unit = SVGLengthUnitType.SVG_LENGTHTYPE_MM;
                i = i3;
                break;
            case true:
                this.unit = SVGLengthUnitType.SVG_LENGTHTYPE_CM;
                i = i3;
                break;
            case true:
                this.unit = SVGLengthUnitType.SVG_LENGTHTYPE_IN;
                i = i3;
                break;
            default:
                this.unit = SVGLengthUnitType.SVG_LENGTHTYPE_NUMBER;
                i = length;
                break;
        }
        this.value = Double.valueOf(trim.substring(0, i)).doubleValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ArrayList<SVGLength> arrayFrom(Dynamic dynamic) {
        int i = C01081.$SwitchMap$com$facebook$react$bridge$ReadableType[dynamic.getType().ordinal()];
        if (i == 1) {
            ArrayList<SVGLength> arrayList = new ArrayList<>(1);
            arrayList.add(new SVGLength(dynamic.asDouble()));
            return arrayList;
        }
        if (i == 2) {
            ArrayList<SVGLength> arrayList2 = new ArrayList<>(1);
            arrayList2.add(new SVGLength(dynamic.asString()));
            return arrayList2;
        }
        if (i != 3) {
            return null;
        }
        ReadableArray asArray = dynamic.asArray();
        int size = asArray.size();
        ArrayList<SVGLength> arrayList3 = new ArrayList<>(size);
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 >= size) {
                return arrayList3;
            }
            arrayList3.add(from(asArray.getDynamic(i3)));
            i2 = i3 + 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SVGLength from(Dynamic dynamic) {
        int i = C01081.$SwitchMap$com$facebook$react$bridge$ReadableType[dynamic.getType().ordinal()];
        return i != 1 ? i != 2 ? new SVGLength() : new SVGLength(dynamic.asString()) : new SVGLength(dynamic.asDouble());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String toString(Dynamic dynamic) {
        int i = C01081.$SwitchMap$com$facebook$react$bridge$ReadableType[dynamic.getType().ordinal()];
        if (i == 1) {
            return String.valueOf(dynamic.asDouble());
        }
        if (i != 2) {
            return null;
        }
        return dynamic.asString();
    }
}
