package bootcamp.skooldio.assignment.controller;

import bootcamp.skooldio.assignment.model.BasketEntity;
import bootcamp.skooldio.assignment.model.BasketResponse;
import bootcamp.skooldio.assignment.model.PostBasketCheckoutRequest;
import bootcamp.skooldio.assignment.model.PostBasketCheckoutResponse;
import bootcamp.skooldio.assignment.repository.BasketRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;

import javax.transaction.Transactional;
import java.util.Arrays;

import static org.mockito.Mockito.doReturn;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BasketControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;
    @MockBean
    private BasketRepository basketRepository;


    @Test
    void getBasketByUserId() {
        doReturn(Arrays.asList(
                new BasketEntity()
                        .setRecordId(1)
                        .setNumberOfProduct(2)
                        .setBasketId(1)
                        .setBasketActive(true)
                        .setRecordActive(true)
                        .setProductId(1)
                        .setUserId(1),
                new BasketEntity()
                        .setRecordId(2)
                        .setNumberOfProduct(1)
                        .setBasketId(1)
                        .setBasketActive(true)
                        .setRecordActive(true)
                        .setProductId(1)
                        .setUserId(1)))
                .when(basketRepository).findByUserIdAndBasketActiveAndRecordActive(1,true, true);
        BasketResponse response = testRestTemplate.getForObject("/v1/basket/1", BasketResponse.class);
        Assertions.assertEquals(1, response.getBasketId());
        Assertions.assertEquals(1, response.getProducts().size());
        Assertions.assertEquals(1, response.getProducts().get(0).getProductId());
        Assertions.assertEquals("Nike 1", response.getProducts().get(0).getProductName());
        Assertions.assertEquals(3, response.getProducts().get(0).getNumberOfProduct());
        Assertions.assertEquals(999.00, response.getProducts().get(0).getProductPrice());
    }

    @Test
    void postBasketCheckout() {
        doReturn(Arrays.asList(
                new BasketEntity()
                        .setRecordId(1)
                        .setNumberOfProduct(2)
                        .setBasketId(1)
                        .setBasketActive(true)
                        .setRecordActive(true)
                        .setProductId(1)
                        .setUserId(1),
                new BasketEntity()
                        .setRecordId(2)
                        .setNumberOfProduct(1)
                        .setBasketId(1)
                        .setBasketActive(true)
                        .setRecordActive(true)
                        .setProductId(1)
                        .setUserId(1)))
                .when(basketRepository).findByUserIdAndBasketActiveAndRecordActive(1,true, true);
        PostBasketCheckoutResponse response = testRestTemplate.postForObject(
                "/v1/basket/checkout",
                new PostBasketCheckoutRequest()
                        .setBasketId(1)
                        .setUserId(1),
                PostBasketCheckoutResponse.class);
        Assertions.assertEquals(1, response.getCheckoutId());
    }
}
