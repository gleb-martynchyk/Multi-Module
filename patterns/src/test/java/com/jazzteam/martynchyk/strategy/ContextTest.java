package com.jazzteam.martynchyk.strategy;

import com.jazzteam.martynchyk.strategy.strategies.StrategyAdd;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ContextTest {

    private Context context = new Context();

    @Test
    public void testSetStrategy() {
        context.setStrategy(new StrategyAdd());
        assertEquals(context.getStrategy().getClass(), StrategyAdd.class);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testSetStrategyNegative() {
        context.setStrategy(null);
        context.execute(2,2);
    }

    @Test
    public void testExecute() {
    }
}