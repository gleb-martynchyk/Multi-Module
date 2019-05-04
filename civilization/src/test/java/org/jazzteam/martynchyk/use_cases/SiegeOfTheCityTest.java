package org.jazzteam.martynchyk.use_cases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jazzteam.martynchyk.entity.City;
import org.jazzteam.martynchyk.entity.Combat;
import org.jazzteam.martynchyk.entity.units.military.Archer;
import org.jazzteam.martynchyk.entity.units.military.Warrior;
import org.jazzteam.martynchyk.services.CombatService;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class SiegeOfTheCityTest {

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
        Combat city = new City();

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

        assertEquals(combatService.executeBattle(armyFirst, armySecond), -1);
    }

    @Test
    public void executeArcherWarriorsVsCity() {
        Combat city = new City();

        armyFirst.add(new Archer());
        armyFirst.add(new Archer());
        armyFirst.add(new Archer());

        armySecond.add(city);

        assertEquals(combatService.executeBattle(armyFirst, armySecond), -1);
    }

}
