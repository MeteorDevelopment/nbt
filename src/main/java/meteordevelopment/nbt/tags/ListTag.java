package meteordevelopment.nbt.tags;

import java.util.*;

public class ListTag<T extends Tag> extends Tag implements List<T>, Comparable<ListTag<T>>, Iterable<T> {
    public List<T> value;
    public int id;

    public ListTag(List<T> value, TagId id) {
        this.value = value;
        this.id = id.ordinal();
    }

    public ListTag(TagId id) {
        this.value = new ArrayList<>();
        this.id = id.ordinal();
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
    public boolean contains(Object o) {
        return value.contains(o);
    }

    @Override
    public Object[] toArray() {
        return value.toArray();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return value.toArray(a);
    }

    @Override
    public boolean add(T t) {
        return value.add(t);
    }

    @Override
    public boolean remove(Object o) {
        return value.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return value.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return value.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return value.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return value.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return value.retainAll(c);
    }

    @Override
    public void clear() {
        value.clear();
    }

    @Override
    public T get(int index) {
        return value.get(index);
    }

    @Override
    public T set(int index, T element) {
        return value.set(index, element);
    }

    @Override
    public void add(int index, T element) {
        value.add(index, element);
    }

    @Override
    public T remove(int index) {
        return value.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return value.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return value.lastIndexOf(o);
    }

    @Override
    public ListIterator<T> listIterator() {
        return value.listIterator();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return value.listIterator(index);
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return value.subList(fromIndex, toIndex);
    }

    @Override
    public int getId() {
        return TagId.List.ordinal();
    }

    @Override
    public Tag clone() {
        List<T> list = new ArrayList<>(size());
        list.addAll(this);

        return new ListTag<T>(list, TagId.valueOf(id));
    }

    @Override
    public int compareTo(ListTag<T> o) {
        return Integer.compare(size(), o.size());
    }

    @Override
    public Iterator<T> iterator() {
        return value.iterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListTag<?> listTag = (ListTag<?>) o;
        if (size() != listTag.size()) return false;

        for (int i = 0; i < size(); i++) {
            if (!get(i).equals(listTag.get(i))) return false;
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

        for (int i = 0; i < size(); i++) {
            if (i > 0) b.append(", ");

            if (i > 7) {
                b.append("...");
                break;
            }

            b.append(get(i));
        }

        return b.append(']').toString();
    }
}
