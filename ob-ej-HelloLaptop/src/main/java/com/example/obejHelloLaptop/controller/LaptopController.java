package com.example.obejHelloLaptop.controller;

import com.example.obejHelloLaptop.entities.Laptop;
import com.example.obejHelloLaptop.repositories.LaptopRepository;
import io.swagger.annotations.Api;
import jakarta.websocket.server.PathParam;
import org.apache.coyote.Response;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class LaptopController {

    public LaptopRepository laptopRepository;

    public LaptopController(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    @GetMapping("/laptop")
    public List<Laptop> findAll() {
        return laptopRepository.findAll();
    }

    @GetMapping("/laptop/{id}")
    public ResponseEntity<Laptop> findOneById(@PathVariable Long id) {
        if (laptopRepository.count() > 0) {
            Optional<Laptop> laptopOpt = laptopRepository.findById(id);

            if (laptopOpt.isPresent()) {
                return ResponseEntity.ok(laptopOpt.get());
            }

            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/laptop")
    public ResponseEntity<Laptop> create(@RequestBody Laptop laptop) {
        if (laptop.getId() != null) return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(laptopRepository.save(laptop));
    }

    @PutMapping("/laptop")
    public ResponseEntity<Laptop> update(@RequestBody Laptop laptop) {
        if (laptop.getId() == null) return ResponseEntity.badRequest().build();

        if (!laptopRepository.existsById(laptop.getId())) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok( laptopRepository.save(laptop));
    }

    @DeleteMapping("/laptop/{id}")
    public ResponseEntity<Laptop> delete(@PathVariable Long id) {
        if (laptopRepository.existsById(id)) {
            laptopRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/laptop")
    public ResponseEntity<Laptop> deleteAll() {
        if (laptopRepository.count() > 0) {
            laptopRepository.deleteAll();
            ResponseEntity.noContent().build();
        }

        return ResponseEntity.badRequest().build();
    }
}
