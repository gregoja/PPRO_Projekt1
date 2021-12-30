package cz.uhk.ppro.projekt.repository;

import cz.uhk.ppro.projekt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
