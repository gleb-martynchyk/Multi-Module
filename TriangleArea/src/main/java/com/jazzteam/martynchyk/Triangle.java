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
        fromArrayToCoordinates(arr);
    }


    public double area() {
        return Math.abs(0.5 * ((x[0] - x[2]) * (y[1] - y[2]) - (y[0] - y[2]) * (x[1] - x[2])));
    }

    public double perimetr() {
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

    @Override
    public String toString() {
        return "Triangle{" +
                "x=" + Arrays.toString(x) +
                ", y=" + Arrays.toString(y) +
                '}';
    }

    private void fromArrayToCoordinates(double[] coordinates) {
        x = new double[N];
        y = new double[N];

        if (isInputValid(coordinates)) {
            for (int i = 0; i < N; i++) {
                x[i] = coordinates[i * 2];
                y[i] = coordinates[i * 2 + 1];
            }
        } else {
            System.out.println("Not valid data, try again");
        }
    }

}
