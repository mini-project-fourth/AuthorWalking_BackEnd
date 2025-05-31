package com.mini4.aiLibrary.service;

import com.mini4.aiLibrary.domain.Book;
import com.mini4.aiLibrary.domain.HashTag;
import com.mini4.aiLibrary.dto.BookRequestDto;
import com.mini4.aiLibrary.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book insertBook(BookRequestDto bookDto) {
        Book book = Book.builder()
                .title(bookDto.getTitle())
                .author(bookDto.getAuthor())
                .contents(bookDto.getContents())
                .cover(bookDto.getCover())
                .build();

        List<HashTag> tags = bookDto.getHashTags().stream()
                .map(tag -> HashTag.builder()
                        .tagName(tag)
                        .book(book)
                        .build())
                .collect(Collectors.toList());

        book.setHashTags(tags);
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Long id, BookRequestDto bookDto) {
        Book book = findBook(id);
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setContents(bookDto.getContents());
        book.setCover(bookDto.getCover());

        List<HashTag> tags = bookDto.getHashTags().stream()
                .map(tag -> HashTag.builder()
                        .tagName(tag)
                        .book(book)
                        .build())
                .collect(Collectors.toList());

        book.setHashTags(tags);
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
