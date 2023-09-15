package com.challenge.picpay.core.usecases.user;

import java.util.Optional;

import com.challenge.picpay.core.domain.User;

public interface UserRepository {
  Optional<User> findByDocument(String document);

  Optional<User> findUserById(Long id);
}
