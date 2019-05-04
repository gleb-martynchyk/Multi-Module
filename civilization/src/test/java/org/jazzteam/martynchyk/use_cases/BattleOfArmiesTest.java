package org.jazzteam.martynchyk.use_cases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jazzteam.martynchyk.entity.City;
import org.jazzteam.martynchyk.entity.Combat;
import org.jazzteam.martynchyk.entity.units.military.*;
import org.jazzteam.martynchyk.services.CombatService;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

//@WebAppConfiguration
//@ContextConfiguration(classes = {CombatServiceConfig.class})
public class BattleOfArmiesTest {

    private CombatService combatService = new CombatService();
    private Logger log = LogManager.getLogger(BattleOfArmiesTest.class);
    private List<Combat> armyFirst = new ArrayList<>();
    private List<Combat> armySecond = new ArrayList<>();

    @BeforeMethod
    public void setUp() {
        armyFirst.clear();
        armySecond.clear();
    }

    @Test
    public void executeBattleOneVsOneTest() {
        BaseWarrior warrior1 = new Warrior();
        BaseWarrior warrior2 = new Warrior();

        armyFirst.add(warrior1);
        armySecond.add(warrior2);

        assertEquals(combatService.executeBattle(armyFirst, armySecond), 0);
    }

    @Test
    public void executeBattleWarriorVsSpearmanTest() {
        BaseWarrior warrior = new Warrior();
        BaseWarrior spearman = new Spearman();

        armyFirst.add(warrior);
        armySecond.add(spearman);

        assertEquals(combatService.executeBattle(armyFirst, armySecond), -1);
    }

    @Test
    public void executeBattleHorseManVsSpearmanTest() {
        BaseWarrior horseMan = new HorseMan();
        BaseWarrior spearman = new Spearman();

        armyFirst.add(horseMan);
        armySecond.add(spearman);

        assertEquals(combatService.executeBattle(armyFirst, armySecond), -1);
    }

    @Test
    public void executeBattleHorseManVsArcherTest() {
        BaseWarrior horseMan = new HorseMan();
        BaseWarrior archer = new Archer();

        armyFirst.add(horseMan);
        armySecond.add(archer);

        assertEquals(combatService.executeBattle(armyFirst, armySecond), 1);
    }


    @Test
    public void executeBattleArchersAndWarriors() {
        BaseWarrior warrior1 = new Warrior();
        BaseWarrior warrior2 = new Warrior();
        BaseWarrior warrior3 = new Warrior();

        BaseWarrior archer1 = new Archer();
        BaseWarrior archer2 = new Archer();

        armyFirst.add(warrior1);
        armyFirst.add(warrior2);
        armyFirst.add(warrior3);

        armySecond.add(archer1);
        armySecond.add(archer2);

        assertEquals(combatService.executeBattle(armyFirst, armySecond), -1);
    }

    @Test
    public void executeBattleArchersVsCity() {
        Combat city = new City();

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
    public void executeBattleTest() {
        BaseWarrior warrior1 = new Warrior();
        BaseWarrior warrior2 = new Warrior();
        BaseWarrior warrior3 = new Warrior();

        BaseWarrior warrior4 = new Warrior();
        BaseWarrior warrior5 = new Warrior();
        BaseWarrior warrior6 = new Warrior();

        armyFirst.add(warrior1);
        armyFirst.add(warrior2);
        armyFirst.add(warrior3);

        armySecond.add(warrior4);
        armySecond.add(warrior5);
        armySecond.add(warrior6);

        log.info(combatService.executeBattle(armyFirst, armySecond));
    }

}
