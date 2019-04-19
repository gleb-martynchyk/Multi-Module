package com.jazzteam.martynchyk.chain_of_responsibility;

public class VRAM {
    private float frequencyMhz;
    private int size;
    private Type type;
    private int busSize;

    public enum Type {
        DDR3,
        GDDR5,
        GDDR5X,
        GDDR6,
    }

    public VRAM(float frequencyMhz, int size, Type type, int busSize) {
        this.frequencyMhz = frequencyMhz;
        this.size = size;
        this.type = type;
        this.busSize = busSize;
    }

    public int getBusSize() {
        return busSize;
    }

    public float getFrequencyMhz() {
        return frequencyMhz;
    }

    public int getSize() {
        return size;
    }

    public Type getType() {
        return type;
    }
}
