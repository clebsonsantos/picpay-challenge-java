package com.challenge.picpay.core.domain;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

@Value
@AllArgsConstructor
@Getter
@Setter
public class User {
  private final Long id;

  private final String firstName;

  private final String lastName;

  private final String document;

  private final String email;

  private final String password;

  private final BigDecimal balance;

  private final UserType userType;
}

enum UserType {
  COMMON,
  MERCHANT
}