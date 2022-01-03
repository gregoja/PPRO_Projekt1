package cz.uhk.ppro.projekt.service;

import cz.uhk.ppro.projekt.entity.Product;
import cz.uhk.ppro.projekt.entity.Review;
import cz.uhk.ppro.projekt.entity.User;
import cz.uhk.ppro.projekt.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewService (ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public Review save (Review review) {
        reviewRepository.save(review);
        return review;
    }

    public List<Review> findByProduct(Product product) {
        return reviewRepository.findByProductsByProductId(product);
    }

    public Review findByProductAndUser(Product product, User user) {
        return reviewRepository.findByProductsByProductIdAndAndUsersByUserId(product, user);
    }

    public void deleteReview(Review review) {
        reviewRepository.delete(review);
    }
}
