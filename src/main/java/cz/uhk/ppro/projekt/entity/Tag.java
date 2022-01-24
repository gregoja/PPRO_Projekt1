package cz.uhk.ppro.projekt.entity;

import javax.persistence.*;

@Entity
@Table(name = "tags", schema = "cukrarna", catalog = "")
public class Tag {
    private int tagId;
    private String name;
    private String color;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TAG_ID", nullable = false)
    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    @Basic
    @Column(name = "TAGNAME", nullable = false, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String tagname) {
        this.name = tagname;
    }

    @Basic
    @Column(name = "COLOR", nullable = false, length = 50)
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tag tag = (Tag) o;

        if (tagId != tag.tagId) return false;
        if (name != null ? !name.equals(tag.name) : tag.name != null) return false;
        if (color != null ? !color.equals(tag.color) : tag.color != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tagId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (color != null ? color.hashCode() : 0);
        return result;
    }
}
