package com.book.recommender.service.logic;

import com.book.recommender.service.model.Feedback;
import com.book.recommender.service.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {
    @Autowired
    private FeedbackRepository feedbackRepository;

    public List<Feedback> getAllReviews() {
        return feedbackRepository.findAll();  // Fetch all reviews from the database
    }
}
