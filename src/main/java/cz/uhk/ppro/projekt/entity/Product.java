package cz.uhk.ppro.projekt.entity;

import javax.persistence.*;
import java.sql.Timestamp;

//TODO pred odevzdanim projektu projet vsechny entity a ujistit se, ze hashCode a Equals a ToString obsahuji
// vsechny parametry

@Entity
@Table(name = "products", schema = "cukrarna", catalog = "")
public class Product {
    private long productId;
    private int price;
    private String name;
    private String description;
    private Timestamp added;
    private String pictureUrl;
    private byte archived;
    private ProductCategory productCategoriesByCategoryId;
    private Tag tagsByTagId;

    @Id
    @Column(name = "PRODUCT_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "PRICE", nullable = false)
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Basic
    @Column(name = "NAME", nullable = false, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "DESCRIPTION", nullable = true)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "ADDED", insertable = false, updatable = false, nullable = false)
    @GeneratedValue
    public Timestamp getAdded() {
        return added;
    }

    public void setAdded(Timestamp added) {
        this.added = added;
    }

    @Basic
    @Column(name = "PICTURE_URL", nullable = true, length = 150)
    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    @Basic
    @Column(name = "ARCHIVED", nullable = false)
    public byte getArchived() {
        return archived;
    }

    public void setArchived(byte archived) {
        this.archived = archived;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (productId != product.productId) return false;
        if (price != product.price) return false;
        if (archived != product.archived) return false;
        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        if (description != null ? !description.equals(product.description) : product.description != null) return false;
        if (added != null ? !added.equals(product.added) : product.added != null) return false;
        return pictureUrl != null ? pictureUrl.equals(product.pictureUrl) : product.pictureUrl == null;
    }

    @Override
    public int hashCode() {
        int result = (int) productId;
        result = 31 * result + price;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (added != null ? added.hashCode() : 0);
        result = 31 * result + (pictureUrl != null ? pictureUrl.hashCode() : 0);
        result = 31 * result + (int) archived;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID", referencedColumnName = "CATEGORY_ID")
    public ProductCategory getProductCategoriesByCategoryId() {
        return productCategoriesByCategoryId;
    }

    public void setProductCategoriesByCategoryId(ProductCategory productCategoriesByCategoryId) {
        this.productCategoriesByCategoryId = productCategoriesByCategoryId;
    }

    @ManyToOne
    @JoinColumn(name = "TAG_ID", referencedColumnName = "TAG_ID")
    public Tag getTagsByTagId() {
        return tagsByTagId;
    }

    public void setTagsByTagId(Tag tagsByTagId) {
        this.tagsByTagId = tagsByTagId;
    }
}
