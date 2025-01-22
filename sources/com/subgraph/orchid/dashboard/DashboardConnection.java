package com.subgraph.orchid.dashboard;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.Socket;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/dashboard/DashboardConnection.class */
public class DashboardConnection implements Runnable {
    private static final int REFRESH_INTERVAL = 1000;
    private final Dashboard dashboard;
    private final ScheduledExecutorService refreshExecutor = new ScheduledThreadPoolExecutor(1);
    private final Socket socket;

    public DashboardConnection(Dashboard dashboard, Socket socket) {
        this.dashboard = dashboard;
        this.socket = socket;
    }

    private void clear(PrintWriter printWriter) throws IOException {
        emitCSI(printWriter);
        printWriter.write("2J");
    }

    private void closeQuietly(Socket socket) {
        try {
            socket.close();
        } catch (IOException e) {
        }
    }

    private Runnable createRefreshRunnable(final PrintWriter printWriter) {
        return new Runnable() { // from class: com.subgraph.orchid.dashboard.DashboardConnection.1
            @Override // java.lang.Runnable
            public void run() {
                DashboardConnection.this.refresh(printWriter);
            }
        };
    }

    private void emitCSI(Writer writer) throws IOException {
        writer.append((char) 27);
        writer.append('[');
    }

    private void hideCursor(Writer writer) throws IOException {
        emitCSI(writer);
        writer.write("?25l");
    }

    private void moveTo(PrintWriter printWriter, int i, int i2) throws IOException {
        emitCSI(printWriter);
        printWriter.printf("%d;%dH", Integer.valueOf(i + 1), Integer.valueOf(i2 + 1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refresh(PrintWriter printWriter) {
        try {
            if (this.socket.isClosed()) {
                return;
            }
            hideCursor(printWriter);
            clear(printWriter);
            moveTo(printWriter, 0, 0);
            this.dashboard.renderAll(printWriter);
            printWriter.flush();
        } catch (IOException e) {
            closeQuietly(this.socket);
        }
    }

    private void runInputLoop(InputStream inputStream) throws IOException {
        while (true) {
            int read = inputStream.read();
            if (read == -1) {
                return;
            }
            if (read == 99) {
                toggleFlagWithVerbose(1, 2);
            } else if (read == 112) {
                toggleFlag(4);
            }
        }
    }

    private void toggleFlag(int i) {
        if (this.dashboard.isEnabled(i)) {
            this.dashboard.disableFlag(i);
        } else {
            this.dashboard.enableFlag(i);
        }
    }

    private void toggleFlagWithVerbose(int i, int i2) {
        if (this.dashboard.isEnabled(i2)) {
            this.dashboard.disableFlag(i | i2);
        } else if (this.dashboard.isEnabled(i)) {
            this.dashboard.enableFlag(i2);
        } else {
            this.dashboard.enableFlag(i);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x005d, code lost:            if (r9 == null) goto L16;     */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void run() {
        /*
            r8 = this;
            r0 = 0
            r12 = r0
            r0 = 0
            r11 = r0
            r0 = r11
            r10 = r0
            r0 = r12
            r9 = r0
            java.io.PrintWriter r0 = new java.io.PrintWriter     // Catch: java.lang.Throwable -> L4e java.io.IOException -> L89
            r1 = r0
            r2 = r8
            java.net.Socket r2 = r2.socket     // Catch: java.lang.Throwable -> L4e java.io.IOException -> L89
            java.io.OutputStream r2 = r2.getOutputStream()     // Catch: java.lang.Throwable -> L4e java.io.IOException -> L89
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L4e java.io.IOException -> L89
            r13 = r0
            r0 = r11
            r10 = r0
            r0 = r12
            r9 = r0
            r0 = r8
            java.util.concurrent.ScheduledExecutorService r0 = r0.refreshExecutor     // Catch: java.lang.Throwable -> L4e java.io.IOException -> L89
            r1 = r8
            r2 = r13
            java.lang.Runnable r1 = r1.createRefreshRunnable(r2)     // Catch: java.lang.Throwable -> L4e java.io.IOException -> L89
            r2 = 0
            r3 = 1000(0x3e8, double:4.94E-321)
            java.util.concurrent.TimeUnit r4 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch: java.lang.Throwable -> L4e java.io.IOException -> L89
            java.util.concurrent.ScheduledFuture r0 = r0.scheduleAtFixedRate(r1, r2, r3, r4)     // Catch: java.lang.Throwable -> L4e java.io.IOException -> L89
            r11 = r0
            r0 = r11
            r10 = r0
            r0 = r11
            r9 = r0
            r0 = r8
            r1 = r8
            java.net.Socket r1 = r1.socket     // Catch: java.lang.Throwable -> L4e java.io.IOException -> L89
            java.io.InputStream r1 = r1.getInputStream()     // Catch: java.lang.Throwable -> L4e java.io.IOException -> L89
            r0.runInputLoop(r1)     // Catch: java.lang.Throwable -> L4e java.io.IOException -> L89
            r0 = r11
            if (r0 == 0) goto L68
            r0 = r11
            r9 = r0
            goto L60
        L4e:
            r9 = move-exception
            goto L72
        L52:
            r0 = r9
            r10 = r0
            r0 = r8
            r1 = r8
            java.net.Socket r1 = r1.socket     // Catch: java.lang.Throwable -> L4e
            r0.closeQuietly(r1)     // Catch: java.lang.Throwable -> L4e
            r0 = r9
            if (r0 == 0) goto L68
        L60:
            r0 = r9
            r1 = 1
            boolean r0 = r0.cancel(r1)
        L68:
            r0 = r8
            java.util.concurrent.ScheduledExecutorService r0 = r0.refreshExecutor
            r0.shutdown()
            return
        L72:
            r0 = r10
            if (r0 == 0) goto L7e
            r0 = r10
            r1 = 1
            boolean r0 = r0.cancel(r1)
        L7e:
            r0 = r8
            java.util.concurrent.ScheduledExecutorService r0 = r0.refreshExecutor
            r0.shutdown()
            r0 = r9
            throw r0
        L89:
            r10 = move-exception
            goto L52
        */
        throw new UnsupportedOperationException("Method not decompiled: com.subgraph.orchid.dashboard.DashboardConnection.run():void");
    }
}
