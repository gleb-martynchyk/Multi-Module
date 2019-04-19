package com.jazzteam.martynchyk.chain_of_responsibility.handler.implementation;

import com.jazzteam.martynchyk.chain_of_responsibility.VRAM;
import com.jazzteam.martynchyk.chain_of_responsibility.handler.Handler;

public class Gddr6Handler extends Handler {
    @Override
    public double calculateBandwidth(VRAM ram) {
        return ram.getFrequencyMhz() * ram.getBusSize();
    }

    @Override
    public boolean canHandle(VRAM ram) {
        return ram.getType() == VRAM.Type.GDDR6;
    }
}
