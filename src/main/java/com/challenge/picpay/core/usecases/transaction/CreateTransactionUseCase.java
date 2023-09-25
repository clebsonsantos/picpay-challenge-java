package com.challenge.picpay.core.usecases.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
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
import com.fasterxml.jackson.databind.ObjectMapper;

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

  public Transaction perform(TransactionDTO transaction) throws UserException, TransactionException {
    User sender = this.findUserById.perform(transaction.senderId());
    User receive = this.findUserById.perform(transaction.receiveId());

    validateTransaction.perform(sender, transaction.value());
    boolean isAuthorized = this.authrorizeTransaction(sender, transaction.value());

    if (!isAuthorized) {
      throw new TransactionException("Não autorizado.");
    }

    Transaction newTransaction = new Transaction();
    newTransaction.setAmount(transaction.value());
    newTransaction.setReceiver(receive);
    newTransaction.setSender(sender);
    newTransaction.setTimestamp(LocalDateTime.now());

    sender.setBalance(sender.getBalance().subtract(transaction.value()));
    receive.setBalance(receive.getBalance().add(transaction.value()));

    Long transactionId = this.transactionRepository.save(newTransaction);
    this.userRepository.save(sender);
    this.userRepository.save(receive);
    newTransaction.setId(transactionId);

    return newTransaction;
  }

  public boolean authrorizeTransaction(User sender, BigDecimal value) {
    try {
      String webhook = "https://webhook.site/7bffa1f3-af4c-4130-b2b3-53c2e3eae2e0";
      ResponseEntity<String> response = restTemplate.getForEntity(webhook, String.class);
      System.out.println(response.getBody());

      Map<String, Object> result = new ObjectMapper().readValue(response.getBody(), HashMap.class);
      if (response.getStatusCode() == HttpStatus.OK) {
        String message = (String) result.get("message");
        return "Autorizado".equalsIgnoreCase(message);
      } else
        return false;
    } catch (Exception exception) {
      return false;
    }
  }
}
