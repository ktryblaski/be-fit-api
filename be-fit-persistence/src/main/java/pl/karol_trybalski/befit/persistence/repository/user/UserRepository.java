package pl.karol_trybalski.befit.persistence.repository.user;

import org.springframework.stereotype.Repository;
import pl.karol_trybalski.befit.domain.user.User;
import pl.karol_trybalski.befit.persistence.base.BaseRepository;

@Repository
public interface UserRepository extends BaseRepository<User, Long> {

}
