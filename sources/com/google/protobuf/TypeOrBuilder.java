package com.google.protobuf;

import java.util.List;

/* loaded from: classes08-dex2jar.jar:com/google/protobuf/TypeOrBuilder.class */
public interface TypeOrBuilder extends MessageOrBuilder {
    Field getFields(int i);

    int getFieldsCount();

    List<Field> getFieldsList();

    FieldOrBuilder getFieldsOrBuilder(int i);

    List<? extends FieldOrBuilder> getFieldsOrBuilderList();

    String getName();

    ByteString getNameBytes();

    String getOneofs(int i);

    ByteString getOneofsBytes(int i);

    int getOneofsCount();

    List<String> getOneofsList();

    Option getOptions(int i);

    int getOptionsCount();

    List<Option> getOptionsList();

    OptionOrBuilder getOptionsOrBuilder(int i);

    List<? extends OptionOrBuilder> getOptionsOrBuilderList();

    SourceContext getSourceContext();

    SourceContextOrBuilder getSourceContextOrBuilder();

    Syntax getSyntax();

    int getSyntaxValue();

    boolean hasSourceContext();
}
