package pl.karol_trybalski.befit.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.karol_trybalski.befit.domain.day_of_eating.DayOfEatingBegin;
import pl.karol_trybalski.befit.domain.entity.day_of_eating.DayOfEating;
import pl.karol_trybalski.befit.domain.entity.day_of_eating.DayOfEatingLite;
import pl.karol_trybalski.befit.dto.dto.day_of_eating.DayOfEatingBeginDTO;
import pl.karol_trybalski.befit.dto.dto.day_of_eating.DayOfEatingDTO;
import pl.karol_trybalski.befit.dto.dto.day_of_eating.DayOfEatingLiteDTO;

@Mapper(uses = {MealMapper.class})
public interface DayOfEatingMapper {

    DayOfEatingMapper INSTANCE = Mappers.getMapper(DayOfEatingMapper.class);

    DayOfEatingBegin map(DayOfEatingBeginDTO begin);

    DayOfEatingDTO map(DayOfEating dayOfEating);
    DayOfEatingLiteDTO map(DayOfEatingLite dayOfEating);

}
