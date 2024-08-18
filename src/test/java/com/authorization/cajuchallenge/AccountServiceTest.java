package com.authorization.cajuchallenge;

import com.authorization.cajuchallenge.model.Account;
import com.authorization.cajuchallenge.repository.AccountRepository;
import com.authorization.cajuchallenge.service.AccountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountServiceImpl accountService;

    @BeforeEach
    void setUp() {
        try (AutoCloseable mocks = MockitoAnnotations.openMocks(this)) {
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void testCreateAccount() {
        Account account = Account.builder()
                .accountId("123456")
                .build();

        when(accountRepository.save(account)).thenReturn(account);

        Account createdAccount = accountService.createAccount(account);
        assertNotNull(createdAccount);
        verify(accountRepository, times(1)).save(account);
    }

    @Test
    void testGetAccountById() {
        Account account = Account.builder()
                .id("1")
                .accountId("123456")
                .build();

        when(accountRepository.findById("1")).thenReturn(java.util.Optional.of(account));

        Account foundAccount = accountService.getAccountById("1");
        assertNotNull(foundAccount);
        assertEquals("123456", foundAccount.getAccountId());
    }
}