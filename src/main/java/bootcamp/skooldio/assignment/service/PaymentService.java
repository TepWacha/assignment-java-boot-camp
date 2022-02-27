package bootcamp.skooldio.assignment.service;

import bootcamp.skooldio.assignment.gateway.PaymentGateway;
import bootcamp.skooldio.assignment.model.*;
import bootcamp.skooldio.assignment.repository.CheckoutRepository;
import bootcamp.skooldio.assignment.repository.OrderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private CheckoutRepository checkoutRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private PaymentGateway paymentGateway;
    private ObjectMapper objectMapper = new ObjectMapper();


    public PostPaymentConfirmToOrderResponse postPaymentConfirmToOrder(PostPaymentConfirmToOrderRequest request) throws JsonProcessingException {
        List<CheckoutEntity> checkoutEntities = checkoutRepository.findByCheckoutId(request.getCheckoutId());
        CheckoutEntity checkoutEntity = checkoutEntities.get(0);
        PaymentResponse paymentResponse = paymentGateway.pay(new PaymentRequest().setPaymentInfo(request.getPaymentInfo()).setPrice(checkoutEntity.getCheckoutValue()));
        orderRepository.save(
                new OrderEntity()
                        .setUserId(checkoutEntity.getUserId())
                        .setListOfProduct(checkoutEntity.getListOfProduct())
                        .setOrderPrice(checkoutEntity.getCheckoutValue())
                        .setPaymentInfo(objectMapper.writeValueAsString(request.getPaymentInfo()))
                        .setShippingInfo(objectMapper.writeValueAsString(request.getShippingInfo()))
                        .setCancelStatus(false)
                        .setInvoiceNumber(paymentResponse.getInvoiceNumber()));
        checkoutRepository.save(checkoutEntity.setCheckoutActive(false));
        return new PostPaymentConfirmToOrderResponse()
                .setInvoiceNumber(paymentResponse.getInvoiceNumber())
                .setOrderPrice(checkoutEntity.getCheckoutValue())
                .setPayer(request.getPaymentInfo().getCardName())
                .setPayee(paymentResponse.getPayee())
                .setBarcodeNumber(paymentResponse.getBarcodeNumber())
                .setExpirationDatetime(paymentResponse.getExpirationDatetime())
                .setTransactionDatetime(paymentResponse.getTransactionDatetime());
    }
}
