package com.challenge.picpay.core.usecases.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenge.picpay.core.domain.User;
import com.challenge.picpay.core.domain.dtos.TransactionDTO;
import com.challenge.picpay.core.usecases.user.FindUserByIdUseCase;
import com.challenge.picpay.core.usecases.user.UserException;

@Service
public class createTransactionUseCase {
  // @Autowired
  // private TransactionReposytory transactionReposytory;

  @Autowired
  private FindUserByIdUseCase findUserById;

  @Autowired
  private ValidateTransactionUseCase validateTransaction;

  public void perform(TransactionDTO transaction) throws UserException, TransactionException {
    User sender = this.findUserById.perform(transaction.senderId());
    // User receive = this.findUserById.perform(transaction.receiveId());

    validateTransaction.perform(sender, transaction.value());
  }
}
