package com.icodeap.bankingtransactions.domain.model;

import com.icodeap.bankingtransactions.domain.exception.InsufficientBalanceException;
import com.icodeap.bankingtransactions.domain.exception.NegativeMoneyException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Account {
    private final AccountId id;
    private final String customerId;
    private Money balance;
    private List<Transaction> transactions = new ArrayList<>();

    public Account(AccountId id, String customerId, Money initialBalance ){
        this.id = id;
        this.customerId = customerId;
        this.balance = initialBalance;
    }

    // deposit
    public void  deposit(Money amount){
        Objects.requireNonNull(amount,"Deposit amount must not be null");
        if (amount.getAmount().compareTo(BigDecimal.ZERO) <= 0)
            throw new NegativeMoneyException("Deposit amount must be greater than zero");

        this.balance = this.balance.add(amount);
        this.transactions.add(new Transaction(TransactionType.DEPOSIT, amount));
    }

    // retiro
    public  void withdraw(Money amount){
        Objects.requireNonNull(amount,"Withdraw amount must not be null");
        if (amount.getAmount().compareTo(BigDecimal.ZERO) <= 0)
            throw new NegativeMoneyException("Withdraw amount must be greater than zero");

        Money newBalance = this.balance.subtract(amount);
        if(newBalance.isNegative())
            throw  new InsufficientBalanceException("Insuficient funds for withdraw");
        this.balance = newBalance;
        this.transactions.add(new Transaction(TransactionType.WITHDRAW, amount));

    }

    public  List<Transaction> getTransactions(){
        return Collections.unmodifiableList(this.transactions);
    }

    public Money getBalance() {
        return balance;
    }
}
