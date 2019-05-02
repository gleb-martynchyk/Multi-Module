package org.jazzteam.martynchyk.use_cases;

import org.jazzteam.martynchyk.entity.Combat;
import org.jazzteam.martynchyk.entity.units.military.Warrior;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.testng.Assert.assertEquals;

public class BattleOfArmiesTest {
    //City city = new City();
    Warrior unitA = new Warrior();
    Warrior unitB = new Warrior();
    Warrior unitC = new Warrior();

    Warrior unitAA = new Warrior();
    Warrior unitBA = new Warrior();
    Warrior unitCA = new Warrior();
    Warrior unitDA = new Warrior();

    List<Combat> armyA = new ArrayList<>();
    List<Combat> armyB = new ArrayList<>();

    @BeforeMethod
    public void setUp() {
        armyA.add(unitA);
        armyA.add(unitB);
        armyA.add(unitC);

        armyB.add(unitAA);
        armyB.add(unitBA);
        armyB.add(unitCA);
        armyB.add(unitDA);

    }

    @Test
    public void executeBattleTest() {
        assertEquals(executeBattle(armyA, armyB), 0);
    }


    public int executeBattle(List<Combat> firstArmy, List<Combat> secondArmy) {
        int step = 0;
        Combat first;
        Combat second;
        while (firstArmy.size() > 0 && secondArmy.size() > 0) {
            if (step % 2 == 0) {
                first = getNext(firstArmy);
                second = getNext(secondArmy);
                first.attack(second);

                if (first.isDead())
                    firstArmy.remove(first);
                if (second.isDead())
                    secondArmy.remove(first);

            } else {
                first = getNext(firstArmy);
                second = getNext(secondArmy);
                second.attack(first);

                if (first.isDead())
                    firstArmy.remove(first);
                if (second.isDead())
                    secondArmy.remove(first);
            }
            step++;
        }

        if (firstArmy.size() <= 0 && secondArmy.size() <= 0) {
            return 0;
        } else if (secondArmy.size() <= 0) {
            return 1;
        } else {
            return -1;
        }
    }

    public Combat getNext(List<Combat> army) {
        int randomIndex = new Random().nextInt(army.size());
        return army.get(randomIndex);
    }
}
