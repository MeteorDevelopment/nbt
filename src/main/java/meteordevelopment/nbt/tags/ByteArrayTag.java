package meteordevelopment.nbt.tags;

import java.util.Arrays;

public class ByteArrayTag extends Tag implements Comparable<ByteArrayTag> {
    public byte[] value;

    public ByteArrayTag(byte[] value) {
        this.value = value;
    }

    public ByteArrayTag(int size) {
        value = new byte[size];
    }

    @Override
    public int getId() {
        return TagId.ByteArray.ordinal();
    }

    @Override
    public Tag clone() {
        return new ByteArrayTag(Arrays.copyOf(value, value.length));
    }

    @Override
    public int compareTo(ByteArrayTag o) {
        return Integer.compare(value.length, o.value.length);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ByteArrayTag that = (ByteArrayTag) o;

        return Arrays.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(value);
    }

    @SuppressWarnings("Duplicates")
    @Override
    public String toString() {
        if (value.length == 0) return "[]";

        StringBuilder b = new StringBuilder();
        b.append('[');

        for (int i = 0; i < value.length; i++) {
            if (i > 0) b.append(", ");

            if (i > 7) {
                b.append("...");
                break;
            }

            b.append(value[i]);
        }

        return b.append(']').toString();
    }
}
