package com.subgraph.orchid.directory;

import com.subgraph.orchid.Directory;
import com.subgraph.orchid.DirectoryStore;
import com.subgraph.orchid.GuardEntry;
import com.subgraph.orchid.Router;
import com.subgraph.orchid.Tor;
import com.subgraph.orchid.crypto.TorRandom;
import com.xiaomi.mipush.sdk.Constants;
import java.nio.ByteBuffer;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/directory/StateFile.class */
public class StateFile {
    private static final int DATE_LENGTH = 19;
    static final String KEYWORD_ENTRY_GUARD = "EntryGuard";
    static final String KEYWORD_ENTRY_GUARD_ADDED_BY = "EntryGuardAddedBy";
    static final String KEYWORD_ENTRY_GUARD_DOWN_SINCE = "EntryGuardDownSince";
    static final String KEYWORD_ENTRY_GUARD_UNLISTED_SINCE = "EntryGuardUnlistedSince";
    private static final Logger logger = Logger.getLogger(StateFile.class.getName());
    private final Directory directory;
    private final DirectoryStore directoryStore;
    private final List<GuardEntryImpl> guardEntries = new ArrayList();
    private final TorRandom random = new TorRandom();
    private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/directory/StateFile$Line.class */
    public class Line {
        final String line;
        int offset = 0;

        Line(String str) {
            this.line = str;
        }

        private char getChar() {
            return this.line.charAt(this.offset);
        }

        private boolean hasChars() {
            return this.offset < this.line.length();
        }

        private void incrementOffset(int i) {
            this.offset += i;
            if (this.offset > this.line.length()) {
                this.offset = this.line.length();
            }
        }

        private void skipWhitespace() {
            while (hasChars() && Character.isWhitespace(getChar())) {
                this.offset++;
            }
        }

        String nextToken() {
            skipWhitespace();
            if (!hasChars()) {
                return null;
            }
            StringBuilder sb = new StringBuilder();
            while (hasChars() && !Character.isWhitespace(getChar())) {
                sb.append(getChar());
                this.offset++;
            }
            return sb.toString();
        }

