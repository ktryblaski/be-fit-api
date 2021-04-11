package pl.karol_trybalski.befit.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.karol_trybalski.befit.domain.exception.DomainError;
import pl.karol_trybalski.befit.domain.exception.DomainException;
import pl.karol_trybalski.befit.domain.user.AuthUser;
import pl.karol_trybalski.befit.domain.user.User;
import pl.karol_trybalski.befit.persistence.repository.user.UserRepository;

import java.time.LocalDateTime;

import static java.lang.String.format;

@RequiredArgsConstructor
@Transactional
@Service
public class UserService implements UserDetailsService {

  private final UserRepository repository;

  @Override
  public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
    return repository.findByEmailIgnoreCase(email)
      .map(AuthUser::new)
      .orElseThrow(() -> new UsernameNotFoundException(format("User [%s] not found", email)));
  }

  public void register(final User user) {
    user.setRegisterAt(LocalDateTime.now());
    try {
      repository.save(user);
    } catch (final DataIntegrityViolationException e) {
      throw new DomainException(DomainError.USER_WITH_GIVEN_EMAIL_ALREADY_EXISTS);
    }
  }

}
