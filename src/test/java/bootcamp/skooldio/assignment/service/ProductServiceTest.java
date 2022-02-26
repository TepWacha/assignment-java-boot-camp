package bootcamp.skooldio.assignment.service;

import bootcamp.skooldio.assignment.model.BasketEntity;
import bootcamp.skooldio.assignment.model.PostProductAddToBasketRequest;
import bootcamp.skooldio.assignment.model.ProductEntity;
import bootcamp.skooldio.assignment.model.ProductResponse;
import bootcamp.skooldio.assignment.repository.BasketRepository;
import bootcamp.skooldio.assignment.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static reactor.core.publisher.Mono.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @InjectMocks
    private ProductService productService;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private BasketRepository basketRepository;
    @Captor
    private ArgumentCaptor<BasketEntity> basketEntityCaptor;
    
    @Test
    void getProductByKeyword() {
        List<ProductEntity> productEntityList = new ArrayList<>();
        ProductEntity nike1 = new ProductEntity()
                .setProductId(1)
                .setProductName("Nike 1")
                .setNumberInStock(5)
                .setProductSummary("One in the Nike Number Series")
                .setProductPrice(999.00);
        productEntityList.add(nike1);
        ProductEntity nike2 = new ProductEntity()
                .setProductId(2)
                .setProductName("Nike 2")
                .setNumberInStock(5)
                .setProductSummary("One in the Nike Number Series")
                .setProductPrice(999.00);
        productEntityList.add(nike2);
        ProductEntity nike3 = new ProductEntity()
                .setProductId(3)
                .setProductName("Nike 3")
                .setNumberInStock(5)
                .setProductSummary("One in the Nike Number Series")
                .setProductPrice(999.00);
        productEntityList.add(nike3);
        ProductEntity nike4 = new ProductEntity()
                .setProductId(4)
                .setProductName("Nike 4")
                .setNumberInStock(5)
                .setProductSummary("One in the Nike Number Series")
                .setProductPrice(999.00);
        productEntityList.add(nike4);
        ProductEntity nike5 = new ProductEntity()
                .setProductId(5)
                .setProductName("Nike 5")
                .setNumberInStock(5)
                .setProductSummary("One in the Nike Number Series")
                .setProductPrice(999.00);
        productEntityList.add(nike5);
        ProductEntity nike6 = new ProductEntity()
                .setProductId(6)
                .setProductName("Nike 6")
                .setNumberInStock(5)
                .setProductSummary("One in the Nike Number Series")
                .setProductPrice(999.00);
        productEntityList.add(nike6);
        ProductEntity nike7 = new ProductEntity()
                .setProductId(7)
                .setProductName("Nike 7")
                .setNumberInStock(5)
                .setProductSummary("One in the Nike Number Series")
                .setProductPrice(999.00);
        productEntityList.add(nike7);
        ProductEntity nike8 = new ProductEntity()
                .setProductId(8)
                .setProductName("Nike 8")
                .setNumberInStock(5)
                .setProductSummary("One in the Nike Number Series")
                .setProductPrice(999.00);
        productEntityList.add(nike8);
        ProductEntity nike9 = new ProductEntity()
                .setProductId(9)
                .setProductName("Nike 9")
                .setNumberInStock(5)
                .setProductSummary("One in the Nike Number Series")
                .setProductPrice(999.00);
        productEntityList.add(nike9);
        ProductEntity nike10 = new ProductEntity()
                .setProductId(10)
                .setProductName("Nike 10")
                .setNumberInStock(5)
                .setProductSummary("One in the Nike Number Series")
                .setProductPrice(999.00);
        productEntityList.add(nike10);
        doReturn(productEntityList).when(productRepository).findByProductNameContaining("Nike");
        ProductResponse response = productService.getProductByKeyword("Nike");
        Assertions.assertEquals(10, response.getProducts().size());
    }

    @Test
    void postProductAddToBasket_noActiveBasket() {
        doReturn(Collections.EMPTY_LIST).when(basketRepository)
                .findByUserIdAndBasketActive(1, true);
        doReturn(new BasketEntity()).when(basketRepository).save(any(BasketEntity.class));
        productService.addToBasket(new PostProductAddToBasketRequest()
                .setNumberOfProduct(3)
                .setProductId(1)
                .setUserId(1));
        verify(basketRepository).save(basketEntityCaptor.capture());
        Assertions.assertEquals(3, basketEntityCaptor.getValue().getNumberOfProduct());
        Assertions.assertEquals(1, basketEntityCaptor.getValue().getProductId());
        Assertions.assertEquals(1, basketEntityCaptor.getValue().getUserId());
        Assertions.assertEquals(true, basketEntityCaptor.getValue().getBasketActive());
        Assertions.assertEquals(true, basketEntityCaptor.getValue().getRecordActive());
    }

    @Test
    void postProductAddToBasket_withActiveBasket() {
        doReturn(Collections.singletonList(new BasketEntity().setBasketId(1))).when(basketRepository)
                .findByUserIdAndBasketActive(1, true);
        doReturn(new BasketEntity()).when(basketRepository).save(any(BasketEntity.class));
        productService.addToBasket(new PostProductAddToBasketRequest()
                .setNumberOfProduct(3)
                .setProductId(1)
                .setUserId(1));
        verify(basketRepository).save(basketEntityCaptor.capture());
        Assertions.assertEquals(1, basketEntityCaptor.getValue().getBasketId());
        Assertions.assertEquals(3, basketEntityCaptor.getValue().getNumberOfProduct());
        Assertions.assertEquals(1, basketEntityCaptor.getValue().getProductId());
        Assertions.assertEquals(1, basketEntityCaptor.getValue().getUserId());
        Assertions.assertEquals(true, basketEntityCaptor.getValue().getBasketActive());
        Assertions.assertEquals(true, basketEntityCaptor.getValue().getRecordActive());
    }
}
