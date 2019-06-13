package org.jazzteam.martynchyk.dto;

import lombok.Getter;
import lombok.Setter;
import org.jazzteam.martynchyk.entity.City;
import org.jazzteam.martynchyk.entity.Civilization;
import org.jazzteam.martynchyk.entity.Combat;
import org.jazzteam.martynchyk.entity.building.Improving;
import org.jazzteam.martynchyk.entity.building.Producing;
import org.jazzteam.martynchyk.entity.enums.ReligionType;
import org.jazzteam.martynchyk.entity.resources.Resource;
import org.jazzteam.martynchyk.entity.trade.TradeRoute;
import org.jazzteam.martynchyk.entity.units.Unit;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
public class CityDto {
    private String name;
    private Civilization civilization;
    private double defence;
    private double healthPoint;
    private int strength;
    private int level;
    private int tradingCapacity;
    private Map<Class, Resource> resources;
    private boolean isSieged;
    private ReligionType dominantReligion;
    private List<Unit> units;
    private List<Combat> besiegeUnits;
    private List<Improving> improvingBuildings;
    private List<Producing> producingBuildings;
    private Set<TradeRoute> tradeRoutes;

    public CityDto() {
    }

    public CityDto(City city) {
        if (city != null) {
            this.civilization = city.getCivilization();
            setName(city.getName());
            setDefence(city.getDefence());
            setHealthPoint(city.getHealthPoint());
            setStrength(city.getStrength());
            setLevel(city.getLevel());
            setTradingCapacity(city.getTradingCapacity());
            setResources(city.getResources());
            setSieged(city.isSieged());
            setDominantReligion(city.getDominantReligion());
            setUnits(city.getUnits());
            setBesiegeUnits(city.getBesiegeUnits());
            setImprovingBuildings(city.getImprovingBuildings());
            setProducingBuildings(city.getProducingBuildings());
            setTradeRoutes(city.getTradeRoutes());
        }
    }

    public City convertToEntity() {
        City city = new City(this.getCivilization());
        city.setName(getName());
        city.setDefence(getDefence());
        city.setHealthPoint(getHealthPoint());
        city.setStrength(getStrength());
        city.setLevel(getLevel());
        city.setTradingCapacity(getTradingCapacity());
        city.setResources(getResources());
        city.setSieged(isSieged());
        city.setDominantReligion(getDominantReligion());
        city.setUnits(getUnits());
        city.setBesiegeUnits(getBesiegeUnits());
        city.setImprovingBuildings(getImprovingBuildings());
        city.setProducingBuildings(getProducingBuildings());
        city.setTradeRoutes(getTradeRoutes());
        return city;
    }
}
