package com.droar.restex.webflux_svc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.droar.restex.webflux_svc.util.Constants;

/**
 * Very basic spring security setting
 * 
 * @author droar
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // Disable CSRF
    http.csrf().disable()
        // Only admin can delete
        .authorizeRequests().antMatchers(HttpMethod.DELETE).hasRole(Constants.SecurityProfiles.ROLE_ADMIN)
        // For testing purposes, details can only be viewed by the admin
        .antMatchers("/users/{id}").hasAnyRole(Constants.SecurityProfiles.ROLE_ADMIN)
        // any authenticated user can perform all other operations
        .antMatchers("/users/**").hasAnyRole(Constants.SecurityProfiles.ROLE_USER, Constants.SecurityProfiles.ROLE_ADMIN)
        .and().httpBasic()
        // Permit all other request without authentication
         .and().authorizeRequests().anyRequest().permitAll()
        // We don't need sessions to be created.
        .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  }

  @Override
  public UserDetailsService userDetailsService() {
    return userDetailsService;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(10);
  }
}
