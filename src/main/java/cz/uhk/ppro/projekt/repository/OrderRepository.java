package cz.uhk.ppro.projekt.repository;

import cz.uhk.ppro.projekt.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Integer> {
}
