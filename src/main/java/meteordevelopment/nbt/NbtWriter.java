package meteordevelopment.nbt;

import meteordevelopment.nbt.tags.*;

import java.io.*;
import java.util.zip.GZIPOutputStream;

public class NbtWriter implements Closeable, AutoCloseable {
    private final OutputStream stream;
    private final DataOutput out;

    public NbtWriter(String name, OutputStream stream, boolean compressed) {
        if (compressed) {
            try {
                stream = new GZIPOutputStream(stream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        this.stream = stream;
        this.out = new DataOutputStream(stream);

        writeCompoundStart(name == null ? "" : name);
    }
    public NbtWriter(String name, OutputStream stream) {
        this(name, stream, false);
    }

    public NbtWriter(String name, File file, boolean compressed) throws FileNotFoundException {
        this(name, new FileOutputStream(file), compressed);
    }
    public NbtWriter(String name, File file) throws FileNotFoundException {
        this(name, file, false);
    }

    public void writeCompoundStart(String name) {
        try {
            writeHeader(TagId.Compound.ordinal(), name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeCompoundStart() { writeCompoundStart(null); }

    public void writeCompoundEnd() {
        try {
            out.writeByte(TagId.End.ordinal());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeByte(String name, byte v) {
        try {
            writeHeader(TagId.Byte.ordinal(), name);
            out.writeByte(v);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeByte(byte v) { writeByte(null, v); }

    public void writeBool(String name, boolean v) {
        writeByte(name, (byte) (v ? 1 : 0));
    }
    public void writeBool(boolean v) { writeBool(null, v); }

    public void writeShort(String name, short v) {
        try {
            writeHeader(TagId.Short.ordinal(), name);
            out.writeShort(v);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeShort(short v) { writeShort(null, v); }

    public void writeInt(String name, int v) {
        try {
            writeHeader(TagId.Int.ordinal(), name);
            out.writeInt(v);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeInt(int v) { writeInt(null, v); }

    public void writeLong(String name, long v) {
        try {
            writeHeader(TagId.Long.ordinal(), name);
            out.writeLong(v);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeLong(long v) { writeLong(null, v); }

    public void writeFloat(String name, float v) {
        try {
            writeHeader(TagId.Float.ordinal(), name);
            out.writeFloat(v);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeFloat(float v) { writeFloat(null, v); }

    public void writeDouble(String name, double v) {
        try {
            writeHeader(TagId.Double.ordinal(), name);
            out.writeDouble(v);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeDouble(double v) { writeDouble(null, v); }

    public void writeString(String name, String v) {
        try {
            writeHeader(TagId.String.ordinal(), name);
            out.writeUTF(v);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeString(String v) { writeString(null, v); }

    public void writeByteArray(String name, byte[] v) {
        try {
            writeHeader(TagId.ByteArray.ordinal(), name);
            out.writeInt(v.length);
            out.write(v);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeByteArray(byte[] v) { writeByteArray(null, v); }

    public void writeIntArray(String name, int[] v) {
        try {
            writeHeader(TagId.IntArray.ordinal(), name);
            out.writeInt(v.length);
            for (int j : v) out.writeInt(j);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeIntArray(int[] v) { writeIntArray(null, v); }

    public void writeLongArray(String name, long[] v) {
        try {
            writeHeader(TagId.LongArray.ordinal(), name);
            out.writeInt(v.length);
            for (long l : v) out.writeLong(l);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeLongArray(long[] v) { writeLongArray(null, v); }

    public void writeList(String name, int type, int size) {
        try {
            writeHeader(TagId.List.ordinal(), name);
            out.writeByte(type);
            out.writeInt(size);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeList(int type, int size) { writeList(null, type, size); }
    public void writeList(String name, TagId type, int size) { writeList(name, type.ordinal(), size); }
    public void writeList(TagId type, int size) { writeList(null, type.ordinal(), size); }

    public void write(String name, Tag tag) {
        try {
            writeHeader(tag.getId(), name);

            switch (TagId.valueOf(tag.getId())) {
                case Byte -> out.writeByte(((ByteTag) tag).value);
                case Short -> out.writeShort(((ShortTag) tag).value);
                case Int -> out.writeInt(((IntTag) tag).value);
                case Long -> out.writeLong(((LongTag) tag).value);
                case Float -> out.writeFloat(((FloatTag) tag).value);
                case Double -> out.writeDouble(((DoubleTag) tag).value);
                case String -> out.writeUTF(((StringTag) tag).value);
                case ByteArray -> {
                    ByteArrayTag t = (ByteArrayTag) tag;

                    out.writeInt(t.value.length);
                    out.write(t.value);
                }
                case IntArray -> {
                    IntArrayTag t = (IntArrayTag) tag;

                    out.writeInt(t.value.length);
                    for (int v : t.value) out.writeInt(v);
                }
                case LongArray -> {
                    LongArrayTag t = (LongArrayTag) tag;

                    out.writeInt(t.value.length);
                    for (long v : t.value) out.writeLong(v);
                }
                case Compound -> {
                    CompoundTag t = (CompoundTag) tag;

                    for (String key : t.keySet()) {
                        write(key, t.get(key));
                    }

                    writeCompoundEnd();
                }
                case List -> {
                    ListTag<?> t = (ListTag<?>) tag;

                    out.writeByte(t.id);
                    out.writeInt(t.size());

                    for (Tag ta : t) write(ta);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void write(Tag tag) { write(null, tag); }

    private void writeHeader(int id, String name) throws IOException {
        if (name != null) {
            out.writeByte(id);
            out.writeUTF(name);
        }
    }

    @Override
    public void close() throws IOException {
        writeCompoundEnd();

        stream.flush();
        stream.close();
    }
}
