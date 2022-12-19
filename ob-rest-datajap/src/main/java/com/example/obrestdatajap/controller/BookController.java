package com.example.obrestdatajap.controller;

import com.example.obrestdatajap.entities.Book;
import com.example.obrestdatajap.repository.BookRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    // Atributos
    private BookRepository bookRepository;

    // Constructores
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    //

    // CRUD sobre la entidad Book

    // Buscar todos los libros (lista de libros)
    /**
     * http://localhost:8080/api/books
     * @return
     * **/
    @GetMapping("/api/books")
    public List<Book> findAll() {
        // Recuperar i devolver los libros de base de datos
        return bookRepository.findAll();
    }

    // Buscar un solo libro en base de datos segun su id
    @GetMapping("/api/books/{id}")
    public ResponseEntity<Book> findOneById(@PathVariable Long id) {
        Optional<Book> bookOpt = bookRepository.findById(id);

        // Opción 1
        if (bookOpt.isPresent()) {
            return ResponseEntity.ok(bookOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }

        // Opción 2
        // return bookOpt.orElse(null);
    }

    // Crear un nuevo libro en base a datos


    // Actualizar un libro existente en base de datos

    // Borrar un libro en base de datos
}
