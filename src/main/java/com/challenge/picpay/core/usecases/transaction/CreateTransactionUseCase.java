package com.challenge.picpay.core.usecases.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.challenge.picpay.core.domain.Transaction;
import com.challenge.picpay.core.domain.User;
import com.challenge.picpay.core.dtos.TransactionDTO;
import com.challenge.picpay.core.usecases.user.FindUserByIdUseCase;
import com.challenge.picpay.core.usecases.user.UserException;
import com.challenge.picpay.core.usecases.user.UserRepository;

public class CreateTransactionUseCase {
  private TransactionRepository transactionRepository;

  private UserRepository userRepository;

  private FindUserByIdUseCase findUserById;

  private ValidateTransactionUseCase validateTransaction;

  private RestTemplate restTemplate;

  public CreateTransactionUseCase(TransactionRepository transactionRepository, UserRepository UserRepository,
      FindUserByIdUseCase findUserById, ValidateTransactionUseCase validateTransaction, RestTemplate restTemplate) {
    this.transactionRepository = transactionRepository;
    this.userRepository = UserRepository;
    this.findUserById = findUserById;
    this.validateTransaction = validateTransaction;
    this.restTemplate = restTemplate;
  }

  public void perform(TransactionDTO transaction) throws UserException, TransactionException {
    User sender = this.findUserById.perform(transaction.senderId());
    User receive = this.findUserById.perform(transaction.receiveId());

    validateTransaction.perform(sender, transaction.value());

    if (!this.authrorizeTransaction(sender, transaction.value())) {
      throw new TransactionException("Não autorizado.");
    }

    Transaction newTransaction = new Transaction();
    newTransaction.setAmount(transaction.value());
    newTransaction.setReceiver(receive);
    newTransaction.setSender(sender);
    newTransaction.setTimestamp(LocalDateTime.now());

    sender.setBalance(sender.getBalance().subtract(transaction.value()));
    receive.setBalance(receive.getBalance().add(transaction.value()));

    this.transactionRepository.save(newTransaction);
    this.userRepository.save(sender);
    this.userRepository.save(receive);
  }

  public boolean authrorizeTransaction(User sender, BigDecimal value) {
    ResponseEntity<Map> response = restTemplate.getForEntity("https://teste.com.br", Map.class);
    if (response.getStatusCode() == HttpStatus.OK) {
      String message = (String) response.getBody().get("message");
      return "Autorizado".equalsIgnoreCase(message);
    } else
      return false;
  }
}
