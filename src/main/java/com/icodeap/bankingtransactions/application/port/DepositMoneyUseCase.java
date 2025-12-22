package com.icodeap.bankingtransactions.application.port;

import com.icodeap.bankingtransactions.application.dto.AccountDetailsDto;
import com.icodeap.bankingtransactions.application.dto.DepositMoneyCommand;

public interface DepositMoneyUseCase {
    AccountDetailsDto deposit(DepositMoneyCommand command);
}
