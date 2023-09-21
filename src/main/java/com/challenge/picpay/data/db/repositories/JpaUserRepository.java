package com.challenge.picpay.data.db.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.challenge.picpay.core.domain.User;
import com.challenge.picpay.data.db.entities.UserData;

public interface JpaUserRepository extends JpaRepository<UserData, Long> {
  Optional<UserData> findUserByDocument(String document);

  void save(User user);
}
