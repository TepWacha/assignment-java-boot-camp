package bootcamp.skooldio.assignment.controller;

import bootcamp.skooldio.assignment.model.ProductResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void getProductByKeyword() {
        ProductResponse response = testRestTemplate.getForObject("/v1/product/Nike", ProductResponse.class);
        Assertions.assertEquals(10, response.getProducts().size());
    }

}
