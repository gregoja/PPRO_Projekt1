package cz.uhk.ppro.projekt.repository;

import cz.uhk.ppro.projekt.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom {

}