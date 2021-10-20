package cz.uhk.ppro.projekt.services;

import cz.uhk.ppro.projekt.model.Product;

import java.util.List;

public interface ProductDao {
    public List<Product> getProducts();
    public void add(Product p);
    public void delete(Product p);
    public void delete(int id);
    public void edit(Product p);
    public Product getById(int id);
}
