package com.challenge.picpay.core.usecases.transaction;

import java.math.BigDecimal;
import com.challenge.picpay.core.domain.User;
import com.challenge.picpay.core.domain.UserType;

public class ValidateTransactionUseCase {

  public void perform(User sender, BigDecimal amount) throws TransactionException {
    if (sender.getUserType() == UserType.MERCHANT) {
      throw new TransactionException("Usuário do tipo logista não está autorizado a realizar transação");
    }

    if (sender.getBalance().compareTo(amount) < 0) {
      throw new TransactionException("Saldo insuficiente");
    }
  }
}
