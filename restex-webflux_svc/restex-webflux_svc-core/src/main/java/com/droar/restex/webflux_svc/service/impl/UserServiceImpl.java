package com.droar.restex.webflux_svc.service.impl;

import java.util.Arrays;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.droar.boot.fwk.base.service.impl.GenericServiceBaseImpl;
import com.droar.restex.webflux_svc.entity.User;
import com.droar.restex.webflux_svc.exception.ExceptionHandler;
import com.droar.restex.webflux_svc.repository.UserRepository;
import com.droar.restex.webflux_svc.service.UserService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 
 * @author droar
 *
 *         We will implement from our own user service, but at the same time from spring security
 *         one, we want to let him obtain our roles.
 *
 */
@Service
public class UserServiceImpl extends GenericServiceBaseImpl<User, Integer, UserRepository>
    implements UserService, UserDetailsService {

  /** The exceptionHandler. */
  @SuppressWarnings("unused")
  @Autowired
  private ExceptionHandler exceptionHandler;

  @Override
  public Flux<User> findUsers(String firstName, String career, String userName) {
    if (StringUtils.isNotBlank(firstName)) {
      // Just for trying the query lookup from repository, we call it in case username is not empty
      return Flux.fromIterable(this.getRepo().findByFirstNameContaining(firstName));
    } else {
      // We go to the normal querying TODO: Need to have querying, before fixing the flux arround here
      return Flux.fromIterable(this.getRepo().findUsers(firstName, career, userName));
    }
  }

  @Override
  public Mono<User> findUserById(String strId) {
    // transform to integer from controller and return
    Validate.notNull("User id cannot be null: " + strId);

    return Mono.justOrEmpty(this.getRepo().findById(NumberUtils.toInt(strId)));
  }

  @Override
  public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    // We first fetch our user, we need this to be blocked by userdetails
    User user = this.getRepo().findUsers(null, null, userName).stream().findFirst().orElse(null);

    // We construct the user details
    if (user != null && user.getRole() != null) {
      // We get the grant
      SimpleGrantedAuthority grantAuth = new SimpleGrantedAuthority(user.getRole().getName());

      return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
          Arrays.asList(grantAuth));
    } else {
      throw new UsernameNotFoundException("User not found with username: " + userName);
    }
  }

}
