package meteordevelopment.nbt.tags;

import java.util.Objects;

public class StringTag extends Tag {
    public String value;

    public StringTag(String value) {
        this.value = value;
    }

    @Override
    public int getId() {
        return TagId.String.ordinal();
    }

    @Override
    public Tag clone() {
        return new StringTag(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StringTag stringTag = (StringTag) o;

        return Objects.equals(value, stringTag.value);
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }

    @Override
    public String toString() {
        return value;
    }
}
