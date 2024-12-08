package com.book.recommender.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.book.recommender.service.model.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
	// Custom queries if needed
}
