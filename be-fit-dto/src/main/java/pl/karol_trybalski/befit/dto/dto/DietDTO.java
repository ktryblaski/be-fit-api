package pl.karol_trybalski.befit.dto.dto;

import lombok.Data;
import pl.karol_trybalski.befit.domain.enums.DietType;
import pl.karol_trybalski.befit.dto.dto.base.BaseDTO;

import java.time.LocalDate;
import java.util.Set;

@Data
public class DietDTO extends BaseDTO {

    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private DietType type;
    private MacronutrientsDTO macronutrients;
    private Set<DietMealDTO> meals;

}
