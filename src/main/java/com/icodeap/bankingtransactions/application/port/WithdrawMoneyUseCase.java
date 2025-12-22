package com.icodeap.bankingtransactions.application.port;

import com.icodeap.bankingtransactions.application.dto.AccountDetailsDto;
import com.icodeap.bankingtransactions.application.dto.WithdrawMoneyCommand;

public interface WithdrawMoneyUseCase {
    AccountDetailsDto withdraw(WithdrawMoneyCommand command);
}
