package pl.karol_trybalski.befit.api.controller.auth.dto;

import lombok.Data;
import pl.karol_trybalski.befit.api.validation.Password;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class LoginRequestDTO {

  @NotNull
  @Email
  private String email;

  @Password
  private String password;

}
