package com.jazzteam.martynchyk.chain_of_responsibility.handler.implementation;

import com.jazzteam.martynchyk.chain_of_responsibility.VRAM;
import com.jazzteam.martynchyk.chain_of_responsibility.handler.Handler;

public class Ddr3Handler extends Handler {
    @Override
    public double calculateBandwidth(VRAM ram) {
        if (canHandle(ram)) {
            return ram.getFrequencyMhz() * 2 * ram.getBusSize() / 8;
        } else {
            return super.getNext().calculateBandwidth(ram);
        }
    }

    @Override
    public boolean canHandle(VRAM ram) {
        return ram.getType() == VRAM.Type.DDR3;
    }
}
