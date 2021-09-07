package meteordevelopment.nbt.tags;

public class ByteTag extends Tag implements Comparable<Byte> {
    public byte value;

    public ByteTag(byte value) {
        this.value = value;
    }

    public ByteTag(boolean value) {
        this.value = (byte) (value ? 1 : 0);
    }

    public boolean asBool() {
        return value > 0;
    }

    @Override
    public int getId() {
        return TagId.Byte.ordinal();
    }

    @Override
    public Tag clone() {
        return new ByteTag(value);
    }

    @Override
    public int compareTo(Byte o) {
        return Byte.compare(value, o);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ByteTag byteTag = (ByteTag) o;

        return value == byteTag.value;
    }

    @Override
    public int hashCode() {
        return value;
    }

    @Override
    public String toString() {
        return Byte.toString(value);
    }
}
