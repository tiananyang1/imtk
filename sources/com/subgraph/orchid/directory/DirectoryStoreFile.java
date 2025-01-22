package com.subgraph.orchid.directory;

import com.subgraph.orchid.Document;
import com.subgraph.orchid.TorConfig;
import com.subgraph.orchid.crypto.TorRandom;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/directory/DirectoryStoreFile.class */
public class DirectoryStoreFile {
    private final String cacheFilename;
    private final TorConfig config;
    private boolean directoryCreationFailed;
    private RandomAccessFile openFile;
    private boolean openFileFailed;
    private static final Logger logger = Logger.getLogger(DirectoryStoreFile.class.getName());
    private static final ByteBuffer EMPTY_BUFFER = ByteBuffer.allocate(0);
    private static final TorRandom random = new TorRandom();

    /* JADX INFO: Access modifiers changed from: package-private */
    public DirectoryStoreFile(TorConfig torConfig, String str) {
        this.config = torConfig;
        this.cacheFilename = str;
    }

    private ByteBuffer createBufferForChannel(FileChannel fileChannel) throws IOException {
        return ByteBuffer.allocateDirect((int) (fileChannel.size() & (-1)));
    }

    private void createDirectoryIfMissing() {
        if (this.directoryCreationFailed) {
            return;
        }
        File dataDirectory = this.config.getDataDirectory();
        if (dataDirectory.exists() || dataDirectory.mkdirs()) {
            return;
        }
        this.directoryCreationFailed = true;
        logger.warning("Failed to create data directory " + dataDirectory);
    }

    private File createTempFile() {
        long nextLong = random.nextLong();
        File file = new File(this.config.getDataDirectory(), this.cacheFilename + Long.toString(nextLong));
        file.deleteOnExit();
        return file;
    }

    private boolean ensureOpened() {
        boolean z = false;
        if (this.openFileFailed) {
            return false;
        }
        if (this.openFile != null) {
            return true;
        }
        this.openFile = openFile();
        if (this.openFile != null) {
            z = true;
        }
        return z;
    }

    private boolean fileExists() {
        return getFile().exists();
    }

    private File getFile() {
        return new File(this.config.getDataDirectory(), this.cacheFilename);
    }

    private void installTempFile(File file) {
        close();
        File file2 = getFile();
        if (file2.exists() && !file2.delete()) {
            logger.warning("Failed to delete file " + file2);
        }
        if (!file.renameTo(file2)) {
            logger.warning("Failed to rename temp file " + file + " to " + file2);
        }
        file.delete();
        ensureOpened();
    }

    private RandomAccessFile openFile() {
        try {
            File file = new File(this.config.getDataDirectory(), this.cacheFilename);
            createDirectoryIfMissing();
            return new RandomAccessFile(file, "rw");
        } catch (FileNotFoundException e) {
            this.openFileFailed = true;
            logger.warning("Failed to open cache file " + this.cacheFilename);
            return null;
        }
    }

    private FileOutputStream openFileOutputStream(File file) {
        try {
            createDirectoryIfMissing();
            return new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            logger.warning("Failed to open file " + file + " : " + e);
            return null;
        }
    }

    private void quietClose(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException e) {
        }
    }

    private ByteBuffer readAllFromChannel(FileChannel fileChannel) throws IOException {
        fileChannel.position(0L);
        ByteBuffer createBufferForChannel = createBufferForChannel(fileChannel);
        while (createBufferForChannel.hasRemaining()) {
            if (fileChannel.read(createBufferForChannel) == -1) {
                logger.warning("Unexpected EOF reading from cache file");
                return EMPTY_BUFFER;
            }
        }
        createBufferForChannel.rewind();
        return createBufferForChannel;
    }

    private void writeAllToChannel(WritableByteChannel writableByteChannel, ByteBuffer byteBuffer) throws IOException {
        byteBuffer.rewind();
        while (byteBuffer.hasRemaining()) {
            writableByteChannel.write(byteBuffer);
        }
    }

    private void writeDocumentsToChannel(FileChannel fileChannel, List<? extends Document> list) throws IOException {
        Iterator<? extends Document> it = list.iterator();
        while (it.hasNext()) {
            writeAllToChannel(fileChannel, it.next().getRawDocumentBytes());
        }
    }

    public void appendDocuments(List<? extends Document> list) {
        if (ensureOpened()) {
            try {
                FileChannel channel = this.openFile.getChannel();
                channel.position(channel.size());
                writeDocumentsToChannel(channel, list);
                channel.force(true);
            } catch (IOException e) {
                logger.warning("I/O error writing to cache file " + this.cacheFilename);
            }
        }
    }

    void close() {
        RandomAccessFile randomAccessFile = this.openFile;
        if (randomAccessFile != null) {
            quietClose(randomAccessFile);
            this.openFile = null;
        }
    }

    public ByteBuffer loadContents() {
        if (!fileExists() || !ensureOpened()) {
            return EMPTY_BUFFER;
        }
        try {
            return readAllFromChannel(this.openFile.getChannel());
        } catch (IOException e) {
            logger.warning("I/O error reading cache file " + this.cacheFilename + " : " + e);
            return EMPTY_BUFFER;
        }
    }

    public void remove() {
        close();
        getFile().delete();
    }

    public void writeData(ByteBuffer byteBuffer) {
        File createTempFile = createTempFile();
        FileOutputStream openFileOutputStream = openFileOutputStream(createTempFile);
        if (openFileOutputStream == null) {
            return;
        }
        try {
            writeAllToChannel(openFileOutputStream.getChannel(), byteBuffer);
            quietClose(openFileOutputStream);
            installTempFile(createTempFile);
        } catch (IOException e) {
            logger.warning("I/O error writing to temporary cache file " + createTempFile + " : " + e);
        } finally {
            quietClose(openFileOutputStream);
            createTempFile.delete();
        }
    }

    public void writeDocuments(List<? extends Document> list) {
        File createTempFile = createTempFile();
        FileOutputStream openFileOutputStream = openFileOutputStream(createTempFile);
        if (openFileOutputStream == null) {
            return;
        }
        try {
            writeDocumentsToChannel(openFileOutputStream.getChannel(), list);
            quietClose(openFileOutputStream);
            installTempFile(createTempFile);
        } catch (IOException e) {
            logger.warning("I/O error writing to temporary cache file " + createTempFile + " : " + e);
        } finally {
            quietClose(openFileOutputStream);
            createTempFile.delete();
        }
    }
}
