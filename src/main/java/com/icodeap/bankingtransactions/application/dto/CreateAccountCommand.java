package com.icodeap.bankingtransactions.application.dto;

public record CreateAccountCommand(
        String customerId,
        double initialBalance
) {
}
