package org.jazzteam.martynchyk.entity;

import lombok.Getter;
import lombok.Setter;
import org.jazzteam.martynchyk.entity.building.Improving;
import org.jazzteam.martynchyk.entity.building.Producing;
import org.jazzteam.martynchyk.entity.enums.ReligionType;
import org.jazzteam.martynchyk.entity.resources.implementation.Food;
import org.jazzteam.martynchyk.entity.resources.implementation.Production;
import org.jazzteam.martynchyk.entity.units.Unit;
import org.jazzteam.martynchyk.entity.units.military.BaseWarrior;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class City implements Combat, Time {

    private Civilization civilization;
    private double defence;
    private double healthPoint;
    private int strength;
    private int level;
    private Food food;
    private Production production;
    private boolean isSieged;
    private ReligionType dominantReligion;
    private List<Unit> units;
    private List<Combat> besiegeUnits;
    private List<Improving> improvingBuildings;
    private List<Producing> producingBuildings;

    public City() {
    }

    public City(Civilization civilization) {
        this.civilization = civilization;
        this.healthPoint = 100;
        this.strength = 40;
        this.level = 0;
        this.defence = 20;
        this.dominantReligion = null;
        this.food = new Food(10);
        this.production = new Production(10);
        this.isSieged = false;
        this.units = new ArrayList<>();
        this.besiegeUnits = new ArrayList<>();
        this.improvingBuildings = new ArrayList<>();
        this.producingBuildings = new ArrayList<>();
    }

    @Override
    public int getRange() {
        return 0;
    }

    @Override
    public int getRangedStrength() {
        return 0;
    }

    @Override
    public int getStrength() {
        return strength;
    }

    @Override
    public double getDefence() {
        return defence;
    }

    public void setDefence(double defence) {
        this.defence = defence;
    }

    @Override
    public void doTick() {
        food.setAmount(food.getAmount() - 2 * units.size());
        collectResources();
        if (resourcesAreEmpty()) {
            getResourcesFromOtherCities();
        }
    }

    public void getResourcesFromOtherCities() {
        if (!isSieged) {
            civilization.requestResources(Food.class.getName());
        }
    }

    private boolean resourcesAreEmpty() {
        return getFood().getAmount() <= 2 * units.size() && getProduction().getAmount() <= 0;
    }

    public void addBesiegeUnit(BaseWarrior unit) {
        besiegeUnits.add(unit);
        setSieged(besiegeUnits.size() > level + 10);
    }

    public void removeBesiegeUnit(BaseWarrior unit) {
        besiegeUnits.remove(unit);
    }

    public void addImprovingBuildings(Improving improvingBuilding) {
        improvingBuilding.improveAttribute(this);
        improvingBuildings.add(improvingBuilding);
    }

    public void removeImprovingBuildings(Improving improvingBuilding) {
        improvingBuilding.afterRemoving(this);
        improvingBuildings.remove(improvingBuilding);
    }

    public void addProducingBuildings(Producing producingBuilding) {
        producingBuildings.add(producingBuilding);
    }

    public void removeProducingBuildings(Producing producingBuilding) {
        producingBuildings.remove(producingBuilding);
    }


    public void addUnit(Unit unit) {
        units.add(unit);
        unit.setCity(this);
        level++;
    }

    public void removeUnit(Unit unit) {
        units.remove(unit);
        level--;
    }

    public void collectResources() {
        producingBuildings.stream()
                .forEach(building -> building.produceResource(this));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Double.compare(city.defence, defence) == 0 &&
                Double.compare(city.healthPoint, healthPoint) == 0 &&
                strength == city.strength &&
                level == city.level &&
                isSieged == city.isSieged &&
                Objects.equals(civilization, city.civilization) &&
                Objects.equals(food, city.food) &&
                Objects.equals(production, city.production) &&
                dominantReligion == city.dominantReligion &&
                Objects.equals(units, city.units) &&
                Objects.equals(besiegeUnits, city.besiegeUnits) &&
                Objects.equals(improvingBuildings, city.improvingBuildings) &&
                Objects.equals(producingBuildings, city.producingBuildings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(civilization, defence, healthPoint, strength, level, food, production, isSieged, dominantReligion, units, besiegeUnits, improvingBuildings, producingBuildings);
    }

    @Override
    public String toString() {
        return "City{" +
                ", defence=" + defence +
                ", healthPoint=" + healthPoint +
                ", strength=" + strength +
                ", level=" + level +
                ", food=" + food +
                ", production=" + production +
                ", dominantReligion=" + dominantReligion +
                ", units=" + units +
                ", improvingBuildings=" + improvingBuildings +
                ", producingBuildings=" + producingBuildings +
                '}';
    }
}
