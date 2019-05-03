package org.jazzteam.martynchyk.use_cases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jazzteam.martynchyk.entity.Combat;
import org.jazzteam.martynchyk.entity.units.military.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.testng.Assert.assertEquals;

public class BattleOfArmiesTest {
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

        assertEquals(executeBattle(armyFirst, armySecond), 0);
    }

    @Test
    public void executeBattleWarriorVsSpearmanTest() {
        BaseWarrior warrior = new Warrior();
        BaseWarrior spearman = new Spearman();

        armyFirst.add(warrior);
        armySecond.add(spearman);

        assertEquals(executeBattle(armyFirst, armySecond), -1);
    }

    @Test
    public void executeBattleHorseManVsSpearmanTest() {
        BaseWarrior horseMan = new HorseMan();
        BaseWarrior spearman = new Spearman();

        armyFirst.add(horseMan);
        armySecond.add(spearman);

        assertEquals(executeBattle(armyFirst, armySecond), -1);
    }

    @Test
    public void executeBattleHorseManVsArcherTest() {
        BaseWarrior horseMan = new HorseMan();
        BaseWarrior archer = new Archer();

        armyFirst.add(horseMan);
        armySecond.add(archer);

        assertEquals(executeBattle(armyFirst, armySecond), 1);
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

        log.info(executeBattle(armyFirst, armySecond));
    }


    public int executeBattle(List<Combat> firstArmy, List<Combat> secondArmy) {
        int step = 0;
        Combat first;
        Combat second;
        while (firstArmy.size() > 0 && secondArmy.size() > 0) {
            if (step % 2 == 0) {
                first = getNext(firstArmy);
                second = getNext(secondArmy);
                first.fight(second);

                if (first.isDead())
                    firstArmy.remove(first);
                if (second.isDead())
                    secondArmy.remove(second);

            } else {
                first = getNext(firstArmy);
                second = getNext(secondArmy);
                second.fight(first);

                if (first.isDead())
                    firstArmy.remove(first);
                if (second.isDead())
                    secondArmy.remove(second);
            }
            step++;
        }
        log.info("Steps: " + step);
        if (firstArmy.size() <= 0 && secondArmy.size() <= 0) {
            return 0;
        } else if (secondArmy.size() <= 0) {
            log.info("Winner: " + firstArmy);
            return 1;
        } else {
            log.info("Winner: " + secondArmy);
            return -1;
        }
    }

    public Combat getNext(List<Combat> army) {
        int randomIndex = new Random().nextInt(army.size());
        return army.get(randomIndex);
    }
}
