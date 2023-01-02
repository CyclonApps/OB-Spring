package com.example.obejHelloLaptop.controller;

import com.example.obejHelloLaptop.entities.Laptop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LaptopControllerTest {

    private TestRestTemplate testRestTemplate;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

     @LocalServerPort
     private int port;

     @BeforeEach
     void setUp() {
         restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port);
         testRestTemplate = new TestRestTemplate(restTemplateBuilder);
     }

    @Test
    void findAll() {
        ResponseEntity<Laptop[]> response =
                testRestTemplate.getForEntity("/api/laptop", Laptop[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());

        List<Laptop> laptops = Arrays.asList(response.getBody());
        System.out.println(laptops.size());
    }

    @Test
    void findOneById() {
         ResponseEntity<Laptop> response =
                 testRestTemplate.getForEntity("/api/laptop/1", Laptop.class);

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

        assertEquals(3L, result.getId());
    }

    @Test
    void update() {

    }

    @Test
    void delete() {
    }

    @Test
    void deleteAll() {
    }
}