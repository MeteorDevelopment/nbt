package meteordevelopment.nbt.tags;

public class ShortTag extends Tag implements Comparable<Short> {
    public short value;

    public ShortTag(short value) {
        this.value = value;
    }

    @Override
    public int getId() {
        return TagId.Short.ordinal();
    }

    @Override
    public Tag clone() {
        return new ShortTag(value);
    }

    @Override
    public int compareTo(Short o) {
        return Short.compare(value, o);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShortTag shortTag = (ShortTag) o;

        return value == shortTag.value;
    }

    @Override
    public int hashCode() {
        return value;
    }

    @Override
    public String toString() {
        return Short.toString(value);
    }
}
