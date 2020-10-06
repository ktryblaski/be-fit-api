package pl.karol_trybalski.befit.dto.dto.day_of_eating;

import lombok.Data;
import pl.karol_trybalski.befit.dto.dto.base.BaseDTO;
import pl.karol_trybalski.befit.dto.dto.meal.MealDTO;

import java.time.LocalDate;
import java.util.List;

@Data
public class DayOfEatingDTO extends BaseDTO {

    private LocalDate dayDate;
    private List<MealDTO> meals;

}
