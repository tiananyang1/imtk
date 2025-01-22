package com.subgraph.orchid.directory.parsing;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/directory/parsing/DocumentObject.class */
public class DocumentObject {
    private String bodyContent;
    private String footerLine;
    private final String headerLine;
    private final String keyword;
    private final StringBuilder stringContent = new StringBuilder();

    public DocumentObject(String str, String str2) {
        this.keyword = str;
        this.headerLine = str2;
    }

    public void addContent(String str) {
        this.stringContent.append(str);
        this.stringContent.append("\n");
    }

    public void addFooterLine(String str) {
        this.footerLine = str;
        this.bodyContent = this.stringContent.toString();
    }

    public String getContent() {
        return getContent(true);
    }

    public String getContent(boolean z) {
        if (!z) {
            return this.bodyContent;
        }
        return this.headerLine + "\n" + this.bodyContent + this.footerLine + "\n";
    }

    public String getKeyword() {
        return this.keyword;
    }
}
