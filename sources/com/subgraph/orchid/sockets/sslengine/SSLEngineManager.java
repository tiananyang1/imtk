package com.subgraph.orchid.sockets.sslengine;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;
import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLEngineResult;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/sockets/sslengine/SSLEngineManager.class */
public class SSLEngineManager {
    private static final Logger logger = Logger.getLogger(SSLEngineManager.class.getName());
    private final SSLEngine engine;
    private final HandshakeCallbackHandler handshakeCallback;
    private boolean handshakeStarted = false;
    private final InputStream input;
    private final ByteBuffer myApplicationBuffer;
    private final ByteBuffer myNetworkBuffer;
    private final OutputStream output;
    private final ByteBuffer peerApplicationBuffer;
    private final ByteBuffer peerNetworkBuffer;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.subgraph.orchid.sockets.sslengine.SSLEngineManager$1 */
    /* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/sockets/sslengine/SSLEngineManager$1.class */
    public static /* synthetic */ class C03621 {
        static final /* synthetic */ int[] $SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus = new int[SSLEngineResult.HandshakeStatus.values().length];
        static final /* synthetic */ int[] $SwitchMap$javax$net$ssl$SSLEngineResult$Status;

        /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:30:0x006c
            	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1166)
            	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:1022)
            	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:55)
            */
        static {
            /*
                javax.net.ssl.SSLEngineResult$HandshakeStatus[] r0 = javax.net.ssl.SSLEngineResult.HandshakeStatus.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.subgraph.orchid.sockets.sslengine.SSLEngineManager.C03621.$SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus = r0
                int[] r0 = com.subgraph.orchid.sockets.sslengine.SSLEngineManager.C03621.$SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus     // Catch: java.lang.NoSuchFieldError -> L60
                javax.net.ssl.SSLEngineResult$HandshakeStatus r1 = javax.net.ssl.SSLEngineResult.HandshakeStatus.NEED_TASK     // Catch: java.lang.NoSuchFieldError -> L60
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L60
                r2 = 1
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L60
            L14:
                int[] r0 = com.subgraph.orchid.sockets.sslengine.SSLEngineManager.C03621.$SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus     // Catch: java.lang.NoSuchFieldError -> L60 java.lang.NoSuchFieldError -> L64
                javax.net.ssl.SSLEngineResult$HandshakeStatus r1 = javax.net.ssl.SSLEngineResult.HandshakeStatus.NEED_UNWRAP     // Catch: java.lang.NoSuchFieldError -> L64
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L64
                r2 = 2
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L64
            L1f:
                int[] r0 = com.subgraph.orchid.sockets.sslengine.SSLEngineManager.C03621.$SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus     // Catch: java.lang.NoSuchFieldError -> L64 java.lang.NoSuchFieldError -> L68
                javax.net.ssl.SSLEngineResult$HandshakeStatus r1 = javax.net.ssl.SSLEngineResult.HandshakeStatus.NEED_WRAP     // Catch: java.lang.NoSuchFieldError -> L68
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L68
                r2 = 3
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L68
            L2a:
                javax.net.ssl.SSLEngineResult$Status[] r0 = javax.net.ssl.SSLEngineResult.Status.values()     // Catch: java.lang.NoSuchFieldError -> L68
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.subgraph.orchid.sockets.sslengine.SSLEngineManager.C03621.$SwitchMap$javax$net$ssl$SSLEngineResult$Status = r0
                int[] r0 = com.subgraph.orchid.sockets.sslengine.SSLEngineManager.C03621.$SwitchMap$javax$net$ssl$SSLEngineResult$Status     // Catch: java.lang.NoSuchFieldError -> L6c
                javax.net.ssl.SSLEngineResult$Status r1 = javax.net.ssl.SSLEngineResult.Status.BUFFER_OVERFLOW     // Catch: java.lang.NoSuchFieldError -> L6c
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L6c
                r2 = 1
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L6c
            L3e:
                int[] r0 = com.subgraph.orchid.sockets.sslengine.SSLEngineManager.C03621.$SwitchMap$javax$net$ssl$SSLEngineResult$Status     // Catch: java.lang.NoSuchFieldError -> L6c java.lang.NoSuchFieldError -> L70
                javax.net.ssl.SSLEngineResult$Status r1 = javax.net.ssl.SSLEngineResult.Status.BUFFER_UNDERFLOW     // Catch: java.lang.NoSuchFieldError -> L70
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L70
                r2 = 2
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L70
            L49:
                int[] r0 = com.subgraph.orchid.sockets.sslengine.SSLEngineManager.C03621.$SwitchMap$javax$net$ssl$SSLEngineResult$Status     // Catch: java.lang.NoSuchFieldError -> L70 java.lang.NoSuchFieldError -> L74
                javax.net.ssl.SSLEngineResult$Status r1 = javax.net.ssl.SSLEngineResult.Status.CLOSED     // Catch: java.lang.NoSuchFieldError -> L74
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L74
                r2 = 3
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L74
            L54:
                int[] r0 = com.subgraph.orchid.sockets.sslengine.SSLEngineManager.C03621.$SwitchMap$javax$net$ssl$SSLEngineResult$Status     // Catch: java.lang.NoSuchFieldError -> L74 java.lang.NoSuchFieldError -> L78
                javax.net.ssl.SSLEngineResult$Status r1 = javax.net.ssl.SSLEngineResult.Status.OK     // Catch: java.lang.NoSuchFieldError -> L78
                int r1 = r1.ordinal()     // Catch: java.lang.NoSuchFieldError -> L78
                r2 = 4
                r0[r1] = r2     // Catch: java.lang.NoSuchFieldError -> L78
                return
            L60:
                r4 = move-exception
                goto L14
            L64:
                r4 = move-exception
                goto L1f
            L68:
                r4 = move-exception
                goto L2a
            L6c:
                r4 = move-exception
                goto L3e
            L70:
                r4 = move-exception
                goto L49
            L74:
                r4 = move-exception
                goto L54
            L78:
                r4 = move-exception
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.subgraph.orchid.sockets.sslengine.SSLEngineManager.C03621.m3885clinit():void");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SSLEngineManager(SSLEngine sSLEngine, HandshakeCallbackHandler handshakeCallbackHandler, InputStream inputStream, OutputStream outputStream) {
        this.engine = sSLEngine;
        this.handshakeCallback = handshakeCallbackHandler;
        this.input = inputStream;
        this.output = outputStream;
        SSLSession session = sSLEngine.getSession();
        this.peerApplicationBuffer = createApplicationBuffer(session);
        this.peerNetworkBuffer = createPacketBuffer(session);
        this.myApplicationBuffer = createApplicationBuffer(session);
        this.myNetworkBuffer = createPacketBuffer(session);
    }

    private static ByteBuffer createApplicationBuffer(SSLSession sSLSession) {
        return createBuffer(sSLSession.getApplicationBufferSize());
    }

    private static ByteBuffer createBuffer(int i) {
        return ByteBuffer.wrap(new byte[i]);
    }

    private static ByteBuffer createPacketBuffer(SSLSession sSLSession) {
        return createBuffer(sSLSession.getPacketBufferSize());
    }

    private void handshakeFinished() {
        HandshakeCallbackHandler handshakeCallbackHandler = this.handshakeCallback;
        if (handshakeCallbackHandler != null) {
            handshakeCallbackHandler.handshakeCompleted();
        }
    }

    private boolean handshakeUnwrap() throws IOException {
        logger.fine("handshakeUnwrap()");
        if (!this.engine.isInboundDone() && this.peerNetworkBuffer.position() == 0 && networkReadBuffer(this.peerNetworkBuffer) < 0) {
            return false;
        }
        this.peerNetworkBuffer.flip();
        SSLEngineResult unwrap = this.engine.unwrap(this.peerNetworkBuffer, this.peerApplicationBuffer);
        this.peerNetworkBuffer.compact();
        if (logger.isLoggable(Level.FINE)) {
            logResult(unwrap);
        }
        if (unwrap.getHandshakeStatus() == SSLEngineResult.HandshakeStatus.FINISHED) {
            handshakeFinished();
        }
        int i = C03621.$SwitchMap$javax$net$ssl$SSLEngineResult$Status[unwrap.getStatus().ordinal()];
        if (i == 2) {
            return networkReadBuffer(this.peerNetworkBuffer) >= 0;
        }
        if (i != 3) {
            return i == 4;
        }
        if (!this.engine.isOutboundDone()) {
            return false;
        }
        this.output.close();
        return false;
    }

    private boolean handshakeWrap() throws IOException {
        logger.fine("handshakeWrap()");
        this.myApplicationBuffer.flip();
        SSLEngineResult wrap = this.engine.wrap(this.myApplicationBuffer, this.myNetworkBuffer);
        this.myApplicationBuffer.compact();
        if (logger.isLoggable(Level.FINE)) {
            logResult(wrap);
        }
        if (wrap.getHandshakeStatus() == SSLEngineResult.HandshakeStatus.FINISHED) {
            handshakeFinished();
        }
        if (wrap.getStatus() == SSLEngineResult.Status.CLOSED) {
            try {
                flush();
            } catch (SocketException e) {
                e.printStackTrace();
            }
        } else {
            flush();
        }
        int i = C03621.$SwitchMap$javax$net$ssl$SSLEngineResult$Status[wrap.getStatus().ordinal()];
        if (i != 3) {
            return i == 4;
        }
        if (!this.engine.isOutboundDone()) {
            return false;
        }
        this.output.close();
        return false;
    }

    private void logResult(SSLEngineResult sSLEngineResult) {
        logger.fine("Result status=" + sSLEngineResult.getStatus() + " hss=" + sSLEngineResult.getHandshakeStatus() + " consumed = " + sSLEngineResult.bytesConsumed() + " produced = " + sSLEngineResult.bytesProduced());
    }

    private int networkReadBuffer(ByteBuffer byteBuffer) throws IOException {
        byte[] array = byteBuffer.array();
        int position = byteBuffer.position();
        int limit = byteBuffer.limit() - position;
        int read = this.input.read(array, position, limit);
        if (read != -1) {
            byteBuffer.position(position + read);
        }
        logger.fine("networkReadBuffer(b, " + position + ", " + limit + ") = " + read);
        return read;
    }

    private void networkWriteBuffer(ByteBuffer byteBuffer) throws IOException {
        byte[] array = byteBuffer.array();
        int position = byteBuffer.position();
        int limit = byteBuffer.limit() - position;
        logger.fine("networkWriteBuffer(b, " + position + ", " + limit + ")");
        this.output.write(array, position, limit);
        this.output.flush();
        byteBuffer.position(byteBuffer.limit());
    }

    private boolean processHandshake() throws IOException {
        SSLEngineResult.HandshakeStatus handshakeStatus = this.engine.getHandshakeStatus();
        logger.fine("processHandshake() hs = " + handshakeStatus);
        int i = C03621.$SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus[handshakeStatus.ordinal()];
        if (i == 1) {
            synchronousRunDelegatedTasks();
            return processHandshake();
        }
        if (i == 2) {
            return handshakeUnwrap();
        }
        if (i != 3) {
            return false;
        }
        return handshakeWrap();
    }

    private boolean runHandshake() throws IOException {
        boolean z = false;
        while (true) {
            boolean z2 = z;
            if (!processHandshake()) {
                return z2;
            }
            z = true;
        }
    }

    private void synchronousRunDelegatedTasks() {
        logger.fine("runDelegatedTasks()");
        while (true) {
            Runnable delegatedTask = this.engine.getDelegatedTask();
            if (delegatedTask == null) {
                return;
            }
            logger.fine("Running a task: " + delegatedTask);
            delegatedTask.run();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void close() throws IOException {
        try {
            flush();
            if (!this.engine.isOutboundDone()) {
                this.engine.closeOutbound();
                runHandshake();
            } else if (!this.engine.isInboundDone()) {
                this.engine.closeInbound();
                runHandshake();
            }
        } finally {
            this.output.close();
        }
    }

    void flush() throws IOException {
        this.myNetworkBuffer.flip();
        networkWriteBuffer(this.myNetworkBuffer);
        this.myNetworkBuffer.compact();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ByteBuffer getRecvBuffer() {
        return this.peerApplicationBuffer;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ByteBuffer getSendBuffer() {
        return this.myApplicationBuffer;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int read() throws IOException {
        int networkReadBuffer;
        logger.fine("read()");
        if (!this.handshakeStarted) {
            startHandshake();
        }
        if (this.engine.isInboundDone() || (networkReadBuffer = networkReadBuffer(this.peerNetworkBuffer)) == -1) {
            return -1;
        }
        int position = this.peerApplicationBuffer.position();
        this.peerNetworkBuffer.flip();
        SSLEngineResult unwrap = this.engine.unwrap(this.peerNetworkBuffer, this.peerApplicationBuffer);
        this.peerNetworkBuffer.compact();
        if (logger.isLoggable(Level.FINE)) {
            logResult(unwrap);
        }
        int i = C03621.$SwitchMap$javax$net$ssl$SSLEngineResult$Status[unwrap.getStatus().ordinal()];
        if (i == 1) {
            throw new BufferOverflowException();
        }
        if (i == 2) {
            return 0;
        }
        if (i == 3) {
            this.input.close();
        }
        runHandshake();
        if (networkReadBuffer == -1) {
            this.engine.closeInbound();
        }
        if (this.engine.isInboundDone()) {
            return -1;
        }
        return this.peerApplicationBuffer.position() - position;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void startHandshake() throws IOException {
        logger.fine("startHandshake()");
        this.handshakeStarted = true;
        this.engine.beginHandshake();
        runHandshake();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int write() throws IOException {
        logger.fine("write()");
        if (!this.handshakeStarted) {
            startHandshake();
        }
        int position = this.myApplicationBuffer.position();
        if (position == 0) {
            return 0;
        }
        this.myNetworkBuffer.clear();
        this.myApplicationBuffer.flip();
        SSLEngineResult wrap = this.engine.wrap(this.myApplicationBuffer, this.myNetworkBuffer);
        this.myApplicationBuffer.compact();
        if (logger.isLoggable(Level.FINE)) {
            logResult(wrap);
        }
        int i = C03621.$SwitchMap$javax$net$ssl$SSLEngineResult$Status[wrap.getStatus().ordinal()];
        if (i == 1) {
            throw new BufferOverflowException();
        }
        if (i == 2) {
            throw new BufferUnderflowException();
        }
        if (i == 3) {
            throw new SSLException("SSLEngine is closed");
        }
        flush();
        if (runHandshake()) {
            write();
        }
        return position - this.myApplicationBuffer.position();
    }
}
