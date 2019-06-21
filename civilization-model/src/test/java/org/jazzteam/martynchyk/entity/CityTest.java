package org.jazzteam.martynchyk.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jazzteam.martynchyk.entity.resources.implementation.Food;
import org.jazzteam.martynchyk.entity.resources.implementation.Production;
import org.jazzteam.martynchyk.entity.units.Settler;
import org.jazzteam.martynchyk.usecases.BattleWithTheCityTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.*;

public class CityTest {

    private Civilization civilization;
    private City city;
    private Logger log = LogManager.getLogger(BattleWithTheCityTest.class);

    @BeforeMethod
    public void setUp() {
        civilization = new Civilization();
        city = new City(civilization);
    }

    @Test
    public void testResourcesAreEmptyInNewCity() {
        assertFalse(city.resourcesAreEmpty());
    }

    @Test
    public void testResourcesAreEmptyFood() {
        city.setFood(new Food(0));
        assertTrue(city.resourcesAreEmpty());
    }

    @Test
    public void testResourcesAreEmptyProduction() {
        city.setProduction(new Production(0));
        assertTrue(city.resourcesAreEmpty());
    }

    @Test
    public void testUpdateResourceAmount() {
        int amount = 5;
        assertTrue(city.updateResourceAmount(Food.class, amount));
    }

    @Test
    public void testUpdateResourceAmountWhenTakeAll() {
        int amount = -city.getFood().getAmount();
        assertTrue(city.updateResourceAmount(Food.class, amount));
    }

    @Test
    public void testUpdateResourceAmountWhenTakeMore() {
        int amount = -(city.getFood().getAmount() + 1);
        assertFalse(city.updateResourceAmount(Food.class, amount));
    }

    @Test
    public void testUpdateResourceAmountFieldIsChanging() {
        int amount = 5;
        int expected = city.getFood().getAmount();
        city.updateResourceAmount(Food.class, amount);
        assertEquals(expected + amount, city.getFood().getAmount());
    }

    @Test(dataProvider = "UnitsAndFood")
    public void testFeedUnits(int unitsCount, int foodAmount) {
        city.getResources().get(Food.class).setAmount(foodAmount);
        for (int i = 0; i < unitsCount; i++) {
            city.addUnit(new Settler());
        }

        assertTrue(city.feedUnits());
    }

    @DataProvider(name = "UnitsAndFood")
    public Object[][] getUnitsCountAndFoodAmount() {
        return new Object[][]{
                {3, 10},
                {3, 50},
                {3, 5},
        };
    }
}