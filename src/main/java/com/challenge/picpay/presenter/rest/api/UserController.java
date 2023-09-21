package com.challenge.picpay.presenter.rest.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.picpay.core.domain.User;
import com.challenge.picpay.core.dtos.UserDTO;
import com.challenge.picpay.core.usecases.user.PersistUserUseCase;
import com.challenge.picpay.core.usecases.user.UserException;

@RestController("/api/users")
public class UserController {
  private PersistUserUseCase persistUserUseCase;

  public UserController(PersistUserUseCase persistUserUseCase) {
    this.persistUserUseCase = persistUserUseCase;
  }

  @PostMapping
  public ResponseEntity<User> createUser(UserDTO user) throws UserException {
    User newUser = this.persistUserUseCase.perform(user);
    return new ResponseEntity<>(newUser, HttpStatus.CREATED);
  }

}
