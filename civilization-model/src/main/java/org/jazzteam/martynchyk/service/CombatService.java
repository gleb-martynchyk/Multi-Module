package org.jazzteam.martynchyk.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jazzteam.martynchyk.entity.Combat;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class CombatService {
    private Logger log = LogManager.getLogger(CombatService.class);

    public int executeBattle(List<Combat> firstArmy, List<Combat> secondArmy) {
        int step = 0;
        Combat first;
        Combat second;
        while (firstArmy.size() > 0 && secondArmy.size() > 0) {
            if (step % 2 == 0) {
                first = getNext(firstArmy);
                second = getNext(secondArmy);
                first.fight(second);

                if (first.isDead()) {
                    firstArmy.indexOf(first);
                    firstArmy.remove(first);
                }
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
