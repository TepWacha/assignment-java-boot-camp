package bootcamp.skooldio.assignment.repository;

import bootcamp.skooldio.assignment.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity,Integer> {
    List<ProductEntity> findByProductNameContaining(String productName);
}