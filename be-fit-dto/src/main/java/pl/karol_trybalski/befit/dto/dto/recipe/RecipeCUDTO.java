package pl.karol_trybalski.befit.dto.dto.recipe;

import lombok.Data;
import pl.karol_trybalski.befit.dto.dto.base.BaseDTO;

import java.util.List;

@Data
public class RecipeCUDTO extends BaseDTO {

    private String name;
    private String description;
    private boolean active;
    private List<IngredientDTO> ingredients;

    @Data
    public static class IngredientDTO {
        private Long id;
        private Long productId;
        private Integer weight;
    }
}
