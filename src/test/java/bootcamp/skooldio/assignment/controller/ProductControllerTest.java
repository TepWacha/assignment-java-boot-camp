package bootcamp.skooldio.assignment.controller;

import bootcamp.skooldio.assignment.model.PostProductAddToBasketRequest;
import bootcamp.skooldio.assignment.model.PostProductAddToBasketResponse;
import bootcamp.skooldio.assignment.model.ProductResponse;
import bootcamp.skooldio.assignment.repository.BasketRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;
    @MockBean
    private BasketRepository basketRepository;

    @Test
    void getProductByKeyword() {
        ProductResponse response = testRestTemplate.getForObject("/v1/product/Nike", ProductResponse.class);
        Assertions.assertEquals(10, response.getProducts().size());
    }

    @Test
    void postProductAddToBasket() {
        PostProductAddToBasketResponse response = testRestTemplate.postForObject(
                "/v1/product/add-to-basket",
                new PostProductAddToBasketRequest().setProductId(1).setNumberOfProduct(3).setUserId(1),
                PostProductAddToBasketResponse.class);
        Assertions.assertEquals("success", response.getStatus());
    }

}
