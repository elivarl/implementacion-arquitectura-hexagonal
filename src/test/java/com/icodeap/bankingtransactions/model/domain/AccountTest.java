package com.icodeap.bankingtransactions.model.domain;

import com.icodeap.bankingtransactions.domain.exception.InsufficientBalanceException;
import com.icodeap.bankingtransactions.domain.model.Account;
import com.icodeap.bankingtransactions.domain.model.AccountId;
import com.icodeap.bankingtransactions.domain.model.Money;
import com.icodeap.bankingtransactions.domain.model.TransactionType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {

    //AAA

    // arrange - inicializacion
    // Act - realizar la accion
    //Afirm - Afirmacion

    @Test
    void should_deposit_money_correctly(){
        // arrange
        Account account = new Account(AccountId.newId(), "123", Money.of(100));
        // act
        account.deposit(Money.of(50));
        //afirm
        assertEquals(Money.of(150).getAmount(), account.getBalance().getAmount() );
        assertFalse(account.getTransactions().isEmpty());
        assertEquals(TransactionType.DEPOSIT, account.getTransactions().getFirst().getTransactionType());

    }

    @Test
    void should_not_allow_withdraw_when_insufficient_balance(){
        // arrange
        Account account = new Account(AccountId.newId(), "123", Money.of(100));
        // act
        InsufficientBalanceException ex = assertThrows(
                InsufficientBalanceException.class, ()->account.withdraw(Money.of(150))
        );

        //afirm
        assertNotNull(ex.getMessage());
        assertEquals(Money.of(100).getAmount(), account.getBalance().getAmount());
        assertTrue(account.getTransactions().isEmpty());


    }
}
