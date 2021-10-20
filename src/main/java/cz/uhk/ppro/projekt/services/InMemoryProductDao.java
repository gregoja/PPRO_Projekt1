package cz.uhk.ppro.projekt.services;


import cz.uhk.ppro.projekt.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class InMemoryProductDao implements ProductDao{
    private List<Product> products = new ArrayList<Product>();

    @Override
    public List<Product> getProducts() {
        return Collections.unmodifiableList(products);
    }

    @Override
    public void add(Product p) {
        products.add(p);
        int maxId = 0;
        // vygenerovat nove id (bezne dela databaze)
        for (Product inz : products) {
            if (inz.getId() > maxId) maxId = inz.getId();
        }
        p.setId(maxId + 1);
        Collections.sort(products,new Comparator<Product>() {

            public int compare(Product o1, Product o2) {
                return o1.getAdded().compareTo(o2.getAdded());
            }

        });
    }

    @Override
    public void delete(Product p) {
        products.remove(p);
    }

    @Override
    public void delete(int id) {
        products.remove(getById(id));
    }

    @Override
    public Product getById(int id) {
        for(Product p : products) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    @Override
    public void edit(Product p) {
        for(int j = 0; j < products.size(); j++) {
            if (p.getId() == products.get(j).getId()) {
                products.set(j, p);
                return;
            }
        }
    }
}
