package cz.uhk.ppro.projekt.repository;

import cz.uhk.ppro.projekt.entity.OrderRow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRowRepository extends JpaRepository<OrderRow,Integer> {
}
