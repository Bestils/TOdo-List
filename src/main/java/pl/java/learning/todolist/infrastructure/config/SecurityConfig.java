package pl.java.learning.todolist.infrastructure.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication()
        .withUser("admin")
        .password(
            passwordEncoder()
                .encode("admin")
        ).
        roles("ADMIN")
        .and()
        .withUser("user")
        .password(
            passwordEncoder()
                .encode("admin")
        ).
        roles("USER");

  }

  @Override
  protected void configure(final HttpSecurity http) throws Exception {
    http
        .csrf().disable()
          .headers().frameOptions().disable()
        .and()
          .authorizeRequests()
          .antMatchers("/login*").anonymous()
          .antMatchers("/webjars/**").permitAll()
          .anyRequest().authenticated()
        .and()
          .formLogin()
          .successForwardUrl("/tasks")
        .and()
          .logout();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}