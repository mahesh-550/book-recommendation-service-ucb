package com.book.recommender.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.book.recommender.service.repository.BookRepository;
import com.book.recommender.service.repository.FeedbackRepository;
import com.book.recommender.service.repository.UserRepository;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"com.book.recommender.service.controller","com.book.recommender.service.logic"})
public class BookRecommendationServiceApplication{
	
	@Autowired
	BookRepository bookRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
	FeedbackRepository recommendationRepository;


	public static void main(String[] args) {
		SpringApplication.run(BookRecommendationServiceApplication.class, args);
	}

    /*
	@Override
    public void run(String... args) throws Exception {
        // Create sample books
        Book book1 = new Book();
        book1.setTitle("Effective Java");
        book1.setAuthor("Joshua Bloch");
        book1.setGenre("Programming");

        Book book2 = new Book();
        book2.setTitle("Clean Code");
        book2.setAuthor("Robert C. Martin");
        book2.setGenre("Programming");

       bookRepository.save(book1);
       bookRepository.save(book2);

        // Print all books to verify
        System.out.println("Books in the database:");
        Optional<List<Book>> books = bookRepository.findByGenre("Programming");
        System.out.print(books);

        //Enter users
        User user1 = new User();
        user1.setUsername("sasi");
        user1.setPassword("hello");
        user1.setEmail("kalyan@com");
        user1.setMobile("7075873532");
        user1.setFirstname("k");
        user1.setLastname("kalyan");

        User user2 = new User();
        user2.setUsername("sasikalyan");
        user2.setPassword("hello");
        user2.setEmail("ksasi@gmai.com");
        user2.setMobile("12345");
        user2.setFirstname("k");
        user2.setLastname("sasi");

        userRepository.save(user1);
        userRepository.save(user2);

        Recommendation recommendation1 = new Recommendation();
        recommendation1.setBook(book1);
        recommendation1.setRating(3L);
        recommendation1.setUser(user1);

        Recommendation recommendation2 = new Recommendation();
        recommendation2.setUser(user2);
        recommendation2.setBook(book2);
        recommendation2.setRating(4L);

        recommendationRepository.save(recommendation1);
        recommendationRepository.save(recommendation2);
    }*/

}
