package pl.karol_trybalski.befit.persistence.repository;

import org.springframework.stereotype.Repository;
import pl.karol_trybalski.befit.domain.entity.meal_template.MealTemplate;
import pl.karol_trybalski.befit.persistence.base.BaseRepository;

import java.util.List;

@Repository
public interface MealTemplateRepository extends BaseRepository<MealTemplate, Long> {

  List<MealTemplate> findByActiveTrue();

}
