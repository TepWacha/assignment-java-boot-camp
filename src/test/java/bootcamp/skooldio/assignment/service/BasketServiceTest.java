package bootcamp.skooldio.assignment.service;

import bootcamp.skooldio.assignment.model.*;
import bootcamp.skooldio.assignment.repository.BasketRepository;
import bootcamp.skooldio.assignment.repository.CheckoutRepository;
import bootcamp.skooldio.assignment.repository.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class BasketServiceTest {

    @InjectMocks
    private BasketService basketService;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private BasketRepository basketRepository;
    @Mock
    private CheckoutRepository checkoutRepository;

    @Test
    void getBasketByUserId() {
        doReturn(Collections.singletonList(new ProductEntity().setProductId(1).setProductPrice(999.00).setProductName("Nike 1")))
                .when(productRepository).findByProductId(1);
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
        BasketResponse response = basketService.getBasketByUserId(1);
        Assertions.assertEquals(1, response.getBasketId());
        Assertions.assertEquals(1, response.getProducts().size());
        Assertions.assertEquals(1, response.getProducts().get(0).getProductId());
        Assertions.assertEquals("Nike 1", response.getProducts().get(0).getProductName());
        Assertions.assertEquals(3, response.getProducts().get(0).getNumberOfProduct());
        Assertions.assertEquals(999.00, response.getProducts().get(0).getProductPrice());
    }

    @Test
    void postBasketCheckout() throws JsonProcessingException {
        doReturn(Collections.singletonList(new ProductEntity().setProductId(1).setProductPrice(999.00).setProductName("Nike 1")))
                .when(productRepository).findByProductId(1);
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
                .when(basketRepository).findByBasketId(1);
        doReturn(new CheckoutEntity().setCheckoutId(1)).when(checkoutRepository).save(any(CheckoutEntity.class));
        PostBasketCheckoutResponse response = basketService.postBasketCheckout(new PostBasketCheckoutRequest().setUserId(1).setBasketId(1));
        Assertions.assertEquals(1, response.getCheckoutId());
    }

}
