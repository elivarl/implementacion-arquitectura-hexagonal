package com.icodeap.bankingtransactions.application.service;

import com.icodeap.bankingtransactions.application.dto.AccountDetailsDto;
import com.icodeap.bankingtransactions.application.dto.CreateAccountCommand;
import com.icodeap.bankingtransactions.application.dto.MapToAccountDetailsDto;
import com.icodeap.bankingtransactions.application.port.CreateAccountUseCase;
import com.icodeap.bankingtransactions.domain.model.Account;
import com.icodeap.bankingtransactions.domain.model.AccountId;
import com.icodeap.bankingtransactions.domain.model.Money;
import com.icodeap.bankingtransactions.domain.port.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CreateAccountService implements CreateAccountUseCase {

    private final AccountRepository accountRepository;

    public CreateAccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional
    public AccountDetailsDto createAccount(CreateAccountCommand command) {
        Account account = new Account(
                AccountId.newId(),
                command.customerId(),
                Money.of(command.initialBalance())
        );
        Account saved = accountRepository.save(account);
        return MapToAccountDetailsDto.from(saved);
    }
}
