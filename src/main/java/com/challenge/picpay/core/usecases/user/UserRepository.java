package com.challenge.picpay.core.usecases.user;

import java.util.List;
import java.util.Optional;

import com.challenge.picpay.core.domain.User;

public interface UserRepository {
  Optional<User> findByDocument(String document);

  Optional<User> findUserById(Long id);

  User save(User user);

  List<User> findAll();
}
