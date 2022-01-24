package cz.uhk.ppro.projekt.model;

public class ReviewRequest {
    private String text;
    private int stars;
    private int productId;

    @Override
    public String toString() {
        return "ReviewRequest{" +
                "text='" + text + '\'' +
                ", stars=" + stars +
                ", product=" + productId +
                '}';
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
