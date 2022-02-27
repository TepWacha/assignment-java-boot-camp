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
public class PaymentResponse implements Serializable {
    private String invoiceNumber;
    private String barcodeNumber;
    private String payee;
    private String transactionDatetime;
    private String expirationDatetime;
}
