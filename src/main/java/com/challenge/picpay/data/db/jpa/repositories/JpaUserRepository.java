package com.challenge.picpay.data.db.jpa.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.challenge.picpay.data.db.jpa.entities.UserData;

@Repository
public interface JpaUserRepository extends JpaRepository<UserData, Long> {
  Optional<UserData> findUserByDocument(String document);
}
