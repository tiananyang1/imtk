package com.google.protobuf;

import com.google.protobuf.Field;
import java.util.List;

/* loaded from: classes08-dex2jar.jar:com/google/protobuf/FieldOrBuilder.class */
public interface FieldOrBuilder extends MessageOrBuilder {
    Field.Cardinality getCardinality();

    int getCardinalityValue();

    String getDefaultValue();

    ByteString getDefaultValueBytes();

    String getJsonName();

    ByteString getJsonNameBytes();

    Field.Kind getKind();

    int getKindValue();

    String getName();

    ByteString getNameBytes();

    int getNumber();

    int getOneofIndex();

    Option getOptions(int i);

    int getOptionsCount();

    List<Option> getOptionsList();

    OptionOrBuilder getOptionsOrBuilder(int i);

    List<? extends OptionOrBuilder> getOptionsOrBuilderList();

    boolean getPacked();

    String getTypeUrl();

    ByteString getTypeUrlBytes();
}
