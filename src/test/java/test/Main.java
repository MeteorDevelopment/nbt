package test;

import meteordevelopment.nbt.NBT;
import meteordevelopment.nbt.NamedTag;
import meteordevelopment.nbt.NbtFormatException;
import meteordevelopment.nbt.NbtWriter;
import meteordevelopment.nbt.tags.CompoundTag;
import meteordevelopment.nbt.tags.DoubleTag;
import meteordevelopment.nbt.tags.StringTag;
import meteordevelopment.nbt.tags.TagId;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        File file = new File("test.nbt");

        // Write
        // NBT.write() also exists for simple writing of compound tags
        try (NbtWriter nbt = new NbtWriter("hi", file)) {
            // First way of writing NBT is using the NbtWriter methods directly, this is faster because it does not need to allocate new class instances to store the NBT in memory
            nbt.writeInt("a", 159);
            nbt.writeString("b", "POG");

            nbt.writeList("c", TagId.Double, 3);
            nbt.writeDouble(5);
            nbt.writeDouble(0.65);
            nbt.writeDouble(Math.PI);

            nbt.writeIntArray("d", new int[] { 5, 6, 8, 469, 84, 87, 15, 56, 1, 5, 2, 13, 86, 135, 153, 156 });


            // Second way of writing NBT is simply by creating an in memory representation of the NBT structure with the various Tag classes and then writing them using NbtWriter.write() or NBT.write()
            CompoundTag tag = new CompoundTag();
            tag.putBool("idk",true);
            tag.putList("list", List.of(new StringTag("hi"), new StringTag("no")), TagId.String);

            nbt.write("e", tag);
        } catch (IOException e) {
            e.printStackTrace();
        }


        // Read
        try {
            NamedTag namedTag = NBT.read(file);
            CompoundTag tag = namedTag.tag;

            System.out.println("Name: " + namedTag.name);

            System.out.println("b: " + tag.getString("b"));
            System.out.println("PI: " + tag.getList("c", DoubleTag.class).get(2));
            System.out.println("list 1: " + tag.getCompound("e").getList("list", StringTag.class).get(1));
        } catch (NbtFormatException e) {
            System.out.println("Failed to read NBT file: " + e.getMessage());
        }
    }
}
