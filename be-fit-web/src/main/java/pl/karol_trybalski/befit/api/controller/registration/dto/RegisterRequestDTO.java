package pl.karol_trybalski.befit.api.controller.registration.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.karol_trybalski.befit.api.validation.Password;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

// TODO change default locale bc of ugly out of the box constraints' messages
@NoArgsConstructor
@Data
public class RegisterRequestDTO {

  @NotNull
  @Size(min = 3, max = 128)
  private String name;

  @NotNull
  @Size(min = 3, max = 128)
  private String surname;

  @NotNull
  @Email
  private String email;

  @Password
  private String password;

}
