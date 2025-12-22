package com.icodeap.bankingtransactions.application.service;

import com.icodeap.bankingtransactions.application.dto.AccountDetailsDto;
import com.icodeap.bankingtransactions.application.dto.MapToAccountDetailsDto;
import com.icodeap.bankingtransactions.application.dto.WithdrawMoneyCommand;
import com.icodeap.bankingtransactions.application.port.WithdrawMoneyUseCase;
import com.icodeap.bankingtransactions.domain.exception.AccountNotFoundException;
import com.icodeap.bankingtransactions.domain.model.AccountId;
import com.icodeap.bankingtransactions.domain.model.Money;
import com.icodeap.bankingtransactions.domain.port.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class WithdrawMoneyService implements WithdrawMoneyUseCase {

    private final AccountRepository accountRepository;

    public WithdrawMoneyService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional
    public AccountDetailsDto withdraw(WithdrawMoneyCommand command) {

        var accountId = new AccountId(command.accountId());

        var account = accountRepository.findById(accountId)
                .orElseThrow( ()-> new AccountNotFoundException(command.accountId()));

        account.withdraw(Money.of(command.amount()));
        accountRepository.save(account);

        return MapToAccountDetailsDto.from(account);
    }
}
