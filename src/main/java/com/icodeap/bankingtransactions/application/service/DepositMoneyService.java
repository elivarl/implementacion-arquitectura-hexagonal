package com.icodeap.bankingtransactions.application.service;

import com.icodeap.bankingtransactions.application.dto.AccountDetailsDto;
import com.icodeap.bankingtransactions.application.dto.DepositMoneyCommand;
import com.icodeap.bankingtransactions.application.dto.MapToAccountDetailsDto;
import com.icodeap.bankingtransactions.application.port.DepositMoneyUseCase;
import com.icodeap.bankingtransactions.domain.exception.AccountNotFoundException;
import com.icodeap.bankingtransactions.domain.model.AccountId;
import com.icodeap.bankingtransactions.domain.model.Money;
import com.icodeap.bankingtransactions.domain.port.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class DepositMoneyService implements DepositMoneyUseCase {
    private final AccountRepository accountRepository;

    public DepositMoneyService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Override
    @Transactional
    public AccountDetailsDto deposit(DepositMoneyCommand command) {
        var accountId = new AccountId(command.accountId());

        var account = accountRepository.findById(accountId)
                .orElseThrow( ()-> new AccountNotFoundException(command.accountId()));

        account.deposit(Money.of(command.amount()));

        accountRepository.save(account);

        return MapToAccountDetailsDto.from(account);
    }
}
