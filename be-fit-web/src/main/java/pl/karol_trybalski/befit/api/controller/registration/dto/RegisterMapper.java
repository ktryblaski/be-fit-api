package pl.karol_trybalski.befit.api.controller.registration.dto;

import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.karol_trybalski.befit.domain.user.User;

@RequiredArgsConstructor
@Mapper(componentModel = "spring")
public abstract class RegisterMapper {

  @Autowired
  protected PasswordEncoder passwordEncoder;

  @Mappings({
    @Mapping(target = "password", expression = "java(passwordEncoder.encode(register.getPassword()))")
  })
  public abstract User map(final RegisterRequestDTO register);

}
