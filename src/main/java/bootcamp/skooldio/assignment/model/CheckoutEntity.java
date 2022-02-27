package bootcamp.skooldio.assignment.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "checkout_cashier")
@Data
@Accessors(chain = true)
public class CheckoutEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer checkoutId;
    private Boolean checkoutActive;
    private Integer userId;
    private String listOfProduct;
    private Double checkoutValue;
}
