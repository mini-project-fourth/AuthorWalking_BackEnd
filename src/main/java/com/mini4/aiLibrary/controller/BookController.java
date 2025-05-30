package com.mini4.aiLibrary.controller;

import com.mini4.aiLibrary.domain.Book;
import com.mini4.aiLibrary.dto.BookDto;
import com.mini4.aiLibrary.service.BookService;
import com.mini4.aiLibrary.service.BookServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public List<Book> getBooks() {return bookService.findBooks();}

    @GetMapping("/books/{id}")
    public Book getBook(@PathVariable Long id){
        return bookService.findBook(id);
    }

    @PostMapping("/books")
    public Book createBook(@RequestBody BookDto.BookPost bookDto) {
        return bookService.insertBook(bookDto);
    }
    @GetMapping("/books")
    public List<Book> getBooks() {return bookService.findBooks();}

    @GetMapping("books/{id}")
    public Book getBook(@PathVariable Long id){
        return bookService.findBook(id);
    }
  
}
