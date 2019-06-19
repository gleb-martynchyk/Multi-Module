package org.jazzteam.martynchyk.controller;

import org.jazzteam.martynchyk.dao.implementation.CivilizationDao;
import org.jazzteam.martynchyk.dto.CivilizationDto;
import org.jazzteam.martynchyk.entity.City;
import org.jazzteam.martynchyk.entity.Civilization;
import org.jazzteam.martynchyk.entity.building.providing_implementations.Farm;
import org.jazzteam.martynchyk.entity.building.providing_implementations.Mine;
import org.jazzteam.martynchyk.entity.resources.implementation.Food;
import org.jazzteam.martynchyk.entity.units.Settler;
import org.jazzteam.martynchyk.entity.units.Trader;
import org.jazzteam.martynchyk.entity.units.Worker;
import org.jazzteam.martynchyk.entity.units.military.Archer;
import org.jazzteam.martynchyk.entity.units.military.Spearman;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Transactional(readOnly = false)
@RequestMapping("civilizations")
public class CivilizationController {

    private final CivilizationDao civilizationDao;

    @Autowired
    public CivilizationController(CivilizationDao civilizationDao) {
        this.civilizationDao = civilizationDao;
    }

    @GetMapping("/form")
    public String showCivilizationForm(@ModelAttribute("error") String error, Model model) {
        model.addAttribute("civilization", new CivilizationDto());
        return "civilization_form";
    }

    @PostMapping()
    public String createCivilization(
            @ModelAttribute("civilization") Civilization civilization,
            RedirectAttributes redirectAttributes) {
        if (civilization.getCapital() != null) {
            civilization.getCapital().setCivilization(civilization);
        }
        try {
            civilizationDao.create(civilization);
        } catch (PersistenceException exc) {
            redirectAttributes.addFlashAttribute("error",
                    "Civilization with name \"" + civilization.getName() + "\" exist");
            return "redirect:civilizations/form/";
        }

        return "redirect:civilizations/";
    }

    @GetMapping("/{id}")
    public String getCivilizationById(@PathVariable Long id, Model model) {
        Civilization civilization = civilizationDao.find(id);
        if (civilization == null) {
            model.addAttribute("error", "ошибка");
        }
        model.addAttribute("civilization", new CivilizationDto(civilization));
        return "civilization";
    }

    @GetMapping()
    public String showCivilizationsList(Model model) {
        List<Civilization> civilizations = civilizationDao.findAll();

        if (civilizations == null || civilizations.size() == 0) {
            model.addAttribute("error", "Civilizations not found");
            return "civilizations";
        }

        List<CivilizationDto> civilizationsDto = civilizations.stream()
                .map(CivilizationDto::new)
                .collect(Collectors.toList());

        model.addAttribute("civilizationsList", civilizationsDto);
        return "civilizations";
    }

    @DeleteMapping("/{id}")
    public String deleteCivilizationById(@PathVariable("id") Long id) {
        civilizationDao.delete(id);
        return "redirect:/civilizations/";
    }

    @GetMapping("/{id}/nextstep")
    public String doTick(@PathVariable Long id) {
        civilizationDao.find(id).doTick();
        civilizationDao.update(civilizationDao.find(id));
        return "redirect:/civilizations/" + id;
    }

//    @RequestMapping(value = "/processForm", method = RequestMethod.POST)
//    public String processForm(@RequestParam("message") final String message, final Model model) {
//        model.addAttribute("message", message);
//        return "message";
//    }

    @GetMapping("/init")
    public String initializeDatabase() {
        Civilization expectedCivilization = new Civilization();
        expectedCivilization.setName("Russia");

        City moscow = new City(expectedCivilization);
        moscow.setName("Moscow");
        expectedCivilization.addCity(moscow);
        expectedCivilization.setCapital(moscow);

        City samara = new City(expectedCivilization);
        samara.setName("Samara");
        expectedCivilization.addCity(samara);

        moscow.getResources().get(Food.class).setAmount(200);
        samara.getResources().get(Food.class).setAmount(50);

        moscow.addUnit(new Settler());
        moscow.addUnit(new Trader());
        moscow.addUnit(new Worker());

        samara.addUnit(new Archer());
        samara.addUnit(new Spearman());

        moscow.addProducingBuildings(new Mine());
        samara.addProducingBuildings(new Farm());


        civilizationDao.create(expectedCivilization);
        return "redirect:/civilizations/";
    }

}
