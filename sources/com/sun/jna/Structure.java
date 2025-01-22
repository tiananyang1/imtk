package com.sun.jna;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.nio.Buffer;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes08-dex2jar.jar:com/sun/jna/Structure.class */
public abstract class Structure {
    public static final int ALIGN_DEFAULT = 0;
    public static final int ALIGN_GNUC = 2;
    public static final int ALIGN_MSVC = 3;
    public static final int ALIGN_NONE = 1;
    protected static final int CALCULATE_SIZE = -1;
    private int actualAlignType;
    private int alignType;
    private Structure[] array;
    private boolean autoRead;
    private boolean autoWrite;
    private String encoding;
    private Pointer memory;
    private final Map<String, Object> nativeStrings;
    private boolean readCalled;
    private int size;
    private int structAlignment;
    private Map<String, StructField> structFields;
    private long typeInfo;
    private TypeMapper typeMapper;
    private static final Logger LOG = Logger.getLogger(Structure.class.getName());
    static final Map<Class<?>, LayoutInfo> layoutInfo = new WeakHashMap();
    static final Map<Class<?>, List<String>> fieldOrder = new WeakHashMap();
    private static final ThreadLocal<Map<Pointer, Structure>> reads = new ThreadLocal<Map<Pointer, Structure>>() { // from class: com.sun.jna.Structure.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public Map<Pointer, Structure> initialValue() {
            HashMap hashMap;
            synchronized (this) {
                hashMap = new HashMap();
            }
            return hashMap;
        }
    };
    private static final ThreadLocal<Set<Structure>> busy = new ThreadLocal<Set<Structure>>() { // from class: com.sun.jna.Structure.2
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public Set<Structure> initialValue() {
            StructureSet structureSet;
            synchronized (this) {
                structureSet = new StructureSet();
            }
            return structureSet;
        }
    };
    private static final Pointer PLACEHOLDER_MEMORY = new Pointer(0) { // from class: com.sun.jna.Structure.3
        @Override // com.sun.jna.Pointer
        public Pointer share(long j, long j2) {
            return this;
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes08-dex2jar.jar:com/sun/jna/Structure$AutoAllocated.class */
    public static class AutoAllocated extends Memory {
        public AutoAllocated(int i) {
            super(i);
            super.clear();
        }

        @Override // com.sun.jna.Memory, com.sun.jna.Pointer
        public String toString() {
            return "auto-" + super.toString();
        }
    }

    /* loaded from: classes08-dex2jar.jar:com/sun/jna/Structure$ByReference.class */
    public interface ByReference {
    }

    /* loaded from: classes08-dex2jar.jar:com/sun/jna/Structure$ByValue.class */
    public interface ByValue {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @FieldOrder({"size", "alignment", "type", "elements"})
    /* loaded from: classes08-dex2jar.jar:com/sun/jna/Structure$FFIType.class */
    public static class FFIType extends Structure {
        private static final int FFI_TYPE_STRUCT = 13;
        private static final Map<Object, Object> typeInfoMap = new WeakHashMap();
        public short alignment;
        public Pointer elements;
        public size_t size;
        public short type = 13;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes08-dex2jar.jar:com/sun/jna/Structure$FFIType$FFITypes.class */
        public static class FFITypes {
            private static Pointer ffi_type_double;
            private static Pointer ffi_type_float;
            private static Pointer ffi_type_longdouble;
            private static Pointer ffi_type_pointer;
            private static Pointer ffi_type_sint16;
            private static Pointer ffi_type_sint32;
            private static Pointer ffi_type_sint64;
            private static Pointer ffi_type_sint8;
            private static Pointer ffi_type_uint16;
            private static Pointer ffi_type_uint32;
            private static Pointer ffi_type_uint64;
            private static Pointer ffi_type_uint8;
            private static Pointer ffi_type_void;

            private FFITypes() {
            }
        }

        /* loaded from: classes08-dex2jar.jar:com/sun/jna/Structure$FFIType$size_t.class */
        public static class size_t extends IntegerType {
            private static final long serialVersionUID = 1;

            public size_t() {
                this(0L);
            }

            public size_t(long j) {
                super(Native.SIZE_T_SIZE, j);
            }
        }

        static {
            if (Native.POINTER_SIZE == 0) {
                throw new Error("Native library not initialized");
            }
            if (FFITypes.ffi_type_void == null) {
                throw new Error("FFI types not initialized");
            }
            typeInfoMap.put(Void.TYPE, FFITypes.ffi_type_void);
            typeInfoMap.put(Void.class, FFITypes.ffi_type_void);
            typeInfoMap.put(Float.TYPE, FFITypes.ffi_type_float);
            typeInfoMap.put(Float.class, FFITypes.ffi_type_float);
            typeInfoMap.put(Double.TYPE, FFITypes.ffi_type_double);
            typeInfoMap.put(Double.class, FFITypes.ffi_type_double);
            typeInfoMap.put(Long.TYPE, FFITypes.ffi_type_sint64);
            typeInfoMap.put(Long.class, FFITypes.ffi_type_sint64);
            typeInfoMap.put(Integer.TYPE, FFITypes.ffi_type_sint32);
            typeInfoMap.put(Integer.class, FFITypes.ffi_type_sint32);
            typeInfoMap.put(Short.TYPE, FFITypes.ffi_type_sint16);
            typeInfoMap.put(Short.class, FFITypes.ffi_type_sint16);
            Pointer pointer = Native.WCHAR_SIZE == 2 ? FFITypes.ffi_type_uint16 : FFITypes.ffi_type_uint32;
            typeInfoMap.put(Character.TYPE, pointer);
            typeInfoMap.put(Character.class, pointer);
            typeInfoMap.put(Byte.TYPE, FFITypes.ffi_type_sint8);
            typeInfoMap.put(Byte.class, FFITypes.ffi_type_sint8);
            typeInfoMap.put(Pointer.class, FFITypes.ffi_type_pointer);
            typeInfoMap.put(String.class, FFITypes.ffi_type_pointer);
            typeInfoMap.put(WString.class, FFITypes.ffi_type_pointer);
            typeInfoMap.put(Boolean.TYPE, FFITypes.ffi_type_uint32);
            typeInfoMap.put(Boolean.class, FFITypes.ffi_type_uint32);
        }

        private FFIType(Structure structure) {
            Pointer[] pointerArr;
            structure.ensureAllocated(true);
            int i = 0;
            if (!(structure instanceof Union)) {
                Pointer[] pointerArr2 = new Pointer[structure.fields().size() + 1];
                Iterator<StructField> it = structure.fields().values().iterator();
                while (true) {
                    pointerArr = pointerArr2;
                    if (!it.hasNext()) {
                        break;
                    }
                    pointerArr2[i] = structure.getFieldTypeInfo(it.next());
                    i++;
                }
            } else {
                StructField typeInfoField = ((Union) structure).typeInfoField();
                pointerArr = new Pointer[]{get(structure.getFieldValue(typeInfoField.field), typeInfoField.type), null};
            }
            init(pointerArr);
        }

        private FFIType(Object obj, Class<?> cls) {
            int length = Array.getLength(obj);
            Pointer[] pointerArr = new Pointer[length + 1];
            Pointer pointer = get(null, cls.getComponentType());
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= length) {
                    init(pointerArr);
                    return;
                } else {
                    pointerArr[i2] = pointer;
                    i = i2 + 1;
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static Pointer get(Object obj) {
            return obj == null ? FFITypes.ffi_type_pointer : obj instanceof Class ? get(null, (Class) obj) : get(obj, obj.getClass());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Pointer get(Object obj, Class<?> cls) {
            TypeMapper typeMapper = Native.getTypeMapper(cls);
            Class<?> cls2 = cls;
            if (typeMapper != null) {
                ToNativeConverter toNativeConverter = typeMapper.getToNativeConverter(cls);
                cls2 = cls;
                if (toNativeConverter != null) {
                    cls2 = toNativeConverter.nativeType();
                }
            }
            synchronized (typeInfoMap) {
                Object obj2 = typeInfoMap.get(cls2);
                if (obj2 instanceof Pointer) {
                    return (Pointer) obj2;
                }
                if (obj2 instanceof FFIType) {
                    return ((FFIType) obj2).getPointer();
                }
                if ((Platform.HAS_BUFFERS && Buffer.class.isAssignableFrom(cls2)) || Callback.class.isAssignableFrom(cls2)) {
                    typeInfoMap.put(cls2, FFITypes.ffi_type_pointer);
                    return FFITypes.ffi_type_pointer;
                }
                if (Structure.class.isAssignableFrom(cls2)) {
                    Object obj3 = obj;
                    if (obj == null) {
                        obj3 = newInstance(cls2, Structure.PLACEHOLDER_MEMORY);
                    }
                    if (ByReference.class.isAssignableFrom(cls2)) {
                        typeInfoMap.put(cls2, FFITypes.ffi_type_pointer);
                        return FFITypes.ffi_type_pointer;
                    }
                    FFIType fFIType = new FFIType((Structure) obj3);
                    typeInfoMap.put(cls2, fFIType);
                    return fFIType.getPointer();
                }
                if (NativeMapped.class.isAssignableFrom(cls2)) {
                    NativeMappedConverter nativeMappedConverter = NativeMappedConverter.getInstance(cls2);
                    return get(nativeMappedConverter.toNative(obj, new ToNativeContext()), nativeMappedConverter.nativeType());
                }
                if (cls2.isArray()) {
                    FFIType fFIType2 = new FFIType(obj, cls2);
                    typeInfoMap.put(obj, fFIType2);
                    return fFIType2.getPointer();
                }
                throw new IllegalArgumentException("Unsupported type " + cls2);
            }
        }

        private void init(Pointer[] pointerArr) {
            this.elements = new Memory(Native.POINTER_SIZE * pointerArr.length);
            this.elements.write(0L, pointerArr, 0, pointerArr.length);
            write();
        }
    }

    @Target({ElementType.TYPE})
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    /* loaded from: classes08-dex2jar.jar:com/sun/jna/Structure$FieldOrder.class */
    public @interface FieldOrder {
        String[] value();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes08-dex2jar.jar:com/sun/jna/Structure$LayoutInfo.class */
    public static class LayoutInfo {
        private int alignType;
        private int alignment;
        private final Map<String, StructField> fields;
        private int size;
        private StructField typeInfoField;
        private TypeMapper typeMapper;
        private boolean variable;

        private LayoutInfo() {
            this.size = -1;
            this.alignment = 1;
            this.fields = Collections.synchronizedMap(new LinkedHashMap());
            this.alignType = 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes08-dex2jar.jar:com/sun/jna/Structure$StructField.class */
    public static class StructField {
        public FromNativeContext context;
        public Field field;
        public boolean isReadOnly;
        public boolean isVolatile;
        public String name;
        public FromNativeConverter readConverter;
        public Class<?> type;
        public ToNativeConverter writeConverter;
        public int size = -1;
        public int offset = -1;

        protected StructField() {
        }

        public String toString() {
            return this.name + "@" + this.offset + "[" + this.size + "] (" + this.type + ")";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes08-dex2jar.jar:com/sun/jna/Structure$StructureSet.class */
    public static class StructureSet extends AbstractCollection<Structure> implements Set<Structure> {
        private int count;
        Structure[] elements;

        StructureSet() {
        }

        private void ensureCapacity(int i) {
            Structure[] structureArr = this.elements;
            if (structureArr == null) {
                this.elements = new Structure[(i * 3) / 2];
            } else if (structureArr.length < i) {
                Structure[] structureArr2 = new Structure[(i * 3) / 2];
                System.arraycopy(structureArr, 0, structureArr2, 0, structureArr.length);
                this.elements = structureArr2;
            }
        }

        private int indexOf(Structure structure) {
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= this.count) {
                    return -1;
                }
                Structure structure2 = this.elements[i2];
                if (structure == structure2) {
                    return i2;
                }
                if (structure.getClass() == structure2.getClass() && structure.size() == structure2.size() && structure.getPointer().equals(structure2.getPointer())) {
                    return i2;
                }
                i = i2 + 1;
            }
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean add(Structure structure) {
            if (contains(structure)) {
                return true;
            }
            ensureCapacity(this.count + 1);
            Structure[] structureArr = this.elements;
            int i = this.count;
            this.count = i + 1;
            structureArr[i] = structure;
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return indexOf((Structure) obj) != -1;
        }

        public Structure[] getElements() {
            return this.elements;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Structure> iterator() {
            int i = this.count;
            Structure[] structureArr = new Structure[i];
            if (i > 0) {
                System.arraycopy(this.elements, 0, structureArr, 0, i);
            }
            return Arrays.asList(structureArr).iterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            int indexOf = indexOf((Structure) obj);
            if (indexOf == -1) {
                return false;
            }
            int i = this.count - 1;
            this.count = i;
            if (i < 0) {
                return true;
            }
            Structure[] structureArr = this.elements;
            int i2 = this.count;
            structureArr[indexOf] = structureArr[i2];
            structureArr[i2] = null;
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return this.count;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Structure() {
        this(0);
    }

    protected Structure(int i) {
        this((Pointer) null, i);
    }

    protected Structure(int i, TypeMapper typeMapper) {
        this(null, i, typeMapper);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Structure(Pointer pointer) {
        this(pointer, 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Structure(Pointer pointer, int i) {
        this(pointer, i, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Structure(Pointer pointer, int i, TypeMapper typeMapper) {
        this.size = -1;
        this.nativeStrings = new HashMap();
        this.autoRead = true;
        this.autoWrite = true;
        setAlignType(i);
        setStringEncoding(Native.getStringEncoding(getClass()));
        initializeTypeMapper(typeMapper);
        validateFields();
        if (pointer != null) {
            useMemory(pointer, 0, true);
        } else {
            allocateMemory(-1);
        }
        initializeFields();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Structure(TypeMapper typeMapper) {
        this(null, 0, typeMapper);
    }

    private int addPadding(int i) {
        return addPadding(i, this.structAlignment);
    }

    private int addPadding(int i, int i2) {
        int i3 = i;
        if (this.actualAlignType != 1) {
            int i4 = i % i2;
            i3 = i;
            if (i4 != 0) {
                i3 = i + (i2 - i4);
            }
        }
        return i3;
    }

    private void allocateMemory(boolean z) {
        allocateMemory(calculateSize(true, z));
    }

    public static void autoRead(Structure[] structureArr) {
        structureArrayCheck(structureArr);
        if (structureArr[0].array == structureArr) {
            structureArr[0].autoRead();
            return;
        }
        for (int i = 0; i < structureArr.length; i++) {
            if (structureArr[i] != null) {
                structureArr[i].autoRead();
            }
        }
    }

    public static void autoWrite(Structure[] structureArr) {
        structureArrayCheck(structureArr);
        if (structureArr[0].array == structureArr) {
            structureArr[0].autoWrite();
            return;
        }
        for (int i = 0; i < structureArr.length; i++) {
            if (structureArr[i] != null) {
                structureArr[i].autoWrite();
            }
        }
    }

    private Class<?> baseClass() {
        return (((this instanceof ByReference) || (this instanceof ByValue)) && Structure.class.isAssignableFrom(getClass().getSuperclass())) ? getClass().getSuperclass() : getClass();
    }

    static Set<Structure> busy() {
        return busy.get();
    }

    public static List<String> createFieldsOrder(String str) {
        return Collections.unmodifiableList(Collections.singletonList(str));
    }

    public static List<String> createFieldsOrder(List<String> list, List<String> list2) {
        ArrayList arrayList = new ArrayList(list.size() + list2.size());
        arrayList.addAll(list);
        arrayList.addAll(list2);
        return Collections.unmodifiableList(arrayList);
    }

    public static List<String> createFieldsOrder(List<String> list, String... strArr) {
        return createFieldsOrder(list, (List<String>) Arrays.asList(strArr));
    }

    public static List<String> createFieldsOrder(String... strArr) {
        return Collections.unmodifiableList(Arrays.asList(strArr));
    }

    private LayoutInfo deriveLayout(boolean z, boolean z2) {
        Class<?> type;
        Class<?> cls;
        List<Field> fields = getFields(z);
        if (fields == null) {
            return null;
        }
        LayoutInfo layoutInfo2 = new LayoutInfo();
        layoutInfo2.alignType = this.alignType;
        layoutInfo2.typeMapper = this.typeMapper;
        Iterator<Field> it = fields.iterator();
        int i = 0;
        boolean z3 = true;
        while (true) {
            boolean z4 = z3;
            if (!it.hasNext()) {
                if (i <= 0) {
                    throw new IllegalArgumentException("Structure " + getClass() + " has unknown or zero size (ensure all fields are public)");
                }
                int addPadding = addPadding(i, layoutInfo2.alignment);
                if ((this instanceof ByValue) && !z2) {
                    getTypeInfo();
                }
                layoutInfo2.size = addPadding;
                return layoutInfo2;
            }
            Field next = it.next();
            int modifiers = next.getModifiers();
            type = next.getType();
            if (type.isArray()) {
                layoutInfo2.variable = true;
            }
            StructField structField = new StructField();
            structField.isVolatile = Modifier.isVolatile(modifiers);
            structField.isReadOnly = Modifier.isFinal(modifiers);
            if (structField.isReadOnly) {
                if (!Platform.RO_FIELDS) {
                    throw new IllegalArgumentException("This VM does not support read-only fields (field '" + next.getName() + "' within " + getClass() + ")");
                }
                next.setAccessible(true);
            }
            structField.field = next;
            structField.name = next.getName();
            structField.type = type;
            if (Callback.class.isAssignableFrom(type) && !type.isInterface()) {
                throw new IllegalArgumentException("Structure Callback field '" + next.getName() + "' must be an interface");
            }
            if (type.isArray() && Structure.class.equals(type.getComponentType())) {
                throw new IllegalArgumentException("Nested Structure arrays must use a derived Structure type so that the size of the elements can be determined");
            }
            if (Modifier.isPublic(next.getModifiers())) {
                Object fieldValue = getFieldValue(structField.field);
                if (fieldValue == null && type.isArray()) {
                    if (z) {
                        throw new IllegalStateException("Array fields must be initialized");
                    }
                    return null;
                }
                if (NativeMapped.class.isAssignableFrom(type)) {
                    NativeMappedConverter nativeMappedConverter = NativeMappedConverter.getInstance(type);
                    cls = nativeMappedConverter.nativeType();
                    structField.writeConverter = nativeMappedConverter;
                    structField.readConverter = nativeMappedConverter;
                    structField.context = new StructureReadContext(this, next);
                } else {
                    TypeMapper typeMapper = this.typeMapper;
                    if (typeMapper != null) {
                        ToNativeConverter toNativeConverter = typeMapper.getToNativeConverter(type);
                        FromNativeConverter fromNativeConverter = this.typeMapper.getFromNativeConverter(type);
                        if (toNativeConverter != null && fromNativeConverter != null) {
                            Object obj = toNativeConverter.toNative(fieldValue, new StructureWriteContext(this, structField.field));
                            Class cls2 = obj != null ? obj.getClass() : Pointer.class;
                            structField.writeConverter = toNativeConverter;
                            structField.readConverter = fromNativeConverter;
                            structField.context = new StructureReadContext(this, next);
                            cls = cls2;
                            fieldValue = obj;
                        } else if (toNativeConverter != null || fromNativeConverter != null) {
                            break;
                        }
                    }
                    cls = type;
                }
                Object obj2 = fieldValue;
                if (fieldValue == null) {
                    obj2 = initializeField(structField.field, type);
                }
                try {
                    structField.size = getNativeSize(cls, obj2);
                    int nativeAlignment = getNativeAlignment(cls, obj2, z4);
                    if (nativeAlignment == 0) {
                        throw new Error("Field alignment is zero for field '" + structField.name + "' within " + getClass());
                    }
                    layoutInfo2.alignment = Math.max(layoutInfo2.alignment, nativeAlignment);
                    int i2 = i % nativeAlignment;
                    int i3 = i;
                    if (i2 != 0) {
                        i3 = i + (nativeAlignment - i2);
                    }
                    if (this instanceof Union) {
                        structField.offset = 0;
                        i = Math.max(i3, structField.size);
                    } else {
                        structField.offset = i3;
                        i = structField.size + i3;
                    }
                    layoutInfo2.fields.put(structField.name, structField);
                    if (layoutInfo2.typeInfoField == null || layoutInfo2.typeInfoField.size < structField.size || (layoutInfo2.typeInfoField.size == structField.size && Structure.class.isAssignableFrom(structField.type))) {
                        layoutInfo2.typeInfoField = structField;
                    }
                } catch (IllegalArgumentException e) {
                    if (!z && this.typeMapper == null) {
                        return null;
                    }
                    throw new IllegalArgumentException("Invalid Structure field in " + getClass() + ", field name '" + structField.name + "' (" + structField.type + "): " + e.getMessage(), e);
                }
            }
            z3 = false;
        }
        throw new IllegalArgumentException("Structures require bidirectional type conversion for " + type);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ensureAllocated(boolean z) {
        if (this.memory == null) {
            allocateMemory(z);
            return;
        }
        if (this.size == -1) {
            this.size = calculateSize(true, z);
            Pointer pointer = this.memory;
            if (pointer instanceof AutoAllocated) {
                return;
            }
            try {
                this.memory = pointer.share(0L, this.size);
            } catch (IndexOutOfBoundsException e) {
                throw new IllegalArgumentException("Structure exceeds provided memory bounds", e);
            }
        }
    }

    private List<String> fieldOrder() {
        List<String> list;
        Class<?> cls = getClass();
        synchronized (fieldOrder) {
            List<String> list2 = fieldOrder.get(cls);
            list = list2;
            if (list2 == null) {
                list = getFieldOrder();
                fieldOrder.put(cls, list);
            }
        }
        return list;
    }

    private String format(Class<?> cls) {
        String name = cls.getName();
        return name.substring(name.lastIndexOf(".") + 1);
    }

    static Pointer getTypeInfo(Object obj) {
        return FFIType.get(obj);
    }

    private Object initializeField(Field field, Class<?> cls) {
        if (!Structure.class.isAssignableFrom(cls) || ByReference.class.isAssignableFrom(cls)) {
            if (!NativeMapped.class.isAssignableFrom(cls)) {
                return null;
            }
            NativeMapped defaultValue = NativeMappedConverter.getInstance(cls).defaultValue();
            setFieldValue(field, defaultValue);
            return defaultValue;
        }
        try {
            Structure newInstance = newInstance((Class<Structure>) cls, PLACEHOLDER_MEMORY);
            setFieldValue(field, newInstance);
            return newInstance;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Can't determine size of nested structure", e);
        }
    }

    private void initializeFields() {
        for (Field field : getFieldList()) {
            try {
                if (field.get(this) == null) {
                    initializeField(field, field.getType());
                }
            } catch (Exception e) {
                throw new Error("Exception reading field '" + field.getName() + "' in " + getClass(), e);
            }
        }
    }

    private void initializeTypeMapper(TypeMapper typeMapper) {
        TypeMapper typeMapper2 = typeMapper;
        if (typeMapper == null) {
            typeMapper2 = Native.getTypeMapper(getClass());
        }
        this.typeMapper = typeMapper2;
        layoutChanged();
    }

    private void layoutChanged() {
        if (this.size != -1) {
            this.size = -1;
            if (this.memory instanceof AutoAllocated) {
                this.memory = null;
            }
            ensureAllocated();
        }
    }

    public static <T extends Structure> T newInstance(Class<T> cls) throws IllegalArgumentException {
        T t = (T) Klass.newInstance(cls);
        if (t instanceof ByValue) {
            t.allocateMemory();
        }
        return t;
    }

    private static <T extends Structure> T newInstance(Class<T> cls, long j) {
        try {
            T t = (T) newInstance(cls, j == 0 ? PLACEHOLDER_MEMORY : new Pointer(j));
            if (j != 0) {
                t.conditionalAutoRead();
            }
            return t;
        } catch (Throwable th) {
            LOG.log(Level.WARNING, "JNA: Error creating structure", th);
            return null;
        }
    }

    public static <T extends Structure> T newInstance(Class<T> cls, Pointer pointer) throws IllegalArgumentException {
        try {
            return cls.getConstructor(Pointer.class).newInstance(pointer);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException("Instantiation of " + cls + " (Pointer) not allowed, is it public?", e);
        } catch (InstantiationException e2) {
            throw new IllegalArgumentException("Can't instantiate " + cls, e2);
        } catch (NoSuchMethodException | SecurityException e3) {
            T t = (T) newInstance(cls);
            if (pointer != PLACEHOLDER_MEMORY) {
                t.useMemory(pointer);
            }
            return t;
        } catch (InvocationTargetException e4) {
            throw new IllegalArgumentException("Exception thrown while instantiating an instance of " + cls, e4);
        }
    }

    static Map<Pointer, Structure> reading() {
        return reads.get();
    }

    private void setFieldValue(Field field, Object obj, boolean z) {
        try {
            field.set(this, obj);
        } catch (IllegalAccessException e) {
            if (!Modifier.isFinal(field.getModifiers())) {
                throw new Error("Unexpectedly unable to write to field '" + field.getName() + "' within " + getClass(), e);
            }
            if (!z) {
                throw new UnsupportedOperationException("Attempt to write to read-only field '" + field.getName() + "' within " + getClass(), e);
            }
            throw new UnsupportedOperationException("This VM does not support Structures with final fields (field '" + field.getName() + "' within " + getClass() + ")", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int size(Class<? extends Structure> cls) {
        return size(cls, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v18, types: [com.sun.jna.Structure] */
    public static <T extends Structure> int size(Class<T> cls, T t) {
        LayoutInfo layoutInfo2;
        synchronized (layoutInfo) {
            layoutInfo2 = layoutInfo.get(cls);
        }
        int i = (layoutInfo2 == null || layoutInfo2.variable) ? -1 : layoutInfo2.size;
        int i2 = i;
        if (i == -1) {
            T t2 = t;
            if (t == null) {
                t2 = newInstance(cls, PLACEHOLDER_MEMORY);
            }
            i2 = t2.size();
        }
        return i2;
    }

    private static <T extends Comparable<T>> List<T> sort(Collection<? extends T> collection) {
        ArrayList arrayList = new ArrayList(collection);
        Collections.sort(arrayList);
        return arrayList;
    }

    private static void structureArrayCheck(Structure[] structureArr) {
        if (ByReference[].class.isAssignableFrom(structureArr.getClass())) {
            return;
        }
        Pointer pointer = structureArr[0].getPointer();
        int size = structureArr[0].size();
        int i = 1;
        while (true) {
            int i2 = i;
            if (i2 >= structureArr.length) {
                return;
            }
            if (structureArr[i2].getPointer().peer != pointer.peer + (size * i2)) {
                throw new IllegalArgumentException("Structure array elements must use contiguous memory (bad backing address at Structure array index " + i2 + ")");
            }
            i = i2 + 1;
        }
    }

    private String toString(int i, boolean z, boolean z2) {
        String str;
        String str2;
        String str3;
        ensureAllocated();
        String property = System.getProperty("line.separator");
        String str4 = format(getClass()) + "(" + getPointer() + ")";
        String str5 = str4;
        if (!(getPointer() instanceof Memory)) {
            str5 = str4 + " (" + size() + " bytes)";
        }
        String str6 = "";
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 >= i) {
                break;
            }
            str6 = str6 + "  ";
            i2 = i3 + 1;
        }
        if (z) {
            Iterator<StructField> it = fields().values().iterator();
            String str7 = property;
            while (true) {
                str = str7;
                if (!it.hasNext()) {
                    break;
                }
                StructField next = it.next();
                Object fieldValue = getFieldValue(next.field);
                String format = format(next.type);
                String str8 = str7 + str6;
                if (!next.type.isArray() || fieldValue == null) {
                    str2 = "";
                } else {
                    format = format(next.type.getComponentType());
                    str2 = "[" + Array.getLength(fieldValue) + "]";
                }
                String str9 = str8 + String.format("  %s %s%s@0x%X", format, next.name, str2, Integer.valueOf(next.offset));
                Object obj = fieldValue;
                if (fieldValue instanceof Structure) {
                    obj = ((Structure) fieldValue).toString(i + 1, !(fieldValue instanceof ByReference), z2);
                }
                String str10 = str9 + "=";
                if (obj instanceof Long) {
                    str3 = str10 + String.format("0x%08X", (Long) obj);
                } else if (obj instanceof Integer) {
                    str3 = str10 + String.format("0x%04X", (Integer) obj);
                } else if (obj instanceof Short) {
                    str3 = str10 + String.format("0x%02X", (Short) obj);
                } else if (obj instanceof Byte) {
                    str3 = str10 + String.format("0x%01X", (Byte) obj);
                } else {
                    str3 = str10 + String.valueOf(obj).trim();
                }
                String str11 = str3 + property;
                str7 = str11;
                if (!it.hasNext()) {
                    str7 = str11 + str6 + "}";
                }
            }
        } else {
            str = "...}";
        }
        String str12 = str;
        if (i == 0) {
            str12 = str;
            if (z2) {
                String str13 = str + property + "memory dump" + property;
                byte[] byteArray = getPointer().getByteArray(0L, size());
                int i4 = 0;
                while (true) {
                    int i5 = i4;
                    if (i5 >= byteArray.length) {
                        break;
                    }
                    int i6 = i5 % 4;
                    String str14 = str13;
                    if (i6 == 0) {
                        str14 = str13 + "[";
                    }
                    String str15 = str14;
                    if (byteArray[i5] >= 0) {
                        str15 = str14;
                        if (byteArray[i5] < 16) {
                            str15 = str14 + "0";
                        }
                    }
                    String str16 = str15 + Integer.toHexString(byteArray[i5] & 255);
                    if (i6 == 3) {
                        str13 = str16;
                        if (i5 < byteArray.length - 1) {
                            str13 = str16 + "]" + property;
                        }
                    } else {
                        str13 = str16;
                    }
                    i4 = i5 + 1;
                }
                str12 = str13 + "]";
            }
        }
        return str5 + " {" + str12;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T extends Structure> T updateStructureByReference(Class<T> cls, T t, Pointer pointer) {
        if (pointer == null) {
            return null;
        }
        if (t != null && pointer.equals(t.getPointer())) {
            t.autoRead();
            return t;
        }
        T t2 = (T) reading().get(pointer);
        if (t2 != null && cls.equals(t2.getClass())) {
            t2.autoRead();
            return t2;
        }
        T t3 = (T) newInstance(cls, pointer);
        t3.conditionalAutoRead();
        return t3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void validate(Class<? extends Structure> cls) {
        try {
            cls.getConstructor(new Class[0]);
        } catch (NoSuchMethodException | SecurityException e) {
            throw new IllegalArgumentException("No suitable constructor found for class: " + cls.getName());
        }
    }

    private void validateField(String str, Class<?> cls) {
        ToNativeConverter toNativeConverter;
        TypeMapper typeMapper = this.typeMapper;
        if (typeMapper != null && (toNativeConverter = typeMapper.getToNativeConverter(cls)) != null) {
            validateField(str, toNativeConverter.nativeType());
            return;
        }
        if (cls.isArray()) {
            validateField(str, cls.getComponentType());
            return;
        }
        try {
            getNativeSize(cls);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid Structure field in " + getClass() + ", field name '" + str + "' (" + cls + "): " + e.getMessage(), e);
        }
    }

    private void validateFields() {
        for (Field field : getFieldList()) {
            validateField(field.getName(), field.getType());
        }
    }

    protected void allocateMemory() {
        allocateMemory(false);
    }

    protected void allocateMemory(int i) {
        if (i == -1) {
            i = calculateSize(false);
        } else if (i <= 0) {
            throw new IllegalArgumentException("Structure size must be greater than zero: " + i);
        }
        if (i != -1) {
            Pointer pointer = this.memory;
            if (pointer == null || (pointer instanceof AutoAllocated)) {
                this.memory = autoAllocate(i);
            }
            this.size = i;
        }
    }

    protected Memory autoAllocate(int i) {
        return new AutoAllocated(i);
    }

    public void autoRead() {
        if (!getAutoRead()) {
            return;
        }
        read();
        if (this.array == null) {
            return;
        }
        int i = 1;
        while (true) {
            int i2 = i;
            Structure[] structureArr = this.array;
            if (i2 >= structureArr.length) {
                return;
            }
            structureArr[i2].autoRead();
            i = i2 + 1;
        }
    }

    public void autoWrite() {
        if (!getAutoWrite()) {
            return;
        }
        write();
        if (this.array == null) {
            return;
        }
        int i = 1;
        while (true) {
            int i2 = i;
            Structure[] structureArr = this.array;
            if (i2 >= structureArr.length) {
                return;
            }
            structureArr[i2].autoWrite();
            i = i2 + 1;
        }
    }

    protected void cacheTypeInfo(Pointer pointer) {
        this.typeInfo = pointer.peer;
    }

    protected int calculateSize(boolean z) {
        return calculateSize(z, false);
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x003a, code lost:            if (r4.typeMapper != r0.typeMapper) goto L12;     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    int calculateSize(boolean r5, boolean r6) {
        /*
            r4 = this;
            r0 = r4
            java.lang.Class r0 = r0.getClass()
            r9 = r0
            java.util.Map<java.lang.Class<?>, com.sun.jna.Structure$LayoutInfo> r0 = com.sun.jna.Structure.layoutInfo
            r7 = r0
            r0 = r7
            monitor-enter(r0)
            java.util.Map<java.lang.Class<?>, com.sun.jna.Structure$LayoutInfo> r0 = com.sun.jna.Structure.layoutInfo     // Catch: java.lang.Throwable -> La1
            r1 = r9
            java.lang.Object r0 = r0.get(r1)     // Catch: java.lang.Throwable -> La1
            com.sun.jna.Structure$LayoutInfo r0 = (com.sun.jna.Structure.LayoutInfo) r0     // Catch: java.lang.Throwable -> La1
            r8 = r0
            r0 = r7
            monitor-exit(r0)     // Catch: java.lang.Throwable -> La1
            r0 = r8
            if (r0 == 0) goto L3d
            r0 = r4
            int r0 = r0.alignType
            r1 = r8
            int r1 = com.sun.jna.Structure.LayoutInfo.access$200(r1)
            if (r0 != r1) goto L3d
            r0 = r8
            r7 = r0
            r0 = r4
            com.sun.jna.TypeMapper r0 = r0.typeMapper
            r1 = r8
            com.sun.jna.TypeMapper r1 = com.sun.jna.Structure.LayoutInfo.access$300(r1)
            if (r0 == r1) goto L44
        L3d:
            r0 = r4
            r1 = r5
            r2 = r6
            com.sun.jna.Structure$LayoutInfo r0 = r0.deriveLayout(r1, r2)
            r7 = r0
        L44:
            r0 = r7
            if (r0 == 0) goto L9f
            r0 = r4
            r1 = r7
            int r1 = com.sun.jna.Structure.LayoutInfo.access$400(r1)
            r0.structAlignment = r1
            r0 = r4
            r1 = r7
            java.util.Map r1 = com.sun.jna.Structure.LayoutInfo.access$500(r1)
            r0.structFields = r1
            r0 = r7
            boolean r0 = com.sun.jna.Structure.LayoutInfo.access$000(r0)
            if (r0 != 0) goto L9a
            java.util.Map<java.lang.Class<?>, com.sun.jna.Structure$LayoutInfo> r0 = com.sun.jna.Structure.layoutInfo
            r8 = r0
            r0 = r8
            monitor-enter(r0)
            java.util.Map<java.lang.Class<?>, com.sun.jna.Structure$LayoutInfo> r0 = com.sun.jna.Structure.layoutInfo     // Catch: java.lang.Throwable -> L94
            r1 = r9
            boolean r0 = r0.containsKey(r1)     // Catch: java.lang.Throwable -> L94
            if (r0 == 0) goto L82
            r0 = r4
            int r0 = r0.alignType     // Catch: java.lang.Throwable -> L94
            if (r0 != 0) goto L82
            r0 = r4
            com.sun.jna.TypeMapper r0 = r0.typeMapper     // Catch: java.lang.Throwable -> L94
            if (r0 == 0) goto L8e
        L82:
            java.util.Map<java.lang.Class<?>, com.sun.jna.Structure$LayoutInfo> r0 = com.sun.jna.Structure.layoutInfo     // Catch: java.lang.Throwable -> L94 java.lang.Throwable -> L94
            r1 = r9
            r2 = r7
            java.lang.Object r0 = r0.put(r1, r2)     // Catch: java.lang.Throwable -> L94
        L8e:
            r0 = r8
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L94
            goto L9a
        L94:
            r7 = move-exception
            r0 = r8
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L94
            r0 = r7
            throw r0
        L9a:
            r0 = r7
            int r0 = com.sun.jna.Structure.LayoutInfo.access$100(r0)
            return r0
        L9f:
            r0 = -1
            return r0
        La1:
            r8 = move-exception
            r0 = r7
            monitor-exit(r0)     // Catch: java.lang.Throwable -> La1
            r0 = r8
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.jna.Structure.calculateSize(boolean, boolean):int");
    }

    public void clear() {
        ensureAllocated();
        this.memory.clear(size());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void conditionalAutoRead() {
        if (this.readCalled) {
            return;
        }
        autoRead();
    }

    public boolean dataEquals(Structure structure) {
        return dataEquals(structure, false);
    }

    public boolean dataEquals(Structure structure, boolean z) {
        if (z) {
            structure.getPointer().clear(structure.size());
            structure.write();
            getPointer().clear(size());
            write();
        }
        byte[] byteArray = structure.getPointer().getByteArray(0L, structure.size());
        byte[] byteArray2 = getPointer().getByteArray(0L, size());
        if (byteArray.length != byteArray2.length) {
            return false;
        }
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= byteArray.length) {
                return true;
            }
            if (byteArray[i2] != byteArray2[i2]) {
                return false;
            }
            i = i2 + 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void ensureAllocated() {
        ensureAllocated(false);
    }

    public boolean equals(Object obj) {
        return (obj instanceof Structure) && obj.getClass() == getClass() && ((Structure) obj).getPointer().equals(getPointer());
    }

    protected int fieldOffset(String str) {
        ensureAllocated();
        StructField structField = fields().get(str);
        if (structField != null) {
            return structField.offset;
        }
        throw new IllegalArgumentException("No such field: " + str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Map<String, StructField> fields() {
        return this.structFields;
    }

    public boolean getAutoRead() {
        return this.autoRead;
    }

    public boolean getAutoWrite() {
        return this.autoWrite;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public List<Field> getFieldList() {
        ArrayList arrayList = new ArrayList();
        Class<?> cls = getClass();
        while (true) {
            Class<?> cls2 = cls;
            if (cls2.equals(Structure.class)) {
                return arrayList;
            }
            ArrayList arrayList2 = new ArrayList();
            Field[] declaredFields = cls2.getDeclaredFields();
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 < declaredFields.length) {
                    int modifiers = declaredFields[i2].getModifiers();
                    if (!Modifier.isStatic(modifiers) && Modifier.isPublic(modifiers)) {
                        arrayList2.add(declaredFields[i2]);
                    }
                    i = i2 + 1;
                }
            }
            arrayList.addAll(0, arrayList2);
            cls = cls2.getSuperclass();
        }
    }

    protected List<String> getFieldOrder() {
        LinkedList linkedList = new LinkedList();
        Class<?> cls = getClass();
        while (true) {
            Class<?> cls2 = cls;
            if (cls2 == Structure.class) {
                return Collections.unmodifiableList(linkedList);
            }
            FieldOrder fieldOrder2 = (FieldOrder) cls2.getAnnotation(FieldOrder.class);
            if (fieldOrder2 != null) {
                linkedList.addAll(0, Arrays.asList(fieldOrder2.value()));
            }
            cls = cls2.getSuperclass();
        }
    }

    Pointer getFieldTypeInfo(StructField structField) {
        Class<?> cls = structField.type;
        Object fieldValue = getFieldValue(structField.field);
        TypeMapper typeMapper = this.typeMapper;
        Class<?> cls2 = cls;
        Object obj = fieldValue;
        if (typeMapper != null) {
            ToNativeConverter toNativeConverter = typeMapper.getToNativeConverter(cls);
            cls2 = cls;
            obj = fieldValue;
            if (toNativeConverter != null) {
                cls2 = toNativeConverter.nativeType();
                obj = toNativeConverter.toNative(fieldValue, new ToNativeContext());
            }
        }
        return FFIType.get(obj, cls2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Object getFieldValue(Field field) {
        try {
            return field.get(this);
        } catch (Exception e) {
            throw new Error("Exception reading field '" + field.getName() + "' in " + getClass(), e);
        }
    }

    protected List<Field> getFields(boolean z) {
        List<Field> fieldList = getFieldList();
        HashSet hashSet = new HashSet();
        Iterator<Field> it = fieldList.iterator();
        while (it.hasNext()) {
            hashSet.add(it.next().getName());
        }
        List<String> fieldOrder2 = fieldOrder();
        if (fieldOrder2.size() == fieldList.size() || fieldList.size() <= 1) {
            if (new HashSet(fieldOrder2).equals(hashSet)) {
                sortFields(fieldList, fieldOrder2);
                return fieldList;
            }
            throw new Error("Structure.getFieldOrder() on " + getClass() + " returns names (" + sort(fieldOrder2) + ") which do not match declared field names (" + sort(hashSet) + ")");
        }
        if (!z) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Structure.getFieldOrder() on ");
        sb.append(getClass());
        sb.append(fieldOrder2.size() < fieldList.size() ? " does not provide enough" : " provides too many");
        sb.append(" names [");
        sb.append(fieldOrder2.size());
        sb.append("] (");
        sb.append(sort(fieldOrder2));
        sb.append(") to match declared fields [");
        sb.append(fieldList.size());
        sb.append("] (");
        sb.append(sort(hashSet));
        sb.append(")");
        throw new Error(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x019e, code lost:            if (com.sun.jna.Platform.isPPC() == false) goto L70;     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x01ce, code lost:            if (r13 == java.lang.Double.class) goto L79;     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int getNativeAlignment(java.lang.Class<?> r6, java.lang.Object r7, boolean r8) {
        /*
            Method dump skipped, instructions count: 471
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.jna.Structure.getNativeAlignment(java.lang.Class, java.lang.Object, boolean):int");
    }

    protected int getNativeSize(Class<?> cls) {
        return getNativeSize(cls, null);
    }

    protected int getNativeSize(Class<?> cls, Object obj) {
        return Native.getNativeSize(cls, obj);
    }

    public Pointer getPointer() {
        ensureAllocated();
        return this.memory;
    }

    protected String getStringEncoding() {
        return this.encoding;
    }

    protected int getStructAlignment() {
        if (this.size == -1) {
            calculateSize(true);
        }
        return this.structAlignment;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Pointer getTypeInfo() {
        Pointer typeInfo = getTypeInfo(this);
        cacheTypeInfo(typeInfo);
        return typeInfo;
    }

    TypeMapper getTypeMapper() {
        return this.typeMapper;
    }

    public int hashCode() {
        return getPointer() != null ? getPointer().hashCode() : getClass().hashCode();
    }

    public void read() {
        if (this.memory == PLACEHOLDER_MEMORY) {
            return;
        }
        this.readCalled = true;
        ensureAllocated();
        if (busy().contains(this)) {
            return;
        }
        busy().add(this);
        if (this instanceof ByReference) {
            reading().put(getPointer(), this);
        }
        try {
            Iterator<StructField> it = fields().values().iterator();
            while (it.hasNext()) {
                readField(it.next());
            }
        } finally {
            busy().remove(this);
            if (reading().get(getPointer()) == this) {
                reading().remove(getPointer());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Object readField(StructField structField) {
        int i = structField.offset;
        Class<?> cls = structField.type;
        FromNativeConverter fromNativeConverter = structField.readConverter;
        if (fromNativeConverter != null) {
            cls = fromNativeConverter.nativeType();
        }
        Object obj = null;
        Object fieldValue = (Structure.class.isAssignableFrom(cls) || Callback.class.isAssignableFrom(cls) || (Platform.HAS_BUFFERS && Buffer.class.isAssignableFrom(cls)) || Pointer.class.isAssignableFrom(cls) || NativeMapped.class.isAssignableFrom(cls) || cls.isArray()) ? getFieldValue(structField.field) : null;
        if (cls == String.class) {
            Pointer pointer = this.memory.getPointer(i);
            if (pointer != null) {
                obj = pointer.getString(0L, this.encoding);
            }
        } else {
            obj = this.memory.getValue(i, cls, fieldValue);
        }
        if (fromNativeConverter != null) {
            Object fromNative = fromNativeConverter.fromNative(obj, structField.context);
            obj = fromNative;
            if (fieldValue != null) {
                obj = fromNative;
                if (fieldValue.equals(fromNative)) {
                    obj = fieldValue;
                }
            }
        }
        if (cls.equals(String.class) || cls.equals(WString.class)) {
            this.nativeStrings.put(structField.name + ".ptr", this.memory.getPointer(i));
            this.nativeStrings.put(structField.name + ".val", obj);
        }
        setFieldValue(structField.field, obj, true);
        return obj;
    }

    public Object readField(String str) {
        ensureAllocated();
        StructField structField = fields().get(str);
        if (structField != null) {
            return readField(structField);
        }
        throw new IllegalArgumentException("No such field: " + str);
    }

    protected void setAlignType(int i) {
        this.alignType = i;
        int i2 = i;
        if (i == 0) {
            int structureAlignment = Native.getStructureAlignment(getClass());
            i2 = structureAlignment;
            if (structureAlignment == 0) {
                i2 = Platform.isWindows() ? 3 : 2;
            }
        }
        this.actualAlignType = i2;
        layoutChanged();
    }

    public void setAutoRead(boolean z) {
        this.autoRead = z;
    }

    public void setAutoSynch(boolean z) {
        setAutoRead(z);
        setAutoWrite(z);
    }

    public void setAutoWrite(boolean z) {
        this.autoWrite = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setFieldValue(Field field, Object obj) {
        setFieldValue(field, obj, false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setStringEncoding(String str) {
        this.encoding = str;
    }

    public int size() {
        ensureAllocated();
        return this.size;
    }

    protected void sortFields(List<Field> list, List<String> list2) {
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= list2.size()) {
                return;
            }
            String str = list2.get(i2);
            int i3 = 0;
            while (true) {
                int i4 = i3;
                if (i4 >= list.size()) {
                    break;
                }
                if (str.equals(list.get(i4).getName())) {
                    Collections.swap(list, i2, i4);
                    break;
                }
                i3 = i4 + 1;
            }
            i = i2 + 1;
        }
    }

    public Structure[] toArray(int i) {
        return toArray((Structure[]) Array.newInstance(getClass(), i));
    }

    public Structure[] toArray(Structure[] structureArr) {
        ensureAllocated();
        Pointer pointer = this.memory;
        if (pointer instanceof AutoAllocated) {
            Memory memory = (Memory) pointer;
            int length = structureArr.length * size();
            if (memory.size() < length) {
                useMemory(autoAllocate(length));
            }
        }
        structureArr[0] = this;
        int size = size();
        int i = 1;
        while (true) {
            int i2 = i;
            if (i2 >= structureArr.length) {
                break;
            }
            structureArr[i2] = newInstance(getClass(), this.memory.share(i2 * size, size));
            structureArr[i2].conditionalAutoRead();
            i = i2 + 1;
        }
        if (!(this instanceof ByValue)) {
            this.array = structureArr;
        }
        return structureArr;
    }

    public String toString() {
        return toString(Boolean.getBoolean("jna.dump_memory"));
    }

    public String toString(boolean z) {
        return toString(0, true, z);
    }

    StructField typeInfoField() {
        LayoutInfo layoutInfo2;
        synchronized (layoutInfo) {
            layoutInfo2 = layoutInfo.get(getClass());
        }
        if (layoutInfo2 != null) {
            return layoutInfo2.typeInfoField;
        }
        return null;
    }

    protected void useMemory(Pointer pointer) {
        useMemory(pointer, 0);
    }

    protected void useMemory(Pointer pointer, int i) {
        useMemory(pointer, i, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void useMemory(Pointer pointer, int i, boolean z) {
        try {
            this.nativeStrings.clear();
            if (!(this instanceof ByValue) || z) {
                long j = i;
                this.memory = pointer.share(j);
                if (this.size == -1) {
                    this.size = calculateSize(false);
                }
                if (this.size != -1) {
                    this.memory = pointer.share(j, this.size);
                }
            } else {
                byte[] bArr = new byte[size()];
                pointer.read(0L, bArr, 0, bArr.length);
                this.memory.write(0L, bArr, 0, bArr.length);
            }
            this.array = null;
            this.readCalled = false;
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Structure exceeds provided memory bounds", e);
        }
    }

    public void write() {
        if (this.memory == PLACEHOLDER_MEMORY) {
            return;
        }
        ensureAllocated();
        if (this instanceof ByValue) {
            getTypeInfo();
        }
        if (busy().contains(this)) {
            return;
        }
        busy().add(this);
        try {
            for (StructField structField : fields().values()) {
                if (!structField.isVolatile) {
                    writeField(structField);
                }
            }
        } finally {
            busy().remove(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x005d, code lost:            if (com.sun.jna.WString.class == r12) goto L12;     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void writeField(com.sun.jna.Structure.StructField r8) {
        /*
            Method dump skipped, instructions count: 544
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.jna.Structure.writeField(com.sun.jna.Structure$StructField):void");
    }

    public void writeField(String str) {
        ensureAllocated();
        StructField structField = fields().get(str);
        if (structField != null) {
            writeField(structField);
            return;
        }
        throw new IllegalArgumentException("No such field: " + str);
    }

    public void writeField(String str, Object obj) {
        ensureAllocated();
        StructField structField = fields().get(str);
        if (structField != null) {
            setFieldValue(structField.field, obj);
            writeField(structField);
        } else {
            throw new IllegalArgumentException("No such field: " + str);
        }
    }
}
