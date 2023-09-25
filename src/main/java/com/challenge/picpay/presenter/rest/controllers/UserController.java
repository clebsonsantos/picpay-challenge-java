package com.challenge.picpay.presenter.rest.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.picpay.core.domain.User;
import com.challenge.picpay.core.dtos.UserDTO;
import com.challenge.picpay.core.usecases.user.GetAllUsersUseCase;
import com.challenge.picpay.core.usecases.user.PersistUserUseCase;
import com.challenge.picpay.core.usecases.user.UserException;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("users")
public class UserController {
  final PersistUserUseCase persistUserUseCase;
  final GetAllUsersUseCase getAllUsersUseCase;

  public UserController(PersistUserUseCase persistUserUseCase, GetAllUsersUseCase getAllUsersUseCase) {
    this.persistUserUseCase = persistUserUseCase;
    this.getAllUsersUseCase = getAllUsersUseCase;
  }

  @PostMapping
  public ResponseEntity<User> createUser(@RequestBody UserDTO user) throws UserException {
    User newUser = this.persistUserUseCase.perform(user);
    return new ResponseEntity<>(newUser, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<User>> listAllUsers() throws UserException {
    List<User> users = this.getAllUsersUseCase.perform();
    return new ResponseEntity<>(users, HttpStatus.CREATED);
  }
}
