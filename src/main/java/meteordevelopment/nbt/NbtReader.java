package meteordevelopment.nbt;

import meteordevelopment.nbt.tags.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

public class NbtReader {
    public static NamedTag read(InputStream stream, boolean compressed) throws NbtFormatException {
        try {
            if (compressed) stream = new GZIPInputStream(stream);
            DataInput in = new DataInputStream(stream);

            if (in.readByte() != TagId.Compound.ordinal()) throw new NbtFormatException("File doesn't start with compound tag.");

            String name = in.readUTF();
            CompoundTag tag = readCompound(in);

            stream.close();
            return new NamedTag(name, tag);
        } catch (IOException e) {
            throw new NbtFormatException(e instanceof EOFException ? "Unexpected end of NBT file." : e.getMessage());
        }
    }

    private static CompoundTag readCompound(DataInput in) throws IOException, NbtFormatException {
        CompoundTag tag = new CompoundTag();

        while (true) {
            byte idByte = in.readByte();
            if (!TagId.isValid(idByte)) throw new NbtFormatException("Unknown tag with id " + idByte + ".");

            TagId id = TagId.valueOf(idByte);
            if (id == TagId.End) break;

            String name = in.readUTF();
            tag.put(name, readTag(in, id));
        }

        return tag;
    }

    private static Tag readTag(DataInput in, TagId id) throws IOException, NbtFormatException {
        return switch (id) {
            case Byte -> new ByteTag(in.readByte());
            case Short -> new ShortTag(in.readShort());
            case Int -> new IntTag(in.readInt());
            case Long -> new LongTag(in.readLong());
            case Float -> new FloatTag(in.readFloat());
            case Double -> new DoubleTag(in.readDouble());
            case String -> new StringTag(in.readUTF());
            case ByteArray -> {
                byte[] bytes = new byte[in.readInt()];
                in.readFully(bytes);
                yield new ByteArrayTag(bytes);
            }
            case IntArray -> {
                int[] ints = new int[in.readInt()];
                for (int i = 0; i < ints.length; i++) ints[i] = in.readInt();
                yield new IntArrayTag(ints);
            }
            case LongArray -> {
                long[] longs = new long[in.readInt()];
                for (int i = 0; i < longs.length; i++) longs[i] = in.readInt();
                yield new LongArrayTag(longs);
            }
            case Compound -> readCompound(in);
            case List -> {
                byte listIdByte = in.readByte();
                if (!TagId.isValid(listIdByte)) throw new NbtFormatException("Unknown tag with id " + listIdByte + ".");

                TagId listId = TagId.valueOf(listIdByte);
                int size = in.readInt();
                List<Tag> list = new ArrayList<>(size);

                for (int i = 0; i < size; i++) {
                    list.add(readTag(in, listId));
                }

                yield new ListTag<>(list, listId);
            }
            default -> throw new NbtFormatException("NbtReader.readTag() not implemented for " + id + ".");
        };
    }
}
