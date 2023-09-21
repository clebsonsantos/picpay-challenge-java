package com.challenge.picpay.core.usecases.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.challenge.picpay.core.domain.User;

@Service
public class FindUserByIdUseCase {
  @Autowired
  private UserRepository repository;

  public User perform(Long id) throws UserException {
    if (id == null) {
      new UserException("O id não pode ser nulo");
    }

    return this.repository.findUserById(id).orElseThrow(() -> new UserException("Usuário não encontrado"));
  }
}
