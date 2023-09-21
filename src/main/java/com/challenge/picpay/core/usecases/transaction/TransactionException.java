package com.challenge.picpay.core.usecases.transaction;

public class TransactionException extends Exception {
  public TransactionException(String message) {
    super("Falha na transação: ".concat(message));
  }
}
