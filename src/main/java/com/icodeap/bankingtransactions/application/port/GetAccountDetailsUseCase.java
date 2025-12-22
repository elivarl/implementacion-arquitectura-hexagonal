package com.icodeap.bankingtransactions.application.port;

import com.icodeap.bankingtransactions.application.dto.AccountDetailsDto;

public interface GetAccountDetailsUseCase {
    AccountDetailsDto getById(String accountId);
}
