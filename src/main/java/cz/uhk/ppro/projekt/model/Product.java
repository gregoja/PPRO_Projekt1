package cz.uhk.ppro.projekt.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class Product {
    /*
     PRODUCT_ID` int(11) NOT NULL AUTO_INCREMENT,
     `PRICE` int(11) NOT NULL,
     `NAME` varchar(50) NOT NULL,
     `DESCRIPTION` text DEFAULT NULL,
     `ADDED` timestamp NOT NULL DEFAULT current_timestamp(),
     `CATEGORY_ID` int(11) DEFAULT NULL,
     `TAG_ID` int(11) DEFAULT NULL,
     `PICTURE_URL` varchar(150) DEFAULT NULL,
     `ARCHIVED` TinyInt(1) NOT NULL DEFAULT 0,
     PRIMARY KEY (`PRODUCT_ID`),
     KEY `TAG_ID` (`TAG_ID`),
     KEY `CATEGORY_ID` (`CATEGORY_ID`)
    * */

    // na tagy kašleme. Minimálně zatím. mohou být null
    // stejmě tak kategorie
    private int id;
    private BigDecimal price;
    private String name;
    private String description;
    private Date added;
    private String pictureUrl;
    private boolean archived;

    public Product() {
        added = new Date();
    }

    public Product(BigDecimal price, String name, String description, String pictureUrl) {
        this.price = price;
        this.name = name;
        this.description = description;
        this.added = new Date();
        this.pictureUrl = pictureUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && archived == product.archived && Objects.equals(price, product.price) && Objects.equals(name, product.name) && Objects.equals(description, product.description) && Objects.equals(added, product.added) && Objects.equals(pictureUrl, product.pictureUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, name, description, added, pictureUrl, archived);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", added=" + added +
                ", pictureUrl='" + pictureUrl + '\'' +
                ", archived=" + archived +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getAdded() {
        return added;
    }

    public void setAdded(Date added) {
        this.added = added;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }
}