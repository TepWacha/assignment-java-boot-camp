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
public class PaymentInfo implements Serializable {
    private String cardNumber;
    private String cardName;
    private String cardExpiration;
    private String cardCVV;
}
