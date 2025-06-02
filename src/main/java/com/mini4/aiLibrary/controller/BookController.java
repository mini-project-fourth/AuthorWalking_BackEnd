package com.mini4.aiLibrary.controller;

import com.mini4.aiLibrary.domain.Book;
import com.mini4.aiLibrary.dto.BookRequestDto;
import com.mini4.aiLibrary.service.BookService;
import com.mini4.aiLibrary.util.JwtUtil;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
public class BookController {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);
    private final BookService bookService;
    private final JwtUtil jwtUtil;

    public BookController(BookService bookService, JwtUtil jwtUtil) {
        this.bookService = bookService;
        this.jwtUtil = jwtUtil;
    }

    private String validateTokenAndExtractEmail(String token) {
        if (token == null || !token.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Missing or invalid Authorization header");
        }
        logger.info("Authorization Header: {}", token);
        String email = jwtUtil.extractEmail(token.replace("Bearer ", ""));
        logger.info("Extracted Email: {}", email);
        return email;
    }

    @PostMapping("/books")
    public Book createBook(@RequestBody BookRequestDto bookDto,
                           @RequestHeader(value = "Authorization", required = false) String token) {
        String email = validateTokenAndExtractEmail(token);
        return bookService.insertBook(bookDto, email);
    }

    @PutMapping("/books/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody BookRequestDto bookDto,
                           @RequestHeader(value = "Authorization", required = false) String token) {
        validateTokenAndExtractEmail(token);
        return bookService.updateBook(id, bookDto);
    }

    @GetMapping("/books")
    public List<Book> getBooks(@RequestHeader(value = "Authorization", required = false) String token) {
        validateTokenAndExtractEmail(token);
        return bookService.findBooks();
    }

    @GetMapping("/books/{id}")
    public Book getBook(@PathVariable Long id,
                        @RequestHeader(value = "Authorization", required = false) String token) {
        validateTokenAndExtractEmail(token);
        return bookService.findBook(id);
    }

    @DeleteMapping("/books/{id}")
    public void deleteBook(@PathVariable Long id,
                           @RequestHeader(value = "Authorization", required = false) String token) {
        validateTokenAndExtractEmail(token);
        bookService.deleteBook(id);
    }
}
