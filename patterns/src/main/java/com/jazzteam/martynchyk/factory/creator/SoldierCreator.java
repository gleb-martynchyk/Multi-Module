package com.jazzteam.martynchyk.factory.creator;

import com.jazzteam.martynchyk.factory.units.Soldier;
import com.jazzteam.martynchyk.factory.units.Unit;

public class SoldierCreator extends UnitsCreator {
    @Override
    Unit createUnit() {
        return new Soldier();
    }
}
