package pl.karol_trybalski.befit.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.karol_trybalski.befit.domain.entity.meal_template.MealTemplate;
import pl.karol_trybalski.befit.dto.dto.meal_template.MealTemplateDTO;

@Mapper
public interface MealTemplateMapper {

    MealTemplateMapper INSTANCE = Mappers.getMapper(MealTemplateMapper.class);

    MealTemplateDTO map(MealTemplate mealTemplate);

}
