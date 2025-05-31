package com.mini4.aiLibrary.controller;

import com.mini4.aiLibrary.domain.Book;
import com.mini4.aiLibrary.dto.BookRequestDto;
import com.mini4.aiLibrary.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/books")
    public Book createBook(@RequestBody BookRequestDto bookDto) {
        return bookService.insertBook(bookDto);
    }

    @PutMapping("/books/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody BookRequestDto bookDto) {
        return bookService.updateBook(id, bookDto);
    }

    @GetMapping("/books")
    public List<Book> getBooks() {return bookService.findBooks();}

    @GetMapping("/books/{id}")
    public Book getBook(@PathVariable Long id){
        return bookService.findBook(id);
    }

    @DeleteMapping("/books/{id}")
    public void deleteBook(@PathVariable Long id){
       bookService.deleteBook(id);
    }
  
}
