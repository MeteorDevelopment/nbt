package meteordevelopment.nbt.tags;

public class IntTag extends Tag implements Comparable<Integer> {
    public int value;

    public IntTag(int value) {
        this.value = value;
    }

    @Override
    public int getId() {
        return TagId.Int.ordinal();
    }

    @Override
    public Tag clone() {
        return new IntTag(value);
    }

    @Override
    public int compareTo(Integer o) {
        return Integer.compare(value, o);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IntTag intTag = (IntTag) o;

        return value == intTag.value;
    }

    @Override
    public int hashCode() {
        return value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
