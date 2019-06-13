package org.jazzteam.martynchyk.entity;

import lombok.Getter;
import lombok.Setter;
import org.jazzteam.martynchyk.entity.building.Improving;
import org.jazzteam.martynchyk.entity.building.Producing;
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
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table
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
    @Column(name="city_resources")
    @OneToMany(cascade = CascadeType.ALL)
    private Map<Class, Resource> resources;
    private boolean isSieged;
    @Transient
    private ReligionType dominantReligion;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Unit> units;
    @Transient
    private List<Combat> besiegeUnits;
    @Transient
    private List<Improving> improvingBuildings;
    @Transient
    private List<Producing> producingBuildings;
    @Transient
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
        this.civilization.removeCity(this);
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
        besiegeUnits = besiegeUnits.stream()
                .filter(combat -> !combat.isDead())
                .collect(Collectors.toList());

//        TODO setAmount должен возвращать boolean, чтобы не установить еду отрицательной
//        тут еще должин происходить пересчет уровня города, и количества торговых путей
//        религии и тд

        getFood().setAmount(getFood().getAmount() - 2 * units.size());
        collectResources();
        if (resourcesAreEmpty()) {
            requestResourcesFromCivilization();
        }
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
//        return amountOfExcessToGive * 0.3 >= tradeDeal.getAmountTo()
//                && amountOfExcessToTake * 0.3 <= tradeDeal.getAmountFrom();
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
        cityDonor.getResources().get(resourceType).setAmount(
                cityDonor.getResources().get(resourceType).getAmount() - excess
        );
        this.getResources().get(resourceType).setAmount(
                this.getResources().get(resourceType).getAmount() + excess
        );
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
