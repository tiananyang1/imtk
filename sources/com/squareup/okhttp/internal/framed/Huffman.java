package com.squareup.okhttp.internal.framed;

import com.sun.jna.Function;
import im.imkey.imkeylibrary.common.Constants;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes08-dex2jar.jar:com/squareup/okhttp/internal/framed/Huffman.class */
class Huffman {
    private static final int[] CODES = {8184, 8388568, 268435426, 268435427, 268435428, 268435429, 268435430, 268435431, 268435432, 16777194, 1073741820, 268435433, 268435434, 1073741821, 268435435, 268435436, 268435437, 268435438, 268435439, 268435440, 268435441, 268435442, 1073741822, 268435443, 268435444, 268435445, 268435446, 268435447, 268435448, 268435449, 268435450, 268435451, 20, 1016, 1017, 4090, 8185, 21, 248, 2042, 1018, 1019, 249, 2043, 250, 22, 23, 24, 0, 1, 2, 25, 26, 27, 28, 29, 30, 31, 92, 251, 32764, 32, 4091, 1020, 8186, 33, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, Constants.MAX_UTXO_NUMBER, 115, 253, 8187, 524272, 8188, 16380, 34, 32765, 3, 35, 4, 36, 5, 37, 38, 39, 6, 116, 117, 40, 41, 42, 7, 43, 118, 44, 8, 9, 45, 119, 120, 121, 122, 123, 32766, 2044, 16381, 8189, 268435452, 1048550, 4194258, 1048551, 1048552, 4194259, 4194260, 4194261, 8388569, 4194262, 8388570, 8388571, 8388572, 8388573, 8388574, 16777195, 8388575, 16777196, 16777197, 4194263, 8388576, 16777198, 8388577, 8388578, 8388579, 8388580, 2097116, 4194264, 8388581, 4194265, 8388582, 8388583, 16777199, 4194266, 2097117, 1048553, 4194267, 4194268, 8388584, 8388585, 2097118, 8388586, 4194269, 4194270, 16777200, 2097119, 4194271, 8388587, 8388588, 2097120, 2097121, 4194272, 2097122, 8388589, 4194273, 8388590, 8388591, 1048554, 4194274, 4194275, 4194276, 8388592, 4194277, 4194278, 8388593, 67108832, 67108833, 1048555, 524273, 4194279, 8388594, 4194280, 33554412, 67108834, 67108835, 67108836, 134217694, 134217695, 67108837, 16777201, 33554413, 524274, 2097123, 67108838, 134217696, 134217697, 67108839, 134217698, 16777202, 2097124, 2097125, 67108840, 67108841, 268435453, 134217699, 134217700, 134217701, 1048556, 16777203, 1048557, 2097126, 4194281, 2097127, 2097128, 8388595, 4194282, 4194283, 33554414, 33554415, 16777204, 16777205, 67108842, 8388596, 67108843, 134217702, 67108844, 67108845, 134217703, 134217704, 134217705, 134217706, 134217707, 268435454, 134217708, 134217709, 134217710, 134217711, 134217712, 67108846};
    private static final byte[] CODE_LENGTHS = {13, 23, 28, 28, 28, 28, 28, 28, 28, 24, 30, 28, 28, 30, 28, 28, 28, 28, 28, 28, 28, 28, 30, 28, 28, 28, 28, 28, 28, 28, 28, 28, 6, 10, 10, 12, 13, 6, 8, 11, 10, 10, 8, 11, 8, 6, 6, 6, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 7, 8, 15, 6, 12, 10, 13, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 8, 7, 8, 13, 19, 13, 14, 6, 15, 5, 6, 5, 6, 5, 6, 6, 6, 5, 7, 7, 6, 6, 6, 5, 6, 7, 6, 5, 5, 6, 7, 7, 7, 7, 7, 15, 11, 14, 13, 28, 20, 22, 20, 20, 22, 22, 22, 23, 22, 23, 23, 23, 23, 23, 24, 23, 24, 24, 22, 23, 24, 23, 23, 23, 23, 21, 22, 23, 22, 23, 23, 24, 22, 21, 20, 22, 22, 23, 23, 21, 23, 22, 22, 24, 21, 22, 23, 23, 21, 21, 22, 21, 23, 22, 23, 23, 20, 22, 22, 22, 23, 22, 22, 23, 26, 26, 20, 19, 22, 23, 22, 25, 26, 26, 26, 27, 27, 26, 24, 25, 19, 21, 26, 27, 27, 26, 27, 24, 21, 21, 26, 26, 28, 27, 27, 27, 20, 24, 20, 21, 22, 21, 21, 23, 22, 22, 25, 25, 24, 24, 26, 23, 26, 27, 26, 26, 27, 27, 27, 27, 27, 28, 27, 27, 27, 27, 27, 26};
    private static final Huffman INSTANCE = new Huffman();
    private final Node root = new Node();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes08-dex2jar.jar:com/squareup/okhttp/internal/framed/Huffman$Node.class */
    public static final class Node {
        private final Node[] children;
        private final int symbol;
        private final int terminalBits;

