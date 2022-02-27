package bootcamp.skooldio.assignment.controller;

import bootcamp.skooldio.assignment.model.*;
import bootcamp.skooldio.assignment.service.PaymentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("v1/payment/confirm-to-order")
    public PostPaymentConfirmToOrderResponse postBasketCheckout(@RequestBody PostPaymentConfirmToOrderRequest request) throws JsonProcessingException {
        return paymentService.postPaymentConfirmToOrder(request);
    }
}
