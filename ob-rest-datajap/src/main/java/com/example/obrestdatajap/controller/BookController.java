package com.example.obrestdatajap.controller;

import com.example.obrestdatajap.entities.Book;
import com.example.obrestdatajap.repository.BookRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    private final Logger log = LoggerFactory.getLogger(BookController.class);

    // Atributos
    private BookRepository bookRepository;

    // Constructores
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // CRUD sobre la entidad Book

    // Buscar todos los libros que hay en base de datos (ArrayList de Libros)
    /**
     * http://localhost:8080/api/books
     * @return
     */
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

        // return bookOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Crear un nuevo libro en base a datos
     * Método POST, no colisiona con findAll porque son diferentes métodos HTTP: GET vs. POST
     * @param book
     * @param headers
     * @return
     */
    @PostMapping("/api/books")
    public ResponseEntity<Book> create(@RequestBody Book book, @RequestHeader HttpHeaders headers) {
        System.out.println(headers.get("User-Agent"));
        // Guardar el libro recibido por parámetro en la base de datos
        if(book.getId() != null) { // quiere decir que existe el id y por tanto no es una creación
            log.warn("Trying to create a book with id");
            System.out.println("Trying to create a book with id");
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(bookRepository.save(book));
    }

    /**
     * Actualizar un libro existente en base de datos
     */
    @PutMapping("/api/books")
    public ResponseEntity<Object> update(@RequestBody Book book) {
        if (book.getId() == null) { // si no tiene id quiere decir que si es una creacion
            log.warn("Trying to update a non existent book");
            return ResponseEntity.badRequest().build();
        }

        if (!bookRepository.existsById(book.getId())) {
            log.warn("Trying to update a non existent book");
            return ResponseEntity.notFound().build();
        }

        // El proceso de actualización
        Book result = bookRepository.save(book);
        return ResponseEntity.ok(result);
    }

    // Borrar un libro en base de datos
    @DeleteMapping("/api/books/{id}")
    @ApiOperation("Buscar un libro por clave primaria id Long")
    public ResponseEntity<Book> delete(@ApiParam("Clave primaria tipo Long") @PathVariable Long id) {

        if (!bookRepository.existsById(id)) {
            log.warn("Trying to delete a non existent book");
            return ResponseEntity.notFound().build();
        }

        bookRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @ApiIgnore // Ignorar este método para que no aparezca en la documentación de la api Swagger
    @DeleteMapping("/api/books")
    public ResponseEntity<Book> deleteAll() {
        log.debug("REST Request for deleting all books");
        bookRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
