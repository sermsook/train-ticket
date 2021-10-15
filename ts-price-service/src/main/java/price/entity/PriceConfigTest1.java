package price.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

/**
 * @author fdse
 */
@Data
@AllArgsConstructor
@Document(collection="price_config")
public class PriceConfigTest1 {

    @Id
    private UUID id;

    private String trainType;

    private String routeId;

    private double basicPriceRate;

    private double firstClassPriceRate;

    public PriceConfigTest1() {
        //Empty Constructor
    }

}
