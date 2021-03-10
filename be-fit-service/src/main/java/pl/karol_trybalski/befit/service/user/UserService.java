package pl.karol_trybalski.befit.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.karol_trybalski.befit.persistence.repository.user.PermissionRepository;
import pl.karol_trybalski.befit.persistence.repository.user.UserRepository;

@RequiredArgsConstructor
@Transactional
@Service
public class UserService {

  private final PermissionRepository permissionRepository;
  private final UserRepository repository;

}
