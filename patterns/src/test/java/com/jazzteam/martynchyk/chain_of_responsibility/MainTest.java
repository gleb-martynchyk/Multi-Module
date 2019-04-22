package com.jazzteam.martynchyk.chain_of_responsibility;


import com.jazzteam.martynchyk.chain_of_responsibility.handler.Handler;
import com.jazzteam.martynchyk.chain_of_responsibility.handler.implementation.Ddr3Handler;
import com.jazzteam.martynchyk.chain_of_responsibility.handler.implementation.Gddr5xHandler;
import com.jazzteam.martynchyk.chain_of_responsibility.handler.implementation.Gddr6Handler;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class MainTest {
    private VRAM ddr3 = new VRAM(900, 512, VRAM.Type.DDR3, 256);
    private VRAM ddr5 = new VRAM(1750, 6, VRAM.Type.GDDR5, 192);
    private VRAM ddr5x = new VRAM(1400, 8192, VRAM.Type.GDDR5X, 256);
    private VRAM ddr6 = new VRAM(1400, 8192, VRAM.Type.GDDR5X, 256);

    public Handler h1 = new Ddr3Handler();
    private Handler h2 = new Gddr5xHandler();
    private Handler h3 = new Gddr6Handler();

    @BeforeMethod
    public void setUp() {
        h1.setNext(h2);
        h2.setNext(h3);
    }

    //TODO ПАРАМЕТРИЗИРОВАТЬ
    @Test
    public void testChainsFirstElement() {
        assertEquals(h1.calculateBandwidth(ddr3), 57600, 0.5);
    }

    @Test
    public void testTypeGddr5x() {
        assertEquals(h1.calculateBandwidth(ddr5x), 11200, 0.5);
    }

}
