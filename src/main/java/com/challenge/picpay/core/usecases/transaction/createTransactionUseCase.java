package com.challenge.picpay.core.usecases.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.challenge.picpay.core.domain.Transaction;
import com.challenge.picpay.core.domain.User;
import com.challenge.picpay.core.domain.dtos.TransactionDTO;
import com.challenge.picpay.core.usecases.user.FindUserByIdUseCase;
import com.challenge.picpay.core.usecases.user.PersistUserUseCase;
import com.challenge.picpay.core.usecases.user.UserException;

@Service
public class createTransactionUseCase {
  @Autowired
  private TransactionReposytory transactionReposytory;

  @Autowired
  private FindUserByIdUseCase findUserById;

  @Autowired
  private PersistUserUseCase persistUser;

  @Autowired
  private ValidateTransactionUseCase validateTransaction;

  @Autowired
  private RestTemplate restTemplate;

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

    this.transactionReposytory.save(newTransaction);
    this.persistUser.perform(sender);
    this.persistUser.perform(receive);
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
