package pl.karol_trybalski.befit.dto.dto;

import lombok.Data;
import pl.karol_trybalski.befit.domain.enums.MealType;
import pl.karol_trybalski.befit.dto.dto.base.BaseDTO;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class MealViewDTO extends BaseDTO {

    private String name;
    private String description;

    @Enumerated(EnumType.STRING)
    private MealType type;

    private int weight;
    private int carbohydrates;
    private int proteins;
    private int fats;

}
