package com.authorization.cajuchallenge.repository;

import com.authorization.cajuchallenge.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends MongoRepository<Account, String> {

}