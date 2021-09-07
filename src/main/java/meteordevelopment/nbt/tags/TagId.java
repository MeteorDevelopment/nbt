package meteordevelopment.nbt.tags;

public enum TagId {
    End,
    Byte,
    Short,
    Int,
    Long,
    Float,
    Double,
    ByteArray,
    String,
    List,
    Compound,
    IntArray,
    LongArray;

    private static final TagId[] values = values();

    public static boolean isValid(int i) {
        return i >= 0 && i < values.length;
    }

    public static TagId valueOf(int i) {
        return values[i];
    }
}
