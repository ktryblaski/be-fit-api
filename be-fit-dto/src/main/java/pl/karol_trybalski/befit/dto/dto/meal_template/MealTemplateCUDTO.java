package pl.karol_trybalski.befit.dto.dto.meal_template;

import lombok.Data;
import pl.karol_trybalski.befit.dto.dto.base.BaseDTO;

import java.util.List;

@Data
public class MealTemplateCUDTO extends BaseDTO {

    private String name;
    private String description;
    private List<IngredientDTO> ingredients;

    @Data
    public static class IngredientDTO {
        private Long productId;
        private Integer weight;
    }
}
