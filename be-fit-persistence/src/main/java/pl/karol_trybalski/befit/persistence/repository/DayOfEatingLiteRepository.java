package pl.karol_trybalski.befit.persistence.repository;

import org.springframework.stereotype.Repository;
import pl.karol_trybalski.befit.domain.entity.day_of_eating.DayOfEatingLite;
import pl.karol_trybalski.befit.persistence.base.BaseRepository;

@Repository
public interface DayOfEatingLiteRepository extends BaseRepository<DayOfEatingLite, Long> {

}
