package org.jazzteam.martynchyk.entity;

import lombok.Data;
import org.jazzteam.martynchyk.entity.enums.ReligionType;
import org.jazzteam.martynchyk.entity.tree.Tree;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
        cities.stream()
                .forEach(city -> city.doTick());
    }

    public void addCity(City city) {
        cities.add(city);
    }

    public void removeCity(City city) {
        cities.remove(city);
    }

}
