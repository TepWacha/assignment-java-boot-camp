package bootcamp.skooldio.assignment.controller;

import bootcamp.skooldio.assignment.model.PostProductAddToBasketRequest;
import bootcamp.skooldio.assignment.model.PostProductAddToBasketResponse;
import bootcamp.skooldio.assignment.model.ProductResponse;
import bootcamp.skooldio.assignment.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("v1/product/{keyword}")
    public ProductResponse getProductByKeyword(@PathVariable String keyword) {
        return productService.getProductByKeyword(keyword);
    }

    @PostMapping("v1/product/add-to-basket")
    public PostProductAddToBasketResponse postProductAddToBasket(@RequestBody PostProductAddToBasketRequest request) {
        productService.addToBasket(request);
        return new PostProductAddToBasketResponse().setStatus("success");
    }
}