package pl.karol_trybalski.befit.persistence.repository.user;

import org.springframework.stereotype.Repository;
import pl.karol_trybalski.befit.domain.user.Permission;
import pl.karol_trybalski.befit.persistence.base.BaseRepository;

@Repository
public interface PermissionRepository extends BaseRepository<Permission, Long> { }
