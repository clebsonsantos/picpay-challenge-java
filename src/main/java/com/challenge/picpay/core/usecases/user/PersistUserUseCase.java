package com.challenge.picpay.core.usecases.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.challenge.picpay.core.domain.User;

@Service
public class PersistUserUseCase {
  @Autowired
  private UserRepository repository;

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
