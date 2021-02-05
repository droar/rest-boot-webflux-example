package com.droar.restex.webflux_svc.service;

import com.droar.restex.webflux_svc.entity.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 
 * @author droar
 *
 */
public interface UserService  {


  /**
   * Find users
   * 
   * @param firstName
   * @param career
   * @param userName
   * @return users
   */
  public Flux<User> findUsers(String firstName, String career, String userName);
  
  /**
   * Find user.
   *
   * @param id the id
   * @return the user
   */
  public Mono<User> findUserById(String id);

}
