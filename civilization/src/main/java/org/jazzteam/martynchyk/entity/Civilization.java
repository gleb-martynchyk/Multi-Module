package org.jazzteam.martynchyk.entity;

import org.jazzteam.martynchyk.entity.enums.ReligionType;
import org.jazzteam.martynchyk.entity.tree.Tree;

import java.util.List;

public class Civilization {
    private List<City> cities;
    private ReligionType dominantReligios;
    private Tree scinceTree;
    private int scinceLevel;
}
