package pl.karol_trybalski.befit.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.karol_trybalski.befit.domain.entity.MealView;
import pl.karol_trybalski.befit.dto.dto.MealViewDTO;

@Mapper
public interface MealMapper {

    MealMapper INSTANCE = Mappers.getMapper(MealMapper.class);

    MealViewDTO map(MealView product);

}
