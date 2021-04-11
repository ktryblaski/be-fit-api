package pl.karol_trybalski.befit.security;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.util.CookieGenerator;
import pl.karol_trybalski.befit.domain.user.AuthUser;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.UUID;

import static java.lang.String.format;

@Slf4j
@RequiredArgsConstructor
@Service
public class JWTService {

  @Value("${befit.security.jwt-expiration-in-ms}")
  private Integer jwtExpirationInMs;

  private String jwtSecret;

  private final CookieGenerator cookieGenerator;

  @PostConstruct
  public void postConstruct() {
    jwtSecret = format("%s_BEFIT_%d", UUID.randomUUID().toString(), new Date().getTime());
  }

  public boolean isValidJWTToken(final String jwtToken) {
    try {
      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwtToken);
      return true;
    } catch (SignatureException e) {
      log.error("Invalid JWT signature", e);
    } catch (MalformedJwtException e) {
      log.error("Invalid JWT token", e);
    } catch (ExpiredJwtException e) {
      log.info("JWT token is expired", e);
    } catch (UnsupportedJwtException e) {
      log.error("JWT token is unsupported", e);
    } catch (IllegalArgumentException e) {
      log.error("JWT claims string is empty", e);
    }

    return false;
  }

  public String getUsernameFromJWTToken(final String jwtToken) {
    return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwtToken).getBody().getSubject();
  }

  public void addJWTCookie(final HttpServletResponse response, final Authentication authentication) {
    cookieGenerator.addCookie(response, createJWTToken(authentication));
  }

  public void removeJWTCookie(final HttpServletResponse response) {
    cookieGenerator.removeCookie(response);
  }

  private String createJWTToken(final Authentication authentication) {
    final AuthUser authenticatedUser = (AuthUser) authentication.getPrincipal();

    return Jwts.builder()
      .setSubject(authenticatedUser.getUsername())
      .setIssuedAt(new Date())
      .setExpiration(new Date(new Date().getTime() + jwtExpirationInMs))
      .signWith(SignatureAlgorithm.HS512, jwtSecret)
      .compact();
  }

}
