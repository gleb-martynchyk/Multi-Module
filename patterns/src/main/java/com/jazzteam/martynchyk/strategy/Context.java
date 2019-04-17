package com.jazzteam.martynchyk.strategy;

public class Context {
    private Strategy strategy;

    public double execute(double a, double b) {
        return strategy.execute(a, b);
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public Strategy getStrategy() {
        return strategy;
    }
}
