package com.book.recommender.service.repository;
import com.book.recommender.service.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {
    Book findByTitleAndAuthor(String title, String author);
    // JpaSpecificationExecutor provides Specification-based methods for dynamic querying
    List<Book> findByAuthor(String author);
    Optional<Book> findByBookHash(String bookHash);
    //void deleteByBookHash(String bookHash);
}
