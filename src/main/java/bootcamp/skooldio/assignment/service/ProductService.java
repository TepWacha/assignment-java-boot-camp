package bootcamp.skooldio.assignment.service;

import bootcamp.skooldio.assignment.model.*;
import bootcamp.skooldio.assignment.repository.BasketRepository;
import bootcamp.skooldio.assignment.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BasketRepository basketRepository;

    public ProductResponse getProductByKeyword(String keyword) {
        List<ProductEntity> productEntities = productRepository.findByProductNameContaining(keyword);
        List<Product> productList = new ArrayList<>();
        for (ProductEntity productEntity : productEntities) {
            productList.add(new Product()
                    .setProductId(productEntity.getProductId())
                    .setProductName(productEntity.getProductName())
                    .setNumberInStock(productEntity.getNumberInStock())
                    .setProductSummary(productEntity.getProductSummary())
                    .setProductPrice(productEntity.getProductPrice()));
        }
        return new ProductResponse().setProducts(productList);
    }

    public void addToBasket(PostProductAddToBasketRequest request) {
        List<BasketEntity> basketEntities = basketRepository.findByUserIdAndBasketActive(request.getUserId(), true);
        if (basketEntities.isEmpty()) {
            basketRepository.save(new BasketEntity()
                    .setBasketActive(true)
                    .setRecordActive(true)
                    .setNumberOfProduct(request.getNumberOfProduct())
                    .setProductId(request.getProductId())
                    .setUserId(request.getUserId()));
        } else {
            basketRepository.save(new BasketEntity()
                    .setBasketId(basketEntities.get(0).getBasketId())
                    .setBasketActive(true)
                    .setRecordActive(true)
                    .setNumberOfProduct(request.getNumberOfProduct())
                    .setProductId(request.getProductId())
                    .setUserId(request.getUserId()));
        }
    }
}
