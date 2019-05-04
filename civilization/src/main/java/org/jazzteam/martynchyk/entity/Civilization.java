package org.jazzteam.martynchyk.entity;

import org.jazzteam.martynchyk.entity.enums.ReligionType;
import org.jazzteam.martynchyk.entity.tree.Tree;

import java.util.List;

public class Civilization {
    private int faith;
    private int gold;
    private int scince;
    private List<City> cities;
    private ReligionType dominantReligios;
    private Tree scinceTree;
}
