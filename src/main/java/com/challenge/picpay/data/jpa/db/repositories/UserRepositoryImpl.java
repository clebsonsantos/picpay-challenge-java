package com.challenge.picpay.data.jpa.db.repositories;

import java.util.Optional;

import com.challenge.picpay.core.domain.User;

import com.challenge.picpay.core.usecases.user.UserRepository;
import com.challenge.picpay.data.jpa.db.entities.UserData;

public class UserRepositoryImpl implements UserRepository {
  private final JpaUserRepository repository;

  public UserRepositoryImpl(JpaUserRepository repository) {
    this.repository = repository;
  }

  @Override
  public Optional<User> findByDocument(String document) {
    return this.repository
        .findUserByDocument(document)
        .map(UserData::fromThis);
  }

  @Override
  public Optional<User> findUserById(Long id) {
    return this.repository
        .findById(id)
        .map(UserData::fromThis);
  }

  @Override
  public void save(User user) {
    this.repository.save(user);
  }

}
