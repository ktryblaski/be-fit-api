package pl.karol_trybalski.befit.dto.dto;

import lombok.Data;
import pl.karol_trybalski.befit.domain.enums.MealType;
import pl.karol_trybalski.befit.dto.dto.base.BaseDTO;
import pl.karol_trybalski.befit.dto.dto.meal.MealDTO;

@Data
public class DietMealDTO extends BaseDTO {

    private MealType mealType;
    private MealDTO meal;

}
