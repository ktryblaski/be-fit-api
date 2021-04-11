package pl.karol_trybalski.befit.domain.user;

import java.util.stream.Collectors;

public class AuthUser extends org.springframework.security.core.userdetails.User {

  public AuthUser(final User user) {
    super(
      user.getEmail(),
      user.getPassword(),
      !user.isLocked(),
      true,
      true,
      true,
      user.getPermissions().stream().map(AuthAuthority::new).collect(Collectors.toSet())
    );
  }

}
