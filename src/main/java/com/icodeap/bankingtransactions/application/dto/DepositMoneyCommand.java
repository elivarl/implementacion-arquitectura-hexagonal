package com.icodeap.bankingtransactions.application.dto;

public record DepositMoneyCommand(
        String accountId,
        double amount
) {
}
