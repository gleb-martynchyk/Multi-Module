package com.jazzteam.martynchyk.chain_of_responsibility.handler.implementation;


import com.jazzteam.martynchyk.chain_of_responsibility.VRAM;
import com.jazzteam.martynchyk.chain_of_responsibility.handler.Handler;

public class Gddr5xHandler extends Handler {
    @Override
    public double calculateBandwidth(VRAM ram) {
        return ram.getFrequencyMhz() * 64 / 8;
    }

    @Override
    public boolean canHandle(VRAM ram) {
        return ram.getType() == VRAM.Type.GDDR5X || ram.getType() == VRAM.Type.GDDR5;
    }
}
