package org.jazzteam.martynchyk.entity;

import java.util.List;

public class TimeManager implements Time {
    private List<Civilization> civilizations;
    private long ticks;

    public TimeManager(List<Civilization> civilizations) {
        this.civilizations = civilizations;
        this.ticks = 0;
    }

    @Override
    public void doTick() {
        civilizations.stream()
                .forEach(Civilization::doTick);
        ticks++;
    }

    public void doTicks(int ticks) {
        for (int i = 0; i < ticks; i++) {
            doTick();
        }
    }
}
