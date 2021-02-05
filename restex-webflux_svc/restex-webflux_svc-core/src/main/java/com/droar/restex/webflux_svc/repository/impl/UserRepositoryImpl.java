package com.droar.restex.webflux_svc.repository.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import com.droar.restex.webflux_svc.entity.QRole;
import com.droar.restex.webflux_svc.entity.QUser;
import com.droar.restex.webflux_svc.entity.User;
import com.droar.restex.webflux_svc.exception.ExceptionHandler;
import com.droar.restex.webflux_svc.repository.UserRepositoryCustom;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.extern.slf4j.Slf4j;


/**
 * The Class UserRepositoryImpl.
 * 
 * @author droar
 *
 */
@Slf4j
@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {

  /** The exceptionHandler. */
  @Autowired
  private ExceptionHandler exceptionHandler;

  /** The entity manager. */
  @PersistenceContext
  private EntityManager entityManager;

  public List<User> findUsers(String firstName, String career, String userName) {
    List<User> lstUsers = null;
    try {

      QUser qUser = QUser.user;
      QRole qRole = QRole.role;

      JPAQuery<User> query = new JPAQuery<User>(this.entityManager);

      query.select(qUser);

      query.from(qUser);

      // Lazy inicitialization.
      query.join(qUser.role, qRole).fetchJoin();

      // Username Filter
      if (StringUtils.isNotBlank(firstName))
        query.where(qUser.firstName.like(firstName));
      // Career Filter
      if (StringUtils.isNotBlank(career))
        query.where(qUser.career.eq(career));
      if (StringUtils.isNotBlank(userName))
        query.where(qUser.username.eq(userName));

      query.orderBy(qUser.id.desc());

      // We fetch the results
      lstUsers = query.fetch();

    } catch (Exception e) {
      log.error("There's been an error when querying users by patterns");
      log.error(e.getMessage());
      throw this.exceptionHandler.getHttpException(HttpStatus.BAD_REQUEST);
    }

    return lstUsers;
  }
  
  public List<User> findUsersReactive(String firstName, String career, String userName) {
    List<User> lstUsers = null;
   
    //TODO Pending, depends on r2dbc

    return lstUsers;
  }
}
