package pl.karol_trybalski.befit.configuration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;
import org.springframework.web.util.CookieGenerator;

import static java.lang.String.format;
import static pl.karol_trybalski.befit.security.JWTAuthTokenFilter.JWT_COOKIE_NAME;

@Configuration
public class SecurityBeanConfiguration {

  @Bean
  public CookieGenerator cookieGenerator(final ApplicationContext context) {
    final CookieGenerator cookieGenerator = new CookieGenerator();

    cookieGenerator.setCookieHttpOnly(true);
    cookieGenerator.setCookieName(context.getEnvironment().getProperty(JWT_COOKIE_NAME));

    Assert.notNull(cookieGenerator.getCookieName(), format("%s property is not set", JWT_COOKIE_NAME));

    return cookieGenerator;
  }

  @Bean
  public GrantedAuthorityDefaults grantedAuthorityDefaults() {
    return new GrantedAuthorityDefaults("");
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
