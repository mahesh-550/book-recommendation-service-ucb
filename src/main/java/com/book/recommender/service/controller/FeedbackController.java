package com.book.recommender.service.controller;

import com.book.recommender.service.logic.FeedbackService;
import com.book.recommender.service.model.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("/api/getReviews")
    public List<Feedback> getAllReviews() {
        return feedbackService.getAllReviews();  // Returns a list of reviews
    }
}
