package com.challenge.picpay.core.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

@Value
@AllArgsConstructor
@Getter
@Setter
public class Transaction {
  private final Long id;

  private final BigDecimal amount;

  private final User sender;

  private final User receiver;

  private final LocalDateTime timestamp;
}
