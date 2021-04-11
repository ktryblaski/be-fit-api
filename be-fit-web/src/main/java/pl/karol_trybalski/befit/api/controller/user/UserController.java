package pl.karol_trybalski.befit.api.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.karol_trybalski.befit.persistence.repository.user.UserRepository;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {

  private final UserRepository repository;

  @GetMapping("/exists-by-mail")
  public boolean existsByEmail(@RequestParam final String email) {
    return repository.existsByEmail(email);
  }

}
