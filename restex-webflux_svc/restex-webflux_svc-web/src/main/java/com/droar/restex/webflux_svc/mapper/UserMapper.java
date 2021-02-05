package com.droar.restex.webflux_svc.mapper;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.droar.restex.webflux_svc.entity.User;
import com.droar.restex.webflux_svc.model.svc_user.UserGetDTO;
import com.droar.restex.webflux_svc.model.svc_user.UserGetDetailDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * The Class UserMapper.
 */
@Component
public class UserMapper {

  /**
   * 
   * @param flxUsers
   * @return
   */
  public Flux<UserGetDTO> transformToDTOList(Flux<User> flxUsers) {
    List<UserGetDTO> lstUsersDTO = new ArrayList<UserGetDTO>();

    // TODO: This is not reactive (and very horrible tbh), have to change to asyncronous parsing
    List<User> lstEntityUsers = flxUsers.collectList().block();
    lstEntityUsers.stream().forEach(u -> {
      lstUsersDTO.add(new UserGetDTO(u.getId().toString(), u.getFirstName(), u.getLastName(), u.getCareer()));
    });
    
    return Flux.just(lstUsersDTO.toArray(new UserGetDTO[lstUsersDTO.size()]));
  }

  /**
   * 
   * @param mnUser
   * @return
   */
  public Mono<UserGetDetailDTO> transformToDTO(Mono<User> mnUser) {

    // TODO: This is not reactive (and very horrible tbh), have to change to asyncronous parsing
    User entityUser = mnUser.block(Duration.ofSeconds(2));

    UserGetDetailDTO userDTO = new UserGetDetailDTO();
    userDTO.setFirstName(entityUser.getFirstName());
    userDTO.setLastName(entityUser.getLastName());
    userDTO.setCareer(entityUser.getCareer());

    // we transform back
    Mono<UserGetDetailDTO> mnUserGetDetailDTO = Mono.just(userDTO);

    return mnUserGetDetailDTO;

  }

}
