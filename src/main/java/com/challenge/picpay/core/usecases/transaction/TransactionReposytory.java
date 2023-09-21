package com.challenge.picpay.core.usecases.transaction;

import com.challenge.picpay.core.domain.Transaction;

public interface TransactionReposytory {
  void save(Transaction transaction) throws TransactionException;
}
