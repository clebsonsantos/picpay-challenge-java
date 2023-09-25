package com.challenge.picpay.presenter.rest.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.picpay.core.domain.User;
import com.challenge.picpay.core.dtos.UserDTO;
import com.challenge.picpay.core.usecases.user.PersistUserUseCase;
import com.challenge.picpay.core.usecases.user.UserException;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("users")
public class UserController {
  final PersistUserUseCase persistUserUseCase;

  public UserController(PersistUserUseCase persistUserUseCase) {
    this.persistUserUseCase = persistUserUseCase;
  }

  @PostMapping
  public ResponseEntity<User> createUser(@RequestBody UserDTO user) throws UserException {
    User newUser = this.persistUserUseCase.perform(user);
    return new ResponseEntity<>(newUser, HttpStatus.CREATED);
  }

}
