package bootcamp.skooldio.assignment.repository;

import bootcamp.skooldio.assignment.model.BasketEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
class BasketRepositoryTest {

    @Autowired
    private BasketRepository basketRepository;

    @Test
    void findByUserIdAndBasketActive() {
        basketRepository.save(new BasketEntity().setUserId(1).setProductId(1).setNumberOfProduct(3).setRecordActive(true).setBasketActive(true));
        basketRepository.save(new BasketEntity().setUserId(1).setProductId(2).setNumberOfProduct(3).setRecordActive(true).setBasketActive(false));
        basketRepository.save(new BasketEntity().setUserId(2).setProductId(3).setNumberOfProduct(3).setRecordActive(true).setBasketActive(true));
        List<BasketEntity> response = basketRepository.findByUserIdAndBasketActive(1, true);
        Assertions.assertEquals(1, response.size());
        Assertions.assertEquals(1, response.get(0).getProductId());
    }
}
