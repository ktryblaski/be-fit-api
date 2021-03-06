package pl.karol_trybalski.befit.dto.dto.product;

import lombok.Data;
import pl.karol_trybalski.befit.dto.dto.MacronutrientsDTO;
import pl.karol_trybalski.befit.dto.dto.base.BaseDTO;

@Data
public class ProductDTO extends BaseDTO {

    private String name;
    private boolean favourite;
    private MacronutrientsDTO macronutrients;

}
