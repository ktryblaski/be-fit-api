package pl.karol_trybalski.befit.dto.dto.product;

import lombok.Data;
import pl.karol_trybalski.befit.dto.dto.base.BaseDTO;

@Data
public class ProductViewDTO extends BaseDTO {

    private String name;
    private boolean favourite;
    private int proteins;
    private int fats;
    private int carbohydrates;

}
