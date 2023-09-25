package com.challenge.picpay.data.db.jpa.repositories;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.challenge.picpay.core.domain.Transaction;
import com.challenge.picpay.core.usecases.transaction.TransactionException;
import com.challenge.picpay.core.usecases.transaction.TransactionRepository;
import com.challenge.picpay.data.db.jpa.entities.TransactionData;

@Component
public class TransactionRepositoryImpl implements TransactionRepository {
  private final JpaTransactionRepository repository;

  public TransactionRepositoryImpl(JpaTransactionRepository repository) {
    this.repository = repository;
  }

  @Override
  public Long save(Transaction transaction) throws TransactionException {
    TransactionData transactionData = new TransactionData();
    BeanUtils.copyProperties(transaction, transactionData);

    return this.repository.save(transactionData).getId();
  }

}
