package com.challenge.picpay.core.usecases.user;

import java.util.List;

import com.challenge.picpay.core.domain.User;

public class GetAllUsersUseCase {
  final UserRepository userRepository;

  public GetAllUsersUseCase(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<User> perform() {
    return this.userRepository.findAll();
  }

}
