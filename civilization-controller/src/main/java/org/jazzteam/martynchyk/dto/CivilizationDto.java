package org.jazzteam.martynchyk.dto;

import lombok.Getter;
import lombok.Setter;
import org.jazzteam.martynchyk.entity.Civilization;
import org.jazzteam.martynchyk.entity.enums.ReligionType;
import org.jazzteam.martynchyk.entity.tree.Tree;
import org.jazzteam.martynchyk.entity.units.Unit;
import org.jazzteam.martynchyk.entity.units.military.BaseWarrior;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class CivilizationDto {
    private long id;
    private String name;
    private int faith;
    private int gold;
    private int science;
    private CityDto capital;
    private List<CityDto> cities;
    private ReligionType dominantReligion;
    private Tree scienceTree;

    public CivilizationDto() {
    }

    public CivilizationDto(Civilization civilization) {
        this.id = civilization.getId();
        this.name = civilization.getName();
        this.faith = civilization.getFaith();
        this.gold = civilization.getGold();
        this.science = civilization.getScience();
        this.capital = new CityDto(civilization.getCapital());
        this.dominantReligion = civilization.getDominantReligion();
        this.scienceTree = civilization.getScienceTree();
        this.cities = civilization.getCities().stream()
                .map(CityDto::new)
                .collect(Collectors.toList());
    }

    public Civilization convertToEntity() {
        Civilization civilization = new Civilization();
        civilization.setId(this.id);
        civilization.setName(this.name);
        civilization.setFaith(this.gold);
        civilization.setScience(this.science);
        civilization.setCapital(this.capital.convertToEntity());
        civilization.setDominantReligion(this.dominantReligion);
        civilization.setScienceTree(this.getScienceTree());
        if (this.cities != null) {
            civilization.setCities(getCities().stream()
                    .map(CityDto::convertToEntity)
                    .collect(Collectors.toList()));
        }
        return civilization;
    }

    public List<Unit> getUnits() {
        List<Unit> units = new ArrayList<>();
        for (CityDto city : cities) {
            units.addAll(city.getUnits());
        }
        return units;
    }

    public int getPopulation() {
        return getUnits().size();
    }

    public long getWarriorUnitsSize() {
        return getUnits().stream()
                .filter(unit -> unit instanceof BaseWarrior)
                .count();
    }


}
