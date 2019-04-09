package com.jazzteam.martynchyk;


import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class TriangleTest {

    private Triangle triangle;
    double[] input;

    @BeforeTest
    public void setUp() throws Exception {
    }

    @Test
    public void area() {
        input = new double[]{1, 1, 2, 5, 5, 3};
        triangle = new Triangle(input);
        assertEquals(7.0, triangle.area());
    }

    @Test
    public void perimetr() {
        input = new double[]{1, 1, 2, 5, 5, 3};
        triangle = new Triangle(input);
        assertEquals(12.2, triangle.perimetr(), 0.1);
    }
}