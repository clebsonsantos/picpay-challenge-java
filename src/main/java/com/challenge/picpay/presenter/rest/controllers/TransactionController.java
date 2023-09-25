package com.challenge.picpay.presenter.rest.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.picpay.core.domain.Transaction;
import com.challenge.picpay.core.dtos.TransactionDTO;
import com.challenge.picpay.core.usecases.transaction.CreateTransactionUseCase;
import com.challenge.picpay.core.usecases.transaction.TransactionException;
import com.challenge.picpay.core.usecases.user.UserException;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("transaction")
public class TransactionController {
  final CreateTransactionUseCase createTransactionUseCase;

  public TransactionController(CreateTransactionUseCase createTransactionUseCase) {
    this.createTransactionUseCase = createTransactionUseCase;
  }

  @PostMapping
  public ResponseEntity<Transaction> createUser(@RequestBody TransactionDTO transactionDTO)
      throws TransactionException, UserException {
    Transaction transaction = this.createTransactionUseCase.perform(transactionDTO);
    return new ResponseEntity<>(transaction, HttpStatus.OK);
  }
}
