package com.icodeap.bankingtransactions.application.port;

import com.icodeap.bankingtransactions.application.dto.AccountDetailsDto;
import com.icodeap.bankingtransactions.application.dto.CreateAccountCommand;

public interface CreateAccountUseCase {
    AccountDetailsDto createAccount(CreateAccountCommand command);
}
