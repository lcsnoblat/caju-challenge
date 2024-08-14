package com.authorization.cajuchallenge.repository.configuration;

import com.authorization.cajuchallenge.model.Account;
import com.authorization.cajuchallenge.model.BalanceTypes;
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

                BalanceTypes balanceTypes1 = new BalanceTypes();
                balanceTypes1.setFood(new BigDecimal("500.00"));
                balanceTypes1.setMeal(new BigDecimal("300.00"));
                balanceTypes1.setCash(new BigDecimal("200.00"));

                BalanceTypes balanceTypes2 = new BalanceTypes();
                balanceTypes2.setFood(new BigDecimal("1000.00"));
                balanceTypes2.setMeal(new BigDecimal("600.00"));
                balanceTypes2.setCash(new BigDecimal("400.00"));

                Account account1 = Account.builder()
                        .name("John Doe")
                        .email("john.doe@example.com")
                        .accountId("123")
                        .balanceTypes(balanceTypes1)
                        .build();

                Account account2 = Account.builder()
                        .name("Jane Doe")
                        .email("jane.doe@example.com")
                        .accountId("456")
                        .balanceTypes(balanceTypes2)
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