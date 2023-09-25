package com.challenge.picpay.core.usecases.transaction;

import com.challenge.picpay.core.domain.Transaction;

public interface TransactionRepository {
  Long save(Transaction transaction) throws TransactionException;
}
