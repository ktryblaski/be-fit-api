package pl.karol_trybalski.befit.dto.dto;

import lombok.Data;
import pl.karol_trybalski.befit.domain.entity.Ingredient;
import pl.karol_trybalski.befit.domain.enums.MealType;
import pl.karol_trybalski.befit.dto.dto.base.BaseDTO;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Set;

@Data
public class MealDTO extends BaseDTO {

    private String name;
    private String description;

    @Enumerated(EnumType.STRING)
    private MealType type;

    private Set<Ingredient> ingredients;

}
