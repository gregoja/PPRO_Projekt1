package cz.uhk.ppro.projekt.repository;

import cz.uhk.ppro.projekt.entity.Product;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductRepositoryCustom {

    List<Product> findAllProductsByCriteria(List<String> desiredCategories, List<String> desiredTags, Pageable paging);
}