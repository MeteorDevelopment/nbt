package meteordevelopment.nbt.tags;

public class LongTag extends Tag implements Comparable<Long> {
    public long value;

    public LongTag(long value) {
        this.value = value;
    }

    @Override
    public int getId() {
        return TagId.Long.ordinal();
    }

    @Override
    public Tag clone() {
        return new LongTag(value);
    }

    @Override
    public int compareTo(Long o) {
        return Long.compare(value, o);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LongTag longTag = (LongTag) o;

        return value == longTag.value;
    }

    @Override
    public int hashCode() {
        return (int) (value ^ (value >>> 32));
    }

    @Override
    public String toString() {
        return Long.toString(value);
    }
}
