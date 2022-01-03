package cz.uhk.ppro.projekt.repository;

import cz.uhk.ppro.projekt.entity.Product;
import cz.uhk.ppro.projekt.entity.Review;
import cz.uhk.ppro.projekt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

    List<Review> findByProductsByProductId(Product product);

    Review findByProductsByProductIdAndAndUsersByUserId(Product product, User user);
}
