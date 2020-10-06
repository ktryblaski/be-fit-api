package pl.karol_trybalski.befit.dto.dto.meal_template;

import lombok.Data;
import pl.karol_trybalski.befit.domain.entity.Ingredient;
import pl.karol_trybalski.befit.dto.dto.base.BaseDTO;

import java.util.Set;

@Data
public class MealTemplateDTO extends BaseDTO {

    private String name;
    private String description;
    private boolean active;
    private Set<Ingredient> ingredients;

}
