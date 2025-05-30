package com.mini4.aiLibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mini4.aiLibrary.domain.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>  {
}
