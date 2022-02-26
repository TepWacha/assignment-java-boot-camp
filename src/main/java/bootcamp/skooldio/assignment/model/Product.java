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
public class Product implements Serializable {
    private Integer productId;
    private String productName;
    private Integer numberInStock;
    private String productSummary;
    private Double productPrice;
}
