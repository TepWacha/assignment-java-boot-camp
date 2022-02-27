package bootcamp.skooldio.assignment.mockpaymentservice;

import bootcamp.skooldio.assignment.model.PaymentRequest;
import bootcamp.skooldio.assignment.model.PaymentResponse;
import bootcamp.skooldio.assignment.model.PostPaymentConfirmToOrderRequest;
import bootcamp.skooldio.assignment.model.PostPaymentConfirmToOrderResponse;
import bootcamp.skooldio.assignment.service.PaymentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MockPaymentServiceController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("mock-payment-service")
    public PaymentResponse postBasketCheckout(@RequestBody PaymentRequest request){
        return new PaymentResponse()
                .setBarcodeNumber("11111111111111111")
                .setInvoiceNumber("1")
                .setExpirationDatetime("27-02-2018T23:33:00+07:00")
                .setTransactionDatetime("25-02-2018T23:33:00+07:00")
                .setPayee("Lazada");
    }
}
