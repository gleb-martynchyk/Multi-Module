package com.jazzteam.martynchyk;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.util.Arrays;

import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

public class TriangleTest {

    private Triangle triangle = new Triangle();

    @Test
    public void initializeCoordinates() {
        String input = "0 0 5 0 3 3";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        triangle.initializeCoordinates();
        String[] inputs = input.split("\\s");
        int size = inputs.length / 2;
        double[] xExpected = new double[size];
        double[] yExpected = new double[size];

        for (int i = 0; i < size; i++) {
            xExpected[i] = Double.parseDouble(inputs[i * 2]);
            yExpected[i] = Double.parseDouble(inputs[i * 2 + 1]);
        }

        assertTrue(Arrays.equals(triangle.getY(), yExpected));
        assertTrue(Arrays.equals(triangle.getX(), xExpected));

    }

//    @Test(dataProvider = "BadInputsString")
//    public void initializeCoordinatesNegative(String input) {
//        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
//        System.setIn(in);
//        triangle.initializeCoordinates();
//        System.setIn(System.in);
//    }

    @Test(dataProvider = "ValidInputsArea")
    public void area(double[] input) {
        triangle = new Triangle(Arrays.stream(input).limit(6).toArray());
        assertEquals(input[6], triangle.area(), 0.1);
    }

    @Test(dataProvider = "BadInputsArea")
    public void areaNegative(double[] badInputs) {
        triangle = new Triangle(badInputs);
        assertEquals(-1.0, triangle.area());
    }

    @Test(dataProvider = "ValidInputsPerimeter")
    public void perimeter(double[] input) {
        triangle = new Triangle(Arrays.stream(input).limit(6).toArray());
        assertEquals(input[6], triangle.perimeter(), 0.1);
    }

    @Test(dataProvider = "BadInputsArea")
    public void perimeterNegative(double[] input) {
        triangle = new Triangle(input);
        assertEquals(-1.0, triangle.perimeter());
    }

    @DataProvider(name = "BadInputsArea")
    public Object[][] getBadData() {
        return new Object[][]{
                {1, 1, 2},
                {0, 0, 2, 2, 5},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 2, 2, 5, 5}};
    }

    @DataProvider(name = "BadInputsString")
    public Object[] getBadStringData() {
        return new Object[]{
//                ""
                "hello"
//                "0123",
//                "500",
        };
    }

    @DataProvider(name = "ValidInputsArea")
    public Object[][] getValidDataArea() {
        return new Object[][]{
                {0, 0, 5, 0, 3, 3, 7.5},
                {1, 1, -2, 4, -2, -2, 9},
                {1, 1, -3, 4, -2, 10, 13.5},
                {1, 1, 0, 0, -2, 10, 6},
                {0.1, 0.9, 0, 0, 5, 1, 2.2}
        };
    }

    @DataProvider(name = "ValidInputsPerimeter")
    public Object[][] getValidDataPerimeter() {
        return new Object[][]{
                {0, 0, 5, 0, 3, 3, 12.85},
                {1, 1, -2, 4, -2, -2, 14.48},
                {1, 1, -3, 4, -2, 10, 20.57},
                {1, 1, 0, 0, -2, 10, 21.1},
                {0.1, 0.9, 0, 0, 5, 1, 10.9}
        };
    }
}