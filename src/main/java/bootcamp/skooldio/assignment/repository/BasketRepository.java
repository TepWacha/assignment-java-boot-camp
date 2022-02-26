package bootcamp.skooldio.assignment.repository;

import bootcamp.skooldio.assignment.model.BasketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BasketRepository extends JpaRepository<BasketEntity, Integer> {
    List<BasketEntity> findByUserIdAndBasketActive(Integer userId, Boolean basketActive);
    List<BasketEntity> findByUserIdAndBasketActiveAndRecordActive(Integer userId, Boolean basketActive, Boolean recordActive);
}