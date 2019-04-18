package com.jazzteam.martynchyk.locator;

import com.jazzteam.martynchyk.locator.services.ItemService;
import com.jazzteam.martynchyk.locator.services.OrderService;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static junit.framework.Assert.*;

public class ServiceLocatorTest {

    private ServiceLocator locator;
    private ItemService itemService = new ItemService();
    private OrderService orderService = new OrderService();

    @BeforeMethod
    public void setUp() {
        locator = new ServiceLocator();
    }

    @Test
    public void testAdd() {
        locator.add(itemService.getClass().getName(), itemService);
        assertEquals(locator.get(itemService.getClass().getName()), itemService);
    }

    @Test
    public void testHas() {
        locator.add(itemService.getClass().getName(), itemService);
        assertTrue(locator.has(itemService.getClass().getName()));
    }

    @Test
    public void testHasNegative() {
        locator.add(itemService.getClass().getName(), itemService);
        assertFalse(locator.has(""));
    }

    @Test
    public void testHasNegative1() {
        locator.add(itemService.getClass().getName(), itemService);
        assertFalse(locator.has(null));
    }

    @Test
    public void testGet() {
        locator.add(itemService.getClass().getName(), itemService);
        assertEquals(locator.get(itemService.getClass().getName()), itemService);
    }

    @Test
    public void testGet1() {
        locator.add("", itemService);
        locator.add(itemService.getClass().getName(), itemService);
        assertEquals(locator.get(""), itemService);
    }
}