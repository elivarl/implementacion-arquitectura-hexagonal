package com.icodeap.bankingtransactions.infrastructure.adapter;

import com.icodeap.bankingtransactions.domain.model.Account;
import com.icodeap.bankingtransactions.domain.model.AccountId;
import com.icodeap.bankingtransactions.domain.port.AccountRepository;
import com.icodeap.bankingtransactions.infrastructure.mapper.AccountMapper;
import com.icodeap.bankingtransactions.infrastructure.persistence.AccountEntity;
import com.icodeap.bankingtransactions.infrastructure.repository.SpringDataAccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class AccountRepositoryAdapter implements AccountRepository {

    private final SpringDataAccountRepository springDataAccountRepository;

    @Override
    public Account save(Account account) {
        AccountEntity accountEntity = AccountMapper.toEntity(account);
        AccountEntity saved = springDataAccountRepository.save(accountEntity);
        return AccountMapper.toDomain(saved);
    }

    @Override
    public Optional<Account> findById(AccountId accountId) {
        return springDataAccountRepository.findById(accountId.value()).map(
                AccountMapper::toDomain
        );
    }
}
