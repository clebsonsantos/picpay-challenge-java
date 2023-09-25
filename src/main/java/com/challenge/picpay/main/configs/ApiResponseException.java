package com.challenge.picpay.main.configs;

import lombok.Value;

@Value
public class ApiResponseException {
  private final Boolean success;
  private final String message;
}