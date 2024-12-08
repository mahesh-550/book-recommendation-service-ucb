package com.book.recommender.service.logic;

import com.book.recommender.service.model.Book;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class BookSpecification {

    public static Specification<Book> hasTitleOrAuthor(String search) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.hasText(search)) {
                String likePattern = "%" + search + "%";
                return criteriaBuilder.or(
                        criteriaBuilder.like(root.get("title"), likePattern),
                        criteriaBuilder.like(root.get("author"), likePattern)
                );
            }
            return criteriaBuilder.conjunction(); // No-op if search is null or empty
        };
    }

    public static Specification<Book> hasBranch(String branch) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.hasText(branch)) {
                return criteriaBuilder.equal(root.get("branch"), branch);
            }
            return criteriaBuilder.conjunction();
        };
    }

    public static Specification<Book> hasGenre(String genre) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.hasText(genre)) {
                return criteriaBuilder.equal(root.get("genre"), genre);
            }
            return criteriaBuilder.conjunction();
        };
    }

    public static Specification<Book> hasRating(Integer rating) {
        return (root, query, criteriaBuilder) -> {
            if (rating != null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("rating"), rating);
            }
            return criteriaBuilder.conjunction();
        };
    }
}

