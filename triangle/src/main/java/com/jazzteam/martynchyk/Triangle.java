package com.jazzteam.martynchyk;

import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

@Getter
public class Triangle {

    private static Logger log = LogManager.getLogger(Triangle.class);
    private final static int N = 3;
    private double[] x;
    private double[] y;

    public Triangle() {
    }

    public Triangle(double[] arr) {
        try {
            fromArrayToCoordinates(arr);
        } catch (Exception e) {}
    }


    public double area() {
        if (isCoordinatesValid()) {
            return Math.abs(0.5 * ((x[0] - x[2]) * (y[1] - y[2]) - (y[0] - y[2]) * (x[1] - x[2])));
        } else {
            return -1;
        }
    }

    public double perimeter() {
        if (isCoordinatesValid()) {
            return Math.sqrt((x[1] - x[0]) * (x[1] - x[0]) + (y[1] - y[0]) * (y[1] - y[0]))
                    + Math.sqrt((x[2] - x[0]) * (x[2] - x[0]) + (y[2] - y[0]) * (y[2] - y[0]))
                    + Math.sqrt((x[2] - x[1]) * (x[2] - x[1]) + (y[2] - y[1]) * (y[2] - y[1]));
        } else {
            return -1;
        }
    }


    public void initializeCoordinates() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] inputString;
        double[] inputDouble = new double[N * 2];

        while (true) {
            log.info("Enter x0 y0 x1 y1 x2 y2 ");
            try {
                inputString = bufferedReader.readLine().split("\\s");
                for (int i = 0; i < N * 2; i++) {
                    inputDouble[i] = Double.parseDouble(inputString[i]);
                }
            } catch (IOException | ArrayIndexOutOfBoundsException | NumberFormatException | NullPointerException e) {
                log.info("Not valid data, try again");
                continue;
            }
            if (isInputValid(inputDouble)) {
                fromArrayToCoordinates(inputDouble);
                break;
            } else {
                log.info("Not valid data, try again");
            }
        }
    }

    private void fromArrayToCoordinates(double[] coordinates) {
        x = new double[N];
        y = new double[N];
        if (isInputValid(coordinates)) {
            for (int i = 0; i < N; i++) {
                x[i] = coordinates[i * 2];
                y[i] = coordinates[i * 2 + 1];
            }
        }
    }

    private boolean isInputValid(double[] input) {
        return input.length == 6;
    }

    private boolean isCoordinatesValid() {
        if (x != null && y != null) {
            double[] nullArray = {0.0, 0.0, 0.0};

            return x.length == 3 && y.length == 3
                    && (!Arrays.equals(x, nullArray) || !Arrays.equals(y, nullArray))
                    && x[0] != y[0] || x[1] != y[1] || x[2] != y[2];

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
