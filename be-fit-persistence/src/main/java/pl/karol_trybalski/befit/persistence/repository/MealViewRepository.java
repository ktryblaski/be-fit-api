package pl.karol_trybalski.befit.persistence.repository;

import org.springframework.stereotype.Repository;
import pl.karol_trybalski.befit.domain.entity.Meal;
import pl.karol_trybalski.befit.domain.entity.MealView;
import pl.karol_trybalski.befit.persistence.base.BaseRepository;

@Repository
public interface MealViewRepository extends BaseRepository<MealView, Long> {

}
