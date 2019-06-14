package org.jazzteam.martynchyk.controller;

import org.jazzteam.martynchyk.dao.implementation.CivilizationDao;
import org.jazzteam.martynchyk.dto.CivilizationDto;
import org.jazzteam.martynchyk.entity.City;
import org.jazzteam.martynchyk.entity.Civilization;
import org.jazzteam.martynchyk.entity.units.Settler;
import org.jazzteam.martynchyk.entity.units.Trader;
import org.jazzteam.martynchyk.entity.units.Worker;
import org.jazzteam.martynchyk.entity.units.military.Scout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@Transactional
@RequestMapping("civilizations")
public class CivilizationController {

    private final CivilizationDao civilizationDao;

    @Autowired
    public CivilizationController(CivilizationDao civilizationDao) {
        this.civilizationDao = civilizationDao;
    }

    @GetMapping("/list")
    public String showList() {
        return "list";
    }

    @GetMapping()
    public String showCivilizationsList(Model model) {
        List<Civilization> civilizations = civilizationDao.findAll();

        if (civilizations == null || civilizations.size() == 0) {
            model.addAttribute("error", "data not found");
            return "civilizations";
        }

        List<CivilizationDto> civilizationsDto = civilizations.stream()
                .map(CivilizationDto::new)
                .collect(Collectors.toList());

        //model.addAttribute("error", "null");
        model.addAttribute("civilizationsList", civilizationsDto);
        return "civilizations";
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String getCivilizationById(@PathVariable Long id, Model model) {
        Civilization civilization = civilizationDao.find(id);
        if (civilization == null) {
            model.addAttribute("error", "ошибка");
        }
        model.addAttribute("civilization", new CivilizationDto(civilization));
        return "civilization";
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public class ResourceNotFoundException extends RuntimeException {
    }

    @GetMapping("/init")
    public void initializeDatabase() {
        Civilization expectedCivilization = new Civilization();
        expectedCivilization.setName("Russia");

        City moscow = new City(expectedCivilization);
        moscow.setName("Moscow");
        expectedCivilization.addCity(moscow);
        expectedCivilization.setCapital(moscow);

        City samara = new City(expectedCivilization);
        samara.setName("Samara");
        expectedCivilization.addCity(samara);

        moscow.addUnit(new Settler());
        moscow.addUnit(new Trader());


        samara.addUnit(new Trader());
        samara.addUnit(new Worker());
        samara.addUnit(new Scout());

        civilizationDao.create(expectedCivilization);
    }

}