        Node() {
            this.children = new Node[Function.MAX_NARGS];
            this.symbol = 0;
            this.terminalBits = 0;
        }

        Node(int i, int i2) {
            this.children = null;
            this.symbol = i;
            int i3 = i2 & 7;
            this.terminalBits = i3 == 0 ? 8 : i3;
        }
    }

    private Huffman() {
        buildTree();
    }

    private void addCode(int i, int i2, byte b) {
        Node node = new Node(i, b);
        Node node2 = this.root;
        while (true) {
            Node node3 = node2;
            if (b > 8) {
                b = (byte) (b - 8);
                int i3 = (i2 >>> b) & 255;
                if (node3.children == null) {
                    throw new IllegalStateException("invalid dictionary: prefix not unique");
                }
                if (node3.children[i3] == null) {
                    node3.children[i3] = new Node();
                }
                node2 = node3.children[i3];
            } else {
                int i4 = 8 - b;
                int i5 = (i2 << i4) & 255;
                int i6 = i5;
                while (true) {
                    int i7 = i6;
                    if (i7 >= i5 + (1 << i4)) {
                        return;
                    }
                    node3.children[i7] = node;
                    i6 = i7 + 1;
                }
            }
        }
    }

    private void buildTree() {
        int i = 0;
        while (true) {
            int i2 = i;
            byte[] bArr = CODE_LENGTHS;
            if (i2 >= bArr.length) {
                return;
            }
            addCode(i2, CODES[i2], bArr[i2]);
            i = i2 + 1;
        }
    }

    public static Huffman get() {
        return INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] decode(byte[] bArr) throws IOException {
        int i;
        Node node;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Node node2 = this.root;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (true) {
            i = i4;
            node = node2;
            if (i2 >= bArr.length) {
                break;
            }
            i3 = (i3 << 8) | (bArr[i2] & 255);
            i4 += 8;
            while (i4 >= 8) {
                node2 = node2.children[(i3 >>> (i4 - 8)) & 255];
                if (node2.children == null) {
                    byteArrayOutputStream.write(node2.symbol);
                    i4 -= node2.terminalBits;
                    node2 = this.root;
                } else {
                    i4 -= 8;
                }
            }
            i2++;
        }
        while (i > 0) {
            Node node3 = node.children[(i3 << (8 - i)) & 255];
            if (node3.children != null || node3.terminalBits > i) {
                break;
            }
            byteArrayOutputStream.write(node3.symbol);
            i -= node3.terminalBits;
            node = this.root;
        }
        return byteArrayOutputStream.toByteArray();
    }

    void encode(byte[] bArr, OutputStream outputStream) throws IOException {
        long j = 0;
        int i = 0;
        for (byte b : bArr) {
            int i2 = b & 255;
            int i3 = CODES[i2];
            byte b2 = CODE_LENGTHS[i2];
            j = (j << b2) | i3;
            i += b2;
            while (i >= 8) {
                i -= 8;
                outputStream.write((int) (j >> i));
            }
        }
        if (i > 0) {
            outputStream.write((int) ((255 >>> i) | (j << (8 - i))));
        }
    }

    int encodedLength(byte[] bArr) {
        long j = 0;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= bArr.length) {
                return (int) ((j + 7) >> 3);
            }
            j += CODE_LENGTHS[bArr[i2] & 255];
            i = i2 + 1;
        }
    }
}
