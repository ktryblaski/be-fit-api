package pl.karol_trybalski.befit.dto.dto.day_of_eating;

import lombok.Data;
import pl.karol_trybalski.befit.dto.dto.base.BaseDTO;

import java.time.LocalDate;

@Data
public class DayOfEatingLiteDTO extends BaseDTO {

    private LocalDate dayDate;
    private int mealsNumber;

}
