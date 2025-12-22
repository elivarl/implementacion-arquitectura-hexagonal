package com.icodeap.bankingtransactions.application.dto;

public record TransactionDto(
        String id,
        String type,
        double amount
) {
}
