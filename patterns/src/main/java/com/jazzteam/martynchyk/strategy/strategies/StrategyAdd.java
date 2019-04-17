package com.jazzteam.martynchyk.strategy.strategies;

import com.jazzteam.martynchyk.strategy.Strategy;

public class StrategyAdd implements Strategy {
    @Override
    public double execute(double a, double b) {
        return a + b;
    }
}
