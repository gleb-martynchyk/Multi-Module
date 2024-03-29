package org.jazzteam.martynchyk.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.jazzteam.martynchyk.entity.building.ImprovingEntity;
import org.jazzteam.martynchyk.entity.building.ProducingEntity;
import org.jazzteam.martynchyk.entity.enums.ReligionType;
import org.jazzteam.martynchyk.entity.resources.Resource;
import org.jazzteam.martynchyk.entity.resources.implementation.Food;
import org.jazzteam.martynchyk.entity.resources.implementation.Production;
import org.jazzteam.martynchyk.entity.trade.TradeDeal;
import org.jazzteam.martynchyk.entity.trade.TradeDealResult;
import org.jazzteam.martynchyk.entity.trade.TradeRoute;
import org.jazzteam.martynchyk.entity.units.Unit;
import org.jazzteam.martynchyk.entity.units.military.BaseWarrior;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@Entity
public class City implements Combat, Time {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    @ManyToOne
    private Civilization civilization;
    private double defence;
    private double healthPoint;
    private int strength;
    private int level;
    private int tradingCapacity;

    @Column(name = "city_resources")
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Map<Class, Resource> resources;

    private boolean isSieged;

    @Transient
    private ReligionType dominantReligion;

    @OneToMany(mappedBy = "city", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SELECT)
    private List<Unit> units;

    @Transient
    private List<Combat> besiegeUnits;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<ImprovingEntity> improvingBuildings;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<ProducingEntity> producingBuildings;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<TradeRoute> tradeRoutes;

    public City() {
        this.resources = new HashMap<>();
        resources.put(Food.class, new Food(10));
        resources.put(Production.class, new Production(10));
        this.healthPoint = 100;
        this.strength = 40;
        this.level = 0;
        this.tradingCapacity = 0;
        this.defence = 20;
        this.dominantReligion = null;
        this.isSieged = false;
        this.units = new ArrayList<>();
        this.besiegeUnits = new ArrayList<>();
        this.improvingBuildings = new ArrayList<>();
        this.producingBuildings = new ArrayList<>();
        this.tradeRoutes = new HashSet<>();
    }

    public City(Civilization civilization) {
        this.resources = new HashMap<>();
        resources.put(Food.class, new Food(10));
        resources.put(Production.class, new Production(10));
        this.civilization = civilization;
        this.healthPoint = 100;
        this.strength = 40;
        this.level = 0;
        this.tradingCapacity = 0;
        this.defence = 20;
        this.dominantReligion = null;
        this.isSieged = false;
        this.units = new ArrayList<>();
        this.besiegeUnits = new ArrayList<>();
        this.improvingBuildings = new ArrayList<>();
        this.producingBuildings = new ArrayList<>();
        this.tradeRoutes = new HashSet<>();
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
        return this.resources.get(Food.class);
    }

    public void setFood(Food food) {
        this.resources.put(Food.class, food);
    }

    public Resource getProduction() {
        return resources.get(Production.class);
    }

    public void setProduction(Production production) {
        this.resources.put(Production.class, production);
    }

    public void setCivilization(Civilization civilization) {
        if (civilization.getCities().size() != 0) {
            this.civilization.removeCity(this);
        }
        this.civilization = civilization;
        civilization.addCity(this);
    }

    public boolean updateResourceAmount(Class resource, int amount) {
        if (this.getResources().get(resource).getAmount() + amount >= 0) {
            this.getResources().get(resource).setAmount(
                    this.getResources().get(resource).getAmount()
                            + amount
            );
            return true;
        } else return false;
    }

    @Override
    public void doTick() {
        besiegeUnits.removeIf(Combat::isDead);
        units.removeIf(Unit::isDead);

//        int nextFoodAmount = getFood().getAmount() - 2 * units.size();
//        if (nextFoodAmount < 0) {
//            //health points reduces with a lack of food
//            units.forEach(unit -> unit.setHealthPoint(unit.getHealthPoint() - 1));
//        } else {
//            this.resources.get(Food.class).setAmount(nextFoodAmount);
//        }
//        int foodAmount = this.resources.get(Food.class).getAmount();

        feedUnits();

        collectResources();
        if (resourcesAreEmpty()) {
            requestResourcesFromCivilization();
        }
    }

    public boolean feedUnits() {
        int unitMinFoodForStep = 2;
        boolean returnValue = true;
        int unitsSize = units.size();
        Set<Integer> unitsIndexes = new HashSet<>();
        for (int i = 0; i < unitsSize; i++) {
            unitsIndexes.add(i);
        }
        Random r = new Random();
        int nextUnitIndex;
        int nextFoodAmount = resources.get(Food.class).getAmount();

        for (int i = 0; i < unitsSize; i++) {
            nextUnitIndex = r.nextInt(unitsSize);
            if (nextFoodAmount < unitMinFoodForStep) {
                if (units.size() > 0) {
                    units.get(nextUnitIndex).setHealthPoint(units.get(nextUnitIndex).getHealthPoint() - 1);
                }
                returnValue = false;
            } else {
                unitsIndexes.remove(nextUnitIndex);
                unitsSize--;
                nextFoodAmount -= unitMinFoodForStep;
            }
        }
        resources.get(Food.class).setAmount(nextFoodAmount);
        return returnValue;
    }

