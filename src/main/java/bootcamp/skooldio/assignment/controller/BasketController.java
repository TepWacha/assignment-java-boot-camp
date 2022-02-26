package bootcamp.skooldio.assignment.controller;

import bootcamp.skooldio.assignment.model.BasketResponse;
import bootcamp.skooldio.assignment.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasketController {

    @Autowired
    private BasketService basketService;

    @GetMapping("v1/basket/{userId}")
    public BasketResponse getBasketByUserId(@PathVariable Integer userId) {
        return basketService.getBasketByUserId(userId);
    }
}
