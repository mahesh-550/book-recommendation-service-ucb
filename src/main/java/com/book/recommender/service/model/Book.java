package com.book.recommender.service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "books_table")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;
	private String author;
	private String genre;
	private String branch;

	private Double rating = 0.0;

	@Column(unique = true, nullable = false, length = 8)
	private String bookHash;

	// Getters and Setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public String getBookHash() {
		return bookHash;
	}

	// Generate a unique 8-digit bookHash based on title and author
	@PrePersist
	private void generateBookHash() {
		if (bookHash == null || bookHash.isEmpty()) {
			int hash = Math.abs((title + author).hashCode());
			bookHash = String.format("%08d", hash % 100000000); // 8 digits
		}
	}

	@Override
	public String toString() {
		return "Book" + "[" + title + "," + author + "," + genre + "," + branch + ", bookHash=" + bookHash + "]";
	}
}
