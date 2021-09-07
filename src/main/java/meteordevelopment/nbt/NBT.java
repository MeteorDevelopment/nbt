package meteordevelopment.nbt;

import meteordevelopment.nbt.tags.CompoundTag;

import java.io.*;

public class NBT {
    public static void write(CompoundTag tag, OutputStream out) {
        try (NbtWriter nbt = new NbtWriter("", out)) {
            for (String key : tag.keySet()) nbt.write(key, tag.get(key));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void write(CompoundTag tag, File file) {
        try {
            write(tag, new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static NamedTag read(InputStream stream) throws NbtFormatException {
        return NbtReader.read(stream);
    }

    public static NamedTag read(File file) throws NbtFormatException {
        try {
            return read(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            throw new NbtFormatException(e.getMessage());
        }
    }
}
