package bootcamp.skooldio.assignment.controller;

import bootcamp.skooldio.assignment.model.*;
import bootcamp.skooldio.assignment.service.BasketService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BasketController {

    @Autowired
    private BasketService basketService;

    @GetMapping("v1/basket/{userId}")
    public BasketResponse getBasketByUserId(@PathVariable Integer userId) {
        return basketService.getBasketByUserId(userId);
    }

    @PostMapping("v1/basket/checkout")
    public PostBasketCheckoutResponse postBasketCheckout(@RequestBody PostBasketCheckoutRequest request) throws JsonProcessingException {
        return basketService.postBasketCheckout(request);
    }
}
