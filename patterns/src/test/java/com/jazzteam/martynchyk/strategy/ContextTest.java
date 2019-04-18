package com.jazzteam.martynchyk.strategy;

import com.jazzteam.martynchyk.strategy.strategies.StrategyAdd;
import com.jazzteam.martynchyk.strategy.strategies.StrategyDivide;
import com.jazzteam.martynchyk.strategy.strategies.StrategyMultiply;
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
        assertEquals(context.execute(2, 3), 5);
    }

    @Test(dataProvider = "ValidInputsAdd", dataProviderClass = ContextDataProvider.class)
    public void testExecuteAdd(double[] input) {
        context.setStrategy(new StrategyAdd());
        assertEquals(context.execute(input[0], input[1]), input[2]);
    }

    @Test(dataProvider = "ValidInputsMultiply", dataProviderClass = ContextDataProvider.class)
    public void testExecuteMultiply(double[] input) {
        context.setStrategy(new StrategyMultiply());
        assertEquals(context.execute(input[0], input[1]), input[2]);
    }

    @Test(dataProvider = "ValidInputsDivide", dataProviderClass = ContextDataProvider.class)
    public void testExecuteDivide(double[] input) {
        context.setStrategy(new StrategyDivide());
        assertEquals(context.execute(input[0], input[1]), input[2]);
    }

    @Test
    public void testExecuteNegative() {
        context.setStrategy(new StrategyDivide());
        assertEquals(context.execute(33,0),Double.POSITIVE_INFINITY);
    }

}