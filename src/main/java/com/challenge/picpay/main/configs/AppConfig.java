package com.challenge.picpay.main.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.challenge.picpay.core.usecases.transaction.CreateTransactionUseCase;
import com.challenge.picpay.core.usecases.transaction.TransactionRepository;
import com.challenge.picpay.core.usecases.transaction.ValidateTransactionUseCase;
import com.challenge.picpay.core.usecases.user.FindUserByIdUseCase;
import com.challenge.picpay.core.usecases.user.GetAllUsersUseCase;
import com.challenge.picpay.core.usecases.user.PersistUserUseCase;
import com.challenge.picpay.core.usecases.user.UserRepository;

@Configuration
public class AppConfig {

  @Bean
  PersistUserUseCase persistUserUseCase(UserRepository userRepository) {
    return new PersistUserUseCase(userRepository);
  }

  @Bean
  FindUserByIdUseCase findUserByIdUseCase(UserRepository userRepository) {
    return new FindUserByIdUseCase(userRepository);
  }

  @Bean
  ValidateTransactionUseCase validateTransactionUseCase() {
    return new ValidateTransactionUseCase();
  }

  @Bean
  CreateTransactionUseCase createTransactionUseCase(TransactionRepository transactionRepository,
      UserRepository userRepository) {
    return new CreateTransactionUseCase(transactionRepository, userRepository, this.findUserByIdUseCase(userRepository),
        this.validateTransactionUseCase(), this.restTemplate());
  };

  @Bean
  RestTemplate restTemplate() {
    return new RestTemplate();
  }

  @Bean
  GetAllUsersUseCase getAllUsersUseCase(UserRepository userRepository) {
    return new GetAllUsersUseCase(userRepository);
  }
}
