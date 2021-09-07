package meteordevelopment.nbt.tags;

public abstract class Tag implements Cloneable {
    public abstract int getId();

    @Override
    public abstract Tag clone();
}
