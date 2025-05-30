package com.mini4.aiLibrary.service;

import com.mini4.aiLibrary.domain.Book;
import com.mini4.aiLibrary.dto.BookDto;
import com.mini4.aiLibrary.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book insertBook(BookDto.BookPost bookDto) {
        Book book = bookDto.toEntity();
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Long id, BookDto.BookPut bookDto) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("수정할 책이 존재하지 않습니다."));
        book.setTitle(bookDto.getTitle());
        book.setContents(bookDto.getContents());
        book.setCover(bookDto.getCover());
        return bookRepository.save(book);
    }

    @Override
    public List<Book> findBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book findBook(Long id){
        return bookRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("책을 찾을 수 없습니다.")
        );
    }
  
    @Override
    public void deleteBook(Long id){
        if (!bookRepository.existsById(id)){
            throw new IllegalArgumentException("해당 책이 존재하지 않습니다. id=" + id);
        }
        bookRepository.deleteById(id);
    }

}
