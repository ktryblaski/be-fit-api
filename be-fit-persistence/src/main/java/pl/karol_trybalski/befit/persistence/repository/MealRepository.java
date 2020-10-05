package pl.karol_trybalski.befit.persistence.repository;

import org.springframework.stereotype.Repository;
import pl.karol_trybalski.befit.domain.entity.meal.Meal;
import pl.karol_trybalski.befit.persistence.base.BaseRepository;

@Repository
public interface MealRepository extends BaseRepository<Meal, Long> {

}
