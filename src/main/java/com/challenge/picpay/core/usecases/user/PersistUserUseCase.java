package com.challenge.picpay.core.usecases.user;

import com.challenge.picpay.core.domain.User;
import com.challenge.picpay.core.dtos.UserDTO;

public class PersistUserUseCase {
  private UserRepository repository;

  public PersistUserUseCase(UserRepository repository) {
    this.repository = repository;
  }

  public User perform(UserDTO user) throws UserException {
    if (user.document() == null &&
        user.firstName() == null &&
        user.lastName() == null &&
        user.password() == null) {
      new UserException("Usuário não possui as informações obrigatórias para cadastramento");
    }
    User newUser = new User();
    newUser.setDocument(user.document());
    newUser.setBalance(user.balance());
    newUser.setEmail(user.email());
    newUser.setFirstName(user.firstName());
    newUser.setLastName(user.lastName());
    newUser.setPassword(user.password());

    this.repository.save(newUser);
    return newUser;
  }
}
