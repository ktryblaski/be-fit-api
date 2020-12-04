package pl.karol_trybalski.befit.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.karol_trybalski.befit.domain.module.recipe.Recipe;
import pl.karol_trybalski.befit.domain.module.recipe.RecipeView;
import pl.karol_trybalski.befit.dto.dto.recipe.RecipeDTO;
import pl.karol_trybalski.befit.dto.dto.recipe.RecipeViewDTO;

@Mapper
public interface RecipeMapper {

    RecipeMapper INSTANCE = Mappers.getMapper(RecipeMapper.class);

    RecipeDTO map(Recipe recipe);
    RecipeViewDTO map(RecipeView recipe);

}
