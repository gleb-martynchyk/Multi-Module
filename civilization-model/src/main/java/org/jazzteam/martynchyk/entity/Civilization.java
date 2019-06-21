package org.jazzteam.martynchyk.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jazzteam.martynchyk.entity.enums.ReligionType;
import org.jazzteam.martynchyk.entity.tree.Tree;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Entity
public class Civilization implements Time {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    private String name;
    private int faith;
    private int gold;
    private int science;
    @OneToOne(cascade = CascadeType.ALL)
    private City capital;
    @OneToMany(mappedBy = "civilization", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<City> cities;
    @Transient
    private ReligionType dominantReligion;
    @Transient
    private Tree scienceTree;

    public Civilization() {
        this.faith = 0;
        this.gold = 0;
        this.science = 0;
        this.cities = new ArrayList<>();
    }

    @Override
    public void doTick() {
        for (int i=0;i<cities.size();i++){
            cities.get(i).doTick();
        }
    }

    public void addCity(City city) {
        cities.add(city);
    }

    public void removeCity(City city) {
        cities.remove(city);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Civilization that = (Civilization) o;
        return id == that.id &&
                faith == that.faith &&
                gold == that.gold &&
                science == that.science &&
                Objects.equals(name, that.name) &&
                Objects.equals(capital, that.capital) &&
                Objects.equals(cities, that.cities) &&
                dominantReligion == that.dominantReligion &&
                Objects.equals(scienceTree, that.scienceTree);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, faith, gold, science, capital, cities, dominantReligion, scienceTree);
    }
}
