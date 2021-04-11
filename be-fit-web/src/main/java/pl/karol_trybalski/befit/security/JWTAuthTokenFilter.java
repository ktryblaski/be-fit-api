package pl.karol_trybalski.befit.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.Assert;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;
import pl.karol_trybalski.befit.service.user.UserService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static java.lang.String.format;

@RequiredArgsConstructor
@Slf4j
public class JWTAuthTokenFilter extends OncePerRequestFilter {

  public static final String JWT_COOKIE_NAME = "befit.security.jwt-cookie-name";

  private String jwtCookieName;

  private final JWTService jwtService;
  private final UserService userService;

  public JWTAuthTokenFilter(final ApplicationContext context,
                            final JWTService jwtService,
                            final UserService userService) {

    this.jwtService = jwtService;
    this.userService = userService;

    this.jwtCookieName = context.getEnvironment().getProperty(JWT_COOKIE_NAME);

    Assert.notNull(this.jwtCookieName, format("%s property is not set", JWT_COOKIE_NAME));
  }

  @Override
  protected void doFilterInternal(final HttpServletRequest request,
                                  final HttpServletResponse response,
                                  final FilterChain filterChain) throws ServletException, IOException {

    try {
      final Optional<Cookie> jwtTokenCookie = retrieveJWTTokenCookie(request);
      jwtTokenCookie.ifPresent(cookie -> {
        if(jwtService.isValidJWTToken(cookie.getValue())) {
          final String username = jwtService.getUsernameFromJWTToken(cookie.getValue());

          final UserDetails userDetails = userService.loadUserByUsername(username);
          final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
            userDetails, null, userDetails.getAuthorities()
          );
          authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

          SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
          jwtService.removeJWTCookie(response);
        }
      });
    } catch (Exception e) {
      log.error("An error occurred while authenticating user", e);
    }

    filterChain.doFilter(request, response);
  }

  private Optional<Cookie> retrieveJWTTokenCookie(HttpServletRequest request) {
    return Optional.ofNullable(WebUtils.getCookie(request, jwtCookieName));
  }

}