        Date parseDate() {
            skipWhitespace();
            if (!hasChars()) {
                return null;
            }
            try {
                Date parse = StateFile.this.dateFormat.parse(this.line.substring(this.offset));
                incrementOffset(19);
                return parse;
            } catch (ParseException e) {
                return null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public StateFile(DirectoryStore directoryStore, Directory directory) {
        this.directoryStore = directoryStore;
        this.directory = directory;
    }

    private void addEntryIfValid(GuardEntryImpl guardEntryImpl) {
        if (isValidEntry(guardEntryImpl)) {
            addGuardEntry(guardEntryImpl, false);
        }
    }

    private void addGuardEntry(GuardEntry guardEntry, boolean z) {
        synchronized (this.guardEntries) {
            if (this.guardEntries.contains(guardEntry)) {
                return;
            }
            GuardEntryImpl guardEntryImpl = (GuardEntryImpl) guardEntry;
            this.guardEntries.add(guardEntryImpl);
            synchronized (guardEntryImpl) {
                guardEntryImpl.setAddedFlag();
                if (z) {
                    writeFile();
                }
            }
        }
    }

    private boolean isValidEntry(GuardEntryImpl guardEntryImpl) {
        return (guardEntryImpl == null || guardEntryImpl.getNickname() == null || guardEntryImpl.getIdentity() == null || guardEntryImpl.getVersion() == null || guardEntryImpl.getCreatedTime() == null) ? false : true;
    }

    private void loadGuardEntries(ByteBuffer byteBuffer) {
        GuardEntryImpl guardEntryImpl = null;
        while (true) {
            GuardEntryImpl guardEntryImpl2 = guardEntryImpl;
            Line readLine = readLine(byteBuffer);
            if (readLine == null) {
                addEntryIfValid(guardEntryImpl2);
                return;
            }
            guardEntryImpl = processLine(readLine, guardEntryImpl2);
        }
    }

    private void processEntryGuardAddedBy(Line line, GuardEntryImpl guardEntryImpl) {
        if (guardEntryImpl == null) {
            logger.warning("EntryGuardAddedBy line seen before EntryGuard in state file");
            return;
        }
        String nextToken = line.nextToken();
        String nextToken2 = line.nextToken();
        Date parseDate = line.parseDate();
        if (nextToken == null || nextToken.isEmpty() || nextToken2 == null || nextToken2.isEmpty() || parseDate == null) {
            logger.warning("Missing EntryGuardAddedBy field in state file");
        } else {
            guardEntryImpl.setVersion(nextToken2);
            guardEntryImpl.setCreatedTime(parseDate);
        }
    }

    private void processEntryGuardDownSince(Line line, GuardEntryImpl guardEntryImpl) {
        if (guardEntryImpl == null) {
            logger.warning("EntryGuardDownSince line seen before EntryGuard in state file");
            return;
        }
        Date parseDate = line.parseDate();
        Date parseDate2 = line.parseDate();
        if (parseDate == null) {
            logger.warning("Failed to parse date field in EntryGuardDownSince line in state file");
        } else {
            guardEntryImpl.setDownSince(parseDate, parseDate2);
        }
    }

    private GuardEntryImpl processEntryGuardLine(Line line) {
        String nextToken = line.nextToken();
        String nextToken2 = line.nextToken();
        if (nextToken != null && !nextToken.isEmpty() && nextToken2 != null && !nextToken2.isEmpty()) {
            return new GuardEntryImpl(this.directory, this, nextToken, nextToken2);
        }
        logger.warning("Failed to parse EntryGuard line: " + line.line);
        return null;
    }

    private void processEntryGuardUnlistedSince(Line line, GuardEntryImpl guardEntryImpl) {
        if (guardEntryImpl == null) {
            logger.warning("EntryGuardUnlistedSince line seen before EntryGuard in state file");
            return;
        }
        Date parseDate = line.parseDate();
        if (parseDate == null) {
            logger.warning("Failed to parse date field in EntryGuardUnlistedSince line in state file");
        } else {
            guardEntryImpl.setUnlistedSince(parseDate);
        }
    }

    private GuardEntryImpl processLine(Line line, GuardEntryImpl guardEntryImpl) {
        String nextToken = line.nextToken();
        if (nextToken == null) {
            return guardEntryImpl;
        }
        if (nextToken.equals(KEYWORD_ENTRY_GUARD)) {
            addEntryIfValid(guardEntryImpl);
            GuardEntryImpl processEntryGuardLine = processEntryGuardLine(line);
            return processEntryGuardLine == null ? guardEntryImpl : processEntryGuardLine;
        }
        if (nextToken.equals(KEYWORD_ENTRY_GUARD_ADDED_BY)) {
            processEntryGuardAddedBy(line, guardEntryImpl);
            return guardEntryImpl;
        }
        if (nextToken.equals(KEYWORD_ENTRY_GUARD_DOWN_SINCE)) {
            processEntryGuardDownSince(line, guardEntryImpl);
            return guardEntryImpl;
        }
        if (nextToken.equals(KEYWORD_ENTRY_GUARD_UNLISTED_SINCE)) {
            processEntryGuardUnlistedSince(line, guardEntryImpl);
        }
        return guardEntryImpl;
    }

    private Line readLine(ByteBuffer byteBuffer) {
        char c;
        if (!byteBuffer.hasRemaining()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        while (byteBuffer.hasRemaining() && (c = (char) (byteBuffer.get() & 255)) != '\n') {
            if (c != '\r') {
                sb.append(c);
            }
        }
        return new Line(sb.toString());
    }

    public void addGuardEntry(GuardEntry guardEntry) {
        addGuardEntry(guardEntry, true);
    }

    public GuardEntry createGuardEntryFor(Router router) {
        GuardEntryImpl guardEntryImpl = new GuardEntryImpl(this.directory, this, router.getNickname(), router.getIdentityHash().toString());
        guardEntryImpl.setVersion(Tor.getImplementation() + Constants.ACCEPT_TIME_SEPARATOR_SERVER + Tor.getVersion());
        guardEntryImpl.setCreatedTime(new Date(new Date().getTime() - (((long) this.random.nextInt(2592000)) * 1000)));
        return guardEntryImpl;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String formatDate(Date date) {
        return this.dateFormat.format(date);
    }

    ByteBuffer getFileContents() {
        StringBuilder sb = new StringBuilder();
        synchronized (this.guardEntries) {
            Iterator<GuardEntryImpl> it = this.guardEntries.iterator();
            while (it.hasNext()) {
                sb.append(it.next().writeToString());
            }
        }
        return ByteBuffer.wrap(sb.toString().getBytes(Tor.getDefaultCharset()));
    }

    public List<GuardEntry> getGuardEntries() {
        ArrayList arrayList;
        synchronized (this.guardEntries) {
            arrayList = new ArrayList(this.guardEntries);
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void parseBuffer(ByteBuffer byteBuffer) {
        synchronized (this.guardEntries) {
            this.guardEntries.clear();
            loadGuardEntries(byteBuffer);
        }
    }

    public void removeGuardEntry(GuardEntry guardEntry) {
        synchronized (this.guardEntries) {
            this.guardEntries.remove(guardEntry);
            writeFile();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writeFile() {
        this.directoryStore.writeData(DirectoryStore.CacheFile.STATE, getFileContents());
    }
}
