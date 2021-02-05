package com.droar.restex.webflux_svc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.droar.restex.webflux_svc.assembler.UserResourceAssembler;
import com.droar.restex.webflux_svc.entity.User;
import com.droar.restex.webflux_svc.exception.ExceptionHandler;
import com.droar.restex.webflux_svc.mapper.UserMapper;
import com.droar.restex.webflux_svc.model.svc_user.UserGetDTO;
import com.droar.restex.webflux_svc.model.svc_user.UserGetDetailDTO;
import com.droar.restex.webflux_svc.service.UserService;
import com.droar.restex.webflux_svc.util.Constants;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*",
    methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.DELETE, RequestMethod.PUT})
@RequestMapping("/users")
public class UserController {

  /** The user service. */
  @Autowired
  private UserService userService;

  /** The user mapper. */
  @Autowired
  private UserMapper userMapper;

  /** The exception handler. */
  @Autowired
  private ExceptionHandler exceptionHandler;

  /** The assembler. */
  @Autowired
  private UserResourceAssembler userAssembler;

  @GetMapping
  public Flux<UserGetDTO> getUsers(@RequestHeader("Authorization") String token,
      @RequestHeader(value = "Accept-Language", defaultValue = Constants.Controller.DEFAULT_LANGUAGE, required = false) String language,
      @RequestParam(value = "firstName", required = false) String firstName,
      @RequestParam(value = "career", required = false) String career) {
    log.info("[API USERS]: [GET] /users: Getting users by filters -> " + firstName);

    Flux<UserGetDTO> flxAssembledDTO = null;

    // We call to the service, to handle logic and eventually search the repository
    Flux<User> flxFoundUsers = this.userService.findUsers(firstName, career, null);

    if (flxFoundUsers == null) {
      log.error("No userwith this pattern has been found -> " + firstName + '/' + career);
      throw this.exceptionHandler.getHttpException(HttpStatus.BAD_REQUEST);
    }

    // We map entities to DTO and we add their links
    Flux<UserGetDTO> flxDTOitems = this.userMapper.transformToDTOList(flxFoundUsers);

    // We assemble the HATEOAS Links
    flxAssembledDTO = flxDTOitems.flatMap(d -> this.userAssembler.toModel(d, null));

    return flxAssembledDTO;
  }

  @GetMapping(Constants.UserController.GET_USER_BY_ID)
  public Mono<UserGetDetailDTO> getUser(@RequestHeader("Authorization") String token,
      @RequestHeader(value = "Accept-Language", defaultValue = Constants.Controller.DEFAULT_LANGUAGE,
          required = false) String language,
      @PathVariable(value = "id", required = false) String strId) {
    log.info("[API USERS]: [GET] /users/{id}: Getting user by id -> " + strId);

    Mono<UserGetDetailDTO> mnAssembledDTO = null;

    Mono<User> mnUser = this.userService.findUserById(strId);

    if (mnUser == null) {
      log.error("No userwith this pattern has been found -> " + strId);
      throw this.exceptionHandler.getHttpException(HttpStatus.BAD_REQUEST);
    }

    // We map entities to DTO and we add their links
    mnAssembledDTO = this.userMapper.transformToDTO(mnUser);
    
    return mnAssembledDTO;
  }

}
