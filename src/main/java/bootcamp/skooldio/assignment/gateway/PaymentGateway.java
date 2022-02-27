package bootcamp.skooldio.assignment.gateway;

import bootcamp.skooldio.assignment.model.PaymentRequest;
import bootcamp.skooldio.assignment.model.PaymentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PaymentGateway {
    @Autowired
    private RestTemplate restTemplate;

    public PaymentResponse pay(PaymentRequest request) {
        return restTemplate.postForObject("http://localhost:8080/mock-payment-service", request, PaymentResponse.class);
    }
}
