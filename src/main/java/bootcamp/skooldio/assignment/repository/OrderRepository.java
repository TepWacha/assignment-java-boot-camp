package bootcamp.skooldio.assignment.repository;

import bootcamp.skooldio.assignment.model.CheckoutEntity;
import bootcamp.skooldio.assignment.model.OrderEntity;
import org.hibernate.criterion.Order;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {

}