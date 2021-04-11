package pl.karol_trybalski.befit.api.controller.registration;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.karol_trybalski.befit.api.controller.registration.dto.RegisterRequestDTO;
import pl.karol_trybalski.befit.api.controller.registration.dto.RegisterMapper;
import pl.karol_trybalski.befit.domain.user.User;
import pl.karol_trybalski.befit.service.user.UserService;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/registration")
@RestController
public class RegistrationController {

  private final RegisterMapper mapper;
  private final UserService service;

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping("/register")
  public void register(@Valid @RequestBody RegisterRequestDTO register) {
    final User user = mapper.map(register);
    service.register(user);
  }

}
