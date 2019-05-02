package org.jazzteam.martynchyk.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UnitController {
    @GetMapping("/sample")
    public String showForm() {
        return "sample";
    }

    @GetMapping("/")
    public String showIndex() {
        return "index";
    }
}
