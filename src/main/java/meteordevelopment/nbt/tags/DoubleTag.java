package meteordevelopment.nbt.tags;

public class DoubleTag extends Tag implements Comparable<Double> {
    public double value;

    public DoubleTag(double value) {
        this.value = value;
    }

    @Override
    public int getId() {
        return TagId.Double.ordinal();
    }

    @Override
    public Tag clone() {
        return new DoubleTag(value);
    }

    @Override
    public int compareTo(Double o) {
        return Double.compare(value, o);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DoubleTag doubleTag = (DoubleTag) o;

        return Double.compare(doubleTag.value, value) == 0;
    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(value);
        return (int) (temp ^ (temp >>> 32));
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }
}
