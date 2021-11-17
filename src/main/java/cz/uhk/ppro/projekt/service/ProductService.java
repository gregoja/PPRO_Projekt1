package cz.uhk.ppro.projekt.service;

import cz.uhk.ppro.projekt.entity.Product;
import cz.uhk.ppro.projekt.repository.ProductRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Optional<Product> getById(long id){
        return repository.findById(id);
    }

    public List<Product> getByCriteria(List<String> desiredCategories, List<String> desiredTags, Pageable paging){
        return repository.findAllProductsByCriteria(desiredCategories, desiredTags, paging);
    }
}