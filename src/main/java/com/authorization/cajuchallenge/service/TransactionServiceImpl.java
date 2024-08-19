package com.authorization.cajuchallenge.service;

import com.authorization.cajuchallenge.model.Account;
import com.authorization.cajuchallenge.model.Transaction;
import com.authorization.cajuchallenge.model.enums.MccCode;
import com.authorization.cajuchallenge.model.enums.TransactionStatusCode;
import com.authorization.cajuchallenge.model.response.CreateTransactionResponse;
import com.authorization.cajuchallenge.repository.AccountRepository;
import com.authorization.cajuchallenge.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final BalanceService balanceService;
    private final MerchantService merchantService;
    private final ConcurrentHashMap<String, ReentrantLock> accountLocks = new ConcurrentHashMap<>();

    public TransactionServiceImpl(TransactionRepository transactionRepository, AccountRepository accountRepository, BalanceService balanceService, MerchantService merchantService) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.balanceService = balanceService;
        this.merchantService = merchantService;
    }

    @Override
    public CreateTransactionResponse authorize(Transaction transaction) {
        String accountId = transaction.getAccountId();
        ReentrantLock lock = accountLocks.computeIfAbsent(accountId, id -> new ReentrantLock());

        lock.lock();
        try {
            Account account = accountRepository.findByAccountId(accountId).orElse(null);
            BigDecimal amount = transaction.getTotalAmount();

            if (account == null) {
                return new CreateTransactionResponse(TransactionStatusCode.ERROR.getCode());
            }

            MccCode mccCode = merchantService.getMccByMerchant(transaction.getMerchant(), transaction.getMcc());
            BigDecimal currentBalance = balanceService.getBalanceByMcc(account.getBalanceTypes(), mccCode);

            if (currentBalance.compareTo(amount) >= 0) {
                balanceService.deductBalance(account.getBalanceTypes(), mccCode, amount);
                accountRepository.save(account);
                transactionRepository.save(transaction);
                return new CreateTransactionResponse(TransactionStatusCode.SUCCESS.getCode());
            } else {
                BigDecimal cashBalance = account.getBalanceTypes().getCash();
                if (cashBalance.compareTo(amount) >= 0) {
                    balanceService.deductBalance(account.getBalanceTypes(), MccCode.CASH_OTHER, amount);
                    accountRepository.save(account);
                    transactionRepository.save(transaction);
                    return new CreateTransactionResponse(TransactionStatusCode.SUCCESS.getCode());
                }
            }

            return new CreateTransactionResponse(TransactionStatusCode.INSUFFICIENT_FUNDS.getCode());
        } finally {
            lock.unlock();
            accountLocks.remove(accountId);
        }
    }

    @Override
    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction getTransactionById(String id) {
        return transactionRepository.findById(id).orElse(null);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
}