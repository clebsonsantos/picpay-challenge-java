package com.challenge.picpay.core.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Transaction {
  private Long id;

  private BigDecimal amount;

  private User sender;

  private User receiver;

  private LocalDateTime timestamp;
}
