package com.durov.maks.cinema.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inject-data")
public class InjectDataController {

    @GetMapping
    public String injectData() {
        return "data injected";
    }
}
