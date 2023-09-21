package com.challenge.picpay.core.domain.dtos;

import java.math.BigDecimal;

public record TransactionDTO(BigDecimal value, Long senderId, Long receiveId) {

}
