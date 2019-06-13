package org.jazzteam.martynchyk.controller;

import org.jazzteam.martynchyk.dao.implementation.CivilizationDao;
import org.jazzteam.martynchyk.dto.CivilizationDto;
import org.jazzteam.martynchyk.entity.City;
import org.jazzteam.martynchyk.entity.Civilization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@Transactional
public class CivilizationController {

//    private final CivilizationDao civilizationDao;
//
//    @Autowired
//    public CivilizationController(CivilizationDao civilizationDao) {
//        this.civilizationDao = civilizationDao;
//    }

    @Autowired
    CivilizationDao civilizationDao;

    @GetMapping("/list")
    public String showList() {
        return "list";
    }

    @GetMapping("/civilizations")
    public String showCivilizationsList() {
        return "civilizations";
    }


    @GetMapping("/civilization")
    @ResponseStatus(HttpStatus.OK)
    public String getCivilization(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        Civilization civilization = new Civilization();
        civilization.setName("Russia");

        City moscow = new City(civilization);
        moscow.setName("Moscow");
        civilization.addCity(moscow);
        civilization.setCapital(moscow);


        City samara = new City(civilization);
        samara.setName("Samara");
        civilization.addCity(samara);

        model.addAttribute("civilization", new CivilizationDto(civilization));
        return "civilization";
    }


    @GetMapping("/civilization/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String getCivilizationById(@PathVariable Long id, Model model) {
        Civilization civilization = civilizationDao.find(id);
        if (civilization == null) {
            model.addAttribute("eror", "ошибка");
        }
        model.addAttribute("civilization", new CivilizationDto(civilization));
        return "civilization";
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public class ResourceNotFoundException extends RuntimeException {
    }

}
