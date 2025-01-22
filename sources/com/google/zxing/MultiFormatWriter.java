package com.google.zxing;

import com.google.zxing.aztec.AztecWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.datamatrix.DataMatrixWriter;
import com.google.zxing.oned.CodaBarWriter;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.oned.Code39Writer;
import com.google.zxing.oned.Code93Writer;
import com.google.zxing.oned.EAN13Writer;
import com.google.zxing.oned.EAN8Writer;
import com.google.zxing.oned.ITFWriter;
import com.google.zxing.oned.UPCAWriter;
import com.google.zxing.oned.UPCEWriter;
import com.google.zxing.pdf417.PDF417Writer;
import com.google.zxing.qrcode.QRCodeWriter;
import java.util.Map;

/* loaded from: classes08-dex2jar.jar:com/google/zxing/MultiFormatWriter.class */
public final class MultiFormatWriter implements Writer {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.zxing.MultiFormatWriter$1 */
    /* loaded from: classes08-dex2jar.jar:com/google/zxing/MultiFormatWriter$1.class */
    public static /* synthetic */ class C00811 {
        static final /* synthetic */ int[] $SwitchMap$com$google$zxing$BarcodeFormat = new int[BarcodeFormat.values().length];

        /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:85:0x00cd
            	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1166)
            	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:1022)
            	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:55)
            */
        static {
            /*
                com.google.zxing.BarcodeFormat[] r0 = com.google.zxing.BarcodeFormat.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.google.zxing.MultiFormatWriter.C00811.$SwitchMap$com$google$zxing$BarcodeFormat = r0
                int[] r0 = com.google.zxing.MultiFormatWriter.C00811.$SwitchMap$com$google$zxing$BarcodeFormat     // Catch: java.lang.NoSuchFieldError -> La1
                com.google.zxing.BarcodeFormat r1 = com.google.zxing.BarcodeFormat.EAN_8     // Catch: java.lang.NoSuchFieldError -> La1
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> La1
                r2 = 1
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> La1
            L14:
                int[] r0 = com.google.zxing.MultiFormatWriter.C00811.$SwitchMap$com$google$zxing$BarcodeFormat     // Catch: java.lang.NoSuchFieldError -> La1 java.lang.NoSuchFieldError -> La5
                com.google.zxing.BarcodeFormat r1 = com.google.zxing.BarcodeFormat.UPC_E     // Catch: java.lang.NoSuchFieldError -> La5
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> La5
                r2 = 2
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> La5
            L1f:
                int[] r0 = com.google.zxing.MultiFormatWriter.C00811.$SwitchMap$com$google$zxing$BarcodeFormat     // Catch: java.lang.NoSuchFieldError -> La5 java.lang.NoSuchFieldError -> La9
                com.google.zxing.BarcodeFormat r1 = com.google.zxing.BarcodeFormat.EAN_13     // Catch: java.lang.NoSuchFieldError -> La9
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> La9
                r2 = 3
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> La9
            L2a:
                int[] r0 = com.google.zxing.MultiFormatWriter.C00811.$SwitchMap$com$google$zxing$BarcodeFormat     // Catch: java.lang.NoSuchFieldError -> La9 java.lang.NoSuchFieldError -> Lad
                com.google.zxing.BarcodeFormat r1 = com.google.zxing.BarcodeFormat.UPC_A     // Catch: java.lang.NoSuchFieldError -> Lad
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> Lad
                r2 = 4
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> Lad
            L35:
                int[] r0 = com.google.zxing.MultiFormatWriter.C00811.$SwitchMap$com$google$zxing$BarcodeFormat     // Catch: java.lang.NoSuchFieldError -> Lad java.lang.NoSuchFieldError -> Lb1
                com.google.zxing.BarcodeFormat r1 = com.google.zxing.BarcodeFormat.QR_CODE     // Catch: java.lang.NoSuchFieldError -> Lb1
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> Lb1
                r2 = 5
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> Lb1
            L40:
                int[] r0 = com.google.zxing.MultiFormatWriter.C00811.$SwitchMap$com$google$zxing$BarcodeFormat     // Catch: java.lang.NoSuchFieldError -> Lb1 java.lang.NoSuchFieldError -> Lb5
                com.google.zxing.BarcodeFormat r1 = com.google.zxing.BarcodeFormat.CODE_39     // Catch: java.lang.NoSuchFieldError -> Lb5
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> Lb5
                r2 = 6
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> Lb5
            L4c:
                int[] r0 = com.google.zxing.MultiFormatWriter.C00811.$SwitchMap$com$google$zxing$BarcodeFormat     // Catch: java.lang.NoSuchFieldError -> Lb5 java.lang.NoSuchFieldError -> Lb9
                com.google.zxing.BarcodeFormat r1 = com.google.zxing.BarcodeFormat.CODE_93     // Catch: java.lang.NoSuchFieldError -> Lb9
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> Lb9
                r2 = 7
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> Lb9
            L58:
                int[] r0 = com.google.zxing.MultiFormatWriter.C00811.$SwitchMap$com$google$zxing$BarcodeFormat     // Catch: java.lang.NoSuchFieldError -> Lb9 java.lang.NoSuchFieldError -> Lbd
                com.google.zxing.BarcodeFormat r1 = com.google.zxing.BarcodeFormat.CODE_128     // Catch: java.lang.NoSuchFieldError -> Lbd
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> Lbd
                r2 = 8
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> Lbd
            L64:
                int[] r0 = com.google.zxing.MultiFormatWriter.C00811.$SwitchMap$com$google$zxing$BarcodeFormat     // Catch: java.lang.NoSuchFieldError -> Lbd java.lang.NoSuchFieldError -> Lc1
                com.google.zxing.BarcodeFormat r1 = com.google.zxing.BarcodeFormat.ITF     // Catch: java.lang.NoSuchFieldError -> Lc1
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> Lc1
                r2 = 9
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> Lc1
            L70:
                int[] r0 = com.google.zxing.MultiFormatWriter.C00811.$SwitchMap$com$google$zxing$BarcodeFormat     // Catch: java.lang.NoSuchFieldError -> Lc1 java.lang.NoSuchFieldError -> Lc5
                com.google.zxing.BarcodeFormat r1 = com.google.zxing.BarcodeFormat.PDF_417     // Catch: java.lang.NoSuchFieldError -> Lc5
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> Lc5
                r2 = 10
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> Lc5
            L7c:
                int[] r0 = com.google.zxing.MultiFormatWriter.C00811.$SwitchMap$com$google$zxing$BarcodeFormat     // Catch: java.lang.NoSuchFieldError -> Lc5 java.lang.NoSuchFieldError -> Lc9
                com.google.zxing.BarcodeFormat r1 = com.google.zxing.BarcodeFormat.CODABAR     // Catch: java.lang.NoSuchFieldError -> Lc9
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> Lc9
                r2 = 11
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> Lc9
            L88:
                int[] r0 = com.google.zxing.MultiFormatWriter.C00811.$SwitchMap$com$google$zxing$BarcodeFormat     // Catch: java.lang.NoSuchFieldError -> Lc9 java.lang.NoSuchFieldError -> Lcd
                com.google.zxing.BarcodeFormat r1 = com.google.zxing.BarcodeFormat.DATA_MATRIX     // Catch: java.lang.NoSuchFieldError -> Lcd
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> Lcd
                r2 = 12
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> Lcd
            L94:
                int[] r0 = com.google.zxing.MultiFormatWriter.C00811.$SwitchMap$com$google$zxing$BarcodeFormat     // Catch: java.lang.NoSuchFieldError -> Lcd java.lang.NoSuchFieldError -> Ld1
                com.google.zxing.BarcodeFormat r1 = com.google.zxing.BarcodeFormat.AZTEC     // Catch: java.lang.NoSuchFieldError -> Ld1
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> Ld1
                r2 = 13
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> Ld1
                return
            La1:
                r4 = move-exception
                goto L14
            La5:
                r4 = move-exception
                goto L1f
            La9:
                r4 = move-exception
                goto L2a
            Lad:
                r4 = move-exception
                goto L35
            Lb1:
                r4 = move-exception
                goto L40
            Lb5:
                r4 = move-exception
                goto L4c
            Lb9:
                r4 = move-exception
                goto L58
            Lbd:
                r4 = move-exception
                goto L64
            Lc1:
                r4 = move-exception
                goto L70
            Lc5:
                r4 = move-exception
                goto L7c
            Lc9:
                r4 = move-exception
                goto L88
            Lcd:
                r4 = move-exception
                goto L94
            Ld1:
                r4 = move-exception
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.MultiFormatWriter.C00811.m3478clinit():void");
        }
    }

    @Override // com.google.zxing.Writer
    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i, int i2) throws WriterException {
        return encode(str, barcodeFormat, i, i2, null);
    }

    @Override // com.google.zxing.Writer
    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) throws WriterException {
        Writer eAN8Writer;
        switch (C00811.$SwitchMap$com$google$zxing$BarcodeFormat[barcodeFormat.ordinal()]) {
            case 1:
                eAN8Writer = new EAN8Writer();
                break;
            case 2:
                eAN8Writer = new UPCEWriter();
                break;
            case 3:
                eAN8Writer = new EAN13Writer();
                break;
            case 4:
                eAN8Writer = new UPCAWriter();
                break;
            case 5:
                eAN8Writer = new QRCodeWriter();
                break;
            case 6:
                eAN8Writer = new Code39Writer();
                break;
            case 7:
                eAN8Writer = new Code93Writer();
                break;
            case 8:
                eAN8Writer = new Code128Writer();
                break;
            case 9:
                eAN8Writer = new ITFWriter();
                break;
            case 10:
                eAN8Writer = new PDF417Writer();
                break;
            case 11:
                eAN8Writer = new CodaBarWriter();
                break;
            case 12:
                eAN8Writer = new DataMatrixWriter();
                break;
            case 13:
                eAN8Writer = new AztecWriter();
                break;
            default:
                throw new IllegalArgumentException("No encoder available for format " + barcodeFormat);
        }
        return eAN8Writer.encode(str, barcodeFormat, i, i2, map);
    }
}
