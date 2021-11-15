package cz.uhk.ppro.projekt.entity;

import javax.persistence.*;

@Entity
@Table(name = "product_categories", schema = "cukrarna", catalog = "")
public class ProductCategory {
    private long categoryId;
    private String name;

    @Id
    @Column(name = "CATEGORY_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    @Basic
    @Column(name = "NAME", nullable = false, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductCategory that = (ProductCategory) o;

        if (categoryId != that.categoryId) return false;
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        int result = (int) categoryId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
