package com.subgraph.orchid.directory;

import com.subgraph.orchid.Directory;
import com.subgraph.orchid.GuardEntry;
import com.subgraph.orchid.Router;
import com.subgraph.orchid.data.HexDigest;
import java.util.Date;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/directory/GuardEntryImpl.class */
public class GuardEntryImpl implements GuardEntry {

    /* renamed from: NL */
    private static final String f196NL = System.getProperty("line.separator");
    private Date createdTime;
    private final Directory directory;
    private Date downSince;
    private final String identity;
    private boolean isAdded;
    private Date lastConnect;
    private final Object lock = new Object();
    private final String nickname;
    private final StateFile stateFile;
    private Date unlistedSince;
    private String version;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GuardEntryImpl(Directory directory, StateFile stateFile, String str, String str2) {
        this.directory = directory;
        this.stateFile = stateFile;
        this.nickname = str;
        this.identity = str2;
    }

    private void appendEntryGuardAddedBy(StringBuilder sb) {
        sb.append("EntryGuardAddedBy");
        sb.append(" ");
        sb.append(this.identity);
        sb.append(" ");
        sb.append(this.version);
        sb.append(" ");
        sb.append(formatDate(this.createdTime));
        sb.append(f196NL);
    }

    private void appendEntryGuardDownSince(StringBuilder sb) {
        if (this.downSince == null) {
            return;
        }
        sb.append("EntryGuardDownSince");
        sb.append(" ");
        sb.append(formatDate(this.downSince));
        if (this.lastConnect != null) {
            sb.append(" ");
            sb.append(formatDate(this.lastConnect));
        }
        sb.append(f196NL);
    }

    private void appendEntryGuardLine(StringBuilder sb) {
        sb.append("EntryGuard");
        sb.append(" ");
        sb.append(this.nickname);
        sb.append(" ");
        sb.append(this.identity);
        sb.append(f196NL);
    }

    private void appendEntryGuardUnlistedSince(StringBuilder sb) {
        if (this.unlistedSince == null) {
            return;
        }
        sb.append("EntryGuardUnlistedSince");
        sb.append(" ");
        sb.append(formatDate(this.unlistedSince));
        sb.append(f196NL);
    }

    private Date dup(Date date) {
        if (date == null) {
            return null;
        }
        return new Date(date.getTime());
    }

    private String formatDate(Date date) {
        return this.stateFile.formatDate(date);
    }

    private void markUnusable() {
        synchronized (this) {
            synchronized (this.lock) {
                if (this.unlistedSince == null) {
                    this.unlistedSince = new Date();
                    if (this.isAdded) {
                        this.stateFile.writeFile();
                    }
                }
            }
        }
    }

    private void markUsable() {
        synchronized (this.lock) {
            if (this.unlistedSince != null) {
                this.unlistedSince = null;
                if (this.isAdded) {
                    this.stateFile.writeFile();
                }
            }
        }
    }

    @Override // com.subgraph.orchid.GuardEntry
    public void clearDownSince() {
        synchronized (this.lock) {
            this.downSince = null;
            this.lastConnect = null;
        }
        if (this.isAdded) {
            this.stateFile.writeFile();
        }
    }

    public void clearUnlistedSince() {
        synchronized (this.lock) {
            this.unlistedSince = null;
        }
        if (this.isAdded) {
            this.stateFile.writeFile();
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GuardEntryImpl guardEntryImpl = (GuardEntryImpl) obj;
        String str = this.identity;
        if (str == null) {
            if (guardEntryImpl.identity != null) {
                return false;
            }
        } else if (!str.equals(guardEntryImpl.identity)) {
            return false;
        }
        String str2 = this.nickname;
        return str2 == null ? guardEntryImpl.nickname == null : str2.equals(guardEntryImpl.nickname);
    }

    @Override // com.subgraph.orchid.GuardEntry
    public Date getCreatedTime() {
        Date dup;
        synchronized (this.lock) {
            dup = dup(this.createdTime);
        }
        return dup;
    }

    @Override // com.subgraph.orchid.GuardEntry
    public Date getDownSince() {
        Date dup;
        synchronized (this.lock) {
            dup = dup(this.downSince);
        }
        return dup;
    }

    @Override // com.subgraph.orchid.GuardEntry
    public String getIdentity() {
        return this.identity;
    }

    @Override // com.subgraph.orchid.GuardEntry
    public Date getLastConnectAttempt() {
        Date dup;
        synchronized (this.lock) {
            dup = dup(this.lastConnect);
        }
        return dup;
    }

    @Override // com.subgraph.orchid.GuardEntry
    public String getNickname() {
        return this.nickname;
    }

    @Override // com.subgraph.orchid.GuardEntry
    public Router getRouterForEntry() {
        return this.directory.getRouterByIdentity(HexDigest.createFromString(this.identity));
    }

    @Override // com.subgraph.orchid.GuardEntry
    public Date getUnlistedSince() {
        Date dup;
        synchronized (this.lock) {
            dup = dup(this.unlistedSince);
        }
        return dup;
    }

    @Override // com.subgraph.orchid.GuardEntry
    public String getVersion() {
        return this.version;
    }

    public int hashCode() {
        String str = this.identity;
        int i = 0;
        int hashCode = str == null ? 0 : str.hashCode();
        String str2 = this.nickname;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return ((hashCode + 31) * 31) + i;
    }

    @Override // com.subgraph.orchid.GuardEntry
    public boolean isAdded() {
        return this.isAdded;
    }

    @Override // com.subgraph.orchid.GuardEntry
    public void markAsDown() {
        synchronized (this.lock) {
            Date date = new Date();
            if (this.downSince == null) {
                this.downSince = date;
            } else {
                this.lastConnect = date;
            }
        }
        if (this.isAdded) {
            this.stateFile.writeFile();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setAddedFlag() {
        this.isAdded = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setCreatedTime(Date date) {
        this.createdTime = date;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setDownSince(Date date, Date date2) {
        synchronized (this.lock) {
            this.downSince = date;
            this.lastConnect = date2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setUnlistedSince(Date date) {
        synchronized (this.lock) {
            this.unlistedSince = date;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setVersion(String str) {
        this.version = str;
    }

    @Override // com.subgraph.orchid.GuardEntry
    public boolean testCurrentlyUsable() {
        Router routerForEntry = getRouterForEntry();
        if (routerForEntry != null && routerForEntry.isValid() && routerForEntry.isPossibleGuard() && routerForEntry.isRunning()) {
            markUsable();
            return true;
        }
        markUnusable();
        return false;
    }

    public String writeToString() {
        StringBuilder sb = new StringBuilder();
        synchronized (this.lock) {
            appendEntryGuardLine(sb);
            appendEntryGuardAddedBy(sb);
            if (this.downSince != null) {
                appendEntryGuardDownSince(sb);
            }
            if (this.unlistedSince != null) {
                appendEntryGuardUnlistedSince(sb);
            }
        }
        return sb.toString();
    }
}
