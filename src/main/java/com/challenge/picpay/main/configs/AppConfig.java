package com.challenge.picpay.main.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.challenge.picpay.core.usecases.user.PersistUserUseCase;
import com.challenge.picpay.core.usecases.user.UserRepository;

@Configuration
public class AppConfig {

  @Bean
  PersistUserUseCase persistUserUseCase(UserRepository userRepository) {
    return new PersistUserUseCase(userRepository);
  }

  @Bean
  RestTemplate restTemplate() {
    return new RestTemplate();
  }
}
