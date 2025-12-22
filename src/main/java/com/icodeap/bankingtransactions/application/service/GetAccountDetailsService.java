package com.icodeap.bankingtransactions.application.service;

import com.icodeap.bankingtransactions.application.dto.AccountDetailsDto;
import com.icodeap.bankingtransactions.application.dto.MapToAccountDetailsDto;
import com.icodeap.bankingtransactions.application.port.GetAccountDetailsUseCase;
import com.icodeap.bankingtransactions.domain.exception.AccountNotFoundException;
import com.icodeap.bankingtransactions.domain.model.AccountId;
import com.icodeap.bankingtransactions.domain.port.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class GetAccountDetailsService implements GetAccountDetailsUseCase {
    private final AccountRepository accountRepository;

    public GetAccountDetailsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional
    public AccountDetailsDto getById(String accountId) {
         var id = new AccountId(accountId);
         var account = accountRepository.findById(id)
                 .orElseThrow(()->new AccountNotFoundException(accountId));
        return MapToAccountDetailsDto.from(account);
    }
}
