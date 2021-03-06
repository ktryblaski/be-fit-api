package pl.karol_trybalski.befit.dto.dto;

import lombok.Data;
import pl.karol_trybalski.befit.dto.dto.base.BaseDTO;

@Data
public class MacronutrientsDTO extends BaseDTO {

    private int proteins;
    private int fats;
    private int carbohydrates;

}
