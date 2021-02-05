package com.droar.restex.webflux_svc.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.reactive.ReactiveRepresentationModelAssembler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import com.droar.restex.webflux_svc.controller.UserController;
import com.droar.restex.webflux_svc.model.svc_user.UserGetDTO;
import reactor.core.publisher.Mono;

/**
 * 
 * @author droar
 *
 */
@Component
public class UserResourceAssembler implements ReactiveRepresentationModelAssembler<UserGetDTO, UserGetDTO> {

  @Override
  public Mono<UserGetDTO> toModel(UserGetDTO userDTO, ServerWebExchange exchange) {
    
    Link selfLink = linkTo(UserController.class).slash(userDTO.getId()).withSelfRel();
    userDTO.add(selfLink);
    return Mono.just(userDTO);
  }
}
