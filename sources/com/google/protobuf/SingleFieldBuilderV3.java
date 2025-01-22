package com.google.protobuf;

import com.google.protobuf.AbstractMessage;
import com.google.protobuf.AbstractMessage.Builder;
import com.google.protobuf.MessageOrBuilder;

/* loaded from: classes08-dex2jar.jar:com/google/protobuf/SingleFieldBuilderV3.class */
public class SingleFieldBuilderV3<MType extends AbstractMessage, BType extends AbstractMessage.Builder, IType extends MessageOrBuilder> implements AbstractMessage.BuilderParent {
    private BType builder;
    private boolean isClean;
    private MType message;
    private AbstractMessage.BuilderParent parent;

    public SingleFieldBuilderV3(MType mtype, AbstractMessage.BuilderParent builderParent, boolean z) {
        this.message = (MType) Internal.checkNotNull(mtype);
        this.parent = builderParent;
        this.isClean = z;
    }

    private void onChanged() {
        AbstractMessage.BuilderParent builderParent;
        if (this.builder != null) {
            this.message = null;
        }
        if (!this.isClean || (builderParent = this.parent) == null) {
            return;
        }
        builderParent.markDirty();
        this.isClean = false;
    }

    public MType build() {
        this.isClean = true;
        return getMessage();
    }

    public SingleFieldBuilderV3<MType, BType, IType> clear() {
        MType mtype = this.message;
        this.message = (MType) ((AbstractMessage) (mtype != null ? mtype.getDefaultInstanceForType() : this.builder.getDefaultInstanceForType()));
        BType btype = this.builder;
        if (btype != null) {
            btype.dispose();
            this.builder = null;
        }
        onChanged();
        return this;
    }

    public void dispose() {
        this.parent = null;
    }

    public BType getBuilder() {
        if (this.builder == null) {
            this.builder = this.message.newBuilderForType(this);
            this.builder.mergeFrom(this.message);
            this.builder.markClean();
        }
        return this.builder;
    }

    public MType getMessage() {
        if (this.message == null) {
            this.message = this.builder.buildPartial();
        }
        return this.message;
    }

    public IType getMessageOrBuilder() {
        BType btype = this.builder;
        return btype != null ? btype : this.message;
    }

    public void markDirty() {
        onChanged();
    }

    public SingleFieldBuilderV3<MType, BType, IType> mergeFrom(MType mtype) {
        Message message;
        if (this.builder == null && (message = this.message) == message.getDefaultInstanceForType()) {
            this.message = mtype;
        } else {
            getBuilder().mergeFrom(mtype);
        }
        onChanged();
        return this;
    }

    public SingleFieldBuilderV3<MType, BType, IType> setMessage(MType mtype) {
        this.message = (MType) Internal.checkNotNull(mtype);
        BType btype = this.builder;
        if (btype != null) {
            btype.dispose();
            this.builder = null;
        }
        onChanged();
        return this;
    }
}
