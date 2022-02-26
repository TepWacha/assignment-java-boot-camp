package bootcamp.skooldio.assignment.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "products")
@Data
@Accessors(chain = true)
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;
    private String productName;
    private Integer numberInStock;
    private String productSummary;
    private Double productPrice;
}
