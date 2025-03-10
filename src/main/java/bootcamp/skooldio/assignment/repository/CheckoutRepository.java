package bootcamp.skooldio.assignment.repository;

import bootcamp.skooldio.assignment.model.CheckoutEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CheckoutRepository extends JpaRepository<CheckoutEntity, Integer> {
    List<CheckoutEntity> findByCheckoutId(Integer checkoutId);
}