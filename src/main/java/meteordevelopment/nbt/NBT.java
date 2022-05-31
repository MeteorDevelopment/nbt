package meteordevelopment.nbt;

import meteordevelopment.nbt.tags.CompoundTag;

import java.io.*;

public class NBT {
    public static void write(CompoundTag tag, OutputStream out, boolean compressed) {
        try (NbtWriter nbt = new NbtWriter("", out, compressed)) {
            for (String key : tag.keySet()) nbt.write(key, tag.get(key));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void write(CompoundTag tag, OutputStream out) {
        write(tag, out, false);
    }

    public static void write(CompoundTag tag, File file, boolean compressed) {
        try {
            file.getParentFile().mkdirs();
            write(tag, new FileOutputStream(file), compressed);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void write(CompoundTag tag, File file) {
        write(tag, file, false);
    }

    public static NamedTag read(InputStream stream, boolean compressed) throws NbtFormatException {
        return NbtReader.read(stream, compressed);
    }
    public static NamedTag read(InputStream stream) throws NbtFormatException {
        return read(stream, false);
    }

    public static NamedTag read(File file, boolean compressed) throws NbtFormatException {
        try {
            return read(new FileInputStream(file), compressed);
        } catch (FileNotFoundException e) {
            throw new NbtFormatException(e.getMessage());
        }
    }
    public static NamedTag read(File file) throws NbtFormatException {
        return read(file, false);
    }
}
