package com.jazzteam.martynchyk;


import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class TriangleTest {

    private Triangle triangle;
    double[] input;

    @Test
    public void area() {
        input = new double[]{1, 1, 2, 5, 5, 3};
        triangle = new Triangle(input);
        assertEquals(7.0, triangle.area());
    }

    @Test
    public void areaNegative() {
        input = new double[]{1, 1, 2};
        triangle = new Triangle(input);
        assertEquals(-1.0, triangle.area());
    }

    @Test
    public void perimeter() {
        input = new double[]{1, 1, 2, 5, 5, 3};
        triangle = new Triangle(input);
        assertEquals(12.2, triangle.perimeter(), 0.1);
    }

    @Test
    public void perimeterNegative() {
        input = new double[]{1};
        triangle = new Triangle(input);
        assertEquals(-1, triangle.perimeter());
    }
}