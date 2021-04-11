package pl.karol_trybalski.befit.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pl.karol_trybalski.befit.security.JWTAuthTokenFilter;
import pl.karol_trybalski.befit.security.JWTService;
import pl.karol_trybalski.befit.service.user.UserService;

import javax.servlet.http.HttpServletResponse;


@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  private final ApplicationContext context;
  private final JWTService jwtService;
  private final UserService userService;

  @Override
  protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userService);
  }

  @Override
  protected void configure(final HttpSecurity http) throws Exception {
    http
      .authorizeRequests(authorizeRequests -> authorizeRequests
        .antMatchers(
          "/auth/login",
          "/auth/logout",
          "/registration/*",
          "/user/exists-by-mail"
        ).permitAll()
        .anyRequest().authenticated()
      )
      .csrf(AbstractHttpConfigurer::disable)
      .exceptionHandling(customizer ->
        customizer.authenticationEntryPoint((req, res, ex) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage()))
      )
      .logout(AbstractHttpConfigurer::disable)
      .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

    http.addFilterBefore(new JWTAuthTokenFilter(context, jwtService, userService), UsernamePasswordAuthenticationFilter.class);
  }

  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

}
