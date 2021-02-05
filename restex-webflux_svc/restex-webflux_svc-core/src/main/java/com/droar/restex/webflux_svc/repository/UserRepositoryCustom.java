package com.droar.restex.webflux_svc.repository;

import java.util.List;
import com.droar.restex.webflux_svc.entity.User;


/**
 * The Interface UserRepositoryCustom.
 */

public interface UserRepositoryCustom {

  /**
   * Find users example semi reactive
   *
   * @param firstName the username
   * @param career the career
   * @param userName the userName
   * @return the users
   */
  public List<User> findUsers(String firstName, String career, String userName);
}
