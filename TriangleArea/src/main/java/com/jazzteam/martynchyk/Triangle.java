package com.jazzteam.martynchyk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Triangle {

    private double x1, x2, x3, y1, y2, y3;

    public Triangle() {
    }

    public Triangle(double x1, double x2, double x3, double y1, double y2, double y3) {
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        this.y1 = y1;
        this.y2 = y2;
        this.y3 = y3;
    }

    public double CalculateArea() {
        return 0;
    }

    public void InitializeCoordinates() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] strArray;
        try {
            for (int i = 0; i < 2; i++) {
                System.out.println("Enter x" + i + " y" + i);
                strArray = bufferedReader.readLine().split("\\s");
                if (isInputValid(strArray)) {
                    System.out.println(strArray[0]);
                    System.out.println(strArray[1]);
                }
            }
        } catch (IOException e) {
        }

    }

    private boolean isInputValid(String[] input) {
        if (input.length == 2) {
            if (Double.parseDouble(input[0]) >= 0 && Double.parseDouble(input[1]) >= 0) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
