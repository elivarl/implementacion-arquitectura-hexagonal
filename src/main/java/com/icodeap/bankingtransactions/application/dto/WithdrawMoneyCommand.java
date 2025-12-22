package com.icodeap.bankingtransactions.application.dto;

public record WithdrawMoneyCommand(
        String accountId,
        double amount
) {
}
