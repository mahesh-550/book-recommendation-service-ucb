/*package com.book.recommender.service.logic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.book.recommender.service.model.Book;
import com.book.recommender.service.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getFilteredBooks(String search, String type, String branchGenre, Integer rating) {
//        System.out.println(search+" "+type+" "+branchGenre+" "+rating);

        return bookRepository.findAll().stream()
                .filter(book -> (search == null || book.getTitle().contains(search) || book.getAuthor().contains(search)))
                .filter(book -> {

                    List<Book> result = new ArrayList<>();
                    if (type.equalsIgnoreCase("Branch")) {
                        System.out.println("if");
                        return branchGenre == null || book.getBranch().equalsIgnoreCase(branchGenre);
                    } else if (type.equalsIgnoreCase("Genre")) {
                        System.out.println("else if");
                        System.out.println(book.getGenre()+" "+book.getTitle()+" "+book.getAuthor());
                        return branchGenre == null || book.getGenre().equalsIgnoreCase(branchGenre);
                    }
                    return true;
                })
                .filter(book -> rating == null || book.getRating() == rating)
                .collect(Collectors.toList());
    }


}
*/

/*
package com.book.recommender.service.logic;

import java.util.List;
import java.util.stream.Collectors;

import com.book.recommender.service.model.Book;
import com.book.recommender.service.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getFilteredBooks(String search, String type, String branchGenre, Integer rating) {
        return bookRepository.findAll().stream()
                .filter(book -> (search == null ||
                        book.getTitle().toLowerCase().contains(search.toLowerCase()) ||
                        book.getAuthor().toLowerCase().contains(search.toLowerCase())))
                .filter(book -> {
                    if ("Branch".equalsIgnoreCase(type)) {
                        return branchGenre == null || book.getBranch().equalsIgnoreCase(branchGenre);
                    } else if ("Genre".equalsIgnoreCase(type)) {
                        return branchGenre == null || book.getGenre().equalsIgnoreCase(branchGenre);
                    }
                    return true;
                })
                .filter(book -> rating == null || book.getRating()==(rating))
                .collect(Collectors.toList());
    }
}


package com.book.recommender.service.logic;

import java.util.ArrayList;
import java.util.List;
import com.book.recommender.service.model.Book;
import com.book.recommender.service.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getFilteredBooks(String search, String type, String branchGenre, Integer rating) {
        logger.info("Received request to filter books with parameters - search: {}, type: {}, branchGenre: {}, rating: {}",
                search, type, branchGenre, rating);

        List<Book> allBooks = bookRepository.findAll();
        logger.info("Total books retrieved from repository: {}", allBooks.size());

        List<Book> filteredBooks = allBooks.stream()
                .filter(book -> {
                    boolean matchesSearch = (search == null ||
                            book.getTitle().toLowerCase().contains(search.toLowerCase()) ||
                            book.getAuthor().toLowerCase().contains(search.toLowerCase()));
                    logger.debug("Book: {} | Title: {} | Author: {} | Matches search: {}",
                            book.getId(), book.getTitle(), book.getAuthor(), matchesSearch);
                    return matchesSearch;
                })
                .filter(book -> {
                    logger.debug("Checking book: {} with Genre: {} and Branch: {}", book.getId(), book.getGenre(), book.getBranch());
                    boolean matchesType = true;
                    if (type != null) {
                        if (type.equalsIgnoreCase("Branch")) {
                            matchesType = branchGenre == null || book.getBranch().equalsIgnoreCase(branchGenre);
                            logger.debug("Book: {} | Branch: {} | Matches branchGenre: {}",
                                    book.getId(), book.getBranch(), matchesType);
                        } else if (type.equalsIgnoreCase("Genre")) {
                            matchesType = branchGenre == null || book.getGenre().equalsIgnoreCase(branchGenre);
                            logger.debug("Book: {} | Genre: {} | Matches branchGenre: {}",
                                    book.getId(), book.getGenre(), matchesType);
                        }
                    }
                    return matchesType;
                })
                .filter(book -> {
                    boolean matchesRating = rating == null || book.getRating() == (rating);
                    logger.debug("Book: {} | Rating: {} | Matches rating: {}",
                            book.getId(), book.getRating(), matchesRating);
                    return matchesRating;
                })
                .collect(Collectors.toList());

        logger.info("Filtered books count: {}", filteredBooks.size());
        return filteredBooks;
    }
}
*/

