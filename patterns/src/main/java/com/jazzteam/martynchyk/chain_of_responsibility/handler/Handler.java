package com.jazzteam.martynchyk.chain_of_responsibility.handler;

import com.jazzteam.martynchyk.chain_of_responsibility.VRAM;

public abstract class Handler {
    private Handler next;

    public Handler getNext() {
        return next;
    }

    public void setNext(Handler next) {
        this.next = next;
    }

    public abstract double calculateBandwidth(VRAM ram);

    public abstract boolean canHandle(VRAM ram);
}
