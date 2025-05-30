package com.mini4.aiLibrary.service;

import com.mini4.aiLibrary.domain.Book;
import com.mini4.aiLibrary.dto.BookDto;

import java.util.List;

public interface BookService {

    Book insertBook(BookDto.BookPost bookDto);
    Book updateBook(Long id, BookDto.BookPut bookDto);
    List<Book> findBooks();
    Book findBook(Long id);
    void deleteBook(Long id);
}
