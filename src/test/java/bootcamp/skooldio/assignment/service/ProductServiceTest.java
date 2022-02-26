package bootcamp.skooldio.assignment.service;

import bootcamp.skooldio.assignment.model.ProductEntity;
import bootcamp.skooldio.assignment.model.ProductResponse;
import bootcamp.skooldio.assignment.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static reactor.core.publisher.Mono.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @InjectMocks
    private ProductService productService;
    @Mock
    private ProductRepository productRepository;
    
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
}
