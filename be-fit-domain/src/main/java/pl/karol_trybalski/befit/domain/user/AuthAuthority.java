package pl.karol_trybalski.befit.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthAuthority implements GrantedAuthority {

  private String authority;

  public AuthAuthority(final Permission permission) {
    this(permission.getCode());
  }

}
