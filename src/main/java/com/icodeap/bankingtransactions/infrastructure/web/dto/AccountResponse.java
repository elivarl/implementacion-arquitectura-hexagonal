package com.icodeap.bankingtransactions.infrastructure.web.dto;

import com.icodeap.bankingtransactions.application.dto.AccountDetailsDto;

import java.util.List;

public record AccountResponse(
        String id,
        String customerId,
        double balance,
        List<TransactionSumary> transactions
) {

    public static AccountResponse fromDto(AccountDetailsDto dto){
        List<TransactionSumary> transactionSumaries = dto.transactions().stream()
                .map(t -> new TransactionSumary(t.id(), t.type(), t.amount())).toList();

        return new AccountResponse(dto.id(), dto.customerId(), dto.balance(), transactionSumaries);
    }
}
