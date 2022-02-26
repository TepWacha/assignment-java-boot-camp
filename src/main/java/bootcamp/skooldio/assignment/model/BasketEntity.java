package bootcamp.skooldio.assignment.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "basket")
@Data
@Accessors(chain = true)
public class BasketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer recordId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer basketId;
    private Boolean basketActive;
    private Integer userId;
    private Integer productId;
    private Integer numberOfProduct;
    private Boolean recordActive;
}
