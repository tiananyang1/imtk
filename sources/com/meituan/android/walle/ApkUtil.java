package com.meituan.android.walle;

import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: classes08-dex2jar.jar:com/meituan/android/walle/ApkUtil.class */
final class ApkUtil {
    public static final int APK_CHANNEL_BLOCK_ID = 1903654775;
    public static final int APK_SIGNATURE_SCHEME_V2_BLOCK_ID = 1896449818;
    public static final long APK_SIG_BLOCK_MAGIC_HI = 3617552046287187010L;
    public static final long APK_SIG_BLOCK_MAGIC_LO = 2334950737559900225L;
    private static final int APK_SIG_BLOCK_MIN_SIZE = 32;
    public static final String DEFAULT_CHARSET = "UTF-8";
    private static final int UINT16_MAX_VALUE = 65535;
    private static final int ZIP_EOCD_COMMENT_LENGTH_FIELD_OFFSET = 20;
    private static final int ZIP_EOCD_REC_MIN_SIZE = 22;
    private static final int ZIP_EOCD_REC_SIG = 101010256;

    private ApkUtil() {
    }

    private static void checkByteOrderLittleEndian(ByteBuffer byteBuffer) {
        if (byteBuffer.order() != ByteOrder.LITTLE_ENDIAN) {
            throw new IllegalArgumentException("ByteBuffer byte order must be little endian");
        }
    }

    public static Pair<ByteBuffer, Long> findApkSigningBlock(FileChannel fileChannel) throws IOException, SignatureNotFoundException {
        return findApkSigningBlock(fileChannel, findCentralDirStartOffset(fileChannel));
    }

    public static Pair<ByteBuffer, Long> findApkSigningBlock(FileChannel fileChannel, long j) throws IOException, SignatureNotFoundException {
        if (j < 32) {
            throw new SignatureNotFoundException("APK too small for APK Signing Block. ZIP Central Directory offset: " + j);
        }
        fileChannel.position(j - 24);
        ByteBuffer allocate = ByteBuffer.allocate(24);
        fileChannel.read(allocate);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        if (allocate.getLong(8) != APK_SIG_BLOCK_MAGIC_LO || allocate.getLong(16) != APK_SIG_BLOCK_MAGIC_HI) {
            throw new SignatureNotFoundException("No APK Signing Block before ZIP Central Directory");
        }
        long j2 = allocate.getLong(0);
        if (j2 < allocate.capacity() || j2 > 2147483639) {
            throw new SignatureNotFoundException("APK Signing Block size out of range: " + j2);
        }
        int i = (int) (8 + j2);
        long j3 = j - i;
        if (j3 < 0) {
            throw new SignatureNotFoundException("APK Signing Block offset out of range: " + j3);
        }
        fileChannel.position(j3);
        ByteBuffer allocate2 = ByteBuffer.allocate(i);
        fileChannel.read(allocate2);
        allocate2.order(ByteOrder.LITTLE_ENDIAN);
        long j4 = allocate2.getLong(0);
        if (j4 == j2) {
            return Pair.m9of(allocate2, Long.valueOf(j3));
        }
        throw new SignatureNotFoundException("APK Signing Block sizes in header and footer do not match: " + j4 + " vs " + j2);
    }

    public static long findCentralDirStartOffset(FileChannel fileChannel) throws IOException {
        return findCentralDirStartOffset(fileChannel, getCommentLength(fileChannel));
    }

    public static long findCentralDirStartOffset(FileChannel fileChannel, long j) throws IOException {
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        fileChannel.position((fileChannel.size() - j) - 6);
        fileChannel.read(allocate);
        return allocate.getInt(0);
    }

    public static Map<Integer, ByteBuffer> findIdValues(ByteBuffer byteBuffer) throws SignatureNotFoundException {
        checkByteOrderLittleEndian(byteBuffer);
        ByteBuffer sliceFromTo = sliceFromTo(byteBuffer, 8, byteBuffer.capacity() - 24);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int i = 0;
        while (sliceFromTo.hasRemaining()) {
            i++;
            if (sliceFromTo.remaining() < 8) {
                throw new SignatureNotFoundException("Insufficient data to read size of APK Signing Block entry #" + i);
            }
            long j = sliceFromTo.getLong();
            if (j < 4 || j > 2147483647L) {
                throw new SignatureNotFoundException("APK Signing Block entry #" + i + " size out of range: " + j);
            }
            int i2 = (int) j;
            int position = sliceFromTo.position();
            if (i2 > sliceFromTo.remaining()) {
                throw new SignatureNotFoundException("APK Signing Block entry #" + i + " size out of range: " + i2 + ", available: " + sliceFromTo.remaining());
            }
            linkedHashMap.put(Integer.valueOf(sliceFromTo.getInt()), getByteBuffer(sliceFromTo, i2 - 4));
            sliceFromTo.position(position + i2);
        }
        return linkedHashMap;
    }

    private static ByteBuffer getByteBuffer(ByteBuffer byteBuffer, int i) throws BufferUnderflowException {
        if (i < 0) {
            throw new IllegalArgumentException("size: " + i);
        }
        int limit = byteBuffer.limit();
        int position = byteBuffer.position();
        int i2 = i + position;
        if (i2 < position || i2 > limit) {
            throw new BufferUnderflowException();
        }
        byteBuffer.limit(i2);
        try {
            ByteBuffer slice = byteBuffer.slice();
            slice.order(byteBuffer.order());
            byteBuffer.position(i2);
            return slice;
        } finally {
            byteBuffer.limit(limit);
        }
    }

    public static long getCommentLength(FileChannel fileChannel) throws IOException {
        long size = fileChannel.size();
        if (size < 22) {
            throw new IOException("APK too small for ZIP End of Central Directory (EOCD) record");
        }
        long j = size - 22;
        long min = Math.min(j, 65535L);
        int i = 0;
        while (true) {
            int i2 = i;
            long j2 = i2;
            if (j2 > min) {
                throw new IOException("ZIP End of Central Directory (EOCD) record not found");
            }
            long j3 = j - j2;
            ByteBuffer allocate = ByteBuffer.allocate(4);
            fileChannel.position(j3);
            fileChannel.read(allocate);
            allocate.order(ByteOrder.LITTLE_ENDIAN);
            if (allocate.getInt(0) == ZIP_EOCD_REC_SIG) {
                ByteBuffer allocate2 = ByteBuffer.allocate(2);
                fileChannel.position(j3 + 20);
                fileChannel.read(allocate2);
                allocate2.order(ByteOrder.LITTLE_ENDIAN);
                int i3 = allocate2.getShort(0);
                if (i3 == i2) {
                    return i3;
                }
            }
            i = i2 + 1;
        }
    }

    private static ByteBuffer sliceFromTo(ByteBuffer byteBuffer, int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException("start: " + i);
        }
        if (i2 < i) {
            throw new IllegalArgumentException("end < start: " + i2 + " < " + i);
        }
        int capacity = byteBuffer.capacity();
        if (i2 > byteBuffer.capacity()) {
            throw new IllegalArgumentException("end > capacity: " + i2 + " > " + capacity);
        }
        int limit = byteBuffer.limit();
        int position = byteBuffer.position();
        try {
            byteBuffer.position(0);
            byteBuffer.limit(i2);
            byteBuffer.position(i);
            ByteBuffer slice = byteBuffer.slice();
            slice.order(byteBuffer.order());
            return slice;
        } finally {
            byteBuffer.position(0);
            byteBuffer.limit(limit);
            byteBuffer.position(position);
        }
    }
}
