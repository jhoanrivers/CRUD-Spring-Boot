package com.example.demo.controller;


import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    BookRepository bookRepository;


    @GetMapping("/books")
    public List<Book> getAllBook(){
        return bookRepository.findAll();
    }

    @GetMapping("/books/{id}")
    public Book getSingleBook(@PathVariable (value = "id") long id) throws Exception {
        return bookRepository.findById(id).orElseThrow(()->new Exception("Not found"));
    }

    @PostMapping("/books")
    public Book createBook(@Valid @RequestBody Book book){
        return bookRepository.save(book);
    }

    @PutMapping("/books/{id}")
    public Book updateBook(@PathVariable (value = "id") long id, @Valid @RequestBody Book book) throws Exception {
        Book newbook = bookRepository.findById(id).orElseThrow(()->new Exception("not Found"));

        newbook.setTitle(book.getTitle());
        newbook.setAuthor(book.getAuthor());

        Book updateBook = bookRepository.save(newbook);
        return updateBook;

    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable (value = "id") long id) throws Exception {
        Book delbook = bookRepository.findById(id).orElseThrow(()-> new Exception("Not Found"));

        bookRepository.delete(delbook);
        return ResponseEntity.ok().build();
    }



}
