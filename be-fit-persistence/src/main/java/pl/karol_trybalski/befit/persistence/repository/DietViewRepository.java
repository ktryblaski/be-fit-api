package pl.karol_trybalski.befit.persistence.repository;

import org.springframework.stereotype.Repository;
import pl.karol_trybalski.befit.domain.entity.DietView;
import pl.karol_trybalski.befit.persistence.base.BaseRepository;

@Repository
public interface DietViewRepository extends BaseRepository<DietView, Long> {

}
