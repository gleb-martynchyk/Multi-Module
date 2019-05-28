package org.jazzteam.martynchyk.entity;

import lombok.Getter;
import lombok.Setter;
import org.jazzteam.martynchyk.entity.building.Improving;
import org.jazzteam.martynchyk.entity.building.Producing;
import org.jazzteam.martynchyk.entity.enums.ReligionType;
import org.jazzteam.martynchyk.entity.resources.Resource;
import org.jazzteam.martynchyk.entity.resources.implementation.Food;
import org.jazzteam.martynchyk.entity.resources.implementation.Production;
import org.jazzteam.martynchyk.entity.units.Unit;
import org.jazzteam.martynchyk.entity.units.military.BaseWarrior;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
public class City implements Combat, Time {

    private Civilization civilization;
    private double defence;
    private double healthPoint;
    private int strength;
    private int level;
    private Map<String, Resource> resources;
    private boolean isSieged;
    private ReligionType dominantReligion;
    private List<Unit> units;
    private List<Combat> besiegeUnits;
    private List<Improving> improvingBuildings;
    private List<Producing> producingBuildings;

    //TODO может удалить? посмотреть тесты
    public City() {
    }

    public City(Civilization civilization) {
        this.resources = new HashMap<>();
        resources.put(Food.class.getName(), new Food(10));
        resources.put(Production.class.getName(), new Production(10));
        this.civilization = civilization;
        civilization.addCity(this);
        this.healthPoint = 100;
        this.strength = 40;
        this.level = 0;
        this.defence = 20;
        this.dominantReligion = null;
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

    public Resource getFood() {
        return resources.get(Food.class.getName());
    }

    public void setFood(Food food) {
        this.resources.put(Food.class.getName(), food);
    }

    public Resource getProduction() {
        return resources.get(Production.class.getName());
    }

    public void setProduction(Production production) {
        this.resources.put(Production.class.getName(), production);
    }

    @Override
    public void doTick() {
        besiegeUnits = besiegeUnits.stream()
                .filter(combat -> !combat.isDead())
                .collect(Collectors.toList());
        getFood().setAmount(getFood().getAmount() - 2 * units.size());
        collectResources();
        if (resourcesAreEmpty()) {
            requestSupport();
        }
    }

    public boolean requestSupport() {
        if (!isSieged) {
            List<City> cities = civilization.getCities();
            cities.remove(this);
            final int maxRequestCount = (int) (cities.size() * 2.5);
            resources.entrySet().stream()
                    .filter(resource -> resource.getValue().isEmpty())
                    .forEach(resource -> {
                        int i = 0;
                        while (resource.getValue().isEmpty() && i < maxRequestCount) {
                            String resourceType = resource.getKey();

                            City cityDonor = cities.stream()
                                    .filter(city -> city.getResources().get(resourceType).isInAbundance(city))
                                    .max(Comparator.comparingInt(city -> city.getResources().get(resourceType).getAmount()))
                                    .orElse(null);

                            if (cityDonor != null) {
                                int excess = (int) (0.3 * cityDonor.getResources().get(resourceType).getExcess(cityDonor));
                                cityDonor.getResources().get(resourceType).setAmount(
                                        cityDonor.getResources().get(resourceType).getAmount() - excess
                                );
                                this.getResources().get(resourceType).setAmount(
                                        this.getResources().get(resourceType).getAmount() + excess
                                );
                            }
                            i++;
                        }
                    });
            return !resourcesAreEmpty();
        } else return false;
    }

    public boolean resourcesAreEmpty() {
        return resources.entrySet().stream()
                .filter(resource -> resource.getValue().isEmpty())
                .count() > 0;
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
                Objects.equals(resources, city.resources) &&
                dominantReligion == city.dominantReligion &&
                Objects.equals(units, city.units) &&
                Objects.equals(besiegeUnits, city.besiegeUnits) &&
                Objects.equals(improvingBuildings, city.improvingBuildings) &&
                Objects.equals(producingBuildings, city.producingBuildings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(civilization, defence, healthPoint, strength, level, resources, isSieged, dominantReligion, units, besiegeUnits, improvingBuildings, producingBuildings);
    }

    @Override
    public String toString() {
        return "City{" +
                ", defence=" + defence +
                ", healthPoint=" + healthPoint +
                ", strength=" + strength +
                ", level=" + level +
                ", food=" + getFood() +
                ", production=" + getProduction() +
                ", dominantReligion=" + dominantReligion +
                ", units=" + units +
                ", improvingBuildings=" + improvingBuildings +
                ", producingBuildings=" + producingBuildings +
                '}';
    }
}
