package bootcamp.skooldio.assignment.controller;

import bootcamp.skooldio.assignment.model.ProductResponse;
import bootcamp.skooldio.assignment.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("v1/product/{keyword}")
    public ProductResponse getProductByKeyword(@PathVariable String keyword) {
        return productService.getProductByKeyword(keyword);
    }
}