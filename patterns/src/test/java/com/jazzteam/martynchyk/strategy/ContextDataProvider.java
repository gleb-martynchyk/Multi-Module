package com.jazzteam.martynchyk.strategy;

import org.testng.annotations.DataProvider;

public class ContextDataProvider {
    @DataProvider(name = "ValidInputsAdd")
    public Object[] getDataForAdding() {
        return new Object[][]{
                {54, 27, 81},
                {80, 90, 170},
                {65, 34, 99},
                {130, 98, 228}
        };
    }

    @DataProvider(name = "ValidInputsMultiply")
    public Object[] getDataForMultiply() {
        return new Object[][]{
                {0, 465465, 0},
                {54, 27, 1458},
                {-80, 90, -7200},
                {65, 34, 2210},
                {-130, 98, -12740}
        };
    }

    @DataProvider(name = "ValidInputsDivide")
    public Object[] getDataForDivide() {
        return new Object[][]{
                {0, 465465, 0},
                {54, 27, 2},
                {-80, 40, -2},
                {65, -5, -13},
                {1000, 0.0001, 10000000}
        };
    }
}
