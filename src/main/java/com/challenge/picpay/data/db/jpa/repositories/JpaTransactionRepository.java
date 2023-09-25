package com.challenge.picpay.data.db.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.challenge.picpay.data.db.jpa.entities.TransactionData;

@Repository
public interface JpaTransactionRepository extends JpaRepository<TransactionData, Long> {
}
