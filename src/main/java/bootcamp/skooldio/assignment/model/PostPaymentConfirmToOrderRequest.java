package bootcamp.skooldio.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class PostPaymentConfirmToOrderRequest implements Serializable {
    private Integer userId;
    private Integer checkoutId;
    private PaymentInfo paymentInfo;
    private ShippingInfo shippingInfo;
}
