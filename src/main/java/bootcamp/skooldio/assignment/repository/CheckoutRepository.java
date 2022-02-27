package bootcamp.skooldio.assignment.repository;

import bootcamp.skooldio.assignment.model.CheckoutEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CheckoutRepository extends JpaRepository<CheckoutEntity, Integer> {

}