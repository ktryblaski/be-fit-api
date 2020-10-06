package pl.karol_trybalski.befit.persistence.repository;

import org.springframework.stereotype.Repository;
import pl.karol_trybalski.befit.domain.entity.day_of_eating.DayOfEating;
import pl.karol_trybalski.befit.persistence.base.BaseRepository;

import java.time.LocalDate;

@Repository
public interface DayOfEatingRepository extends BaseRepository<DayOfEating, Long> {

  boolean existsDayOfEatingByDayDate(LocalDate dayDate);

}
