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

@Controller
@Transactional
@RequestMapping("cities")
public class CityController {

    private final CityDao cityDao;

    @Autowired
    public CityController(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    @GetMapping("/form")
    public String showCityForm(Model model) {
        model.addAttribute("city", new CityDto());
        return "city_form";
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String getCityById(@PathVariable Long id, Model model) {
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
