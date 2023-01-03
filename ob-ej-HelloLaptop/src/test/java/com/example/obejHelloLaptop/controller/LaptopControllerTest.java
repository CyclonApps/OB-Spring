package com.example.obejHelloLaptop.controller;

import com.example.obejHelloLaptop.entities.Laptop;
import com.example.obejHelloLaptop.repositories.LaptopRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LaptopControllerTest {

    private TestRestTemplate testRestTemplate;


    @Autowired
    private RestTemplateBuilder restTemplateBuilder;
    @Autowired
    private LaptopRepository laptopRepository;

     @LocalServerPort
     private int port;

     @BeforeEach
     void setUp() {
         laptopRepository.save(new Laptop(null, "Acer", "Nitro", "Test Description", LocalDate.now()));

         restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port);
         testRestTemplate = new TestRestTemplate(restTemplateBuilder);
     }

    @Test
    void findAll() {
        ResponseEntity<Laptop[]> response =
                testRestTemplate.getForEntity("/api/laptop", Laptop[].class);

        List<Laptop> laptops = Arrays.asList(response.getBody());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void findOneById() {
         ResponseEntity<Laptop> response =
                 testRestTemplate.getForEntity("/api/laptop/2", Laptop.class);

         assertEquals(HttpStatus.OK, response.getStatusCode());
         assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void create() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json = """
                {
                    "brand": "MSI",
                    "model": "Titan",
                    "description": "Test Description",
                    "releaseDate": "2023-01-02"
                }
                """;

        HttpEntity<String> request = new HttpEntity<>(json, headers);

        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptop", HttpMethod.POST, request, Laptop.class);

        Laptop result = response.getBody();

        assertEquals(2L, result.getId());
    }

    @Test
    void update() {
        laptopRepository.save(new Laptop(null, "Acer", "Nitro", "Test Description", LocalDate.now()));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json = """
                {
                    "id": 1,
                    "brand": "Acer",
                    "model": "ZenBook",
                    "description": "Test Description Updated",
                    "releaseDate": "2022-05-05"
                }
                """;

        HttpEntity<String> request = new HttpEntity<>(json, headers);

        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptop", HttpMethod.PUT, request, Laptop.class);

        Laptop result = response.getBody();

        assertEquals(1L, result.getId());
        assertEquals("Test Description Updated", result.getDescription());
    }

    @Test
    void delete() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> request = new HttpEntity<>(headers);

        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptop/1", HttpMethod.DELETE, request, Laptop.class);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void deleteAll() {
         HttpHeaders headers = new HttpHeaders();
         headers.setContentType(MediaType.APPLICATION_JSON);
         headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

         HttpEntity<String> request = new HttpEntity<>(headers);

         ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptop", HttpMethod.DELETE, request, Laptop.class);

         assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}