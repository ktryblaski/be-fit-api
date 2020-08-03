package pl.karol_trybalski.befit.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.karol_trybalski.befit.domain.entity.Diet;
import pl.karol_trybalski.befit.domain.entity.DietView;
import pl.karol_trybalski.befit.dto.dto.DietDTO;
import pl.karol_trybalski.befit.dto.dto.DietViewDTO;

@Mapper
public interface DietMapper {

    DietMapper INSTANCE = Mappers.getMapper(DietMapper.class);

    DietViewDTO map(DietView diet);

    DietDTO map(Diet diet);

}