    public TradeDealResult trade(TradeDeal tradeDeal) {
        TradeDealResult tradeResult;

        TradeRoute tradeRoute = tradeRoutes.stream()
                .filter(route -> route.getCityToTrade().agreeToTrade(tradeDeal))
                .findAny()
                .orElse(null);

        if (tradeRoute != null) {
            tradeResult = tradeRoute.exchange(tradeDeal, this);
        } else {
            tradeResult = new TradeDealResult();
            tradeResult.setStatus(TradeDealResult.Status.REFUSED);
            return tradeResult;
        }

        return tradeResult;
    }

    public boolean executeTradeDeal(TradeDeal tradeDeal, City cityToTrade) {
        updateResourceAmount(tradeDeal.getResourceFrom(), -tradeDeal.getAmountFrom());
        cityToTrade.updateResourceAmount(tradeDeal.getResourceFrom(), tradeDeal.getAmountFrom());
        cityToTrade.updateResourceAmount(tradeDeal.getResourceTo(), -tradeDeal.getAmountTo());
        updateResourceAmount(tradeDeal.getResourceTo(), +tradeDeal.getAmountTo());
        return false;
    }

    public boolean agreeToTrade(TradeDeal tradeDeal) {
        int amountOfExcessToGive = getResources().get(tradeDeal.getResourceTo()).getExcess(this);
        int amountOfExcessToTake = getResources().get(tradeDeal.getResourceFrom()).getExcess(this);

        return amountOfExcessToGive > amountOfExcessToTake;
    }

    public boolean requestResourcesFromCivilization() {
        if (isSieged) {
            return false;
        }

        List<City> cities = civilization.getCities();
        cities.remove(this);
        final int maxRequestCount = (int) (cities.size() * 2.5);
        resources.entrySet().stream()
                .filter(resource -> resource.getValue().isEmpty())
                .forEach(resource -> {
                    for (int i = 0; resource.getValue().isEmpty() && i < maxRequestCount; i++) {
                        Class resourceType = resource.getKey();

                        City supporter = findCityThatCanProvideResource(cities, resourceType);

                        if (supporter != null) {
                            requestResourceFromCity(supporter, resourceType);
                        }
                    }
                });

        return !resourcesAreEmpty();
    }

    private City findCityThatCanProvideResource(List<City> cities, Class resourceType) {
        return cities.stream()
                .filter(city -> city.getResources().get(resourceType).isInAbundance(city))
                .max(Comparator.comparingInt(city -> city.getResources().get(resourceType).getAmount()))
                .orElse(null);
    }

    public int requestResourceFromCity(City cityDonor, Class resourceType) {
        int excess = (int) (0.3 * cityDonor.getResources().get(resourceType).getExcess(cityDonor));
//        cityDonor.getResources().get(resourceType).setAmount(
//                cityDonor.getResources().get(resourceType).getAmount() - excess
//        );
//        this.getResources().get(resourceType).setAmount(
//                this.getResources().get(resourceType).getAmount() + excess
//        );

        cityDonor.updateResourceAmount(resourceType, -excess);
        this.updateResourceAmount(resourceType, excess);
        //TODO update resource amount, чтобы все было в одной функции
        return excess;
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

    public void addTradeRoute(TradeRoute route) {
        tradeRoutes.add(route);
    }

    public void addTradeRoute(City city) {
        tradeRoutes.add(new TradeRoute(city));
    }

    public void removeTradeRoute(TradeRoute route) {
        tradeRoutes.remove(route);
    }

    public void addImprovingBuildings(ImprovingEntity improvingBuilding) {
        improvingBuilding.improveAttribute(this);
        improvingBuildings.add(improvingBuilding);
    }

    public void removeImprovingBuildings(ImprovingEntity improvingBuilding) {
        improvingBuilding.afterRemoving(this);
        improvingBuildings.remove(improvingBuilding);
    }

    public void addProducingBuildings(ProducingEntity producingBuilding) {
        producingBuildings.add(producingBuilding);
    }

    public void removeProducingBuildings(ProducingEntity producingBuilding) {
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
        return id == city.id &&
                Double.compare(city.defence, defence) == 0 &&
                Double.compare(city.healthPoint, healthPoint) == 0 &&
                strength == city.strength &&
                level == city.level &&
                tradingCapacity == city.tradingCapacity &&
                isSieged == city.isSieged &&
                resources.equals(city.resources) &&
                dominantReligion == city.dominantReligion &&
                units.equals(city.units) &&
                besiegeUnits.equals(city.besiegeUnits) &&
                improvingBuildings.equals(city.improvingBuildings) &&
                producingBuildings.equals(city.producingBuildings) &&
                tradeRoutes.equals(city.tradeRoutes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, civilization, defence, healthPoint, strength, level, tradingCapacity, resources, isSieged, dominantReligion, units, besiegeUnits, improvingBuildings, producingBuildings, tradeRoutes);
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
