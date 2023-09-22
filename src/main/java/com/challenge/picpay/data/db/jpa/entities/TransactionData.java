package com.challenge.picpay.data.db.jpa.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class TransactionData {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private BigDecimal amount;

  @ManyToOne
  @JoinColumn(name = "sender_id")
  private UserData sender;

  @ManyToOne
  @JoinColumn(name = "receiver_id")
  private UserData receiver;

  private LocalDateTime timestamp;
}
