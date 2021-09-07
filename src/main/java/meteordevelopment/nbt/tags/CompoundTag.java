package meteordevelopment.nbt.tags;

import java.util.*;

public class CompoundTag extends Tag implements Map<String, Tag>, Comparable<CompoundTag>, Iterable<Map.Entry<String, Tag>> {
    public Map<String, Tag> value;

    public CompoundTag(Map<String, Tag> value) {
        this.value = value;
    }

    public CompoundTag() {
        value = new HashMap<>();
    }

    @Override
    public int size() {
        return value.size();
    }

    @Override
    public boolean isEmpty() {
        return value.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return value.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return this.value.containsValue(value);
    }

    @Override
    public Tag get(Object key) {
        return value.get(key);
    }

    @Override
    public Tag put(String key, Tag value) {
        return this.value.put(key, value);
    }

    @Override
    public Tag remove(Object key) {
        return value.remove(key);
    }

    @Override
    public void putAll(Map<? extends String, ? extends Tag> m) {
        value.putAll(m);
    }

    @Override
    public void clear() {
        value.clear();
    }

    @Override
    public Set<String> keySet() {
        return value.keySet();
    }

    @Override
    public Collection<Tag> values() {
        return value.values();
    }

    @Override
    public Set<Entry<String, Tag>> entrySet() {
        return value.entrySet();
    }

    public <T extends Tag> T get(String key, Class<T> klass) {
        Tag tag = get(key);
        return tag == null ? null : klass.cast(tag);
    }

    public boolean is(String key, Class<? extends Tag> klass) {
        Tag tag = get(key);
        return tag != null && klass == tag.getClass();
    }

    public boolean getBool(String key) {
        return get(key, ByteTag.class).asBool();
    }

    public short getShort(String key) {
        return get(key, ShortTag.class).value;
    }

    public byte getByte(String key) {
        return get(key, ByteTag.class).value;
    }

    public int getInt(String key) {
        return get(key, IntTag.class).value;
    }

    public long getLong(String key) {
        return get(key, LongTag.class).value;
    }

    public float getFloat(String key) {
        return get(key, FloatTag.class).value;
    }

    public double getDouble(String key) {
        return get(key, DoubleTag.class).value;
    }

    public String getString(String key) {
        return get(key, StringTag.class).value;
    }

    public byte[] getByteArray(String key) {
        return get(key, ByteArrayTag.class).value;
    }

    public int[] getIntArray(String key) {
        return get(key, IntArrayTag.class).value;
    }

    public long[] getLongArray(String key) {
        return get(key, LongArrayTag.class).value;
    }

    public CompoundTag getCompound(String key) {
        return get(key, CompoundTag.class);
    }

    @SuppressWarnings("unchecked")
    public <T extends Tag> ListTag<T> getList(String key, Class<T> klass) {
        return get(key, ListTag.class);
    }

    public void putByte(String key, byte v) {
        put(key, new ByteTag(v));
    }

    public void putBool(String key, boolean v) {
        put(key, new ByteTag(v));
    }

    public void putShort(String key, short v) {
        put(key, new ShortTag(v));
    }

    public void putInt(String key, int v) {
        put(key, new IntTag(v));
    }

    public void putLong(String key, long v) {
        put(key, new LongTag(v));
    }

    public void putFloat(String key, float v) {
        put(key, new FloatTag(v));
    }

    public void putDouble(String key, double v) {
        put(key, new DoubleTag(v));
    }

    public void putString(String key, String v) {
        put(key, new StringTag(v));
    }

    public void putByteArray(String key, byte[] v) {
        put(key, new ByteArrayTag(v));
    }

    public void putIntArray(String key, int[] v) {
        put(key, new IntArrayTag(v));
    }

    public void putLongArray(String key, long[] v) {
        put(key, new LongArrayTag(v));
    }

    public void putList(String key, List<? extends Tag> v, TagId id) {
        put(key, new ListTag<>(v, id));
    }

    @Override
    public int getId() {
        return TagId.Compound.ordinal();
    }

    @Override
    public Tag clone() {
        Map<String, Tag> clone = new HashMap<>(size());

        for (String key : keySet()) {
            clone.put(key, get(key).clone());
        }

        return new CompoundTag(clone);
    }

    @Override
    public int compareTo(CompoundTag o) {
        return Integer.compare(size(), o.size());
    }

    @Override
    public Iterator<Map.Entry<String, Tag>> iterator() {
        return entrySet().iterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompoundTag entries = (CompoundTag) o;

        for (String key : keySet()) {
            Tag tag = entries.get(key);
            if (tag == null || !get(key).equals(tag)) return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }

    @Override
    public String toString() {
        if (size() == 0) return "[]";

        StringBuilder b = new StringBuilder();
        b.append('[');

        int i = 0;
        for (String key : keySet()) {
            if (i > 0) b.append(", ");

            if (i > 7) {
                b.append("...");
                break;
            }

            b.append(key).append(": ").append(get(key));
            i++;
        }

        return b.append(']').toString();
    }
}
