package com.jazzteam.martynchyk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Triangle {

    private final static int N = 3;
    private double x[];
    private double y[];

    public Triangle() {
    }

    public Triangle(double[] arr) {
        try {
            fromArrayToCoordinates(arr);
        } catch (Exception e) {
        }
    }


    public double area() {
        if (isCoordinatesValid()) {
            return Math.abs(0.5 * ((x[0] - x[2]) * (y[1] - y[2]) - (y[0] - y[2]) * (x[1] - x[2])));
        } else {
            return -1;
        }
    }

    public double perimeter() {
        return Math.sqrt((x[1] - x[0]) * (x[1] - x[0]) + (y[1] - y[0]) * (y[1] - y[0]))
                + Math.sqrt((x[2] - x[0]) * (x[2] - x[0]) + (y[2] - y[0]) * (y[2] - y[0]))
                + Math.sqrt((x[2] - x[1]) * (x[2] - x[1]) + (y[2] - y[1]) * (y[2] - y[1]));
    }


    public void initializeCoordinates() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] inputString;
        double[] inputDouble = new double[N * 2];
        try {
            while (true) {
                System.out.println("Enter x0 y0 x1 y1 x2 y2 ");
                inputString = bufferedReader.readLine().split("\\s");
                for (int i = 0; i < N * 2; i++) {
                    inputDouble[i] = Double.parseDouble(inputString[i]);
                }
                if (isInputValid(inputDouble)) {
                    fromArrayToCoordinates(inputDouble);
                    break;
                } else {
                    System.out.println("Not valid data, try again");
                }
            }
        } catch (IOException e) {
        } catch (Exception e) {
            System.out.println("Not valid data, try again");
        }

    }

    private void fromArrayToCoordinates(double[] coordinates) throws Exception {
        x = new double[N];
        y = new double[N];

        if (isInputValid(coordinates)) {
            for (int i = 0; i < N; i++) {
                x[i] = coordinates[i * 2];
                y[i] = coordinates[i * 2 + 1];
            }
        } else {
            throw new Exception("Too small values");
        }
    }

    private boolean isInputValid(double[] input) {

        if (input.length == 6) {
            for (double num : input) {
                if (num < 0) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    private boolean isCoordinatesValid() {
        if (x != null || y != null) {
            double[] nullArray = {0.0, 0.0, 0.0};
            if (x.length == 3 && y.length == 3 && !Arrays.equals(x, nullArray) || !Arrays.equals(y, nullArray)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }


    @Override
    public String toString() {
        return "Triangle{" +
                "x=" + Arrays.toString(x) +
                ", y=" + Arrays.toString(y) +
                '}';
    }

}
