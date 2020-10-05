package pl.karol_trybalski.befit.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.karol_trybalski.befit.domain.entity.meal.Meal;
import pl.karol_trybalski.befit.dto.dto.meal.MealDTO;

@Mapper
public interface MealMapper {

    MealMapper INSTANCE = Mappers.getMapper(MealMapper.class);

    MealDTO map(Meal meal);

}
