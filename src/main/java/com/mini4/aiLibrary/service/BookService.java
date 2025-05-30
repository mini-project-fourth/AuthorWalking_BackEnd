package com.mini4.aiLibrary.service;

import com.mini4.aiLibrary.domain.Book;
import com.mini4.aiLibrary.dto.BookDto;

public interface BookService {

    public Book insertBook(BookDto.BookPost bookDto);

}
