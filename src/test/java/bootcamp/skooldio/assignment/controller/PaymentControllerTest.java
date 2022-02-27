package bootcamp.skooldio.assignment.controller;

import bootcamp.skooldio.assignment.model.*;
import bootcamp.skooldio.assignment.repository.BasketRepository;
import bootcamp.skooldio.assignment.repository.CheckoutRepository;
import bootcamp.skooldio.assignment.repository.OrderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import wiremock.com.fasterxml.jackson.databind.JsonNode;

import java.util.Arrays;
import java.util.Collections;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
@AutoConfigureWireMock(port = 8080)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PaymentControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;
    @MockBean
    private CheckoutRepository checkoutRepository;
    @MockBean
    private OrderRepository orderRepository;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void postBasketCheckout() throws JsonProcessingException {
        doReturn(Collections.singletonList(
                new CheckoutEntity()
                        .setCheckoutId(1)
                        .setCheckoutActive(true)
                        .setCheckoutValue(999.00)
                        .setUserId(1)
                        .setListOfProduct("")))
                .when(checkoutRepository).findByCheckoutId(1);
        doReturn(new CheckoutEntity()
                        .setCheckoutId(1)
                        .setCheckoutActive(true)
                        .setCheckoutValue(999.00)
                        .setUserId(1)
                        .setListOfProduct(""))
                .when(checkoutRepository).save(any());
        doReturn(new OrderEntity()
                        .setOrderId(1)
                        .setUserId(1)
                        .setPaymentInfo("")
                        .setShippingInfo("")
                        .setListOfProduct("")
                        .setInvoiceNumber("1")
                        .setCancelStatus(false)
                        .setOrderPrice(999.00))
                .when(orderRepository).save(any());
        stubFor(post(urlEqualTo("/mock-payment-service"))
                .willReturn(aResponse().withStatus(200).withHeader("Content-Type", "application/json").withBody(objectMapper.writeValueAsString(new PaymentResponse()
                        .setBarcodeNumber("11111111111111111")
                        .setInvoiceNumber("1")
                        .setExpirationDatetime("27-02-2018T23:33:00+07:00")
                        .setTransactionDatetime("25-02-2018T23:33:00+07:00")
                        .setPayee("Lazada")))));
        PostPaymentConfirmToOrderResponse response = testRestTemplate.postForObject(
                "/v1/payment/confirm-to-order",
                new PostPaymentConfirmToOrderRequest()
                        .setUserId(1)
                        .setCheckoutId(1)
                        .setPaymentInfo(new PaymentInfo().setCardName("Tep Wacharotayangoon"))
                        .setShippingInfo(new ShippingInfo()),
                PostPaymentConfirmToOrderResponse.class);
        Assertions.assertEquals("1", response.getInvoiceNumber());
    }
}
