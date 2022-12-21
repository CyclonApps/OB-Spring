package com.example.obejHelloLaptop.repositories;

import com.example.obejHelloLaptop.entities.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  LaptopRepository extends JpaRepository<Laptop, Long> {
}
