package com.jazzteam.martynchyk.facade.library;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class KeyTest {

    @Test
    public void testGenerateKey() {
        Key key = new Key();
        key.generateKey();
        assertNotEquals(key.getKey(),0);
    }
}