package com.book.recommender.service.logic;

/*
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.book.recommender.service.model.Book;
import com.book.recommender.service.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getFilteredBooks(String searchText, String type, String branchOrGenre, Integer rating) {
        // Log the received filter parameters
        System.out.println("Received request to filter books with parameters - searchText: " + searchText
                + ", type: " + type + ", branchOrGenre: " + branchOrGenre + ", rating: " + rating);

        // Fetch all books from the repository
        List<Book> allBooks = bookRepository.findAll();
        System.out.println("Total books retrieved from repository: " + allBooks.size());

        // Filter books based on the criteria provided
        List<Book> filteredBooks = allBooks.stream()
                // Filter by title or author if searchText is provided
                .filter(book -> (searchText == null || book.getTitle().toLowerCase().contains(searchText.toLowerCase())
                        || book.getAuthor().toLowerCase().contains(searchText.toLowerCase())))
                // Filter by branch or genre based on type
                .filter(book -> {
                    if ("Branch".equalsIgnoreCase(type)&&book.getBranch().equalsIgnoreCase(branchOrGenre)) {
//                        return branchOrGenre == null || book.getBranch().equalsIgnoreCase(branchOrGenre);
                        return true;
                    } else if ("Genre".equalsIgnoreCase(type)&&book.getGenre().equalsIgnoreCase(branchOrGenre)) {
//                        System.out.println(book.getTitle()+" "+book.getAuthor()+" "+ book.getGenre());
//                        System.out.println(book.getGenre()+" "+branchOrGenre);
//                        return branchOrGenre == null || book.getGenre().equalsIgnoreCase(branchOrGenre);
                        return true;
                    }
                    return false; // No specific type provided, pass through
                })
                // Filter by rating if provided
                .filter(book -> rating == null || book.getRating()==(rating))
                .collect(Collectors.toList());

        // Log the count of filtered books
        System.out.println("Filtered books count: " + filteredBooks.size());

        return filteredBooks;
    }
}

 */

import com.book.recommender.service.model.Book;
import com.book.recommender.service.model.Feedback;
import com.book.recommender.service.repository.BookRepository;
import com.book.recommender.service.repository.FeedbackRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private FeedbackRepository feedbackRepository;

    public List<Book> findBooksByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    public List<Book> findBooks(String search, String type, String branchGenre, Integer rating) {

        if(search != null){
            Specification<Book> spec = Specification.where(BookSpecification.hasTitleOrAuthor(search));
            // Sort by rating in descending order
            return bookRepository.findAll(spec);
        }else if (type.equalsIgnoreCase("branch")){
            Specification<Book> spec = Specification.where(BookSpecification.hasTitleOrAuthor(search))
                    .and(BookSpecification.hasBranch(branchGenre))
                    .and(BookSpecification.hasRating(rating));
            // Sort by rating in descending order
            return bookRepository.findAll(spec, Sort.by(Sort.Direction.DESC, "rating"));
        }else {
            Specification<Book> spec = Specification.where(BookSpecification.hasTitleOrAuthor(search))
                    .and(BookSpecification.hasGenre(branchGenre))
                    .and(BookSpecification.hasRating(rating));
            // Sort by rating in descending order
            return bookRepository.findAll(spec, Sort.by(Sort.Direction.DESC, "rating"));
        }
    }

    // Method to update rating and store the feedback
    public void updateRatingAndFeedback(String title, String author, Double rating, String description) {
        // Update the rating in the books_table
        Book book = bookRepository.findByTitleAndAuthor(title, author);
        if (book != null) {
            book.setRating(rating);
            bookRepository.save(book);
        }

        // Persist the feedback in the feedback_table
        Feedback feedback = new Feedback();
        feedback.setTitle(title);
        feedback.setAuthor(author);
        feedback.setRating(rating);
        StringBuilder sb = new StringBuilder();
        sb.append("Comment added by : ");
        sb.append(description);
        String desc = sb.toString();
        feedback.setDescription(desc);
        feedbackRepository.save(feedback);
    }

    @Transactional
    public boolean deleteBookByHash(String bookHash) {
        Optional<Book> bookOptional = bookRepository.findByBookHash(bookHash);
        if (bookOptional.isPresent()) {
            bookRepository.delete(bookOptional.get());
            return true;  // Successfully deleted
        }
        return false; // Book not found
    }
}

