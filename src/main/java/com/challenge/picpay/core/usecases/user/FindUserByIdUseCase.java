package com.challenge.picpay.core.usecases.user;

import com.challenge.picpay.core.domain.User;

public class FindUserByIdUseCase {
  private UserRepository repository;

  public FindUserByIdUseCase(UserRepository repository) {
    this.repository = repository;
  }

  public User perform(Long id) throws UserException {
    if (id == null) {
      new UserException("O id não pode ser nulo");
    }

    return this.repository.findUserById(id).orElseThrow(() -> new UserException("Usuário não encontrado"));
  }
}
