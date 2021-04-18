package pl.karol_trybalski.befit.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

  @Override
  public void commence(final HttpServletRequest request,
                       final HttpServletResponse response,
                       final AuthenticationException exception) throws IOException {

    // TODO integrate with exception resolver
    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, exception.getMessage());
  }

}
