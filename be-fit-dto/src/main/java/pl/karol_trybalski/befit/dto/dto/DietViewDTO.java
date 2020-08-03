package pl.karol_trybalski.befit.dto.dto;

import lombok.Data;
import pl.karol_trybalski.befit.domain.enums.DietType;
import pl.karol_trybalski.befit.dto.dto.base.BaseDTO;

import java.time.LocalDate;

@Data
public class DietViewDTO extends BaseDTO {

    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private DietType type;
    private int carbohydrates;
    private int proteins;
    private int fats;
    private int meals;

}
