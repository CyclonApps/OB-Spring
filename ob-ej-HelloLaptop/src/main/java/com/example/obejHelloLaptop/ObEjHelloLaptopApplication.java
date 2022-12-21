package com.example.obejHelloLaptop;

import com.example.obejHelloLaptop.entities.Laptop;
import com.example.obejHelloLaptop.repositories.LaptopRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;

@SpringBootApplication
public class ObEjHelloLaptopApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ObEjHelloLaptopApplication.class, args);
		LaptopRepository repository = context.getBean(LaptopRepository.class);

		// Create Laptop
		Laptop laptop1 = new Laptop(null, "Acer", "Nitro", "Test Description", LocalDate.now());
		Laptop laptop2 = new Laptop(null, "HP", "Omen", "Test Description", LocalDate.now());

		repository.save(laptop1);
		repository.save(laptop2);
	}
}
