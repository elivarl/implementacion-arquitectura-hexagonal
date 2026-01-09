package com.icodeap.bankingtransactions.infrastructure.web.dto;

public record TransactionSumary(
        String id, String type, double amount
) {
}
