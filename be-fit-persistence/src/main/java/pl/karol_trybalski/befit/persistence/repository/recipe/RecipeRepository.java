package pl.karol_trybalski.befit.persistence.repository.recipe;

import org.springframework.stereotype.Repository;
import pl.karol_trybalski.befit.domain.module.recipe.Recipe;
import pl.karol_trybalski.befit.persistence.base.BaseRepository;

import java.util.List;

@Repository
public interface RecipeRepository extends BaseRepository<Recipe, Long> {

  List<Recipe> findByActiveTrue();

}
