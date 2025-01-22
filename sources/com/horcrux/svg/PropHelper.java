package com.horcrux.svg;

import android.graphics.Path;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes08-dex2jar.jar:com/horcrux/svg/PropHelper.class */
class PropHelper {
    private static final int inputMatrixDataSize = 6;

    /* renamed from: com.horcrux.svg.PropHelper$1 */
    /* loaded from: classes08-dex2jar.jar:com/horcrux/svg/PropHelper$1.class */
    static /* synthetic */ class C01041 {
        static final /* synthetic */ int[] $SwitchMap$com$horcrux$svg$SVGLengthUnitType = new int[SVGLengthUnitType.values().length];

        /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:71:0x00ad
            	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1166)
            	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:1022)
            	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:55)
            */
        static {
            /*
                com.horcrux.svg.SVGLengthUnitType[] r0 = com.horcrux.svg.SVGLengthUnitType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.horcrux.svg.PropHelper.C01041.$SwitchMap$com$horcrux$svg$SVGLengthUnitType = r0
                int[] r0 = com.horcrux.svg.PropHelper.C01041.$SwitchMap$com$horcrux$svg$SVGLengthUnitType     // Catch: java.lang.NoSuchFieldError -> L89
                com.horcrux.svg.SVGLengthUnitType r1 = com.horcrux.svg.SVGLengthUnitType.SVG_LENGTHTYPE_NUMBER     // Catch: java.lang.NoSuchFieldError -> L89
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L89
                r2 = 1
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L89
            L14:
                int[] r0 = com.horcrux.svg.PropHelper.C01041.$SwitchMap$com$horcrux$svg$SVGLengthUnitType     // Catch: java.lang.NoSuchFieldError -> L89 java.lang.NoSuchFieldError -> L8d
                com.horcrux.svg.SVGLengthUnitType r1 = com.horcrux.svg.SVGLengthUnitType.SVG_LENGTHTYPE_PX     // Catch: java.lang.NoSuchFieldError -> L8d
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L8d
                r2 = 2
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L8d
            L1f:
                int[] r0 = com.horcrux.svg.PropHelper.C01041.$SwitchMap$com$horcrux$svg$SVGLengthUnitType     // Catch: java.lang.NoSuchFieldError -> L8d java.lang.NoSuchFieldError -> L91
                com.horcrux.svg.SVGLengthUnitType r1 = com.horcrux.svg.SVGLengthUnitType.SVG_LENGTHTYPE_PERCENTAGE     // Catch: java.lang.NoSuchFieldError -> L91
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L91
                r2 = 3
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L91
            L2a:
                int[] r0 = com.horcrux.svg.PropHelper.C01041.$SwitchMap$com$horcrux$svg$SVGLengthUnitType     // Catch: java.lang.NoSuchFieldError -> L91 java.lang.NoSuchFieldError -> L95
                com.horcrux.svg.SVGLengthUnitType r1 = com.horcrux.svg.SVGLengthUnitType.SVG_LENGTHTYPE_EMS     // Catch: java.lang.NoSuchFieldError -> L95
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L95
                r2 = 4
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L95
            L35:
                int[] r0 = com.horcrux.svg.PropHelper.C01041.$SwitchMap$com$horcrux$svg$SVGLengthUnitType     // Catch: java.lang.NoSuchFieldError -> L95 java.lang.NoSuchFieldError -> L99
                com.horcrux.svg.SVGLengthUnitType r1 = com.horcrux.svg.SVGLengthUnitType.SVG_LENGTHTYPE_EXS     // Catch: java.lang.NoSuchFieldError -> L99
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L99
                r2 = 5
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L99
            L40:
                int[] r0 = com.horcrux.svg.PropHelper.C01041.$SwitchMap$com$horcrux$svg$SVGLengthUnitType     // Catch: java.lang.NoSuchFieldError -> L99 java.lang.NoSuchFieldError -> L9d
                com.horcrux.svg.SVGLengthUnitType r1 = com.horcrux.svg.SVGLengthUnitType.SVG_LENGTHTYPE_CM     // Catch: java.lang.NoSuchFieldError -> L9d
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L9d
                r2 = 6
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L9d
            L4c:
                int[] r0 = com.horcrux.svg.PropHelper.C01041.$SwitchMap$com$horcrux$svg$SVGLengthUnitType     // Catch: java.lang.NoSuchFieldError -> L9d java.lang.NoSuchFieldError -> La1
                com.horcrux.svg.SVGLengthUnitType r1 = com.horcrux.svg.SVGLengthUnitType.SVG_LENGTHTYPE_MM     // Catch: java.lang.NoSuchFieldError -> La1
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> La1
                r2 = 7
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> La1
            L58:
                int[] r0 = com.horcrux.svg.PropHelper.C01041.$SwitchMap$com$horcrux$svg$SVGLengthUnitType     // Catch: java.lang.NoSuchFieldError -> La1 java.lang.NoSuchFieldError -> La5
                com.horcrux.svg.SVGLengthUnitType r1 = com.horcrux.svg.SVGLengthUnitType.SVG_LENGTHTYPE_IN     // Catch: java.lang.NoSuchFieldError -> La5
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> La5
                r2 = 8
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> La5
            L64:
                int[] r0 = com.horcrux.svg.PropHelper.C01041.$SwitchMap$com$horcrux$svg$SVGLengthUnitType     // Catch: java.lang.NoSuchFieldError -> La5 java.lang.NoSuchFieldError -> La9
                com.horcrux.svg.SVGLengthUnitType r1 = com.horcrux.svg.SVGLengthUnitType.SVG_LENGTHTYPE_PT     // Catch: java.lang.NoSuchFieldError -> La9
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> La9
                r2 = 9
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> La9
            L70:
                int[] r0 = com.horcrux.svg.PropHelper.C01041.$SwitchMap$com$horcrux$svg$SVGLengthUnitType     // Catch: java.lang.NoSuchFieldError -> La9 java.lang.NoSuchFieldError -> Lad
                com.horcrux.svg.SVGLengthUnitType r1 = com.horcrux.svg.SVGLengthUnitType.SVG_LENGTHTYPE_PC     // Catch: java.lang.NoSuchFieldError -> Lad
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> Lad
                r2 = 10
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> Lad
            L7c:
                int[] r0 = com.horcrux.svg.PropHelper.C01041.$SwitchMap$com$horcrux$svg$SVGLengthUnitType     // Catch: java.lang.NoSuchFieldError -> Lad java.lang.NoSuchFieldError -> Lb1
                com.horcrux.svg.SVGLengthUnitType r1 = com.horcrux.svg.SVGLengthUnitType.SVG_LENGTHTYPE_UNKNOWN     // Catch: java.lang.NoSuchFieldError -> Lb1
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> Lb1
                r2 = 11
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> Lb1
                return
            L89:
                r4 = move-exception
                goto L14
            L8d:
                r4 = move-exception
                goto L1f
            L91:
                r4 = move-exception
                goto L2a
            L95:
                r4 = move-exception
                goto L35
            L99:
                r4 = move-exception
                goto L40
            L9d:
                r4 = move-exception
                goto L4c
            La1:
                r4 = move-exception
                goto L58
            La5:
                r4 = move-exception
                goto L64
            La9:
                r4 = move-exception
                goto L70
            Lad:
                r4 = move-exception
                goto L7c
            Lb1:
                r4 = move-exception
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.horcrux.svg.PropHelper.C01041.m3568clinit():void");
        }
    }

    /* loaded from: classes08-dex2jar.jar:com/horcrux/svg/PropHelper$PathParser.class */
    static class PathParser {
        private WritableArray mBezierCurves;
        private String mLastCommand;
        private WritableMap mLastStartPoint;
        private String mLastValue;
        private Matcher mMatcher;
        private Path mPath;
        private float mPenDownX;
        private float mPenDownY;
        private final float mScale;
        private final String mString;
        private static final Pattern PATH_REG_EXP = Pattern.compile("[a-df-z]|[\\-+]?(?:[\\d.]e[\\-+]?|[^\\s\\-+,a-z])+", 2);
        private static final Pattern DECIMAL_REG_EXP = Pattern.compile("(\\.\\d+)(?=-?\\.)");
        private float mPenX = 0.0f;
        private float mPenY = 0.0f;
        private float mPivotX = 0.0f;
        private float mPivotY = 0.0f;
        private boolean mValid = true;
        private boolean mPendDownSet = false;

        /* JADX INFO: Access modifiers changed from: package-private */
        public PathParser(String str, float f) {
            this.mScale = f;
            this.mString = str;
        }

        private void arc(float f, float f2, float f3, boolean z, boolean z2, float f4, float f5) {
            arcTo(f, f2, f3, z, z2, f4 + this.mPenX, f5 + this.mPenY);
        }

        /* JADX WARN: Code restructure failed: missing block: B:28:0x022b, code lost:            if (r0 < 180.0f) goto L39;     */
        /* JADX WARN: Code restructure failed: missing block: B:35:0x023a, code lost:            r11 = 360.0f - r0;     */
        /* JADX WARN: Code restructure failed: missing block: B:37:0x0237, code lost:            if (r0 > 180.0f) goto L39;     */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private void arcTo(float r11, float r12, float r13, boolean r14, boolean r15, float r16, float r17) {
            /*
                Method dump skipped, instructions count: 664
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.horcrux.svg.PropHelper.PathParser.arcTo(float, float, float, boolean, boolean, float, float):void");
        }

        /* JADX WARN: Removed duplicated region for block: B:10:0x00bc A[LOOP:0: B:8:0x00b5->B:10:0x00bc, LOOP_END] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private void arcToBezier(float r11, float r12, float r13, float r14, float r15, float r16, boolean r17, float r18) {
            /*
                Method dump skipped, instructions count: 378
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.horcrux.svg.PropHelper.PathParser.arcToBezier(float, float, float, float, float, float, boolean, float):void");
        }

        private WritableMap clonePointMap(WritableMap writableMap) {
            WritableMap createMap = Arguments.createMap();
            createMap.putDouble("x", writableMap.getDouble("x"));
            createMap.putDouble("y", writableMap.getDouble("y"));
            return createMap;
        }

        private void close() {
            if (this.mPendDownSet) {
                this.mPenX = this.mPenDownX;
                this.mPenY = this.mPenDownY;
                this.mPendDownSet = false;
                this.mPath.close();
                WritableArray createArray = Arguments.createArray();
                createArray.pushMap(clonePointMap(this.mLastStartPoint));
                createArray.pushMap(clonePointMap(this.mLastStartPoint));
                createArray.pushMap(clonePointMap(this.mLastStartPoint));
                this.mBezierCurves.pushArray(createArray);
            }
        }

        private void cubicTo(float f, float f2, float f3, float f4, float f5, float f6) {
            setPenDown();
            this.mPenX = f5;
            this.mPenY = f6;
            Path path = this.mPath;
            float f7 = this.mScale;
            path.cubicTo(f * f7, f2 * f7, f3 * f7, f4 * f7, f5 * f7, f6 * f7);
            WritableArray createArray = Arguments.createArray();
            createArray.pushMap(getPointMap(f, f2));
            createArray.pushMap(getPointMap(f3, f4));
            createArray.pushMap(getPointMap(f5, f6));
            this.mBezierCurves.pushArray(createArray);
        }

        private void curve(float f, float f2, float f3, float f4, float f5, float f6) {
            float f7 = this.mPenX;
            float f8 = this.mPenY;
            curveTo(f + f7, f2 + f8, f3 + f7, f4 + f8, f5 + f7, f6 + f8);
        }

        private void curveTo(float f, float f2, float f3, float f4, float f5, float f6) {
            this.mPivotX = f3;
            this.mPivotY = f4;
            cubicTo(f, f2, f3, f4, f5, f6);
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        private void executeCommand(String str) {
            boolean z;
            switch (str.hashCode()) {
                case 65:
                    if (str.equals("A")) {
                        z = 17;
                        break;
                    }
                    z = -1;
                    break;
                case 67:
                    if (str.equals("C")) {
                        z = 9;
                        break;
                    }
                    z = -1;
                    break;
                case 72:
                    if (str.equals("H")) {
                        z = 5;
                        break;
                    }
                    z = -1;
                    break;
                case 76:
                    if (str.equals("L")) {
                        z = 3;
                        break;
                    }
                    z = -1;
                    break;
                case 77:
                    if (str.equals("M")) {
                        z = true;
                        break;
                    }
                    z = -1;
                    break;
                case 81:
                    if (str.equals("Q")) {
                        z = 13;
                        break;
                    }
                    z = -1;
                    break;
                case 83:
                    if (str.equals("S")) {
                        z = 11;
                        break;
                    }
                    z = -1;
                    break;
                case 84:
                    if (str.equals("T")) {
                        z = 15;
                        break;
                    }
                    z = -1;
                    break;
                case 86:
                    if (str.equals("V")) {
                        z = 7;
                        break;
                    }
                    z = -1;
                    break;
                case 90:
                    if (str.equals("Z")) {
                        z = 18;
                        break;
                    }
                    z = -1;
                    break;
                case 97:
                    if (str.equals("a")) {
                        z = 16;
                        break;
                    }
                    z = -1;
                    break;
                case 99:
                    if (str.equals("c")) {
                        z = 8;
                        break;
                    }
                    z = -1;
                    break;
                case 104:
                    if (str.equals("h")) {
                        z = 4;
                        break;
                    }
                    z = -1;
                    break;
                case 108:
                    if (str.equals("l")) {
                        z = 2;
                        break;
                    }
                    z = -1;
                    break;
                case 109:
                    if (str.equals("m")) {
                        z = false;
                        break;
                    }
                    z = -1;
                    break;
                case 113:
                    if (str.equals("q")) {
                        z = 12;
                        break;
                    }
                    z = -1;
                    break;
                case 115:
                    if (str.equals("s")) {
                        z = 10;
                        break;
                    }
                    z = -1;
                    break;
                case 116:
                    if (str.equals("t")) {
                        z = 14;
                        break;
                    }
                    z = -1;
                    break;
                case 118:
                    if (str.equals("v")) {
                        z = 6;
                        break;
                    }
                    z = -1;
                    break;
                case 122:
                    if (str.equals("z")) {
                        z = 19;
                        break;
                    }
                    z = -1;
                    break;
                default:
                    z = -1;
                    break;
            }
            switch (z) {
                case false:
                    move(getNextFloat(), getNextFloat());
                    break;
                case true:
                    moveTo(getNextFloat(), getNextFloat());
                    break;
                case true:
                    line(getNextFloat(), getNextFloat());
                    break;
                case true:
                    lineTo(getNextFloat(), getNextFloat());
                    break;
                case true:
                    line(getNextFloat(), 0.0f);
                    break;
                case true:
                    lineTo(getNextFloat(), this.mPenY);
                    break;
                case true:
                    line(0.0f, getNextFloat());
                    break;
                case true:
                    lineTo(this.mPenX, getNextFloat());
                    break;
                case true:
                    curve(getNextFloat(), getNextFloat(), getNextFloat(), getNextFloat(), getNextFloat(), getNextFloat());
                    break;
                case true:
                    curveTo(getNextFloat(), getNextFloat(), getNextFloat(), getNextFloat(), getNextFloat(), getNextFloat());
                    break;
                case true:
                    smoothCurve(getNextFloat(), getNextFloat(), getNextFloat(), getNextFloat());
                    break;
                case true:
                    smoothCurveTo(getNextFloat(), getNextFloat(), getNextFloat(), getNextFloat());
                    break;
                case true:
                    quadraticBezierCurve(getNextFloat(), getNextFloat(), getNextFloat(), getNextFloat());
                    break;
                case true:
                    quadraticBezierCurveTo(getNextFloat(), getNextFloat(), getNextFloat(), getNextFloat());
                    break;
                case true:
                    smoothQuadraticBezierCurve(getNextFloat(), getNextFloat());
                    break;
                case true:
                    smoothQuadraticBezierCurveTo(getNextFloat(), getNextFloat());
                    break;
                case true:
                    arc(getNextFloat(), getNextFloat(), getNextFloat(), getNextBoolean(), getNextBoolean(), getNextFloat(), getNextFloat());
                    break;
                case true:
                    arcTo(getNextFloat(), getNextFloat(), getNextFloat(), getNextBoolean(), getNextBoolean(), getNextFloat(), getNextFloat());
                    break;
                case true:
                case true:
                    close();
                    break;
                default:
                    this.mLastValue = str;
                    executeCommand(this.mLastCommand);
                    return;
            }
            this.mLastCommand = str;
            if (str.equals("m")) {
                this.mLastCommand = "l";
            } else if (str.equals("M")) {
                this.mLastCommand = "L";
            }
        }

        private boolean getNextBoolean() {
            if (this.mMatcher.find()) {
                return this.mMatcher.group().equals("1");
            }
            this.mValid = false;
            this.mPath = new Path();
            return false;
        }

        private float getNextFloat() {
            String str = this.mLastValue;
            if (str != null) {
                this.mLastValue = null;
                return Float.parseFloat(str);
            }
            if (this.mMatcher.find()) {
                return Float.parseFloat(this.mMatcher.group());
            }
            this.mValid = false;
            this.mPath = new Path();
            return 0.0f;
        }

        private WritableMap getPointMap(float f, float f2) {
            WritableMap createMap = Arguments.createMap();
            createMap.putDouble("x", f * this.mScale);
            createMap.putDouble("y", f2 * this.mScale);
            return createMap;
        }

        private void line(float f, float f2) {
            lineTo(f + this.mPenX, f2 + this.mPenY);
        }

        private void lineTo(float f, float f2) {
            setPenDown();
            this.mPenX = f;
            this.mPivotX = f;
            this.mPenY = f2;
            this.mPivotY = f2;
            Path path = this.mPath;
            float f3 = this.mScale;
            path.lineTo(f * f3, f3 * f2);
            WritableArray createArray = Arguments.createArray();
            createArray.pushMap(getPointMap(f, f2));
            createArray.pushMap(getPointMap(f, f2));
            createArray.pushMap(getPointMap(f, f2));
            this.mBezierCurves.pushArray(createArray);
        }

        private void move(float f, float f2) {
            moveTo(f + this.mPenX, f2 + this.mPenY);
        }

        private void moveTo(float f, float f2) {
            this.mPenX = f;
            this.mPivotX = f;
            this.mPenDownX = f;
            this.mPenY = f2;
            this.mPivotY = f2;
            this.mPenDownY = f2;
            Path path = this.mPath;
            float f3 = this.mScale;
            path.moveTo(f * f3, f3 * f2);
            this.mLastStartPoint = getPointMap(f, f2);
            WritableArray createArray = Arguments.createArray();
            createArray.pushMap(getPointMap(f, f2));
            this.mBezierCurves.pushArray(createArray);
        }

        private void quadraticBezierCurve(float f, float f2, float f3, float f4) {
            float f5 = this.mPenX;
            float f6 = this.mPenY;
            quadraticBezierCurveTo(f + f5, f2 + f6, f3 + f5, f4 + f6);
        }

        private void quadraticBezierCurveTo(float f, float f2, float f3, float f4) {
            this.mPivotX = f;
            this.mPivotY = f2;
            float f5 = f * 2.0f;
            float f6 = (f3 + f5) / 3.0f;
            float f7 = f2 * 2.0f;
            cubicTo((this.mPenX + f5) / 3.0f, (this.mPenY + f7) / 3.0f, f6, (f4 + f7) / 3.0f, f3, f4);
        }

        private double round(double d) {
            return Math.round(d * r0) / Math.pow(10.0d, 4.0d);
        }

        private void setPenDown() {
            if (this.mPendDownSet) {
                return;
            }
            this.mPenDownX = this.mPenX;
            this.mPenDownY = this.mPenY;
            this.mPendDownSet = true;
        }

        private void smoothCurve(float f, float f2, float f3, float f4) {
            float f5 = this.mPenX;
            float f6 = this.mPenY;
            smoothCurveTo(f + f5, f2 + f6, f3 + f5, f4 + f6);
        }

        private void smoothCurveTo(float f, float f2, float f3, float f4) {
            float f5 = this.mPenX;
            float f6 = this.mPivotX;
            float f7 = this.mPenY;
            float f8 = this.mPivotY;
            this.mPivotX = f;
            this.mPivotY = f2;
            cubicTo((f5 * 2.0f) - f6, (f7 * 2.0f) - f8, f, f2, f3, f4);
        }

        private void smoothQuadraticBezierCurve(float f, float f2) {
            smoothQuadraticBezierCurveTo(f + this.mPenX, f2 + this.mPenY);
        }

        private void smoothQuadraticBezierCurveTo(float f, float f2) {
            quadraticBezierCurveTo((this.mPenX * 2.0f) - this.mPivotX, (this.mPenY * 2.0f) - this.mPivotY, f, f2);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Path getPath() {
            this.mPath = new Path();
            this.mBezierCurves = Arguments.createArray();
            this.mMatcher = PATH_REG_EXP.matcher(DECIMAL_REG_EXP.matcher(this.mString).replaceAll("$1,"));
            while (this.mMatcher.find() && this.mValid) {
                executeCommand(this.mMatcher.group());
            }
            return this.mPath;
        }
    }

    PropHelper() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static double fromRelative(SVGLength sVGLength, double d, double d2, double d3, double d4) {
        double d5;
        double d6;
        if (sVGLength == null) {
            return d2;
        }
        SVGLengthUnitType sVGLengthUnitType = sVGLength.unit;
        double d7 = sVGLength.value;
        double d8 = d4;
        switch (C01041.$SwitchMap$com$horcrux$svg$SVGLengthUnitType[sVGLengthUnitType.ordinal()]) {
            case 1:
            case 2:
                d8 = 1.0d;
                d5 = d7 * d8;
                d6 = d5 * d3;
                break;
            case 3:
                d6 = (d7 / 100.0d) * d;
                break;
            case 4:
                d5 = d7 * d8;
                d6 = d5 * d3;
                break;
            case 5:
                d8 = d4 / 2.0d;
                d5 = d7 * d8;
                d6 = d5 * d3;
                break;
            case 6:
                d8 = 35.43307d;
                d5 = d7 * d8;
                d6 = d5 * d3;
                break;
            case 7:
                d8 = 3.543307d;
                d5 = d7 * d8;
                d6 = d5 * d3;
                break;
            case 8:
                d8 = 90.0d;
                d5 = d7 * d8;
                d6 = d5 * d3;
                break;
            case 9:
                d8 = 1.25d;
                d5 = d7 * d8;
                d6 = d5 * d3;
                break;
            case 10:
                d8 = 15.0d;
                d5 = d7 * d8;
                d6 = d5 * d3;
                break;
            default:
                d5 = d7;
                d6 = d5 * d3;
                break;
        }
        return d6 + d2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0140  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0144 A[PHI: r13
  0x0144: PHI (r13v1 int) = (r13v0 int), (r13v2 int) binds: [B:30:0x0114, B:31:0x0140] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x014e  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x015a  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0166  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0172  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x017e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static double fromRelative(java.lang.String r5, double r6, double r8, double r10) {
        /*
            Method dump skipped, instructions count: 425
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.horcrux.svg.PropHelper.fromRelative(java.lang.String, double, double, double):double");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int toMatrixData(ReadableArray readableArray, float[] fArr, float f) {
        int size = readableArray.size();
        if (size != 6) {
            return size;
        }
        fArr[0] = (float) readableArray.getDouble(0);
        fArr[1] = (float) readableArray.getDouble(2);
        fArr[2] = ((float) readableArray.getDouble(4)) * f;
        fArr[3] = (float) readableArray.getDouble(1);
        fArr[4] = (float) readableArray.getDouble(3);
        fArr[5] = ((float) readableArray.getDouble(5)) * f;
        return 6;
    }
}
