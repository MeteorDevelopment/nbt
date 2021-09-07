package meteordevelopment.nbt;

import meteordevelopment.nbt.tags.CompoundTag;

public class NamedTag {
    public final String name;
    public final CompoundTag tag;

    public NamedTag(String name, CompoundTag tag) {
        this.name = name;
        this.tag = tag;
    }
}
