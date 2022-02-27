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
public class PostPaymentConfirmToOrderResponse implements Serializable {
    private String invoiceNumber;
    private String payer;
    private String payee;
    private Double orderPrice;
    private String barcodeNumber;
    private String transactionDatetime;
    private String expirationDatetime;
}
