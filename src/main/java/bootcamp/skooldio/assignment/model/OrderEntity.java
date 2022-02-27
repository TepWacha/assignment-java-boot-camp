package bootcamp.skooldio.assignment.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "confirmed_order")
@Data
@Accessors(chain = true)
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;
    private Integer userId;
    private Boolean cancelStatus;
    private String listOfProduct;
    private Double orderPrice;
    private String paymentInfo;
    private String shippingInfo;
    private String invoiceNumber;
}
