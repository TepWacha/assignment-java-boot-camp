package bootcamp.skooldio.assignment.controller;

import bootcamp.skooldio.assignment.model.*;
import bootcamp.skooldio.assignment.repository.BasketRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BasketControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private BasketRepository basketRepository;

    @Test
    void getBasketByUserId() {
        basketRepository.save(new BasketEntity()
                .setNumberOfProduct(2)
                .setBasketId(1)
                .setBasketActive(true)
                .setRecordActive(true)
                .setProductId(1)
                .setUserId(1));
        basketRepository.save(new BasketEntity()
                .setNumberOfProduct(1)
                .setBasketId(1)
                .setBasketActive(true)
                .setRecordActive(true)
                .setProductId(1)
                .setUserId(1));
        BasketResponse response = testRestTemplate.getForObject("/v1/basket/1", BasketResponse.class);
        Assertions.assertEquals(1, response.getBasketId());
        Assertions.assertEquals(1, response.getProducts().size());
        Assertions.assertEquals(1, response.getProducts().get(0).getProductId());
        Assertions.assertEquals("Nike 1", response.getProducts().get(0).getProductName());
        Assertions.assertEquals(3, response.getProducts().get(0).getNumberOfProduct());
        Assertions.assertEquals(999.00, response.getProducts().get(0).getProductPrice());
    }

}
