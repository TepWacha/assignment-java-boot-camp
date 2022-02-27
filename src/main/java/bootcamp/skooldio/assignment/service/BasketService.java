package bootcamp.skooldio.assignment.service;

import bootcamp.skooldio.assignment.model.*;
import bootcamp.skooldio.assignment.repository.BasketRepository;
import bootcamp.skooldio.assignment.repository.CheckoutRepository;
import bootcamp.skooldio.assignment.repository.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BasketService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BasketRepository basketRepository;
    @Autowired
    private CheckoutRepository checkoutRepository;
    private ObjectMapper objectMapper = new ObjectMapper();


    public BasketResponse getBasketByUserId(Integer userId) {
        List<BasketEntity> basketEntities = basketRepository.findByUserIdAndBasketActiveAndRecordActive(userId, true, true);
        BasketResponse response = new BasketResponse();
        if(!basketEntities.isEmpty() && null != basketEntities.get(0).getBasketId()) {
            response.setBasketId(basketEntities.get(0).getBasketId());
            Map<Integer,ProductInBasket> productIdToProductInBasket = new HashMap<>();
            for (BasketEntity basketEntity : basketEntities) {
                if (productIdToProductInBasket.containsKey(basketEntity.getProductId())) {
                    productIdToProductInBasket.put(
                            basketEntity.getProductId(),
                            productIdToProductInBasket
                                    .get(basketEntity.getProductId()).setNumberOfProduct(
                                            productIdToProductInBasket.get(basketEntity.getProductId()).getNumberOfProduct()
                                                    + basketEntity.getNumberOfProduct()));
                } else {
                    List<ProductEntity> productEntity = productRepository.findByProductId(basketEntity.getProductId());
                    productIdToProductInBasket.put(
                            basketEntity.getProductId(),
                            new ProductInBasket()
                                    .setProductId(basketEntity.getProductId())
                                    .setProductName(productEntity.get(0).getProductName())
                                    .setNumberOfProduct(basketEntity.getNumberOfProduct())
                                    .setProductPrice(productEntity.get(0).getProductPrice()));
                }
            }
            List<ProductInBasket> productsInBasket = new ArrayList<>(productIdToProductInBasket.values());
            response.setProducts(productsInBasket);
        }
        return response;
    }

    public PostBasketCheckoutResponse postBasketCheckout(PostBasketCheckoutRequest request) throws JsonProcessingException {
        BasketResponse basketResponse = this.getBasketByUserId(request.getUserId());
        Double checkoutValue = this.calculateCheckoutValue(basketResponse.getProducts());
        CheckoutEntity checkoutEntity = checkoutRepository.save(
                new CheckoutEntity()
                        .setUserId(request.getUserId())
                        .setListOfProduct(objectMapper.writeValueAsString(basketResponse.getProducts()))
                        .setCheckoutValue(checkoutValue)
                        .setCheckoutActive(true));
        this.updateBasketActiveByBasketId(request.getBasketId(), false);
        return new PostBasketCheckoutResponse().setCheckoutId(checkoutEntity.getCheckoutId());
    }

    void updateBasketActiveByBasketId(Integer basketId, Boolean basketActive) {
        List<BasketEntity> basketList = basketRepository.findByBasketId(basketId);
        for (BasketEntity basketEntity : basketList) {
            basketRepository.save(basketEntity.setBasketActive(basketActive));
        }
    }

    Double calculateCheckoutValue (List<ProductInBasket> productInBasketList) {
        double checkoutValue = 0.00;
        for (ProductInBasket product : productInBasketList) {
            checkoutValue += (product.getProductPrice() * product.getNumberOfProduct());
        }
        return checkoutValue;
    }
}
