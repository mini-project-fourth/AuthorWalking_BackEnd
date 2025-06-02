package com.mini4.aiLibrary.service;

import com.mini4.aiLibrary.domain.Book;
import com.mini4.aiLibrary.dto.BookRequestDto;

import java.util.List;

public interface BookService {

    Book insertBook(BookRequestDto bookDto, String email);
    Book updateBook(Long id, BookRequestDto bookDto);
    List<Book> findBooks();
    Book findBook(Long id);
    void deleteBook(Long id);
}
