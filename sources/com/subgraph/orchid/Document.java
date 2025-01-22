package com.subgraph.orchid;

import java.nio.ByteBuffer;

/* loaded from: classes08-dex2jar.jar:com/subgraph/orchid/Document.class */
public interface Document {
    ByteBuffer getRawDocumentBytes();

    String getRawDocumentData();

    boolean isValidDocument();
}
