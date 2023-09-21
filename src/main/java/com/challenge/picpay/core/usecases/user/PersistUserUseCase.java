package com.challenge.picpay.core.usecases.user;

import com.challenge.picpay.core.domain.User;

public class PersistUserUseCase {
  private UserRepository repository;

  public PersistUserUseCase(UserRepository repository) {
    this.repository = repository;
  }

  public void perform(User user) throws UserException {
    if (user.getDocument() == null &&
        user.getFirstName() == null &&
        user.getLastName() == null &&
        user.getPassword() == null) {
      new UserException("Usuário não possui as informações obrigatórias para cadastramento");
    }

    this.repository.save(user);
  }
}
