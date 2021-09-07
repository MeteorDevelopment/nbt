package meteordevelopment.nbt.tags;

import java.util.Arrays;

public class LongArrayTag extends Tag implements Comparable<LongArrayTag> {
    public long[] value;

    public LongArrayTag(long[] value) {
        this.value = value;
    }

    public LongArrayTag(int size) {
        value = new long[size];
    }

    @Override
    public int getId() {
        return TagId.LongArray.ordinal();
    }

    @Override
    public Tag clone() {
        return new LongArrayTag(Arrays.copyOf(value, value.length));
    }

    @Override
    public int compareTo(LongArrayTag o) {
        return Integer.compare(value.length, o.value.length);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LongArrayTag that = (LongArrayTag) o;

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
