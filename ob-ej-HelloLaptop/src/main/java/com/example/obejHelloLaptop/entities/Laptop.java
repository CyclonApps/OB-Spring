package com.example.obejHelloLaptop.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

@Entity
@Table
public class Laptop {
    // Attributes
    @Id
    @GeneratedValue
    public Long id;
    public String brand;
    public String model;
    public String description;
    public LocalDate releaseDate;

    // Constructors
    public Laptop() {
    }

    public Laptop(Long id, String brand, String model, String description, LocalDate releaseDate) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
}
