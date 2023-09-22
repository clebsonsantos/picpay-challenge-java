package com.challenge.picpay.core.dtos;

import java.math.BigDecimal;

import com.challenge.picpay.core.domain.UserType;

public record UserDTO(String firstName, String lastName, String document, BigDecimal balance, String email,
        String password, UserType userType) {

}
