package com.authorization.cajuchallenge;

import com.authorization.cajuchallenge.model.Account;
import com.authorization.cajuchallenge.model.BalanceTypes;
import com.authorization.cajuchallenge.model.Transaction;
import com.authorization.cajuchallenge.model.enums.MccCode;
import com.authorization.cajuchallenge.model.response.CreateTransactionResponse;
import com.authorization.cajuchallenge.repository.AccountRepository;
import com.authorization.cajuchallenge.repository.TransactionRepository;
import com.authorization.cajuchallenge.service.BalanceService;
import com.authorization.cajuchallenge.service.MerchantService;
import com.authorization.cajuchallenge.service.TransactionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class TransactionServiceTest {

    @Mock
    private BalanceService balanceService;

    @Mock
    private MerchantService merchantService;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    private Account account;
    private BalanceTypes balanceTypes;

    @BeforeEach
    void setUp() {
        try (AutoCloseable mocks = MockitoAnnotations.openMocks(this)) {
            // Setup is now within the try-with-resources block to avoid resource leakage
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        balanceTypes = new BalanceTypes();
        balanceTypes.setFood(new BigDecimal("500.00"));
        balanceTypes.setMeal(new BigDecimal("300.00"));
        balanceTypes.setCash(new BigDecimal("200.00"));

        account = Account.builder()
                .accountId("123456789")
                .balanceTypes(balanceTypes)
                .build();
    }

    @Test
    void testAuthorizeTransactionSuccess() {
        Transaction transaction = Transaction.builder()
                .accountId("123456789")
                .totalAmount(new BigDecimal("100.00"))
                .mcc("5812")
                .merchant("Restaurant A")
                .build();

        when(accountRepository.findByAccountId("123456789")).thenReturn(java.util.Optional.of(account));
        when(merchantService.getMccByMerchant("Restaurant A", "5812")).thenReturn(MccCode.MEAL);
        when(balanceService.getBalanceByMcc(balanceTypes, MccCode.MEAL)).thenReturn(new BigDecimal("300.00"));

        CreateTransactionResponse response = transactionService.authorize(transaction);
        assertEquals("00", response.getCode());
    }

    @Test
    void testAuthorizeTransactionInsufficientFunds() {
        Transaction transaction = Transaction.builder()
                .accountId("123456789")
                .totalAmount(new BigDecimal("1000.00"))
                .mcc("5812")
                .merchant("Restaurant A")
                .build();

        when(accountRepository.findByAccountId("123456789")).thenReturn(java.util.Optional.of(account));
        when(merchantService.getMccByMerchant("Restaurant A", "5812")).thenReturn(MccCode.MEAL);
        when(balanceService.getBalanceByMcc(balanceTypes, MccCode.MEAL)).thenReturn(new BigDecimal("300.00"));
        when(balanceService.getBalanceByMcc(balanceTypes, MccCode.CASH_OTHER)).thenReturn(new BigDecimal("200.00"));

        CreateTransactionResponse response = transactionService.authorize(transaction);
        assertEquals("51", response.getCode());
    }

    @Test
    void testAuthorizeTransactionFallbackToCash() {
        Transaction transaction = Transaction.builder()
                .accountId("123456789")
                .totalAmount(new BigDecimal("100.00"))
                .mcc("5411")
                .merchant("UNKNOWN MERCHANT")
                .build();

        when(accountRepository.findByAccountId("123456789")).thenReturn(java.util.Optional.of(account));
        when(merchantService.getMccByMerchant("UNKNOWN MERCHANT", "5411")).thenReturn(MccCode.CASH_OTHER);
        when(balanceService.getBalanceByMcc(balanceTypes, MccCode.CASH_OTHER)).thenReturn(new BigDecimal("200.00"));

        CreateTransactionResponse response = transactionService.authorize(transaction);
        assertEquals("00", response.getCode());
    }
}