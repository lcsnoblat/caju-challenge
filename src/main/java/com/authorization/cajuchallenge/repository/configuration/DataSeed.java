package com.authorization.cajuchallenge.repository.configuration;

import com.authorization.cajuchallenge.model.Account;
import com.authorization.cajuchallenge.model.Transaction;
import com.authorization.cajuchallenge.repository.AccountRepository;
import com.authorization.cajuchallenge.repository.TransactionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.List;

@Configuration
public class DataSeed {

    @Bean
    CommandLineRunner initDatabase(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        return args -> {
            if (accountRepository.count() == 0) {
                Account account1 = Account.builder()
                        .name("John Doe")
                        .email("john.doe@example.com")
                        .accountId("123")
                        .balance(new BigDecimal("1000.00"))
                        .build();

                Account account2 = Account.builder()
                        .name("Jane Doe")
                        .email("jane.doe@example.com")
                        .accountId("456")
                        .balance(new BigDecimal("2000.00"))
                        .build();

                accountRepository.saveAll(List.of(account1, account2));

                Transaction transaction1 = Transaction.builder()
                        .accountId(account1.getId())
                        .totalAmount(new BigDecimal("150.00"))
                        .mcc("5812")
                        .merchant("Restaurant A")
                        .build();

                Transaction transaction2 = Transaction.builder()
                        .accountId(account2.getId())
                        .totalAmount(new BigDecimal("200.00"))
                        .mcc("5411")
                        .merchant("Grocery Store B")
                        .build();

                transactionRepository.saveAll(List.of(transaction1, transaction2));

                System.out.println("Seed data has been inserted");
            } else {
                System.out.println("Seed data already exists, skipping insertion");
            }
        };
    }
}