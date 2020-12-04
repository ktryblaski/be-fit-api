package pl.karol_trybalski.befit.dto.dto.recipe;

import lombok.Data;
import pl.karol_trybalski.befit.domain.entity.Ingredient;
import pl.karol_trybalski.befit.dto.dto.base.BaseDTO;

import java.util.Set;

@Data
public class RecipeViewDTO extends BaseDTO {

    private String name;
    private String description;
    private boolean active;
    private int weight;
    private double proteins;
    private double fats;
    private double carbohydrates;

}
