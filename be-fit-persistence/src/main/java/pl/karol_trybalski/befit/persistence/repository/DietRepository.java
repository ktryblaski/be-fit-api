package pl.karol_trybalski.befit.persistence.repository;

import org.springframework.stereotype.Repository;
import pl.karol_trybalski.befit.domain.entity.Diet;
import pl.karol_trybalski.befit.persistence.base.BaseRepository;

@Repository
public interface DietRepository extends BaseRepository<Diet, Long> {

}
