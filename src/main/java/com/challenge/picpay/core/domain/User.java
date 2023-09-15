package com.challenge.picpay.core.domain;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class User {
  private Long id;

  private String firstName;

  private String lastName;

  private String document;

  private String email;

  private String password;

  private BigDecimal balance;

  private UserType userType;
}
