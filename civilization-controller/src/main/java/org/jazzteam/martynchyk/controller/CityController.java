package org.jazzteam.martynchyk.controller;

import org.jazzteam.martynchyk.dao.implementation.CityDao;
import org.jazzteam.martynchyk.dto.CityDto;
import org.jazzteam.martynchyk.entity.City;
import org.jazzteam.martynchyk.entity.units.Trader;
import org.jazzteam.martynchyk.entity.units.Worker;
import org.jazzteam.martynchyk.entity.units.military.Scout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@Transactional
@RequestMapping("cities")
public class CityController {

    private final CityDao cityDao;

    @Autowired
    public CityController(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    @GetMapping("/post")
    public String showCityForm() {
        return "city_form";
    }

    @GetMapping()
    public String showCivilizations(Model model) {
        List<City> cities = cityDao.findAll();

        if (cities == null || cities.size() == 0) {
            model.addAttribute("error", "data not found");
            return "civilizations";
        }

        List<CityDto> citiesDto = cities.stream()
                .map(CityDto::new)
                .collect(Collectors.toList());

        model.addAttribute("citiesList", citiesDto);
        return "city";
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String getCivilizationById(@PathVariable Long id, Model model) {
        City city = cityDao.find(id);
        if (city == null) {
            model.addAttribute("error", "ошибка");
        }
        model.addAttribute("error", "null");
        model.addAttribute("civilization", new CityDto(city));
        return "civilization";
    }

    @GetMapping("/init")
    public void initializeDatabase() {

        City samara = new City();
        samara.setName("Samara");
        samara.addUnit(new Trader());
        samara.addUnit(new Worker());
        samara.addUnit(new Scout());

        cityDao.create(samara);
    }

}
