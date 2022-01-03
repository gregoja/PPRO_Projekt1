package cz.uhk.ppro.projekt.entity;

import cz.uhk.ppro.projekt.repository.ProductRepository;

import javax.persistence.*;

@Entity
@Table(name = "reviews", schema = "cukrarna", catalog = "")
public class Review {
    private int reviewId;
    private String review;
    private int stars;
    private User usersByUserId;
    private Product productsByProductId;

    @Id
    @Column(name = "REVIEW_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    @Basic
    @Column(name = "REVIEW", nullable = false, length = -1)
    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    @Basic
    @Column(name = "STARS", nullable = false)
    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Review review1 = (Review) o;

        if (reviewId != review1.reviewId) return false;
        if (stars != review1.stars) return false;
        if (review != null ? !review.equals(review1.review) : review1.review != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = reviewId;
        result = 31 * result + (review != null ? review.hashCode() : 0);
        result = 31 * result + stars;
        return result;
    }

    @ManyToOne
    @JoinColumn(name="productId")
    public Product getProductsByProductId() {
        return productsByProductId;
    }

    public void setProductsByProductId(Product productsByProductId) {
        this.productsByProductId = productsByProductId;
    }

    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID", nullable = false)
    public User getUsersByUserId() {
        return usersByUserId;
    }

    public void setUsersByUserId(User usersByUserId) {
        this.usersByUserId = usersByUserId;
    }
}
