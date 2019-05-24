package org.jazzteam.martynchyk.use_cases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jazzteam.martynchyk.entity.City;
import org.jazzteam.martynchyk.entity.Civilization;
import org.jazzteam.martynchyk.entity.Combat;
import org.jazzteam.martynchyk.entity.building.DefensiveWall;
import org.jazzteam.martynchyk.entity.units.military.Archer;
import org.jazzteam.martynchyk.entity.units.military.Warrior;
import org.jazzteam.martynchyk.services.CombatService;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SiegeOfTheCityTest {

    private Civilization civilization = new Civilization();
    private CombatService combatService = new CombatService();
    private Logger log = LogManager.getLogger(SiegeOfTheCityTest.class);
    private List<Combat> armyFirst = new ArrayList<>();
    private List<Combat> armySecond = new ArrayList<>();

    @BeforeMethod
    public void setUp() {
        armyFirst.clear();
        armySecond.clear();
    }


    @Test
    public void executeBattleWarriorsVsCity() {
        Combat city = new City(civilization);

        armyFirst.add(new Warrior());
        armyFirst.add(new Warrior());
        armyFirst.add(new Warrior());
        armyFirst.add(new Warrior());
        armyFirst.add(new Warrior());
        armyFirst.add(new Warrior());
        armyFirst.add(new Warrior());
        armyFirst.add(new Warrior());
        armyFirst.add(new Warrior());

        armySecond.add(city);

        assertEquals(combatService.executeBattle(armyFirst, armySecond), 1);
    }

    @Test
    public void executeBattleArchersVsCity() {
        Combat city = new City(civilization);

        armyFirst.add(new Archer());
        armyFirst.add(new Archer());
        armyFirst.add(new Archer());

        armySecond.add(city);

        assertEquals(combatService.executeBattle(armyFirst, armySecond), -1);
    }

    @Test
    public void executeBattleArcherAndWarriorsVsCity() {
        Combat city = new City(civilization);

        armyFirst.add(new Archer());
        armyFirst.add(new Archer());
        armyFirst.add(new Archer());
        armyFirst.add(new Archer());
        armyFirst.add(new Archer());
        armyFirst.add(new Archer());
        armyFirst.add(new Archer());
        armyFirst.add(new Archer());
        armyFirst.add(new Warrior());
        armyFirst.add(new Warrior());
        armyFirst.add(new Warrior());
        armyFirst.add(new Warrior());
        armyFirst.add(new Warrior());
        armyFirst.add(new Warrior());
        armyFirst.add(new Warrior());

        armySecond.add(city);

        assertEquals(combatService.executeBattle(armyFirst, armySecond), 1);
    }

    @Test
    public void executeBattleArchersVsDefensiveCity() {
        List<Combat> armySecond1 = new ArrayList<>();
        List<Combat> armyFirst1 = new ArrayList<>();

        City city = new City(civilization);
        City citySecond = new City(civilization);

        armyFirst.add(new Archer());
        armyFirst.add(new Archer());
        armyFirst.add(new Archer());

        armyFirst1.add(new Archer());
        armyFirst1.add(new Archer());
        armyFirst1.add(new Archer());

        armySecond.add(city);
        armySecond1.add(citySecond);
        citySecond.addBuilding(new DefensiveWall());

        combatService.executeBattle(armyFirst1, armySecond1);
        combatService.executeBattle(armyFirst, armySecond);

        double expected = city.getHealthPoint();
        double actual = citySecond.getHealthPoint();
        assertTrue(actual > expected);
    }
}
