package pl.karol_trybalski.befit.persistence.repository;

import org.springframework.stereotype.Repository;
import pl.karol_trybalski.befit.persistence.base.BaseRepository;
import pl.karol_trybalski.befit.domain.entity.Ingredient;

@Repository
public interface IngredientRepository extends BaseRepository<Ingredient, Long> {

}
