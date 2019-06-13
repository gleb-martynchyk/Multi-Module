package org.jazzteam.martynchyk.dto;

import lombok.Getter;
import lombok.Setter;
import org.jazzteam.martynchyk.entity.Civilization;
import org.jazzteam.martynchyk.entity.enums.ReligionType;
import org.jazzteam.martynchyk.entity.tree.Tree;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class CivilizationDto {
    private String name;
    private int faith;
    private int gold;
    private int science;
    private CityDto capital;
    private List<CityDto> cities;
    private ReligionType dominantReligios;
    private Tree scienceTree;

    public CivilizationDto() {
    }

    public CivilizationDto(Civilization civilization) {
        this.name = civilization.getName();
        this.faith = civilization.getFaith();
        this.gold = civilization.getGold();
        this.science = civilization.getScience();
        this.capital = new CityDto(civilization.getCapital());
        this.dominantReligios = civilization.getDominantReligios();
        this.scienceTree = civilization.getScienceTree();
        this.cities = civilization.getCities().stream()
                .map(CityDto::new)
                .collect(Collectors.toList());
    }

    public Civilization convertToEntity() {
        Civilization civilization = new Civilization();
        civilization.setName(getName());
        civilization.setFaith(getGold());
        civilization.setScience(getScience());
        civilization.setCapital(getCapital().convertToEntity());
        civilization.setDominantReligios(getDominantReligios());
        civilization.setScienceTree(getScienceTree());
        if (getCities() != null) {
            civilization.setCities(getCities().stream()
                    .map(CityDto::convertToEntity)
                    .collect(Collectors.toList()));
        }
        return civilization;
    }
}
