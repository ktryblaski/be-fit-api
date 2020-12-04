package pl.karol_trybalski.befit.persistence.repository.recipe;

import org.springframework.stereotype.Repository;
import pl.karol_trybalski.befit.domain.module.recipe.RecipeView;
import pl.karol_trybalski.befit.persistence.base.BaseRepository;

@Repository
public interface RecipeViewRepository extends BaseRepository<RecipeView, Long> { }
