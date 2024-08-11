package com.authorization.cajuchallenge.repository;

import com.authorization.cajuchallenge.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionCommandRepository extends MongoRepository<Transaction, String> {
}