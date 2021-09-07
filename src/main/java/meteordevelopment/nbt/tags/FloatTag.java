package meteordevelopment.nbt.tags;

public class FloatTag extends Tag implements Comparable<Float> {
    public float value;

    public FloatTag(float value) {
        this.value = value;
    }

    @Override
    public int getId() {
        return TagId.Float.ordinal();
    }

    @Override
    public Tag clone() {
        return new FloatTag(value);
    }

    @Override
    public int compareTo(Float o) {
        return Float.compare(value, o);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FloatTag floatTag = (FloatTag) o;

        return Float.compare(floatTag.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return (value != +0.0f ? Float.floatToIntBits(value) : 0);
    }

    @Override
    public String toString() {
        return Float.toString(value);
    }
}
