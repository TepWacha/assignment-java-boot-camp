package bootcamp.skooldio.assignment.repository;

import bootcamp.skooldio.assignment.model.BasketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BasketRepository extends JpaRepository<BasketEntity, Integer> {
    List<BasketEntity> findByUserIdAndBasketActive(Integer userId, Boolean basketActive);
    List<BasketEntity> findByUserIdAndBasketActiveAndRecordActive(Integer userId, Boolean basketActive, Boolean recordActive);
    List<BasketEntity> findByBasketId(Integer basketId);
}