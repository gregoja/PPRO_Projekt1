package cz.uhk.ppro.projekt.web;

import cz.uhk.ppro.projekt.entity.Product;
import cz.uhk.ppro.projekt.entity.Review;
import cz.uhk.ppro.projekt.entity.User;
import cz.uhk.ppro.projekt.model.ReviewRequest;
import cz.uhk.ppro.projekt.repository.ProductRepository;
import cz.uhk.ppro.projekt.service.PasswordAuthentication;
import cz.uhk.ppro.projekt.service.ReviewService;
import cz.uhk.ppro.projekt.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

@SpringBootTest
@Transactional
public class ProductControllerTest {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductController productController;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private HttpSession session;

    @Autowired
    private PasswordAuthentication passwordAuthentication;

    @Test
    void saveReviewTest () {
        User user = createUser();
        session.setAttribute("userId", user.getUserId());
        Product product = createProduct();
        productRepository.save(product);
        ReviewRequest reviewRequest = createReview(product.getProductId(), 4, "Text Review");
        productController.saveReview(reviewRequest, session);
    }

    @Test
    void saveReviewOverReviewBySameUser() {
        User user = createUser();
        session.setAttribute("userId", user.getUserId());
        Product product = createProduct();
        productRepository.save(product);

        ReviewRequest reviewRequest = createReview(product.getProductId(), 4, "Text Review");
        productController.saveReview(reviewRequest, session);
        Review oldReview = reviewService.findByProductAndUser(product, user);

        reviewRequest = createReview(product.getProductId(), 3, "Next review");
        productController.saveReview(reviewRequest, session);
        Review newReview = reviewService.findByProductAndUser(product, user);

        Assertions.assertNotEquals(newReview.getStars(), oldReview.getStars());
        Assertions.assertNotEquals(newReview.getReview(), oldReview.getReview());
        Assertions.assertEquals(newReview.getStars(), 3);
        Assertions.assertEquals(newReview.getReview(), "Next review");
    }

    private User createUser() {
       return userService.createUser("username", passwordAuthentication.hash("password".toCharArray()));
    }

    private ReviewRequest createReview(int id, int stars, String text) {
        ReviewRequest reviewRequest = new ReviewRequest();
        reviewRequest.setProductId(id);
        reviewRequest.setStars(stars);
        reviewRequest.setText(text);
        return reviewRequest;
    }

    private Product createProduct() {
        Product product = new Product();
        product.setProductId(1);
        product.setPrice(130);
        product.setDescription("Malinový");
        product.setName("Malinak");
        return product;
    }

    private Product createProduct(int id, int price, String desc, String name) {
        Product product = new Product();
        product.setProductId(id);
        product.setPrice(price);
        product.setDescription(desc);
        product.setName(name);
        return product;
    }
}
