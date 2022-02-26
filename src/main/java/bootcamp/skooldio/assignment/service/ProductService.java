package bootcamp.skooldio.assignment.service;

import bootcamp.skooldio.assignment.model.Product;
import bootcamp.skooldio.assignment.model.ProductEntity;
import bootcamp.skooldio.assignment.model.ProductResponse;
import bootcamp.skooldio.assignment.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

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

}
