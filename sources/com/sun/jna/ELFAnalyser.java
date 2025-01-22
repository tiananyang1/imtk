package com.sun.jna;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* loaded from: classes08-dex2jar.jar:com/sun/jna/ELFAnalyser.class */
class ELFAnalyser {
    private static final int EF_ARM_ABI_FLOAT_HARD = 1024;
    private static final int EF_ARM_ABI_FLOAT_SOFT = 512;
    private static final int EI_CLASS_64BIT = 2;
    private static final int EI_DATA_BIG_ENDIAN = 2;
    private static final byte[] ELF_MAGIC = {Byte.MAX_VALUE, 69, 76, 70};
    private static final int E_MACHINE_ARM = 40;
    private final String filename;
    private boolean ELF = false;
    private boolean _64Bit = false;
    private boolean bigEndian = false;
    private boolean armHardFloatFlag = false;
    private boolean armSoftFloatFlag = false;
    private boolean armEabiAapcsVfp = false;
    private boolean arm = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sun.jna.ELFAnalyser$1 */
    /* loaded from: classes08-dex2jar.jar:com/sun/jna/ELFAnalyser$1.class */
    public static /* synthetic */ class C03651 {

        /* renamed from: $SwitchMap$com$sun$jna$ELFAnalyser$ArmAeabiAttributesTag$ParameterType */
        static final /* synthetic */ int[] f213xd3185832 = new int[ArmAeabiAttributesTag.ParameterType.values().length];

        /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:15:0x002f
            	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1166)
            	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:1022)
            	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:55)
            */
        static {
            /*
                com.sun.jna.ELFAnalyser$ArmAeabiAttributesTag$ParameterType[] r0 = com.sun.jna.ELFAnalyser.ArmAeabiAttributesTag.ParameterType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.sun.jna.ELFAnalyser.C03651.f213xd3185832 = r0
                int[] r0 = com.sun.jna.ELFAnalyser.C03651.f213xd3185832     // Catch: java.lang.NoSuchFieldError -> L2b
                com.sun.jna.ELFAnalyser$ArmAeabiAttributesTag$ParameterType r1 = com.sun.jna.ELFAnalyser.ArmAeabiAttributesTag.ParameterType.UINT32     // Catch: java.lang.NoSuchFieldError -> L2b
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L2b
                r2 = 1
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L2b
            L14:
                int[] r0 = com.sun.jna.ELFAnalyser.C03651.f213xd3185832     // Catch: java.lang.NoSuchFieldError -> L2b java.lang.NoSuchFieldError -> L2f
                com.sun.jna.ELFAnalyser$ArmAeabiAttributesTag$ParameterType r1 = com.sun.jna.ELFAnalyser.ArmAeabiAttributesTag.ParameterType.NTBS     // Catch: java.lang.NoSuchFieldError -> L2f
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L2f
                r2 = 2
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L2f
            L1f:
                int[] r0 = com.sun.jna.ELFAnalyser.C03651.f213xd3185832     // Catch: java.lang.NoSuchFieldError -> L2f java.lang.NoSuchFieldError -> L33
                com.sun.jna.ELFAnalyser$ArmAeabiAttributesTag$ParameterType r1 = com.sun.jna.ELFAnalyser.ArmAeabiAttributesTag.ParameterType.ULEB128     // Catch: java.lang.NoSuchFieldError -> L33
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
            throw new UnsupportedOperationException("Method not decompiled: com.sun.jna.ELFAnalyser.C03651.m3894clinit():void");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes08-dex2jar.jar:com/sun/jna/ELFAnalyser$ArmAeabiAttributesTag.class */
    public static class ArmAeabiAttributesTag {
        private final String name;
        private final ParameterType parameterType;
        private final int value;
        private static final List<ArmAeabiAttributesTag> tags = new LinkedList();
        private static final Map<Integer, ArmAeabiAttributesTag> valueMap = new HashMap();
        private static final Map<String, ArmAeabiAttributesTag> nameMap = new HashMap();
        public static final ArmAeabiAttributesTag File = addTag(1, "File", ParameterType.UINT32);
        public static final ArmAeabiAttributesTag Section = addTag(2, "Section", ParameterType.UINT32);
        public static final ArmAeabiAttributesTag Symbol = addTag(3, "Symbol", ParameterType.UINT32);
        public static final ArmAeabiAttributesTag CPU_raw_name = addTag(4, "CPU_raw_name", ParameterType.NTBS);
        public static final ArmAeabiAttributesTag CPU_name = addTag(5, "CPU_name", ParameterType.NTBS);
        public static final ArmAeabiAttributesTag CPU_arch = addTag(6, "CPU_arch", ParameterType.ULEB128);
        public static final ArmAeabiAttributesTag CPU_arch_profile = addTag(7, "CPU_arch_profile", ParameterType.ULEB128);
        public static final ArmAeabiAttributesTag ARM_ISA_use = addTag(8, "ARM_ISA_use", ParameterType.ULEB128);
        public static final ArmAeabiAttributesTag THUMB_ISA_use = addTag(9, "THUMB_ISA_use", ParameterType.ULEB128);
        public static final ArmAeabiAttributesTag FP_arch = addTag(10, "FP_arch", ParameterType.ULEB128);
        public static final ArmAeabiAttributesTag WMMX_arch = addTag(11, "WMMX_arch", ParameterType.ULEB128);
        public static final ArmAeabiAttributesTag Advanced_SIMD_arch = addTag(12, "Advanced_SIMD_arch", ParameterType.ULEB128);
        public static final ArmAeabiAttributesTag PCS_config = addTag(13, "PCS_config", ParameterType.ULEB128);
        public static final ArmAeabiAttributesTag ABI_PCS_R9_use = addTag(14, "ABI_PCS_R9_use", ParameterType.ULEB128);
        public static final ArmAeabiAttributesTag ABI_PCS_RW_data = addTag(15, "ABI_PCS_RW_data", ParameterType.ULEB128);
        public static final ArmAeabiAttributesTag ABI_PCS_RO_data = addTag(16, "ABI_PCS_RO_data", ParameterType.ULEB128);
        public static final ArmAeabiAttributesTag ABI_PCS_GOT_use = addTag(17, "ABI_PCS_GOT_use", ParameterType.ULEB128);
        public static final ArmAeabiAttributesTag ABI_PCS_wchar_t = addTag(18, "ABI_PCS_wchar_t", ParameterType.ULEB128);
        public static final ArmAeabiAttributesTag ABI_FP_rounding = addTag(19, "ABI_FP_rounding", ParameterType.ULEB128);
        public static final ArmAeabiAttributesTag ABI_FP_denormal = addTag(20, "ABI_FP_denormal", ParameterType.ULEB128);
        public static final ArmAeabiAttributesTag ABI_FP_exceptions = addTag(21, "ABI_FP_exceptions", ParameterType.ULEB128);
        public static final ArmAeabiAttributesTag ABI_FP_user_exceptions = addTag(22, "ABI_FP_user_exceptions", ParameterType.ULEB128);
        public static final ArmAeabiAttributesTag ABI_FP_number_model = addTag(23, "ABI_FP_number_model", ParameterType.ULEB128);
        public static final ArmAeabiAttributesTag ABI_align_needed = addTag(24, "ABI_align_needed", ParameterType.ULEB128);
        public static final ArmAeabiAttributesTag ABI_align8_preserved = addTag(25, "ABI_align8_preserved", ParameterType.ULEB128);
        public static final ArmAeabiAttributesTag ABI_enum_size = addTag(26, "ABI_enum_size", ParameterType.ULEB128);
        public static final ArmAeabiAttributesTag ABI_HardFP_use = addTag(27, "ABI_HardFP_use", ParameterType.ULEB128);
        public static final ArmAeabiAttributesTag ABI_VFP_args = addTag(28, "ABI_VFP_args", ParameterType.ULEB128);
        public static final ArmAeabiAttributesTag ABI_WMMX_args = addTag(29, "ABI_WMMX_args", ParameterType.ULEB128);
        public static final ArmAeabiAttributesTag ABI_optimization_goals = addTag(30, "ABI_optimization_goals", ParameterType.ULEB128);
        public static final ArmAeabiAttributesTag ABI_FP_optimization_goals = addTag(31, "ABI_FP_optimization_goals", ParameterType.ULEB128);
        public static final ArmAeabiAttributesTag compatibility = addTag(32, "compatibility", ParameterType.NTBS);
        public static final ArmAeabiAttributesTag CPU_unaligned_access = addTag(34, "CPU_unaligned_access", ParameterType.ULEB128);
        public static final ArmAeabiAttributesTag FP_HP_extension = addTag(36, "FP_HP_extension", ParameterType.ULEB128);
        public static final ArmAeabiAttributesTag ABI_FP_16bit_format = addTag(38, "ABI_FP_16bit_format", ParameterType.ULEB128);
        public static final ArmAeabiAttributesTag MPextension_use = addTag(42, "MPextension_use", ParameterType.ULEB128);
        public static final ArmAeabiAttributesTag DIV_use = addTag(44, "DIV_use", ParameterType.ULEB128);
        public static final ArmAeabiAttributesTag nodefaults = addTag(64, "nodefaults", ParameterType.ULEB128);
        public static final ArmAeabiAttributesTag also_compatible_with = addTag(65, "also_compatible_with", ParameterType.NTBS);
        public static final ArmAeabiAttributesTag conformance = addTag(67, "conformance", ParameterType.NTBS);
        public static final ArmAeabiAttributesTag T2EE_use = addTag(66, "T2EE_use", ParameterType.ULEB128);
        public static final ArmAeabiAttributesTag Virtualization_use = addTag(68, "Virtualization_use", ParameterType.ULEB128);
        public static final ArmAeabiAttributesTag MPextension_use2 = addTag(70, "MPextension_use", ParameterType.ULEB128);

        /* loaded from: classes08-dex2jar.jar:com/sun/jna/ELFAnalyser$ArmAeabiAttributesTag$ParameterType.class */
        public enum ParameterType {
            UINT32,
            NTBS,
            ULEB128
        }

        public ArmAeabiAttributesTag(int i, String str, ParameterType parameterType) {
            this.value = i;
            this.name = str;
            this.parameterType = parameterType;
        }

        private static ArmAeabiAttributesTag addTag(int i, String str, ParameterType parameterType) {
            ArmAeabiAttributesTag armAeabiAttributesTag = new ArmAeabiAttributesTag(i, str, parameterType);
            if (!valueMap.containsKey(Integer.valueOf(armAeabiAttributesTag.getValue()))) {
                valueMap.put(Integer.valueOf(armAeabiAttributesTag.getValue()), armAeabiAttributesTag);
            }
            if (!nameMap.containsKey(armAeabiAttributesTag.getName())) {
                nameMap.put(armAeabiAttributesTag.getName(), armAeabiAttributesTag);
            }
            tags.add(armAeabiAttributesTag);
            return armAeabiAttributesTag;
        }

        public static ArmAeabiAttributesTag getByName(String str) {
            return nameMap.get(str);
        }

        public static ArmAeabiAttributesTag getByValue(int i) {
            if (valueMap.containsKey(Integer.valueOf(i))) {
                return valueMap.get(Integer.valueOf(i));
            }
            return new ArmAeabiAttributesTag(i, "Unknown " + i, getParameterType(i));
        }

        private static ParameterType getParameterType(int i) {
            ArmAeabiAttributesTag byValue = getByValue(i);
            return byValue == null ? i % 2 == 0 ? ParameterType.ULEB128 : ParameterType.NTBS : byValue.getParameterType();
        }

        public static List<ArmAeabiAttributesTag> getTags() {
            return Collections.unmodifiableList(tags);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.value == ((ArmAeabiAttributesTag) obj).value;
        }

        public String getName() {
            return this.name;
        }

        public ParameterType getParameterType() {
            return this.parameterType;
        }

        public int getValue() {
            return this.value;
        }

        public int hashCode() {
            return 469 + this.value;
        }

        public String toString() {
            return this.name + " (" + this.value + ")";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes08-dex2jar.jar:com/sun/jna/ELFAnalyser$ELFSectionHeaderEntry.class */
    public static class ELFSectionHeaderEntry {
        private final int flags;
        private String name;
        private final int nameOffset;
        private final int offset;
        private final int size;
        private final int type;

        public ELFSectionHeaderEntry(boolean z, ByteBuffer byteBuffer) {
            this.nameOffset = byteBuffer.getInt(0);
            this.type = byteBuffer.getInt(4);
            this.flags = (int) (z ? byteBuffer.getLong(8) : byteBuffer.getInt(8));
            this.offset = (int) (z ? byteBuffer.getLong(24) : byteBuffer.getInt(16));
            this.size = (int) (z ? byteBuffer.getLong(32) : byteBuffer.getInt(20));
        }

        public int getFlags() {
            return this.flags;
        }

        public String getName() {
            return this.name;
        }

        public int getNameOffset() {
            return this.nameOffset;
        }

        public int getOffset() {
            return this.offset;
        }

        public int getSize() {
            return this.size;
        }

        public int getType() {
            return this.type;
        }

        public void setName(String str) {
            this.name = str;
        }

        public String toString() {
            return "ELFSectionHeaderEntry{nameIdx=" + this.nameOffset + ", name=" + this.name + ", type=" + this.type + ", flags=" + this.flags + ", offset=" + this.offset + ", size=" + this.size + '}';
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes08-dex2jar.jar:com/sun/jna/ELFAnalyser$ELFSectionHeaders.class */
    public static class ELFSectionHeaders {
        private final List<ELFSectionHeaderEntry> entries = new ArrayList();

        public ELFSectionHeaders(boolean z, boolean z2, ByteBuffer byteBuffer, RandomAccessFile randomAccessFile) throws IOException {
            long j;
            short s;
            short s2;
            short s3;
            byte b;
            if (z) {
                j = byteBuffer.getLong(40);
                s = byteBuffer.getShort(58);
                s2 = byteBuffer.getShort(60);
                s3 = byteBuffer.getShort(62);
            } else {
                j = byteBuffer.getInt(32);
                s = byteBuffer.getShort(46);
                s2 = byteBuffer.getShort(48);
                s3 = byteBuffer.getShort(50);
            }
            ByteBuffer allocate = ByteBuffer.allocate(s2 * s);
            allocate.order(z2 ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
            randomAccessFile.getChannel().read(allocate, j);
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= s2) {
                    break;
                }
                allocate.position(i2 * s);
                ByteBuffer slice = allocate.slice();
                slice.order(allocate.order());
                slice.limit(s);
                this.entries.add(new ELFSectionHeaderEntry(z, slice));
                i = i2 + 1;
            }
            ByteBuffer allocate2 = ByteBuffer.allocate(this.entries.get(s3).getSize());
            allocate2.order(z2 ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
            randomAccessFile.getChannel().read(allocate2, r0.getOffset());
            allocate2.rewind();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(20);
            for (ELFSectionHeaderEntry eLFSectionHeaderEntry : this.entries) {
                byteArrayOutputStream.reset();
                allocate2.position(eLFSectionHeaderEntry.getNameOffset());
                while (allocate2.position() < allocate2.limit() && (b = allocate2.get()) != 0) {
                    byteArrayOutputStream.write(b);
                }
                eLFSectionHeaderEntry.setName(byteArrayOutputStream.toString("ASCII"));
            }
        }

        public List<ELFSectionHeaderEntry> getEntries() {
            return this.entries;
        }
    }

    private ELFAnalyser(String str) {
        this.filename = str;
    }

    public static ELFAnalyser analyse(String str) throws IOException {
        ELFAnalyser eLFAnalyser = new ELFAnalyser(str);
        eLFAnalyser.runDetection();
        return eLFAnalyser;
    }

    private static Map<Integer, Map<ArmAeabiAttributesTag, Object>> parseAEABI(ByteBuffer byteBuffer) {
        HashMap hashMap = new HashMap();
        while (byteBuffer.position() < byteBuffer.limit()) {
            int position = byteBuffer.position();
            int intValue = readULEB128(byteBuffer).intValue();
            int i = byteBuffer.getInt();
            if (intValue == 1) {
                hashMap.put(Integer.valueOf(intValue), parseFileAttribute(byteBuffer));
            }
            byteBuffer.position(position + i);
        }
        return hashMap;
    }

    private static Map<Integer, Map<ArmAeabiAttributesTag, Object>> parseArmAttributes(ByteBuffer byteBuffer) {
        if (byteBuffer.get() != 65) {
            return Collections.EMPTY_MAP;
        }
        while (byteBuffer.position() < byteBuffer.limit()) {
            int position = byteBuffer.position();
            int i = byteBuffer.getInt();
            if (i <= 0) {
                break;
            }
            if ("aeabi".equals(readNTBS(byteBuffer, null))) {
                return parseAEABI(byteBuffer);
            }
            byteBuffer.position(position + i);
        }
        return Collections.EMPTY_MAP;
    }

    private void parseEabiAapcsVfp(ByteBuffer byteBuffer, RandomAccessFile randomAccessFile) throws IOException {
        for (ELFSectionHeaderEntry eLFSectionHeaderEntry : new ELFSectionHeaders(this._64Bit, this.bigEndian, byteBuffer, randomAccessFile).getEntries()) {
            if (".ARM.attributes".equals(eLFSectionHeaderEntry.getName())) {
                ByteBuffer allocate = ByteBuffer.allocate(eLFSectionHeaderEntry.getSize());
                allocate.order(this.bigEndian ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
                randomAccessFile.getChannel().read(allocate, eLFSectionHeaderEntry.getOffset());
                allocate.rewind();
                Map<ArmAeabiAttributesTag, Object> map = parseArmAttributes(allocate).get(1);
                if (map != null) {
                    Object obj = map.get(ArmAeabiAttributesTag.ABI_VFP_args);
                    if ((obj instanceof Integer) && ((Integer) obj).equals(1)) {
                        this.armEabiAapcsVfp = true;
                    } else if ((obj instanceof BigInteger) && ((BigInteger) obj).intValue() == 1) {
                        this.armEabiAapcsVfp = true;
                    }
                }
            }
        }
    }

    private static Map<ArmAeabiAttributesTag, Object> parseFileAttribute(ByteBuffer byteBuffer) {
        HashMap hashMap = new HashMap();
        while (byteBuffer.position() < byteBuffer.limit()) {
            ArmAeabiAttributesTag byValue = ArmAeabiAttributesTag.getByValue(readULEB128(byteBuffer).intValue());
            int i = C03651.f213xd3185832[byValue.getParameterType().ordinal()];
            if (i == 1) {
                hashMap.put(byValue, Integer.valueOf(byteBuffer.getInt()));
            } else if (i == 2) {
                hashMap.put(byValue, readNTBS(byteBuffer, null));
            } else if (i == 3) {
                hashMap.put(byValue, readULEB128(byteBuffer));
            }
        }
        return hashMap;
    }

    private static String readNTBS(ByteBuffer byteBuffer, Integer num) {
        if (num != null) {
            byteBuffer.position(num.intValue());
        }
        int position = byteBuffer.position();
        while (byteBuffer.get() != 0 && byteBuffer.position() <= byteBuffer.limit()) {
        }
        byte[] bArr = new byte[(byteBuffer.position() - position) - 1];
        byteBuffer.position(position);
        byteBuffer.get(bArr);
        byteBuffer.position(byteBuffer.position() + 1);
        try {
            return new String(bArr, "ASCII");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    private static BigInteger readULEB128(ByteBuffer byteBuffer) {
        BigInteger bigInteger = BigInteger.ZERO;
        int i = 0;
        while (true) {
            int i2 = i;
            byte b = byteBuffer.get();
            bigInteger = bigInteger.or(BigInteger.valueOf(b & Byte.MAX_VALUE).shiftLeft(i2));
            if ((b & 128) == 0) {
                return bigInteger;
            }
            i = i2 + 7;
        }
    }

    private void runDetection() throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(this.filename, "r");
        try {
            if (randomAccessFile.length() > 4) {
                byte[] bArr = new byte[4];
                randomAccessFile.seek(0L);
                randomAccessFile.read(bArr);
                if (Arrays.equals(bArr, ELF_MAGIC)) {
                    this.ELF = true;
                }
            }
            if (!this.ELF) {
                try {
                    randomAccessFile.close();
                    return;
                } catch (IOException e) {
                    return;
                }
            }
            randomAccessFile.seek(4L);
            byte readByte = randomAccessFile.readByte();
            byte readByte2 = randomAccessFile.readByte();
            this._64Bit = readByte == 2;
            this.bigEndian = readByte2 == 2;
            randomAccessFile.seek(0L);
            ByteBuffer allocate = ByteBuffer.allocate(this._64Bit ? 64 : 52);
            randomAccessFile.getChannel().read(allocate, 0L);
            allocate.order(this.bigEndian ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
            this.arm = allocate.get(18) == 40;
            if (this.arm) {
                int i = allocate.getInt(this._64Bit ? 48 : 36);
                this.armHardFloatFlag = (i & EF_ARM_ABI_FLOAT_HARD) == EF_ARM_ABI_FLOAT_HARD;
                boolean z = false;
                if ((i & 512) == 512) {
                    z = true;
                }
                this.armSoftFloatFlag = z;
                parseEabiAapcsVfp(allocate, randomAccessFile);
            }
        } finally {
            try {
                randomAccessFile.close();
            } catch (IOException e2) {
            }
        }
    }

    public String getFilename() {
        return this.filename;
    }

    public boolean is64Bit() {
        return this._64Bit;
    }

    public boolean isArm() {
        return this.arm;
    }

    public boolean isArmEabiAapcsVfp() {
        return this.armEabiAapcsVfp;
    }

    public boolean isArmHardFloat() {
        return isArmEabiAapcsVfp() || isArmHardFloatFlag();
    }

    public boolean isArmHardFloatFlag() {
        return this.armHardFloatFlag;
    }

    public boolean isArmSoftFloatFlag() {
        return this.armSoftFloatFlag;
    }

    public boolean isBigEndian() {
        return this.bigEndian;
    }

    public boolean isELF() {
        return this.ELF;
    }
}
