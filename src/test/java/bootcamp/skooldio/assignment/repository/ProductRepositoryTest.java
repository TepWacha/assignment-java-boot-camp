package bootcamp.skooldio.assignment.repository;

import bootcamp.skooldio.assignment.model.ProductEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void findByProductNameContaining() {
        List<ProductEntity> response = productRepository.findByProductNameContaining("Nike");
        Assertions.assertEquals(10, response.size());
    }
}
