package com.example.obejHelloLaptop.controller;

import com.example.obejHelloLaptop.entities.Laptop;
import com.example.obejHelloLaptop.repositories.LaptopRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LaptopController {

    public LaptopRepository laptopRepository;
    public LaptopController(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    // Method to get all laptops
    @GetMapping("/laptop")
    public List<Laptop> getLaptops() {
        return laptopRepository.findAll();
    }
}
