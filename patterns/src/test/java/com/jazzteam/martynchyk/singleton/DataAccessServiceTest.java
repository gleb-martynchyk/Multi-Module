package com.jazzteam.martynchyk.singleton;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class DataAccessServiceTest {

    DataAccessService singletonExcpected;

    @BeforeMethod
    public void setUp() {
        singletonExcpected = DataAccessService.getInstance();
    }
    //TODO добавить негативные тесты
    @Test
    public void testGetInstance() {
        assertNotNull(DataAccessService.getInstance());
    }

    @Test
    public void testGetInstance1() {
        DataAccessService actual = DataAccessService.getInstance();
        assertEquals(actual, singletonExcpected);
    }
}