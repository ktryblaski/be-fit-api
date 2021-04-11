package pl.karol_trybalski.befit.api.controller.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.karol_trybalski.befit.api.controller.auth.dto.LoginRequestDTO;
import pl.karol_trybalski.befit.security.JWTService;

import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {

  private final AuthenticationManager authenticationManager;
  private final JWTService jwtService;

  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  @ExceptionHandler(BadCredentialsException.class)
  public void handleBadCredentialsException() {
    // no body needed
  }

  @PostMapping("/check-authentication")
  public void checkAuthentication() {
    // no body needed
  }

  @PostMapping("/login")
  public void logIn(@RequestBody final LoginRequestDTO request, final HttpServletResponse response) {
    final Authentication authentication = authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
    );

    SecurityContextHolder.getContext().setAuthentication(authentication);
    jwtService.addJWTCookie(response, authentication);
  }

  @PostMapping("/logout")
  public void logout(HttpServletResponse response) {
    this.jwtService.removeJWTCookie(response);
  }

}
