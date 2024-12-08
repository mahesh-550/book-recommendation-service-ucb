package com.book.recommender.service.controller;

import com.book.recommender.service.dto.RatingRequest;
import com.book.recommender.service.logic.BookService;
import com.book.recommender.service.model.Book;
import com.book.recommender.service.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("api/books")
    public List<Book> getBooks(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String branchGenre,
            @RequestParam(required = false) Integer rating) {
        return bookService.findBooks(search, type, branchGenre, rating);
    }

    @PostMapping("api/updateRating")
    public String updateRating(@RequestBody RatingRequest ratingRequest) {
        try {
            bookService.updateRatingAndFeedback(
                    ratingRequest.getTitle(),
                    ratingRequest.getAuthor(),
                    ratingRequest.getRating(),
                    ratingRequest.getDescription()
            );
            return "Rating and feedback updated successfully!";
        } catch (Exception e) {
            return "Failed to update rating and feedback!";
        }
    }

    @PostMapping("api/addBook")
    public ResponseEntity<String> addNewBook(@RequestBody Book book){
        try{
            bookRepository.save(book);
            return ResponseEntity.status(HttpStatus.CREATED).body("Added book successfully!");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding book" + e.getMessage());
        }
    }

    // Endpoint to filter books by author
    @GetMapping("api/book/filterByAuthor")
    public ResponseEntity<?> filterBooksByAuthor(@RequestParam String author) {
        List<Book> books = bookService.findBooksByAuthor(author);
        if (books.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"message\":\"Book Not Found\"}");
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    // Endpoint to delete a book by bookHash
    @DeleteMapping("api/book/delete")
    public ResponseEntity<String> deleteBook(@RequestParam("bookHash") String bookHash) {
        boolean isDeleted = bookService.deleteBookByHash(bookHash);

        if (isDeleted) {
            return ResponseEntity.ok("Book successfully deleted.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found with the provided hash.");
        }
    }

    @GetMapping("/api/books/fetch")
    public List<Book> getAllBooks() {
        // Fetch books dynamically from the database
        return bookRepository.findAll();
    }
}
