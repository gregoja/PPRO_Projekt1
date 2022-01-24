package cz.uhk.ppro.projekt.web;

import cz.uhk.ppro.projekt.entity.Product;
import cz.uhk.ppro.projekt.entity.Review;
import cz.uhk.ppro.projekt.entity.User;
import cz.uhk.ppro.projekt.model.ReviewRequest;
import cz.uhk.ppro.projekt.service.ProductService;
import cz.uhk.ppro.projekt.service.ReviewService;
import cz.uhk.ppro.projekt.service.UserService;
import cz.uhk.ppro.projekt.web.errors.ResourceNotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class ProductController {
    private final ProductService productService;
    private final ReviewService reviewService;
    private final UserService userService;

    public ProductController(ProductService productService, ReviewService reviewService, UserService userService) {
        this.productService = productService;
        this.reviewService = reviewService;
        this.userService = userService;
    }

    /**
     *  /products?categories=Donut&tags=Sleva,Novinka&pageNo=1
     *
     */
    @GetMapping("/products")
    @ResponseBody
    public List<Product> getProductsByCriteria(@RequestParam(value = "categories", required = false) String categories,
                                               @RequestParam(value = "tags", required = false) String tags,
                                               @RequestParam(value = "pageNo",required = false, defaultValue = "0") int pageNo){
        List<String> desiredCategories = new ArrayList<>();
        List<String> desiredTags = new ArrayList<>();
        if(tags != null){
            desiredTags = Arrays.asList(tags.split(","));
        }
        if(categories != null){
            desiredCategories = Arrays.asList(categories.split(","));
        }

        return productService.getByCriteria(desiredCategories, desiredTags, PageRequest.of(pageNo,9));
    }

    @GetMapping(value = "/products/{id}")
    @ResponseBody
    public Product getProductById(@PathVariable int id){
        return productService.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @PostMapping("saveReview")
    @ResponseBody
    public void saveReview(@RequestBody ReviewRequest reviewRequest, HttpSession session){
        User user = userService.getUserById((Integer) session.getAttribute("userId"));
        Product product = productService.getById(reviewRequest.getProductId());
        Review oldReview = reviewService.findByProductAndUser(product, user);
        if (oldReview != null) {
            reviewService.deleteReview(oldReview);
        }

        Review review = new Review();
        review.setReview(reviewRequest.getText());
        review.setStars(reviewRequest.getStars());
        review.setProductsByProductId(product);
        review.setUsersByUserId(user);
        reviewService.save(review);
    }

    @PostMapping("deleteReview")
    @ResponseBody
    public void deleteReview(@RequestBody Integer productId, HttpSession session) {
        User user = userService.getUserById((Integer) session.getAttribute("userId"));
        Product product = productService.getById(productId);
        Review review = reviewService.findByProductAndUser(product, user);
        if (review != null) {
            reviewService.deleteReview(review);
        }
    }

    @GetMapping("/product")
    public String getProduct(@RequestParam(value = "productId") int productId, Model model, HttpSession session){
        Product product = productService.findById(productId).orElseThrow(ResourceNotFoundException::new);
        model.addAttribute("product",product);
        List<Review> reviews;
        reviews = reviewService.findByProduct(productService.getById(productId));

        // presunuti review prihlaseneho uzivatele na zacatek
        if (session.getAttribute("userId") != null) {
            Review loginUserReview = reviewService.findByProductAndUser(product,
                    userService.getUserById((Integer) session.getAttribute("userId")));
            if (loginUserReview != null) {
                int reviewPos = reviews.indexOf(loginUserReview);
                reviews.remove(reviewPos);
                reviews.add(0, loginUserReview);
            }
        }

        model.addAttribute("reviews",reviews);
        return "product";
    }

    @GetMapping("/reviews")
    @ResponseBody
    public ResponseEntity<List<Review>> getReviews(@RequestParam(value = "productId") int productId){
        List<Review> reviews;
        reviews = reviewService.findByProduct(productService.getById(productId));

        return ResponseEntity.ok(reviews);
    }
}