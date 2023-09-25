package com.challenge.picpay.data.db.jpa.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.challenge.picpay.core.domain.User;

import com.challenge.picpay.core.usecases.user.UserRepository;
import com.challenge.picpay.data.db.jpa.entities.UserData;

@Component
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
  public User save(User user) {
    UserData userData = new UserData();
    BeanUtils.copyProperties(user, userData);

    return this.repository.save(userData).fromThis();
  }

  @Override
  public List<User> findAll() {
    return repository.findAll().stream().map(UserData::fromThis).toList();
  }
}
