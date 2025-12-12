package com.icodeap.bankingtransactions.domain.port;

import com.icodeap.bankingtransactions.domain.model.Account;
import com.icodeap.bankingtransactions.domain.model.AccountId;

import java.util.Optional;

public interface AccountRepository {
    Account save (Account account);
    Optional<Account> findById(AccountId accountId);
}
