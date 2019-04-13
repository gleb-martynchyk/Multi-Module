package com.jazzteam.martynchyk;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class TriangleTest {

    private Triangle triangle;
    private double[] input;

    @Test
    public void area() {
        input = new double[]{1, 1, 2, 5, 5, 3};
        triangle = new Triangle(input);
        assertEquals(7.0, triangle.area());
    }

    @Test(dataProvider = "BadInputs")
    public void areaNegative(double[] badInputs) {
        triangle = new Triangle(badInputs);
        assertEquals(-1.0, triangle.area());
    }

    @Test
    public void perimeter() {
        input = new double[]{1, 1, 2, 5, 5, 3};
        triangle = new Triangle(input);
        assertEquals(12.2, triangle.perimeter(), 0.1);
    }

    @Test(dataProvider = "BadInputs")
    public void perimeterNegative(double[] badInputs) {
        triangle = new Triangle(badInputs);
        assertEquals(-1.0, triangle.perimeter());
    }

    @DataProvider(name="BadInputs")
    public Object[][] getData(){
        return new Object[][]{
                {1, 1, 2},
                {0, 0, 2, 2, 5},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 2, 2, 5, 5}};
    }
}