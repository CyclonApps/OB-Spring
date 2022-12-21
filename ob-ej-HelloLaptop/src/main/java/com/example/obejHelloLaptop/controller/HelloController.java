package com.example.obejHelloLaptop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    // Clase que retorna una salutació
    @GetMapping("/salutacion")
    public String getGreeting() {
        return "Hola, salutaciones";
    }
}
